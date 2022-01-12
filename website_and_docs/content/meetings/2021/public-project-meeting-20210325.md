---
title: "Public Project Meeting - March 25, 2021"
linkTitle: "TLC - March 25, 2021"
date: 2021-03-25
---


Continuing the series of bi-weekly public project meetings, here is the
timeline of the meeting held on March 25, 2021,5:30 PM CET. (Below times are on IST)

Meetings are held on the `#selenium-tlc` channel on [Selenium Slack](https://seleniumhq.slack.com/join/shared_invite/enQtODAwOTUzOTM5OTEwLTZjZjgzN2ExOTBmZGE0NjkwYzA2Nzc0MjczMGYwYjdiNGQ5YjI0ZjdjYjFhMjVlMjFkZWJmNDYyMmU1OTYyM2Y).

---   


_Diego Molina  9:54 PM_

Agenda for the public meeting:
* General project statistics
* Pending work that needs to be done for the third Beta?
* CoC, next steps?

Please feel free to add any other topics

pinging @jimevans @barancev @simonstewart @AutomatedTester @titusfortner @harsha509 @luke @Puja Jagani
and anyone else who wants to join 

_Simon Stewart  9:56 PM_

Thanks for the ping :slightly_smiling_face:

_Diego Molina  9:58 PM_

forgot to ping @manoj9788 and @mmerrell

_Marcus Merrell  10:00 PM_

I haven't heard back about the CoC work - last we left it, Karen was reaching out to Larissa

_David Burns  10:00 PM_

I will message Larissa now to see if that happened

_Marcus Merrell  10:01 PM_

thanks

_10:01_

I can make contact with her too, if you want to cut yourself out as the middle man
I might be able to speed things up

around the conference, it sounds like the Holiday Inn in Chicago is undergoing an ownership change, and hasn't gotten back to us about postponing to 2022
that's as of last week

_David Burns  10:02 PM_

I've messaged her in the Mozilla Alumni slack

_Marcus Merrell  10:03 PM_

ok, thanks

_Diego Molina  10:03 PM_

Sounds good, so we covered one topic already :slightly_smiling_face:

So…
General project statistics
* Previous meeting: 202 open issues, 32 open PRs
* Current: 178, 23 PRs

_Simon Stewart  10:04 PM_

That's amazing work, folks

_Diego Molina  10:04 PM_

We are doing pretty well there, I went through many issues and asked if 
they are still valid. Depending on their reply we can close around 20 more in 2-3 weeks.

Most of the open issues are either feature requests, for the JS and C# bindings
Perhaps we need to search for folks to give us a hand there

_Simon Stewart  10:06 PM_

We could absolutely do with more committers.

_Diego Molina  10:07 PM_

I will look for folks who have frameworks built on 
top of Selenium and see if they have time to give us a hand

_Simon Stewart  10:08 PM_

That's a great idea

_Diego Molina  10:08 PM_

I’ve seen this one called Watir, maybe they can help us

_Titus Fortner  10:09 PM_

They have a slack channel I can ask

_Diego Molina  10:09 PM_

The original idea is from Titus actually :slightly_smiling_face:

_Titus Fortner  10:09 PM_

I mean, I cajoled Thomas from Capybara into helping :-D

we were down to one ruby labeled issue.
This one we need some kind of decision on: https://github.com/SeleniumHQ/selenium/issues/8179

maybe it's a wontfix and can be closed, or we can figure out the right thing and knock out the changes

_Simon Stewart  10:11 PM_

Send Keys in actions is really meant for individual key presses.
But I guess we could decompose a more complicated set of inputs to individual key presses and releases

_Diego Molina  10:14 PM_

Would you like to dig deeper into the issue right now, @titusfortner?

_Titus Fortner  10:14 PM_

I was generally looking at the needs-discussion ones: https://github.com/SeleniumHQ/selenium/labels/A-needs%20decision

if we need a discussion to figure out what is the right thing, this seems like a good forum for that
if we need more info we can push it back til next meeting, etc

_Diego Molina  10:15 PM_

Makes sense, I can definitely go over those issues and put them in the agenda for the next one, good idea

_Titus Fortner  10:15 PM_

but no, I don't need anything specific from that one 

right now
just wanted to raise it since we haven't discussed it before :)

if and when we get guidance I/others can implement it, but need to know what to implement :-D
or I'll make the Watir dev do it

_Diego Molina  10:18 PM_

ok, next topic
* Pending work that needs to be done for the third Beta?

Things left in Java, Python, JS, Ruby, C#?
* “se:cdpVersion”?
* Can all bindings do CDP over Grid?

Anything else on the client side?

_Simon Stewart  10:19 PM_

I have local diffs with se:cdpVersion wired up properly in Firefox and Chromium-based browsers
It's a little funky, and I'd like a test around it before doing much more

_Titus Fortner  10:20 PM_

We have cdpVersion hard coded for Firefox and are getting browser version to automatically serve the right version to Chrome/Edge
the endpoint would make a couple things easier :)

_Simon Stewart  10:21 PM_

I'm making the browsers return the cdp version and endpoint in the capabilities

se:cdpVersion and se:cdp respectively

_Titus Fortner  10:21 PM_

oh yes, capabilities not endpoint, I knew what I meant to say in my brain ;-)

_Simon Stewart  10:22 PM_

Ha! :slightly_smiling_face:

_Diego Molina  10:23 PM_

Ok now, Server side:
* Enable retries of commands where necessary using failsafe
* Pipe VNC connectors through the websocket plumbing for live video
* Return CDP url by using the Grid url
* CDP url for Firefox
* Platform matching but when platform is Windows
* Investigate: Grid gets stuck when screenshot is taken in a terminated session

this is what I have in my list, not sure if there is anything more

* Platform matching bug when platform is Windows

_Simon Stewart  10:25 PM_

We should already be sending the CDP url for the Grid in Capabilities

And I've got the CDP url for Firefox in my local patches

_Diego Molina  10:25 PM_

ah, that is true

scratching that

_Simon Stewart  10:26 PM_

Hurrah! One step closer :slightly_smiling_face:

_Diego Molina  10:27 PM_

by looking at the download numbers and issues created after beta 2, which is not a lot…
what is our overall feeling about beta 3?

let’s say, when most of features and bugfixes are done we release it or do we want to wait a bit more for feedback?

_David Burns  10:28 PM_

I think do the bug fixes and release

_Diego Molina  10:28 PM_

+1 to that

_David Burns  10:28 PM_

the "closer" we are to a full release the more our pool will increase

a "better" question... what do we need to get to a RC?

_Jim Evans  10:29 PM_

.NET doesn’t do CDP over grid right now. a refactor is required to make that possible. 
i have it in progress, but it’s not ready to get committe.

_David Burns  10:29 PM_

so we need ^ to get to RC
what else?

_Simon Stewart  10:29 PM_

Oh. Yeah. RC backwards compat

_David Burns  10:30 PM_

I mean harder to get to points but wont stop beta releases

_Simon Stewart  10:30 PM_

I'd also like to properly clean up the java client code to strip out JWP support

_David Burns  10:30 PM_

and RC I mean release candidate

_Simon Stewart  10:30 PM_

But I think that's going to be a hard thing to do

_Diego Molina  10:30 PM_

Simon using every chance to ship RC into Selenium 4 :smile:
New

_Simon Stewart  10:30 PM_

We can do an RC once we think we've got all the features done

I suspect beta 3 may be our last beta, if @jimevans gets the time and support he needs for CDP over Grid

_Diego Molina  10:32 PM_

I am getting a new Windows machine, in part to see how I can help with C#, so expect questions @jimevans :slightly_smiling_face:

_Simon Stewart  10:32 PM_

Amazing! :slightly_smiling_face:

_Jim Evans  10:33 PM_

@diemol happy to collaborate.

_Diego Molina  10:33 PM_

I believe we covered all topics, unless someone has anything else?

_Michael Mintz  10:34 PM_

Full official release of Selenium 4 by year's end?

_Simon Stewart  10:34 PM_

Even sooner

When shall we aim to ship beta3?

_Titus Fortner  10:35 PM_

I'm waiting for Simon to decide he has to rewrite the Java HTTP Client before we ship Se 4 ;-)

_David Burns  10:35 PM_

not next week as I am away but the week after maybe?

_Diego Molina  10:37 PM_

2-3 weeks from now would be realistic, I think

_Simon Stewart  10:37 PM_

Here's the plan, then. We'll ship beta3 when two of these conditions are met:
* The .net bindings support CDP over Grid
* We send se:cdpVersion in Capabilities and that's also handled by Grid
* Two weeks have passed

Sound good?

_Diego Molina  10:38 PM_

Sounds good to me

alright, that sounds like a wrap for today’s meeting…
however, if anyone has a comment about what we discussed today, feel free to comment it here at any time

_Titus Fortner  10:43 PM_

Thanks @diemol!
