---
title: "Migrating from Selenium 3"
linkTitle: "Migrating from Selenium 3"
weight: 13
description: >
  What changes when a Selenium 3 suite moves to Selenium 4, and where to find the step-by-step
  upgrade guide.
---

A suite written against Selenium 3 still compiles, or still installs, but sessions fail to
start, deprecation warnings fill the log, and the driver you used to download by hand no
longer matches the browser. The fix is to upgrade, and for the officially supported
languages the upgrade is usually painless.

## Recommended approach

Follow the [upgrade guide]({{< ref "/documentation/webdriver/troubleshooting/upgrade_to_selenium_4.md" >}}).
It walks through preparing the test code, updating the dependency for each binding, and the
deprecation messages you may see afterwards. The changes it covers fall into a few groups.

**Capabilities.** Selenium 4 removed the legacy JSON Wire Protocol and speaks only the W3C
WebDriver standard. Capabilities must be W3C compliant: the standard ones (`browserName`,
`browserVersion`, `platformName`, `acceptInsecureCerts`, `pageLoadStrategy`, `proxy`,
`timeouts`, `unhandledPromptBehavior`) are allowed as they are, and anything else, including
browser-specific and cloud-vendor options, needs a vendor prefix. Desired Capabilities are
gone; build the session from the browser's Options class instead.

**Finding elements in Java.** The `findElementBy*` and `findElementsBy*` convenience methods
were removed. Use `findElement(By.id(...))` and the other `By` locators.

**Deprecations by binding.** In Java, timeouts take a `Duration` rather than a number and a
unit, `merge` on capabilities returns a new object instead of mutating the caller, the legacy
Firefox driver option is deprecated, and `BrowserType` gives way to `Browser`. In C#,
`AddAdditionalCapability` is replaced by `AddAdditionalOption`. In Python, the driver's
`executable_path` argument is deprecated in favour of a `Service` object, or no path at all.

**Drivers.** Once on a recent Selenium 4 release you no longer need to download drivers or
use a third-party driver manager: Selenium Manager resolves and installs the matching driver
automatically. Remove any manual driver management from the suite once the upgrade is done.

Older material on the web, and the legacy section of this documentation, describes Selenium 3
ways of working. It is kept for historical reference and should not be used as a basis for
new code.

## Common errors

* [SessionNotCreatedException]({{< ref "/documentation/webdriver/troubleshooting/errors/_index.md#sessionnotcreatedexception" >}}):
  capabilities that are not W3C compliant, or a driver that does not match the browser.
* [InvalidArgumentException]({{< ref "/documentation/webdriver/troubleshooting/errors/_index.md#invalidargumentexception" >}}):
  a non-standard capability sent without a vendor prefix.
* [Unable to locate driver]({{< ref "/documentation/webdriver/troubleshooting/errors/driver_location.md" >}}):
  the manually managed driver is gone or stale; let Selenium Manager handle it.

## Reference

* [Upgrade to Selenium 4]({{< ref "/documentation/webdriver/troubleshooting/upgrade_to_selenium_4.md" >}}): the full guide
* [Browser options]({{< ref "/documentation/webdriver/drivers/options.md" >}}): the W3C capabilities and the per-browser Options classes
* [Selenium Manager]({{< ref "/documentation/selenium_manager.md" >}})
* [Legacy documentation]({{< ref "/documentation/legacy/_index.md" >}}): Selenium 2 and 3 material, for reference only
