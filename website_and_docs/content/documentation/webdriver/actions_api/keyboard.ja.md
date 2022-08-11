---
title: "Keyboard actions"
linkTitle: "Keyboard"
weight: 2
description: >
  A representation of any key input device for interacting with a web page.
aliases: [
"/documentation/en/webdriver/keyboard/",
"/documentation/webdriver/keyboard/"
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

{{< tabpane disableCodeBlock=true height="1">}}
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

{{< tabpane disableCodeBlock=true height="4">}}
    {{< tab header="Java" >}}
        {{< gh-codeblock path="examples/java/src/test/java/dev/selenium/actions_api/KeysTest.java#L18-L21" >}}
    {{< /tab >}}
    {{< tab header="Python" >}}
        {{< gh-codeblock path="examples/python/tests/actions_api/test_keys.py#L10-L13" >}}
    {{< /tab >}}
    {{< tab header="CSharp" >}}
        {{< gh-codeblock path="examples/dotnet/SeleniumDocs/ActionsAPI/KeysTest.cs#L16-L19" >}}
    {{< /tab >}}
    {{< tab header="Ruby" >}}
        {{< gh-codeblock path="examples/ruby/spec/actions_api/keys_spec.rb#L10-L13" >}}
    {{< /tab >}}
    {{< tab header="JavaScript" >}}
        {{< gh-codeblock path="examples/javascript/test/actionsApi/keysTest.spec.js#L18-L21" >}}
   {{< /tab >}}
    {{< tab header="Kotlin" >}}
        // Add Code
    {{< /tab >}}
{{< /tabpane >}}

## Key up

{{< tabpane disableCodeBlock=true height="6">}}
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
        {{< gh-codeblock path="examples/ruby/spec/actions_api/keys_spec.rb#L22-L27" >}}
    {{< /tab >}}
    {{< tab header="JavaScript" >}}
        {{< gh-codeblock path="examples/javascript/test/actionsApi/keysTest.spec.js#L33-L38" >}}
   {{< /tab >}}
    {{< tab header="Kotlin" >}}
        // Add Code
    {{< /tab >}}
{{< /tabpane >}}

## Send keys

This is a convenience method in the Actions API that combines keyDown and keyUp commands in one action.
Executing this command differs slightly from using the element method, but
primarily this gets used when needing to type multiple characters in the middle of other actions.

### Active Element

{{< tabpane disableCodeBlock=true height="3">}}
    {{< tab header="Java" >}}
        {{< gh-codeblock path="examples/java/src/test/java/dev/selenium/actions_api/KeysTest.java#L46-L48" >}}
    {{< /tab >}}
    {{< tab header="Python" >}}
        {{< gh-codeblock path="examples/python/tests/actions_api/test_keys.py#L34-L36" >}}
    {{< /tab >}}
    {{< tab header="CSharp" >}}
        {{< gh-codeblock path="examples/dotnet/SeleniumDocs/ActionsAPI/KeysTest.cs#L46-L48" >}}
    {{< /tab >}}
    {{< tab header="Ruby" >}}
        {{< gh-codeblock path="examples/ruby/spec/actions_api/keys_spec.rb#L36-L38" >}}
    {{< /tab >}}
    {{< tab header="JavaScript" >}}
        {{< gh-codeblock path="examples/javascript/test/actionsApi/keysTest.spec.js#L49-L51" >}}
   {{< /tab >}}
    {{< tab header="Kotlin" >}}
        // Add Code
    {{< /tab >}}
{{< /tabpane >}}


### Designated Element

{{< tabpane disableCodeBlock=true height="4">}}
    {{< tab header="Java" >}}
        {{< gh-codeblock path="examples/java/src/test/java/dev/selenium/actions_api/KeysTest.java#L59-L62" >}}
    {{< /tab >}}
    {{< tab header="Python" >}}
        {{< gh-codeblock path="examples/python/tests/actions_api/test_keys.py#L45-L48" >}}
    {{< /tab >}}
    {{< tab header="CSharp" >}}
        {{< gh-codeblock path="examples/dotnet/SeleniumDocs/ActionsAPI/KeysTest.cs#L60-L63" >}}
    {{< /tab >}}
    {{< tab header="Ruby" >}}
        {{< gh-codeblock path="examples/ruby/spec/actions_api/keys_spec.rb#L48-L51" >}}
    {{< /tab >}}
    {{< tab header="JavaScript" >}}
        // Add Code
   {{< /tab >}}
    {{< tab header="Kotlin" >}}
        // Add Code
    {{< /tab >}}
{{< /tabpane >}}

## Copy and Paste

Here's an example of using all of the above methods to conduct a copy / paste action.
Note that the key to use for this operation will be different depending on if it is a Mac OS or not.
This code will end up with the text: `SeleniumSelenium!`

{{< tabpane disableCodeBlock=true height="13">}}
    {{< tab header="Java" >}}
        {{< gh-codeblock path="examples/java/src/test/java/dev/selenium/actions_api/KeysTest.java#L73-L85" >}}
    {{< /tab >}}
    {{< tab header="Python" >}}
        {{< gh-codeblock path="examples/python/tests/actions_api/test_keys.py#L55-L66" >}}
    {{< /tab >}}
    {{< tab header="CSharp" >}}
        {{< gh-codeblock path="examples/dotnet/SeleniumDocs/ActionsAPI/KeysTest.cs#L76-L87" >}}
    {{< /tab >}}
    {{< tab header="Ruby" >}}
        {{< gh-codeblock path="examples/ruby/spec/actions_api/keys_spec.rb#L60-L70" >}}
    {{< /tab >}}
    {{< tab header="JavaScript" >}}
        // Add Code
   {{< /tab >}}
    {{< tab header="Kotlin" >}}
        // Add Code
    {{< /tab >}}
{{< /tabpane >}}
