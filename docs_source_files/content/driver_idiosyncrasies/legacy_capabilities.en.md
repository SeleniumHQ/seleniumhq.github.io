---
title: "Legacy capabilities"
weight: 1
---
 
In-order to create a new browser session, Selenium WebDriver 
uses a set of properties in a JSON format describing 
the features that the user requests for that particular session. 
We usually call them as `desiredCapabilites` or `requiredCapabilities`.

### Deprecation of Desired Capabilities

Majority of the selenium client uses `desiredCapabilities` and 
`requiredCapabilities` to configure a new session. Most of the 
driver vendors support use of this legacy capabilities, but in favour of
browser specific option classes **desiredCapabilities** and 
**requiredCapabilities** are deprecated and should be avoided.

One should use the **browser specific capabilities** to set 
capability for a browser session.