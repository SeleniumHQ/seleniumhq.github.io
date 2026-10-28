---
title: "Selenium 4.51 Released!"
linkTitle: "Selenium 4.51 Released!"
date: 2026-10-09
tags: [ "selenium" ]
categories: [ "releases" ]
author: Diego Molina [@diemol](https://www.diemol.com)
images:
  - "/images/blog/2026/selenium_4.51.jpg"
description: >
  Today we're happy to announce that Selenium 4.51 has been released!
---

We’re excited to announce the release of **Selenium 4.51** for JavaScript, Ruby, Python, .NET, Java
and the Grid! 🎉

Links to all assets can be found on our [downloads page][downloads].


---

# Selenium 4.51 Released

## ✨ Highlights

- **[Java]** Guava is no longer a dependency of the client bindings, so `ExpectedCondition` no
  longer implements Guava's `Function` interface. See [how to migrate][guava] if your code relies
  on it.
- **[JavaScript]** The browser atoms, now written in TypeScript, are published as a standalone
  `@selenium/atoms` npm package, and the obsolete WebSQL and AppCache atoms were removed.
- **[Python]** Added support for Python 3.15.
- **BiDi** code generation keeps growing: Java gained a BiDi code generator, JavaScript can generate
  vendor specific classes, and the Firefox vendor CDDL is now pinned from the Firefox repository.
- **[JavaScript]** The driver can install and uninstall web extensions through BiDi, falling back
  to the classic commands when BiDi isn't available.
- **[Java]** Added `RemoteWebDriver.getClientFactory()`, and the removal of the deprecated
  `HttpCommandExecutor` fields was postponed to 4.53 to give users more time to migrate.
- **[Build & Infra]** Java artifacts are now signed in-process, and the release workflow can
  recover from an incomplete Java release.

---

## 📦 Notable Changes

### Java
- Removed Guava from the client bindings; `ExpectedCondition` no longer implements Guava's
  `Function` interface ([migration guide][guava]).
- Added `RemoteWebDriver.getClientFactory()` and moved the `HttpCommandExecutor` field removal to
  4.53.
- Stopped leaking the `HttpClient` when the driver fails to start.
- Stopped passing a bare `--proxy` argument to Selenium Manager.
- Added a BiDi code generator, decoded nested `RemoteValue`s from the parsed tree instead of
  re-serializing each subtree, and typed field-less synthetic map records as `Map<String, V>`.

### Python
- Added support for Python 3.15.
- Fixed the validation of the WebSocket response interval.
- Sent the BiDi `setClientWindowState` rect members as sibling parameters.

### .NET
- Escaped the Edge browser path on macOS.
- Upgraded the test suite to NUnit 5, moved the NUnit infrastructure to its own project, and ran
  the internal tests in parallel.

### Ruby
- Typed field-less synthetic map records as validated maps in the BiDi generator.

### JavaScript
- Published the TypeScript atoms as the `@selenium/atoms` npm package as part of the npm release.
- Removed the WebSQL and AppCache atoms.
- Added driver methods to install and uninstall web extensions with BiDi, with a classic fallback.
- Added vendor specific class generation for BiDi.

### Selenium Manager
- Degraded to empty metadata when the cache can't be read, instead of failing.
- Configured the Chrome sandbox after unpacking a Chrome for Testing build on Windows.

### Build & Infra
- Signed Java artifacts in-process with in-memory PGP keys instead of `gpg-agent`.
- Recovered from an incomplete Java release by dropping staged repositories, and generated release
  docs at approval time, committing them once the packages are published.
- Pinned the Firefox BiDi vendor CDDL from the Firefox repository.
- Sourced the Java and .NET versions from a root `version.bzl`, added a stub mode for using
  Selenium Manager with Bazel, and pinned the driver location once per test process.
- Removed obsolete third party dependencies, Bazel patches with upstream fixes, and IntelliJ
  project files.

<br>

We thank all our contributors for their incredible efforts in making Selenium better with every
release. ❤️

For a detailed look at all changes, check out
the [release notes](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.51.0).

<br>

## Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/cuishuang" >}}
{{< gh-user "https://api.github.com/users/Mochxd" >}}
{{< gh-user "https://api.github.com/users/yashp676" >}}
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
{{< gh-user "https://api.github.com/users/joerg1985" >}}
{{< gh-user "https://api.github.com/users/nvborisenko" >}}
{{< gh-user "https://api.github.com/users/pujagani" >}}
{{< gh-user "https://api.github.com/users/rpallavisharma" >}}
{{< gh-user "https://api.github.com/users/titusfortner" >}}
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

[guava]: /blog/2026/expectedcondition-drops-guava/

[BiDi]: https://github.com/w3c/webdriver-bidi
