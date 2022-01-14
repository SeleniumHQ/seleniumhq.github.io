---
title: "Public Project Meeting - June 03, 2021"
linkTitle: "Public Project Meeting - June 03, 2021"
date: 2021-06-03
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
timeline of the meeting held on June 03, 2021,5:30 PM CET. (Below times are on IST)

Meetings are held on the `#selenium-tlc` channel on [Selenium Slack](https://seleniumhq.slack.com/join/shared_invite/enQtODAwOTUzOTM5OTEwLTZjZjgzN2ExOTBmZGE0NjkwYzA2Nzc0MjczMGYwYjdiNGQ5YjI0ZjdjYjFhMjVlMjFkZWJmNDYyMmU1OTYyM2Y).

---   

_Diego Molina  9:01 PM_

:wave:
Anyone around for our public meeting?
@barancev @manoj9788 @mmerrell @simonstewart @harsha509 @Puja Jagani @AutomatedTester @titusfortner @p0deje @jimevans

_9:02 PM_

Let me post our ongoing agenda

## General project statistics
* Previous meeting: 161 open issues, 25 open pull requests
* Currently: 167 open issues, 28 open pull requests
* Pending work for the Beta 4?

## Server - Grid

* Pipe VNC connectors through the WebSocket plumbing for live video
* Check that the Redis/JDBC backed services actually function
* Distributor follows spec when allocating new sessions

## Language bindings

* “se:cdpVersion” is missing in C# and JS
* Can all bindings do CDP over Grid?
* What are the features present in Selenium 4?
* Do all bindings implement them?

## Release process

* After beta 4, we should document how each binding and the Grid are released

## Selenium IDE

* Who knows what the release process is? What is needed?
* Who can help reviewing pull requests?

* please feel free to add any topics you’d like to add

_Todd Tarsi  9:04 PM_

I have been puttering on running ide v3 inside of ide v4, and it is kinda coming along. I'll just post a screencap of where it's at when we get there

_Simon Stewart  9:04 PM_

Agenda LGTM

_Diego Molina  9:05 PM_

A comment on
General project statistics
It feels as if folks are trying betas a bit more, I see an increase of issues

_Simon Stewart  9:06 PM_

That's a Good Thing.

_Diego Molina  9:06 PM_

Most of the Grid ones can be fixed with docs, so I can take time to do that

other ones, seem to be either bugs or people just figuring out how to use beta 3, not sure

_Simon Stewart  9:06 PM_

I got some informal feedback from someone who said "btw it seems a ton more stable than 3.141.59 for us on soak test".

_Diego Molina  9:06 PM_

That is nice to know

_Simon Stewart  9:06 PM_

Are there any issues we should be prioritising?

I've not read them recently, but if there's a theme, we can try and address the systemic causes

_Diego Molina  9:09 PM_

We also have a few PRs pending to review, some of them related to the Grid, 
which @Puja Jagani created, and maybe when things are less intense, you could have a look @simonstewart, please?

_Simon Stewart  9:10 PM_

Sure. I'll get on that ASAP

_Diego Molina  9:10 PM_

ok, so
Pending work for the Beta 4?

_Simon Stewart  9:11 PM_

Just the .Net bindings, I think
New

_Diego Molina  9:11 PM_

* Server - Grid
* Pipe VNC connectors through the WebSocket plumbing for live video
* Check that the Redis/JDBC backed services actually function
* Distributor follows spec when allocating new sessions
  I think “Distributor follows spec when allocating new sessions” was done, right?
  
and I believe @Puja Jagani tackled “Check that the Redis/JDBC backed services actually function”, not sure

_Simon Stewart  9:11 PM_

I think so, just checking

_Simon Stewart  9:11 PM_

I think so, just checking

_9:12 PM_

Yeah. We follow the spec better now

Before rc1, I'll do some more command line testing of it using curl. I bet I can still make it fail

_Diego Molina  9:13 PM_

I was going to start working on “Pipe VNC connectors through the WebSocket plumbing for live video” but 
I bumped into the StressTest not passing, which lead to a fix. I want to have this before RC1, hopefully for beta 4

_Simon Stewart  9:13 PM_

Hopefully the infrastructure you'll need is already in place.

I guess you're going to expose a se:vnc capability or similar?

_Diego Molina  9:14 PM_

yes, that is the idea

_Simon Stewart  9:14 PM_

Perfect

Which reminds me, we should make sure that both Sauce and BS know that the Selenium 4 local 
ends will all try connecting to se:cdp and their infra could/should/might want to handle that

_Titus Fortner  9:18 PM_

local ends should only try connecting to that endpoint if using a devtools feature. 
Might make sense to just let it error out as unknown endpoint if it isn't supported.

_Diego Molina  9:18 PM_

We are working internally on that, everyone at Sauce is aware :slightly_smiling_face:

_Simon Stewart  9:18 PM_

@titusfortner that's what the java code does

@diemol that's great news :slightly_smiling_face:

_Diego Molina  9:20 PM_

Ok, so
Language bindings
* “se:cdpVersion” is missing in C# and JS
* Can all bindings do CDP over Grid?

I am not sure if @harsha509 and @AutomatedTester had time to use that in the JS bindigns

_Simon Stewart  9:21 PM_

If bindings are using se:cdp properly, then they should be able to do CDP over Grid.

_Sri Harsha  9:22 PM_

No @diemol. As discussed in last we punt it to rc1. If time permits i will look into it for sure

_Simon Stewart  9:22 PM_

I think the .Net bindings aren't aware of the se:cdp capability yet

A quick grep suggests that's true

_Diego Molina  9:22 PM_

thank for the update, @harsha509!

ok, so maybe we can jump to the next topic:
* What are the features present in Selenium 4?
* Do all bindings implement them?

I have not been able to get this list done, lack of time and also I do not know where to start to get this information

_9:26 PM_

from the top of my head, is:
* Relative locators (Java can accept the locator, the other bindings use withTag)
* New tab command
* CDP (but what CDP features?)
* Full page screenshot in Firefox
* Print to PDF

_9:26 PM_

any others?

_Sri Harsha  9:26 PM_

JS bindings doesnt have 	/session/{session id}/element/{element id}/shadow endpoint implemented

_9:27 PM_

not sure of other language bindings

* computedRole

* computedLabel

_Titus Fortner  9:28 PM_

Do any of the drivers have shadow endpoint implemented?

_Simon Stewart  9:28 PM_

I'll wire that into Java next week

_Jim Evans  9:28 PM_

working away at .NET to get ready for beta.
:tada:
4

_Simon Stewart  9:28 PM_

I may wire in a fallback too

_Jim Evans  9:29 PM_

i want to also include the shadow endpoints, but i may not get to that.

_Simon Stewart  9:29 PM_

It can wait for rc1.

_9:30 PM_

For the record, I'm thinking of rc1 as "if we want to, we could tag this as 4.0 and be done", so until we ship that, we don't need all the commands in place.
:+1:
1

_9:30 PM_

I'm seriously hoping beta4 is the last beta

_Titus Fortner  9:31 PM_

So, it's not explicitly on the agenda, but relates to the languages implementing everything....

documentation.

Would be nice to have something that explains everything that is new with examples in each of the languages

_9:31 PM_

(as in I am willing to help not just complain about this one)

_9:33 PM_

but...

* Do we still want to update to a different theme first?

* Would it be possible to get help from a "Technical Content Strategist" or the like to help us organize things?

_Diego Molina  9:33 PM_

that is my idea by listing the features and seeing where they are implemented and where they need to be implemented
:+1:
2

_Sri Harsha  9:33 PM_

I have tried my best to provide examples for all language bindings and a note  on top of it like
https://www.selenium.dev/documentation/en/webdriver/browser_manipulation/#create-new-window-or-new-tab-and-switch

_Simon Stewart  9:34 PM_

@titusfortner a human's guide to the new features is a good idea.

_Titus Fortner  9:34 PM_

After spending a little time with the Sauce tech content team it has really showed me how 
much of a difference it makes to have someone who does this for a living organize things

I can look at something and say "this structure doesn't make sense," but my solution will be something that also doesn't make sense. :smile:

_Diego Molina  9:36 PM_

I want to move to Docsy, a hugo theme that helps us to have website and docs in one single place

_Titus Fortner  9:36 PM_

and if we can reference code snippets from selenium repo tests rather than 
having to create/maintain them independently in our docs that would be huge

Is docsy the one I was looking at last time (whenever that was)?

_Simon Stewart  9:36 PM_

@diemol can the move to docsy be done at the same time as the docs themselves are updated?

_Diego Molina  9:36 PM_

and I have found Hugo shortcodes that can render real tests hosted in GitHub repositories, so we can run those tests in GitHub actions and provide working examples
:tada:
2

_Simon Stewart  9:37 PM_

Oh! That's nice!

_Diego Molina  9:37 PM_

I want to move the website first and leave for last the docs, 
but it will be hard to sync things, but it can be done

_Titus Fortner  9:38 PM_

I feel like if we have
* A more intuitive structure of documentation
* A template for adding code snippets in tabs

then it's just a matter of finding the test code in the project for each feature

_Diego Molina  9:39 PM_

nro. 2, is the Hugo shortcode I found

_Titus Fortner  9:39 PM_

and I'd like to propose we actually look to hire someone to help us with #1.

Top notch documentation for Selenium 4 could make all the difference

_Simon Stewart  9:39 PM_

We can easily set up an "example" repo that people can look at

_Titus Fortner  9:40 PM_

@diemol yeah, I just need an example in the template we want to use, 
because that's what I was missing the last time I tried to help :slightly_smiling_face:

_Luis Correia  9:41 PM_

sorry for intruding, the documentation so far is too "surgical", lacking proper context.
I would provide a full Java/C#/Python Class/Snipped with inline comments that would explain constructs

_Titus Fortner  9:41 PM_

I loaded the new template, spent like 8 hours tyring to figure 
out how to get tabs to work, and then gave up. :smile:

_Luis Correia  9:42 PM_

bonus points would go to code interacting with an example website

_Diego Molina  9:42 PM_

This is normally the type of thing that gets done when someone spends a 
few days working on it and then enabling others to work
:+1:
1

_9:42 PM_

So hopefully I can have time for that after beta 4 is released

_Titus Fortner  9:43 PM_

I think if I understood Go better I might have been successful

_9:43 PM_

it just is very different from Docusaurus and Jekyll which are the 
2 static site implementations I've worked with

and I like @Luis Correia’s point about more context

_Diego Molina  9:43 PM_

We can pair on that, I don’t know Go so much, I just know how to copy & paste properly

_Titus Fortner  9:44 PM_

but as someone who writes things with a lot more context, it's also a *much more significant undertaking

_9:44 PM_

Watir we have the inline docs that are surgical, then "guides" on our website that try to explain things

And we have examples in each of the repos against our test server

something we might do is publish the test server on selenium.dev so anyone can run their own code against it without having to build selenium?

_9:45 PM_

I did that for the watir test code as well

_Luis Correia  9:46 PM_

yes, public test servers are a good thing

I can help testing and correct testing code (mainly for Java) against that server

I'm kinda "well versed" into Maven build cycles and can probably help the project in that respect

_Titus Fortner  9:48 PM_

I just copy/pasted everything to the examples directy: http://watir.com/examples

People can change url_for("forms_with_input_elements.html") in the specs to "http://watir.com/examples/forms_with_input_elements.html"

_Diego Molina  9:48 PM_

maybe we can jump into the next topic?
Release process
* After beta 4, we should document how each binding and the Grid are released

_Titus Fortner  9:52 PM_

For Ruby you need an account on rubygems. We can probably set one up a common email @selenium.dev with a secret password

_9:53 PM_

we can build the gem with bazel now, but the actual release requires manual intervention still

but I think we all agree that it is needed and we can do it after Se4 release

_Diego Molina  9:56 PM_

Something we were talking yesterday was to record the release process in a zoom call or similar and some of us who have more time, can document that process

_Simon Stewart  9:58 PM_

The java process isn't too taxing

_9:58 PM_

The main thing is you need a Sonatype OSS account so you can publish the artifacts

_Diego Molina  9:59 PM_

so I believe we can focus on this topic a bit more when we feel close to beta 4 release?

_Titus Fortner  10:01 PM_

or RC :slightly_smiling_face:

_Simon Stewart  10:03 PM_

We are close to the b4 release :slightly_smiling_face:

@titusfortner getting the leg-rc package working properly again is on my list

_Diego Molina  10:04 PM_

I think we should find a way to document releases sooner than later because I imagine we will need to iterate faster when RC1 happens

_Titus Fortner  10:04 PM_

I meant release candidate version vs beta 4, not the RC package, but sure :wink:

_Simon Stewart  10:04 PM_

@barancev and I both have the keys for java releases

_Diego Molina  10:11 PM_

ok, I guess the idea would be to simply record those sessions

_Sri Harsha  10:11 PM_

Coming to Selenium-ide, I have the production build from V3 branch.

At the time of release i can change the build version, build, zip it and pass it over to someone who has access for release

_Titus Fortner  10:11 PM_

:thumbsup:

_Diego Molina  10:12 PM_

but I think we can chat more about this when we are actually going to release beta 4?

_10:13 PM_

Good idea to jump into the next topic :slightly_smiling_face:

Selenium IDE
* Who knows what the release process is? What is needed?
* Who can help reviewing pull requests

so releasing it is as easy as uploading the zip somewhere? to the browser stores?

_Todd Tarsi  10:16 PM_

I can help review pull requests, but I don't have any permissions around that stuff. Would reviewing PRs stil lhelp?

_Diego Molina  10:17 PM_

that would definitely help

_Todd Tarsi  10:17 PM_

Sounds good, I'll take up a more active role reviewing PRs then.

_Diego Molina  10:18 PM_

I can help with merging if needed :slightly_smiling_face:

but we really need to figure out how to release

_Todd Tarsi  10:18 PM_

The release process seems like something that shouldn't be that complicated. We build for edge, firefox, and chrome and then upload the zip files to the stores, right?

_Sri Harsha  10:18 PM_

Yes @diemol. Uplaoding the zip in webstore who has release access

_Diego Molina  10:18 PM_

I will try to ping Dave and Tomer privately

_Simon Stewart  10:18 PM_

Thank you @Todd Tarsi Helping to review things would be really helpful

There's a chance I have access to the release account

_Sri Harsha  10:19 PM_

i asked @corevo . He said @simonstewart may have access to webstore to publish

_Simon Stewart  10:19 PM_

At least for Firefox, and maybe Chrome

_Diego Molina  10:20 PM_

sounds good, that would help a lot

_Simon Stewart  10:22 PM_

Will check tomorrow
