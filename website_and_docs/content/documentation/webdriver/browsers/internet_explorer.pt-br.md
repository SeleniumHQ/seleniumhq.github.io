---
title: "Funcionalidade específica do IE"
linkTitle: "Internet Explorer"
weight: 8
description: >-
    Estas capacidades e características são específicas ao navegador Microsoft Internet Explorer.
aliases: [
"/pt-br/documentation/capabilities/internet_explorer"
]
---

Desde Junho de 2022, o Projecto Selenium deixou de suportar oficialmente o navegador Internet Explorer.
O driver Internet Explorer continua a suportar a execução do Microsoft Edge no modo "IE Compatibility Mode."

## Considerações especiais

O IE Driver é o único driver mantido directamente pelo Projecto Selenium.
Embora existam binários para as versões de 32 e 64 bits, existem algumas
[limitações conhecidas](//jimevansmusic.blogspot.co.uk/2014/09/screenshots-sendkeys-and-sixty-four.html)
com o driver de 64 bits. Desta forma, recomenda-se a utilização do driver de 32 bits.

Informação adicional sobre como usar o Internet Explorer pode ser encontrada na
[página IE Driver Server]({{< ref "/documentation/ie_driver_server/" >}})

## Opções

Este é um exemplo de como iniciar o navegador Microsoft Edge em modo compatibilidade Internet Explorer
usando um conjunto de opções básicas:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/InternetExplorerTest.java#38-L41" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_internet_explorer.py#L11-L14" >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/InternetExplorerTest.cs#L35-L38" >}}
{{% /tab %}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/internet_explorer_spec.rb#L10-L13" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

A partir do driver versão v4.5.0:
* Se o IE não estiver presente no sistema (ausente por omissão no Windows 11), não necessita
usar os parametros "attachToEdgeChrome" e "withEdgeExecutablePath", pois o IE Driver 
irá encontrar e usar o Edge automaticamente.
* Se o IE e o Edge estiverem ambos presentes no sistema, use o parametro "attachToEdgeChrome",
o IE Driver irá encontrar e usar o Edge automaticamente.

So, if IE is not on the system, you only need:

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/InternetExplorerTest.java#46-L47" >}}
{{< /tab >}}
{{% tab header="Python" text=true %}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_internet_explorer.py#L21-L22" >}}
{{% /tab %}}
{{% tab header="CSharp" text=true %}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/InternetExplorerTest.cs#L44-L45" >}}
{{% /tab %}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/internet_explorer_spec.rb#L17-L18" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
let driver = await new Builder()
.forBrowser('internet explorer')
.setIEOptions(options)
.build();
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-examples >}}
val options = InternetExplorerOptions()
val driver = InternetExplorerDriver(options)
{{< /tab >}}
{{< /tabpane >}}

Aqui pode ver alguns exemplos de utilização com capacidades diferentes:

### fileUploadDialogTimeout

Em alguns ambientes, o Internet Explorer pode expirar ao abrir a
Caixa de Diálogo de upload de arquivo. O IEDriver tem um tempo limite padrão de 1000 ms, mas você
pode aumentar o tempo limite usando o recurso fileUploadDialogTimeout.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
InternetExplorerOptions options = new InternetExplorerOptions();
options.waitForUploadDialogUpTo(Duration.ofSeconds(2));
WebDriver driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver

options = webdriver.IeOptions()
options.file_upload_dialog_timeout = 2000
driver = webdriver.Ie(options=options)

# Navegar para Url
driver.get("http://www.google.com")

driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
var options = new InternetExplorerOptions();
options.FileUploadDialogTimeout = TimeSpan.FromMilliseconds(2000);
var driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
options = Selenium::WebDriver::IE::Options.new
options.file_upload_dialog_timeout = 2000
driver = Selenium::WebDriver.for(:ie, options: options)
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const ie = require('selenium-webdriver/ie');
let options = new ie.Options().fileUploadDialogTimeout(2000);
let driver = await Builder()
          .setIeOptions(options)
          .build(); 
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val options = InternetExplorerOptions()
options.waitForUploadDialogUpTo(Duration.ofSeconds(2))
val driver = RemoteWebDriver(options)
  {{< /tab >}}
{{< /tabpane >}}

### ensureCleanSession

Quando definido como `true`, este recurso limpa o _Cache,
Histórico do navegador e cookies_ para todas as instâncias em execução
do InternetExplorer, incluindo aquelas iniciadas manualmente
ou pelo driver. Por padrão, é definido como `false`.

Usar este recurso causará queda de desempenho quando
iniciar o navegador, pois o driver irá esperar até que o cache
seja limpo antes de iniciar o navegador IE.

Esse recurso aceita um valor booleano como parâmetro.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
InternetExplorerOptions options = new InternetExplorerOptions();
options.destructivelyEnsureCleanSession();
WebDriver driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver

options = webdriver.IeOptions()
options.ensure_clean_session = True
driver = webdriver.Ie(options=options)

# Navegar para Url
driver.get("http://www.google.com")

driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
var options = new InternetExplorerOptions();
options.EnsureCleanSession = true;
var driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
options = Selenium::WebDriver::IE::Options.new
options.ensure_clean_session = true
driver = Selenium::WebDriver.for(:ie, options: options)
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const ie = require('selenium-webdriver/ie');
let options = new ie.Options().ensureCleanSession(true);
let driver = await Builder()
          .setIeOptions(options)
          .build(); 
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val options = InternetExplorerOptions()
options.destructivelyEnsureCleanSession()
val driver = RemoteWebDriver(options)
  {{< /tab >}}
{{< /tabpane >}}

### ignoreZoomSetting

O driver do InternetExplorer espera que o nível de zoom do navegador seja de 100%,
caso contrário, o driver lançará uma exceção. Este comportamento padrão
pode ser desativado definindo _ignoreZoomSetting_ como _true_.

Esse recurso aceita um valor booleano como parâmetro.
 
{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
InternetExplorerOptions options = new InternetExplorerOptions();
options.ignoreZoomSettings();
WebDriver driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver

options = webdriver.IeOptions()
options.ignore_zoom_level = True
driver = webdriver.Ie(options=options)

# Navegar para Url
driver.get("http://www.google.com")

driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
var options = new InternetExplorerOptions();
options.IgnoreZoomLevel = true;
var driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
options = Selenium::WebDriver::IE::Options.new
options.ignore_zoom_level = true
driver = Selenium::WebDriver.for(:ie, options: options)
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const ie = require('selenium-webdriver/ie');
let options = new ie.Options().ignoreZoomSetting(true);
let driver = await Builder()
          .setIeOptions(options)
          .build(); 
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val options = InternetExplorerOptions()
options.ignoreZoomSettings()
val driver = RemoteWebDriver(options)
  {{< /tab >}}
{{< /tabpane >}}

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
 
{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
InternetExplorerOptions options = new InternetExplorerOptions();
options.introduceFlakinessByIgnoringSecurityDomains();
WebDriver driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver

options = webdriver.IeOptions()
options.ignore_protected_mode_settings = True
driver = webdriver.Ie(options=options)

# Navegar para Url
driver.get("http://www.google.com")

driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
var options = new InternetExplorerOptions();
options.IntroduceInstabilityByIgnoringProtectedModeSettings = true;
var driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
options = Selenium::WebDriver::IE::Options.new
options.ignore_protected_mode_settings = true
driver = Selenium::WebDriver.for(:ie, options: options)
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const ie = require('selenium-webdriver/ie');
let options = new ie.Options().introduceFlakinessByIgnoringProtectedModeSettings(true);
let driver = await Builder()
          .setIeOptions(options)
          .build(); 
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val options = InternetExplorerOptions()
options.introduceFlakinessByIgnoringSecurityDomains()
val driver = RemoteWebDriver(options)
  {{< /tab >}}
{{< /tabpane >}}

### silent

Quando definido como `true`, esse recurso suprime a
saída de diagnóstico do IEDriverServer.

Esse recurso aceita um valor booleano como parâmetro.
 
{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
InternetExplorerOptions options = new InternetExplorerOptions();
options.setCapability("silent", true);
WebDriver driver = new InternetExplorerDriver(options);
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver

options = webdriver.IeOptions()
options.set_capability("silent", True)
driver = webdriver.Ie(options=options)

# Navegar para Url
driver.get("http://www.google.com")

driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
InternetExplorerOptions options = new InternetExplorerOptions();
options.AddAdditionalInternetExplorerOption("silent", true);
IWebDriver driver = new InternetExplorerDriver(options);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    # Por favor inclua um PR para adicionar uma amostra de código
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
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
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
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
  {{< /tab >}}
{{< /tabpane >}}

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

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
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
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver

options = webdriver.IeOptions()
options.add_argument('-private')
options.force_create_process_api = True
driver = webdriver.Ie(options=options)

# Navegar para Url
driver.get("http://www.google.com")

driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
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
  {{< /tab >}}
  {{< tab header="Ruby" >}}
require 'selenium-webdriver'
options = Selenium::WebDriver::IE::Options.new
options.force_create_process_api = true
options.add_argument('-k')
driver = Selenium::WebDriver.for(:ie, options: options)

begin
  # Navegar para URL
  driver.get 'https://google.com'
  puts(driver.capabilities.to_json)
ensure
  driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const ie = require('selenium-webdriver/ie');
let options = new ie.Options();
options.addBrowserCommandSwitches('-k');
options.addBrowserCommandSwitches('-private');
options.forceCreateProcessApi(true);

driver = await env.builder()
          .setIeOptions(options)
          .build();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}

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
  {{< /tab >}}
{{< /tabpane >}}

### forceCreateProcessApi

Força a inicialização do Internet Explorer
usando a API CreateProcess. O valor padrão é falso.

Para IE 8 e superior, esta opção requer que
o valor de registro "TabProcGrowth" seja definido como 0.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
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
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver

options = webdriver.IeOptions()
options.force_create_process_api = True
driver = webdriver.Ie(options=options)

# Navegar para Url
driver.get("http://www.google.com")

driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
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
  {{< /tab >}}
  {{< tab header="Ruby" >}}
require 'selenium-webdriver'
options = Selenium::WebDriver::IE::Options.new
options.force_create_process_api = true
driver = Selenium::WebDriver.for(:ie, options: options)

begin
  # Navegar para Url
  driver.get 'https://google.com'
  puts(driver.capabilities.to_json)
ensure
  driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const ie = require('selenium-webdriver/ie');
let options = new ie.Options();
options.forceCreateProcessApi(true);

driver = await env.builder()
          .setIeOptions(options)
          .build();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
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
  {{< /tab >}}
{{< /tabpane >}}



## Service

Service settings common to all browsers are described on the [Service page]({{< ref "../drivers/service.md" >}}).

### Log output

Getting driver logs can be helpful for debugging various issues. The Service class lets you
direct where the logs will go. Logging output is ignored unless the user directs it somewhere.

#### File output

To change the logging output to save to a specific file:

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/InternetExplorerTest.java#L53" >}}
**Note**: Java also allows setting file output by System Property:\
Property key: `InternetExplorerDriverService.IE_DRIVER_LOGFILE_PROPERTY`\
Property value: String representing path to log file
{{% /tab %}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/browsers/test_internet_explorer.py#L29" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/ruby/spec/browsers/internet_explorer_spec.rb#L34" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

#### Console output

To change the logging output to display in the console as STDOUT:

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/InternetExplorerTest.java#L67" >}}
**Note**: Java also allows setting console output by System Property;\
Property key: `InternetExplorerDriverService.IE_DRIVER_LOGFILE_PROPERTY`\
Property value: `DriverService.LOG_STDOUT` or `DriverService.LOG_STDERR`
{{% /tab %}}
{{< tab header="Python" >}}
{{< badge-version version="4.11" >}}
{{< gh-codeblock path="examples/python/tests/browsers/test_internet_explorer.py#L41" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/ruby/spec/browsers/internet_explorer_spec.rb#L43" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Log Level
There are 6 available log levels: `FATAL`, `ERROR`, `WARN`, `INFO`, `DEBUG`, and `TRACE`
If logging output is specified, the default level is `FATAL`

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/InternetExplorerTest.java#L82" >}}
**Note**: Java also allows setting log level by System Property:\
Property key: `InternetExplorerDriverService.IE_DRIVER_LOGLEVEL_PROPERTY`\
Property value: String representation of `InternetExplorerDriverLogLevel.DEBUG.toString()` enum
{{% /tab %}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/browsers/test_internet_explorer.py#L53" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Browsers/InternetExplorerTest.cs#L85" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/ruby/spec/browsers/internet_explorer_spec.rb#L54" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Supporting Files Path

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/InternetExplorerTest.java#L94" >}}
**Note**: Java also allows setting log level by System Property:\
Property key: `InternetExplorerDriverService.IE_DRIVER_EXTRACT_PATH_PROPERTY`\
Property value: String representing path to supporting files directory
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.11" >}}
{{< gh-codeblock path="examples/python/tests/browsers/test_internet_explorer.py#L65" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Browsers/InternetExplorerTest.cs#L98" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="examples/ruby/spec/browsers/internet_explorer_spec.rb#L64" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

