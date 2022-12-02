---
title: "Selenium 4.7.0 Released!"
linkTitle: "Selenium 4.7.0 Released!"
date: 2022-12-02
tags: ["selenium"]
categories: ["releases"]
author: Titus Fortner ([@titusfortner](https://twitter.com/titusfortner))
description: >
  Today we're happy to announce that Selenium 4.7.0 has been released!
---

We're very happy to announce the release of Selenium 4.7.0 for Java, 
.NET, and Javascript as well as the Grid and Internet Explorer Driver;
for Ruby use 4.7.1, and Python 4.7.2. Links to everything can be found on our 
[downloads page][downloads].

### Highlights

  * Chrome DevTools support is now: v106, v107, and v108 (Firefox still uses v85 for all versions)
  * [Selenium Manager](/blog/2022/introducing-selenium-manager/) now supports IE Driver & has improved error logging.
  * Using Edge in IE Mode no longer requires ignoring zoom levels.
  * We're continuing to remove [Legacy Protocol](https://www.selenium.dev/blog/2022/legacy-protocol-support/) classes in Java and Grid. 
  * Java adds WebDriver-BiDi support for Logging.
  * .NET includes an explicit target for net6.0 framework.
  * Ruby is now publishing [nightly gems](https://github.com/SeleniumHQ/selenium/packages/)
  * Plus various language specific bug fixes; see the full list of changes in the [Changelogs][bindings]

### Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/cclauss" >}}
{{< gh-user "https://api.github.com/users/Dor-bl" >}}
{{< gh-user "https://api.github.com/users/fenilgmehta" >}}
{{< gh-user "https://api.github.com/users/jaredwebber" >}}
{{< gh-user "https://api.github.com/users/joerg1985" >}}
{{< gh-user "https://api.github.com/users/mdmintz" >}}
{{< gh-user "https://api.github.com/users/nvborisenko" >}} 
{{< gh-user "https://api.github.com/users/TamsilAmani" >}}
    </div>
  </div>
</div>

**Thanks as well to all the [Selenium Team Members][team] who contributed to this release:**

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/AutomatedTester" >}}
{{< gh-user "https://api.github.com/users/bonigarcia" >}}
{{< gh-user "https://api.github.com/users/diemol" >}}
{{< gh-user "https://api.github.com/users/harsha509" >}}
{{< gh-user "https://api.github.com/users/jimevans" >}}
{{< gh-user "https://api.github.com/users/p0deje" >}}
{{< gh-user "https://api.github.com/users/pujagani" >}}
{{< gh-user "https://api.github.com/users/shs96c" >}} 
{{< gh-user "https://api.github.com/users/symonk" >}} 
{{< gh-user "https://api.github.com/users/titusfortner" >}}
    </div>
  </div>
</div>

[downloads]: /downloads
[bindings]: /downloads#bindings
[team]: /project/structure
