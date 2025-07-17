---
title: "Selenium 4.34 Released!"
linkTitle: "Selenium 4.34 Released!"
date: 2025-06-29
tags: [ "selenium" ]
categories: [ "releases" ]
author: Diego Molina [@diemol](https://www.diemol.com)
images:
  - "/images/blog/2025/selenium_4.34.jpg"
description: >
  Today we're happy to announce that Selenium 4.34 has been released!
---

We’re excited to announce the release of **Selenium 4.34** for Javascript, Ruby, Python, .NET, Java
and the Grid! 🎉

Links to all assets can be found on our [downloads page][downloads].


---

## 🔦 Highlights

- **macOS Improvements**: Added macOS-specific key support for both Ruby and Python.
- **Web Extension Support**: BiDi implementations now support Chromium web extensions (Java, Python).
- **Deprecations**: FTP proxy support deprecated across Java, Python, Ruby, and .NET.
- **Selenium Manager**: Now supports Electron (Rust backend). Still needs implementation in the bindings.
- **BiDi Enhancements**: Continued progress with `historyUpdated`, `permissions`, and `storage` modules (Java, .NET, Python).
- **Quality Improvements**: Significant type annotation cleanup, test stability enhancements, and doc generation in Python.

### Java

- ✅ Implemented BiDi commands:
    - `browsingContext.historyUpdated`
    - `webExtensions` and extended `BrowsingContextInfo`
- 🛠 Refactored `CommandPayload`, removed deprecated classes:
    - `ContextAware`
    - `CommandLine`
    - `OsProcess`
- ⚠️ Deprecated `FtpProxy`
- ➕ Environment variable support for driver paths with Selenium Manager
- 🔐 Improvements in `VirtualAuthenticator`

### Python

- 🔑 Added macOS-specific keys to `Keys` enum (`OPTION`, `FN`)
- 🧠 Extensive BiDi updates:
    - WebExtensions
    - Permissions
    - Storage
    - History updates (with timestamps)
- 🧼 Code quality:
    - mypy/type hint cleanups
    - API docs improvements (auto-generated)
    - tox/ruff upgrades
- 💡 `enable_webextensions()` now documented with CDP note
- ❌ Deprecated: FTP proxy support
- 🌐 Better error reporting on HTTP failures, improved error handling in `expected_conditions`

### .NET

- 🚫 Deprecated FTP proxy support
- 📚 BiDi enhancements:
    - `OnHistoryUpdated` event
    - AcceptInsecureCerts & Proxy in user context
    - Implicit screenshot-to-bytes conversion
    - Protected DTOs from inheritance
- 🧹 Cleanup:
    - Namespace simplifications
    - Removed StyleCop config

### JavaScript

- 📢 Warning added when FTP proxy is used
- 💡 Declared minimum required Node.js version: `>= 20.0.0`

### Grid
- 🧪 Grid UI updated to Node 20 for type compatibility
- 🧰 New built-in slot selector: `GreedySlotSelector`
- 🧹 UI cleanup: session deletion, log level validation

### Ruby

- 🧑‍💻 Added macOS key mappings (Option/Fn)
- ⚠️ Deprecated FTP proxy support
- 🛠 Fixed child process termination handling

### Rust (Selenium Manager)

- 🖥️ Added **Electron** browser support
- 🔧 Fixed Edge version test logic
- Electron support.

### Docker Selenium

- K8s: Distributor uses Greedy as slot selector strategy in autoscaling ([#2875](https://github.com/SeleniumHQ/docker-selenium/pull/2875))
- K8s: Fix video uploader secrets pass to Node single container ([#2886](https://github.com/SeleniumHQ/docker-selenium/pull/2886))
- Docker: Update dependencies version for CVEs fix
- Docker: Enable `SE_NODE_ENABLE_MANAGED_DOWNLOADS` in Node config by default ([#2869](https://github.com/SeleniumHQ/docker-selenium/pull/2869))
- Docker: Session created in Node container can be deleted on UI by default ([#2871](https://github.com/SeleniumHQ/docker-selenium/pull/2871))
- Docker: Environment variable flag to upgrade latest version of Chrome and ChromeDriver in container ([#2872](https://github.com/SeleniumHQ/docker-selenium/pull/2872))
- [See all changes](https://github.com/SeleniumHQ/docker-selenium/releases)


<br>

We thank all our contributors for their incredible efforts in making Selenium better with every
release. ❤️

For a detailed look at all changes, check out
the [release notes](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.34.0).

<br>

## Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/AB-xdev" >}}
{{< gh-user "https://api.github.com/users/Bradltr95" >}}
{{< gh-user "https://api.github.com/users/Delta456" >}}
{{< gh-user "https://api.github.com/users/LuisOsv" >}}
{{< gh-user "https://api.github.com/users/ShauryaDusht" >}}
{{< gh-user "https://api.github.com/users/adolfoarmas" >}}
{{< gh-user "https://api.github.com/users/asolntsev" >}}
{{< gh-user "https://api.github.com/users/iampopovich" >}}                                                                                                                                                                 
{{< gh-user "https://api.github.com/users/manuelsblanco" >}}                                                                                                                                                               
{{< gh-user "https://api.github.com/users/pallavigitwork" >}}                                                                                                                                                              
{{< gh-user "https://api.github.com/users/sandeepsuryaprasad" >}}                                                                                                                                                          
    </div>
  </div>
</div>

### [Selenium Docs & Website](https://github.com/SeleniumHQ/seleniumhq.github.io)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/ShinySaana" >}}
{{< gh-user "https://api.github.com/users/alaahong" >}}
{{< gh-user "https://api.github.com/users/ivonnegattringer" >}}
{{< gh-user "https://api.github.com/users/navin772" >}}
{{< gh-user "https://api.github.com/users/noritaka1166" >}}
{{< gh-user "https://api.github.com/users/pallavigitwork" >}}
    </div>
  </div>
</div>

### [Docker Selenium](https://github.com/SeleniumHQ/docker-selenium)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/KyriosGN0" >}}
{{< gh-user "https://api.github.com/users/cgoldberg" >}}
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
