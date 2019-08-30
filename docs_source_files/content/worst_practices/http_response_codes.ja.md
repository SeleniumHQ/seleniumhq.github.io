---
title: "HTTP response codes"
weight: 3
---

{{% notice info %}}
<i class="fas fa-language"></i> ページは英語から日本語へ訳されています。
日本語は話せますか？プルリクエストをして翻訳を手伝ってください!
{{% /notice %}}

For some browser configurations in Selenium RC,
Selenium acted as a proxy between the browser
and the site being automated.
This meant that all browser traffic passed through Selenium
could be captured or manipulated.
The `captureNetworkTraffic()` method
purported to capture all of the network traffic between the browser
and the site being automated,
including HTTP response codes.

Selenium WebDriver is a completely different approach
to browser automation,
preferring to act more like a user
and this is represented in the way you write tests with WebDriver.
In automated functional testing,
checking the status code
is not a particularly important detail of a test's failure;
the steps that preceded it are more important.

The browser will always represent the HTTP status code,
imagine for example a 404 or a 500 error page.
A simple way to “fail fast” when you encounter one of these error pages
is to check the page title or content of a reliable point
(e.g. the `<h1>` tag) after every page load.
If you are using the page object model,
you can include this check in your class constructor
or similar point where the page load is expected.
Occasionally, the HTTP code may even be represented
in the browser's error page
and you could use WebDriver to read this
and improve your debugging output.

Checking the webpage itself is in line
with WebDriver's ideal practice
of representing and asserting upon the user’s view of the website.

If you insist, an advanced solution to capturing HTTP status codes
is to replicate the behaviour of Selenium RC by using a proxy.
WebDriver API provides the ability to set a proxy for the browser,
and there are a number of proxies that will
programmatically allow you to manipulate
the contents of requests sent to and received from the web server.
Using a proxy lets you decide how you want to respond
to redirection response codes.
Additionally, not every browser
makes the response codes available to WebDriver,
so opting to use a proxy
allows you to have a solution that works for every browser.
