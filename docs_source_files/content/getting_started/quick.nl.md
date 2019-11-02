---
title: "Korte uitleg"
weight: 1
---

Selenium is niet enkel een instrument of API,
het bestaat uit verschillende instrumenten.

## WebDriver

_[WebDriver]({{< ref "/webdriver/_index.nl.md" >}})_ staat ook bekend als Selenium 2.
Als je begint met het automatiseren van websites, dan zul je de WebDriver
API's gebruiken. WebDriver gebruikt automatisatie API's die door de browsers zelf
worden aangeboden. Laatstgenoemden worden gebruikt om de browser aan te sturen. Op die 
manier word een echte gebruiker gesimuleerd.WebDriver staat los van de applicatieve 
code en is daarom niet-intrusief. Zo test je dezelfde applicatie als diegene die live staat.

## Remote Control

_[Remote Control]({{< ref "/legacy_docs/selenium_rc.nl.md" >}})_
staat ook bekend als Selenium 1.
Selenium RC werd het meest gebruikt totdat Selenium WebDriver verscheen.
Selenium RC gebruikte een proxy server om Javascript in de browser te injecteren, zodat deze
kon aangestuurd worden. Wegens de grote impact op de browser, wegens Javascript injectie, was je
nooit zeker of je dezelfde applicatie aan het testen was als diegene die live stond.
In Selenium 2 zitten nog Selenium RC API's, maar sinds de release van Selenium 3, zijn al deze
API's verwijderd. Als je nog steeds Selenium RC gebruikt, moet je
[_overschakelen_]({{< ref "/legacy_docs/migrating_from_rc_to_webdriver.nl.md" >}}) naar
Selenium WebDriver.

## IDE

_[IDE](https://www.seleniumhq.org/selenium-ide)_ is een Firefox plugin die gebruikt kan
worden om testen op te nemen in Firefox. Selenium IDE kan gebruikt worden om _quick and dirty_
testcode te genereren in verschillende programmeertalen (vb. C#, Java, Python en Ruby).
De resulterende code vergt veel onderhoud en is daardoor alleen aan te raden om gewend te
raken aan element locators of om snel _wergwerpcode_ te genereren. Als je de WebDriver API
leert kennen, zal je geen nood meer hebben aan Selenium IDE.

## Grid

Nadat je enkele WebDriver testen hebt geschreven, zal je snel tot de constatatie komen
dat je testen wil uitvoeren tegen verschillende (versies van) browsers.
Hiervoor kun je _[Grid]({{< ref "/grid/_index.nl.md" >}})_ gebruiken.

## HTML Runner

Hiermee kun je Test Suites uitvoeren via de command line. Test Suites zijn
HTML exports vanuit Selenium IDE of andere compatiebele tools _[HTML Runner]({{< ref "/getting_started/html-runner.nl.md" >}})_.

