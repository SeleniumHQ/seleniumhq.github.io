---
title: "Your Agent Learned Selenium From My Old Blog Posts"
linkTitle: "Using AI agents with Selenium"
date: 2026-09-29
tags: ["selenium", "ai"]
categories: ["general"]
author: David Burns [@automatedtester](https://www.linkedin.com/in/theautomatedtester//)
description: >
  A lot of Selenium code is now written by coding agents, and it is wrong in the same handful
  of ways every time. We have added a documentation page on how to fix that.
---

A large share of the Selenium code being written today is written with a coding agent
sitting next to the person writing it. That is fine. What is less fine is that a lot of
that code is wrong, and it is wrong in the same handful of ways every single time.

Ask an agent for a Selenium test and there is a good chance you will get a driver manager
dependency you don't need, a `DesiredCapabilities` object that hasn't existed for years, an
absolute XPath copied out of DevTools, and a `sleep(5)` sitting in the middle of it. It will
be confident. It will have a plausible explanation for each one. It will also be describing
Selenium 3.

## The Problem: the training data is a decade out of date

Models learned Selenium from more than a decade of blog posts, forum answers, and tutorials.
I have written my share of that material, and so has most of this community. The trouble is
that the overwhelming bulk of it describes Selenium 2 and Selenium 3, and it is still sitting
out there, indexed, outnumbering everything we have published since.

It gets worse than just old APIs. Some of the most-repeated patterns in that corpus were
never good practice, even back when they compiled. Sleeping to wait for a page. Downloading
driver binaries by hand. Right-clicking an element in DevTools and pasting the XPath. Those
were bad advice in 2013 and they are bad advice now, but they are extremely well represented
in the training data, which as far as a model is concerned is much the same thing as being
correct.

So the model is not hallucinating. It is doing exactly what it was taught. It was just
taught by the internet of 2015.

## The Solution: tell it where the current docs live

None of this is a reason to keep agents away from your test suite. I use them. Plenty of
people on the project use them. It is a reason to give an agent the same things you would
give a new team member on their first day: the version you are on, where the current
documentation lives, and the conventions your project actually expects.

We have written that up properly, and it is now on the site:
[Using AI coding agents with Selenium](https://www.selenium.dev/documentation/ai_agents/).

There are three things in there worth pulling out.

### Point it at llms.txt

The site publishes a [llms.txt](https://www.selenium.dev/llms.txt) following the
[llmstxt.org](https://llmstxt.org) convention: a single plain-text index of the
documentation in a sensible reading order, so an agent can find the right page without
crawling the site or guessing at URLs.

It is curated rather than exhaustive, and that is the point. The Selenium 2 and 3 era legacy
documentation and the CDP pages are deliberately kept out of it. Both are accurate and both
need to exist. Both are also the worst possible input for a model that is about to write new
code.

Point it at the [examples directory](https://github.com/SeleniumHQ/seleniumhq.github.io/tree/trunk/examples)
too. Every code tab on this site is a link into that repository, and everything in it runs
against current Selenium releases in CI. Unlike a blog post, it cannot silently rot.

### Write the rules down in a file

Agents follow conventions far more reliably when the conventions live in a file than when
you repeat them in chat every morning. The documentation page has a paste-ready block for
your `AGENTS.md`, `CLAUDE.md`, or whatever your tool reads, with per-binding tabs for the
removals people hit most often.

The single most useful instruction in there is a negative one: if an API cannot be found in
the current documentation or API reference, it does not exist, however familiar it looks.

### Let it drive a real browser

An agent that can only write code is guessing about your application. It cannot see your
login form, so it is pattern-matching on what a login form usually looks like. That is
exactly where invented locators come from.

The cheapest fix needs nothing new: tell the agent it may write a throwaway Selenium script,
run it against the real application, and print what it finds. It navigates, dumps the
candidate elements, tries a locator, and reports back. It also exercises the same stack your
real test will use, which is a claim no external tool can make.

And when a test does fail, hand it the actual exception rather than "the test failed". Our
[common errors](https://www.selenium.dev/documentation/webdriver/troubleshooting/errors/)
pages exist precisely so that the likely cause and the possible fixes for a
`StaleElementReferenceException` are one page away.

## The one to watch for in review

If you take a single thing from this post, make it this one.

When a test starts failing intermittently, an agent will very often "fix" it by raising a
timeout or dropping in a sleep. The test goes green. The race is still there. You have just
paid for it in suite runtime and bought yourself a failure on a slower day.

Insist it tells you which condition was not yet true, and then wait for that condition. That
is the whole job of an [explicit wait](https://www.selenium.dev/documentation/webdriver/waits/),
and it is the difference between a suite you trust and a suite you rerun.

## The Bottom Line

Agents are going to keep writing Selenium code, in increasing volume, and the training data
is not getting any younger. What we can do is make the current answer easy to find and easy
to enforce: a curated index for the machine, a rules file for the project, and a real browser
to check against.

Go and read [the new page](https://www.selenium.dev/documentation/ai_agents/), steal the
rules block, and adjust it for your project. If we have missed a pattern your agent keeps
getting wrong, open a PR on
[the docs repo](https://github.com/SeleniumHQ/seleniumhq.github.io) and tell us.

Review the diff like you would a new starter's, and keep your tests green.
