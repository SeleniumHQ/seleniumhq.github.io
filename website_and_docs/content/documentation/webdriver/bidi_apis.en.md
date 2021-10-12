---
title: "BiDi APIs"
linkTitle: "BiDi APIs"
weight: 12
aliases: ["/documentation/en/webdriver/bidi_apis/"]
---

Selenium is working with browser vendors to create the 
[WebDriver BiDirectional Protocol](https://w3c.github.io/webdriver-bidi/) 
as a means to provide a stable, cross-browser API that uses the bidirectional
functionality useful for both browser automation generally and testing specifically. 
Before now, users seeking this functionality have had to rely on 
the [Chrome DevTools Protocol]({{< ref "/chrome_devtools.md" >}}), 
with all of its frustrations and limitations.

The traditional webdriver model of strict request/response commands will be supplemented 
with the ability to stream events from the user agent to the controlling software via WebSockets, 
better matching the evented nature of the browser DOM.

Because it's a bad idea to tie your tests to a specific version of a specific browser, 
the Selenium project recommends using WebDriver BiDi wherever possible. 
However, until the spec is complete there are many useful things that the CDP offers. 
To help keep your tests independent and portable, Selenium offers some useful helper classes. 
At the moment, these use the CDP, but when we shall be using WebDriver Bidi as soon as possible

The following list of APIs will be growing as the Selenium
project works through supporting real world use cases. If there
is additional functionality you'd like to see, please raise a 
[feature request](https://github.com/SeleniumHQ/selenium/issues/new?assignees=&labels=&template=feature.md).

## Register Basic Auth

Some applications make use of browser authentication to secure pages. 
With Selenium, you can automate the input of basic auth credentials whenever they arise.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
Predicate<URI> uriPredicate = uri -> uri.getHost().contains("your-domain.com");

((HasAuthentication) driver).register(uriPredicate, UsernameAndPassword.of("admin", "password"));
driver.get("https://your-domain.com/login");
{{< /tab >}}
{{< tab header="Python" >}}
# Please raise a PR to add code sample
{{< /tab >}}
{{< tab header="CSharp" >}}
NetworkAuthenticationHandler handler = new NetworkAuthenticationHandler()
{
    UriMatcher = (d) => d.Host.Contains("your-domain.com"),
    Credentials = new PasswordCredentials("admin", "password")
};

INetwork networkInterceptor = driver.Manage().Network;
networkInterceptor.AddAuthenticationHandler(handler);
await networkInterceptor.StartMonitoring();
{{< /tab >}}
{{< tab header="Ruby" >}}
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
{{< tab header="JavaScript" >}}
const {Builder} = require('selenium-webdriver');

(async function example() {
  try {
    let driver = await new Builder()
      .forBrowser('chrome')
      .build();

    const pageCdpConnection = await driver.createCDPConnection();
    await driver.register('username', 'password', pageCdpConnection);
    await driver.get('https://the-internet.herokuapp.com/basic_auth');
    await driver.quit();
  }catch (e){
    console.log(e)
  }
}())
{{< /tab >}}
{{< tab header="Kotlin" >}}
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

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
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
  {{< tab header="Python" >}}
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
  {{< tab header="CSharp" >}}
# Please raise a PR to add code sample
  {{< /tab >}}
  {{< tab header="Ruby" >}}
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
  {{< tab header="JavaScript" >}}
const {Builder, until} = require('selenium-webdriver');
const assert = require("assert");

(async function example() {
  try {
    let driver = await new Builder()
      .forBrowser('chrome')
      .build();

    const cdpConnection = await driver.createCDPConnection();
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
# Please raise a PR to add code sample
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
# Please raise a PR to add code sample
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
# Please raise a PR to add code sample
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

## Collect Performance Metrics

Collect various performance
metrics while navigating the application.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

public void performanceMetricsExample() {
    ChromeDriver driver = new ChromeDriver();
    DevTools devTools = driver.getDevTools();
    devTools.createSession();
    devTools.send(Performance.enable(Optional.empty()));
    List<Metric> metricList = devTools.send(Performance.getMetrics());

    driver.get("https://google.com");
    driver.quit();

    for(Metric m : metricList) {
        System.out.println(m.getName() + " = " + m.getValue());
    }
}
{{< /tab >}}
{{< tab header="Python" >}}
# Please raise a PR to add code sample
{{< /tab >}}
{{< tab header="CSharp" >}}
// File must contain the following using statements
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.DevTools;

// We must use a version-specific set of domains
using OpenQA.Selenium.DevTools.V94.Performance;

public async Task PerformanceMetricsExample()
{
    IWebDriver driver = new ChromeDriver();
    IDevTools devTools = driver as IDevTools;
    DevToolsSession session = devTools.GetDevToolsSession();
    await session.SendCommand<EnableCommandSettings>(new EnableCommandSettings());
    var metricsResponse = 
        await session.SendCommand<GetMetricsCommandSettings, GetMetricsCommandResponse>(
            new GetMetricsCommandSettings());

    driver.Navigate().GoToUrl("http://www.google.com");
    driver.Quit();

    var metrics = metricsResponse.Metrics;
    foreach (Metric metric in metrics)
    {
        Console.WriteLine("{0} = {1}", metric.Name, metric.Value);
    }
}
{{< /tab >}}
{{< tab header="Ruby" >}}
require 'selenium-webdriver'

driver = Selenium::WebDriver.for :chrome

begin
  driver.get 'https://www.duckduckgo.com'
  driver.execute_cdp('Performance.enable', {})
  metrics = driver.execute_cdp('Performance.getMetrics', {})
  puts metrics
ensure
  driver.quit
end
{{< /tab >}}
{{< tab header="JavaScript" >}}
await driver.get("https://www.duckduckgo.com");

await driver.sendAndGetDevToolsCommand('Performance.enable')

let result = await driver.sendAndGetDevToolsCommand('Performance.getMetrics')
console.log(result)

await driver.quit();
{{< /tab >}}
{{< tab header="Kotlin" >}}
val driver = ChromeDriver()
val devTools = driver.devTools
devTools.createSession()
devTools.send(Performance.enable(Optional.empty()))
val metricList: List<Metric> = devTools.send(Performance.getMetrics())

driver["https://google.com"]
driver.quit()

for (m in metricList) {
    println(m.name.toString() + " = " + m.value)
}
{{< /tab >}}
{{< /tabpane >}}
