---
title: "Selenium 4.46 Released!"
linkTitle: "Selenium 4.46 Released!"
date: 2026-07-11
tags: [ "selenium" ]
categories: [ "releases" ]
author: Diego Molina [@diemol](https://www.diemol.com)
images:
  - "/images/blog/2026/selenium_4.46.jpg"
description: >
  Today we're happy to announce that Selenium 4.46 has been released!
---

We’re excited to announce the release of **Selenium 4.46** for JavaScript, Ruby, Python, .NET, Java
and the Grid! 🎉

Links to all assets can be found on our [downloads page][downloads].


---

# Selenium 4.46 Released

## ✨ Highlights

- **Security** Selenium Manager (Rust) now guards against path traversal when extracting tar/pkg
  archives.
- **[Java]** Hardened the JSON parser for RFC 8259 compliance (rejecting unescaped control
  characters, fixing an EOF sentinel collision, correcting comma handling), and marked all current
  BiDi classes as beta ahead of further changes.
- **BiDi** Continued unifying the BiDi protocol layer across bindings around a shared,
  binding-neutral CDDL-derived schema — now adopted by JavaScript and Ruby.
- **[Java]** BiDi creation moved to `RemoteWebDriver`, with BiDi now enabled for Safari options and
  event subscriptions tracked by subscription ID.
- **[Python]** Safari, Safari Technology Preview, and WebView2 now route to their own dedicated
  handlers.
- **[Rust]** Selenium Manager fixes for Firefox binary discovery on Linux and macOS `.pkg`/pbzx
  payload support.
- **[Grid]** Fixed classpath packaging for the Redis-backed `SessionQueue` introduced in 4.45.

---

## 📦 Notable Changes

### Java
- Hardened the JSON parser: fixed an EOF sentinel collision with U+FFFF, rejected unescaped
  control characters, tightened the number lexer to RFC 8259, and corrected comma handling.
- Marked all current BiDi classes as beta, moved BiDi creation to `RemoteWebDriver`, and enabled
  BiDi for Safari options.
- Deprecated methods that surfaced the internal BiDi connection on the driver, and now track event
  subscriptions by subscription ID.
- Fixed the Docker version comparator, and system proxy handling for Selenium Manager arguments.

### Python
- Routed Safari, Safari Technology Preview, and WebView2 to their own handlers.
- Fixed the `WebDriverWait` import example in a docstring.

### Ruby
- Generated the BiDi protocol layer from the shared binding-neutral schema.
- Added `ClientConfig` for HTTP client customization, and trimmed whitespace around `NO_PROXY`
  entries.

### .NET
- Made product info determination thread-safe.
- Split event-stream-backed subscription and enumeration for BiDi, and continued test
  infrastructure cleanup.

### Grid
- Fixed classpath packaging for the Redis-backed `SessionQueue` introduced in 4.45.

### Build & Infra
- Added a design decision record (ADR) process and template for documenting significant changes.
- Added a binding-neutral BiDi schema (JavaScript) and an `update_cddl` script to refresh pinned
  w3c/webref CDDL files.
- Bumped Rust build tooling (`rules_rs`, `@llvm`) and the Node.js toolchain.

---

### 🐳 Docker Selenium

- Docker: Add Redis external datastore support for SessionQueue configuration (#3160)
- Docker: Fix video.sh busy-loop when se:recordVideo=false (#3165)
- Docker: Env var SE_VIDEO_SESSION_SUBFOLDER to standardize recording in dynamic grid (#3156)
- [See all changes](https://github.com/SeleniumHQ/docker-selenium/releases)

<br>

We thank all our contributors for their incredible efforts in making Selenium better with every
release. ❤️

For a detailed look at all changes, check out
the [release notes](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.46.0).

<br>

## Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/bhecquet" >}}
{{< gh-user "https://api.github.com/users/v-dermichev" >}}
{{< gh-user "https://api.github.com/users/vasiliy-mikhailov" >}}
    </div>
  </div>
</div>

### [Selenium Docs & Website](https://github.com/SeleniumHQ/seleniumhq.github.io)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/ajithrao2509" >}}
{{< gh-user "https://api.github.com/users/noritaka1166" >}}
{{< gh-user "https://api.github.com/users/rahuljhakant" >}}
    </div>
  </div>
</div>

### [Docker Selenium](https://github.com/SeleniumHQ/docker-selenium)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/barkep" >}}
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
{{< gh-user "https://api.github.com/users/Delta456" >}}
{{< gh-user "https://api.github.com/users/diemol" >}}
{{< gh-user "https://api.github.com/users/nvborisenko" >}}
{{< gh-user "https://api.github.com/users/pujagani" >}}
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
