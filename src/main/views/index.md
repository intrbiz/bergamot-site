# What is Bergamot Monitoring?

Bergamot Monitoring is an Open Source distributed monitoring system which has an
easy migration path from Nagios.  Bergamot Monitoring has a range of compelling 
features which make it a ideal for modern cloud based infrastructures.

![Bergamot Dashboard Screenshot](/images/dashboard_sample.png)

[Screenshots](/screenshots)

# Nagios Compatible

Bergamot Monitoring is capable of executing Nagios plugins, so there is no need 
to throw away your existing custom checks.

Bergamot Monitoring natively supports NRPE checks, without the need to fork, 
making NRPE checks quicker and more efficient under Bergamot Monitoring.  For 
estates with a large number of NRPE based checks, Bergamot Monitoring will scale 
better.

An existing Nagios configuration can be imported, giving an easy migration path, 
without the need to re-setup your monitoring.

# Distributed Monitoring

Bergamot Monitoring is distributed by default, rather than as an afterthought, 
making use of message queues to distribute work across a number of servers.

This allows polling servers to be geographically distributed across your 
infrastructure while also allowing for redundancy and load balancing of check 
executions.

# Scalable

Bergamot Monitoring has been designed from the outset to scale.  A facet of 
being distributed by default, is that check execution is really simple to 
scale, simply add more polling servers!

Bergamot Monitoring makes use of Hazelcast to cluster the UI servers.  This forms 
the UI servers into a coherent cluster, which will share the responsibilities of 
scheduling checks, processing check results, as well as presenting the user 
interface.

# Resilient

Another facet of being distributed by default, is a level of resilience.  Check 
execution is naturally spread and balanced across a number of polling servers.  
In the event a server fails, other servers pick up the extra work, with no 
intervention needed.

# Modular

By following a distributed micro server architecture, Bergamot Monitoring is 
naturally loosely coupled and modular.  New check execution engines, 
notification engines and other services can be added with minimal effort and 
without the need to modify the core system.

# Persistent

Bergamot Monitoring durably stores all check state and history in a PostgreSQL 
database.  This also gives you the freedom to build complex reports against the 
underlying monitoring data should you need to.

# Multi-tenanted

Bergamot Monitoring has been designed from the ground up to be multi-tenanted.  
A single deployment can operate many virtual monitoring systems, each 
individually configurable, with nothing shared.

# Real Time

The Bergamot Monitoring UI makes use of modern web technologies such as 
WebSockets to provide real time updates of check state and notifications.  
Within the blink of an eye, Bergamot Monitoring can execute a check and update 
your browser's display.

# Extensible

Bergamot Monitoring has a fully functional JSON REST API, this API provides 
access to most of the functionality that is available.  You can access check 
state, manage checks and even configure checks via the API.

This API allows Bergamot Monitoring to fit into your automated provisioning and 
deployment systems.  You can use the API to create checks when you provision 
servers, or to suppress alerts when you upgrade a service.

The API gives you an easy way to integrate with existing applications and 
processes.

# Open Source

Best of all Bergamot Monitoring is licensed under a permissive Open Source 
license.  You have the freedom to alter Bergamot Monitoring to suit your needs, 
or even better to work with us.

# Interested?

## Try it out

Try out our [demo instance](/getit)

## Spin it up

Download our [virtual appliance](/getit) to get up and running immediately.


