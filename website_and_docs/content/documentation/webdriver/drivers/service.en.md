---
title: "Driver Service Class"
linkTitle: "Service"
weight: 3
---

The Service classes are for managing the starting and stopping of local drivers.
They cannot be used with a Remote WebDriver session.

Service classes allow you to specify information about the driver, 
like location and which port to use.
They also let you specify what arguments get passed
to the command line. Most of the useful arguments are related to logging.

## Default Service instance

To start a driver with a default service instance:

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/drivers/ServiceTest.java#L15-L16" >}}
**Note**: Java Service classes only allow values to be set during construction with a Builder pattern.
{{% /tab %}}
{{% tab header="Python" %}}
{{< badge-version version="4.11" >}}
{{< gh-codeblock path="examples/python/tests/drivers/test_service.py#L5-L6" >}}
**Note**: Python Service classes only allow values to be set as arguments to the constructor.
{{% /tab %}}
{{% tab header="CSharp" %}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Drivers/ServiceTest.cs#L17-L18" >}}
**Note**: .NET Service classes allow values to be set as properties.
{{% /tab %}}
{{% tab header="Ruby" %}}
{{< gh-codeblock path="examples/ruby/spec/drivers/service_spec.rb#L14-L15" >}}
**Note**: Ruby Service classes allow values to be set either as arguments in the constructor or as attributes.
{{% /tab %}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## Driver location

**Note:** If you are using Selenium 4.6 or greater, you shouldn't need to set a driver location.
If you cannot update Selenium or have an advanced use case, here is how to specify the driver location:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/drivers/ServiceTest.java#L25-L26" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.11" >}}
{{< gh-codeblock path="examples/python/tests/drivers/test_service.py#L15" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-version version="4.9" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Drivers/ServiceTest.cs#L25" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="examples/ruby/spec/drivers/service_spec.rb#L22" >}}
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

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/drivers/ServiceTest.java#L33" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.11" >}}
{{< gh-codeblock path="examples/python/tests/drivers/test_service.py#L23" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Drivers/ServiceTest.cs#L34" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="examples/ruby/spec/drivers/service_spec.rb#L29" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

<span id="setting-log-output"></span>
## Logging

Logging functionality varies between browsers. Most browsers allow you to 
specify location and level of logs. Take a look at the respective browser page:
* [Chrome]({{< ref "../browsers/chrome#service" >}})
* [Edge]({{< ref "../browsers/edge#service" >}})
* [Firefox]({{< ref "../browsers/firefox#service" >}})
* [Internet Explorer]({{< ref "../browsers/internet_explorer#service" >}})
* [Safari]({{< ref "../browsers/safari#service" >}})
