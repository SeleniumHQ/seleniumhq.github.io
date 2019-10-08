---
title: "File downloads"
weight: 2
---

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