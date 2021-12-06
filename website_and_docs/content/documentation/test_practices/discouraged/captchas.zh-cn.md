---
title: "验证码"
linkTitle: "验证码"
weight: 1
aliases: [
"/documentation/zh-cn/worst_practices/captchas/",
"/zh-cn/documentation/worst_practices/captchas/"
] 
---
验证码 (CAPTCHA), 是 _全自动区分计算机和人类的图灵测试_ *(Completely Automated Public Turing test to tell Computers and Humans Apart)* 的简称,
是被明确地设计用于阻止自动化的, 所以不要尝试!  
规避验证码的检查, 主要有两个策略:

* 在测试环境中禁用验证码
* 添加钩子以允许测试绕过验证码
