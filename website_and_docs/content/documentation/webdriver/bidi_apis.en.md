---
title: "WebDriver Bidi APIs"
linkTitle: "WebDriver Bidi APIs"
weight: 11
aliases: ["/documentation/en/webdriver/bidi_apis/"]
---

Selenium is working with Browser Vendors to create the 
[BiDirectional WebDriver Protocol](https://w3c.github.io/webdriver-bidi/) 
as a means to provide a stable, cross-browser implementation of a subset of the functionality found
[Chrome DevTools](../../support_packages/chrome_devtools).
The traditional webdriver model of strict request/response commands will be supplemented with the ability to
stream events from the user agent to the controlling software via WebSockets, better matching the evented nature of the browser DOM.

While this initiative is being developed, Selenium has implemented this API which is currently supported by
Chrome, Edge and Firefox using the Chrome DevTools Protocol. 
While the underlying implementation may change, the goal is for this API to remain constant.

The following list of APIs will be growing as the Selenium
project works through supporting real world use cases. If there
is a missing API, please raise a [feature request](https://github.com/SeleniumHQ/selenium/issues/new?assignees=&labels=&template=feature.md).

## Register Basic Auth:

Some applications require to keep some pages behind an auth and most of the time
to keep things simple, a developer uses Basic Auth. With Selenium and devtools
integration, you can automate the input of basic auth credentials whenever they arise.

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
# Please raise a PR to add code sample
{{< /tab >}}
{{< tab header="JavaScript" >}}
# Please raise a PR to add code sample
{{< /tab >}}
{{< tab header="Kotlin" >}}
val uriPredicate =
Predicate { uri: URI ->
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
WebDriver driver = new FirefoxDriver();


HasLogEvents logger = (HasLogEvents) driver;

AtomicReference<DomMutationEvent> seen = new AtomicReference<>();
CountDownLatch latch = new CountDownLatch(1);
logger.onLogEvent(domMutation(mutation -> {
    seen.set(mutation);
    latch.countDown();
}));

driver.get("http://www.google.com");
WebElement span = driver.findElement(By.cssSelector("span"));

((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('cheese', 'gouda');", span);

assertThat(latch.await(10, SECONDS)).isTrue();
assertThat(seen.get().getAttributeName()).isEqualTo("cheese");
assertThat(seen.get().getCurrentValue()).isEqualTo("gouda");


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
IWebDriver driver = new FirefoxDriver();
driver.Url = "http://www.google.com";
// Please help with a .NET example
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
val driver = FirefoxDriver()
driver.get("http://www.google.com")
// Please help us create an example for Kotlin
  {{< /tab >}}
{{< /tabpane >}}

## Listen to console.log events

Listen to the `console.log` events and register callbacks to process the event.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v87.log.Log;

public void consoleLogTest() {
ChromeDriver driver = new ChromeDriver();
DevTools devTools = driver.getDevTools();
devTools.createSession();

    devTools.send(Log.enable());
    devTools.addListener(Log.entryAdded(),
            logEntry -> {
                System.out.println("log: "+logEntry.getText());
                System.out.println("level: "+logEntry.getLevel());
            });
}
{{< /tab >}}
{{< tab header="Python" >}}
# Please raise a PR to add code sample
{{< /tab >}}
{{< tab header="CSharp" >}}
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.DevTools;
using System;
using DevToolsSessionDomains = OpenQA.Selenium.DevTools.V91.DevToolsSessionDomains;

    namespace Selenium4Sample {
      public class Example {
        public void ConsoleLogTest() {
          var driver = new ChromeDriver();
          var devToolsSessionDomains = ((IDevTools) driver).GetDevToolsSession()
           .GetVersionSpecificDomains < DevToolsSessionDomains > ();
          devToolsSessionDomains.Log.Enable();
          devToolsSessionDomains.Log.EntryAdded += (sender, e) => {
          Console.WriteLine("log: " + e.Entry.Text);
          Console.WriteLine("level: " + e.Entry.Level);
      };
    }
}
}
{{< /tab >}}
{{< tab header="Ruby" >}}
# Please raise a PR to add code sample
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
# Please raise a PR to add code sample
{{< /tab >}}
{{< tab header="CSharp" >}}
# Please raise a PR to add code sample
{{< /tab >}}
{{< tab header="Ruby" >}}
# Please raise a PR to add code sample
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
# Please raise a PR to add code sample
{{< /tab >}}
{{< tab header="Ruby" >}}
# Please raise a PR to add code sample
{{< /tab >}}
{{< tab header="JavaScript" >}}
# Please raise a PR to add code sample
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
