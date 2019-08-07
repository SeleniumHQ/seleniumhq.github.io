---
title: "Aan de slag met WebDriver"
chapter: true
weight: 4
---

# Aan de slag met WebDriver

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

Meer info over de drivers kan je terugvinden via 
[Driver Eigenschappen]({{< ref "/driver_idiosyncrasies/_index.nl.md" >}}).
