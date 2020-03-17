+++
Description = "<p>When we pushed the 2.0rc1 live, we really hoped that the next release would be 2.0 final. We very quickly got some feedback that encouraged us to push a 2.0rc2. Now, after just under three weeks, we’re launching a third and final release candidate. You can download it from the Selenium HQ site or directly […]</p>"
Title = "Selenium 2.0rc3: The “Next One’s The Big One” Release"
Date = 2011-06-27
Author = "shs96c"
AuthorLink = "https://twitter.com/shs96c"
+++

<p>When we pushed the 2.0rc1 live, we really hoped that the next release would be 2.0 final. We very quickly got some feedback that encouraged us to push a 2.0rc2. Now, after just under three weeks, we&#8217;re launching a third and final release candidate. You can download it from the <a href="http://seleniumhq.org/download/">Selenium HQ</a> site or directly from <a href="http://code.google.com/p/selenium/downloads/list">Google Code.</a></p>
<p>We think we&#8217;ve addressed many of the common issues, added some polish and added a host of bug fixes and minor changes, and we hope to hear your feedback! The following headline changes have been made in Selenium 2rc3:</p>
<ul>
<li>The deprecated RenderedWebElement interface has now been removed. Most of the functionality has been moved to either <a href="http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/WebElement.html">WebElement</a> or to the <a href="http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/interactions/Actions.html">Actions</a> class.</li>
<li>The deprecated WebElement.getValue() method has been removed. Use <a href="http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/WebElement.html#getAttribute(java.lang.String)">WebElement.getAttribute(&#8220;value&#8221;)</a> instead.</li>
<li>After some debate in the team, &#8220;<a href="http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/WebElement.html#setSelected()">WebElement.setSelected</a>&#8221; and &#8220;<a href="http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/WebElement.html#toggle()">WebElement.toggle</a>&#8221; have been deprecated. They will be removed in the final release.</li>
<li>Thanks to the hard work of Mozilla engineers, we now offer Firefox 5 support.</li>
<li>The <a href="http://www.opera.com/developer/tools/operadriver/">Opera driver</a>, developed by the lovely chaps at Opera Software, is bundled with this release.</li>
<li>Improvements in the way that mouse interactions are simulated, particularly when elements are outside the visible area of the page.</li>
</ul>
<p>As with almost all releases, there are still some issues left to resolve, but we&#8217;re working hard to make Selenium 2.0 as good as it can be.</p>

