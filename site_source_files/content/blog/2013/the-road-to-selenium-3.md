+++
Description = "<p>Selenium 2 was released in July 2011. It’s now two years old, and what a couple of years it’s been! The WebDriver APIs, which were the major addition in Selenium 2, are now the basis for a W3C standard, and there are implementations written and supported by Google, Mozilla and Opera. There have been 34 […]</p>"
Title = "The Road to Selenium 3"
Date = 2013-08-28
Author = "shs96c"
AuthorLink = "https://twitter.com/shs96c"
tags = ["selenium"]
categories = ["general"]
+++

<p dir="ltr">Selenium 2 was <a href="http://seleniumhq.wordpress.com/2011/07/08/selenium-2-0/">released in July 2011</a>. It’s now two years old, and what a couple of years it’s been! The WebDriver APIs, which were the major addition in Selenium 2, are now the basis for a <a href="http://www.w3.org/TR/webdriver/">W3C standard</a>, and there are implementations written and supported by Google, Mozilla and Opera. There have been 34 releases, with official support for Java, C#, Python, Ruby and Javascript, and the community has stepped in to provide bindings for Perl, PHP and others. There have been 57 different people authoring changes in the code base, and countless more participating in the online forums, offering help and advice.</p>
<p>While all this has been happening, the world has moved on, and now it’s time for the Selenium project to look to the future. It’s with great pleasure that I can now say that we’re working towards Selenium 3.</p>
<p>We aim for Selenium 3 to be “a tool for user-focused automation of mobile and web apps”.</p>
<p>What does this mean? For mobile users, the Selenium project will be hosting a suite of tests to facilitate interoperability between the many different projects available that are extending the WebDriver API to also cope with mobile. Developers from projects such as <a href="http://appium.io/">Appium</a>, <a href="http://ios-driver.github.io/ios-driver/">ios-driver</a> and <a href="http://selendroid.io/">selendroid</a> will be working on the suite of tests to enable this.</p>
<p>We’ll also be working on making the technology behind Selenium as stable and capable as possible. For this reason, Selenium 3 will see the removal of the original Selenium Core implementations, and consequently we’ll be deprecating the RC APIs too. The old versions will still be available as a separate download, but active development will cease, except for very urgent fixes. We will still be providing an implementation of the RC APIs backed by WebDriver, so you can continue running your existing tests, but now would be a great time to make the move to using the WebDriver APIs directly.</p>
<p dir="ltr">For those of you exporting your tests from IDE and running the HTML suites, we’ll provide an alternative runner that allows you to continue running those tests too, though it’ll be backed by the same “WebDriver-backed” RC implementation as offered by the main download. Again, the original implementation will be available as a download, but it will no longer be actively developed once we release 3.0.</p>
<p>Our current plan is to start shipping 3.0 by Christmas this year: it’s going to be a lot of fun!</p>

