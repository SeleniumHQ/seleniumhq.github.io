# Screenshots and PDFs

> Capturing the viewport, a single element or the whole page as an image, and printing a page to PDF.

URL: https://www.selenium.dev/documentation/guides/screenshots/
Site index: https://www.selenium.dev/llms.txt

---

A test fails on the CI server and the stack trace says an element was not found. What was on
the screen at the time? Without a picture, the first debugging step is to re-run the test
locally and hope it fails the same way. A screenshot taken at the moment of failure turns
that guess into evidence, and the same API is the basis of visual comparison and archiving.

## Recommended approach

WebDriver's screenshot command returns the current viewport as a PNG, Base64-encoded. Every
binding wraps that so you can save it straight to a file. Calling the same command on an
element rather than the driver crops the image to that element's bounding box, which is the
right choice when you want to compare a component rather than a whole page.

A viewport screenshot captures only what is visible without scrolling. Firefox supports a
full-page screenshot through its own driver API; with WebDriver BiDi enabled, the browsing
context module's `captureScreenshot` command works across browsers and can also capture a box
or an element.

To produce a PDF rather than an image, use the print command. It takes a `PrintOptions`
object for orientation, page ranges, size, margins, scale and whether to print background
graphics, and returns the document Base64-encoded, ready to decode and write to a file.

Attach a screenshot to every failure in your test framework's teardown hook: it is cheap,
and the one time you need it you will be glad it is there.

Take a screenshot of the viewport:

**JavaScript**

```javascript
let encodedString = await driver.takeScreenshot();
```

Take a screenshot of one element:

**JavaScript**

```javascript
let header = await driver.findElement(By.css('h1'));
// Captures the element screenshot
let encodedString = await header.takeScreenshot(true);
```

Take a full-page screenshot in Firefox:

**Java**

```java
File screenshot = driver.getFullPageScreenshotAs(OutputType.FILE);
```

**Python**

```python
driver.save_full_page_screenshot("full_page_screenshot.png")
```

**Ruby**

```ruby
screenshot = driver.save_full_page_screenshot(File.join(dir, 'screenshot.png'))
```

Capture a screenshot through BiDi:

**Java**

```java
String screenshot = browsingContext.captureScreenshot();
```

**CSharp**

```csharp
var screenshot = await context.CaptureScreenshotAsync();
```

**JavaScript**

```javascript
const response = await browsingContext.captureScreenshot()
```

Print the page to PDF:

**Java**

```java
PrintsPage printer = (PrintsPage) driver;
PrintOptions printOptions = new PrintOptions();
Pdf printedPage = printer.print(printOptions);
```

**Python**

```python
print_options = PrintOptions()
pdf = driver.print_page(print_options)
```

**CSharp**

```csharp
PrintOptions printOptions = new PrintOptions();
PrintDocument printedPage = driver.Print(printOptions);
```

**JavaScript**

```javascript
let base64 = await driver.printPage({pageRanges: ["1-2"]});
```

## Common errors

* [ScreenshotException](https://www.selenium.dev/documentation/webdriver/troubleshooting/errors/index.md#screenshotexception):
  the browser could not capture the screen, usually because the window is minimised or the
  element is not rendered. Maximise the window or scroll the element into view.
* [StaleElementReferenceException](https://www.selenium.dev/documentation/webdriver/troubleshooting/errors/index.md#staleelementreferenceexception):
  the element being captured was re-rendered between locating it and taking the screenshot.

## Reference

* [Browser window screenshots](https://www.selenium.dev/documentation/webdriver/interactions/windows/index.md#takescreenshot) and
  [element screenshots](https://www.selenium.dev/documentation/webdriver/interactions/windows/index.md#takeelementscreenshot)
* [Firefox full-page screenshots](https://www.selenium.dev/documentation/webdriver/browsers/firefox/index.md#full-page-screenshots)
* [BiDi browsing context](https://www.selenium.dev/documentation/webdriver/bidi/w3c/browsing_context/index.md#capture-screenshot): screenshot and print commands
* [Print page](https://www.selenium.dev/documentation/webdriver/interactions/print_page/index.md): every print option
