---
title: "Generating application state"
weight: 3
---

{{% notice info %}}
<i class="fas fa-language"></i> ページは英語から日本語へ訳されています。
日本語は話せますか？プルリクエストをして翻訳を手伝ってください!
{{% /notice %}}

Selenium should not be used to prepare a test case.  All repetitive
actions, and preparation for a test case, should be done through other
methods.  For example, most web UIs have authentication (e.g. a login
form). Eliminating logging in via web browser before every test will
improve both the speed and stability of the test. A method should be
created to gain access to the AUT (e.g. using an API to login and set a
cookie).  Also, creating methods to pre-load data for
testing should not be done using Selenium.  As mentioned previously,
existing APIs should be leveraged to create data for the AUT.
