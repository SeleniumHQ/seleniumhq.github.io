---
title: "Selenium 4 Release Candidate 2"
linkTitle: "Selenium 4 Release Candidate 2"
date: 2021-10-02
tags: ["selenium", "status"]
author: Simon Stewart ([@shs96c](https://twitter.com/shs96c))
description: >
  The second (and last!) release candidate of Selenium 4 is here!
---

We've listeneed and responded to the feedback from the first release
candidate of Selenium 4, and we're now happy to announce the second
release candidate of Selenium 4. This is shipping for .Net, Java,
Python, Ruby, and Javascript, and it's available from all the popular
package managers. Go! Try it out!

Unless we encounter a very serious issue or bug, this is likely to be
the last release before we push Selenium 4.0.0 itself. What does this
mean for you? It's your last chance to try it on for size and let us
know if there are any problems.

In this release, we've fixed a nasty issue where closing the first
window would cause CDP-related features to break, and have landed a
few other cleanups and fixes. 

If you're a Firefox user, please make sure you're using the very
latest version of the [geckodriver][] If you don't, there may be
problems starting sessions as we rely on new features that are
included in this new geckodriver release.

If you're new Selenium 4, then you may want to read this [blog
post][], which explains more, but you might want to have a look at:

  * Relative locators, which provide a way of finding elements by
    where they are in relation to one another (like "above", or "to
    the right of")
  * Authentication on websites using basic or digest authentication
  * Network interception, which provides an easy easy way to capture
    and modify HTTP traffic from the browser, as well as making it
    possible to get HTTP status codes.

Thank you to everyone in the community who's given us so much helpful
and thoughtful feedback on the first release candidate. We really hope
that you enjoy this release candidate too!

{{< tweet user="AutomatedTester" id="1443577793871179786" >}}

{{< tweet user="jimevansmusic" id="1443601790092681221" >}}

{{< tweet user="titusfortner" id="1443575623822151688" >}}

{{< tweet user="shs96c" id="1443606189791662080" >}}


[geckodriver]: https://github.com/mozilla/geckodriver/releases
[blog post]: /blog/2020/what-is-coming-in-selenium-4-new-tricks/
