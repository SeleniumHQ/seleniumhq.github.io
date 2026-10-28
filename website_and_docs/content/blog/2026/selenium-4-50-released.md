---
title: "Selenium 4.50 Released!"
linkTitle: "Selenium 4.50 Released!"
date: 2026-09-30
tags: [ "selenium" ]
categories: [ "releases" ]
author: Diego Molina [@diemol](https://www.diemol.com)
images:
  - "/images/blog/2026/selenium_4.50.jpg"
description: >
  Today we're happy to announce that Selenium 4.50 has been released!
---

We’re excited to announce the release of **Selenium 4.50** for JavaScript, Ruby, Python, .NET, Java
and the Grid! 🎉

Links to all assets can be found on our [downloads page][downloads].


---

# Selenium 4.50 Released

## ✨ Highlights

- **Relative locators** in Java and .NET can now be anchored to elements and shadow roots, not
  just the driver.
- **Firefox** deprecated profile extension and certificate methods were removed from every
  binding, and Java, Python, and .NET now declare Firefox default preferences in the binding
  itself.
- **BiDi** .NET added screenshot image size, a screencast destination folder,
  `Emulation.SetTextLayoutModeOverride`, and activity source monitoring; Ruby can now install and
  uninstall web extensions through BiDi; Java parses each BiDi command response only once.
- **[Java]** Removed deprecated methods from the `HttpClient` interface, and a `RemoteWebDriver`
  now uses a single `HttpClient.Factory`.
- **[Python]** `ClientConfig` `user_agent` and `extra_headers` are now applied per connection.
- **[Build & Infra]** Releases are now built on RBE, Selenium Manager can be cross-compiled for all
  platforms from Linux or Mac, and the BiDi schema is generated from more upstream sources,
  including Mozilla's Firefox CDDL.

---

## 📦 Notable Changes

### Java
- Supported relative locators from elements and shadow roots (shared with .NET).
- Removed deprecated methods from the `HttpClient` interface; the `HttpCommandExecutor` `client`
  and `httpClientFactory` fields were restored as deprecated to ease the transition.
- Used one `HttpClient.Factory` per `RemoteWebDriver` instance.
- Resolved exceptions from the W3C error state in `ErrorHandler`, and kept guarded runnables alive
  when a task throws an `Error`.
- Parsed each BiDi command response once, and cleared `fetchError` and realm listeners when
  closing `Network` and `Script`.

### Python
- Applied `ClientConfig` `user_agent` and `extra_headers` per connection.
- Typed `BaseOptions.set_capability` and `WebElement.get_attribute` parameters.
- Cleaned up exception chaining for ruff rule B904.
- Moved the full GitHub Actions test suite to nightly runs, keeping fast smoke tests on PRs, and
  ran Grid specific tests on Windows during scheduled runs.

### .NET
- Supported relative locators from elements and shadow roots (shared with Java).
- Added BiDi screenshot image size, a `DestinationFolder` option for `StartScreencast`,
  `Emulation.SetTextLayoutModeOverride`, activity source monitoring, and `hasPlannedNavigation`
  in `ContextCreatedEventArgs`.
- Removed BiDi context from the hot path, preserved additional data in context commands, and
  aligned the `CapabilityResponse` type name with the spec.
- NuGet packages now include license and notice files.

### Ruby
- Added driver methods to install and uninstall web extensions via BiDi.
- Rendered WebSocket debug log lines lazily.
- Covered BiDi key, wheel, release, and `setFiles` scenarios in the Input spec.

### JavaScript
- Aligned the BiDi generator with the low-level BiDi contract.
- Ran the test suite against the Grid.

### All bindings
- Removed the deprecated Firefox profile extension and certificate methods.

### Build & Infra
- Built release artifacts on RBE, and automatically merged the release PR once required checks
  pass.
- Added a Bazel flag to cross-compile all Selenium Manager binaries from Linux or Mac, and moved
  Selenium Manager publishing into the snapshot workflow so releases no longer skip it.
- Generated the BiDi schema from the spec's external specifications list, Mozilla's Firefox CDDL,
  and its Commands fragment.
- Added ADRs for a network async/event API and a behavioral contract for the low-level BiDi layer.
- Selenium Manager now shows a proper error message when the metadata file can't be opened.

---

### 🐳 Docker Selenium

- [build] Update Selenium Grid 4.49.0 (#3240)
- [ci] Take the arm64 ChromeDriver from Chrome for Testing (#3238)
- Update Helm release redis to ^0.35.0 (#3239)
- [See all changes](https://github.com/SeleniumHQ/docker-selenium/releases)

<br>

We thank all our contributors for their incredible efforts in making Selenium better with every
release. ❤️

For a detailed look at all changes, check out
the [release notes](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.50.0).

<br>

## Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/adamtheturtle" >}}
{{< gh-user "https://api.github.com/users/ikraamg" >}}
{{< gh-user "https://api.github.com/users/Mochxd" >}}
{{< gh-user "https://api.github.com/users/rnestler" >}}
{{< gh-user "https://api.github.com/users/yashp676" >}}
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
{{< gh-user "https://api.github.com/users/navin772" >}}
{{< gh-user "https://api.github.com/users/nvborisenko" >}}
{{< gh-user "https://api.github.com/users/pujagani" >}}
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
