---
title: "Public Project Meeting - August 13, 2020"
linkTitle: "Public Project Meeting - August 13, 2020"
date: 2020-08-13
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
timeline of the meeting held on August 13, 2020 (times are on IST). 

Meetings are held on the `#selenium-tlc` channel on [Selenium Slack](https://seleniumhq.slack.com/join/shared_invite/enQtODAwOTUzOTM5OTEwLTZjZjgzN2ExOTBmZGE0NjkwYzA2Nzc0MjczMGYwYjdiNGQ5YjI0ZjdjYjFhMjVlMjFkZWJmNDYyMmU1OTYyM2Y).

The next meeting will be on August 27, 2020, 4:30 PM CET.

---   

_Diego Molina  7:58 PM_
:wave:
* Here is the agenda for today
* General project statistics (Previous meeting: 338 open issues, 73 open PRs)
* Overall announcements? New people on committees, with the commit bit, etc?
* Improving workflow (Reviewing PRs, Improving design requirements)
* Selenium Conf website in GitHub org? (README is pending)
* IDE to the main repo?
* Users/passwords, access keys, etc… to distribution and release repositories. 
* Anything new to report from the last PLC/SFC call?
* Conferences. Status for SeConf India & SeConf Chicago?
* Pending work that needs to be done for Alpha 7?
* Pending work that needs to be done for the first Beta?
* Selenium Branding guide
* Selenium marketing strategy (e.g: Webpack)
* CoC, next steps?
* Did anyone talk to Miki about the go bindings? (This topic has been skipped in the previous meetings but it is good to keep it on the agenda so we act on it when we have more bandwidth)


_8:00 PM_

Let’s start with the 1st item
General project statistics (Previous meeting: 338 open issues, 73 open PRs)

_8:01 PM_

Current statistics 334 open issues, 63 open PRs
Next item
Overall announcements? New people on committees, with the commit bit, etc?
Not that I am aware, anyone else?

_David Burns  8:02 PM_

No, but I would like to propose to create a new team on github for my team to at least have triage ability on bugs

_8:03 PM_

I can't assign bugs unless they are somehow associated to the org

_Diego Molina  8:03 PM_

That is a good idea, I had the thought of creating a triage team and add people who are helping already in the #selenium channel, like @Salmon Mode
:+1:

_Simon Stewart  8:03 PM_

+1 for a triage team

_Diego Molina  8:04 PM_

We should add that to the governance model

_David Burns  8:04 PM_

we can always discuss commit bit for my team at a later stage but I want them in the org

_Diego Molina  8:04 PM_

as a way to be part of the team without the need of explicitly be a committer

_8:05 PM_

who wants to take that task? add this to the governance model?

_David Burns  8:05 PM_

I can

_Diego Molina  8:05 PM_

:thumbsup:

_8:06 PM_

next topic?
Improving workflow (Reviewing PRs, Improving design requirements)

_David Burns  8:06 PM_

that's mine

_8:07 PM_

I would like to see if there is a way we can get designs for what people want descrribed better in issues
I have noticed people misinterpretting requests in bugs that my team are working on

_8:08 PM_

and I dont know if it's tribal knowledge that is missing or general experience or what
can we get more definitive process for this

_Simon Stewart  8:08 PM_

“misinterpreting”?

_David Burns  8:09 PM_

well, we started down the observability issue and there are cases where work was done based on what their intrepretation was and it turned out to be different
they didnt have the same context and motivation behind the changes

_Simon Stewart  8:10 PM_

It really helped when we had a conversation about the event stuff
And since I’d planned to do that work myself, it seemed more lightweight not to fully describe everything in the issue

_David Burns  8:10 PM_

I agree, and I want to see if there are times we can front load that in to the issue
rather than go down, write code , it's wrong, try again

_Simon Stewart  8:10 PM_

Maybe if we make clear that they’re a placeholder for a conversation
and identify who to speak to?

_Diego Molina  8:11 PM_

what I am missing in general about this, is that the decisions made through conversations in Slack are not being written in the GitHub issues or in the PRs, which makes things hard to review and move on

_David Burns  8:11 PM_

there is that too

_8:12 PM_

but having a converrsation requires the person that needs to be spoken to being free which isnt always a realistic expectation due to numerrous factors

_Simon Stewart  8:13 PM_

True, but front-loading all the design work is also impractical

_David Burns  8:13 PM_

I guess if we add more context and motivation that would be a good halfway point

_8:13 PM_

motivation being the main one for me.

_Diego Molina  8:13 PM_

what we also need to realise is that we did not have something that looked like a roadmap before, Simon helped with his brain dump that turned into the GitHub issues we have for the Selenium 4 release project
so we are in a better place than the one we were
but I agree that more detail on what is intended in the issues is needed, a natural next step in the iteration
:+1:

_David Burns  8:14 PM_

agreed

_8:15 PM_

because, and this is my next item, if we can have some more info in issues it's easierr to review
there are changes that I can review and I go for correctness in the java server but it might be the wrong idea
and we have PRs that sit, again due to people having lives and can't always be here

_Diego Molina  8:16 PM_

do you think we should back to the open issues and find ways to extend them and make them more clear?

_Simon Stewart  8:17 PM_

Select the subset that are needing clarification and ask?

_David Burns  8:17 PM_

I have started doing that with some of the next load of work that I want my team to work on
my goal, like everyone, is to ship se4

_Simon Stewart  8:17 PM_

Agreed
And I want to help make that happen

_8:18 PM_

But I’m under some weird time constraints

_David Burns  8:18 PM_

I know, I am not blaming anyone

_Simon Stewart  8:18 PM_

I know :slightly_smiling_face:

_David Burns  8:18 PM_

I just want to make your life easier and my team's
and you're kinda in both

_8:19 PM_

so... since code is mutable... can we go with I review for correctness, if it's wrong and we spot then a new issue is raised with more context/motivation?
and I will put it on my team's stack?

_8:20 PM_

I feel this issue could raise it's head with Sauce if they expand their team

_Diego Molina  8:20 PM_

uh, I need the simple English translation for that phrase

_David Burns  8:21 PM_

heh... sorry
since I can review code, and make sure we have tests. If I feel it's ok to merge I will, after a few days

_8:22 PM_

if others in this group disagree with the change, let me know, I will add it to our work and will get it sorted
The issue of missing context and motivation could happen to anyone joining this group e.g. Sauce expanding their OSS team

_Diego Molina  8:24 PM_

got it, thanks for the translation
I don’t see any issue with that, I think it makes sense
in the end we can always improve things after merging code

_David Burns  8:24 PM_

cool. there are a few PRs from my team that have been sitting for a while

_8:25 PM_

and didn't feel I could merge as I didnt have confidence in the serverr (give me a browser any day)

_Diego Molina  8:26 PM_

I am finding ways to help a bit more there, but we should follow your suggestion, I would appreciate a comment in the PR before merging it, like “hey, this has been sitting for while, it looks correct to me, I’ll merge it tomorrow unless someone has something against”
:+1:

_David Burns  8:27 PM_

deal
and we can get to a stage where my team can review each other which would be even better

_Diego Molina  8:27 PM_

:thumbsup:

_8:28 PM_

Next topic?
 Selenium Conf website in GitHub org? (README is pending)
 
_8:29 PM_

Quick update here, Andrew gave me an intro on how to make changes, the setup is more complex than I thought. We are in the process of documenting it.
We should in the near future formalise a team in the governance model for the Selenium conference, there is a lot of work happening there that should be more recognised
:+1:

_8:30 PM_

Next topic
IDE to the main repo?

_8:30 PM_

I’ve seen @AutomatedTester and @rajendra moving the IDE repo into bazel, right?

_David Burns  8:31 PM_

bazel work is hand
once that is done we can move it over wholesale
I think the bazel work is 40% of packages. Tests still need doing but we're touch the rough edges of bazel here

_8:32 PM_

we've both been chatting to people on the bazel slack

_Diego Molina  8:32 PM_

thanks for the update
next topic?

_8:33 PM_

Users/passwords, access keys, etc… to distribution and release repositories.
I added this one

_8:34 PM_

A few weeks ago I changed the way docker-selenium is released, moved it to GitHub actions, since I did not know the password used to push the images to Docker Hub, I had to reset the password and use it there

_8:35 PM_

which means that I am the only one who has the password now, and I believe this is the case for many other things we have (npm, python and ruby stuff, stores for Google/Firefox etc..)
is there a way the ones in the TLC can have access to this information?

_Simon Stewart  8:35 PM_

We have some of the passwords stored in LastPass

_Diego Molina  8:35 PM_

and also consolidate it?

_Simon Stewart  8:36 PM_

We can share out individual credentials as needed

_Diego Molina  8:36 PM_

the thing is that the “as needed” moment usually comes when the one who has the credentials is not available

_Simon Stewart  8:37 PM_

That’s why we share them on LastPass :slightly_smiling_face:

_Titus Fortner  8:37 PM_

We could create a selenium account for Rubygems and share the password, right now it is giving individual accounts permission manually

_Diego Molina  8:38 PM_

Who has access to LastPass and can give access to all the TLC members?

_Simon Stewart  8:38 PM_

I do.
Everyone in the PLC should have it too

_Diego Molina  8:38 PM_

I am only referring to credentials needed for releases and so on
do we want to have different types of credentials mixed?

_Simon Stewart  8:38 PM_

(Sinking into a meeting where I need to talk at work. Dropping out of here)

_Diego Molina  8:39 PM_

Ok, then we can touch this topic again in the next meeting, but we already started it :slightly_smiling_face:
Next topic:
Anything new to report from the last PLC/SFC call?

_8:40 PM_

Seems not :slightly_smiling_face:
Next topic:
Conferences. Status for SeConf India & SeConf Chicago?

_David Burns  8:42 PM_

@mmerrell or @manoj9788 ?

_Diego Molina  8:42 PM_

I know India is virtual and there is a schedule already, I think they are figuring out if workshops will be done
:+1::skin-tone-4:

_Diego Molina  8:43 PM_

and I saw that Chicago is officially postponed to 2021

_David Burns  8:43 PM_

in that case, move this topic to just india and then post that conference drop it from the agenda for a while

_Diego Molina  8:44 PM_

ok, makes sense
next topic:
Pending work that needs to be done for Alpha 7?
Pending work that needs to be done for the first Beta?
I am not sure if there are many updates here

_8:44 PM_

I think we need to land those PRs that are sitting to move to the last alpha

_Marcus Merrell  8:46 PM_

Yeah--we've moved out all SeConf Chicago meetings until November. We're working to choose a new date with the venue for next year, and working on language in the contract that will allow us "ultimate cancel ability" if this thing continues to be a problem. We can move that off of the agenda for the time being
:+1:

_8:47 PM_

I don't have any new information about India - as far as I know there are no changes to the plan

_Diego Molina  8:48 PM_

thanks Marcus!

_8:49 PM_

So, I know that the 4 remaining topics in the agenda have no updates, so I suggest we skip them and we end the meeting early
what do you think?

_David Burns  8:49 PM_

fine
@mmerrell hey... friendly poke about that draining PR

_Diego Molina  8:51 PM_

ok, thanks everyone for your time!
