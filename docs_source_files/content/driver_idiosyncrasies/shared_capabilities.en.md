---
title: "Shared capabilities"
weight: 1
---

In order to create a new session by Selenium WebDriver, 
the local end should provide the basic capabilities to the remote end. 
The remote end uses the same set of capabilities to 
create a session and describes the current session features. 

WebDriver provides capabilities that each remote 
end will/should support the implementation. 
The following capabilities are supported by WebDriver:

## browserName:

This capability is used to set the `browserName` for a given session. 
If the specified browser is not installed at the 
remote end, the session creation will fail.

## browserVersion: 

This capability is optional, this is used to 
set the available browser version at remote end. 
For Example, if ask for Chrome version 75 on a system that 
only has 80 installed, the session creation will fail.

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
[insecure certificate error](//developer.mozilla.org/en-US/docs/Web/WebDriver/Errors/InsecureCertificate) 
will be returned as navigation encounters any domain 
certificate problems. If set to `true`, invalid certificate will be 
trusted by the browser.

All self-signed certificates will be trusted by this capability by default. 
Once set, `acceptInsecureCerts` capability will have an 
effect for the entire session.

## Session timeouts

A WebDriver `session` is imposed with a certain `session timeout`
interval, during which the user can control the behaviour
of executing scripts or retrieving information from the browser.

Each session timeout is configured with
combination of different `timeouts` as described below:

### Script Timeout:
Specifies when to interrupt an executing script in
a current browsing context. The default timeout **30,000**
is imposed when a new session is created by WebDriver.

### Page Load Timeout:
Specifies the time interval in which web page
needs to be loaded in a current browsing context.
The default timeout **300,000** is imposed when a
new session is created by WebDriver. If page load limits
a given/default time frame, the script will be stopped by
_TimeoutException_.

### Implicit Wait Timeout
This specifies the time to wait for the
implicit element location strategy when
locating elements. The default timeout **0**
is imposed when a new session is created by WebDriver.

## unhandledPromptBehavior

Specifies the state of current session's `user prompt handler`. 
Defaults to **dismiss and notify state**

### User Prompt Handler

This defines what action must take when a 
user prompt encounters at the remote-end. This is defined by 
`unhandledPromptBehavior` capability and has the following states:

* dismiss
* accept
* dismiss and notify
* accept and notify
* ignore

## setWindowRect

This command alters the size and position of the current 
browsing context window. This command acts as setter 
to `getWindowRect` command which accepts **width**, **height**,
**x**, **y** as _optional_ arguments.

During automation, the current browsing context will be associated 
with window states, which describe the visibility 
of the browser window. The window states are

* maximized
* minimized
* normal
* fullscreen

Setting _Width_ or _Height_ does not guaranteed that the resulting 
window size will exactly match that which was requested. This is because 
some drivers may not be able to resize in single-pixel increments.
Due to this, fetching the window state/details by `getWindowRect` 
may not match the values set in the browser.

## strictFileInteractability

This new capability indicates if strict interactability checks 
should be applied to _input type=file_ elements. As strict interactability 
checks are off by default, there is a change in behaviour 
when using _Element Send Keys_ with hidden file upload controls.
