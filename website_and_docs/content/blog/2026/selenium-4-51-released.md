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

- **Chrome DevTools** support is now v153, v154, and v155 (v152 was removed).
- **[Java]** A new BiDi code generator builds the protocol classes from the spec, and the
  generated classes are exported from `selenium-remote-driver`.
- **[Java]** Guava is no longer a dependency of the client bindings, and `RemoteWebDriver` gained
  `getClientFactory()`.
- **[JavaScript]** The driver can now install and uninstall web extensions through BiDi, with a
  fallback when BiDi is not available, and the atoms are published as the `@seleniumhq/atoms` npm
  package.
- **[Python]** Python 3.15 is now supported.
- **Selenium Manager** configures the Chrome sandbox after unpacking a Chrome for Testing build on
  Windows, and 32-bit Windows binaries are back.
- **[Build & Infra]** Java artifacts are signed in-process, the Firefox BiDi vendor CDDL is pinned
  from the Firefox repository, and a failed Java release can now recover on its own.

---

## 📦 Notable Changes

### Java
- Added a BiDi code generator and exported the generated protocol classes from
  `selenium-remote-driver`.
- Removed Guava from the client bindings.
- Added `RemoteWebDriver.getClientFactory()`, and postponed removing the `HttpCommandExecutor`
  fields to 4.53.
- Stopped leaking the `HttpClient` when the driver fails to start, and no longer passed a bare
  `--proxy` to Selenium Manager.
- Decoded nested BiDi `RemoteValue`s from the parsed tree instead of re-serializing each subtree,
  and typed field-less map records as `Map<String, V>`.

### Python
- Supported Python 3.15.
- Sent the `setClientWindowState` rectangle values as sibling parameters, as the BiDi spec expects.
- Validated the WebSocket response interval correctly.
- Suffixed generated CDP names that collide with Python keywords.

### .NET
- Escaped the macOS Edge browser path.
- Moved the internal tests to NUnit 5, ran them in parallel, and gave fixtures an entry point to
  change driver options.

### Ruby
- Typed field-less BiDi map records as validated maps.
- Updated test guards for Safari, Windows Firefox, and Chrome beta.

### JavaScript
- Added methods to install and uninstall web extensions on the driver, using BiDi with a fallback.
- Created the `@seleniumhq/atoms` npm package from the TypeScript atoms and published it with
  each npm release.
- Removed the WebSQL and AppCache atoms.
- Generated vendor specific BiDi classes.

### Selenium Manager
- Configured the Chrome sandbox after unpacking a Chrome for Testing build on Windows.
- Fell back to empty metadata when the cache cannot be read.
- Restored the 32-bit Windows binary.

### Build & Infra
- Signed Java artifacts in-process with in-memory PGP keys instead of `gpg-agent`.
- Pinned the Firefox BiDi vendor CDDL from the Firefox repository.
- Recovered from an incomplete Java release by dropping staged repositories, and generated release
  docs at approval.
- Added a stub mode for using Selenium Manager with Bazel, and removed obsolete third party
  dependencies and Bazel patches.

---

### 🐳 Docker Selenium

- [See all changes](https://github.com/SeleniumHQ/docker-selenium/releases)

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

[BiDi]: https://github.com/w3c/webdriver-bidi
