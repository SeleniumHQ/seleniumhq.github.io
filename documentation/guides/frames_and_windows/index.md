# Frames and windows

> Reaching elements inside an iframe, following a link that opens a new tab or window, and getting back to where you started.

URL: https://www.selenium.dev/documentation/guides/frames_and_windows/
Site index: https://www.selenium.dev/llms.txt

---

The element is plainly visible on the page, the locator is correct, and `findElement` still
reports that it does not exist. The element is inside an `<iframe>`, or on a tab the link just
opened, and WebDriver is still looking at the document it started in.

## Recommended approach

WebDriver always has exactly one _current browsing context_: one window or tab, and within it
one frame. Commands only see that context. Switching is explicit, and you are responsible for
switching back.

**Frames.** Locate the `<iframe>` element like any other element and switch to it. You can also
switch by the frame's `name` or `id`, or by index, but a located element is the least
ambiguous. When you are done, switch to the default content to return to the top-level
document. A frame reference goes stale when the page reloads, so switch again after navigation.

**Windows and tabs.** Each window or tab has a _handle_, an opaque string. Record the current
handle before clicking a link that opens a new one, wait until the number of handles grows,
switch to the handle you have not seen before, and switch back to the original handle when
you are finished. WebDriver makes no distinction between a tab and a window. If you just need
a clean tab, open one yourself with `newWindow`; it is created already switched to.

Closing the current window leaves the session pointing at nothing, so switch to another
handle immediately after `close`. Call `quit`, not `close`, at the end of the test; it closes
every window and ends the session.

Switch into a frame using a located element:

**Java**

```java
WebElement iframe = driver.findElement(By.id("iframe1"));
//Switch to the frame
driver.switchTo().frame(iframe);
```

**Python**

```python
iframe = driver.find_element(By.ID, "iframe1")
driver.switch_to.frame(iframe)
```

**CSharp**

```csharp
IWebElement iframe = driver.FindElement(By.Id("iframe1"));
//Switch to the frame
driver.SwitchTo().Frame(iframe);
```

**Ruby**

```ruby
iframe = driver.find_element(:id, 'iframe1')
driver.switch_to.frame(iframe)
```

Return to the top-level document:

**Java**

```java
driver.switchTo().defaultContent();
```

**Python**

```python
driver.switch_to.default_content()
```

**CSharp**

```csharp
driver.SwitchTo().DefaultContent();
```

**Ruby**

```ruby
driver.switch_to.default_content
```

Follow a link into a new window and switch to it:

**Java**

```java
//click on link to open a new window
driver.findElement(By.linkText("Open new window")).click();
//fetch handles of all windows, there will be two, [0]- default, [1] - new window
Object[] windowHandles=driver.getWindowHandles().toArray();
driver.switchTo().window((String) windowHandles[1]);
//assert on title of new window
String title=driver.getTitle();
assertEquals("Simple Page",title);
```

**Python**

```python
original_window_handle = driver.current_window_handle

driver.find_element(By.LINK_TEXT, "Open new window").click()
wait.until(EC.number_of_windows_to_be(2))

new_window_handle = (set(driver.window_handles) - {original_window_handle}).pop()
driver.switch_to.window(new_window_handle)
```

**CSharp**

```csharp
//click on link to open a new window
driver.FindElement(By.LinkText("Open new window")).Click();
//fetch handles of all windows, there will be two, [0]- default, [1] - new window
IList<string> windowHandles = new List<string>(driver.WindowHandles);
driver.SwitchTo().Window(windowHandles[1]);
//assert on title of new window
String title = driver.Title;
Assert.AreEqual("Simple Page", title);
```

Open a fresh tab and switch to it in one step:

**Ruby**

```ruby
driver.switch_to.new_window(:tab)
```

**JavaScript**

```javascript
await driver.switchTo().newWindow('tab');
```

## Common errors

* [NoSuchElementException](https://www.selenium.dev/documentation/webdriver/troubleshooting/errors/index.md#nosuchelementexception):
  the element exists but in a different frame or window from the current one.
* [NoSuchFrameException](https://www.selenium.dev/documentation/webdriver/troubleshooting/errors/index.md#nosuchframeexception):
  the frame name, id or index did not match, or the frame was removed by a navigation.
* [NoSuchWindowException](https://www.selenium.dev/documentation/webdriver/troubleshooting/errors/index.md#nosuchwindowexception):
  the window you were in was closed. Switch to a handle that still exists.
* [StaleElementReferenceException](https://www.selenium.dev/documentation/webdriver/troubleshooting/errors/index.md#staleelementreferenceexception):
  an element from the old frame or page is being used after a switch or reload.

## Reference

* [Working with iframes and frames](https://www.selenium.dev/documentation/webdriver/interactions/frames/index.md)
* [Working with windows and tabs](https://www.selenium.dev/documentation/webdriver/interactions/windows/index.md)
* [BiDi browsing context](https://www.selenium.dev/documentation/webdriver/bidi/w3c/browsing_context/index.md): creating, inspecting and closing contexts with events
