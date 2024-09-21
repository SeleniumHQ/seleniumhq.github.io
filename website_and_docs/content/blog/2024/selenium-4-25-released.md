---
title: "Selenium 4.25 Released!"
linkTitle: "Selenium 4.25 Released!"
date: 2024-09-20
tags: ["selenium"]
categories: ["releases"]
author: Diego Molina [@diemol](https://www.diemol.com)
images:
  - "/images/blog/2024/selenium_4.25.jpg"
description: >
  Today we're happy to announce that Selenium 4.25 has been released!
---

We're very happy to announce the release of Selenium 4.25 for 
Javascript, Ruby, Python, .NET, Java and the Grid!
Links to everything can be found on our [downloads page][downloads].

Selenium 4.25.0 introduces several important changes and improvements across multiple programming 
languages and build systems. Below are the key highlights from this release.

### General Updates
- Chrome DevTools support is now: v129, v128, and v127 (Firefox still uses v85 for all versions)
- Selenium has at least [4M active users](https://plausible.io/manager.selenium.dev) in the last 30 days. 1.5M more than 4 months ago! 
- **First implementation of BiDi (Bidirectional WebDriver Protocol) for .NET**, providing advanced capabilities like inspecting browser contexts and receiving real-time events.
  - We are looking for feedback on this feature, so please try it out and let us know what you think! Try `var bidi = await driver.AsBiDiAsync();` to get started.

  <br>

### Java
- Escape cookie values when required for tests ([#14486](https://github.com/SeleniumHQ/selenium/commit/375e841c7acaf575133617968406e289ee04b459))
- Warn about an upcoming change enforcing string quotes in TOML ([#14491](https://github.com/SeleniumHQ/selenium/commit/6b4c39c19e9ac3f63bbf8827cfd26aa782e77042))
- [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/java/CHANGELOG)

  <br>

### Python
- Fixed type errors for `pointer_input.py`, `wheel_input.py`, and `firefox/options.py` ([#14476](https://github.com/SeleniumHQ/selenium/commit/05bce9b4c088d939d4a25a33e0d014d3f3a67473))
- Fixed failing BiDi tests ([#14448](https://github.com/SeleniumHQ/selenium/commit/be40b5c85350b2f5cf83194cce4cb1ab23e13172))
- Dropped support for Python 2.x in `firefox_profile.py` ([#14489](https://github.com/SeleniumHQ/selenium/commit/71a0629521b42263ad34874adba4e97cd8747fbd))
- [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/py/CHANGES)

  <br>

### JavaScript
- Fixed flaky network event tests for BiDi ([#14512](https://github.com/SeleniumHQ/selenium/commit/2970ad30a75d798edb4abdbcfd04666a95f8ef8a))
- Closed CDP websocket connection on `driver.quit` ([#14501](https://github.com/SeleniumHQ/selenium/commit/7c8b46dd4a4ce11ad6fd1c436416cdbd448c1b73))
- [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/javascript/node/selenium-webdriver/CHANGES.md)

  <br>

### .NET
- BiDi API updates, including renaming methods and simplifying context handling ([#14318](https://github.com/SeleniumHQ/selenium/commit/3e8b34cea24635e89aa42d09db8c37b6723a9005))
- Exposed BiDi associated references in browsing context ([#14495](https://github.com/SeleniumHQ/selenium/commit/6c0df70463242ba1f7b182e11060fcf9a8e50a01))
- [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/dotnet/CHANGELOG)

  <br>

### Ruby
- Allowed driver path to be set using environment variables ([#14287](https://github.com/SeleniumHQ/selenium/commit/7602371488ebd14d2c6d8d980134bff42bbd17e9))
- Fixed the `add_cause` method not being able to process an array of hashes ([#14433](https://github.com/SeleniumHQ/selenium/commit/247bc2bbced6502625786dc9fb56c602bc9786dc))
- [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/rb/CHANGES)

  <br>

### Rust
- Fixed errors in Selenium Manager when the browser path is incorrect ([#14381](https://github.com/SeleniumHQ/selenium/commit/0d426741c9b609f0748e64cff6e63343215eebcf))
- Reused driver mirror URLs to discover Firefox versions ([#14493](https://github.com/SeleniumHQ/selenium/commit/64590084bc4baa5a00c8b7709b80c75e77de818a))
- [See all changes](https://github.com/SeleniumHQ/selenium/blob/trunk/rust/CHANGELOG.md)

## Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/Delta456" >}}
{{< gh-user "https://api.github.com/users/aguspe" >}}
{{< gh-user "https://api.github.com/users/angiejones" >}}
{{< gh-user "https://api.github.com/users/cgossett" >}}
{{< gh-user "https://api.github.com/users/dnwe" >}}
{{< gh-user "https://api.github.com/users/manuelsblanco" >}}
{{< gh-user "https://api.github.com/users/mk868" >}}
{{< gh-user "https://api.github.com/users/navin772" >}}
{{< gh-user "https://api.github.com/users/sandeepsuryaprasad" >}}
{{< gh-user "https://api.github.com/users/shbenzer" >}}
    </div>
  </div>
</div>


### [Selenium Docs & Website](https://github.com/SeleniumHQ/seleniumhq.github.io)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/KaranocaVe" >}}
{{< gh-user "https://api.github.com/users/aguspe" >}}
{{< gh-user "https://api.github.com/users/pallavigitwork" >}}
{{< gh-user "https://api.github.com/users/shbenzer" >}}
    </div>
  </div>
</div>

### [Docker Selenium](https://github.com/SeleniumHQ/docker-selenium)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/Koeppchen" >}}
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
