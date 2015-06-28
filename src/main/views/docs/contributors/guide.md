# Bergamot Monitoring - Contributors Guide

Anybody is welcome to contribute to Bergamot Monitoring.  You can contribute bug 
fixes, new features, entire new services, check definitions, documentation, 
packages.  The more the merrier.

## Licensing
Licensing is dull, but it is essential.

Bergamot Monitoring does not require copyright assignment.  All contributors 
will retain their copyrights and moral rights.  All contributors will be 
credited on the project website.

All code contributed must be licensed under the GNU LGPL V3.  Any dependencies 
that you make use of must be compatible with the GNU LGPL V3.  This is 
especially true when contributing code to Bergamot Monitoring core or when 
linking against any Bergamot Monitoring core code.

Where you are contributing an entire new service, that is not linking against 
any of the Bergamot Monitoring GNU LGPL V3 license then the above restrictions 
do not apply.  We would prefer to keep everything under a single license, 
however if there is specific reason for using an alternative license your 
contribution will be considered.

## How To Contribute

### Keep Talking
Before you contribute to Bergamot Monitoring please say hello on our IRC channel 
or mailing list.  Please discuss what you are considering contributing, it might 
be that someone else is already working on that.  Or maybe someone has an idea 
as to how something could be implemented.

### Code Contributions
Before contributing code to Bergamot Monitoring, it would be worth reading our 
developer documentation.  Make sure you have a development copy of Bergamot 
Monitoring up and running.

The core of Bergamot Monitoring is written in Java.  As such contributions to 
core functionality will be expected in Java.  By default Java 8 source code 
level is used, however the Bergamot Agent uses Java 6 source code level. Ideally 
your contribution should work with both the official Oracle JDK and the OpenJDK.

Contributions for entire services in other languages will be accepted on 
condition that someone is comfortable in maintaining and supporting the 
contribution.

Most importantly, your code should be as readable and as simple as possible. For 
Java code follow the standard Java coding standards, with one exception: braces 
on the following line.

### Submitting Your Contribution
Ideally fork our repository on GitHub, make your changes in a feature branch and 
then raise a pull request with Bergamot Monitoring.

Please do not email code patches directly to people and please don’t send patch 
requests to the mailing list.

When submitting a contribution please explain in detail:
What you are contributing
Why you are contributing it
What testing you have done of it (if applicable)

Note it is at the sole discretion of the Bergamot Monitoring project owners what 
contributions are accepted. 
