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

### Nagios Compatible

Bergamot Monitoring is capable of executing Nagios plugins and can execute 
checks via NRPE natively.  An existing Nagios configuration can be imported, 
permitting an easy migration path.

### Distributed Monitoring

RabbitMQ is used to distribute messages between 

## Test Drive

Try out our [demon instance](/demo)

## Give It A Go

Download our [virtual appliance](/getit) to get up and running immediately.


