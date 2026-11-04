# Browsing Context

URL: https://www.selenium.dev/ja/documentation/webdriver/bidi/w3c/browsing_context/

---

> <p class="lead">
>    <i class="fas fa-language d-4"></i>
>    Page being translated from
>    English to Japanese. Do you speak Japanese? Help us to translate
>    it by sending us pull requests!
> 

## Commands 
This section contains the APIs related to browsing context commands. 

### Open a new window

Creates a new browsing context in a new window.

**Java**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```java
BrowsingContext browsingContext = new BrowsingContext(driver, WindowType.WINDOW);
```

**CSharp**

```csharp
var bidi = await driver.AsBiDiAsync();

var context = (await bidi.BrowsingContext.CreateAsync(ContextType.Window)).Context;

Assert.IsNotNull(context);
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```javascript
const browsingContext = await BrowsingContext(driver, {
  type: 'window',
})
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Open a new tab

Creates a new browsing context in a new tab.

**Java**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```java

@Test
void testNavigateToAUrl() {
    BrowsingContext browsingContext = new BrowsingContext(driver, WindowType.TAB);
```

**CSharp**

```csharp
var bidi = await driver.AsBiDiAsync();

var context = (await bidi.BrowsingContext.CreateAsync(ContextType.Tab)).Context;

Assert.IsNotNull(context);
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```javascript
const browsingContext = await BrowsingContext(driver, {
  type: 'tab',
})
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Use existing window handle

Creates a browsing context for the existing tab/window to run commands.

**Java**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```java
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
}

@Test
void testCreateABrowsingContextForGivenId() {
```

**CSharp**

```csharp
var context = (await bidi.BrowsingContext.GetTreeAsync()).Contexts[0].Context;

Assert.IsNotNull(context);
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```javascript
const id = await driver.getWindowHandle()
const browsingContext = await BrowsingContext(driver, {
  browsingContextId: id,
})
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Open a window with a reference browsing context
A reference browsing context is a [top-level browsing context](https://html.spec.whatwg.org/multipage/document-sequences.html#top-level-browsing-context). 
The API allows to pass the reference browsing context, which is used to create a new window. The implementation is operating system specific.

**Java**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```java
    Assertions.assertNotNull(browsingContext.getId());
}

@Test
void testCreateATab() {
    BrowsingContext browsingContext = new BrowsingContext(driver, WindowType.TAB);
```

**CSharp**

```csharp
var context1 = context;

var context2 = (await context1.BiDi.BrowsingContext.CreateAsync(ContextType.Window, new() { ReferenceContext = context1 })).Context;

Assert.IsNotNull(context2);
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```javascript
const browsingContext = await BrowsingContext(driver, {
  type: 'window',
  referenceContext: await driver.getWindowHandle(),
})
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Open a tab with a reference browsing context
A reference browsing context is a [top-level browsing context](https://html.spec.whatwg.org/multipage/document-sequences.html#top-level-browsing-context). 
The API allows to pass the reference browsing context, which is used to create a new tab. The implementation is operating system specific.

**Java**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```java

    Assertions.assertNotNull(browsingContext.getId());
    Assertions.assertNotNull(info.getNavigationId());
    Assertions.assertTrue(info.getUrl().contains("/bidi/logEntryAdded.html"));
}
```

**CSharp**

```csharp
var context1 = context;

var context2 = (await context1.BiDi.BrowsingContext.CreateAsync(ContextType.Tab, new() { ReferenceContext = context1 })).Context;

Assert.IsNotNull(context2);
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```javascript
const browsingContext = await BrowsingContext(driver, {
  type: 'tab',
  referenceContext: await driver.getWindowHandle(),
})
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Navigate to a URL

**Java**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```java
    BrowsingContext browsingContext = new BrowsingContext(driver, WindowType.TAB);

    NavigationResult info = browsingContext.navigate("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html",
            ReadinessState.COMPLETE);

    Assertions.assertNotNull(browsingContext.getId());
    Assertions.assertNotNull(info.getNavigationId());
    Assertions.assertTrue(info.getUrl().contains("/bidi/logEntryAdded.html"));
}
```

**CSharp**

```csharp
var info = await context.NavigateAsync("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");

Assert.IsNotNull(info);
Assert.IsNotNull(info.Navigation);
StringAssert.Contains(info.Url, "/bidi/logEntryAdded.html");
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```javascript
let info = await browsingContext.navigate('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Navigate to a URL with readiness state

**Java**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```java
void testGetTreeWithAChild() {
    String referenceContextId = driver.getWindowHandle();
    BrowsingContext parentWindow = new BrowsingContext(driver, referenceContextId);

    parentWindow.navigate("https://www.selenium.dev/selenium/web/iframes.html", ReadinessState.COMPLETE);

    List<BrowsingContextInfo> contextInfoList = parentWindow.getTree();

    Assertions.assertEquals(1, contextInfoList.size());
    BrowsingContextInfo info = contextInfoList.get(0);
```

**CSharp**

```csharp
var info = await context.NavigateAsync("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html", new() { Wait = ReadinessState.Complete });

Assert.IsNotNull(info);
Assert.IsNotNull(info.Navigation);
StringAssert.Contains(info.Url, "/bidi/logEntryAdded.html");
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```javascript
const info = await browsingContext.navigate(
  'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html',
  'complete'
)
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Get browsing context tree

Provides a tree of all browsing contexts descending from the parent browsing context, including the parent browsing context.

**Java**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```java
    Assertions.assertTrue(info.getChildren().get(0).getUrl().contains("formPage.html"));
}

@Test
void testGetTreeWithDepth() {
    String referenceContextId = driver.getWindowHandle();
    BrowsingContext parentWindow = new BrowsingContext(driver, referenceContextId);

    parentWindow.navigate("https://www.selenium.dev/selenium/web/iframes.html", ReadinessState.COMPLETE);

    List<BrowsingContextInfo> contextInfoList = parentWindow.getTree(0);

    Assertions.assertEquals(1, contextInfoList.size());
    BrowsingContextInfo info = contextInfoList.get(0);
```

**CSharp**

```csharp
await context.NavigateAsync("https://www.selenium.dev/selenium/web/iframes.html", new() { Wait = ReadinessState.Complete });

var contexts = (await context.GetTreeAsync()).Contexts;

Assert.AreEqual(1, contexts.Length);
Assert.IsNotNull(contexts[0].Children);
Assert.IsTrue(contexts[0].Children.Value.Length >= 1, "Context should contain iframes as children");
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```javascript
const browsingContextId = await driver.getWindowHandle()
const parentWindow = await BrowsingContext(driver, {
  browsingContextId: browsingContextId,
})
await parentWindow.navigate('https://www.selenium.dev/selenium/web/iframes.html', 'complete')

const contextInfo = await parentWindow.getTree()
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Get browsing context tree with depth

Provides a tree of all browsing contexts descending from the parent browsing context, including the parent browsing context upto the depth value passed.

**Java**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```java
}

@Test
void testGetAllTopLevelContexts() {
    BrowsingContext window1 = new BrowsingContext(driver, driver.getWindowHandle());
    BrowsingContext window2 = new BrowsingContext(driver, WindowType.WINDOW);

    List<BrowsingContextInfo> contextInfoList = window1.getTopLevelContexts();

    Assertions.assertEquals(2, contextInfoList.size());
}

@Test
```

**CSharp**

```csharp
await context.NavigateAsync("https://www.selenium.dev/selenium/web/iframes.html", new() { Wait = ReadinessState.Complete });

var contexts = (await context.GetTreeAsync(new() { MaxDepth = 0 })).Contexts;

Assert.AreEqual(1, contexts.Length);
Assert.IsNull(contexts[0].Children, "Context should not contain iframes as children since depth is 0");
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```javascript
const browsingContextId = await driver.getWindowHandle()
const parentWindow = await BrowsingContext(driver, {
  browsingContextId: browsingContextId,
})
await parentWindow.navigate('https://www.selenium.dev/selenium/web/iframes.html', 'complete')

const contextInfo = await parentWindow.getTree(0)
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Get All Top level browsing contexts

**Java**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```java
    BrowsingContext window2 = new BrowsingContext(driver, WindowType.WINDOW);

    window2.close();

    Assertions.assertThrows(BiDiException.class, window2::getTree);
}

@Test
```

**CSharp**

```csharp
var window = (await bidi.BrowsingContext.CreateAsync(ContextType.Window)).Context;

var contexts = (await bidi.BrowsingContext.GetTreeAsync()).Contexts;

Assert.AreEqual(2, contexts.Length);
Assert.IsTrue(contexts.Any(c => c.Context == window));
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.20.0](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.20.0.0) or later._

```javascript
const id = await driver.getWindowHandle()
const window1 = await BrowsingContext(driver, {
  browsingContextId: id,
})
await BrowsingContext(driver, { type: 'window' })
const res = await window1.getTopLevelContexts()
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Close a tab/window

**Java**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```java
    BrowsingContext tab2 = new BrowsingContext(driver, WindowType.TAB);

    tab2.close();

    Assertions.assertThrows(BiDiException.class, tab2::getTree);
}

@Test
void testActivateABrowsingContext() {
    BrowsingContext window1 = new BrowsingContext(driver, driver.getWindowHandle());
    BrowsingContext window2 = new BrowsingContext(driver, WindowType.WINDOW);

    window1.activate();

    boolean isFocused = (boolean) ((JavascriptExecutor) driver).executeScript("return document.hasFocus();");

    Assertions.assertTrue(isFocused);
}
```

**CSharp**

```csharp
[TestMethod]
public async Task CloseTab()
{
    var context = (await bidi.BrowsingContext.CreateAsync(ContextType.Tab)).Context;

    await context.CloseAsync();
}

[TestMethod]
public async Task CloseWindow()
{
    var context = (await bidi.BrowsingContext.CreateAsync(ContextType.Window)).Context;

    await context.CloseAsync();
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```javascript
const window1 = await BrowsingContext(driver, {type: 'window'})
const window2 = await BrowsingContext(driver, {type: 'window'})

await window2.close()
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Activate a browsing context

**Java**

_Requires [Selenium v4.14.1](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.14.1.0) or later._

```java
BrowsingContext browsingContext = new BrowsingContext(driver, WindowType.TAB);

browsingContext.navigate("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html", ReadinessState.COMPLETE);

NavigationResult reloadInfo = browsingContext.reload(ReadinessState.INTERACTIVE);
```

**CSharp**

```csharp
await context.ActivateAsync();
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.15](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.15.0) or later._

```javascript
const window1 = await BrowsingContext(driver, {
  browsingContextId: id,
})
```

```javascript
await window1.activate()
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Reload a browsing context

**Java**

_Requires [Selenium v4.13.0](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.13.0.0) or later._

```java
BrowsingContext browsingContext = new BrowsingContext(driver, driver.getWindowHandle());

driver.get("https://www.selenium.dev/selenium/web/alerts.html");

driver.findElement(By.id("alert")).click();
```

**CSharp**

```csharp
await context.NavigateAsync("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html", new() { Wait = ReadinessState.Complete });

var info = await context.ReloadAsync();

Assert.IsNotNull(info);
Assert.IsNotNull(info.Navigation);
StringAssert.Contains(info.Url, "/bidi/logEntryAdded.html");
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.15](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.15.0) or later._

```javascript
await browsingContext.reload(undefined, 'complete')
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Handle user prompt

**Java**

_Requires [Selenium v4.13.0](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.13.0.0) or later._

```java
@Test
void testDismissUserPromptWithText() {
    BrowsingContext browsingContext = new BrowsingContext(driver, driver.getWindowHandle());

    driver.get("https://www.selenium.dev/selenium/web/alerts.html");

    driver.findElement(By.id("prompt-with-default")).click();

    String userText = "Selenium automates browsers";
```

**CSharp**

```csharp
// temporary use firefox because of chrome automatically handle prompts
using var driver = new FirefoxDriver(new FirefoxOptions() { UseWebSocketUrl = true, UnhandledPromptBehavior = UnhandledPromptBehavior.Ignore });

var bidi = await driver.AsBiDiAsync();

var context = (await bidi.BrowsingContext.GetTreeAsync()).Contexts[0].Context;

driver.Url = "https://www.selenium.dev/selenium/web/alerts.html";

TaskCompletionSource<UserPromptOpenedEventArgs> promptOpened = new();

await bidi.BrowsingContext.UserPromptOpened.SubscribeAsync(args => promptOpened.TrySetResult(args));

driver.FindElement(By.Id("prompt-with-default")).Click();

await promptOpened.Task.WaitAsync(TimeSpan.FromSeconds(5));

await context.HandleUserPromptAsync(new() { Accept = true, UserText = "Selenium automates browsers" });
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.15](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.15.0) or later._

```javascript
await browsingContext.handleUserPrompt(true, userText)
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Capture Screenshot

**Java**

_Requires [Selenium v4.13.0](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.13.0.0) or later._

```java

driver.get("https://www.selenium.dev/selenium/web/coordinates_tests/simple_page.html");

WebElement element = driver.findElement(By.id("box"));
Rectangle elementRectangle = element.getRect();
```

**CSharp**

```csharp
var screenshot = await context.CaptureScreenshotAsync();

Assert.IsNotNull(screenshot);
Assert.IsNotNull(screenshot.Data);
Assert.IsNotNull(screenshot.ToByteArray());
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.15](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.15.0) or later._

```javascript
const response = await browsingContext.captureScreenshot()
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Capture Viewport Screenshot

**Java**

_Requires [Selenium v4.14.0](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.14.0.0) or later._

```java
}

@Test
void textCaptureElementScreenshot() {
    BrowsingContext browsingContext = new BrowsingContext(driver, driver.getWindowHandle());

    driver.get("https://www.selenium.dev/selenium/web/formPage.html");
    WebElement element = driver.findElement(By.id("checky"));

    String screenshot = browsingContext.captureElementScreenshot(((RemoteWebElement) element).getId());
```

**CSharp**

```csharp
var screenshot = await context.CaptureScreenshotAsync(new() { Clip = new BoxClipRectangle(5, 5, 10, 10) });

Assert.IsNotNull(screenshot);
Assert.IsNotNull(screenshot.Data);
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.15](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.15.0) or later._

```javascript
const browsingContext = await BrowsingContext(driver, {
  browsingContextId: id,
})

const response = await browsingContext.captureBoxScreenshot(5, 5, 10, 10)
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Capture Element Screenshot

**Java**

_Requires [Selenium v4.14.0](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.14.0.0) or later._

```java
BrowsingContext browsingContext = new BrowsingContext(driver, driver.getWindowHandle());
driver.get("https://www.selenium.dev/selenium/web/formPage.html");

browsingContext.setViewport(250, 300);

List<Long> newViewportSize =
```

**CSharp**

```csharp
driver.Url = "https://www.selenium.dev/selenium/web/formPage.html";

var nodes = (await context.LocateNodesAsync(new CssLocator("#checky"))).Nodes;

Assert.IsTrue(nodes.Length > 0, "Expected to locate #checky");

var screenshot = await context.CaptureScreenshotAsync(new() { Clip = new ElementClipRectangle(nodes[0]) });

Assert.IsNotNull(screenshot);
Assert.IsNotNull(screenshot.Data);
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.15](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.15.0) or later._

```javascript
const response = await browsingContext.captureElementScreenshot(elementId)
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Set Viewport

**Java**

_Requires [Selenium v4.14.1](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.14.1.0) or later._

```java
BrowsingContext browsingContext = new BrowsingContext(driver, driver.getWindowHandle());

driver.get("https://www.selenium.dev/selenium/web/formPage.html");
PrintOptions printOptions = new PrintOptions();
```

**CSharp**

```csharp
await context.SetViewportAsync(new() { Viewport = new Viewport(Width: 250, Height: 300), DevicePixelRatio = 5 });
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.15](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.15.0) or later._

```javascript
await browsingContext.setViewport(250, 300)
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Print page

**Java**

_Requires [Selenium v4.14.1](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.14.1.0) or later._

```java
browsingContext.navigate("https://www.selenium.dev/selenium/web/formPage.html", ReadinessState.COMPLETE);

wait.until(visibilityOfElementLocated(By.id("imageButton"))).submit();
wait.until(titleIs("We Arrive Here"));

browsingContext.back();
```

**CSharp**

```csharp
var pdf = await context.PrintAsync(new() { PageRanges = [1, 2, 3..5, new(3, 5), 7..] });

Assert.IsNotNull(pdf);
Assert.IsNotNull(pdf.Data);
Assert.IsNotNull(pdf.ToByteArray());
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```javascript
const result = await browsingContext.printPage({
  orientation: 'landscape',
  scale: 1,
  background: true,
  width: 30,
  height: 30,
  top: 1,
  bottom: 1,
  left: 1,
  right: 1,
  shrinkToFit: true,
  pageRanges: ['1-2'],
})
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Navigate back

**Java**

_Requires [Selenium v4.16.0](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.16.0.0) or later._

```java

wait.until(visibilityOfElementLocated(By.id("imageButton"))).submit();
wait.until(titleIs("We Arrive Here"));

browsingContext.back();
Assertions.assertTrue(driver.getPageSource().contains("We Leave From Here"));
```

**CSharp**

```csharp
await context.NavigateAsync("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html", new() { Wait = ReadinessState.Complete });

await context.TraverseHistoryAsync(-1);

var url = await WaitForUrlAsync("about:blank");

Assert.AreEqual("about:blank", url);
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.17](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.17.0) or later._

```javascript
await browsingContext.back()
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Navigate forward

**Java**

_Requires [Selenium v4.16.0](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.16.0.0) or later._

```java
    void canTraverseBrowserHistory() {
        BrowsingContext browsingContext = new BrowsingContext(driver, driver.getWindowHandle());
        browsingContext.navigate("https://www.selenium.dev/selenium/web/formPage.html", ReadinessState.COMPLETE);

        wait.until(visibilityOfElementLocated(By.id("imageButton"))).submit();
        wait.until(titleIs("We Arrive Here"));

        browsingContext.traverseHistory(-1);
        Assertions.assertTrue(driver.getPageSource().contains("We Leave From Here"));
    }
}
```

**CSharp**

```csharp
await context.NavigateAsync("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html", new() { Wait = ReadinessState.Complete });

await context.TraverseHistoryAsync(-1);
await WaitForUrlAsync("about:blank");

await context.TraverseHistoryAsync(1);
var url = await WaitForUrlAsync("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");

Assert.AreEqual("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html", url);
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.17](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.17.0) or later._

```javascript
await browsingContext.forward()
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Traverse history

**Java**

_Requires [Selenium v4.16.0](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.16.0.0) or later._

```java

```

**CSharp**

```csharp
await context.NavigateAsync("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html", new() { Wait = ReadinessState.Complete });

await context.TraverseHistoryAsync(-1);

var url = await WaitForUrlAsync("about:blank");

Assert.AreEqual("about:blank", url);
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.17](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.17.0) or later._

```javascript
await browsingContext.traverseHistory(-1)
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

## Events 
This section contains the APIs related to browsing context events. 

### Browsing Context Created Event

**Java**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```java
try (BrowsingContextInspector inspector = new BrowsingContextInspector(driver)) {
    CompletableFuture<BrowsingContextInfo> future = new CompletableFuture<>();

    inspector.onBrowsingContextCreated(future::complete);

    String windowHandle = driver.switchTo().newWindow(WindowType.WINDOW).getWindowHandle();

    BrowsingContextInfo browsingContextInfo = future.get(5, TimeUnit.SECONDS);
```

**CSharp**

```csharp
TaskCompletionSource<ContextCreatedEventArgs> tcs = new();

await bidi.BrowsingContext.ContextCreated.SubscribeAsync(args => tcs.TrySetResult(args));

driver.SwitchTo().NewWindow(OpenQA.Selenium.WindowType.Window);

var info = await tcs.Task.WaitAsync(TimeSpan.FromSeconds(5));

Assert.IsNotNull(info);
Console.WriteLine(info);
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.9.2](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.9.2.0) or later._

```javascript
const browsingContextInspector = await BrowsingContextInspector(driver)
await browsingContextInspector.onBrowsingContextCreated((entry) => {
  contextInfo = entry
})

await driver.switchTo().newWindow('window')
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Dom Content loaded Event

**Java**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```java
        String windowHandle = driver.switchTo().newWindow(WindowType.TAB).getWindowHandle();

        BrowsingContextInfo browsingContextInfo = future.get(5, TimeUnit.SECONDS);

        Assertions.assertEquals(windowHandle, browsingContextInfo.getId());
    }
}

@Test
void canListenToDomContentLoadedEvent()
```

**CSharp**

```csharp
TaskCompletionSource<DomContentLoadedEventArgs> tcs = new();

await context.DomContentLoaded.SubscribeAsync(args => tcs.TrySetResult(args));

await context.NavigateAsync("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html", new() { Wait = ReadinessState.Complete });

var navigationInfo = await tcs.Task.WaitAsync(TimeSpan.FromSeconds(5));

Assert.IsNotNull(navigationInfo);
Console.WriteLine(navigationInfo);
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.9.2](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.9.2.0) or later._

```javascript
const browsingContextInspector = await BrowsingContextInspector(driver)
let navigationInfo = null
await browsingContextInspector.onDomContentLoaded((entry) => {
  navigationInfo = entry
})

const browsingContext = await BrowsingContext(driver, {
  browsingContextId: await driver.getWindowHandle(),
})
await browsingContext.navigate('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html', 'complete')
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Browsing Context Loaded Event

**Java**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```java
try (BrowsingContextInspector inspector = new BrowsingContextInspector(driver)) {
    CompletableFuture<NavigationInfo> future = new CompletableFuture<>();
    inspector.onBrowsingContextLoaded(future::complete);

    BrowsingContext context = new BrowsingContext(driver, driver.getWindowHandle());
    context.navigate("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html", ReadinessState.COMPLETE);

    NavigationInfo navigationInfo = future.get(5, TimeUnit.SECONDS);
```

**CSharp**

```csharp
TaskCompletionSource<LoadEventArgs> tcs = new();

await context.Load.SubscribeAsync(args => tcs.TrySetResult(args));

await context.NavigateAsync("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html", new() { Wait = ReadinessState.Complete });

var navigationInfo = await tcs.Task.WaitAsync(TimeSpan.FromSeconds(5));

Assert.IsNotNull(navigationInfo);
Console.WriteLine(navigationInfo);
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.9.2](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.9.2.0) or later._

```javascript
const browsingContextInspector = await BrowsingContextInspector(driver)

await browsingContextInspector.onBrowsingContextLoaded((entry) => {
  navigationInfo = entry
})
const browsingContext = await BrowsingContext(driver, {
  browsingContextId: await driver.getWindowHandle(),
})
await browsingContext.navigate('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html', 'complete')
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Navigated Started Event

**Java**

_Requires [Selenium v4.15](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.15.0) or later._

```java
try (BrowsingContextInspector inspector = new BrowsingContextInspector(driver)) {
    CompletableFuture<NavigationInfo> future = new CompletableFuture<>();
    inspector.onNavigationStarted(future::complete);

    BrowsingContext context = new BrowsingContext(driver, driver.getWindowHandle());
    context.navigate("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html", ReadinessState.COMPLETE);

    NavigationInfo navigationInfo = future.get(5, TimeUnit.SECONDS);
```

**CSharp**

```csharp
TaskCompletionSource<NavigationStartedEventArgs> tcs = new();

await context.NavigationStarted.SubscribeAsync(args => tcs.TrySetResult(args));

await context.NavigateAsync("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html", new() { Wait = ReadinessState.Complete });

var info = await tcs.Task.WaitAsync(TimeSpan.FromSeconds(5));

Assert.IsNotNull(info);
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Fragment Navigated Event

**Java**

_Requires [Selenium v4.15](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.15.0) or later._

```java
try (BrowsingContextInspector inspector = new BrowsingContextInspector(driver)) {
    CompletableFuture<NavigationInfo> future = new CompletableFuture<>();

    BrowsingContext context = new BrowsingContext(driver, driver.getWindowHandle());
    context.navigate("https://www.selenium.dev/selenium/web/linked_image.html", ReadinessState.COMPLETE);

    inspector.onFragmentNavigated(future::complete);

    context.navigate("https://www.selenium.dev/selenium/web/linked_image.html#linkToAnchorOnThisPage", ReadinessState.COMPLETE);

    NavigationInfo navigationInfo = future.get(5, TimeUnit.SECONDS);
```

**CSharp**

```csharp
await context.NavigateAsync("https://www.selenium.dev/selenium/web/linked_image.html", new() { Wait = ReadinessState.Complete });

TaskCompletionSource<FragmentNavigatedEventArgs> tcs = new();

await context.FragmentNavigated.SubscribeAsync(args => tcs.TrySetResult(args));

await context.NavigateAsync("https://www.selenium.dev/selenium/web/linked_image.html#linkToAnchorOnThisPage", new() { Wait = ReadinessState.Complete });

var navigationInfo = await tcs.Task.WaitAsync(TimeSpan.FromSeconds(5));

Assert.IsNotNull(navigationInfo);
Console.WriteLine(navigationInfo);
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.15.0](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.15.0.0) or later._

```javascript
const browsingContextInspector = await BrowsingContextInspector(driver)

const browsingContext = await BrowsingContext(driver, {
  browsingContextId: await driver.getWindowHandle(),
})
await browsingContext.navigate('https://www.selenium.dev/selenium/web/linked_image.html', 'complete')

await browsingContextInspector.onFragmentNavigated((entry) => {
  navigationInfo = entry
})

await browsingContext.navigate('https://www.selenium.dev/selenium/web/linked_image.html#linkToAnchorOnThisPage', 'complete')
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### User Prompt Opened Event

**Java**

_Requires [Selenium v4.15](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.15.0) or later._

```java
try (BrowsingContextInspector inspector = new BrowsingContextInspector(driver)) {
    CompletableFuture<NavigationInfo> future = new CompletableFuture<>();

    BrowsingContext context = new BrowsingContext(driver, driver.getWindowHandle());
    context.navigate("https://www.selenium.dev/selenium/web/linked_image.html", ReadinessState.COMPLETE);

    inspector.onFragmentNavigated(future::complete);

    context.navigate("https://www.selenium.dev/selenium/web/linked_image.html#linkToAnchorOnThisPage", ReadinessState.COMPLETE);

    NavigationInfo navigationInfo = future.get(5, TimeUnit.SECONDS);
```

**CSharp**

```csharp
TaskCompletionSource<UserPromptOpenedEventArgs> tcs = new();

await context.NavigateAsync("https://www.selenium.dev/selenium/web/alerts.html", new() { Wait = ReadinessState.Complete });

// TODO: this event can be a part of context
await bidi.BrowsingContext.UserPromptOpened.SubscribeAsync(args => tcs.TrySetResult(args));

driver.FindElement(By.Id("prompt")).Click();

var userPromptOpenedEventArgs = await tcs.Task.WaitAsync(TimeSpan.FromSeconds(5));

Assert.IsNotNull(userPromptOpenedEventArgs);
Console.WriteLine(userPromptOpenedEventArgs);
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### User Prompt Closed Event

**Java**

_Requires [Selenium v4.15](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.15.0) or later._

```java
try (BrowsingContextInspector inspector = new BrowsingContextInspector(driver)) {
    CompletableFuture<UserPromptClosed> future = new CompletableFuture<>();

    BrowsingContext context = new BrowsingContext(driver, driver.getWindowHandle());
    inspector.onUserPromptClosed(future::complete);

    driver.get("https://www.selenium.dev/selenium/web/alerts.html");

    driver.findElement(By.id("prompt")).click();

    context.handleUserPrompt(true, "selenium");

    UserPromptClosed userPromptClosed = future.get(5, TimeUnit.SECONDS);
    Assertions.assertEquals(context.getId(), userPromptClosed.getBrowsingContextId());
```

**CSharp**

```csharp
TaskCompletionSource<UserPromptOpenedEventArgs> opened = new();
TaskCompletionSource<UserPromptClosedEventArgs> closed = new();

await context.NavigateAsync("https://www.selenium.dev/selenium/web/alerts.html", new() { Wait = ReadinessState.Complete });

// TODO: these events can be a part of context
await bidi.BrowsingContext.UserPromptOpened.SubscribeAsync(args => opened.TrySetResult(args));
await bidi.BrowsingContext.UserPromptClosed.SubscribeAsync(args => closed.TrySetResult(args));

driver.FindElement(By.Id("prompt")).Click();

await opened.Task.WaitAsync(TimeSpan.FromSeconds(5));

try
{
    // Chrome's default unhandled prompt behavior may already have closed the
    // prompt by this point; tolerate that instead of treating it as a failure.
    await context.HandleUserPromptAsync(new() { Accept = true });
}
catch (BiDiException ex) when (ex.Message.Contains("no such alert"))
{
}

var userPromptClosedEventArgs = await closed.Task.WaitAsync(TimeSpan.FromSeconds(5));

Assert.IsNotNull(userPromptClosedEventArgs);
Console.WriteLine(userPromptClosedEventArgs);
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Browsing Context Destroyed Event

**Java**

_Requires [Selenium v4.18](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.18.0) or later._

```java
try (BrowsingContextInspector inspector = new BrowsingContextInspector(driver)) {
    CompletableFuture<BrowsingContextInfo> future = new CompletableFuture<>();

    inspector.onBrowsingContextDestroyed(future::complete);

    String windowHandle = driver.switchTo().newWindow(WindowType.WINDOW).getWindowHandle();

    driver.close();

    BrowsingContextInfo browsingContextInfo = future.get(5, TimeUnit.SECONDS);

    Assertions.assertEquals(windowHandle, browsingContextInfo.getId());
```

**CSharp**

```csharp
TaskCompletionSource<ContextDestroyedEventArgs> tcs = new();

await bidi.BrowsingContext.ContextDestroyed.SubscribeAsync(args => tcs.TrySetResult(args));

await context.CloseAsync();

var contextInfo = await tcs.Task.WaitAsync(TimeSpan.FromSeconds(5));

Assert.IsNotNull(contextInfo);
Assert.AreEqual(context, contextInfo.Context);
Console.WriteLine(contextInfo);
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.18.0](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.18.0.0) or later._

```javascript
const browsingContextInspector = await BrowsingContextInspector(driver)
await browsingContextInspector.onBrowsingContextDestroyed((entry) => {
  contextInfo = entry
})

await driver.switchTo().newWindow('window')

const windowHandle = await driver.getWindowHandle()
await driver.close()
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._
