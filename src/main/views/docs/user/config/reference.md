---
Author: Chris Ellis
Date: 2015-04-05
Code: xml
---
# Bergamot Configuration Reference

<p><a id="contents"></a></p>
## Contents

* [Security Domain](#security_domain)
* [Team](#team)
* [Contact](#contact)
* [Location](#location)
* [Group](#group)
* [Time Period](#time_period)
* [Command](#command)
* [Host](#host)
* [Service](#service)
* [Trap](#trap)
* [Cluster](#cluster)
* [Resource](#resource)

<p><a id="security_domain"></a></p>
## Security domain

[Back to contents](#contents)

A security domain defines a set of checks which share common access controls.

    <security-domain name="...">
        <summary>...</summary>
        <description>...</description>
    </security-domain>

<p><a id="team"></a></p>
## Team

[Back to contents](#contents)

A team contains multiple contacts, making it easy to send notifications to 
multiple contacts.  Permissions can also be granted and revoked at the team 
level.

    <team name="..." extends="..., ..." template="yes/no" teams="..., ..." grants="..., ..." revokes="..., ..." security-domains="...[, ...]">
        <summary>...</summary>
        <description>...</description>
        <access-control security-domain="..." grants="...[, ...]" revokes="...[, ...]"/>
        <parameter description="..." name="...">...</parameter>
    </team>
    
### Attributes

    name="..."

The team name, used to reference this team from other configuration objects.

    extends="..., ..."

A comma separated list of templates to inherit from.

    template="yes/no"

Is this a template: yes / no.

    teams="..., ..."

A comma separated list of team names that this team is a member of.

    grants="..., ..."

A comma separated list of permissions which are granted to all members in this team.

    revokes="..., ..."

A comma separated list of permissions which are revoked (not granted) to all members in this team.

     security-domains="...[, ...]"

A comma separated list of security domain names which this team is in.

### Elements

    <summary>...</summary>

The displayed name of this team.

    <description>...</description>

An optional long, multi-line description of this team.

    <access-control security-domain="..." grants="...[, ...]" revokes="...[, ...]"/>

Define permissions over a specific domain of objects.

    <parameter description="..." name="...">...</parameter>

An arbitrary name value pair.  The parameter name is given by the `name` attribute.  
The parameter value is given by either a `value` attribute or as the element text. 
Optionally a description of the parameter maybe given using the `description` attribute.

<p><a id="contact"></a></p>
## Contact

[Back to contents](#contents)

Contacts are people (or machines) which should be notified when something goes 
wrong (or goes right).  Contacts are also users of the user interface and or API.

    <contact name="..." extends="..., ..." template="yes/no" teams="..., ..." grants="..., ..." revokes="..., ..." security-domains="...[, ...]">
        <summary>...</summary>
        <description>...</description>
        <first-name>...</first-name>
        <preferred-name>...</preferred-name>
        <family-name>...</family-name>
        <full-name>...</full-name>
        <email>...</email>
        <pager>...</pager>
        <mobile>...</mobile>
        <phone>...</phone>
        <im>...</im>
        <notifications enabled="yes/no" time-period="..." alerts="yes/no" recovery="yes/no" ignore="..., ..." all-engines="yes/no">
            <notification-engine engine="..." enabled="yes/no" time-period="..." alerts="yes/no" recovery="yes/no" ignore="..., ..."/>
        </notifications>
        <access-control security-domain="..." grants="...[, ...]" revokes="...[, ...]"/>
        <parameter description="..." name="...">...</parameter>
    </contact>

### Attributes

    name="..."

The contact name, used to reference this contact from other configuration objects.

    extends="..., ..."

A comma separated list of templates to inherit from.

    template="yes/no"

Is this a template: yes / no.

    ### `grants="..., ..."

A comma separated list of permissions which are granted to this contact.

    revokes="..., ..."

A comma separated list of permissions which are revoked (not granted) to this contact.

     security-domains="...[, ...]"

A comma separated list of security domain names which this contact is in.

### Elements

    <summary>...</summary>

The displayed name of this contact.

    <description>...</description>

An optional long, multi-line description of this contact.

    <parameter description="..." name="...">...</parameter>

An arbitrary name value pair.  The parameter name is given by the `name` attribute.  
The parameter value is given by either a `value` attribute or as the element text. 
Optionally a description of the parameter maybe given using the `description` attribute.

    <first-name>...</first-name>

This first name of this contact.

    <preferred-name>...</preferred-name>

This preferred name of this contact, for example Christopher might normally be 
called Chris.  This is optional.

    <family-name>...</family-name>

This family name of this contact.

    <full-name>...</full-name>

The complete name of the contact, this is normally computed from the first or 
preferred name and the family name.

    <email>...</email>

This email address of the contact, to which notifications should be sent.

    <pager>...</pager>

The phone number to which SMS notifications should be sent.

    <mobile>...</mobile>

The mobile phone number to which SMS notifications could be sent.  Note the 
pager phone number is used in preference.

    <phone>...</phone>

A phone number for this contact, not currently used to send notifications too.

    <im>...</im>

The instance messaging address for this contact, not currently used.

    <access-control security-domain="..." grants="...[, ...]" revokes="...[, ...]"/>

Define permissions over a specific domain of objects.

    <notifications enabled="yes/no" time-period="..." alerts="yes/no" recovery="yes/no" ignore="..., ..." all-engines="yes/no">
        <notification-engine engine="..." enabled="yes/no" time-period="..." alerts="yes/no" recovery="yes/no" ignore="..., ..."/>
    </notifications>
    
Configure how and when notifications can be sent to this contact.

#### Attributes

    enabled="yes/no"

Can notifications be sent to this contact: yes or no.

    time-period="..."

The name of a time period which defines when notifications can be sent to this 
contact.  Notifications will only be sent to this contact during this time period.

    alerts="yes/no"

Can alert notifications be sent to this contact: yes or no.

    recovery="yes/no"

Can recovery notifications be sent to this contact: yes or no.

    ignore="..., ..."

A comma separated list of statuses for which notifications should not be sent, 
allowed statues are: `pending`, `info`, `ok`, `warning`, `critical`, `unknown`, 
`timeout`, `error`, `disconnected`, `action`.

    all-engines="yes/no"

Should notifications be sent using all notification engines that are available or 
should notifications only be sent using the configured engines: yes or no.

#### Elements

    <notification-engine engine="..." enabled="yes/no" time-period="..." alerts="yes/no" recovery="yes/no" ignore="..., ..."/>

Configure a specific notification engine which can be used to notify a contact.

##### Attributes

    engine="..."

The name of the engine which will be used to send the notification, for 
example: `email`, `sms`.

    enabled="yes/no"

Can notifications be sent via this engine: yes or no.

    time-period="..."

The name of a time period which defines when notifications can be sent via this 
engine to this contact.  Notifications will only be sent to this contact via 
this engine during this time period.

    alerts="yes/no"

Can alert notifications be sent to this contact via this engine: yes or no.

    recovery="yes/no"

Can recovery notifications be sent to this contact via this engine: yes or no.

    ignore="..., ..."

A comma separated list of statuses for which notifications should not be sent 
via this engine, allowed statues are: `pending`, `info`, `ok`, `warning`, 
`critical`, `unknown`, `timeout`, `error`, `disconnected`, `action`.

<p><a id="location"></a></p>
## Location

[Back to contents](#contents)

Locations are physical locations which contain hosts, this could be an office, 
a data centre.  Locations can also be contained in larger locations, it could be 
that an office is located in the UK.

    <location name="..." extends="..." template="yes/no" location="..." worker-pool="..." security-domains="...[, ...]">
        <summary>...</summary>
        <description>...</description>
        <parameter description="..." name="...">...</parameter>
    </location>
    
### Attributes

    name="..."

The location name, used to reference this location from other configuration objects.

    extends="..., ..."

A comma separated list of templates to inherit from.

    template="yes/no"

Is this a template: yes / no.

    location="..."
    
The name of a location which this location is in, this specifies the parent 
location.

    worker-pool="..."
    
The tag of a worker pool which checks all hosts that are in this location.  This 
attribute is optional and used for advanced routing of check executions.

     security-domains="...[, ...]"

A comma separated list of security domain names which this location is in.

### Elements

    <summary>...</summary>

The displayed name of this location.

    <description>...</description>

An optional long, multi-line description of this location.

    <parameter description="..." name="...">...</parameter>

An arbitrary name value pair.  The parameter name is given by the `name` attribute.  
The parameter value is given by either a `value` attribute or as the element text. 
Optionally a description of the parameter maybe given using the `description` attribute.

<p><a id="group"></a></p>
## Group

[Back to contents](#contents)

A group is a grouping of checks, which logically belong together.  This could 
be checks for a specific service area.  Groups can contain any type of check, 
there are no specialised host or service groups.

    <group name="..." extends="..." template="yes/no" groups="..., ..." security-domains="...[, ...]">
        <summary>...</summary>
        <description>...</description>
        <parameter description="..." name="...">...</parameter>
    </group>
    
### Attributes

    name="..."

The group name, used to reference this group from other configuration objects.

    extends="..., ..."

A comma separated list of templates to inherit from.

    template="yes/no"

Is this a template: yes / no.

    groups="..., ..."

A comma separated list of group names which this group is in, this defines 
parent groups which contain this group.

     security-domains="...[, ...]"

A comma separated list of security domain names which this group is in.

### Elements

    <summary>...</summary>

The displayed name of this group.

    <description>...</description>

An optional long, multi-line description of this group.

    <parameter description="..." name="...">...</parameter>

An arbitrary name value pair.  The parameter name is given by the `name` attribute.  
The parameter value is given by either a `value` attribute or as the element text. 
Optionally a description of the parameter maybe given using the `description` attribute.

<p><a id="time_period"></a></p>
## Time Period

[Back to contents](#contents)

A time period defines a calendar of time during which something should or should 
not happen.  Time periods are essentially named calendars which are used by 
various other configuration object.  For example controlling when notifications 
could be sent or when a check should be executed.

    <time-period name="..." extends="..." template="yes/no" excludes="..., ..." security-domains="...[, ...]">
        <summary>...</summary>
        <description>...</description>
        <time-zone id="(Europe/London|etc)"/>
        <time-range>...</time-range>
        <parameter description="..." name="...">...</parameter>
    </time-period>
    
### Attributes

    name="..."

The time period name, used to reference this time period from other configuration objects.

    extends="..., ..."

A comma separated list of templates to inherit from.

    template="yes/no"

Is this a template: yes / no.

    excludes="..., ..."
    
A comma separated list of time periods which this time period will exclude.  If 
a point in time is valid for any of the excluded time periods then it is not 
valid for this time period.  As such excluding a time period in essence inverses 
said time period.

     security-domains="...[, ...]"

A comma separated list of security domain names which this time-period is in.

### Elements

    <summary>...</summary>

The displayed name of this time period.

    <description>...</description>

An optional long, multi-line description of this time period.

    <parameter description="..." name="...">...</parameter>

An arbitrary name value pair.  The parameter name is given by the `name` attribute.  
The parameter value is given by either a `value` attribute or as the element text. 
Optionally a description of the parameter maybe given using the `description` attribute.

    <time-zone id="(Europe/London|etc)"/>
    
Specify the time zone that this time period is in.  This is currently not 
implemented and represents a future feature.

    <time-range>...</time-range>

Specify a time range that this time period is for.  A time period is composed 
from a set of time ranges, where a time range specifies a range to time.

At its simplest a time range is a contiguous range of hours, in 24 hour clock 
starting at `00:00` and ending at `23:59`, `24:00` means `23:59:59`, for 
example:

    09:00 - 17:30
    09:00 - 10:00, 13:00 - 14:00

A time range can be qualified with a day of the week:

    monday 09:00 - 17:30
    sunday 09:00 - 10:00, 12:00 - 13:00
    
A time range can be qualified with a specific day of a month:

    january 1 00:00-24:00
    april 18 00:00-24:00

A time range can also define a specific week day within a month, where a 
negative counts backwards:

    monday 1 may 00:00-24:00
    monday -1 may 00:00-24:00

A time range can only qualify the month:

    may 00:00-24:00
    july 00:00-02:00

A time range can only qualify the day, where a negative counts backwards:

    day 1 00:00-24:00
    day 2 00:00-24:00
    day -1 00:00-24:00

A specific date can also be given as a qualifier:

    2015-04-05 00:00-24:00
    2015-12-31 00:10-00:15

<p><a id="command"></a></p>
## Command

[Back to contents](#contents)

A command defines something which is used to perform a check.  Commands are 
fairly loosely defined, their structure is mostly defined by the engine used to 
execute them.  Primarily a command is a set of arbitrary name value pair 
parameters

    <command name="..." extends="..." template="yes/no" engine="..." executor="..." security-domains="...[, ...]">
        <summary>...</summary>
        <description>...</description>
        <parameter description="..." name="...">...</parameter>
    </command>

### Attributes

    name="..."

The team name, used to reference this team from other configuration objects.

    extends="..., ..."

A comma separated list of templates to inherit from.

    template="yes/no"

Is this a template: yes / no.

    engine="..."

The name of the engine which will execute this command, something 
like: `nagios`, `nrpe`, `snmp`, `http`, `agent`

    executor="..."

A specific command executor within an engine which will execute this command.

     security-domains="...[, ...]"

A comma separated list of security domain names which this command is in.

### Elements

    <summary>...</summary>

The displayed name of this team.

    <description>...</description>

An optional long, multi-line description of this team.

    <parameter description="..." name="...">...</parameter>

An arbitrary name value pair.  The parameter name is given by the `name` attribute.  
The parameter value is given by either a `value` attribute or as the element text. 
Optionally a description of the parameter maybe given using the `description` attribute.

<p><a id="host"></a></p>
##  Host

[Back to contents](#contents)

A host represents a networked device which should be checked.  This is often a 
server providing some service which is part of your infrastructure.

    <host name="..." extends="..." template="yes/no" suppressed="yes/no" enabled="yes/no" groups="..., ..." external-ref="..." worker-pool="..." agent-id="..." location="..." address="..." security-domains="...[, ...]">
        <summary>...</summary>
        <description>...</description>
        <initially status="pending|info|ok|warning|critical|error|timeout|action|disconnected" output=".."/>
        <notifications enabled="yes/no" time-period="..." alerts="yes/no" recovery="yes/no" ignore="..., ..." all-engines="yes/no">
            <notification-engine engine="..." enabled="yes/no" time-period="..." alerts="yes/no" recovery="yes/no" ignore="..., ..."/>
        </notifications>
        <notify teams="..., ..." contacts="..., ..."/>
        <state failed-after="[0-9]+" recovers-after="[0-9]+"/>
        <schedule every="[0-9]+(s|m|h)?" changing-every="[0-9]+(s|m|h)?" retry-every="[0-9]+(s|m|h)?" time-period="..."/>
        <check-command command="...">
            <parameter description="..." name="...">...</parameter>
        </check-command>
        <parameter description="..." name="...">...</parameter>
        <service>...</service>
        <trap>...</trap>
    </host>

### Attributes

    name="..."

The host name, used to reference this host from other configuration objects.

    extends="..., ..."

A comma separated list of templates to inherit from.

    template="yes/no"

Is this a template: yes / no.

    suppressed="yes/no"

Is this check suppressed by default, a suppressed check will not raise 
notifications: yes or no.

    enabled="yes/no"

Is this check executed by default, a disabled check will not execute when 
scheduled: yes or no.

    groups="..., ..."

A comma separated list of group names that this check is a member of.

    external-ref="..."

An arbitrary textual external reference which might be used to lookup this host, 
optional.

    worker-pool="..."

The worker pool tag, if different from this hosts location, which is used to 
route check executions to a specific pool of workers when executing checks 
for this host.

    agent-id="..."

The UUID of the Bergamot Agent deployed onto this host.

    location="..."

The name of the location which contains this host.

    address="..."

The IP address of this host, can be a DNS name, this is often used by commands 
as the name / address to communicate with.

     security-domains="...[, ...]"

A comma separated list of security domain names which this host is in.

### Elements

    <summary>...</summary>

The displayed name of this host.

    <description>...</description>

An optional long, multi-line description of this host.

    <parameter description="..." name="...">...</parameter>

An arbitrary name value pair.  The parameter name is given by the `name` attribute.  
The parameter value is given by either a `value` attribute or as the element text. 
Optionally a description of the parameter maybe given using the `description` attribute.

    <initially status="pending|info|ok|warning|critical|error|timeout|action|disconnected" output=".."/>

The initial state of this check, where `status` is the initial check status 
for this host and `output` is the initial textual output.  By default a check 
will initially be `pending`.

    <notify teams="..., ..." contacts="..., ..."/>

The teams and contacts to notify for this host, where `teams` is a comma 
separated list of team names and `contacts` is a comma separated list of 
contact names.

    <notifications enabled="yes/no" time-period="..." alerts="yes/no" recovery="yes/no" ignore="..., ..." all-engines="yes/no">
        <notification-engine engine="..." enabled="yes/no" time-period="..." alerts="yes/no" recovery="yes/no" ignore="..., ..."/>
    </notifications>
    
Configure how and when notifications can be sent for this check.  Notification 
settings for specific checks cannot override the notification settings on 
individual contacts.  This allows for a check to filter notifications sent to 
contacts but a contact will never be notified when they don't want to be.  The 
notification settings configured for a host does not apply to the services of 
a host.

#### Attributes

    enabled="yes/no"

Can notifications be sent for this check

    time-period="..."

The name of a time period which defines when notifications can be sent for this 
check.  Notifications will only be sent to contacts during this time period.

    alerts="yes/no"

Can alert notifications be sent for this check: yes or no.

    recovery="yes/no"

Can recovery notifications be sent for this check: yes or no.

    ignore="..., ..."

A comma separated list of statuses for which notifications should not be sent, 
allowed statues are: `pending`, `info`, `ok`, `warning`, `critical`, `unknown`, 
`timeout`, `error`, `disconnected`, `action`.

    all-engines="yes/no"

Should notifications be sent using all notification engines that are available or 
should notifications only be sent using the configured engines: yes or no.

#### Elements

    <notification-engine engine="..." enabled="yes/no" time-period="..." alerts="yes/no" recovery="yes/no" ignore="..., ..."/>

Configure a specific notification engine which can be used to notify a contact 
for this check.

##### Attributes

    engine="..."

The name of the engine which will be used to send the notification, for 
example: `email`, `sms`.

    enabled="yes/no"

Can notifications be sent via this engine: yes or no.

    time-period="..."

The name of a time period which defines when notifications can be sent via this 
engine to a contact.  Notifications will only be sent to a contact via 
this engine during this time period.

    alerts="yes/no"

Can alert notifications be sent to a contact via this engine: yes or no.

    recovery="yes/no"

Can recovery notifications be sent to a contact via this engine: yes or no.

    ignore="..., ..."

A comma separated list of statuses for which notifications should not be sent 
via this engine, allowed statues are: `pending`, `info`, `ok`, `warning`, 
`critical`, `unknown`, `timeout`, `error`, `disconnected`, `action`.

    <state failed-after="[0-9]+" recovers-after="[0-9]+"/>

After how many consecutive attempts will a check reach a steady state.  The 
`failed-after` attribute defines the number of consecutive non-ok results 
required to reach a steady state.  The `recovers-after` attribute defines the 
number of consecutive ok results required to reach a steady state.

    <schedule every="[0-9]+(s|m|h)?" changing-every="[0-9]+(s|m|h)?" retry-every="[0-9]+(s|m|h)?" time-period="..."/>

Specify how often this host will be checked.  The `every` attribute defines how 
frequently the check will be executed when it is in a steady ok state.  The 
`retry-every` attribute defines how frequently the check will be executed when 
it is in a steady non-ok state.  The `changing-every` attribute defines how 
frequently the check will execute when it is changing state.  The value for 
these three periods defaults to minutes, a value may be suffixed 
with: `h`, `m`, `s` to denote hours, minutes and seconds respectively.  Note: 
you cannot mix units, to specify 1.5 minutes use: `90s`.

    <check-command command="...">
        <parameter description="..." name="...">...</parameter>
    </check-command>

Specify the command which will be executed to check this host is ok.  The 
`command` attribute is the name of the command which is to be executed.  Should 
specific command parameters need to be defined or overridden they should be 
specified as child elements of the `check-command` element, using the usual 
`parameter` element.

    <service>...</service>

Configure a service on this host, see the service configuration reference for 
specific details on how services are configured.

    <trap>...</trap>

Configure a trap on this host, see the trap configuration reference for 
specific details on how traps are configured.

<p><a id="service"></a></p>
## Service

[Back to contents](#contents)

A service represents something running on a host which should be checked, this 
could be a file system, a process, free memory.  Just like hosts, services are 
actively checked.

    <service name="..." extends="..." template="yes/no" suppressed="yes/no" enabled="yes/no" groups="..., ..." external-ref="..." worker-pool="..." agent-id="..." security-domains="...[, ...]">
        <summary>...</summary>
        <description>...</description>
        <initially status="pending|info|ok|warning|critical|error|timeout|action|disconnected" output=".."/>
        <notifications enabled="yes/no" time-period="..." alerts="yes/no" recovery="yes/no" ignore="..., ..." all-engines="yes/no">
            <notification-engine engine="..." enabled="yes/no" time-period="..." alerts="yes/no" recovery="yes/no" ignore="..., ..."/>
        </notifications>
        <notify teams="..., ..." contacts="..., ..."/>
        <state failed-after="[0-9]+" recovers-after="[0-9]+"/>
        <schedule every="[0-9]+(s|m|h)?" changing-every="[0-9]+(s|m|h)?" retry-every="[0-9]+(s|m|h)?" time-period="..."/>
        <check-command command="...">
            <parameter description="..." name="...">...</parameter>
        </check-command>
        <parameter description="..." name="...">...</parameter>
    </service>

### Attributes

    name="..."

The service name, used to reference this service from other configuration objects.

    extends="..., ..."

A comma separated list of templates to inherit from.

    template="yes/no"

Is this a template: yes / no.

    suppressed="yes/no"

Is this check suppressed by default, a suppressed check will not raise 
notifications: yes or no.

    enabled="yes/no"

Is this check executed by default, a disabled check will not execute when 
scheduled: yes or no.

    groups="..., ..."

A comma separated list of group names that this check is a member of.

    external-ref="..."

An arbitrary textual external reference which might be used to lookup this host, 
optional.

    worker-pool="..."

The worker pool tag, if different from this service's host, which is used to 
route check executions to a specific pool of workers when executing checks 
for this host.

    agent-id="..."

The UUID of the Bergamot Agent deployed for this service, only required if 
different from the UUID of the host agent.  This is currently unimplemented and 
is designed for services which are running an embedded agent.

     security-domains="...[, ...]"

A comma separated list of security domain names which this service is in.

### Elements

    <summary>...</summary>

The displayed name of this service.

    <description>...</description>

An optional long, multi-line description of this service.

    <parameter description="..." name="...">...</parameter>

An arbitrary name value pair.  The parameter name is given by the `name` attribute.  
The parameter value is given by either a `value` attribute or as the element text. 
Optionally a description of the parameter maybe given using the `description` attribute.

    <initially status="pending|info|ok|warning|critical|error|timeout|action|disconnected" output=".."/>

The initial state of this check, where `status` is the initial check status 
for this service and `output` is the initial textual output.  By default a check 
will initially be `pending`.

    <notify teams="..., ..." contacts="..., ..."/>

The teams and contacts to notify for this service, where `teams` is a comma 
separated list of team names and `contacts` is a comma separated list of 
contact names.

    <notifications enabled="yes/no" time-period="..." alerts="yes/no" recovery="yes/no" ignore="..., ..." all-engines="yes/no">
        <notification-engine engine="..." enabled="yes/no" time-period="..." alerts="yes/no" recovery="yes/no" ignore="..., ..."/>
    </notifications>
    
Configure how and when notifications can be sent for this check.  Notification 
settings for specific checks cannot override the notification settings on 
individual contacts.  This allows for a check to filter notifications sent to 
contacts but a contact will never be notified when they don't want to be.

#### Attributes

    enabled="yes/no"

Can notifications be sent for this check

    time-period="..."

The name of a time period which defines when notifications can be sent for this 
check.  Notifications will only be sent to contacts during this time period.

    alerts="yes/no"

Can alert notifications be sent for this check: yes or no.

    recovery="yes/no"

Can recovery notifications be sent for this check: yes or no.

    ignore="..., ..."

A comma separated list of statuses for which notifications should not be sent, 
allowed statues are: `pending`, `info`, `ok`, `warning`, `critical`, `unknown`, 
`timeout`, `error`, `disconnected`, `action`.

    all-engines="yes/no"

Should notifications be sent using all notification engines that are available or 
should notifications only be sent using the configured engines: yes or no.

#### Elements

    <notification-engine engine="..." enabled="yes/no" time-period="..." alerts="yes/no" recovery="yes/no" ignore="..., ..."/>

Configure a specific notification engine which can be used to notify a contact 
for this check.

##### Attributes

    engine="..."

The name of the engine which will be used to send the notification, for 
example: `email`, `sms`.

    enabled="yes/no"

Can notifications be sent via this engine: yes or no.

    time-period="..."

The name of a time period which defines when notifications can be sent via this 
engine to a contact.  Notifications will only be sent to a contact via 
this engine during this time period.

    alerts="yes/no"

Can alert notifications be sent to a contact via this engine: yes or no.

    recovery="yes/no"

Can recovery notifications be sent to a contact via this engine: yes or no.

    ignore="..., ..."

A comma separated list of statuses for which notifications should not be sent 
via this engine, allowed statues are: `pending`, `info`, `ok`, `warning`, 
`critical`, `unknown`, `timeout`, `error`, `disconnected`, `action`.

    <state failed-after="[0-9]+" recovers-after="[0-9]+"/>

After how many consecutive attempts will a check reach a steady state.  The 
`failed-after` attribute defines the number of consecutive non-ok results 
required to reach a steady state.  The `recovers-after` attribute defines the 
number of consecutive ok results required to reach a steady state.

    <schedule every="[0-9]+(s|m|h)?" changing-every="[0-9]+(s|m|h)?" retry-every="[0-9]+(s|m|h)?" time-period="..."/>

Specify how often this service will be checked.  The `every` attribute defines how 
frequently the check will be executed when it is in a steady ok state.  The 
`retry-every` attribute defines how frequently the check will be executed when 
it is in a steady non-ok state.  The `changing-every` attribute defines how 
frequently the check will execute when it is changing state.  The value for 
these three periods defaults to minutes, a value may be suffixed 
with: `h`, `m`, `s` to denote hours, minutes and seconds respectively.  Note: 
you cannot mix units, to specify 1.5 minutes use: `90s`.

    <check-command command="...">
        <parameter description="..." name="...">...</parameter>
    </check-command>

Specify the command which will be executed to check this service is ok.  The 
`command` attribute is the name of the command which is to be executed.  Should 
specific command parameters need to be defined or overridden they should be 
specified as child elements of the `check-command` element, using the usual 
`parameter` element.

<p><a id="trap"></a></p>
## Trap

[Back to contents](#contents)

A trap defines something on a host which is not actively checked but rather is 
passively checked.  No check executions will be scheduled or even executed from 
a trap, instead results are triggered from some kind of external system.  A 
good example of a trap are SNMP traps raised from networking devices.

    <trap name="..." extends="..." template="yes/no" suppressed="yes/no" enabled="yes/no" groups="..., ..." external-ref="..." security-domains="...[, ...]">
        <summary>...</summary>
        <description>...</description>
        <initially status="pending|info|ok|warning|critical|error|timeout|action|disconnected" output=".."/>
        <notifications enabled="yes/no" time-period="..." alerts="yes/no" recovery="yes/no" ignore="..., ..." all-engines="yes/no">
            <notification-engine engine="..." enabled="yes/no" time-period="..." alerts="yes/no" recovery="yes/no" ignore="..., ..."/>
        </notifications>
        <notify teams="..., ..." contacts="..., ..."/>
        <state failed-after="[0-9]+" recovers-after="[0-9]+"/>
        <check-command command="...">
            <parameter description="..." name="...">...</parameter>
        </check-command>
        <parameter description="..." name="...">...</parameter>
    </trap>

### Attributes

    name="..."

The trap name, used to reference this trap from other configuration objects.

    extends="..., ..."

A comma separated list of templates to inherit from.

    template="yes/no"

Is this a template: yes / no.

    suppressed="yes/no"

Is this check suppressed by default, a suppressed check will not raise 
notifications: yes or no.

    enabled="yes/no"

Is this check executed by default, a disabled check will not execute when 
scheduled: yes or no.

    groups="..., ..."

A comma separated list of group names that this check is a member of.

    external-ref="..."

An arbitrary textual external reference which might be used to lookup this host, 
optional.

     security-domains="...[, ...]"

A comma separated list of security domain names which this trap is in.

### Elements

    <summary>...</summary>

The displayed name of this trap.

    <description>...</description>

An optional long, multi-line description of this trap.

    <parameter description="..." name="...">...</parameter>

An arbitrary name value pair.  The parameter name is given by the `name` attribute.  
The parameter value is given by either a `value` attribute or as the element text. 
Optionally a description of the parameter maybe given using the `description` attribute.

    <initially status="pending|info|ok|warning|critical|error|timeout|action|disconnected" output=".."/>

The initial state of this check, where `status` is the initial check status 
for this trap and `output` is the initial textual output.  By default a check 
will initially be `pending` as such setting the initial state for traps is 
important, usually you would set the initial state of `ok`.

    <notify teams="..., ..." contacts="..., ..."/>

The teams and contacts to notify for this trap, where `teams` is a comma 
separated list of team names and `contacts` is a comma separated list of 
contact names.

    <notifications enabled="yes/no" time-period="..." alerts="yes/no" recovery="yes/no" ignore="..., ..." all-engines="yes/no">
        <notification-engine engine="..." enabled="yes/no" time-period="..." alerts="yes/no" recovery="yes/no" ignore="..., ..."/>
    </notifications>
    
Configure how and when notifications can be sent for this check.  Notification 
settings for specific checks cannot override the notification settings on 
individual contacts.  This allows for a check to filter notifications sent to 
contacts but a contact will never be notified when they don't want to be.

#### Attributes

    enabled="yes/no"

Can notifications be sent for this check

    time-period="..."

The name of a time period which defines when notifications can be sent for this 
check.  Notifications will only be sent to contacts during this time period.

    alerts="yes/no"

Can alert notifications be sent for this check: yes or no.

    recovery="yes/no"

Can recovery notifications be sent for this check: yes or no.

    ignore="..., ..."

A comma separated list of statuses for which notifications should not be sent, 
allowed statues are: `pending`, `info`, `ok`, `warning`, `critical`, `unknown`, 
`timeout`, `error`, `disconnected`, `action`.

    all-engines="yes/no"

Should notifications be sent using all notification engines that are available or 
should notifications only be sent using the configured engines: yes or no.

#### Elements

    <notification-engine engine="..." enabled="yes/no" time-period="..." alerts="yes/no" recovery="yes/no" ignore="..., ..."/>

Configure a specific notification engine which can be used to notify a contact 
for this check.

##### Attributes

    engine="..."

The name of the engine which will be used to send the notification, for 
example: `email`, `sms`.

    enabled="yes/no"

Can notifications be sent via this engine: yes or no.

    time-period="..."

The name of a time period which defines when notifications can be sent via this 
engine to a contact.  Notifications will only be sent to a contact via 
this engine during this time period.

    alerts="yes/no"

Can alert notifications be sent to a contact via this engine: yes or no.

    recovery="yes/no"

Can recovery notifications be sent to a contact via this engine: yes or no.

    ignore="..., ..."

A comma separated list of statuses for which notifications should not be sent 
via this engine, allowed statues are: `pending`, `info`, `ok`, `warning`, 
`critical`, `unknown`, `timeout`, `error`, `disconnected`, `action`.

    <state failed-after="[0-9]+" recovers-after="[0-9]+"/>

After how many consecutive attempts will a check reach a steady state.  The 
`failed-after` attribute defines the number of consecutive non-ok results 
required to reach a steady state.  The `recovers-after` attribute defines the 
number of consecutive ok results required to reach a steady state. Usually for 
traps these attempt thresholds will be both set to 1, causing immediate state 
transitions.

    <check-command command="...">
        <parameter description="..." name="...">...</parameter>
    </check-command>

For passive checks the command is never actively scheduled or executed.  Instead 
the command provides metadata which is used to configure how the trap is 
watched.

<p><a id="cluster"></a></p>
##  Cluster

[Back to contents](#contents)

Clusters are virtual checks unlike hosts which are real checks.  Clusters 
represent a check which is computed from the state of multiple hosts.  Clusters 
are designed to model clusters of hosts where the resultant state can still be 
ok even if one or more hosts has failed.  Imagine a high availability database 
cluster, the overall cluster can still ok even if one host has failed.

Virtual checks are not scheduled and are not passively checked, rather they are 
composed from a set of checks.  The state of a virtual check is computed each 
time a dependent check changes state.

    <cluster name="..." extends="..." template="yes/no" suppressed="yes/no" enabled="yes/no" groups="..., ..." external-ref="..." security-domains="...[, ...]">
        <summary>...</summary>
        <description>...</description>
        <initially status="pending|info|ok|warning|critical|error|timeout|action|disconnected" output=".."/>
        <notifications enabled="yes/no" time-period="..." alerts="yes/no" recovery="yes/no" ignore="..., ..." all-engines="yes/no">
            <notification-engine engine="..." enabled="yes/no" time-period="..." alerts="yes/no" recovery="yes/no" ignore="..., ..."/>
        </notifications>
        <notify teams="..., ..." contacts="..., ..."/>
        <condition>...</condition>
        <parameter description="..." name="...">...</parameter>
        <resource>...</resource>
    </cluster>

### Attributes

    name="..."

The cluster name, used to reference this cluster from other configuration objects.

    extends="..., ..."

A comma separated list of templates to inherit from.

    template="yes/no"

Is this a template: yes / no.

    suppressed="yes/no"

Is this check suppressed by default, a suppressed check will not raise 
notifications: yes or no.

    enabled="yes/no"

Is this check executed by default, a disabled check will not execute when 
scheduled: yes or no.

    groups="..., ..."

A comma separated list of group names that this check is a member of.

    external-ref="..."

An arbitrary textual external reference which might be used to lookup this host, 
optional.

     security-domains="...[, ...]"

A comma separated list of security domain names which this cluster is in.

### Elements

    <summary>...</summary>

The displayed name of this cluster.

    <description>...</description>

An optional long, multi-line description of this cluster.

    <parameter description="..." name="...">...</parameter>

An arbitrary name value pair.  The parameter name is given by the `name` attribute.  
The parameter value is given by either a `value` attribute or as the element text. 
Optionally a description of the parameter maybe given using the `description` attribute.

    <initially status="pending|info|ok|warning|critical|error|timeout|action|disconnected" output=".."/>

The initial state of this check, where `status` is the initial check status 
for this cluster and `output` is the initial textual output.  By default a check 
will initially be `pending`.

    <notify teams="..., ..." contacts="..., ..."/>

The teams and contacts to notify for this cluster, where `teams` is a comma 
separated list of team names and `contacts` is a comma separated list of 
contact names.

    <notifications enabled="yes/no" time-period="..." alerts="yes/no" recovery="yes/no" ignore="..., ..." all-engines="yes/no">
        <notification-engine engine="..." enabled="yes/no" time-period="..." alerts="yes/no" recovery="yes/no" ignore="..., ..."/>
    </notifications>
    
Configure how and when notifications can be sent for this check.  Notification 
settings for specific checks cannot override the notification settings on 
individual contacts.  This allows for a check to filter notifications sent to 
contacts but a contact will never be notified when they don't want to be.

#### Attributes

    enabled="yes/no"

Can notifications be sent for this check

    time-period="..."

The name of a time period which defines when notifications can be sent for this 
check.  Notifications will only be sent to contacts during this time period.

    alerts="yes/no"

Can alert notifications be sent for this check: yes or no.

    recovery="yes/no"

Can recovery notifications be sent for this check: yes or no.

    ignore="..., ..."

A comma separated list of statuses for which notifications should not be sent, 
allowed statues are: `pending`, `info`, `ok`, `warning`, `critical`, `unknown`, 
`timeout`, `error`, `disconnected`, `action`.

    all-engines="yes/no"

Should notifications be sent using all notification engines that are available or 
should notifications only be sent using the configured engines: yes or no.

#### Elements

    <notification-engine engine="..." enabled="yes/no" time-period="..." alerts="yes/no" recovery="yes/no" ignore="..., ..."/>

Configure a specific notification engine which can be used to notify a contact 
for this check.

##### Attributes

    engine="..."

The name of the engine which will be used to send the notification, for 
example: `email`, `sms`.

    enabled="yes/no"

Can notifications be sent via this engine: yes or no.

    time-period="..."

The name of a time period which defines when notifications can be sent via this 
engine to a contact.  Notifications will only be sent to a contact via 
this engine during this time period.

    alerts="yes/no"

Can alert notifications be sent to a contact via this engine: yes or no.

    recovery="yes/no"

Can recovery notifications be sent to a contact via this engine: yes or no.

    ignore="..., ..."

A comma separated list of statuses for which notifications should not be sent 
via this engine, allowed statues are: `pending`, `info`, `ok`, `warning`, 
`critical`, `unknown`, `timeout`, `error`, `disconnected`, `action`.

    <condition>...</condition>

Specify the virtual check condition which will compute the state of this 
cluster.  This condition is essentially a boolean algebraic expression, which 
will calculate the state of this cluster from the state of multiple other 
checks.  A simple condition would be:

    host 'db1.local' or host 'db2.local' or host 'db3.local'

With the above condition, the state of the virtual check would represent the 
best state of all three hosts. As such for this check to fail all three hosts 
would need to fail.

    <resource>...</resource>

Configure a resource on this cluster, see the resource configuration reference 
for specific details on how resources are configured.

<p><a id="resource"></a></p>
##  Resource

[Back to contents](#contents)

Resources are virtual checks unlike service and traps which are real checks.  
Resources represent a check which is computed from the state of multiple 
services or traps.  Resources exist on clusters, a resource is to a cluster what 
a service or trap is to a host.

Virtual checks are not scheduled and are not passively checked, rather they are 
composed from a set of checks.  The state of a virtual check is computed each 
time a dependent check changes state.

    <resource name="..." extends="..." template="yes/no" suppressed="yes/no" enabled="yes/no" groups="..., ..." external-ref="..." security-domains="...[, ...]">
        <summary>...</summary>
        <description>...</description>
        <initially status="pending|info|ok|warning|critical|error|timeout|action|disconnected" output=".."/>
        <notifications enabled="yes/no" time-period="..." alerts="yes/no" recovery="yes/no" ignore="..., ..." all-engines="yes/no">
            <notification-engine engine="..." enabled="yes/no" time-period="..." alerts="yes/no" recovery="yes/no" ignore="..., ..."/>
        </notifications>
        <notify teams="..., ..." contacts="..., ..."/>
        <condition>...</condition>
        <parameter description="..." name="...">...</parameter>
    </resource>

### Attributes

    name="..."

The resource name, used to reference this resource from other configuration objects.

    extends="..., ..."

A comma separated list of templates to inherit from.

    template="yes/no"

Is this a template: yes / no.

    suppressed="yes/no"

Is this check suppressed by default, a suppressed check will not raise 
notifications: yes or no.

    enabled="yes/no"

Is this check executed by default, a disabled check will not execute when 
scheduled: yes or no.

    groups="..., ..."

A comma separated list of group names that this check is a member of.

    external-ref="..."

An arbitrary textual external reference which might be used to lookup this host, 
optional.

     security-domains="...[, ...]"

A comma separated list of security domain names which this resource is in.

### Elements

    <summary>...</summary>

The displayed name of this resource.

    <description>...</description>

An optional long, multi-line description of this resource.

    <parameter description="..." name="...">...</parameter>

An arbitrary name value pair.  The parameter name is given by the `name` attribute.  
The parameter value is given by either a `value` attribute or as the element text. 
Optionally a description of the parameter maybe given using the `description` attribute.


    <initially status="pending|info|ok|warning|critical|error|timeout|action|disconnected" output=".."/>

The initial state of this check, where `status` is the initial check status 
for this resource and `output` is the initial textual output.  By default a 
check will initially be `pending`.

    <notify teams="..., ..." contacts="..., ..."/>

The teams and contacts to notify for this resource, where `teams` is a comma 
separated list of team names and `contacts` is a comma separated list of 
contact names.

    <notifications enabled="yes/no" time-period="..." alerts="yes/no" recovery="yes/no" ignore="..., ..." all-engines="yes/no">
        <notification-engine engine="..." enabled="yes/no" time-period="..." alerts="yes/no" recovery="yes/no" ignore="..., ..."/>
    </notifications>
    
Configure how and when notifications can be sent for this check.  Notification 
settings for specific checks cannot override the notification settings on 
individual contacts.  This allows for a check to filter notifications sent to 
contacts but a contact will never be notified when they don't want to be.

#### Attributes

    enabled="yes/no"

Can notifications be sent for this check

    time-period="..."

The name of a time period which defines when notifications can be sent for this 
check.  Notifications will only be sent to contacts during this time period.

    alerts="yes/no"

Can alert notifications be sent for this check: yes or no.

    recovery="yes/no"

Can recovery notifications be sent for this check: yes or no.

    ignore="..., ..."

A comma separated list of statuses for which notifications should not be sent, 
allowed statues are: `pending`, `info`, `ok`, `warning`, `critical`, `unknown`, 
`timeout`, `error`, `disconnected`, `action`.

    all-engines="yes/no"

Should notifications be sent using all notification engines that are available or 
should notifications only be sent using the configured engines: yes or no.

#### Elements

    <notification-engine engine="..." enabled="yes/no" time-period="..." alerts="yes/no" recovery="yes/no" ignore="..., ..."/>

Configure a specific notification engine which can be used to notify a contact 
for this check.

##### Attributes

    engine="..."

The name of the engine which will be used to send the notification, for 
example: `email`, `sms`.

    enabled="yes/no"

Can notifications be sent via this engine: yes or no.

    time-period="..."

The name of a time period which defines when notifications can be sent via this 
engine to a contact.  Notifications will only be sent to a contact via 
this engine during this time period.

    alerts="yes/no"

Can alert notifications be sent to a contact via this engine: yes or no.

    recovery="yes/no"

Can recovery notifications be sent to a contact via this engine: yes or no.

    ignore="..., ..."

A comma separated list of statuses for which notifications should not be sent 
via this engine, allowed statues are: `pending`, `info`, `ok`, `warning`, 
`critical`, `unknown`, `timeout`, `error`, `disconnected`, `action`.

    <condition>...</condition>

Specify the virtual check condition which will compute the state of this 
resource.  This condition is essentially a boolean algebraic expression, which 
will calculate the state of this resource from the state of multiple other 
checks.  A simple condition would be:

    service 'cpu' on host 'db1.local' or service 'cpu' on host 'db2.local' or service 'cpu' on host 'db3.local'

With the above condition, the state of the virtual check would represent the 
best state of all three hosts. As such for this check to fail all three hosts 
would need to fail.
