---
title: "Actions API"
linkTitle: "Actions API"
needsTranslation: true
weight: 14
description: >
    A low-level interface for providing virtualised device input to the web browser.
---

Unlike the high-level [element interactions]({{< ref "/documentation/webdriver/elements/interactions.md" >}}),
which conducts additional validations,
the [Actions API](https://w3c.github.io/webdriver/#dfn-actions) provides granular control over input devices.

Selenium provides access to 3 input sources: key inputs for keyboard devices, pointer inputs for a mouse, pen or
touch device, and a wheel inputs for scroll wheel support.
