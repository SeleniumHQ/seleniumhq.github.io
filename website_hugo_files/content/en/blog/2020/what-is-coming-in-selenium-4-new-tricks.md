---
title: "New Tricks in Selenium 4"
linkTitle: "New Tricks in Selenium 4"
date: 2020-12-16
tags: ["selenium"]
categories: ["general"]
author: Simon Stewart ([@shs96c](https://twitter.com/shs96c))
description: >
  Today, we’ll cover some details about the new tricks and capabilities that Selenium 4 offers.
---


>In the third post in his series, Simon Stewart continues talking about what's coming in 
>Selenium 4 and reviews some of the tricks in the new release. 

In my last posts, we talked about 
[how to contribute to Selenium](/blog/2020/what-is-coming-in-selenium-4-how-can-i-contribute), and 
[why we’re bumping the version number to 4](/blog/2020/what-is-coming-in-selenium-4-why-the-major-version-bump). 
That’s enough preamble! Today, we’ll cover some details about the new tricks and capabilities 
that Selenium 4 offers.

## Finding Elements, and Relative Locators

You know, finding elements on a page can be really difficult. I've seen loads of people using
very complicated XPATH expressions, and trying to figure out complex CSS selectors and things 
like that. There have been whole talks about the subject at SeleniumConf. Surely there must
be a better way to do this?

Think about how we describe where an element is on the page. Think about how you’d do this
over the phone. You’d never talk about the raw DOM, “Ah, find the fifth DIV element nested
inside the SPAN with an ‘id’ of ‘foo’”. You’d just never say that! Instead, you’d say 
something like, “just find that thing above that image, and to the right of that link,” 
when talking about where things are located on the page.

A long time ago, a project called [Sahi](https://sahipro.com) started to locate elements 
like this. Sahi had [Relation APIs](https://sahipro.com/docs/sahi-apis/accessor-api-basics.html#Use%20of%20Positional%20Relation%20APIs), which are a lovely way of finding elements, 
and it was very, very fluent and pleasant to use. When I was starting WebDriver years ago, 
I was talking with [Narayan](https://twitter.com/narayanraman) (creator of Sahi) and I 
promised him that I was going to hold off implementing this flagship feature of Sahi. It 
was something that was lovely, but it was also something Sahi was rightly proud of. However, 
there are now other tools, such as Tyco, that are implementing this style of API, so it 
seems like the time is right to do the same in Selenium. In Selenium 4 we call them 
“relative locators.” You may sometimes see us refer to them as “friendly locators,” since the 
initial implementation called them that, but “relative” better describes how they work. We 
have a handful of them: near, above, below, left of, right of. They allow you to talk in human 
language about where an element is on a page.

Looking at the future, we are also planning to enable users to add new types of locators, 
not only on the, on the client side, but also to the Selenium Server. For example, although 
JS UI frameworks come and go (anyone else remember using jquery?), it’d be nice to be able 
to add a custom locator for the current tools people like, such as React.

## Authenticating to Web Sites

One feature that people have been asking for since we started the project has been the ability 
to authenticate to a web site. Previously, you could do this by crafting the URL the browser 
went to properly, but this leaks credentials to any man-in-the-middle and leaves them in server 
logs, so browsers have slowly removed this piece of functionality. That’s unfortunate, since 
it’s something that we know people frequently need to do in their tests. In Selenium 4, we 
now offer a mechanism to register a username and password that can be used to authenticate against these sites.

## Intercepting Network Traffic

A common complaint of Selenium tests is that they’re slow and flaky. While the bindings to the 
browser are excellent, and fully described by the W3C WebDriver spec, it is true that any end-to-end 
test is likely to suffer more flakiness than a simple unit test—there are just more moving parts, 
and more possibilities for things to go wrong.

One way to resolve this issue is to stub out the backend of the application, intercepting network 
traffic in the test and returning pre-canned responses. Tools such as [mountebank](http://www.mbtest.org) 
make this easy for API testing. Wouldn’t it be nice if there was a similar tool for Selenium?

With Selenium 4, we now provide a mechanism to do this, using the NetworkInterceptor (well, that’s 
what we call it in the Java bindings). Pass it your WebDriver instance, and it’ll be called every time 
the browser is about to make an HTTP request, allowing you to return almost anything you want

## The Chrome Debugging Protocol

As I mentioned previously, one of the nice things about Selenium 4 is our work to ensure a stable 
and modern user-facing API. What does this mean? To start, we need to acknowledge that after a long 
time there’s now competition back in the browser automation space. Notably in the form of Puppeteer 
and Cypress, and taking a step back, what do they offer? They build upon the 
[Chrome DevTools Protocol (CDP)](https://chromedevtools.github.io/devtools-protocol/)>, which is a 
protocol developed to enable a debugger inside Chromium-based browsers.

Because of what it was designed to do, it is a chatty protocol, and not a user-facing API that 
allows you to introspect into the browser. Worse, introduce a network hop between the test and the 
browser, this chattiness leads to slower tests as the network latency increases. That is why tools 
such as Puppeteer want you to run on the local machine. That’s great for the speed of an individual 
test, but makes parallelisation harder, as you can't use services such as Sauce Labs easily.

To complicate matters, because the CDP is designed as a _debugging_ protocol, it can change with no 
notice between versions. That’s why Puppeteer and Cypress are tied to specific versions of browsers, 
and that causes a dilemma for you as a test author: how do you test on multiple versions of a browser? 
Updating the test API to get a different browser can lead to you no longer making valid API calls. 
Not doing so ties you to a potentially outdated version of a browser.

Despite this, using the CDP opens up a host of possibilities, and that’s why we’ve added support 
for it in Selenium 4. In fact, some of our new features are built on top of it (though we hide the details!)

But we’ve also gone further that just adding basic support. Selenium 4 can support multiple versions 
of the CDP at the same time, meaning you can test with both the current and beta versions of Chromium-based 
browsers without needing to rewrite tests. We do this by providing an “idealised CDP”, which is 
stable and covers the features we feel tests need. If that idealised view of the world isn’t enough 
for you, we also expose the raw CDP APIs too, giving you flexibility to choose what’s best for your tests.

We’re feeding this experience into the new W3C WebDriver Bidi specification, which is helping make that 
a better fit for testers.

I am leaving out of this post all the details related to another important trick in Selenium 4, the new 
Selenium Grid, which I will cover in the next post. Stay tuned!


*This was originally posted at https://saucelabs.com/blog/new-tricks-in-selenium-4*
