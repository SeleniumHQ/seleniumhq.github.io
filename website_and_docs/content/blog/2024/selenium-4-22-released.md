---
title: "Selenium 4.22 Released!"
linkTitle: "Selenium 4.22 Released!"
date: 2024-05-16
tags: ["selenium"]
categories: ["releases"]
author: Titus Fortner [@titusfortner](https://titusfortner.com)
description: >
  Today we're happy to announce that Selenium 4.22 has been released!
---

We're very happy to announce the release of Selenium 4.22.0 for 
Javascript, Ruby, Python, .NET, Java and the Grid!
Links to everything can be found on our [downloads page][downloads].

### Highlights

  * Selenium has at least [2.6 active users](https://plausible.io/manager.selenium.dev) in the last 30 days. 200k more than last month!
    * All information we collect is publicly available.
    * The numbers only represent users who have Selenium Manager enabled and are using Selenium v4.17 or greater.
    * Python, Chrome and Windows all see the majority of use.
* Chrome DevTools support is now: v124, v125, and v126 (Firefox still uses v85 for all versions)
* The first implementations of the final BiDi API have rolled out.

#### Noteworthy changes per language

  * Java 
    * Enabling BiDi can now be accomplished by calling `enableBiDi()` on an Options class instance.
    * Video file name in Dynamic Grid can be seet with `se:videoName` capability.
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/java/CHANGELOG)

  <br>
  
  * JavaScript
    * BiDi API for console logging and JavaScript errors has been implemented. 
    * Additional BiDi implementations.
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/javascript/node/selenium-webdriver/CHANGES.md)
  
  <br>
  
  * .NET
    * The .NET bindings have started to roll out asynchronous methods.
      * The synchronous methods will still be supported, but they will call the async methods "under the hood."
      * This release adds asynchronous methods to the Navigation class.
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/dotnet/CHANGELOG)

  <br>
  
  * Python
    * This release implements a new way of working with Chrome Devtools Protocol
      * The previous implementation requires async/await pattern, so it was not backwards compatible.
      * The new implementation is backwards compatible and executes async code in separate threads.
    * Updated the webkitgtk and wpewebkit driver implementations.
    * Enabling BiDi can now be accomplished by setting the `enable_bidi()` property of an Options class instance to `True`.
    * BiDi API for console logging and JavaScript errors has been implemented.
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/py/CHANGES)

  <br>
  
  * Ruby
    * Implemented a toggle for BiDi and Classic implementations.
    * BiDi API for console logging and JavaScript errors has been implemented.
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/rb/CHANGES)


  <br>

  * Rust
    * Added the ability to stream logging information to stdout instead of after execution complete.
    * Improved binary location on Windows with native Rust methods.
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/rust/CHANGELOG.md)

### Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

#### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/3dprogramin" >}}
{{< gh-user "https://api.github.com/users/VietND96" >}}
{{< gh-user "https://api.github.com/users/aguspe" >}}
{{< gh-user "https://api.github.com/users/bgermann" >}}
{{< gh-user "https://api.github.com/users/Earlopain" >}}
{{< gh-user "https://api.github.com/users/iampopovich" >}}
{{< gh-user "https://api.github.com/users/millin" >}}
{{< gh-user "https://api.github.com/users/sbabcoc" >}}
{{< gh-user "https://api.github.com/users/vlad8x8" >}}
{{< gh-user "https://api.github.com/users/yuzawa-san" >}}
    </div>
  </div>
</div>


#### [Selenium Docs & Website](https://github.com/SeleniumHQ/seleniumhq.github.io)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/alaahong" >}}
{{< gh-user "https://api.github.com/users/aguspe" >}}
{{< gh-user "https://api.github.com/users/digitalvoice-nz
{{< gh-user "https://api.github.com/users/josh-pinwheelapi
{{< gh-user "https://api.github.com/users/pallavigitwork" >}}
{{< gh-user "https://api.github.com/users/Pexpe" >}}
{{< gh-user "https://api.github.com/users/sangcnguyen" >}}
" >}}
    </div>
  </div>
</div>

#### [Docker Selenium](https://github.com/SeleniumHQ/docker-selenium)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/VietND96" >}}
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
{{< gh-user "https://api.github.com/users/p0deje" >}}
{{< gh-user "https://api.github.com/users/pujagani" >}}
{{< gh-user "https://api.github.com/users/shs96c" >}}
{{< gh-user "https://api.github.com/users/titusfortner" >}}
    </div>
  </div>
</div>

Stay tuned for updates by following SeleniumHQ on [X (Formerly Twitter)](https://twitter.com/seleniumhq) or [LinkedIn](https://www.linkedin.com/company/selenium/)!

Happy automating!

[downloads]: /downloads
[bindings]: /downloads#bindings
[team]: /project/structure
[BiDi]: https://github.com/w3c/webdriver-bidi
