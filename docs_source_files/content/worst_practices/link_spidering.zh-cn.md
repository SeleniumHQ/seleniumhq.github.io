---
title: "Link spidering"
weight: 7
---

{{% notice info %}}
<i class="fas fa-language"></i> 页面需要从英语翻译为简体中文。
您熟悉英语与简体中文吗？帮助我们翻译它，通过 pull requests 给我们！
{{% /notice %}}

Using WebDriver to spider through links
is not a recommended practice not because it cannot be done,
but because it’s definitely not the most ideal tool.
WebDriver needs time to start up,
and can take several seconds up to a minute
depending on how your test is written,
just to get to the page and traverse through the DOM.

Instead of using WebDriver for this,
you could save a ton of time
by executing a [curl](//curl.haxx.se/) command,
or using a library such as BeautifulSoup
since these methods don’t rely
on creating a browser and navigating to a page.
You are saving tonnes of time by not using WebDriver for this task.

