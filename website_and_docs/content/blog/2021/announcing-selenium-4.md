---
title: "Announcing Selenium 4"
linkTitle: "Announcing Selenium 4"
date: 2021-10-13
tags: ["selenium", "status"]
author: Simon Stewart ([@shs96c](https://twitter.com/shs96c))
description: >
  Selenium 4.0.0 is released! Get it while it's hot!
---

It’s with very great pleasure that **we are announcing the release of
Selenium 4**. This is available for Java, .Net, Python, Ruby, and
Javascript. You can download it from your favourite package manager or
right [from GitHub][github]!

If you’re already a Selenium user, this update should be as easy as
just changing your dependency from 3.x to 4.0.0. We’ve worked hard to
ensure that this is a “drop-in” upgrade, having focused on keeping the
public APIs as stable as possible.

Of course, we’ve made changes, so if you relied on code that was
marked as internal to Selenium, or that was deprecated, you might
experience some hiccups. Please [check our documentation][docs] for
advice on how to deal with each of the common problems we’re aware of.

But there’s more to Selenium 4 than just being a stable release of
what was there before! It brings a whole host of new and exciting
features that we hope will make your tests more fun to write, and more
stable when you run them. Let’s take a look at some of them!

We’ve introduced “[relative locators][relative locators]”. These allow you to specify
where on the page an element can be found using language that people
use too; things like “above that element”, or “to the right of this
other element”. This will hopefully provide you all with a tool to
fight against incredibly complex locators, making your tests read a
little more clearly, and being more resilient to changes in the page’s
DOM. We’re not the first ones to come up with this idea -- that honour
belongs to [Sahi][sahi] -- but if you’ve not used them before, we hope
you like them!

If you’re using [Firefox][firefox] or a browser that is derived from
[Chromium][chromium], we’ve added a slew of new capabilities
too. These include ways of handling [“basic” and “digest”
authentication][auth], Network Interception ([Are you an HTTP
418?][teapot]), and also performing commonly requested tasks, like
waiting for a [change in the DOM][mutation], or providing a way to
look at [Javascript errors][js errors].

We’ve added these new features in a way that fits in with our existing
APIs. There’s no need to rewrite your tests: just use the new features
when it feels right to you.

We have also rebuilt the [Selenium Grid][grid], taking lessons from
successful projects such as [Zalenium][zalenium] and
[Selenoid][selenoid] to enhance the capabilities. This new Grid runs
just as well as a single process, running on a single machine, as it
does in the traditional “hub and node” configuration, but it also
supports a fully-distributed mode, for use in modern infrastructure
running Kubernetes. It has better security baked-in, because we know
that securing a Grid can be a difficult task. And at all of these
scales and sizes, all the new features we’ve added to the language
bindings will work as expected.

The Grid can also manage Docker containers on your local machine,
pulling images such as the [standalone firefox server][docker] so your
infrastructure maintenance becomes just a little bit less taxing.

Finally, the Grid is easier to manage. We’ve revamped the UI, placing
it on top of a GraphQL model that anyone is free to query and make use
of to create their own visualisations or monitors of the Grid. If
you’d like to peek into a running session, there are live VNC previews
you can open and interact with, providing even better insight into
what’s been going on. And if you want even more information, we’ve
integrated support for [OpenTelemetry][otel] into the Grid, so now you
can find out exactly what’s happening, where, and when.

I know it’s a cliche to say that it’s a “very great pleasure”, but,
being honest with you, it genuinely is. Working on this new version of
Selenium has been a chance to work with some amazing engineers, and to
be part of a vibrant and energetic community. It’s been a lot of fun
to write this code, with these people, and it feels right to say
“thank you” to as many of them as possible here. So, without waiting
any longer....

We would like to thank all of our users who have helped Selenium be
successful over the years. Without you we wouldn’t be where we are
today. We would also like to thank all the contributors who have
submitted [Pull Requests][pr], your contributions make Selenium better. For
everyone who’s taken the time to file an issue, and to let us know
where there’s been a problem: thank you. We only have a chance to
improve when we know that there’s something that needs work!

And finally, thank you to all the Selenium Committers,
[BrowserStack][browserstack], [Sauce Labs][saucelabs], and our
[Selenium-Level sponsors][sponsors] for getting this release ready for
our users.

**We hope you enjoy Selenium 4, and we can’t wait to see what you do
with it!**

[auth]: /documentation/webdriver/bidirectional/bidi_api/#register-basic-auth
[browserstack]: https://www.browserstack.com/
[chromium]: https://www.chromium.org/Home
[docker]: https://hub.docker.com/u/selenium
[docs]: /documentation/getting_started/how_to_upgrade_to_selenium_4/
[firefox]: https://www.mozilla.org/en-GB/firefox/new/
[github]: https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.0.0
[grid]: /documentation/grid/
[js errors]: /documentation/webdriver/bidirectional/bidi_api/#listen-to-js-exceptions
[mutation]: /documentation/webdriver/bidirectional/bidi_api/#mutation-observation
[otel]: https://opentelemetry.io
[pr]: https://github.com/SeleniumHQ/selenium/pulls
[relative locators]: /documentation/webdriver/locating_elements/#relative-locators
[sahi]: https://www.sahipro.com
[saucelabs]: https://saucelabs.com
[selenoid]: https://aerokube.com/selenoid/latest/
[sponsors]: https://www.selenium.dev/sponsors/
[teapot]: https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/418
[zalenium]: https://opensource.zalando.com/zalenium/
