---
title: "Public Project Meeting - July 02, 2020"
linkTitle: "TLC - July 02, 2020"
date: 2020-07-02
---


Continuing the series of bi-weekly public project meetings, here is the
timeline of the meeting held on July 02, 2020 (times are on IST). 

Meetings are held on the `#selenium-tlc` channel on [Selenium Slack](https://seleniumhq.slack.com/join/shared_invite/enQtODAwOTUzOTM5OTEwLTZjZjgzN2ExOTBmZGE0NjkwYzA2Nzc0MjczMGYwYjdiNGQ5YjI0ZjdjYjFhMjVlMjFkZWJmNDYyMmU1OTYyM2Y).

The next meeting will be on July 16, 2020, 4:30 PM CET.

---   

_Diego Molina  8:00 PM_

Hi all, who is in for the Public Project meeting?

_8:01 PM_

* Here is the overall agenda:
* Overall announcements? New people on committees, with the commit bit, etc?
* Anything new to report from the last PLC/SFC call?
* Did anyone talk to Miki about the go bindings?
* This topic has been skipped in the previous meetings but it is good to keep it on the agenda so we act on it when we have more bandwidth
* Pending work that needs to be done for Alpha 7?
* Pending work that needs to be done for the first Beta?
* Conferences. Status for SeConf India & SeConf Chicago?
* Selenium Conf website in GitHub org?
* IDE to the main repo?
* CoC, next steps?
* Selenium Branding guide
* Selenium marketing strategy (e.g: Webpack)
(if anyone wants to add something, feel free to do it)

_8:02 PM_

First, some project health:
General project statistics
Previous meeting: 343 open issues, 72 open PRs
Now: 338 open issues, 73 open PRs

_David Burns  8:03 PM_

looks like healthy movement as I know I've handled a few issues and PRs personally

_Diego Molina  8:03 PM_

Next topic:
Overall announcements? New people on committees, with the commit bit, etc?

_Simon Stewart  8:04 PM_

WebDriver BiDi meeting was yesterday. There’s some spec prose

_David Burns  8:04 PM_

@tourdedave is stepping back from the tlc, for a while at least unless I have misunderstood

_Manoj Kumar Kumar  8:05 PM_

@AutomatedTester stepping into TLC

_Simon Stewart  8:05 PM_

I thought he did that a while ago, but we failed to update everything

_David Burns  8:05 PM_

its more official :)

_Diego Molina  8:05 PM_

do we want to remove him from the list explicitly? does it make a difference?

_Simon Stewart  8:05 PM_

And @AutomatedTester should have been in the TLC from the start :man-facepalming::skin-tone-2:

“The list”? On the governance part of the site?

Yes

_Diego Molina  8:05 PM_

I thought @AutomatedTester was there, it just my mistake :slightly_smiling_face:

_Simon Stewart  8:06 PM_

Unofficial draft of the bidi spec: https://w3c.github.io/webdriver-bidi/ There’s a way to go yet

_Diego Molina  8:06 PM_

ok, I will create a PR to do that (removing @tourdedave)

_David Burns  8:06 PM_

As retribution for not being on the TLC officially you all owe me cake
:cake:

_8:06_
:P
as for the webdriver bidi, as @simonstewart said, there is prose.

_8:07_

yesterday's meeting notes are in https://www.w3.org/2020/07/01-webdriver-minutes.html
The spec is being driven by Mozilla and Google mostly atm

_Diego Molina  8:08 PM_

thanks for the updates
next topic?
Anything new to report from the last PLC/SFC call?

_Luke Hill  8:08 PM_

I'm just being nosy, nothing to see here.

_Manoj Kumar Kumar  8:09 PM_

Nothing specific from the last PLC/SFC call. We've caught up on conference updates, which we will cover shortly.

_Diego Molina  8:10 PM_

ok, sounds good
next topic is:
Did anyone talk to Miki about the go bindings?
- This topic has been skipped in the previous meetings 
but it is good to keep it on the agenda so we act on it 
when we have more bandwidth there is no need to 
comment or discuss if no one has updates though

_Simon Stewart  8:10 PM_

Nothing from me

_Diego Molina  8:11 PM_

ok
the next topic is:
Pending work that needs to be done for Alpha 7?

_Simon Stewart  8:11 PM_

I’d really like us to get the new UI in place for alpha 7
Even if it’s just a skeleton

_David Burns  8:12 PM_

That is in flight now, hope to get a demo on Monday and see 
what needs to be improved before we move into the repo
:tada:

_Simon Stewart  8:12 PM_

Great news :slightly_smiling_face:
Lemme check the project plan

_Diego Molina  8:12 PM_

I have seen CDP in Python and JS are almost there

_David Burns  8:12 PM_

we already have some of the parts required 
in master and the rest are being worked on
CDP in python has landed

_Simon Stewart  8:13 PM_

https://github.com/SeleniumHQ/selenium/projects/2

_Manoj Kumar Kumar  8:13 PM_

And GraphQL is nearing completion, I guess.

_Simon Stewart  8:13 PM_

Things on my list are the new features based on CDP usage
Pluggable locators

_8:14_

And CDP/WebDriver interop (mostly at the element and window level)
Those will let us call alpha 7 the final alpha
:tada:
And we can make a start on the betas as we’ll be feature complete
IMO
Anything else that folks can think of?

_Diego Molina  8:15 PM_

nothing else from my side

_David Burns  8:15 PM_

we do have the beta list at https://github.com/SeleniumHQ/selenium/issues?q=is%3Aopen+is%3Aissue+label%3ASelenium-4-Beta

_8:16_

do we need to move more things there?

_Simon Stewart  8:16 PM_

Are those “bugs to fix in the betas” or “bugs to fix before we can cut a beta”?

_David Burns  8:16 PM_
or remove
yes

_Simon Stewart  8:16 PM_

Logically correct, but not an actual answer :slightly_smiling_face:

_David Burns  8:16 PM_

it's a list of things @diemol thought we needed and I mostly agree

_Diego Molina  8:17 PM_

they come from the Google Doc we went through in London in February with Simon

_Simon Stewart  8:17 PM_

I shall ruthlessly reprioritise
Those are things that we mostly need in place before release

_8:18_

But I think we can go into the betas with “the features are there, but they’re known to be unstable”
The alphas are “the features aren’t even there yet”

_Diego Molina  8:18 PM_

I would expect to have a few things being unstable in the betas

_Simon Stewart  8:19 PM_

“A few things” will definitely be unstable

_David Burns  8:19 PM_

I would like to get things that may have got sorted but 
the bug list is out of date sorted as well from our lists

_Simon Stewart  8:20 PM_

That list seems mostly up to date

_David Burns  8:20 PM_

but things like Ruby on bazel and ide in the 
mono repo I dont think are Se4 dependent

_Simon Stewart  8:20 PM_

I don’t see things that are already fixed in it

_David Burns  8:20 PM_

my comment was general so people checked :D

_Diego Molina  8:20 PM_

indeed, I usually keep an eye on the issues, and I believe is up to date
btw, we are already covering the next topic in the agenda
Pending work that needs to be done for the first Beta?
:+1::skin-tone-4:


_David Burns  8:21 PM_

yes and no...
Can we get PRs in flight finished if they are just waiting on reviews
on fixing review comments I meant

_Simon Stewart  8:22 PM_

Which ones in particular?

_David Burns  8:22 PM_

The draining one and the redis one (not picking on people) come to mind

_Simon Stewart  8:22 PM_

I’m not happy landing the Redis one since it leaves a broken 
RedisBackedDistributor in the tree, and that’s suboptimal

_8:23_

I need to re-review the draining one

_David Burns  8:23 PM_

I am not saying we need to fix it but we need people to finish them off :)

_Simon Stewart  8:23 PM_

Indeed.

_Diego Molina  8:25 PM_

so, is there anything else to add before moving to the next one?

_Manoj Kumar Kumar  8:25 PM_

Selenium IDE work is a separate topic? Unsure if @corevo is here?
I meant on the Electron piece of work.

_David Burns  8:25 PM_

I can field that...

_Manoj Kumar Kumar  8:25 PM_

great!

_David Burns  8:26 PM_

Selenium IDE is now in the Edge store
:tada:
that's the extension version

_8:27_

As for the electron version, that's slightly on hold atm but hopefully we can start that up soon
:+1::skin-tone-4:


_Manoj Kumar Kumar  8:27 PM_

https://microsoftedge.microsoft.com/addons/detail/selenium-ide/ajdpfmkffanmkhejnopjppegokpogffp
Link to MS Edge store

_David Burns  8:27 PM_

I would like it to be a thing as we work on core "fit and finish" pieces

_Diego Molina  8:28 PM_

sounds good
should we move to the next topic?

_Manoj Kumar Kumar  8:28 PM_

yes

_Diego Molina  8:28 PM_

Conferences. Status for SeConf India & SeConf Chicago?

_Manoj Kumar Kumar  8:29 PM_

SeConf Chicago - Postponed to 2021 - we are working on new dates and Hotels.

_8:30_

SeConf India - It was supposed to held on June - we've moved it to September, but it seems 
hard and the Indian borders won't open up for any travels in and out.

_8:31_

Final decision is yet to be made, it will get postponed or we may have a shortened(online event)

Diego Molina  8:32 PM
thanks for the updates :slightly_smiling_face:

_Manoj Kumar Kumar  8:32 PM_

@mmerrell Please fill in, if I've missed anything!

_Diego Molina  8:32 PM_

next topic is:
Selenium Conf website in GitHub org?
@andrewmkrug did a great job in revamping the site weeks ago, and now the 
code is in a private repo under the SeleniumHQ GitHub org he'll add some 
instructions to the README on how to develop on it and deploy it
:clap::skin-tone-4:

_8:34_

the repo is private just to keep information about future 
conferences confidential :slightly_smiling_face:
next topic:
IDE to the main repo?

_David Burns  8:35 PM_

No change on this. I hope to get to it soon, 
I have a branch doing the Bazel work

_Diego Molina  8:35 PM_

ok, sounds good
next topic:
CoC, next steps?

_David Burns  8:36 PM_

Last week @diemol and I met with Sage 
Sharp and had a productive meeting

We have given them a bunch of details and they 
hoped to get back to use end of this week or beginning of next week with a draft

_Diego Molina  8:37 PM_

yeah, it was a great session, 
and I think the CoC is on a good path

_Marcus Merrell  8:38 PM_

The only conference-related note is that we're 
looking at a potential opportunity to host an online-only 
conference sometime in September/October, 
but we don't have details or info on the $$ piece yet

_Diego Molina  8:39 PM_

thanks @mmerrell

_8:40_

should we move to the next topic?

_David Burns  8:40 PM_

regarding online confs I would like people to speak to their 
respective marketing companies and see if it's being over done

_Marcus Merrell  8:40 PM_

that's my #1 concern

_David Burns  8:40 PM_

there are so many atm...

_Marcus Merrell  8:40 PM_

how's Breakpoint looking, btw?

_David Burns  8:41 PM_

It's good so far but I know marketing 
team don't really want anything past that

_Marcus Merrell  8:41 PM_

right... not surprising

_David Burns  8:41 PM_
but that may change

_Marcus Merrell  8:42 PM_

@simonstewart was talking about potentially having a webinar 
series instead of a 6-hour block of content, and I'm kind 
of interested in anything that will keep the zoom fatigue to a minimum

unfortunately, online conferences are not my thing, 
as an organizer or a participant... this might make 
me an ideal candidate to create an event that won't over-do it

_David Burns  8:42 PM_

heheh

_Diego Molina  8:43 PM_

ok, seems we can move to the next topic

Selenium Branding guide

_Manoj Kumar Kumar  8:44 PM_

We’ve always get many requests on usage of Selenium Logo for 
multiple purposes, PLC usually respond to those 
requests with the help of SFC folks.

And there is also an issue to track : https://github.com/SeleniumHQ/seleniumhq.github.io/issues/81
This has been long pending and finally  we've started with it...

_8:45_

Selenium Branding guide is in-progress. Its on a private Google doc shared with the PLC and TLC members.
The legal rep from the SFC team has reviewed and shared feedback and recommendations.
And right now its with our Selenium team (PLC, TLC(may not be all of TLC) to look at it and see if we need more clarifications.

_8:46_

@simonstewart @diemol @AutomatedTester  and others When you get a moment, pls take a look and share your feedback

_Diego Molina  8:47 PM_

I think it makes sense to have something like that, it is easy 
to see the Selenium logo / name misused in many places

that's a good initiative @manoj9788!

_David Burns  8:47 PM_

What does this mean when people use our brand incorrectly. 
Like there is a linkedin group that charges for people making specific posts there.

_8:48_

(from what I am told)
do we have recourse to challenge them?

_Manoj Kumar Kumar  8:49 PM_

That reminds of another questions,`What are social media accounts that are handled by SeleniumHQ team?`

_Marcus Merrell  8:49 PM_

using it to promote "Selenium Certification" is the example I see most often
I believe we've done it before (before I joined the PLC)
"done it" == "challenged them"

_Manoj Kumar Kumar  8:50 PM_

AFAIK,
Twitter: SeleniumHQ, SeleniumConference are handled by SeleniumHQ team.
LinkedIn: Is there one?
Facebook: I think there is one, but don't think anyone from SeleniumHQ team is handling that.

_Marcus Merrell  8:51 PM_

we should hire a tween to manage our insta
(and TikTok)
only half-kidding

_Diego Molina  8:51 PM_

there is this https://www.linkedin.com/company/selenium/about/
and this https://www.linkedin.com/company/selenium/about/

_Marcus Merrell  8:51 PM_

especially considering how many tweens 
use Selenium to do fortnite/roblox stuff

_Manoj Kumar Kumar  8:51 PM_

TikTok is officially banned in India :slightly_smiling_face:

_Marcus Merrell  8:51 PM_

lol

_Diego Molina  8:52 PM_

sorry, the 2nd link was meant to be this one
https://www.linkedin.com/groups/961927/ (edited) 

_Manoj Kumar Kumar  8:52 PM_

ok, no politics here.
No idea, who owns that.

_Diego Molina  8:54 PM_

I feel this topic overlaps with the last one in the agenda:
Selenium marketing strategy (e.g: Webpack)

_Manoj Kumar Kumar  8:55 PM_

Diego you could share some insights on the example you gave on Webpack

_Diego Molina  8:56 PM_

yeah, I was trying to find some info while Googling

_8:57_

the idea I heard, is that webpack was not having a good moment in terms of users and reputation
and revamping their image (docs, social media, and of course improving the project code) helped them enormously
so this point is just to have a conversation starter and see if you think something similar makes sense for Selenium

_Simon Stewart  8:58 PM_

It’s useful. I think we should also take a look at the 
things that people like about Cypress and make it 
clear how to surface the same thing in Selenium
:+1::skin-tone-4:

_Manoj Kumar Kumar  8:59 PM_

Agreed! I think it makes sense @diemol

_David Burns  8:59 PM_

I would love for us to do something here.
@simonstewart and I have previously discussed this as "fit and finish"

_9:00_

these are the things that people like about puppeteer/cypress and we've not done a good job of explaining similar things

_Simon Stewart  9:01 PM_

Indeed.
TBH, I thought we’d be done with Se4 at this point
And well into the fit-and-finish bit that could be Se5
But I agree with @diemol that part of that is a nice site with excellent docs

_Diego Molina  9:02 PM_

nice, so it seems there is a general feeling that we should be doing something like that
I can try to do something about it and give an update for the next meeting

_Simon Stewart  9:02 PM_

Just one piece isn’t good enough to solve the problems
That’d be fantastic, @diemol

_Manoj Kumar Kumar  9:02 PM_

Happy to pair on that @diemol

_Diego Molina  9:02 PM_

sounds good!

_9:03_

well, that was the last topic in the agenda, and we are 3 minutes past the end of the meeting
thank you everyone!

_David Burns  9:03 PM_

yay

_Manoj Kumar Kumar  9:03 PM_

And we've moved from Master to Trunk
Thanks @AutomatedTester

_David Burns  9:03 PM_

yes, and it was an important move

_Manoj Kumar Kumar  9:04 PM_

Thanks @diemol @simonstewart @AutomatedTester and everyone!
