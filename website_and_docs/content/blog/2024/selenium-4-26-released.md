---
title: "Selenium 4.26 Released!"
linkTitle: "Selenium 4.26 Released!"
date: 2024-11-03
tags: ["selenium"]
categories: ["releases"]
author: Diego Molina [@diemol](https://www.diemol.com)
images:
  - "/images/blog/2024/selenium_4.26.jpg"
description: >
  Today we're happy to announce that Selenium 4.26 has been released!
---

We're very happy to announce the release of Selenium 4.26 for 
Javascript, Ruby, Python, .NET, Java and the Grid!
Links to everything can be found on our [downloads page][downloads].

Selenium 4.26.0 release introduces new features, key enhancements, and numerous bug fixes across 
different languages and components. This version focuses on improving compatibility, updating 
dependencies, enhancing internal logging, and providing broader WebDriver capabilities. Here are 
the most important updates:

## General Highlights
- **Chrome DevTools support** is now: v130, v129, and v128 (Firefox still uses v85 for all versions)
- **Selenium has at least** [4.8M active users](https://plausible.io/manager.selenium.dev) in the last 30 days. 800K more than 1 month ago!
- **Selenium Manager Enhancements**: Added better handling for invalid browser versions and improved logging, helping to streamline browser management.
- **Expanded BiDi (Bidirectional WebDriver Protocol) Support for .NET**: Continuing the work on BiDi for .NET, this release includes improved WebSocket communication, CDP DevTools integration, and expanded logging, advancing real-time and bidirectional interactions.
- **Grid UI Enhancements**: New sorting options by Platform, Status, and ID, session timeout display, and WebSocket connection management for better performance and user experience.
- **CI/CD Pipeline Improvements**: Numerous updates for CI workflows, such as artifact handling and new testing configurations, to boost stability and developer productivity.

  <br>

### .NET
- Updated WebSocket communication and DevTools integration in the BiDi implementation, adding extensive internal logs to improve diagnostics ([#14566](https://github.com/SeleniumHQ/selenium/pull/14566), [#14558](https://github.com/SeleniumHQ/selenium/pull/14558)).
- Added support for the `GetLog` command in the Remote WebDriver ([#14549](https://github.com/SeleniumHQ/selenium/pull/14549)).
- Enhanced configuration for `PrintOptions`, allowing direct control over `PageDimensions` and `PageMargins` ([#14593](https://github.com/SeleniumHQ/selenium/pull/14593)).
- Deprecated several old constructors for cleaner exception handling and improved compatibility with Ahead-of-Time (AOT) compilation ([#14574](https://github.com/SeleniumHQ/selenium/pull/14574)).

  <br>

### Java
- Increased property scope for improved compatibility with Appium ([#14183](https://github.com/SeleniumHQ/selenium/pull/14183)).
- Updated SpotBugs settings and fixed issues in `ChromiumDriver` and `PortProber` for cleaner code ([#14589](https://github.com/SeleniumHQ/selenium/pull/14589)).
- Added PAC proxy URL support for Selenium Manager to expand proxy configuration capabilities ([#14506](https://github.com/SeleniumHQ/selenium/pull/14506)).

  <br>

### Python
- Added more internal logging for CDP, and configured WebDriver HTTP client settings for enhanced performance ([#14668](https://github.com/SeleniumHQ/selenium/pull/14668), [#13286](https://github.com/SeleniumHQ/selenium/pull/13286)).
  > Explore the various configuration parameters for the [WebDriver HTTP client](https://www.selenium.dev/documentation/webdriver/drivers/http_client/).
- Removed deprecated EdgeService parameters and eliminated Python 2.x code from various test files ([#14563](https://github.com/SeleniumHQ/selenium/pull/14563), [#14502](https://github.com/SeleniumHQ/selenium/pull/14502)).
- Set consistent polling for `WebDriverWait` methods to align behavior between Java and Python implementations ([#14626](https://github.com/SeleniumHQ/selenium/pull/14626)).
- Improves binding extensibility for seamless integration of Selenium into Appium's Python client. ([#14587](https://github.com/SeleniumHQ/selenium/pull/14587)).

  <br>

### JavaScript
- Closed BiDi WebSocket connection on session end, improving session management in BiDi ([#14507](https://github.com/SeleniumHQ/selenium/pull/14507)).
- Fixed issues with `sendKeys` command, addressing errors in `FileDetector` handling ([#14663](https://github.com/SeleniumHQ/selenium/pull/14663)).

  <br>

### Ruby
- Added RBS type support for BiDi-related classes, aligning with updates for Ruby BiDi compatibility ([#14611](https://github.com/SeleniumHQ/selenium/pull/14611)).
- Updated BiDi script structures to match recent specifications for consistent implementation ([#14236](https://github.com/SeleniumHQ/selenium/pull/14236)).

  <br>

### Selenium Grid
- New Grid UI features for sorting and WebSocket management, adding clarity and control to session management ([#14571](https://github.com/SeleniumHQ/selenium/pull/14571), [#14598](https://github.com/SeleniumHQ/selenium/pull/14598), [#14599](https://github.com/SeleniumHQ/selenium/pull/14599)).
- Enabled async requests in `httpclient` to enhance request handling performance ([#14409](https://github.com/SeleniumHQ/selenium/pull/14409)).
- Improved node handling for better scalability and stability ([#14628](https://github.com/SeleniumHQ/selenium/pull/14628)).

  <br>

### Docker Selenium
- Updated FFmpeg v7.1 in video recorder ([#2439](https://github.com/SeleniumHQ/docker-selenium/pull/2439)).
- Updated in Helm chart for Selenium Grid deployment to Kubernetes
  - Add GraphQL metrics exporter for monitoring ([#2425](https://github.com/SeleniumHQ/docker-selenium/pull/2425)).
  - Add templates for Relay node ([#2453](https://github.com/SeleniumHQ/docker-selenium/pull/2453)).
  - Allow to overwrite config videoRecorder in each node ([#2445](https://github.com/SeleniumHQ/docker-selenium/pull/2445)).

  <br>

## Contributors

**Special shout-out to everyone who helped the Selenium Team get this release out!**

### [Selenium](https://github.com/SeleniumHQ/selenium)

<div class="d-flex justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/BlitzDestroyer" >}}
{{< gh-user "https://api.github.com/users/Delta456" >}}
{{< gh-user "https://api.github.com/users/Mr0grog" >}}
{{< gh-user "https://api.github.com/users/RenderMichael" >}}
{{< gh-user "https://api.github.com/users/aguspe" >}}
{{< gh-user "https://api.github.com/users/dbernhard-0x7CD" >}}
{{< gh-user "https://api.github.com/users/garg3133" >}}
{{< gh-user "https://api.github.com/users/iampopovich" >}}                                                                                                                                                                                                                                                                   
{{< gh-user "https://api.github.com/users/mk868" >}}                                                                                                                                                                                                                                                                         
{{< gh-user "https://api.github.com/users/navin772" >}}                                                                                                                                                                                                                                                                      
{{< gh-user "https://api.github.com/users/shbenzer" >}}                                                                                                                                                                                                                                                                      
    </div>
  </div>
</div>


### [Selenium Docs & Website](https://github.com/SeleniumHQ/seleniumhq.github.io)

<div class="row justify-content-center">
  <div class="col-11 p-4 bg-transparent">
    <div class="row justify-content-center">
{{< gh-user "https://api.github.com/users/Abdelrhman-Ellithy" >}}
{{< gh-user "https://api.github.com/users/AishIngale" >}}
{{< gh-user "https://api.github.com/users/Delta456" >}}
{{< gh-user "https://api.github.com/users/alaahong" >}}
{{< gh-user "https://api.github.com/users/harshitBhardwaj97" >}}
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
{{< gh-user "https://api.github.com/users/brunobritorj" >}}
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

## Project Highlights

This year marks a monumental milestone—20 years of Selenium transforming browser automation! 
Since its inception as a modest open-source project, Selenium has grown into the world’s most 
trusted tool for web automation, powering testing and development for countless users globally. 
From revolutionizing open-source collaboration to shaping automation practices, Selenium has 
impacted developers, testers, and organizations worldwide.

To honor this journey, the Selenium team hosted a special webinar on October 28th, 2024, where 
the leadership team shared insights on Selenium’s evolution, the latest advancements in WebDriver 
BiDi, and exciting prospects for the future. If you’d like to learn more about Selenium’s 
incredible journey and future plans, head to the official blog post 
[here](https://www.selenium.dev/blog/2024/selenium-milestone-20yrs-blog/).

Special thanks to the Selenium community for your continued support and contributions, to
the entire Selenium team for their dedication and hard work, 
and to [Pallavi Sharma](https://www.linkedin.com/in/pallavimuse/) and 
[Maaret Pyhäjärvi](https://www.linkedin.com/in/maaret/) for organizing and leading this event.



Stay tuned for updates by following SeleniumHQ on [X (Formerly Twitter)](https://twitter.com/seleniumhq) or [LinkedIn](https://www.linkedin.com/company/selenium/)!

Happy automating!

[downloads]: /downloads
[bindings]: /downloads#bindings
[team]: /project/structure
[BiDi]: https://github.com/w3c/webdriver-bidi
