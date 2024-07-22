---
title: "Selenium 4.21 Released!"
linkTitle: "Selenium 4.21 Released!"
date: 2024-05-16
tags: ["selenium"]
categories: ["releases"]
author: Diego Molina [@diemol](https://diemol.com)
images:
  - "/images/blog/selenium_4.21.png"
description: >
  Today we're happy to announce that Selenium 4.21 has been released!
---

We're very happy to announce the release of Selenium 4.21.0 for 
Javascript, Ruby, Python, .NET, Java and the Grid!
Links to everything can be found on our [downloads page][downloads].

### Highlights

  * Chrome DevTools support is now: v123, v124, and v125 (Firefox still uses v85 for all versions)
  * Selenium has at least [2.4M active users](https://plausible.io/manager.selenium.dev) in the last 30 days. 100k more than last month!
    * India and United States are the top countries with the most users.
    * Python is the most used language from the last 5 releases.
    * The most used operating system is Windows, with at least 1.9M users.
  * Extensibility points started to be implemented to simplify the integration between Selenium and Appium. Ruby is the first language to implement it. 
  * Java and JavaScript keep adding more WebDriver [BiDi] features.
  * In Java, it is possible to set parameters for Selenium Manager via system properties.
  * Nightly packages are tested daily with the examples from the Selenium [documentation](/documentation). 


#### Noteworthy changes per language

  * Java 
    * Browser containers provisioned in Dynamic Grid will use the hostConfig
    * Set test name to video file name in Dynamic Grid
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/java/CHANGELOG)


  <br>
  
  * JavaScript
    * Ensure `selectVisibleByText` method is same as other languages
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/javascript/node/selenium-webdriver/CHANGES.md)
  
  <br>
  
  * .NET
    * Overwrite internal log file if it already exists
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/dotnet/CHANGELOG)

  <br>
  
  * Python
    * Moving ignore_local_proxy_environment_variables to BaseOptions
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/py/CHANGES)

  <br>
  
  * Ruby
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/rb/CHANGES)


  <br>

  * Rust
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/rust/CHANGELOG.md)



### Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

#### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/VietND96" >}}
{{< gh-user "https://api.github.com/users/iampopovich" >}}
{{< gh-user "https://api.github.com/users/joebandenburg" >}}
{{< gh-user "https://api.github.com/users/kool79" >}}
{{< gh-user "https://api.github.com/users/sandeepsuryaprasad" >}}
    </div>
  </div>
</div>


#### [Selenium Docs & Website](https://github.com/SeleniumHQ/seleniumhq.github.io)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/Arpan3323" >}}
{{< gh-user "https://api.github.com/users/aguspe" >}}
{{< gh-user "https://api.github.com/users/chamiz" >}}
{{< gh-user "https://api.github.com/users/pallavigitwork" >}}
    </div>
  </div>
</div>

#### [Docker Selenium](https://github.com/SeleniumHQ/docker-selenium)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/VietND96" >}}
{{< gh-user "https://api.github.com/users/edhinard" >}}
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
