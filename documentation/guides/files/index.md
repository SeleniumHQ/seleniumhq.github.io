# Files: upload and download

> Uploading a file through a form without touching the operating system's file dialog, and why downloading through the browser is not the right way to test a download.

URL: https://www.selenium.dev/documentation/guides/files/
Site index: https://www.selenium.dev/llms.txt

---

Clicking a file input opens the operating system's file chooser, which is outside the browser
and invisible to WebDriver. Clicking a download link starts a download the API cannot watch:
there is no event for "finished", no progress, and no portable way to find the file afterwards.

## Recommended approach

**Uploads.** Do not click the input. Send the absolute path of the file to the
`<input type="file">` element with `sendKeys`, then submit the form as usual. The browser
attaches the file exactly as if it had been chosen through the dialog. The path must exist on
the machine where the _browser_ is running; when the browser is remote, on a Grid, set a local
file detector so the client uploads the file to the node first.

**Downloads.** Testing a download through the browser is discouraged. The browser's download
behaviour is configurable per browser and per platform, the file lands in a directory you then
have to poll, and none of this is what a user actually cares about. Instead, use Selenium to
get to the point where the download link exists, read its `href` (and any cookies the request
needs), and fetch it with an HTTP client in your test language. That gives you the bytes
directly, with a proper status code, and no polling.

If you are running on Selenium Grid and really need the browser to perform the download, Grid
can be configured to collect downloaded files from the node and the client can retrieve them;
see the remote WebDriver page below.

Upload a file by sending its path to the input:

**Java**

```java
WebElement fileInput = driver.findElement(By.cssSelector("input[type=file]"));
fileInput.sendKeys(uploadFile.getAbsolutePath());
driver.findElement(By.id("file-submit")).click();
```

**Python**

```python
file_input = driver.find_element(By.CSS_SELECTOR, "input[type='file']")
file_input.send_keys(upload_file)
driver.find_element(By.ID, "file-submit").click()
```

**CSharp**

```csharp
IWebElement fileInput = driver.FindElement(By.CssSelector("input[type=file]"));
fileInput.SendKeys(uploadFile);
driver.FindElement(By.Id("file-submit")).Click();
```

**Ruby**

```ruby
file_input = driver.find_element(css: 'input[type=file]')
file_input.send_keys(upload_file)
driver.find_element(id: 'file-submit').click
```

**JavaScript**

```javascript
await driver.findElement(By.id("file-upload")).sendKeys(image);
await driver.findElement(By.id("file-submit")).submit();
```

## Common errors

* [InvalidArgumentException](https://www.selenium.dev/documentation/webdriver/troubleshooting/errors/index.md#invalidargumentexception):
  the path does not exist on the machine running the browser. Use an absolute path, and a file
  detector when the browser is remote.
* [ElementNotInteractableException](https://www.selenium.dev/documentation/webdriver/troubleshooting/errors/index.md#elementnotinteractableexception):
  the file input is hidden behind a styled button. Sending keys to a hidden file input is
  allowed in most browsers; if it is refused, check the `strictFileInteractability` capability.

## Reference

* [File upload](https://www.selenium.dev/documentation/webdriver/elements/file_upload/index.md)
* [File downloads are discouraged](https://www.selenium.dev/documentation/test_practices/discouraged/file_downloads/index.md)
* [Remote WebDriver](https://www.selenium.dev/documentation/webdriver/drivers/remote_webdriver/index.md): uploads and downloads through a Grid
* [strictFileInteractability](https://www.selenium.dev/documentation/webdriver/drivers/options/index.md#strictfileinteractability)
