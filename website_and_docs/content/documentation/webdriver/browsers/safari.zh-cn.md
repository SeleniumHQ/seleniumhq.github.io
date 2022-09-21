---
title: "Safari specific functionality"
linkTitle: "Safari"
weight: 10
description: >-
    These are capabilities and features specific to Apple Safari browsers.
aliases: [
"/zh-cn/documentation/capabilities/safari"
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

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/SafariTest.java#21-L22" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_internet_explorer.py#L10-L11" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/SafariTest.cs#L14-L15" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/internet_explorer_spec.rb#L8-L9" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
  let options = new safari.Options();
  let driver = await new Builder()
    .forBrowser('safari')
    .setSafariOptions(options)
    .build();
{{< /tab >}}
{{< tab header="Kotlin" >}}
  val options = SafariOptions()
  val driver = SafariDriver(options)
{{< /tab >}}
{{< /tabpane >}}

### Mobile
Those looking to automate Safari on iOS should look to the [Appium project](//appium.io/).
