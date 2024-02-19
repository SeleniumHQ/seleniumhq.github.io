---
title: "Organizing and Executing Selenium Code"
linkTitle: "Using Selenium"
weight: 10
description: >
    Scaling Selenium execution with an IDE and a Test Runner library
---

If you want to run more than a handful of one-off scripts, you need to 
be able to organize and work with your code. This page should give you
ideas for how to actually do productive things with your Selenium code.

## Common Uses

Most people use Selenium to execute automated tests for web applications, 
but Selenium support any use case of browser automation.

### Repetitive Tasks

Perhaps you need to log into a website and download something, or submit a form.
You can create a Selenium script to run with a service at preset times.

### Web Scraping

Are you looking to collect data from a site that doesn't have an API? Selenium
will let you do this, but please make sure you are familiar with the website's
terms of service as some websites do not permit it and others will even block Selenium.

### Testing

Running Selenium for testing requires making assertions on actions taken by Selenium.
So a good assertion library is required. Additional features to provide structure for tests
require use of [Test Runner](#test-runners).


## IDEs

Regardless of how you use Selenium code, 
you won't be very effective writing or executing it without a good
Integrated Developer Environment. Here are some common options...

- [Eclipse](https://www.eclipse.org/)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/)
- [PyCharm](https://www.jetbrains.com/pycharm/)
- [RubyMine](https://www.jetbrains.com/ruby/)
- [Rider](https://www.jetbrains.com/rider/)
- [WebStorm](https://www.jetbrains.com/webstorm/)
- [VS Code](https://code.visualstudio.com/)

## Test Runner

Even if you aren't using Selenium for testing, if you have advanced use cases, it might make
sense to use a test runner to better organize your code. Being able to use before/after hooks 
and run things in groups or in parallel can be very useful.

### Choosing
There are many different test runners available.

All the code examples in this documentation can be found in (or is being moved to) our
example directories that use test runners and get executed every release to ensure all the code is correct and updated.
Here is a list of test runners with links. The first item is the one that is used by this repository and the one
that will be used for all examples on this page.

{{< tabpane text=true >}}
{{% tab header="Java" %}}
- [JUnit](https://junit.org/junit5/) - A widely-used testing framework for Java-based Selenium tests.
- [TestNG](https://testng.org/) - Offers extra features like parallel test execution and parameterized tests.
{{% /tab %}}

{{% tab header="Python" %}}
- [pytest](https://pytest.org/) - A preferred choice for many, thanks to its simplicity and powerful plugins.
- [unittest](https://docs.python.org/3/library/unittest.html) - Python's standard library testing framework.
{{% /tab %}}

{{% tab header="CSharp" %}}
- [NUnit](https://nunit.org/) - A popular unit-testing framework for .NET.
- [MS Test](https://docs.microsoft.com/en-us/visualstudio/test/getting-started-with-unit-testing?view=vs-2019) - Microsoft's own unit testing framework.
{{% /tab %}}

{{% tab header="Ruby" %}}
- [RSpec](https://rspec.info/) - The most widely used testing library for running Selenium tests in Ruby.
- [Minitest](https://github.com/seattlerb/minitest) - A lightweight testing framework that comes with Ruby standard library.
{{% /tab %}}

{{% tab header="JavaScript" %}}
- [Jest](https://jestjs.io/) - Primarily known as a testing framework for React, it can also be used for Selenium tests.
- [Mocha](https://mochajs.org/) - The most common JS library for running Selenium tests.
{{% /tab %}}

{{% tab header="Kotlin" %}}

{{% /tab %}}
{{< /tabpane >}}


### Installing

This is very similar to what was required in [Install a Selenium Library]({{< ref "install_library.md" >}}).
This code is only showing examples for what is being used in our Documentation Examples project.

{{< tabpane text=true >}}
{{% tab header="Java" %}}

**Maven**

**Gradle**

{{% /tab %}}
{{% tab header="Python" %}}

To use it in a project, add it to the `requirements.txt` file:

{{% /tab %}}
{{% tab header="CSharp" %}}
in the project's `csproj` file, specify the dependency as a `PackageReference` in `ItemGroup`:

{{% /tab %}}
{{% tab header="Ruby" %}}

Add to project's gemfile

{{% /tab %}}
{{% tab header="JavaScript" %}}
In your project's `package.json`, add requirement to `dependencies`:

{{% /tab %}}
{{< tab header="Kotlin" >}}
{{< /tab >}}
{{< /tabpane >}}

### Asserting

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< badge-code >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Setting Up and Tearing Down

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< badge-code >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Executing

{{< tabpane text=true >}}
{{% tab header="Java" %}}
### Maven

```shell
mvn clean test
```

### Gradle

```shell
gradle clean test
```

{{% /tab %}}
{{% tab header="Python" %}}
{{< badge-code >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{% tab header="JavaScript" %}}
```shell
mocha runningTests.spec.js
```
{{% /tab %}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Examples

In [First script]({{< ref "first_script.md" >}}), we saw each of the components of a Selenium script.
Here's an example of that code using a test runner:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/UsingSeleniumTest.java" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/getting_started/using_selenium_tests.py" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/GettingStarted/UsingSeleniumTest.cs" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/getting_started/using_selenium_spec.rb" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/getting_started/runningTests.spec.js" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## Next Steps

Take what you've learned and build out your Selenium code! 

As you find more functionality that you need, read up on the rest of our
[WebDriver documentation]({{< ref "/documentation/webdriver/" >}}).
