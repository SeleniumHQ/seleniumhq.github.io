---
title: "Selenium 4.39 Released!"
linkTitle: "Selenium 4.39 Released!"
date: 2025-12-06
tags: [ "selenium" ]
categories: [ "releases" ]
author: Diego Molina [@diemol](https://www.diemol.com)
images:
  - "/images/blog/2025/selenium_4.39.jpg"
description: >
  Today we're happy to announce that Selenium 4.39 has been released!
---

We’re excited to announce the release of **Selenium 4.39** for JavaScript, Ruby, Python, .NET, Java
and the Grid! 🎉

Links to all assets can be found on our [downloads page][downloads].


---

# Selenium 4.39 Released

Selenium 4.39 is a feature-rich release with numerous improvements across the board. It brings 
enhancements to BiDi across Java, Python, and .NET, more emulation commands, major updates to 
Dynamic Grid behavior, and continuous work on JSpecify annotations. It also includes docstring 
cleanups, test infra upgrades, and build system optimizations.

---

## Highlights

- **Chrome DevTools support** is now: v143, v142, and v141.
- **BiDi Enhancements Across Languages**: New emulation and download behavior commands for Java, Python, and .NET.
- **Dynamic Grid Improvements**: Better container handling, new labels, and minimum Docker API version enforcement.
- **Docstring and Type Hinting**: Continued work on Python docstrings using Google style and improving type hints.
- **JSpecify Coverage in Java**: Many new annotations for type-safety and better tooling support.
- **Build & CI Enhancements**: Optimized workflows, Bazel module updates, and CI improvements across languages.

---

## Language-specific Changes

### Java

- **BiDi**:
  - Support for:
    - `emulation.setTimezoneOverride`
    - `emulation.setScriptingEnabled`
    - `emulation.setUserAgentOverride`
    - `browser.setDownloadBehavior`
    - `browsingContext.setViewport(null, null)`
  - Refactored request interception tests with CORS handling.
- **JSpecify annotations**:
  - `CommandCodec`, `CommandInfo`, `CommandPayload`, `Color`, `ChromiumDriver`, and more.
- **Other**:
  - Prevent logging debug logs at `INFO` level.
  - Fixes for `PrintOptions.setPageRanges`, ClassCastException, and Chrome tests on Windows.
  - Improvements to exception handling and collection simplification.

---

### Python

- **BiDi**:
  - Added:
    - `set_locale_override`
    - `set_scripting_enabled`
    - `set_screen_orientation_override`
    - `set_download_behavior`
    - `set_user_agent_override`
    - Viewport reset command
  - Enabled download event tests for Firefox.
- **Documentation & Type Hints**:
  - Full docstring conversion to Google format.
  - Removed `noqa` markers, improved type annotations with union syntax.
- **Dev/Tooling**:
  - Bumped `pytest`, `ruff`, `mypy`, and `urllib3`.
  - WebSocket improvements (daemon threads).
  - Test reuse and performance improvements.

---

### .NET

- **BiDi**:
  - Improved event subscription and emulation module support.
  - Better JSON (de)serialization contexts per module.
  - Viewport reset, record equality, hydration, and type unification improvements.
  - Added Permissions module.
- **General**:
  - Migrated solution to `.slnx`.
  - Removed obsolete dependencies (`Newtonsoft.Json`, `FtpProxy`).
  - Syntax highlighting improvements for JS strings.
  - Updated build tools (`paket`, `rules_dotnet`).

---

### Ruby

- Fixed flaky `devtools_spec.rb`.
- Minor BiDi updates.

---

### Rust (Selenium Manager)

- Bazel migration of `rules_rust`.

---

## Grid

- Minimum Docker API 1.44 support.
- Improved:
  - Session ID handling
  - Container labels
  - Compose stack grouping
  - Docker client logic
- Tracking `SessionRemovalInfo` and improved session map cleanup.

---

## Tooling & CI

- Cancel redundant CI runs.
- Mirror file workflow fixes and pagination.
- Bazel build improvements.

---

### 🐳 Docker Selenium

- K8s: Add transitionary support for FB_BASEURL to FB_BASE_URL (#3053)
- Docker: Add package fonts-thai-tlwg-ttf for Thai language (#3052)
- K8s: Template handle scaledJobOptions with value is zero (#3054)
* - [See all changes](https://github.com/SeleniumHQ/docker-selenium/releases)

<br>

We thank all our contributors for their incredible efforts in making Selenium better with every
release. ❤️

For a detailed look at all changes, check out
the [release notes](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.39.0).

<br>

## Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/giulong" >}}                                                                                                                                                     
{{< gh-user "https://api.github.com/users/iampopovich" >}}                                                                                                                                                 
{{< gh-user "https://api.github.com/users/manuelsblanco" >}}                                                                                                                                               
{{< gh-user "https://api.github.com/users/mk868" >}}                                                                                                                                                       
{{< gh-user "https://api.github.com/users/spencerarq" >}}                                                                                                                                                     
    </div>
  </div>
</div>

### [Selenium Docs & Website](https://github.com/SeleniumHQ/seleniumhq.github.io)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/DNotNice" >}}                                                                                                                                                        
{{< gh-user "https://api.github.com/users/JacekSzwed" >}}                                                                                                                                                      
{{< gh-user "https://api.github.com/users/alaahong" >}}                                                                                                                                                        
{{< gh-user "https://api.github.com/users/janetmoreno" >}}                                                                                                                                                     
{{< gh-user "https://api.github.com/users/kathyrollo" >}}                                                                                                                                                      
{{< gh-user "https://api.github.com/users/rpallavisharma" >}}                                                                                                                                                  
{{< gh-user "https://api.github.com/users/rushixdd" >}}
    </div>
  </div>
</div>

### [Docker Selenium](https://github.com/SeleniumHQ/docker-selenium)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/visit1985" >}}
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
