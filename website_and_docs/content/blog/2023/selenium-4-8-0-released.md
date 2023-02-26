---
title: "Selenium 4.8.0 Released!"
linkTitle: "Selenium 4.8.0 Released!"
date: 2023-01-23
tags: ["selenium"]
categories: ["releases"]
author: Diego Molina ([@diegofmolina](https://twitter.com/diegofmolina))
description: >
  Today we're happy to announce that Selenium 4.8.0 has been released!
---

We're very happy to announce the release of Selenium 4.8.0 for Java, 
.NET, Ruby, Python, and Javascript as well as the Grid and Internet Explorer Driver.
Links to everything can be found on our [downloads page][downloads].

### Highlights

  * Chrome DevTools support is now: v107, v108, and v109 (Firefox still uses v85 for all versions)
  * Large JS executions have the name as a comment to help understand what payload being sent to/from server/driver.
  * Deprecation of headless convenience method. Read more about in the [headless blog post](/blog/2023/headless-is-going-away/).
  * Ruby overhauls Options classes (again)
  * Initial [BiDi] support in JavaScript, Ruby, and improvements in Java.
  * We're continuing to remove [Legacy Protocol](/blog/2022/legacy-protocol-support/) classes in Java and Grid. 
  * Accommodate ability to specify sub-paths in Grid.
  * Plus various language specific bug fixes; see the full list of changes in the [Changelogs][bindings]

### Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/nvborisenko" >}} 
{{< gh-user "https://api.github.com/users/joerg1985" >}}
{{< gh-user "https://api.github.com/users/kianelbo" >}}
{{< gh-user "https://api.github.com/users/jameshilliard" >}}
{{< gh-user "https://api.github.com/users/potapovDim" >}}
{{< gh-user "https://api.github.com/users/j3soon" >}}
{{< gh-user "https://api.github.com/users/gdams" >}}
{{< gh-user "https://api.github.com/users/jdufresne" >}}
{{< gh-user "https://api.github.com/users/valfirst" >}}
    </div>
  </div>
</div>

**Thanks as well to all the [Selenium Team Members][team] who contributed to this release:**

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/AutomatedTester" >}}
{{< gh-user "https://api.github.com/users/p0deje" >}}
{{< gh-user "https://api.github.com/users/titusfortner" >}}
{{< gh-user "https://api.github.com/users/diemol" >}}
{{< gh-user "https://api.github.com/users/pujagani" >}}
{{< gh-user "https://api.github.com/users/krmahadevan" >}}
{{< gh-user "https://api.github.com/users/harsha509" >}}
{{< gh-user "https://api.github.com/users/bonigarcia" >}}
{{< gh-user "https://api.github.com/users/symonk" >}} 
{{< gh-user "https://api.github.com/users/shs96c" >}} 
{{< gh-user "https://api.github.com/users/TamsilAmani" >}}
    </div>
  </div>
</div>

[downloads]: /downloads
[bindings]: /downloads#bindings
[team]: /project/structure
[BiDi]: https://github.com/w3c/webdriver-bidi

