---
title: "Remote WebDriver"
linkTitle: "Remote WebDriver"
weight: 10
aliases: [
"/documentation/pt-br/remote_webdriver/",
"/documentation/pt-br/remote_webdriver/remote_webdriver_client/",
"/pt-br/documentation/webdriver/remote_webdriver/",
]

---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to Portuguese. Do you speak Portuguese? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

Você pode usar o WebDriver remotamente da mesma forma que o usaria
localmente. A principal diferença é que um WebDriver remoto precisa ser
configurado para que possa executar seus testes em uma máquina separada.

Um WebDriver remoto é composto por duas peças: um cliente e um
servidor. O cliente é o seu teste WebDriver e o servidor é simplesmente um
Servlet Java, que pode ser hospedado em qualquer servidor de aplicativo JEE moderno.

Para executar um cliente WebDriver remoto, primeiro precisamos nos conectar ao RemoteWebDriver.
Fazemos isso apontando a URL para o endereço do servidor que está executando nossos testes.
Para personalizar nossa configuração, definimos os recursos desejados.
Abaixo está um exemplo de como instanciar um objeto WebDriver remoto
apontando para nosso servidor remoto da web, _www.example.com_,
executando nossos testes no Firefox.

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


Para personalizar ainda mais nossa configuração de teste, podemos adicionar outros recursos desejados.


## Opções do browser

Por exemplo, suponha que você queira executar o Chrome no Windows XP,
usando o Chrome versão 67:

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


## Detector de arquivo local

O Detector de Arquivo Local permite a transferência de arquivos da máquina do cliente para o servidor remoto. Por exemplo, se um teste precisa carregar um
arquivo para um aplicativo da web, um WebDriver remoto pode transferir automaticamente
o arquivo da máquina local para o servidor web remoto durante
o tempo de execução. Isso permite que o arquivo seja carregado da máquina remota
executando o teste. Não é habilitado por padrão e pode ser habilitado em
da seguinte forma:

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

Assim que o código acima for definido, você pode fazer upload de um arquivo em seu teste da seguinte maneira:

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
