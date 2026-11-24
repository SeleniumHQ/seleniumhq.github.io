---
title: "Selenium 4.54 Released!"
linkTitle: "Selenium 4.54 Released!"
date: 2026-11-23
tags: [ "selenium" ]
categories: [ "releases" ]
author: Diego Molina [@diemol](https://www.diemol.com)
images:
  - "/images/blog/2026/selenium_4.54.jpg"
description: >
  Today we're happy to announce that Selenium 4.54 has been released!
---

We’re excited to announce the release of **Selenium 4.54** for JavaScript, Ruby, Python, .NET, Java
and the Grid! 🎉

Links to all assets can be found on our [downloads page][downloads].


---

# Selenium 4.54 Released

## ✨ Highlights

- **Faster, cache-backed CI**: every GitHub Actions job, on all bindings and Rust, now uses the
  EngFlow remote cache, including Windows and Linux ARM jobs.
- **Firefox BiDi port**: .NET, Python, and JavaScript let Firefox choose its own BiDi WebSocket
  port, which avoids port clashes when running browsers in parallel.
- **Safari diagnostics**: Java, Python, Ruby, and .NET pass `--diagnose` to `safaridriver` when
  `SE_DEBUG` is set.
- **[Grid]** A Node no longer reports a failed status when a session stops while its status is
  being read.
- **[Ruby]** Driver startup is more robust: the driver service is stopped when a session fails to
  start, startup fails fast when the driver process exits, and driver output goes to the logger
  again at debug level.
- **[.NET]** The BiDi `Subscribe` command accepts `userContexts`, and frame ids that are not valid
  CSS identifiers are escaped.

---

## 📦 Notable Changes

### Java
- Safari driver quits like geckodriver, without waiting an extra 10 seconds.
- Remaining diagnostics are logged at a fixed `FINE` level.
- The deprecated `Debug.getDebugLogLevel()` was removed.

### Python
- Stopping geckodriver skips the shutdown request.
- Firefox picks its own BiDi WebSocket port.

### Ruby
- The driver service is stopped when starting a session fails with any error.
- Startup fails fast when the driver process exits, and stops the driver when startup fails.
- Driver output is sent to the logger again when it is at debug level.
- Local BiDi event listeners were added to the low-level modules.
- `Remote::Bridge` and `Remote::BiDiBridge` are marked as `@api private`.

### .NET
- The BiDi `Subscribe` command accepts the missing `userContexts` parameter.
- Frame ids that are not valid CSS identifiers are escaped.
- Firefox picks its own BiDi WebSocket port.

### JavaScript
- Generated BiDi modules keep received node attributes and type vendor-declared fields, including
  on extensible session capabilities.
- Generated BiDi modules are tagged as internal.
- Firefox picks its own BiDi WebSocket port.

### Grid
- Node status no longer fails when a session stops mid-read.

### Selenium Manager
- `se-metadata.json` is written atomically.

### Build & Infra
- All bindings and Rust use the EngFlow cache on GitHub Actions, including Windows and Linux ARM.
- Selenium Manager browser versions match the pinned browser versions in tests.
- Test Grids start with only the browser under test, and JavaScript tests honor `--headless`.

### 🐳 Docker Selenium

Released as
[4.54.0-20261123](https://github.com/SeleniumHQ/docker-selenium/releases/tag/4.54.0-20261123),
with Chrome 158.0.8127.36, Edge 158.0.4370.33, and Firefox 160.0.1 on Ubuntu 26.04.1 LTS. The Helm
chart is [0.65.0](https://github.com/SeleniumHQ/docker-selenium/releases/tag/selenium-grid-0.65.0).

- [build] Update Selenium Grid 4.54.0 (#3322)
- [build] Support running with a read-only root filesystem (#3316)
- [build] Add log level to script logs and use one timestamp format (#3317)
- [chart] Allow setting schedulerName for node pods (#3320)
- [chart] Honor kube-prometheus-stack.enabled: false (#3321)
- [docs] Document high availability setup in the Helm chart (#3315)
- [See all changes](https://github.com/SeleniumHQ/docker-selenium/releases)

<br>

We thank all our contributors for their incredible efforts in making Selenium better with every
release. ❤️

For a detailed look at all changes, check out
the [release notes](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.54.0).

<br>

## Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/b84330808" >}}
{{< gh-user "https://api.github.com/users/Mochxd" >}}
{{< gh-user "https://api.github.com/users/MohabMohie" >}}
{{< gh-user "https://api.github.com/users/UweKubosch" >}}
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
{{< gh-user "https://api.github.com/users/nvborisenko" >}}
{{< gh-user "https://api.github.com/users/pujagani" >}}
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
