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

In cloud-based providers, 
setting `platformName` sets the OS at the remote-end. 

## acceptInsecureCerts

This capability checks whether an expired (or) 
invalid `TLS Certificate` is used while navigating 
during a session.

If the capability is set to `false`, an 
[insecure certificate error](//developer.mozilla.org/de/docs/Web/WebDriver/Errors/InsecureCertificate) 
will be returned as navigation encounters any domain 
certificate problems. If set to `true`, invalid certificate will be 
trusted by the browser.

All self-signed certificates will be trusted by this capability by default. 
Once set, `acceptInsecureCerts` capability will have an 
effect for the entire session.

## Session timeouts

A WebDriver `session` is imposed with a certain `session timeout`
interval, during which the user can control the behaviour
of executing scripts or retrieving information from the browser.

Each session timeout is configured with
combination of different `timeouts` as described below:

### Script Timeout:
Specifies when to interrupt an executing script in
a current browsing context. The default timeout **30,000**
is imposed when a new session is created by WebDriver.

### Page Load Timeout:
Specifies the time interval in which web page
needs to be loaded in a current browsing context.
The default timeout **300,000** is imposed when a
new session is created by WebDriver. If page load limits
a given/default time frame, the script will be stopped by
_TimeoutException_.

### Implicit Wait Timeout
This specifies the time to wait for the
implicit element location strategy when
locating elements. The default timeout **0**
is imposed when a new session is created by WebDriver.