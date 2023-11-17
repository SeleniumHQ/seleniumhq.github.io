---
title: "Chrome Devtools Protocol with BiDi API"
linkTitle: "BiDi API"
weight: 6
description: >
  These examples are currently implemented with CDP, but the same code should work when the functionality
  is re-implemented with WebDriver-BiDi.
---

## Usage

A seguinte lista de APIs crescerá à medida que o projeto Selenium se prepara
para suportar casos de uso do mundo real. Se houver funcionalidades adicionais que você gostaria de ver, por favor, levante uma [solicitação de recurso](https://github.com/SeleniumHQ/selenium/issues/new?assignees=&labels=&template=feature.md).

As these examples are re-implemented with the [WebDriver-Bidi](https://w3c.github.io/webdriver-bidi) protocol, they will
be moved to the [WebDriver Bidi]({{< ref "../webdriver_bidi/" >}}) pages.

## Examples

### Basic authentication

Alguns aplicativos fazem o uso da autenticação do navegador para proteger suas páginas. Com o Selenium, você pode automatizar a entrada de credenciais básicas de autenticação sempre que for necessário.

Alternate implementations can be found at
[CDP Endpoint Basic Authentication]({{< ref "cdp_endpoint#basic-authentication" >}})
and [CDP API Basic Authentication]({{< ref "cdp_api#basic-authentication" >}})

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/chrome_devtools/BidiApiTest.java#L36-L39" >}}
{{% /tab %}}
{{% tab header="Python" %}}
An alternate implementation may be found at
[CDP Endpoint Basic Authentication]({{< ref "cdp_endpoint#basic-authentication" >}})
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Bidirectional/ChromeDevtools/BidiApiTest.cs#L18-L27" >}}
{{% /tab %}}
{{% tab header="Ruby" %}}
{{< gh-codeblock path="/examples/ruby/spec/bidirectional/chrome_devtools/bidi_api_spec.rb#L9-L11" >}}
{{% /tab %}}
{{% tab header="JavaScript" %}}
{{< badge-examples >}}
```js
const {Builder} = require('selenium-webdriver');

(async function example() {
  try {
    let driver = await new Builder()
      .forBrowser('chrome')
      .build();

    const pageCdpConnection = await driver.createCDPConnection('page');
    await driver.register('username', 'password', pageCdpConnection);
    await driver.get('https://the-internet.herokuapp.com/basic_auth');
    await driver.quit();
  }catch (e){
    console.log(e)
  }
}())
```
{{% /tab %}}
{{% tab header="Kotlin" %}}
{{< badge-examples >}}
```java
val uriPredicate = Predicate { uri: URI ->
        uri.host.contains("your-domain.com")
    }
(driver as HasAuthentication).register(uriPredicate, UsernameAndPassword.of("admin", "password"))
driver.get("https://your-domain.com/login")
```
{{% /tab %}}
{{< /tabpane >}}


### Pin scripts
This can be especially useful when executing on a remote server. For example,
whenever you check the visibility of an element, or whenever you use
the classic get attribute method, Selenium is sending the contents of a js file
to the script execution endpoint. These files are each about 50kB, which adds up.

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="Python" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Bidirectional/ChromeDevtools/BidiApiTest.cs#L41-L43" >}}
{{% /tab %}}
{{% tab header="Ruby" %}}
{{< gh-codeblock path="/examples/ruby/spec/bidirectional/chrome_devtools/bidi_api_spec.rb#L22-L23" >}}
{{% /tab %}}
{{% tab header="JavaScript" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="Kotlin" %}}
{{< badge-code >}}
{{% /tab %}}
{{< /tabpane >}}


### Mutation observation

Mutation Observation(Observação de Mutação) é a capacidade de capturar eventos via WebDriver BiDi quando há mutações DOM em um elemento específico no DOM.

{{< tabpane text=true >}}
  {{% tab header="Java" %}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/chrome_devtools/BidiApiTest.java#L64-L65" >}}
  {{% /tab %}}
  {{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/bidirectional/chrome_devtools/test_bidi_api.py#L9-L11" >}}
  {{% /tab %}}
  {{% tab header="CSharp" %}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Bidirectional/ChromeDevtools/BidiApiTest.cs#L59-L68" >}}
  {{% /tab %}}
  {{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/bidirectional/chrome_devtools/bidi_api_spec.rb#L31-L32" >}}
  {{< /tab >}}
  {{% tab header="JavaScript" %}}
  {{< badge-examples >}}
```js
const {Builder, until} = require('selenium-webdriver');
const assert = require("assert");

(async function example() {
  try {
    let driver = await new Builder()
      .forBrowser('chrome')
      .build();

    const cdpConnection = await driver.createCDPConnection('page');
    await driver.logMutationEvents(cdpConnection, event => {
      assert.deepStrictEqual(event['attribute_name'], 'style');
      assert.deepStrictEqual(event['current_value'], "");
      assert.deepStrictEqual(event['old_value'], "display:none;");
    });

    await driver.get('dynamic.html');
    await driver.findElement({id: 'reveal'}).click();
    let revealed = driver.findElement({id: 'revealed'});
    await driver.wait(until.elementIsVisible(revealed), 5000);
    await driver.quit();
  }catch (e){
    console.log(e)
  }
}())
```
{{% /tab %}}
  {{< tab header="Kotlin" >}}
{{< badge-code >}}
  {{< /tab >}}
{{< /tabpane >}}


### Console logs and errors
Vigie os eventos `console.log` e registre os callbacks(retornos de chamada) para processar o evento.

[CDP API Console logs]({{< ref "cdp_api#console-logs-and-errors" >}})
and [WebDriver BiDi Console logs]({{< ref "../webdriver_bidi/log#console-logs" >}})

{{< tabpane text=true >}}
{{% tab header="Java" %}}
Use the [WebDriver BiDi Console logs]({{< ref "../webdriver_bidi/log#console-logs" >}}) implementation. `HasLogEvents`
will likely end up deprecated because it does not implement `Closeable`.
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/chrome_devtools/BidiApiTest.java#L77-L78" >}}
{{% /tab %}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/bidirectional/chrome_devtools/test_bidi_api.py#L23-L26" >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Bidirectional/ChromeDevtools/BidiApiTest.cs#L85-L92" >}}
{{% /tab %}}
{{% tab header="Ruby" %}}
{{< gh-codeblock path="/examples/ruby/spec/bidirectional/chrome_devtools/bidi_api_spec.rb#L43-L44" >}}
{{% /tab %}}
{{% tab header="JavaScript" %}}
{{< badge-examples >}}
```js
const {Builder} = require('selenium-webdriver');
(async () => {
  try {
    let driver = new Builder()
      .forBrowser('chrome')
      .build();

    const cdpConnection = await driver.createCDPConnection('page');
    await driver.onLogEvent(cdpConnection, function (event) {
      console.log(event['args'][0]['value']);
    });
    await driver.executeScript('console.log("here")');
    await driver.quit();
  }catch (e){
    console.log(e);
  }
})()
```
{{% /tab %}}
{{% tab header="Kotlin" %}}
{{< badge-examples >}}
```java
fun kotlinConsoleLogExample() {
    val driver = ChromeDriver()
    val devTools = driver.devTools
    devTools.createSession()

    val logConsole = { c: ConsoleEvent -> print("Console log message is: " + c.messages)}
    devTools.domains.events().addConsoleListener(logConsole)

    driver.get("https://www.google.com")

    val executor = driver as JavascriptExecutor
    executor.executeScript("console.log('Hello World')")

    val input = driver.findElement(By.name("q"))
    input.sendKeys("Selenium 4")
    input.sendKeys(Keys.RETURN)
    driver.quit()
}
```
{{% /tab %}}
{{< /tabpane >}}

### JavaScript exceptions

Vigie as exceções JS
e registre callbacks(retornos de chamada) para processar os detalhes da exceção.

{{< tabpane text=true >}}
{{% tab header="Java" %}}
Use the [WebDriver BiDi JavaScript exceptions]({{< ref "../webdriver_bidi/log#javascript-exceptions" >}}) implementation
{{% /tab %}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/bidirectional/chrome_devtools/test_bidi_api.py#L36-L39" >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Bidirectional/ChromeDevtools/BidiApiTest.cs#L107-L114" >}}
{{% /tab %}}
{{% tab header="Ruby" %}}
{{< gh-codeblock path="/examples/ruby/spec/bidirectional/chrome_devtools/bidi_api_spec.rb#L57-L58" >}}
{{% /tab %}}
{{% tab header="JavaScript" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="Kotlin" %}}
{{< badge-code >}}
{{% /tab %}}
{{< /tabpane >}}


### Interceptação de Rede

Both requests and responses can be recorded or transformed.

#### Response information

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/chrome_devtools/BidiApiTest.java#L90-L101" >}}
{{% /tab %}}
{{% tab header="Python" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Bidirectional/ChromeDevtools/BidiApiTest.cs#L125-L133" >}}
{{% /tab %}}
{{% tab header="Ruby" %}}
{{< gh-codeblock path="/examples/ruby/spec/bidirectional/chrome_devtools/bidi_api_spec.rb#L67-L72" >}}
{{% /tab %}}
{{% tab header="JavaScript" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="Kotlin" %}}
{{< badge-code >}}
{{% /tab %}}
{{< /tabpane >}}

#### Response transformation

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/chrome_devtools/BidiApiTest.java#L111-L121" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Bidirectional/ChromeDevtools/BidiApiTest.cs#L143-L156" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/bidirectional/chrome_devtools/bidi_api_spec.rb#L79-L83" >}}
{{< /tab >}}
{{% tab header="JavaScript" %}}
{{< badge-examples >}}
```js
const connection = await driver.createCDPConnection('page')
let url = fileServer.whereIs("/cheese")
let httpResponse = new HttpResponse(url)
httpResponse.addHeaders("Content-Type", "UTF-8")
httpResponse.body = "sausages"
await driver.onIntercept(connection, httpResponse, async function () {
  let body = await driver.getPageSource()
  assert.strictEqual(body.includes("sausages"), true, `Body contains: ${body}`)
})
driver.get(url)
```
{{% /tab %}}
{{% tab header="Kotlin" %}}
{{< badge-examples >}}
```java
val driver = ChromeDriver()
val interceptor = new NetworkInterceptor(
      driver,
      Route.matching(req -> true)
        .to(() -> req -> new HttpResponse()
          .setStatus(200)
          .addHeader("Content-Type", MediaType.HTML_UTF_8.toString())
          .setContent(utf8String("Creamy, delicious cheese!"))))

    driver.get(appServer.whereIs("/cheese"))

    String source = driver.getPageSource()
```
{{% /tab %}}
{{< /tabpane >}}

#### Request interception

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Bidirectional/ChromeDevtools/BidiApiTest.cs#L167-L181" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/bidirectional/chrome_devtools/bidi_api_spec.rb#L90-L94" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}
