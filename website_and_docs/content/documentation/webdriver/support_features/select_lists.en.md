---
title: "Working with select list elements"
linkTitle: "Select Lists"
weight: 5
aliases: [
"/documentation/en/support_packages/working_with_select_elements/",
"/documentation/support_packages/working_with_select_elements/",
"/documentation/webdriver/elements/select_elements/",
"/documentation/webdriver/elements/select_list",
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

{{< tabpane code=false langEqualsHeader=true >}}
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
{{< gh-codeblock path="/examples/ruby/spec/support/select_list_spec.rb#L14-L15" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## List options

There are two lists that can be obtained:

### All options

Get a list of all options in the `<select>` element:

{{< tabpane code=false langEqualsHeader=true >}}
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
{{< gh-codeblock path="/examples/ruby/spec/support/select_list_spec.rb#L41" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Selected options

Get a list of selected options in the `<select>` element. For a standard select list
this will only be a list with one element, for a multiple select list it can contain
zero or many elements.

{{< tabpane code=false langEqualsHeader=true >}}
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
{{< gh-codeblock path="/examples/ruby/spec/support/select_list_spec.rb#L44" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## Select option

The Select class provides three ways to select an option.
Note that for multiple select type Select lists, you can repeat these methods
for each element you want to select.

### Text

Select the option based on its visible text

{{< tabpane code=false langEqualsHeader=true >}}
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
{{< gh-codeblock path="/examples/ruby/spec/support/select_list_spec.rb#L21" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Value

Select the option based on its value attribute

{{< tabpane code=false langEqualsHeader=true >}}
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
{{< gh-codeblock path="/examples/ruby/spec/support/select_list_spec.rb#L24" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Index

Select the option based on its position in the list

{{< tabpane code=false langEqualsHeader=true >}}
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
{{< gh-codeblock path="/examples/ruby/spec/support/select_list_spec.rb#L27" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
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

{{< tabpane code=false langEqualsHeader=true >}}
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
{{< gh-codeblock path="/examples/ruby/spec/support/select_list_spec.rb#L63-L65" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## De-select option

Only multiple select type select lists can have options de-selected. 
You can repeat these methods for each element you want to select.

{{< tabpane code=false langEqualsHeader=true >}}
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
{{< gh-codeblock path="/examples/ruby/spec/support/select_list_spec.rb#L53" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}
