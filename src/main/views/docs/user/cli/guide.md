---
Author: Chris Ellis
Date: 2015-04-05
Code: bash
---
# Bergamot Monitoring CLI Guide

## Introduction

The Bergamot Monitoring CLI allows you to control a Bergamot Monitoring instance from the command line.  It provides both local administration commands as well as control over individual sites remotely.

The CLI can be used against a specific site by any user who posses CLI permissions, the CLI will communicate with Bergamot Monitoring site using the JSON REST API.  Authentication is via tokens, which can either be generated via the web interface or via the CLI.  Authentication tokens can be revoked from within the web interface.

administration functionality of the CLI allows for management over the whole Bergamot Monitoring cluster, these are site global commands. This administrator mode, the CLI must be invoked upon one of the UI nodes and a valid UI configuration file needs to be readable by the CLI.

## Using the CLI

The normal CLI is invoked as follows, by default printing the overview help:

    cellis@cedesktop:~> bergamot-cli
    Bergamot CLI - Help
    Commands:
    help [<command>]                          Display Bergamot CLI command help
    config (show|add-site|remove-site) ...    Configure the Bergamot CLI
    convert <nagios-config-dir>               Convert Nagios configuration to Bergamot configuration
    validate-config <config-dir>              Parse the validate the Bergamot configuration in the given directory
    test <site-name>                          Test connectivity to a Bergamot site
    site.xml <site-name>                      Export the complete site configuration
    alerts <site-name>                        Get the current alerts for a site
    agent (generate) ...                      Manager Bergamot Agent certificates

    For further detail about a specific command use:
    bergamot-cli help <command>

    Note: remember to quote all arguments

### Configuring a site

Before you can use the CLI against a site you need to configure authentication for that site, using the `config add-site` command.  This command 
takes the following arguments:

 * The site name you will use to reference this site when using the CLI
 * The API URL for this site
 * Your API authentication token

You can generate an API authentication token from your profile page, select 'Hello <your name>' from the main menu in Bergamot Monitoring.

For example:

    cellis@cedesktop:~> bergamot-cli config add-site 'demo.bergamot-monitoring.org' 'https://demo.bergamot-monitoring.org/' 'QUIqzeQYNe7glNuS1nDGyPDDL6zRp2MIREwS7tRQ2ACpsbSlwZDDKLcjxIGei9mfSuorDGIxcGsO5hqW2cM4sKbhIHHntYfCWzDLJSOg'
    Successfully connected to demo.bergamot-monitoring.org (https://demo.bergamot-monitoring.org/) => Hello World
    Sucessfully authenticated with demo.bergamot-monitoring.org
    <bergamot-cli>
        <site auth-token="QUIqzeQYNe7glNuS1nDGyPDDL6zRp2MIREwS7tRQ2ACpsbSlwZDDKLcjxIGei9mfSuorDGIxcGsO5hqW2cM4sKbhIHHntYfCWzDLJSOg" url="https://demo.bergamot-monitoring.org/" name="demo.bergamot-monitoring.org"/>
    </bergamot-cli>

## Testing access to a site

Once you have configured a site, you can test access to it, the CLI will perform a number of communication test with the Bergamot Monitoring server and report the status, for example:

    cellis@cedesktop:~> bergamot-cli test demo.bergamot-monitoring.org
    Test basic API connectivity: Hello World
    Test authenticated API connectivity: Hello op


## Using the admin CLI

The administrator level CLI commands are all prefixed with `admin` as the first argument.  Executing the CLI with `admin` as the first and only argument will display the overview help for the administrator CLI, for example:

    cellis@cedesktop:~> bergamot-cli admin
    Bergamot CLI - Help
    Commands:
    admin help [<command>]                                               Display Bergamot CLI command help
    admin create-site '<site-name>' '<site-summary>' [<site-alias>, ]    Create a Bergamot site with the given <site-name> and optional <site-alias>es.
    admin list-sites                                                     List all Bergamot sites which are configured
    admin db-version                                                     Display the version of the Bergamot database schema
    admin db-install                                                     Install the latest version of the Bergamot DB schema
    admin add-site-alias <site-name> <site-alias> [<site-alias>, ...]    Add aliases to an existing site
    admin db-import-config <config-dir>                                  Import Bergamot configuration directly into the database
    admin generate-site-cas                                              Ensure that a Site certificate authority is generated for each site
    admin server generate ...                                            Manager Bergamot Agent Server certificates
    admin security-key (generate|set)                                    Manage Bergamot UI installation security keys

    For further detail about a specific command use:
    bergamot-cli help <command>

    Note: remember to quote all arguments



    
