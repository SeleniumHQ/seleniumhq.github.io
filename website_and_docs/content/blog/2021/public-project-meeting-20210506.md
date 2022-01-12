---
title: "Public Project Meeting - May 06, 2021"
linkTitle: "Public Project Meeting - May 06, 2021"
date: 2021-05-06
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
timeline of the meeting held on May 06, 2021,5:30 PM CET. (Below times are on IST)

Meetings are held on the `#selenium-tlc` channel on [Selenium Slack](https://seleniumhq.slack.com/join/shared_invite/enQtODAwOTUzOTM5OTEwLTZjZjgzN2ExOTBmZGE0NjkwYzA2Nzc0MjczMGYwYjdiNGQ5YjI0ZjdjYjFhMjVlMjFkZWJmNDYyMmU1OTYyM2Y).

---   


_Simon Stewart  9:01 PM_

Alright then. It's time

_9:02_

@AutomatedTester, @titusfortner @p0deje, @jimevans, @harsha509, @barancev, @Puja Jagani, and @diemol (plus anyone else who's interested)

Let's get the show on the road


_9:02 PM_

I think we can wait for @diemol to be back for the stats for the past couple of weeks

Any agenda items?

_9:03 PM_

* Stats
* What do we want in Beta 4?
* When do we ship Beta 4?

_Diego Molina  9:03 PM_

Here, quick meeting with my manager done

_David Burns  9:04 PM_

Managers are the worst...

_Simon Stewart  9:04 PM_

hands over meeting reigns to @diemol

_Diego Molina  9:04 PM_

Stats from previous meeting:

* 157 open issues, 
* 25 open pull requests
* Current: 163 open issues, 30 open pull requests

Data taken from this link[https://www.diemol.com/github-repo-stats/seleniumhq_selenium/index.html]

_David Burns  9:05 PM_

A lot of those are from @Puja Jagani

_9:05 PM_

she has been busy

_Simon Stewart  9:06 PM_

She's been rocking it

_Diego Molina  9:06 PM_

the issues or the PRs?

_9:07_

Right, work that we want for the next beta (4)

(I could not contribute that much during the last two weeks :confused:)

_9:08 PM_

These are items from the last meeting:

* Pipe VNC connectors through the WebSocket plumbing for live video
* Investigate: Grid gets stuck when screenshot is taken in a terminated session
* Investigate: Grid gets stuck when the browser driver process cannot be killed
* Investigate: Router request timeouts when forwarding command to Node
* Unify new Session Queue
* Stress test the new Grid on AWS or similar with >50 simultaneous tests
* Check that the Redis/JDBC backed services actually function
* More than 1 IE session per Node? https://github.com/SeleniumHQ/selenium/issues/9388

Maybe only this one got done? “Unify new Session Queue”

_Simon Stewart  9:08 PM_

I'd like to add:

* Distributor follows spec when allocating new sessions

_David Burns  9:08 PM_

Do we have issues/docs on the investigate stuff?

_Simon Stewart  9:08 PM_

Yeah. We unified the new session queue

_9:09 PM_

I've a PR in the works for the "follow the spec" bit

_David Burns  9:09 PM_

for the last item about IE... we can allow that to happen but it seems like a major footgun

_Diego Molina  9:10 PM_

There are issues for the investigation items, 
and maybe there is a PR from @Puja Jagani solving the 
“browser driver process cannot be killed” one

I will double check that all items have a GitHub issue, and if not, I will create one

_David Burns  9:12 PM_

thank you

_Diego Molina  9:14 PM_

For language bindings we had this:

Language bindings

* “se:cdpVersion” is missing in C#, Java and JS
* Can all bindings do CDP over Grid
* Document features and double check all bindings implement all features

_Saksham Gupta  9:14 PM_

Hello all, been a year since I started my internship. Thought I'll drop by to say hi.

_Simon Stewart  9:15 PM_

Java should be doing se:cdpVersion now

_David Burns  9:15 PM_

@harsha509 do you need a hand with the cdpVersion stuff for JS?

_Simon Stewart  9:16 PM_

I think we need to review what's in the python bindings that's 
not elsewhere: my impression is that there's been a few things added

_David Burns  9:17 PM_

@simonstewart only 1 thing which is mobile options

_Simon Stewart  9:17 PM_

kk

_Sri Harsha  9:17 PM_

Yes @AutomatedTester, i have been busy with paid work, 
didnt got a chance to look into it

_Diego Molina  9:17 PM_

what are mobile options?

_David Burns  9:17 PM_

@diemol the ability to set android* config better

you could do it before but I made it a little nicer after a request from whimboo

_9:18 PM_

I actually want to change it to support iOS better

_Diego Molina  9:18 PM_

Nice!

_David Burns  9:19 PM_

but it means you can create a normal chrome/firefox options and 
pass it through to the driver to connect to said browser on android
:+1:
1

_9:20 PM_

you could before if you knew the magic for it

_Diego Molina  9:20 PM_

Something else we need to figure out is how to release the IDE, 
given that @Todd Tarsi is helping us and contributing to it

_9:20 PM_

does anyone know about it?

_Simon Stewart  9:20 PM_

Who did the last release? @corevo, I thought

_9:20 PM_

?

_David Burns  9:21 PM_

I think it was him. I will email him and Dave for guidance here

_Diego Molina  9:21 PM_

great, thank you

and I have one last thing from my side

I have spoken to @Puja Jagani and asked her if she is interested in becoming a committer
Therefore, I have created this PR https://github.com/SeleniumHQ/seleniumhq.github.io/pull/679
Folks, please go an check it out

I think she has done so much great work and without her we would not have moved at the speed we are currently moving :slightly_smiling_face:


_Puja Jagani_

Thank you David, Diego and Simon for the constant support and help 
:relaxed: Very happy working with the Selenium TLC folks!

_Simon Stewart  9:25 PM_

I'm onboard with this

_Diego Molina  9:25 PM_

(there is probably a shorter way to say what I said, but my English is no bueno sometimes)

_David Burns  9:26 PM_

+1 but I am biased :smile:

_Diego Molina  9:26 PM_

We simply need PR approvals :slightly_smiling_face:

_Titus Fortner  9:27 PM_

Sorry, I'm showing up late. :slightly_smiling_face:

Did we get the blockers for beta 4?

Have a couple bug fixes that would be nice to get released

_Simon Stewart  9:27 PM_

@diemol’s in charge here

_Diego Molina  9:28 PM_

No @titusfortner, sorry. I think we need to improve with PR reviews 
in the next days so we can get closer to beta 4

_9:28 PM_

I believe you are talking about the JUnit issue?

_Sri Harsha  9:29 PM_

i will update few things in ide stuff, electron version bump and updating rules_nodejs

_Titus Fortner  9:29 PM_

No, that's a different issue :slightly_smiling_face:

_Diego Molina  9:30 PM_

Which issues did you have in time?

_9:30 PM_

in mind*

_Titus Fortner  9:32 PM_

some fixes in Ruby code

_9:32 PM_

just wanted to get stuff out there sooner if that's an option

_Simon Stewart  9:33 PM_

We've yet to agree when we want to ship beta4, so there's probably time

_9:34 PM_

Unless you want the fixes to be blockers

_Titus Fortner  9:35 PM_

I mean, Alex changed something that was blocking 
someone and I'm wondering when we can get it released to him :smile:

_Simon Stewart  9:36 PM_

Ah! So you want the release sooner :slightly_smiling_face:

_Titus Fortner  9:36 PM_

_9:36 PM_

apparently there is a project that refuses to look at firstMatch values

_Sri Harsha  9:36 PM_

For JS i want to:
* Implement “se:cdpVersion”
* API documentation for beta 4

_Simon Stewart  9:37 PM_

@titusfortner I have no idea how a project not following the 
spec is a reason for us to ship sooner, but I assume there's context I'm missing….

_David Burns  9:37 PM_

is this appium?

_Titus Fortner  9:40 PM_

A Watir user is using aerokube/ggr

Ruby code defaults to F/M, but the project doesn't accept it (https://github.com/aerokube/ggr/issues/314)

He asked the devs and their private reply: Ggr does not support "sophisticated" selenium "match" 
rules and we do not plan to implement that, because it is ambiguous

So Ruby changed default implementation to be A/M since it *shouldn't matter but apparently does

just trying to help the user

_Simon Stewart  9:41 PM_

It's the exact opposite of ambiguous

_Titus Fortner  9:41 PM_

oh, I know

_9:41 PM_

if this had been a public response i'd have been all over it

_Simon Stewart  9:41 PM_

WE should probably have a chat with the Aerokube folks

_9:42 PM_

Because Se4 will break them a lot

_Titus Fortner  9:42 PM_

The suggestion I got is either to try their new product: aerokube.com/moon 
or do not use ggr and go with selenium.

_Simon Stewart  9:43 PM_

So moon supports the w3c payload but not ggr?

_Titus Fortner  9:44 PM_

that's what it sounds like

_David Burns  9:44 PM_

Aerokube… the group that bad mouths selenium… that aerokube?

_Titus Fortner  9:44 PM_

I don't know what ggr vs moon is, 
didn't get too deep into it

_Simon Stewart  9:45 PM_

Moon is their paid-for product.

_Titus Fortner  9:45 PM_

ah of course it is

you want fancy, you pay the money!

s/fancy/sophisticated

_Simon Stewart  9:45 PM_

A perfectly valid business model

_David Burns  9:45 PM_

In which they they tell everyone that selenium grid is useless

_9:46 PM_

I’m happy to help those that don’t bad mouth us

Difference of opinion is also fine

_9:47 PM_

But… *channels his inner @jimevans *

_Titus Fortner  9:47 PM_

I don't remember off hand which bindings default to A/M and which to F/M

_9:47 PM_

I was going to run some tests

_Diego Molina  9:50 PM_

I think we should not hurry for a project that does not support standards

_Titus Fortner  9:51 PM_

I don't care about them

_9:51 PM_

I care about a user who needs to change because of them

_Diego Molina  9:51 PM_

Anyway, I think those were all the topics we had :slightly_smiling_face:
