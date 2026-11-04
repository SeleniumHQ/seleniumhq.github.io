# Opções do navegador

> Esses recursos são compartilhados por todos os navegadores.

URL: https://www.selenium.dev/pt-br/documentation/webdriver/drivers/options/

---

> <p class="lead">
>    <i class="fas fa-language d-4"></i> 
>    Page being translated from English to Portuguese. 
>    Do you speak Portuguese? Help us to translate
>    it by sending us pull requests!
> 

No Selenium 3, os recursos foram definidos em uma sessão usando classes de recursos desejados.
A partir do Selenium 4, você deve usar as classes de opções do navegador.
Para sessões remotas de driver, uma instância de opções do navegador é necessária, pois determina qual navegador será usado.

Essas opções são descritas na especificação w3c para [Capabilities](https://w3c.github.io/webdriver/#capabilities).

Cada navegador tem [custom options](https://www.selenium.dev/pt-br/documentation/webdriver/browsers/index.md) que podem ser definidas além das definidas na especificação.

## browserName

Esta capacidade é usada para definir o `browserName` para uma determinada sessão.
Se o navegador especificado não estiver instalado no
extremidade remota, a criação da sessão falhará.

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
String name = chromeOptions.getBrowserName();
```

**Python**

```python
options = get_default_chrome_options()
assert options.capabilities['browserName'] == 'chrome'
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

## browserVersion

Esta capacidade é opcional, é usada para
defina a versão do navegador disponível na extremidade remota.
Por exemplo, se solicitar o Chrome versão 75 em um sistema que
tiver apenas 80 instalados, a criação da sessão falhará.

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
String version = "latest";
chromeOptions.setBrowserVersion(version);
```

**Python**

```python
options = get_default_chrome_options()
options.browser_version = 'stable'
assert options.capabilities['browserVersion'] == 'stable'
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
options.browser_version = 'latest'
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

## pageLoadStrategy

Três tipos de estratégias de carregamento de página estão disponíveis.

A estratégia de carregamento da página consulta o
[document.readyState](//developer.mozilla.org/en-US/docs/Web/API/Document/readyState)
conforme descrito na tabela abaixo:

| Estratégia | Estado pronto | Notas |
| -------- | ----------- | ----- |
| normal | completo | Usado por padrão, aguarda o download de todos os recursos |
| ansioso | interativo | O acesso DOM está pronto, mas outros recursos como imagens ainda podem estar carregando |
| nenhum | Qualquer | Não bloqueia o WebDriver |

A propriedade `document.readyState` de um documento descreve o estado de carregamento do documento atual.

Ao navegar para uma nova página via URL, por padrão, o WebDriver irá adiar a conclusão de uma navegação
(por exemplo, driver.navigate().get()) até que o estado pronto do documento seja concluído. isso _não
significa necessariamente que a página terminou de carregar_, especialmente para sites como Single Page Applications
que usam JavaScript para carregar conteúdo dinamicamente depois que o estado Pronto retorna completo. Observe também
que esse comportamento não se aplica à navegação resultante de clicar em um elemento ou enviar um formulário.

Se uma página demorar muito para carregar como resultado do download de ativos (por exemplo, imagens, css, js)
que não são importantes para a automação, você pode mudar do parâmetro padrão de `normal` para
`eager` ou `none` para acelerar a sessão. Esse valor se aplica a toda a sessão, portanto, certifique-se
que sua [waiting strategy](https://www.selenium.dev/pt-br/documentation/webdriver/waits/index.md) é suficiente para minimizar
descamação.

### normal (default)

WebDriver waits until the [load](https://developer.mozilla.org/en-US/docs/Web/API/Window/load_event) 
event fire is returned.

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
WebDriver driver = new ChromeDriver(chromeOptions);
```

**Python**

```python
options = get_default_chrome_options()
options.page_load_strategy = 'normal'
driver = webdriver.Chrome(options=options)
```

**CSharp**

```csharp
ChromeOptions chromeOptions = new ChromeOptions();
chromeOptions.PageLoadStrategy = PageLoadStrategy.Normal;
```

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.page_load_strategy = :normal
```

**JavaScript**

```javascript
let driver = new Builder()
  .forBrowser(Browser.CHROME)
  .setChromeOptions(options.setPageLoadStrategy('normal'))
  .build();

await driver.get('https://www.selenium.dev/selenium/web/blank.html');
await driver.quit();
```

**Kotlin**

```kotlin
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
```

### eager

WebDriver waits until [DOMContentLoaded](https://developer.mozilla.org/en-US/docs/Web/API/Document/DOMContentLoaded_event) 
event fire is returned.

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
WebDriver driver = new ChromeDriver(chromeOptions);
```

**Python**

```python
options = get_default_chrome_options()
options.page_load_strategy = 'eager'
driver = webdriver.Chrome(options=options)
```

**CSharp**

```csharp
var chromeOptions = new ChromeOptions();
chromeOptions.PageLoadStrategy = PageLoadStrategy.Eager;
```

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.page_load_strategy = :eager
```

**JavaScript**

```javascript
let driver = new Builder()
  .forBrowser(Browser.CHROME)
  .setChromeOptions(options.setPageLoadStrategy('eager'))
  .build();

await driver.get('https://www.selenium.dev/selenium/web/blank.html');
await driver.quit();
```

**Kotlin**

```kotlin
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
```

### none

WebDriver only waits until the initial page is downloaded.

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE);
WebDriver driver = new ChromeDriver(chromeOptions);
```

**Python**

```python
options = get_default_chrome_options()
options.page_load_strategy = 'none'
driver = webdriver.Chrome(options=options)
```

**CSharp**

```csharp
[TestMethod]
public void SetPageLoadStrategyNone()
```

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.page_load_strategy = :none
```

**JavaScript**

```javascript
let driver = new Builder()
  .forBrowser(Browser.CHROME)
  .setChromeOptions(options.setPageLoadStrategy('none'))
  .build();

await driver.get('https://www.selenium.dev/selenium/web/blank.html');
await driver.quit();
```

**Kotlin**

```kotlin
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
```

## platformName

This identifies the operating system at the remote-end, 
fetching the `platformName` returns the OS name. 

In cloud-based providers, 
setting `platformName` sets the OS at the remote-end.

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
String platform = "OS X 10.6";
chromeOptions.setPlatformName(platform);
```

**Python**

```python
options = get_default_chrome_options()
options.platform_name = 'any'
driver = webdriver.Chrome(options=options)
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
options = Selenium::WebDriver::Options.firefox
options.platform_name = 'Windows 10'
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

## acceptInsecureCerts

This capability checks whether an expired (or) 
invalid `TLS Certificate` is used while navigating 
during a session.

If the capability is set to `false`, an 
[insecure certificate error](//developer.mozilla.org/en-US/docs/Web/WebDriver/Errors/InsecureCertificate) 
will be returned as navigation encounters any domain 
certificate problems. If set to `true`, invalid certificate will be 
trusted by the browser.

All self-signed certificates will be trusted by this capability by default. 
Once set, `acceptInsecureCerts` capability will have an 
effect for the entire session.

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
chromeOptions.setAcceptInsecureCerts(true);
```

**Python**

```python
options = get_default_chrome_options()
options.accept_insecure_certs = True
driver = webdriver.Chrome(options=options)
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.accept_insecure_certs = true
```

**JavaScript**

```javascript
let driver = new Builder()
  .forBrowser(Browser.CHROME)
  .setChromeOptions(options.setAcceptInsecureCerts(true))
  .build();
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

## timeouts

A WebDriver `session` is imposed with a certain `session timeout`
interval, during which the user can control the behaviour
of executing scripts or retrieving information from the browser.

Each session timeout is configured with
combination of different `timeouts` as described below:

### Script Timeout
Specifies when to interrupt an executing script in
a current browsing context. The default timeout **30,000**
is imposed when a new session is created by WebDriver.

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
Duration duration = Duration.of(5, ChronoUnit.SECONDS);
chromeOptions.setScriptTimeout(duration);
```

**Python**

```python
options = get_default_chrome_options()
options.timeouts = { 'script': 5000 }
driver = webdriver.Chrome(options=options)
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.timeouts = {script: 40_000}
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Page Load Timeout
Specifies the time interval in which web page
needs to be loaded in a current browsing context.
The default timeout **300,000** is imposed when a
new session is created by WebDriver. If page load limits
a given/default time frame, the script will be stopped by
_TimeoutException_.

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
Duration duration = Duration.of(5, ChronoUnit.SECONDS);
chromeOptions.setPageLoadTimeout(duration);
```

**Python**

```python
options = get_default_chrome_options()
options.timeouts = { 'pageLoad': 5000 }
driver = webdriver.Chrome(options=options)
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.timeouts = {page_load: 400_000}
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Implicit Wait Timeout
This specifies the time to wait for the
implicit element location strategy when
locating elements. The default timeout **0**
is imposed when a new session is created by WebDriver.

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
Duration duration = Duration.of(5, ChronoUnit.SECONDS);
chromeOptions.setImplicitWaitTimeout(duration);
```

**Python**

```python
options = get_default_chrome_options()
options.timeouts = { 'implicit': 5000 }
driver = webdriver.Chrome(options=options)
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.timeouts = {implicit: 1}
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

## unhandledPromptBehavior

Specifies the state of current session's `user prompt handler`. 
Defaults to **dismiss and notify state**

### User Prompt Handler

This defines what action must take when a 
user prompt encounters at the remote-end. This is defined by 
`unhandledPromptBehavior` capability and has the following states:

* dismiss
* accept
* dismiss and notify
* accept and notify
* ignore

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS_AND_NOTIFY);
```

**Python**

```python
options = get_default_chrome_options()
options.unhandled_prompt_behavior = 'accept'
driver = webdriver.Chrome(options=options)
```

**CSharp**

```csharp
var chromeOptions = new ChromeOptions();
chromeOptions.UnhandledPromptBehavior = UnhandledPromptBehavior.DismissAndNotify;
```

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.unhandled_prompt_behavior = :accept
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

Nota: Para prompts de `beforeunload` especificamente,
o ChromeDriver precisa que `ignore` seja definido além de ter o
[BiDi](https://www.selenium.dev/pt-br/documentation/webdriver/bidi/index.md) habilitado para manter o prompt aberto.

## setWindowRect

Indicates whether the remote end supports all of the [resizing and repositioning](https://w3c.github.io/webdriver/#resizing-and-positioning-windows) [commands](https://w3c.github.io/webdriver/#dfn-commands).

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
chromeOptions.setCapability(CapabilityType.SET_WINDOW_RECT, true);
```

**Python**

```python
options = webdriver.FirefoxOptions()
options.set_window_rect = True # Full support in Firefox
driver = webdriver.Firefox(options=options)
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
options = Selenium::WebDriver::Options.firefox
options.set_window_rect = true
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

## strictFileInteractability

This new capability indicates if strict interactability checks 
should be applied to _input type=file_ elements. As strict interactability 
checks are off by default, there is a change in behaviour 
when using _Element Send Keys_ with hidden file upload controls.

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
chromeOptions.setCapability(CapabilityType.STRICT_FILE_INTERACTABILITY, true);
```

**Python**

```python
options = get_default_chrome_options()
options.strict_file_interactability = True
driver = webdriver.Chrome(options=options)
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.strict_file_interactability = true
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

## proxy

A proxy server acts as an intermediary for
requests between a client and a server. In simple,
the traffic flows through the proxy server
on its way to the address you requested and back.

A proxy server for automation scripts
with Selenium could be helpful for:

* Capture network traffic
* Mock backend calls made by the website
* Access the required website under complex network
  topologies or strict corporate restrictions/policies.

If you are in a corporate environment, and a
browser fails to connect to a URL, this is most
likely because the environment needs a proxy to be accessed.

Selenium WebDriver provides a way to proxy settings:

**Java**

```java
Proxy proxy = new Proxy();
proxy.setHttpProxy("myproxy.com:8080");
ChromeOptions options = getDefaultChromeOptions();
options.setCapability("proxy", proxy);
```

**Python**

```python
options = get_default_chrome_options()
options.proxy = Proxy({ 'proxyType': ProxyType.MANUAL, 'httpProxy' : 'http.proxy:1234'})
driver = webdriver.Chrome(options=options)
```

**CSharp**

```csharp
ChromeOptions options = new ChromeOptions();
Proxy proxy = new Proxy();
proxy.Kind = ProxyKind.Manual;
proxy.IsAutoDetect = false;
proxy.SslProxy = "myproxy.com:8080";
options.Proxy = proxy;
options.AddArgument("ignore-certificate-errors");
```

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.proxy = Selenium::WebDriver::Proxy.new(http: 'myproxy.com:8080')
```

**JavaScript**

```javascript
const options = new chrome.Options()
options.setProxy(proxy.manual({ http: 'myproxy.com:8080' }))
```

**Kotlin**

```kotlin
val proxy = Proxy()
proxy.setHttpProxy("myproxy.com:8080")
val options = ChromeOptions()
options.setCapability("proxy", proxy)
```
