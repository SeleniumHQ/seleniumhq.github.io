---
title: "Selenium 4.40 Released!"
linkTitle: "Selenium 4.40 Released!"
date: 2026-01-18
tags: [ "selenium" ]
categories: [ "releases" ]
author: Diego Molina [@diemol](https://www.diemol.com)
images:
  - "/images/blog/2026/selenium_4.40.jpg"
description: >
  Today we're happy to announce that Selenium 4.40 has been released!
---

We’re excited to announce the release of **Selenium 4.40** for JavaScript, Ruby, Python, .NET, Java
and the Grid! 🎉

Links to all assets can be found on our [downloads page][downloads].


---

# Selenium 4.40 Released

## ✨ Highlights

- **Chrome DevTools support** is now: v144, v143, and v142.
- **[Java]** Expanded BiDi support: `setScreenOrientationOverride`, `setNetworkConditions`, and command return type changes.
- **[Python]** Introduced `LocalWebDriver` base class, improved docstring formatting and typing, removed deprecated code.
- **[Ruby]** Better test synchronization, clearer BiDi API boundaries, and internal improvements.
- **[DotNet]** BiDi improvements in Input, Emulation, and buffer pooling logic.
- **[Grid]** Improved dynamic Grid container handling, better concurrent session handling, and race condition fixes.
- **[Build]** Bazel-based documentation and release automation improvements across all bindings.

---

## 📦 Notable Changes

### Java
- Added support for new BiDi emulation and network commands.
- Deprecated unused logging and Guava collections.
- Improved test infrastructure and flaky test fixes.
- Improved Grid file upload, JSON parsing, and error handling.
- Automatically save screenshots and page sources on failure.

### Python
- Added `LocalWebDriver` base class.
- Removed deprecated `FirefoxBinary` and `FTP proxy` support.
- Improved linting and typing with `ruff` and `mypy`.
- Better grid test integration and SE_DEBUG logging.

### Ruby
- Improved error handling and synchronization for socket interactions.
- Marked BiDi internals as private.
- Fixed various flaky test issues.

### .NET
- Support for `SetScreenSettingsOverrideAsync`, `FileDialogOpened`, and input module exposure.
- Improved memory usage and buffer reuse in BiDi communication.
- Removed obsolete or unstable APIs.

### Grid
- Smarter container naming and label handling in Dynamic Grid.
- Race condition and concurrent session handling improvements.

### Build & Infra
- All bindings now support rerun on test failures.
- Improved CI speed, clarity, and isolation.
- Build/test infra modernized with Bazel rules and doc generation.

---

### 🐳 Docker Selenium

- Docker: update video_graphQLQuery shell script to python (#3002)
- Update ubuntu:noble Docker digest to cd1dba6 (#3063)
- Update list new environment variables (#3062)
- [See all changes](https://github.com/SeleniumHQ/docker-selenium/releases)

<br>

We thank all our contributors for their incredible efforts in making Selenium better with every
release. ❤️

For a detailed look at all changes, check out
the [release notes](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.40.0).

<br>

## Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/mzyndul" >}}                                                                                                                                                     
    </div>
  </div>
</div>

### [Selenium Docs & Website](https://github.com/SeleniumHQ/seleniumhq.github.io)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/alaahong" >}}
    </div>
  </div>
</div>

### [Docker Selenium](https://github.com/SeleniumHQ/docker-selenium)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/KyriosGN0" >}}
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
