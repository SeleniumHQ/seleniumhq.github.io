---
title: "Browser Options"
linkTitle: "Options"
weight: 2
needsTranslation: true
description: >-
  These capabilities are shared by all browsers.
aliases: [
"/documentation/pt-br/driver_idiosyncrasies/shared_capabilities/",
"/pt-br/documentation/webdriver/capabilities/shared_capabilities/",
"/documentation/pt-br/webdriver/http_proxies/",
"/pt-br/documentation/webdriver/http_proxies/",
"/pt-br/documentation/webdriver/capabilities/http_proxies/",
"/documentation/pt-br/webdriver/page_loading_strategy/",
"/pt-br/documentation/webdriver/page_loading_strategy/",
"/pt-br/documentation/webdriver/capabilities/page_loading_strategy/",
"/pt-br/documentation/capabilitis/shared/",
]
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from English to Portuguese. 
   Do you speak Portuguese? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

In Selenium 3, capabilities were defined in a session by using Desired Capabilities classes.
As of Selenium 4, you must use the browser options classes.
For remote driver sessions, a browser options instance is required as it determines which browser will be used.

These options are described in the w3c specification for [Capabilities](https://w3c.github.io/webdriver/#capabilities).

Each browser has ({{< ref "../browsers/" >}}) that may be defined in addition to the ones defined in the specification.

## browserName

Este recurso é usado para definir o `browserName` para uma determinada sessão.
Se o navegador especificado não estiver instalado na
extremidade remota, a criação da sessão irá falhar

## browserVersion

Este recurso é opcional, usado para
definir a versão do navegador disponível na extremidade remota.
Por exemplo, se pedir o Chrome versão 75 em um sistema que
tem apenas a versão 80 instalada, a criação da sessão irá falhar

## pageLoadStrategy

Three types of page load strategies are available.

The page load strategy queries the 
[document.readyState](//developer.mozilla.org/en-US/docs/Web/API/Document/readyState)
as described in the table below:

| Strategy | Ready State | Notes |
| -------- | ----------- | ----- |
| normal | complete | Used by default, waits for all resources to download |
| eager | interactive | DOM access is ready, but other resources like images may still be loading |
| none | Any | Does not block WebDriver at all |

The `document.readyState` property of a document describes the loading state of the current document.

When navigating to a new page via URL, by default, WebDriver will hold off on completing a navigation 
method (e.g., driver.navigate().get()) until the document ready state is complete. This _does not 
necessarily mean that the page has finished loading_, especially for sites like Single Page Applications 
that use JavaScript to dynamically load content after the Ready State returns complete. Note also 
that this behavior does not apply to navigation that is a result of clicking an element or submitting a form.

If a page takes a long time to load as a result of downloading assets (e.g., images, css, js) 
that aren't important to the automation, you can change from the default parameter of `normal` to
`eager` or `none` to speed up the session. This value applies to the entire session, so make sure 
that your [waiting strategy]({{< ref "/documentation/webdriver/waits.md" >}}) is sufficient to minimize 
flakiness.


### normal (default)

WebDriver waits until the [load](https://developer.mozilla.org/en-US/docs/Web/API/Window/load_event) 
event fire is returned.

{{< tabpane langEqualsHeader=true code=false >}}
{{< tab header="Java" code=true >}}
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

public class pageLoadStrategy {
  public static void main(String[] args) {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
    WebDriver driver = new ChromeDriver(chromeOptions);
    try {
      // Navigate to Url
      driver.get("https://google.com");
    } finally {
      driver.quit();
    }
  }
}
{{< /tab >}}
{{< tab header="Python" code=true >}}
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
options = Options()
options.page_load_strategy = 'normal'
driver = webdriver.Chrome(options=options)
driver.get("http://www.google.com")
driver.quit()
{{< /tab >}}
{{< tab header="CSharp" code=true >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace pageLoadStrategy {
  class pageLoadStrategy {
    public static void Main(string[] args) {
      var chromeOptions = new ChromeOptions();
      chromeOptions.PageLoadStrategy = PageLoadStrategy.Normal;
      IWebDriver driver = new ChromeDriver(chromeOptions);
      try {
        driver.Navigate().GoToUrl("https://example.com");
      } finally {
        driver.Quit();
      }
    }
  }
}
{{< /tab >}}
{{< tab header="Ruby" code=true >}}
require 'selenium-webdriver'
caps = Selenium::WebDriver::Remote::Capabilities.chrome
caps.page_load_strategy='normal'

driver = Selenium::WebDriver.for :chrome, :desired_capabilities => caps
driver.get('https://www.google.com')
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/capabilities/pageLoading.spec.js#L27-L33">}}
{{< /tab >}}
{{< tab header="Kotlin" code=true >}}
import org.openqa.selenium.PageLoadStrategy
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

fun main() {
  val chromeOptions = ChromeOptions()
  chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL)
  val driver = ChromeDriver(chromeOptions)
  try {
    driver.get("https://www.google.com")
  }
  finally {
    driver.quit()
  }
}
{{< /tab >}}
{{< /tabpane >}}

### eager

WebDriver waits until [DOMContentLoaded](https://developer.mozilla.org/en-US/docs/Web/API/Document/DOMContentLoaded_event) 
event fire is returned.

{{< tabpane langEqualsHeader=true code=false >}}
{{< tab header="Java" code=true >}}
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

public class pageLoadStrategy {
  public static void main(String[] args) {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
    WebDriver driver = new ChromeDriver(chromeOptions);
    try {
      // Navigate to Url
      driver.get("https://google.com");
    } finally {
      driver.quit();
    }
  }
}
{{< /tab >}}
{{< tab header="Python" code=true >}}
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
options = Options()
options.page_load_strategy = 'eager'
driver = webdriver.Chrome(options=options)
driver.get("http://www.google.com")
driver.quit()
{{< /tab >}}
{{< tab header="CSharp" code=true >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace pageLoadStrategy {
  class pageLoadStrategy {
    public static void Main(string[] args) {
      var chromeOptions = new ChromeOptions();
      chromeOptions.PageLoadStrategy = PageLoadStrategy.Eager;
      IWebDriver driver = new ChromeDriver(chromeOptions);
      try {
        driver.Navigate().GoToUrl("https://example.com");
      } finally {
        driver.Quit();
      }
    }
  }
}
{{< /tab >}}
{{< tab header="Ruby" code=true >}}
require 'selenium-webdriver'
caps = Selenium::WebDriver::Remote::Capabilities.chrome
caps.page_load_strategy='eager'

driver = Selenium::WebDriver.for :chrome, :desired_capabilities => caps
driver.get('https://www.google.com')
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/capabilities/pageLoading.spec.js#L7-L13">}}
{{< /tab >}}
{{< tab header="Kotlin" code=true >}}
import org.openqa.selenium.PageLoadStrategy
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

fun main() {
  val chromeOptions = ChromeOptions()
  chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER)
  val driver = ChromeDriver(chromeOptions)
  try {
    driver.get("https://www.google.com")
  }
  finally {
    driver.quit()
  }
}
{{< /tab >}}
{{< /tabpane >}}

### none

WebDriver only waits until the initial page is downloaded.

{{< tabpane langEqualsHeader=true code=false >}}
{{< tab header="Java" code=true >}}
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

public class pageLoadStrategy {
  public static void main(String[] args) {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE);
    WebDriver driver = new ChromeDriver(chromeOptions);
    try {
      // Navigate to Url
      driver.get("https://google.com");
    } finally {
      driver.quit();
    }
  }
}
{{< /tab >}}
{{< tab header="Python" code=true >}}
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
options = Options()
options.page_load_strategy = 'none'
driver = webdriver.Chrome(options=options)
driver.get("http://www.google.com")
driver.quit()
{{< /tab >}}
{{< tab header="CSharp" code=true >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace pageLoadStrategy {
  class pageLoadStrategy {
    public static void Main(string[] args) {
      var chromeOptions = new ChromeOptions();
      chromeOptions.PageLoadStrategy = PageLoadStrategy.None;
      IWebDriver driver = new ChromeDriver(chromeOptions);
      try {
        driver.Navigate().GoToUrl("https://example.com");
      } finally {
        driver.Quit();
      }
    }
  }
}
{{< /tab >}}
{{< tab header="Ruby" code=true >}}
require 'selenium-webdriver'
caps = Selenium::WebDriver::Remote::Capabilities.chrome
caps.page_load_strategy='none'

driver = Selenium::WebDriver.for :chrome, :desired_capabilities => caps
driver.get('https://www.google.com')
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/capabilities/pageLoading.spec.js#L17-L23">}}
{{< /tab >}}
{{< tab header="Kotlin" code=true >}}
import org.openqa.selenium.PageLoadStrategy
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

fun main() {
  val chromeOptions = ChromeOptions()
  chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE)
  val driver = ChromeDriver(chromeOptions)
  try {
    driver.get("https://www.google.com")
  }
  finally {
    driver.quit()
  }
}
{{< /tab >}}
{{< /tabpane >}}

## platformName

Isso identifica o sistema operacional na extremidade remota e 
buscar o `platformName` retorna o nome do sistema operacional.

Em provedores baseados em nuvem,
definir `platformName` define o sistema operacional na extremidade remota.

## acceptInsecureCerts

Este recurso verifica se um `Certificado TLS`
expirado (ou) inválido é usado durante a navegação
durante uma sessão.

Se o recurso for definido como `false`, um
[erro de certificado inseguro](//developer.mozilla.org/en-US/docs/Web/WebDriver/Errors/InsecureCertificate)
será retornado quando a navegação encontrar qualquer
problema de certificado de domínio. Se definido como `verdadeiro`, o certificado inválido será
confiável para o navegador.

Todos os certificados autoassinados serão considerados confiáveis por esse recurso por padrão.
Uma vez definido, o recurso `acceptInsecureCerts` terá um
efeito para toda a sessão.

## timeout

Uma `session` do WebDriver é imposta com um certo intervalo `session timeout`
durante o qual o usuário pode controlar o comportamento
de executar scripts ou recuperar informações do navegador.

Cada tempo limite de sessão é configurado com
combinação de diferentes `timeouts`, conforme descrito abaixo:

### Timeout de Script:
Especifica quando interromper um script em execução em
um contexto de navegação atual. O tempo limite padrão **30.000**
é imposto quando uma nova sessão é criada pelo WebDriver.

### Timeout de Carregamento de Página:
Especifica o intervalo de tempo em que a página da web
precisa ser carregado em um contexto de navegação atual.
O tempo limite padrão **300.000** é imposto quando uma
nova sessão é criada pelo WebDriver. Se os carregamento da página delimitar
um determinado período de tempo, o script será interrompido por
_TimeoutException_.

### Timeout de Espera Implícita:
Isso especifica o tempo de espera pela
estratégia de implicit element location quando
localizando de elementos. O tempo limite padrão **0**
é imposto quando uma nova sessão é criada pelo WebDriver.

## unhandledPromptBehavior

Especifica o estado do `user prompt handler` da sessão atual.
O padrão é **dismiss and notify state** (**dispensar e notificar estado**, em português)

### User Prompt Handler

Isso define qual ação deve ser tomada quando um
o prompt do usuário se encontra na extremidade remota. Isso é definido pelo
recurso `unhandledPromptBehavior` e tem os seguintes estados:

* dismiss
* accept
* dismiss and notify
* accept and notify
* ignore

## setWindowRect

Indicates whether the remote end supports all of the [resizing and repositioning](https://w3c.github.io/webdriver/#resizing-and-positioning-windows) [commands](https://w3c.github.io/webdriver/#dfn-commands).

## strictFileInteractability

O novo recurso indica se as verificações estritas de interatividade
devem ser aplicadas aos elementos _input type = file_. Como as verificações de
interatividade estrita estão desativadas por padrão, há uma mudança no comportamento
ao usar _Element Send Keys_ com controles de upload de arquivos ocultos.

## proxy

Um servidor proxy atua como intermediário para
solicitações entre um cliente e um servidor. De forma simples,
o tráfego flui através do servidor proxy
a caminho do endereço que você solicitou e de volta.

Um servidor proxy para scripts de automação
com Selenium pode ser útil para:

* Capturar o tráfego da rede
* Simular chamadas de back-end feitas pelo site
* Acessar o site necessário em uma rede complexa
  topologias ou restrições / políticas corporativas estritas.

Se você estiver em um ambiente corporativo, e um
navegador não consegue se conectar a um URL, isso é
provavelmente porque o ambiente precisa de um proxy para ser acessado.

O Selenium WebDriver fornece uma maneira de configurações de proxy:

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class proxyTest {
public static void main(String[] args) {
Proxy proxy = new Proxy();
proxy.setHttpProxy("<HOST:PORT>");
ChromeOptions options = new ChromeOptions();
options.setCapability("proxy", proxy);
WebDriver driver = new ChromeDriver(options);
driver.get("https://www.google.com/");
driver.manage().window().maximize();
driver.quit();
}
}
{{< /tab >}}
{{< tab header="Python" >}}
from selenium import webdriver

PROXY = "<HOST:PORT>"
webdriver.DesiredCapabilities.FIREFOX['proxy'] = {
"httpProxy": PROXY,
"ftpProxy": PROXY,
"sslProxy": PROXY,
"proxyType": "MANUAL",

}

with webdriver.Firefox() as driver:
# Open URL
driver.get("https://selenium.dev")

{{< /tab >}}
{{< tab header="CSharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

public class ProxyTest{
public static void Main() {
ChromeOptions options = new ChromeOptions();
Proxy proxy = new Proxy();
proxy.Kind = ProxyKind.Manual;
proxy.IsAutoDetect = false;
proxy.SslProxy = "<HOST:PORT>";
options.Proxy = proxy;
options.AddArgument("ignore-certificate-errors");
IWebDriver driver = new ChromeDriver(options);
driver.Navigate().GoToUrl("https://www.selenium.dev/");
}
}
{{< /tab >}}
{{< tab header="Ruby" >}}
# este código foi escrito com Selenium 4

proxy = Selenium::WebDriver::Proxy.new(http: '<HOST:PORT>')
cap   = Selenium::WebDriver::Remote::Capabilities.chrome(proxy: proxy)

driver = Selenium::WebDriver.for(:chrome, capabilities: cap)
driver.get('http://google.com')
{{< /tab >}}
{{< tab header="JavaScript" >}}
let webdriver = require('selenium-webdriver');
let chrome = require('selenium-webdriver/chrome');
let proxy = require('selenium-webdriver/proxy');
let opts = new chrome.Options();

(async function example() {
opts.setProxy(proxy.manual({http: '<HOST:PORT>'}));
let driver = new webdriver.Builder()
.forBrowser('chrome')
.setChromeOptions(opts)
.build();
try {
await driver.get("https://selenium.dev");
}
finally {
await driver.quit();
}
}());
{{< /tab >}}
{{< tab header="Kotlin" >}}
import org.openqa.selenium.Proxy
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

class proxyTest {
fun main() {

        val proxy = Proxy()
        proxy.setHttpProxy("<HOST:PORT>")
        val options = ChromeOptions()
        options.setCapability("proxy", proxy)
        val driver: WebDriver = ChromeDriver(options)
        driver["https://www.google.com/"]
        driver.manage().window().maximize()
        driver.quit()
    }
}
{{< /tab >}}
{{< /tabpane >}}
