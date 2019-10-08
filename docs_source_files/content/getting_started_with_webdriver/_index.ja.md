---
title: "Getting started with WebDriver"
chapter: true
weight: 4
---

{{% notice info %}}
<i class="fas fa-language"></i> ページは英語から日本語へ訳されています。
日本語は話せますか？プルリクエストをして翻訳を手伝ってください!
{{% /notice %}}

# Getting started with WebDriver

Selenium supports automation of all the major browsers in the market
through the use of _WebDriver_.
WebDriver is an API and protocol that defines a language-neutral interface
for controlling the behaviour of web browsers.
Each browser is backed by a specific WebDriver implementation, called a *driver*.
The driver is the component responsible for delegating down to the browser,
and handles communication to and from Selenium and the browser.

This separation is part of a conscious effort to have browser vendors
take responsibility for the implementation for their browsers.
Selenium makes use of these third party drivers where possible,
but also provides its own drivers maintained by the project
for the cases when this is not a reality.

The Selenium framework ties all of these pieces together
through a user-facing interface that enables the different browser backends
to be used transparently,
enabling cross-browser and cross-platform automation.

More details about drivers can be found in
[Driver Idiosyncrasies]({{< ref "/driver_idiosyncrasies/_index.md" >}}).
