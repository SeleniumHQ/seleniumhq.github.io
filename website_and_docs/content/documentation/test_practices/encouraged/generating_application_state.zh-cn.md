---
title: "生成应用程序状态"
linkTitle: "生成应用程序状态"
weight: 5
aliases: [
"/documentation/zh-cn/guidelines_and_recommendations/generating_application_state/",
"/zh-cn/documentation/guidelines/generating_application_state/"
]
---
 

Selenium不应用于准备测试用例.
测试用例中所有重复性动作和准备工作, 都应通过其他方法来完成.  
例如, 大多数Web UI都具有身份验证 (诸如一个登录表单) . 
在每次测试之前通过Web浏览器进行登录的消除, 将提高测试的速度和稳定性. 
应该创建一种方法来获取对 AUT* 的访问权限 (例如, 使用API登录并设置Cookie) . 
此外, 不应使用Selenium创建预加载数据来进行测试的方法.  
如前所述, 应利用现有的API为 AUT* 创建数据. 
***AUT**: 待测系统
