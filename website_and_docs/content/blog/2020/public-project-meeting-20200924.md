---
title: "Public Project Meeting - September 24, 2020"
linkTitle: "Public Project Meeting - September 24, 2020"
date: 2020-09-24
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
timeline of the meeting held on September 24, 2020 (times are on IST). 

Meetings are held on the `#selenium-tlc` channel on [Selenium Slack](https://seleniumhq.slack.com/join/shared_invite/enQtODAwOTUzOTM5OTEwLTZjZjgzN2ExOTBmZGE0NjkwYzA2Nzc0MjczMGYwYjdiNGQ5YjI0ZjdjYjFhMjVlMjFkZWJmNDYyMmU1OTYyM2Y).

The next meeting will be on October 09, 2020, 4:30 PM CET.

---   
_Diego Molina  9:00 PM_

So this is the proposed agenda, and @AutomatedTester has some more topics:
General project statistics
* Previous meeting: 334 open issues, 63 open PRs
* Current: 281, 61 PRs
Overall announcements? New people on committees, with the commit bit, etc?
* Anything new to report from the last PLC/SFC call?
* Pending work that needs to be done for Alpha 7?
* Pending work that needs to be done for the first Beta?

_David Burns  9:00 PM_

I dont have more topic, I want to discuss some of those topics

_Diego Molina  9:01 PM_

I need to improve reading skill :slightly_smiling_face:

_9:01 PM_

OK, let’s start

_Simon Stewart  9:01 PM_

settles in

_Diego Molina  9:02 PM_

General project statistics
Previous meeting: 334 open issues, 63 open PRs
Current: 281, 61 PRs
We are slowly cleaning the list of issues, more cleaning to come :slightly_smiling_face:

_Simon Stewart  9:02 PM_

Another 32 weeks, and we’ll be done
(Well, PRs)

_David Burns  9:03 PM_

I think we should pat ourselves on the back. In February we had over 600 issues

_Diego Molina  9:03 PM_

Absolutely

_9:04_

Ok, next topic
Overall announcements? New people on committees, with the commit bit, etc?
I am not aware of any announcements…

_Simon Stewart  9:04 PM_

Not that I’m aware of

_Diego Molina  9:04 PM_

Ok, so probably the next topic won’t have any updates either
Anything new to report from the last PLC/SFC call?

_Simon Stewart  9:05 PM_

Not from me

_Diego Molina  9:06 PM_

Perfect, so let’s jump to the next one
Pending work that needs to be done for Alpha 7?

_Jim Evans:black_medium_square:  9:06 PM_
.NET needs a fair bit of refactor for CDP
i’m planning on taking all day tomorrow to look at that.

_David Burns  9:07 PM_

Oh that's awesome

_Simon Stewart  9:07 PM_

The java code is in a releasable state, I think
Though I broke a test in the distributor, so I may want to fix that before landing

_Rajendra Kadam  9:07 PM_

I am working on js cdp stuff

_David Burns  9:07 PM_

The python code for here is just me putting in the work to make the API not suck

_Jim Evans:black_medium_square:  9:07 PM_

once i get CDP generation redone for C#, i’ll be pretty happy with the state of the .NET code base.

_Simon Stewart  9:07 PM_

There’s the session queue PR that it’d be nice to land, but I don’t think it’s essential

_David Burns  9:07 PM_

@p0deje has the Ruby stuff done

_Diego Molina  9:07 PM_

I am reviewing @Puja Jagani’s PR about the session queue, it’d be nice to have it for the alpha

_Simon Stewart  9:07 PM_

@jimevans this is good news :slightly_smiling_face:

_Jim Evans:black_medium_square:  9:08 PM_

i do need to think about a “version independent API for CDP” for .NET

_Titus Fortner  9:08 PM_

Do we need cdp for alpha 7? There are a number of bug fixes from 6 to get out there

_Simon Stewart  9:08 PM_

I’d like us to have a fairly unified approach between (at least) java and .net for the unified CDP. Happy to make changes to the java tree to make that happen

_David Burns  9:08 PM_

The queueing stuff I think needs to be in the alpha so we can get it to nightly users

and then improve in the betas

_Jim Evans:black_medium_square:  9:09 PM_

@titusfortner i at least need to get that version-independent api done before alpha7. .NET skipped alpha6 altogether, because i suck.

_Simon Stewart  9:09 PM_

@titusfortner in the java tree, the new CDP stuff has allowed us to hook in a bunch of APIs. It’d be nice if those were in other languages too

_David Burns  9:10 PM_

but... feature wise after that we're done right?
I'm itching to get us out of alpha
we're not getting enough usage

_Simon Stewart  9:10 PM_

I think we’re basically there once it’s done
How’s the UI looking?

_Diego Molina  9:11 PM_

Sounds like it, for Alpha 7, to summarise:
CDP working across bindings
Session queue in Grid

_David Burns  9:11 PM_

the UI is there, I think. We've fixed the main UI issues

_Jim Evans:black_medium_square:  9:11 PM_

i agree with that assessment. once CDP work for .NET is done, i’m considering it feature-complete for 4.x.

_David Burns  9:11 PM_

and that's all in trunk

_Diego Molina  9:13 PM_

Great, I am not sure if there is anything else to add to the topic

_Simon Stewart  9:13 PM_

Other than a proposed date to release a7?

_David Burns  9:13 PM_

next week (no pressure everyone)

_Jim Evans:black_medium_square:  9:14 PM_

wednesday or after of next week. i’m sure i’m not going to get it all done in one day.

_David Burns  9:14 PM_

Thursday and Fridays are our normal release dates so that sounds good

_Simon Stewart  9:14 PM_

I’m going to make a decision to gate the release on @jimevans saying that .Net is ready to roll

_Jim Evans:black_medium_square:  9:14 PM_

to quote @AutomatedTester “no pressure” :slightly_smiling_face:
we’re putting a stake in the ground saying a7 is our last alpha?

_Simon Stewart  9:15 PM_

With a hard stop-date of 2020-10-07
For java, yes
And I think we have the most moving parts

_Jim Evans:black_medium_square:  9:15 PM_

(agreed as a7 being last alpha for .NET too)

_Diego Molina  9:17 PM_

Any comments from other bindings?
@AutomatedTester @titusfortner @rajendra @harsha509?

_David Burns  9:17 PM_

I want us out of alpha
so...

_Simon Stewart  9:17 PM_

@AutomatedTester we all want to be out of alpha

_Rajendra Kadam  9:17 PM_

working out for js, some tests issues, trying to fix those

_Simon Stewart  9:17 PM_

But we also don’t want to move “just because”

_Sri Harsha  9:18 PM_

JS is already in alpha 7, can we release JS npm with like alpha7-patch1 with CDP ?

_Simon Stewart  9:18 PM_

Bump JS to alpha8
Keep it simple :slightly_smiling_face:

_Sri Harsha  9:19 PM_

ok, Thank you @simonstewart

_Diego Molina  9:21 PM_

Does it make sense to discuss the next topic? “Betas”

_Simon Stewart  9:22 PM_

We can move on from “alphas” :slightly_smiling_face:

_Diego Molina  9:22 PM_

Like, having a timeline or something like that for Betas?

_David Burns  9:22 PM_

There are a number of issues that are "assigned"  in the "in Progress" part of https://github.com/SeleniumHQ/selenium/projects/2
I think understanding which of those features are needed for use to move through the betas is important

_Simon Stewart  9:23 PM_

I have a pile of branches locally with changes
If it’s in the 4.0 roadmap, that kind of suggests it’s needed before release
If it’s not in the roadmap, we don’t need to worry about it
(For the release itself)
(We do need to worry about it)

_David Burns  9:24 PM_

so... I guess my request is, if you're doing it, what can we do to get it sorted (like hand off to someone else) or if you've not started can you unassign yourself
I think it would be good to get a clearer picture of where people can help

_Simon Stewart  9:25 PM_

I’m not actually assigned to that much

_David Burns  9:25 PM_

this is a comment to all of us really

_Simon Stewart  9:25 PM_

One code review, one “add support for retries to handlers”
I’ve a local change that has the skeleton of retries written, as well as fallback URLs for commands

_Diego Molina  9:27 PM_

I’ve seen that one and wanted to check, but time hasn’t been on my side lately

_Simon Stewart  9:27 PM_

Join the club :slightly_smiling_face:

_David Burns  9:28 PM_

so part of my request really is knowing where I can help without having to badger you all
I being me and my team

_Simon Stewart  9:29 PM_

Getting Grid rock solid would be really helpful
I suspect we’re not really using ZMQ properly
And if that falls over, we’re in trouble

_David Burns  9:31 PM_

we've engaged @adamgoucher and he seems Ok, but this is where I want to get more usage from betas
and the grid is pretty solid
we've fixed the main issues from browserstack at least

_Simon Stewart  9:32 PM_

One thing I’d quite like is a way to hook in SaaS providers to the Grid

_David Burns  9:32 PM_

and I would love to get a new alpha out so we can start using Jaegar more

_Simon Stewart  9:33 PM_

The Docker support also needs some work
Firebase support and XRay would be nice for use in GCP and AWS
Our HTTP client needs work: I don’t think reactor is really going to work for us

_9:34_

Every time I try and switch to it, there’s Yet Another Weird Failure

_David Burns  9:35 PM_

I am happy to get Puja involved here

_Simon Stewart  9:36 PM_

I think one thing we’d really love to demo is “deploying Grid to EKS” and seeing it start outputting information
@Puja Jagani’s help would be amazing

_Puja Jagani  9:36 PM_

Would love to help!

_Simon Stewart  9:36 PM_

Awesomeness :slightly_smiling_face:

_Diego Molina  9:36 PM_

we can do that, it should not be hard :slightly_smiling_face:

_Simon Stewart  9:37 PM_

Getting a Kafka version of EventBus would allow folks to use other hosted infra, but I think that may be too much for 4.0

_Diego Molina  9:39 PM_

I prefer to see the Gird being rock solid at its core, and when that is the case, add the other things

_David Burns  9:39 PM_

augementing things like that is a definite post 4.0 item

_Diego Molina  9:39 PM_

for example, I would say we can tackle the redis backed distributor after the release

_Simon Stewart  9:40 PM_

I’m working on some changes to make the state of the distributor serializable
Once those are done, I’ll be a lot happier with it

_Diego Molina  9:43 PM_

Great, I think those are the topics for today, is there something else?

_Simon Stewart  9:44 PM_

Not from me

_David Burns  9:44 PM_

I'm done

_Diego Molina  9:45 PM_

Thanks everyone!

_Simon Stewart  9:45 PM_
Thank you, @diemol!


