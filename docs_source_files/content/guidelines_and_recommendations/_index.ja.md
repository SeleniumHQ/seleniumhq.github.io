---
title: "Guidelines and recommendations"
menuTitle: "Guidelines"
chapter: true
weight: 7
---

{{% notice info %}}
<i class="fas fa-language"></i> ページは英語から日本語へ訳されています。
日本語は話せますか？プルリクエストをして翻訳を手伝ってください!
{{% /notice %}}

# Guidelines and recommendations

A note on "Best Practices": We've intentionally avoided the phrase "Best
Practices" in this documentation. No one approach works for all situations.
We prefer the idea of "Guidelines and Recommendations." We encourage
you to read through these and thoughtfully decide what approaches
will work for you in your particular environment.

Functional testing is difficult to get right for many reasons.
As if application state, complexity, and dependencies don't make testing difficult enough,
dealing with browsers (especially with cross-browser incompatibilities)
makes writing good tests a challenge.

Selenium provides tools to make functional user interaction easier,
but doesn't help you write well-architected test suites.
In this chapter we offer advice, guidelines, and recommendations.
on how to approach functional web page automation.

This chapter records software design patterns popular
amongst many of the users of Selenium
that have proven successful over the years.
