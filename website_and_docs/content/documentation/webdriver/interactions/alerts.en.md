---
title: "JavaScript alerts, prompts and confirmations"
linkTitle: "Alerts"
weight: 2
aliases: [
"/documentation/en/webdriver/js_alerts_prompts_and_confirmations/",
"/documentation/webdriver/js_alerts_prompts_and_confirmations/",
"/documentation/webdriver/browser/alerts",
"/documentation/ja/webdriver/js_alerts_prompts_and_confirmations/",
"/documentation/pt-br/webdriver/js_alerts_prompts_and_confirmations/",
"/documentation/zh-cn/webdriver/js_alerts_prompts_and_confirmations/",
"/ja/documentation/webdriver/browser/alerts",
"/ja/documentation/webdriver/interactions/alerts/",
"/ja/documentation/webdriver/js_alerts_prompts_and_confirmations/",
"/pt-br/documentation/webdriver/browser/alerts/",
"/pt-br/documentation/webdriver/interactions/alerts/",
"/pt-br/documentation/webdriver/js_alerts_prompts_and_confirmations/",
"/zh-cn/documentation/webdriver/browser/alerts/",
"/zh-cn/documentation/webdriver/interactions/alerts/",
"/zh-cn/documentation/webdriver/js_alerts_prompts_and_confirmations/"
]
---

WebDriver provides an API for working with the three types of native
popup messages offered by JavaScript. These popups are styled by the
browser and offer limited customisation.

## Alerts

The simplest of these is referred to as an alert, which shows a
custom message, and a single button which dismisses the alert, labelled
in most browsers as OK. It can also be dismissed in most browsers by
pressing the close button, but this will always do the same thing as
the OK button. <a onclick="window.alert('Sample alert')">See an example alert</a>.

WebDriver can get the text from the popup and accept or dismiss these
alerts.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/interactions/AlertsTest.java#L36-L41" >}}
{{< /tab >}}

{{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/interactions/test_alerts.py#L13-L19" >}}
{{< /tab >}}

{{< tab header="CSharp" text=true >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Interactions/AlertsTest.cs#L42-L52" >}}
{{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/interactions/alerts_spec.rb#L15-L22" >}}
{{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/interactions/alert.spec.js#L19-L21" >}}
{{< /tab >}}
{{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/interactions/AlertsTest.kt#L25-L35" >}}
{{< /tab >}}
{{< /tabpane >}}

## Confirm

A confirm box is similar to an alert, except the user can also choose
to cancel the message. <a onclick="window.confirm('Are you sure?')">See
a sample confirm</a>.

This example also shows a different approach to storing an alert:

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/interactions/AlertsTest.java#L131-L138" >}}
{{< /tab >}}

{{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/interactions/test_alerts.py#L27-L33" >}}
{{< /tab >}}

{{< tab header="CSharp" text=true >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Interactions/AlertsTest.cs#L62-L76" >}}
{{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/interactions/alerts_spec.rb#L28-L35" >}}
{{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/interactions/alert.spec.js#L30-L32" >}}
{{< /tab >}}
{{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/interactions/AlertsTest.kt#L44-L58" >}}
{{< /tab >}}
{{< /tabpane >}}

## Prompt

Prompts are similar to confirm boxes, except they also include a text
input. Similar to working with form elements, you can use WebDriver's
send keys to fill in a response. This will completely replace the placeholder
text. Pressing the cancel button will not submit any text.
<a onclick="window.prompt('What is your tool of choice?',navigator.appName)">
See a sample prompt</a>.


{{< tabpane langEqualsHeader=true text=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/interactions/AlertsTest.java#L79-L84" >}}
{{< /tab >}}

{{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/interactions/test_alerts.py#L41-L48" >}}
{{< /tab >}}

{{< tab header="CSharp" text=true >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Interactions/AlertsTest.cs#L86-L100" >}}
{{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/interactions/alerts_spec.rb#L41-L48" >}}
{{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/interactions/alert.spec.js#L42-L45" >}}
{{< /tab >}}
{{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/interactions/AlertsTest.kt#L67-L81" >}}
{{< /tab >}}
{{< /tabpane >}}

## beforeunload

Recent drivers automatically dismiss `beforeunload` prompts by default. To keep the old behavior,
see [unhandledPromptBehavior]({{< ref "../drivers/options#unhandledpromptbehavior" >}}).
