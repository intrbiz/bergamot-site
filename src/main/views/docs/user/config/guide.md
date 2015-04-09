---
Author: Chris Ellis
Date: 2015-04-05
Code: xml
---
# Bergamot Configuration Guide

## Introduction

Bergamot Monitoring uses an XML format for configuring checks.  This XML 
configuration format is currently used within the user interface, API and also 
for manual configuration import using the `bergamot-cli` command.

When a site is created using the `bergamot-cli` command, the site configuration 
template is copied, edited and imported automatically.  By default the site 
configuration directory is: `/etc/bergamot/config/template`.

Once a site has been created configuration changes should be made from within 
the user interface using the admin configuration change interface.  This allows 
for configuration changes to be made online.  This allows for small and often 
configuration changes to be made.

## General Structure

Bergamot recursively loads any file ending in .xml within the configuration 
directory.  There is no restriction on the layout of the configuration directory 
except that every XML file is valid.  Each XML file must start with the 
`<bergamot site="my.site.name">` root element, for example:

    <bergamot site="demo.bergamot-monitoring.org">
        <!-- Configuration elements here -->
    </bergamot>
    
The site attribute correlates configuration for a particular site.  One Bergamot 
instance is capable of monitoring many, disconnected and isolated sites.  
However, you cannot mix and match configuration for different sites within the 
same configuration file.  The site name is usually the domain name for that 
site.

When using the user interface, the root XML element will be created 
automatically when you start a configuration change, all you need to do is enter
 the required configuration.

## Comments

Add comments like you would in any XML file, as follows:

    <!-- My Comment -->

## Inheritance

Bergamot uses inheritance heavily to make the configuration as painless as 
possible.  All configuration objects support the use of inheritance.  A 
configuration object is able to inherit from multiple parents, in the order 
defined.  All properties are inherited, including services and traps on hosts 
and resources on clusters.  This makes it really easy to define a class of 
servers and apply it to multiple servers.

Any object defined with the attribute `template="yes"` is only used for 
inheritance purposes and will not load loaded as a check.  You are perfectly 
able to inherit from an object which is not a template.

It will pay off to get your head around inheritance early on, as it will make 
your life far easier in the future.Take a look at the example configurations, 
to see the recommended approaches for configuring Bergamot.

Take a look at this short example of how inheritance behaves:

    <service name="generic-service" template="yes" enabled="yes" suppressed="no">
        <notifications enabled="yes" time-period="workhours"/>
        <notify teams="admins"/>
        <state failed-after="3" recovers-after="3"/>
        <schedule every="5" retry-every="1" time-period="24x7"/>
    </service>
    
    <host name="generic-host" template="yes" enabled="yes" suppressed="no">
        <notifications enabled="yes" time-period="24x7"/>
        <notify teams="admins"/>
        <state failed-after="3" recovers-after="3"/>
        <schedule every="5" retry-every="1" time-period="24x7"/>
        <command extends="check_host_alive"/>
    </host>
    
    <host name="linux-server" extends="generic-host" template="yes">
        <service name="check_load" extends="generic-service">
            <summary>Load Average</summary>
            <command extends="check_load"/>
        </service>
    </host>
    
    <host name="localhost" extends="linux-server">
    </host/>


The host `localhost` is a `linux-server` which in turn is a `generic-host`.  As 
such the host `localhost`will inherit the service `check_load`.  The 
notification and host check configuration would be inherited from 
`generic-host`.

The service `check_load` would itself inherit from the service 
`generic-service`.  As such it would inherit the notification and scheduling 
configuration from `generic-service`.

## Common Attributes and Elements

Every check configuration object has a set of common attributes and elements 
which can be specified.

### Attributes

`name="..."` The name of the object, required if the object is to be inherited.


`extends="...[, ...]"` The comma separated list of objects to inherit from, in 
order (left to right) of importance.


`template="yes/no"` Is this object a template or should it be imported into 
Bergamot

### Elements

`<summary>...</summary>` The summary (name displayed within the Web Interface) 
of the object.

`<description>...</description>` The long (multi-line) description of the 
object, used within the Web Interface.

`<parameter name="...">...</parameter>` All configuration object can have 
arbitrary name value pair parameters defined, these can then be referenced in 
value expressions, allowing for configuration to be defined where logical rather
 then where it is defined.

## Common Parameters

## `ui-icon`

    <parameter name="ui-icon">/path/to/icon.png</parameter>

Change the icon which is displayed in the user interface.  
This can be specified on `command`, `host`, `service`, `trap`, `cluster`, 
`resource` objects.  The value should be the URL path a 64px by 64px PNG icon.  
Note: the icon should use transparency to allow the check status to be visible.

## Configuring A Location

Locations represent physical locations where your infrastructure resides.  
Locations form a tree, where a location can contain many locations but a 
location can only exist with-in one location. The general form for configuring 
a location is:

    <location name="aws-ireland" location="aws">
        <summary>Amazon Web Services - Ireland</summary>
    </location>

This would define the location `aws-ireland` as a child of the location `aws`.  
The title in the web interface would be `Amazon Web Services - Ireland`. Should 
you wish for a group not to be a member of another group, simply do not specify 
the `location` attribute.

## Configuring A Group

A group is an arbitary grouping of checks (host, clusters, resources, services 
and traps).  Groups are a graph, with a group existing in many other groups (try
 to avoid cycles).  A group can contain many groups.  Bergamot does not 
 discriminate based on what the group contains, for example you can mix hosts 
 and services in the same group.

    <group name="linux-hosts" groups="hosts, linux">
        <summary>Linux Hosts</summary>
    </group>

This would define the group `linux-hosts` as a child of the groups `hosts` and 
`linux`.  The title in the web interface would be `Linux Hosts`.  Should you 
wish for a group not to be a member of another group, simply do not specify the 
`groups` attribute.

## Configuring A Team

Teams group Contacts, usually a Team mirrors a team of people within your 
organisation.  Teams may contain many sub teams and a team may be a member of 
many teams.  Teams are defined as follows:

    <team name="admins" teams="everyone">
        <summary>Admins</summary>
    </team>

This would define the team `admins` as a child of the team `everyone`.
The title in the web interface would be `Admins`.  Should you wish for a team 
not to be a member of another team, simply do not specify the `teams` attribute.

## Configuring A Time Period

Time Periods are calendars used to determind when something should occur.  
Amongst other things, Time Periods are used to specify when checks should be 
scheduled, when notifications can be sent.

    <time-period name="24x7">
        <summary>24 Hours a day, every day of the year</summary>
        <time-range>00:00 - 24:00<time-range>
    </time-period>

Time Periods comprise of a set of time ranges, these are defined with 
`time-range` elements.   A time range is a range of hours with optional 
qualifiers. Examples of time ranges include:

    09:00 - 17:30
    monday 09:00 - 17:30
    monday 09:00 - 10:00, 12:00 - 13:00
    january 1 00:00-24:00
    april 18 00:00-24:00
    april 21 00:00-24:00
    monday 1 may 00:00-24:00
    monday -1 may 00:00-24:00
    monday -1 august 00:00-24:00
    december 25 00:00-24:00
    december 26 00:00-24:00

Just like other configuration object, Time Periods can extend other Time Periods,
 via the `extends` attribute.Unlike other configuration objects a Time Period 
 can exclude other Time Periods.  A Time Period which excludes other Time 
Periods will be valid when the excluded Time Periods are not valid.

For example we can define work hours (09:00 to 17:30 during weekdays, except for
 Bank Holidays).  We can then define out of hours which is the inverse of work 
 hours, as follows:

    <time-period name="uk-holidays">
        <summary>UK Holidays</summary>
        <time-range>january 1 00:00-24:00</time-range>
        <time-range>april 18 00:00-24:00</time-range>
        <time-range>april 21 00:00-24:00</time-range>
        <time-range>monday 1 may 00:00-24:00</time-range>
        <time-range>monday -1 may 00:00-24:00</time-range>
        <time-range>monday -1 august 00:00-24:00</time-range>
        <time-range>december 25 00:00-24:00</time-range>
        <time-range>december 26 00:00-24:00</time-range>
    </time-period>

    <time-period name="workhours" excludes="uk-holidays">
        <summary>Work Hours - 09:00 to 17:30, Monday to Friday</summary>
        <time-range>monday    09:00 - 17:30<time-range>
        <time-range>tuesday   09:00 - 17:30<time-range>
        <time-range>wednesday 09:00 - 17:30<time-range>
        <time-range>thursday  09:00 - 17:30<time-range>
        <time-range>friday    09:00 - 17:30<time-range>
    </time-period>

    <time-period name="out-of-hours" excludes="workhours">
        <summary>Out Of Hours, Not Work Hours</summary>
    </time-period>

## Configuring A Contact

A Contact represents someone (or thing) which should be notified when some thing goes wrong or recovers, as well as 
controlling access to the user interface and API.

A Contact will be notified when a check alerts and recovers, Bergamot Monitoring will only send one notification 
for an alert and recovery.  A Contact is only notified during a particular time period and can be notified via 
multiple Notification Engines (email, sms, etc).

It is reccomended to define a generic Contact template, which contacts inherit from.  This template should define 
when and how a contact is notified by default.  For example:

    <contact name="generic-contact" template="yes">        
        <notifications enabled="yes" time-period="24x7" alerts="yes" recovery="yes">
            <notification-engine engine="email" enabled="yes" time-period="24x7"/>
            <notification-engine engine="sms" enabled="yes" time-period="24x7" alert="yes" recovery="no" ignore="pending, ok, warning"/>
        </notifications>
    </contact>

This template would send notifications during the time period `24x7`.  The contact would be notified by `email` during work hours and would be notified by `sms` out side of work hours.

We can now define a contact, which inheirts all the default configuration, as follows:

    <contact name="joe.bloggs" extends="generic-contact">
        <summary>Joe Bloggs</summary>
        <email>joe@example.com</email>
        <pager>+447000010203</pager>
    </contact>

## Configuring Notifications

Notification settings can be configured on contacts and checks.  The `notifications` element is used to define notification settings, 
notification settings configured on a check take priority then followed by the settings for a contact.  Note notification settings of 
a check cannot override the notification settings of a contact.

The following is an example of notification settings:

    <notifications enabled="yes" time-period="24x7" alerts="yes" recovery="yes">
        <notification-engine engine="email" enabled="yes" time-period="24x7"/>
        <notification-engine engine="sms" enabled="yes" time-period="24x7" alert="yes" recovery="no" ignore="pending, ok, warning"/>
    </notifications>

Bergamot supports multiple notification engines, a notification engine sends notifications via a particular method, be it email, SMS, etc. 
Different settings can be applied to different notification engines, say sending SMSes outside of working hours for alerts but otherwise 
sending emails.

## Configuring A Command

Commands define something, which may be executed in order to monitor something.  Commands are utilised by services and 
hosts to configure how they should be monitored.

Like every other configuration object, Commands are named via the `name` attribute.  Commands can make use 
of inheritance just like every other configuration object. Commands define the check engine which will execute them 
using the `engine` attribute.

Commands are parameterised and self documenting.  A Command will specify multiple parameters which are needed by the 
check engine in order to execute the command.  The Command can provide a default value for a parameter and can also 
provide a description of the purpose of the parameter.

Parameter values are able to use the Express expression language.  This allows for a Parameter to 
use the value of another defined Parameter.  Note, that Parameters are evaluated top to bottom, in the order they 
are defined.  As such you must place the most specific values at the top.  When inheritance is applied, the Parameters 
are merged, with the most specific value of a Parameter taking precedence.

The following example, defines a `check_ping` Command, it is a standard Nagios plugin and thus executed via 
the `nagios` check engine.  The nagios check engine requires the `command_line` parameter.  This is
the command line which will be executed.

    <command engine="nagios" name="check_ping">
        <parameter name="warning" description="The warning threshold, eg: 100.0,20%">100.0,20%</parameter>
        <parameter name="critical" description="The critical threshold, eg: 500.0,60%">500.0,60%</parameter>
        <parameter name="packets" description="The number of packets to send">5</parameter>
        <parameter name="command_line" >#{nagios.path}/check_ping -H #{host.address} -w #{warning} -c #{critical} -p #{packets}</parameter>
    </command>

Bergamot provides a specific check engine for NRPE checks, the `nrpe` check 
engine.  This check engine does not require forking of a process to execute an 
NRPE check, this is lighter-weight and more efficient to installations which 
primarily check hosts via NRPE.  A generic check_nrpe command can be defined 
as follows:

    <command engine="nrpe" name="check_nrpe">
        <parameter name="command" description="The NRPE command, eg: check_load"></parameter>
        <parameter name="host" description="The host to connect to">#{host.address}</parameter>
        <parameter name="port" description="The port number">5666</parameter>
    </command>

To make life easier, a more specific Command can be defined, for example:

    <command name="check_load" extends="check_nrpe">
        <parameter name="command">check_load</parameter>
    </command>

You can use the expression language to pull parameters from related objects and 
to find the first non-null value.  For example, for SNMP checks we can define 
the SNMP community name at the `<location>` level and use this as the default 
for all hosts in that location, permitting overriding at the host level.

    <location name="my-datacentre">
        <parameter name="snmp_community">public</parameter>
    </location>
    
    <host name="some-host" location="my-datacentre">
    </host>
    
    <host name="another-host" location="my-datacentre">
        <parameter name="snmp_community">private</parameter>
    </host>
    
    <command name="check_snmp_name">
        <parameter name="snmp_community">#{coalesce(host.getParameter('snmp_community'), host.location.getParameter('snmp_community'), 'public')}</parameter>
    </command>

The expression language can be used in the values of `command` and 
`check-command` parameters.  An expression is always starts with `#{` and ends 
with `}`.  The expression language offers a powerful way to extract 
information from Bergamot objects, this allows for configuration to be stored 
where it is semantically relevant.

## Configuring Checks

Bergamot has a few different types of checks:

* Hosts - a networked device which should be actively checked
* Services - something running on a Host which should be actively checked
* Traps - something running on a Host which is passively checked
* Clusters - a virtual Host which is computed based on the state of multiple Hosts
* Resources - a virtual Service which is computed from the state of multiple Services

Checks fall into three types: active, passive and virtual.  An active check 
is scheduled and executed (polled) by Bergamot.  A passive check is not 
scheduled and is not executed, instead it is watched by Bergamot.  A virtual 
check is computed from the state of dependent checks, the state of a virtual 
check is computed when a dependent check changes state.

### Configuring Hosts

A Host, is some form of networked device which is to be monitored.  A Host is 
actively checked using a Command and contains a set of Services and Traps which 
monitor the Host.

For anyone used to Nagios, it is important to realise that Bergamot inherits 
services from host templates.  Bergamot's configuration differs significantly 
from Nagios in this regard, rather than defining services to hosts and host 
groups.

This difference makes Bergamot's configuration more akin to how servers often 
deployed, especially for environments which use automated deployment, via tools 
such as Puppet or Ansible.

Again it is recommended to make use of inheritance when defining hosts, first 
lets define a generic host template:

    <host name="generic-host" template="yes">
        <summary>Generic Host</summary>
        <notifications enabled="yes" time-period="24x7" all-engines="yes"/>
        <notify teams="admins"/>
        <state failed-after="4" recovers-after="10"/>
        <schedule every="5" retry-every="1" changing-every="1" time-period="24x7"/>
        <check-command command="check-host-alive"/>
        <description>A generic host template</description>
    </host>

This defines a generic template which contains the common check configuration.
We can break the above sample down as follows:

    <notifications enabled="yes" time-period="24x7" all-engines="yes"/>

The above configures when notifications will be sent for this check.  In this 
sample, the check will send notifications as per the `24x7` time period and to 
all engines.  The notification settings of a contact always take priority over 
the notification settings of a check.

    <notify teams="admins"/>

The `notify` element configures which teams and contacts should be notified for 
a given check.  Here notifications will be sent to all contacts in the `admins` 
team.  Individual contacts can be specified using the `contacts` attribute.

    <state failed-after="4" recovers-after="10"/>
    
The `state` element configures how the check will transition states.  It will 
take 4 non-ok check results before an alert is raised for the check and it will 
take 10 ok check results before a recovery is raised.

    <schedule every="5" retry-every="1" changing-every="1" time-period="24x7"/>

The `schedule` element configures how often a check will be executed. In this 
example the check will be executed every 5 minutes, under normal conditions. 
Should the check be changing between state then it will be executed every 1 
minute (as defined by the `changing-every` attribute.  If the check is in a 
hard non-ok state, then it will be executed every 1 minute (as defined by the 
`retry-every` attribute.  The `time-period` attribute defines a time period 
during which a check will be executed.  The `every`, `retry-every` and 
`changing-every` attributes configure a time interval, which defaults to 
minutes, other values maybe defined using the suffixes: `s`, `m`, `h`.  For 
example `30s` for every 30 seconds, `5m` for every 5 minutes, `2h` for every 
hour.  Currently only simple intervals can be defined, in order to define a 
check which executes every 1.5 minutes you would use: `90s`.

    <check-command command="check-host-alive"/>
    
The `check-command` element defines the command which will be executed in order 
to check this host, by default the `check-host-alive` 
command.

With the above generic host template defined, the simplest host can be defined 
as follows:

    <host name="gateway.local" address="192.168.1.1" location="office" groups="routers" extends="generic-host">
        <summary>Local Gateway</summary>
    </host>
    
The real power comes from being able to define services within host templates, 
for example:

    <host name="linux-server-nrpe" extends="generic-host" template="yes" groups="linux-servers">
        <summary>Linux Server (NRPE)</summary>
        <notify teams="linux-admins"/>
        <description>Default Linux server checked via NRPE template</description>
        <service extends="linux-service" name="load" groups="linux-services">
            <summary>Load</summary>
            <check-command command="check_nrpe_load"/>
        </service>
    </host>
    
The above example defines a host template with one services, which checks the 
host load average via NRPE.

The above template can then be used as follows:

    <host name="a.linux.server" address="127.0.0.1" location="office" extends="linux-server-nrpe">
        <summary>A Linux Server</summary>
    </host>

### Configuring Services

A Service is some specific aspect of a host which must be monitored.  This 
could be a process which should be running, a TCP socket which should be 
listening, the free space of a file system, etc.  Services are actively checked 
using a command, Bergamot Monitoring schedules and executes services 
periodically.

Services are configured much the same as host, being active checks they share a 
lot of the same configuration options.  Services maybe defined within a host or 
at the top level, any services defined at the top level must be templates.
Services define within  a host do not need to specify if they are templates or 
not, this is specified from the host they are contained by.

It is recommended to define a generic service template that all services will 
inherit from, this generic template will define common configuration options:

    <service template="yes" name="generic-service">
        <summary>Generic Service</summary>
        <notifications enabled="yes" time-period="24x7" all-engines="yes"/>
        <notify teams="admins"/>
        <state failed-after="4" recovers-after="10"/>
        <schedule every="5" retry-every="1" time-period="24x7"/>
        <description>A generic service template</description>
    </service>

This defines a generic template which contains the common check configuration.
We can break the above sample down as follows:

    <notifications enabled="yes" time-period="24x7" all-engines="yes"/>

The above configures when notifications will be sent for this check.  In this 
sample, the check will send notifications as per the `24x7` time period and to 
all engines.  The notification settings of a contact always take priority over 
the notification settings of a check.

    <notify teams="admins"/>

The `notify` element configures which teams and contacts should be notified for 
a given check.  Here notifications will be sent to all contacts in the `admins` 
team.  Individual contacts can be specified using the `contacts` attribute.

    <state failed-after="4" recovers-after="10"/>
    
The `state` element configures how the check will transition states.  It will 
take 4 non-ok check results before an alert is raised for the check and it will 
take 10 ok check results before a recovery is raised.

    <schedule every="5" retry-every="1" changing-every="1" time-period="24x7"/>

The `schedule` element configures how often a check will be executed. In this 
example the check will be executed every 5 minutes, under normal conditions. 
Should the check be changing between state then it will be executed every 1 
minute (as defined by the `changing-every` attribute.  If the check is in a 
hard non-ok state, then it will be executed every 1 minute (as defined by the 
`retry-every` attribute.  The `time-period` attribute defines a time period 
during which a check will be executed.  The `every`, `retry-every` and 
`changing-every` attributes configure a time interval, which defaults to 
minutes, other values maybe defined using the suffixes: `s`, `m`, `h`.  For 
example `30s` for every 30 seconds, `5m` for every 5 minutes, `2h` for every 
hour.  Currently only simple intervals can be defined, in order to define a 
check which executes every 1.5 minutes you would use: `90s`.

Because services are configured as part of hosts, it makes more sense to 
concentrate effort on creating host templates for your infrastructure.  You 
can define service template for each service you will have, however this might 
result in too much overhead to be easily maintainable.

It is suggested to define specific service template for each grouping of 
services you have.  For example define service templates for each combination 
of grouping and notification teams you have:

    <service name="linux-service" groups="linux-services" extends="generic-service" template="yes">
        <notify teams="linux-admins"/>
    </service>

The above would define a generic Linux service, which by default places checks 
into the group `linux-services` and will notify the `linux-admins` team.

You can then make use of these templates when defining a service on a host 
as follows:

    <host>
        <service name="nginx" extends="linux-service">
            <summary>Nginx Web Server</summary>
            <check-command command="check_process_nginx" />
        </service>    
    </host>

### Configuring Traps

A trap is an aspect of a host which should be monitored passively.  Traps are 
similar to services with one big difference, a trap watches something rather 
than actively polling something.  Traps are NOT scheduled or executed, instead 
some external input pushes information into Bergamot Monitoring.  The best 
example of traps are SNMP traps sent by networking devices.

Traps are passive checks and share some core options with services. Like 
services, traps are defined within hosts with templates being defined at the 
top level.  Traps defined within a host do not need to specify if they are 
templates or not, this is specified from the host they are contained by.

It is recommended to define a generic trap template that all traps will inherit 
from, this generic template will define common configuration options:

    <trap template="yes" name="generic-trap">
        <summary>Generic Trap</summary>
        <notifications enabled="yes" time-period="24x7" all-engines="yes"/>
        <notify teams="admins"/>
        <state failed-after="1" recovers-after="1"/>
        <initially status="ok" output="OK"/>
        <description>A generic trap template</description>
    </trap>

This defines a generic template which contains the common check configuration.
We can break the above sample down as follows:

    <notifications enabled="yes" time-period="24x7" all-engines="yes"/>

The above configures when notifications will be sent for this check.  In this 
sample, the check will send notifications as per the `24x7` time period and to 
all engines.  The notification settings of a contact always take priority over 
the notification settings of a check.

    <notify teams="admins"/>

The `notify` element configures which teams and contacts should be notified for 
a given check.  Here notifications will be sent to all contacts in the `admins` 
team.  Individual contacts can be specified using the `contacts` attribute.

    <state failed-after="4" recovers-after="10"/>
    
The `state` element configures how the check will transition states.  It will 
take 4 non-ok check results before an alert is raised for the check and it will 
take 10 ok check results before a recovery is raised.

    <initially status="ok" output="OK"/>

The `initially` element defines the initial status for a check.  This is 
important for traps, as a result might never be received.  Checks default to 
being `pending` initially, for a Trap we usually want to set this to `ok`. The 
`status` attribute set the initial status of the check, this can be one 
of: `pending`, `info`, `ok`, `warning`, `critical`, `unknown`, `timeout`, 
`error`, `disconnected`, `action`.  The `output` attribute specified the 
textual output which will be displayed initially.

Because traps are configured as part of hosts, it makes more sense to 
concentrate effort on creating host templates for your infrastructure.  You 
can define trap template for each trap you will have, however this might 
result in too much overhead to be easily maintainable.

It is suggested to define specific trap template for each grouping of 
traps you have.  For example define trap templates for each combination 
of grouping and notification teams you have:

    <trap name="network-trap" extends="generic-trap" template="yes">
        <notify teams="network-admins"/>
    </trap>

The above would define a generic network trap, which by default places checks 
into the group `network-services` and will notify the `network-admins` team.

You can then make use of these templates when defining a trap on a host 
as follows:

    <host>
        <trap name="port-E/01/01" extends="network-trap">
            <summary>Port E/01/01</summary>
            <check-command command="snmp-link-trap">
                <parameter name="interface-name">E/1/01</parameter>
            </check-command>
        </service>    
    </host>

### Configuring Clusters

Clusters represent a cooperative set of hosts.  Clusters are designed to 
monitor, high availability clusters, whereby a set of hosts provide something.

Clusters are virtual checks, they do not actually check anything.  Instead the 
state of a cluster is computed from the state of a set of dependent hosts, this 
calculation is made when ever a dependent host changes state.

Imagine a highly available website or database hosted by a number of servers.
As long as at least one host is ok, then the cluster is ok.

It is recommended to define a generic cluster template that all clusters will 
inherit from, this generic template will define common configuration options:

    <cluster template="yes" name="generic-cluster">
        <summary>Generic Cluster</summary>
        <notifications time-period="24x7"/>
        <notify teams="admins"/>
        <description>A generic cluster template</description>
    </cluster>

This defines a generic template which contains the common check configuration.
We can break the above sample down as follows:

    <notifications enabled="yes" time-period="24x7" all-engines="yes"/>

The above configures when notifications will be sent for this check.  In this 
sample, the check will send notifications as per the `24x7` time period and to 
all engines.  The notification settings of a contact always take priority over 
the notification settings of a check.

    <notify teams="admins"/>

The `notify` element configures which teams and contacts should be notified for 
a given check.  Here notifications will be sent to all contacts in the `admins` 
team.  Individual contacts can be specified using the `contacts` attribute.

The above template can then be used as follows:

    <cluster name="pgsql.cluster" extends="generic-cluster">
        <summary>PostgreSQL Cluster</summary>
        <condition>host 'pgsql1.local' or host 'pgsql2.local'</condition>
        <resource name="listener-pgsql" extends="generic-resource">
            <summary>Listener: PostgreSQL</summary>
            <condition>service 'listener-pgsql' on host 'pgsql1.local' or service 'listener-pgsql' on host 'pgsql2.local'</condition>
        </resource>
    </cluster>

Sadly with clusters inheritance is only really useful for managing the 
notification settings.

The actual logic of a cluster is specified by the `condition` element, this 
defines the virtual check expression which is used to compute the state of the 
cluster.  In the above example the cluster is based on the `or` relationship of 
two hosts, IE: it represents the best case of the two hosts.

### Configuring Resources

Resources represent a cooperative set of services or traps.  Resources are 
designed to monitor, high availability clustered services, whereby a set of 
services across a set of hosts provide something.

Resources are virtual checks, they do not actually check anything.  Instead the 
state of a resource is computed from the state of a set of dependent services 
and traps, this calculation is made when ever a dependent check changes state.

Imagine a highly available website or database hosted by a number of servers.
As long as the web server or database is running on at least one server then 
the resource is ok.

It is recommended to define a generic resource template that all resources 
will inherit from, this generic template will define common configuration 
options:

    <resource template="yes" name="generic-resource">
        <summary>Generic Resource</summary>
        <notifications enabled="yes" time-period="24x7" all-engines="yes"/>
        <notify teams="admins"/>
        <description>A generic resource template</description>
    </resource>

This defines a generic template which contains the common check configuration.
We can break the above sample down as follows:

    <notifications enabled="yes" time-period="24x7" all-engines="yes"/>

The above configures when notifications will be sent for this check.  In this 
sample, the check will send notifications as per the `24x7` time period and to 
all engines.  The notification settings of a contact always take priority over 
the notification settings of a check.

    <notify teams="admins"/>

The `notify` element configures which teams and contacts should be notified for 
a given check.  Here notifications will be sent to all contacts in the `admins` 
team.  Individual contacts can be specified using the `contacts` attribute.

The above template can then be used as follows:

    <resource name="listener-pgsql" extends="generic-resource">
        <summary>Listener: PostgreSQL</summary>
        <condition>service 'listener-pgsql' on host 'pgsql1.local' or service 'listener-pgsql' on host 'pgsql2.local'</condition>
    </resource>

## Advice For Sane Configuration

### Define Commands

Commands define how to check something, as such they naturally define a point 
of reuse, many checks use the same command.  It is recommended to define a 
command for each NRPE or Bergamot Agent command you may have.

### Use Host Templates

Most infrastructures have many hosts which follow similar patterns, be it lots 
of web servers or lots of generic Linux servers.  As such it makes sense to 
define templates for each of these patterns.  Apply these templates to individual 
hosts and avoid duplicating configuration.  Remember a host can inherit from 
multiple templates, and remember that services are inherited from templates.

## FAQ

TODO













