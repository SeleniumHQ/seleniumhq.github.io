---
title: "Public Project Meeting - May 07, 2020"
linkTitle: "Public Project Meeting - May 07, 2020"
date: 2020-05-09
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
timeline of the meeting held on May 07, 2020 (times are on IST). 

Meetings are held on the `#selenium-tlc` channel on [Selenium Slack](https://seleniumhq.slack.com/join/shared_invite/enQtODAwOTUzOTM5OTEwLTZjZjgzN2ExOTBmZGE0NjkwYzA2Nzc0MjczMGYwYjdiNGQ5YjI0ZjdjYjFhMjVlMjFkZWJmNDYyMmU1OTYyM2Y).

The next meeting will be on May 21, 2020, 4:30 PM CET.

---   

_adamgoucher  8:00 PM_

alright. welcome back. time for another fortnightly chat about the project.


_Simon Stewart  8:01 PM_

:wave:

_Diego Molina  8:01 PM_

:wave:

_Sri Harsha  8:01 PM_

:wave:

_adamgoucher   8:02 PM_

some general project statistics;
open issues: 391
open pull request: 68
(which is the lowest i think ive seen either of those numbers before)

_8:03_

agenda wise, this is going to be more loosey-goosey than normal as i’m swamped with day job so ignoring things. we’ll see is that is a good thing or not.

_8:03_

any overall announcements? new people on committees, have the commit bit, etc?

_Simon Stewart   8:04 PM_

I’ve landed the update to OpenTracing, which unblocks me

_8:04_

Next step is to wire the tracing into the Docker support
And then pick up the websocket forwarding

_8:05_

(Which, to be fair, is “mostly done”)

_adamgoucher   8:05 PM_

heh. the last 20% takes the other 80% of the time :slightly_smiling_face:

_Simon Stewart   8:05 PM_

The Pareto Principle in action

_8:06_

I’d be happy pushing a6 before the next meeting

_adamgoucher   8:06 PM_

what do we think is missing from a6 now?

_Simon Stewart   8:06 PM_

It’s been a while since we did a release, and there’s progress in there

_8:07_

I’d dearly love the websocket forwarding, but I can live without it
@diemol landed the first work with Fail Safe for some more robustness in the Grid

_8:08_

I think @barancev is also making some progress switching the underlying HTTP engine again

_adamgoucher   8:08 PM_

looks like there was a bunch of plumbing / dependencies updated in the last week especially with .net and python

_Simon Stewart   8:08 PM_

@jimevans and @AutomatedTester know more than I do

_Jim Evans   8:10 PM_

yes, the .NET bindings build using bazel should now work cross-platform. you should be able to run .NET tests using bazel test (with caveats, which i’ve yet to fully document). it kinda works within VS code, but one can for certain develop using Visual Studio 2019 on Windows, and Visual Studio for Mac on MacOS.

_adamgoucher   8:11 PM_
(still breaks my head to write .net code on mac)

_8:11_

what more was needed for the http engine stuff? i thought there was a flag in there already?

_8:12_

or did we change it again, again

_Simon Stewart   8:13 PM_

The underlying library didn’t support http/2 or unix domain sockets, despite it being possible

_adamgoucher   8:14 PM_

so do we want/need to wait for that work to be done-ish before doing a6? so far that sounds like the only thing that will be confusing to release partially implemented

_Simon Stewart   8:14 PM_

Nah.

_adamgoucher   8:15 PM_

scans commits in another window

_Simon Stewart   8:15 PM_

As long as it’s in before we start on the betas, we’ll be fine

_adamgoucher   8:15 PM_

a question i know we dont have an answer for, but do we have a gut feel for how many more a’s there are before we start the b’s?

_Simon Stewart   8:16 PM_

The major outstanding features are:
websocket forwarding
graph-ql support

_adamgoucher   8:16 PM_

thats not that horrid of a list. its been much worse

_Simon Stewart   8:17 PM_

We’ve been chipping away at it

_adamgoucher   8:18 PM_

so if a6 is within the next 2 weeks, do we have a trigger condition for releasing it?

_Simon Stewart   8:18 PM_

No trigger condition

_8:18_

Other than “someone updates the changelog”, I guess

_adamgoucher   8:19 PM_

do we want to say ‘a6 is next tuesday’ so that all the maintainers are ready to build and tag everything? (feel like we can’t magically do it all from ci but could be making that up)

_Simon Stewart   8:20 PM_

The graphql piece is to support the ui, so strictly speaking we don’t really need it, but it keeps things flexible

_8:20_

@AutomatedTester, @p0deje, @jimevans, @titusfortner, @harsha509 Cool with shipping an alpha next week?

_Jim Evans   8:21 PM_

+1 from me.

_Sri Harsha   8:21 PM_

Yes

_Diego Molina   8:22 PM_

how are we handling the numbers in JS, JS is already in a7, right?

_Simon Stewart   8:22 PM_

“alpha-n+1”

_Sri Harsha   8:22 PM_

Yes @diemol . Do you have any suggestions @corevo . He should be releasing the next alpha as i know.

_Alex Rodionov   8:23 PM_

+1 from me

_Tomer Steinfeld   8:25 PM_

JS has been in alpha for a long time because of historical reasons

_adamgoucher   8:25 PM_

ok. so thats covers the bulk of the pressing tech stuff. other stuff that could be discussed;
when is the next sfc call? (if its happened in the last two weeks, anything to report)
did anyone pick up the ball to talk to miki about the go bindings?
seconf india
seconf us
state of the se-ide into the main repo

_Tomer Steinfeld   8:25 PM_

I think we can land stable 4.0 together, but I don't see a reason we should release 4 alphas together

_adamgoucher   8:26 PM_

(or we can keep talking about js too. but that list is just my remaining things)

_Marcus Merrell   8:26 PM_

the PLC call happened on Tuesday, it was just me and Jim. We mostly talked about conferences, and we were going to follow up with Simon about the status of the Code of Conduct

_Simon Stewart   8:27 PM_

Uhh… I thought @AutomatedTester was running with the CoC stuff.

_Marcus Merrell   8:27 PM_

at this point India is still slated to happen in September, same venue. I don't know the latest about speakers or progress or anything like that

_Simon Stewart   8:27 PM_

It’s entirely possible I am mistaken

_adamgoucher   8:27 PM_

i thought david was on that too

_Tomer Steinfeld   8:27 PM_

Is there an update from the SFC about the MS store?

_Marcus Merrell   8:28 PM_

ok, I thought David was on the MS Store issue, and not the CoC, but it wouldn't be the first time I was mistaken

_Diego Molina   8:28 PM_

why don't we take the CoC draft we have, add it to the new site, and iterate on it?

_Simon Stewart   8:28 PM_

We were going to get a review and some guidance from Sage Sharp

_Marcus Merrell   8:29 PM_

for Chicago, we've got a verbal understanding that the venue will allow us to reschedule the conference for sometime next year with no fees. We've already given them a somewhat hefty deposit, and while we could likely fight to get that back, we'd rather just have it next April or October--we're going to make that decision in late May/early June

_Diego Molina   8:30 PM_

for the SeleniumConf website, @andrewmkrug has joined the Selenium GitHub org, and he is helping us to push the site code to a private repo

_Marcus Merrell   8:30 PM_

for the Fall, we're considering an online-only conference, and we're waiting for a few pieces of data to come in over the next couple weeks, around TestBash and SauceCon, to gauge how they went, ideas for optimization, pros/cons, do's don't's, etc

_Andrew Krug  8:30 PM_

was added to #selenium-tlc by Diego Molina.

_Marcus Merrell   8:32 PM_

I spoke to Deb (from SFC) quite a bit on Friday about tips and pointers for an online-only conference, and I've started to develop some opinions for how it should be done. I haven't yet, however, come up with a firm position on the pricing model for sponsors or attendees--I definitely want to offer all the track sessions/keynotes for free, and offer a paid tier for "something" else, like if people want T-shirts or something else, but it would likely be a model where they pay what they want (to support the project), and the swag they get depends on the tier they buy into

_8:33_

I'm waiting to hear more from Richard Bradshaw before beginning that discussion, probably during our bi-weekly Conference conference call, on May 28th

_adamgoucher   8:35 PM_

so. CoC — when can we get the review and guidance? if its more than ‘not in may’ we should likely throw it up and start the iteration process

_Simon Stewart   8:36 PM_

It depends on Sage’s availability, and that of the people working on the CoC

_adamgoucher   8:37 PM_

(which, sounds like we’re not quite sure of who those are from our end)

_Simon Stewart   8:37 PM_

Last I checked, @mmerrell, @AutomatedTester, @diemol, @jimevans and I were all interested in being involved

_8:38_

From memory.

_adamgoucher   8:38 PM_

so the nebulous items seem to be;
determine who is actually on the coc stuff

_Simon Stewart   8:38 PM_

Agreed

_adamgoucher   8:38 PM_

doh! no editing. and,
determine who is actually doing the ms store stuff

_8:40_

which means, for the next meeting;
- release a6 (a7 of js)
- report who is point and who is assisting with the CoC
- report who is leading the ms store stuff

_8:41_

anyone else have anything they want / need to discuss in public?

_Simon Stewart   8:41 PM_

I’m good

_8:41_

Though perhaps we should figure out who owns what on that list
I hold the build bacon this time

_Diego Molina   8:42 PM_

maybe we can record how a release is done? and from that I can write docs

_Simon Stewart   8:43 PM_

I’ll walk you through the process
We did have it written up here: https://github.com/SeleniumHQ/selenium/wiki/Releasing-Selenium

_adamgoucher   8:43 PM_

from the april 23 meeting, looks like @AutomatedTester is the store person
Screen Shot 2020-05-07 at 11.12.57 AM.png 
Screen Shot 2020-05-07 at 11.12.57 AM.png
 
_David Burns  2 days ago_

I am waiting on replies from SFC. I chased up yesterday. Have meeting with MS next week

_adamgoucher   8:45 PM_

anyone volunteering for CoC lead?

_Diego Molina   8:46 PM_

ah nice, the release process is written, we "just" need to update it

_Simon Stewart   8:46 PM_

The java one doesn’t seem wildly inaccurate

_adamgoucher   8:48 PM_

which means, for the next meeting;
- release a6 (a7 of js) -- simon
- report who is point and who is assisting with the CoC -- tbd
- report who is leading the ms store stuff - david?

_Alexei Barantsev   8:49 PM_

sorry to be late, I'm +1 for the next alpha

_adamgoucher   8:50 PM_

so if there isnt anything else, i say we call this thing done and do it all again in a fortnight.

_8:52_

slack says no one is furiously typing so, see all y’all in two weeks. as ever, if there is something you want discussed, message me directly or just drop it here for us to pickup

_Diego Molina   8:53 PM_

thank you @adamgoucher!

_Simon Stewart   8:54 PM_

Thanks, @adamgoucher!

_David Burns   9:24 PM_

Argh... missed it again!!!

_9:28_

For CoC, I keep dropping the ball on this one. I have been busy with work but hopefully can do something in the next two weeks
