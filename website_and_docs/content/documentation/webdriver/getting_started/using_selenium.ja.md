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

### Testing

Running Selenium for testing requires making assertions on actions taken by Selenium.
So a good assertion library is required. Additional features to provide structure for tests
require use of [Test Runner](#test-runners)


## IDEs

Regardless of how you use Selenium code, 
you won't be very effective writing or executing it without a good
Integrated Developer Environment. Here are some common options...

* Eclipse
* IntelliJ
* PyCharm
* RubyMine
* Rider
* WebStorm
* VS Code

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

{{< tabpane text=true langEqualsHeader=true >}}
{{% tab header="Java" %}}
* JUnit 5
* 
{{% /tab %}}
{{% tab header="Python" %}}
* PyTest
*
{{% /tab %}}
{{% tab header="CSharp" %}}
* MS Test
*
{{% /tab %}}
{{% tab header="Ruby" %}}
* RSpec
*
{{% /tab %}}
{{% tab header="JavaScript" %}}
* Mocha
*
{{% /tab %}}
{{% tab header="Kotlin" %}}
*
*
{{% /tab %}}
{{< /tabpane >}}

### Installing

This is very similar to what was required in [Install a Selenium Library]({{< ref "install_library.md" >}})

{{< tabpane text=true langEqualsHeader=true >}}
{{% tab header="Java" %}}

Popular test runners for Java are [JUnit] and [TestNG]

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
Use the Java bindings for Kotlin.
{{< /tab >}}
{{< /tabpane >}}

### Asserting

{{< tabpane text=true langEqualsHeader=true >}}
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

{{< tabpane text=true langEqualsHeader=true >}}
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

{{< tabpane text=true langEqualsHeader=true >}}
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

{{< tabpane text=true langEqualsHeader=true >}}
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
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## Next Steps

Take what you've learned and build out your Selenium code! 

As you find more functionality that you need, read up on the rest of our
[WebDriver documentation]({{< ref "/documentation/webdriver/" >}}).
