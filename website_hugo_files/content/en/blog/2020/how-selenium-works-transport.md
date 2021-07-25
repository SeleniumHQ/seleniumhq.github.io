---
title: "How Selenium Works: Episode 1 - Transportation"
linkTitle: "How Selenium Works: Episode 1 - Transportation"
date: 2020-06-10
tags: ["slack","meeting","tlc"]
categories: ["governance"]
author: David Burns ([@AutomatedTester](https://twitter.com/AutomatedTester))
description: >
  A series describing how Selenium works under the hood
---


After an interaction on the last weekend of January 2020, on a Selenium Issue where someone said “why can’t you just…” after I explained the issue I thought that I would start explaining commands in Selenium WebDriver and why we landed on the design that we have today.

I will repeat this on every page of the series but a lot, an annoying amount sometimes, of thinking goes into how every little bit of Selenium works.

Why?

Selenium, by chance and being good at what it does, is used by millions of people around the world as well. It's how companies from Microsoft and Google to the small startup make sure that their site works in every browser.

## How Does Selenium talk to the browser?

Selenium, over the years, decided that we were going to use HTTP to speak to the browser. We built a REST-ish API that every client binding could use and hopefully get the same results.

### HTTP and REST-ish? Really?

Yea...

Let's start with the HTTP part. When we started we had to have a unique way to speak for each browser based on the best way to speak to them. So for Internet Explorer we wrote COM code. It was fine, it worked but gave us nightmares. For Firefox we wrote a monstrocity that read line by line and, thankfully, due to Mozilla's "make the browser yours" attitude we could do a lot. Opera allowed us to go in via the DevTools protocol.

Now, it meant that, especially in the early days of WebDriver, we would need to maintain N: M bindings where N is the language bindings and  M is the browsers we support. This is not a road to a good product. We decided that we need something that every language would understand. We also needed something that would be pretty robust. HTTP was chosen and we set about building the `JSONWireProtocol`.

The `JSONWireProtocol` is where we built a REST-ish interface that would speak JSON. I say REST-ish because it didn't follow all the principles of REST but enough to make it powerful for our needs.

### How does it relate to things now?

The Web, the internet, and the world has moved on. Why hasn't Selenium?

This is a good question and the thing is we are trying to move things on. Unfortunately the web has a state where it is broken unless it is working. HTTP is pretty robust as a protocol. It can also allow people to build up clusters for testing without having to worry too much about how the multiplexing would work. This is the reason why Selenium Grid was created and is still a pretty good choice when it comes to farming out your testing to multiple devices and multiple machines.

### But **Some automation Framework that uses Chrome Debug Protocol** is more like the web, be like them.

So... There are tools that use Chrome's Debug Protocol to drive the browser and some of the things they do better than Selenium are down to their choice of how they speak to the speak to the browser. Unfortunately it's a Chrome proprietary protocol and Google is not interested in working with other browsers on making it not.

Also, ignoring the interesting design choices from the the Google team, there is the problem that we have to have a permenantly open connection. In this case it uses WebSockets but if you remember my comment earlier about the internet is down until it's up. WebSockets would be constantly re-establishing the connection. There is also the problem of how much traffic would be going up and down that pipe.

This is fine for puppeteer where you are only speaking to something on your local machine but if you are combining a CI service, like Circle CI or TravisCI and something like AWS Device Farm, Sauce Labs, or BrowserStack you suddenly have a lot of internet inbetween you and your runner and that data needs to get somewhere.

The W3C Browser Testing and Tools Working group, which is made up of browser vendors and Selenium folk, are trying to design what this will look like to make sure that we can make it cross browser from the start without having to do weird hacky patches to browsers and ship those browsers ourselves.

## Want to read more?

* [WebDriver Specification details about transport](https://w3c.github.io/webdriver/#processing-model)

This was originally posted to https://www.theautomatedtester.co.uk/


