+++
Description = "<p>At SeleniumConf in 2013, we announced that a new major version of Selenium would be released “by Christmas”. Fortunately, we never said which Christmas, as it has taken us a while to make all the changes we wanted to make! We’re excited to announce the release of the first beta — Selenium 3.0.0-beta1. We’d love […]</p>"
Title = "Announcing Selenium 3.0-beta1"
Date = 2016-07-29
Author = "shs96c"
AuthorLink = "https://twitter.com/shs96c"
tags = ["selenium","beta"]
categories = ["general","releases"]
+++

<p>At SeleniumConf in 2013, we announced that a new major version of Selenium would be released &#8220;by Christmas&#8221;. Fortunately, we never said which Christmas, as it has taken us a while to make all the changes we wanted to make! We&#8217;re excited to announce the release of the first beta &#8212; Selenium 3.0.0-beta1.</p>
<p>We&#8217;d love you to try it out on your projects, and provide us with feedback on where the rough edges are before we ship the 3.0 itself! Please remember that this is a beta release, so your feedback is incredibly helpful and valuable in order to help us smooth any rough edges.</p>
<p>For the last six years we&#8217;ve been advising users to switch to the newer WebDriver APIs and to stop using the original RC APIs. With Selenium 3.0, the original implementation of RC has been removed, replaced by one that sits on top of WebDriver. For many users, this change will go completely unnoticed, as they&#8217;re no longer using the RC APIs. For those of you who still are, we&#8217;ve done our best to make the change as smooth as possible, but <a href="https://github.com/seleniumhq/selenium/issues">we welcome high quality bug reports</a> to help us fix any problems that occur. Maven users will need to add a dependency on the new &#8220;<a href="http://docs.seleniumhq.org/download/maven.jsp">leg-rc</a>&#8221; package to access the old RC APIs.</p>
<p>There are some other changes that you might need to be aware of:</p>
<ul>
<li><strong>You&#8217;ll need to be running Java 8</strong> to use the Java pieces of Selenium. This is the oldest version of Java officially supported by Oracle, so hopefully you&#8217;re using it already!</li>
<li>Support for Firefox is via Mozilla&#8217;s <a href="https://github.com/mozilla/geckodriver/releases">geckodriver</a>.</li>
<li>Support for Safari is provided on macOS (Sierra or later) via Apple&#8217;s own <a href="https://developer.apple.com/library/prerelease/content/releasenotes/General/WhatsNewInSafari/Articles/Safari_10_0.html">safaridriver</a>.</li>
<li>Support for Edge is provided by MS through their <a href="https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/">webdriver server</a>.</li>
<li>Only versions 9 or above of IE are supported. Earlier versions may work, but are no longer supported as <a href="https://www.microsoft.com/en-gb/WindowsForBusiness/End-of-IE-support">MS no longer supports them</a>.</li>
</ul>
<p>We&#8217;ll be posting more information about Selenium 3.0 to this blog soon, but until then if you&#8217;re interested in learning more then <a href="https://www.youtube.com/watch?v=bistojJPR98">a recent webinar by Simon</a> is a great place to start.</p>

