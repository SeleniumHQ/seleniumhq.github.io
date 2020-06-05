+++
Description = "<p>There are several ways to use Selenium 2: If you don’t have Selenium 1.x legacy code, you might want to directly use on of the new WebDriver implemenations like ChromeDriver, HtmlUnitDriver, FirefoxDriver, or InternetExplorerDriver which provide a nice, small and easy to learn API. If you have Selenium 1.x legacy code, you can still use […]</p>"
Title = "How to use Selenium 2 with Maven"
Date = 2010-07-30
Author = "Michael Tamm"
AuthorLink = "https://twitter.com/MichaelTamm"
tags = ["maven","selenium"]
categories = ["technical"]
+++

<p>There are several ways to use Selenium 2:</p>
<ol>
<li>If you don&#8217;t have Selenium 1.x legacy code, you might want to directly use on of the new <tt>WebDriver</tt> implemenations like <tt>ChromeDriver</tt>, <tt>HtmlUnitDriver</tt>, <tt>FirefoxDriver</tt>, or <tt>InternetExplorerDriver</tt> which provide a nice, small and easy to learn API.</li>
<li>If you have Selenium 1.x legacy code, you can still use the well known <tt>DefaultSelenium</tt> class or the new <tt>WebDriverBackedSelenium</tt>, which extends <tt>DefaultSelenium</tt> but uses one of the <tt>WebDriver</tt> implementations internally.</li>
</ol>
<p>Whichever option you prefer, if you have want to use Maven, all you need to do is add the following dependency to your <tt>pom.xml</tt>:</p>
<pre>    &lt;dependency&gt;
        &lt;groupId&gt;org.seleniumhq.selenium&lt;/groupId&gt;
        &lt;artifactId&gt;selenium&lt;/artifactId&gt;
        &lt;version&gt;2.0a5&lt;/version&gt;
    &lt;/dependency&gt;</pre>
<p>If you know, that you will only use a certain <tt>WebDriver</tt> implementation, e.g. the <tt>FirefoxDriver</tt>, you don&#8217;t need to depend on the <tt>selenium</tt> artifact (which has dependencies to all <tt>WebDriver</tt> implementations as well as the support classes). Instead you can add the dependency to just the artifact you need, e.g.</p>
<pre>    &lt;dependency&gt;
        &lt;groupId&gt;org.seleniumhq.selenium&lt;/groupId&gt;
        &lt;artifactId&gt;selenium-firefox-driver&lt;/artifactId&gt;
        &lt;version&gt;2.0a5&lt;/version&gt;
    &lt;/dependency&gt;</pre>
<p>When using a <tt>WebDriver</tt> implementation, there is no need to start a Selenium server &#8211; the browser will be directly started and remote controlled.</p>
<p>But if you are using <tt>DefaultSelenium</tt> (or the <tt>RemoteWebDriver</tt> implementation), you still need to start a Selenium server.</p>
<p>The best way is to download the <a href="http://code.google.com/p/selenium/downloads/detail?name=selenium-server-standalone-2.0a5.jar">standalone Selenium server jar</a> and just use it.</p>
<p>Furthermore you can also embed the Selenium server into your own project, if you add the following dependency to your <tt>pom.xml</tt>:</p>
<pre>    &lt;dependency&gt;
        &lt;groupId&gt;org.seleniumhq.selenium&lt;/groupId&gt;
        &lt;artifactId&gt;selenium-server&lt;/artifactId&gt;
        &lt;version&gt;2.0a5&lt;/version&gt;
    &lt;/dependency&gt;</pre>
<p>Now you can create a <tt>SeleniumServer</tt> instance yourself and start it.</p>
<p>Be aware, that the <tt>selenium-server</tt> artifact has a dependency to the <tt>servlet-api-2.5</tt> artifact, which you need to exclude, if your project will be run in a web application container.</p>
<p>Well, I hope that covers everything you need to know on how to use Selenium 2 with Maven.</p>
<p>Michael</p>

