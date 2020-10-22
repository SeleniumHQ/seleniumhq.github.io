+++
Description = "<p>We’re very happy to announce the first Release Candidate for Selenium 2, available for Java, C#, Ruby and Python. The API has been stabilised and the functionality needed for the final 2.0 release is mostly in. We’re going to be working hard to get there as soon as possible, but now’s the perfect time to […]</p>"
Title = "Selenium 2.0rc1: The Grid Release"
Date = 2011-06-01
Author = "shs96c"
AuthorLink = "https://twitter.com/shs96c"
+++

<p>We&#8217;re very happy to announce the first Release Candidate for Selenium 2, available for Java, C#, Ruby and Python. The API has been stabilised and the functionality needed for the final 2.0 release is mostly in. We’re going to be working hard to get there as soon as possible, but now’s the perfect time to test the waters and provide us with any feedback you may have! <a href="http://code.google.com/p/selenium/downloads/list">Grab the downloads from the site</a>!</p>
<p>Highlights:</p>
<ul>
<li><a href="http://code.google.com/p/selenium/wiki/Grid2">Grid 2</a>: A major feature of this release is Grid 2, an implementation of the Selenium Grid that supports WebDriver&#8217;s wire protocol, allowing tests using Selenium WebDriver to be distributed through it. There are some <a href="http://code.google.com/p/selenium/wiki/Grid2">docs to help you get started</a> on the wiki.</li>
<li>New <a href="http://code.google.com/p/selenium/wiki/ChromeDriver">ChromeDriver</a>: Following a complete rewrite of the ChromeDriver, Selenium 2 is now supported natively by the Chrome browser itself. In order to use this, you must download the <a href="http://code.google.com/p/selenium/downloads/list">chromedriver executable</a> from the Selenium project site.</li>
<li><a href="http://www.opera.com/developer/tools/operadriver/">OperaDriver</a> support: We&#8217;ve bundled the most excellent OperaDriver into the release to make it easy to get started testing with <a href="http://www.opera.com/browser/">Opera</a>.</li>
<li>Support for native events in Firefox 4.</li>
<li><a href="http://code.google.com/p/selenium/wiki/AdvancedUserInteractions">Advanced User Interactions</a>: An API that allows you to model complex user interactions, such as clicking on an element, holding the shift key, clicking on three more, and then dragging the four elements to a final destination. The entry point to this API is the <a href="http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/interactions/Actions.html">Actions</a> class.</li>
</ul>
<p>We’ve also deleted all methods that were deprecated in 2.0b3 and have marked a number of methods and classes (notably <a href="http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/RenderedWebElement.html">RenderedWebElement</a> and <a href="http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/WebElement.html#getValue()">WebElement.getValue</a>) deprecated. These will be deleted in the next release.</p>
<p>Known issues:</p>
<ul>
<li>Native events on Linux may not work properly on tests that include alerts and prompts.</li>
<li>Mouse actions using the Advanced User Interactions API may not work properly for elements that have to be scrolled into view.</li>
</ul>
<p>We plan on making our releases more frequent in the run up to 2.0final and polish off the bugs and issues. Stay tuned! This is going to be fun 🙂</p>

