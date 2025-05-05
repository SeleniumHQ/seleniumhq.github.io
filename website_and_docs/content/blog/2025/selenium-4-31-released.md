---
title: "Selenium 4.31 Released!"
linkTitle: "Selenium 4.31 Released!"
date: 2025-04-05
tags: [ "selenium" ]
categories: [ "releases" ]
author: Diego Molina [@diemol](https://www.diemol.com)
images:
  - "/images/blog/2025/selenium_4.31.jpg"
description: >
  Today we're happy to announce that Selenium 4.31 has been released!
---

We’re excited to announce the release of **Selenium 4.31** for Javascript, Ruby, Python, .NET, Java
and the Grid! 🎉  
This release focuses on improvements across the board, including better BiDi protocol support, test
reliability, nullability enhancements, and cleanup of legacy code across languages.

Links to all assets can be found on our [downloads page][downloads].


---

## 🚀 Major Highlights

- Continued work towards full BiDi support in all bindings
- Cleanup of unused legacy components (like `wgxpath`)
- Expanded test coverage and fixes for various environments (CI, RBE, MacOS)
- Improvements in documentation and development tooling

---

## 🔹 Language-Specific Changes

### **Java**

- [Handle `getNamedCookie` and `deleteNamedCookie` for empty strings](https://github.com/SeleniumHQ/selenium/pull/15092)
- [Add nullness for AppCacheStatus, Credential, and Either](https://github.com/SeleniumHQ/selenium/pull/15119)
- [Add nullness for interactions](https://github.com/SeleniumHQ/selenium/pull/15118)
- [Enable Safari for CookieImplementationTest](https://github.com/SeleniumHQ/selenium/pull/15544)
- [Add test to add a cookie in a user context (BiDi)](https://github.com/SeleniumHQ/selenium/pull/15312)

### **Python**

- [Fix docstring issues that sphinx complains about](https://github.com/SeleniumHQ/selenium/pull/15466)
- [Only shutdown service if process not already terminated](https://github.com/SeleniumHQ/selenium/pull/15183)
- [Remove unused mocker arg in chrome options test](https://github.com/SeleniumHQ/selenium/pull/15540)
- [Fix driver class name in test fixtures](https://github.com/SeleniumHQ/selenium/pull/15550)

### **JavaScript**

- Fixed BiDi tests for Chrome and Firefox on CI.
- Implemented BiDi `permissions` module commands.

### **Ruby**

- [Fix BiDi test errors](https://github.com/SeleniumHQ/selenium/pull/15482) 
- [Allow symbols again to be passed on `delete_cookie`](https://github.com/SeleniumHQ/selenium/pull/15519)

### **.NET**

- [Decouple nested BiDi types across multiple modules](https://github.com/SeleniumHQ/selenium/pulls?q=is%3Apr+author%3Anvborisenko+label%3Adotnet)
- [Fix null warnings in `RelativeBy` by sealing the type](https://github.com/SeleniumHQ/selenium/pull/15379)
- [Simplify conversion to `LocalValue`](https://github.com/SeleniumHQ/selenium/pull/15441)
- [Unify protected and internal Execute methods](https://github.com/SeleniumHQ/selenium/pull/15233)
- [Make `ContinueWithAuthCommand` closer to spec (breaking change)](https://github.com/SeleniumHQ/selenium/pull/15545)
- [Avoid intermediate JsonDocument allocation to improve performance](https://github.com/SeleniumHQ/selenium/pull/15555)

### **Grid**

- [Expose register status via Node status response](https://github.com/SeleniumHQ/selenium/pull/15448)
- [Add traces for event stop session in Node](https://github.com/SeleniumHQ/selenium/pull/15348)

### **Docker Selenium**

- Helm config: Add template for file browser video records service ([#2763](https://github.com/SeleniumHQ/docker-selenium/pull/2763))
- Helm config: Strictly handle `basicAuth.enabled` in template ([#2760](https://github.com/SeleniumHQ/docker-selenium/pull/2760))
- Selenium Grid Autoscaling in Kubernetes is expected working well with KEDA core v2.17.0.
- [See all changes](https://github.com/SeleniumHQ/docker-selenium/releases)


<br>

We thank all our contributors for their incredible efforts in making Selenium better with every
release. ❤️

For a detailed look at all changes, check out
the [release notes](https://github.com/SeleniumHQ/selenium/releases/tag/4.31).

<br>

## Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/Delta456" >}}
{{< gh-user "https://api.github.com/users/PSandro" >}}
{{< gh-user "https://api.github.com/users/mk868" >}}
{{< gh-user "https://api.github.com/users/navin772" >}}
    </div>
  </div>
</div>

### [Selenium Docs & Website](https://github.com/SeleniumHQ/seleniumhq.github.io)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/alaahong" >}}
{{< gh-user "https://api.github.com/users/pallavigitwork" >}}
    </div>
  </div>
</div>

### [Docker Selenium](https://github.com/SeleniumHQ/docker-selenium)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/KenHuPricer" >}}
{{< gh-user "https://api.github.com/users/KyriosGN0" >}}
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
