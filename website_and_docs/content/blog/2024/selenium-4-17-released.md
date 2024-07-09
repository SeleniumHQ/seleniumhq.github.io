---
title: "Selenium 4.17 Released!"
linkTitle: "Selenium 4.17 Released!"
date: 2024-01-23
tags: ["selenium"]
categories: ["releases"]
author: Titus Fortner [@titusfortner](https://titusfortner.com)
description: >
  Today we're happy to announce that Selenium 4.17 has been released!
---

We're very happy to announce the release of Selenium 4.17.0 for 
Javascript, Ruby, Python, .NET, Java and the Grid!
Links to everything can be found on our [downloads page][downloads].

### Highlights

  * Chrome DevTools support is now: v119, v120, and v121 (Firefox still uses v85 for all versions)
  * Selenium Manager [records usage](https://plausible.io/manager.selenium.dev) set 
environment variable [`SE_AVOID_STATS` to `"true"`](/documentation/selenium_manager/#data-collection) to avoid sending information.
  * Chrome headless changed the name of the browser to reflect that it is not actually chrome; Selenium now handles this seamlessly,
but you should still switch to `--headless=new` (see: [Headless is going away](/blog/2023/headless-is-going-away/))

#### Noteworthy changes per language

  * Java 
    * Remove deprecated event listener classes; update to EventFiringDecorator and WebDriverListener classes
    * Allow disabling Grid UI
    * Deprecated FirefoxBinary class and legacy Error Codes
    * Deprecated HTML5 features for offline storage, location, and network connection
    * No longer accepting session requests with desiredCapabilities keyword
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/java/CHANGELOG)

  <br>
  
  * JavaScript
    * Remove deprecated headless methods and associated references
    * Implemented remote file downloading
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/javascript/node/selenium-webdriver/CHANGES.md)
  
  <br>
  
  * .NET
    * Improvements to the new logging implementation
    * Removed previously deprecated code  
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/dotnet/CHANGELOG)
  
  <br>
  
  * Python
    * Updated WPEWebKit support
    * Removed previously deprecated code
    * Deprecated FirefoxBinary and several outdated FirefoxProfile methods
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/py/CHANGES)

<br>
  
  * Ruby
    * Logger defaults output to stderr instead of stdout
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/rb/CHANGES)

<br>

  * Rust
    * Use latest browser from cache when browser path is not discovered
    * Throw a descriptive message when error parsing JSON from response
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/rust/CHANGELOG.md)


### Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

#### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/Earlopain" >}}
{{< gh-user "https://api.github.com/users/EdwinVanVliet" >}}
{{< gh-user "https://api.github.com/users/asolntsev" >}}
{{< gh-user "https://api.github.com/users/jamesbraza" >}}
{{< gh-user "https://api.github.com/users/lauromoura" >}}
{{< gh-user "https://api.github.com/users/middlingphys" >}}
{{< gh-user "https://api.github.com/users/take0x" >}}
{{< gh-user "https://api.github.com/users/valfirst" >}}
{{< gh-user "https://api.github.com/users/vietnd96" >}}
{{< gh-user "https://api.github.com/users/whimboo" >}}
    </div>
  </div>
</div>


#### [Selenium Docs & Website](https://github.com/SeleniumHQ/seleniumhq.github.io)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/RevealOscar" >}}
{{< gh-user "https://api.github.com/users/YevgeniyShunevych" >}}
    </div>
  </div>
</div>

#### [Docker Selenium](https://github.com/SeleniumHQ/docker-selenium)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/Auto81" >}}
{{< gh-user "https://api.github.com/users/Earlopain" >}}
{{< gh-user "https://api.github.com/users/amardeep2006" >}}
{{< gh-user "https://api.github.com/users/vietnd96" >}}
    </div>
  </div>
</div>

#### [Selenium Team Members][team]

**Thanks as well to all the team members who contributed to this release:**

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/AutomatedTester" >}}
{{< gh-user "https://api.github.com/users/bonigarcia" >}}
{{< gh-user "https://api.github.com/users/diemol" >}}
{{< gh-user "https://api.github.com/users/harsha509" >}}
{{< gh-user "https://api.github.com/users/joerg1985" >}}
{{< gh-user "https://api.github.com/users/nvborisenko" >}}
{{< gh-user "https://api.github.com/users/p0deje" >}}
{{< gh-user "https://api.github.com/users/pujagani" >}}
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
