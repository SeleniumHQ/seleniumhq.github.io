---
title: "理解常见的异常"
linkTitle: "异常"
weight: 2
description: >
  如何处理Selenium代码中的各种问题.
---

The [W3C WebDriver specification](https://w3c.github.io/webdriver/#errors) defines the errors a driver can return.
Each language binding turns them into its own exception (or error) class, and the names are not always the same.
Kotlin uses the Java classes, the Ruby classes are in `Selenium::WebDriver::Error`, and the JavaScript classes
are in the `selenium-webdriver/lib/error` module.

| W3C error | Java | Python | CSharp | Ruby | JavaScript |
|-----------|------|--------|--------|------|------------|
| [detached shadow root](#detachedshadowrootexception) | `DetachedShadowRootException` | `DetachedShadowRootException` | `DetachedShadowRootException` | `DetachedShadowRootError` | `DetachedShadowRootError` |
| [element click intercepted](#elementclickinterceptedexception) | `ElementClickInterceptedException` | `ElementClickInterceptedException` | `ElementClickInterceptedException` | `ElementClickInterceptedError` | `ElementClickInterceptedError` |
| [element not interactable](#elementnotinteractableexception) | `ElementNotInteractableException` | `ElementNotInteractableException` | `ElementNotInteractableException` | `ElementNotInteractableError` | `ElementNotInteractableError` |
| [insecure certificate](#insecurecertificateexception) | `InsecureCertificateException` | `InsecureCertificateException` | `InsecureCertificateException` | `InsecureCertificateError` | `InsecureCertificateError` |
| [invalid argument](#invalidargumentexception) | `InvalidArgumentException` | `InvalidArgumentException` | `WebDriverArgumentException` | `InvalidArgumentError` | `InvalidArgumentError` |
| [invalid cookie domain](#invalidcookiedomainexception) | `InvalidCookieDomainException` | `InvalidCookieDomainException` | `InvalidCookieDomainException` | `InvalidCookieDomainError` | `InvalidCookieDomainError` |
| [invalid element state](#invalidelementstateexception) | `InvalidElementStateException` | `InvalidElementStateException` | `InvalidElementStateException` | `InvalidElementStateError` | `InvalidElementStateError` |
| [invalid selector](#invalidselectorexception) | `InvalidSelectorException` | `InvalidSelectorException` | `InvalidSelectorException` | `InvalidSelectorError` | `InvalidSelectorError` |
| [invalid session id](#invalidsessionidexception) | `NoSuchSessionException` | `InvalidSessionIdException` | `WebDriverException` | `InvalidSessionIdError` | `NoSuchSessionError` |
| [javascript error](#javascriptexception) | `JavascriptException` | `JavascriptException` | `JavaScriptException` | `JavascriptError` | `JavascriptError` |
| [move target out of bounds](#movetargetoutofboundsexception) | `MoveTargetOutOfBoundsException` | `MoveTargetOutOfBoundsException` | `MoveTargetOutOfBoundsException` | `MoveTargetOutOfBoundsError` | `MoveTargetOutOfBoundsError` |
| [no such alert](#noalertpresentexception) | `NoAlertPresentException` | `NoAlertPresentException` | `NoAlertPresentException` | `NoSuchAlertError` | `NoSuchAlertError` |
| [no such cookie](#nosuchcookieexception) | `NoSuchCookieException` | `NoSuchCookieException` | `NoSuchCookieException` | `NoSuchCookieError` | `NoSuchCookieError` |
| [no such element](#nosuchelementexception) | `NoSuchElementException` | `NoSuchElementException` | `NoSuchElementException` | `NoSuchElementError` | `NoSuchElementError` |
| [no such frame](#nosuchframeexception) | `NoSuchFrameException` | `NoSuchFrameException` | `NoSuchFrameException` | `NoSuchFrameError` | `NoSuchFrameError` |
| [no such shadow root](#nosuchshadowrootexception) | `NoSuchShadowRootException` | `NoSuchShadowRootException` | `NoSuchShadowRootException` | `NoSuchShadowRootError` | `NoSuchShadowRootError` |
| [no such window](#nosuchwindowexception) | `NoSuchWindowException` | `NoSuchWindowException` | `NoSuchWindowException` | `NoSuchWindowError` | `NoSuchWindowError` |
| [script timeout](#scripttimeoutexception) | `ScriptTimeoutException` | `TimeoutException` | `WebDriverTimeoutException` | `ScriptTimeoutError` | `ScriptTimeoutError` |
| [session not created](#sessionnotcreatedexception) | `SessionNotCreatedException` | `SessionNotCreatedException` | `InvalidOperationException` | `SessionNotCreatedError` | `SessionNotCreatedError` |
| [stale element reference](#staleelementreferenceexception) | `StaleElementReferenceException` | `StaleElementReferenceException` | `StaleElementReferenceException` | `StaleElementReferenceError` | `StaleElementReferenceError` |
| [timeout](#timeoutexception) | `TimeoutException` | `TimeoutException` | `WebDriverTimeoutException` | `TimeoutError` | `TimeoutError` |
| [unable to capture screen](#screenshotexception) | `ScreenshotException` | `ScreenshotException` | `InvalidOperationException` | `UnableToCaptureScreenError` | `UnableToCaptureScreenError` |
| [unable to set cookie](#unabletosetcookieexception) | `UnableToSetCookieException` | `UnableToSetCookieException` | `UnableToSetCookieException` | `UnableToSetCookieError` | `UnableToSetCookieError` |
| [unexpected alert open](#unhandledalertexception) | `UnhandledAlertException` | `UnexpectedAlertPresentException` | `UnhandledAlertException` | `UnexpectedAlertOpenError` | `UnexpectedAlertOpenError` |
| [unknown command](#unsupportedcommandexception) | `UnsupportedCommandException` | `WebDriverException` | `NotImplementedException` | `UnknownCommandError` | `UnknownCommandError` |
| [unknown error](#webdriverexception) | `WebDriverException` | `WebDriverException` | `UnknownErrorException` | `UnknownError` | `WebDriverError` |
| [unknown method](#unsupportedcommandexception) | `UnsupportedCommandException` | `WebDriverException` | `UnknownMethodException` | `UnknownMethodError` | `UnknownMethodError` |
| [unsupported operation](#unsupportedcommandexception) | `UnsupportedCommandException` | `WebDriverException` | `UnsupportedOperationException` | `UnsupportedOperationError` | `UnsupportedOperationError` |

All of these classes inherit from the base exception of their binding (`WebDriverException` in Java, Python and CSharp,
`WebDriverError` in Ruby and JavaScript), except for the CSharp `InvalidOperationException` and `NotImplementedException`,
which are .NET system exceptions.


## 无效选择器的异常 (InvalidSelectorException) {#invalidselectorexception}

某些时候难以获得正确的CSS以及XPath选择器。

### 潜在原因

* The CSS or XPath selector you are trying to use has invalid characters or an invalid query.
* You may have placed an XPATH value as a parameter to a CSS selector, or vice versa.
* You may have used a CSS or XPATH selector as a parameter to an ID selector.

### 可行方案

通过验证器服务运行选择器：
* [CSS 验证器](http://csslint.net/)
* [xPath 验证器](https://xmlable.com/xpath/)

或者使用浏览器扩展程序来获取已知的良好值：
* [SelectorsHub](https://selectorshub.com/selectorshub/)

## 没有这样元素的异常 (NoSuchElementException) {#nosuchelementexception}

在您尝试找到该元素的当前时刻无法定位元素。

### 潜在原因

* 您在错误的位置寻找元素 (也许以前的操作不成功)
* 您在错误的时间寻找元素 (该元素尚未显示在 DOM 中)
* 自您编写代码以来定位器已变更

### 可行方案

* 确保您位于期望的页面上，并且代码中的前置操作已正确完成
* 确保您使用的是正确的 [等待策略]({{< ref "/documentation/webdriver/waits" >}})
* Use an interactive [Selenium Wait Code Generator](https://99tools.net/selenium-wait-code-generator/) to create accurate explicit wait snippets for multiple supported languages including Java, Python, C#, JavaScript, and Ruby.
* 使用浏览器的devtools控制台更新定位器或使用浏览器扩展程序，例如:
  * [SelectorsHub](https://selectorshub.com/selectorshub/)

## 过时元素引用的异常 (StaleElementReferenceException) {#staleelementreferenceexception}

当成功定位到元素时，
WebDriver会为其设置一个引用ID作为标记，
如果由于上下文环境发生变化，
导致之前元素的位置发生了变化或者无法找到了，
WebDriver并不会自动重新定位，
任何使用之前元素所做的操作将报错该异常。

### 常见因素

以下情况可能发生此异常:

* 您已刷新页面，或者页面的 DOM 已动态更改。
* 您已导航到其他页面。
* 您已切换到另一个窗口，或者进入/移出某个 `frame` / `iframe`。

### 常见方案

**DOM已变更**

当页面刷新或页面上的项目各处移动时，
页面上仍然有一个具有所需定位器的元素，
它只是不再被正在使用的元素对象访问，
并且必须重新定位该元素才能再次使用。

这往往通过以下两种方式之一完成：

* 每次使用时都要重新定位元素。
尽管有可能元素在定位和使用元素之间的微秒内，
发生变化的可能性很小。
缺点是这不是最有效的方法，
尤其是在 `Remote Grid`上运行时。

* 用另一个存储定位器的对象包装 Web 元素，并缓存定位的 Selenium 元素。 
对该包装对象执行操作时，您可以尝试使用之前找到的缓存对象，
如果它是发生了变化，则可以捕获异常，
使用存储的定位器重新定位元素，并重试该方法。
这样效率更高，但如果您使用的定位器在页面更改后引用了不同的元素（而不是您想要的元素），则可能会导致问题。

**上下文已变更**

元素对象是针对特定的上下文存储的，
因此如果您切换到不同的上下文，
比如不同的 `Window` 或不同的 `frame` 或 `iframe` 元素引用仍然有效，
但暂时无法访问。在这种情况下，
重新定位元素无济于事，因为它在当前上下文中不存在。

要解决此问题，您需要确保在使用该元素之前切换回正确的上下文。

**页面已变更**

这种情况发生在您不仅更改了上下文，
而且导航到另一个页面并破坏了元素所在的上下文。
您无法仅从当前上下文重新定位它，
也无法切换回元素有效的活动上下文。
如果这是您的错误原因，
您必须回到正确的位置并重新定位元素。

## ElementClickInterceptedException

This exception occurs when Selenium tries to click an element, but the click would instead 
be received by a different element. Before Selenium will click an element, it checks if the 
element is visible, unobscured by any other elements, and enabled - if the element is obscured, 
it will raise this exception.

### Likely Cause

**UI Elements Overlapping** 

Elements on the UI are typically placed next to each other, but occasionally elements may overlap. 
For example, a navbar always staying at the top of your window as you scroll a page. If that navbar 
happens to be covering an element we are trying to click, Selenium might believe it to be visible 
and enabled, but when you try to click it will throw this exception. Pop-ups and Modals are also 
common offenders here.

**Animations** 

Elements with animations have the potential to cause this exception as well - it is recommended 
to wait for animations to cease before attempting to click an element.

### Possible Solutions

**Use Explicit Waits** 

[Explicit Waits]({{< ref "/documentation/webdriver/waits" >}}) will likely be your best friend 
in these instances. A great way is to use `ExpectedCondition.ToBeClickable()` 
with `WebDriverWait` to wait until the right moment.

**Scroll the Element into View** 

In instances where the element is out of view, but Selenium still registers the element as visible 
(e.g. navbars overlapping a section at the top of your screen), you can use the 
`WebDriver.executeScript()` method to execute a javascript function to scroll 
(e.g. `WebDriver.executeScript('window.scrollBy(0,-250)')`) or you can utilize the 
Actions class with `Actions.moveToElement(element)`.

## 无效SessionId异常 {#invalidsessionidexception}
有时您尝试访问的会话与当前可用的会话不同。

### 可能原因
通常发生在会话被删除时（例如：`driver.quit()`）或会话发生更改时，例如最后一个标签页/浏览器已关闭（例如：`driver.close()`）。

### 可能的解决方案
检查脚本中是否有 `driver.close()` 和 `driver.quit()` 的实例，以及其他可能导致标签页/浏览器关闭的原因。可能是您在应该/能够定位元素之前就尝试定位了该元素。

## SessionNotCreatedException

此异常发生在 WebDriver 无法为浏览器创建新会话时。通常由于版本不匹配、系统级限制或配置问题导致。

### 可能的原因

- 浏览器版本和 WebDriver 版本不兼容（例如 ChromeDriver v113 和 Chrome v115）。
- macOS 隐私设置可能会阻止 WebDriver 运行。
- WebDriver 二进制文件丢失、不可访问或没有执行权限。

### 可能的解决方案

- 确保 WebDriver 版本与浏览器版本匹配。对于 Chrome，请在浏览器中访问 `chrome://settings/help` 检查浏览器版本，并从 [ChromeDriver 下载](https://chromedriver.chromium.org/downloads)页面下载匹配的驱动程序。
- 在 macOS 上，转到 **系统设置 > 隐私与安全性**，并允许驱动程序运行（如果被阻止）。
- 验证驱动程序二进制文件是否可执行（在 Linux/macOS 上运行 `chmod +x /path/to/driver`）。

## ElementNotInteractableException

当 Selenium 尝试与当前状态下无法交互的元素进行交互时，会发生此异常。

### 可能的原因

1. **不支持的操作**：尝试对不支持操作的元素执行操作，例如对 `<form>` 或 `<label>` 使用 `sendKeys`。  
2. **多个元素匹配定位器**：定位器匹配到非可交互的元素，例如 `<td>` 标签，而不是目标的 `<input>` 字段。  
3. **隐藏的元素**：元素存在于 DOM 中，但由于 CSS、`hidden` 属性或元素超出可见视口范围而不可见。

### 可能的解决方案

1. 根据元素类型使用适当的操作（例如，仅对 `<input>` 字段使用 `sendKeys`）。  
2. 确保定位器唯一标识目标元素，以避免错误匹配。  
3. 在与元素交互之前，检查其是否在页面上可见。如果需要，将元素滚动到视图中。  
4. 使用显式等待以确保元素在执行操作前可交互。

## DetachedShadowRootException

A shadow root was found earlier, but it is no longer attached to the DOM. This is the shadow root
equivalent of a [StaleElementReferenceException](#staleelementreferenceexception).

### Likely Cause

* The page was refreshed or the user navigated to another page after the shadow root was found.
* The shadow host element was removed from the DOM or replaced by a new element.

### Possible Solutions

* Find the shadow host element again and get its shadow root before using it.
* See [Evaluating the Shadow DOM]({{< ref "/documentation/webdriver/elements/finders#evaluating-the-shadow-dom" >}})
  for how to work with shadow roots.

## InsecureCertificateException

Navigating to a page made the browser show a certificate warning, which is usually the result of an expired,
self-signed or otherwise invalid TLS certificate.

### Likely Cause

* The site under test uses a self-signed certificate, which is common in test environments.
* The certificate has expired or does not match the domain name.

### Possible Solutions

* If you trust the site, set the
  [`acceptInsecureCerts`]({{< ref "/documentation/webdriver/drivers/options#acceptinsecurecerts" >}})
  capability to `true` in the browser options.
* Otherwise, fix the certificate of the site under test.

## InvalidArgumentException

The arguments passed to a command are invalid or malformed.

### Likely Cause

* A value of the wrong type was passed to a command, e.g., a number where a string is expected.
* A URL passed to `get` is not a valid absolute URL (e.g., it is missing `https://`).
* A browser option or capability has a value the driver does not accept.
* The file passed for a [file upload]({{< ref "/documentation/webdriver/elements/file_upload" >}}) does not exist.
  In a [remote session]({{< ref "/documentation/webdriver/drivers/remote_webdriver#uploads" >}}), the file must exist
  on the machine where the browser runs, unless a Local File Detector is used, which sends the file from the machine
  running the test.

### Possible Solutions

* Read the error message, which usually says which argument is invalid.
* Check the values you pass to the failing command, and the capabilities used to start the session.

## InvalidCookieDomainException

A cookie was added for a domain that is different from the domain of the current page.

### Likely Cause

* You are adding a cookie before navigating to the site it belongs to (e.g., on `about:blank` or the browser start page).
* The cookie's `domain` value does not match the current page.
* The current page does not accept cookies, e.g., a `file://` or `data:` URL.

### Possible Solutions

* Navigate to a page of the domain the cookie belongs to before adding it.
* See [Working with cookies]({{< ref "/documentation/webdriver/interactions/cookies" >}}).

## InvalidElementStateException

A command cannot be completed because the element is in a state that does not allow it.

### Likely Cause

* You are trying to clear an element that is not editable, such as a disabled or read-only input.
* You are trying to change an element that is disabled.

### Possible Solutions

* Check that the element is enabled and editable before interacting with it.
* Wait for the application to enable the element, using an [explicit wait]({{< ref "/documentation/webdriver/waits" >}}).

## JavascriptException

The JavaScript code passed to the driver failed while running in the browser.

### Likely Cause

* The script has a syntax error or throws an error.
* The script uses a variable or element that does not exist on the current page.
* The script uses arguments that were not passed to it.

### Possible Solutions

* Read the error message, which includes the JavaScript error raised by the browser.
* Run the script in the browser's developer tools console to debug it.
* Prefer Selenium commands over JavaScript when there is a command that does the same thing.

## MoveTargetOutOfBoundsException

An [Actions]({{< ref "/documentation/webdriver/actions_api" >}}) command tried to move the pointer to a point
outside of the browser's viewport.

### Likely Cause

* The offset used in a move command puts the pointer outside of the viewport.
* The target element is outside of the viewport and cannot be scrolled into it.
* The browser window is too small for the page.

### Possible Solutions

* Check the offsets used with the mouse and pen actions. Depending on the origin of the move, offsets are relative
  to the viewport, to the current pointer position, or to the center of an element.
* Scroll the element into view first, e.g., with the [wheel actions]({{< ref "/documentation/webdriver/actions_api/wheel" >}}).
* Use a bigger browser window.

## NoAlertPresentException

A command tried to use an alert, confirm or prompt dialog, but no dialog was open.

### Likely Cause

* The dialog has not opened yet.
* The dialog was already closed, either by your code or by the
  [`unhandledPromptBehavior`]({{< ref "/documentation/webdriver/drivers/options#unhandledpromptbehavior" >}})
  of the session.
* The page opens a dialog that is built with HTML (a modal), which is not a JavaScript alert.

### Possible Solutions

* Wait for the alert to be present with an [explicit wait]({{< ref "/documentation/webdriver/waits" >}}) before switching to it.
* Interact with HTML modals as regular elements.
* See [JavaScript alerts, prompts and confirmations]({{< ref "/documentation/webdriver/interactions/alerts" >}}).

## NoSuchCookieException

No cookie with the given name exists for the current page.

### Likely Cause

* The cookie has not been set yet, it has expired, or it was deleted.
* The cookie belongs to a different domain or path than the current page.

### Possible Solutions

* Check that you are on a page of the domain and path the cookie belongs to.
* Get all cookies to check which ones are available.
* See [Working with cookies]({{< ref "/documentation/webdriver/interactions/cookies" >}}).

## NoSuchFrameException

A command to switch to a frame or iframe could not find it.

### Likely Cause

* The frame has not been loaded yet.
* The locator, index, name or ID used to switch to the frame is wrong.
* The frame is inside another frame, and the driver is not switched to the parent frame.

### Possible Solutions

* Wait for the frame to be available and switch to it, with an [explicit wait]({{< ref "/documentation/webdriver/waits" >}}).
* Switch to each parent frame first when frames are nested.
* See [Working with IFrames and frames]({{< ref "/documentation/webdriver/interactions/frames" >}}).

## NoSuchShadowRootException

An element does not have a shadow root.

### Likely Cause

* The element is not a shadow host.
* The shadow root has not been attached yet.

### Possible Solutions

* Check in the browser's developer tools that you are getting the shadow root of the shadow host element.
* Wait for the shadow root to be attached before getting it.
* See [Evaluating the Shadow DOM]({{< ref "/documentation/webdriver/elements/finders#evaluating-the-shadow-dom" >}}).

## NoSuchWindowException

The window or tab the command was sent to does not exist.

### Likely Cause

* The window or tab the driver is switched to was closed, by your code (e.g., `driver.close()`) or by the page.
* The window handle used to switch to a window is wrong.

### Possible Solutions

* After closing a window or tab, switch to another open window before sending more commands.
* Get the current window handles to check which windows are available.
* See [Working with windows and tabs]({{< ref "/documentation/webdriver/interactions/windows" >}}).

## ScreenshotException

The browser could not take a screenshot.

### Likely Cause

* The page or element is too big for the browser to capture, e.g., a full page screenshot of a very long page.

### Possible Solutions

* Take a screenshot of the viewport or of a smaller element instead.

## ScriptTimeoutException

A script passed to the driver did not finish before the script timeout expired.

### Likely Cause

* An asynchronous script did not call the callback function it receives as its last argument.
* The script takes longer than the script timeout, which is 30 seconds by default.

### Possible Solutions

* Check that asynchronous scripts call the callback function in every code path.
* Increase the [script timeout]({{< ref "/documentation/webdriver/drivers/options#script-timeout" >}}) if the script needs more time.

## TimeoutException

An operation did not finish before its timeout expired.

### Likely Cause

* An [explicit wait]({{< ref "/documentation/webdriver/waits" >}}) timed out because its condition was not met in time.
  This is the most common cause, and the exception is raised by the binding, not the driver.
* A page took longer to load than the
  [page load timeout]({{< ref "/documentation/webdriver/drivers/options#page-load-timeout" >}}), which is 300 seconds by default.

### Possible Solutions

* Check that the condition you are waiting for can be met, e.g., that the locator is correct.
* Increase the timeout of the wait, if the application needs more time.
* Use a different [page load strategy]({{< ref "/documentation/webdriver/drivers/options#pageloadstrategy" >}})
  if you do not need to wait for the whole page to load.

## UnableToSetCookieException

The browser could not set a cookie.

### Likely Cause

* The cookie has invalid values, e.g., a malformed name, or an expiry date in the past.
* The cookie is set as `secure`, and the current page does not use HTTPS.

### Possible Solutions

* Check the values of the cookie.
* Use HTTPS pages when setting `secure` cookies.
* See [Working with cookies]({{< ref "/documentation/webdriver/interactions/cookies" >}}).

## UnhandledAlertException

An alert, confirm or prompt dialog is open, and it blocks the command.

### Likely Cause

* The page opened a dialog that your code did not expect or did not close.

### Possible Solutions

* Switch to the alert and accept or dismiss it before sending more commands.
* Set the [`unhandledPromptBehavior`]({{< ref "/documentation/webdriver/drivers/options#unhandledpromptbehavior" >}})
  capability to make the driver handle unexpected dialogs automatically.
* See [JavaScript alerts, prompts and confirmations]({{< ref "/documentation/webdriver/interactions/alerts" >}}).

## UnsupportedCommandException

The driver does not support the command. Java uses this exception for the `unknown command`,
`unknown method` and `unsupported operation` W3C errors. See the table at the top of this page for the class each binding uses for each of these errors.

### Likely Cause

* The command is not part of the W3C WebDriver specification, or the driver has not implemented it yet.
* The command only works with some browsers (e.g., a browser specific command used with another browser).
* The driver is older than the Selenium version being used.

### Possible Solutions

* Check that the browser and driver support the command.
* Update the browser and driver. [Selenium Manager]({{< ref "/documentation/selenium_manager" >}}) gets the driver
  that matches your browser automatically.

## WebDriverException

An `unknown error` happened in the driver while processing the command. It is the base exception of most bindings,
so it is also raised for errors that do not have a more specific class.

### Likely Cause

* The browser crashed or was closed while the command was running.
* The driver could not connect to the browser.
* An error in the browser or driver that is not described by any other error.

### Possible Solutions

* Read the full error message, which usually has more details.
* Update the browser and driver to the latest versions.
* Enable [logging]({{< ref "/documentation/webdriver/troubleshooting/logging" >}}) to get more information.

## ElementNotVisibleException

{{% alert title="Legacy" color="warning" %}}
This exception is not part of the W3C WebDriver specification, and current drivers do not return it.
When an element cannot be interacted with because it is not visible, drivers return an
[ElementNotInteractableException](#elementnotinteractableexception) instead.
Only the Python binding still defines `ElementNotVisibleException`, for backward compatibility.
{{% /alert %}}


This exception is thrown when the element you are trying to interact with _is_ present in the DOM, but is not visible. 

### Likely Cause

This can occur in several situations:
* Another element is blocking your intended element
* The element is disabled/invisible to the user

### Possible Solutions

This issue cannot always be resolved on the user's end, however when it can it is usually solved by the following: 
using an explicit wait, or interacting with the page in such a way to make the element visible 
(scrolling, clicking a button, etc.)
