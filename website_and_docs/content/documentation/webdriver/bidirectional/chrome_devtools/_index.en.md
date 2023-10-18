---
title: "Chrome DevTools"
linkTitle: "Chrome DevTools"
weight: 2
aliases: [
"/documentation/en/support_packages/chrome_devtools/",
"/documentation/support_packages/chrome_devtools/"
]
---

Many browsers provide "DevTools" -- a set of tools that are integrated with the browser that 
developers can use to debug web apps and explore the performance of their pages. Google Chrome's 
DevTools make use of a protocol called the Chrome DevTools Protocol (or "CDP" for short). 
As the name suggests, this is not designed for testing, nor to have a stable API, so functionality 
is highly dependent on the version of the browser.

The [WebDriver BiDirectional Protocol](https://w3c.github.io/webdriver-bidi/) is the next generation of the 
W3C WebDriver protocol and aims to provide a stable API implemented by all browsers, but it's not yet complete. 
Until it is, Selenium provides access to 
the CDP for those browsers that implement it (such as Google Chrome, or Microsoft Edge, and 
Firefox), allowing you to enhance your tests in interesting ways. Some examples of what you can 
do with it are given below.

### Ways to Use Chrome DevTools With Selenium
There are three different ways to access Chrome DevTools in Selenium. If you look for other examples online,
you will likely see each of these mixed and matched.

* The [CDP Endpoint]({{< ref "cdp_endpoint.md" >}}) was the first option available to users.
It only works for the most simple things (setting state, getting basic information), and you
have to know the "magic strings" for the domain and methods and key value pairs. 
For basic requirements, this might be simpler than the other options. These methods are only temporarily supported.
* The [CDP API]({{< ref "cdp_api.md" >}}) is an improvement on just using the endpoint because you can set
do things asynchronously. Instead of a String and a Map, you can access the supported classes, 
methods and parameters in the code. These methods are also only temporarily supported.
* The [BiDi API]({{< ref "bidi_api.md" >}}) option should be used whenever possible because it
abstracts away the implementation details entirely and will work with either CDP or WebDriver-BiDi
when Selenium moves away from CDP.

### Examples With Limited Value

There are a number of commonly cited examples for using CDP that are of limited practical value.
* **Geo Location** — almost all sites use the IP address to determine physical location, 
so setting an emulated geolocation rarely has the desired effect.
* **Overriding Device Metrics** — Chrome provides a great API for setting [Mobile Emulation](https://chromedriver.chromium.org/mobile-emulation)
in the Options classes, which is generally superior to attempting to do this with CDP.

Check out the examples in these documents for ways to do additional useful things:
