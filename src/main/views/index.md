# What is Bergamot?

Bergamot is a simple, scalable and distributed monitoring system which offers an easy migration path from Nagios.  
Bergamot is written in Java and makes use of RabbitMQ to distribute checks.  It is able to read the Nagios object 
configuration and is able to execute Nagios checks.

![Bergamot Dashboard Screenshot](/images/dashboard_sample.png)

## Features

* Nagios compatible - can import Nagios configuration and execute Nagios plugins
* Native NRPE implementation - efficient and scalable NRPE check execution, no fork!
* Notifications - email notifications when a check fails
* Distributed - uses RabbitMQ to distribute checks among a pool of servers
* Scalable - able to share load over a number of servers
* Modern - written in a safe, managed language, with a clean code base
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

## Roadmap

To be viable, the following key features are missing:

* Persistence - the core object model should be persisted into a database
* Notifications - it's probably helpful to be able to send emails
* UI - the current UI is minimalist to say the least

## Authors and Contributors

Bergamot is a personal project of Chris Ellis [@intrbiz](https://twitter.com/intrbiz)


