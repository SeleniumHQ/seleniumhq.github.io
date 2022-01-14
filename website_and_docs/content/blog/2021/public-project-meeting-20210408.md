---
title: "Public Project Meeting - April 08, 2021"
linkTitle: "Public Project Meeting - April 08, 2021"
date: 2021-04-08
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
timeline of the meeting held on April 08, 2021,5:30 PM CET. (Below times are on IST)

Meetings are held on the `#selenium-tlc` channel on [Selenium Slack](https://seleniumhq.slack.com/join/shared_invite/enQtODAwOTUzOTM5OTEwLTZjZjgzN2ExOTBmZGE0NjkwYzA2Nzc0MjczMGYwYjdiNGQ5YjI0ZjdjYjFhMjVlMjFkZWJmNDYyMmU1OTYyM2Y).

---   

_Diego Molina  8:59 PM_

Ok, proposed agenda is:
* General project statistics
* Stuff implemented/fixed in the last two weeks
* Pending work that needs to be done for the third Beta?
* PRs that need to be reviewed

please feel free to add any other topics

pinging @titusfortner @manoj9788 @mmerrell @simonstewart @barancev @Puja Jagani @AutomatedTester @harsha509 @jimevans

Alright, let’s start with today’s public meeting :slightly_smiling_face:

_9:02 PM_

General project statistics
* Previous meeting: 178 open issues, 23 open PRs
* Current: 163, 24 PRs

_Marcus Merrell  9:03 PM_

no news to report from me--still working on the CoC with Karen, 
and working on either booking the Chicago venue for a 2022 conference, 
or getting our $40,000 back

_Diego Molina  9:04 PM_

thanks for sharing @mmerrell

_Simon Stewart  9:04 PM_

Presumably we're looking at late 2022 for the in-person conference?

_Diego Molina  9:05 PM_

I hope so

are there any thoughts around a virtual conference this year?

_Simon Stewart  9:06 PM_

I'm lukewarm about virtual conferences. It's hard to sit and watch YouTube videos all day

_Diego Molina  9:06 PM_

me too, but as a project we should consider that alternative

_David Burns  9:06 PM_

having just done one for work there is a "we've had enough" vibe going

_9:07 PM_

it was a success but I don't think we could have done it later this year

_Simon Stewart  9:07 PM_

I'd love to do some short videos on topics, though

Things like relative locators, shadow DOM, CDP, etc

And we can do a "q&a" one with committers and a host

We should ask folks if they'd like to do something, and then maybe post one or two a month?

Diego Molina  9:08 PM
right, a mini conference would be nice

_Simon Stewart  9:08 PM_

Provided we pay for someone to help make the videos and edit them

_Titus Fortner  9:08 PM_

less "talks" and more "video tutorials with Q&A?" :)

_Diego Molina  9:08 PM_

perhaps one day of workshops and one day of short talks

_Simon Stewart  9:09 PM_

Workshops are a really good thing to do

_David Burns  9:09 PM_

I would like to think on it more before committing either way

_Simon Stewart  9:09 PM_

Not even sure about "one day of short talks". Just posting things regularly on the YT channel

_Titus Fortner  9:09 PM_

I really like it when there is a "time" that a video is shown, 
and the author can chat with people about it in whatever room, 
and then answer questions at the end

_9:10 PM_

then everything can be posted in whatever channel for people to watch

_Simon Stewart  9:10 PM_

I can see that

_Diego Molina  9:10 PM_

well, there we have a whole topic to think about :slightly_smiling_face:

_Simon Stewart  9:10 PM_

Ha! :slightly_smiling_face:

_Titus Fortner  9:10 PM_

but I do like the idea of a series of shorts on different pertinent selenium topics

_9:11 PM_

less "why testing is like a toaster oven" and 
more practical on how to get the most out of new Selenium 4, etc :-D

_Simon Stewart  9:11 PM_

Right

_Diego Molina  9:12 PM_

ok, I am a bit short of time today, so I suggest to go back to 
the agenda and if there is time, we can circle back to ideas about a “conference”

_Titus Fortner  9:12 PM_

:thumbsup:

_Diego Molina  9:12 PM_

Next topic:
* Stuff implemented/fixed in the last two weeks
  
I have a short list of things I know they were done, so I will paste it here
* CDP url for Firefox
* Platform matching but when platform is Windows
* noVNC added to docker-selenium

_Simon Stewart  9:12 PM_

Been quiet from me, but I have landed the bits to plumb 
through se:cdpVersion and have started on a unified New Session Queue

_Diego Molina  9:13 PM_

(there was a bug when matching Windows as a platform)

_9:14 PM_

yeah, if you try the Grid right now, CDP for Firefox should work also
(but there are some CI tests failing for Firefox)

I also added noVNC to the docker containers, 
that bring us closer to have the live view in the Grid UI

_Simon Stewart  9:15 PM_

That's very cool

_Diego Molina  9:15 PM_

Ok, so now having said what we have done during the last two weeks, 
we can now talk about what is left for beta 3:

Pending work that needs to be done for the third Beta?

I will paste the things that come from the 
previous meeting, but please add/remove as you wish

_Simon Stewart  9:16 PM_

Thank you

_Diego Molina  9:16 PM_

Server changes:
* Enable retries of commands where necessary using failsafe
* Pipe VNC connectors through the websocket plumbing for live video
* Investigate: Grid gets stuck when screenshot is taken in a terminated session
* Investigate: Router request timeouts when forwarding command to Node

Things left in Java, Python, JS, Ruby, C#?
* “se:cdpVersion”?
* Can all bindings do CDP over Grid?

_Marcus Merrell  9:16 PM_

(just to close out the 2022 conference idea--yes, Fall of 2022 in Chicago)

_Simon Stewart  9:16 PM_

Adding: unify the new session queue

_Diego Molina  9:18 PM_

We had this comment from the last meeting:

Here’s the plan, then. We’ll ship beta3 when two of these conditions are met:
* The .net bindings support CDP over Grid
* We send se:cdpVersion in Capabilities and that’s also handled by Grid

is this still valid? what do you all think?

_Simon Stewart  9:18 PM_

Time has elapsed. I'm not convinced we're using se:cdpVersion properly on the local end yet

_Titus Fortner  9:19 PM_

looks like Java is the only one using it

_Simon Stewart  9:19 PM_

@jimevans can tell us whether the .Net bindings work

_David Burns  9:19 PM_

I will look at the Python/JS stuff for that tomorrow

_Titus Fortner  9:20 PM_

There are several Ruby bugs that we fixed, so I'm for releasing beta 3 even without cdpversion implemented

_9:21 PM_

I would like to get v90 & v91 devtools support added

_Diego Molina  9:21 PM_

Grid is now returning se:cdpVersion properly, and the 
Java bindings (when running locally) are also returning it properly

_Titus Fortner  9:21 PM_

or at least v90

_David Burns  9:21 PM_

that seems simple then...

_Diego Molina  9:21 PM_

but I wonder if the other bindings also create and 
manage se:cdpVersion when running locally (not over Grid)

_Titus Fortner  9:22 PM_

wait is Firefox using CDP 85 or 86?

_Simon Stewart  9:22 PM_

@titusfortner I can help you land the CDP v90 stuff

_Diego Molina  9:22 PM_

86

_Titus Fortner  9:23 PM_

I think I followed the guid and have the pdl files for the latest v90

_Simon Stewart  9:23 PM_

@diemol I'm not sure the java bindings use se:cdpVersion properly

_David Burns  9:23 PM_

firefox is 85 unless they changed it

_Titus Fortner  9:23 PM_

Well, if we aren't hard coding it to 85, then I need to 
figure out how we want to approach that going forward :slightly_smiling_face:

I just see that trunk has FirefoxDriver set to use 86

(in Java)

which I didn't think was correct

_David Burns  9:24 PM_

Java is wrong then

_Simon Stewart  9:24 PM_

Easy fix

_David Burns  9:24 PM_

r-

_9:24_

:stuck_out_tongue:

_Diego Molina  9:25 PM_

Seems we need to create a GitHub issue to track that

I’ll do that now

_Simon Stewart  9:26 PM_

Thanks

_Jim Evans  9:27 PM_

.NET still doesn’t proxy CDP across RemoteWebDriver yet.

is jumping through corporate IT/security/legal hoops right now.

_Titus Fortner  9:29 PM_

that sounds joyful

_Diego Molina  9:30 PM_

ok, created this to track it https://github.com/SeleniumHQ/selenium/issues/9365

please feel free to add more details

(if needed)

_9:32 PM_

So, IMO, we should have se:cdpVersion working and all bindings doing CDP over Grid before beta 3, right?

what do you think?

_Simon Stewart  9:32 PM_

I'm letting @jimevans call it

_Jim Evans  9:33 PM_

let’s not hold up beta3 for that. if i get to it before then, fine, but i’m swamped right now.

_Titus Fortner  9:33 PM_

I definitely don't think we need cdpVersion

_9:34 PM_

doing CDP over grid doesn't require that per se

_Diego Molina  9:34 PM_

no, it does not, they are two separate things

_Titus Fortner  9:34 PM_

Does Python & JS have CDP over grid working?

_Simon Stewart  9:34 PM_

I'd like to get the java bindings consuming se:cdpVersion properly.

_Titus Fortner  9:35 PM_

can we save that for beta4?

_David Burns  9:35 PM_

replied to a thread:
Does Python & JS have CDP over grid working?

It should do

_Diego Molina  9:35 PM_

I know it would be ideal to get a beta 3 soon, 
but if we need to wait to have all bindings working with CDP, I am ok with that

_Titus Fortner  9:35 PM_

I generally don't like putting off a release until Java gets 
something working, because that just means we wait for everyone 
else to implement it or Java is off from the rest of us. :smile:

_Diego Molina  9:36 PM_

I have seen issues asking “can we have in JS what it is already done in Java”, and similar

_Titus Fortner  9:36 PM_

Like I said, I have several bug fixes that would be nice to get into people's hands sooner

I guess I can always do a beta 2.1 for ruby, but...

_Simon Stewart  9:36 PM_

Let's aim for next Tuesday for beta 3

_Diego Molina  9:37 PM_

I am also ok with that

but I don’t want to reach RC without some 
degree of feature parity between bindings

even if it takes longer

_Jim Evans  9:38 PM_

i get my second vaccine dose on tuesday, so it’ll be in the afternoon

_9:38 PM_

(US eastern time)

_Titus Fortner  9:38 PM_

woo hoo!

_Diego Molina  9:38 PM_

oh, that’s amazing!

_Simon Stewart  9:38 PM_

RC should have feature parity

I'd like that one out for a few days, and then we ship 4.0.0

_Titus Fortner  9:39 PM_

oh, while we're here, I think Java & .NET are the only ones who 
have implemented these? https://w3c.github.io/webdriver/#get-computed-role

_9:40 PM_

These new endpoints sneak up on me

_Simon Stewart  9:40 PM_

There are a few. The Shadow DOM ones are new as well

_Titus Fortner  9:41 PM_

oh no, python & java

_Simon Stewart  9:41 PM_

I think @AutomatedTester has snuck in a broader API 
than the rest of us since he actually reads specs

_Titus Fortner  9:41 PM_

sneaky

_Simon Stewart  9:41 PM_

Clever lad

_David Burns  9:41 PM_

none of the shadow dom stuff is done

_9:42 PM_

but I am adding it to Firefox

_Sri Harsha  9:42 PM_

JS bindings have it too, only left with shadow-dom

_Jim Evans  9:42 PM_

@titusfortner.NET does the get computed ones too

_David Burns  9:42 PM_

If you watched todays twitch you would have seen why... I am not sure of the API for things not Java/.NET

_Titus Fortner  9:43 PM_

so what everyone is saying is that it's just Ruby slacking

_Diego Molina  9:43 PM_

ok, so we’ll aim beta 3 for next Tuesday :slightly_smiling_face:

_David Burns  9:43 PM_

@titusfortner not ruby... whistles
:stuck_out_tongue_winking_eye:

_Diego Molina  9:43 PM_

last topic is:
* PRs that need to be reviewed

_9:44 PM_

there are a few ones that could be an “easy” merge and ideally they do not need a lot of time for a review

I also see @Puja Jagani now contributing to the C# bindings, which is great!

I’ll list the PRs here:

C#
* https://github.com/SeleniumHQ/selenium/pull/9363
* https://github.com/SeleniumHQ/selenium/pull/9361

Python

* https://github.com/SeleniumHQ/selenium/pull/9336

_David Burns_

done

_Diego Molina_

thank you!

_Diego Molina  9:47 PM_

Java:

* https://github.com/SeleniumHQ/selenium/pull/8977
* https://github.com/SeleniumHQ/selenium/pull/8815

_9:48 PM_

in case you have some spare time, @AutomatedTester @jimevans @simonstewart

that’s all I had in the agenda :slightly_smiling_face:
