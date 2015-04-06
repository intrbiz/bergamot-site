---
Author: Chris Ellis
Date: 2015-04-06
Code: bash
---
# Bergamot Monitoring Scheduling Overview

Bergamot Monitoring by default uses an imprecise scheduling algorithm, as such 
there are a few edge cases which need to be considered.

The scheduler used by Bergamot Monitoring is designed around optimising 
scheduling the periodic execution of checks at scale and as such has a few 
caveats.  Firstly checks cannot execute faster than once every second.  Secondly
checks are always randomly skewed within the bounds of the scheduling period.

When a check is first scheduled, the initial delay is randomised between 0 and 
the check period.  Imagine a check which executed every 5 minutes, the initial 
delay will be a random value between 0 and 5 minutes.  After this initial delay 
the check will execute every 5 minutes.

This skewing aims to ensure checks are evenly executed and attempts to 
eliminate the thundering heard problem, where by every check would execute at 
the same time.  As such this represents a trade off between scheduling 
precision and stability.  It is considered more important to keep executing 
checks in a stable manner than it is to execute checks at a precise time.

Scheduling is separated into a number of segments, each segment represents a 
second in time.  Checks are balanced across these buckets, leading to the 
limitation that a check can only execute at most every second.  This bucketing 
is based around the concept that there will always be work for the 
scheduler.  Even small scale deployments are likely to be executing a check 
once a second.

The scheduler essentially uses Time Periods as calendar filters.  Each time a 
check should execute then the Time Period is used to compute if the current 
time is valid for that Time Period, if it is not nothing happens otherwise the 
check will execute.

These optimisations are suitable for the vast amount of checks which will be 
executed.  However a small number of configurations might have issues, if they 
are expecting precise scheduling.

Imagine a situation where a check should execute at 09:00 every day, this might 
be configured using time range `09:00 - 09:01`.  However the scheduler will 
make no effort to ensure that the check is executed at 09:00.  Because the 
check execution is skewed, a time range cannot be less than the check 
period.  As such the best that can be expected is that the check would execute 
some time between 09:00 and 09:05, assuming a 5 minute period.

