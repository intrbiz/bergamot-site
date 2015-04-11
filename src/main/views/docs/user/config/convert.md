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

The converter will also output the file `bergamot_groups.xml` which will 
contain the default Bergamot groups.  It will also output files in `templates` 
this directory will contain the Bergamot specific host templates.

The converter won't output a Bergamot configuration file for every file, if a 
file only contains services applied to hostgroups and hosts.

The converter will map services into Bergamot host templates.  Where services 
are applied via hostgroups, a template for that hostgroup will be created and 
the services added to that template.  Where a service is applied to a set of 
hosts and host template is automatically generated and the services are added 
to that template.











