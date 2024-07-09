---
title: "Selenium 4.20 Released!"
linkTitle: "Selenium 4.20 Released!"
date: 2024-04-25
tags: ["selenium"]
categories: ["releases"]
author: Diego Molina [@diemol](https://diemol.com)
images:
  - "/images/blog/selenium_4.20.png"
description: >
  Today we're happy to announce that Selenium 4.20 has been released!
---

We're very happy to announce the release of Selenium 4.20.0 for 
Javascript, Ruby, Python, .NET, Java and the Grid!
Links to everything can be found on our [downloads page][downloads].

### Highlights

  * Chrome DevTools support is now: v122, v123, and v124 (Firefox still uses v85 for all versions)
  * Selenium has at least [2.3M active users](https://plausible.io/manager.selenium.dev) in the last 30 days. 500k more than last month!
  * Java and JavaScript keep adding more WebDriver [BiDi] features.
  * .NET C# and JavaScript now publish [nightly builds to GitHub packages](/downloads/#nightly).
  * Nightly packages are tested daily with the examples from the Selenium [documentation](/documentation). 
  * The code used to invoke Selenium Manager has been refactored in all languages, making it easier to maintain and improve. 
    * The interface has changed and if users were invoking it, they might experience issues. 
      Selenium Manager is still in beta and these type of changes are expected. 


#### Noteworthy changes per language

  * Java 
    * Browser containers provisioned in Dynamic Grid will use the hostConfig
    * Dynamic Grid re-fetches browser images if they were pruned during runtime
    * Several [BiDi] additions: Update browsing context create method, between others. 
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/java/CHANGELOG)


  <br>
  
  * JavaScript
    * Several [BiDi] additions: Update capture screenshot APIs to include all parameters and remove scroll parameter, between others
    * Nightly JS builds are now pushed to GitHub packages
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/javascript/node/selenium-webdriver/CHANGES.md)
  
  <br>
  
  * .NET
    * The correct devtools session id is now used after reinitialization
    * Nightly .NET C# builds are now pushed to GitHub packages
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/dotnet/CHANGELOG)

  <br>
  
  * Python
    * Improvements for type hints in parameters
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/py/CHANGES)

  <br>
  
  * Ruby
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/rb/CHANGES)


  <br>

  * Rust
    * Use DEBUG level instead of WARN traces in offline mode
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/rust/CHANGELOG.md)



### Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

#### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/Trigtrig" >}}
{{< gh-user "https://api.github.com/users/VietND96" >}}
    </div>
  </div>
</div>


#### [Selenium Docs & Website](https://github.com/SeleniumHQ/seleniumhq.github.io)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/Sakif-Al-Faruque" >}}
{{< gh-user "https://api.github.com/users/VietND96" >}}
{{< gh-user "https://api.github.com/users/aguspe" >}}
{{< gh-user "https://api.github.com/users/digitalvoice-nz" >}}
{{< gh-user "https://api.github.com/users/harshitBhardwaj97" >}}
{{< gh-user "https://api.github.com/users/pallavigitwork" >}}
{{< gh-user "https://api.github.com/users/zipperer" >}}
    </div>
  </div>
</div>

#### [Docker Selenium](https://github.com/SeleniumHQ/docker-selenium)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/Earlopain" >}}
{{< gh-user "https://api.github.com/users/VietND96" >}}
{{< gh-user "https://api.github.com/users/maxmanuylov" >}}
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
