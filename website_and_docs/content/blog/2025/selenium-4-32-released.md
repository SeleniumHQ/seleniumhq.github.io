---
title: "Selenium 4.32 Released!"
linkTitle: "Selenium 4.32 Released!"
date: 2025-05-05
tags: [ "selenium" ]
categories: [ "releases" ]
author: Diego Molina [@diemol](https://www.diemol.com)
images:
  - "/images/blog/2025/selenium_4.32.jpg"
description: >
  Today we're happy to announce that Selenium 4.32 has been released!
---

We’re excited to announce the release of **Selenium 4.32** for Javascript, Ruby, Python, .NET, Java
and the Grid! 🎉  
This release continues the focus on strengthening BiDi support across multiple bindings, improving
stability in tests, and refining documentation and developer experience.

Links to all assets can be found on our [downloads page][downloads].


---

## 🚀 Major Highlights

- Enhanced **BiDi (Bi-Directional)** protocol support for Python, Java, Ruby, and .NET bindings
- Dozens of **bug fixes and stability improvements** in tests and documentation
- Selenium Grid now better handles **capabilities for mobile testing with Relay Nodes**
- New utility class in Python to manage a local Grid server
- Additional updates to support AOT compatibility and memory optimizations in .NET

---

## 🔹 Language-Specific Changes

### **Java**

- BiDi improvements: `onNavigationCommitted`, `getClientWindows`, and Edge support [#15560](https://github.com/SeleniumHQ/selenium/pull/15560), [#15661](https://github.com/SeleniumHQ/selenium/pull/15661)
- BiDi tests enabled for Edge network module [#15654](https://github.com/SeleniumHQ/selenium/pull/15654)
- Set BiDi as active protocol for Remote Firefox [#15224](https://github.com/SeleniumHQ/selenium/pull/15224)
- Dependency versioning improvements via BOM [#15689](https://github.com/SeleniumHQ/selenium/pull/15689)

### **Python**

- Fixes to test args for `--headless` and `--bidi` [#15567](https://github.com/SeleniumHQ/selenium/pull/15567)
- Improvements in test coverage and cleanup [#15579](https://github.com/SeleniumHQ/selenium/pull/15579), [#15580](https://github.com/SeleniumHQ/selenium/pull/15580)
- FedCM state leak fix [#15583](https://github.com/SeleniumHQ/selenium/pull/15583)
- BiDi Network: intercepts and authentication implemented [#14592](https://github.com/SeleniumHQ/selenium/pull/14592)
- Implemented BiDi `browser`, `browsing_context`, and `log` modules [#15616](https://github.com/SeleniumHQ/selenium/pull/15616), [#15631](https://github.com/SeleniumHQ/selenium/pull/15631), [#15668](https://github.com/SeleniumHQ/selenium/pull/15668)
- Added `Server` utility class to manage Grid [#15666](https://github.com/SeleniumHQ/selenium/pull/15666)
- Modernized linting setup and doc publishing [#15614](https://github.com/SeleniumHQ/selenium/pull/15614)

### **JavaScript**

- [Set remote active protocol in Firefox to BiDi only](https://github.com/SeleniumHQ/selenium/commit/a1ff120a9fd69daeea6a51d41aee6beb83748895)

### **Ruby**

- Added `PrintOptions` support [#15158](https://github.com/SeleniumHQ/selenium/pull/15158)
- WebSocket port handling for Firefox [#15458](https://github.com/SeleniumHQ/selenium/pull/15458)
- BiDi `setViewport`, `activate`, and log support enhanced [#15290](https://github.com/SeleniumHQ/selenium/pull/15290), [#15365](https://github.com/SeleniumHQ/selenium/pull/15365)


### **.NET**

- Extensive BiDi refactoring for better spec alignment and AOT compatibility [#15575](https://github.com/SeleniumHQ/selenium/pull/15575), [#15591](https://github.com/SeleniumHQ/selenium/pull/15591)
- Introduced strong typing for LocalValue conversions [#15532](https://github.com/SeleniumHQ/selenium/pull/15532)
- Refined network interception and error handling [#15603](https://github.com/SeleniumHQ/selenium/pull/15603), [#15521](https://github.com/SeleniumHQ/selenium/pull/15521)
- Websocket memory and platform detection improvements [#15640](https://github.com/SeleniumHQ/selenium/pull/15640), [#15649](https://github.com/SeleniumHQ/selenium/pull/15649)

### **Grid**

- Fixed Safari-specific capability prefix handling [#15574](https://github.com/SeleniumHQ/selenium/pull/15574)
- Improved handling of `browserName` for Relay Nodes in mobile [#15537](https://github.com/SeleniumHQ/selenium/pull/15537)

### **Docker Selenium**

- Docker: Init python venv with non-root user ([#2769](https://github.com/SeleniumHQ/docker-selenium/pull/2769))
- Docker: Remove Hub GraphQL dependency from video recorder ([#2813](https://github.com/SeleniumHQ/docker-selenium/pull/2813))
- Docker: Fluxbox not rendering Chinese characters via VNC view ([#2817](https://github.com/SeleniumHQ/docker-selenium/pull/2817))
- [See all changes](https://github.com/SeleniumHQ/docker-selenium/releases)


<br>

We thank all our contributors for their incredible efforts in making Selenium better with every
release. ❤️

For a detailed look at all changes, check out
the [release notes](https://github.com/SeleniumHQ/selenium/releases/tag/4.32).

<br>

## Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/Delta456" >}}
{{< gh-user "https://api.github.com/users/FFederi" >}}
{{< gh-user "https://api.github.com/users/navin772" >}}
{{< gh-user "https://api.github.com/users/yvsvarma" >}}
    </div>
  </div>
</div>

### [Selenium Docs & Website](https://github.com/SeleniumHQ/seleniumhq.github.io)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/HandyCC" >}}
{{< gh-user "https://api.github.com/users/Ozoniuss" >}}
{{< gh-user "https://api.github.com/users/alaahong" >}}
{{< gh-user "https://api.github.com/users/manoj9788" >}}
{{< gh-user "https://api.github.com/users/pallavigitwork" >}}
    </div>
  </div>
</div>

### [Docker Selenium](https://github.com/SeleniumHQ/docker-selenium)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/Trigtrig" >}}
{{< gh-user "https://api.github.com/users/lermit" >}}
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
