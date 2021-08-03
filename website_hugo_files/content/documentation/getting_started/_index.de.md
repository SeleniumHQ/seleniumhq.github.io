---
title: "Erste Schritte"
linkTitle: "Erste Schritte"
weight: 2
description: >
  If you are new to Selenium, we have a few resources that can help you get up to speed right away.
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Diese Seite wird von Englisch 
   auf Deutsch übersetzt. Sprichst Du Deutsch? Hilf uns die Seite 
   zu übersetzen indem Du uns einen Pull Reqeust schickst!
</p>
{{% /pageinfo %}}


Die Installation von Selenium unterscheidet sich grundlegend von 
der Installation kommerzieller Tools. Um Selenium in Deinem Automationsprojekt
zu verwenden, ist es notwendig zuerst die entsprechenden Bibliotheken der 
verwendeten Programmiersprache zu installieren. Weiters benötigst Du die passenden
WebDriver Dateien entsprechend des verwendeten Browsers, der für die Automation 
benötigt, und auf dem die Tests ausgeführt werden.

Installing Selenium can be divided in three steps:

1. [Installing the Selenium library]({{< ref "/installing_selenium_libraries.md" >}}) for your desired programming language
2. [Set up the browser driver]({{< ref "/installing_browser_drivers.md" >}}) to automate your browser (e.g. GeckoDriver for Firefox)
3. (Optional) Set up and configure [Selenium Grid]({{< ref "/grid.md" >}}) if you want to scale up your tests

If you wish to start with a low-code/record and playback tool, please check 
[Selenium IDE](https://selenium.dev/selenium-ide)

After completing the setup, you can run the code snippet shown at the 
[starting page](/de/documentation) in our docs. Then head to the 
[WebDriver]({{< ref "/webdriver.md" >}}) section to learn more about
browser automation with Selenium.