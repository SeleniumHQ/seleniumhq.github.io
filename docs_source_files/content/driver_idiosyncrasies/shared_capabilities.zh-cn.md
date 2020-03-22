---
title: "共享的功能"
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
 
## 页面加载策略
通过URL导航到新页面时，默认情况下，Selenium将等待页面完全加载后再进行响应。这对于初学者来说效果很好，但是在加载大量第三方资源的页面上可能会导致较长的等待时间。在这种情况下，使用非默认策略可以使测试的执行速度更快，但是也可能导致不稳定，即页面上的元素随元素加载和大小变化而改变位置.

页面加载策略可以参考链接
[document.readyState](https://developer.mozilla.org/zh-CN/docs/Web/API/Document/readyState)
如下述表格的描述:

| 策略 | 准备完成的状态 | 备注 |
| -------- | ----------- | ----- |
| normal | complete | 默认情况下使用，等待所有资源下载完成 |
| eager | interactive | DOM访问已准备就绪，但其他资源（如图像）可能仍在加载中 |
| none | Any | 完全不阻塞WebDriver|

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
[insecure certificate error](//developer.mozilla.org/de/docs/Web/WebDriver/Errors/InsecureCertificate) 
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
user prompt encounters at remote-end. This is defined by 
`unhandledPromptBehavior` capability and has the following states:

* dismiss
* accept
* dismiss and notify
* accept and notify
* ignore