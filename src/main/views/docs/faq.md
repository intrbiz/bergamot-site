# FAQ

### What is the current state of play?

Bergamot Monitoring is a version 1 release, at this stage the core architecture 
of Bergamot Monitoring is stable but some features are lacking.  The version 1 
release has all the basic featured needed for a monitoring system and would be 
acceptable for deployment, feedback is appreciated.

There will be on going releases, on a short release cycle, which will focus on 
adding more features.

### Is Bergamot a drop-in replacement for Nagios?

To an extent.  Bergamot Monitoring is capable of executing Nagios plugins and 
of directly executing plugins via NRPE.  Bergamot Monitoring does not directly 
utilise the Nagios configuration format, however a conversion tool can convert 
a Nagios configuration to Bergamot Monitoring's configuration.

Bergamot Monitoring also does not support Nagios Event Broker modules nor does 
it support LiveStatus.  Bergamot Monitoring offers an easy migration path but 
is not a drop in replacement for Nagios.

### Why did you create Bergamot?

Having used Nagios for a while, I've been frustrated by its number of 
shortcomings.  I personally feel that Nagios is fundamentally broken.  This 
project was born out of a "how hard can it be" thought, once I could parse the 
Nagios config, how much work was it to execute the checks, etc.  I'm hoping 
that Bergamot can be an escape path for users of Nagios.

### Which shortcommings of Nagios does Bergamot address?

Personally I think Nagios has some fundamental architectural flaws.  Nagios is 
inherently a single process, holding all state in memory, and constantly forking 
processes to execute checks.  Whilst this works for small deployments, it soon 
falls down.  Bergamot is of a modern design, written in a safe, fast and clean 
language.

Out of the box, Bergamot gives you distributed monitoring.  Workers are loosely 
coupled via message queues to the core scheduling / result processing.  This 
single change solves the biggest flaw of Nagios.

### Can Bergamot distribute / load-balance plugin execution?

Yes.  Bergamot is distributed by default using messages queues between the 
Bergamot master nodes and Bergamot worker nodes.  Routing of messages is 
flexible and configurable.  Therefore it is possible to have multiple workers 
executing checks (load-balancing) and also to distribute checks between 
different queues (distributed monitoring).  There is no limit to the number of 
workers which can be running, or to the number of queues which can be used.

### Can Bergamot be configured live?

You bet ya.  You can add checks, live, via the UI and API.  Effort is taken to 
apply configuration changes atomically and with least impact.  The intention is 
to support small and constant configuration changes.  Modern infrastructures 
are flexible and changing often, a monitoring system needs to cope with this.  
Coupled with clustering of all Bergamot Monitoring daemons and the lack of any 
single point of failure, the aim is for Bergamot Monitoring to support high 
availability out of the box.

### Does Bergamot have an API?

Yes.  Bergamot Monitoring has a rich JSON REST web services API.  The API can 
be used to perform any action that can be performed via the web user interface.  
In addition a websocket API can be used to receive real time status updates.  

Should deeper integration be required, the internal APIs maybe used via the 
message queues.  However the stability of these internal APIs is not guaranteed.

### Why did you write it in Java?

Well, first off, Java is my language of choice.  I like its clean syntax, while 
it can be verbose at times, I find it to be very readable.  It is fast, being 
JITed it can get close to the performance of native code.  A good standard 
library, with sane data structures.  Popular, Java is the second most widely 
used language after C.  Java has great tooling, Eclipse is a fantastic IDE, it 
has an excellent debugger built-in and profiling tools.

### Is Bergamot Free / Open Source Software?

Yes.  It is licensed under the terms of the GNU Lesser General Public License 
(LGPL) V3.

### Can Bergamot execute Nagios plugins (checks) ?

Yes.  NRPE is natively supported, using non-blocking TCP sockets, no need to 
fork a process to create a TCP connection.  You might also wish to look at the 
Bergamot Agent, this provides a far better level of security than NRPE does.

### Does Bergamot support Nagios Event Broker Modules?

No and it never will!

### Does Bergamot support Livestatus?

No.  It would require a reasonable amount of logic to map between LiveStatus 
'tables' and Bergamot's object model.  Instead make use of the JSON REST API 
provided by Bergamot.

### Where did the name come from?

I quite like Earl Grey tea.  Earl Grey tea is flavoured with oil extracted 
from the skin of the [Bergamot Orange](https://en.wikipedia.org/wiki/Bergamot_orange), 
a citrus fruit.

