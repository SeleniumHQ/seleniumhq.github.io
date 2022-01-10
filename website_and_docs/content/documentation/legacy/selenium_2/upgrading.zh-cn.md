---
title: "从RC迁移到WebDriver"
linkTitle: "从RC迁移到WebDriver"
weight: 2
aliases: [
"/documentation/zh-cn/legacy_docs/migrating_from_rc_to_webdriver/",
"/zh-cn/documentation/legacy/migrating_from_rc_to_webdriver/"
]
---


## 如何迁移到Selenium WebDriver


在采用Selenium 2时, 一个常见的问题是, 
在将新测试添加到现有测试集中时, 
正确的做法是什么?
刚接触框架的用户可以通过使用新的WebDriver API编写测试开始. 
但是, 已经拥有一套现有测试的用户又该如何呢?
本指南旨在演示如何将现有测试迁移到新的API, 
从而允许使用WebDriver提供的新功能编写所有新测试. 

此处介绍的方法描述了向WebDriver API的零星迁移, 
而无需一次大刀阔斧地重新进行所有工作. 
这意味着您可以留出更多时间来迁移现有测试, 
这可以使您更轻松地决定将精力花在哪里.

本指南使用Java编写, 因为它为迁移提供了最佳支持. 
由于我们为其他语言提供了更好的工具, 
因此本指南将扩展为包括这些语言.


## 为什么要迁移到WebDriver


将一组测试从一个API移到另一个API需要大量的工作. 
为什么您和您的团队考虑采取此举?
这是您应考虑迁移Selenium测试以使用WebDriver的一些原因.

* 较小, 紧凑的API. 
WebDriver的API比原始的Selenium RC API更面向对象. 
这样可以更轻松地使用.
* 更好地模拟用户交互. 
WebDriver在可能的情况下利用本机事件与网页进行交互. 
这更紧密地模仿了您的用户使用您的网站和应用程序的方式. 
此外, WebDriver提供了高级的用户交互API, 
使您可以为与网站的复杂交互建模.
* 浏览器供应商的支持. 
Opera, Mozilla和Google都是WebDriver开发的积极参与者, 
并且各自都有工程师致力于改善框架. 
通常, 这意味着对WebDriver的支持已包含在浏览器本身中：
您的测试运行得尽可能快且稳定.


## 在开始之前


为了使迁移过程尽可能轻松, 
请确保所有测试都在最新的Selenium版本中正常运行. 
这听起来似乎显而易见, 但是最好说一下!


## 开始上手


开始迁移的第一步是更改获取Selenium实例的方式. 
使用Selenium RC时, 就像这样:

```java
Selenium selenium = new DefaultSelenium("localhost", 4444, "*firefox", "http://www.yoursite.com");
selenium.start();
```

应该这样替换:

```java
WebDriver driver = new FirefoxDriver();
Selenium selenium = new WebDriverBackedSelenium(driver, "http://www.yoursite.com");
```

## 下一步


一旦测试成功执行, 下一步就是迁移实际的测试代码以使用WebDriver API. 
根据代码的抽象程度, 
这可能是一个短暂的过程, 也可能是一个漫长的过程. 
在这两种情况下, 方法都是相同的, 
可以简单地总结一下：修改代码以在使用新API时进行编辑.

如果您需要从Selenium实例中提取基础WebDriver实现, 
则只需将其强制转换为WrapsDriver:

```java
WebDriver driver = ((WrapsDriver) selenium).getWrappedDriver();
```

这使您可以继续正常传递Selenium实例, 
但可以根据需要解包WebDriver实例.

有时, 您的代码库将主要使用较新的API. 
此时, 您可以翻转关系, 
使用WebDriver并按需实例化Selenium实例:

```java
Selenium selenium = new WebDriverBackedSelenium(driver, baseUrl);
```

## 常见问题


幸运的是, 您不是第一个进行此迁移的人, 
因此这里有其他人已经看到的一些常见问题以及如何解决它们.


### 单击和键入更加完整


Selenium RC测试中的常见模式如下所示:

```java
selenium.type("name", "exciting tex");
selenium.keyDown("name", "t");
selenium.keyPress("name", "t");
selenium.keyUp("name", "t");
```
    
这依赖于以下事实：
"类型"仅替换所标识元素的内容, 
而不触发用户与页面进行交互时通常会触发的所有事件. 
最后的"key*"直接调用导致JS处理程序按预期方式触发.

使用WebDriverBackedSelenium时, 
填写表单字段的结果将是"exciting texttt"：
并非您所期望的!
这样做的原因是WebDriver更准确地模拟了用户行为, 
因此一直会触发事件.

相同的事实有时可能会导致页面加载比Selenium 1测试中更早触发. 
如果WebDriver抛出"StaleElementException", 
您可以说这已经发生.


### WaitForPageToLoad很快返回

发现何时完成页面加载是一项艰巨的任务. 
我们的意思是"何时触发加载事件", 
"何时完成所有AJAX请求", 
"何时没有网络流量", 
"何时document.readyState发生更改"
或其他所有内容?

WebDriver试图模拟原始的Selenium行为, 
但是由于种种原因, 这种方法并不总是能完美发挥作用. 
最常见的原因是, 很难区分在尚未开始的页面加载与在方法调用之间完成的页面加载之间的区别. 
有时这意味着在页面完成（甚至开始!）加载之前, 控件已返回测试.

解决方案是等待特定的东西. 
通常, 这可能是您想与之交互的元素, 
或者是将某些Javascript变量设置为特定值. 
一个例子是:

```java
Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(30));
WebElement element= wait.until(visibilityOfElementLocated(By.id("some_id")));
```
    
其中"visibilityOfElementLocated"实现为:

```java
public ExpectedCondition<WebElement> visibilityOfElementLocated(final By locator) {
  return new ExpectedCondition<WebElement>() {
    public WebElement apply(WebDriver driver) {
      WebElement toReturn = driver.findElement(locator);
      if (toReturn.isDisplayed()) {
        return toReturn;
      }
      return null;
    }
  };
}
```
 
这看起来很复杂, 但这几乎是所有样板代码. 
唯一有趣的一点是, 将反复评估"ExpectedCondition", 
直到"apply"方法返回的结果既不是"null"
也不是Boolean.FALSE.

当然, 添加所有这些"等待"调用可能会使您的代码混乱. 
如果是这样, 并且您的需求很简单, 请考虑使用隐式等待:

```java
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
```

这样, 每次定位某个元素时（如果不存在该元素）, 
都会重试该位置, 直到该元素存在或经过30秒为止.

### 通过XPath或CSS选择器查找并不总是可行, 但在Selenium 1中却可以

在Selenium 1中, xpath通常使用捆绑的库而不是浏览器本身的功能. 
除非没有其他选择, 否则WebDriver将始终使用本机浏览器方法. 
这意味着复杂的xpath表达式可能会在某些浏览器上中断.

Selenium 1中的CSS选择器是使用Sizzle库实现的. 
这实现了CSS Selector规范的超集, 
而且并不总是清楚您越界的位置. 
如果您使用的是WebDriverBackedSelenium, 
并且使用Sizzle定位器而不是CSS选择器来查找元素, 
则会在控制台上记录一条警告. 
值得花时间去寻找这些东西, 
尤其是如果由于找不到元素而导致测试失败时.

### 没有Browserbot

Selenium RC是基于Selenium Core的, 
因此, 当您执行Javascript时, 
可以访问Selenium Core的某些部分以使事情变得容易. 
由于WebDriver不基于Selenium Core, 因此不再可能. 
如何判断您是否正在使用Selenium Core?简单!
只要看看您的"getEval"或类似调用是否在评估的Javascript中使用"Selenium"或"browserbot".

您可能正在使用browserbot获取测试的当前窗口或文档的句柄. 
幸运的是, WebDriver总是在当前窗口的上下文中评估JS, 
因此您可以直接使用"window"或"document".

另外, 您可能正在使用browserbot查找元素. 
在WebDriver中, 这样做的习惯是首先找到该元素, 
然后将其作为参数传递给Javascript. 
从而:

```java
String name = selenium.getEval(
    "selenium.browserbot.findElement('id=foo', browserbot.getCurrentWindow()).tagName");
```

变成:

```java
WebElement element = driver.findElement(By.id("foo"));
String name = (String) ((JavascriptExecutor) driver).executeScript(
    "return arguments[0].tagName", element);
```
        
请注意, 传入的"element"变量如何显示为JS标准"arguments"数组中的第一项.        


### 执行JavaScript不会返回任何内容


WebDriver的JavascriptExecutor将包装所有JS并将其评估为匿名表达式. 
这意味着您需要使用"return"关键字:

```java
String title = selenium.getEval("browserbot.getCurrentWindow().document.title");
```

变成:

```java
((JavascriptExecutor) driver).executeScript("return document.title;");
```
    
