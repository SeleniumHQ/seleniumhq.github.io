---
title: "A Curious Case of Selenium Manager Usage: What's Behind Chrome 127.0.6533.99?"
linkTitle: "A Curious Case of Selenium Manager Usage: What's Behind Chrome 127.0.6533.99?"
date: 2025-09-14
tags: ["selenium", "manager", "chrome", "telemetry"]
categories: ["browsers", "releases", "security", "privacy"]
author: Boni García ([@boni_gg](https://twitter.com/boni_gg))
description: >
   Telemetry from Selenium Manager revealed an unusual pattern: over 28 million users are locked to Chrome version 127.0.6533.99. We explore this anomaly and its possible links to security or privacy.
---

Over the past two years, Selenium has included [Selenium Manager](https://www.selenium.dev/documentation/selenium_manager/), a CLI tool (written in Rust) that provides **automatic management of drivers and browsers** across all official language bindings (Java, JavaScript, Python, .NET, and Ruby). Its purpose is to simplify the developer experience: if you create a driver object like this:

```java
WebDriver driver = new ChromeDriver();
```

Selenium Manager takes care of detecting whether Chrome is installed, downloading the required driver, and even provisioning a copy of [Chrome for Testing (CfT)](https://googlechromelabs.github.io/chrome-for-testing/) if Chrome is not present on the system. This also works for Firefox and Edge, on Windows, Linux, and macOS.

Beyond driver and browser setup, Selenium Manager also implements **telemetry**. Once per day and user, it sends anonymous usage data to a [public Plausible dashboard](https://plausible.io/manager.selenium.dev). The purpose is to help project maintainers understand how Selenium is being used. While telemetry has generated some debate in the community, the dataset occasionally reveals interesting patterns.

### An Unexpected Browser Version

When reviewing the telemetry recently, one statistic stood out. Most users (70.1% of the total users at the time of this writing) don't specify a browser version explicitly, which is expected. But surprisingly, **28.8% of the total users — over 28 million unique clients — report running the exact version 127.0.6533.99 of Chrome**. These numbers are growing daily, since if we filter the results to the last 28 days, we discover the number of users using this version is over 40% of the total.

Importantly, specific browser versions are only gathered by Selenium Manager when the user explicitly requests a given version with the Selenium API as follows (the example below is Java, but this can also be done with the rest of Selenium bindings). Alternatively, a browser version can be specified with an environment variable (`SE_BROWSER_VERSION=127.0.6533.99`). In any case (the Selenium API or the environment variable), users have explicitly selected this version.

```java
ChromeOptions options = new ChromeOptions();
options.setBrowserVersion("127.0.6533.99");
WebDriver driver = new ChromeDriver(options);
```

At first, this looked like a bug. Perhaps this version may be hardcoded somewhere in the Selenium code by mistake. So we checked the Selenium repository (a monorepo covering all bindings and Selenium Manager itself) to see if that version was present somewhere. It wasn't. Which means an increasingly large number of users are explicitly pinning this version to be downloaded automatically with Selenium Manager and used with Selenium.

Looking it up, 127.0.6533.99 corresponds to a Chrome release from August 2024 ([release notes](https://chromereleases.googleblog.com/2024/08/stable-channel-update-for-desktop.html)). That version patched several vulnerabilities, including the critical **CVE-2024-7532** (although the Chromium issue tracker entry for that CVE is [no longer publicly available](https://issues.chromium.org/issues/350528343)).


### Why Chrome 127.0.6533.99?

[Digging further into the telemetry](https://plausible.io/manager.selenium.dev?f=is,props:browser_version,127.0.6533.99), we saw that the majority of these requests come from a few countries:

- Russian Federation (4.7M)
- Iran (3.2M)
- Pakistan (1.7M)
- India (1.6M)
- Vietnam (1.4M)
- Ukraine (1M)
- Belarus (884k)

This raises the question: **why would so many users in these regions be tied to exactly this version of Chrome?** We don't have a definitive answer. Some possibilities include:

- Perhaps Chrome 127.0.6533.99 is considered a **"safe" baseline** because it patched critical vulnerabilities.
- Or conversely, it may be the **last version with an exploitable weakness**, making it useful for malicious automation.
- It could also be distributed widely in enterprise or academic environments in certain countries, leading to a statistical cluster.

Whatever the reason, it's unusual for a single version to hold such a large share of Chrome usage, especially a version over a year old.

### Why Share This?

At Selenium, we believe in transparency. Telemetry exists not only to help maintainers but also to inform the community. Security researchers, privacy advocates, and automation engineers may all find this anomaly interesting. If you have insights, we'd love to hear from you in the [Selenium Slack channel](https://inviter.co/seleniumhq).