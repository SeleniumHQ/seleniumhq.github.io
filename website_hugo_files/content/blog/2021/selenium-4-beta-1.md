---
title: "Selenium 4 Beta 1 Released"
linkTitle: "Selenium 4 Beta 1 Released"
date: 2021-02-15
tags: ["beta", "selenium", "status"]
categories: ["general", "releases"]
author: Simon Stewart ([@shs96c](https://twitter.com/shs96c))
description: >
  We're very happy to announce the release of the first beta of Selenium 4.
---


We're very happy to announce the release of the first beta of Selenium
4. We're shipping this for Java, .Net, Python, Ruby, and JavaScript,
so if you're using any of those languages, go and grab it from your
package manager of choice!

This has been the culmination of a lot of work by so many people, not
only the [project TLC][tlc], but also of literally hundreds of people:
205 since we released Selenium 3.141.59, at the last count. A big
thank you to everybody who's helped make this possible!

So, what's changed since Selenium 3? The answer is both "**not much**"
and also "**almost everything**".

By "**not much**", I mean that if your tests are working with Selenium
3 right now, you should be able to just upgrade your dependency to
Selenium 4. You will find that things that were marked "deprecated"
are now gone, but the advantage of the long time between the last
Selenium 3 release and this is that you've had plenty of time to try
and find alternatives.

If this doesn't work, please let us know! We've worked hard to ensure
compatibility between the releases, but it's possible we may have
missed some things.

One thing you may want to do to get ready for the update (which you
can do before updating the dependency itself!) is to update the
drivers you need. In particular, please update [geckodriver][] to
0.29.0 or later.

By "**almost everything**", I mean that under the covers there have
been substantial changes. We've rewritten the Selenium server to allow
it to work not only in the familiar "standalone" and "hub and node"
modes, but also in a new "distributed" mode, which makes it
signifcantly easier to deploy to something such as Kubernetes in a way
that scales well.

The new server is also wired up with support for [OpenTelemetry][] and
exposes a [GraphQL endpoint][graphql], so that figuring what's going
on in the Grid, and tracking down what's gone wrong if something
happens, is easier than ever.

Not all the changes are server-side. We recently wrote about the [new
features in Selenium 4][se4] that you can use in your tests, but some
of the main highlights are:

  * Relative locators, for finding elements using terms that make
    sense to us humans.
  * The ability to intercept network traffic
  * Authentication with basic or digest authentication.

We'll be telling you more about these features in later blog posts,
and as we improve our documentation.

If this sounds interesting, please download the beta from your
favourite package manager (maven, nuget, npm, pip, or the gem), or
directly from the [Selenium site][download].

[download]: /downloads
[geckodriver]: https://github.com/mozilla/geckodriver/releases
[graphql]: https://github.com/SeleniumHQ/selenium/blob/selenium-4.0.0-beta-1/java/server/src/org/openqa/selenium/grid/graphql/selenium-grid-schema.graphqls
[OpenTelemetry]: https://opentelemetry.io
[se4]: /blog/2020/what-is-coming-in-selenium-4-new-tricks/
[tlc]: /structure/#tlc
