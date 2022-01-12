---
title: "Public Project Meeting - June 04, 2020"
linkTitle: "Public Project Meeting - June 04, 2020"
date: 2020-06-04
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
timeline of the meeting held on June 04, 2020 (times are on IST). 

Meetings are held on the `#selenium-tlc` channel on [Selenium Slack](https://seleniumhq.slack.com/join/shared_invite/enQtODAwOTUzOTM5OTEwLTZjZjgzN2ExOTBmZGE0NjkwYzA2Nzc0MjczMGYwYjdiNGQ5YjI0ZjdjYjFhMjVlMjFkZWJmNDYyMmU1OTYyM2Y).

The next meeting will be on June 18, 2020, 4:30 PM CET.

---   

_Diego Molina  8:05 PM_

the agenda will be:
* anything people want to add in the next 2 minutes
* me looking to previous agendas and adding those items here

_Simon Stewart  8:06 PM_

Hahaha

Agenda item: path to betas

8:07
I’d like to hear about the work that @AutomatedTester has in mind for his interns, and how (as a project) we’ll support them

_Diego Molina  8:08 PM_

Agenda:
* general project statistics
* any overall announcements? new people on committees, have the commit bit, etc?
* so do we want/need to wait for that work to be done before doing Alpha 7?
* Work to be done before beta? (Path to betas)
* when is the next SFC call? (if its happened in the last two weeks, anything to report)
* Did anyone pick up the ball to talk to Miki about the go bindings?
* SeConf India SeConf Chicago
* Selenium Conf website in GitHub org?
* IDE to main repo?
* CoC

Let's start

8:09
general project statistics:
two weeks ago: 391 open issues, open pull request: 68
now: 343 open issues, open pull request: 72

8:10
looking better, let's keep the pace!
1



next item:;
any overall announcements? new people on committees, have the commit bit, etc?

_Simon Stewart  8:11 PM_

Just a quick note that @Bongosway has landed a few patches recently. I’m hoping that we find a way to help him into the fold a little more

And a high five to @rajendra for his work on the JDBC stuff
1 reply
4 days ago
View thread


_Diego Molina  8:12 PM_

absolutely, thanks for contributing!

anything else or should we move to the next item in the agenda?

8:13
ok, next item:
what do we need to be done before doing Alpha 7?

_Simon Stewart  8:15 PM_

Things I’d like to see:

* Switch to the reactor client for http
* Land a basic replacement for the UI
* Straw-man proposals for the functionality that might rely on CDP

Of which that last is in my court

_David Burns  8:16 PM_

1) I am working on

as for my interns, one of which in here so say hi to @Saksham Gupta

_Simon Stewart  8:17 PM_

waves to @Saksham Gupta!
1



_David Burns  8:17 PM_

They will be extending the GraphQL system with in grid (@Saksham Gupta )

and Phani, when he joins, will be building out the Grid UI and modernising it

_Diego Molina  8:18 PM_

that sounds great!

_Marcus Merrell  8:18 PM_

welcome, @Saksham Gupta!
1



_David Burns  8:18 PM_

The goal of the work is to add most, if not all, the features we want in this area

_8:19_

both @Saksham Gupta and Phani will be supported by me and @rajendra

_8:20_

and next week we have another member joining our team who we can get to support both interns to make sure we don't have any performance hits with the work they add and guide them through perf profiling. etc

_Simon Stewart  8:20 PM_

@Saksham Gupta you might find this useful: https://docs.google.com/document/d/1aFMmmMhBg1PgfTPmyaXaEJAhsh0lf3p0VpV7kfHIstI/edit

_Diego Molina  8:20 PM_

anything else pending from the non-Java stuff?

I know CDP is not working with JS yet, not sure about Python

_David Burns  8:20 PM_

Non-java we need to add CDP to python and JS which my team will be doing

_Simon Stewart  8:21 PM_

One thing i’m keen on is steering users away from raw CDP

_David Burns  8:21 PM_

agreed

_Simon Stewart  8:21 PM_

When we get WebDriver BiDi in place, we’d like to use that where possible

CDP is ugly, hard to use, and deeply fragile

_Diego Molina  8:22 PM_

that is a good point, because we don't want GitHub issues reporting bugs merely due to something failing in CDP

_Simon Stewart  8:23 PM_

Something will fail with the CDP stuff

I may have to version it independently of the rest of the java pieces.

_Diego Molina  8:23 PM_

is the idea to have CDP in JS and Python for Alpha 7?

_Marcus Merrell  8:23 PM_

has the chromium team talked at all about CDP becoming a standard, or publishing a roadmap? (I'm off to put this exact question into google)

_Simon Stewart  8:24 PM_

They’re involved with the webdriver bidi stuff

I think there’s a lot of value separating “automation” from “debugging APIs”

_Marcus Merrell  8:24 PM_

yeah

_David Burns  8:24 PM_

the spec is being worked on right now

I have been reviewing it this week

_Marcus Merrell  8:24 PM_

I saw the transcript from last week, good stuff

_David Burns  8:25 PM_

CDP is not going to be the standard

_Diego Molina  8:25 PM_

asking again to stay in the topic, is the idea to have CDP in JS and Python for Alpha 7?

_Simon Stewart  8:25 PM_

Yes

_David Burns  8:25 PM_

@diemol maybe.. hopefully

_Simon Stewart  8:25 PM_

We need all languages to have that in place before we can move to betas

_Diego Molina  8:27 PM_

I have seen a few PRs from https://github.com/seanpoulter, he seems keen to help in JS
he already asked how he can implement relative locators in JS
we should try to give him quick feedback

Sean Poulter
Location
Ottawa, Canada
Repositories
22
Followers
17
@seanpoulter | Oct 18th, 2012 | Added by GitHub


_Simon Stewart  8:28 PM_

@harsha509 would you like to help with that? Or @corevo?

And


_David Burns  8:28 PM_

JS relative locators is in there, might need some TLC

_Sri Harsha  8:28 PM_

i need a week to hack into js things. currently busy in paid work 

_Simon Stewart  8:28 PM_

NP.

8:29
These paid jobs are what allow us to have the luxury of working on OSS. Focusing on them makes a lot of sense 


_Diego Molina  8:29 PM_

in any case, let's try to give feedback, we are in the look for JS maintainers and who knows if he could be "the one" 


ok, next item?

_Simon Stewart  8:30 PM_

Have we finished the beta discussion?

I don’t think we’ve got more items to add to it

_Diego Molina  8:31 PM_

no, that is next

Work to be done before beta? (Path to betas)

_Simon Stewart  8:31 PM_

The things for alpha-7 are the blockers for beta

8:32
After that, I think the changes are incremental rather than entirely new features

8:33
(other than (maybe) pluggable locators0

_Marcus Merrell  8:33 PM_

also my "drain" feature

I'm negotiating a couple days with my paid work to get back on that

_Simon Stewart  8:33 PM_

That’d be nice to get into a7 

I suspect that the graphql stuff will cause some changes to have to happen

_Marcus Merrell  8:33 PM_

can we set up a time to pair for an hour or so?

_Sri Harsha  8:33 PM_

I will take over maintaining JS bindings for sure in future 

_Simon Stewart  8:33 PM_

@mmerrell sure

8:34
I’m around monday and tuesday next week

@harsha509 WOOHOO!

That’d be awesome

_Marcus Merrell  8:34 PM_

perfect--I've just blocked out most of the day for it

I know what I need to do, but would like a bit of validation on the approach, and then I need to test it

_Diego Molina  8:36 PM_

ok, seems the betas part has been mostly discussed in the Alpha 7 part and these previous lines

if everyone is ok with it, next topic
2



when is the next SFC call? (if its happened in the last two weeks, anything to report)

_Marcus Merrell  8:36 PM_

it happened Tuesday, and I believe we primarily discussed the conference

_Diego Molina  8:38 PM_

ah perfect, there is an item in the agenda for "SeConf India SeConf Chicago"

_Simon Stewart  8:38 PM_

@manoj9788 will know the most about that

_Marcus Merrell  8:40 PM_

yep

_Diego Molina  8:40 PM_

is there something else to comment from SFC or conferences? or should we move to the next item?

_Marcus Merrell  8:40 PM_

I can speak for Chicago

I don't think we talked about anything else... the SFC wasn't present on the call

_Diego Molina  8:41 PM_

would you like to say something about the Chicago conference?'

_Marcus Merrell  8:41 PM_

yeah--we're negotiating with the hotel to take our contract and move it to 2021, around the same time of year

_8:42_

we're waiting to hear back from them with a selection of dates in the Sep-Oct-Nov range

hoping not to have it the week before Thanksgiving, but also hoping to keep the room rates low

_8:43_

as to this year, we're talking about holding a very sane, relatively small virtual conference, or a series of webinars spread over a couple months

we're also discussing having another group help us out with planning and execution, someone with experience at promoting and running virtual conferences

_8:44_

the first discussion will happen today, and if we decide to explore further, I will probably feel more comfortable talking about who we're considering 


there is a lot to consider for a virtual conference, and we want to make sure we are thoughtful and careful

_Rajendra Kadam  8:44 PM_

replied to a thread:
And a high five to @rajendra for his work on the JDBC stuff
Thanks @simonstewart. 
. It wouldn't have been complete without your help. 
1



_Diego Molina  8:44 PM_

all that is already a lot of info, thanks for sharing!

_Marcus Merrell  8:45 PM_

for SeConf India, I don't know if @manoj9788 is on and would like to share. If not, I can at least say a little about what they're considering, I just don't want it to be incomplete

_Diego Molina  8:46 PM_

up to you I would say 

_Marcus Merrell  8:48 PM_

it appears that there's no way they're going to be able to put on a conference in September in Bangalore. The borders and COVID caseload aren't adequately under control, and much like the US conference, there are too many variables that would need to be under control right now for Naresh to feel confident about having an in-person conference a scant 3 months from now
1



(by "borders" I mean "international travel & quarantine procedures")

_Diego Molina  8:48 PM_

that makes a lot of sense

_Marcus Merrell  8:50 PM_

so we are discussing whether or not we can essentially combine the notion of a virtual conference. That troubles me because I know that all parties involved want to see revenue, but the Selenium project doesn't want to charge for admission. I'm concerned there would be too many hands in the pot for any one of them to receive enough $$ to make it worth their while

but that's my own concern--not meant to represent the opinion of the planning committee. We're looking for data, not opinions--so we're reaching out to people who have been through this in recent weeks to find out what's possible

8:51
we hope to report in a couple weeks on any progress, both with planning the virtual conference, and with whether or not we can expect to fund our coffers in the near future

_Diego Molina  8:51 PM_

ok, then we can follow up in the next meeting, thanks!
1



8:52
next topic: Did anyone pick up the ball to talk to Miki about the go bindings?

_Simon Stewart  8:52 PM_

Not me

_Diego Molina  8:52 PM_

I think no one did but we should keep the topic around, and try to get to it when we have Se 4 released or close to be released

8:53
adding one more binding now would be too distracting
which does not mean that we cannot reach out to him

_Simon Stewart  8:54 PM_

Agreed

_Diego Molina  8:55 PM_

ok, let's move to the next one since we only have a few minutes left

Selenium Conf website in GitHub org?

I see the site was revamped, so great job @andrewmkrug

8:56
but I am not sure if @andrewmkrug is having problems to push the code to the org
I can reach out to him

_Simon Stewart  8:56 PM_

Thank you
1



_Diego Molina  8:56 PM_

next topic: IDE to main repo?

_8:57_

I guess we should focus more on the bindings and the Grid for now?

_Simon Stewart  8:58 PM_

I think @AutomatedTester has already done a chunk of the heavy lifting

_9:00_

Got to go. Meeting time

_Diego Molina  9:00 PM_

last topic was CoC, but we can take it next time

well, we covered everything (almost), thank you everyone!
1

_Marcus Merrell  9:01 PM_

thank YOU, @diemol
1
