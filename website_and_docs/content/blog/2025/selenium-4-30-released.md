---
title: "Selenium 4.30 Released!"
linkTitle: "Selenium 4.30 Released!"
date: 2025-03-21
tags: [ "selenium" ]
categories: [ "releases" ]
author: Diego Molina [@diemol](https://www.diemol.com)
images:
  - "/images/blog/2025/selenium_4.30.jpg"
description: >
  Today we're happy to announce that Selenium 4.30 has been released!
---

We're very happy to announce the release of Selenium 4.30 for Javascript, Ruby, Python, .NET, Java
and the Grid!
This version brings key updates across the project, with improvements to the BiDi protocol,
extensive nullability work in .NET, better error handling, and various bug fixes. It’s a great
step forward as we continue strengthening Selenium’s stability, consistency, and support across
all supported languages.

Links to all assets can be found on our [downloads page][downloads].


---

## 🚀 Major Highlights

- Continued enhancements to **BiDi (Bi-Directional Protocol)** support across Java, Ruby, .NET, JavaScript, and Python.
- Extensive **nullability annotations** added throughout the .NET bindings.
- Selenium Manager (Rust) now supports **nightly Grid builds**.
- Improvements to testing infrastructure and developer experience, including better packaging, linting, and platform support.
- Numerous bug fixes and refactors across the Grid, bindings, and devtools.

---

## 🔹 Language-Specific Changes

### **Java**

- Implemented BiDi commands: `getBidiSessionStatus` and `Permissions`.
- Refined logger initialization.
- Removed deprecated, non-W3C compliant `NetworkConnection` interface.
- Added support for setting viewport and handling CDP warnings gracefully.

### **Python**

- Improved devtools test handling and documentation.
- Fixed packaging issues and test discovery for `pytest`.
- Added docstring updates for clarity and modernization.
- Replaced strings with `By` class attributes.
- Improved socket resource management and error handling.
- Updated `expected_conditions` type annotations.

### **JavaScript**

- Fixed BiDi tests for Chrome and Firefox on CI.
- Implemented BiDi `permissions` module commands.

### **Ruby**

- Fixed a compatibility issue with Ruby 3.1 ("no anonymous block parameter").
- Added BiDi support for:
  - Setting viewport
  - Activating browser context
  - Providing responses
- Added a `target_type` parameter to devtools.

### **.NET**

- Enabled **nullable reference types** across many components.
- Trimmed away CDP for **AOT** applications.
- Enhanced BiDi support including:
  - `SetFiles` command
  - Support for `UnhandledPromptBehavior`
  - Event support like `OnNavigationCommitted`
  - Encapsulation of the transport layer
- Improved `WebDriver`, `WebElement`, and capabilities types with nullability.
- Introduced `SystemClock` singleton.
- Revisited and fixed test execution on Windows/macOS.
- Removed obsoleted members for 4.30.

### **Grid & Selenium Manager**

- Added trace logging for session stop events in Grid.
- Improved configuration options for server timeouts and session handling.
- Added support in Selenium Manager (Rust) for **nightly Grid builds**.
- Enhanced ability to trace and view live sessions.

### **Docker Selenium**

- Helm config: Node Relay to extend autoscaling Grid with test cloud resources ([#2703](https://github.com/SeleniumHQ/docker-selenium/pull/2703)).
- Docker: Disable HeapDumpOnOutOfMemoryError by default ([#2708](https://github.com/SeleniumHQ/docker-selenium/pull/2708))
- [See all changes](https://github.com/SeleniumHQ/docker-selenium/releases)


<br>

We thank all our contributors for their incredible efforts in making Selenium better with every
release. ❤️

For a detailed look at all changes, check out
the [release notes](https://github.com/SeleniumHQ/selenium/releases/tag/4.30).

<br>

## Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/Delta456" >}}
{{< gh-user "https://api.github.com/users/FloKNetcare" >}}
{{< gh-user "https://api.github.com/users/ahalbrock" >}}
{{< gh-user "https://api.github.com/users/allrob23" >}}
{{< gh-user "https://api.github.com/users/jpawlyn" >}}
{{< gh-user "https://api.github.com/users/navin772" >}}
{{< gh-user "https://api.github.com/users/smortex" >}}
{{< gh-user "https://api.github.com/users/pallavigitwork" >}}
{{< gh-user "https://api.github.com/users/ahalbrock" >}}
    </div>
  </div>
</div>

### [Selenium Docs & Website](https://github.com/SeleniumHQ/seleniumhq.github.io)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/Delta456" >}}
{{< gh-user "https://api.github.com/users/WasiqB" >}}
{{< gh-user "https://api.github.com/users/alaahong" >}}
{{< gh-user "https://api.github.com/users/beinghumantester" >}}
{{< gh-user "https://api.github.com/users/franciscotrenco" >}}
{{< gh-user "https://api.github.com/users/pallavigitwork" >}}
    </div>
  </div>
</div>

### [Docker Selenium](https://github.com/SeleniumHQ/docker-selenium)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/VietND96" >}}
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
