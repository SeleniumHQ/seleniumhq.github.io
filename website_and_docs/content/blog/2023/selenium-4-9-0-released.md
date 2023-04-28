---
title: "Selenium 4.9.0 Released!"
linkTitle: "Selenium 4.9.0 Released!"
date: 2023-04-21
tags: ["selenium"]
categories: ["releases"]
author: Diego Molina ([@diegofmolina](https://twitter.com/diegofmolina))
description: >
  Today we're happy to announce that Selenium 4.9.0 has been released!
---

We're very happy to announce the release of Selenium 4.9.0 for Java, 
.NET, Ruby, Python, and Javascript as well as the Grid and Internet Explorer Driver.
Links to everything can be found on our [downloads page][downloads].

### Highlights

  * Chrome DevTools support is now: v110, v111, and v112 (Firefox still uses v85 for all versions)
  * Maven BOM for the Java bindings.
  * Remote file downloads are [now possible through Selenium Grid](/documentation/grid/configuration/cli_options/#enabling-managed-downloads-by-the-node).
  * First steps taken to phase out CDP in Firefox and replace it with the BiDi implementation.
  * `InvalidSelectorException` [now extends `WebDriverException` instead of `NoSuchElementException`](/blog/2023/invalid-selector-exception-has-changed/).
  * Selenium Manager uses information set in the browser options to get the correct browser driver.
  * A [sub-path](/documentation/grid/configuration/cli_options/#router) can be set in Selenium Grid to have a custom Grid url. 
  * Complete removal of [Json Wire Protocol support in Java and Grid](https://www.selenium.dev/blog/2022/legacy-protocol-support/).


### Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/atrnh" >}} 
{{< gh-user "https://api.github.com/users/nvborisenko" >}} 
{{< gh-user "https://api.github.com/users/dev-ardi" >}}
{{< gh-user "https://api.github.com/users/arnonax-tr" >}}
{{< gh-user "https://api.github.com/users/robotdana" >}}
{{< gh-user "https://api.github.com/users/iampopovich" >}}
{{< gh-user "https://api.github.com/users/joerg1985" >}}
{{< gh-user "https://api.github.com/users/snackattas" >}}
{{< gh-user "https://api.github.com/users/arielj" >}}
{{< gh-user "https://api.github.com/users/whimboo" >}}
{{< gh-user "https://api.github.com/users/code-with-abdullah" >}}
{{< gh-user "https://api.github.com/users/71n9" >}}
{{< gh-user "https://api.github.com/users/lifefloating" >}}
{{< gh-user "https://api.github.com/users/jnhyperion" >}}
{{< gh-user "https://api.github.com/users/MohabMohie" >}}
{{< gh-user "https://api.github.com/users/MMK-IBSEN" >}}
{{< gh-user "https://api.github.com/users/RussiaVk" >}}
{{< gh-user "https://api.github.com/users/Kouzukii" >}}
{{< gh-user "https://api.github.com/users/mdmintz" >}}
{{< gh-user "https://api.github.com/users/RenderMichael" >}}
{{< gh-user "https://api.github.com/users/tosmolka" >}}
{{< gh-user "https://api.github.com/users/etiennebarrie" >}}
{{< gh-user "https://api.github.com/users/jameshilliard" >}}
{{< gh-user "https://api.github.com/users/marksmayo" >}}
    </div>
  </div>
</div>

**Thanks as well to all the [Selenium Team Members][team] who contributed to this release:**

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/p0deje" >}}
{{< gh-user "https://api.github.com/users/bonigarcia" >}}
{{< gh-user "https://api.github.com/users/titusfortner" >}}
{{< gh-user "https://api.github.com/users/diemol" >}}
{{< gh-user "https://api.github.com/users/TamsilAmani" >}}
{{< gh-user "https://api.github.com/users/harsha509" >}}
{{< gh-user "https://api.github.com/users/shs96c" >}} 
{{< gh-user "https://api.github.com/users/krmahadevan" >}}
{{< gh-user "https://api.github.com/users/AutomatedTester" >}}
{{< gh-user "https://api.github.com/users/symonk" >}} 
{{< gh-user "https://api.github.com/users/pujagani" >}}
    </div>
  </div>
</div>

Stay tuned for updates by following [SeleniumHQ](https://twitter.com/seleniumhq)!

Happy testing!

[downloads]: /downloads
[bindings]: /downloads#bindings
[team]: /project/structure
[BiDi]: https://github.com/w3c/webdriver-bidi

