---
title: "期望状态的等待"
linkTitle: "期望状态"
weight: 1
description: >
  这些是用于描述需要等待的内容的类.
---

期望状态与 [显示等待]({{< ref "../waits#explicit-waits" >}}) 一起使用.
与其定义要使用 _lambda_ 执行的代码块, 
不如使用 _lambda_ 执行可以创建 Conditions 方法来表示等待的常见事物.
有些方法将定位器作为参数, 有些方法将元素作为参数.

这些方法可以包括以下条件:

* 元素存在
* 元素过时
* 元素可见
* 文本可见
* 标题包含特定值

{{< tabpane text=true >}}
{{% tab header="Java" %}}
[Expected Conditions Documentation](https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/ui/ExpectedConditions.html)
{{< badge-code >}}
{{% /tab %}}
{{< tab header="Python" >}}
[Expected Conditions Documentation](https://www.selenium.dev/selenium/docs/api/py/webdriver_support/selenium.webdriver.support.expected_conditions.html)
{{< badge-code >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
.NET stopped supporting Expected Conditions in Selenium 4 to minimize maintenance hassle and redundancy.
{{< /tab >}}
{{< tab header="Ruby" >}}
Ruby makes frequent use of blocks, procs and lambdas and does not need Expected Conditions classes
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}
