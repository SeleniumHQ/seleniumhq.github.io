---
title: "Erste Schritte"
linkTitle: "Erste Schritte"
weight: 2
description: >
  If you are new to Selenium, we have a few resources that can help you get up to speed right away.
aliases: 
        [
            "/documentation/de/getting_started/", 
            "/documentation/de/getting_started/quick/",
            "/documentation/de/selenium_installation/",
            "/documentation/de/getting_started_with_webdriver/",
            "/de/documentation/getting_started/"
        ]
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Diese Seite wird von Englisch 
   auf Deutsch übersetzt. Sprichst Du Deutsch? Hilf uns die Seite 
   zu übersetzen indem Du uns einen Pull Reqeust schickst!
</p>
{{% /pageinfo %}}

Selenium unterstützt das Automatisieren aller wichtigsten Webbrowser 
durch die Verwendung von _WebDriver_.

WebDriver ist eine API und ein Protokoll, das eine programmiersprachen 
unabhängige Schnittstelle definiert um den Webrowser zu steuern.
Jeder Browser ist mit einer spezifischen Implementierung des Webdriver
ausgestattet, auch *driver* genannt. Dieser driver ist die Komponente
die verantwortlich ist um den Browser fernzusteuern, weiters handabt dieser 
die Kommunikation zwischen Selenium und dem Webbrowser.

Diese Trennung wurde bewusst gemacht, um die Verantwortung der 
browserspezifischen Implementierung in die Hände der Browserhersteller
zu legen. Selenium ermöglicht es diese Drittanbieter driver zu verwenden.
Weiters werden auch eigene driver zur Verfügung gestellt die durch
das Projekt gewartet werden, für den Fall das der Browserhersteller
keine zur Verfügung stellt.

Das Seleniumframework verbindet diese einzelnen Komponenten zu einer
benutzerfreundlichen Schnittstelle die es ermöglicht, die verschiedenen 
Browserbackends einheitlich und browser- als auch plattform-unabhängig 
zu automatisieren.

Die Installation von Selenium unterscheidet sich grundlegend von 
der Installation kommerzieller Tools. Um Selenium in Deinem Automationsprojekt
zu verwenden, ist es notwendig zuerst die entsprechenden Bibliotheken der 
verwendeten Programmiersprache zu installieren. Weiters benötigst Du die passenden
WebDriver Dateien entsprechend des verwendeten Browsers, der für die Automation 
benötigt, und auf dem die Tests ausgeführt werden.

Installing Selenium can be divided in three steps:

1. [Installing the Selenium library]({{< ref "/install_selenium_library" >}}) for your desired programming language
2. [Set up the browser driver]({{< ref "/install_browser_drivers" >}}) to automate your browser (e.g. GeckoDriver for Firefox)
3. (Optional) Set up and configure [Selenium Grid]({{< ref "/grid.md" >}}) if you want to scale up your tests

If you wish to start with a low-code/record and playback tool, please check 
[Selenium IDE](https://selenium.dev/selenium-ide)

After completing the setup, you can run the code snippet shown at the 
[starting page](/de/documentation) in our docs. Then head to the 
[WebDriver]({{< ref "/webdriver.md" >}}) section to learn more about
browser automation with Selenium.
