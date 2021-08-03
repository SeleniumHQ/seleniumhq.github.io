---
title: "What’s Coming in Selenium 4: Why The Major Version Bump?"
linkTitle: "What’s Coming in Selenium 4: Why The Major Version Bump?"
date: 2020-11-10
tags: ["selenium"]
categories: ["general"]
author: Simon Stewart ([@shs96c](https://twitter.com/shs96c))
description: >
  Let’s start talking about Selenium 4 and what’s coming. One thing I think I should clear up is "why the major version number bump?"
---


>In the second post in this series, Simon Stewart continues talking about 
>what's coming in Selenium 4 and why this release has a major version bump.

In my [last post](/blog/2020/what-is-coming-in-selenium-4-how-can-i-contribute/), 
I shared a bit about how the Selenium project works overall. Now, let’s start talking about Selenium 4 
and what’s coming. One thing I think I should clear up is "why the major version number bump?"

Sometimes I joke that the major reason for the version number bump is that while the digits of Pi are 
infinite, when we went from 3.14 to 3.141, people got a little bit upset. Moving to 3.141.5 and then 
3.141.59 was as far as we wanted to push that particular idea :)

More seriously, the first reason is that we have a revised Selenium IDE. Years ago, this used to be 
Firefox only because it used the XPI extension mechanism (which was specific to Firefox). It’s now a 
web component, and you can download it for Chrome, Firefox, and anything else that supports Web 
Components. There is currently work to rewrite it as an Electron app, which will allow us to make 
better use of the native OS the IDE is running on. That work was largely pushed forward by developers 
working at Applitools. They started from a base of the original Selenium IDE that had been migrated 
to use Web Components by a company called SideX. It’s been a fantastic example of the community 
working together well.

Secondly, we have fully adopted the W3C WebDriver protocol, and have dropped support for the original 
home-grown wire protocol. The way that Selenium communicates with a web browser is via a wire protocol 
that’s effectively just JSON over HTTP. Originally this grew organically as we figured things out, 
and we tried to make browsers do what we needed them to. That original protocol is known as the JSON 
wire protocol because it spoke JSON over the wire (and we’re not great at coming up with very original names).

That original protocol was the base for the W3C WebDriver protocol, which smoothed some of the rough 
edges, and brought some much needed consistency to the protocol. The two major areas the standardised 
protocol improved on included session creation, where we removed considerable ambiguity, and by providing 
a far richer API for specifying user actions.

So what does this adoption of the W3C protocol mean for you? I'll be honest: it probably doesn’t mean 
much to you at all. If you're using a modern browser (released over the past couple of years), you will 
find that actually you already speak the W3C protocol with Selenium 3. 

So who does care about the protocol dialects? When we talk about companies like Sauce Labs who provide 
Selenium as a service—they care about it. The ecosystem is ready for this next step, because the 
technical folks at these companies have ensured that they understand and comply with the W3C protocol, 
and folks from the Selenium project have been there offering help and advice as needed.

One of the other nice things about Selenium 4 is that we've done our best to ensure a stable user-facing 
API. That means when you upgrade your project from Selenium 3 to Selenium 4, it should be a drop-in 
upgrade. You just change the version number, recompile and you should be done.

There are a few caveats that you should be aware of, however! The major one is that if in the last version 
of Selenium 3 a method was deprecated, it’s now probably gone. We’ve taken the opportunity of a major 
version bump to delete them and clean up the behind-the-scenes internals people don’t normally get to see. 
If you’re a software developer, you may recognise this as us paying off some of our technical debt :)

Stay tuned for the next post, where I’ll go over some new tricks in Selenium 4.

*This was originally posted at https://saucelabs.com/blog/whats-coming-in-selenium-4-why-the-major-version-bump*
