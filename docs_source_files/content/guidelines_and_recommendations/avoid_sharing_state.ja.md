---
title: "Avoid sharing state"
weight: 6
---

{{% notice info %}}
<i class="fas fa-language"></i> ページは英語から日本語へ訳されています。
日本語は話せますか？プルリクエストをして翻訳を手伝ってください!
{{% /notice %}}

Although mentioned in several places it is worth mentioning again. Ensure
tests are isolated from one another.

Don't share test data. Imagine several tests that each query the database
for valid orders before picking one to perform an action on. Should two tests
pick up the same order you are likely to get unexpected behaviour.

Clean up stale data in the application that might be picked up by another
test e.g. invalid order records.

Create a new WebDriver instance per test. This helps ensure test isolation
and makes parallelisation simpler.
