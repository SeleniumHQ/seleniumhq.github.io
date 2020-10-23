+++
Description = "<p>We’ve just released Selenium 2.0b2. If you’re the impatient sort who loves to have the latest and greatest, head over to the download site and get it while it’s hot. If you’re a Python user, then all you need to do is a simple “pip install -U selenium”. Ruby users can, as ever, simply run […]</p>"
Title = "Selenium 2.0b2 Released"
Date = 2011-02-15
Author = "shs96c"
AuthorLink = "https://twitter.com/shs96c"
tags = ["selenium","beta"]
categories = ["releases"]
+++

<p>We&#8217;ve just released Selenium 2.0b2. If you&#8217;re the impatient sort who loves to have the latest and greatest, head over to the<a href="http://code.google.com/p/selenium/downloads/list"> download site</a> and get it while it&#8217;s hot. If you&#8217;re a Python user, then all you need to do is a simple &#8220;pip install -U selenium&#8221;. Ruby users can, as ever, simply run &#8220;gem install selenium-webdriver&#8221;. Maven users need to wait just a little bit longer: we&#8217;re going to be checking the release in ASAP.</p>
<p>Between beta 1 and beta 2, we held a week-long Bug Bash, during which we closed a significant number of bugs. From a user&#8217;s perspective, other highlights include:</p>
<ul>
<li>A more stable, capable iPhone driver.</li>
<li>Updated <a href="http://code.google.com/p/selenium/downloads/detail?name=selenium-server-2.0b2.zip&amp;can=2&amp;q=">Android driver</a>.</li>
<li>Improved python bindings for Selenium WebDriver. The namespace is now &#8220;selenium.webdriver&#8221;</li>
<li>Added &#8220;<a href="http://selenium.googlecode.com/svn/trunk/docs/api/java/com/thoughtworks/selenium/Selenium.html#getCssCount(java.lang.String)">Selenium.getCssCount</a>&#8221; to mirror &#8220;Selenium.getXpathCount&#8221;</li>
<li>&#8220;<a href="http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/WebElement.html#getText()">WebElement.getText()</a>&#8221; performs more consistently across different browsers.</li>
<li>Mono users can use the .Net bindings</li>
<li>Continued to improve the <a href="http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/WebDriverBackedSelenium.html">WebDriverBackedSelenium</a>. If you&#8217;re looking to migrate from Selenium 1 to Selenium 2, and want to take your time, this is a useful stepping stone.</li>
<li>Reworked the Advanced User Interactions APIs. The big change is that the WebDriver APIs no longer rely on classes from the AWT.</li>
<li>.Net users now have more support classes, to make writing tests less tiresome.</li>
<li>The remote webdriver makes better use of sockets, which improves stability and scalability on Windows.</li>
<li>Started to add support for driving multiple IE instances. This is considered experimental, but we&#8217;d love to hear it&#8217;s working for you!</li>
</ul>
<p>If you&#8217;re interested in the guts of Selenium 2 and how it worked, then you might find these interesting:</p>
<ul>
<li>Continued reworking the IE and iPhone drivers to use the Automation Atoms.</li>
<li>Reworked the structure of the source tree to be more language focused.</li>
<li>We have the skeleton of a webdriver-backed selenium for Python.</li>
</ul>
<p>As you can see, this is a big release. Beta 3 should be out a lot more quickly, and will be focusing on improving support for IE 9 and Firefox 4. Over the course of the 2.0b3 development, we shall also be removing as many deprecated methods as possible, so be sure to remove deprecation warnings from your builds when using 2.0b2!</p>

