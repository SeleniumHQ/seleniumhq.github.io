---
title: "Selenium 4.52 Released!"
linkTitle: "Selenium 4.52 Released!"
date: 2026-10-29
tags: [ "selenium" ]
categories: [ "releases" ]
author: Diego Molina [@diemol](https://www.diemol.com)
images:
  - "/images/blog/2026/selenium_4.52.jpg"
description: >
  Today we're happy to announce that Selenium 4.52 has been released!
---

We’re excited to announce the release of **Selenium 4.52** for JavaScript, Ruby, Python, .NET, Java
and the Grid! 🎉

Links to all assets can be found on our [downloads page][downloads].


---

# Selenium 4.52 Released

## ✨ Highlights

- **Selenium Manager** now validates TLS certificates and explains certificate errors. An opt-in
  insecure mode is available for environments that need it.
- **Trust roots** were updated across bindings after the eMudhra CA distrust: Python requires a
  newer `certifi`, Selenium Manager bumps its trust roots, and JavaScript requires Node releases
  that block the compromised keys.
- **[Ruby]** The client now raises on unverified Grid certificates instead of silently falling
  back to `VERIFY_NONE`.
- **[Python]** **[Ruby]** BiDi WebSocket responses are no longer polled with sleeps, so commands
  return faster. In Python, `websocket_interval` is now deprecated.
- **[Python]** **[JavaScript]** First steps toward shipping Selenium Manager as a separate
  package.
- **[JavaScript]** The atoms that Appium uses were ported from Closure to TypeScript.
- **[Build & Infra]** Moved to Bazel 9.3.0 and updated all Bazel dependencies.

---

## 📦 Notable Changes

### Java
- `selenium.debug` and `webdriver.verbose` now configure the real logger.
- BiDi events are no longer delivered to a listener after it is cleared.
- Stopped waiting for process output once the process is silent.
- Third-party jars are under `lib/` in the distribution zips again, and annotation-only jars are no
  longer shipped as POM dependencies.
- Added tests for the BiDi generator and to check the shipped bytecode supports Java 11.

### Python
- Web extensions can be installed from the driver.
- Cookie domain and path matching now follow RFC 6265.
- `Service.start()` is bounded by time, so it fails fast on Windows.
- Fixed `ErrorHandler` failing when the error value is missing, and `Proxy` now uses the
  `ssl_proxy` setting when created from a raw dict.
- Generated BiDi numbers are held to their declared ranges, and null list elements are rejected.

### Ruby
- HTTPS connections to the Grid now raise on unverified certificates instead of ignoring them.
- Stopped passing a bare `--proxy` to Selenium Manager.
- BiDi values are checked against their declared types and number ranges.
- Atoms now work outside Bazel, and `local_dev` makes it easy to use a local checkout.

### .NET
- Added a unit test suite and moved cookie, proxy, and other tests to it.
- Made driver configurations configurable through environment variables and NUnit parameters.

### JavaScript
- Ported the atoms Appium uses to TypeScript.
- Fixed locator edge cases in the `find_elements` atom and ignored hidden links in link text
  locators.
- Required Node releases that block the compromised eMudhra keys.

### Grid
- A node config with a null stereotype now throws a `ConfigException`.

### Selenium Manager
- Validated TLS certificates in the HTTP client, with an opt-in insecure mode.
- Created the HTTP client on first use and explained TLS certificate errors.
- Returned the cached artifact once the lock is held instead of downloading it again.
- Stopped resolving a Java runtime on `--grid`.
- Updated dependencies, replaced the `fs2` crate with the standard file lock, and moved from
  `sevenz_rust` to `sevenz_rust2`.

### Build & Infra
- Bumped to Bazel 9.3.0, enabled CDC, and updated all Bazel dependencies.
- Ran unit tests in parallel while keeping browser tests serial.
- Patched `rules_jvm` to fix a 5 second hostname lookup deadlock in JUnit 5 tests.
- Scoped RBE internet access to the tests that use Selenium Manager.
- Open bot PRs are now updated in place, and branches of PRs closed without merging are deleted.

### 🐳 Docker Selenium

- [build] Update base image to Ubuntu 26.04 (resolute) (#3272)
- [build] Update Selenium Grid 4.51.0 (#3267)
- [build] Update Selenium Grid 4.50.0 (#3257)
- [ci] Build FFmpeg natively per architecture, then merge (#3273)
- [test] Drop backward-compat Edge versions whose driver Microsoft has pruned (#3263)
- Update Helm release traefik to v42 (#3270)
- Update Helm release kube-prometheus-stack to v92 (#3258)
- [See all changes](https://github.com/SeleniumHQ/docker-selenium/releases)

<br>

We thank all our contributors for their incredible efforts in making Selenium better with every
release. ❤️

For a detailed look at all changes, check out
the [release notes](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.52.0).

<br>

## Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/dmitgush" >}}
{{< gh-user "https://api.github.com/users/gjreddy" >}}
{{< gh-user "https://api.github.com/users/goldpanth3r" >}}
{{< gh-user "https://api.github.com/users/Mochxd" >}}
{{< gh-user "https://api.github.com/users/MohabMohie" >}}
{{< gh-user "https://api.github.com/users/sagarithm" >}}
{{< gh-user "https://api.github.com/users/saiyam-jain-dev" >}}
{{< gh-user "https://api.github.com/users/yashp676" >}}
    </div>
  </div>
</div>

### [Selenium Docs & Website](https://github.com/SeleniumHQ/seleniumhq.github.io)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/beinghumantester" >}}
{{< gh-user "https://api.github.com/users/brentspine" >}}
{{< gh-user "https://api.github.com/users/githarshil" >}}
{{< gh-user "https://api.github.com/users/StuMinch" >}}
    </div>
  </div>
</div>

### [Selenium Team Members][team]

**Thanks as well to all the team members who contributed to this release:**

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/aguspe" >}}
{{< gh-user "https://api.github.com/users/alaahong" >}}
{{< gh-user "https://api.github.com/users/AutomatedTester" >}}
{{< gh-user "https://api.github.com/users/bonigarcia" >}}
{{< gh-user "https://api.github.com/users/cgoldberg" >}}
{{< gh-user "https://api.github.com/users/diemol" >}}
{{< gh-user "https://api.github.com/users/iampopovich" >}}
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
