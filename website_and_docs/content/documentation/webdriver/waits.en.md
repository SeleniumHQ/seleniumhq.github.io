---
title: "Waiting Strategies"
linkTitle: "Waits"
weight: 6
aliases: ["/documentation/en/webdriver/waits/"]
---

Perhaps the most common challenge for browser automation is ensuring
that the web application is in a state to execute a particular
Selenium command as desired. the processes often end up in
a _race condition_ where sometimes the browser gets into the right
state first (things work as intended) and sometimes the Selenium code
executes first (things do not work as intended). This is the
primary cause of _flaky tests_.

All navigation commands wait for a specific `readyState` value
based on the [page load strategy]({{< ref "drivers/options#pageloadstrategy" >}}) (the 
default is `"complete"`) before the driver returns control to the code.
The `readyState` only concerns itself with loading assets defined in the HTML, 
but loaded JavaScript assets often result in changes to the site,
and elements you need to interact with may not yet be on the page
when the code is ready to execute the next Selenium command.

Similarly, in a lot of single page applications, elements get dynamically
added to a page or change visibility based on a click. For example, the box
element takes a second to show up after clicking on the "adder" button, so
even trying to locate that button will error without some form of synchronization: 

{{< tabpane text=true langEqualsHeader=true >}}
  {{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/waits/WaitsTest.java#L23-L28" >}}
  {{< /tab >}}
  {{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/waits/test_waits.py#L9-L13" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Waits/WaitsTest.cs#L15-L20" >}}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/waits/waits_spec.rb#L9-L14" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
{{< badge-code >}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
{{< badge-code >}}
  {{< /tab >}}
{{< /tabpane >}}

There are several synchronization strategies that can be used to properly wait for
the application to be in the state you need it to be for the next Selenium command.

## Hard-coded sleeps

This causes the code to stop executing for a set period of time.
Because your code can't know exactly how long you need to wait, this
will either fail when it doesn't sleep long enough, or will cause
your sessions to take much longer than they need to. That said, putting in a sleep command is one way to keep
the above code from failing:

{{< tabpane text=true langEqualsHeader=true >}}
  {{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/waits/WaitsTest.java#L34-L38" >}}
  {{< /tab >}}
  {{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/waits/test_waits.py#L18-L21" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Waits/WaitsTest.cs#L27-L31" >}}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/waits/waits_spec.rb#L19-L22" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
{{< badge-code >}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
{{< badge-code >}}
  {{< /tab >}}
{{< /tabpane >}}

## Implicit waits
Selenium has a built-in way to automatically wait for elements called an _implicit wait_.
You set an implicit wait either with the [timeouts](({{< ref "drivers/options#timeouts" >}}))
capability in the browser options, or with a driver method (as shown below).

This is a global setting that applies to every element location call for the entire session.
The default value is `0`, which means that if the element is not found, it will
immediately return an error. If an implicit wait is set, the driver will wait for the 
duration of the provided value before returning the error. Note that as soon as the 
element is located, the driver will return the value and your code may continue, so a larger
implicit wait value won't necessarily increase the time of your session.

*Warning:*
Do not mix implicit and explicit waits.
Doing so can cause unpredictable wait times.
For example, setting an implicit wait of 10 seconds
and an explicit wait of 15 seconds
could cause a timeout to occur after 20 seconds.

Solving our example with an implicit wait looks like this:

{{< tabpane text=true langEqualsHeader=true >}}
  {{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/waits/WaitsTest.java#L45-L49" >}}
  {{< /tab >}}
  {{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/waits/test_waits.py#L27-L31" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Waits/WaitsTest.cs#L39-L44" >}}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/waits/waits_spec.rb#L28-L32" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
{{< badge-code >}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
{{< badge-code >}}
  {{< /tab >}}
{{< /tabpane >}}

## Explicit waits

_Explicit waits_ are loops you add to your code that poll the application 
for a specific condition to evaluate as true before it exits the loop and
continues to the next command in your code. If the condition is not met before a designated timeout value, 
the code will give a timeout error.

There are many ways for the application not to be in the desired state,
so explicit waits are a great choice to specify exactly what is desired
in each place they are needed. 
For example, an element must be both present in the DOM and 
[displayed](({{< ref "elements/information/#is-displayed" >}})) on the page
in order for Selenium to interact with it. 

In this example, clicking the
"reveal" button displays an input field that is already present in the DOM.
An explicit wait can be used to ensure the element is interactable before 
sending keys to it. An important feature of the Wait class in Selenium is that it will automatically retry
when a _no such element_ error happens, which makes it much easier to write succinct code.
We do not need to handle whether the element is there and we can just focus on whether it
evaluates as displayed:

{{< tabpane text=true langEqualsHeader=true >}}
  {{% tab header="Java" %}}
This example shows the condition being waited for as a _lambda_. Java also supports
[Expected Conditions](({{< ref "support_features/expected_conditions" >}}))
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/waits/WaitsTest.java#L57-L63" >}}
  {{% /tab %}}
  {{% tab header="Python" %}}
This example shows the condition being waited for as a _lambda_. Python also supports
[Expected Conditions](({{< ref "support_features/expected_conditions" >}}))
{{< gh-codeblock path="examples/python/tests/waits/test_waits.py#L38-L44" >}}
  {{% /tab %}}
  {{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Waits/WaitsTest.cs#L53-L59" >}}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/waits/waits_spec.rb#L39-L45" >}}
  {{< /tab >}}
  {{% tab header="JavaScript" %}}
JavaScript also supports [Expected Conditions](({{< ref "support_features/expected_conditions" >}}))
{{< badge-code >}}
  {{% /tab %}}
  {{< tab header="Kotlin" >}}
{{< badge-code >}}
  {{< /tab >}}
{{< /tabpane >}}

### Customization

The Wait class can be created with various parameters that will change how the conditions are evaluated.

This can include:
* Changing how often the code is evaluated (polling interval)
* Specifying which exceptions should be retried
* Changing the total timeout length
* Customizing the timeout message

For instance, if the _element not interactable_ error is retried by default, then we can
add an action on a method inside the code getting executed (we just need to 
make sure that the code returns `true` when it is successful):

{{< tabpane text=true langEqualsHeader=true >}}
  {{% tab header="Java" %}}
The easiest way to customize Waits in Java is to use the `FluentWait` class:
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/waits/WaitsTest.java#L70-L80" >}}
  {{% /tab %}}
  {{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/waits/test_waits.py#L50-L55" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Waits/WaitsTest.cs#L67-L78" >}}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/waits/waits_spec.rb#L51-L59" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
{{< badge-code >}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
{{< badge-code >}}
  {{< /tab >}}
{{< /tabpane >}}
