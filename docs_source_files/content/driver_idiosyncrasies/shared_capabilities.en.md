---
title: "Shared capabilities"
weight: 1
---

In-order to create a new session by Selenium WebDriver, 
local end should provide the basic capabilities to remote end. 
The remote end uses the same set of capabilities to 
create a session and describes the current session features. 

WebDriver provides capabilities that each remote 
end will/should support the implementation. 
Following are the capabilities that WebDriver supports:

## browserName:

This capability is used to set the `browserName` for a given session. 
If the specified browser is not installed at the 
remote end, the session creation will fail

## browserVersion: 

This capability is optional, this is used to 
set the available browser version at remote end. 
For Example, if ask for Chrome version 75 on a system that 
only has 80 installed, the session creation will fail

## pageLoadStrategy:

When navigating to a new page via URL, by default Selenium will wait
until the page has fully loaded before responding. This works well for
beginners, but can cause long wait times on pages that load a large
number of third party resources. Using a non default strategy can make
test execution faster in cases like this, but can also introduce flakiness
where elements on the page change position as elements load in and change
size.

The page load strategy queries the
[document.readyState](//developer.mozilla.org/en-US/docs/Web/API/Document/readyState)
as described in the table below:

| Strategy | Ready State | Notes |
| -------- | ----------- | ----- |
| normal | complete | Used by default, waits for all resources to download |
| eager | interactive | DOM access is ready, but other resources like images may still be loading |
| none | Any | Does not block WebDriver at all |

## platformName

This identifies the operating system at the remote-end, 
fetching the `platformName` returns the OS name. 

In cloud-based providers, 
setting `platformName` sets the OS at the remote-end.

## acceptInsecureCerts

This capability checks whether an expired (or) 
invalid `TLS Certificate` is used while navigating 
during a session.

If the capability is set to `false`, an 
[insecure certificate error](//developer.mozilla.org/en/docs/Web/WebDriver/Errors/InsecureCertificate) 
will be returned as navigation encounters any domain 
certificate problems. If set to `true`, invalid certificate will be 
trusted by the browser.

All self-signed certificates will be trusted by this capability by default. 
Once set, `acceptInsecureCerts` capability will have an 
effect for the entire session.
