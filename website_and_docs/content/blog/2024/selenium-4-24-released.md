---
title: "Selenium 4.24 Released!"
linkTitle: "Selenium 4.24 Released!"
date: 2024-08-28
tags: ["selenium"]
categories: ["releases"]
author: Diego Molina [@diemol](https://www.diemol.com)
images:
  - "/images/blog/2024/selenium_4.24.png"
description: >
  Today we're happy to announce that Selenium 4.24 has been released!
---

We're very happy to announce the release of Selenium 4.24.0 for 
Javascript, Ruby, Python, .NET, Java and the Grid!
Links to everything can be found on our [downloads page][downloads].

#### Noteworthy changes per language

  * Java 
    * **BiDi Prompt/Alert Test Fix**: Fixed prompt/alert related tests if BiDi is enabled.
    * **Execute Script API**: Added execute script high-level API for BiDi.
    * **JSpecify Annotations**: Added JSpecify annotations for WebDriver, WebElement, SearchContext, and other interfaces.
    * **Dom Mutation Handler Support**: Added DOM mutation handler support for BiDi.
    * **Close HttClient on Session Failure**: Ensured HttClient is closed if starting the session fails.
    * **System Property to Disable Tracing**: Added a system property to disable tracing. ([c8676eff10](https://github.com/SeleniumHQ/selenium/commit/c8676eff107a7e5e617c6fc953baad45305cc680))
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/java/CHANGELOG)

  <br>
  
  * JavaScript
    * **High-Level Script Command for BiDi**: Added a high-level script command for BiDi.
    * **Authentication Handlers for BiDi**: Added authentication handlers for BiDi.
    * **Expose Selenium Version for Node.js**: Exposed the Selenium version for Node.js.
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/javascript/node/selenium-webdriver/CHANGES.md)
  
  <br>
  
  * .NET
    * **Migration to System.Text.Json**: Migrated from `Newtonsoft.Json` to `System.Text.Json` package.
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/dotnet/CHANGELOG)

  <br>
  
  * Python
    * **Unhandled Prompt Behavior**: Added unhandled prompt behavior to 'ignore' option if BiDi is enabled.
    * **Mypy Error Fixes**: Fixed mypy errors for various modules.
    * **Pytest Configuration Update**: Moved pytest configuration settings to `pyproject.toml`.
    * **Global Default Timeout Override**: Allowed overriding `GLOBAL_DEFAULT_TIMEOUT`.
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/py/CHANGES)

  <br>
  
  * Ruby
    * **Deprecate WebStorage JS Methods**: Deprecated WebStorage JavaScript methods.
    * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/rb/CHANGES)

  <br>

   * Rust
     * **Skipping Drivers and Browsers in Path**: Included arguments for skipping drivers and browsers in the path. ([a056044d9c](https://github.com/SeleniumHQ/selenium/commit/a056044d9c20c174e5c04804eb30a446132be60a))
     * **Use Debug Format Specifier**: Used the Debug format specifier to display error messages. ([d8a7172a2a](https://github.com/SeleniumHQ/selenium/commit/d8a7172a2a3a591af0852203449c81eb13aead2b))
     * **Firefox Version Discovery**: Used Firefox history major releases endpoint for version discovery. ([02d6903006](https://github.com/SeleniumHQ/selenium/commit/02d6903006d884f57781f5625eb33a887f4369f5))
     * [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/rust/CHANGELOG.md)


### Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

#### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/MustafaAgamy" >}}
{{< gh-user "https://api.github.com/users/aguspe" >}}
{{< gh-user "https://api.github.com/users/angiejones" >}}
{{< gh-user "https://api.github.com/users/cgossett" >}}
{{< gh-user "https://api.github.com/users/diogoteles08" >}}
{{< gh-user "https://api.github.com/users/dnwe" >}}
{{< gh-user "https://api.github.com/users/iampopovich" >}}
{{< gh-user "https://api.github.com/users/lauromoura" >}}
{{< gh-user "https://api.github.com/users/manuelsblanco" >}}
{{< gh-user "https://api.github.com/users/mdmintz" >}}
{{< gh-user "https://api.github.com/users/mk868" >}}
{{< gh-user "https://api.github.com/users/navin772" >}}
{{< gh-user "https://api.github.com/users/paveloom" >}}
{{< gh-user "https://api.github.com/users/sandeepsuryaprasad" >}}
{{< gh-user "https://api.github.com/users/shbenzer" >}}
    </div>
  </div>
</div>


#### [Selenium Docs & Website](https://github.com/SeleniumHQ/seleniumhq.github.io)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/MustafaAgamy" >}}
{{< gh-user "https://api.github.com/users/aguspe" >}}
{{< gh-user "https://api.github.com/users/alaahong" >}}
{{< gh-user "https://api.github.com/users/codespearhead" >}}
{{< gh-user "https://api.github.com/users/innazh" >}}
{{< gh-user "https://api.github.com/users/jochen-testingbot" >}}
{{< gh-user "https://api.github.com/users/pallavigitwork" >}}
{{< gh-user "https://api.github.com/users/pmartinez1" >}}
{{< gh-user "https://api.github.com/users/sbabcoc" >}}
{{< gh-user "https://api.github.com/users/shbenzer" >}}
{{< gh-user "https://api.github.com/users/skyhirider" >}}
    </div>
  </div>
</div>

#### [Docker Selenium](https://github.com/SeleniumHQ/docker-selenium)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/DrFaust92" >}}
{{< gh-user "https://api.github.com/users/Trigtrig" >}}
{{< gh-user "https://api.github.com/users/nandorpal" >}}
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
{{< gh-user "https://api.github.com/users/VietND96" >}}
    </div>
  </div>
</div>

Stay tuned for updates by following SeleniumHQ on [X (Formerly Twitter)](https://twitter.com/seleniumhq) or [LinkedIn](https://www.linkedin.com/company/selenium/)!

Happy automating!

[downloads]: /downloads
[bindings]: /downloads#bindings
[team]: /project/structure
[BiDi]: https://github.com/w3c/webdriver-bidi
