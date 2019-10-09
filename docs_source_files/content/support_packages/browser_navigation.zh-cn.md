---
title: "Browser navigation"
weight: 1
---

{{% notice info %}}
<i class="fas fa-language"></i> 页面需要从英语翻译为简体中文。
您熟悉英语与简体中文吗？帮助我们翻译它，通过 pull requests 给我们！
{{% /notice %}}

There are commands for various webpage loading actions:

{{< code-tab >}}
  {{< code-panel language="java" >}}
// Navigate to a URL (both of the statements below are
// functionally equivalent).
driver.get("https://www.google.com");
driver.navigate().to("https://www.google.com");

// Go forward one page in the browser (if you're not on the
// last page that was viewed).
driver.navigate().forward();

// Go back one page in the browser (if you're not on the
// first page that was viewed).
driver.navigate().back();

// Refresh the current page.
driver.navigate().refresh();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Navigate to a URL.
driver.get('https://www.google.com')

# Go forward one page in the browser (if you're not on the
# last page that was viewed).
driver.forward()

# Go back one page in the browser (if you're not on the
# first page that was viewed).
driver.back()

# Refresh the current page.
driver.refresh()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Navigate to a URL (both of the statements below are
# functionally equivalent).
driver.get('https://www.google.com')
driver.navigate.to('https://www.google.com')

# Go forward one page in the browser (if you're not on the
# last page that was viewed).
driver.navigate.forward

# Go back one page in the browser (if you're not on the
# first page that was viewed).
driver.navigate.back

# Refresh the current page.
driver.navigate.refresh
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// We don't have a JavaScript code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Navigate to a URL (both of the statements below are
// functionally equivalent).
driver.get("https://www.google.com")
driver.navigate().to("https://www.google.com")

// Go forward one page in the browser (if you're not on the
// last page that was viewed).
driver.navigate().forward()

// Go back one page in the browser (if you're not on the
// first page that was viewed).
driver.navigate().back()

// Refresh the current page.
driver.navigate().refresh()
  {{< / code-panel >}}
{{< / code-tab >}}