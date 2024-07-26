---
title: "Selenium 4.23 Released!"
linkTitle: "Selenium 4.23 Released!"
date: 2024-07-26
tags: ["selenium"]
categories: ["releases"]
author: Puja Jagani [@pujagani](https://github.com/pujagani)
description: >
  Today we're happy to announce that Selenium 4.23 has been released!
---

We're very happy to announce the release of Selenium 4.23.0 for 
Javascript, Ruby, Python, .NET, Java and the Grid!
Links to everything can be found on our [downloads page][downloads].

#### Noteworthy changes per language

  * Java 
    * Add custom duration for Actions constructor (#14085).
    * Set session-request-timeout as client readTimeout in RemoteNewSessionQueue.
    * Add high-level BiDi logging APIs.
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/java/CHANGELOG)

  <br>
  
  * JavaScript
    * Add source type to BiDi log entry.
    * Add high-level script pinning BiDi APIs.
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/javascript/node/selenium-webdriver/CHANGES.md)
  
  <br>
  
  * .NET
    * Log http requests/responses via internal DiagnosticsHttpHandler.
    * Return protected synchronous Execute method in WebDriver.
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/dotnet/CHANGELOG)

  <br>
  
  * Python
    * Allow RelativeBy to take 2 parameters.
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/py/CHANGES)

  <br>
  
  * Ruby
    * Improved error messaging.
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/rb/CHANGES)


  <br>

  * Rust
    * Include mixed output (INFO, WARN, DEBUG, etc. to stderr and minimal JSON to stdout.
    * Display driver path in error trace when driver is unavailable.
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/rust/CHANGELOG.md)

### Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

#### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/VietND96" >}}
{{< gh-user "https://api.github.com/users/aguspe" >}}
{{< gh-user "https://api.github.com/users/iampopovich" >}}
    </div>
  </div>
</div>


#### [Selenium Docs & Website](https://github.com/SeleniumHQ/seleniumhq.github.io)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/alaahong" >}}
{{< gh-user "https://api.github.com/users/aguspe" >}}
{{< gh-user "https://api.github.com/users/pallavigitwork" >}}
    </div>
  </div>
</div>

#### [Docker Selenium](https://github.com/SeleniumHQ/docker-selenium)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/VietND96" >}}
{{< gh-user "https://api.github.com/users/slhck" >}}
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
