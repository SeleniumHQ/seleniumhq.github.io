---
title: "Selenium 4.27 Released!"
linkTitle: "Selenium 4.27 Released!"
date: 2024-11-27
tags: ["selenium"]
categories: ["releases"]
author: Diego Molina [@diemol](https://www.diemol.com)
images:
  - "/images/blog/2024/selenium_4.27.webp"
description: >
  Today we're happy to announce that Selenium 4.27 has been released!
---
We're very happy to announce the release of Selenium 4.27 for 
Javascript, Ruby, Python, .NET, Java and the Grid!
Links to everything can be found on our [downloads page][downloads].

Here is the latest iteration of the world’s most popular browser automation tool! This release 
brings significant updates across all supported languages, enhancing functionality, performance, 
and compatibility. From new features like FedCM command support in Python and improved BiDi 
handling in .NET to critical deprecations like CDP methods for Firefox. 

## General Highlights

- **Chrome DevTools support** is now: v131, v128, and v127 (Firefox still uses v85 for all versions)
- **Selenium has over** [5.1M active users](https://plausible.io/manager.selenium.dev) in the last 30 days. 300K more than 1 month ago!
- **Deprecation of CDP methods for Firefox** across several bindings to align with evolving automation standards.
- **Enhanced Selenium Grid** with improved session handling, distributed retry logic, and faster server shutdown processes.
- **Updates for .NET and Java** to modernize exception handling, improve BiDi support, and address compatibility warnings.
- **Deprecation of `getAttribute`** in multiple languages as part of Selenium's evolution.


<br>

### Python
- Deprecated CDP methods for Firefox. ([e2e9ac5f7e](https://github.com/SeleniumHQ/selenium/commit/e2e9ac5f7e5ca2a2326bea9d16425525ce43da57))
- Replaced `imghdr` with `filetype` for better compatibility. ([b1828bf108](https://github.com/SeleniumHQ/selenium/commit/b1828bf1087d7d4acfd437d83ef6168617286191))
- Moved project metadata from `setup.py` to `pyproject.toml`. ([673d2c78be](https://github.com/SeleniumHQ/selenium/commit/673d2c78be76f1ccbb2e1017e5240d52f428b400))
- Added FedCM command support. ([d3d8070d50](https://github.com/SeleniumHQ/selenium/commit/d3d8070d50b481d2c6da98223322bc843cc25a01))
- Introduced backward compatibility for `AppiumConnection`. ([3a3c46b3c1](https://github.com/SeleniumHQ/selenium/commit/3a3c46b3c144b0a350dea3598481edd2761f11c5))
- Added user agent and extra headers via `ClientConfig`. ([e2023893c7](https://github.com/SeleniumHQ/selenium/commit/e2023893c7f37f69b2f7106a3907e0275bd9fbe1))
- Addressed `DetachedShadowRoot` exception handling. ([7aabb8d1b4](https://github.com/SeleniumHQ/selenium/commit/7aabb8d1b48c1cae74ae97710009daea960dc9a3))

<br>

### Ruby
- Deprecated CDP methods for Firefox. ([e9c09a200e](https://github.com/SeleniumHQ/selenium/commit/e9c09a200e374bba63acb0ef605175abb125e82e))
- Resolved deprecation warnings for the `uri` gem. ([751bacb6bc](https://github.com/SeleniumHQ/selenium/commit/751bacb6bc934436ec9dec2416a022d8d577e30a))
- Added BiDi navigation commands and support for network interception. ([573c8fe961](https://github.com/SeleniumHQ/selenium/commit/573c8fe9612c9c81406642e3e7a917cb5314eb3c))


<br>

### Java
- Enhanced error messages for `NoSuchElementException`. ([4a0d05e50e](https://github.com/SeleniumHQ/selenium/commit/4a0d05e50ea1750482211e04ece8062436eb5c6b))
- Deprecated `WebElement.getAttribute()`. ([cd7303c437](https://github.com/SeleniumHQ/selenium/commit/cd7303c437b0702d3a17c9ef43594375c11016eb))
- Introduced methods for selecting options containing specific text. ([b4b8aaba2b](https://github.com/SeleniumHQ/selenium/commit/b4b8aaba2bd3df57cae31164c614aec5f377c443))
- Added Firefox CDP deprecation warnings. ([19fc217985](https://github.com/SeleniumHQ/selenium/commit/19fc2179855d0f70b7241a6c4cfbd9152e023609))

<br>

### .NET
- Added CDP deprecation warnings for Firefox. ([8f725b3a80](https://github.com/SeleniumHQ/selenium/commit/8f725b3a80c3f3d621821e94a87db346ea91a8b1))
- Improved BiDi and async support across modules. ([9054e892cc](https://github.com/SeleniumHQ/selenium/commit/9054e892ccabfb470243e2bad585f0474901dd31))
- Enabled nullability annotations for better type safety. ([d9149acc09](https://github.com/SeleniumHQ/selenium/commit/d9149acc097531d336e611bd92d897381a0316c6))
- Introduced compatibility improvements for actions with clashing device names. ([a9ec9ca682](https://github.com/SeleniumHQ/selenium/commit/a9ec9ca6821fd466e8e9d6e966d0feb150b0a5a4))
- **Deprecated `GetAttribute` method** for WebElements. ([ac523a5d0a](https://github.com/SeleniumHQ/selenium/commit/ac523a5d0aa5a980a71c5adda3f4dafb0a560409))


<br>

### JavaScript
- Enabled BiDi tests for locating nodes with Chrome and Edge. ([339421538b](https://github.com/SeleniumHQ/selenium/commit/339421538b790c0ac2cf0a1a0aad62d0e76349eb))
- Enhanced support for authentication handlers in BiDi commands. ([25551adfe8](https://github.com/SeleniumHQ/selenium/commit/25551adfe80f788453ec38fac7933c5369616d4f))
- Updated dependencies to resolve security alerts. ([3906742748](https://github.com/SeleniumHQ/selenium/commit/3906742748d8b94b2eac074aeaf839eed20a95fa))

<br>

### Rust
- Selenium Manager now honors full browser versions. ([fe5b1985e5](https://github.com/SeleniumHQ/selenium/commit/fe5b1985e570bae90bf757c23439d461ef0dda9c))
- Updated logic to prioritize stable versions for Firefox management. ([0d2dda17b4](https://github.com/SeleniumHQ/selenium/commit/0d2dda17b4c4aba6ab0537f9d28910527c45a38b))

<br>

### Selenium Grid
- Improved retry logic for session creation in distributed grids. ([e4ab299ea4](https://github.com/SeleniumHQ/selenium/commit/e4ab299ea4d16943c18e8c31e9db1f7738ed9493))
- Improved session handling in Selenium Grid and reduced test flakiness. ([b0464e1adf](https://github.com/SeleniumHQ/selenium/commit/b0464e1adf8b4367dab9a98c26c800a7172cc0f8))
- Enhanced server shutdown for faster termination. ([62aa0e551e](https://github.com/SeleniumHQ/selenium/commit/62aa0e551e79176f21e3e1658518bc40855f81ae))
- Implemented graceful handling of stale sessions and client timeouts. ([b0464e1adf](https://github.com/SeleniumHQ/selenium/commit/b0464e1adf8b4367dab9a98c26c800a7172cc0f8))
- Improved detection of unsupported HTTP methods during request handling. ([f56b3d07d9](https://github.com/SeleniumHQ/selenium/commit/f56b3d07d932f81bafc80b90d9b3cb059fba133e))

  <br>

### Docker Selenium
- K8s: Allow multiple nodes of the same type in Helm configuration ([#2475](https://github.com/SeleniumHQ/docker-selenium/pull/2475))
- [See all changes](https://github.com/SeleniumHQ/docker-selenium/releases/tag/4.27.0-20241127)

  <br>

## Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/Delta456" >}}
{{< gh-user "https://api.github.com/users/Earlopain" >}}
{{< gh-user "https://api.github.com/users/RenderMichael" >}}
{{< gh-user "https://api.github.com/users/andrew" >}}
{{< gh-user "https://api.github.com/users/emanlove" >}}
{{< gh-user "https://api.github.com/users/iampopovich" >}}
{{< gh-user "https://api.github.com/users/josegomezr" >}}
{{< gh-user "https://api.github.com/users/mk868" >}}
{{< gh-user "https://api.github.com/users/navin772" >}}
{{< gh-user "https://api.github.com/users/pnatashap" >}}
{{< gh-user "https://api.github.com/users/sandeepsuryaprasad" >}}
{{< gh-user "https://api.github.com/users/shbenzer" >}}
{{< gh-user "https://api.github.com/users/syber911911" >}}
    </div>
  </div>
</div>


### [Selenium Docs & Website](https://github.com/SeleniumHQ/seleniumhq.github.io)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/AishIngale" >}}
{{< gh-user "https://api.github.com/users/RenderMichael" >}}
{{< gh-user "https://api.github.com/users/YevgeniyShunevych" >}}
{{< gh-user "https://api.github.com/users/alaahong" >}}
{{< gh-user "https://api.github.com/users/jasonren0403" >}}
{{< gh-user "https://api.github.com/users/navin772" >}}
{{< gh-user "https://api.github.com/users/pallavigitwork" >}}
{{< gh-user "https://api.github.com/users/shbenzer" >}}
{{< gh-user "https://api.github.com/users/zipperer" >}}
    </div>
  </div>
</div>

### [Docker Selenium](https://github.com/SeleniumHQ/docker-selenium)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/VietND96" >}}
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
{{< gh-user "https://api.github.com/users/diemol" >}}
{{< gh-user "https://api.github.com/users/harsha509" >}}
{{< gh-user "https://api.github.com/users/joerg1985" >}}
{{< gh-user "https://api.github.com/users/nvborisenko" >}}
{{< gh-user "https://api.github.com/users/p0deje" >}}
{{< gh-user "https://api.github.com/users/pujagani" >}}
{{< gh-user "https://api.github.com/users/shs96c" >}}
{{< gh-user "https://api.github.com/users/titusfortner" >}}
{{< gh-user "https://api.github.com/users/VietND96" >}}
    </div>
  </div>
</div>



Stay tuned for updates by following SeleniumHQ on [X (Formerly Twitter)](https://twitter.com/seleniumhq) or [LinkedIn](https://www.linkedin.com/company/selenium/)!

Happy automating!

[downloads]: /downloads
[bindings]: /downloads#bindings
[team]: /project/structure
[BiDi]: https://github.com/w3c/webdriver-bidi
