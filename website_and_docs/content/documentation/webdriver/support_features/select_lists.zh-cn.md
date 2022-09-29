---
title: "使用选择列表元素"
linkTitle: "选择列表"
weight: 10
aliases: [
"/documentation/zh-cn/support_packages/working_with_select_elements/",
"/zh-cn/documentation/support_packages/working_with_select_elements/",
"/zh-cn/documentation/webdriver/elements/select_elements/",
"/zh-cn/documentation/webdriver/elements/select_list",
]
description: >
  与其他元素相比，选择列表具有特殊的行为.
---

Select对象现在将为您提供一系列命令,
用于允许您与 `<select>` 元素进行交互.

如果您使用的是 Java 或 .NET，
请确保您在代码中已正确加载所需的包.
您可以通过GitHub查看下面示例的完整代码.

请注意，此类仅适用于 HTML 元素 `select` 和 `option`.
这个类将不适用于那些通过 `div` 或 `li` 
并使用JavaScript遮罩层设计的下拉列表.

## 类型

选择方法的行为可能会有所不同，
具体取决于正在使用的 `<select>` 元素的类型.

### 单选

这是标准的下拉对象，其只能选定一个选项.

```html
<select name="selectomatic">
    <option selected="selected" id="non_multi_option" value="one">One</option>
    <option value="two">Two</option>
    <option value="four">Four</option>
    <option value="still learning how to count, apparently">Still learning how to count, apparently</option>
</select>
```

### 复选

此选择列表允许同时选定和取消选择多个选项.
这仅适用于具有 `multiple` 属性的 `<select>`元素.

```html
<select name="multi" id="multi" multiple="multiple">
    <option selected="selected" value="eggs">Eggs</option>
    <option value="ham">Ham</option>
    <option selected="selected" value="sausages">Sausages</option>
    <option value="onion gravy">Onion gravy</option>
</select>
```

## 构建类

首先定位一个 `<select>` 元素,
然后借助其初始化一个`Select` 对象.
请注意, 从 Selenium 4.5 开始,
您无法针对禁用的 `<select>` 元素构建 `Select` 对象.


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

## 选项列表

共有两种列表可以被获取:

### 全部选项

获取 `<select>` 元素中所有选项列表:

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

### 选中的选项

获取 `<select>` 元素中所选中的选项列表.
对于标准选择列表这将只是一个包含一个元素的列表, 
对于复选列表则表示包含的零个或多个元素.

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

## 选项

Select类提供了三种选择选项的方法.
请注意, 对于复选类型的选择列, 
对于要选择的每个元素可以重复使用这些方法.

### 文本

根据其可见文本选择选项

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

### 值

根据其值属性选择选项

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

### 序号

根据其在列表中的位置选择选项

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

### 禁用的选项

{{< badge-version version="4.5" >}}

具有 `disabled` 属性的选项可能无法被选择.

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

## 取消选择选项

只有复选类型的选择列表才能取消选择选项.
您可以对要选择的每个元素重复使用这些方法.

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
