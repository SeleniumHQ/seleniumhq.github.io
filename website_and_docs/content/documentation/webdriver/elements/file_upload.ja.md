---
title: "ファイルのアップロード"
linkTitle: "ファイルのアップロード"
weight: 5
aliases: [
"/documentation/webdriver/additional_features/fileupload/"
]
---


Selenium はファイル選択ダイアログを操作できないため、ダイアログを開かずにファイルを
アップロードする方法を提供しています。要素が `type` 属性に `file` を指定した `input` 要素であれば、
`sendKeys` メソッドを使用して、アップロードするファイルの完全なパスを送信できます。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/elements/FileUploadTest.java#L17-L19" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/elements/test_file_upload.py#L12-L14" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Elements/FileUploadTest.cs#L21-L23" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/elements/file_upload_spec.rb#L12-L14" >}}
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
