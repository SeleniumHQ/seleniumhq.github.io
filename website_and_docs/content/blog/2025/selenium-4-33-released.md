---
title: "Selenium 4.33 Released!"
linkTitle: "Selenium 4.33 Released!"
date: 2025-05-25
tags: [ "selenium" ]
categories: [ "releases" ]
author: Diego Molina [@diemol](https://www.diemol.com)
images:
  - "/images/blog/2025/selenium_4.33.jpg"
description: >
  Today we're happy to announce that Selenium 4.33 has been released!
---

We’re excited to announce the release of **Selenium 4.33** for Javascript, Ruby, Python, .NET, Java
and the Grid! 🎉

This release contains improvements, cleanups, and new features across all language bindings and the
Grid. This release continues our effort to modernize the codebase, improve developer experience, and
refine the project’s tooling and documentation.

Links to all assets can be found on our [downloads page][downloads].


---

## 🚀 Highlights

- [9f3c923670](https://github.com/SeleniumHQ/selenium/commit/9f3c92367005f19fad2bc79c171e7250cce43da3) - Grid UI now includes live previews for each Node.
- [43e6bb970e](https://github.com/SeleniumHQ/selenium/commit/43e6bb970e65ec62692d6bf49962ea81e1103e78) - Python BiDi support expands with the new webExtension module.
- [ef05c15798](https://github.com/SeleniumHQ/selenium/commit/ef05c15798b22a3ade4bb1f111d3e1955988e267) - Java: Reverted deprecation notice for `getAttribute` after community feedback.
- [638621f4bc](https://github.com/SeleniumHQ/selenium/commit/638621f4bc3c632c5955fb4d056fd2f01b6cf835) - Java: Clean-up of deprecated timeout configuration methods.

## 🔍 Changes by Component

### Grid

- [9f3c923670](https://github.com/SeleniumHQ/selenium/commit/9f3c92367005f19fad2bc79c171e7250cce43da3) - UI Overview is able to see live preview per Node
- [7401a3db93](https://github.com/SeleniumHQ/selenium/commit/7401a3db93a7b6cca6f4697c5d032196b2e7f661) - UI Sessions capability fields to display as additional columns

### Python

- [92db47fa2a](https://github.com/SeleniumHQ/selenium/commit/92db47fa2ad6b4f8baa70446b7c18e6c17966306) - Add missing modules to python API docs
- [4fc2582bf9](https://github.com/SeleniumHQ/selenium/commit/4fc2582bf96ecc2d0d0f4552c0c200a1d4e1e303) - Better error for downloads on local webdrivers
- [43e6bb970e](https://github.com/SeleniumHQ/selenium/commit/43e6bb970e65ec62692d6bf49962ea81e1103e78) - Add bidi webExtension module (#15749)

### Rust

- [7497552255](https://github.com/SeleniumHQ/selenium/commit/7497552255a2bef5a1d9883d7620de2e41c6b553) - Replace WMIC commands (deprecated) by WinAPI in Windows

### Java

- [ef05c15798](https://github.com/SeleniumHQ/selenium/commit/ef05c15798b22a3ade4bb1f111d3e1955988e267) - Reverting deprecation notice for `getAttribute`.
- [638621f4bc](https://github.com/SeleniumHQ/selenium/commit/638621f4bc3c632c5955fb4d056fd2f01b6cf835) - Removing deprecated `setScriptTimeout` and `pageLoadTimeout`.
- [fcf4c9d09e](https://github.com/SeleniumHQ/selenium/commit/fcf4c9d09ecd41223d185a0d9922f14f37f9d4f6) - Removing deprecated SlowLoadableComponent constructor.
- [1e65b7b49f](https://github.com/SeleniumHQ/selenium/commit/1e65b7b49f4c22e842b3620d9c5841961dfccc5e) - Removing deprecated NATIVE_EVENTS field.
- [f3f0cadedb](https://github.com/SeleniumHQ/selenium/commit/f3f0cadedbaef98cc224dc7c84f4d8720d115565) - Deprecating methods that use FirefoxBinary as well.

### Ruby

- [212fc8be35](https://github.com/SeleniumHQ/selenium/commit/212fc8be3566e333ee3823e153b770162c3902b8) - Upgrade to Ruby 3.2.
- [1e2945de78](https://github.com/SeleniumHQ/selenium/commit/1e2945de78c8005d96bad66af43a02b46bde3d20) - Let firefox choose the bidi port by default.

### .NET

- [212fc8be35](https://github.com/SeleniumHQ/selenium/commit/212fc8be3566e333ee3823e153b770162c3902b8) - Upgrade to Ruby 3.2.
- [1e2945de78](https://github.com/SeleniumHQ/selenium/commit/1e2945de78c8005d96bad66af43a02b46bde3d20) - Let firefox choose the bidi port by default.

### JavaScript

- [3ef1c25fe8](https://github.com/SeleniumHQ/selenium/commit/3ef1c25fe8eef39b195550f7b5bf76d38f4f42ca) - Chrome capabilities test passes now in RBE.


### Docker Selenium

- K8s: Fix Helm chart template for deployment of video recording manager ([#2828](https://github.com/SeleniumHQ/docker-selenium/pull/2828), [#2831](https://github.com/SeleniumHQ/docker-selenium/pull/2831)).
- K8s: Node enable readiness probe checks status registered to Hub ([#2833](https://github.com/SeleniumHQ/docker-selenium/pull/2833)).
- K8s: Video recorder run as sidecar container is disabled by default ([#2843](https://github.com/SeleniumHQ/docker-selenium/pull/2843)).
- K8s: Fix chart template issue that might occur when using Helm version v3.18.0 ([365c106](https://github.com/SeleniumHQ/docker-selenium/commit/365c10659905e6ad5e7e972fcb54225dc2a8c928)).
- K8s: Update chart dependencies (KEDA core 2.17,1, and so on).
- [See all changes](https://github.com/SeleniumHQ/docker-selenium/releases)


<br>

We thank all our contributors for their incredible efforts in making Selenium better with every
release. ❤️

For a detailed look at all changes, check out
the [release notes](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.33.0).

<br>

## Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/DeflateAwning" >}}
{{< gh-user "https://api.github.com/users/Delta456" >}}
{{< gh-user "https://api.github.com/users/bandophahita" >}}
{{< gh-user "https://api.github.com/users/navin772" >}}
{{< gh-user "https://api.github.com/users/t7ru" >}}
{{< gh-user "https://api.github.com/users/tomhughes" >}}
    </div>
  </div>
</div>

### [Selenium Docs & Website](https://github.com/SeleniumHQ/seleniumhq.github.io)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/PeteSong" >}}
{{< gh-user "https://api.github.com/users/alaahong" >}}
{{< gh-user "https://api.github.com/users/pallavigitwork" >}}
    </div>
  </div>
</div>

### [Docker Selenium](https://github.com/SeleniumHQ/docker-selenium)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/alcpereira" >}}
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
