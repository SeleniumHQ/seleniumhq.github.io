---
title: "Selenium 4.45 Released!"
linkTitle: "Selenium 4.45 Released!"
date: 2026-06-16
tags: [ "selenium" ]
categories: [ "releases" ]
author: Diego Molina [@diemol](https://www.diemol.com)
images:
  - "/images/blog/2026/selenium_4.45.jpg"
description: >
  Today we're happy to announce that Selenium 4.45 has been released!
---

We’re excited to announce the release of **Selenium 4.45** for JavaScript, Ruby, Python, .NET, Java
and the Grid! 🎉

Links to all assets can be found on our [downloads page][downloads].


---

# Selenium 4.45 Released

## ✨ Highlights

- **[Java]** New support for driving Electron apps via `ElectronOptions`/`ElectronDriver`, plus a
  published `selenium-devtools-latest` artifact.
- **Cross-binding** Continued migration of shared "atoms" (`isDisplayed`, `getAttribute`,
  `find-elements`) from Closure to TypeScript, now adopted across JavaScript, Ruby, Java, and .NET.
- **[Grid]** Hardened the WebSocket proxy (pre-handshake race, TCP backpressure, fast-path framing)
  and added Redis-backed `SessionQueue` support alongside the already-bundled Redis `SessionMap`.
- **[Python]** New high-level BiDi network APIs for request/response/auth handling and extra
  headers, plus BiDi script module alignment with the cross-binding API design.
- **[Ruby]** Deprecated the `curb` HTTP client and `Chromium Profile` classes, and split
  responsibilities between `Service`, `DriverFinder`, and `Options`.
- **[Rust]** Selenium Manager now prunes cache entries older than 30 days and switched its TLS
  backend to `ring`.
- **[Build]** Broad CI/release modernization: Bazel bumped to 9.1 and a rerun-safe release
  pipeline.

---

## 📦 Notable Changes

### Java
- Added support for driving Electron apps with `ElectronOptions` and `ElectronDriver`.
- Published a `selenium-devtools-latest` artifact and removed deprecated logging classes.
- Corrected deprecation annotations for JSON Wire error code APIs, and skip browser-restricted
  ports when picking a free port.
- Adopted the shared TypeScript atoms for `isShown`/`getAttribute`.

### Python
- Added high-level BiDi network APIs: request, response, and authentication handlers, plus extra
  headers support.
- Aligned the BiDi script module with the cross-binding API design, and close the BiDi websocket
  on `quit()`.
- Extracted actions, alert, and color into dedicated libraries/subpackages.
- Marked Safari tests broken by SafariDriver 26.5 as expected failures.

### Ruby
- Deprecated `curb` HTTP client support and the `Chromium Profile` classes.
- Separated concerns between `Service`, `DriverFinder`, and `Options`.
- Adopted the shared TypeScript atoms, added Safari tests, and upgraded to Steep 2.0.

### .NET
- Added support for using any external BiDi transport, plus a fake BiDi transport for tests.
- Expanded JSON data available on BiDi commands, events, and results, and added a download ID to
  download events.
- Made user-facing BiDi collections immutable, and improved exception messages for malformed
  capability URLs.

### Grid
- Closed a pre-handshake race and applied TCP backpressure and fast-path framing in the WebSocket
  proxy.
- Bundled a Redis-backed `SessionMap` by default and added Redis-backed `SessionQueue` support.
- Added debug logging for file downloads.

### Build & Infra
- Bumped Bazel to 9.1 and made the release pipeline rerun-safe.
- Set up trusted publishing from GitHub to npm, and generated release notes from the previous
  minor release tag.
- Continued disk-space and Bazel cache monitoring improvements across CI jobs.

---

### 🐳 Docker Selenium

- Docker: Add Redis external datastore support for Distributor configuration (#3137)
- Docker: Video recording starts via session capability se:recordVideo (#3131)
- K8s: Migrate subchart Redis to cloudpirates/redis from bitnami/redis (#3148)
- [See all changes](https://github.com/SeleniumHQ/docker-selenium/releases)

<br>

We thank all our contributors for their incredible efforts in making Selenium better with every
release. ❤️

For a detailed look at all changes, check out
the [release notes](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.45.0).

<br>

## Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

### [Selenium Docs & Website](https://github.com/SeleniumHQ/seleniumhq.github.io)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/noritaka1166" >}}
    </div>
  </div>
</div>

### [Docker Selenium](https://github.com/SeleniumHQ/docker-selenium)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/nktt-s" >}}
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
{{< gh-user "https://api.github.com/users/bonigarcia" >}}
{{< gh-user "https://api.github.com/users/Delta456" >}}
{{< gh-user "https://api.github.com/users/diemol" >}}
{{< gh-user "https://api.github.com/users/harsha509" >}}
{{< gh-user "https://api.github.com/users/navin772" >}}
{{< gh-user "https://api.github.com/users/nvborisenko" >}}
{{< gh-user "https://api.github.com/users/pujagani" >}}
{{< gh-user "https://api.github.com/users/rpallavisharma" >}}
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
