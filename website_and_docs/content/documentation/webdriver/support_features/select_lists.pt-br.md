---
title: "Trabalhando com elementos select"
linkTitle: "Trabalhando com elementos select"
weight: 10
needsTranslation: true
aliases: [
"/documentation/pt-br/support_packages/working_with_select_elements/",
"/pt-br/documentation/support_packages/working_with_select_elements/",
"/pt-br/documentation/webdriver/elements/select_elements/",
"/pt-br/documentation/webdriver/elements/select_list",
]
description: >
  Select lists have special behaviors compared to other elements.
---

The Select object will now give you a series of commands
that allow you to interact with a `<select>` element.

If you are using Java or .NET make sure that you've properly required the support package
in your code. See the full code from GitHub in any of the examples below.

Note that this class only works for HTML elements `select` and `option`.
It is possible to design drop-downs with JavaScript overlays using `div` or `li`,
and this class will not work for those.

## Types

Select methods may behave differently depending on which type of `<select>` element is being worked with.

### Single select

This is the standard drop-down object where one and only one option may be selected.

```html
<select name="selectomatic">
    <option selected="selected" id="non_multi_option" value="one">One</option>
    <option value="two">Two</option>
    <option value="four">Four</option>
    <option value="still learning how to count, apparently">Still learning how to count, apparently</option>
</select>
```

### Multiple select

This select list allows selecting and deselecting more than one option at a time.
This only applies to `<select>` elements with the `multiple` attribute.

```html
<select name="multi" id="multi" multiple="multiple">
    <option selected="selected" value="eggs">Eggs</option>
    <option value="ham">Ham</option>
    <option selected="selected" value="sausages">Sausages</option>
    <option value="onion gravy">Onion gravy</option>
</select>
```

## Create class

First locate a `<select>` element, then use it to initialize a `Select` object.
Note that as of Selenium 4.5, you can't create a `Select` object if the `<select>` element is disabled.

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/support/SelectListTest.java#L23-L24" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/support/test_select_list.py#L9-L10" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Support/SelectListTest.cs#L23-L24" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/support/select_list_spec.rb#L13-L14" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/select/selectListTest.spec.js#L18-L19" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/support/SelectListTest.kt#L20-L21" >}}
{{< /tab >}}
{{< /tabpane >}}

## List options

There are two lists that can be obtained:

### All options

Get a list of all options in the `<select>` element:

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/support/SelectListTest.java#L51" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/support/test_select_list.py#L37" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Support/SelectListTest.cs#L52" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/support/select_list_spec.rb#L40" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/select/selectListTest.spec.js#L45" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/support/SelectListTest.kt#L48" >}}
{{< /tab >}}
{{< /tabpane >}}

### Selected options

Get a list of selected options in the `<select>` element. For a standard select list
this will only be a list with one element, for a multiple select list it can contain
zero or many elements.

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/support/SelectListTest.java#L54" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/support/test_select_list.py#L40" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Support/SelectListTest.cs#L56" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/support/select_list_spec.rb#L43" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/select/selectListTest.spec.js#L51" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/support/SelectListTest.kt#L51" >}}
{{< /tab >}}
{{< /tabpane >}}

## Select option

The Select class provides three ways to select an option.
Note that for multiple select type Select lists, you can repeat these methods
for each element you want to select.

### Text

Select the option based on its visible text

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/support/SelectListTest.java#L30" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/support/test_select_list.py#L16" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Support/SelectListTest.cs#L30" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/support/select_list_spec.rb#L20" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/select/selectListTest.spec.js#L25" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/support/SelectListTest.kt#L27" >}}
{{< /tab >}}
{{< /tabpane >}}

### Value

Select the option based on its value attribute

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/support/SelectListTest.java#L33" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/support/test_select_list.py#L19" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Support/SelectListTest.cs#L33" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/support/select_list_spec.rb#L23" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/select/selectListTest.spec.js#L28" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/support/SelectListTest.kt#L30" >}}
{{< /tab >}}
{{< /tabpane >}}

### Index

Select the option based on its position in the list

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/support/SelectListTest.java#L36" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/support/test_select_list.py#L22" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Support/SelectListTest.cs#L36" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/support/select_list_spec.rb#L26" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/select/selectListTest.spec.js#L31" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/support/SelectListTest.kt#L33" >}}
{{< /tab >}}
{{< /tabpane >}}

### Disabled options

{{< badge-version version="4.5" >}}

Options with a `disabled` attribute may not be selected.

```html
    <select name="single_disabled">
      <option id="sinlge_disabled_1" value="enabled">Enabled</option>
      <option id="sinlge_disabled_2" value="disabled" disabled="disabled">Disabled</option>
    </select>
```

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/support/SelectListTest.java#L77-L79" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/support/test_select_list.py#L60-L61" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Support/SelectListTest.cs#L77" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/support/select_list_spec.rb#L62-L64" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/select/selectListTest.spec.js#L73-L76" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/support/SelectListTest.kt#L74-L76" >}}
{{< /tab >}}
{{< /tabpane >}}

## De-select option

Only multiple select type select lists can have options de-selected. 
You can repeat these methods for each element you want to select.

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/support/SelectListTest.java#L66" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/support/test_select_list.py#L49" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Support/SelectListTest.cs#L65" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/support/select_list_spec.rb#L52" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/select/selectListTest.spec.js#L63" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/support/SelectListTest.kt#L63" >}}
{{< /tab >}}
{{< /tabpane >}}
