---
title: "Selenium 4.18 Released!"
linkTitle: "Selenium 4.18 Released!"
date: 2024-02-19
tags: ["selenium"]
categories: ["releases"]
author: Diego Molina [@diemol](https://diemol.com)
description: >
  Today we're happy to announce that Selenium 4.18 has been released!
---

We're very happy to announce the release of Selenium 4.18.0 and 4.18.1 for 
Javascript, Ruby, Python, .NET, Java and the Grid!
Links to everything can be found on our [downloads page][downloads].

### Highlights

  * Chrome DevTools support is now: v120, v121, and v122 (Firefox still uses v85 for all versions)
  * Selenium Manager [records usage](https://plausible.io/manager.selenium.dev) has been decreased to reduce impact on users.
  * Chrome headless changed the name of the browser to reflect that it is not actually chrome; Selenium now handles this seamlessly,
but you should still switch to `--headless=new` (see: [Headless is going away](/blog/2023/headless-is-going-away/))

#### Noteworthy changes per language

  * Java 
    * Enabling Grid to use self-signed certificate for debugging
    * Added explicit target locator events to the listener support classes in `WebDriverListener`.
    * Add missing event handlers for TargetLocator interface in `WebDriverListener`.
    * Several [BiDi] additions: Browsing context destroyed event, Network intercept commands, command `continuewithAuth`, between others.
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/java/CHANGELOG)


  <br>
  
  * JavaScript
    * Several [BiDi] additions: Browsing context destroyed event, realm destroyed event, command `continuewithAuth`, between others.
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/javascript/node/selenium-webdriver/CHANGES.md)
  
  <br>
  
  * .NET
    * Fix protocol cdp version for `RemoteWebDriver`.
    * Fix network response data encoding.
    * Explicitly support passing the full path to driver in Service constructor  
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/dotnet/CHANGELOG)

  <br>
  
  * Python
    * Python for [nightly releases](https://test.pypi.org/project/selenium/)
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/py/CHANGES)

  <br>
  
  * Ruby
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/rb/CHANGES)


  <br>

  * Rust
    * Add timestamps to Selenium Manager logs
    * Selenium Manager decreases frequency of statistics reporting
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/rust/CHANGELOG.md)



### Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

#### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/RevealOscar" >}}
{{< gh-user "https://api.github.com/users/Trigtrig" >}}
{{< gh-user "https://api.github.com/users/manuelsblanco" >}}
{{< gh-user "https://api.github.com/users/mtrea" >}}
{{< gh-user "https://api.github.com/users/oleg-rd" >}}
{{< gh-user "https://api.github.com/users/semaperepelitsa" >}}
{{< gh-user "https://api.github.com/users/symonk" >}}
{{< gh-user "https://api.github.com/users/valfirst" >}}
    </div>
  </div>
</div>


#### [Selenium Docs & Website](https://github.com/SeleniumHQ/seleniumhq.github.io)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/BlazerYoo" >}}
{{< gh-user "https://api.github.com/users/bhecquet" >}}
{{< gh-user "https://api.github.com/users/geekmister" >}}
{{< gh-user "https://api.github.com/users/justnpT" >}}
{{< gh-user "https://api.github.com/users/mmonfared" >}}
{{< gh-user "https://api.github.com/users/netassa" >}}
{{< gh-user "https://api.github.com/users/nvborisenko" >}}
{{< gh-user "https://api.github.com/users/pallavigitwork" >}}
    </div>
  </div>
</div>

#### [Docker Selenium](https://github.com/SeleniumHQ/docker-selenium)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/0xC4N1" >}}
{{< gh-user "https://api.github.com/users/Doofus100500" >}}
{{< gh-user "https://api.github.com/users/Opvolger" >}}
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
