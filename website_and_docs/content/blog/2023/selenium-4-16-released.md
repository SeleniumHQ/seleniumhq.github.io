---
title: "Selenium 4.16 Released!"
linkTitle: "Selenium 4.16 Released!"
date: 2023-12-06
tags: ["selenium"]
categories: ["releases"]
author: Titus Fortner [@titusfortner](https://titusfortner.com)
description: >
  Today we're happy to announce that Selenium 4.16 has been released!
---

We're very happy to announce the release of Selenium 4.16.0 for 
Javascript, Ruby, Python, and Selenium 4.16.1 for .NET, Java and the Grid.
Links to everything can be found on our [downloads page][downloads].

### Highlights

  * Chrome DevTools support is now: v118, v119, and v120 (Firefox still uses v85 for all versions)
  * Users may now opt in to Selenium Manager by specifying a `browserVersion` that is different from what is found on the System 
  * All bindings now [log Selenium information](https://www.selenium.dev/documentation/webdriver/troubleshooting/logging/)

#### Noteworthy changes per language

  * Java 
    * Improve Grid usage of Selenium Manager by always allowing browser version of "stable"
    * Implement CDP script pinning for Chrome-based browsers
    * Allow reusing devtools instance with JDK 11 client
    * Moved org.openqa.selenium.remote.http.jdk to selenium-http
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/java/CHANGELOG)

  <br>
  
  * JavaScript
    * Fix logging levels in http.js and webdriver.js
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/javascript/node/selenium-webdriver/CHANGES.md)
  
  <br>
  
  * .NET
    * Allow overriding default Actions duration
    * Remove System.Drawing.Common as package dependency
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/dotnet/CHANGELOG)
  
  <br>
  
  * Python
    * Remove deprecated Safari parameters for reuse_service and quiet
    * Fix bug to allow Remote WebDriver to use custom Chrome-based commands
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/py/CHANGES)

<br>
  
  * Ruby
    * Added initial typing support with rbs files
    * Fix bug preventing Selenium Manager from working with Unix and Cygwin
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/rb/CHANGES)

<br>

  * Rust
    * Use online mapping to discover proper geckodriver version
    * Fix webview2 support when browser path is set
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/rust/CHANGELOG.md)


### Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

#### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/EdwinVanVliet" >}}
{{< gh-user "https://api.github.com/users/RevealOscar" >}}
{{< gh-user "https://api.github.com/users/aguspe" >}}
{{< gh-user "https://api.github.com/users/asottile" >}}
{{< gh-user "https://api.github.com/users/centic9" >}}
{{< gh-user "https://api.github.com/users/jnhyperion" >}}
{{< gh-user "https://api.github.com/users/manuelsblanco" >}}
{{< gh-user "https://api.github.com/users/matt-kemp-m2x" >}}
{{< gh-user "https://api.github.com/users/nikhlagrwl" >}}
{{< gh-user "https://api.github.com/users/purkhusid" >}}
{{< gh-user "https://api.github.com/users/vietnd96" >}}
{{< gh-user "https://api.github.com/users/whimboo" >}}
    </div>
  </div>
</div>

#### [Selenium Docs & Website](https://github.com/SeleniumHQ/seleniumhq.github.io)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/SparshKesari" >}}
{{< gh-user "https://api.github.com/users/emanlove" >}}
{{< gh-user "https://api.github.com/users/ronPy" >}}
{{< gh-user "https://api.github.com/users/utamas" >}}
    </div>
  </div>
</div>

#### [Docker Selenium](https://github.com/SeleniumHQ/docker-selenium)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/Thab310" >}}
{{< gh-user "https://api.github.com/users/amardeep2006" >}}
{{< gh-user "https://api.github.com/users/mtcolman" >}}
{{< gh-user "https://api.github.com/users/vietnd96" >}}
    </div>
  </div>
</div>

#### [Selenium Team Members][team]

**Thanks as well to all the team members who contributed to this release:**

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/alaahong" >}}
{{< gh-user "https://api.github.com/users/bonigarcia" >}}
{{< gh-user "https://api.github.com/users/diemol" >}}
{{< gh-user "https://api.github.com/users/harsha509" >}}
{{< gh-user "https://api.github.com/users/joerg1985" >}}
{{< gh-user "https://api.github.com/users/luisfcorreia" >}}
{{< gh-user "https://api.github.com/users/nvborisenko" >}}
{{< gh-user "https://api.github.com/users/p0deje" >}}
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

