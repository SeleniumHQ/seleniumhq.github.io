---
title: "Public Project Meeting - April 23, 2020"
linkTitle: "Public Project Meeting - April 23, 2020"
date: 2020-04-24
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
timeline of the meeting held on April 23, 2020 (times are on CET). 

Meetings are held on the `#selenium-tlc` channel on [Selenium Slack](https://seleniumhq.slack.com/join/shared_invite/enQtODAwOTUzOTM5OTEwLTZjZjgzN2ExOTBmZGE0NjkwYzA2Nzc0MjczMGYwYjdiNGQ5YjI0ZjdjYjFhMjVlMjFkZWJmNDYyMmU1OTYyM2Y).

The next meeting will be on May 7, 2020, 4:30 PM CET.

---   

_Diego Molina  4:32 PM_

Hi all, we are going to start the next project status meeting in a moment

for now, the agenda would be:

- project stats: +/- issues, prs
- any staffing announcements
- whats been worked on (seems things are picking up again)
- whats next
- when do we plan the next release
- SeleniumConf status (for India and Chicago), and its website

_Simon Stewart  4:32 PM_

Does someone want to announce it on the main #selenium channel too?

_Diego Molina  4:33 PM_

I can do that

_Simon Stewart  4:33 PM_

Thanks

_Diego Molina  4:34 PM_

before we start, does anyone want to add a topic?

_4:36_

maybe we can also discuss the Go bindings again

but I will take that silence as a "no more topics"

First topic, project stats: +/- issues, prs...

_Simon Stewart  4:37 PM_

@AutomatedTester probably knows those

_Diego Molina  4:37 PM_

Since a few weeks we are under 65 PRs and 400 issues, we made progress but not as significant as in the previous weeks

I am looking at a couple of OSS projects that build a dashboard so we have a better overview

_Simon Stewart  4:38 PM_

It’s the law of diminishing returns. We’re starting to hit actual issues and things that need thinking about

_Diego Molina  4:41 PM_

I experimented during the weekend with https://chaoss.github.io/grimoirelab/ and https://github.com/cncf/devstats, I liked the first one more, but we need to find a place where to deploy it for free
Recently I bumped into https://github.com/google/triage-party, which could be also a good one to have an overview (edited) 

_4:42_

all this in the spirit of having a clear project status for everyone

I can try to have something working for the next meeting, does anyone have more comments or should we move to the next topic?

_Simon Stewart  4:42 PM_

No comments from me

_Diego Molina  4:43 PM_

next topic: any staffing announcements, what did you mean by this @adamgoucher?

_Simon Stewart  4:43 PM_

@harsha509 has joined the team :slightly_smiling_face:

_adamgoucher  4:43 PM_

anyone get the commit bit, step away from a committee, etc.

_Diego Molina  4:44 PM_

well, @tourdedave mentioned he does not have the bandwidth to be in the TLC (which is my fault because I added him without asking, in the spirit of having someone from the IDE)

_4:45_

we could just leave it as it is or ask around if someone is interested?

_Simon Stewart  4:45 PM_

Having a strong owner for the IDE would be really helpful

_Marcus Merrell  4:47 PM_

I'm afraid unless he or Tomer change their mind, I'm not sure anyone exists who could fit the bill

we maintain really strong contacts with Dave.. that might have to be good enough for now

_Diego Molina  4:48 PM_

the TLC has no size limit, so things could continue as they are, right?

_Simon Stewart  4:49 PM_

They could

And it sounds a bit unfair to continue to lean on @tourdedave after he’s stepped away

_Diego Molina  4:51 PM_

what the IDE needs for now is support from the PLC, since Microsoft wants to help with having the extension on Edge (new), but I think @AutomatedTester asked to be introduced to them, not sure what the status is there

support as in, sort out legal stuff for creating an account on the MS store

_Simon Stewart  4:52 PM_

That won’t be hard. Someone needs to message the selenium@conservancy.org

_Tomer Steinfeld  4:52 PM_

I introduced @AutomatedTester I think the next step is to have someone from the SFC create an account

To do that we need PLC stamp

So that the SFC will do it

_Simon Stewart  4:52 PM_

@mmerrell want to run with that, or shall we ask @AutomatedTester to do it?

_Titus Fortner  4:52 PM_

What does that give the project though, if it isn't the electron implementation?

_Marcus Merrell  4:52 PM_

I can run with it

_Simon Stewart  4:52 PM_

I’m happy with @AutomatedTester on point here :slightly_smiling_face:

_Titus Fortner  4:52 PM_

Not saying we should ever turn down help

Just curious

_Marcus Merrell  4:53 PM_

He might have better contacts than me

_Simon Stewart  4:53 PM_

@titusfortner support on Edge, and maybe someone from MS will step into the gap

_Diego Molina  4:53 PM_

to have the IDE working on the new Edge

_Simon Stewart  4:53 PM_

And also, and this is important, IDE working on Edge (as @diemol says)

_Titus Fortner  4:55 PM_

I thought all chromium apps worked already. I should actually use it :)

_Diego Molina  4:55 PM_

could we leave as an action item to have @mmerrell (since he is in the PLC) chat with @AutomatedTester in case David needs help with the PLC? and have a status for the next meeting?

_Simon Stewart  4:56 PM_

Sure

_Diego Molina  4:57 PM_

you ok with that @mmerrell?

while he answers, should we move slowly to the next topic?

_Marcus Merrell  5:00 PM_

yeah, I'm ok with that

_Diego Molina  5:00 PM_

next topic, whats been worked on (seems things are picking up again)

_Simon Stewart  5:00 PM_

I’ve some diffs lined up

_5:01_

- Streamlining how we manage cli flags and configuration, so that things included on the ClassPath get picked up too
- WebSocket proxying
- A patch that I started at SeConf (I know) to get relative locators working a little more nicely
- I also have experimental support for pinning browser versions (and their drivers) in the bazel build, which resolves some issues that folks may have

When I get some bandwidth, landing those would be nice

After that, I’ll dig into the .Net stuff now that @jimevans has it working on macOS and see whether there’s some jiggery-pokery with select we can do so that bazel test //… will work the way we expect it to

Oh, I’ve also started wrapping OpenTelemetry with our own APIs so we do an update without too much chaos

0.2.0 -> 0.3.0 is messy

_Diego Molina  5:04 PM_

From my side:

- Docker images working with the Grid 4 Alpha 5, only supporting standalone and hub/node for now
- Working on adding support for retries in the Grid, issue #8167
- Also, added tags and categories to the blog in selenium.dev, so now all governance related meetings can be seen at https://www.selenium.dev/categories/governance/

_Marcus Merrell  5:04 PM_

ditto me for the "quiesce" feature, in terms of "working since SeConf" and "when I get some bandwidth"

_Jim Evans  5:05 PM_

@simonstewart let me know what you’d like to see here in terms of .NET.

_Marcus Merrell  5:05 PM_

the bulk of it is complete, still do to:

actually spin down the nodes

_5:05_

write unit tests

_Simon Stewart  5:06 PM_

I’ve still been pondering on how we make it possible to spin the grid up in an ad-hoc order

And I keep coming back to the fact that life would be easier if we had a backbone of a “message bus”, a “key/value store”, and a “service registry”

We definitely have the message bus already

And the session-map is perilously close to being a key/value store

_Diego Molina  5:07 PM_

conceptually I wanted to cover that in the support for retries issue, because we cannot expect the docker containers to start always in the same order

_Simon Stewart  5:07 PM_

Right

Regularly has support for a successful retry and an unsuccessful retry

But we can’t build everything on that :slightly_smiling_face:

_Diego Molina  5:08 PM_

I want to tackle that first, so at some point containers can be used in other envs

between https://jodah.net/failsafe/fallback/ and https://github.com/resilience4j/resilience4j I am liking more the first one (edited) 

_Simon Stewart  5:08 PM_

You’re writing the code, and I like both of them :slightly_smiling_face:

_5:09_

Fallback is also used by an alternative netty-based http client

We may need to switch from AsynHttpClient because it doesn’t support http2 (or unix domain sockets)

_5:10_

I’ve been looking at io.projectreactor.netty:reactor-netty:0.9.6.RELEASE and I think @barancev may have some code

Or he may not :slightly_smiling_face:

_Diego Molina  5:10 PM_

I guess we can come back to that when some written code from my side actually works :slightly_smiling_face:

_Simon Stewart  5:11 PM_

Well, it means that looking at failsafe fallback is a better bet

Which leans into your preference

So it’s more a datapoint than anything else

_Diego Molina  5:12 PM_

sounds good

ok, should we move to the next topic, so we can hopefully finish on time

_Simon Stewart  5:12 PM_

SGTM

_Diego Molina  5:12 PM_

next topic is, what's next & when do we plan the next release

_Simon Stewart  5:12 PM_

Next release? I’d like a banner feature in. WebSockets and CDP across the Grid would be it, for me

_5:13_

Or we can just ship what we have

_Marcus Merrell  5:13 PM_

that would be amazing

_Simon Stewart  5:13 PM_

It’s a SMOP

Simple Matter of Programming

_Diego Molina  5:13 PM_

we have 267 commits after the alpha 5 release, sounds like there could be something we can release?

_Simon Stewart  5:13 PM_

So I think the choice we have to make is “timed releases” or “feature releases”

_Marcus Merrell  5:14 PM_

I thought SMOP might be "Simple Matter of Pfantasy"

_Simon Stewart  5:14 PM_

`git log selenium-4.0.0-alpha-5..HEAD --oneline java`

_5:15_

HA! 098eb99991

_5:15_

We landed support for configs via TOML….

_Diego Molina  5:16 PM_

true, what if we try to have the retries thing, docs for toml, and maybe have a release before the next status meeting?

_Simon Stewart  5:16 PM_

Looks like lots of little fixes

DOCS?

:wink:

I can write an info section for configuration

_Diego Molina  5:17 PM_

that is what I was thinking

_Simon Stewart  5:17 PM_

kk

File an issue and assign it to me

(At some point, I need to do my day job)

_Diego Molina  5:17 PM_

before the beta some of us need to make those docs a bit nicer for Se4

_5:18_

I will do that then

_Simon Stewart  5:18 PM_

Thank you

Shall we mark some issues as “4.0a6”?

_Diego Molina  5:19 PM_

Maybe it is too much, not sure

_Marcus Merrell  5:19 PM_

my offer still stands to help with issue hygiene, I just need permissions

_Simon Stewart  5:19 PM_

Or, perhaps, let’s work on our features, and next meeting ship 4.0a6 regardless of where we are

@mmerrell I’ll fix that up for you now

And @harsha509 too

_Sri Harsha  5:20 PM_
Js bindings is already at alpha7. We can try to implement relative locators(which is pending in JS bindings) and release in next meeting.

_Simon Stewart  5:21 PM_

That’d be great

Thank you

_Diego Molina  5:21 PM_

issue for TOML https://github.com/SeleniumHQ/selenium/issues/8232

_Simon Stewart  5:22 PM_

Thank you

_Diego Molina  5:22 PM_

ok, two topics left

first is, SeleniumConf status (for India and Chicago), and its website

_Marcus Merrell  5:23 PM_

I'll see if I can get a website update from Bill McGee now that it's a bit later on the west coast

_Diego Molina  5:23 PM_

so India will happen (:crossed_fingers: ) in September, and Chicago in November, does anyone know any news related to that?

_Simon Stewart  5:24 PM_

@mmerrell you should have an invitation from GH to join the SeleniumHQ org. Am slightly surprised you’re not a member already :slightly_smiling_face:

_Marcus Merrell  5:26 PM_

the conference status is currently:

- We're waiting until around May 15th to make a decision, but
- While we believe the conference could happen in November, it's Bill McGee's observation that people right now just don't seem to be interested in buying tickets, submitting papers, or writing checks to sponsor in-person conferences right now
- Given that we're having trouble motivating people to take the actions they need to take now (the previous point), it's hard to imagine how we can feel confident in a November conference in the near future
- What I'm saying is that, things in the community need to happen in May in order to hold a conference in November, and I don't have even the barest hint that those things will happen

so, at this point we believe we will be able to recover most/all funds from the Holiday Inn, based on Sauce's experience with the Fairmont in Austin, and TestBash's experience in Detroit

_5:27_

but we're going to wait a few more weeks, a) because we can, and b) in case there's some kind of earth-shattering news that comes out that changes the picture

_Simon Stewart  5:27 PM_

The UK has been told that social distancing will remain necessary until next year

_Alexei Barantsev  5:28 PM_

@simonstewart I don't have any new code for http client yet, just an item on my todo list

_Marcus Merrell  5:28 PM_

as you might have seen, our leadership much more optimistic :face_palm:

_Simon Stewart  5:28 PM_

@barancev ok. There’s no rush

_Diego Molina  5:29 PM_

that's a great update @mmerrell, thanks

something else I wanted to ask is, if we could have at some point the SeleniumConf website code and assets also under the SeleniumHQ GitHub org

_Simon Stewart  5:29 PM_

Totes up for that

5:30
_I’ve another meeting to go to_

_Marcus Merrell  5:30 PM_

me too. that's where things get sensitive

_Simon Stewart  5:30 PM_

Toodle pip

_Marcus Merrell  5:30 PM_

but I think we need to head in that direction

_Diego Molina  5:30 PM_

and as part of this, add the general conference guidelines to the governance doc

_Marcus Merrell  5:30 PM_

yep, that's on my plate

_Diego Molina  5:30 PM_

that's fine, we can stop here as we have no more time

_5:31_

but we can keep the topic in our head for informal discussions

_Simon Stewart  5:31 PM_

You can carry on without me. I’m fine with that :slightly_smiling_face:

_Diego Molina  5:31 PM_

and have a status of it in the next meeting

_5:32_

to respect people's time, we will end the meeting here, I will upload the minutes to selenium.dev, and prepare an agenda for the next one

_5:32_

thank you everyone!
