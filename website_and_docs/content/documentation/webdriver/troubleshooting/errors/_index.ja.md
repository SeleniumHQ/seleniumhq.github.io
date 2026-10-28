---
title: "Understanding Common Errors"
linkTitle: "Errors"
weight: 2
description: >
  How to solve various problems in your Selenium code.
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


## InvalidSelectorException

CSS and XPath Selectors are sometimes difficult to get correct.

### Likely Cause

* The CSS or XPath selector you are trying to use has invalid characters or an invalid query.
* You may have placed an XPATH value as a parameter to a CSS selector, or vice versa.
* You may have used a CSS or XPATH selector as a parameter to an ID selector.

### Possible Solutions

Run your selector through a validator service:
* [CSS Validator](http://csslint.net/)
* [xPath Validator](https://xmlable.com/xpath/)

Or use a browser extension to get a known good value:
* [SelectorsHub](https://selectorshub.com/selectorshub/)

## NoSuchElementException

The element can not be found at the exact moment you attempted to locate it.

### Likely Cause

* You are looking for the element in the wrong place (perhaps a previous action was unsuccessful).
* You are looking for the element at the wrong time (the element has not shown up in the DOM, yet)
* The locator has changed since you wrote the code

### Possible Solutions

* Make sure you are on the page you expect to be on, and that previous actions in your code completed correctly
* Make sure you are using a proper [Waiting Strategy]({{< ref "/documentation/webdriver/waits" >}})
* Use an interactive [Selenium Wait Code Generator](https://99tools.net/selenium-wait-code-generator/) to create accurate explicit wait snippets for multiple supported languages including Java, Python, C#, JavaScript, and Ruby.
* Update the locator with the browser's devtools console or use a browser extension like:
  * [SelectorsHub](https://selectorshub.com/selectorshub/)

## StaleElementReferenceException 

An element goes stale when it was previously located, but can not be currently accessed.
Elements do not get relocated automatically; the driver creates a reference ID for the element and
has a particular place it expects to find it in the DOM. If it can not find the element
in the current DOM, any action using that element will result in this exception.

### Likely Cause

This can happen when:

* You have refreshed the page, or the DOM of the page has dynamically changed.
* You have navigated to a different page.
* You have switched to another window or into or out of a frame or iframe.

### Possible Solutions

**The DOM has changed**

When the page is refreshed or items on the page have moved around, there is still
an element with the desired locator on the page, it is just no longer accessible
by the element object being used, and the element must be relocated before it can be used again.
This is often done in one of two ways:

* Always relocate the element every time you go to use it. The likelihood of
the element going stale in the microseconds between locating and using the element
is small, though possible. The downside is that this is not the most efficient approach,
especially when running on a remote grid.

* Wrap the Web Element with another object that stores the locator, and caches the
located Selenium element. When taking actions with this wrapped object, you can
attempt to use the cached object if previously located, and if it is stale, exception
can be caught, the element relocated with the stored locator, and the method re-tried.
This is more efficient, but it can cause problems if the locator you're using
references a different element (and not the one you want) after the page has changed.

**The Context has changed**

Element objects are stored for a given context, so if you move to a different context —
like a different window or a different frame or iframe — the element reference will
still be valid, but will be temporarily inaccessible. In this scenario, it won't
help to relocate the element, because it doesn't exist in the current context.
To fix this, you need to make sure to switch back to the correct context before using the element. 

**The Page has changed**

This scenario is when you haven't just changed contexts, you have navigated to another page
and have destroyed the context in which the element was located. 
You can't just relocate it from the current context,
and you can't switch back to an active context where it is valid. If this is the reason
for your error, you must both navigate back to the correct location and relocate it.

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

Elements with animations have the potential to cause this exception as well - it is recommended to 
wait for animations to cease before attempting to click an element.

### Possible Solutions

**Use Explicit Waits** 

[Explicit Waits]({{< ref "/documentation/webdriver/waits" >}}) will likely be your best friend in these instances. 
A great way is to use `ExpectedCondition.ToBeClickable()` with `WebDriverWait` to wait until the right moment.

**Scroll the Element into View** 

In instances where the element is out of view, but Selenium still registers the element as visible 
(e.g. navbars overlapping a section at the top of your screen), you can use the `WebDriver.executeScript()` 
method to execute a javascript function to scroll (e.g. `WebDriver.executeScript('window.scrollBy(0,-250)')`) 
or you can utilize the Actions class with `Actions.moveToElement(element)`.

## InvalidSessionIdException

Sometimes the session you're trying to access is different than what's currently available

### Likely Cause

This usually occurs when the session has been deleted (e.g. `driver.quit()`) or if the session has changed, 
like when the last tab/browser has closed (e.g. `driver.close()`)

### Possible Solutions

Check your script for instances of `driver.close()` and `driver.quit()`, and any other possible causes of closed 
tabs/browsers. It could be that you are locating an element before you should/can.

## SessionNotCreatedException

This exception occurs when the WebDriver is unable to create a new session for the browser. This often happens due to version mismatches, system-level restrictions, or configuration issues.

### Likely Cause

- The browser version and WebDriver version are incompatible (e.g., ChromeDriver v113 with Chrome v115).
- macOS privacy settings may block the WebDriver from running.
- The WebDriver binary is missing, inaccessible, or lacks the necessary execution permissions (e.g., on Linux/macOS, the driver file may not be executable).


### Possible Solutions

- Ensure the WebDriver version matches the browser version. For Chrome, check the browser version at `chrome://settings/help` and download the matching driver from [ChromeDriver Downloads](https://chromedriver.chromium.org/downloads).
- On macOS, go to **System Settings > Privacy & Security**, and allow the driver to run if blocked.
- Verify the driver binary is executable (`chmod +x /path/to/driver` on Linux/macOS).

## ElementNotInteractableException

This exception occurs when Selenium tries to interact with an element that is not interactable in its current state.

### Likely Cause

1. **Unsupported Operation**: Performing an action, like `sendKeys`, on an element that doesn’t support it (e.g., `<form>` or `<label>`).  
2. **Multiple Elements Matching Locator**: The locator targets a non-interactable element, such as a `<td>` tag, instead of the intended `<input>` field.  
3. **Hidden Elements**: The element is present in the DOM but not visible on the page due to CSS, the `hidden` attribute, or being outside the visible viewport.

### Possible Solutions

1. Use actions appropriate for the element type (e.g., use `sendKeys` with `<input>` fields only).  
2. Ensure locators uniquely identify the intended element to avoid incorrect matches.  
3. Check if the element is visible on the page before interacting with it. Use scrolling to bring the element into view, if required.  
4. Use explicit waits to ensure the element is interactable before performing actions.

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

