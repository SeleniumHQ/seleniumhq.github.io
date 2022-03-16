---
title: "The Selenium Browser Automation Project"
linkTitle: "Documentation"
cascade:
- type: docs
aliases: ["/documentation/en/"]
---

Selenium is an umbrella project for a range of tools and libraries 
that enable and support the automation of web browsers. 

It provides extensions to emulate user interaction with browsers,
a distribution server for scaling browser allocation,
and the infrastructure for implementations of the 
[W3C WebDriver specification](//www.w3.org/TR/webdriver/)
that lets you write interchangeable code for all major web browsers.

This project is made possible by volunteer contributors
who have put in thousands of hours of their own time,
and made the source code 
[freely available]({{< ref "copyright.md#license" >}})
for anyone to use, enjoy, and improve.

Selenium brings together browser vendors, engineers, and enthusiasts
to further an open discussion around automation of the web platform.
The project organises [an annual conference](//seleniumconf.com/)
to teach and nurture the community.

At the core of Selenium is [WebDriver]({{< ref "webdriver" >}}), 
an interface to write instruction sets that can be run interchangeably in many 
browsers. Once you've installed everything, only a few lines of code get you inside
a browser. You can find a more comprehensive example in [Writing your first Selenium script]({{< ref "first_script.md" >}})

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" disableCodeBlock=true >}}
    {{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/hello/HelloSelenium.java" >}}
{{< /tab >}}
{{< tab header="Python" disableCodeBlock=true >}}
    {{< gh-codeblock path="/examples/python/tests/hello/test_hello_selenium.py" >}}
{{< /tab >}}
{{< tab header="CSharp" disableCodeBlock=true >}}
    {{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Hello/HelloSelenium.cs" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
require 'selenium-webdriver'

driver = Selenium::WebDriver.for :chrome

driver.get 'https://selenium.dev'

driver.quit
{{< /tab >}}
{{< tab header="JavaScript" >}}
const {Builder, By, Key, until} = require('selenium-webdriver');

(async function helloSelenium() {
    let driver = await new Builder().forBrowser('chrome').build();

    await driver.get('https://selenium.dev');

    await driver.quit();
})();
{{< /tab >}}
{{< tab header="Kotlin" >}}
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver

fun main() {
    val driver = ChromeDriver()

    driver.get("https://selenium.dev")

    driver.quit()
}
{{< /tab >}}
{{< /tabpane >}}

See the [Overview]({{< ref "overview" >}}) to check the different project 
components and decide if Selenium is the right tool for you.

You should continue on to [Getting Started]({{< ref "webdriver/getting_started" >}})
to understand how you can install Selenium and successfully use it as a test 
automation tool, and scaling simple tests like this to run in large, distributed 
environments on multiple browsers, on several different operating systems.



