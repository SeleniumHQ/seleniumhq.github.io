---
title: "Selenium 4.49 Released!"
linkTitle: "Selenium 4.49 Released!"
date: 2026-09-09
tags: [ "selenium" ]
categories: [ "releases" ]
author: Diego Molina [@diemol](https://www.diemol.com)
images:
  - "/images/blog/2026/selenium_4.49.jpg"
description: >
  Today we're happy to announce that Selenium 4.49 has been released!
---

We’re excited to announce the release of **Selenium 4.49** for JavaScript, Ruby, Python, .NET, Java
and the Grid! 🎉

Links to all assets can be found on our [downloads page][downloads].


---

# Selenium 4.49 Released

## ✨ Highlights

- **BiDi** .NET closed out a run of BiDi wire-format bugs (missing DTO fields, mismatched proxy,
  realm, and `NamespaceUri` JSON property names, an oversized command-descriptor allocation) and
  gained browsing context screencast support; Python now raises typed WebDriver errors for BiDi
  wire error codes and runs its BiDi suite against the Grid server; JavaScript, Python, and Ruby
  now derive BiDi field names and enum value types directly from the shared schema.
- **[Grid]** Fixed a 500 error when downloading a file whose name contains spaces.
- **[Java]** Removed the deprecated `GET /session/{sessionId}/se/files/{fileName}` endpoint, and
  jars now ship with LICENSE/NOTICE files and smaller published javadocs.
- **[Rust]** Fixed Selenium Manager's Windows architecture detection under WOW64, and added
  Chrome arm64-on-Linux support.
- **[Python]** Cleaned up typing across the codebase (untyped decorators, unreachable mypy
  warnings) and removed stale `xfail` markers.
- **[Build & Infra]** Continued hardening the release and Selenium Manager build pipeline: native
  Linux builds, a pinned Rust toolchain, prebuilt Linux arm64 binaries in every binding, and a
  daily job to keep the pinned CDDL specs current.

---

## 📦 Notable Changes

### Java
- Removed the deprecated `GET /session/{sessionId}/se/files/{fileName}` endpoint.
- Jars now include LICENSE and NOTICE files, and published javadoc jars are smaller.
- Removed a try/catch around opening a new window, and dropped a "best guess" URI encoding
  fallback.

### Python
- Raised typed WebDriver errors for BiDi wire error codes.
- Ran the BiDi test suite against the Grid server, and fixed flaky BiDi input tests that raced
  the page's autofocus.
- Added BiDi upload tests that verify files actually reach the server.
- Enabled mypy's `disallow_untyped_decorators` and fixed unreachable-code warnings; removed stale
  `xfail` markers.
- Wired `print_pdf_tests.py` into the browser test suites.
- Adopted the worthwhile ruff 0.16 rule families.

### .NET
- Fixed several BiDi DTO issues: missing fields, mismatched proxy and `NamespaceUri` JSON
  property names, and realm-type enum serialization.
- Avoided a massive allocation when building BiDi command descriptors.
- Added browsing context screencast support.
- Only embed the git revision in SourceLink metadata when stamping.

### JavaScript
- Capitalized non-Latin text in the `getVisibleText` atom (shared with Python).
- Now derives BiDi field names and enum value types from the shared schema (shared with Python
  and Ruby).

### Grid
- Fixed a 500 error when downloading a file whose name contains spaces.

### Build & Infra
- Improved pre-release workflow ordering, and began tracking the generated BiDi schema.
- Fixed Selenium Manager's Windows architecture detection under WOW64, and added Chrome
  arm64-on-Linux support.
- Added license and notice links to Selenium Manager's help output.
- Built Selenium Manager's Linux binaries natively with a pinned Rust toolchain, ran Rust tests
  and built Selenium Manager from source on Windows, and shipped a prebuilt Linux arm64 binary in
  every binding.
- Pinned the `rules_rs` zlib bootstrap to an immutable snapshot (with a Launchpad fallback), and
  bumped `rules_rs` to 0.0.108/0.0.109 and `@llvm` to 0.8.19.
- Added a daily workflow to update the pinned CDDL specs, and a compact execution log for CI.
- Populated the Linux Bazel cache from the gh-cache workflow instead of RBE.

---

### 🐳 Docker Selenium

- fix: node-kubernetes liveness probe — tcpSocket instead of /readyz (#3210)
- K8s: Replace external scaler jobScalingStrategy with includeOngoingSessions (#3218)
- [See all changes](https://github.com/SeleniumHQ/docker-selenium/releases)

<br>

We thank all our contributors for their incredible efforts in making Selenium better with every
release. ❤️

For a detailed look at all changes, check out
the [release notes](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.49.0).

<br>

## Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/aayushisabharwal" >}}
{{< gh-user "https://api.github.com/users/ashrafiucse" >}}
{{< gh-user "https://api.github.com/users/dennisameling" >}}
{{< gh-user "https://api.github.com/users/iamricard" >}}
{{< gh-user "https://api.github.com/users/shubmittal" >}}
    </div>
  </div>
</div>

### [Selenium Docs & Website](https://github.com/SeleniumHQ/seleniumhq.github.io)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/ashrafiucse" >}}
{{< gh-user "https://api.github.com/users/cyrilbois" >}}
{{< gh-user "https://api.github.com/users/shossain786" >}}
{{< gh-user "https://api.github.com/users/tag-und-nacht" >}}
{{< gh-user "https://api.github.com/users/tarun3kumar" >}}
    </div>
  </div>
</div>

### [Docker Selenium](https://github.com/SeleniumHQ/docker-selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/rishabhjainrj01" >}}
    </div>
  </div>
</div>

### [Selenium Team Members][team]

**Thanks as well to all the team members who contributed to this release:**

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/AutomatedTester" >}}
{{< gh-user "https://api.github.com/users/diemol" >}}
{{< gh-user "https://api.github.com/users/iampopovich" >}}
{{< gh-user "https://api.github.com/users/joerg1985" >}}
{{< gh-user "https://api.github.com/users/nvborisenko" >}}
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
