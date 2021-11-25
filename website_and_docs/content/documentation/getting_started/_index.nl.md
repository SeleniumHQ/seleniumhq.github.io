---
title: "Getting Started"
linkTitle: "Getting Started"
weight: 2
description: >
  If you are new to Selenium, we have a few resources that can help you get up to speed right away.
aliases: 
        [
          "/documentation/nl/getting_started/", 
          "/documentation/nl/getting_started/quick/",
          "/documentation/nl/selenium_installation/",
          "/documentation/nl/getting_started_with_webdriver/"
        ]
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to Dutch. Do you speak Dutch? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

Door middel van _WebDriver_ ondersteunt Selenium alle grote browsers op de markt.
WebDriver is een API en protocol dat een taal-onafhankelijke interface aanbied die
de browser kan aansturen. Elke browser bevat een specifieke WebDriver implementatie.
We verwijzen naar deze implementatie als *driver*.
De driver is verantwoordelijk voor de vertaalslag naar de browser en staat in voor
de communicatie van en naar Selenium en de browser.

Deze opsplitsing is bewust gedaan zodat de leverancier van de browser de 
verantwoordelijkheid van de implementatie op zich neemt. Selenium maakt gebruik
van deze aangeleverde drivers indien mogelijk. Eveneens bied ze haar eigen drivers 
aan voor het geval dat de leverancier hierover niet beschikt. Deze worden onderhouden
door de community.

Selenium schakelt al deze stukken aan elkaar door middel van een interface.
Deze zorgt voor een transparante en gebruiksvriendelijke manier om de backend
van de verschillende browsers aan te spreken. Hierdoor onstaat een browser en
platform onafhankelijke oplossing.

Selenium setup is quite different from the setup of other commercial tools.
To use Selenium in your automation project you need to install the language
bindings libraries for your language of choice. In addition you will need
WebDriver binaries for the browsers you want to automate and run test on. 

Installing Selenium can be divided in three steps:

1. [Installing the Selenium library]({{< ref "/installing_selenium_libraries.md" >}}) for your desired programming language
2. [Set up the browser driver]({{< ref "/installing_browser_drivers.md" >}}) to automate your browser (e.g. GeckoDriver for Firefox)
3. (Optional) Set up and configure [Selenium Grid]({{< ref "/grid.md" >}}) if you want to scale up your tests

If you wish to start with a low-code/record and playback tool, please check 
[Selenium IDE](https://selenium.dev/selenium-ide)

After completing the setup, you can run the code snippet shown at the 
[starting page](/nl/documentation) in our docs. Then head to the 
[WebDriver]({{< ref "/webdriver.md" >}}) section to learn more about
browser automation with Selenium.
