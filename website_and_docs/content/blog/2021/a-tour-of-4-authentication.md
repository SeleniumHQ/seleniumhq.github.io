---
title: "A Tour of 4: Authentication"
linkTitle: "A Tour of 4: Authentication"
date: 2021-10-10
tags: ["selenium", "webdriver"]
author: Simon Stewart ([@shs96c](https://twitter.com/shs96c))
description: >
  Introducing how Selenium 4 handles authentication
---

Before we ship Selenium 4, I thought it would be nice to introduce
some of the new features in-depth. Of course, all of the features
we're covering are in the main [WebDriver documentation][], so if
you're not one for reading blog posts, then feel free to dive in
there!

The first thing I'd like to introduce you to is Selenium 4's new-found
ability to authenticate to websites. We've always been able to handle
"form-based" authentication, where a login page has some `INPUT`
elements that you need to enter the user name and password, but
handling sites that use [basic or digest
authentication][authentication] has always been harder. When using
Selenium 3, the advice has been to get the login cookie and set it on
your session before accessing the site, but you can now just call a
`register` method to add a user name and password (or, in the future,
other kinds of authentication credentials)

In short, in Selenium 4, the process has become less
complicated. Perhaps an example will help?

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
// This "HasAuthentication" interface is the key!
HasAuthentication authentication (HasAuthentication) driver;

// You can either register something for all sites
authentication.register(() -> new UsernameAndPassword("admin", "admin"));

// Or use something different for specific sites
authentication.register(
  uri -> uri.getHost().contains("mysite.com"),
  new UsernameAndPassword("AzureDiamond", "hunter2"));
  {{< /tab >}}
  {{< tab header="Ruby" >}}
driver.register(username: 'admin', password: 'admin')
  {{< /tab >}}
{{< /tabpane >}}

Once you've done this, every time the `driver` loads a page that needs
authentication, it will automatically use the user name and password
you've given it.

This feature is currently implemented on top of Selenium 4's [CDP][]
support, and so only works on those browser that support that
protocol, but as we push forwards with development of [WebDriver
Bidi][] implementations will switch to that when they can.

[authentication]: https://developer.mozilla.org/en-US/docs/Web/HTTP/Authentication
[CDP]: https://chromedevtools.github.io/devtools-protocol/
[WebDriver Bidi]: https://w3c.github.io/webdriver-bidi/
[WebDriver documentation]: /documentation/webdriver/
