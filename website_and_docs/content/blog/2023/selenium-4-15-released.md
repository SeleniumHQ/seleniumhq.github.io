---
title: "Selenium 4.15 Released!"
linkTitle: "Selenium 4.15 Released!"
date: 2023-11-01
tags: ["selenium"]
categories: ["releases"]
author: Titus Fortner [@titusfortner](https://titusfortner.com)
description: >
  Today we're happy to announce that Selenium 4.15 has been released!
---

We're very happy to announce the release of Selenium 4.15.0 for Java, 
Javascript, Ruby, .NET and the Grid; 4.15.2 for Python.
Links to everything can be found on our [downloads page][downloads].

### Highlights

  * Chrome DevTools support is now: v117, v118, and v119 (Firefox still uses v85 for all versions)
  * Implemented [Remote File Downloads]({{< ref "../../documentation/webdriver/drivers/remote_webdriver#downloads" >}})
  * Selenium Manager supports webview2, Firefox ESR and Driver and Browser Mirrors

#### Noteworthy changes per language

  * Java 
    * Numerous BiDi implementations
    * Remove CDP version dependencies in the server
    * Selenium Manager now gets copied to cache directory instead of being used from temp directory
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/java/CHANGELOG)

  <br>
  
  * JavaScript
    * Replaced calls to console.log with managed loggers
    * Added several BiDi methods
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/javascript/node/selenium-webdriver/CHANGES.md)
  
  <br>
  
  * .NET
    * Improved nuget packages metadata
    * Allows starting service object directly
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/dotnet/CHANGELOG)
  
  <br>
  
  * Python
    * Remove selenium manager accommodation for Conda
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/py/CHANGES)

<br>
  
  * Ruby
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/rb/CHANGES)

<br>

  * Rust
    * Included debug and split-debuginfo in dev profile
    * Changed windows target to stable-i686-pc-windows-gnu
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/rust/CHANGELOG.md)


### Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

#### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/pinterior" >}} 
    </div>
  </div>
</div>

#### [Selenium Docs & Website](https://github.com/SeleniumHQ/seleniumhq.github.io)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/Afranioalves" >}}
{{< gh-user "https://api.github.com/users/AnselmoPfeifer" >}}
{{< gh-user "https://api.github.com/users/BlazerYoo" >}}
{{< gh-user "https://api.github.com/users/luisfcorreia" >}}
{{< gh-user "https://api.github.com/users/pallavigitwork" >}}
{{< gh-user "https://api.github.com/users/sangcnguyen" >}}
{{< gh-user "https://api.github.com/users/sombo20" >}}
{{< gh-user "https://api.github.com/users/SripriyaPKulkarni" >}}
{{< gh-user "https://api.github.com/users/zacharyzollman" >}}
{{< gh-user "https://api.github.com/users/zViniicius" >}}
    </div>
  </div>
</div>

#### [Docker Selenium](https://github.com/SeleniumHQ/docker-selenium)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/luisfcorreia" >}}
{{< gh-user "https://api.github.com/users/msvticket " >}}
{{< gh-user "https://api.github.com/users/mtcolman" >}}
{{< gh-user "https://api.github.com/users/philippe-granet" >}}
{{< gh-user "https://api.github.com/users/sehaas" >}}
{{< gh-user "https://api.github.com/users/vietnd96" >}}
    </div>
  </div>
</div>

**Thanks as well to all the [Selenium Team Members][team] who contributed to this release:**

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/bonigarcia" >}}
{{< gh-user "https://api.github.com/users/diemol" >}}
{{< gh-user "https://api.github.com/users/harsha509" >}}
{{< gh-user "https://api.github.com/users/joerg1985" >}}
{{< gh-user "https://api.github.com/users/nvborisenko" >}}
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

