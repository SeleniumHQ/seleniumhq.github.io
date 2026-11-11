---
# TRANSLATION PENDING: Pendente de tradução. Esta página é um placeholder com o conteúdo original em inglês; remova este comentário e o aviso abaixo após traduzir.
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

> **Esta página ainda não foi traduzida para português**
>
> O conteúdo abaixo está em inglês. Consulte o [guia de contribuição](/pt-br/documentation/about/contributing/) para ajudar na tradução.

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

