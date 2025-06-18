---
title: "Selenium 4.12.0 Released!"
linkTitle: "Selenium 4.12.0 Released!"
date: 2023-08-31
tags: ["selenium"]
categories: ["releases"]
author: Titus Fortner [@titusfortner](https://titusfortner.com)
description: >
  Today we're happy to announce that Selenium 4.12.0 has been released!
---

We're very happy to announce the release of Selenium 4.12.0 for Java, 
.NET, Ruby, Python, and Javascript as well as the Grid.
Links to everything can be found on our [downloads page][downloads].

### Highlights

  * Chrome DevTools support is now: v114, v115, and v116 (Firefox still uses v85 for all versions)
  * Quite a few fixes for Selenium Manager, and now with Firefox browser management! 
Read about all the [new Selenium Manager features](/blog/2023/whats_new_in_selenium_manager_0.4.12_shipped_with_selenium_4.12.0/)
  * .NET only explicitly targets `netstandard2.0`
  * Python no longer supports Python 3.7
  * Ruby no longer supports `:capabilities` arguments for local drivers (must use `:options` now)

#### Relevant improvements per language

  * Java 
    * Several improvements in working with Selenium Manager
    * Allow deleting remote downloaded files from grid ([#12501](https://github.com/SeleniumHQ/selenium/pull/12501))
    * Removed deprecated `UNEXPECTED_ALERT_BEHAVIOR`, `BROWSER_LOGFILE`, `createPointerDown`, `createPointerUp` and JWP `/file` endpoint
    * Deprecated `disableNativeEvents` and Remote Response status field
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/java/CHANGELOG)

  <br>
  
  * JavaScript 
    * Fix how browsers and drivers are discovered ([#12456](https://github.com/SeleniumHQ/selenium/pull/12456))
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/javascript/node/selenium-webdriver/CHANGES.md)
  
  <br>
  
  * .NET
    * Several big fixes for improving DevTools execution ([#12486](https://github.com/SeleniumHQ/selenium/pull/12486), 
[#12614](https://github.com/SeleniumHQ/selenium/pull/12614), [#12592](https://github.com/SeleniumHQ/selenium/pull/12592), 
[#12591](https://github.com/SeleniumHQ/selenium/pull/12591))
    * Bug fix for how driver location is specified in Service class ([#12473](https://github.com/SeleniumHQ/selenium/pull/12473))
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/dotnet/CHANGELOG)
  
  <br>
  
  * Python
    * Support Selenium Manager in conda installs ([#12536](https://github.com/SeleniumHQ/selenium/pull/12536))
    * Several bug fixes in Typing suggestions ([#12477](https://github.com/SeleniumHQ/selenium/pull/12477), 
[#12464](https://github.com/SeleniumHQ/selenium/pull/12477))
    * allow setting http client certifications with REQUESTS_CA_BUNDLE env ([#11957](https://github.com/SeleniumHQ/selenium/pull/11957))
    * Fix bugs for [ElementScrollBehavior](#12462), [sending keys with long strings](https://github.com/SeleniumHQ/selenium/pull/12474) 
and [getting capabilities from options](https://github.com/SeleniumHQ/selenium/pull/12499)
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/py/CHANGES)

<br>
  
  * Ruby
    * Fix bug preventing good error messages in Selenium Manager when stdout empty
    * Fix bug with Firefox not loading net/http library by default ([#12506](https://github.com/SeleniumHQ/selenium/pull/12506))
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/rb/CHANGES)

### Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

#### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/nvborisenko" >}} 
{{< gh-user "https://api.github.com/users/sandeepsuryaprasad" >}}
{{< gh-user "https://api.github.com/users/sbabcoc" >}}
    </div>
  </div>
</div>

#### [Selenium Docs & Website](https://github.com/SeleniumHQ/seleniumhq.github.io)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/ahndmal" >}}
{{< gh-user "https://api.github.com/users/asolntsev" >}}
{{< gh-user "https://api.github.com/users/nvborisenko" >}} 
{{< gh-user "https://api.github.com/users/pallavigitwork" >}}
{{< gh-user "https://api.github.com/users/Tahanima" >}}
{{< gh-user "https://api.github.com/users/veerendrajana" >}}
    </div>
  </div>
</div>

#### [Docker Selenium](https://github.com/SeleniumHQ/docker-selenium)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/SurelyMario" >}}
    </div>
  </div>
</div>

**Thanks as well to all the [Selenium Team Members][team] who contributed to this release:**

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/AutomatedTester" >}}
{{< gh-user "https://api.github.com/users/bonigarcia" >}}
{{< gh-user "https://api.github.com/users/diemol" >}}
{{< gh-user "https://api.github.com/users/harsha509" >}}
{{< gh-user "https://api.github.com/users/jimevans" >}}
{{< gh-user "https://api.github.com/users/jlipps" >}}
{{< gh-user "https://api.github.com/users/joerg1985" >}}
{{< gh-user "https://api.github.com/users/luke-hill" >}}
{{< gh-user "https://api.github.com/users/p0deje" >}}
{{< gh-user "https://api.github.com/users/pujagani" >}}
{{< gh-user "https://api.github.com/users/shs96c" >}} 
{{< gh-user "https://api.github.com/users/symonk" >}}
{{< gh-user "https://api.github.com/users/titusfortner" >}}
    </div>
  </div>
</div>

Stay tuned for updates by following [SeleniumHQ](https://twitter.com/seleniumhq)!

Happy testing!

[downloads]: /downloads
[bindings]: /downloads#bindings
[team]: /project/structure
[BiDi]: https://github.com/w3c/webdriver-bidi

