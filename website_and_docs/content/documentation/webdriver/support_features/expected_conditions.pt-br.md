[expected_conditions.ja.md](expected_conditions.ja.md)---
title: "Waiting with Expected Conditions"
linkTitle: "Expected Conditions"
weight: 1
description: >
  These are classes used to describe what needs to be waited for.
---

Expected Conditions are used with [Explicit Waits](({{< ref "waits#explicit-waits" >}})).
Instead of defining the block of code to be executed with a _lambda_, an expected
conditions method can be created to represent common things that get waited on. Some
methods take locators as arguments, others take elements as arguments.

These methods can include conditions such as:

* element exists
* element is stale
* element is visible
* text is visible
* title contains specified value

{{< tabpane text=true langEqualsHeader=true >}}
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
