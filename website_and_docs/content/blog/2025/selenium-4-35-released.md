---
title: "Selenium 4.35 Released!"
linkTitle: "Selenium 4.35 Released!"
date: 2025-08-12
tags: [ "selenium" ]
categories: [ "releases" ]
author: Diego Molina [@diemol](https://www.diemol.com)
images:
  - "/images/blog/2025/selenium_4.35.jpg"
description: >
  Today we're happy to announce that Selenium 4.35 has been released!
---

We’re excited to announce the release of **Selenium 4.35** for Javascript, Ruby, Python, .NET, Java
and the Grid! 🎉

Links to all assets can be found on our [downloads page][downloads].


---

## 🔦 Highlights

- **Chrome DevTools support** is now: v139, v138, and v137.
- **BiDi Improvements Across Bindings**: Expanded BiDi support including emulation, input, script execution, and user context enhancements.
- **Java Cleanup and JSpecify Annotations**: Deprecated APIs removed and comprehensive `@Nullable` annotations added for better type safety.
- **Grid Performance Enhancements**: Improved logging, reduced redundancy, race condition fixes, and migration from Guava to Caffeine.
- **Better Proxy and Network Handling**: Support for `SameSite=default`, IPv6 improvements, and fixes for proxy authentication and WebView2.
- **Logging Improvements**: Driver logs in .NET are more structured and can output to console or file with timestamps.

---

## 🧪 Language-specific Updates

### Java

- 🔧 Added support for:
  - BiDi emulation module
  - `SameSite=default` for cookies
  - Shadow DOM element normalization
- 🧹 Major cleanup of deprecated classes:
  - `LocationContext`, `WebStorage`, `FirefoxBinary`, `SessionStorage`, `AppCacheStatus`, and more
- ✅ Enhanced test coverage: `--connect-existing` WebSocket check
- 📝 Added JSpecify `@Nullable` annotations across all driver services and locator classes
- 🧼 Memory/resource improvements:
  - Released `HttpClient` resources
  - Removed unused internal classes

### Python

- 🧠 BiDi enhancements:
  - Implemented input and emulation modules
  - Added `pin`, `unpin`, and `execute` for scripts
  - Supported `accept_insecure_certs`, `proxy`, and `unhandled_prompt_behavior` in user context
- 🔧 Improved handling for:
  - Proxy authentication with special characters
  - WebView2 + CDP/BiDi compatibility
  - Vendor prefix fix for Edge
- 📦 Loosened dependency for `urllib3`, and included IPv6 support for `free_port()`
- 📚 API documentation improvements, including nightly builds and license notices

### .NET

- 💡 Logging Enhancements:
  - Timestamps for Chromium-based browser logs
  - GeckoDriver log file support
  - Default log level now `WARN`
  - Console output support for all drivers
- 🧠 BiDi enhancements:
  - Exposed internal methods and new result types
  - User context supports `UnhandledPromptBehavior`, `proxy`, `accept_insecure_certs`
  - Tree and Emulation modules added
- 🧹 Cleanup:
  - Removed long-deprecated members
  - Reduced internal tracing noise
- 🔄 Native packaging for Selenium Manager
- 🌐 IPv6 support for port allocation

### JavaScript

- 🧪 BiDi:
  - Stability fix for flaky cookie network test
  - Skip FedCM tests until Chrome 140
- ⚠️ Added `SameSite=default` cookie support
- 🔄 Dependency updates (`typescript`, `@emotion/styled`)

### Ruby

- 🔒 Guarded support for Firefox Beta
- 🚫 Removed deprecated local/session storage APIs
- 🆗 Allowed use of `rubyzip` v3
- ✂️ Excluded Rakefile from line-length linter
- ⚠️ Added support for `SameSite=default`

### Rust (Selenium Manager)

- 🧪 Updated base URL for Edge WebDriver
- ⬆️ Dependency upgrades (`zip`, `rstest`, `which`, Bazel lock files)
- 🔧 Improved architecture normalization for Plausible analytics

### Grid

- 🔁 Performance and logging improvements:
  - Reduced duplicate logs
  - Improved node health checks
  - Better session map handling and retry queue management
- 🧰 Switched from Guava’s CacheBuilder to Caffeine
- 🧪 New UI sorting option by URI


### 🐳 Docker Selenium

- K8s: Add config for over-provision ratio in autoscaling deployment of Nodes ([#2930](https://github.com/SeleniumHQ/docker-selenium/pull/2930))
- Docker: Distributor uses Greedy as the slot selector strategy default in Hub-Node and Standalone mode ([#2915](https://github.com/SeleniumHQ/docker-selenium/pull/2915))
- Docker: Update Google Noto font family to support better language character displays ([#2914](https://github.com/SeleniumHQ/docker-selenium/pull/2914))
- [See all changes](https://github.com/SeleniumHQ/docker-selenium/releases)


<br>

We thank all our contributors for their incredible efforts in making Selenium better with every
release. ❤️

For a detailed look at all changes, check out
the [release notes](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.35.0).

<br>

## Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/Earlopain" >}}
{{< gh-user "https://api.github.com/users/asolntsev" >}}
{{< gh-user "https://api.github.com/users/iampopovich" >}}
{{< gh-user "https://api.github.com/users/jameshilliard" >}}
{{< gh-user "https://api.github.com/users/mk868" >}}
{{< gh-user "https://api.github.com/users/musicinmybrain" >}}
{{< gh-user "https://api.github.com/users/navin772" >}}
{{< gh-user "https://api.github.com/users/noritaka1166" >}}
{{< gh-user "https://api.github.com/users/nxs7" >}}
{{< gh-user "https://api.github.com/users/pallavigitwork" >}}
{{< gh-user "https://api.github.com/users/sandeepsuryaprasad" >}}
    </div>
  </div>
</div>

### [Selenium Docs & Website](https://github.com/SeleniumHQ/seleniumhq.github.io)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/alaahong" >}}
{{< gh-user "https://api.github.com/users/pallavigitwork" >}}
    </div>
  </div>
</div>

### [Docker Selenium](https://github.com/SeleniumHQ/docker-selenium)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/KyriosGN0" >}}
{{< gh-user "https://api.github.com/users/amardeep2006" >}}
{{< gh-user "https://api.github.com/users/anwaramoon" >}}
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
