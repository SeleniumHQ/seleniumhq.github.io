---
title: "Public Project Meeting - February 11, 2021"
linkTitle: "Public Project Meeting - February 11, 2021"
date: 2021-02-11
tags: ["slack","meeting","tlc"]
categories: ["governance"]
author: Diego Molina ([@diegofmolina](https://twitter.com/diegofmolina))
description: >
  Continuing the series of bi-weekly public project meetings...
---

{{< dismissible-banner title="Archived meeting minutes" alert="note" color="blue" >}}
All meeting minutes can now be found [here](/meetings).
{{< /dismissible-banner >}}

Continuing the series of bi-weekly public project meetings, here is the
timeline of the meeting held on February 11, 2021,5:30 PM CET. (Below times are on CET)

Meetings are held on the `#selenium-tlc` channel on [Selenium Slack](https://seleniumhq.slack.com/join/shared_invite/enQtODAwOTUzOTM5OTEwLTZjZjgzN2ExOTBmZGE0NjkwYzA2Nzc0MjczMGYwYjdiNGQ5YjI0ZjdjYjFhMjVlMjFkZWJmNDYyMmU1OTYyM2Y).

---   

_Diego Molina  5:31 PM_

Agenda for today:
* General project statistics (Previous meeting: 252 open issues, 35 open PRs, Current: 234, 32 PRs)
* Anything new to report from the last PLC/SFC call?
* Overall announcements? New people on committees, with the commit bit, etc?
* Pending work that needs to be done for the first Beta?
* CoC, next steps?

@simonstewart @barancev @titusfortner @AutomatedTester

feel free to add more topics

intentionally not pinging Jim, Jim is not able to join

@harsha509 @luke and others, feel free to join as well

_Simon Stewart  5:34 PM_

@titusfortner wants to add "Remove Edge HTML"

_Titus Fortner  5:35 PM_ 

maybe a discussion of the general plan for post beta since it looks like the release is imminent?

_Diego Molina  5:35 PM_

Alright, first thing to mention is… great job everyone dealing with open PRs and open GH issues!

I don’t remember when we had such a “low” number of issues and PRs

but we can keep making that number smaller :slightly_smiling_face:

first topic:
Anything new to report from the last PLC/SFC call?

_Alexei Barantsev  5:37 PM_

I expect the number of bugreports to go up after beta 1 release date

people will download and try the new version

be prepared :slightly_smiling_face:

_Titus Fortner  5:38 PM_

hopefully. :)

_Luke Hill  5:38 PM_

ah right. When are these and "should" I be coming to them? Also is there some form of meeting request with link on e.t.c.

I only ask because the cucumber one which I go to i've stuck on my calendar so I don't forget.

_Simon Stewart  5:38 PM_

@luke there's a public calendar we share

There's something every Thursday at 4:30 UK time

_Diego Molina  5:39 PM_

I believe this is the link to the calendar, Luke
https://calendar.google.com/calendar/embed?src=7nmc82h5ok17obircmdu27sghs%40group.calendar.google.com&ctz=Europe%2FBerlin

_Simon Stewart  5:39 PM_

We announce here 30 minutes before things kick off

_Diego Molina  5:39 PM_

So, anything from our first topic?

Anything new to report from the last PLC/SFC call?

_Luke Hill  5:40 PM_

Rightio, is it just typed in here or is there a video call?

_Diego Molina  5:40 PM_

only typed

_Simon Stewart  5:41 PM_

Nothing much to report from the PLC/SFC call

_Alexei Barantsev  5:41 PM_

re: issues and PR, there is a new contributor to the JS part of the project, https://github.com/SeleniumHQ/selenium/pulls/potapovDim
I think we should support him as much as possible, he seems to be motivated, I saw he streamed about official selenium JS binding to youtube

_Simon Stewart  5:42 PM_

We're waiting for @mmerrell to report back: he was going to reach out to the SFC directly

@barancev +1 for supporting new folks. Anything we can do to help them?

_Alexei Barantsev  5:42 PM_

the JS part of the crew is traditionally underrepresented... so we should care about JS contributors very much

I'm trying to get into contact with potapovDim to get him more involved

_David Burns  5:43 PM_

I've been trying to make the JS bindings a first class citizen with bazel

I think I have that nearly done

_Diego Molina  5:44 PM_

ok, the next topic is:

Overall announcements? New people on committees, with the commit bit, etc?

Probably not so much?

_Marcus Merrell  5:45 PM_

I reached out to SFC about the CoC SOW - Karen responded that she'd look at it "tomorrow" which was last Wednesday. I'll text her right now to see what she says

_David Burns  5:45 PM_

cool

our contact for the CoC is still free

_Marcus Merrell  5:45 PM_

you mean "available"? 

_David Burns  5:46 PM_

No, she has been trying to get back to work but taking it slowly as she is shielding due to covid

_Diego Molina  5:47 PM_

ok, let’s jump to the main topic then

Pending work that needs to be done for the first Beta?

_Simon Stewart  5:47 PM_

There's a few things on my list.

Primarily, the Options classes don't serialise to a w3c-safe version automatically.

That won't be hard to fix

_David Burns  5:48 PM_

My list has  Firefox CDP in JS (nearly done)

_Simon Stewart  5:48 PM_

There's been a team effort to get the Grid stable. It eats memory, but should work now (@barancev and @diemol can correct me on that)

_Diego Molina  5:49 PM_

I had these two items on the client side, are the still pending?
Make relative locators return elements sorted by proximity
Allow locator strategies to be pluggable Enable fallbacks for commands

_Alexei Barantsev  5:49 PM_

I think that the grid is ready to ship

_Jim Evans  5:49 PM_

my list is all post-beta1, mostly around refactoring to allow CDP in remote and to enable for firerfox.

_Alexei Barantsev  5:49 PM_

there is a memory leak, but it's not critical for the beta

_Simon Stewart  5:49 PM_

@diemol both of those are done

_Titus Fortner  5:50 PM_

Firefox CDP shouldn't need to hold up beta

do we need to fix Options to release beta?

hoping we can release more frequently after we get to beta, just want to know what has to be there for it to be a beta

_Diego Molina  5:52 PM_

when do we think we can have beta 1 released?

should we set a date for next week?

_Titus Fortner  5:52 PM_

tonight?

everything is green, so what feature do we need to hold up the release for?

can we do it all in future betas?

_Diego Molina  5:53 PM_

perhaps the only item is what @simonstewart mentioned?

_Simon Stewart  5:53 PM_

The Options thing is it, AFAIAC

_Titus Fortner  5:54 PM_

and we can't release a beta without it? Is it a user facing change?

_Simon Stewart  5:54 PM_

It's suboptimal to not be able to create webdriver instances in java

_Titus Fortner  5:54 PM_

I completely agree it's needed

_Simon Stewart  5:54 PM_

It's a user-facing change

_Titus Fortner  5:54 PM_

ok, then :) easy.

_David Burns  5:54 PM_

I mean... no one uses Java right

_Simon Stewart  5:55 PM_

Seldom touched.

It's a niche thing

_Jim Evans  5:55 PM_

considers changing his name to “no one”

_Titus Fortner  5:55 PM_

I thought it was an sub-optimized thing that we fix in implementation

but if it changes what a user has to do, then great. Let's set a date next week? :)

_Simon Stewart  5:57 PM_

We'll ship when it's ready.

But probably next week

_Diego Molina  5:58 PM_

could we set a tentative date?

So people have it in mind and, if possible, save some time for it

like, we target next Thursday and we touch base on Tuesday to see if we actually can make it, what do you think?

_Titus Fortner  6:01 PM_

Whatever makes sense to Simon. Ruby stuff should be ready to go on short notice

_Simon Stewart  6:03 PM_

I'm very time constrained.

But Monday and Tuesday should be selenium days for me

_Alexei Barantsev  6:04 PM_

let's ship on Monday then

_David Burns  6:04 PM_

Python stuff is ready, changelog is already updated

_Simon Stewart  6:05 PM_

@barancev if everything works on Monday, we ship on Monday

_Alexei Barantsev  6:06 PM_

yes, if everything works

_Titus Fortner  6:06 PM_

sounds good to me

excited for it

_David Burns  6:06 PM_

Let's not set a date, as much as I want a beta. People will focus on it too much

_Diego Molina  6:06 PM_

sounds good, my intention was just to drive this conversation :slightly_smiling_face:


_Titus Fortner  6:08 PM_

Are we ready to talk about removing EdgeHTML? :)

_Diego Molina  6:08 PM_

got for it

_Titus Fortner  6:09 PM_

who wants to keep it in Selenium4?

No one? Ok, let's remove it.

:)

_Simon Stewart  6:09 PM_

I'm going to let @jimevans make the call

_Titus Fortner  6:10 PM_

seriously, though, it'll be less confusing for calling "edge" methods in Se4 to represent the latest instead of having to require people to specify "edge_chrome" and "edge_html", etc

_Simon Stewart  6:10 PM_

I'm fine with edge and edgehtml

_Titus Fortner  6:10 PM_

well "edge" used to be edgehtml

_Simon Stewart  6:10 PM_

Everyone who's lazy will get the right thing by accident

_Titus Fortner  6:10 PM_

so it's going to be a change either way

but I'm fine with whatever @jimevans decides on it

_Jim Evans  6:11 PM_

let me make the call regarding EdgeHTML?

_Simon Stewart  6:11 PM_

Yeah

_Titus Fortner  6:12 PM_

should we all ditch explicit support for it in Se 4 since MS is forcing people off of it... :)

_Alexei Barantsev  6:12 PM_

@titusfortner we provide support for IE :slightly_smiling_face:

_Titus Fortner  6:13 PM_

IE is and will remain special

_Diego Molina  6:13 PM_

this is about EdgeHTML, not IE, right?

_David Burns  6:13 PM_

Looks like bwalderman submitted a patch for python to remove it a while back

_Titus Fortner  6:13 PM_

I think MS can force upgrading from EdgeHTML to Edgium more easily than forcing people to upgrade IE

_David Burns  6:13 PM_

bwalderman of John Jansen's team

_Alexei Barantsev  6:13 PM_

@diemol yes, EdgeHTML, not IE

_Jim Evans  6:13 PM_

given that they’re doing forced-upgrades of people on EdgeHTML, i’m inclined to remove support for it. sure as 
we do, you know people will still be like “how do i test against edgehtml, since i don’t give any reflection on 
what my users are actually using and don’t want to have to think critically.”

is quite cynical today.

_David Burns  6:14 PM_

so... that was my question really...

_Simon Stewart  6:14 PM_

I'm down with removing it from the java tree, then

_David Burns  6:14 PM_

is Sauce dropping support for it?

or magic "here is an old selenium to support it" type code

_Titus Fortner  6:15 PM_

if the decision were up to me, yes, but it isn't, so ¯\_(ツ)_/¯

_Diego Molina  6:15 PM_

to be honest, I don’t know how we are doing it

_David Burns  6:16 PM_

we could ask your product team especially since they are starting their day or ... drop it with a big YOLO

_Diego Molina  6:16 PM_

but if it is browser that won’t be supported by MS starting on March(?), we will eventually drop it

_Titus Fortner  6:16 PM_

I can see valid use cases for IE, I don't see valid use cases for old Edge, and yeah, it's all complicated since 
things have changed since 3.141.59

_Diego Molina  6:16 PM_

goes and asks the team in charge

_Titus Fortner  6:17 PM_

Dude, we still support running Firefox 4 on Vista

_David Burns  6:18 PM_

screenshots this for that team if they come moaning...

_Titus Fortner  6:18 PM_

So, you can test EdgeHTML with MutableCapabilities if you need to

_David Burns  6:19 PM_

the python code treats them the same, just creates a different service

_Titus Fortner  6:21 PM_

Titus: supports
Jim: inclined to support

anyone else with opinions on keeping it?

it's really the force upgrades that is pushing me to be more drastic in the recommendation, plus the potential confusion over the method

_David Burns  6:22 PM_

I'm happy to drop

_Diego Molina  6:22 PM_

+1 to that

_Titus Fortner  6:22 PM_

@barancev?

Oh, missed Simon saying he's good with it

whew, ok, I'll stop banging on about it :)

thanks

_Diego Molina  6:25 PM_
@titusfortner, maybe you can create a GitHub issue and add labels for each language binding? Then we can track it


_Titus Fortner  6:25 PM_

Another ask...

Can we create a list of the new features that the bindings need to implement so that we can make sure all the languages are supporting the same things?

Some of the stuff being done in Java I'm not sure if it is grid related or bindings related.

I know Ruby is missing a few things, and I'm not even sure I know which all of them are.

_Diego Molina  6:28 PM_

I think we would need to go through the changelogs and build that list

_David Burns  6:30 PM_

@titusfortner thanks for offering to make sure the docs are up to date

_Titus Fortner  6:30 PM_

Can we make a grid in an issue, or do we need to track it elsewhere

I legit tried to help with the docs, but Go kicked my butt and I threw in the towel

I'm sticking with Jekyll where the OO makes sense :)

_Diego Molina  6:31 PM_

an issue would be nice

_David Burns  6:31 PM_

What...

@titusfortner you should watch my twitch... the docs are easy

_Diego Molina  6:32 PM_

Can we make a grid in an issue, or do we need to track it elsewhere

I am replying to this

lol

_David Burns  6:32 PM_

The what was for Titus

_Diego Molina  6:32 PM_

ok, so the meeting time is up, thank you everyone!

