---
title: "待機"
linkTitle: "待機"
weight: 6
aliases: ["/documentation/ja/webdriver/waits/"]
---

Perhaps the most common challenge for browser automation is ensuring
that the web application is in a state to execute a particular
Selenium command as desired. The processes often end up in
a _race condition_ where sometimes the browser gets into the right
state first (things work as intended) and sometimes the Selenium code
executes first (things do not work as intended). This is one of the
primary causes of _flaky tests_.

All navigation commands wait for a specific `readyState` value
based on the [page load strategy]({{< ref "drivers/options#pageloadstrategy" >}}) (the
default value to wait for is `"complete"`) before the driver returns control to the code.
The `readyState` only concerns itself with loading assets defined in the HTML, 
but loaded JavaScript assets often result in changes to the site,
and elements that need to be interacted with may not yet be on the page
when the code is ready to execute the next Selenium command.

Similarly, in a lot of single page applications, elements get dynamically
added to a page or change visibility based on a click.
An element must be both present and
[displayed]({{< ref "elements/information/#is-displayed" >}}) on the page
in order for Selenium to interact with it.

Take this page for example: https://www.selenium.dev/selenium/web/dynamic.html
When the "Add a box!" button is clicked, a "div" element that does not exist is created.
When the "Reveal a new input" button is clicked, a hidden text field element is displayed.
In both cases the transition takes a couple seconds.
If the Selenium code is to click one of these buttons and interact with the resulting element,
it will do so before that element is ready and fail.

The first solution many people turn to is adding a sleep statement to
pause the code execution for a set period of time.
Because the code can't know exactly how long it needs to wait, this
can fail when it doesn't sleep long enough. Alternately, if the value is set too high
and a sleep statement is added in every place it is needed, the duration of
the session can become prohibitive.

Selenium provides two different mechanisms for synchronization that are better.


## Implicit waits
Selenium has a built-in way to automatically wait for elements called an _implicit wait_.
An implicit wait value can be set either with the [timeouts]({{< ref "drivers/options#timeouts" >}})
capability in the browser options, or with a driver method (as shown below).

This is a global setting that applies to every element location call for the entire session.
The default value is `0`, which means that if the element is not found, it will
immediately return an error. If an implicit wait is set, the driver will wait for the 
duration of the provided value before returning the error. Note that as soon as the 
element is located, the driver will return the element reference and the code will continue executing, 
so a larger implicit wait value won't necessarily increase the duration of the session.

*Warning:*
Do not mix implicit and explicit waits.
Doing so can cause unpredictable wait times.
For example, setting an implicit wait of 10 seconds
and an explicit wait of 15 seconds
could cause a timeout to occur after 20 seconds.

Solving our example with an implicit wait looks like this:

{{< tabpane text=true >}}
  {{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/waits/WaitsTest.java#L50" >}}
  {{< /tab >}}
  {{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/waits/test_waits.py#L27" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Waits/WaitsTest.cs#L39" >}}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/waits/waits_spec.rb#L28" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/waits/waits.spec.js#L37-L41" >}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
{{< badge-code >}}
  {{< /tab >}}
{{< /tabpane >}}

## Explicit waits

_Explicit waits_ are loops added to the code that poll the application
for a specific condition to evaluate as true before it exits the loop and
continues to the next command in the code. If the condition is not met before a designated timeout value,
the code will give a timeout error. Since there are many ways for the application not to be in the desired state,
so explicit waits are a great choice to specify the exact condition to wait for
in each place it is needed.
Another nice feature is that, by default, the Selenium Wait class automatically waits for the designated element to exist.

{{< tabpane text=true >}}
  {{% tab header="Java" %}}
This example shows the condition being waited for as a _lambda_. Java also supports
[Expected Conditions]({{< ref "support_features/expected_conditions" >}})
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/waits/WaitsTest.java#L67-L68" >}}
  {{% /tab %}}
  {{% tab header="Python" %}}
This example shows the condition being waited for as a _lambda_. Python also supports
[Expected Conditions]({{< ref "support_features/expected_conditions" >}})
{{< gh-codeblock path="examples/python/tests/waits/test_waits.py#L41-L42" >}}
  {{% /tab %}}
  {{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Waits/WaitsTest.cs#L56-L57" >}}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/waits/waits_spec.rb#L42-L43" >}}
  {{< /tab >}}
  {{% tab header="JavaScript" %}}
JavaScript also supports [Expected Conditions]({{< ref "support_features/expected_conditions" >}})
{{< gh-codeblock path="examples/javascript/test/waits/waits.spec.js#L47-L51" >}}
  {{% /tab %}}
  {{< tab header="Kotlin" >}}
{{< badge-code >}}
  {{< /tab >}}
{{< /tabpane >}}

### Customization

The Wait class can be instantiated with various parameters that will change how the conditions are evaluated.

This can include:
* Changing how often the code is evaluated (polling interval)
* Specifying which exceptions should be handled automatically
* Changing the total timeout length
* Customizing the timeout message

For instance, if the _element not interactable_ error is retried by default, then we can
add an action on a method inside the code getting executed (we just need to 
make sure that the code returns `true` when it is successful):

{{< tabpane text=true >}}
  {{% tab header="Java" %}}
The easiest way to customize Waits in Java is to use the `FluentWait` class:
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/waits/WaitsTest.java#L82-L92" >}}
  {{% /tab %}}
  {{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/waits/test_waits.py#L53-L55" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Waits/WaitsTest.cs#L70-L79" >}}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/waits/waits_spec.rb#L54-L60" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
{{< badge-code >}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
{{< badge-code >}}
  {{< /tab >}}
{{< /tabpane >}}
