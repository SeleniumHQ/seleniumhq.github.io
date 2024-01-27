---
title: "Write your first Selenium script"
linkTitle: "First Script"
weight: 8
description: >
    Step-by-step instructions for constructing a Selenium script
---

Once you have [Selenium installed]({{< ref "install_library.md" >}}), 
you're ready to write Selenium code.

## Eight Basic Components

Everything Selenium does is send the browser commands to do something or send requests for information.
Most of what you'll do with Selenium is a combination of these basic commands

Click on the link to "View full example on GitHub" to see the code in context.

### 1. Start the session

For more details on starting a session read our documentation on [driver sessions]({{< ref "../drivers/" >}})

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/FirstScript.java#L12" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/getting_started/first_script.py#L4" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/GettingStarted/FirstScript.cs#L11" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/getting_started/first_script.rb#L3" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/getting_started/firstScript.spec.js#L8" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/getting_started/FirstScriptTest.kt#L16" >}}
{{< /tab >}}
{{< /tabpane >}}

### 2. Take action on browser
In this example we are [navigating]({{< ref "/documentation/webdriver/interactions/navigation.md" >}}) to a web page. 

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/FirstScript.java#L14" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/getting_started/first_script.py#L6" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/GettingStarted/FirstScript.cs#L13" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/getting_started/first_script.rb#L5" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/getting_started/firstScript.spec.js#L9" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/getting_started/FirstScriptTest.kt#L18" >}}
{{< /tab >}}
{{< /tabpane >}}

### 3. Request browser information

There are a bunch of types of [information about the browser]({{< ref "/documentation/webdriver/interactions" >}}) you
can request, including window handles, browser size / position, cookies, alerts, etc.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/FirstScript.java#16" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/getting_started/first_script.py#L8" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/GettingStarted/FirstScript.cs#L15" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/getting_started/first_script.rb#L7" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/getting_started/firstScript.spec.js#L11" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/getting_started/FirstScriptTest.kt#L20" >}}
{{< /tab >}}
{{< /tabpane >}}

### 4. Establish Waiting Strategy

Synchronizing the code with the current state of the browser is one of the biggest challenges
with Selenium, and doing it well is an advanced topic. 

Essentially you want to make sure that the element is on the page before you attempt to locate it
and the element is in an interactable state before you attempt to interact with it.

An implicit wait is rarely the best solution, but it's the easiest to demonstrate here, so 
we'll use it as a placeholder. 

Read more about [Waiting strategies]({{< ref "/documentation/webdriver/waits.md" >}}).

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/FirstScript.java#L18" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/getting_started/first_script.py#L10" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/GettingStarted/FirstScript.cs#L17" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/getting_started/first_script.rb#L9" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/getting_started/firstScript.spec.js#L14" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/getting_started/FirstScriptTest.kt#L23" >}}
{{< /tab >}}
{{< /tabpane >}}

### 5. Find an element
The majority of commands in most Selenium sessions are element related, and you can't interact
with one without first [finding an element]({{< ref "/documentation/webdriver/elements" >}})

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/FirstScript.java#L20-L21" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/getting_started/first_script.py#L12-L13" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/GettingStarted/FirstScript.cs#L19-L20" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/getting_started/first_script.rb#L11-L12" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/getting_started/firstScript.spec.js#L16-L17" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/getting_started/FirstScriptTest.kt#L25-L26" >}}
{{< /tab >}}
{{< /tabpane >}}

### 6. Take action on element
There are only a handful of [actions to take on an element]({{< ref "/documentation/webdriver/elements/interactions.md" >}}),
but you will use them frequently. 

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/FirstScript.java#L23-L24" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/getting_started/first_script.py#L15-L16" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/GettingStarted/FirstScript.cs#L22-L23" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/getting_started/first_script.rb#L14-L15" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/getting_started/firstScript.spec.js#L19-L20" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/getting_started/FirstScriptTest.kt#L28-L29" >}}
{{< /tab >}}
{{< /tabpane >}}

### 7. Request element information
Elements store a lot of [information that can be requested]({{< ref "/documentation/webdriver/elements/information" >}}).

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/FirstScript.java#L27" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/getting_started/first_script.py#L19" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/GettingStarted/FirstScript.cs#L26" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/getting_started/first_script.rb#L18" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/getting_started/firstScript.spec.js#L23" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/getting_started/FirstScriptTest.kt#L32" >}}
{{< /tab >}}
{{< /tabpane >}}

### 8. End the session 

This ends the driver process, which by default closes the browser as well. 
No more commands can be sent to this driver instance. 
See [Quitting Sessions]({{< ref "../drivers/#quitting-sessions" >}}).

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/FirstScript.java#L29" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/getting_started/first_script.py#L21" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/GettingStarted/FirstScript.cs#L28" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/getting_started/first_script.rb#L20" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/getting_started/firstScript.spec.js#L28" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/getting_started/FirstScriptTest.kt#L35" >}}
{{< /tab >}}
{{< /tabpane >}}

## Running Selenium File

{{< alert-code >}}
Add example for how to execute each of the above pages via command line
{{< /alert-code >}}


## Next Steps

Most Selenium users execute many sessions and need to organize them to minimize duplication and keep the code
more maintainable. Read on to learn about how to put this code into context for your use case with
[Using Selenium]({{< ref "using_selenium.md" >}}).
