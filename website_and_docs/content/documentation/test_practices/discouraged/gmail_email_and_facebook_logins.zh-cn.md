---
title: "Gmail, email 和 Facebook 登录"
linkTitle: "Gmail, email and Facebook"
weight: 4
aliases: [
"/documentation/zh-cn/worst_practices/gmail_email_and_facebook_logins/",
"/zh-cn/documentation/worst_practices/gmail_email_and_facebook_logins/"
] 
---


由于多种原因, 不建议使用WebDriver登录Gmail和Facebook等网站. 
除了违反这些网站的使用条款之外 (您可能会面临帐户被关闭的风险) , 
还有其运行速度缓慢且不可靠的因素.


理想的做法是使用电子邮件供应商提供的API, 
或者对于Facebook, 使用开发者工具的服务, 
该服务是被用于创建测试帐户、朋友等内容的API. 
尽管使用API可能看起来有些额外的工作量, 
但是您将获得基于速度、可靠性和稳定性的回报. 
API不会频繁更改, 但是网页和HTML定位符经常变化, 
并且需要您更新测试框架的代码.

在任何时候测试使用WebDriver登录第三方站点, 
都会增加测试失败的风险, 
因为这会使您的测试时间更长. 
通常的经验是, 执行时间较长的测试会更加脆弱和不可靠.

符合[W3C conformant](//w3c.github.io/webdriver/webdriver-spec.html)
的WebDriver实现, 
也会使用 `WebDriver` 的属性对 `navigator` 对象进行注释, 
用于缓解拒绝服务的攻击.
