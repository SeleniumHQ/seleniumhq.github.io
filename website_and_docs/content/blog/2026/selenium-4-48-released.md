---
title: "Selenium 4.48 Released!"
linkTitle: "Selenium 4.48 Released!"
date: 2026-08-27
tags: [ "selenium" ]
categories: [ "releases" ]
author: Diego Molina [@diemol](https://www.diemol.com)
images:
  - "/images/blog/2026/selenium_4.48.jpg"
description: >
  Today we're happy to announce that Selenium 4.48 has been released!
---

We’re excited to announce the release of **Selenium 4.48** for JavaScript, Ruby, Python, .NET, Java
and the Grid! 🎉

Links to all assets can be found on our [downloads page][downloads].


---

# Selenium 4.48 Released

## ✨ Highlights

- **BiDi** Continued strengthening the shared binding-neutral BiDi layer: Ruby now rejects
  invalid inbound values (missing required fields, out-of-union scalars, non-whole floats for
  integer fields), .NET throws on an unknown discriminator, and JavaScript gained a
  serialization/domain layer plus connection-level event subscription support.
- **[Ruby]** Fixed a silent hang when receiving an oversized WebSocket frame.
- **[Grid]** `se:remoteUrl` is no longer forwarded past the Node that consumes it, and file
  upload/download now works for Kubernetes, Docker, and relay sessions.
- **[.NET]** Added the `SetMediaFeaturesOverride` BiDi command to the Emulation module.
- **[Python]** Fixed W3C capabilities built from a list of options (`create_matches`).
- **[JavaScript]** Custom locator results are now normalized when empty, and BiDi is no longer
  exposed on `Driver`.
- **[Build & Infra]** Added weekly flaky-test reporting, a Slack alert when a CDP update lands on
  trunk, and fixed the release-workflow issues that broke the previous release.

---

## 📦 Notable Changes

### Java
- Fixed two flaky BiDi tests that left timing-dependent state behind.
- Added a warning annotation for undeclared fields encountered during JSON coercion.

### Python
- Fixed W3C capabilities built from a list of options (`create_matches`).
- Fixed a broken reStructuredText link in the docs.
- Began generating the internal BiDi protocol layer from the shared binding-neutral schema,
  updating it to match the latest proposed ADR.

### Ruby
- Added low-level BiDi protocol integration specs.
- Fixed a silent hang on oversized WebSocket frames.
- Fixed intermittent Safari test failures.
- Tightened inbound BiDi validation: reject a missing required field, accept whole-valued floats
  for integer fields, and reject scalars outside a union's declared arms.

### .NET
- Throw in case of an unknown BiDi discriminator.
- Added the `SetMediaFeaturesOverride` command to the Emulation module.

### JavaScript
- Normalized empty custom locator results.
- Ensured BiDi is not exposed on `Driver`.
- Added a BiDi serialization and domain layer, plus connection-level event subscription.

### Grid
- Stopped forwarding `se:remoteUrl` past the Node that consumes it.
- Forwarded file upload/download for Kubernetes, Docker, and relay sessions.

### Build & Infra
- Fixed the issues that broke the release workflow during the last release, and made Java/.NET
  release reruns pass when already published.
- Began recording flaky tests on trunk and reporting weekly offenders to Slack, and alerting
  Slack when a CDP update lands on trunk.
- Reduced GitHub cache churn and aggressively pruned CodeQL caches; fixed the CI build badge on
  the default branch.
- Honored the `DO_NOT_TRACK` environment variable, and moved dependency updates into a weekly
  workflow.
- Published an ADR on installing browser extensions directly from the driver, and linked the
  accepted BiDi support boundary record in the project charter.

---

### 🐳 Docker Selenium

- Docker: Install Edge browser and driver from an archive mirror when Microsoft prunes old
  versions (#3205)
- K8s: Added a configurable `securityContext` (with `seccompProfile`) for the external autoscaler
  (#3199)
- [See all changes](https://github.com/SeleniumHQ/docker-selenium/releases)

<br>

We thank all our contributors for their incredible efforts in making Selenium better with every
release. ❤️

For a detailed look at all changes, check out
the [release notes](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.48.0).

<br>

## Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/munawiki" >}}
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
{{< gh-user "https://api.github.com/users/cgoldberg" >}}
{{< gh-user "https://api.github.com/users/diemol" >}}
{{< gh-user "https://api.github.com/users/navin772" >}}
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
