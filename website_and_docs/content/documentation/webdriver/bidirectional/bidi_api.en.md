---
title: "BiDirectional API"
linkTitle: "BiDi API"
weight: 1
---

The following list of APIs will be growing as the Selenium
project works through supporting real world use cases. If there
is additional functionality you'd like to see, please raise a
[feature request](https://github.com/SeleniumHQ/selenium/issues/new?assignees=&labels=&template=feature.md).

## Register Basic Auth

Some applications make use of browser authentication to secure pages.
With Selenium, you can automate the input of basic auth credentials whenever they arise.

{{< tabpane langEqualsHeader=true code=false >}}
{{< tab header="Java" code=true >}}
Predicate<URI> uriPredicate = uri -> uri.getHost().contains("your-domain.com");

((HasAuthentication) driver).register(uriPredicate, UsernameAndPassword.of("admin", "password"));
driver.get("https://your-domain.com/login");
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="CSharp" code=true >}}
NetworkAuthenticationHandler handler = new NetworkAuthenticationHandler()
{
    UriMatcher = (d) => d.Host.Contains("your-domain.com"),
    Credentials = new PasswordCredentials("admin", "password")
};

INetwork networkInterceptor = driver.Manage().Network;
networkInterceptor.AddAuthenticationHandler(handler);
await networkInterceptor.StartMonitoring();
{{< /tab >}}
{{< tab header="Ruby" code=true >}}
require 'selenium-webdriver'

driver = Selenium::WebDriver.for :chrome

begin
  driver.devtools.new
  driver.register(username: 'username', password: 'password')
  driver.get '<your site url>'
ensure
  driver.quit
end
{{< /tab >}}
{{< tab header="JavaScript" code=true >}}
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
{{< /tab >}}
{{< tab header="Kotlin" code=true >}}
val uriPredicate = Predicate { uri: URI ->
        uri.host.contains("your-domain.com")
    }
(driver as HasAuthentication).register(uriPredicate, UsernameAndPassword.of("admin", "password"))
driver.get("https://your-domain.com/login")
{{< /tab >}}
{{< /tabpane >}}

## Mutation Observation

Mutation Observation is the ability to capture events via
WebDriver BiDi when there are DOM mutations on a specific
element in the DOM.

{{< tabpane langEqualsHeader=true code=false >}}
  {{< tab header="Java" code=true >}}
ChromeDriver driver = new ChromeDriver();

AtomicReference<DomMutationEvent> seen = new AtomicReference<>();
CountDownLatch latch = new CountDownLatch(1);
((HasLogEvents) driver).onLogEvent(domMutation(mutation -> {
    seen.set(mutation);
    latch.countDown();
}));

driver.get("https://www.google.com");
WebElement span = driver.findElement(By.cssSelector("span"));

((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('cheese', 'gouda');", span);

assertThat(latch.await(10, SECONDS), is(true));
assertThat(seen.get().getAttributeName(), is("cheese"));
assertThat(seen.get().getCurrentValue(), is("gouda"));

driver.quit();
  {{< /tab >}}
  {{< tab header="Python" code=true >}}
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.wait import WebDriverWait

driver = webdriver.Chrome()
async with driver.log.mutation_events() as event:
    pages.load("dynamic.html")
    driver.find_element(By.ID, "reveal").click()
    WebDriverWait(driver, 5)\
        .until(EC.visibility_of(driver.find_element(By.ID, "revealed")))

assert event["attribute_name"] == "style"
assert event["current_value"] == ""
assert event["old_value"] == "display:none;"

  {{< /tab >}}
  {{< tab header="CSharp" code=true >}}
List<DomMutationData> attributeValueChanges = new List<DomMutationData>();
DefaultWait<List<DomMutationData>> wait = new DefaultWait<List<DomMutationData>>(attributeValueChanges);
wait.Timeout = TimeSpan.FromSeconds(3);

IJavaScriptEngine monitor = new JavaScriptEngine(driver);
monitor.DomMutated += (sender, e) =>
{
    attributeValueChanges.Add(e.AttributeData);
};
await monitor.StartEventMonitoring();

driver.Navigate().GoToUrl("http://www.google.com");
IWebElement span = driver.FindElement(By.CssSelector("span"));

await monitor.EnableDomMutationMonitoring();
((IJavaScriptExecutor) driver).ExecuteScript("arguments[0].setAttribute('cheese', 'gouda');", span);

wait.Until((list) => list.Count > 0);
Console.WriteLine("Found {0} DOM mutation events", attributeValueChanges.Count);
foreach(var record in attributeValueChanges)
{
    Console.WriteLine("Attribute name: {0}", record.AttributeName);
    Console.WriteLine("Attribute value: {0}", record.AttributeValue);
}

await monitor.DisableDomMutationMonitoring();
  {{< /tab >}}
  {{< tab header="Ruby" code=true >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :firefox
begin
  driver.on_log_event(:mutation) { |mutation| mutations.push(mutation) }
  driver.navigate.to url_for('dynamic.html')
  driver.find_element(id: 'reveal').click
  wait.until { mutations.any? }
  mutation = mutations.first
  expect(mutation.element).to eq(driver.find_element(id: 'revealed'))
  expect(mutation.attribute_name).to eq('style')
  expect(mutation.current_value).to eq('')
  expect(mutation.old_value).to eq('display:none;')
ensure
  driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" code=true >}}
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
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
{{< badge-code >}}
  {{< /tab >}}
{{< /tabpane >}}

## Listen to `console.log` events

Listen to the `console.log` events and register callbacks to process the event.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
ChromeDriver driver = new ChromeDriver();
DevTools devTools = driver.getDevTools();
devTools.createSession();
devTools.send(Log.enable());
devTools.addListener(Log.entryAdded(),
                           logEntry -> {
                               System.out.println("log: "+logEntry.getText());
                               System.out.println("level: "+logEntry.getLevel());
                           });
driver.get("http://the-internet.herokuapp.com/broken_images");
// Check the terminal output for the browser console messages.
driver.quit();
{{< /tab >}}
{{< tab header="Python" >}}
async def printConsoleLogs():
  chrome_options = webdriver.ChromeOptions()
  driver = webdriver.Chrome()
  driver.get("http://www.google.com")

  async with driver.bidi_connection() as session:
      log = Log(driver, session)
      from selenium.webdriver.common.bidi.console import Console
      async with log.add_listener(Console.ALL) as messages:
          driver.execute_script("console.log('I love cheese')")
      print(messages["message"])

  driver.quit()
{{< /tab >}}
{{< tab header="CSharp" >}}
IJavaScriptEngine monitor = new JavaScriptEngine(driver);
List<string> consoleMessages = new List<string>();
monitor.JavaScriptConsoleApiCalled += (sender, e) =>
{
    Console.WriteLine("Log: {0}", e.MessageContent);
};
await monitor.StartEventMonitoring();
{{< /tab >}}
{{< tab header="Ruby" >}}
require 'selenium-webdriver'

driver = Selenium::WebDriver.for :chrome
begin
  driver.get 'http://www.google.com'
  logs = []
  driver.on_log_event(:console) do |event|
    logs.push(event)
    puts logs.length
  end

  driver.execute_script('console.log("here")')

ensure
  driver.quit
end
{{< /tab >}}
{{< tab header="JavaScript" >}}
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
{{< /tab >}}
{{< tab header="Kotlin" >}}
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
{{< /tab >}}
{{< /tabpane >}}

## Listen to JS Exceptions

Listen to the JS Exceptions
and register callbacks to process the exception details.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

public void jsExceptionsExample() {
    ChromeDriver driver = new ChromeDriver();
    DevTools devTools = driver.getDevTools();
    devTools.createSession();

    List<JavascriptException> jsExceptionsList = new ArrayList<>();
    Consumer<JavascriptException> addEntry = jsExceptionsList::add;
    devTools.getDomains().events().addJavascriptExceptionListener(addEntry);

    driver.get("<your site url>");

    WebElement link2click = driver.findElement(By.linkText("<your link text>"));
    ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);",
          link2click, "onclick", "throw new Error('Hello, world!')");
    link2click.click();

    for (JavascriptException jsException : jsExceptionsList) {
        System.out.println("JS exception message: " + jsException.getMessage());
        System.out.println("JS exception system information: " + jsException.getSystemInformation());
        jsException.printStackTrace();
    }
}
{{< /tab >}}
{{< tab header="Python" >}}
async def catchJSException():
  chrome_options = webdriver.ChromeOptions()
  driver = webdriver.Chrome()

  async with driver.bidi_connection() as session:
      driver.get("<your site url>")
      log = Log(driver, session)
      async with log.add_js_error_listener() as messages:
          # Operation on the website that throws an JS error
      print(messages)

  driver.quit()
{{< /tab >}}
{{< tab header="CSharp" >}}
List<string> exceptionMessages = new List<string>();
IJavaScriptEngine monitor = new JavaScriptEngine(driver);
monitor.JavaScriptExceptionThrown += (sender, e) =>
{
    exceptionMessages.Add(e.Message);
};

await monitor.StartEventMonitoring();

driver.Navigate.GoToUrl("<your site url>");

IWebElement link2click = driver.FindElement(By.LinkText("<your link text>"));
((IJavaScriptExecutor) driver).ExecuteScript("arguments[0].setAttribute(arguments[1], arguments[2]);",
      link2click, "onclick", "throw new Error('Hello, world!')");
link2click.Click();

foreach (string message in exceptionMessages)
{
    Console.WriteLine("JS exception message: {0}", message);
}
{{< /tab >}}
{{< tab header="Ruby" >}}
require 'selenium-webdriver'

driver = Selenium::WebDriver.for :chrome
begin
  driver.get '<your-site-url>'
  exceptions = []
  driver.on_log_event(:exception) do |event|
    exceptions.push(event)
    puts exceptions.length
  end

  # Actions causing JS exceptions

ensure
  driver.quit
end
{{< /tab >}}
{{< tab header="JavaScript" >}}
const {Builder, By} = require('selenium-webdriver');
(async () => {
  try {
    let driver = new Builder()
      .forBrowser('chrome')
      .build();

    const cdpConnection = await driver.createCDPConnection('page')
    await driver.onLogException(cdpConnection, function (event) {
      console.log(event['exceptionDetails']);
    })
    await driver.get('https://the-internet.herokuapp.com');
    const link = await driver.findElement(By.linkText('Checkboxes'));
    await driver.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", link, "onclick","throw new Error('Hello, world!')");
    await link.click();
    await driver.quit();
  }catch (e){
    console.log(e);
  }
})()
{{< /tab >}}
{{< tab header="Kotlin" >}}
fun kotlinJsErrorListener() {
    val driver = ChromeDriver()
    val devTools = driver.devTools
    devTools.createSession()

    val logJsError = { j: JavascriptException -> print("Javascript error: '" + j.localizedMessage + "'.") }
    devTools.domains.events().addJavascriptExceptionListener(logJsError)

    driver.get("https://www.google.com")

    val link2click = driver.findElement(By.name("q"))
    (driver as JavascriptExecutor).executeScript(
      "arguments[0].setAttribute(arguments[1], arguments[2]);",
      link2click, "onclick", "throw new Error('Hello, world!')"
    )
    link2click.click()

    driver.quit()
}
{{< /tab >}}
{{< /tabpane >}}

## Network Interception

If you want to capture network events coming into the browser and you want manipulate them you are able to do
it with the following examples.

{{< tabpane langEqualsHeader=true code=false >}}
{{< tab header="Java" code=true >}}
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.devtools.HasDevTools;
    import org.openqa.selenium.devtools.NetworkInterceptor;
    import org.openqa.selenium.remote.http.Contents;
    import org.openqa.selenium.remote.http.Filter;
    import org.openqa.selenium.remote.http.HttpResponse;
    import org.openqa.selenium.remote.http.Route;

    NetworkInterceptor interceptor = new NetworkInterceptor(
      driver,
      Route.matching(req -> true)
        .to(() -> req -> new HttpResponse()
          .setStatus(200)
          .addHeader("Content-Type", MediaType.HTML_UTF_8.toString())
          .setContent(utf8String("Creamy, delicious cheese!"))));

   driver.get("https://example-sausages-site.com");

    String source = driver.getPageSource();

    assertThat(source).contains("delicious cheese!");
{{< /tab >}}
{{< tab header="Python" >}}
Currently unavailable in python due the inability to mix certain async and sync commands
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" code=true >}}
require 'selenium-webdriver'

driver = Selenium::WebDriver.for :chrome
driver.intercept do |request, &continue|
    uri = URI(request.url)
    if uri.path.end_with?('one.js')
      uri.path = '/devtools_request_interception_test/two.js'
      request.url = uri.to_s
    end
    continue.call(request)
end
driver.navigate.to url_for('devToolsRequestInterceptionTest.html')
driver.find_element(tag_name: 'button').click
expect(driver.find_element(id: 'result').text).to eq('two')
{{< /tab >}}

{{< tab header="JavaScript" code=true >}}
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
{{< /tab >}}
{{< tab header="Kotlin" code=true >}}
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
{{< /tab >}}
{{< /tabpane >}}
