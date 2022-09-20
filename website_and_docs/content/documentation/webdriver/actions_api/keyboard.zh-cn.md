---
title: "Keyboard actions"
linkTitle: "Keyboard"
weight: 2
description: >
  A representation of any key input device for interacting with a web page.
aliases: [
"/documentation/zh-cn/webdriver/keyboard/",
"/zh-cn/documentation/webdriver/keyboard/"
]
---

There are only 2 actions that can be accomplished with a keyboard:
pressing down on a key, and releasing a pressed key.
In addition to supporting ASCII characters, each keyboard key has
a representation that can be pressed or released in designated sequences.

## Keys

In addition to the keys represented by regular unicode, 
unicode values have been assigned to other keyboard keys for use with Selenium. 
Each language has its own way to reference these keys; the full list can be found
[here](https://www.w3.org/TR/webdriver/#keyboard-actions).

{{< tabpane code=false langEqualsHeader=true >}}
    {{< tab header="Java" >}}
Use the [Java Keys enum](https://github.com/SeleniumHQ/selenium/blob/selenium-4.2.0/java/src/org/openqa/selenium/Keys.java#L28)
    {{< /tab >}}
    {{< tab header="Python" >}}
Use the [Python Keys class](https://github.com/SeleniumHQ/selenium/blob/selenium-4.2.0/py/selenium/webdriver/common/keys.py#L23)
    {{< /tab >}}
    {{< tab header="CSharp" >}}
Use the [.NET static Keys class](https://github.com/SeleniumHQ/selenium/blob/selenium-4.2.0/dotnet/src/webdriver/Keys.cs#L28)
    {{< /tab >}}
    {{< tab header="Ruby" >}}
Use the [Ruby KEYS constant](https://github.com/SeleniumHQ/selenium/blob/selenium-4.2.0/rb/lib/selenium/webdriver/common/keys.rb#L28)
    {{< /tab >}}
    {{< tab header="JavaScript" >}}
Use the [JavaScript KEYS constant](https://github.com/SeleniumHQ/selenium/blob/selenium-4.2.0/javascript/node/selenium-webdriver/lib/input.js#L44)
   {{< /tab >}}
    {{< tab header="Kotlin" >}}
Use the [Java Keys enum](https://github.com/SeleniumHQ/selenium/blob/selenium-4.2.0/java/src/org/openqa/selenium/Keys.java#L28)
    {{< /tab >}}
{{< /tabpane >}}

## Key down

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/actions_api/KeysTest.java#L18-L21" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/actions_api/test_keys.py#L10-L13" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/ActionsAPI/KeysTest.cs#L17-L20" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/actions_api/keys_spec.rb#L13-L16" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/actionsApi/keysTest.spec.js#L19-L22" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/KeysTest.kt#L19-L22" >}}
{{< /tab >}}
{{< /tabpane >}}

## Key up

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/actions_api/KeysTest.java#L31-L36" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/actions_api/test_keys.py#L21-L26" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/ActionsAPI/KeysTest.cs#L30-L35" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/actions_api/keys_spec.rb#L25-L30" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/actionsApi/keysTest.spec.js#L34-L39" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/KeysTest.kt#L32-L37" >}}
{{< /tab >}}
{{< /tabpane >}}

## Send keys

This is a convenience method in the Actions API that combines keyDown and keyUp commands in one action.
Executing this command differs slightly from using the element method, but
primarily this gets used when needing to type multiple characters in the middle of other actions.

### Active Element

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/actions_api/KeysTest.java#L46-L48" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/actions_api/test_keys.py#L34-L36" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/ActionsAPI/KeysTest.cs#L45-L47" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/actions_api/keys_spec.rb#L39-L41" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/actionsApi/keysTest.spec.js#L47-L48" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/KeysTest.kt#L47-L49" >}}
{{< /tab >}}
{{< /tabpane >}}

### Designated Element

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/actions_api/KeysTest.java#L59-L62" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/actions_api/test_keys.py#L45-L48" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/ActionsAPI/KeysTest.cs#L58-L61" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/actions_api/keys_spec.rb#L51-L54" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.5.0" >}}
{{< gh-codeblock path="examples/javascript/test/actionsApi/keysTest.spec.js#L61-L65" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/KeysTest.kt#L60-L63" >}}
{{< /tab >}}
{{< /tabpane >}}

## Copy and Paste

Here's an example of using all of the above methods to conduct a copy / paste action.
Note that the key to use for this operation will be different depending on if it is a Mac OS or not.
This code will end up with the text: `SeleniumSelenium!`

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/actions_api/KeysTest.java#L73-L85" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/actions_api/test_keys.py#L55-L66" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/ActionsAPI/KeysTest.cs#L72-L82" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/actions_api/keys_spec.rb#L63-L73" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/actionsApi/keysTest.spec.js#L75-L87" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/KeysTest.kt#L74-L86" >}}
{{< /tab >}}
{{< /tabpane >}}
