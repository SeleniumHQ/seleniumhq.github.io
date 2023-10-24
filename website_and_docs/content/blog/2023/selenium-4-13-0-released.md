---
title: "Selenium 4.13 Released!"
linkTitle: "Selenium 4.13 Released!"
date: 2023-09-25
tags: ["selenium"]
categories: ["releases"]
author: Titus Fortner [@titusfortner](https://titusfortner.com)
description: >
  Today we're happy to announce that Selenium 4.13 has been released!
---

We're very happy to announce the release of Selenium 4.13.0 for Java, 
Python, Javascript and the Grid; and 4.13.1 for .NET and Ruby.
Links to everything can be found on our [downloads page][downloads].

### Highlights

  * Chrome DevTools support is now: v115, v116, and v117 (Firefox still uses v85 for all versions)
  * Reminder: this is the last version of Selenium with [Java 8 support](/blog/2023/java-8-support/). 
Please upgrade to at least Java 11.
  * The location of Selenium Manager can be set manually in all bindings with `SE_MANAGER_PATH` environment variable.

#### Relevant improvements per language

  * Java 
    * Deprecated setScriptTimeout(), use scriptTimeout()
    * Fixed several bugs related to logging driver output
    * Removed a number of previously deprecated methods
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/java/CHANGELOG)

  <br>
  
  * JavaScript
    * Minor bug fixes
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/javascript/node/selenium-webdriver/CHANGES.md)
  
  <br>
  
  * .NET
    * Users can now start a service before creating the driver object instance
    * Removed Microsoft.IdentityModel.Tokens as dependency
    * Fixed several bugs and made improvements to DevTools implementations
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/dotnet/CHANGELOG)
  
  <br>
  
  * Python
    * Removed deprecated headless methods
    * Fixed bug preventing using performance logging in Chrome and Edge
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/py/CHANGES)

<br>
  
  * Ruby
    * Fixed bug preventing using performance logging in Chrome and Edge
    * Users can now start a service before creating the driver object instance
    * Removed deprecated driver extensions for location and network connection
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/rb/CHANGES)

<br>

  * Rust
    * Various bug fixes for improved Selenium Manager functionality 
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/rust/CHANGELOG.md)


### Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

#### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/JefferyVin" >}} 
{{< gh-user "https://api.github.com/users/KrishnaSuravarapu" >}}
{{< gh-user "https://api.github.com/users/RevealOscar" >}}
{{< gh-user "https://api.github.com/users/Sean-Gomez" >}}
{{< gh-user "https://api.github.com/users/manuelsblanco" >}}
{{< gh-user "https://api.github.com/users/mdmintz" >}}
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
{{< gh-user "https://api.github.com/users/SparshKesari" >}}
{{< gh-user "https://api.github.com/users/eaccmk" >}}
{{< gh-user "https://api.github.com/users/nvborisenko" >}} 
{{< gh-user "https://api.github.com/users/sachhu" >}}
    </div>
  </div>
</div>

#### [Docker Selenium](https://github.com/SeleniumHQ/docker-selenium)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/Doofus100500" >}}
{{< gh-user "https://api.github.com/users/amardeep2006" >}}
{{< gh-user "https://api.github.com/users/luisfcorreia" >}}
{{< gh-user "https://api.github.com/users/marcusalb" >}}
{{< gh-user "https://api.github.com/users/williamlac" >}}
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
{{< gh-user "https://api.github.com/users/joerg1985" >}}
{{< gh-user "https://api.github.com/users/pujagani" >}}
{{< gh-user "https://api.github.com/users/shs96c" >}} 
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

