---
title: "Selenium 4.42 Released!"
linkTitle: "Selenium 4.42 Released!"
date: 2026-04-09
tags: [ "selenium" ]
categories: [ "releases" ]
author: Diego Molina [@diemol](https://www.diemol.com)
images:
  - "/images/blog/2026/selenium_4.42.jpg"
description: >
  Today we're happy to announce that Selenium 4.42 has been released!
---

We’re excited to announce the release of **Selenium 4.42** for JavaScript, Ruby, Python, .NET, Java
and the Grid! 🎉

Links to all assets can be found on our [downloads page][downloads].


---

# Selenium 4.42 Released

## ✨ Highlights

- **[.NET]** A large BiDi consistency pass: unified event arguments, exposed BiDi functionality via interfaces, thread-safe event registration and processing, and commands/events that allocate close to nothing.
- **[Java]** Implemented the BiDi `speculation` module, continued JSpecify nullability coverage (chromium, browsers, grid, devtools, docker packages), and fixed several `Keys`/`ScriptKey` correctness issues.
- **[Grid]** Hardened the WebSocket router: a transparent TCP tunnel bypass path, handling for dropped close frames and idle disconnects, and a new `NodeCommandInterceptor` for pluggable command interception via `--ext`.
- **[Python]** Expanded BiDi test coverage and added type stubs for lazily imported classes.
- **[JavaScript]** Added a `Color` class to the JavaScript library.
- **[Build]** Bumped to Bazel 9.

---

## 📦 Notable Changes

### Java
- Implemented the BiDi `speculation` module.
- Continued JSpecify nullability annotations across the `chrom*`, browsers, `grid.*`, `devtools`, and `docker` packages.
- Deduplicated Unicode PUA mappings in `Keys`, made `OPTION` an alias of `ALT`, and deprecated `FN`.
- Fixed a regression that unnecessarily serialized binary streams in `RemoteWebDriver.builder()`, and guarded against an NPE in `Platform.extractFromSysProperty`.
- Enhanced `ScriptKey.toString()` and masked script content in `UnpinnedScriptKey`.

### Python
- Expanded BiDi test coverage.
- Added type stubs for lazily imported classes and modules.
- Added a module for importing the latest DevTools version, and stopped closing externally provided `log_output` streams.

### Ruby
- Switched to portable Ruby.
- Fixed a linter error in the `./go authors` script.

### .NET
- Unified all BiDi event arguments to a consistent `*EventArgs` type, and exposed BiDi functionality via interfaces.
- Made event registration and command/event processing thread-safe, with close to zero allocation per command/event.
- Properly handled the WebSocket close handshake, and made any `WebDriver` disposable asynchronously.
- Bumped the C# language version to 14.0.

### Grid
- Added a transparent TCP tunnel bypass path for the WebSocket data path in the Router.
- Handled dropped close frames, idle disconnects, and high-latency proxying in the Router WebSocket.
- Added `NodeCommandInterceptor` for pluggable command interception via `--ext`.
- Fixed VNC capabilities not being propagated for sessions without a `browserName`.

### Build & Infra
- Upgraded to Bazel 9.
- Fixed several CI reliability issues (RBE tests, lint/format CI, skipped failing Firefox Beta tests).

---

### 🐳 Docker Selenium

- Docker: Mirror images to GitHub Container Registry (#3098)
- Docker: Enable SE_VIDEO_EVENT_DRIVEN by default (#3084)
- Unified configs for Dynamic Grid Docker and Kubernetes (#3088)
- [See all changes](https://github.com/SeleniumHQ/docker-selenium/releases)

<br>

We thank all our contributors for their incredible efforts in making Selenium better with every
release. ❤️

For a detailed look at all changes, check out
the [release notes](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.42.0).

<br>

## Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/jit3pam" >}}
{{< gh-user "https://api.github.com/users/mayank-at-sauce" >}}
{{< gh-user "https://api.github.com/users/seethinajayadileep" >}}
    </div>
  </div>
</div>

### [Selenium Docs & Website](https://github.com/SeleniumHQ/seleniumhq.github.io)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/rushixdd" >}}
    </div>
  </div>
</div>

### [Selenium Team Members][team]

**Thanks as well to all the team members who contributed to this release:**

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/asolntsev" >}}
{{< gh-user "https://api.github.com/users/AutomatedTester" >}}
{{< gh-user "https://api.github.com/users/cgoldberg" >}}
{{< gh-user "https://api.github.com/users/diemol" >}}
{{< gh-user "https://api.github.com/users/harsha509" >}}
{{< gh-user "https://api.github.com/users/navin772" >}}
{{< gh-user "https://api.github.com/users/nvborisenko" >}}
{{< gh-user "https://api.github.com/users/p0deje" >}}
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
