---
title: "Page loading strategy"
weight: 8
---

Defines the current session's page loading strategy. 
By default, when selenium loads a page, 
it follows the _normal_ pageLoadStrategy. 
It is always recommended to stop downloading additional 
resources (like images, css, js) when the page loading takes lot of time.

WebDriver _pageLoadStrategy_ supports the following values:

## normal

This will make selenium to wait for the entire page is loaded. 
When set to **normal** the selenium waits until the 
[load](https://developer.mozilla.org/en-US/docs/Web/API/Window/load_event) event fire is returned.

{{< code-tab >}}
  {{< code-panel language="java" >}}
// Please raise a pr
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="c#" >}}
 // Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Please raise a PR
  {{< / code-panel >}}
{{< / code-tab >}}

## eager

This will make selenium to wait until the 
initial HTML document has been completely loaded, 
and discards loading of stylesheets, images and subframes.

When set to **eager** selenium waits until 
[DOMContentLoaded](https://developer.mozilla.org/en-US/docs/Web/API/Document/DOMContentLoaded_event) event fire is returned.

{{< code-tab >}}
  {{< code-panel language="java" >}}
// Please raise a pr
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="c#" >}}
 // Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Please raise a PR
  {{< / code-panel >}}
{{< / code-tab >}}