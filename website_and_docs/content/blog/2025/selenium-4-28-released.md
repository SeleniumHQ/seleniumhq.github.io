---
title: "Selenium 4.28 Released!"
linkTitle: "Selenium 4.28 Released!"
date: 2025-01-20
tags: ["selenium"]
categories: ["releases"]
author: Diego Molina [@diemol](https://www.diemol.com)
images:
  - "/images/blog/2025/selenium_4.28.jpg"
description: >
  Today we're happy to announce that Selenium 4.28 has been released!
---
We're very happy to announce the release of Selenium 4.28 for 
Javascript, Ruby, Python, .NET, Java and the Grid!
Links to everything can be found on our [downloads page][downloads].


<br>

## **Highlights**
- **Chrome DevTools support** is now: v132, v131, and v130 (Firefox still uses v85 for all versions)
- Expanded **nullability annotations** for better type safety in .NET and Java.
- Refinements to **Selenium Grid**, including more efficient session handling and node management.
- **Packaging and installation enhancements** across Python and Ruby for smoother integration.
- **Documentation improvements** across Python and .NET libraries, ensuring clearer developer guidance.
- Updated **language-specific implementations** for modern development standards.

<br>

## **Changes by Language**

### **Java**
- **Encapsulation Improvements**: Encapsulated `additionalCommands` with a getter method ([#14816](https://github.com/SeleniumHQ/selenium/pull/14816)).
- **Nullability Enhancements**: Added nullness annotations for enums, exceptions, interactions, logging, and Proxy ([#15105](https://github.com/SeleniumHQ/selenium/pull/15105), [#15094](https://github.com/SeleniumHQ/selenium/pull/15094)).
- **SpotBugs Updates**: Excluded specific warnings to maintain clean code ([#14766](https://github.com/SeleniumHQ/selenium/pull/14766)).
- **Improved Logging**: Enhanced error handling and message clarity in exceptions ([#15116](https://github.com/SeleniumHQ/selenium/pull/15116)).
- **Relative Locators**: Updates for `RelativeBy` locators, simplifying usage ([#14482](https://github.com/SeleniumHQ/selenium/pull/14482)).

<br>

### **Python**
- **Packaging Fixes**: Addressed issues for smoother installation ([#14806](https://github.com/SeleniumHQ/selenium/pull/14806), [#14823](https://github.com/SeleniumHQ/selenium/pull/14823)).
- **Documentation Upgrades**: Added comprehensive docstrings to multiple classes, including `WebDriverWait`, `ExpectedConditions`, and `WebElement` ([#15077](https://github.com/SeleniumHQ/selenium/pull/15077), [#15096](https://github.com/SeleniumHQ/selenium/pull/15096)).
- **Refactoring**: Moved project metadata and improved code organization ([#14837](https://github.com/SeleniumHQ/selenium/pull/14837)).
- **Enhanced CDP Command Handling**: Added `execute_cdp_cmd` to `Remote` ([#14809](https://github.com/SeleniumHQ/selenium/pull/14809)).

<br>

### **JavaScript**
- **Federated Credential Management Support**: Introduced support for Federated Credential Management, enhancing authentication capabilities. ([#15008](https://github.com/SeleniumHQ/selenium/pull/15008))
- **Node.js Version Specification**: The minimum required Node.js version has been specified as 18.20.5.
- **Improved Logging**: Added detailed error messages for invalid cookie name validation in `getCookie` and `deleteCookie` methods, aiding in debugging.
- **Diagnostic Logging for Safari**: Enabled diagnostic logging for Safari, facilitating better issue tracking and resolution.

<br>

### **.NET**
- **Nullability Improvements**: Added annotations to `SessionId`, `Alert`, `CookieJar`, `Logs API`, and more ([#14840](https://github.com/SeleniumHQ/selenium/pull/14840), [#14874](https://github.com/SeleniumHQ/selenium/pull/14874)).
- **Refactored DevTools**: Modernized code style and enhanced JSON parsing ([#14990](https://github.com/SeleniumHQ/selenium/pull/14990)).
- **Future-Proofing**: Added notes and deprecated setters for better immutability ([#15107](https://github.com/SeleniumHQ/selenium/pull/15107)).
- **Testing Updates**: Migrated NUnit assertions to modern syntax ([#14870](https://github.com/SeleniumHQ/selenium/pull/14870)).

<br>

### **Ruby**
- **BiDi Network Enhancements**: Added request handlers for authentication and interception ([#14751](https://github.com/SeleniumHQ/selenium/pull/14751)).
- **Cookie Management Updates**: Added tests and improved handling for cookies ([#14843](https://github.com/SeleniumHQ/selenium/pull/14843)).

<br>

### **Grid**
- **Improved Session Management**: Enhanced slot matching and session queue handling ([#14914](https://github.com/SeleniumHQ/selenium/pull/14914)).
- **Dynamic Grid Enhancements**: Added video recording capabilities on browser node ([#15047](https://github.com/SeleniumHQ/selenium/pull/15047)).
- **Reliability Boost**: Improved HTTP request retries and node health checks ([#14924](https://github.com/SeleniumHQ/selenium/pull/14924)).

<br>

### Docker Selenium
- Update procedure to install Firefox in Node/Standalone Firefox ([#2550](https://github.com/SeleniumHQ/docker-selenium/discussions/2550))
- Enable video recorder in Node/Standalone containers ([#2539](https://github.com/SeleniumHQ/docker-selenium/discussions/2539))
- Env var `SE_ENABLE_TRACING=false` is not required when starting the container anymore ([#2549](https://github.com/SeleniumHQ/docker-selenium/discussions/2549))
- Env var `SE_NODE_PLATFORM_NAME` & `SE_NODE_BROWSER_VERSION` to adjust default Node stereotypes for autoscaling ([#2520](https://github.com/SeleniumHQ/docker-selenium/pull/2520), [#2525](https://github.com/SeleniumHQ/docker-selenium/pull/2525))
- Selenium Grid scaler in KEDA improvements ([KEDA#6437](https://github.com/kedacore/keda/pull/6437), [KEDA#6477](https://github.com/kedacore/keda/pull/6477))
- [See all changes](https://github.com/SeleniumHQ/docker-selenium/releases)

  <br>

We thank all our contributors for their incredible efforts in making Selenium better with every release. ❤️

For a detailed look at all changes, check out the [release notes](https://github.com/SeleniumHQ/selenium/releases/tag/4.28).

<br>


## Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/AdamPDotty" >}}
{{< gh-user "https://api.github.com/users/DineshKumarRA" >}}
{{< gh-user "https://api.github.com/users/MustafaAgamy" >}}
{{< gh-user "https://api.github.com/users/dennisoelkers" >}}
{{< gh-user "https://api.github.com/users/iampopovich" >}}                                                                                                                                                                                                                                            
{{< gh-user "https://api.github.com/users/lauromoura" >}}                                                                                                                                                                                                                                             
{{< gh-user "https://api.github.com/users/mk868" >}}                                                                                                                                                                                                                                                  
{{< gh-user "https://api.github.com/users/navin772" >}}                                                                                                                                                                                                                                               
{{< gh-user "https://api.github.com/users/shbenzer" >}}
    </div>
  </div>
</div>


### [Selenium Docs & Website](https://github.com/SeleniumHQ/seleniumhq.github.io)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/Delta456" >}}
{{< gh-user "https://api.github.com/users/jasonren0403" >}}
{{< gh-user "https://api.github.com/users/navin772" >}}
{{< gh-user "https://api.github.com/users/pallavigitwork" >}}
{{< gh-user "https://api.github.com/users/praveenmar" >}}
{{< gh-user "https://api.github.com/users/shbenzer" >}}
{{< gh-user "https://api.github.com/users/yvsvarma" >}}
    </div>
  </div>
</div>

### [Docker Selenium](https://github.com/SeleniumHQ/docker-selenium)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/KyriosGN0" >}}
{{< gh-user "https://api.github.com/users/jbolsens-legion" >}}
{{< gh-user "https://api.github.com/users/joshfng" >}}
    </div>
  </div>
</div>

### [Selenium Team Members][team]

**Thanks as well to all the team members who contributed to this release:**

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/aguspe" >}}
{{< gh-user "https://api.github.com/users/AutomatedTester" >}}
{{< gh-user "https://api.github.com/users/bonigarcia" >}}
{{< gh-user "https://api.github.com/users/diemol" >}}
{{< gh-user "https://api.github.com/users/harsha509" >}}
{{< gh-user "https://api.github.com/users/joerg1985" >}}
{{< gh-user "https://api.github.com/users/nvborisenko" >}}
{{< gh-user "https://api.github.com/users/p0deje" >}}
{{< gh-user "https://api.github.com/users/pujagani" >}}
{{< gh-user "https://api.github.com/users/RenderMichael" >}}
{{< gh-user "https://api.github.com/users/shs96c" >}}
{{< gh-user "https://api.github.com/users/titusfortner" >}}
{{< gh-user "https://api.github.com/users/VietND96" >}}
    </div>
  </div>
</div>



Stay tuned for updates by following SeleniumHQ on:
- [Mastodon](https://mastodon.social/@seleniumHQ@fosstodon.org)
- [BlueSky](https://bsky.app/profile/seleniumconf.bsky.social)
- [LinkedIn](https://www.linkedin.com/company/selenium/)
- [Selenium Community YouTube Channel](https://www.youtube.com/@SeleniumHQProject/streams)
- [X (Formerly Twitter)](https://twitter.com/seleniumhq) 

Happy automating!

[downloads]: /downloads
[bindings]: /downloads#bindings
[team]: /project/structure
[BiDi]: https://github.com/w3c/webdriver-bidi
