---
Author: Chris Ellis
Date: 2015-07-08
Code: bash
---
# Bergamot Deployment Guide

## Introduction

This guide aims to outline the best ways to deploy Bergamot monitoring and the 
things you need to consider when you do.

### Typical Deployment

When deploying Bergamot Monitoring, you should focus on redundancy, attempting to 
remove all single points of failure. Usually this consists on ensuring you run 
multiple worker nodes, multiple notifier nodes, multiple UI nodes, clustered 
RabbitMQ servers and clustered PostgreSQL servers.

### Deploying Workers

You should deploy multiple worker nodes into each geographic site that you have. 
This is to offer load balancing of check executions as well as redundancy.

Each geographic site should be allocated it's own worker pool name, this will be 
used to route check executions to a particular site. The workers deployed into a
geographic should all be configured with the same worker pool name.

Worker daemons will need to be able to connect in to the RabbitMQ cluster. Workers 
will have differing deployment requirements based upon what they do. Differing in 
what resources will need to be accessed or what will need to access the worker 
daemon.

It is suggested to deploy each worker daemon onto its own server.  This will help 
to isolate workers from each other, should something go awry.

### Deploying Notifiers

Notifiers should be deployed into the primary site, along with the UI nodes. The 
notifier daemons can happily be deployed onto the same server as the UI nodes. When 
deploying Bergamot Monitoring for a high volume implementation you may wish to run 
these notifier nodes on additional servers.

Ultimately the notifier nodes only need a connection in to the RabbitMQ cluster. 
Notifiers will also require access to resources needed to raise notifications.

### Deploying UI

All UI nodes which form the coherrent UI cluster should be deployed into the same 
geographic site.  The clustered UI nodes use HazelCast to share cached objects and 
state between them.  As such the UI nodes will communicate heavily between each 
other and should have fast, low latency networking connections between them.

The UI nodes will also require a fast, low latency connection to the PostgreSQL 
database servers.  For most use caes a 1Gb/s ethernet network will be suitable.

When deploying more than one UI node, you will need to deploy a load balancer in 
front of the UI nodes. This load balancer must support sticky sessions, a user 
should always be toggled to a single backend for as long as possible.  Note that 
the UI nodes also use HazelCast to share session state, however requests should 
not flip flop between servers.

The UI nodes require to be able to connect to the RabbitMQ cluster and the 
PostgreSQL database servers.