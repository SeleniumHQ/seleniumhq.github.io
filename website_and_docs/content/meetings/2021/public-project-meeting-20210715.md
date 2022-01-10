---
title: "Public Project Meeting - July 15, 2021"
linkTitle: "TLC - July 15, 2021"
date: 2021-07-15
---

Continuing the series of bi-weekly public project meetings, here is the
timeline of the meeting held on July 15, 2021,5:30 PM CET. (Below times are on IST)

Meetings are held on the `#selenium-tlc` channel on [Selenium Slack](https://seleniumhq.slack.com/join/shared_invite/enQtODAwOTUzOTM5OTEwLTZjZjgzN2ExOTBmZGE0NjkwYzA2Nzc0MjczMGYwYjdiNGQ5YjI0ZjdjYjFhMjVlMjFkZWJmNDYyMmU1OTYyM2Y).

---

_***Diego Molina  9:00 PM***_

Here is the agenda proposal:

## General project statistics
* Previous meeting: 170 open issues, 23 open pull requests
* Currently: 168 open issues, 25 open pull requests

* Pending work for the RC 1?
* Server - Grid
* Hub reports Nodes going down https://github.com/SeleniumHQ/docker-selenium/issues/1257
* TimeoutException https://github.com/SeleniumHQ/selenium/issues/9528
* IE Stopped working on beta 4 https://github.com/SeleniumHQ/selenium/issues/9600
* Language bindings

“se:cdpVersion” is missing in C# and JS

Can all bindings do CDP over Grid?

* What are the features present in Selenium 4?

Do all bindings implement them


_***9:01***_

@AutomatedTester @Puja Jagani @barancev @jimevans @titusfortner @p0deje @harsha509
please tag the ones I forgot

_***Titus Fortner  9:02 PM***_

Documentation theme change update

_***9:03***_

(I know you've been busy)

_***David Burns  9:04 PM***_

The python ci will be green today

_***Diego Molina  9:04 PM***_

ok, so let’s start

Regarding bugs we had, with @Puja Jagani we fixed the queue issue, so now the Grid runs enough sessions in parallel

_***9:05***_

but we still have 3 open issues, mentioned in the agenda

the timeout one has proven to be hard to reproduce, and therefore fix

_***David Burns  9:05 PM***_

I was able to reliably recreate the timeout issue. We were hitting issues in chrome that makes us think chrome is breaking

_***Diego Molina  9:06 PM***_

really? The original issue is reported with Firefox

_***David Burns  9:06 PM***_

Well, I am getting the timeout message, like with Firefox, with chrome 91

_***9:07***_

And it’s like the browser tab has hung/crashed and it manifests

@Puja Jagani has the script and I will help reduce if I can

_***Simon Stewart  9:07 PM***_

I need to do some rework of NetworkInterceptor to allow everything to be intercepted

_***9:08***_

And I think https://github.com/SeleniumHQ/selenium/issues/9594 is fixed by 
https://github.com/SeleniumHQ/selenium/commit/0795e78559924be1b69b4c8051f5185f289a4268
#9594 Hub reporting no available nodes after a few hours
<https://github.com/SeleniumHQ/selenium|SeleniumHQ/selenium>SeleniumHQ/selenium | Apr 8th | Added by GitHub


_***Diego Molina  9:08 PM***_

I think @Puja Jagani saw the issue this morning, and she said she was having a look

_***Simon Stewart  9:09 PM***_

Darn it. I thought we had it fixed

Though we don’t handle the case automatically. I manually tweaked things to make that work

_***David Burns  9:09 PM***_

Shoutout to Puja for looking at ALL THE THINGS

_***Simon Stewart  9:10 PM***_

+1000

_***Diego Molina  9:11 PM***_

aside from those two, now looking into the IE issue with Grid\

as the Grid is sending and invalid JSON to the IEServer

_***Jim Evans  9:12 PM***_

um, se:cdpVersion was implemented in .NET in f10cb89


_***Simon Stewart  9:12 PM***_

Woot!

_***Diego Molina  9:12 PM***_

great, I will update that item :tada:

on the client side, we still need to find time to build the feature parity doc

_***9:15***_

Aside from those items, I can give an update on the new (or reworked) website

_***9:16***_

I’ve been spending 1-2 hours per day, for now I am only building the website as it is, not digging into the docs yet

_***Titus Fortner  9:16 PM***_

"digging into the docs" == "updating the docs theme" ?

_***Diego Molina  9:16 PM***_

so, everything below the about menu is already implemented

_***Titus Fortner  9:16 PM***_

or writing the docs

_***Diego Molina  9:17 PM***_

and I still need to create pages for these links

_***Titus Fortner  9:18 PM***_

that all looks great!

_***Diego Molina  9:18 PM***_

to bring context, our website is made from two different Hugo themes, glued together during the build process
and a few hacks in between

I’ve found https://www.docsy.dev/ a good alternative since it offers a good theme that can contain both things in one

_***9:19***_

this requires to recreate our website using the docsy theme, this part is around 60% done
then we need to migrate the existing docs into the new theme

_***9:20***_

migrating the docs could be done as a task of copying and pasting or we could see it as a chance to reestructure and organise the existing content, and adding a few things more

_***9:21***_

if we want to reestructure, it would be nice to see proposals (like a global TOC with brief descriptions of each item)

_***9:21***_

then we can reuse what we have currently, and add what is missing

_***Titus Fortner  9:22 PM***_

So code snippets would look like this: https://gist.github.com/harsha509/e690dd2c72f1f5c5785be4c8a47c87fd

_***Simon Stewart  9:22 PM***_

Lift and shift first, rework second

_***Diego Molina  9:22 PM***_

code snippets is an implementation detail

_***Simon Stewart  9:22 PM***_

(That is, let’s open the door to contributions first, then figure out how things should be shaped)

_***Titus Fortner  9:22 PM***_

I get it's implementation

_***9:23***_

but that's the piece I can help with :slightly_smiling_face:

_***Diego Molina  9:23 PM***_

right now they look similar to that, so it could be a matter of adjusting things


_***9:24***_

ideally, the future for code snippets is to render code stored in GitHub, so we can execute it in GitHub actions for example

_***Titus Fortner  9:25 PM***_

I guess the question I had about it (what I tried to figure out a few months back and failed) is if there's a way to point to a repo like you & Christian built for Docusaurus :smile:
(another implementation detail)
But... there's nothing preventing us from writing that code now and figuring it out later

_***David Burns  9:25 PM***_

I like rust for this reason… when you run tests it also tests the doc snippets

_***Titus Fortner  9:25 PM***_

@p0deje implemented that for Ruby

_***9:26***_

it's really neat

I'll try to find time to propose something here and try to get feedback from people smarter than I am about these things
https://seleniumhq.slack.com/archives/CBH302726/p1626364266427200

_***Diego Molina***_

if we want to reestructure, it would be nice to see proposals (like a global TOC with brief descriptions of each item)
Posted in #selenium-tlc | Today at 9:21 PM | View message

_***Diego Molina  9:28 PM***_

all WIP is committed, so if someone wants to have a look, please check https://github.com/SeleniumHQ/seleniumhq.github.io#wip-moving-to-hugo-docsy-theme

SeleniumHQ/seleniumhq.github.io
Official Selenium website and documentation
Website
https://selenium.dev/

_***Titus Fortner  9:29 PM***_

Nice work on all of that

_***Simon Stewart  9:29 PM***_

Agreed. Excellent work, @diemol. Thank you

_***Diego Molina  9:29 PM***_

hopefully I can have the site migrated in a couple of weeks, then we have focus on the docs

_***9:30***_

I do not have any more topics, does anyone have something else?

_***Titus Fortner  9:31 PM***_

Hopefully I'll have all my ${paidWork} code in a good place at that point to take a break

_***9:32***_

I also promised the chromedriver team that I'd help fix up their Ruby code

Lots of things to update

_***9:33***_

Right now I'm busy updating all of my Java test examples from JUnit 4 to JUnit 5 thanks to @Puja Jagani 

_***Diego Molina  9:36 PM***_

ok, so it seems we are done with today’s public meeting, thank you everyone!
