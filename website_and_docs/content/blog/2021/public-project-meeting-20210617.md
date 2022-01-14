---
title: "Public Project Meeting - June 17, 2021"
linkTitle: "Public Project Meeting - June 17, 2021"
date: 2021-06-17
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
timeline of the meeting held on June 17, 2021,5:30 PM CET. (Below times are on IST)

Meetings are held on the `#selenium-tlc` channel on [Selenium Slack](https://seleniumhq.slack.com/join/shared_invite/enQtODAwOTUzOTM5OTEwLTZjZjgzN2ExOTBmZGE0NjkwYzA2Nzc0MjczMGYwYjdiNGQ5YjI0ZjdjYjFhMjVlMjFkZWJmNDYyMmU1OTYyM2Y).

---   

_***Diego Molina  8:52 PM***_

Here is the proposed agenda:

## General project statistics

* Previous meeting: 167 open issues, 28 open pull requests
* Currently: 170 open issues, 23 open pull requests

## Pending work for the RC 1?

* Server - Grid
* Hub reports Nodes going down https://github.com/SeleniumHQ/docker-selenium/issues/1257
* TimeoutException https://github.com/SeleniumHQ/selenium/issues/9528

## Language bindings

* “se:cdpVersion” is missing in C# and JS
* Can all bindings do CDP over Grid?
* What are the features present in Selenium 4?
* Do all bindings implement them?

## Release process

After beta 4, we should document how each binding and the Grid are released

## Selenium IDE

* Who knows what the release process is? What is needed?
* Who can help reviewing pull requests?

* [#1257 Hub reporting no available nodes after a few hours](https://github.com/SeleniumHQ/docker-selenium|SeleniumHQ/docker-selenium) SeleniumHQ/docker-selenium | Apr 8th | Added by GitHub

* [#9528 java.util.concurrent.TimeoutException thrown at random netty read timeouts with RemoteWebDriver](https://github.com/SeleniumHQ/selenium|SeleniumHQ/selenium) SeleniumHQ/selenium | May 28th | Added by GitHub

_***8:56 PM***_

btw, there is no hurry to start, we can also start when most folks are available, I believe this won’t be a long one

_***Diego Molina  9:01 PM***_

Just a comment from the increased number of issues…
I believe users are starting to give more feedback, so that’s good

_***9:05 PM***_

I believe we can slowly jump into the first topic:
Pending work for the RC 1?

_***9:06 PM***_

Regarding the Grid, I would say it is feature complete

_***9:07 PM***_

but we have a couple of bugs we are checking with @Puja Jagani

_***9:08 PM***_

I think we can sort them out during the next week
(fingers crossed :slightly_smiling_face:)

_***Diego Molina  9:13 PM***_

talking about the language bindings, I think the most relevant thing for RC1 is to check feature parity

_***Titus Fortner  9:14 PM***_

@AutomatedTester did the shadow Dom work you did end up in geckodriver /Firefox yet?

_***9:15 PM***_

I know some bindings implemented the endpoints already

_***David Burns  9:15 PM***_

No, it’s nearly done

Maybe I should hand it to the Mozilla folks and maybe they could finish it off

_***Titus Fortner  9:15 PM***_

I kind of wanted to wait until I could make sure tests 
passed and stuff because I never trust myself :)

_***David Burns  9:16 PM***_

There is an issue with something between marionette and geckodriver but those are super hard to diagnose

_***Titus Fortner  9:17 PM***_

There were some accessibility endpoints? I'm not sure we've all added. 
And I think ruby deprecated methods that other languages implemented with JS

I've been meaning to do a deeper dive into the various tests, but haven't gotten there yet. 
Ruby broke CDP in last beta release, we need to figure out

_***9:21 PM***_

We probably need to do a beta 4.1 just for ruby with that fix

_***Diego Molina  9:22 PM***_

Since the VNC stuff has been implemented, I have a bit of time next week to start working in an issue to document feature parity

_***Titus Fortner  9:23 PM***_

I'll dedicate some time this month to helping with it

Probably the last topic is
Release process

_***9:24 PM***_

Last week Simon recorded the Java release and shared the video so I can document it

It’d be great if the other folks who also do releases do the same, 
so we can document and potentially automate releases

_***Diego Molina  9:30 PM***_

I believe that was everything we had in the agenda

_***Titus Fortner  9:36 PM***_

Documentation. We got an offer from a company to help us with documentation so they can integrate it with their product which is an executable browser IDE thing

Or something. I'm not entirely certain, but sounds potentially interesting.

_***Diego Molina  9:37 PM***_

tell us more, @titusfortner :slightly_smiling_face:

_***Titus Fortner  9:37 PM***_

I at least want to get a demo and see what they mean.

I don't know more, yet. :)

_***Diego Molina  9:37 PM***_

ah ok, they reached out to you privately :slightly_smiling_face:

_***Titus Fortner  9:38 PM***_

Yeah, I'll loop you in when we set up a demo. Might not work for what we need

_***Diego Molina  9:39 PM***_

sounds good
