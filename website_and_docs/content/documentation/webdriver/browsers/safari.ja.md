---
title: "Safari specific functionality"
linkTitle: "Safari"
weight: 10
description: >-
    These are capabilities and features specific to Apple Safari browsers.
aliases: [
"/ja/documentation/capabilities/safari"
]
---

Unlike Chromium and Firefox drivers, the safaridriver is installed with the Operating System.
To enable automation on Safari, run the following command from the terminal:

```shell
safaridriver --enable
```

## Options

Capabilities common to all browsers are described on the [Options page]({{< ref "../drivers/options.md" >}}).

Capabilities unique to Safari can be found at Apple's page [About WebDriver for Safari](https://developer.apple.com/documentation/webkit/about_webdriver_for_safari#2957227)

Starting a Safari session with basic defined options looks like this:

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/SafariTest.java#24-L25" >}}
{{< /tab >}}
{{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_safari.py#L9-L10" >}}
{{< /tab >}}
{{< tab header="CSharp" text=true >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/SafariTest.cs#L22-L23" >}}
{{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/safari_spec.rb#L8-L9" >}}
{{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/browser/safariSpecificCap.spec.js#L10-L12" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
val options = SafariOptions()
val driver = SafariDriver(options)
{{< /tab >}}
{{< /tabpane >}}

### Mobile
Those looking to automate Safari on iOS should look to the [Appium project](//appium.io/).


## Service

Service settings common to all browsers are described on the [Service page]({{< ref "../drivers/service.md" >}}).

### Logging

Unlike other browsers, Safari doesn't let you choose where logs are output, or change levels. The one option
available is to turn logs off or on. If logs are toggled on, they can be found at:`~/Library/Logs/com.apple.WebDriver/`.

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/SafariTest.java#L31" >}}
**Note**: Java also allows setting console output by System Property;\
Property key: `SafariDriverService.SAFARI_DRIVER_LOGGING`\
Property value: `"true"` or `"false"`
{{% /tab %}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/browsers/test_safari.py#L17" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="examples/ruby/spec/browsers/safari_spec.rb#L20" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}


## Safari Technology Preview

Apple provides a development version of their browser — [Safari Technology Preview](https://developer.apple.com/safari/technology-preview/)
To use this version in your code:

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
{{< gh-codeblock path="examples/ruby/spec/browsers/safari_spec.rb#L36-L37" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}
