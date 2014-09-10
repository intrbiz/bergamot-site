# FAQ

### What is the current state of play?

Bergamot is currently in it's fledgling stages, only being a few days work so far.  It is 
being actively developed at the moment and will evolve quickly.  As such it is very much an 
alpha.  But please feel free to get involved, I'd be interested to hear people's toughts, 
comments, ideas.  I'd be greatful for people willing to give it a try and provide feedback.

### Is Bergamot a drop-in replacement for Nagios?

That is the plan.  Bergamot is capable of importing Nagios configuration.  Bergamot does 
not implement all the semantics of Nagios, so there will be some edge-cases where Bergamot 
is not a drop-in replacement.  Bergamot is perfectly capable of executing Nagios plugins 
(checks), so worst case you should only need to alter some configuration.

### Why did you create Bergamot?

Having used Nagios for a while, I've been frustrated by its number of shortcommings.  I 
personally feel that Nagios is fundamentally broken.  This project was born out of a 
"how hard can it be" thought, once I could parse the Nagios config, how much work was 
it to execute the checks, etc.  I'm hoping that Bergamot can be an escape path for users 
of Nagios.

### Which shortcommings of Nagios does Bergamot address?

Personally I think Nagios has some fundamental architectural flaws.  Nagios is inherently 
a single process, holding all state in memory, and constantly forking processes to execute 
checks.  Whilst this works for small deployments, it soon falls down.  Bergamot is of a 
modern design, written in a safe, fast and clean language.

Out of the box, Bergamot gives you distributed monitoring.  Workers are loosely coupled via 
message queues to the core scheduling / result processing.  This single change solves 
the biggest failing of Nagios, granted this is partially tackled by Naemon.

Read the developer docs, for more details.

### Can Bergamot distribute / load-balance plugin execution?

Yes.  Bergamot uses RabbitMQ by default to queue messages between the Bergamot Master daemon 
and Bergamot worker daemons.  Routing of messages is flexible and configurable.  Therefore it 
is possible to have multiple workers executing checks (load-balancing) and also to distribute 
checks between different queues (distributed monitoring).  There is no limit to the number of 
workers which can be running, or to the number of queues which can be used.

### Can Bergamot be configured live?

You bet ya (it will be able to soon).  You can add checks, live, via the UI.  There is no 
need to restart Bergamot to load configuration changes.  Configuration and state is persisted 
into a PostgreSQL database.  You will also be able to import configuration, whilst Bergamot 
is running.  The aim is not never have to restart Bergamot.

### Does Bergamot have an API?

It will have.  Clients will be able to execute RPC calls and listen to events.  This will be 
accessible over RabbitMQ and web-sockets.

### Why did you write it in Java?

Well, first off, Java is my language of choice.  I like its clean syntax, while it can be 
verbose at times, I find it to be very readable.  It is fast, being JITed it can get close to 
if not faster than the performance of C.  A good standard library, with sane data structures.
Popular, Java is the second most widely used language after C.  Java has great tooling, Eclipse 
is a fantastic IDE, it has an excellent debugger built-in and profiling tools.

Usually people are afraid of Java because they believe some myths about it.  Usually people 
perceive Java to be memory hungry.  Java does have overheads because it is a virtual machine 
(usually around 20MB), but ultimately an object isn't that different from a C structure.
Often it looks like Java is using alot of memory, due to the fact it avoid doing garbage 
collection, why bother if you don't need to.  Also bear in mind, these days, memory is cheap!

### Is Bergamot Free / Open Source Software?

Yes.  It is licensed under the terms of the GNU Lesser General Public License (LGPL) V3.

### Does Bergamot support Nagios Event Broker Modules?

No and it probably never will.

### Can Bergamot execute Nagios plugins (checks) ?

Yes.

### Does Bergamot support Livestatus?

Not currently, however we might implement some form of support to allow UI's developed for 
Nagios to be used with Bergamot.

### Where did the name come from?

I quite like Earl Grey tea.

