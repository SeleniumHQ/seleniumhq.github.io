# Funcionalidade específica do IE

> Estas capacidades e características são específicas ao navegador Microsoft Internet Explorer.

URL: https://www.selenium.dev/pt-br/documentation/webdriver/browsers/internet_explorer/

---

Desde Junho de 2022, o Projecto Selenium deixou de suportar oficialmente o navegador Internet Explorer.
O driver Internet Explorer continua a suportar a execução do Microsoft Edge no modo "IE Compatibility Mode."

## Considerações especiais

O IE Driver é o único driver mantido directamente pelo Projecto Selenium.
Embora existam binários para as versões de 32 e 64 bits, existem algumas
[limitações conhecidas](//jimevansmusic.blogspot.co.uk/2014/09/screenshots-sendkeys-and-sixty-four.html)
com o driver de 64 bits. Desta forma, recomenda-se a utilização do driver de 32 bits.

Informação adicional sobre como usar o Internet Explorer pode ser encontrada na
[página IE Driver Server](https://www.selenium.dev/pt-br/documentation/legacy/ie_driver_server/index.md)

## Opções

Este é um exemplo de como iniciar o navegador Microsoft Edge em modo compatibilidade Internet Explorer
usando um conjunto de opções básicas:

**Java**

```java
InternetExplorerOptions options = new InternetExplorerOptions();
options.attachToEdgeChrome();
options.withEdgeExecutablePath(getEdgeLocation());
driver = new InternetExplorerDriver(options);
```

**Python**

```python
options = webdriver.IeOptions()
options.attach_to_edge_chrome = True
options.edge_executable_path = edge_bin
driver = webdriver.Ie(options=options)
```

**CSharp**

```csharp
var options = new InternetExplorerOptions();
options.AttachToEdgeChrome = true;
options.EdgeExecutablePath = GetEdgeLocation();
_driver = new InternetExplorerDriver(options);
```

**Ruby**

```ruby
options = Selenium::WebDriver::IE::Options.new
options.attach_to_edge_chrome = true
options.edge_executable_path = edge_location
@driver = Selenium::WebDriver.for :ie, options: options
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

A partir do driver versão v4.5.0:
* Se o IE não estiver presente no sistema (ausente por omissão no Windows 11), não necessita
usar os parametros "attachToEdgeChrome" e "withEdgeExecutablePath", pois o IE Driver 
irá encontrar e usar o Edge automaticamente.
* Se o IE e o Edge estiverem ambos presentes no sistema, use o parametro "attachToEdgeChrome",
o IE Driver irá encontrar e usar o Edge automaticamente.

So, if IE is not on the system, you only need:

_These examples are not yet tested in CI ([move them to the examples directory](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#moving-examples))._

**Java**

```java
InternetExplorerOptions options = new InternetExplorerOptions();
driver = new InternetExplorerDriver(options);
```

**Python**

```python
options = webdriver.IeOptions()
driver = webdriver.Ie(options=options)
```

**CSharp**

```csharp
var options = new InternetExplorerOptions();
_driver = new InternetExplorerDriver(options);
```

**Ruby**

```ruby
options = Selenium::WebDriver::Options.ie
@driver = Selenium::WebDriver.for :ie, options: options
```

**JavaScript**

```javascript
let driver = await new Builder()
.forBrowser('internet explorer')
.setIEOptions(options)
.build();
```

**Kotlin**

```kotlin
val options = InternetExplorerOptions()
val driver = InternetExplorerDriver(options)
```

Aqui pode ver alguns exemplos de utilização com capacidades diferentes:

### fileUploadDialogTimeout

Em alguns ambientes, o Internet Explorer pode expirar ao abrir a
Caixa de Diálogo de upload de arquivo. O IEDriver tem um tempo limite padrão de 1000 ms, mas você
pode aumentar o tempo limite usando o recurso fileUploadDialogTimeout.

**Java**

```java
InternetExplorerOptions options = new InternetExplorerOptions();
options.waitForUploadDialogUpTo(Duration.ofSeconds(2));
WebDriver driver = new RemoteWebDriver(options);

```

**Python**

```python
options = webdriver.IeOptions()
options.file_upload_timeout = 2000
```

**CSharp**

```csharp
var options = new InternetExplorerOptions();
options.FileUploadDialogTimeout = TimeSpan.FromMilliseconds(2000);
var driver = new RemoteWebDriver(options);

```

**Ruby**

```ruby
@options.file_upload_dialog_timeout = 2000
```

**JavaScript**

```javascript
const ie = require('selenium-webdriver/ie');
let options = new ie.Options().fileUploadDialogTimeout(2000);
let driver = await Builder()
          .setIeOptions(options)
          .build(); 

```

**Kotlin**

```kotlin
val options = InternetExplorerOptions()
options.waitForUploadDialogUpTo(Duration.ofSeconds(2))
val driver = RemoteWebDriver(options)

```

### ensureCleanSession

Quando definido como `true`, este recurso limpa o _Cache,
Histórico do navegador e cookies_ para todas as instâncias em execução
do InternetExplorer, incluindo aquelas iniciadas manualmente
ou pelo driver. Por padrão, é definido como `false`.

Usar este recurso causará queda de desempenho quando
iniciar o navegador, pois o driver irá esperar até que o cache
seja limpo antes de iniciar o navegador IE.

Esse recurso aceita um valor booleano como parâmetro.

**Java**

```java
InternetExplorerOptions options = new InternetExplorerOptions();
options.destructivelyEnsureCleanSession();
WebDriver driver = new RemoteWebDriver(options);

```

**Python**

```python
options = webdriver.IeOptions()
options.ensure_clean_session = True
```

**CSharp**

```csharp
var options = new InternetExplorerOptions();
options.EnsureCleanSession = true;
var driver = new RemoteWebDriver(options);

```

**Ruby**

```ruby
@options.ensure_clean_session = true
```

**JavaScript**

```javascript
const ie = require('selenium-webdriver/ie');
let options = new ie.Options().ensureCleanSession(true);
let driver = await Builder()
          .setIeOptions(options)
          .build(); 

```

**Kotlin**

```kotlin
val options = InternetExplorerOptions()
options.destructivelyEnsureCleanSession()
val driver = RemoteWebDriver(options)

```

### ignoreZoomSetting

O driver do InternetExplorer espera que o nível de zoom do navegador seja de 100%,
caso contrário, o driver lançará uma exceção. Este comportamento padrão
pode ser desativado definindo _ignoreZoomSetting_ como _true_.

Esse recurso aceita um valor booleano como parâmetro.

**Java**

```java
InternetExplorerOptions options = new InternetExplorerOptions();
options.ignoreZoomSettings();
WebDriver driver = new RemoteWebDriver(options);

```

**Python**

```python
options = webdriver.IeOptions()
options.ignore_zoom_level = True
```

**CSharp**

```csharp
var options = new InternetExplorerOptions();
options.IgnoreZoomLevel = true;
var driver = new RemoteWebDriver(options);

```

**Ruby**

```ruby
@options.ignore_zoom_level = true
```

**JavaScript**

```javascript
const ie = require('selenium-webdriver/ie');
let options = new ie.Options().ignoreZoomSetting(true);
let driver = await Builder()
          .setIeOptions(options)
          .build(); 

```

**Kotlin**

```kotlin
val options = InternetExplorerOptions()
options.ignoreZoomSettings()
val driver = RemoteWebDriver(options)

```

### ignoreProtectedModeSettings

Se deve ignorar a verificação do _Modo protegido_ durante o lançamento
uma nova sessão do IE.

Se não for definido e as configurações do _Modo protegido_ não forem iguais para
todas as zonas, uma exceção será lançada pelo driver.

Se a capacidade for definida como `true`, os testes podem
tornar-se instáveis, não responderem ou os navegadores podem travar.
No entanto, esta ainda é de longe a segunda melhor escolha,
e a primeira escolha *sempre* deve ser
definir as configurações do Modo protegido de cada zona manualmente.
Se um usuário estiver usando esta propriedade,
apenas um "melhor esforço" no suporte será dado.

Esse recurso aceita um valor booleano como parâmetro.

**Java**

```java
InternetExplorerOptions options = new InternetExplorerOptions();
options.introduceFlakinessByIgnoringSecurityDomains();
WebDriver driver = new RemoteWebDriver(options);

```

**Python**

```python
options = webdriver.IeOptions()
options.ignore_protected_mode_settings = True
```

**CSharp**

```csharp
var options = new InternetExplorerOptions();
options.IntroduceInstabilityByIgnoringProtectedModeSettings = true;
var driver = new RemoteWebDriver(options);

```

**Ruby**

```ruby
@options.ignore_protected_mode_settings = true
```

**JavaScript**

```javascript
const ie = require('selenium-webdriver/ie');
let options = new ie.Options().introduceFlakinessByIgnoringProtectedModeSettings(true);
let driver = await Builder()
          .setIeOptions(options)
          .build(); 

```

**Kotlin**

```kotlin
val options = InternetExplorerOptions()
options.introduceFlakinessByIgnoringSecurityDomains()
val driver = RemoteWebDriver(options)

```

### silent

Quando definido como `true`, esse recurso suprime a
saída de diagnóstico do IEDriverServer.

Esse recurso aceita um valor booleano como parâmetro.

**Java**

```java
InternetExplorerOptions options = new InternetExplorerOptions();
options.setCapability("silent", true);
WebDriver driver = new InternetExplorerDriver(options);

```

**Python**

```python
service = webdriver.IeService(service_args=["--silent"])
driver = webdriver.Ie(service=service)
```

**CSharp**

```csharp
InternetExplorerOptions options = new InternetExplorerOptions();
options.AddAdditionalInternetExplorerOption("silent", true);
IWebDriver driver = new InternetExplorerDriver(options);

```

**Ruby**

```ruby
@options.silent = true
```

**JavaScript**

```javascript
const {Builder,By, Capabilities} = require('selenium-webdriver');
let caps = Capabilities.ie();
caps.set('silent', true);

(async function example() {
    let driver = await new Builder()
        .forBrowser('internet explorer')
        .withCapabilities(caps)
        .build();
    try {
        await driver.get('http://www.google.com/ncr');
    }
    finally {
        await driver.quit();
    }
})();

```

**Kotlin**

```kotlin
import org.openqa.selenium.Capabilities
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.ie.InternetExplorerOptions

fun main() {
    val options = InternetExplorerOptions()
    options.setCapability("silent", true)
    val driver = InternetExplorerDriver(options)
    try {
        driver.get("https://google.com/ncr")
        val caps = driver.getCapabilities()
        println(caps)
    } finally {
        driver.quit()
    }
}

```

### Opções de linha de comando do IE

O Internet Explorer inclui várias opções de linha de comando
que permitem solucionar problemas e configurar o navegador.

Os seguintes pontos descrevem algumas opções de linha de comando com suporte

* _-private_: Usado para iniciar o IE no modo de navegação privada. Isso funciona para o IE 8 e versões posteriores.

* _-k_: Inicia o Internet Explorer no modo quiosque.
O navegador é aberto em uma janela maximizada que não exibe a barra de endereço, os botões de navegação ou a barra de status.

* _-extoff_: Inicia o IE no modo sem add-on.
Esta opção é usada especificamente para solucionar problemas com complementos do navegador. Funciona no IE 7 e versões posteriores.

Nota: __forceCreateProcessApi__ deve ser habilitado para que os argumentos da linha de comando funcionem.

**Java**

```java
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class ieTest {
    public static void main(String[] args) {
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.useCreateProcessApiToLaunchIe();
        options.addCommandSwitches("-k");
        InternetExplorerDriver driver = new InternetExplorerDriver(options);
        try {
            driver.get("https://google.com/ncr");
            Capabilities caps = driver.getCapabilities();
            System.out.println(caps);
        } finally {
            driver.quit();
        }
    }
}

```

**Python**

```python
options = webdriver.IeOptions()
options.add_argument("-private")

driver = webdriver.Ie(options=options)
```

**CSharp**

```csharp
using System;
using OpenQA.Selenium;
using OpenQA.Selenium.IE;

namespace ieTest {
 class Program {
  static void Main(string[] args) {
   InternetExplorerOptions options = new InternetExplorerOptions();
   options.ForceCreateProcessApi = true;
   options.BrowserCommandLineArguments = "-k";
   IWebDriver driver = new InternetExplorerDriver(options);
   driver.Url = "https://google.com/ncr";
  }
 }
}

```

**Ruby**

```ruby
@options.add_argument('-k')
```

**JavaScript**

```javascript
const ie = require('selenium-webdriver/ie');
let options = new ie.Options();
options.addBrowserCommandSwitches('-k');
options.addBrowserCommandSwitches('-private');
options.forceCreateProcessApi(true);

driver = await env.builder()
          .setIeOptions(options)
          .build();

```

**Kotlin**

```kotlin
import org.openqa.selenium.Capabilities
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.ie.InternetExplorerOptions

fun main() {
    val options = InternetExplorerOptions()
    options.useCreateProcessApiToLaunchIe()
    options.addCommandSwitches("-k")
    val driver = InternetExplorerDriver(options)
    try {
        driver.get("https://google.com/ncr")
        val caps = driver.getCapabilities()
        println(caps)
    } finally {
        driver.quit()
    }
}

```

### forceCreateProcessApi

Força a inicialização do Internet Explorer
usando a API CreateProcess. O valor padrão é falso.

Para IE 8 e superior, esta opção requer que
o valor de registro "TabProcGrowth" seja definido como 0.

**Java**

```java
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class ieTest {
    public static void main(String[] args) {
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.useCreateProcessApiToLaunchIe();
        InternetExplorerDriver driver = new InternetExplorerDriver(options);
        try {
            driver.get("https://google.com/ncr");
            Capabilities caps = driver.getCapabilities();
            System.out.println(caps);
        } finally {
            driver.quit();
        }
    }
}

```

**Python**

```python
options = webdriver.IeOptions()
options.force_create_process_api = True

driver = webdriver.Ie(options=options)
```

**CSharp**

```csharp
using System;
using OpenQA.Selenium;
using OpenQA.Selenium.IE;

namespace ieTest {
 class Program {
  static void Main(string[] args) {
   InternetExplorerOptions options = new InternetExplorerOptions();
   options.ForceCreateProcessApi = true;
   IWebDriver driver = new InternetExplorerDriver(options);
   driver.Url = "https://google.com/ncr";
  }
 }
}

```

**Ruby**

```ruby
@options.force_create_process_api = true
```

**JavaScript**

```javascript
const ie = require('selenium-webdriver/ie');
let options = new ie.Options();
options.forceCreateProcessApi(true);

driver = await env.builder()
          .setIeOptions(options)
          .build();

```

**Kotlin**

```kotlin
import org.openqa.selenium.Capabilities
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.ie.InternetExplorerOptions

fun main() {
    val options = InternetExplorerOptions()
    options.useCreateProcessApiToLaunchIe()
    val driver = InternetExplorerDriver(options)
    try {
        driver.get("https://google.com/ncr")
        val caps = driver.getCapabilities()
        println(caps)
    } finally {
        driver.quit()
    }
}

```

## Service

Service settings common to all browsers are described on the [Service page](https://www.selenium.dev/pt-br/documentation/webdriver/drivers/service/index.md).

### Log output

Getting driver logs can be helpful for debugging various issues. The Service class lets you
direct where the logs will go. Logging output is ignored unless the user directs it somewhere.

#### File output

To change the logging output to save to a specific file:

**Java**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```java
.withLogFile(getLogLocation())
```

**Note**: Java also allows setting file output by System Property:<br>
Property key: `InternetExplorerDriverService.IE_DRIVER_LOGFILE_PROPERTY`<br>
Property value: String representing path to log file

**Python**

```python
service = webdriver.IeService(log_output=log_path, log_level="INFO")

driver = webdriver.Ie(service=service)
```

**CSharp**

_Not available in this language._

**Ruby**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```ruby
service.log = file_name
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

#### Console output

To change the logging output to display in the console as STDOUT:

**Java**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```java
.withLogOutput(System.out)
```

**Note**: Java also allows setting console output by System Property;<br>
Property key: `InternetExplorerDriverService.IE_DRIVER_LOGFILE_PROPERTY`<br>
Property value: `DriverService.LOG_STDOUT` or `DriverService.LOG_STDERR`

**Python**

_Requires [Selenium v4.11](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.11.0) or later._

```python
service = webdriver.IeService(log_output=subprocess.STDOUT)

driver = webdriver.Ie(service=service)
```

**CSharp**

_Not available in this language._

**Ruby**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```ruby
service.log = $stdout
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Log Level
There are 6 available log levels: `FATAL`, `ERROR`, `WARN`, `INFO`, `DEBUG`, and `TRACE`
If logging output is specified, the default level is `FATAL`

**Java**

```java
.withLogLevel(InternetExplorerDriverLogLevel.WARN)
```

**Note**: Java also allows setting log level by System Property:<br>
Property key: `InternetExplorerDriverService.IE_DRIVER_LOGLEVEL_PROPERTY`<br>
Property value: String representation of `InternetExplorerDriverLogLevel.DEBUG.toString()` enum

**Python**

```python
service = webdriver.IeService(log_output=log_path, log_level="WARN")

driver = webdriver.Ie(service=service)
```

**CSharp**

```csharp
if (_tempPath == null || !File.Exists(_tempPath))
```

**Ruby**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```ruby
service.args << '-log-level=WARN'
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Supporting Files Path

**Java**

```java
.withExtractPath(getTempDirectory())
```

**Note**: Java also allows setting log level by System Property:\
Property key: `InternetExplorerDriverService.IE_DRIVER_EXTRACT_PATH_PROPERTY`\
Property value: String representing path to supporting files directory

**Python**

_Requires [Selenium v4.11](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.11.0) or later._

```python
service = webdriver.IeService(service_args=["–extract-path=" + temp_dir])

driver = webdriver.Ie(service=service)
```

**CSharp**

```csharp
}
```

**Ruby**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```ruby
service.args << "–extract-path=#{root_directory}"
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._
