---
title: "Browser navigation"
weight: 1
---

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
// Navigate to a URL
driver.Navigate().GoToUrl("http://www.google.com");

// Go forward one page in the browser (if you're not on the
// last page that was viewed).
driver.Navigate().Forward();

// Go back one page in the browser (if you're not on the
// first page that was viewed).
driver.Navigate().Back();
            
// Refresh the current page.
driver.Navigate().Refresh();
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
# Navigate to a URL (both of the statements below are
# functionally equivalent).
driver.get("https://www.google.com");
driver.navigate().to("https://www.google.com");

# Go forward one page in the browser (if you're not on the
# last page that was viewed).
driver.navigate().forward();

# Go back one page in the browser (if you're not on the
# first page that was viewed).
driver.navigate().back();

# Refresh the current page.
driver.navigate().refresh();
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
