---
title: "Public Project Meeting - December 03, 2020"
linkTitle: "TLC - December 03, 2020"
date: 2020-12-03
---


Continuing the series of bi-weekly public project meetings, here is the
timeline of the meeting held on December 03, 2020,5:30 PM CET. 

Meetings are held on the `#selenium-tlc` channel on [Selenium Slack](https://seleniumhq.slack.com/join/shared_invite/enQtODAwOTUzOTM5OTEwLTZjZjgzN2ExOTBmZGE0NjkwYzA2Nzc0MjczMGYwYjdiNGQ5YjI0ZjdjYjFhMjVlMjFkZWJmNDYyMmU1OTYyM2Y).

---   

_Diego Molina  5:32 PM_

So, things from the previous meeting

General project statistics
- Previous meeting: 280 open issues, 62 open PRs
- Current: 278, 60 PRs

_Diego Molina  5:35 PM_

here are the topics that we initially cover today:

Overall announcements? New people on committees, with the commit bit, etc?

Anything new to report from the last PLC/SFC call?

Pending work that needs to be done for the first Beta?

are we all around to start?

_Simon Stewart  5:38 PM_

@titusfortner @jimevans @p0deje @barancev @AutomatedTester @harsha509

_David Burns  5:39 PM_

I'm here

_Diego Molina  5:41 PM_

The first topic is:
Overall announcements? New people on committees, with the commit bit, etc?
I believe there is not much about this, right?

_Simon Stewart  5:41 PM_

Nothing from me

_David Burns  5:42 PM_

nope

_Diego Molina  5:43 PM_

Maybe, just for awareness of the meeting minutes, for the ones who read them
we are doing a survey that will help with the future of Selenium
https://twitter.com/SeleniumHQ/status/1333830343368077319

_David Burns  5:44 PM_

We've had nearly 400 respondents

_Diego Molina  5:44 PM_

and Boni García replied to it with https://www.mdpi.com/2079-9292/9/7/1067
I have not read it yet

_David Burns  5:44 PM_

if you haven't retweeted the survey, could you do that please?

_Simon Stewart  5:44 PM_

I hadn’t seen the reply

_Diego Molina  5:46 PM_

Boni is the creator of WebDriverManager and a JUnit 5 extension for Selenium
I think he is a professor at a university in Madrid

_Simon Stewart  5:47 PM_

I’m glad WebDriverManager exists

_Diego Molina  5:47 PM_

Alright, maybe we should have a read at that document and report our findings :slightly_smiling_face:

So, probably we can move to the next topic:
Anything new to report from the last PLC/SFC call?

I think there was one call a couple of days ago

_David Burns  5:50 PM_

Main question: did someone follow up on my CoC request?

I did email again

and mentioned it to @mmerrell

_Simon Stewart  5:51 PM_

Monday is my next Selenium day. If no-one has responded by then, I’ll chase

_Diego Molina  5:52 PM_

ok, probably we can move to the next topic and circle back to this one if someone has more information

Pending work that needs to be done for the first Beta?

This is what we wrote/discussed in the previous meeting:

Client facing changes:
- Make relative locators return elements sorted by proximity
- Fix Java Module System problems
- Allow locator strategies to be pluggable
- Enable fallbacks for commands

Server changes:
- Enable retries of commands where necessary using failsafe
- Get the Grid UI looking nice, and returning useful data
- Allow locator strategies to be pluggable
- Pipe VNC connectors through the websocket plumbing for live video

I have on me the task to map this list into GitHub issues in case they do not exist (I have not done it yet)

_Simon Stewart  5:54 PM_

I’ve made relative locators return elements sorted by proximity

_Diego Molina  5:54 PM_

is there something else to add? do we have an status update on any item?

_Simon Stewart  5:55 PM_

My plan on Monday is to look at pluggable locator strategies

Which will require fallbacks for commands

_David Burns  5:55 PM_

I've been working with the Mozilla folk to add bidi support for Firefox, there are  bugs on their side that they are working on

_Marcus Merrell  5:55 PM_

I’m sorry, @AutomatedTester , I never received an email about it. I don’t know if there’s a list I’m not on, or if my address is wrong, or if it went to spam, but I don’t have it

I’ll be glad to follow up if I can get the info

_David Burns  5:56 PM_

@mmerrell I emailed selenium@sfc

_Marcus Merrell  5:56 PM_

Right... guess I need to verify that I’m on that

_Simon Stewart  5:58 PM_

You should be.

_Diego Molina  5:59 PM_

Which will require fallbacks for commands
I am sorry I never found the time to check your branch and work on it

_Marcus Merrell  5:59 PM_

I haven’t seen any of the sponsor emails either... starting to think there’s a problem

_Diego Molina  6:02 PM_

ok, so I believe we don’t have any more comments for this?

_Simon Stewart  6:02 PM_

Not from me

(Looks like a short meeting today, he says. Jinxing it)

_Alexei Barantsev  6:04 PM_

as for the beta1, I'm going to add commands getAriaRole and getAccessibilityName and I call java api complete

_Diego Molina  6:05 PM_

perhaps the last thing to mention is that I have pending to fill out the table about who has access to the distribution engines (npmi, sonartype, etc…)
so the idea is that I will make sure we all have access to all of them

_Simon Stewart  6:05 PM_

Speaking of which, we really do need to push a JS release

Anyone want to volunteer for that?

_David Burns  6:05 PM_

If it's not done this evening I will do it

about to take youngest to ballet so will be out for a few hours

_Alexei Barantsev  6:06 PM_

I've enabled GitHub integration in sonarcube, so it should sync org members and provide access

_Simon Stewart  6:07 PM_

That’s nice

Thank you

_Diego Molina  6:09 PM_

well, so I think that’s it for this meeting, right?

_Simon Stewart  6:10 PM_

I think so

_Diego Molina  6:11 PM_

ok, then, thank you all!

_Titus Fortner  6:49 PM_

Sorry to miss; I was giving training. Also, I miss getting to travel for these; Zoom is just not the same...

_Titus Fortner  7:02 PM_

I haven't looked, has the print pdf endpoint been added to all the bindings?
I haven't looked in past 2 weeks, but Ruby & .NET needs to implement FF Full Page screenshot,
Java (.NET?) need to be able to tell Firefox to take a full Page Screenshot when using RemoteWebDriver
Java & Ruby need to re-fix the STP browser name

Ruby stuff was on my to-do list, until my paid work job gave me a daunting task with a short timeline

_Jim Evans  7:04 PM_

.NET hasn’t added print-to-pdf yet.

nor full-page screenshot.

_Titus Fortner  7:06 PM_

on that note @barancev I couldn't duplicate the Ruby Remote Firefox bug on Mac, and VirtualBox appears to be a disaster running on Mac because I can't even get it to play nicely with a Linux install to test it there. NET read timeout is often a random network problem, but it looks like we've been seeing the error consistently, so I'm concerned about it being a real problem. @p0deje do you have a linux machine available to see if we can replicate locally?

_Alex Rodionov  9:35 PM_

@titusfortner Not really and frankly I don’t have spare time to set it up to investigate

Speaking of which, I’ll be leaving for a 1 month road trip next week and I won’t be able to do any Selenium work during that time. Likely till early February in fact. If there is anything I can do before that, please let me know.

_Titus Fortner  9:39 PM_

@p0deje when do you leave?

_Alex Rodionov  9:40 PM_

Next Wednesday

_Titus Fortner  9:45 PM_

oof, well I've been assigned to create and give 20+ hours of classroom training on Selenium for a client between now and EOY, and I have maybe half of that right now :)
I should be more available in January to help, but I've spent more time with the Selenium java code than Ruby code recently, so I'm not sure how we have the DevTools stuff implemented.
@twalpole what's your availability this month? :-D

_David Burns  9:49 PM_
do we have people to do releases for ruby with @p0deje away?

_Alex Rodionov  9:50 PM_

According to https://github.com/SeleniumHQ/selenium/issues/8168, a currently missing CDP stuff in Ruby is:
Intercept network requests allowing to mock backend requests
Bootstrap script
Record traffic
The first two I believe are implemented in Java so it should not be hard to redo it in Ruby. I might give it a shot this weekend.
The latter I think is missing in all bindings.
@titusfortner we can hop on pairing session and I’ll guide you through the code. Otherwise, it all lives in devtools directory

@AutomatedTester Yes, Titus can do releases

_David Burns  9:52 PM_

don't forget that @rajendra can help, BS is a ruby shop so we can help

he's been looking through the code for the devtools stuff and is working the print stuff

_Titus Fortner  10:02 PM_

I'll have bandwidth to do a release, but not to dig through the implementations until January :)
