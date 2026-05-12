---
title: "Selenium 4.36 Released!"
linkTitle: "Selenium 4.36 Released!"
date: 2025-09-18
tags: [ "selenium" ]
categories: [ "releases" ]
author: Diego Molina [@diemol](https://www.diemol.com)
images:
  - "/images/blog/2025/selenium_4.36.jpg"
description: >
  Today we're happy to announce that Selenium 4.36 has been released!
---

We’re excited to announce the release of **Selenium 4.36** for JavaScript, Ruby, Python, .NET, Java
and the Grid! 🎉

Links to all assets can be found on our [downloads page][downloads].


---

# Selenium 4.36 Released

Selenium 4.36 is out with updates across all language bindings and the Grid. This release focuses 
on expanding BiDi capabilities, improving developer experience through logging and debugging 
enhancements, and cleaning up deprecated code paths. It also includes dependency updates, new 
tests, and stability improvements.

---

## 🚩 Highlights

- **Chrome DevTools support** is now: v140, v139, and v138.
- **Expanded BiDi Support**:
  - New events and modules added across Python, Java, .NET, and Ruby (e.g. `downloadEnd`, navigation, cookies, network collectors, WebExtensions).
- **Selenium Grid**:
  - Regression fix in request handling and better resource cleanup.
  - Light/Dark Mode toggle added to Grid UI.
- **Developer Experience**:
  - Improved error messages and type annotations in Python.
  - Better logging options across bindings, including Chrome and Safari support.
  - Cleaner structure for BiDi internals in .NET.

---

## 🔧 Language-specific Updates

### Java

- Extended BiDi support:
  - Added `DownloadInfo`, `onNavigationFailed`, and related test hooks.
- Screenshot hooks added to `WebDriverListener`.
- Unified and refactored `Select` class methods.
- Fixed concurrency issue in Selenium Manager.
- Improved resource handling and extended `HttpCommandExecutor` scope.
- Added `@JSpecify` annotations to exception classes and drivers.
- Fixed broken documentation links.

### Python

- BiDi:
  - Added support for `historyUpdated`, `navigate`, and `downloadEnd` events.
  - Added test for BiDi Data URL handling.
- Logging:
  - Enabled `--enable-chrome-logs` for Chrome to inherit browser I/O streams.
- Better error handling:
  - Clearer messages on selector issues (e.g. `InvalidSelectorException` for compound class names).
- Refactors:
  - Converted all relative imports to absolute.
  - Updated example for `DesiredCapabilities` to use modern `Options`.
- Fixed:
  - Type annotations across multiple files.
  - Proxy port handling in IPv6 environments.
- Cleanups:
  - Removed unused `driver_instance` from `conftest.py`.

### .NET

- BiDi:
  - Support for `network collectors`, `response body`, and `WebExtensions`.
  - Improved internal type safety, serialization, and navigation tracking.
  - Introduced `BaseNavigationInfo` and removed obsolete logic.
- Logging:
  - Added SafariDriver logging toggle.
  - Conditionally redirect browser output streams.
  - Suppressed unwanted messages from drivers.
- Infrastructure:
  - Better support for older .NET Framework copying Selenium Manager.
  - Fixed port discovery in IPv4-only environments.
- Documentation:
  - Fixed broken links in code and website.

### Ruby

- BiDi:
  - Implemented `browser` module with user context methods.
  - Support for getting client windows.
  - Removed Chrome FedCM test guards (now fixed in Chrome).
- Other:
  - Updated `unhandled_prompt_behavior` to support hash syntax.
  - Fixed unit tests and exception doc links.

### JavaScript

- Updated several dependencies:
  - `react`, `react-router-dom`, `material-ui`, `ws`, and more.
- BiDi-related fixes:
  - Text transformation issue in atoms corrected (`text-transform: capitalize`).

### Rust (Selenium Manager)

- Now honors full browser version, even when only major version is installed.
- Logs browser path even in offline mode.
- Updated to Rust edition 2024 and bumped dependencies (`rules_cc`, `rulest_rust`, etc.).

---

## 🖥️ Selenium Grid

- **Fixed regression** where requests were incorrectly rejected when nodes had capabilities but no free slots.
- **Improved resource handling**:
  - `HttpClient` is now closed properly after session teardown.
  - Improved timeout handling between Router and Node.
- **Updated to Netty 4.2.4** for better network handling.
- **New UI Feature**:
  - Added Light/Dark mode toggle to Grid interface.

---

## 📚 Documentation & Internal Tooling

- Fixed and updated:
  - Broken API doc links
  - CONTRIBUTING.md guidelines
  - Linting and type annotations
- Bumped:
  - Python dev dependencies
  - Setup scripts and GitHub Actions tooling

---

### 🐳 Docker Selenium

- K8s: Switch image registry for Bitnami deps image in sub charts ([#2960](https://github.com/SeleniumHQ/docker-selenium/pull/2960))
- Docker: Fix regression SE_NODE_STEREOTYPE_EXTRA could not merge stereotypes ([#2971](https://github.com/SeleniumHQ/docker-selenium/pull/2971))
- Update ubuntu:noble Docker digest to 728785b ([#2972](https://github.com/SeleniumHQ/docker-selenium/pull/2972))
- [See all changes](https://github.com/SeleniumHQ/docker-selenium/releases)


<br>

We thank all our contributors for their incredible efforts in making Selenium better with every
release. ❤️

For a detailed look at all changes, check out
the [release notes](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.36.0).

<br>

## Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/NoStory-py" >}}
{{< gh-user "https://api.github.com/users/Osseta" >}}                                                                                                                                                      
{{< gh-user "https://api.github.com/users/Paresh-0007" >}}                                                                                                                                                 
{{< gh-user "https://api.github.com/users/VbhvGupta" >}}                                                                                                                                                   
{{< gh-user "https://api.github.com/users/giulong" >}}                                                                                                                                                     
{{< gh-user "https://api.github.com/users/iampopovich" >}}                                                                                                                                                 
{{< gh-user "https://api.github.com/users/lauromoura" >}}                                                                                                                                                  
{{< gh-user "https://api.github.com/users/mtrea" >}}                                                                                                                                                       
{{< gh-user "https://api.github.com/users/rpallavisharma" >}}                                                                                                                                              
{{< gh-user "https://api.github.com/users/vicky-iv" >}}
    </div>
  </div>
</div>

### [Selenium Docs & Website](https://github.com/SeleniumHQ/seleniumhq.github.io)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/Anilkumar-Shrestha" >}}
{{< gh-user "https://api.github.com/users/Tuscann" >}}                                                                                                                                                     
{{< gh-user "https://api.github.com/users/beinghumantester" >}}                                                                                                                                            
{{< gh-user "https://api.github.com/users/noritaka1166" >}}                                                                                                                                                
{{< gh-user "https://api.github.com/users/olleolleolle" >}}                                                                                                                                                
{{< gh-user "https://api.github.com/users/rpallavisharma" >}}
    </div>
  </div>
</div>

### [Docker Selenium](https://github.com/SeleniumHQ/docker-selenium)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/anwaramoon" >}}                                                                                                                                                  
{{< gh-user "https://api.github.com/users/f-lopes" >}}                                                                                                                                                     
{{< gh-user "https://api.github.com/users/pplulee" >}}                                                                                                                                                     
    </div>
  </div>
</div>

### [Selenium Team Members][team]

**Thanks as well to all the team members who contributed to this release:**

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/aguspe" >}}
{{< gh-user "https://api.github.com/users/asolntsev" >}} 
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
