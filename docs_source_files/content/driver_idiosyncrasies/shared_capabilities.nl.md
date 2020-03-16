---
title: "Gedeelde capabilities"
weight: 1
---

In-order to create a new session by Selenium WebDriver, 
local end should provide the basic capabilities to remote end. 
The remote end uses the same set of capabilities to 
create a session and describes the current session features. 

WebDriver provides capabilities that each remote 
end will/should support the implementation. 
Following are the capabilities that WebDriver supports:

## browserName:

This capability is used to set the `browserName` for a given session. 
If the specified browser is not installed at the 
remote end, the session creation will fail

## browserVersion: 

This capability is optional, this is used to 
set the available browser version at remote end. 
For Example, if ask for Chrome version 75 on a system that 
only has 80 installed, the session creation will fail

## pageLoadStrategy:

Bij het navigeren naar een nieuwe pagina, zal Selenium standaard wachten totdat de pagina volledig geladen is. Deze strategie werkt goed voor beginners maar kan snel resulteren in lange wachttijden op paginas die veel externe resources moeten ophalen. Het gebruik van niet-standaard laadstrategieen kan de doorlooptijd aanzienlijk versnellen maar kan eveneens flakiness introduceren doordat elementen van grootte en/of positie veranderen tijdens het laden.

The page load strategy bevraagt
[document.readyState](//developer.mozilla.org/nl/docs/Web/API/Document/readyState)
zoals hieronder beschreven:

| Strategie | Ready State | Opmerkingen |
| -------- | ----------- | ----- |
| normal | complete | Standaard, wacht tot alle resources gedownload zijn |
| eager | interactive | DOM access is gereed, maar andere resources, zoals grafische elementen, zijn mogelijks nog niet volledig ingeladen. |
| none | Any | WebDriver houdt geen rekening met wachttijden |

## platformName

This identifies the operating system at the remote-end, 
fetching the `platformName` returns the OS name. 

In could-based testing(SauceLabs or BrowserStack), 
setting `platformName` sets the os at the remote-end 

## acceptInsecureCerts

This capability checks whether expired (or) 
invalid `TLS Certificate` are used while navigating 
during a session.

If the capability is set to `false`, an 
[insecure certificate error](//developer.mozilla.org/nl/docs/Web/WebDriver/Errors/InsecureCertificate) 
will be returned as navigation encounters any domain 
certificate problems. If set to `ture`, invalid certificate will be 
trusted by the browser.

All self-signed certificates will be trusted by this capability by default. 
Onc set, `acceptInsecureCerts` capability will have effect for entire session
