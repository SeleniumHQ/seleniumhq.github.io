+++
Description = "<p>Hot off the heals of 1.0.2, we’re releasing Selenium Remote Control 1.0.3. You can download it now. There is no functional difference between this version and 1.0.2, other than it is packaged up a little nicer and we’ve clarified the relationship between selenium-server and the client drivers. That is: we are not releasing new client […]</p>"
Title = "Selenium 1.0.3 Released"
Date = 2010-02-23
Author = "plightbo"
AuthorLink = "https://twitter.com/plightbo"
tags = ["selenium","selenium-rc"]
categories = ["releases"]
+++

<p>Hot off the heals of <a href="http://seleniumhq.wordpress.com/2010/02/22/selenium-1-0-2-release-firefox-3-6-and-snow-leopard-support/">1.0.2</a>, we&#8217;re releasing Selenium Remote Control 1.0.3. You can <a href="http://selenium.googlecode.com/files/selenium-remote-control-1.0.3.zip">download it now</a>.</p>
<p>There is no functional difference between this version and 1.0.2, other than it is packaged up a little nicer and we&#8217;ve clarified the relationship between selenium-server and the client drivers. That is: we are <strong>not</strong> releasing new client drivers with future 1.x releases. The reason is that we locked down the API in version 1.0.1 and so there is no need to push out the same code each time. As such, when you download 1.0.3, you&#8217;ll see all the client drivers are labeled version 1.0.1, which is expected.</p>
<p>This release also is zipped up in a way that is compatible with all operating systems. The 1.0.2 release had some reported issues on Windows that have been fixed.</p>
<p>Finally, we also had many requests from <a href="http://maven.apache.org/">Maven</a> users. While we no longer use Maven to build Selenium, we are including pom releases for both the standalone server (ie: selenium-server.jar) and the &#8220;coreless&#8221; server (ie: selenium-server-coreless.jar). We hope they will be in the central repository shortly.</p>

