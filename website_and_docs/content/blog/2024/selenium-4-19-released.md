---
title: "Selenium 4.19 Released!"
linkTitle: "Selenium 4.19 Released!"
date: 2024-03-27
tags: ["selenium"]
categories: ["releases"]
author: Diego Molina [@diemol](https://diemol.com)
images:
  - "/images/blog/selenium_4.19.png"
description: >
  Today we're happy to announce that Selenium 4.19 has been released!
---

We're very happy to announce the release of Selenium 4.19.0 for 
Javascript, Ruby, Python, .NET, Java and the Grid!
Links to everything can be found on our [downloads page][downloads].

### Highlights

  * Chrome DevTools support is now: v121, v122, and v123 (Firefox still uses v85 for all versions)
  * Thanks to Selenium Manager's [records usage](https://plausible.io/manager.selenium.dev), we know Selenium has at least 1.8M active users in the last 30 days!
  * Java and JavaScript keep adding more WebDriver [BiDi] features.
  * The WebDriver [BiDi] features in Java have a new home, read it at Puja's [blog post](/blog/2024/bidi-java-breaking-change).


#### Noteworthy changes per language

  * Java 
    * `se:bidi` was removed from the session response when starting a Grid session, as `webSocketUrl` is enough.
    * Memory allocation and thread handling was improved in Grid.
    * Add missing event handlers for TargetLocator interface in `WebDriverListener`.
    * Several [BiDi] additions: adding `setFiles` command of the Input Module, between others. 
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/java/CHANGELOG)


  <br>
  
  * JavaScript
    * Several [BiDi] additions: adding `setFiles` command of the Input Module, between others.
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/javascript/node/selenium-webdriver/CHANGES.md)
  
  <br>
  
  * .NET
    * The `ChromiumDriverService.AllowedIPAddresses` name was corrected
    * It is now possible to set timeouts via capabilities
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/dotnet/CHANGELOG)

  <br>
  
  * Python
    * More network interfaces were added to detect lan ip
    * The `install_addon()` (Firefox extensions) was improved to take into account dir paths with trailing slashes
    * Improvements for type parameters
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/py/CHANGES)

  <br>
  
  * Ruby
    * Full RBS support was added (#13234)
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/rb/CHANGES)


  <br>

  * Rust
    * Improving how MS Edge is downloaded
    * Fix how MS Edge is managed in RPM-based Linux
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/rust/CHANGELOG.md)



### Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

#### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/adamtheturtle" >}}
{{< gh-user "https://api.github.com/users/aguspe" >}}
{{< gh-user "https://api.github.com/users/jkbzh" >}}
{{< gh-user "https://api.github.com/users/sbabcoc" >}}
{{< gh-user "https://api.github.com/users/zhani" >}}
    </div>
  </div>
</div>


#### [Selenium Docs & Website](https://github.com/SeleniumHQ/seleniumhq.github.io)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/AlphaWong" >}}
{{< gh-user "https://api.github.com/users/amardeep2006" >}}
{{< gh-user "https://api.github.com/users/jkbzh" >}}
{{< gh-user "https://api.github.com/users/mmonfared" >}}
{{< gh-user "https://api.github.com/users/rdinoff" >}}
    </div>
  </div>
</div>

#### [Docker Selenium](https://github.com/SeleniumHQ/docker-selenium)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/VietND96" >}}
{{< gh-user "https://api.github.com/users/cedricroijakkers" >}}
{{< gh-user "https://api.github.com/users/maxmanuylov" >}}
{{< gh-user "https://api.github.com/users/msvticket" >}}
    </div>
  </div>
</div>

#### [Selenium Team Members][team]

**Thanks as well to all the team members who contributed to this release:**

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/pujagani" >}}
{{< gh-user "https://api.github.com/users/diemol" >}}
{{< gh-user "https://api.github.com/users/bonigarcia" >}}
{{< gh-user "https://api.github.com/users/harsha509" >}}
{{< gh-user "https://api.github.com/users/joerg1985" >}}
{{< gh-user "https://api.github.com/users/nvborisenko" >}}
{{< gh-user "https://api.github.com/users/p0deje" >}}
{{< gh-user "https://api.github.com/users/shs96c" >}}
{{< gh-user "https://api.github.com/users/titusfortner" >}}
{{< gh-user "https://api.github.com/users/AutomatedTester" >}}
 </div>
  </div>
</div>

Stay tuned for updates by following SeleniumHQ on [X (Formerly Twitter)](https://twitter.com/seleniumhq) or [LinkedIn](https://www.linkedin.com/company/selenium/)!

Happy testing!

[downloads]: /downloads
[bindings]: /downloads#bindings
[team]: /project/structure
[BiDi]: https://github.com/w3c/webdriver-bidi
