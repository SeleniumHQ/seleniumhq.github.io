---
title: "Public Project Meeting - April 22, 2021"
linkTitle: "Public Project Meeting - April 22, 2021"
date: 2021-04-22
tags: ["slack","meeting","tlc"]
categories: ["governance"]
author: Sri Harsha ([@sri_harsha509](https://twitter.com/sri_harsha509))
description: >
  Continuing the series of bi-weekly public project meetings...
---

{{< dismissible-banner title="Archived meeting minutes" alert="note" color="blue" >}}
All meeting minutes can now be found [here](/meetings).
{{< /dismissible-banner >}}

Continuing the series of bi-weekly public project meetings, here is the
timeline of the meeting held on April 22, 2021,5:30 PM CET. (Below times are on IST)

Meetings are held on the `#selenium-tlc` channel on [Selenium Slack](https://seleniumhq.slack.com/join/shared_invite/enQtODAwOTUzOTM5OTEwLTZjZjgzN2ExOTBmZGE0NjkwYzA2Nzc0MjczMGYwYjdiNGQ5YjI0ZjdjYjFhMjVlMjFkZWJmNDYyMmU1OTYyM2Y).

---   

_Diego Molina  9:02 PM_

pinging @simonstewart @AutomatedTester @harsha509 @Puja Jagani @jimevans @barancev @manoj9788 @mmerrell @titusfortner @p0deje

_Simon Stewart  9:02 PM_

I'm going to be at SauceCon

_9:02 PM_

Things I want for beta 4:

_Diego Molina  9:02 PM_

and whoever else is around :slightly_smiling_face:

_9:03 PM_

General project statistics
* Previous meeting: 163 open issues, 24 open pull requests
* Currently: 157 open issues, 25 open pull requests

_Simon Stewart  9:03 PM_

Things I want for beta 4:
* Refresh the NewSessionQueue
* Stress test the new Grid on AWS or similar with >50 simultaneous tests
* Check that the Redis/JDBC backed services actually function

_Diego Molina  9:06 PM_

Next topic:
* Pending work for the 4th Beta?
* Server - Grid
* Pipe VNC connectors through the WebSocket plumbing for live video
* Investigate: Grid gets stuck when screenshot is taken in a terminated session
* Investigate: Grid gets stuck when the browser driver process cannot be killed
* Investigate: Router request timeouts when forwarding command to Node
* Unify new Session Queue
* Stress test the new Grid on AWS or similar with >50 simultaneous tests
* Check that the Redis/JDBC backed services actually function
* More than 1 IE session per Node? https://github.com/SeleniumHQ/selenium/issues/9388

Language bindings
* “se:cdpVersion” is missing in C#, Java and JS
* Can all bindings do CDP over Grid?
* What are the features present in Selenium 4?
* Do all bindings implement them?

_Simon Stewart  9:06 PM_

The "unify new session queue" is mostly done, I think

_9:07 pm_

(Famous last words)

_Diego Molina  9:07 PM_

That is the PR you sent, which we will work with @Puja Jagani, I believe

_Luke Hill  9:07 PM_

Someone (In fact a couple), were asking me about the alphas / betas. 
And they were asking more along the lines of are we expecting a whole host of them like we had for v3.

_9:08 PM_

I gave them the whole spiel - We don't do release timelines e.t.c. 
But figured I'd ask here if anyone has a finger in air idea.

_Diego Molina  9:08 PM_

For the Grid work, I am taking the items related to “investigate”

_9:09 PM_

But I wanted to ask folks here, what do you think about 
“More than 1 IE session per Node? https://github.com/SeleniumHQ/selenium/issues/9388”

_Titus Fortner  9:10 PM_

@luke tell them it doesn't matter and if they are only using 
the bindings they should already have updated. The only stuff really being worked on at this point is grid functionality and adding in the various new features from w3c spec, etc
There is no technological advantage to sticking with 3.x at this point

_Luke Hill  9:10 PM_

That went against all advice and articles published that explicitly stated 
"Do not use more than 1 IE session per node" @diemol

_David Burns  9:10 PM_

re: se:cdpVersion, it's easy to add but I noticed we never use the versions, we seem to use the latest one

_Diego Molina  9:10 PM_

Some people are complaining they cannot run more than one test per Node, which we recommend against
Because they have been able to do it. The code change is small, and I believe we should let them, with the caveat of “you are on your own here”

_Luke Hill  9:11 PM_

Do we know when/where this restriction came in? With an accompanying PR?

_Diego Molina  9:11 PM_

IE restriction? Since the moment zero Grid 4 was built

_Simon Stewart  9:12 PM_

@AutomatedTester the java bindings should be using the version

_David Burns  9:12 PM_

@simonstewart sorry, meant JS

JS bindings only use latest...

_David Burns  9:12 PM_

@simonstewart sorry, meant JS

_9:13 PM_

JS bindings only use latest...

_Diego Molina  9:14 PM_

@AutomatedTester I believe it has not been implemented in C# and JS (I need to double check Java)

_David Burns  9:14 PM_

JS doesn't need it... yet without a bit of rearchitecting

I, or @Puja Jagani since she has been dabbling in .NET, can do the c# ones

or @jimevans obvs

_Diego Molina  9:15 PM_

I believe the idea is to use the “right” CDP version depending on the browser version, 
so it’d be nice if JS has it

_David Burns  9:16 PM_

maybe @harsha509 has time to look into it

_Sri Harsha  9:17 PM_

it requires rewriting the CDP in JS. i will look into it then.

_Diego Molina  9:17 PM_

that’d be amazing

cool, so I believe we need to work a bit on the pending items 
and see where we are in two weeks :slightly_smiling_face:

_Titus Fortner  9:18 PM_

From a bugs standpoint, #9359 is an absolute blocker for Sauce users to move to Selenium 4, 
but @Puja Jagani is helping confirm it.

Would be nice to at least know what is going on before next release

_Diego Molina  9:20 PM_

I think we have enough time to figure that one out

_Simon Stewart  9:20 PM_

The reproducible test case is helpful

_Titus Fortner  9:20 PM_

yeah, should have done that from the start

_Diego Molina  9:20 PM_

I wonder if someone asked @titusfortner for a reproducible test case :smile:

_Titus Fortner  9:21 PM_

tbf I did put exactly what needed to go into the POM

_9:21 PM_

I just didn't create a whole project that can be cloned to see the issue

_Diego Molina  9:21 PM_

anyway, we could move to the last topic for today, the IDE

_9:21 PM_

Selenium IDE
* Who knows what the release process is?
* What is needed?
* Who can help reviewing pull requests?

_Sri Harsha  9:22 PM_

By beta 4, i will be resolving JS api docs too. May be i can pass it to @diemol for review and upload

_Diego Molina  9:22 PM_

What is needed for a release*

_Diego Molina  9:23 PM_

@Todd Tarsi is here and they want to contribute, so it would be nice to give them a hand

_David Burns  9:23 PM_

if @corevo or @tourdedave have documented the release process I think I have some of the keys to do it

_Diego Molina  9:23 PM_

I can check if it is documented, else reach out to them

_Todd Tarsi  9:23 PM_

Hi all, yep if I can help in any way, please let me. 
I've been doing selenium ide stuff since it was UI with zanarkand (is that the right name?) back in the day

_Diego Molina  9:24 PM_

We need the keys for the 3 stores, right? Edge, Chrome and Firefox

_9:25 PM_

we should also update the docs to mention it is available in Edge

_Todd Tarsi  9:27 PM_

I can PR simple docs changes if we want.

_Diego Molina  9:27 PM_

cool

so we will check what is needed to properly test and 
release the IDE, and we will post here in #selenium-tlc

thanks for showing interest, @Todd Tarsi

ok, I think that is all for today, right?

does anyone have an extra topic?

seems not, thank you everyone!

(and thanks @harsha509 for publishing the notes to our blog :slightly_smiling_face:)
