---
title: "Driver Service Class"
linkTitle: "Service"
weight: 3
---

The Service classes are for managing the starting and stopping of drivers.
They can not be used with a Remote WebDriver session.

Service classes allow you to specify information about the driver,
like location and which port to use.
They also let you specify what arguments get passed
to the command line. Most of the useful arguments are related to logging.

## Default Service instance

To start a driver with a default service instance:

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
ChromeDriverService service = new ChromeDriverService.Builder().build();
WebDriver driver = new ChromeDriver(service);
{{< /tab >}}
{{< tab header="Python" >}}
from selenium.webdriver.chrome.service import Service
from selenium import webdriver

service = Service()
driver = webdriver.Chrome(service=service)
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
service = Selenium::WebDriver::Service.chrome
driver = Selenium::WebDriver.for :chrome, service: service
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< /tab >}}
{{< /tabpane >}}

## Driver location

**Note:** If you are using Selenium 4.6 or greater, you shouldn't need to set a driver location.
If you can not update Selenium or have an advanced use case here is how to specify the driver location:

{{< tabpane langEqualsHeader=true >}}
{{% tab header="Java" text=true %}}
In Java you can also set the driver location with a System Property:
```java
System.setProperty("webdriver.chrome.driver", driver_location);
```

Otherwise:
```java 
ChromeDriverService service = new ChromeDriverService.Builder()
    .usingDriverExecutable(driverLocation)
    .build();

ChromeDriver driver = new ChromeDriver(service);
```
{{% /tab %}}

{{< tab header="Python" >}}
from selenium.webdriver.chrome.service import Service
from selenium import webdriver

service = Service(executable_path=driver_location)
driver = webdriver.Chrome(service=service)
{{< /tab >}}
{{< tab header="CSharp" >}}
var driver = new ChromeDriver(driverLocation);
{{< /tab >}}
{{< tab header="Ruby" >}}
service = Selenium::WebDriver::Service.chrome(path: driver_location)
driver = Selenium::WebDriver.for :chrome, service: service
{{< /tab >}}
{{< tab header="JavaScript" >}}
const {Builder} = require('selenium-webdriver');
const chrome = require('selenium-webdriver/chrome');

const service = new chrome.ServiceBuilder(driverLocation);
const driver = new Builder().forBrowser('chrome').setChromeService(service).build();
{{< /tab >}}
{{< tab header="Kotlin" >}}
import org.openqa.selenium.chrome.ChromeDriver

fun main(args: Array<String>) {
System.setProperty("webdriver.chrome.driver", driverLocation)
val driver = ChromeDriver()
}
{{< /tab >}}
{{< /tabpane >}}

## Driver port


## Setting log output


### Console output


### File output
