---
title: "Public Project Meeting - May 20, 2021"
linkTitle: "Public Project Meeting - May 20, 2021"
date: 2021-05-20
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
timeline of the meeting held on May 20, 2021,5:30 PM CET. (Below times are on IST)

Meetings are held on the `#selenium-tlc` channel on [Selenium Slack](https://seleniumhq.slack.com/join/shared_invite/enQtODAwOTUzOTM5OTEwLTZjZjgzN2ExOTBmZGE0NjkwYzA2Nzc0MjczMGYwYjdiNGQ5YjI0ZjdjYjFhMjVlMjFkZWJmNDYyMmU1OTYyM2Y).

---   

_Simon Stewart  9:00 PM_

@AutomatedTester, @p0deje @titusfortner, @harsha509 @Puja Jagani @barancev @jimevans @Todd Tarsi and anyone else who's interested, come on down!

_9:00 PM_

Any agenda items?

_Diego Molina  9:00 PM_

I can paste the ongoing agenda and leave you to it :slightly_smiling_face:

_Simon Stewart  9:00 PM_

Yes please!

_Diego Molina  9:01 PM_

## Pending work for the 4th Beta?

* Server - Grid
* Pipe VNC connectors through the WebSocket plumbing for live video
* Unify new Session Queue
* Stress test the new Grid on AWS or similar with >50 simultaneous tests
* Check that the Redis/JDBC backed services actually function
* Distributor follows spec when allocating new sessions
* More than 1 IE session per Node? https://github.com/SeleniumHQ/selenium/issues/9388

## Language bindings

* “se:cdpVersion” is missing in C# and JS
* Can all bindings do CDP over Grid?
* What are the features present in Selenium 4?
* Do all bindings implement them?
* Selenium IDE
* Who knows what the release process is?
* What is needed?
* Who can help reviewing pull requests?

## 9388 Grid 4 doesn't allow more than 1 session for Internet Explorer

:boom: Regression Report

We used Selenium Grid 2 and 3 to run multiple IE sessions in parallel without issues for many years. But now Selenium 4 restrict the number of IE sessions to only 1 without any way to override the restriction.

Last working Selenium version

Worked up to version: 3.141.59

Stopped working in version: 4.0.0-alpha

## To Reproduce

1. Start the hub using java -jar selenium-server-4.0.0-beta-3.jar hub
2. Start the IE node using java -Dwebdriver.ie.driver=IEDriverServer-3.150.1.exe -jar selenium-server-4.0.0-beta-3.jar node --max-sessions 7 --override-max-sessions true
   Expected behavior
   A grid node that allows up to 7 tests that use IE to run in parallel.
   Environment
   OS: Windows  
   Browser: IE  
   Bro… Show more
   Labels
   C-grid
   <https://github.com/SeleniumHQ/selenium|SeleniumHQ/selenium>SeleniumHQ/selenium | Apr 17th | Added by GitHub

_Simon Stewart  9:01 PM_

Thank you!

_9:01 PM_

I'll add:
What do we want in RC1?

_9:01 PM_

Anyone want to add anything else?

_Diego Molina  9:02 PM_

Also

## General project statistics
* Previous meeting: 157 open issues, 25 open pull requests
 Currently: 161 open issues, 25 open pull requests
  
_9:02 PM_

(so, I cannot run the meeting, but I am around :slightly_smiling_face:)

_Simon Stewart  9:02 PM_

It's much appreciated. Thank you, @diemol

_9:03 PM_

I'm guessing silence means that there's nothing else to add?

In that case: Pending work for beta 4

_Simon Stewart  9:04 PM_

I saw that @Puja Jagani has got tracing working again, which I'd love to see in b4.

_Puja Jagani_

Will be landed tomorrow!

_Simon Stewart_

Woohoo!

_Simon Stewart_

Thank you :slightly_smiling_face:

_Simon Stewart  9:04 PM_

And @diemol posted a video of the Grid scaling up to 50 clients

_9:04 PM_

I've done some perf work to get better utilisation

_9:05 PM_

And we've unified the session queue too

_9:05 PM_

That is, I think Grid is good to go for b4 once the tracing work is landed

_9:05 PM_

Anyone (@harsha509 @AutomatedTester) know about se:cdpVersion in the JS bindings?

_David Burns  9:07 PM_

I’ve not done it

_Simon Stewart  9:07 PM_

(I see that @harsha509 is typing....)

_Sri Harsha  9:07 PM_

cdp version check is not implememted on js.

_Simon Stewart  9:07 PM_

OK. Do we need it for beta 4, or should we punt it to rc1?

_David Burns  9:08 PM_

We can punt it

_Simon Stewart  9:08 PM_

Great. Let's punt it to rc 1

_David Burns  9:08 PM_

No one has noticed how it works and no bug reports so…

_Simon Stewart  9:08 PM_

What about CDP over the Grid?

If you're using se:cdp for the websocket endpoint, it'll work

_9:09 PM_

(@titusfortner and @p0deje I'm curious about the ruby bindings and CDP-over-Grid too)

_David Burns  9:09 PM_

That is there already

_Titus Fortner  9:11 PM_

yeah, for Remote, Ruby gets the version from se:cdpVersion

_9:12 PM_

We run the tests for both local and via the Grid, and everything is passing

_Simon Stewart  9:12 PM_

Do the Ruby bindings use se:cdpVersion too?

I guess you lean into the typing less than Java and .Net, so maybe it doesn't matter so much

_Titus Fortner  9:13 PM_

https://github.com/SeleniumHQ/selenium/blob/411b883645023d1e1a629a3197d3552253b93f2f/rb/lib/selenium/webdriver/remote/driver.rb#L54
driver.rb

capabilities['se:cdpVersion'].split('.').first

<https://github.com/SeleniumHQ/selenium|SeleniumHQ/selenium>SeleniumHQ/selenium | Added by GitHub

_Simon Stewart  9:13 PM_

Perfect :slightly_smiling_face:

_9:14 PM_

waits for @titusfortner to type

_Titus Fortner  9:14 PM_

no need, I'm not saying anything useful

_9:14 PM_

but Ruby & devtools things I think we're good

_Simon Stewart  9:15 PM_

Great. Moving on, then!

_9:15 PM_

I get the feeling that if anyone had the time and energy, @jimevans would appreciate a hand with the .Net bindings

_9:16 PM_

I know that last status meeting, he mentioned that there were some features he wanted in there before beta4

_David Burns  9:17 PM_

@Puja Jagani has done some .Net stuff. If Jim tells us what needs doing we can help

_Simon Stewart  9:18 PM_

I think it's probably CDP-over-Grid, and maybe the CDP version stuff

_9:18 PM_

But, obviously, @jimevans knows waaaay more than I do about that

_David Burns  9:18 PM_

I need to do the multiple options handling to capabilities work in the python code

_9:19 PM_

If I had deprecated some of the code in 3.14 it would be Waaaaaaay easier

_Simon Stewart  9:20 PM_

We can have a discussion about what you're thinking of breaking later

_9:20 PM_

It may just be that we do the simple thing, though

As far as I'm concerned, then, the thing we're waiting for is @jimevans to give us the green light for the .Net stuff being ready for beta4

_Titus Fortner  9:21 PM_

I did just notice that Ruby hasn't implemented Full Page screenshot support for Firefox, yet; I'll get that added today.

And on that vein, I need to create some examples of browser specific features being used with Remote drivers in each language, because I know not everything is working in every binding, yet

_Simon Stewart  9:21 PM_

What do other people think?

_David Burns  9:21 PM_

Nothing will break promise

_Simon Stewart  9:21 PM_

@titusfortner I think we can set "feature parity between bindings" as an rc1 goal

_Titus Fortner  9:21 PM_

yup, makes sense

_9:22 PM_

I"ll get the Firefox screenshot thing in before beta4

_Simon Stewart  9:22 PM_

As long as the broad features are there in beta4 (notably, the CDP-related infrastructure, if not the nicer APIs built on top)

_Titus Fortner  9:22 PM_

Very excited about how close we are :slightly_smiling_face:

_Simon Stewart  9:22 PM_

Which neatly brings us on to What do we want in RC1?

_Titus Fortner  9:23 PM_

ensure feature parity :slightly_smiling_face:

_Simon Stewart  9:23 PM_

* Feature parity between languages
* Updated CDP versions for current browsers
* More Grid stress testing

_Titus Fortner  9:23 PM_

or at least document it

_Simon Stewart  9:23 PM_

Documentation :slightly_smiling_face:

_Titus Fortner  9:23 PM_

while we're here, the JUnit 5 dev doesn't have any ideas for how to fix the issue:

* https://github.com/junit-team/junit5/issues/2273#issuecomment-840516940

Comment on #2273 Parallelism value ignored for the fixed strategy

I'm all ears if anyone has a proposal how to resolve this. The only thing I've come up with would be to wrap the execution of each test in another, non-ForkJoinPool thread but that would double the number of required threads and have performance implications.
<https://github.com/junit-team/junit5|junit-team/junit5>junit-team/junit5 | May 13th | Added by GitHub

_9:24 PM_

it's definitely a JUnit thing, but it's going to affect our users :disappointed: :disappointed:

_Simon Stewart  9:24 PM_

Why they don't just maintain an AtomicInteger of the number of running threads is beyond me

_Titus Fortner  9:25 PM_

Can you suggest that?

_9:25 PM_

I am so far out of my depth with threading that I will get in the way more than help

_Simon Stewart  9:25 PM_

We can get this sorted :slightly_smiling_face:

_Titus Fortner  9:26 PM_

:thumbsup:

_Simon Stewart  9:26 PM_

Does anyone have anything else that they want to see in rc1?

_9:26 PM_

Or, to rephrase, if we ship rc1 and nothing major blows up, what would stop us making the release after that 4.0?

_9:28 PM_

(I'm going to take silence here to mean that everyone is really busy right now)

_Titus Fortner  9:29 PM_

Your list looks good to me...

_Simon Stewart  9:30 PM_

OK.

_9:31 PM_

So, let's move on to Selenium IDE

I think that there's a small core of us who could release the IDE

_9:32 PM_

But @corevo and DaveH would know the process best

_9:33 PM_

To recap:
* Selenium IDE
* Who knows what the release process is?
* What is needed?
* Who can help reviewing pull requests?

_Todd Tarsi  9:33 PM_

@simonstewart - I would love to help in any capacity that I can, including reviewing PRs.

_Simon Stewart  9:34 PM_

That's great news :slightly_smiling_face:

_9:34 PM_

Thank you

_9:34 PM_

Again, it feels like we're not going to make much progress today on this.

_Titus Fortner  9:35 PM PM_

I think we need to hear back from former owners to make progress on it

_Simon Stewart  9:35 PM_

Can I suggest that we set up a call or email chain with @corevo, DaveH, @harsha509 and whoever else is interested to move that forwards?

_Titus Fortner  9:35 PM_

can we reach out to them not-on-slack since they don't seem to be paying attention here?

yes, that :slightly_smiling_face:

_Simon Stewart  9:35 PM_

nods

@harsha509, would you be okay arranging that?

If we can get you email addresses….

_Sri Harsha  9:36 PM_

I can take inputs from @corevo  on how to release and document it.

_9:37 PM_

Sure @simonstewart

_Simon Stewart  9:37 PM_

Brilliant. Thank you. I'll be happy to help move things along if I can

_Sri Harsha  9:37 PM_

:bow:

_Simon Stewart  9:37 PM_

@Todd Tarsi I bet it'd be interesting to hear how the electron work was going

_Todd Tarsi  9:38 PM_

@simonstewart - It will be better with a PR as a point of discussion, so I'll put one out later tonight.

_Simon Stewart  9:38 PM_

Oh yeah. Definitely a PR discussion :slightly_smiling_face:

_9:39 PM_

Alright then, folks. I think we've romped through the agenda.

_9:39 PM_

Is there anything else people would like to talk about?

_Titus Fortner  9:39 PM_

What is the current status of BiDi?

_Luis Correia  9:40 PM_

Documentation :slightly_smiling_face:

_Titus Fortner  9:40 PM_

I think we closed the issue that was listing what we were going to implement, presumably because we've implemented the things we deemed essential

_Simon Stewart  9:41 PM_

Status of Bidi? The w3c spec, or the use cases we planned to support?

_Titus Fortner  9:41 PM_

the latter

and the former to the extent that it might be what is driving the latter?

_Simon Stewart  9:41 PM_

@Luis Correia Acknowledged. We'll talk about docs after the bidi stuff

The w3c spec won't be finished before we ship 4.0, so let's set that aside

_Titus Fortner  9:42 PM_

oh yes, I waited until after we were done talking about betas and RCs to bring this up :slightly_smiling_face:

this is future roadmap things

_Simon Stewart  9:42 PM_

So, the use cases from the issue were:
* Listen for DOM events
* Log what's going on in the browser including console and JS errors
* Fail fast on any JS error
* Intercept network requests allowing to mock backend requests
* Record traffic (Alex: Allow replaying it?)
* Authenticate on secure pages
* Bootstrap scripts (for example, execute JavaScript snippet on every page load)

_9:43 PM_

Recording traffic can be done by the same code that does network interception

I think bootstrap scripts are probably the most poorly supported thing right now

_9:44 PM_

Technically, Java has code for it, but I have zero confidence it's robust. Or, indeed, that I actually landed it

The w3c spec started with a discussion of use cases too, and there's a lot of overlap (surprise!)

_9:45 PM_

I'd love to get script pinning using CDP in place. That'll exercise bootstrap scripts sufficiently

_Titus Fortner  9:46 PM_

So maybe I need to better understand how the spec and selenium code are supporting each other. Do we need to make sure the features we are adding are documented in the spec?

(i.e. is one driving the other, or is this like before where geckodriver & w3c drove each other)

_Simon Stewart  9:47 PM_

The spec is currently putting in place a lot of the groundwork to start doing useful things, so it's not covering the use cases yet

_9:48 PM_

I suspect that Selenium 4 utilisation will help steer the spec as we find out the subset of the CDP that's actually useful for testing and automation

_Titus Fortner  9:48 PM_

perfect, thanks

_Simon Stewart  9:48 PM_

It's something of a chicken-and-egg problem right now.

_9:49 PM_

Once we ship 4, I'm planning to switch my focus to the spec

_Titus Fortner  9:49 PM_

always is with specs, right? :slightly_smiling_face:

_Simon Stewart  9:49 PM_

Always :slightly_smiling_face:

We have 10 minutes left! Shall we move on to Documentation?

_Titus Fortner  9:49 PM_

@diemol are we moving / have we moved to a different theme?

for docs

_Simon Stewart  9:49 PM_

@Luis Correia could you please expand on what you meant?

_Luis Correia  9:50 PM_

about single port grid or documentation?

_Simon Stewart  9:51 PM_

Documentation, please

_Luis Correia  9:52 PM_

ok, it is my understanding that current docs are too surgical, explaining one thing but maybe not with full context
Specifically in the grid 4 docs

_Simon Stewart  9:52 PM_

How would you like that to change?

_Luis Correia  9:53 PM_

in the website there isn't a section with all available options and switches, one has to delve into code

_9:53 PM_

note: it's not a blocker for release but rather a nice to have feature

_9:55 PM_

this is a good starting link

https://www.selenium.dev/documentation/en/grid/grid_4/

selenium.dev

Grid 4 :: Documentation for Selenium
Documentation for Selenium

_Simon Stewart  9:55 PM_

OK, so a better description than you get from selenium -h?

_Titus Fortner  9:55 PM_

https://www.selenium.dev/documentation/en/grid/grid_4/configuring_components/config_help/

selenium.dev

Config help :: Documentation for Selenium

Documentation for Selenium

_Luis Correia  9:55 PM_

I would be happy with that on a webpage

_Simon Stewart  9:56 PM_

OK. Would you please leave a comment on https://github.com/SeleniumHQ/selenium/issues/8165?

8165 Update user docs with new Selenium 4 features
:rocket: Feature Proposal
Selenium 4 brings a set of all-new features and https://www.selenium.dev/documentation/en/ needs to be updated to contain the related docs for them.
Motivation

It'd be nice to have the following new features covered in the docs:
• Relative locators
• CDP integration
• New Grid 4.0
Example
...
Labels
C-docs, Selenium-4-Beta
Comments
2
<https://github.com/SeleniumHQ/selenium|SeleniumHQ/selenium>SeleniumHQ/selenium | Apr 7th, 2020 | Added by GitHub

_Titus Fortner  9:56 PM_

looks like the page I linked there shows the commands but not the output?

_Luis Correia  9:56 PM_

@titusfortner or even why,
that's proper context

_9:57 PM_

i will @simonstewart

_Titus Fortner  9:57 PM_

providing context for it would definitely be useful

_Simon Stewart  9:57 PM_

Much appreciated. That's the tracking issue for improving the docs, so adding comments there will be helpful

_Titus Fortner  9:58 PM_

Or you can PR the changes you think would provide the context; docs are all in a public repo.

I'm struggling a bit with Sauce documentation right now. I'm good at making sure the info is "correct," but finding I'm not so great on explaining why it matters to people, and that part is important

_Simon Stewart  9:59 PM_

@titusfortner I guess you've seen https://developers.google.com/tech-writing?

_Titus Fortner  9:59 PM_

It's like I have "presentation" mode and "documentation" mode and I can't get them to work together :smile:

_Simon Stewart  9:59 PM_

Ah yeah. It's hard to get the narrative right

_Titus Fortner  10:00 PM_

well, it also helps to have people at Sauce whose actual career is organizing info so people can understand it

_10:00_

I hadn't seen the tech-writing page, that looks interesting

_Simon Stewart  10:01 PM_

Shall we wrap up? I think we covered most of the things we needed to

_10:02 PM_

The major decision was to wait for @jimevans to give us the green light for shipping beta 4.

I'm totally fine waiting as long as necessary for that, but I also heard @AutomatedTester suggest that maybe @Puja Jagani could help with that

_Titus Fortner  10:06 PM_

sounds great, thanks @simonstewart!
