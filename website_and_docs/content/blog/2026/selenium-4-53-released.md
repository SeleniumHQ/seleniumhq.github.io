---
title: "Selenium 4.53 Released!"
linkTitle: "Selenium 4.53 Released!"
date: 2026-11-17
tags: [ "selenium" ]
categories: [ "releases" ]
author: Diego Molina [@diemol](https://www.diemol.com)
images:
  - "/images/blog/2026/selenium_4.53.jpg"
description: >
  Today we're happy to announce that Selenium 4.53 has been released!
---

We’re excited to announce the release of **Selenium 4.53** for JavaScript, Ruby, Python, .NET, Java
and the Grid! 🎉

Links to all assets can be found on our [downloads page][downloads].


---

# Selenium 4.53 Released

## ✨ Highlights

- **Accessibility snapshots**: Python (`driver.accessibility_snapshot()` and
  `element.accessibility_snapshot()`) and JavaScript (`getAccessibilitySnapshot()`) can now
  return the accessibility tree of a page or element, in both Classic and BiDi sessions. Java,
  Ruby, and .NET will follow in a later release.
- **Selenium MCP server**: an MCP server for coding agents was started in this release and now
  lives in its own repository, [SeleniumHQ/mcp](https://github.com/SeleniumHQ/mcp).
- **[Grid]** New sessions are spread across Nodes again. Since 4.36.0, the Distributor filled one
  Node before using the next one.
- **[Java]** JSON serialisation and parsing are much faster, close to Gson and Jackson. The
  deprecated `Response.status` was removed.
- **[Python]** Relative locators now work from a `WebElement` and from a `ShadowRoot`.
- **[Ruby]** `Selenium::Server` downloads the Grid jar with Selenium Manager, so it is no longer
  limited by the GitHub API rate limit and supports older Grid versions.
- **[JavaScript]** The `@selenium/manager` npm packages are now published with every release.

---

## 📦 Notable Changes

### Java
- Quotes and backslashes in name locators are escaped before they are sent to the driver.
- Frame ids that are not valid CSS identifiers are escaped.
- Remote client diagnostics are logged at a fixed `FINE` level.
- Generated BiDi numbers are held to their declared ranges, vendor-declared BiDi fields are typed
  instead of dropped, and `listExtensions` is now generated.
- `Response.status` was removed. JSON Wire payloads with a numeric `status` still decode as before.

### Python
- Relative locators work from a `WebElement` and a `ShadowRoot`, and the search stays inside it.
- `driver.webextension` is deprecated. Use `driver.install_web_extension()` and
  `driver.uninstall_web_extension()` instead.
- `driver.script` now returns a protocol-neutral object instead of the generated BiDi module.
- `Select` escapes trailing double quotes correctly and finds options by value with XPath.
- Added type annotations for cookies.

### Ruby
- `Selenium::Server` downloads the Grid jar with Selenium Manager.
- Environment variables can be set when starting a browser.
- WebSocket command ids are allocated atomically.

### JavaScript
- Added `getAccessibilitySnapshot()`, which also works in Classic sessions.
- BiDi numbers are held to their declared ranges.
- `@selenium/manager` is published with every release.

### Grid
- New sessions are spread across Nodes again.

### Selenium Manager
- Fixed the Firefox and Electron download URLs on Windows arm64.
- `--output json` keeps stderr empty.
- Usage stats are limited to vetted values before they are sent.
- Updated dependencies.

### Build & Infra
- The Redis-backed Grid suites run in the Java smoke matrix instead of a separate workflow.
- The Java smoke test job uses the EngFlow cache.
- Added a weekly CI check for Plausible pageview usage.

### 🐳 Docker Selenium

Released as
[4.53.0-20261120](https://github.com/SeleniumHQ/docker-selenium/releases/tag/4.53.0-20261120),
with Chrome 158.0.8127.36, Edge 158.0.4370.33, and Firefox 160.0.1 on Ubuntu 26.04.1 LTS. The Helm
chart is [0.64.0](https://github.com/SeleniumHQ/docker-selenium/releases/tag/selenium-grid-0.64.0).

- [build] Update Selenium Grid 4.53.0 (#3311)
- [chart] Keep live node replicas on upgrade so Helm 4 does not conflict with KEDA (#3310)
- [chart] Set Traefik service type under service.spec, which chart 42.3.1+ requires (#3300)
- [See all changes](https://github.com/SeleniumHQ/docker-selenium/releases)

<br>

We thank all our contributors for their incredible efforts in making Selenium better with every
release. ❤️

For a detailed look at all changes, check out
the [release notes](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.53.0).

<br>

## Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/adamtheturtle" >}}
{{< gh-user "https://api.github.com/users/dennisameling" >}}
{{< gh-user "https://api.github.com/users/gjreddy" >}}
{{< gh-user "https://api.github.com/users/macoladik" >}}
{{< gh-user "https://api.github.com/users/Mochxd" >}}
{{< gh-user "https://api.github.com/users/MohabMohie" >}}
{{< gh-user "https://api.github.com/users/Mohsintl" >}}
{{< gh-user "https://api.github.com/users/saiyam-jain-dev" >}}
{{< gh-user "https://api.github.com/users/y-yagi" >}}
{{< gh-user "https://api.github.com/users/yashp676" >}}
    </div>
  </div>
</div>

### [Selenium Docs & Website](https://github.com/SeleniumHQ/seleniumhq.github.io)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/tarun3kumar" >}}
    </div>
  </div>
</div>

### [Selenium Team Members][team]

**Thanks as well to all the team members who contributed to this release:**

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/AutomatedTester" >}}
{{< gh-user "https://api.github.com/users/bonigarcia" >}}
{{< gh-user "https://api.github.com/users/diemol" >}}
{{< gh-user "https://api.github.com/users/nvborisenko" >}}
{{< gh-user "https://api.github.com/users/pujagani" >}}
{{< gh-user "https://api.github.com/users/rpallavisharma" >}}
{{< gh-user "https://api.github.com/users/shs96c" >}}
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
