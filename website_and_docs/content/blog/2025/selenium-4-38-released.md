---
title: "Selenium 4.38 Released!"
linkTitle: "Selenium 4.38 Released!"
date: 2025-10-24
tags: [ "selenium" ]
categories: [ "releases" ]
author: Diego Molina [@diemol](https://www.diemol.com)
images:
  - "/images/blog/2025/selenium_4.38.jpg"
description: >
  Today we're happy to announce that Selenium 4.38 has been released!
---

We’re excited to announce the release of **Selenium 4.38** for JavaScript, Ruby, Python, .NET, Java
and the Grid! 🎉

Links to all assets can be found on our [downloads page][downloads].


---

# Selenium 4.38 Released

Selenium 4.38 is a maintenance-focused release with improvements in code quality, documentation 
formatting, and test infrastructure. The update includes ongoing work on BiDi features, cleanup of 
deprecated code, and expansion of JSpecify annotations for better type safety in Java.

---

## Highlights

- **Chrome DevTools support** is now: v142, v141, and v140.
- **Java JSpecify Coverage Expanded**: Dozens of additional classes annotated to improve null-safety and developer experience.
- **Python Docstring and BiDi Work**: Switched to Google-style docstrings and added support for `set_timezone_override` in BiDi emulation.
- **Ruby Cleanup**: Deprecated logging classes removed and RSpec linting applied.
- **Infrastructure & Docs**: Test workflows cleaned up, doc update jobs fixed, and Travis cruft removed from builds.

---

## Language-specific Changes

### Java

- JSpecify annotations added for:
  - `ExecuteMethod`
  - `org.openqa.selenium.federatedcredential`
  - `org.openqa.selenium.interactions`
  - `org.openqa.selenium.net`
  - `Credential`, `MBean`
  - `ScriptKey`, `UnpinnedScriptKey`
  - `FileDetector`
  - `ExpectedCondition`
  - `Response`, `SessionId`, `HttpSessionId`
- BiDi:
  - New test added for the `onHistoryUpdated` event.

---

### Python

- BiDi:
  - Added `set_timezone_override` command under emulation.
- Documentation:
  - Converted more docstrings to Google style.
  - Removed use of `:param:` and `:args:` Sphinx directives.
- Tooling:
  - Bumped `ruff` formatter/linter version.

---

### .NET

- BiDi:
  - Updated DTO property inclusion to avoid reliance on `JsonInclude`.

---

### Ruby

- BiDi test infra:
  - Reduced number of BiDi-related test targets for better performance.
  - Disabled `dev/shm` for Chrome/Edge tests on RBE.
- Code cleanup:
  - Removed deprecated logging classes.
  - Applied `rspec` linter syntax updates.
  - Fixed `nil` handling in network requests.
- Build improvements:
  - Improved local dev environment generation.

---

### Rust (Selenium Manager)

- Fixed browser version test for Edge.

---

## Tooling & Infrastructure

- Removed unused `test_tag_filter`.
- Removed legacy Travis CI environment code (Java, Ruby).
- Fixed documentation update workflows for both staging and main releases.

---

### 🐳 Docker Selenium

- [ci] Use test public site when test run via relay session to cloud
- K8s: Remove triggerMetadata with empty value to prevent Unmatched input property from KEDA checks (#3005)
- K8s: Expose config session request timeout in Helm chart values (#3006)
- chore(deps): update helm release kube-prometheus-stack to v79 (#3007)
- [See all changes](https://github.com/SeleniumHQ/docker-selenium/releases)

<br>

We thank all our contributors for their incredible efforts in making Selenium better with every
release. ❤️

For a detailed look at all changes, check out
the [release notes](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.38.0).

<br>

## Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/Earlopain" >}}
{{< gh-user "https://api.github.com/users/iampopovich" >}}                                                                                                                                                 
{{< gh-user "https://api.github.com/users/mk868" >}}                                                                                                                                                       
    </div>
  </div>
</div>

### [Selenium Docs & Website](https://github.com/SeleniumHQ/seleniumhq.github.io)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/harsha509" >}}
    </div>
  </div>
</div>

### [Docker Selenium](https://github.com/SeleniumHQ/docker-selenium)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/VietND96" >}}
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
