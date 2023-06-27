---
title: "Driver Service Class"
linkTitle: "服务"
weight: 3
---

服务类用于管理驱动程序的启动和停止.
They can not be used with a Remote WebDriver session.

Service classes allow you to specify information about the driver,
like location and which port to use.
They also let you specify what arguments get passed
to the command line. Most of the useful arguments are related to logging.

## Default Service instance

To start a driver with a default service instance:

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/drivers/ServiceTest.java#L30-L32" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/drivers/test_service.py#L5-L6" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Drivers/ServiceTest.cs#L19-L20" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/drivers/service_spec.rb#L12-L13" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## Driver location

**Note:** If you are using Selenium 4.6 or greater, you shouldn't need to set a driver location.
If you can not update Selenium or have an advanced use case here is how to specify the driver location:

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/drivers/ServiceTest.java#L38" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/drivers/test_service.py#L12" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-version version="4.9" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Drivers/ServiceTest.cs#L27" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="examples/ruby/spec/drivers/service_spec.rb#L18" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## Driver port

If you want the driver to run on a specific port, you may specify it as follows:

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/drivers/ServiceTest.java#L47" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/drivers/test_service.py#L20" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Drivers/ServiceTest.cs#L36" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="examples/ruby/spec/drivers/service_spec.rb#L25" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## Setting log output

Getting driver logs can be helpful for debugging various issues. The Service class lets you
direct where the logs will go

Currently, the default behavior for logging varies by language (and sometimes browser):

{{< tabpane text=true langEqualsHeader=true >}}
{{% tab header="Java" %}}
Logging output defaults to STDERR
{{% /tab %}}
{{% tab header="Python" %}}
Logging output defaults to none, except for Firefox, which defaults to "geckodriver.log"
{{% /tab %}}
{{% tab header="CSharp" %}}
Logging output defaults to none
{{% /tab %}}
{{% tab header="Ruby" %}}
Logging output defaults to none, unless the WebDriver logger level is set to `:debug`,
in which case the driver logging is sent to the same output as the Selenium logging.
{{% /tab %}}
{{% tab header="JavaScript" %}}
{{% /tab %}}
{{% tab header="Kotlin" %}}
Logging output defaults to STDERR
{{% /tab %}}
{{< /tabpane >}}

### File output

To change the logging output to save to a specific file:

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
Java allows you to set this by method:
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/drivers/ServiceTest.java#L52-L54" >}}
Or by System Property:
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/drivers/ServiceTest.java#L61-L62" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/drivers/test_service.py#L33" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Drivers/ServiceTest.cs#L46" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/drivers/service_spec.rb#L32" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Console output

To change the logging output to display in the console as STDOUT:

{{< tabpane text=true langEqualsHeader=true >}}
{{% tab header="Java" %}}
Java allows you to set this by method:
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/drivers/ServiceTest.java#L69-L71" >}}
Or by System Property:
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/drivers/ServiceTest.java#L78-L79" >}}
{{% /tab %}}
{{% tab header="Python" %}}
This is not yet supported in Python
{{% /tab %}}
{{% tab header="CSharp" %}}
This is not yet supported in .NET
{{% /tab %}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/drivers/service_spec.rb#L41" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

