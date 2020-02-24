+++
Description = "<p>I’m pleased to announce the release of Selenium 2.0a5, available for immediate download. This release brings a host of changes under the hood, and represents the efforts of many contributors. Highlights include: New interfaces for dealing with HTML 5 elements. An API for “implicit waits“: quietly waiting until an element is present before continuing with […]</p>"
Title = "Selenium 2.0a5 Released"
Date = 2010-07-14
Author = "shs96c"
AuthorLink = "https://twitter.com/shs96c"
+++

<p>I&#8217;m pleased to announce the release of Selenium 2.0a5, available for <a href="http://code.google.com/p/selenium/downloads/list">immediate download</a>. This release brings a host of changes under the hood, and represents the efforts of many contributors. Highlights include:</p>
<ul>
<li>New<a href="http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/html5/package-frame.html"> interfaces</a> for dealing with HTML 5 elements.</li>
<li>An API for &#8220;<a href="http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/WebDriver.Timeouts.html">implicit waits</a>&#8220;: quietly waiting until an element is present before continuing with a test. You can use them like this: <code>driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS)</code></li>
<li>A revamped Firefox driver.</li>
<li>More shared code between Selenium and WebDriver.</li>
<li>You can now pass firefox profiles to the remote webdriver (this includes extensions and proxy settings!)</li>
<li>Improved .Net bindings: lots of updates to help bring them more in-line with the Java equivalents.</li>
</ul>
<p>Waiting in the wings for release soon is an AndroidDriver, which opens up the world of testing webapps on Android devices through the Selenium WebDriver API.</p>
<p>If you&#8217;re a pythonista or rubyist, you&#8217;ve not been left out of this bonaza of new hotness. There have been regular updates for these languages, which can be installed via &#8220;easy_install -U selenium&#8221; or &#8220;gem install selenium-webdriver&#8221; depending on your language of choice.</p>
<p>Hopefully the next alpha will be the last before we plunge bravely into the betas. Exciting times are ahead!</p>

