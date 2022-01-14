---
title: "Public Project Meeting - February 25, 2021"
linkTitle: "Public Project Meeting - February 25, 2021"
date: 2021-02-25
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
timeline of the meeting held on February 25, 2021,5:30 PM CET. (Below times are on IST)

Meetings are held on the `#selenium-tlc` channel on [Selenium Slack](https://seleniumhq.slack.com/join/shared_invite/enQtODAwOTUzOTM5OTEwLTZjZjgzN2ExOTBmZGE0NjkwYzA2Nzc0MjczMGYwYjdiNGQ5YjI0ZjdjYjFhMjVlMjFkZWJmNDYyMmU1OTYyM2Y).

---   

_Diego Molina  9:54 PM_

@titusfortner @barancev @harsha509 @luke @jimevans @simonstewart

_Luke Hill  9:54 PM_

Yip. I'm here if needed.

_Diego Molina  9:55 PM_

The only topic I have today, to make it a short meeting, is: “what is needed for beta 2”
Feel free to add/propose any other topics

_Luke Hill  9:59 PM_

Alongside that, do we have a rough idea of what would enable us to release a 4.0.0 proper (i.e. what milestones we need to hit)

_Diego Molina  10:01 PM_

Let’s wait a few minutes to see if we have enough people to start the meeting

_Titus Fortner  10:04 PM_

I have a couple minor things I want to do before beta2, but the sooner the better for me :)

_Simon Stewart  10:04 PM_

I'm in a planning meeting, but I'll follow along slowly

_Titus Fortner  10:05 PM_

(Element#dom_attribute is the main one I want to finish). I think Java, C# both have that one already

_Diego Molina  10:05 PM_

Ok, then let’s start with the ones who are around. Only one topic so far: “what is needed for beta 2”

What comes to my head: fixing the leak :slightly_smiling_face:

_Simon Stewart  10:05 PM_

Crush the leaks. Ensure CDP on Grid to a Docker instance works

_Diego Molina  10:06 PM_

Regarding the leak, after reading more about the AsyncHttpClient issues and their google group…

_10:07_

they mention that each AsyncHttpClient instance has its own pool

_Diego Molina  10:07 PM_

which is why they recommend to have one single instance of it

_Puja Jagani_

After you pointed out the potential problem area, I  attempted to use single instance  of the AsyncHttpClient today and still saw the leak :confused:  Will try and dig deeper tomorrow. Let me know if you want me to try something in particular.

_Diego Molina_

One single instance for the whole distributor?

_Puja Jagani_

One single instance as we had earlier, similar to https://github.com/SeleniumHQ/selenium/blob/selenium-4.0.0-alpha-7/java/client/src/org/openqa/selenium/remote/http/netty/NettyClient.java#L41
java/client/src/org/openqa/selenium/remote/http/netty/NettyClient.java:41
private static final AsyncHttpClient httpClient =
<https://github.com/SeleniumHQ/selenium|SeleniumHQ/selenium>SeleniumHQ/selenium | Added by GitHub

_Puja Jagani_

This was just to narrow down the problem area. It was something I was trying to eliminate if creating a new instance is the problem or the way we handle the response (blocking bit you mentioned earlier)

_Puja Jagani_

Distributor heap with single instance :see_no_evil:

_Diego Molina  10:08 PM_

additionally you can limit the size of the pool

so I am planning to tweak the client configuration and see if that helps

_10:09_

sadly, the docs for AsyncHttpClient are inexistent, but it seems like a robust implementation
it is used for Gatling

_10:09_

(a loat test tool)

_10:10_

load*

_David Burns  10:12 PM_

@simonstewart what's left, assuming its everything, to "Ensure CDP on Grid to a Docker instance works"

_Simon Stewart  10:12 PM_

(In a meeting)

_Titus Fortner  10:13 PM_

each of the bindings need to be able to implement that part (getting debugger address from caps, etc)

_David Burns  10:13 PM_

@titusfortner it's done?

_10:14_

it gets it from se:options

_Titus Fortner  10:14 PM_

I don't think he's done yet with the first part

he's changing that

_Simon Stewart  10:14 PM_

se:cdp now
I updated the bindings that use it

_David Burns  10:14 PM_

@simonstewart touched my code... filthy java person in my python code

_Simon Stewart  10:14 PM_

The "CDP in Grid" stuff will add se:cdpVersion

_Luke Hill  10:14 PM_

One of the new things I noticed in ruby (Not sure if it's relevant across the board). Was the new chrome cdp stuff needs to be able to work in local/remote instances.

_Simon Stewart  10:14 PM_

"My precious...."

_David Burns  10:14 PM_

:stuck_out_tongue:

_Simon Stewart  10:15 PM_

@luke that's what the se:cdp capability allows

_Luke Hill  10:15 PM_

One step ahead of me :smile:

_Simon Stewart  10:15 PM_

The Selenium Server knows how to forward CDP traffic

_Titus Fortner  10:15 PM_

yeah, Ruby code has never actually allowed users to benefit from browser specific functionality in Remote WebDriver because of subclassing blah blah

_Luke Hill  10:15 PM_

So check back again in beta2 is the answer basically.

_Titus Fortner  10:15 PM_

I have a PR to address it

_Simon Stewart  10:15 PM_

I'm going to make the local drivers also set se:cdp

_Titus Fortner  10:16 PM_

With that PR, the Ruby CDP code works with server only if it is on localhost, so we'll also have to update to what Simon has done

_Luke Hill  10:16 PM_

We "know" what we need to do. Which is half the battle.

_Titus Fortner  10:17 PM_

With @p0deje been gone for a while and @twalpole being super busy it's just a matter of bandwidth. :)

well, partly, I only know "ish"

_Diego Molina  10:17 PM_

it’s the chance for @luke to do some commits :slightly_smiling_face:

_Luke Hill  10:18 PM_

I can try help where possible. But I'm not quite on their standard.

_Titus Fortner  10:18 PM_

I'd also like to figure out how to do a different gem publishing for CDP versions

_10:19_

it's more important that we be able to update the CDP versions to match the browser versions than Selenium methods to CDP methods, so I'd like to be able to release the artifacts independently, and let users toggle it somehow.

_Luke Hill  10:19 PM_

By Easter I will hopefully be in somewhere more stable again either renting or buying my first place. So yeh should be able to do more in evenings. This is going to be my fifth property move in just over 2 years

_Titus Fortner  10:22 PM_

How much work is there still in the leak investigation? (I ask because I literally have no idea what all is involved)

_Diego Molina  10:23 PM_

Not sure, I’ve invested this week and at least I’ve pinned it down to the Distributor

_10:24_

It eats memory over time when new tests are executed, and never returns it

I am now checking if the problem is really related to the AsyncHttpClient

_10:25_

well, more related to the way we use it

_David Burns  10:25 PM_

@diemol @Puja Jagani knows this is a priority so feel free to rope her in :slightly_smiling_face:

_Diego Molina  10:26 PM_

they recommend to have a single instance, and in the Distributor we have one instance per registered node, and one instance to do the health checks, and another one that I have not pinned down to see what it is

_Simon Stewart  10:30 PM_

Because each instance needs its own config

_10:31_

If we could change config per request, we'd be fine

_Diego Molina  10:31 PM_

they have a different base url, right?

_Simon Stewart  10:31 PM_

Right

_10:32_

And possibly time outs

Anything on the config object

_Diego Molina  10:33 PM_

I did not see different timeouts, but we will need them when we allow users to configure the timeout in Grid

_Puja Jagani_

I think the RequestBuilder allows to set timeouts per request.

_Diego Molina  10:34 PM_

so, hm, interesting…
now sure what to do now :slightly_smiling_face:

_Simon Stewart  10:35 PM_

Once again, I shall mutter about writing our own http client based on netty, starting from the one we have for domain sockets
