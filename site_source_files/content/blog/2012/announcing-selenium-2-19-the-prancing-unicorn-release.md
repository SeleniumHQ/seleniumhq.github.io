+++
Description = "<p>You might be pleased to hear that Selenium 2.19 has been released (download it from here!). There’s one big user facing changing that we’d like to tell you about: the webdriver-backed selenium can now be used in supported languages. By providing this capability, it’s possible to migrate from RC to the WebDriver APIs without rewriting […]</p>"
Title = "Announcing Selenium 2.19: the Prancing Unicorn release"
Date = 2012-02-08
Author = "shs96c"
AuthorLink = "https://twitter.com/shs96c"
+++

<p>You might be pleased to hear that Selenium 2.19 has been released (<a href="http://seleniumhq.org/download/">download it from here!</a>). There&#8217;s one big user facing changing that we&#8217;d like to tell you about: the webdriver-backed selenium can now be used in supported languages.</p>
<p>By providing this capability, it&#8217;s possible to migrate from RC to the WebDriver APIs without rewriting all your tests in one fell swoop (which must be a Good Thing, right?) An example of how to use it in Python would be:</p>
<pre class="brush: python; title: ; notranslate" title="">
driver = RemoteWebDriver(desired_capabilities = DesiredCapabilities.FIREFOX)
selenium = DefaultSelenium('localhost', 4444', '*webdriver', 'http://www.google.com')
selenium.start(driver = driver)
</pre>
<p>Provided you keep a reference to the original webdriver and selenium objects you created you can use the two APIs interchangeably.  You&#8217;ll see that the magic is the &#8220;*webdriver&#8221; browser name passed to the selenium instance, and that we pass the webdriver instance when calling start().</p>
<p>We hope you like it!</p>
<p>PS: I have no idea why this is the Prancing Unicorn release, but it&#8217;s been a while since we named one 🙂</p>

