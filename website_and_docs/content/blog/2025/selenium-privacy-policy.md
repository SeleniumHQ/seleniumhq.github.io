---
title: "Selenium Privacy Policy"
linkTitle: "Selenium Privacy Policy"
date: 2025-02-19
tags: [ "selenium", "manager", "privacy", "telemetry" ]
categories: [ "Privacy" ]
author: Titus Fortner [@titusfortner](https://titusfortner.com)
description: >
  Selenium has introduced a Privacy Policy to clarify our data collection practices, why telemetry exists, and how we ensure compliance with GDPR and CCPA.
---

Selenium has always been committed to open-source transparency and user privacy, but we recognize that
we could have done a better job communicating the details of Selenium Manager's telemetry collection.

To address this, we have published a comprehensive [Privacy Policy](/privacy) that
clearly explains:  
* What data is collected (and what isn’t)  
* How we ensure compliance with GDPR and CCPA  
* Why Selenium Manager includes opt-out telemetry  
* How users can easily disable telemetry

The bottom line is that Selenium Manager collects only anonymous technical information to help us improve Selenium.

Additionally, starting in Selenium 4.30, Selenium Manager displays a one-time console message the first time 
telemetry is sent. This obviously and proactively informs users about telemetry, their right to opt out, 
and provides a direct link to the Privacy Policy.

**Quick Recap of what Selenium Manager collects:**

✔ Selenium version (to ensure compatibility across versions)  
✔ Programming language (Java, Python, JavaScript, .NET)  
✔ Operating system and CPU architecture (to prioritize OS support)  
✔ Browser and version (to understand browser usage trends)  
✔ Approximate geolocation (city-level), temporarily inferred from the IP (IP is discarded after processing)

❌ No usernames, emails, or personal identifiers  
❌ No browser history or test execution data  
❌ No full IP addresses (only used temporarily for location inference, then discarded)  
❌ No cookies or persistent tracking

See everything for yourself: [Selenium Manager Public Analytics](https://plausible.io/manager.selenium.dev)


## Opting Out of Telemetry

Like many other open-source projects, we use opt-out telemetry to ensure we receive representative, anonymous data that
helps improve Selenium. We understand that some people prefer opt-in telemetry,
and we explain our reasoning in detail in the [Privacy Policy](https://www.selenium.dev/privacy/#opt-out)

Regardless, it is quite easy to disable telemetry by either:

1. Setting an environment variable

```sh
SE_AVOID_STATS=true
```

2. Using a configuration file

```ini
avoid-stats = true
```

Please see the [Selenium Manager documentation](https://www.selenium.dev/documentation/selenium_manager/) for further
details on how to do this.

## Insights

**Interesting tidbits we’ve learned from having opt-out telemetry over the past year:**

* Selenium gets over 1 million unique users a week
* The number of unique users is growing at a rate of 10% per month
* Over 80% of sessions are on Windows
* Almost 90% of sessions use Chrome
* Fewer than 1% of sessions are with Safari or Internet Explorer
* A lot more sessions are with C# and Python than Java, and very few people are using Ruby and JavaScript

<em>Note 1: The data only represents users who have updated to Selenium 4.19 or greater</em><br />
<em>Note 2: A unique user represents a unique session from a given IP address for that day</em><br />
