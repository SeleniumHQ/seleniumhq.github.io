+++
Description = "<p>We recently released the first of the betas for Selenium 2. It’s available for Java, C# and Ruby. If you’ve been holding off trying Selenium 2 because of the alpha label, then the biggest improvement you’ll see is with the new WebDriver APIs, but there’s a lot more! A promise of relatively stable APIs For […]</p>"
Title = "Selenium 2.0 beta 1 Release"
Date = 2010-12-24
Author = "shs96c"
AuthorLink = "https://twitter.com/shs96c"
+++

<p>We recently released the first of the betas for Selenium 2. It&#8217;s available for Java, C# and Ruby. If you&#8217;ve been holding off trying Selenium 2 because of the alpha label, then the biggest improvement you&#8217;ll see is with the new WebDriver APIs, but there&#8217;s a lot more!</p>
<ul>
<li>A promise of relatively stable APIs</li>
<li>For Firefox only right now, an API for dealing with <a href="http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/WebDriver.TargetLocator.html#alert()">alerts, prompts and confirms</a>.</li>
<li>A brand new IE driver.</li>
<li>Better <a href="http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/WebDriverBackedSelenium.html">selenium emulation</a> when using webdriver</li>
<li>And a better implementation of webdriver&#8217;s API backed by the <a href="http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/SeleneseCommandExecutor.html">traditional Selenium technology</a>.</li>
<li>Ubiquitous use of <a href="http://sizzlejs.com/">Sizzle</a> for emulating CSS selectors where native CSS selectors aren&#8217;t supported</li>
<li>The <a href="http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/interactions/package-frame.html">advanced user interactions</a> API</li>
<li>An update to the AndroidDriver&#8217;s Android app.</li>
</ul>
<p>Of course, we&#8217;d love this release to be completely bug free, but this is, after all, a beta, so there are some known issues:</p>
<ul>
<li>The selenium 1.x emulation using IE is a little flaky.</li>
<li>The ChromeDriver is not as capable as the others.</li>
<li>Anything in our <a href="http://code.google.com/p/selenium/issues/list">bug list</a>.</li>
</ul>
<p>You can download it from here:</p>
<p><a href="http://code.google.com/p/selenium/downloads/list" target="_blank">http://code.google.com/p/selenium/downloads/list</a></p>
<p>You can read the <a href="http://selenium.googlecode.com/svn/trunk/docs/api/java/index.html">javadocs</a> and the <a href="http://selenium.googlecode.com/svn/trunk/docs/api/rb/index.html">ruby docs</a>.</p>
<p>An obvious question is &#8220;When will the beta end?&#8221; The short answer is when we&#8217;ve implemented the alerts and prompts and advanced user interactions APIs in all supported browsers. We expect there to be some flex in some APIs (removing deprecated methods, and within the advanced user interactions API) but what you have here is basically what you&#8217;re going to get when we hit 2.0 final. I have no idea how long this will take, but if you&#8217;re interested in helping out, <a href="http://groups.google.com/group/selenium-developers">let us know!</a></p>
<p>Thanks are due to all the committers who have worked so hard to get this code into shape. Thanks are also due to all the people who have taken the time to file bugs, ask for features and participated on the mailing lists. Thank you to you too, for going out and trying this new beta of Selenium 2.</p>
<p>You rock. 🙂</p>

