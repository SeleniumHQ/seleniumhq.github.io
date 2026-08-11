---
title: "Selenium 4.47 Released!"
linkTitle: "Selenium 4.47 Released!"
date: 2026-08-10
tags: [ "selenium" ]
categories: [ "releases" ]
author: Diego Molina [@diemol](https://www.diemol.com)
images:
  - "/images/blog/2026/selenium_4.47.jpg"
description: >
  Today we're happy to announce that Selenium 4.47 has been released!
---

We’re excited to announce the release of **Selenium 4.47** for JavaScript, Ruby, Python, .NET, Java
and the Grid! 🎉

Links to all assets can be found on our [downloads page][downloads].


---

# Selenium 4.47 Released

## ✨ Highlights

- **BiDi** Continued unifying the BiDi protocol layer across bindings around the shared,
  binding-neutral CDDL-derived schema — Ruby now generates its BiDi domain type accessors, union
  variant factories, and typed errors from it, matching JavaScript's earlier adoption.
- **[Java]** Removed the redundant BiDi subscription scope tracking and fixed BiDi not
  initializing for `RemoteWebDriver` built via the builder.
- **[.NET]** BiDi transport factories are now composable, the optional command timeout was
  removed from command options, and spurious warnings when there are no BiDi subscribers are
  gone.
- **[.NET][Python][Ruby]** Firefox now blocks CDP access, reinforcing BiDi as the standard
  cross-browser protocol.
- **[Grid]** Dynamic Grid videos are now consistently stored in a per-session subfolder via
  `SE_VIDEO_SESSION_SUBFOLDER`, including on Kubernetes, removing the previous pod-wait and
  relocation logic.
- **[Java]** Fixed `By.className()`/`By.id()` misescaping non-ASCII leading digits, and a
  `By.name()` double `String.format` bug on names containing `%`.
- **[Rust]** Selenium Manager fixes: honors `--browser-version` for Electron driver resolution,
  locates Chrome and Edge in known install directories, and no longer caches an empty driver
  version.

---

## 📦 Notable Changes

### Java
- Removed the redundant BiDi subscription scope tracking, and fixed BiDi not initializing for
  `RemoteWebDriver` built via the builder.
- Fixed `By.className()`/`By.id()` misescaping non-ASCII leading digits, and a `By.name()` double
  `String.format` bug on names containing `%`.
- Added regression coverage for relative locators on collapsed table borders.
- Removed deprecated FTP proxy support and deprecated invalid Firefox profile code (shared with
  JavaScript, Ruby, and .NET).

### Python
- Blocked CDP access with Firefox (shared with .NET and Ruby).
- Fixed `no_proxy` matching so empty entries and substrings no longer bypass the proxy.
- `find_element`/`find_elements` type hints now accept `By`.
- Ensured driver service subprocess resources are cleaned up.
- Fixed the default `command_executor` URL in the `RemoteWebDriver` docstring.

### Ruby
- Generated the BiDi domain type accessors, union variant factories, and typed WebDriver errors
  from the shared binding-neutral schema.
- Constructed the BiDi transport inside the domain from a connection, and routed BiDiBridge
  navigation through the generated `Protocol::BrowsingContext`.
- Added inbound/outbound validation for BiDi fields, including nullable-constant params, ref
  fields, and float/enum type fidelity.
- Added support for WebDriver BiDi on Safari Preview, moving `#bidi` onto `Driver`.
- Added a `browser_family` test guard, and support for custom vendor-specific capabilities in
  options classes.

### .NET
- Made BiDi transport factories composable.
- Removed the optional command timeout from command options, and stopped warning when there are
  no BiDi subscribers.
- Added support for `SE_*DRIVER` environment variables to set driver locations.
- Blocked CDP access with Firefox (shared with Python and Ruby).

### Grid
- Store Dynamic Grid videos in a per-session subfolder via `SE_VIDEO_SESSION_SUBFOLDER`,
  including on Kubernetes, removing the previous pod-wait and relocation logic.
- Inherited the Node Pod container's `securityContext` for Dynamic Grid on Kubernetes.
- Honored client-advertised `se:remoteUrl` for reachable BiDi/CDP/VNC URLs.

### Build & Infra
- Continued the BiDi schema unification: merged vendor CDDL files into the shared schema, derived
  per-type inbound/outbound directionality, and cleaned up schema generation.
- Published a Selenium 5 release charter and an ADR documenting BiDi implementation boundaries.
- Generated a cross-platform Selenium Manager SBOM and NOTICE, bundled into each package.
- Bumped Rust build tooling (`@llvm`, `rules_rs`, `rules_ruby`) and updated Node.js versioning for
  testing and publishing.

---

### 🐳 Docker Selenium

- Docker: Support Google Chrome images for linux/arm64 (#3187)
- [build] Keep Chrome dev/beta images on linux/amd64 only, resolve the latest rclone tag (#3188)
- [video] Fall back to SE_NODE_CONTAINER_NAME for the per-session subfolder (#3194)
- K8s: Implement KEDA external scaler and use it as default (#3169)
- K8s: Gate distributed component replicas on external datastore (#3175)
- [See all changes](https://github.com/SeleniumHQ/docker-selenium/releases)

<br>

We thank all our contributors for their incredible efforts in making Selenium better with every
release. ❤️

For a detailed look at all changes, check out
the [release notes](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.47.0).

<br>

## Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/MathiasPaulenko" >}}
{{< gh-user "https://api.github.com/users/MohabMohie" >}}
{{< gh-user "https://api.github.com/users/dennisameling" >}}
{{< gh-user "https://api.github.com/users/gaurav0107" >}}
{{< gh-user "https://api.github.com/users/vtvipul" >}}
    </div>
  </div>
</div>

### [Selenium Docs & Website](https://github.com/SeleniumHQ/seleniumhq.github.io)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/SuperDXCEL" >}}
{{< gh-user "https://api.github.com/users/ajithrao2509" >}}
{{< gh-user "https://api.github.com/users/bahka" >}}
{{< gh-user "https://api.github.com/users/beinghumantester" >}}
{{< gh-user "https://api.github.com/users/dcki" >}}
{{< gh-user "https://api.github.com/users/halex2005" >}}
{{< gh-user "https://api.github.com/users/noritaka1166" >}}
{{< gh-user "https://api.github.com/users/pmartinez1" >}}
{{< gh-user "https://api.github.com/users/sadik312" >}}
    </div>
  </div>
</div>

### [Selenium Team Members][team]

**Thanks as well to all the team members who contributed to this release:**

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/alaahong" >}}
{{< gh-user "https://api.github.com/users/AutomatedTester" >}}
{{< gh-user "https://api.github.com/users/bonigarcia" >}}
{{< gh-user "https://api.github.com/users/cgoldberg" >}}
{{< gh-user "https://api.github.com/users/diemol" >}}
{{< gh-user "https://api.github.com/users/navin772" >}}
{{< gh-user "https://api.github.com/users/nvborisenko" >}}
{{< gh-user "https://api.github.com/users/pujagani" >}}
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
