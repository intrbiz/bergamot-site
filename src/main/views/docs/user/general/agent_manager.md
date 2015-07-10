---
Author: Chris Ellis
Date: 2015-07-10
Code: bash
---
# Bergamot Agent Manager

The Bergamot Agent Manager is a special daemon which sole purpose is to 
sign TLS certificates.  By the nature of what the Bergamot Agent Manager 
does, it is critical to propperly secure it.

The keys generated and certificates signed by the Bergamot Agent Manager 
are used to secure what Bergamot Agents connect to and what agents can 
connect.

It is essential to issolate the Bergamot Agent Manager as much as possible 
from the other components of your Bergamot Monitoring deployment.  The 
Bergamot Agent Manager MUST be deployed to a dedicated server.  This should 
be in a heavily restricted network.

The Bergamot Agent Manager will need to connect in to the RabbitMQ cluster. 
However it does not require access to anything else.

The Bergamot Agent Manager by default will store it's generated root 
certificate autority and per site intermediate signing keys in plain text 
on the file system.  As such it is currently reccomended to store the Bergamot 
Agent Manager on an encrypted file system.

Remote and physical access to the server running the Bergamot Agent Manager, 
must be restricted to those who are strictly necessary.

In the event that the server running your Bergamot Agent Manager is breached.  
You will need to rekey all deployed Bergamot Agents.  This would require 
reconfiguring every Bergamot Agent that is deployed. As such it is imperitative 
that you take the security of your Bergamot Agent Manager server seriously.

If you started off using the quick start Bergamot Monitoring appliance to kick 
start your deployment.  You should migrate the pre-installed Bergamot Agent 
Manager to a dedicated server.  Ideally you should re-key your deploy Bergamot 
Agents.
