---
title: "Gmail, email and Facebook logins"
menuTitle: "Gmail, email and Facebook"
weight: 4
---

For multiple reasons, logging into sites like Gmail and Facebook
using WebDriver is not recommended.
Aside from being against the usage terms for these sites
(where you risk having the account shut down),
it is slow and unreliable.

The ideal practice is to use the APIs that email providers offer,
or in the case of Facebook the developer tools service
which exposes an API for creating test accounts, friends and so forth.
Although using an API might seem like a bit of extra hard work,
you will be paid back in speed, reliability, and stability.
The API is also unlikely to change,
whereas webpages and HTML locators change often
and require you to update your test framework.

Logging in to third party sites using WebDriver
at any point of your test increases the risk
of your test failing because it makes your test longer.
A general rule of thumb is that longer tests
are more fragile and unreliable.

WebDriver implementations that are
[W3C conformant](//w3c.github.io/webdriver/webdriver-spec.html)
also annotate the `navigator` object
with a `webdriver` property
so that Denial of Service attacks can be mitigated.