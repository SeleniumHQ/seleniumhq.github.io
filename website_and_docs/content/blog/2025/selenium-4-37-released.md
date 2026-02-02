---
title: "Selenium 4.37 Released!"
linkTitle: "Selenium 4.37 Released!"
date: 2025-10-16
tags: [ "selenium" ]
categories: [ "releases" ]
author: Diego Molina [@diemol](https://www.diemol.com)
images:
  - "/images/blog/2025/selenium_4.37.jpg"
description: >
  Today we're happy to announce that Selenium 4.37 has been released!
---

We’re excited to announce the release of **Selenium 4.37** for JavaScript, Ruby, Python, .NET, Java
and the Grid! 🎉

Links to all assets can be found on our [downloads page][downloads].


---

# Selenium 4.37 Released

Selenium 4.37 continues the work on WebDriver BiDi across bindings, improves CI reliability and 
platform support, and delivers several cleanups and internal refactors. This release also tightens 
type safety, modernizes APIs, and removes legacy functionality that had been deprecated for some 
time.

---

## Highlights

- **Chrome DevTools support** is now: v141, v140, and v139.
- **BiDi Expansion in .NET**: Emulation, downloads, network commands, and major internal performance and serialization improvements.
- **Python Platform and CI Updates**: Python 3.14 support, Python 3.9 dropped, and Windows fully restored in CI.
- **Java Reliability Improvements**: Better error reporting during session creation and new HTTP client capabilities.
- **Grid Stability**: Event bus heartbeat added to prevent stale or stolen connections.
- **Cleanup of Deprecated APIs**: Removal of obsolete FTP proxy support and other long-deprecated paths.

---

## Language-specific Changes

### Java

- Improved error handling:
  - Remote session creation errors now properly surface underlying causes.
- Added **NullAway** static analysis for improved null-safety.
- Introduced native **Java 11 HTTP client methods** to the `HttpClient` interface.
- Refactored remote command logic to reduce duplication.
- Made BiDi and DevTools augmentation lazy-loaded.
- Added JSpecify annotations for:
  - `org.openqa.selenium.grid.jmx`
  - `org.openqa.selenium.bidi.permissions`
- Fixed ARM Linux architecture detection (`aarch64`).
- Documentation typo fix in `invisibilityOf`.

---

### Python

- **Platform support**:
  - Added support for Python 3.14.
  - Dropped support for Python 3.9.
- BiDi improvements:
  - Added tests for classic navigation request handlers.
  - Fixed default `rpId` in virtual authenticator.
  - Raised `NotImplementedError` for unsupported download deletion.
- Configuration and stability:
  - WebSocket timeout and wait interval now configurable via `ClientConfig`.
  - Chromium kwargs defaults restored.
  - MIME types now guessed when serving content via the webserver.
- CI and testing:
  - Windows re-enabled in CI workflows.
  - Added unit test jobs and Windows integration tests.
  - Internal Remote tests now use Chrome instead of Firefox.
- Code quality:
  - Extensive mypy fixes.
  - Updated docstrings to Google style guidelines.
  - Removed legacy Travis CI xfail markers.

---

### .NET

- **Major BiDi enhancements**:
  - Emulation module support.
  - Download events and `SetDownloadBehaviour`.
  - Network commands including `SetExtraHeaders`.
  - Added `Request` data type in Network module.
- Performance and correctness:
  - Faster message deserialization.
  - Immediate type info during serialization.
  - Replaced lazy caching with eager initialization.
  - AOT-safe enum serialization.
  - Correct handling of negative zero responses.
- API cleanup:
  - Removed obsolete `FtpProxy`.
  - Removed unnecessary command type metadata.
  - Introduced specific result types per command.
- Infrastructure:
  - Improved support for copying Selenium Manager binaries in .NET Framework projects.

---

### Ruby

- Updated Chrome and Edge arguments used in test environments.
- Removed `prism` dependency.
- Removed JSON version constraint.
- BiDi:
  - Removed Firefox guard for download event listening.
- Test suite fixes and general cleanup.

---

### Selenium Grid

- Added **event bus heartbeat** to prevent stale or stolen connections between components.
- Continued internal stability and connection management improvements.

---

## Build, CI, and Tooling

- Stress tests split for better isolation and reliability.
- CI workflows updated and stabilized across platforms.
- Internal file structure adjustments to match browser protocol changes.
- Version metadata handling cleaned up in PDL files.

---
### 🐳 Docker Selenium

- Docker: Switch supply chain to get backward Chrome versions deb binary
- Update Helm release postgresql to v18 (#2980)
- Update Helm release redis to v23 (#2981)
- Update softprops/action-gh-release action to v2.4.0 (#2982)
- chore(deps): update dependency python to 3.14 (#2983)
- chore(deps): update ubuntu:noble docker digest to 66460d5 (#2989)
- chore(deps): update helm release kube-prometheus-stack to v78 (#2985)
- Docker: Roll back default value of `SE_DISTRIBUTOR_SLOT_SELECTOR` as empty (#2993)
- K8s: Instead of probe to restart Distributor, adding event bus heartbeat to prevent steal connection (#2995)
- [See all changes](https://github.com/SeleniumHQ/docker-selenium/releases)


<br>

We thank all our contributors for their incredible efforts in making Selenium better with every
release. ❤️

For a detailed look at all changes, check out
the [release notes](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.37.0).

<br>

## Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/Earlopain" >}}
{{< gh-user "https://api.github.com/users/diemol" >}}                                                                                                                                                      
{{< gh-user "https://api.github.com/users/ethbra" >}}                                                                                                                                                      
{{< gh-user "https://api.github.com/users/iampopovich" >}}                                                                                                                                                 
{{< gh-user "https://api.github.com/users/manuelsblanco" >}}                                                                                                                                               
{{< gh-user "https://api.github.com/users/mk868" >}}                                                                                                                                                       
{{< gh-user "https://api.github.com/users/mkurz" >}}                                                                                                                                                       
{{< gh-user "https://api.github.com/users/nnnnoel" >}}                                                                                                                                                     
{{< gh-user "https://api.github.com/users/rpallavisharma" >}}                                                                                                                                              
    </div>
  </div>
</div>

### [Selenium Docs & Website](https://github.com/SeleniumHQ/seleniumhq.github.io)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/Benjamin-Loison" >}}
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
{{< gh-user "https://api.github.com/users/asolntsev" >}} 
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
