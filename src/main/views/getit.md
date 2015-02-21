# Get it

## Virtual Appliance

You can download a pre-installed Bergamot Monitoring appliance.  This appliance 
comes with everything you need and will work out of the box (just add a DNS 
entry).  The appliance is designed to run on LibVirt / KVM and comes with a disk 
image and associated domain definition file.

This appliance is based on openSUSE 13.1 and is perfect to get up and running 
quickly it has all the required daemons pre-installed and is acceptable for 
small deployments.  As everything is contained in one VM it offers no redundancy.

You can download the appliance from [https://downloads.bergamot-monitoring.org/img/bergamot-monitoring.tar.xz](https://downloads.bergamot-monitoring.org/img/bergamot-monitoring.tar.xz)

Note: extracted the appliance is 4GiB in size and requires at least 2GiB of RAM.

## Packages

You can download RPMs from our OBS repository, we primarily provide packages 
for openSUSE.  However untested packages are provided for: Centos 7, Red Hat 7.

* [openSUSE 13.1](http://obs.intrbiz.net:82/Bergamot/openSUSE_13.1/)
* [openSUSE 13.2](http://obs.intrbiz.net:82/Bergamot/openSUSE_13.2/)
* [openSUSE Tumbleweed](http://obs.intrbiz.net:82/Bergamot/openSUSE_Tumbleweed/)
* [Centos 7](http://obs.intrbiz.net:82/Bergamot/CentOS_7/)
* [RHEL 7](http://obs.intrbiz.net:82/Bergamot/RHEL_7/)

## Binaries

You can download binary application archives from our download site: [https://downloads.bergamot-monitoring.org/](https://downloads.bergamot-monitoring.org/)

### Bergamot Monitoring V1.0.0 - Latest

* [Bergamot UI](https://downloads.bergamot-monitoring.org/app/1.0.0/bergamot-ui-1.0.0.app)
* [Bergamot Notifier Email](https://downloads.bergamot-monitoring.org/app/1.0.0/bergamot-notifier-email-1.0.0.app)
* [Bergamot Notifier SMS](https://downloads.bergamot-monitoring.org/app/1.0.0/bergamot-notifier-sms-1.0.0.app)
* [Bergamot Nagios Worker](https://downloads.bergamot-monitoring.org/app/1.0.0/bergamot-worker-nagios-1.0.0.app)
* [Bergamot SNMP Worker](https://downloads.bergamot-monitoring.org/app/1.0.0/bergamot-worker-snmp-1.0.0.app)
* [Bergamot HTTP Worker](https://downloads.bergamot-monitoring.org/app/1.0.0/bergamot-worker-http-1.0.0.app)
* [Bergamot SNMP Watcher](https://downloads.bergamot-monitoring.org/app/1.0.0/bergamot-watcher-snmp-1.0.0.app)

## Maven Repository

If you intend to build against any of the Bergamot Monitoring libraries you will 
need to add our Maven Repository to your POM.

    <repositories>
        <repository>
            <id>snapshots</id>
            <url>http://nexus.intrbiz.net/nexus/content/repositories/snapshots</url>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
        </repository>
        <repository>
            <id>intrbiz</id>
            <name>Intrbiz</name>
            <url>http://nexus.intrbiz.net/nexus/content/repositories/releases</url>
        </repository>
    </repositories>
    
    <pluginRepositories>
        <pluginRepository>
            <id>snapshots</id>
            <url>http://nexus.intrbiz.net/nexus/content/repositories/snapshots</url>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
        </pluginRepository>
        <pluginRepository>
            <id>intrbiz</id>
            <name>Intrbiz</name>
            <url>http://nexus.intrbiz.net/nexus/content/repositories/releases</url>
        </pluginRepository>
    </pluginRepositories>

## Source Code

You can find the source code to Bergamot Monitoring on GitHub: [https://github.com/intrbiz/bergamot](https://github.com/intrbiz/bergamot)

To build Bergamot Monitoring, you will need Java 8 JDK and Apache Maven.  You 
can use the Eclipse Maven extensions to import the project into Eclipse.  You 
will probably also want PostgreSQL 9.3 and RabbitMQ installed on your development 
machine.


