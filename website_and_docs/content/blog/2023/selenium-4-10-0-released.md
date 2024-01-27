---
title: "Selenium 4.10.0 Released!"
linkTitle: "Selenium 4.10.0 Released!"
date: 2023-06-07
tags: ["selenium"]
categories: ["releases"]
author: Diego Molina ([@diegofmolina](https://twitter.com/diegofmolina))
description: >
  Today we're happy to announce that Selenium 4.10.0 has been released!
---

We're very happy to announce the release of Selenium 4.10.0 for Java, 
.NET, Ruby, Python, and Javascript as well as the Grid and Internet Explorer Driver.
Links to everything can be found on our [downloads page][downloads].

### Highlights

  * Chrome DevTools support is now: v112, v113, and v114 (Firefox still uses v85 for all versions)
  * Selenium Manager supports proxy usage by using the proxy information set in the browser options.
  * Adding support for [`webview2`](https://learn.microsoft.com/en-us/microsoft-edge/webview2/how-to/webdriver) in Edge for Java, JavaScript, and Ruby
  * Error messages across bindings now include links to the Selenium documentation.  
  * Overhaul of the service classes and logging output options.
  * Logger in Ruby updated default behavior to match other languages and added features to improve filtering types of logging.
  * Python - removal of several sections of deprecated code.
    * Most of them were arguments that can be set in the [`Options` classes](https://www.selenium.dev/documentation/webdriver/drivers/options/) (browser values) or [`Service` classes](https://www.selenium.dev/documentation/webdriver/drivers/service/) (browser driver values).
    * They have been deprecated since the [first Selenium 4 release](https://www.selenium.dev/documentation/webdriver/getting_started/upgrade_to_selenium_4/#python-1)

### Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

#### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/boris-petrov" >}} 
{{< gh-user "https://api.github.com/users/nvborisenko" >}} 
{{< gh-user "https://api.github.com/users/ParadiseWitch" >}} 
{{< gh-user "https://api.github.com/users/rishav887" >}} 
{{< gh-user "https://api.github.com/users/iampopovich" >}}
{{< gh-user "https://api.github.com/users/mdmintz" >}}
{{< gh-user "https://api.github.com/users/vlad8x8" >}} 
{{< gh-user "https://api.github.com/users/cocreature" >}} 
{{< gh-user "https://api.github.com/users/joerg1985" >}}
{{< gh-user "https://api.github.com/users/valfirst" >}} 
{{< gh-user "https://api.github.com/users/bhecquet" >}}
{{< gh-user "https://api.github.com/users/M1troll" >}}
{{< gh-user "https://api.github.com/users/ShadowLNC" >}}
{{< gh-user "https://api.github.com/users/sandeepsuryaprasad" >}}
{{< gh-user "https://api.github.com/users/alpatron" >}}



{{< gh-user "https://api.github.com/users/tarekoraby" >}}
{{< gh-user "https://api.github.com/users/smilinrobin" >}}
{{< gh-user "https://api.github.com/users/pallavigitwork" >}}
{{< gh-user "https://api.github.com/users/kathyrollo" >}}
{{< gh-user "https://api.github.com/users/gulifeng8" >}}
{{< gh-user "https://api.github.com/users/bsquizz" >}}


{{< gh-user "https://api.github.com/users/luisfcorreia" >}}
{{< gh-user "https://api.github.com/users/serlank" >}}
{{< gh-user "https://api.github.com/users/JordanZimmitti" >}}
{{< gh-user "https://api.github.com/users/uluzox" >}}
{{< gh-user "https://api.github.com/users/bschreder" >}}
    </div>
  </div>
</div>

#### [Selenium Docs & Website](https://github.com/SeleniumHQ/seleniumhq.github.io)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/tarekoraby" >}}
{{< gh-user "https://api.github.com/users/smilinrobin" >}}
{{< gh-user "https://api.github.com/users/pallavigitwork" >}}
{{< gh-user "https://api.github.com/users/kathyrollo" >}}
{{< gh-user "https://api.github.com/users/gulifeng8" >}}
{{< gh-user "https://api.github.com/users/bsquizz" >}}
    </div>
  </div>
</div>

#### [Docker Selenium](https://github.com/SeleniumHQ/docker-selenium)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/luisfcorreia" >}}
{{< gh-user "https://api.github.com/users/serlank" >}}
{{< gh-user "https://api.github.com/users/JordanZimmitti" >}}
{{< gh-user "https://api.github.com/users/uluzox" >}}
{{< gh-user "https://api.github.com/users/bschreder" >}}
    </div>
  </div>
</div>

**Thanks as well to all the [Selenium Team Members][team] who contributed to this release:**

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/bonigarcia" >}}
{{< gh-user "https://api.github.com/users/p0deje" >}}
{{< gh-user "https://api.github.com/users/TamsilAmani" >}}
{{< gh-user "https://api.github.com/users/diemol" >}}
{{< gh-user "https://api.github.com/users/titusfortner" >}}
{{< gh-user "https://api.github.com/users/shs96c" >}} 
{{< gh-user "https://api.github.com/users/krmahadevan" >}}
{{< gh-user "https://api.github.com/users/harsha509" >}}
{{< gh-user "https://api.github.com/users/pujagani" >}}
{{< gh-user "https://api.github.com/users/AutomatedTester" >}}
{{< gh-user "https://api.github.com/users/jamesmortensen" >}}
    </div>
  </div>
</div>

Stay tuned for updates by following [SeleniumHQ](https://twitter.com/seleniumhq)!

Happy testing!

[downloads]: /downloads
[bindings]: /downloads#bindings
[team]: /project/structure
[BiDi]: https://github.com/w3c/webdriver-bidi

