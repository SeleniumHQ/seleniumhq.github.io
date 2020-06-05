+++
Description = "<p>Although the WebDriver APIs started life as just a mechanism for automating web browsers, over the past few years it has been extended to also work on mobile devices. Projects such as Appium, iosdriver, and Selendroid have all shown that this approach works, and works well. On the Web, if you start using Selenium WebDriver […]</p>"
Title = "Mobile WebDriver"
Date = 2013-08-28
Author = "shs96c"
AuthorLink = "https://twitter.com/shs96c"
tags = ["selenium","mobile"]
categories = ["general"]
+++

<p>Although the WebDriver APIs started life as just a mechanism for automating web browsers, over the past few years it has been extended to also work on mobile devices. Projects such as Appium, iosdriver, and Selendroid have all shown that this approach works, and works well. On the Web, if you start using Selenium WebDriver with one browser (Firefox, for example), it&#8217;s easy to switch out the browser for another one (such as Internet Explorer or Chrome). It&#8217;d be nice to have a similar option for mobile, switching from one automation framework for Android to another.</p>
<p>As part of the Selenium 3 work, we have started working on a test suite to help ensure this level of interop between <a href="http://appium.io/">appium</a> and <a href="http://ios-driver.github.io/ios-driver/">iosdriver</a>, and appium and <a href="http://selendroid.io/">selendroid</a>. To kick start the process, the primary authors of each of those tools, as well as others including David Burns representing the <a href="https://developer.mozilla.org/en-US/docs/Marionette">Marionette</a> project (Mozilla&#8217;s implementation of WebDriver for Firefox and Firefox OS) and Simon Stewart, the lead of the <a href="http://seleniumhq.org/">Selenium</a> project, have spent the past two days locked in a small room in Mozilla HQ, London. They&#8217;ve taken this time to work out the areas where each of their projects didn&#8217;t align and agreed on a way to ensure a level of interoperability. There was only a minimal quantity of blood and tears, but plenty of hard work.</p>
<p>The <a href="https://docs.google.com/document/d/1rnE13aGCaRiri01hti7j1jWDuPvQHT8aao4bHhEGz8Y/edit">agenda</a> for the past two days can be found here, and the <a href="https://docs.google.com/document/d/1yXXsQo3z7lUVl3ZthAx39h4xBlF62x7q_NZd3NA9jnU/edit">minutes</a> are also available.</p>
<p>As we speak, work has started on a shared test suite, hosted in a <a href="https://code.google.com/p/selenium/source/checkout?repo=mobile">repo</a> in the selenium project&#8217;s Google Code page. Please, feel free to come along and join in!</p>

