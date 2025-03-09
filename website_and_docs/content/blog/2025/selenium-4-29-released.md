---
title: "Selenium 4.29 Released!"
linkTitle: "Selenium 4.29 Released!"
date: 2025-02-20
tags: ["selenium"]
categories: ["releases"]
author: Diego Molina [@diemol](https://www.diemol.com)
images:
  - "/images/blog/2025/selenium_4.29.jpg"
description: >
  Today we're happy to announce that Selenium 4.29 has been released!
---
We're very happy to announce the release of Selenium 4.29 for 
Javascript, Ruby, Python, .NET, Java and the Grid!
Links to everything can be found on our [downloads page][downloads].


---

## 🚀 Major Highlights

- **Final removal of [Firefox CDP support](/blog/2025/remove-cdp-firefox/)** across all language bindings.
- **New WebDriver BiDi capabilities**: Implementation of `setCacheBehavior` and `getClientWindows` commands.
- **Grid UI Fixes**: Live session view now works as expected.
- **PrintOptions enhancements**: Support for predefined and custom paper sizes.
- **Nullability annotations** continue to improve type safety in .NET.
- **BiDi improvements**: Network response handlers and optimizations.
- **Enhancements to logging options** in Java.

---

## 🔹 Language-Specific Changes

### **Java**
- Implemented `setCacheBehavior` for WebDriver BiDi. ([#15130](https://github.com/SeleniumHQ/selenium/pull/15130))
- Enhanced `PageSize` class to support predefined and custom paper sizes. ([#15052](https://github.com/SeleniumHQ/selenium/pull/15052))
- Ensured purging dead nodes service interval is configurable. ([#15175](https://github.com/SeleniumHQ/selenium/pull/15175))
- Improved handling of Selenium logging options. ([#15197](https://github.com/SeleniumHQ/selenium/pull/15197))
- Added support for `getClientWindows` in WebDriver BiDi. ([#14869](https://github.com/SeleniumHQ/selenium/pull/14869))

### **Python**
- Fixed installation issues for source distributions. ([#15128](https://github.com/SeleniumHQ/selenium/pull/15128))
- Updated `PrintOptions` to support different page sizes. ([#15064](https://github.com/SeleniumHQ/selenium/pull/15064))
- Documented `cygwin` path usage in `send_keys()`. ([#15275](https://github.com/SeleniumHQ/selenium/pull/15275))
- Fixed return type and docstrings for `get_downloadable_files()`. ([#15292](https://github.com/SeleniumHQ/selenium/pull/15292))

### **JavaScript**
- Implemented `setCacheBehavior` for WebDriver BiDi. ([#15136](https://github.com/SeleniumHQ/selenium/pull/15136))
- Fixed dependencies for `novnc` v1.5.0. ([#15005](https://github.com/SeleniumHQ/selenium/pull/15005))
- Added support for `getClientWindows` in WebDriver BiDi. ([#15248](https://github.com/SeleniumHQ/selenium/pull/15248))

### **Ruby**
- Removed Java date dependency. ([#15122](https://github.com/SeleniumHQ/selenium/pull/15122))
- Added WebDriver BiDi network response handler. ([#14900](https://github.com/SeleniumHQ/selenium/pull/14900))
- Implemented WebDriver BiDi `setCacheBehavior` command. ([#15114](https://github.com/SeleniumHQ/selenium/pull/15114))

### **.NET**
- Improved BiDi exception handling when it is not enabled. ([#15163](https://github.com/SeleniumHQ/selenium/pull/15163))
- Added nullability annotations across multiple modules, including `Command`, `DriverService`, `FirefoxProfile`, `Manage()`, `SafariOptions`, and `Navigate()`.
- Updated WebAuth credential handling. ([#15201](https://github.com/SeleniumHQ/selenium/pull/15201))
- Simplified creation of network types. ([#15267](https://github.com/SeleniumHQ/selenium/pull/15267))
- Improved logging stability. ([#15257](https://github.com/SeleniumHQ/selenium/pull/15257))

### **Docker Selenium**
- Publish Node/Standalone images with the latest Grid core version and browser backward versions
- Update container environment to JDK21 ([#2642](https://github.com/SeleniumHQ/docker-selenium/pull/2642))
- Node base with share system certificate support ([#2653](https://github.com/SeleniumHQ/docker-selenium/pull/2653))
- Node container is able to restart and retry to register when `register-period` exceeded  ([#2662](https://github.com/SeleniumHQ/docker-selenium/pull/2662))
- Selenium Grid scaler in KEDA feature preview
  - Add trigger param to set custom capabilities for matching specific Nodes ([KEDA#6536](https://github.com/kedacore/keda/pull/6536))
  - Add trigger param for Node enables managed downloads capability ([KEDA#6570](https://github.com/kedacore/keda/pull/6570))
- Helm config: Set K8s node IP to all components via env var KUBERNETES_NODE_HOST_IP in template ([#2668](https://github.com/SeleniumHQ/docker-selenium/pull/2668))
- [See all changes](https://github.com/SeleniumHQ/docker-selenium/releases)


<br>

We thank all our contributors for their incredible efforts in making Selenium better with every release. ❤️

For a detailed look at all changes, check out the [release notes](https://github.com/SeleniumHQ/selenium/releases/tag/4.29).

<br>


## Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/Delta456" >}}
{{< gh-user "https://api.github.com/users/iampopovich" >}}
{{< gh-user "https://api.github.com/users/navin772" >}}
{{< gh-user "https://api.github.com/users/smortex" >}}
{{< gh-user "https://api.github.com/users/yvsvarma" >}}
    </div>
  </div>
</div>


### [Selenium Docs & Website](https://github.com/SeleniumHQ/seleniumhq.github.io)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/AndreyJVM" >}}
{{< gh-user "https://api.github.com/users/Delta456" >}}
{{< gh-user "https://api.github.com/users/alaahong" >}}
{{< gh-user "https://api.github.com/users/automatealchemist" >}}
{{< gh-user "https://api.github.com/users/b2m" >}}
{{< gh-user "https://api.github.com/users/pallavigitwork" >}}
    </div>
  </div>
</div>

### [Docker Selenium](https://github.com/SeleniumHQ/docker-selenium)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/PeterUpfold" >}}
{{< gh-user "https://api.github.com/users/StenAL" >}}
{{< gh-user "https://api.github.com/users/amardeep2006" >}}
{{< gh-user "https://api.github.com/users/calendir" >}}
{{< gh-user "https://api.github.com/users/joshfng" >}}
{{< gh-user "https://api.github.com/users/ritzk" >}}
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
{{< gh-user "https://api.github.com/users/cgoldberg" >}}
{{< gh-user "https://api.github.com/users/diemol" >}}
{{< gh-user "https://api.github.com/users/harsha509" >}}
{{< gh-user "https://api.github.com/users/joerg1985" >}}
{{< gh-user "https://api.github.com/users/nvborisenko" >}}
{{< gh-user "https://api.github.com/users/p0deje" >}}
{{< gh-user "https://api.github.com/users/pujagani" >}}
{{< gh-user "https://api.github.com/users/RenderMichael" >}}
{{< gh-user "https://api.github.com/users/shbenzer" >}}
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
