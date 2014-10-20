# What is Bergamot Monitoring?

Bergamot Monitoring is an Open Source distributed monitoring system which has an
easy migration path from Nagios.  Bergamot Monitoring has a range of compelling 
features which make it a ideal for modern cloud based infrastructures.

![Bergamot Dashboard Screenshot](/images/dashboard_sample.png)

[Screenshots](/screenshots)

## Highlights

* Nagios compatible - can execute Nagios plugins, can import Nagios configuration
* Native NRPE implementation - efficient and scalable NRPE check execution, no forking!
* Distributed - uses RabbitMQ to distribute checks among a pool of workers
* Persistent - all state is stored persistently in PostgreSQL
* Scalable - split all processing over multiple nodes
* Modern - written in a safe, managed language
* Modular - plugable check engine, notification engines, etc

## Mission

Bergamot aims to be a simple, efficient, scalable and distributed monitoring system which is broadly compatible 
with Nagios.

Bergamot should offer:

* An easy migration path from Nagios
* Simple - keep it simple stupid
* Efficient - aim to move away from forking processes
* Scalable - able to handle tens of thousands of checks without requiring complicated add-ons
* Distributed - able to execute checks across different sites

Whilst Bergamot can and will be able to import Nagios object configurations, it does not and probably never will 
support all the 'features' of Nagios.  Bergamot should in no way be considered a port of Nagios, it is a clean 
room implementation by people who have used Nagios in a number of large deployments.

##  Current Progress

Bergamot is in it's early stages, but has the core fundamental elements of a monitoring system implemented.  It is 
currently able to import a Nagios configuration and execute Nagios checks, it has a very minimalist UI to view the 
status of checks.

## Test Drive

Try out our [demon instance](/demo)

## Give It A Go

Download our virtual appliance to get up and running immediately.


