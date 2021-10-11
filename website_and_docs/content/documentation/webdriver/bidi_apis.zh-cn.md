---
title: "BiDi APIs"
linkTitle: "BiDi APIs"
weight: 12
aliases: ["/documentation/zh-cn/webdriver/bidi_apis/"]
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to Chinese. Do you speak Chinese? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}


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
# Please raise a PR to add code sample
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
# Please raise a PR to add code sample
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
# Please raise a PR to add code sample
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
using System;
using NUnit.Framework;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.DevTools;
using System.Threading.Tasks;
// Replace the version to match the Chrome version
using DevToolsSessionDomains = OpenQA.Selenium.DevTools.V93.DevToolsSessionDomains;
using ExceptionThrownEventArgs = OpenQA.Selenium.DevTools.V93.Runtime.ExceptionThrownEventArgs;

namespace CDPJSException {
    public class CDPJSExceptionTest {
        ChromeDriver driver;

        [SetUp]
        public void startBrowser() {
            driver = new ChromeDriver("<path-to-chrome-executable>");
        }

        [Test]
        public void jsExceptionTest()
        {  
            var devToolsSessionDomains = ((IDevTools)driver).GetDevToolsSession()
           .GetVersionSpecificDomains<DevToolsSessionDomains>();

            EventHandler <ExceptionThrownEventArgs> eventHandler =
                (sender, e) => {
                Assert.AreEqual("<your site url>", e.ExceptionDetails.Url);
            };

            enableRuntime(devToolsSessionDomains).GetAwaiter().GetResult();
            devToolsSessionDomains.Runtime.ExceptionThrown += eventHandler;

            driver.Url = new String("<your site url>");
           // Operations that causes JS exception
        }

        public static async Task enableRuntime(DevToolsSessionDomains domains) {
            await domains.Runtime.Enable();
        }

        [TearDown]
        public void closeBrowser() {
            driver.Close();
        }
    }
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
using System;
using System.Threading.Tasks;
using NUnit.Framework;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.DevTools;
// Replace the version to match the Chrome version
using OpenQA.Selenium.DevTools.V93.Performance;
using DevToolsSessionDomains = OpenQA.Selenium.DevTools.V93.DevToolsSessionDomains;

namespace CDPTests {
    public class CDPGetMetricsTest {
        ChromeDriver driver;

        [SetUp]
        public void startBrowser() {
            driver = new ChromeDriver("<path-to-chrome-executable>");
        }

        [Test]
        public void test() {
            DevToolsSession devToolsSession = driver.GetDevToolsSession(93);

            driver.Navigate().GoToUrl("<your site url>");
            var metrics = GetMetrics(devToolsSession).GetAwaiter().GetResult();

            Assert.NotNull(metrics);
            Assert.GreaterOrEqual(metrics.Metrics.Length, 1);

            foreach (var metric in metrics.Metrics) {
                Console.WriteLine($"{metric.Name} = {metric.Value}");
            }
        }

        public static async Task<GetMetricsCommandResponse> GetMetrics(DevToolsSession devToolsSession) {
            EnableCommandSettings enableCommandSettings = new EnableCommandSettings();
            await devToolsSession.GetVersionSpecificDomains<DevToolsSessionDomains>().
                Performance.
                Enable(enableCommandSettings, System.Threading.CancellationToken.None, 50000, true);

            var metrics = await devToolsSession
                .GetVersionSpecificDomains<DevToolsSessionDomains>()
                .Performance.GetMetrics();

            return metrics;
        }

        [TearDown]
        public void closeBrowser() {
            driver.Close();
        }
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
