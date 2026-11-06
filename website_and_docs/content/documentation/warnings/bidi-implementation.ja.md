---
# TRANSLATION PENDING: 未翻訳。本文は英語原文のプレースホルダーです。翻訳完了後にこのコメントと冒頭の注意書きを削除してください。
title: "BiDi implementation API is internal"
linkTitle: "BiDi implementation API"
weight: 1
aliases:
        [
          "/documentation/warnings/SELENIUM001/"
        ]
description: >
    Why Selenium's low-level BiDi implementation classes are marked internal,
    and what to use instead.
---

> **このページはまだ日本語に翻訳されていません**
>
> 以下は英語の原文です。[コントリビューションガイド](/ja/documentation/about/contributing/) を参照して、翻訳にご協力ください。

If your IDE strikes through a Selenium BiDi class or method, shows an "Internal API"
tooltip, or (in .NET) emits a compiler warning, you've reached one of Selenium's
**internal BiDi implementation APIs**.

These are the low-level classes that map directly onto the BiDi (and CDP) wire protocol,
under namespaces like `org.openqa.selenium.bidi.*` (Java), `OpenQA.Selenium.BiDi.*`
(.NET), `selenium.webdriver.common.bidi.*` (Python), and `Selenium::WebDriver::BiDi::*`
(Ruby). They are public only because the higher-level features need them.

## Why they are marked internal

The goal is for the high-level APIs to cover everything you need, so you never have to
reach for these low-level classes directly. They are marked internal to steer you toward
those high-level APIs — but they remain available as an escape hatch if you have a use
case the high-level APIs don't yet cover.

## What to use instead

Use the high-level BiDi APIs:
* [network](/documentation/webdriver/bidi/network/)
* [script](/documentation/webdriver/bidi/script/)

If your use case is only possible through the internal classes, please
[open an issue](https://github.com/SeleniumHQ/selenium/issues) so the high-level API can cover it.

