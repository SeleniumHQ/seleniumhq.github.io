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

## IDE

_[IDE](https://selenium.dev/selenium-ide)_ is een Firefox plugin die gebruikt kan
worden om testen op te nemen in Firefox. Selenium IDE kan gebruikt worden om _quick and dirty_
testcode te genereren in verschillende programmeertalen (vb. C#, Java, Python en Ruby).
De resulterende code vergt veel onderhoud en is daardoor alleen aan te raden om gewend te
raken aan element locators of om snel _wergwerpcode_ te genereren. Als je de WebDriver API
leert kennen, zal je geen nood meer hebben aan Selenium IDE.

## Grid

Nadat je enkele WebDriver testen hebt geschreven, zal je snel tot de constatatie komen
dat je testen wil uitvoeren tegen verschillende (versies van) browsers.
Hiervoor kun je _[Grid]({{< ref "/grid/_index.nl.md" >}})_ gebruiken.


