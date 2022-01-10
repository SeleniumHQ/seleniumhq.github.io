---
title: "Public Project Meeting - April 9, 2020"
linkTitle: "TLC - April 9, 2020"
date: 2020-04-19
---

As an initiative to bring more transparency to the Selenium project, we are now holding bi-weekly public project
meetings. These are written meetings which are held on the `#selenium-tlc` channel on [Selenium Slack](https://seleniumhq.slack.com/join/shared_invite/enQtODAwOTUzOTM5OTEwLTZjZjgzN2ExOTBmZGE0NjkwYzA2Nzc0MjczMGYwYjdiNGQ5YjI0ZjdjYjFhMjVlMjFkZWJmNDYyMmU1OTYyM2Y).

Here is the timeline of the meeting held on April 9, 2020 (times are on CET). 

The next meeting will be on April 23, 2020, 4:30 PM CET.

---   

Simon Stewart  4:31 PM
@adamgoucher there needs to be a calendar invite for these things

adamgoucher  4:32 PM
well, originally the idea was that people could manage their own calendars but if you send me which email you want it attached to i can add you to the one ive been putting people on who wanted an invite

4:35 anyhow; here’s the rough approximation of an agenda. we’ll see how it goes;

- anything governance-y that needs to be discussed in public (not limited to, but including public announcement of people getting the commit bit, etc.)
- status of prs
- status of issues
- status of se project related events (india, chicago are the big ones)
- next release status and/or timeline
- go around the metaphorical table on how people are doing against things they signed up for
- anything that needs to get sussed out in public (from reading the channel, the main thing that came up is the js discussion)
- open the floor for other discussions either instigated by ’project people

4:38
anything governance-y that needs to be discussed in public (not limited to, but including public announcement of people getting the commit bit, etc.)

Diego Molina  4:38 PM
if there is time, let's add the recent topic of adding go bindings

adamgoucher  4:38 PM
has there been an sfc call recently? (i cant remember when they are)

Diego Molina  4:40 PM
governance related, I'd say:
we need to define where we put these meetings minutes, how we announce them widely (do we need?), and that we still need a code of conduct

adamgoucher  4:41 PM
ok. @diemol pester my monday morning if i haven't figured out how to get them onto the blog by then

Diego Molina  4:41 PM
So, meetings minutes... we can put them in the same format as a blog entry, and we can tag/label them so they are publicly accessible
I can do that, I would just need the contents of the previous meetings, and also add small instructions on how to add new ones

adamgoucher  4:43 PM
i think we just start with this one. i didnt take the most excellent notes the last couple and they might have expired out the channel (havent scrolled up to check)

Diego Molina  4:43 PM
ok, so I will start doing that with this onee

adamgoucher  4:44 PM
who is the point person for the code of conduct

Diego Molina  4:44 PM
@AutomatedTester said something about being working on it?
4:44
but we currently have a draft, why don't we use that draft and iterate on it?

adamgoucher  4:45 PM
makes sense. as we did with the other doc

4:46
ok. next thing on the list is status of prs. which @AutomatedTester has been culling like a mad man over the last week and we’re down the 62

4:47
followed closely by status of issues and there is now 402 which i think he is also attacking (but i dont have a number from 2 weeks ago)

Diego Molina  4:48 PM
perhaps we should set up a project health status dashboard
I saw a couple of OSS projects that could do that

adamgoucher  4:49 PM
could be interesting. of course we could also then get into wild semantic discussions around ‘what’ healthy is.  :smile:

4:50
next; status of se project related events (india, chicago are the big ones)

4:51
i saw that seconf india has been moved to september. how is chicago looking?

Diego Molina  4:51 PM
I think no one is around who could reply that question

David Burns  4:52 PM
I also think it's too early to comment. Chicago is in lockdown at the moment for the foreseeable

4:52
we also need to be see how many venues are still around when we come out of our homes

Isaul Vargas  4:53 PM
As for big cities in the US, only by End of May can we know if restrictions will be lifted due to testing and lowering the curve.

adamgoucher  4:53 PM
(it has more of a recurring agenda item more than me seeing someone who can answer)

4:54
and restrictions will be lifted in june and clamped back down in july. as is the way in a pandemic. (and really, will be until we get a vaccine)

4:55
next item; next release status and/or timeline. @simonstewart?

Simon Stewart  4:55 PM
India has been moved to later in the year

4:56
We’re running the 4.0 release in the open: https://github.com/SeleniumHQ/selenium/projects/2

4:56
The config stuff won’t be too horrible. I’m quite liking the fact that TOML opens up some options

adamgoucher  4:56 PM
yup. so is your plan still ‘push the button on stage to do the release and retire for your daily beverage’ or are we aiming for a same-ish part of the calender

Simon Stewart  4:56 PM
When we ship, I stand down

4:57
I’d still like to get the betas out soon

4:57
But there are some big pieces missing before we can get to that point

4:58
The big one being the CDP stuff across Grid, which I’ve many of the moving parts for in various branches on my machine

Diego Molina  4:58 PM
when I look at the things on the "to do" column, maybe 80% is Grid, right?

5:00
if so, it is something that calms me down because at least (from my side), I am having slowly more time to work on the code

5:00
but there are a couple of things on that list which concern me: IDE to the monorepo (and build it with bazel) + implement CDP and relative locators in JS

David Burns  5:01 PM
I am doing the IDE to mono repo now

5:01
relative locators in JS should be easy, I can do it or talk @harsha509 through it

Diego Molina  5:02 PM
but in the IDE, what is our approach in terms of browser extension vs. electron

5:02
right now we could only do the browser extension, right?

David Burns  5:02 PM
that is separate from the mono repo work

5:03
these are questions for @tourdedave and @corevo

Simon Stewart  5:03 PM
Right now, there’s just the browser extension, but @tourdedave and @corevo did start work on the electron port

5:03
I’m not sure what kind of progress they’re making, if any

Diego Molina  5:04 PM
ok, so it seems we will only focus on the browser extension for now

adamgoucher  5:07 PM
ok, so to summarize;
all the known to-dos are in the github project
india has been shifted, but dont want to just move the target to september
aiming for betas to start soon, grid cdp is really the last big hurdle until those can start

Diego Molina  5:08 PM
and the JS stuff, right?

5:08
(CDP + relative locators)

adamgoucher  5:09 PM
i think we can skip the next thing which is ‘how are people progressing on the stuff they signed up for’ with a ‘see the github project’

5:11
because the next thing on the list i prepared was `- anything that needs to get sussed out in public` which, for today, means ‘what to do about javascript’. not sure that anything got resolved the other day but i think the core of it is ‘webdriver.js is the official binding but not under the auspices of the project’ or something?

Simon Stewart  5:11 PM
To reiterate feedback from other people, and my own reflections:

5:12
There is a general feeling that the selenium project should own the core of its own bindings
We’ve gone from having basically no-one interested in JS to having Sri on board (@harsha509)
We’ve not nailed down the overall strategy yet

5:14
Which kind of suggests not using webdriver.io’s webdriver module, but continuing to work with our own stuff

5:14
Unless that module moves under the aegis of the project

Diego Molina  5:15 PM
oh, actually I was talking to @christian-bromann about it, and he manifested again his interest to help move the JS bindings to use the webdriver package, and leave things ready for someone who wants to maintain the bindings

Simon Stewart  5:15 PM
And if the code for the webdriver package was in our repo, I think that’s probably what we’d do

Diego Molina  5:16 PM
I am not a 100% sure that he is ok with moving the code from that package to our repo
but in the worst case, would a copy/fork of the code work?

Simon Stewart  5:17 PM
That seems like a bad idea

Christian Bromann  5:17 PM
What is the problem of not having the webdriver binding in the Selenium repo?

Simon Stewart  5:18 PM
The feedback I’ve had from several people has been that they’re deeply uncomfortable with it, and won’t approve of it

5:18
It’s not just a technical thing

5:18
There are the social aspects to consider too

Christian Bromann  5:19 PM
I understand.

Diego Molina  5:20 PM
well, the idea of the committees is to reach consensus in these cases, and it looks like we would never reach it for this situation

Christian Bromann  5:20 PM
Well the idea of generating raw bindings based of some json files is pretty easy to reimplement and I am happy to support how it can be done.

5:22
copying over the code form WebdriverIO to Selenium is rather difficult as it depends on some other wdio utilities (logging and utility functions)

Simon Stewart  5:22 PM
I do wonder how far adrift the JS bindings are

5:23
The obvious missing 4.0 features are relative locators and the CDP integration

5:23
The CDP integration isn’t nailed down properly anywhere. We’ve got access to the raw protocol, but we’ve yet to build the abstractions people would actually write against

Christian Bromann  5:23 PM
Given the situation I am would be also happy to invite the Selenium JS contributor to the WebdriverIO org to ensure code access and ability to merge/review code changes.

Simon Stewart  5:24 PM
Thank you

Diego Molina  5:25 PM
well, then it seems JS bindings stay as they are
hopefully we all find a way to make them fit for the 4.0 release

5:25
should we talk about the go bindings?

adamgoucher  5:26 PM
thats the only other thing on my list

Simon Stewart  5:26 PM
We can talk about Go bindings

5:27
Miki offered to donate https://github.com/tebeka/selenium to the project a while ago

5:27
I got busy and had health issues, so dropped things on the floor

5:27
Should we pick that back up again?

adamgoucher  5:28 PM
is he also intending to maintain it (dunno the status of them) or is it a ‘i did a thing and dont need it anymore so someone can pick it up if they want’

Simon Stewart  5:28 PM
I dunno

5:28
That would be part of the conversation

David Burns  5:29 PM
if it's the latter then I am not sure we would want them. It's amazingly nice but the maintenance burden, potentially, doesnt feel worth it

Simon Stewart  5:30 PM
Indeed

5:30
But it would be nice to have some “official” Go bindings.

adamgoucher  5:30 PM
i know nothing about go, but i feel like the bar for any binding to be included in the main project;
is it maintained?
is it idomatically correct for the language?
is there not a competing, more obvious one to incorporate?

Simon Stewart  5:30 PM
Turns out that people like them

5:30
s/them/the language/

Diego Molina  5:30 PM
seems it is worthy to give the conversation a go

5:30
who would like to drive that conversation with Miki?

Simon Stewart  5:31 PM
It’s pretty dormant right now, but is that due to being complete?
My understanding is “yes”
Nothing that I’m aware of

5:31
puts finger on nose

adamgoucher  5:31 PM
sounds like ‘you are a go’ for the conversation about go. as it were

Simon Stewart  5:31 PM
Not me

5:31
Yeah, I think it’s a conversation that’s well worth having

Diego Molina  5:32 PM
I can see from a quick look that the go bindings have vendor stuff in it, for example it has the ability to use Sauce Labs out of the box

5:33
even if I work for Sauce, we need to see all those things in case we want to merge them

Simon Stewart  5:34 PM
Aye

Diego Molina  5:36 PM
I know how the Grid works (in general), but I still have black spots in my head when talking about bindings
we need someone who knows how bindings should work and do a check on the go bindings

adamgoucher  5:37 PM
sounds like ‘we think having the go bindings in the main project is cool, but currently the people in the channel right now dont have enough interest (or knowledge) to drive the conversation’

5:38
likely also have to talk to sfc about rights transfer and such. though the existing licensing could cover it

Diego Molina  5:39 PM
maybe we bring the topic back in 2 weeks and see how people feel?

Simon Stewart  5:40 PM
SGTM

adamgoucher  5:41 PM
ok. i didnt have anything else? did anyone else?

Simon Stewart  5:42 PM
I’ve got other meetings to attend, so nowt from me

adamgoucher  5:43 PM
taking silence from the rest as a no. thanks everyone!

Diego Molina  5:43 PM
Thank you all!

