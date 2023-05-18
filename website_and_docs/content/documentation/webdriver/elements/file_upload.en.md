---
title: "File Upload"
linkTitle: "File Upload"
weight: 1
aliases: [
"/documentation/webdriver/additional_features/fileupload/"
]
---

The file upload dialog could be handled using Selenium, 
when the input element is of type file. 
An example of it, could be found on this 
web page-  https://the-internet.herokuapp.com/upload
We will require to have a file available with us, 
which we need to upload. 
The code to upload the file for different programming 
languages will be as follows - 


{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
class fileUploadDoc{
	public static void main(String[] args) {
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
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :chrome
driver.get("https://the-internet.herokuapp.com/upload")
driver.find_element(:id,"file-upload").send_keys("selenium-snapshot.jpg")
driver.find_element(:id,"file-submit").submit()

if driver.page_source().include? "File Uploaded!"
  puts "file upload success"
else
  puts "file upload not successful"
end

  {{< /tab >}}
  
{{< tab header="JavaScript" >}}
import {Builder,By} from "selenium-webdriver"


let driver = await new Builder().forBrowser('chrome').build()
await driver.get("https://the-internet.herokuapp.com/upload");

await driver.findElement(By.id("file-upload")).sendKeys("selenium-snapshot.jpg")
await driver.findElement(By.id("file-submit")).submit()

driver.getPageSource().then(result => {
    if (result.indexOf("File Uploaded!")){
        console.log("file upload success")
    }else {
        console.log("file upload not successful")
    }
})
  
{{< /tab >}}

{{< tab header="Kotlin" >}}
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
{{< /tab >}}
{{< /tabpane >}}

So the above example code helps to understand 
how we can upload a file using Selenium. 
