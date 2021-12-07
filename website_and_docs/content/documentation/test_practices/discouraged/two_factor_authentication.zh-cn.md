---
title: "双因素认证"
linkTitle: "双因素认证"
weight: 8
aliases: [
"/documentation/zh-cn/worst_practices/two_factor_authentication/",
"/zh-cn/documentation/worst_practices/two_factor_authentication/"
] 
---

双因素认证通常简写成 _2FA_ 是一种一次性密码（OTP）通常用在移动应用上例如“谷歌认证器”，
“微软认证器”等等，或者通过短信或者邮件来认证。在Selenium自动化中这些都是影响有效自动化
的极大挑战。虽然也有一些方法可以自动化这些过程，但是同样对于Selenium自动化也引入了很多不安全因素。
所以你应该要避免对2FA自动化。

这里有一些对于如何绕过2FA校验的建议：

* 在测试环境中对特定用户禁止2FA校验，这样对于这些特定用户可以直接进行自动化测试。
* 禁止2FA校验在测试环境中。
* 对于特定IP区域禁止2FA校验，这样我们可以配置测试机器的IP在这些白名单区域中。
