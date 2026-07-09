---
title: ".NET Assemblies Are Now Strongly Signed"
linkTitle: ".NET Assemblies Are Now Strongly Signed"
date: 2026-04-27
tags: ["selenium", "dotnet"]
categories: ["general"]
author: Nikolay Borisenko [@nvborisenko](https://github.com/nvborisenko)
description: >
  Starting with Selenium 4.44, the main NuGet packages ship strongly signed assemblies. The separate StrongNamed packages are retired.
---

Starting with **Selenium 4.44**, the `Selenium.WebDriver` and `Selenium.Support` NuGet packages ship **strongly signed assemblies**. The separate `Selenium.WebDriver.StrongNamed` and `Selenium.Support.StrongNamed` packages are discontinued.

## Why This Matters

Without strong naming on the official packages, any project that itself needed to be strongly signed could not reference Selenium from NuGet. Teams were forced to either forgo strong naming, bundle out-of-band downloads, or use the separate `StrongNamed` packages with a different assembly name — all of which caused friction, especially in enterprise environments.

## What Changed

- `Selenium.WebDriver` and `Selenium.Support` are now strongly signed.
  Assembly names change: `WebDriver` → `Selenium.WebDriver`, `WebDriver.Support` → `Selenium.Support`.
- `AssemblyVersion` stays at `4.0.0.0` (major-only) — no binding redirects needed between `4.x` releases.
- `Selenium.WebDriver.StrongNamed` and `Selenium.Support.StrongNamed` are **retired**.

## Breaking Changes

If you reference the `StrongNamed` packages, migrate as follows:

1. Replace `Selenium.WebDriver.StrongNamed` → `Selenium.WebDriver`
2. Replace `Selenium.Support.StrongNamed` → `Selenium.Support`
3. Update assembly references: `WebDriver.StrongNamed` → `Selenium.WebDriver`, `WebDriver.Support.StrongNamed` → `Selenium.Support`

If you use the regular packages — the assemblies are now signed but the assembly names change (`WebDriver.dll` → `Selenium.WebDriver.dll`, `WebDriver.Support.dll` → `Selenium.Support.dll`), so update any explicit assembly references accordingly. The public API is unchanged.

## Related Issues

- [#12315](https://github.com/SeleniumHQ/selenium/issues/12315) — Publish StrongNamed builds to NuGet feed
- [#10845](https://github.com/SeleniumHQ/selenium/issues/10845) — Revisiting strong-named assemblies for NuGet
- [#14115](https://github.com/SeleniumHQ/selenium/issues/14115) — Strong Name Key

Implementation: [#17397](https://github.com/SeleniumHQ/selenium/pull/17397)
