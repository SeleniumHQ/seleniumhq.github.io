---
title: "How to use Selenium 2 with Maven"
linkTitle: "How to use Selenium 2 with Maven"
date: 2010-07-30
tags: ["selenium","maven"]
categories: ["technical"]
author: Michael Tamm ([@MichaelTamm](https://twitter.com/MichaelTamm))
description: >
  There are several ways to use Selenium 2!
---

There are several ways to use Selenium 2:

1.  If you don’t have Selenium 1.x legacy code, you might want to directly use on of the new WebDriver implemenations like ChromeDriver, HtmlUnitDriver, FirefoxDriver, or InternetExplorerDriver which provide a nice, small and easy to learn API.
2.  If you have Selenium 1.x legacy code, you can still use the well known DefaultSelenium class or the new WebDriverBackedSelenium, which extends DefaultSelenium but uses one of the WebDriver implementations internally.

Whichever option you prefer, if you have want to use Maven, all you need to do is add the following dependency to your pom.xml:

    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium</artifactId>
        <version>2.0a5</version>
    </dependency>

If you know, that you will only use a certain WebDriver implementation, e.g. the FirefoxDriver, you don’t need to depend on the selenium artifact (which has dependencies to all WebDriver implementations as well as the support classes). Instead you can add the dependency to just the artifact you need, e.g.

    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-firefox-driver</artifactId>
        <version>2.0a5</version>
    </dependency>

When using a WebDriver implementation, there is no need to start a Selenium server – the browser will be directly started and remote controlled.

But if you are using DefaultSelenium (or the RemoteWebDriver implementation), you still need to start a Selenium server.

The best way is to download the [standalone Selenium server jar](http://code.google.com/p/selenium/downloads/detail?name=selenium-server-standalone-2.0a5.jar) and just use it.

Furthermore you can also embed the Selenium server into your own project, if you add the following dependency to your pom.xml:

    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-server</artifactId>
        <version>2.0a5</version>
    </dependency>

Now you can create a SeleniumServer instance yourself and start it.

Be aware, that the selenium-server artifact has a dependency to the servlet-api-2.5 artifact, which you need to exclude, if your project will be run in a web application container.

Well, I hope that covers everything you need to know on how to use Selenium 2 with Maven.

Michael