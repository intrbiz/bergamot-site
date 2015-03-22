---
Author: Chris Ellis
Date: 2015-03-22
Code: bash
---
# Bergamot Agent Guide

## Introduction

The Bergamot Agent is a daemon designed to run on monitored servers, similar to 
Nagios' NRPE daemon.  It is designed to make it easy to monitor servers, without 
the need to configure and maintain checks across every monitored server.

Rather than the Bergamot Agent accepting connections, it initiates the 
connection to a Bergamot Monitoring cluster.  It opens a persistent WebSocket 
connection which is used to receive checks to execute.

The Bergamot Agent makes use of TLS certificates to ensure that this connection 
is secure and authenticated.  Every deployed Bergamot Agent must have a specific 
and unique certificate, which is signed by Bergamot Monitoring.  This prevents 
rogue agents from connecting to a Bergamot Monitoring cluster and also prevents 
a Bergamot Agent connecting to a rogue cluster and accepting rogue commands.

As such, the Bergamot Agent is a significant step up in security compared to 
NRPE.  The Bergamot Agent is secure by default and easy to firewall, as it 
connects out to a know hub, rather than accepting connections.

### Installing Bergamot Agent

We currently have packages available for: CentOS 7, RedHat 7, Scientific Linux 7, 
Fedora 20 and 21, openSuse 13.1, 13.2, Tumbleweed and SLE 12.

You can install these packages using:

    root@server:~ # zypper ar http://download.opensuse.org/repositories/server:/database:/postgresql/openSUSE_13.1/server:database:postgresql.repo
    root@server:~ # zypper ref
    root@server:~ # zypper in bergamot-java bergamot-agent
    
You can start the Bergamot Agent using:

    root@server:~ # systemctl enable bergamot-agent
    root@server:~ # systemctl start bergamot-agent

Note: you should configure the Bergamot Agent before starting it, as explained 
below.
    
### Generating Bergamot Agent Configuration

Every Bergamot Agent requires a unique certificate and key pair.  This can 
easily be generated using the Bergamot CLI tool, as follows:

    root@mydesktop: bergamot-cli agent generate '<site-name>' '<agent-host-name>'

The above command will generate a certificate / key pair, sign it and return the 
Bergamot Agent configuration file.  This command is designed to be easy to 
integrate into your existing automated deployment tools.
