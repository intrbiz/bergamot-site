---
Author: Chris Ellis
Date: 2015-04-05
Code: xml
---
# Bergamot Configuration Conversion

## Introduction

While Bergamot Monitoring has a different configuration format, a different 
configuration model and a different check model to that of Nagios. There is a 
tool which will convert a Nagios configuration to a Bergamot configuration.

The configuration conversion tool makes migrating from Nagios to Bertgamot 
easier than needing to rewrite your entire configuration.  There are some 
configuration changes you will want to make by hand.

## Executing The Converter

The Nagios to Bergamot converter is executed using the `bergamot-cli` command 
line tool.  Run the converter as follows:

    bergamot:~ # bergamot-cli convert /path/to/nagios/config

Where `/path/to/nagios/config` is the full path to the directory containing 
your Nagios object configuration files.

The converter will read all Nagios object configuration files.  For each 
configuration file it will output the corresponding Bergamot configuration 
file, this file will have the same name but with a `.xml` extension.

The converter will also output the files `bergamot_groups.xml` which will 
contain the default Bergamot groups and `parameters.xml` which will contain 
the site wide parameters that are in use.  It will also output files in 
`templates` this directory will contain the Bergamot specific host templates.

The converter won't output a Bergamot configuration file for every file, if a 
file only contains services applied to hostgroups and hosts.

### Generated Host Templates

The converter will map services into Bergamot host templates.  Where services 
are applied via hostgroups, a template for that hostgroup will be created which 
the services are added to.  Where a service is applied to a set of hosts a host 
template is automatically generated which the services are added to.

The following Nagios configuration:

    define service {
        use                        local-service
        hostgroup_name             vm-servers
        service_description        Load
        check_command              check_nrpe!check_load
        servicegroups              vm-host-services
    }
    
    define host {
        use                        linux-server
        host_name                  vm1
        alias                      VM1
        address                    1.2.3.4
        hostgroups                 vm-servers
    }

Would be converted to the following:

    <host template="yes" name="vm-servers-template">
        <summary>Generic template for vm-servers</summary>
        <service groups="vm-host-services" extends="local-service">
            <summary>Load</summary>
            <check-command command="check_nrpe">
                <parameter name="arg1">check_load</parameter>
            </check-command>
        </service>
    </host>

    <host address="1.2.3.4" groups="vm-servers" extends="linux-server, vm-servers-template" name="vm1">
        <summary>VM1</summary>
    </host>

### NRPE Commands

If you have a command called `check_nrpe`, this will be automatically converted 
to the Bergamot equivalent.  This definition will execute NRPE checks via 
Bergamot's dedicated NRPE engine, which is far more efficient.

The converted command will be moved to the generated `nrpe_commands.xml` file. 
This file will also contain commands for any NRPE commands which the converter 
has detected are in use through out your configuration.

The converter will auto-generate a specific NRPE command definition when it 
spots a service using the `check_nrpe` command with a **single** argument.

Given the following Nagios service definition:

    define service {
        use                        local-service
        hostgroup_name             vm-servers
        service_description        Load
        check_command              check_nrpe!check_load
        servicegroups              vm-host-services
    }

The following command definition would be generated:

    <command extends="check_nrpe" name="check_nrpe_check_load">
        <parameter name="command">check_load</parameter>
        <summary>Check NRPE: check_load</summary>
    </command>

With the following converted service definition:

    <service groups="vm-host-services" extends="local-service">
        <summary>Load</summary>
        <check-command command="check_nrpe_check_load"/>
    </service>

## Manual Tweaking

The configuration generated from converting an existing Nagios install will be 
a valid Bergamot configuration.  However there are likely to be a few things 
which will need fixing up or changing manually.

### User Defined Nagios Macros

The conversion tool does not read the `resources.cfg` Nagios configuration file 
and as such does not know the value of user defined Nagios macros.  However 
which user macros in user are known, these are mapped to `nagios.user[0-9]+` 
site parameters.  These will be defined in the generated `parameters.xml`, you 
will need to manually fill in the correct parameter values.

By default the `USER1` Nagios macro will be assumed to be the Nagios plugin 
path.  This will be translated to the `nagios.path` site parameter and will 
default to `/usr/lib/nagios/plugins`.  Again this will be defined in the 
generated `parameters.xml` file and will need to be set correctly.

### Command Arguments

Bergamot does not use the `!` delimited command line notation that Nagios uses. 
As such any `ARG[0-9]+` macros will be converted to `arg[0-9]+` parameters.  
These parameters are added to command definitions for reference and are used 
in check definitions.

It is suggested to update the command definitions with at minimum a description 
for what each argument is.  Ideally update the parameter names to be 
semantically relevant.

### NRPE Commands

For commands which execute via NRPE, you should alter the command definitions 
to make use of the Bergamot NRPE worker engine.  This will improve the 
performance and scalability of NRPE based checks.  The Bergamot NRPE worker 
engine natively talks the NRPE protocol and uses fast non-blocking TCP 
connections to be able to execute many concurrent NRPE checks.  This is far 
more efficient than forking a process to create a TCP connection, which is 
what Nagios does.

Given the default `check_nrpe` command definition, which would be translated 
as follows:

    <command engine="nagios" name="check_nrpe">
        <parameter name="arg1"></parameter>
        <parameter name="command_line">#{nagios.path}/check_nrpe -H #{host.address} -t 60 -c #{arg1}</parameter>
    </command>

It should be changed to the following:

    <command engine="nrpe" name="check_nrpe">
        <summary>Check NRPE</summary>
        <parameter description="The NRPE command name" name="command">#{arg1}</parameter>
        <parameter description="The NRPE host" name="host">#{host.address}</parameter>
    </command>

Note: the value of the `command` parameter will by default use the value of the 
`arg1` parameter.  This allows you to improve the command definition without 
needing to alter everything which uses the command definition.


