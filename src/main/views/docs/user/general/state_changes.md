---
Author: Chris Ellis
Date: 2015-04-06
Code: bash
---
# Bergamot Monitoring State Changes

Bergamot Monitoring has configurable thresholds for transitioning from ok to 
non-ok states and back.  Bergamot Monitoring also has hysteresis as the check 
period changed based on the state of the check.

This allows for checks to be configured to fail fast and recovery slowly.  Using 
a small `failed-after` threshold a check will fail fast, especially when 
combined with a quick `changing-every` period.  Setting a larger 
`recovers-after` threshold will cause a slow recovery of the check.

As an example, if `failed-after` is set to 4, `recovers-after` is set to 10 and 
the `changing-every` period is 30 seconds.  It would take 2 minutes for a check 
to raise an alert and it would take 5 minutes for a recovery to be raised.

Choosing the `every` period is a balancing act between spotting an error as 
soon as possible and the amount of resources required for monitoring.

Choosing the `retry-every` period is mainly based on how quick you wish for a 
check to be able to recover.

The following five attributes define how a check changes state.

<table>
    <tr>
        <th>Attribute</th> <th>Description</th>
    </tr>
    <tr>
        <td>failed-after</td> <td>the number of attempts before a check reaches a steady non-ok state</td>
    </tr>
    <tr>
        <td>recovers-after</td> <td>the number of attempts before a check reaches a steady ok state</td>
    </tr>
    <tr>
        <td>every</td> <td>the check period when the check is in a steady ok state</td>
    </tr>
    <tr>
        <td>changing-every</td> <td>the check period when  the check is changing between states</td>
    </tr>
    <tr>
        <td>retry-every</td> <td>the check period when the check is in a steady non-ok state</td>
    </tr>
</table>


