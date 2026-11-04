# ファイルのアップロード

URL: https://www.selenium.dev/ja/documentation/webdriver/elements/file_upload/

---

Selenium はファイル選択ダイアログを操作できないため、ダイアログを開かずにファイルを
アップロードする方法を提供しています。要素が `type` 属性に `file` を指定した `input` 要素であれば、
`sendKeys` メソッドを使用して、アップロードするファイルの完全なパスを送信できます。

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

_These examples are not yet tested in CI ([move them to the examples directory](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#moving-examples))._

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
