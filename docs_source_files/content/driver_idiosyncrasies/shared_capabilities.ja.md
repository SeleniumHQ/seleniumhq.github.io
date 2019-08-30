---
title: "Shared capabilities"
weight: 1
---

{{% notice info %}}
<i class="fas fa-language"></i> ページは英語から日本語へ訳されています。
日本語は話せますか？プルリクエストをして翻訳を手伝ってください!
{{% /notice %}}

### pageLoadStrategy
When navigating to a new page via URL, by default Selenium will wait
until the page has fully loaded before responding. This works well for
beginners, but can cause long wait times on pages that load a large
number of third party resources. Using a non default strategy can make
test execution faster in cases like this, but can also introduce flakiness
where elements on the page change position as elements load in and change
size.

The page load strategy queries the
[document.readyState](//developer.mozilla.org/en-US/docs/Web/API/Document/readyState)
as described in the table below:

| Strategy | Ready State | Notes |
| -------- | ----------- | ----- |
| normal | complete | Used by default, waits for all resources to download |
| eager | interactive | DOM access is ready, but other resources like images may still be loading |
| none | Any | Does not block WebDriver at all |
