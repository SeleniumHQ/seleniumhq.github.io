+++
Description = "<p>It’s been a while since the last Selenium release, but I’m happy to announce that Selenium 2.22 is now available for download. This is a big release for us and features two major changes. The first is that Selenium 2.22 is the first version that requires Java 6 in order to run. This has been […]</p>"
Title = "Announcing Selenium 2.22"
Date = 2012-05-29
Author = "shs96c"
AuthorLink = "https://twitter.com/shs96c"
tags = ["selenium"]
categories = ["releases"]
+++

<p>It&#8217;s been a while since the last Selenium release, but I&#8217;m happy to announce that Selenium 2.22 is now <a href="http://seleniumhq.org/download/">available for download</a>. This is a big release for us and features two major changes.</p>
<p>The first is that<strong> Selenium 2.22 is the first version that requires Java 6</strong> in order to run. This has been the case for the Selenium Server for some time, but this is the first time the client code has required Java 6. Since Java 5 was <a href="http://www.oracle.com/technetwork/java/eol-135779.html">&#8220;end of lifed&#8221; in 2009</a>, we don&#8217;t expect this to impact many users.</p>
<p>The second major change is that <strong>we are now providing a standalone IE server for use with the WebDriver API</strong>, similar to the one used by the chrome driver. You can get it from the normal download page. This will allow us to update our IE support independently of the rest of the library (again, mirroring how Chrome is supported) For now, there&#8217;s a legacy fallback mode you can use that&#8217;ll use the same DLL we&#8217;ve always used which can be activated by setting the DesiredCapability &#8220;useLegacyInternalServer&#8221; to boolean &#8220;true&#8221; when requesting your IE Driver instance.</p>
<p>Of course, as well as these major changes, there&#8217;s the usual host of updates and improvements. We&#8217;re continuing to refine the new SafariDriver, and we&#8217;re happy to announce native events for Firefox 12. You can check out the other updates in the <a href="http://code.google.com/p/selenium/source/browse/trunk/java/CHANGELOG">CHANGELOG</a>.</p>

