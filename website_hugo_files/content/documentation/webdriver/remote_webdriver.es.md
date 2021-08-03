---
title: "Remote WebDriver"
linkTitle: "Remote WebDriver"
weight: 11
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to Spanish. Do you speak Spanish? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

Puedes usar WebDriver de forma remota de la misma manera que lo usarías
localmente. La principal diferencia es que un WebDriver remoto debe ser
configurado para que pueda ejecutar tus pruebas en una máquina diferente.

Un WebDriver remoto se compone de dos piezas: un cliente y un
servidor. El cliente es tu prueba de WebDriver y el servidor es 
simplemente un servlet Java, que se puede alojar en cualquier 
servidor moderno de aplicaciones JEE.

Para ejecutar un cliente de WebDriver remoto, primero debemos 
conectarnos a RemoteWebDriver. Hacemos esto apuntando la URL a la 
dirección del servidor que ejecuta nuestras pruebas. Para 
personalizar nuestra configuración, establecemos las _capabilities_
deseadas. A continuación se muestra un ejemplo de 
la, instanciación de un objeto WebDriver remoto apuntando
a nuestro servidor web remoto, _www.example.com_, 
ejecutando nuestras pruebas en Firefox. 

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
FirefoxOptions firefoxOptions = new FirefoxOptions();
WebDriver driver = new RemoteWebDriver(new URL("http://www.example.com"), firefoxOptions);
driver.get("http://www.google.com");
driver.quit();
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver

firefox_options = webdriver.FirefoxOptions()
driver = webdriver.Remote(
    command_executor='http://www.example.com',
    options=firefox_options
)
driver.get("http://www.google.com")
driver.quit() 
  {{< /tab >}}
  {{< tab header="CSharp" >}}
 FirefoxOptions firefoxOptions = new FirefoxOptions();
 IWebDriver driver = new RemoteWebDriver(new Uri("http://www.example.com"), firefoxOptions);
 driver.Navigate().GoToUrl("http://www.google.com");
 driver.Quit();
  {{< /tab >}}
  {{< tab header="Ruby" >}}
require 'selenium-webdriver'

driver = Selenium::WebDriver.for :remote, url: "http://www.example.com", desired_capabilities: :firefox
driver.get "http://www.google.com"
driver.close
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const { Builder, Capabilities } = require("selenium-webdriver");
var capabilities = Capabilities.firefox();
(async function helloSelenium() {
    let driver = new Builder()        
        .usingServer("http://example.com")   
        .withCapabilities(capabilities)
        .build();
    try {
        await driver.get('http://www.google.com');
    } finally {
        await driver.quit();
    }
})();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
firefoxOptions = FirefoxOptions()
driver: WebDriver = new RemoteWebDriver(new URL("http://www.example.com"), firefoxOptions)
driver.get("http://www.google.com")
driver.quit()
  {{< /tab >}}
{{< /tabpane >}}


Para personalizar aún más nuestra configuración de prueba, 
podemos agregar otras _capabilities_ deseadas.


## Opciones del navegador

Por ejemplo, supón que deseas ejecutar Chrome en Windows XP,
usando Chrome versión 67:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
ChromeOptions chromeOptions = new ChromeOptions();
chromeOptions.setCapability("browserVersion", "67");
chromeOptions.setCapability("platformName", "Windows XP");
WebDriver driver = new RemoteWebDriver(new URL("http://www.example.com"), chromeOptions);
driver.get("http://www.google.com");
driver.quit();
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver

chrome_options = webdriver.ChromeOptions()
chrome_options.set_capability("browserVersion", "67")
chrome_options.set_capability("platformName", "Windows XP")
driver = webdriver.Remote(
    command_executor='http://www.example.com',
    options=chrome_options
)
driver.get("http://www.google.com")
driver.quit()  
  {{< /tab >}}
  {{< tab header="CSharp" >}}
var chromeOptions = new ChromeOptions();
chromeOptions.BrowserVersion = "67";
chromeOptions.PlatformName = "Windows XP";
IWebDriver driver = new RemoteWebDriver(new Uri("http://www.example.com"), chromeOptions);
driver.Navigate().GoToUrl("http://www.google.com");
driver.Quit();
  {{< /tab >}}
  {{< tab header="Ruby" >}}
caps = Selenium::WebDriver::Remote::Capabilities.chrome
caps.platform = Windows XP
caps.version = 67

driver = Selenium::WebDriver.for :remote, :url => "http://www.example.com", :desired_capabilities => caps
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const { Builder } = require("selenium-webdriver");
const chrome = require("selenium-webdriver/chrome");
let opts = new chrome.Options();
opts.setAcceptInsecureCerts(true);
opts.setBrowserVersion('67');
opts.setPlatform('Windows XP');
(async function helloSelenium() {
    let driver = new Builder()
        .usingServer("http://example.com")
        .forBrowser('chrome')
        .setChromeOptions(opts)
        .build();
    try {
        await driver.get('http://www.google.com');
    }
    finally {
        await driver.quit();
    }
})();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val chromeOptions = ChromeOptions()
chromeOptions.setCapability("browserVersion", "67")
chromeOptions.setCapability("platformName", "Windows XP")
val driver: WebDriver = new RemoteWebDriver(new URL("http://www.example.com"), chromeOptions)
driver.get("http://www.google.com")
driver.quit()
  {{< /tab >}}
{{< /tabpane >}}


## Detector de archivos local

El Local File Detector permite la transferencia de archivos
desde la máquina del cliente al servidor remoto. Por ejemplo, 
si una prueba necesita cargar un archivo a una aplicación web, 
un WebDriver remoto puede transferir automáticamente
el archivo de la máquina local al servidor web remoto durante
tiempo de ejecución. Esto permite que el archivo se cargue desde
la máquina remota ejecutando la prueba. No está habilitado de forma
predeterminada y puede habilitarse en de la siguiente manera:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
driver.setFileDetector(new LocalFileDetector());
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium.webdriver.remote.file_detector import LocalFileDetector

driver.file_detector = LocalFileDetector()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
var allowsDetection = this.driver as IAllowsFileDetection;
if (allowsDetection != null)
{
   allowsDetection.FileDetector = new LocalFileDetector();
}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
@driver.file_detector = lambda do |args|
  # args => ["/path/to/file"]
  str = args.first.to_s
  str if File.exist?(str)
end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
var remote = require('selenium-webdriver/remote');
driver.setFileDetector(new remote.FileDetector); 
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
driver.fileDetector = LocalFileDetector()
  {{< /tab >}}
{{< /tabpane >}}

Una vez que se definido el código anterior, 
puedes cargar un archivo en tu prueba de la siguiente manera:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
driver.get("http://sso.dev.saucelabs.com/test/guinea-file-upload");
WebElement upload = driver.findElement(By.id("myfile"));
upload.sendKeys("/Users/sso/the/local/path/to/darkbulb.jpg");
  {{< /tab >}}
  {{< tab header="Python" >}}
driver.get("http://sso.dev.saucelabs.com/test/guinea-file-upload")

driver.find_element(By.ID, "myfile").send_keys("/Users/sso/the/local/path/to/darkbulb.jpg")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
driver.Navigate().GoToUrl("http://sso.dev.saucelabs.com/test/guinea-file-upload");
IWebElement upload = driver.FindElement(By.Id("myfile"));
upload.SendKeys(@"/Users/sso/the/local/path/to/darkbulb.jpg");
  {{< /tab >}}
  {{< tab header="Ruby" >}}
@driver.navigate.to "http://sso.dev.saucelabs.com/test/guinea-file-upload"
    element = @driver.find_element(:id, 'myfile')
    element.send_keys "/Users/sso/SauceLabs/sauce/hostess/maitred/maitred/public/images/darkbulb.jpg"
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
driver.get("http://sso.dev.saucelabs.com/test/guinea-file-upload");
var upload = driver.findElement(By.id("myfile"));
upload.sendKeys("/Users/sso/the/local/path/to/darkbulb.jpg");  
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
driver.get("http://sso.dev.saucelabs.com/test/guinea-file-upload")
val upload: WebElement = driver.findElement(By.id("myfile"))
upload.sendKeys("/Users/sso/the/local/path/to/darkbulb.jpg")
  {{< /tab >}}
{{< /tabpane >}}

## Tracing client requests

This feature is only available for Java client binding (Beta onwards). The Remote WebDriver client sends requests to the Selenium Grid server, which passes them to the WebDriver. Tracing should be enabled at the server and client-side to trace the HTTP requests end-to-end. Both ends should have a trace exporter setup pointing to the visualization framework. 
By default, tracing is enabled for both client and server. 
To set up the visualization framework Jaeger UI and Selenium Grid 4, please refer to [Tracing Setup](https://github.com/SeleniumHQ/selenium/blob/selenium-4.0.0-beta-1/java/server/src/org/openqa/selenium/grid/commands/tracing.txt) for the desired version.

For client-side setup, follow the steps below.

### Beta 1 

#### Add the required dependencies

Installation of external libraries for tracing exporter can be done using Maven.
Add the _opentelemetry-exporter-jaeger_ and _grpc-netty_ dependency in your project pom.xml:

```xml
  <dependency>
      <groupId>io.opentelemetry</groupId>
      <artifactId>opentelemetry-exporter-jaeger</artifactId>
      <version>0.14.0</version>
    </dependency>
    <dependency>
      <groupId>io.grpc</groupId>
      <artifactId>grpc-netty</artifactId>
      <version>1.34.1</version>
    </dependency>
``` 

 
#### Add/pass the required system properties while running the client

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}

System.setProperty("JAEGER_SERVICE_NAME", "selenium-java-client");
System.setProperty("JAEGER_AGENT_HOST","localhost");
System.setProperty("JAEGER_AGENT_PORT","14250");

ImmutableCapabilities capabilities = new ImmutableCapabilities("browserName", "chrome");

WebDriver driver = new RemoteWebDriver(new URL("http://www.example.com"), capabilities);

driver.get("http://www.google.com");

driver.quit();

  {{< /tab >}}
{{< /tabpane >}}

### Beta 2 onwards 

#### Add the required dependencies

Installation of external libraries for tracing exporter can be done using Maven.
Add the _opentelemetry-exporter-jaeger_ and _grpc-netty_ dependency in your project pom.xml:

```xml
  <dependency>
      <groupId>io.opentelemetry</groupId>
      <artifactId>opentelemetry-exporter-jaeger</artifactId>
      <version>1.0.0</version>
    </dependency>
    <dependency>
      <groupId>io.grpc</groupId>
      <artifactId>grpc-netty</artifactId>
      <version>1.35.0</version>
    </dependency>
``` 
 
#### Add/pass the required system properties while running the client

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
System.setProperty("otel.traces.exporter", "jaeger");
System.setProperty("otel.exporter.jaeger.endpoint", "http://localhost:14250");
System.setProperty("otel.resource.attributes", "service.name=selenium-java-client");

ImmutableCapabilities capabilities = new ImmutableCapabilities("browserName", "chrome");

WebDriver driver = new RemoteWebDriver(new URL("http://www.example.com"), capabilities);

driver.get("http://www.google.com");

driver.quit();

  {{< /tab >}}
{{< /tabpane >}}

Please refer to [Tracing Setup](https://github.com/SeleniumHQ/selenium/blob/selenium-4.0.0-beta-1/java/server/src/org/openqa/selenium/grid/commands/tracing.txt) for more information on external dependencies versions required for the desired Selenium version.

More information can be found at:

* OpenTelemetry: https://opentelemetry.io
* Configuring OpenTelemetry:
    https://github.com/open-telemetry/opentelemetry-java/tree/main/sdk-extensions/autoconfigure
* Jaeger: https://www.jaegertracing.io
* [Selenium Grid Observability]({{< ref "observability.md" >}}) 

