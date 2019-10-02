---
title: "Link spidering"
weight: 7
---

Using WebDriver to spider through links
is not a recommended practice. Not because it cannot be done,
but because WebDriver is definitely not the most ideal tool for this.
WebDriver needs time to start up,
and can take several seconds, up to a minute
depending on how your test is written,
just to get to the page and traverse through the DOM.

Instead of using WebDriver for this,
you could save a ton of time
by executing a [curl](//curl.haxx.se/) command,
or using a library such as BeautifulSoup
since these methods do not rely
on creating a browser and navigating to a page.
You are saving tonnes of time by not using WebDriver for this task.

