---
title: "Public Project Meeting - January 14, 2021"
linkTitle: "Public Project Meeting - January 14, 2021"
date: 2021-01-14
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
timeline of the meeting held on January 14, 2021,5:30 PM CET.(Below times are on IST)

Meetings are held on the `#selenium-tlc` channel on [Selenium Slack](https://seleniumhq.slack.com/join/shared_invite/enQtODAwOTUzOTM5OTEwLTZjZjgzN2ExOTBmZGE0NjkwYzA2Nzc0MjczMGYwYjdiNGQ5YjI0ZjdjYjFhMjVlMjFkZWJmNDYyMmU1OTYyM2Y).

---   

_Diego Molina  10:02 PM_

Alright, :wave:  everyone, let me share the agenda

_10:02 PM_

* Overall announcements? New people on committees, with the commit bit, etc?
* Anything new to report from the last PLC/SFC call?
* Pending work that needs to be done for the first Beta?
* CoC, next steps?
* Any other topics?

_10:04 PM_

pinging @titusfortner, @AutomatedTester, @mmerrell, @barancev

who else?

_Simon Stewart  10:04 PM_

@jimevans
@p0deje
@harsha509

_Jim Evans  10:05 PM_

i’m already in another meeting at the moment.

Sri Harsha  10:05 PM

m available

_Diego Molina  10:05 PM_

ok, let’s bring the first topic:
Overall announcements? New people on committees, with the commit bit, etc?

_David Burns  10:05 PM_

here

_Diego Molina  10:06 PM_

So, I asked both to the TLC members about adding @luke as a committer, 
and I also reached out to Luke, both sides are happy with it.

_David Burns  10:06 PM_

cool!

_Diego Molina  10:07 PM_

Then, I will add Luke during the next couple of days 
and follow the steps we have in the Governance model

_Simon Stewart  10:08 PM_

Congratulations, @luke!
:tada:

_Diego Molina  10:09 PM_

great!
So, I think we have nothing else to add to this point?

_Marcus Merrell  10:09 PM_

I've got approval emails out to SFC to get the new contractor on the Code of Conduct. It's time to poke again.
We've tentatively selected dates for a Chicago SeConf (sometime in September, I believe). 
We're still hopeful that with the vaccine, this will be possible. we'll look to make a final decision, based on numbers, in ~April
:parrot:

_10:11 PM_

We need to consider whether or not to run a virtual conference in the case that it doesn't work out to have an irl conference

_Simon Stewart  10:11 PM_

Can that decision also be delayed until April?

_Marcus Merrell  10:11 PM_

I'm a little uncomfortable waiting that long--not for the hotel and 
the risk of losing the deposit $$, but because of marketing/CFP stuff
let's say "early April"

_Diego Molina  10:12 PM_

Great news about the CoC!
ok, seems we covered also the “Anything new to report from the last PLC/SFC call?” topic as well, right?

_Simon Stewart  10:14 PM_

I think so

_Diego Molina  10:15 PM_

ok, so let’s jump to “Pending work that needs to be done for the first Beta?”

_Simon Stewart  10:15 PM_

There’s a few things

_10:16 PM_

I’ve a PR out for the new RemoteWebDriverBuilder, which is one piece.
The other big bit for me is the pluggable locators, tying up the local and remote ends bits we already have.
That’ll be easier with the RemoteWebDriverBuilder in place.

_10:17 PM_

(As it’s essentially creating new decorators of the CommandExecutor)
I’d also like to ensure that things like the relative locators and script pinning work as advertised, but that’s a relatively slim tranche of work
My next hacking days are Monday and Tuesday next week

_Diego Molina  10:18 PM_

here is a list from the previous status meeting:
Client facing changes:
* Make relative locators return elements sorted by proximity
* Fix Java Module System problems
* Allow locator strategies to be pluggable
Enable fallbacks for commands
* Server changes:
* Enable retries of commands where necessary using failsafe
* Get the Grid UI looking nice, and returning useful data
* Allow locator strategies to be pluggable
* Pipe VNC connectors through the websocket plumbing for live video

_10:19 PM_

I think this is (partly?) done “Allow locator strategies to be pluggable”
and I remember @simonstewart did this “Make relative locators return elements sorted by proximity”

_Simon Stewart  10:19 PM_

I did
The Java Module System thing is a good point, but not a huge amount of work

_10:20 PM_

Though it’s a little fiddly

_Titus Fortner  10:20 PM_

is the "pluggable" part a Java specific thing, or something new that the server is going to support that opens up new opportunities for dynamic languages?

_David Burns  10:20 PM_

@Puja Jagani could probably look at the Java Module work

_Puja Jagani_

Sure! I can take that up

_Simon Stewart  10:20 PM_

I can discuss the changes with her

_10:21 PM_

@titusfortner the remote end bits of the pluggable locators allow us to avoid sending large payloads across the wire for (eg) finding by react
But it requires the local end to know in advance that it can use that mechanism
Tying the pieces together with a fallback allows the local end to try the efficient way first and then fallback to the inefficient way

_10:22 PM_

It shouldn’t be a particularly challenging thing to do once the pieces are all nicely lined up, but getting the pieces lined up has taken time :slightly_smiling_face:

_Diego Molina  10:24 PM_

I am slowly getting more time to work on some open issues and getting some items from that list on the server done, like using failsafe for retries

_Puja Jagani_

I created a PR to get started on that front for transient errors, please have a look when time permits. I think that might serve as the starting point.

_Diego Molina_

perfect, will do

_Simon Stewart  10:24 PM_

I think the BrowserStack folks (particularly @rajendra?) are hacking on the Grid UI and can give an update on that
I’m happy to punt the failsafe stuff for the beta, but it really should be in place for the 4.0 release

_Diego Molina  10:24 PM_

I saw one or two GH issues related to that, if I am faster than @rajendra I can tackle it and get familiar with the UI
I would leave this one out as well
Pipe VNC connectors through the websocket plumbing for live video

_10:25 PM_
for beta 1
hopefully it could be there for the 4.0 release

_Simon Stewart  10:25 PM_

I think that’s just a case of adding the video URL to the se:options in the server
Should be in the same place as we add the bit for cdp

_David Burns  10:26 PM_

I had a quick look at the grid-ui in a stream... my CSS sucksbut happy to pair up and fix

_Diego Molina  10:26 PM_

ah, so the scope is way smaller than what I was thinking

_Simon Stewart  10:26 PM_

The WebSocket forwarding either works or it doesn’t. If it doesn’t, CDP over the Grid is broken
And I already got the CDP over Grid working :slightly_smiling_face:

_David Burns  10:26 PM_

@Puja Jagani is looking at adding a few more issues with graphql that we can surface

_Diego Molina  10:27 PM_

so we offer the WebSocket endpoint and the user can use it to stream

_Simon Stewart  10:27 PM_

Can we “drain” using GraphQL yet?

_David Burns_

I dont think so

_David Burns_

we can get the grid ui to call a normal url

_Simon Stewart_

The idea would be to use GraphQL for the entire UI….

_David Burns_

We can add it "soon" but since it works we don't need to block a beta on it. Let's have a stretch for it

_Simon Stewart_

But it doesn’t work if you have a distributed grid

_Simon Stewart_

It’s one of the “tracer bullets” of the graphql stuff, and really does need to be in

_Simon Stewart_

I want the beta as much as anyone, but there are some corners we should avoid cutting

_Simon Stewart  10:27 PM_

@diemol it’s a tiny bit more than that, but, yeah

_Diego Molina  10:27 PM_

I thought we wanted to put that in the Grid UI

_Simon Stewart  10:27 PM_

We do before 4.0

_Diego Molina  10:27 PM_

we would need to embed a VNC client in the UI for that

_Simon Stewart  10:27 PM_

But for the alpha, I’m happy with the endpoint being exposed and being able to wire up a vnc viewer locally that goes through the grid
@diemol what did zalenium use?

_Diego Molina  10:28 PM_

noVNC, but that was something that you need to install on the host, IIRC

_10:29 PM_

ah, I think it also has a JS library, it “should” work
there is also an alternative called “Guacamole”

_Simon Stewart  10:29 PM_

http://guacamole.apache.org might be useful
Ha!

_Diego Molina  10:30 PM_

right, so I can look at that at some point

_Simon Stewart  10:30 PM_

Awesome. Thank you

_Diego Molina  10:30 PM_

coming back to pluggable locators, it is working in Java
but we would need the other bindings to implement it, right?

_Simon Stewart  10:31 PM_

When it’s working in Java, yes :slightly_smiling_face:
But I think it’s enough to get them in one binding for the beta, and the others can follow

_Diego Molina  10:31 PM_

Also, the reworked logic for relative locators has been implemented only in Java?

_Simon Stewart  10:32 PM_

The reworked logic was in the atom.
Everyone has it
Yay for the atoms!

_10:32_

:slightly_smiling_face:

_Diego Molina  10:32 PM_

That’s great!
Do we have anything else?
I will (for sure) this time create GitHub issues for the different items

_Simon Stewart  10:34 PM_

And add them to the project plan, please!

_Diego Molina  10:34 PM_

absolutely

_Simon Stewart  10:35 PM_

Thank you

_Diego Molina  10:36 PM_

Well, I think we do not have other topics, should we cut it short?
New

_Simon Stewart  10:38 PM_

I’m down with that. Thanking for running this!
Unless @jimevans has something to add? I see he’s typing

_Jim Evans  10:38 PM_

re pluggable locators: that’s just having findElement(s) call down the wire using the end point without validating the using and value params, yeah?

_Simon Stewart  10:39 PM_

That’s what the java code does at the moment

_Jim Evans  10:39 PM_

cool. .NET can do that too right now, i think (will have to confirm)

_Simon Stewart  10:39 PM_

I want to land a few more smarts

_David Burns  10:39 PM_

I expect the python and ruby code can do it to since monkey patching is easy
and then add smarts too
improve docs ftw

_Diego Molina  10:43 PM_

ok, seems we are done with the status meeting, thank you everyone!

_Jim Evans  10:45 PM_

re beta readiness: i have a refactor to .NET i want to do to make CDP via RemoteWebDriver easier/more correct, but it can wait until post-beta1

_10:45_

otherwise, i think .NET is ready for beta.
