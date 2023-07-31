---
title: "Selenium 4.11.0 Released!"
linkTitle: "Selenium 4.11.0 Released!"
date: 2023-07-31
tags: ["selenium"]
categories: ["releases"]
author: Diego Molina ([@diegofmolina](https://twitter.com/diegofmolina))
description: >
  Today we're happy to announce that Selenium 4.11.0 has been released!
---

We're very happy to announce the release of Selenium 4.11.0 for Java, 
.NET, Ruby, Python, and Javascript as well as the Grid and Internet Explorer Driver.
Links to everything can be found on our [downloads page][downloads].

### Highlights

  * Chrome DevTools support is now: v113, v114, and v115 (Firefox still uses v85 for all versions)
  * Support for [Chrome for Testing (CfT)](https://googlechromelabs.github.io/chrome-for-testing/) through Selenium Manager. Read more at the new Selenium Manager features [blog post](/blog/2023/whats-new-in-selenium-manager-with-selenium-4.11.0/). 
  * Selenium Manager now locates the browser driver binary on PATH or the configured path, checks for potential incompatibilities, and gives better warning and error messages.
  * [Nightly builds](/downloads/#nightly) are being pushed for Ruby and Java. Support for other languages is coming soon.
  * Ignore process id match when finding the window handle - IE Mode on Edge. ([#12246](https://github.com/SeleniumHQ/selenium/pull/12246)) ([#12279](https://github.com/SeleniumHQ/selenium/pull/12279))


#### Relevant improvements per language

  * Java
    * Make user defined SlotMatcher used everywhere in Grid code ([#12240](https://github.com/SeleniumHQ/selenium/pull/12240))
    * Add support for [FedCM](https://fedidcg.github.io/FedCM/) commands ([#12096](https://github.com/SeleniumHQ/selenium/pull/12096))
  
  <br>
  
  * JavaScript
    * [BiDi] Add Network module events ([#12197](https://github.com/SeleniumHQ/selenium/pull/12197))
  
  <br>
  
  * .NET
    * Implementation of event wrapped shadow root element ([#12073](https://github.com/SeleniumHQ/selenium/pull/12073))
    * Allow setting a different pointer, keyboard, or wheel on input device ([#11513](https://github.com/SeleniumHQ/selenium/pull/11513))
    * Add move to location method to Actions ([#11509](https://github.com/SeleniumHQ/selenium/pull/11509))
    * Add support for Safari Technology Preview ([#12342](https://github.com/SeleniumHQ/selenium/pull/12342))
    * Fix error when we send non-base64 data for fetch command ([#12431](https://github.com/SeleniumHQ/selenium/pull/12431))
    * Fix continueResponse method in CDP ([#12445](https://github.com/SeleniumHQ/selenium/pull/12445))
  
  <br>
  
  * Python
    * removed redundant attributes `capabilities` and `set_capability` in wpewebkit/options.py ([#12169](https://github.com/SeleniumHQ/selenium/pull/12169))
    * improve driver logging, implement log_output() for flexibility and consistency of driver logging ([#12103](https://github.com/SeleniumHQ/selenium/pull/12103))
    * let users pass service args to IE driver ([#12272](https://github.com/SeleniumHQ/selenium/pull/12272))
    * Expose `WPEWebKitService` and `WebKitGTKService` in the public API
    * Remove deprecated `ActionChains.scroll(...)`
    * Add creation flag for windows in selenium_manager ([#12435](https://github.com/SeleniumHQ/selenium/pull/12435))

<br>
  
  * Ruby
    * Made network interception threads fail silently ([#12226](https://github.com/SeleniumHQ/selenium/pull/12226))
    * Remove deprecated code ([#12417](https://github.com/SeleniumHQ/selenium/pull/12417))

### Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

#### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/RevealOscar" >}}
{{< gh-user "https://api.github.com/users/mdmintz" >}}
{{< gh-user "https://api.github.com/users/nvborisenko" >}} 
{{< gh-user "https://api.github.com/users/sandeepsuryaprasad" >}}
{{< gh-user "https://api.github.com/users/bswhb" >}} 
{{< gh-user "https://api.github.com/users/bastimeyer" >}} 
{{< gh-user "https://api.github.com/users/djbrown" >}} 
{{< gh-user "https://api.github.com/users/vedanthvdev" >}}
{{< gh-user "https://api.github.com/users/Hyphenhypen" >}}
{{< gh-user "https://api.github.com/users/baflQA" >}} 
{{< gh-user "https://api.github.com/users/AdamPDotty" >}} 
{{< gh-user "https://api.github.com/users/jlucartc" >}}
{{< gh-user "https://api.github.com/users/ggkiokas" >}}
{{< gh-user "https://api.github.com/users/debanjanc01" >}}
{{< gh-user "https://api.github.com/users/bhecquet" >}}
{{< gh-user "https://api.github.com/users/cbiesinger" >}}
{{< gh-user "https://api.github.com/users/SenZmaKi" >}}
    </div>
  </div>
</div>

#### [Selenium Docs & Website](https://github.com/SeleniumHQ/seleniumhq.github.io)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/pallavigitwork" >}}
{{< gh-user "https://api.github.com/users/cristianrgreco" >}}
{{< gh-user "https://api.github.com/users/GautamJ700" >}}
{{< gh-user "https://api.github.com/users/by-gayathri" >}}
{{< gh-user "https://api.github.com/users/danksearle" >}}
{{< gh-user "https://api.github.com/users/konflic" >}}
{{< gh-user "https://api.github.com/users/nevinaydin" >}}
{{< gh-user "https://api.github.com/users/erick-ribeiro" >}}
    </div>
  </div>
</div>

#### [Docker Selenium](https://github.com/SeleniumHQ/docker-selenium)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/luisfcorreia" >}}
{{< gh-user "https://api.github.com/users/alb3ric" >}}
{{< gh-user "https://api.github.com/users/baflQA" >}}
{{< gh-user "https://api.github.com/users/msvticket" >}}
{{< gh-user "https://api.github.com/users/wintersolutions" >}}
    </div>
  </div>
</div>

**Thanks as well to all the [Selenium Team Members][team] who contributed to this release:**

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/TamsilAmani" >}}
{{< gh-user "https://api.github.com/users/joerg1985" >}}
{{< gh-user "https://api.github.com/users/diemol" >}}
{{< gh-user "https://api.github.com/users/harsha509" >}}
{{< gh-user "https://api.github.com/users/titusfortner" >}}
{{< gh-user "https://api.github.com/users/shs96c" >}} 
{{< gh-user "https://api.github.com/users/bonigarcia" >}}
{{< gh-user "https://api.github.com/users/pujagani" >}}
{{< gh-user "https://api.github.com/users/symonk" >}}
{{< gh-user "https://api.github.com/users/p0deje" >}}
{{< gh-user "https://api.github.com/users/krmahadevan" >}}
{{< gh-user "https://api.github.com/users/AutomatedTester" >}}
{{< gh-user "https://api.github.com/users/jamesmortensen" >}}
    </div>
  </div>
</div>

Stay tuned for updates by following [SeleniumHQ](https://twitter.com/seleniumhq)!

Happy testing!

[downloads]: /downloads
[bindings]: /downloads#bindings
[team]: /project/structure
[BiDi]: https://github.com/w3c/webdriver-bidi

