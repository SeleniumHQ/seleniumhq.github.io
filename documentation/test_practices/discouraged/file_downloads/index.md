# File downloads

URL: https://www.selenium.dev/documentation/test_practices/discouraged/file_downloads/
Site index: https://www.selenium.dev/llms.txt

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
[curl](https:////curl.se/).

The [HtmlUnit driver](https://github.com/SeleniumHQ/htmlunit-driver) can download attachments 
by accessing them as input streams by implementing the 
[AttachmentHandler](https://htmlunit.sourceforge.io/apidocs/com/gargoylesoftware/htmlunit/attachment/AttachmentHandler.html) 
interface. The AttachmentHandler can then be added to the [HtmlUnit](https://htmlunit.sourceforge.io/) WebClient.
