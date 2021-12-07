---
title: "领域特定语言"
linkTitle: "领域特定语言"
weight: 4
aliases: [
"/documentation/zh-cn/guidelines_and_recommendations/domain_specific_language/",
"/zh-cn/documentation/guidelines/domain_specific_language/"
]
---


领域特定语言 (DSL) 是一种为用户提供解决问题的表达方式的系统.
它使用户可以按照自己的术语与系统进行交互, 而不仅仅是通过程序员的语言.

您的用户通常并不关心您网站的外观. 
他们不在乎装饰, 动画或图形. 
他们希望借助于您的系统, 以最小的难度使新员工融入整个流程；
他们想预订去阿拉斯加的旅行；
他们想以折扣价配置和购买独角兽. 
您作为测试人员的工作应尽可能接近"捕捉”这种思维定势. 
考虑到这一点, 我们开始着手"建模”您正在工作的应用程序, 
以使测试脚本 (发布前用户仅有的代理) "说话”并代表用户.


在Selenium中, DSL通常由方法表示, 
其编写方式使API简单易读-它们使开发人员和干系人
 (用户, 产品负责人, 商业智能专家等) 之间能够产生汇报. 
 
 
## 好处

* **可读:** 业务关系人可以理解.
* **可写:** 易于编写, 避免不必要的重复.
* **可扩展:** 可以 (合理地) 添加功能而无需打破约定以及现有功能.
* **可维护:** 通过将实现细节排除在测试用例之外, 您可以很好地隔离 AUT* 的修改.


## Java

以下是Java中合理的DSL方法的示例. 
为简便起见, 假定 `driver` 对象是预定义的并且可用于该方法.

```java
/**
 * Takes a username and password, fills out the fields, and clicks "login".
 * @return An instance of the AccountPage
 */
public AccountPage loginAsUser(String username, String password) {
  WebElement loginField = driver.findElement(By.id("loginField"));
  loginField.clear();
  loginField.sendKeys(username);

  // Fill out the password field. The locator we're using is "By.id", and we should
  // have it defined elsewhere in the class.
  WebElement passwordField = driver.findElement(By.id("password"));
  passwordField.clear();
  passwordField.sendKeys(password);

  // Click the login button, which happens to have the id "submit".
  driver.findElement(By.id("submit")).click();

  // Create and return a new instance of the AccountPage (via the built-in Selenium
  // PageFactory).
  return PageFactory.newInstance(AccountPage.class);
}
```

此方法完全从测试代码中抽象出输入字段, 按钮, 单击甚至页面的概念. 
使用这种方法, 测试人员要做的就是调用此方法. 
这给您带来了维护方面的优势: 如果登录字段曾经更改过, 
则只需更改此方法-而非您的测试.

```java
public void loginTest() {
    loginAsUser("cbrown", "cl0wn3");

    // Now that we're logged in, do some other stuff--since we used a DSL to support
    // our testers, it's as easy as choosing from available methods.
    do.something();
    do.somethingElse();
    Assert.assertTrue("Something should have been done!", something.wasDone());

    // Note that we still haven't referred to a button or web control anywhere in this
    // script...
}
```


郑重强调: 您的主要目标之一应该是编写一个API, 
该API允许您的测试解决 **当前的问题, 而不是UI的问题**. 
用户界面是用户的次要问题–用户并不关心用户界面, 他们只是想完成工作. 
您的测试脚本应该像用户希望做的事情以及他们想知道的事情的完整清单那样易于阅读. 
测试不应该考虑UI如何要求您去做.

***AUT**: 待测系统

