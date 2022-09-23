---
title: "文件上传"
linkTitle: "文件上传"
weight: 1
aliases: [
"/zh-cn/documentation/webdriver/additional_features/fileupload/"
]
---

当input元素为文件类型时,
文件上传对话框可以使用Selenium处理. 
例如, 在这个网页中可以发现-  https://the-internet.herokuapp.com/upload
我们需要一个可用的文件, 
用于上传. 
不同语言的文件上传的代码实现如下 - 


{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
class fileUploadDoc{
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://the-internet.herokuapp.com/upload");
		//we want to import selenium-snapshot file. 
		driver.findElement(By.id("file-upload")).sendKeys("selenium-snapshot.jpg");
		driver.findElement(By.id("file-submit")).submit();
		if(driver.getPageSource().contains("File Uploaded!")) {
			System.out.println("file uploaded");
		}
		else{
				System.out.println("file not uploaded");
			}
		driver.quit();
	}
}

  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver
from webdriver_manager.chrome import ChromeDriverManager
driver = webdriver.Chrome(ChromeDriverManager().install())
driver.implicitly_wait(10)
driver.get("https://the-internet.herokuapp.com/upload");
driver.find_element(By.ID,"file-upload").send_keys("selenium-snapshot.jpg")
driver.find_element(By.ID,"file-submit").submit()
if(driver.page_source.find("File Uploaded!")):
    print("file upload success")
else:
    print("file upload not successful")
driver.quit()

  {{< /tab >}}
  {{< tab header="CSharp" >}}
using System;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace SeleniumDocumentation.SeleniumPRs
{
    class FileUploadExample
    {
        static void Main(String[] args)
        {
            IWebDriver driver = new ChromeDriver();
            try
            {
                // Navigate to Url
                driver.Navigate().GoToUrl("https://the-internet.herokuapp.com/upload");
                driver.FindElement(By.Id("file-upload")).SendKeys("selenium-snapshot.jpg");
                driver.FindElement(By.Id("file-submit")).Submit();
                if (driver.PageSource.Contains("File Uploaded!"))
                {
                    Console.WriteLine("file uploaded");
                }
                else
                {
                    Console.WriteLine("file not uploaded");
                }
                driver.Quit();

            }

            }
}

  {{< /tab >}}
  {{< tab header="Ruby" >}}
// Help us by sending a code sample for file upload

  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Help us by sending a code sample for file upload
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  // Help us by sending a code sample for file upload
{{< /tab >}}
{{< /tabpane >}}


所以上面的示例代码有助于我们理解
如何使用Selenium上传文件. 
