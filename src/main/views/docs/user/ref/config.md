---
Author: Chris Ellis
Date: 2014-08-17
Code: xml
---
# Bergamot Check Configuration

## Introduction

Bergamot currently uses XML to configure checks.  Hopefully, you will rarely need to interact with 
the configuration format, as you can create and modify checks via the Web Interface or API.  The 
check configuration is primarily used when importing from an existing Nagios installation or importing 
from an existing Bergamot installation.

## General Structure

Bergamot recursively loads any file ending in .xml within the configuration directory.  There is no 
restriction on the layout of the configuration directory except that every XML file is valid.  Each XML
file must start with the `<bergamot site="my.site.name">` root element, for example:

    <bergamot site="demo.bergamot-monitoring.org">
        <!-- Configuration elements here -->
    </bergamot>;
    
The site attribute corellates configuration for a particular site.  One Bergamot instance is capable of 
monitoring many, disconnected and isolated sites.  However, you cannot mix and match configuration for 
different sites within the same configuration file.  The site name is usually the domain name for that site.

## Comments

Add comments like you would in any XML file, as follows:

    <!-- My Comment -->

## Inheritance

Bergamot uses inheritance heavily to make the configuration as painless as possible.  All configuration objects 
support the use of inheritance.  A configuration object is able to inherit from multiple parents, in the order 
defined.  All properties are inherited, including services and traps on hosts and resources on clusters.  This 
makes it really easy to define a class of servers and apply it to multiple servers.

Any object defined with the attribute `template="yes"` is only used for inheritance purposes and will 
not load loaded as a check.  You are perfectly able to inherit from an object which is not a template.

It will pay off to get your head around inheritance early on, as it will make your live far easier in the future.
Take a look at the example configurations, to see the recommended approaches for configuring Bergamot.

Take a look at this short example of how inheritance behaves:

    <service name="generic-service" template="yes" enabled="yes" suppressed="no">
        <notifications enabled="yes" time-period="workhours"/>
        <notify teams="admins"/>
        <state failed-after="3" recovers-after="3"/>
        <schedule every="5" retry-every="1" time-period="24x7"/>
    </service>
    
    <service name="check_load" extends="" template="yes">
        <summary>Load Average</summary>
        <command extends="check_load"/>
    </service>
    
    <host name="generic-host" template="yes" enabled="yes" suppressed="no">
        <notifications enabled="yes" time-period="24x7"/>
        <notify teams="admins"/>
        <state failed-after="3" recovers-after="3"/>
        <schedule every="5" retry-every="1" time-period="24x7"/>
        <command extends="check_host_alive"/>
    </host>
    
    <host name="linux-server" extends="generic-host" template="yes">
        <service extends="check_load" />
    </host>
    
    <host name="localhost" extends="linux-server">
    </host/>
 
The host `localhost` is a `linux-server` which in turn is a `generic-host`.  As such the host `localhost`
will inherit the service `check_load`.  The notification and host check configuration would be inherited from `generic-host`.

The service `check_load` would itself inherit from the service `generic-service`.  As such it would inherit the notification and 
scheduling configuration from `generic-service`.

## Recommended Configuration Directory Structure

    /site.name
	/templates
	    /clusters.xml
	    /commands.xml
	    /contacts.xml
	    /hosts.xml
	    /resources.xml
	    /services.xml
	    /traps.xml
	/global
	    /contacts.xml
	    /groups.xml
	    /locations.xml
	    /time-periods.xml
	/some_service
	    /templates
		/clusters.xml
		/hosts.xml
		/resources.xml
		/services.xml
		/traps.xml
	    /clusters.xml
	    /hosts.xml

## Common Attributes and Elements

Every check configuration object has a set of common attributes and elements which can be specified.

### Attributes

`name="..."` The name of the object, required if the object is to be inherited.


`extends="...[, ...]"` The comma separated list of objects to inherit from, in order (left to right) of importance.


`template="yes/no"` Is this object a template or should it be imported into Bergamot

### Elements

`<summary>...</summary>` The summary (name displayed within the Web Interface) of the object.

`<description>...</description>` The long (multi-line) description of the object, used within the Web Interface.

## Configuring A Location

Locations represent physical locations where your infrastructure resides.  Locations form a tree, where a location can contain 
many locations but a location can only exist with-in one location. The general form for configuring a location is:

    <location name="aws-ireland" location="aws">
        <summary>Amazon Web Services - Ireland</summary>
    </location>

This would define the location `aws-ireland` as a child of the location `aws`.  The title 
in the web interface would be `Amazon Web Services - Ireland`. Should you wish for a group not to be a member of 
another group, simply do not specify the `location` attribute.

## Configuring A Group

A group is an arbitary grouping of checks (host, clusters, resources, services and traps).  Groups are a graph, 
with a group existing in many other groups (try to avoid cycles).  A group can contain many groups.  Bergamot does 
not discriminate based on what the group contains, for example you can mix hosts and services in the same group.

    <group name="linux-hosts" groups="hosts, linux">
        <summary>Linux Hosts</summary>
    </group>

This would define the group `linux-hosts` as a child of the groups `hosts` and `linux`.
The title in the web interface would be `Linux Hosts`.  Should you wish for a group not to be a member of 
another group, simply do not specify the `groups` attribute.

## Configuring A Team

Teams group Contacts, usually a Team mirrors a team of people within your organisation.  Teams may contain many sub teams 
and a team may be a member of many teams.  Teams are defined as follows:

    <team name="admins" teams="everyone">
        <summary>Admins</summary>
    </team>

This would define the team `admins` as a child of the team `everyone`.
The title in the web interface would be `Admins`.  Should you wish for a team not 
to be a member of another team, simply do not specify the `teams` attribute.

## Configuring A Time Period

Time Periods are calendars used to determind when something should occur.  Amongst other things, 
Time Periods are used to specify when checks should be scheduled, when notifications can be sent.

    <time-period name="24x7">
        <summary>24 Hours a day, every day of the year</summary>
        <time-range>00:00 - 24:00<time-range>
    </time-period>

Time Periods comprise of a set of time ranges, these are defined with `time-range` elements.   A time range 
is a range of hours with optional qualifiers. Examples of time ranges include:

    09:00 - 17:30
    monday 09:00 - 17:30
    monday 09:00 - 10:00, 12:00 - 13:00
    09:00 - 17:30
    january 1 00:00-24:00
    april 18 00:00-24:00
    april 21 00:00-24:00
    monday 1 may 00:00-24:00
    monday -1 may 00:00-24:00
    monday -1 august 00:00-24:00
    december 25 00:00-24:00
    december 26 00:00-24:00

Just like other configuration oject, Time Periods can extend other Time Periods, via the `extends` attribute.
Unlike other configuration objects a Time Period can exclude other Time Periods.  A Time Period which excludes other Time 
Periods will be valid when the excluded Time Periods are not valid.

For example we can define work hours (09:00 to 17:30 during weekdays, except for Bank Holidays).  We can then define out of 
hours which is the inverse of work hours, as follows:

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

A Contact represents someone (or thing) which should be notified when some thing goes wrong or recoverss.  A Contact 
will be notified when a check alerts and recovers.  A Contact is only notified during a particular time period and can 
be notified via multiple Notification Engines (email, sms, etc).

You should define a generic Contact template, which contacts inherit from.  This template should define when and how a 
contact is notified.  For example:

    <contact name="generic-contact" template="yes">        
        <notifications enabled="yes" time-period="24x7" alerts="yes" recovery="yes">
            <notification-engine engine="email" enabled="yes" time-period="24x7"/>
            <notification-engine engine="sms" enabled="yes" time-period="24x7" alert="yes" recovery="no" ignore="pending, ok, warning"/>
        </notifications>
    </contact>

This would define a generic template which would be used by other Contacts.  Which sends notifications during the 
time period `24x7`.  The contact would be notified by `email` during work hours.  The contact would be notified by `sms` out side of work hours.

Now we can define a contact, as follows:

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

Bergamot provides a specific check engine for NRPE checks, the `nrpe` check engine.  This check engine does not require 
forking of a process to execute an NRPE check, this is lighter-weight and more efficient to installations which primarily check hosts 
via NRPE.  A generic check_nrpe command can be defined as follows:

    <command engine="nrpe" name="check_nrpe">
        <parameter name="command" description="The NRPE command, eg: check_load"></parameter>
        <parameter name="host" description="The host to connect to">#{host.address}</parameter>
        <parameter name="port" description="The port number">5666</parameter>
    </command>

To make life easier, a more specific Command can be defined, for example:

    <command name="check_load" extends="check_nrpe">
        <parameter name="command">check_load</parameter>
    </command>

## Configuring Checks

Bergamot has a few different types of checks:

* Hosts - a networked device which should be actively checked
* Services - something running on a Host which should be actively checked
* Traps - something running on a Host which is passively checked
* Clusters - a virtual Host which is computed based on the state of multiple Hosts
* Resources - a virtual Service which is computed from the state of multiple Services

Checks fall into three categories: active, passive and virtual.  An active check is scheduled and executed (polled) by Bergamot.  
A passive check is not scheduled and is not executed, instead it is watched by Bergamot.  A virtual check is computed from the 
state of dependent checks, the state of a virtual check is computed when a dependent check changes state.

### Configuring Hosts

A Host, is some form of networked device which is to be monitored.  A Host is actively checked using a Command and contains 
a set of Services and Traps which monitor what executes on the Host.










