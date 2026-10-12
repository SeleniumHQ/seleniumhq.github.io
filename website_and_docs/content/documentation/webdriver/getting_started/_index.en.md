---
title: "Getting started"
linkTitle: "Getting Started"
weight: 2
description: >
  Install Selenium, write your first script, and run it as a test.
aliases: [
"/documentation/en/getting_started/", 
"/documentation/en/getting_started/quick/",
"/documentation/en/selenium_installation/",
"/documentation/en/getting_started_with_webdriver/",
"/documentation/getting_started/",
"/documentation/ja/getting_started/",
"/documentation/ja/getting_started/quick/",
"/documentation/ja/selenium_installation/",
"/documentation/ja/getting_started_with_webdriver/",
"/documentation/pt-br/getting_started/",
"/documentation/pt-br/getting_started/quick/",
"/documentation/pt-br/selenium_installation/",
"/documentation/pt-br/getting_started_with_webdriver/",
"/documentation/zh-cn/getting_started/",
"/documentation/zh-cn/getting_started/quick/",
"/documentation/zh-cn/selenium_installation/",
"/documentation/zh-cn/getting_started_with_webdriver/",
"/ja/documentation/getting_started/",
"/ja/documentation/webdriver/getting_started/",
"/pt-br/documentation/getting_started/",
"/pt-br/documentation/webdriver/getting_started/",
"/zh-cn/documentation/getting_started/",
"/zh-cn/documentation/webdriver/getting_started/",
"/documentation/webdriver/getting_started/install_library/",
"/documentation/webdriver/getting_started/first_script/",
"/documentation/webdriver/getting_started/using_selenium/",
"/documentation/en/selenium_installation/installing_selenium_libraries/",
"/documentation/getting_started/installing_selenium_libraries/",
"/documentation/getting_started/install_selenium_library/",
"/documentation/ja/selenium_installation/installing_selenium_libraries/",
"/documentation/pt-br/selenium_installation/installing_selenium_libraries/",
"/documentation/zh-cn/selenium_installation/installing_selenium_libraries/",
"/ja/documentation/getting_started/install_selenium_library/",
"/ja/documentation/getting_started/installing_selenium_libraries/",
"/pt-br/documentation/getting_started/install_selenium_library/",
"/pt-br/documentation/getting_started/installing_selenium_libraries/",
"/zh-cn/documentation/getting_started/install_selenium_library/",
"/zh-cn/documentation/getting_started/installing_selenium_libraries/"
]
---

This page takes you from nothing installed to a Selenium script running as a test.

## You will learn

* How to install the Selenium library for your programming language
* How to write and run a first script that opens a browser, interacts with a page, and closes it
* How to run the same code as a test with a test runner

## Install

{{% alert title="You do not need to download drivers" color="primary" %}}
You do not need to download drivers.
[Selenium Manager]({{< ref "/documentation/selenium_manager.md" >}})
fetches the right browser and driver automatically.
{{% /alert %}}

Add the Selenium library to your project.
Check the [downloads page](/downloads/) for the latest version.

{{< tabpane text=true >}}
{{% tab header="Java" %}}
Add the dependency to your build file.
The minimum supported Java version is listed in the
[Selenium source](https://github.com/SeleniumHQ/selenium/blob/trunk/.bazelrc#L32).

**Maven** (`pom.xml`)

{{< gh-codeblock path="/examples/java/pom.xml#L33-L37" >}}

**Gradle** (`build.gradle`)

{{< gh-codeblock path="/examples/java/build.gradle#L15" >}}
{{% /tab %}}
{{% tab header="Python" %}}
```shell
pip install selenium
```

The minimum supported Python version for each release is listed on
[PyPI](https://pypi.org/project/selenium/).
{{% /tab %}}
{{% tab header="CSharp" %}}
```shell
dotnet add package Selenium.WebDriver
```

The supported frameworks for each release are listed on
[NuGet](https://www.nuget.org/packages/Selenium.WebDriver).
{{% /tab %}}
{{% tab header="Ruby" %}}
```shell
gem install selenium-webdriver
```

Or add `gem 'selenium-webdriver'` to your `Gemfile`.
The minimum supported Ruby version for each release is listed on
[RubyGems](https://rubygems.org/gems/selenium-webdriver/).
{{% /tab %}}
{{% tab header="JavaScript" %}}
```shell
npm install selenium-webdriver
```

The minimum supported Node version for each release is in the "Node Support Policy"
section on [npm](https://www.npmjs.com/package/selenium-webdriver).
{{% /tab %}}
{{% tab header="Kotlin" %}}
Kotlin uses the Java bindings.
Add the same Maven or Gradle dependency shown on the Java tab.
{{% /tab %}}
{{< /tabpane >}}

## Your first script

Everything Selenium does is send the browser commands to do something,
or send requests for information.
This script uses eight basic commands that you will use in almost every session:

1. Start the [driver session]({{< ref "/documentation/webdriver/drivers" >}})
2. [Navigate]({{< ref "/documentation/webdriver/interactions/navigation.md" >}}) to a page
3. Request [information about the browser]({{< ref "/documentation/webdriver/interactions" >}})
4. Establish a [waiting strategy]({{< ref "/documentation/webdriver/waits.md" >}})
5. [Find elements]({{< ref "/documentation/webdriver/elements" >}})
6. [Act on an element]({{< ref "/documentation/webdriver/elements/interactions.md" >}})
7. Request [element information]({{< ref "/documentation/webdriver/elements/information" >}})
8. End the session with
   [quit]({{< ref "/documentation/webdriver/drivers/#quitting-sessions" >}})

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/getting_started/FirstScript.java" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/getting_started/first_script.py" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/GettingStarted/FirstScript.cs" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/getting_started/first_script.rb" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/getting_started/firstScript.spec.js" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/getting_started/FirstScriptTest.kt" >}}
{{< /tab >}}
{{< /tabpane >}}

Run the file with your language's usual tooling.
The browser opens, fills in the form, and closes.

## Run it as a test

Most people use Selenium to test web applications.
A test runner gives you before and after hooks, assertions, and reporting,
and lets you run tests in groups or in parallel.
Here is the same script structured as a test, with one runner for each language:

{{< tabpane text=true >}}
{{% tab header="Java" %}}
[JUnit](https://junit.org/junit5/)

{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/getting_started/UsingSeleniumTest.java" >}}

```shell
mvn test
```
{{% /tab %}}
{{% tab header="Python" %}}
[pytest](https://pytest.org/)

{{< gh-codeblock path="/examples/python/tests/getting_started/using_selenium_tests.py" >}}

```shell
pytest
```
{{% /tab %}}
{{% tab header="CSharp" %}}
[MSTest](https://learn.microsoft.com/en-us/dotnet/core/testing/unit-testing-with-mstest)

{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/GettingStarted/UsingSeleniumTest.cs" >}}

```shell
dotnet test
```
{{% /tab %}}
{{% tab header="Ruby" %}}
[RSpec](https://rspec.info/)

{{< gh-codeblock path="/examples/ruby/spec/getting_started/using_selenium_spec.rb" >}}

```shell
bundle exec rspec
```
{{% /tab %}}
{{% tab header="JavaScript" %}}
[Mocha](https://mochajs.org/)

{{< gh-codeblock path="/examples/javascript/test/getting_started/runningTests.spec.js" >}}

```shell
npx mocha runningTests.spec.js
```
{{% /tab %}}
{{% tab header="Kotlin" %}}
[JUnit](https://junit.org/junit5/)

The Kotlin first script above is already a JUnit test.

```shell
mvn test
```
{{% /tab %}}
{{< /tabpane >}}

## Next steps

* [Waits]({{< ref "/documentation/webdriver/waits.md" >}}):
  make sure elements are ready before you use them
* [Locators]({{< ref "/documentation/webdriver/elements/locators.md" >}}):
  choose the best way to find elements
* [Grid]({{< ref "/documentation/grid/getting_started.md" >}}):
  run your tests on many browsers and machines
* [How Selenium works]({{< ref "/documentation/overview/details.md" >}}):
  WebDriver, drivers, and the rest of the project
