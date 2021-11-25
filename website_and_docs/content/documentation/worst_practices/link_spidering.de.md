---
title: "Link spidering"
linkTitle: "Link spidering"
weight: 7
aliases: ["/documentation/de/worst_practices/link_spidering/"]
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to German. Do you speak German? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

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

