---
title: "Selenium 4.41 Released!"
linkTitle: "Selenium 4.41 Released!"
date: 2026-02-20
tags: [ "selenium" ]
categories: [ "releases" ]
author: Diego Molina [@diemol](https://www.diemol.com)
images:
  - "/images/blog/2026/selenium_4.41.jpg"
description: >
  Today we're happy to announce that Selenium 4.41 has been released!
---

We’re excited to announce the release of **Selenium 4.41** for JavaScript, Ruby, Python, .NET, Java
and the Grid! 🎉

Links to all assets can be found on our [downloads page][downloads].


---

# Selenium 4.41 Released

## ✨ Highlights

- **[DotNet]** Major BiDi expansion in the Emulation module (`SetTouchOverride`, `SetNetworkConditions`, expanded `SetViewport`), a new `Speculation` module, and async command dispatch throughout — including breaking changes that make Selenium Manager and the driver service start/stop asynchronously.
- **[Java]** Continued JSpecify nullability coverage across all BiDi packages, plus a JSON parsing fix for numbers with an exponent.
- **[Grid]** Multiple Dynamic Grid fixes: a Distributor deadlock, WebSocket connection counter leaks, thread exhaustion in health checks, and new Kubernetes cluster and basic-auth support.
- **[Python]** New BiDi `set_screen_settings_override` emulation command, Edge BiDi tests enabled, and a large Bazel/lint modernization pass.
- **[Ruby]** Debug logging on by default, generated atoms no longer stored in the repo, and dependency/lint updates.
- **[Build]** A sweeping overhaul of the release and CI pipeline, and the retirement of the legacy Rake/CrazyFun build system.

---

## 📦 Notable Changes

### Java
- Expanded JSpecify nullability annotations across the `bidi.browser`, `bidi.browsingcontext`, `bidi.emulation`, `bidi.module`, `bidi.log`, `bidi.network`, and `bidi.script` packages.
- Implemented BiDi `emulation.setScreenSettingsOverride`.
- Added JSpecify annotations for `LoadableComponent` and `SlowLoadableComponent`.
- Fixed JSON parsing of numbers with an exponent.
- Improved the error message when the Grid dies, and fixed a secure/non-secure test failure.

### Python
- Added BiDi `set_screen_settings_override` emulation command and enabled Edge BiDi tests.
- Modularized the Bazel build with per-module targets and integrated mypy type checking.
- Switched to lazy imports in `webdriver/__init__.py` and split `ruff` into dedicated format/check targets.
- Removed type stub packages from runtime dependencies and bumped dev dependencies to fix a vulnerability.

### Ruby
- Driver logs now output by default when debug is enabled.
- Removed stored atoms in favor of generating them at build time.
- Updated lint configuration, fixed rubocop offenses, and added missing unit tests.

### .NET
- Added `SetTouchOverride`, `SetNetworkConditions`, and expanded `SetViewport`/`AddPreloadScript`/`MovePointer` in the BiDi Emulation and Input modules.
- Added a new BiDi `Speculation` module and made commands/events fully immutable.
- Made Selenium Manager and the driver service start/stop asynchronous (breaking changes), and added `CancellationToken` support for async commands and event registration.
- Fixed several logging issues: truncated internal log messages, streamed Selenium Manager output, and corrected devtools inline docs generation.

### Grid
- Fixed a potential deadlock in the Distributor and WebSocket connection counter leaks in `ProxyNodeWebsockets`.
- Added a session event API for server-side event bus integration.
- Added Kubernetes cluster support and basic-auth credential passing for Dynamic Grid standalone.
- Fixed thread exhaustion in the node health-check cycle and session retries when a RemoteNode executor is shutting down.

### Build & Infra
- Continued the Bazel migration: per-language patch releases, consolidated nightly and pre-release workflows, and file-level test target indexing.
- Removed the legacy Rake/CrazyFun build system entirely.
- Added linting support for .NET and unified `SE_DEBUG` warning behavior across Python, Java, and JavaScript.

---

### 🐳 Docker Selenium

- Docker: Video recorder/uploader now listen on session events (#3070)
- Docker: Fixed spurious warning messages when basic auth isn't configured (#3071)
- K8s: Updated KEDA to 2.19.0 in the Helm chart (#3068)
- [See all changes](https://github.com/SeleniumHQ/docker-selenium/releases)

<br>

We thank all our contributors for their incredible efforts in making Selenium better with every
release. ❤️

For a detailed look at all changes, check out
the [release notes](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.41.0).

<br>

## Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/mk868" >}}
    </div>
  </div>
</div>

### [Selenium Docs & Website](https://github.com/SeleniumHQ/seleniumhq.github.io)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/Cookiecodess" >}}
{{< gh-user "https://api.github.com/users/RReval102" >}}
    </div>
  </div>
</div>

### [Docker Selenium](https://github.com/SeleniumHQ/docker-selenium)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/msvticket" >}}
    </div>
  </div>
</div>

### [Selenium Team Members][team]

**Thanks as well to all the team members who contributed to this release:**

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/alaahong" >}}
{{< gh-user "https://api.github.com/users/asolntsev" >}}
{{< gh-user "https://api.github.com/users/AutomatedTester" >}}
{{< gh-user "https://api.github.com/users/cgoldberg" >}}
{{< gh-user "https://api.github.com/users/Delta456" >}}
{{< gh-user "https://api.github.com/users/diemol" >}}
{{< gh-user "https://api.github.com/users/harsha509" >}}
{{< gh-user "https://api.github.com/users/joerg1985" >}}
{{< gh-user "https://api.github.com/users/navin772" >}}
{{< gh-user "https://api.github.com/users/nvborisenko" >}}
{{< gh-user "https://api.github.com/users/RenderMichael" >}}
{{< gh-user "https://api.github.com/users/rpallavisharma" >}}
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
