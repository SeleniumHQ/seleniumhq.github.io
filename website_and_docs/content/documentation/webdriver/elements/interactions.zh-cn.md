---
title: "Web元素交互"
linkTitle: "交互"
weight: 2
description: >
   用于操纵表单的高级指令集.

---

仅有五种基本命令可用于元素的操作:
* [点击](https://w3c.github.io/webdriver/#element-click) (适用于任何元素)
* [发送键位](https://w3c.github.io/webdriver/#element-send-keys) (仅适用于文本字段和内容可编辑元素)
* [清除](https://w3c.github.io/webdriver/#element-send-keys) (仅适用于文本字段和内容可编辑元素)
* 提交 (仅适用于表单元素)
* 选择 (参见 [选择列表元素]({{< ref "select_lists.md" >}}))

## 附加验证

这些方法的设计目的是尽量模拟用户体验, 所以,
与 [Actions接口]({{< ref "/documentation/webdriver/actions_api/" >}}) 不同, 
在指定制定操作之前, 
会尝试执行两件事.
1. 如果它确定元素在视口之外, 
   [则会将元素滚动到视图中](https://w3c.github.io/webdriver/#dfn-scrolls-into-view), 
   特别是将元素底部与视口底部对齐.
2. 确保元素在执行操作之前是[可交互的](https://w3c.github.io/webdriver/#interactability) .
   这可能意味着滚动不成功, 
   或者该元素没有以其他方式显示.  
   确定某个元素是否显示在页面上太难了
   [无法直接在webdriver规范中定义](https://w3c.github.io/webdriver/#element-displayedness),
   因此Selenium发送一个带有JavaScript原子的执行命令, 
   检查是否有可能阻止该元素显示.
   如果确定某个元素不在视口中, 不显示, 不可
   [键盘交互](https://w3c.github.io/webdriver/#dfn-keyboard-interactable), 
   或不可
   [指针交互](https://w3c.github.io/webdriver/#dfn-pointer-interactable),
   则返回一个[元素不可交互](https://w3c.github.io/webdriver/#dfn-element-not-interactable) 错误.

## 点击

[元素点击命令](https://w3c.github.io/webdriver/#dfn-element-click) 执行在 [元素中央](https://w3c.github.io/webdriver/#dfn-center-point).
如果元素中央由于某些原因被 [遮挡](https://w3c.github.io/webdriver/#dfn-obscuring) ,
Selenium将返回一个 [元素点击中断](https://w3c.github.io/webdriver/#dfn-element-click-intercepted) 错误.


{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/elements/InteractionTest.java#L18-L22" >}}
{{< /tab >}}
  {{< tab header="Python" >}}

    # Navigate to url
	driver.get("https://www.selenium.dev/selenium/web/inputs.html")

    # Click on the element 
	driver.find_element(By.NAME, "color_input").click()
  {{< /tab >}}
  {{< tab header="CSharp" >}}

  // Navigate to Url
  driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/inputs.html");

  // Click the element
  driver.FindElement(By.Name("color_input")).Click();
  
  {{< /tab >}}
  {{< tab header="Ruby" >}}

    # Navigate to URL
  driver.get 'https://www.selenium.dev/selenium/web/inputs.html'

    # Click the element
  driver.find_element(name: 'color_input').click

  {{< /tab >}}
  {{< tab header="JavaScript" >}}

    // Navigate to Url
    await driver.get('https://www.selenium.dev/selenium/web/inputs.html');

    // Click the element
    await driver.findElement(By.name('color_input')).click();
  
  {{< /tab >}}
  {{< tab header="Kotlin" >}}

    // Navigate to Url
    driver.get("https://www.selenium.dev/selenium/web/inputs.html")

    // Click the element
    driver.findElement(By.name("color_input")).click();
  
  {{< /tab >}}
{{< /tabpane >}}


## 发送键位

[元素发送键位命令](https://w3c.github.io/webdriver/#dfn-element-send-keys)
将录入提供的键位到 [可编辑的](https://w3c.github.io/webdriver/#dfn-editable) 元素.
通常, 这意味着元素是具有 `文本` 类型的表单的输入元素或具有 `内容可编辑` 属性的元素.
如果不可编辑, 则返回
[无效元素状态](https://w3c.github.io/webdriver/#dfn-invalid-element-state) 错误.

[以下](https://www.w3.org/TR/webdriver/#keyboard-actions) 
是WebDriver支持的按键列表.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/elements/InteractionTest.java#L27-L32" >}}
{{< /tab >}}

  {{< tab header="Python" >}}


    # Navigate to url
	driver.get("https://www.selenium.dev/selenium/web/inputs.html")

    # Clear field to empty it from any previous data
	driver.find_element(By.NAME, "email_input").clear()

	# Enter Text
	driver.find_element(By.NAME, "email_input").send_keys("admin@localhost.dev" )

  {{< /tab >}}
  {{< tab header="CSharp" >}}

  // Navigate to Url
  driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/inputs.html");

  // Clear field to empty it from any previous data
  driver.FindElement(By.Name("email_input")).Clear();
  
  //Enter Text
  driver.FindElement(By.Name("email_input")).SendKeys("admin@localhost.dev");
  
  
}
  {{< /tab >}}
  {{< tab header="Ruby" >}}

    # Navigate to URL
	driver.get 'https://www.selenium.dev/selenium/web/inputs.html'

    # Clear field to empty it from any previous data
	driver.find_element(name: 'email_input').clear
	
	# Enter Text
	driver.find_element(name: 'email_input').send_keys 'admin@localhost.dev'

  {{< /tab >}}
  {{< tab header="JavaScript" >}}

    // Navigate to Url
    await driver.get('https://www.selenium.dev/selenium/web/inputs.html');

	//Clear field to empty it from any previous data
	await driver.findElement(By.name('email_input')).clear();

    // Enter text 
    await driver.findElement(By.name('email_input')).sendKeys('admin@localhost.dev');
  
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  
    // Navigate to Url
    driver.get("https://www.selenium.dev/selenium/web/inputs.html")

	//Clear field to empty it from any previous data
	driver.findElement(By.name("email_input")).clear()
	
    // Enter text 
    driver.findElement(By.name("email_input")).sendKeys("admin@localhost.dev")
  
  {{< /tab >}}
{{< /tabpane >}}

## 清除

[元素清除命令](https://w3c.github.io/webdriver/#dfn-element-clear) 
重置元素的内容.
这要求元素 [可编辑](https://w3c.github.io/webdriver/#dfn-editable),
且 [可重置](https://w3c.github.io/webdriver/#dfn-resettable-elements).
通常, 这意味着元素是具有 `文本` 类型的表单的输入元素或具有 `内容可编辑` 属性的元素.
如果不满足这些条件, 将返回
[无效元素状态](https://w3c.github.io/webdriver/#dfn-invalid-element-state) 错误.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/elements/InteractionTest.java#L38-L40" >}}
{{< /tab >}}
  {{< tab header="Python" >}}


    # Navigate to url
	driver.get("https://www.selenium.dev/selenium/web/inputs.html")

    # Clear field to empty it from any previous data
	driver.find_element(By.NAME, "email_input").clear()

	
  {{< /tab >}}
  {{< tab header="CSharp" >}}

  // Navigate to Url
  driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/inputs.html");

  // Clear field to empty it from any previous data
  driver.FindElement(By.Name("email_input")).Clear();
  
 
  
}
  {{< /tab >}}
  {{< tab header="Ruby" >}}

    # Navigate to URL
	driver.get 'https://www.selenium.dev/selenium/web/inputs.html'

    # Clear field to empty it from any previous data
	driver.find_element(name: 'email_input').clear

  {{< /tab >}}
  {{< tab header="JavaScript" >}}

    // Navigate to Url
    await driver.get('https://www.selenium.dev/selenium/web/inputs.html');

	//Clear field to empty it from any previous data
	await driver.findElement(By.name('email_input')).clear();

   
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  
    // Navigate to Url
    driver.get("https://www.selenium.dev/selenium/web/inputs.html")

	//Clear field to empty it from any previous data
	driver.findElement(By.name("email_input")).clear()
	
  
  {{< /tab >}}
{{< /tabpane >}}

## 提交

在Selenium 4中, 
不再通过单独的端点以及脚本执行的方法来实现. 
因此, 建议不要使用此方法, 
而是单击相应的表单提交按钮. 


