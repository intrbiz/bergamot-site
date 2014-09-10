---
Author: Chris Ellis
Date: 2014-08-26
Code: bash
---
# Bergamot Install Guide

## Introduction

This guide details how to install Bergamot from scratch on a openSUSE 13.1 
server.  This guide only covers getting a simple installation up and running, 
with one UI / master server and multiple worker servers.  

Currently we use an Open Build Service instance to offer packages for: 

 * openSUSE 13.1
 * Centos 7
 * Red Hat Enterprise Linux 7
 * Fedora 20

Sadly we currently do not have packages for Debian or Ubuntu, primarily due to 
a reliance on systemd, please feel free to contribute packages :).

### Core Components

Bergamot is a distributed monitoring system, as such there are several components 
which need to be deployed:

 * Bergamot UI daemon
 * Bergamot email notification daemon
 * Bergamot sms notification daemon (optional)
 * Bergamot nagios worker
 * Bergamot SNMP worker (optional)
 * Bergamot SNMP watcher (optional)
 * RabbitMQ server
 * PostgreSQL database server

For simple deployments it is recommended to install the following components on 
the master server:

 * Bergamot UI daemon
 * Bergamot email notification daemon
 * Bergamot sms notification daemon (optional)
 * RabbitMQ server
 * PostgreSQL database server

Then to deploy the worker daemons onto a separate server or many worker servers, 
it is acceptable to deploy all the worker components onto one worker server.

## Prerequisites

Before we can install Bergamot we must install two key components that Bergamot 
heavily relies upon: PostgreSQL and RabbitMQ.  Bergamot persists its state in a 
PostgreSQL database and uses RabbitMQ to pass messages between various 
components.  The PostgreSQL database server is only utilised by the Bergamot UI 
daemons, the RabbitMQ server is used by all Bergamot daemons.

### Installing RabbitMQ

RabbitMQ is available in the main openSUSE repositories, installing is a case 
of:

    root@demo:~ # zypper in rabbitmq-server rabbitmq-server-plugins

Next we want to enable the RabbitMQ management plugin:

    root@demo:~ # rabbitmq-plugins enable rabbitmq_management

Before we can configure users we need to start RabbitMQ:

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
http://my-server:15672 and logging in with the admin account we created above.

### Installing PostgreSQL

Bergamot requires at least version 9.3 of PostgreSQL, therefore we need to add 
an additional repository from the openSUSE build service:

    root@demo:~ # zypper ar http://download.opensuse.org/repositories/server:/database:/postgresql/openSUSE_13.1/server:database:postgresql.repo
    root@demo:~ # zypper ref

Now we can install PostgreSQL 9.3:

    root@demo:~ # zypper in postgresql93 postgresql93-server postgresql93-contrib

Once PostgreSQL is installed start it:

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

## Installing Bergamot

Before we can install the individual Bergamot packages we need to add the 
Bergamot repository:

    root@demo:~ # zypper ar http://obs.intrbiz.net:82/Bergamot/openSUSE_13.1/Bergamot.repo
    root@demo:~ # zypper ref

### Installing A Bergamot Master Node

Install the Bergamot master components:

    root@demo:~ # zypper in bergamot-java bergamot-cli bergamot-ui bergamot-notifier-email bergamot-notifier-sms

The packages will install default daemon configuration files for you and the 
required nginx configuration.

You can start the Bergamot notification engines with:

    root@demo:~ # systemctl start bergamot-notifier-email
    root@demo:~ # systemctl start bergamot-notifier-sms

You can start the Bergamot web interface with:

    root@demo:~ # systemctl start bergamot-ui
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
is not local host, then update that as required.

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
            <parameter name="twillo.account" value=""    description="The Twillo account SID"/>
            <parameter name="twillo.token"   value=""    description="The Twillo auth token"/>
            <parameter name="from"           value="+44" description="The from phone number"/>
        </notification-engine>
    </notifier>

Again configure the RabbitMQ broker with the same syntax as used in the UI 
configuration file.

The SMS daemon uses the Twillo SMS gateway service, to be able to send SMS 
notifications you will need to register for an account with Twillo.  Once you 
have a Twillio account update the configuration with: the account SID, auth token 
and SMS phone number.

### Installing A Bergamot Worker Node

To install the Nagios / NRPE worker:

    root@demo:~ # zypper in bergamot-java bergamot-worker-nagios nagios-plugins-all

To install the SNMP worker and watcher:

demo:~# zypper in bergamot-java bergamot-worker-snmp bergamot-watcher-snmp

You can start the Nagios / NRPE worker with:

    root@demo:~ # systemctl start bergamot-worker-nagios

You can start the SNMP worker with:

    root@demo:~ # systemctl start bergamot-worker-snmp

You can start the SNMP watcher with:

    root@demo:~ # systemctl start bergamot-watcher-snmp

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

#### Configuring The SNMP Watcher Daemon

The SNMP watcher daemon configuration file is: `/etc/bergamot/watcher/snmp.xml`, 
it will look like:

    <watcher name="snmp.watcher.bergamot.local" site="00000000-0000-0000-8000-000000000000">
        <broker url="amqp://127.0.0.1" username="bergamot" password="bergamot"/>
        <parameter description="The port to send SNMP requests from" name="snmp-port">8162</parameter>
    </watcher>

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

