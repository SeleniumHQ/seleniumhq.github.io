---
title: "文件上传"
linkTitle: "文件上传"
weight: 1
aliases: [
"/zh-cn/documentation/webdriver/additional_features/fileupload/"
]
---

由于 Selenium 不能与文件上传对话框交互，因此它提供了一种无需打开对话框即可上传文件的方法。
如果该元素是一个类型为 `file` 的 `input` 元素，则可以使用
send keys 方法发送将要上传文件的完整路径。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/elements/FileUploadTest.java#L17-L19" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="examples/python/tests/elements/test_file_upload.py#L12-L14" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Elements/FileUploadTest.cs#L21-L23" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/elements/file_upload_spec.rb#L12-L14" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/elements/fileUpload.spec.js#L24-L25">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-examples >}}
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
{{< /tab >}}
{{< /tabpane >}}
