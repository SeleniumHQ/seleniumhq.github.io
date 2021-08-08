---
title: "File downloads"
linkTitle: "File downloads"
weight: 2
aliases: ["/documentation/de/worst_practices/file_downloads/"]
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to German. Do you speak German? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

Whilst it is possible to start a download
by clicking a link with a browser under Selenium's control,
the API does not expose download progress,
making it less than ideal for testing downloaded files.
This is because downloading files is not considered an important aspect
of emulating user interaction with the web platform.
Instead, find the link using Selenium
(and any required cookies)
and pass it to a HTTP request library like
[libcurl](//curl.haxx.se/libcurl/).

The [HtmlUnit driver](https://github.com/SeleniumHQ/htmlunit-driver) can download attachments 
by accessing them as input streams by implementing the 
[AttachmentHandler](https://htmlunit.sourceforge.io/apidocs/com/gargoylesoftware/htmlunit/attachment/AttachmentHandler.html) 
interface. The AttachmentHandler can the be added to the [HtmlUnit](https://htmlunit.sourceforge.io/) WebClient.
