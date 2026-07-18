---
title: "Selenium 4.44 Released!"
linkTitle: "Selenium 4.44 Released!"
date: 2026-05-12
tags: [ "selenium" ]
categories: [ "releases" ]
author: Diego Molina [@diemol](https://www.diemol.com)
images:
  - "/images/blog/2026/selenium_4.44.jpg"
description: >
  Today we're happy to announce that Selenium 4.44 has been released!
---

We’re excited to announce the release of **Selenium 4.44** for JavaScript, Ruby, Python, .NET, Java
and the Grid! 🎉

Links to all assets can be found on our [downloads page][downloads].


---

# Selenium 4.44 Released

## ✨ Highlights

- **[Build]** Introduced a new Selenium CLI tool, plus an AI-assisted contribution policy and Rust
  commands to install `skills.md`/`rules.md` files for agent-based contributors.
- **[.NET]** A large batch of BiDi alignment work (statically declared commands/events, additional
  event streaming) alongside several breaking changes: obsolete member removal and
  `UnhandledPromptBehavior` as a string or map.
- **[Java]** Continued JSpecify nullability work in the `remote` package, deprecated native
  `HttpClient` methods, and fixed a `NoSuchElementException` regression for custom `By` locators.
- **[Grid]** Added a Redis-backed Distributor as a built-in option and fixed latent WebSocket proxy
  bugs.
- **[Python]** Now generates BiDi files instead of hand-curating them, and added a CDDL 2 Python
  generator.
- **[JavaScript]** Converted the `getAttribute` and `isDisplayed` atoms from Closure to TypeScript.

---

## 📦 Notable Changes

### Java
- Continued JSpecify nullability annotations in the `remote` package.
- Deprecated native methods on the `HttpClient` interface.
- Fixed a regression causing `NoSuchElementException` for custom `By` locators, and an NPE when a
  response status is null.
- Removed the unused `ChromiumDriver.capabilities` field, and bumped `byte-buddy`.

### Python
- Switched to generated BiDi files instead of hand-curated ones, and added a CDDL 2 Python
  generator.
- Implemented high-level APIs for script execution and improved generated BiDi module docstrings.
- Added an Edge service argument to inherit browser I/O streams.
- Updated docs with a pytest example and general formatting fixes.

### Ruby
- Fixed a credential issue with private keys.
- Fixed a conflict between Firefox's `-v` and `--log` flags.
- Standardized `README` files across bindings.

### .NET
- Statically declared BiDi commands and events, and added additional event streaming (breaking
  change).
- Removed members planned for deprecation in 4.44, and made `UnhandledPromptBehavior` accept a
  string or map (breaking change).
- Migrated tests to MTP and strongly signed assemblies.
- Fixed network monitoring stop behavior and internal log truncation at error/warn levels.

### Grid
- Added a Redis-backed Distributor as built-in support.
- Accept legacy session-closed event payloads.
- Fixed latent bugs in the WebSocket proxy.

### Build & Infra
- Introduced the Selenium CLI tool and an AI-assisted contribution policy, including Rust commands
  to install `skills.md`/`rules.md` files.
- Hardened the release pipeline: trusted publishing for Python, GitHub release drafts before
  publish, and parallelized documentation releases.
- Cleaned up GHA runner disk space and fixed several release/prerelease workflow issues.

---

### 🐳 Docker Selenium

- Docker: Retain recordings for failed sessions only from session capabilities (#3111)
- chart(selenium-grid): add ServiceMonitor and PodMonitor support for Prometheus Operator (#3121)
- Docker: Cleanup CfT and Chromium profiles (#3123)
- [See all changes](https://github.com/SeleniumHQ/docker-selenium/releases)

<br>

We thank all our contributors for their incredible efforts in making Selenium better with every
release. ❤️

For a detailed look at all changes, check out
the [release notes](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.44.0).

<br>

## Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/Chandan25sharma" >}}
{{< gh-user "https://api.github.com/users/devanngg" >}}
{{< gh-user "https://api.github.com/users/dzbarsky" >}}
{{< gh-user "https://api.github.com/users/pierluigilenoci" >}}
    </div>
  </div>
</div>

### [Selenium Docs & Website](https://github.com/SeleniumHQ/seleniumhq.github.io)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/bansidhark" >}}
{{< gh-user "https://api.github.com/users/itsveence" >}}
{{< gh-user "https://api.github.com/users/LetyPG" >}}
{{< gh-user "https://api.github.com/users/srinu2003" >}}
    </div>
  </div>
</div>

### [Docker Selenium](https://github.com/SeleniumHQ/docker-selenium)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/DrFaust92" >}}
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
{{< gh-user "https://api.github.com/users/RenderMichael" >}}
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
