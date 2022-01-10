---
title: "Public Project Meeting - March 11, 2021"
linkTitle: "Public Project Meeting - March 11, 2021"
date: 2021-03-11
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
timeline of the meeting held on March 11, 2021,5:30 PM CET. (Below times are on IST)

Meetings are held on the `#selenium-tlc` channel on [Selenium Slack](https://seleniumhq.slack.com/join/shared_invite/enQtODAwOTUzOTM5OTEwLTZjZjgzN2ExOTBmZGE0NjkwYzA2Nzc0MjczMGYwYjdiNGQ5YjI0ZjdjYjFhMjVlMjFkZWJmNDYyMmU1OTYyM2Y).

---   

_Diego Molina  9:59 PM_

Hopefully it will be a brief one :slightly_smiling_face:
pinging folks for the public project meeting…
@AutomatedTester @harsha509 @titusfortner @mmerrell @jimevans @simonstewart @barancev @luke

_10:01_

Agenda for today:
* General project statistics
* Anything new to report from the last PLC/SFC call?
* CoC, next steps?
* Pending work that needs to be done for the second Beta?

if you have any more topics, please feel free to add them

_Titus Fortner  10:02 PM_

:wave:

_Sri Harsha  10:02 PM_

:wave:

_Diego Molina  10:03 PM_

ok, let’s start slowly while people join…
first topic is:
* General project statistics

Previous meeting: 234 open issues, 32 open PRs
Current: 202, 32 PRs

_10:04_

Again, really great work lowering the number of open issues!

_Diego Molina  10:05 PM_

What I see in the open issues, every now and then, is a few feature requests. 

I think it’d be helpful to give some feedback on them at some point

_Titus Fortner  10:05 PM_

I need to remember not to add PRs until after the meeting :)

_Diego Molina  10:06 PM_

for now, I am closing a few feature requests because they are not aligned at all with 
Selenium or because no one has commented in over 1.5 years

_10:07_

ok, let’s move to the next topic
Anything new to report from the last PLC/SFC call?

_10:08_

@mmerrell @barancev @simonstewart @manoj9788 @jimevans

_Simon Stewart  10:08 PM_

Nothing major. @mmerrell was going to chat with the SFC about the Coc

_Diego Molina  10:08 PM_

perfect, that was the next topic, CoC :slightly_smiling_face:

do we know what we can do to speed the process a bit? We had an incident in the past few 
days where a CoC would be helpful https://groups.google.com/g/selenium-users/c/bpeUO9vAa2A

_10:10_

Could we add the https://www.contributor-covenant.org/ as a start and then iterate on it when the SFC has time?

_Marcus Merrell  10:10 PM_

that chat happened, and Karen is supposed to reach out to the contractor - I haven't pinged her in a week, but I can do that

_Diego Molina  10:11 PM_

thank you, @mmerrell!

_David Burns  10:11 PM_

I think we can just copy what Sage did from their repos

_Diego Molina  10:11 PM_

that is also a good idea to have a place to start

_David Burns  10:11 PM_

yea

_10:11_

and I think we can have the odd hour from Sage to process it since they work for the SFC now

_Diego Molina  10:12 PM_

I forgot about that, I’d say we can do that

_10:13_

what do the other folks think?

_Simon Stewart  10:13 PM_

I'm okay with that

_Marcus Merrell  10:14 PM_

I'm fine with it... I simply can't believe it's been a year

_David Burns  10:16 PM_

tbf... I could have pushed harder but since I am with ${employer} that can be hard to find time

_Marcus Merrell  10:16 PM_

I'm in the same boat, but it's unreasonable that we have to push this hard
as far as I know, we're a top revenue driver for the SFC, and asking a 
lawyer to do an hour of lawyering does not seem unreasonable

but we aren't in a position to make a big change here, so I'll keep pressing where I can


_Diego Molina  10:18 PM_

absolutely, at least it feels that we can have a CoC soon by following this path

do you still have the docs Sage gave us, @AutomatedTester?

_Marcus Merrell  10:19 PM_

if we get that, do we still need to go through the SFC and everything?

_David Burns  10:19 PM_

I am sure I do

_Diego Molina  10:19 PM_

I think we still need an expert on the field to help us refine
the edges so we are sure we have something good in place

_Marcus Merrell  10:19 PM_

ok, good

_10:20_

I just pinged KS

_David Burns  10:20 PM_

at worst I can take Sage's repos and just cobble something together

_Diego Molina  10:20 PM_

perfect, that’d be great

ok, perhaps we can move to the next topic?

* Pending work that needs to be done for the second Beta?

_Titus Fortner  10:24 PM_

we're as good on grid as we're going to get without changing the underlying http client, right?

_Diego Molina  10:24 PM_

ok, so this is what I have about that:
Server changes:
* Pipe VNC connectors through the websocket plumbing for live video (nice to have for Beta 2, not a must)
* Return CDP url by using the Grid url (nice to have for Beta 2, not a must)
* Allow timeout, cleanupCycle to be configured in Grid (this is about allowing users configure some timeouts, @Puja Jagani did a part of it already, the rest seems simple, but not a must for Beta 2)

Things left in Java, Python, JS, Ruby, C#?

* Using se:cdpVersion

yes, about the http client, I’d say so, but we will know more after people use beta 2

_Titus Fortner  10:25 PM_

is there a reason we can't release 2 right away and do the rest for 3?

there are a few things in the Ruby code I want to be able to start using

I'm assuming there have been enough improvements elsewhere to justify a new release?

Would be nice to have more smaller releases so long as code/tests are in a good shape

_Diego Molina  10:27 PM_

I also have the feeling that the major issue was the Grid memory usage, which we adjusted by having a single http client instance
everything else seems to be small adjustments, some could happen for beta 2 and the rest for beta 3

_Simon Stewart  10:27 PM_

I think so

I'm fine with no getting the se:cdpVersion stuff in before we push the next beta

_10:28_

Also on my list before 4.0 is getting script pinning using CDP up and running

That feels somewhat gnarly

_Diego Molina  10:30 PM_

sounds good, so maybe let’s sync during the next days to see when we can release beta 2
(as in, when people are around to give their input as well)

_David Burns  10:30 PM_

It sounds like there is nothing left to do...

_Diego Molina  10:30 PM_

we need to fix the javadocs


_David Burns  10:31 PM_

yes!

_Diego Molina  10:31 PM_

(maybe that is the most important issue for now :slightly_smiling_face:)

_David Burns  10:32 PM_

definitely the most important issue

_Diego Molina  10:33 PM_

ok, I think that was the meeting for today, do we have anything else we’d like to discuss?

_David Burns  10:33 PM_

nope

_Sri Harsha  10:33 PM_

we need to update JS doc too

https://www.selenium.dev/selenium/docs/api/javascript/index.html

_Diego Molina  10:34 PM_

true, while checking old issues, there are 3-4 we could close by updating the docs

ok, so let’s wrap today’s meeting with that, thank you everyone!
