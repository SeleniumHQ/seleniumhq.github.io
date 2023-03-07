---
title: "Headless is Going Away!"
linkTitle: "Headless is Going Away!"
date: 2023-01-29
tags: ["selenium"]
categories: ["releases"]
author: Diego Molina ([@diegofmolina](https://twitter.com/diegofmolina))
description: >
  Now that we got your attention, headless is not actually going away, just the convenience method to set it in Selenium
---

Headless is an execution mode for Firefox and Chromium based browsers. It allows users to run automated scripts in 
headless mode, meaning that the browser window wouldn’t be visible. In most of Selenium's bindings there is a
convenience method to set this execution mode while setting the browser options. However, 
[Selenium 4.8.0](https://www.selenium.dev/blog/2023/selenium-4-8-0-released/) will be deprecated this method 
and now users need to set it through arguments when setting the browser options.

### Why is Selenium doing this?

Chromium based browsers have now two different headless modes (the original one, and one with more 
capabilities added in 2022). When a user sets headless to `true` via the convenience method in Selenium, 
it is using the initial method provided by Chromium based browsers. 

By deprecating the convenience method (and removing it in Selenium 4.10.0), users will be in full control to 
choose which headless mode they want to use.

### What are the two headless modes?

The traditional `--headless`, and since version 96, Chrome has a new headless mode that allows users to 
get the full browser functionality (even run extensions). Between versions 96 to 108 it was 
`--headless=chrome`, after version 109 `--headless=new`.

Using `--headless=new` should bring a better experience when using headless with Selenium.

Thanks to [Michael Mintz](https://github.com/mdmintz) for the detailed 
[explanation](https://stackoverflow.com/questions/45631715/downloading-with-chrome-headless-and-selenium/73840130#73840130)!

Check more details about the [new headleass mode at the official Chrome blog](https://developer.chrome.com/articles/new-headless/).

### How can I set headless mode from now on?

In short, users can add the headless mode they want to use through arguments in browser options.

#### Before 
{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
ChromeOptions options = new ChromeOptions();
options.setHeadless(true);
WebDriver driver = new ChromeDriver(options);
driver.get("https://selenium.dev");
driver.quit();
{{< /tab >}}
{{< tab header="JavaScript" >}}
let driver = await env
  .builder()
  .setChromeOptions(new chrome.Options().headless())
  .build();
await driver.get('https://selenium.dev');
await driver.quit();
{{< /tab >}}
{{< tab header="CSharp" >}}
// C# did not have a convenience method
{{< /tab >}}
{{< tab header="Ruby" >}}
options = Selenium::WebDriver::Chrome::Options.new
options.headless!
driver = Selenium::WebDriver.for :chrome, options: options
driver.get('https://selenium.dev')
driver.quit
{{< /tab >}}
{{< tab header="Python" >}}
options = ChromeOptions()
options.headless = True
driver = webdriver.Chrome(options=options)
driver.get('http://selenium.dev')
driver.quit()
{{< /tab >}}
{{< /tabpane >}}

#### After
{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
ChromeOptions options = new ChromeOptions();
options.addArguments("--headless=new");
WebDriver driver = new ChromeDriver(options);
driver.get("https://selenium.dev");
driver.quit();
{{< /tab >}}
{{< tab header="JavaScript" >}}
let driver = await env
  .builder()
  .setChromeOptions(options.addArguments('--headless=new'))
  .build();
await driver.get('https://selenium.dev');
await driver.quit();
{{< /tab >}}
{{< tab header="CSharp" >}}
var options = new ChromeOptions();
options.AddArgument("--headless=new");
var driver = new ChromeDriver(options);
driver.Navigate().GoToUrl("https://selenium.dev");
driver.Quit();
{{< /tab >}}
{{< tab header="Ruby" >}}
options = Selenium::WebDriver::Options.chrome(args: ['--headless=new'])
driver = Selenium::WebDriver.for :chrome, options: options
driver.get('https://selenium.dev')
driver.quit
{{< /tab >}}
{{< tab header="Python" >}}
options = ChromeOptions()
options.add_argument("--headless=new")
driver = webdriver.Chrome(options=options)
driver.get('http://selenium.dev')
driver.quit()
{{< /tab >}}
{{< /tabpane >}}


If you have any questions or comments, please reach out through any of all the available options 
shown at our [support page](https://www.selenium.dev/support/).

Stay tuned for updates by following [SeleniumHQ](https://twitter.com/seleniumhq)!

Happy testing!