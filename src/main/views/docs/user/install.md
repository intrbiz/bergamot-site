---
Author: Chris Ellis
Date: 2014-08-26
Code: bash
---
# Bergamot Install Guide

## Introduction

This guide details how to install Bergamot from scratch on a openSUSE 13.2 
server.  This guide only covers getting a simple installation up and running.

Currently an Open Build Service instance is used to build packages for:

 * openSUSE 13.1, 13.2, Tumbleweed, SLE 12
 * Centos 7
 * Red Hat Enterprise Linux 7
 * Fedora 20

Sadly packages are not available for Debian or Ubuntu, primarily due to 
a preference for systemd, please feel free to contribute packages :).

### Core Components

Bergamot is a distributed monitoring system, as such there are several components 
which need to be deployed:

 * Bergamot UI daemon
 * Bergamot email notification daemon
 * Bergamot SMS notification daemon (optional)
 * Bergamot Agent Manager
 * Bergamot nagios worker
 * Bergamot SNMP worker
 * Bergamot HTTP worker
 * Bergamot Agent worker
 * RabbitMQ server
 * PostgreSQL database server

For simple deployments it is recommended to install the following components on 
the master server:

 * Bergamot UI daemon
 * Bergamot email notification daemon
 * Bergamot SMS notification daemon (optional)
 * Bergamot Agent Manager
 * RabbitMQ server
 * PostgreSQL database server

Then deploy the worker daemons onto a separate server or many worker servers, 
it is acceptable to deploy all the worker components onto one worker server.

For a high availability deployment, you will need to cluster the PostgreSQL 
and RabbitMQ servers.

## Prerequisites

Before installing Bergamot two key dependencies which are relied upon heavily 
need to be installed: PostgreSQL and RabbitMQ.  Bergamot persists its state in a 
PostgreSQL database and uses RabbitMQ to pass messages between various 
components.

The PostgreSQL database server is only utilised by the Bergamot UI daemons, the 
RabbitMQ server is used by all Bergamot daemons.

### Installing RabbitMQ

RabbitMQ is available in the main openSUSE repositories, installing is a case 
of:

    root@demo:~ # zypper in rabbitmq-server rabbitmq-server-plugins

Next we want to enable the RabbitMQ management plugin:

    root@demo:~ # rabbitmq-plugins enable rabbitmq_management

Before we can configure users we need to start RabbitMQ:

    root@demo:~ # systemctl enable rabbitmq-server
    root@demo:~ # systemctl start rabbitmq-server

Now we can configure an admin user, we can use this to login to the web management 
interface:

    root@demo:~ # rabbitmqctl add_user admin abc123
    root@demo:~ # rabbitmqctl set_user_tags admin administrator
    root@demo:~ # rabbitmqctl set_permissions -p / admin '.*' '.*' '.*'

Now we can create a user for bergamot:

    root@demo:~ # rabbitmqctl add_user bergamot bergamot
    root@demo:~ # rabbitmqctl set_permissions -p / bergamot '.*' '.*' '.*'

Finally we have no need for the guest account so lets remove it:

    root@demo:~ # rabbitmqctl delete_user guest

You can access the web management interface by pointing your browser to: 
`http://my-server:15672` and logging in with the admin account we created above.

### Installing PostgreSQL

Bergamot requires at least version 9.3 of PostgreSQL, therefore we need to add 
an additional repository from the openSUSE build service:

    root@demo:~ # zypper ar http://download.opensuse.org/repositories/server:/database:/postgresql/openSUSE_13.2/server:database:postgresql.repo
    root@demo:~ # zypper ref

Now we can install PostgreSQL 9.3:

    root@demo:~ # zypper in postgresql93 postgresql93-server postgresql93-contrib

Once PostgreSQL is installed start it:

    root@demo:~ # systemctl enable postgresql
    root@demo:~ # systemctl start postgresql

Now alter a few PostgreSQL configuration settings:

    root@demo:~ # cd /var/lib/pgsql/
    root@demo:~ # vi postgresql.conf
    listen_addresses = '*'
    port = 5432
    full_page_writes = on

Then update the host based access:

    root@demo:~ # cd /var/lib/pgsql/
    root@demo:~ # vi pg_hba.conf
    local   all             all                                     trust
    host    all             all             127.0.0.1/32            trust
    host    all             all             ::1/128                 trust

Reload these changes:

    root@demo:~# systemctl restart postgresql

Lets create the user and database for bergamot:

    root@demo:~ # psql -U postgres
    postgres=# CREATE ROLE bergamot LOGIN NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION;
    postgres=# CREATE DATABASE bergamot WITH OWNER = bergamot ENCODING = 'UTF8';
    postgres=# \q

### Installing Nginx

The Bergamot UI uses Nginx as a web server, handling serving plain content and communicating 
to the Bergamot UI application server using SCGI.  This makes it possible to have multiple 
load balanced application servers.  While these application servers share session state which 
maintains user sessions should an application server fail, sticky session load balancing must 
be used.

With openSUSE 13.2 Nginx has moved into a different repository, so we need to add that repository:

    root@demo:~ # zypper ar http://download.opensuse.org/repositories/server:/http/openSUSE_13.2/server:http.repo
    root@demo:~ # zypper ref
    
We can now (optionally) install Nginx with:

    root@demo:~ # zypper in nginx
    
### Installing Monitoring Plugins

While not strictly required it is handy to have the core Monitoring Plugins available.

Again with openSUSE 13.2 these are in a dedicated repository, which we need to add:

    root@demo:~ # zypper ar http://download.opensuse.org/repositories/server:/monitoring/openSUSE_13.2/server:monitoring.repo
    root@demo:~ # zypper ref
    
We can now (optionally) install the Monitoring Plugins with:

    root@demo:~ # zypper in monitoring-plugins
    
## Installing Bergamot

The Bergamot repository must be installed to provided the packages needed:

    root@demo:~ # zypper ar http://obs.intrbiz.net:82/Bergamot/openSUSE_13.2/Bergamot.repo
    root@demo:~ # zypper ref

### Installing A Bergamot Master Node

Install the Bergamot master components:

    root@demo:~ # zypper in nginx bergamot-java bergamot-cli bergamot-ui bergamot-notifier-email bergamot-notifier-sms

The packages will install default daemon configuration files for you and the 
required nginx configuration.

You can start the Bergamot notification engines with:

    root@demo:~ # systemctl enable bergamot-notifier-email
    root@demo:~ # systemctl enable bergamot-notifier-sms
    root@demo:~ # systemctl start bergamot-notifier-email
    root@demo:~ # systemctl start bergamot-notifier-sms

You can start the Bergamot web interface with:

    root@demo:~ # systemctl enable bergamot-ui-cluster@node1
    root@demo:~ # systemctl enable nginx
    root@demo:~ # systemctl start bergamot-ui-cluster@node1
    root@demo:~ # systemctl start nginx

#### Configuring The UI Daemon

The default UI daemon configuration file is: `/etc/bergamot/ui/default.xml`, it 
will look like:

    <ui>
        <broker url="amqp://127.0.0.1" username="bergamot" password="bergamot"/>
        <database url="jdbc:postgresql://127.0.0.1:5432/bergamot" username="bergamot" password="bergamot"/>
    </ui>

You will need to alter the `password` attributes if you have used a different 
password when creating the RabbitMQ user and the PostgreSQL user.  Otherwise 
there is very little to configure.  If your RabbitMQ server or PostgreSQL server 
is not local, then update that as required.

Once you've created the basic UI daemon configuration file it is important to 
generate a security key.  The security key is used to sign authentication tokens 
issued by the the UI.  This key needs to remain static to avoid invalidating all 
issued authentication tokens, it also needs to be the same across all UI servers 
that are clustered.

Generate and store the security token using the following Bergamot CLI command:

    bergamot-cli admin security-key set

This will generate a new security key and update the UI daemon configuration 
file with it.

#### Configuring The Email Notification Daemon

The email notification daemon configuration file is: `/etc/bergamot/notifier/email.xml`, 
it will look like:

    <notifier threads="5" name="email.notifier.bergamot.local">
        <broker url="amqp://127.0.0.1" username="bergamot" password="bergamot"/>
        <notification-engine classname="com.intrbiz.bergamot.notification.engine.email.EmailEngine">
            <parameter name="mail.host"     value="smtp.domain"/>
            <parameter name="mail.tls"      value="true"/>
            <parameter name="mail.user"     value="user@domain"/>
            <parameter name="mail.password" value="my_password"/>
            <parameter name="from"          value="user@domain"/>
        </notification-engine>
    </notifier>

As with the UI daemon update the RabbitMQ broker configuration as needed.  Edit the 
email configuration parameters as required.

#### Configuring The SMS Notification Daemon

The SMS notification daemon configuration file is: `/etc/bergamot/notifier/sms.xml`, 
it will look like:

    <notifier threads="1" name="sms.notifier.bergamot.local">
        <broker url="amqp://127.0.0.1" username="bergamot" password="bergamot"/>
        <notification-engine classname="com.intrbiz.bergamot.notification.engine.sms.SMSEngine">
            <parameter name="twilio.account" value=""    description="The Twilio account SID"/>
            <parameter name="twilio.token"   value=""    description="The Twilio auth token"/>
            <parameter name="from"           value="+44" description="The from phone number"/>
        </notification-engine>
    </notifier>

Again configure the RabbitMQ broker with the same syntax as used in the UI 
configuration file.

The SMS daemon uses the Twilio SMS gateway service, to be able to send SMS 
notifications you will need to register for an account with Twilio.  Once you 
have a Twillio account update the configuration with: the account SID, auth token 
and SMS phone number.

#### Installing the Bergamot Agent Manager

The Bergamot Agent Manager is responsible for signing TLS certificates.  This 
daemon should ideally be deployed onto a dedicate server, to which access is 
strictly controlled, as it contains private key material.  For small deployments 
you can install the Bergamot Agent Manager onto the master server.

If you don't intend to use the Bergamot Agent, then you can skip the install of 
the Bergamot Agent Manager.

Install the Bergamot Agent Manager components:

    root@demo:~ # zypper in bergamot-java bergamot-agent-manager

The packages will install default daemon configuration files, which you should 
edit before starting the Bergamot Agent Manager.

You can start the Bergamot Agent Manager with:

    root@demo:~ # systemctl enable bergamot-agent-manager
    root@demo:~ # systemctl start bergamot-agent-manager

#### Configuring the Bergamot Agent Manager

The Bergamot Agent Manager daemon configuration file is: `/etc/bergamot/agent-manager.xml`, 
it will look like:

    <bergamot-agent-manager>
        <certificate-name country="GB" state="My County" locality="My City" organisation="My Org"/>
        <file-key-store base="/var/opt/bergamot/agent-manager/certificates"/>
        <broker password="bergamot" url="amqp://127.0.0.1" username="bergamot"/>
    </bergamot-agent-manager>

Configure the RabbitMQ broker with the same syntax as used in the UI 
configuration file.  You should also set the `country`, `state`, `locality` and 
`organisation` these values will be used to build a certificate distinguished 
name.  Should you move the certificate repository path (IE: to an encrypted 
disk), set the `path` attribute of the `file-key-store` element.

By default the Bergamot Agent Manager will store private keys and certificates 
under `/var/opt/bergamot/agent-manager/certificates`.  This will include the 
private key for the root certificate authority and a private key per site 
certificate authority.  No private key is stored per agent.

### Installing A Bergamot Worker Node

To install the Nagios / NRPE worker:

    root@demo:~ # zypper in bergamot-java bergamot-worker-nagios monitoring-plugins

To install the SNMP worker:

    root@demo:~# zypper in bergamot-java bergamot-worker-snmp
    
To install the HTTP worker:

    root@demo:~# zypper in bergamot-java bergamot-worker-http
    
To install the Bergamot Agent worker:

    root@demo:~# zypper in bergamot-java bergamot-worker-agent

You can start the Nagios / NRPE worker with:

    root@demo:~ # systemctl start bergamot-worker-nagios

You can start the SNMP worker with:

    root@demo:~ # systemctl start bergamot-worker-snmp

You can start the HTTP worker with:

    root@demo:~ # systemctl start bergamot-worker-http

You can start the Bergamot Agent worker with:

    root@demo:~ # systemctl start bergamot-worker-agent

#### Configuring The Nagios Worker Daemon

The Nagios/NRPE worker daemon configuration file is: `/etc/bergamot/worker/nagios.xml`, 
it will look like:

    <worker name="nagios.worker.bergamot.local">
        <broker url="amqp://127.0.0.1" username="bergamot" password="bergamot"/>
    </worker>

Again configure the RabbitMQ broker with the same syntax as used in the UI 
configuration file.

#### Configuring The SNMP Worker Daemon

The SNMP worker daemon configuration file is: `/etc/bergamot/worker/snmp.xml`, 
it will look like:

    <worker name="snmp.worker.bergamot.local">
        <broker url="amqp://127.0.0.1" username="bergamot" password="bergamot"/>
        <parameter description="The port to send SNMP requests from" name="snmp-port">8161</parameter>
    </worker>

Again configure the RabbitMQ broker with the same syntax as used in the UI 
configuration file.

#### Configuring The HTTP Worker Daemon

The HTTP worker daemon configuration file is: `/etc/bergamot/worker/http.xml`, 
it will look like:

    <worker name="http.worker.bergamot.local" threads="1">
        <broker url="amqp://127.0.0.1" username="bergamot" password="bergamot"/>
    </worker>

Again configure the RabbitMQ broker with the same syntax as used in the UI 
configuration file.

## Creating A Site

Now that Bergamot is installed, we need to create a site before we can go much 
further.  We can use the Bergamot admin CLI to create the site

    root@demo:~# bergamot-cli admin create-site 'bergamot.local' 'Local Bergamot instance' 'bergamot'

The above command create the site `bergamot.local` with the summary 
`Local Bergamot instance` and with two aliases.  Aliases are alternative names 
for a site, there is no limit on the number of aliases a site can have.  Note: 
wild-card aliases are not supported.

The site configuration will be created using the template configuration and 
stored in `/etc/bergamot/config/bergamot.local/` where the site name is 
`bergamot.local`.  This configuration is then imported into the database.

Once a site is created the all Bergamot UI daemons will need to be restarted.

You will need to update the `nginx` configuration with the site names / aliases. 
To do this, edit the file: `/etc/nginx/vhosts.d/bergamot.conf` and update the 
server name.  You may also want to add these aliases to `/etc/hosts` for local 
testing purposes.

You will now be able to login to the Bergamot site what was just created.  Point 
your browser to `http://bergamot.local/` and login with `admin` `bergamot`.

