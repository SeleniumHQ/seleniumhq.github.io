---
title: "Public Project Meeting - November 19, 2020"
linkTitle: "TLC - November 19, 2020"
date: 2020-11-19
---


Continuing the series of bi-weekly public project meetings, here is the
timeline of the meeting held on November 19, 2020 (times are on IST). 

Meetings are held on the `#selenium-tlc` channel on [Selenium Slack](https://seleniumhq.slack.com/join/shared_invite/enQtODAwOTUzOTM5OTEwLTZjZjgzN2ExOTBmZGE0NjkwYzA2Nzc0MjczMGYwYjdiNGQ5YjI0ZjdjYjFhMjVlMjFkZWJmNDYyMmU1OTYyM2Y).

The next meeting will be on December 03, 2020,5:30 PM CET.

---   

_Simon Stewart  9:53 PM_

@diemol are you running today’s status update?

_Diego Molina  9:55 PM_

I can do that
I always forget because the event has no notifications

let me look for the agenda
Some numbers before starting:
Previous meeting: 281 open issues, 61 open PRs
Current: 280, 62 PRs

_Simon Stewart  9:58 PM_

I’ve added a 30 minute reminder to the meeting in Google Calendar

_Diego Molina  10:00 PM_

From previous agendas, I believe these could be the topics:
* Overall announcements? New people on committees, with the commit bit, etc?
* Anything new to report from the last PLC/SFC call?
* Pending work that needs to be done for the first Beta?
* Users/passwords, access keys, etc… to distribution and release repositories. 
* CoC, next steps?

_10:00_

Feel free to add topics

_10:01_

Should we start?
pinging @AutomatedTester @titusfortner @harsha509 @jimevans
@manoj9788 @mmerrell
@barancev @p0deje


_Sri Harsha  10:04 PM_

m in

_Simon Stewart  10:04 PM_

Here!

_Diego Molina  10:05 PM_

First topic: Overall announcements? New people on committees, with the commit bit, etc?


_David Burns  10:05 PM_

here

_Simon Stewart  10:06 PM_

We really need to agree an emoji for “I’ve nothing to say”

:shushing_face: maybe?

_David Burns  10:06 PM_
:speak_no_evil:

_Diego Molina  10:06 PM_

:speak_no_evil:

_Simon Stewart  10:06 PM_

I like that
We shipped 4a7
Nice work, everyone

_Diego Molina  10:08 PM_

ok, let’s move to the next topic :slightly_smiling_face:

2nd topic: Anything new to report from the last PLC/SFC call?

_Simon Stewart  10:09 PM_

I missed it

_David Burns  10:10 PM_

There were notes from @jimevans re: a meeting at the end of October, was that the last SFC?

https://seleniumhq.slack.com/archives/C013TSZD4P5/p1604415853013000

_Diego Molina  10:12 PM_

right, I remember that, probably that covers the topic
then, next one :slightly_smiling_face:
3rd topic: Pending work that needs to be done for the first Beta?

_Simon Stewart  10:13 PM_

There’s a lot. Mostly in Java
I think we should all get together at some point and figure out how closely our CDP-based functionality aligns
I think it’s pretty close, but I know that there are few things that @jimevans did that I’d like “take inspiration” from

_David Burns  10:14 PM_

Can we get things documented since you can be a bottle neck due to life. How can we, being my team, help here?

_Simon Stewart  10:14 PM_

* lient facing changes:
* Make relative locators return elements sorted by proximity
* Fix Java Module System problems
* Allow locator strategies to be pluggable
* Enable fallbacks for commands

_10:16_

Server changes:
* Enable retries of commands where necessary using failsafe
* Get the Grid UI looking nice, and returning useful data
* Allow locator strategies to be pluggable
* Pipe VNC connectors through the websocket plumbing for live video

@AutomatedTester ^^ I documented them :stuck_out_tongue:
CDP for Firefox would be handy too

David Burns  10:17 PM
I am going to start CDP for Firefox tomorrow

_Simon Stewart  10:17 PM_

Are there docs on how to find the ws address to use?

_David Burns  10:17 PM_

No... I will document it

_Diego Molina  10:17 PM_

we really need some sort of docs to have a decent feature parity across bindings

_Titus Fortner  10:17 PM_

does all the CDP stuff work for Edge as well right now? I haven't tried anything but Chrome so far

_David Burns  10:18 PM_

I have a "try" build of geckodriver with it that I need to play with

_Simon Stewart  10:18 PM_

The Java stuff works for edge too

_Titus Fortner  10:18 PM_

I was doing a brief analysis of some things for a talk I was giving, and we 
implemented features in Selenium 3 at very different versions between the bindings. Kind of surprised me :)

_Simon Stewart  10:18 PM_

Heh. Jari was fast :slightly_smiling_face:

_David Burns  10:19 PM_

misses Jari

_Diego Molina  10:20 PM_

do we have GitHub issues for the items missing for beta?

_Jim Evans  10:20 PM_

those notes were from the 3 november call, which was the last PLC call
(they only happen monthly)

_Simon Stewart  10:20 PM_

Most of those things are in the project plan, @diemol

_Jim Evans  10:21 PM_

so there’s a bug in .NET alpha7 that prevents the bindings from working with grid 4.
(it’s fixed now)

_Titus Fortner  10:21 PM_

I mentioned recently that Java & Ruby don't have the right STP name still in 4 alpha and .NET & Ruby both 
need to add support for full page screenshots in Firefox. Not sure what list that needs to get added to?
(I'll try to get to the Ruby stuff this weekend)

_Jim Evans  10:22 PM_

print-to-pdf end point?

_Simon Stewart  10:22 PM_

@jimevans I think the check in the java bindings may be a little too extreme, so we may need to loosen things there too
@titusfortner good point. The STP thing is irksome. I need to dig into it

_Jim Evans  10:23 PM_

sorry i’m late to the party, btw. was spending a few minutes with P this morning before her first client.

_Titus Fortner  10:23 PM_

I tried to fix it in Java, but I was missing pieces. Someone who knows what they are doing, should be easy

_Simon Stewart  10:23 PM_

It’s not easy
If it was, I’d have fixed it already :slightly_smiling_face: Or @barancev would have

_Titus Fortner  10:23 PM_

STP went from "Safari" to "Safari Technology Preview" back to "Safari" I changed Ruby 
to the longer version when I thought I was using the latest STP (I was not)

_Simon Stewart  10:24 PM_

I think I need to tease apart the two versions of the browser more

_Titus Fortner  10:24 PM_

I think we just need to support the latest version

_Simon Stewart  10:24 PM_

I’ll have a chat with folks here to figure out the Right Thing to do

_Jim Evans  10:24 PM_

i want .NET to undergo a radical refactor in its class structure before 4.0 releases. 
now that we have a web standard, the current class hierarchy is wrong.

_Simon Stewart  10:24 PM_

Yeah, just the latest version

_Jim Evans  10:24 PM_

well, not really “radical,” but definitely a change

_Simon Stewart  10:25 PM_

@jimevans we really need to find a way to get someone helping you with the .net stuff

_Jim Evans  10:25 PM_

nothing breaking, API-wise.
with .NET 5 being out, it should be a fair sight easier.

_Diego Molina  10:27 PM_

We can spread the word about the help we need in the different bindings, 
either for refactoring or reaching feature parity if we have those changes described somewhere, 
ideally a GitHub issue. Then we could tweet or something, saying we need help with this issue.

_David Burns  10:28 PM_

Well... I have an idea I would like to share towards this but I can do that at another time unless now is a good time

_Simon Stewart  10:28 PM_

The Java module stuff is obvious if you use java modules

_Jim Evans  10:28 PM_

so, right now, the .NET class hierarchy is RemoteWebDriver (implements IWebDriver et al) -> all browser-specific drivers. 
what it should be is abstract class WebDriver (implements IWebDriver) -> RemoteWebDriver, 
and also abstract WebDriver -> browser specific drivers. if i’m clever about it, this will not be a breaking API change, 
because IWebDriver is still A Thing™.

_Simon Stewart  10:28 PM_

Just like the java 8 issue was

_Diego Molina  10:29 PM_

Please share, @AutomatedTester

_Jim Evans  10:30 PM_

my ${paidWork} project is approaching a bit of stability, 
so i should be able to devote some time between now and the end of the year, hopefully.

_Jim Evans  10:30 PM_

my ${paidWork} project is approaching a bit of stability, 
so i should be able to devote some time between now and the end of the year, hopefully.

_David Burns  10:31 PM_
I would like to, with support of this group, see about creating a Yak Day. 
The idea is, and it requires a bit of upfront work, to get issues in place for this and then see if we can get contributors

_Jim Evans  10:31 PM_

the idea in Selenium 5 is to start phasing out the 
use of IWebDriver in favor of using the abstract base class.

_David Burns  10:32 PM_

it would also require that I, or others, do a "How to contribute" video to share before hand

_Jim Evans  10:32 PM_

(sorry for the parallel idea-spewing into the channel, but i’ve been thinking about this, 
and not really talked about it to anyone outside the multiple voices in my own head.)

_David Burns  10:32 PM_

and if we split it accordingly to language bindings and services we can see if we can try build out a bit more support for each langauge
and may get us more contributors.

_Simon Stewart  10:33 PM_

There’ll be hoops I need to jump through, but I can jump through them to make that happen

_David Burns  10:33 PM_

I appreciate it can flood us with junk and we would need to work out to how to prevent that

_Simon Stewart  10:33 PM_

I’ve got some half-written docs for building with bazel for selenium.dev too

_David Burns  10:33 PM_

but the general gist is create tiny bits of work to build up contributors

_Titus Fortner  10:34 PM_

So, I don't know what everything that is on the lists mentioned above entails, 
but I do want to put in a generic plug that we make sure things absolutely need to be in 4.0. 
If we can release something "good enough" to iterate on through 4.x releases, 
it's going to be *much better than a super polished codebase that isn't released for another 6 months.

_Diego Molina  10:34 PM_

that would be really great

_David Burns  10:35 PM_

I agree @titusfortner, while we are in alpha we have a limited usage group
but we have finite resources so its a fine line to tread

_Titus Fortner  10:36 PM_

nope, totally understand; we're all volunteers and such.
just requesting we keep in mind that perfect is the enemy of releasing something that makes current users lives better

_Simon Stewart  10:36 PM_

After 4.0 ships, I’m going to be stepping away
I’ll probably stay involved with WebDriver Bidi, but I won’t be cutting code

_Diego Molina  10:37 PM_

no no, you cannot leave us alone with bazel :smile:

_Titus Fortner  10:37 PM_

Yeah, I get that this is a factor for sure :)

_David Burns  10:37 PM_

Bazel isn't that scary

_Titus Fortner  10:38 PM_

isn't the point of bazel that we shouldn't have much issue with it once everything transitions? :-D

_Simon Stewart  10:38 PM_

I’ll still be hacking on bazel

_David Burns  10:38 PM_

I've been contributing to that community recently... going to overtake @simonstewart knowledge soon :smile:

_Simon Stewart  10:38 PM_

One can only hope

_Jim Evans  10:39 PM_

bazel is fine… as long as your language and toolset’s opinions don’t conflict with bazel’s

_David Burns  10:39 PM_

@jimevans I'm finding that works with all languages...
but that's an aside

_Simon Stewart  10:39 PM_

@jimevans I want to see better .net support in bazel
What I need is a) time, b) a Windows machine

_Jim Evans  10:40 PM_

well some languages’ tooling are more opinionated than others. 
(see: MSFT is all in on MSBuild, and any other build tool will always, always, always be a second-class citizen.)

_Diego Molina  10:40 PM_

Can the project buy you one?

_Jim Evans  10:41 PM_

@simonstewart i’ve had remarkably good luck using VMs and VMWare Fusion.

_Simon Stewart  10:41 PM_

My hard drive is currently stuffed with multiple versions of macOS.

_Jim Evans  10:41 PM_

like, that’s been my exclusive .NET dev environment for going on 8 years now.

_Simon Stewart  10:42 PM_

Once I get more storage, I can try a VM again

_Diego Molina  10:42 PM_

but ok, to the topic, which was Pending work that needs to be done for the first Beta?
I believe we have commented the most of it

_10:43_

I will go through the meeting minutes and then create GitHub issues if I don’t find any for the items mentioned

_Simon Stewart  10:43 PM_

For me, “beta 1” means “this is what you’ll get in 4.0, but there are known issues”

_Jim Evans  10:43 PM_

.NET needs to add the “make CDP user scenarios easy” methods to be added.

_Diego Molina  10:43 PM_

and I will ping you to add details about it

_Jim Evans  10:44 PM_

yikes that’s atrocious grammar. but you get my meaning.

_Diego Molina  10:44 PM_

should we move to the last two topics? we have 16 minutes left

_David Burns  10:44 PM_

go for it :slightly_smiling_face:

_Diego Molina  10:44 PM_

4th topic: Users/passwords, access keys, etc… to distribution and release repositories.
So, recently @AutomatedTester & me got access to the project to release Java, but in general, 
if we ever need to do a release and the key folks are missing
we do not have access to this information

_Jim Evans  10:45 PM_
anyone who wants access to the nuget repo, create a nuget.org account (requires a “microsoft account”), and let me know. 
i’ll add you to the organization for selenium.

_Titus Fortner  10:46 PM_

who owns email access to selenium.dev? I think we should get a project email/password for these things instead of each using our own?

_Sri Harsha  10:46 PM_

i got access to npm selenium-webdriver package

_Diego Molina  10:46 PM_

for example, we were able to get access to the npm org recently, so @harsha509 could do a JS release

_David Burns  10:46 PM_

and we got access for NPM :wink:

_Diego Molina  10:46 PM_

I thought we could be more proactive in this and not wait until we need the person to share the access.

_Titus Fortner  10:47 PM_

I mean, if people sign up for rubygems and send me their email, I can add them to the list of authorized users; 
but might scale better if there were a lastpass/onepass/whateverpass that stores universal credentials...

_David Burns  10:47 PM_

The only place I can think of is nuget
python has a few people but we can add more.
The other place is Google Storage, do we have enough people with access there?

_Simon Stewart  10:49 PM_

I’ve got Google Storage. @jimevans does too.
I think Luke may still have the keys.

_Diego Molina  10:51 PM_

I believe it is a matter of someone doing the work and going one by one to give access to the TLC to all what we need.
I can find some time to do that

_10:52_

ok, then the last topic
10:52
CoC, next steps?

_David Burns  10:53 PM_

CoC is waiting on SFC/PLC to reply to my message
I should follow up
I have found a contractor, ex-head of D&I from Mozilla, to help do the work

_lukeis:indeed:  10:55 PM_

yeah, i still have the google storage keys… tucked away in an archive i put in my personal google drive :grimacing:

_Simon Stewart  10:55 PM_

I’ve put together a quick [Google Doc](https://docs.google.com/document/d/1PlZMo63a9-wIcwT6Btxb5m3sHJ6EmD5tq3EEaWisKr4/edit?usp=sharing) for folks to add their names to for access to release pathways.

_David Burns  10:56 PM_

thanks @simonstewart!

_lukeis:indeed:  10:57 PM_

we got rid of the google app-engine driving seleniumhq.org right?

_Simon Stewart  10:58 PM_

I can’t remember where we deploy selenium.dev to
It may still be app engine

_David Burns  10:58 PM_

selenium.dev is on github pages

_Diego Molina  10:58 PM_

We use GitHub Pages

_David Burns  10:58 PM_

via Hugo

_Simon Stewart  10:59 PM_

https://github.com/SeleniumHQ/seleniumhq.github.io/blob/dev/.github/workflows/deploy.yml#L26
.github/workflows/deploy.yml:26
        uses: peaceiris/actions-gh-pages@v3
<https://github.com/SeleniumHQ/seleniumhq.github.io|SeleniumHQ/seleniumhq.github.io>SeleniumHQ/seleniumhq.github.io | Added by GitHub

_lukeis:indeed:  11:00 PM_

yeah, the sehq app engine still exists… and the dashboard seems like it’s still getting some kind of requests (very few) 
 wonder if it’s still handling some redirects

_Diego Molina  11:02 PM_

it should be mostly redirects
I need to leave, but thank you all for joining!

_Titus Fortner  11:02 PM_

Thanks @diemol!
