# File Upload

> Como subir arquivos com Selenium

URL: https://www.selenium.dev/pt-br/documentation/webdriver/elements/file_upload/

---

Because Selenium cannot interact with the file upload dialog, it provides a way
to upload files without opening the dialog. If the element is an `input` element with type `file`, 
you can use the send keys method to send the full path to the file that will be uploaded.

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
await driver.get('https://the-internet.herokuapp.com/upload');
// Upload snapshot
```

**Kotlin**

_These examples are not yet tested in CI ([move them to the examples directory](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#moving-examples))._

```java
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver

fun main() {
    val driver = ChromeDriver()
    driver.get("https://the-internet.herokuapp.com/upload")
    driver.findElement(By.id("file-upload")).sendKeys("selenium-snapshot.jpg")
    driver.findElement(By.id("file-submit")).submit()
    if(driver.pageSource.contains("File Uploaded!")) {
        println("file uploaded")
    }
    else{
        println("file not uploaded")
    }
}
```
