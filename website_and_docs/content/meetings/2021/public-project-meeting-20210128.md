---
title: "Public Project Meeting - January 28, 2021"
linkTitle: "TLC - January 28, 2021"
date: 2021-01-28
---


Continuing the series of bi-weekly public project meetings, here is the
timeline of the meeting held on January 28, 2021,5:30 PM CET.(Below times are on IST)

Meetings are held on the `#selenium-tlc` channel on [Selenium Slack](https://seleniumhq.slack.com/join/shared_invite/enQtODAwOTUzOTM5OTEwLTZjZjgzN2ExOTBmZGE0NjkwYzA2Nzc0MjczMGYwYjdiNGQ5YjI0ZjdjYjFhMjVlMjFkZWJmNDYyMmU1OTYyM2Y).

---   

_Diego Molina  9:17 PM_

Yes, here is the short agenda:
* General project statistics (Previous meeting: 262 open issues, 54 open PRs, Current: 252, 35 PRs)
* Overall announcements? New people on committees, with the commit bit, etc?
* Pending work that needs to be done for the first Beta?
* CoC, next steps?

_Alexei Barantsev  9:44 PM_

can the Bot drop the meeting link to the chat, please, I always forget where to get it…

_Diego Molina  9:53 PM_

this is the written one

_Alexei Barantsev  9:58 PM_

awesome! I’m in yet I have nothing to add to the agenda

_Diego Molina  10:01 PM_

sounds good
I’ll ping people around and we can wait a couple of minutes, Simon said he was going to be slightly late

_10:02 PM_

@titusfortner @jimevans @mmerrell @manoj9788 @AutomatedTester @harsha509 @luke

_Jim Evans  10:03 PM_

i’m not going to be able to make the video chat, but i can contribute here if people have questions. the only thing missing from .NET for beta1 is making CDP work via remote, which i don’t think needs to be in beta1 for .NET.

_Diego Molina  10:06 PM_

this is just the written one, the video chat is just to hangout, that one is next week

_Marcus Merrell  10:06 PM_

no status chance on the Chicago conference at this point - still looking at COVID numbers to determine whether or not it's going to be feasible

_David Burns  10:07 PM_

here!
sorry

_Diego Molina  10:07 PM_

Marcus
are we still looking into an online event, Marcus?

_Marcus Merrell  10:07 PM_

we can look into that
haven't been to this point

_Diego Molina  10:08 PM_

I only ask because (I think) it was mentioned last time
ok, perhaps we can start, the first topic is:
Overall announcements? New people on committees, with the commit bit, etc?

_10:10 PM_

Aside from @luke joining recently, there are no more things to announce, right?

_Sri Harsha  10:10 PM_

I think luke joined recently!

_Simon Stewart  10:10 PM_

Nothing that I can think off

_Alexei Barantsev  10:12 PM_

I support @diemol’s appeal to focus attention to the issue tracker. let’s try to clean it up in view of the forthcoming v.4 release

_10:13_

close all outdated issues and PRs, apply everything that worth applying

_Diego Molina  10:13 PM_

the work done during the recent week on the issue tracker has been great, hopefully we keep that going

_10:14 PM_

we could jump into the next topic perhaps…
Pending work that needs to be done for the first Beta?
here is the list I have from the last meeting

_10:15 PM_

### Client facing changes:
* Make relative locators return elements sorted by proximity
* Fix Java Module System problems (I think @simonstewart did this already)
* Allow locator strategies to be pluggable Enable fallbacks for commands
### Server changes:
* Enable retries of commands where necessary using failsafe
* Get the Grid UI looking nice, and returning useful data (@diemol is working on this)
* Allow locator strategies to be pluggable
* Pipe VNC connectors through the websocket plumbing for live video

_Simon Stewart  10:15 PM_

Relative locators now sort by proximity
I think the Java Module stuff is working
I'm working on fallbacks for locators

_Alexei Barantsev  10:15 PM_

I can confirm that java 9 modules are usable now

_Simon Stewart  10:16 PM_

Locator strategies are pluggable on the server side
Once I get the locator strategies falling back on the local end, I'm fine with shipping the beta

_Diego Molina  10:17 PM_

we had some issue reports related to the Grid, in most of the cases it is a matter of showing the correct information on the UI
and I am working on that

_Simon Stewart  10:18 PM_

Also there's a nasty issue with starting a large number of sessions concurrently

_Diego Molina  10:18 PM_

yeah, I was going to point that one out, https://github.com/SeleniumHQ/selenium/issues/9112
I have not had time to research enough, but most of the results when googling point to: either we are creating too many Netty clients or something is wrong with the way the client is created

_David Burns  10:20 PM_

Feel free to pull in @Puja Jagani on the sessions one, I know she was looking at one about starting up a grid and things not working

_10:20 PM_

(can't remember the issue #)

_Diego Molina  10:20 PM_

that’d be great, I can sync with @Puja Jagani later
what do you all estimate for a beta release? 3-4 weeks?

_Simon Stewart  10:21 PM_

Maybe less

_Diego Molina  10:23 PM_

ok, I think we do not have more things pending for beta 1?
the only topic left is the CoC
New

_Titus Fortner  10:24 PM_

Is this a good time/forum to ask about wiki/docs?

_Diego Molina  10:24 PM_

always

_Titus Fortner  10:25 PM_

Diego pointed me at a couple issues @AutomatedTester created for docs & wiki cleanup things
did we have an idea for what we want in the language specific wiki page vs in the docs; and user vs developer focused?
The Ruby wiki stuff is way out of date, not even just Selenium 4 things :)
just want to know where we should be cleaning it up to

_Diego Molina  10:28 PM_

are those two different things?
language specific things
and
dev docs? (people who want to contribute to Selenium, what technical steps to follow?)

_David Burns  10:28 PM_

CoC is still waiting on SFC.
New

_Titus Fortner  10:29 PM_

What we did with Watir was to make the wiki about things devs need to know about the project building/deploying, etc
and everything user related went into the documentation
oh, heh, looks like we ended up deleting the wiki and sending everything to the website

_Diego Molina  10:32 PM_

Why do we need a language bindings specific things on the user docs?
Do they differ too much on how they work?

_Titus Fortner  10:33 PM_

So our current documentation has tabs to show the examples in each of the languages
the wiki is currently just a big page of examples for using Ruby Selenium (https://github.com/SeleniumHQ/selenium/wiki/Ruby-Bindings)

_10:37_

I guess I haven't spent much time with the docs anyway, so maybe I should play around and see what makes sense.
Didn't know if anyone else had opinions on any of it. :)
Guess not

_Diego Molina  10:38 PM_

ah ok, that Wiki seems to be duplicating the user docs
but some parts of the Wiki could evolve to be part of the user docs

_Titus Fortner  10:39 PM_

yeah, it's 2 approaches to organization as well; here's everything in ruby, vs here's everything, click the tab for each to see the ruby.

_Diego Molina  10:39 PM_

I think the 2nd could work well
someone needs to take the time and take the contents of that Wiki and put them in the docs

_Titus Fortner  10:39 PM_

I'll take a look at what might make sense since now that I no longer have any looming work pressing things for the first time in forever

_Diego Molina  10:40 PM_

cool
thank you

I think that was it for today, right?

Thank you everyone!
