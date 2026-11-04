# Classe de Serviço do Driver

URL: https://www.selenium.dev/pt-br/documentation/webdriver/drivers/service/

---

The Service classes are for managing the starting and stopping of drivers.
They can not be used with a Remote WebDriver session.

Service classes allow you to specify information about the driver,
like location and which port to use.
They also let you specify what arguments get passed
to the command line. Most of the useful arguments are related to logging.

## Default Service instance

To start a driver with a default service instance:

**Java**

```java
ChromeDriverService service = new ChromeDriverService.Builder().build();
driver = new ChromeDriver(service);
```

**Python**

_Requires [Selenium v4.11](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.11.0) or later._

```python
service = webdriver.ChromeService()
driver = webdriver.Chrome(service=service)
```

**CSharp**

```csharp
var service = ChromeDriverService.CreateDefaultService();
driver = new ChromeDriver(service);
```

**Ruby**

```ruby
service = Selenium::WebDriver::Service.chrome
@driver = Selenium::WebDriver.for :chrome, service: service
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

## Driver location

**Note:** If you are using Selenium 4.6 or greater, you shouldn't need to set a driver location.
If you can not update Selenium or have an advanced use case here is how to specify the driver location:

**Java**

```java
ChromeDriverService service =
    new ChromeDriverService.Builder().usingDriverExecutable(driverPath).build();
```

**Python**

_Requires [Selenium v4.11](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.11.0) or later._

```python
service = webdriver.ChromeService(executable_path=chromedriver_bin)
```

**CSharp**

_Requires [Selenium v4.9](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.9.0) or later._

```csharp
var service = ChromeDriverService.CreateDefaultService(await GetDriverLocationAsync(options));
```

**Ruby**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```ruby
service.executable_path = driver_path
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

## Driver port

If you want the driver to run on a specific port, you may specify it as follows:

**Java**

```java
ChromeDriverService service = new ChromeDriverService.Builder().usingPort(1234).build();
```

**Python**

_Requires [Selenium v4.11](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.11.0) or later._

```python
service = webdriver.ChromeService(port=1234)
```

**CSharp**

```csharp
service.Port = 1234;
```

**Ruby**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```ruby
service.port = 1234
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

<span id="setting-log-output"></span>
## Logging

Logging functionality varies between browsers. Most browsers allow you to
specify location and level of logs. Take a look at the respective browser page:
* [Chrome](https://www.selenium.dev/pt-br/documentation/webdriver/browsers/chrome/index.md#service)
* [Edge](https://www.selenium.dev/pt-br/documentation/webdriver/browsers/edge/index.md#service)
* [Firefox](https://www.selenium.dev/pt-br/documentation/webdriver/browsers/firefox/index.md#service)
* [Internet Explorer](https://www.selenium.dev/pt-br/documentation/webdriver/browsers/internet_explorer/index.md#service)
* [Safari](https://www.selenium.dev/pt-br/documentation/webdriver/browsers/safari/index.md#service)
