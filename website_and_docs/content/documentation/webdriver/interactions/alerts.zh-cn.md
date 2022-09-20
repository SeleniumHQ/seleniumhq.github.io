---
title: "JavaScript 警告框,提示框和确认框"
linkTitle: "警告框"
weight: 2
aliases: [
"/documentation/zh-cn/webdriver/js_alerts_prompts_and_confirmations/",
"/zh-cn/documentation/webdriver/js_alerts_prompts_and_confirmations/",
"/zh-cn/documentation/webdriver/browser/alerts/",
]
---

WebDriver提供了一个API, 用于处理JavaScript提供的三种类型的原生弹窗消息. 这些弹窗由浏览器提供限定的样式.

## Alerts 警告框

其中最基本的称为警告框, 它显示一条自定义消息, 以及一个用于关闭该警告的按钮, 在大多数浏览器中标记为"确定"(OK). 在大多数浏览器中, 也可以通过按"关闭"(close)按钮将其关闭, 但这始终与“确定”按钮具有相同的作用. <a onclick="window.alert('Sample alert')">查看样例警告框</a>.

WebDriver可以从弹窗获取文本并接受或关闭这些警告.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
//Click the link to activate the alert
driver.findElement(By.linkText("See an example alert")).click();

//Wait for the alert to be displayed and store it in a variable
Alert alert = wait.until(ExpectedConditions.alertIsPresent());

//Store the alert text in a variable
String text = alert.getText();

//Press the OK button
alert.accept();
  {{< /tab >}}
  {{< tab header="Python" >}}
# Click the link to activate the alert
driver.find_element(By.LINK_TEXT, "See an example alert").click()

# Wait for the alert to be displayed and store it in a variable
alert = wait.until(expected_conditions.alert_is_present())

# Store the alert text in a variable
text = alert.text

# Press the OK button
alert.accept()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
//Click the link to activate the alert
driver.FindElement(By.LinkText("See an example alert")).Click();

//Wait for the alert to be displayed and store it in a variable
IAlert alert = wait.Until(ExpectedConditions.AlertIsPresent());

//Store the alert text in a variable
string text = alert.Text;

//Press the OK button
alert.Accept();
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Click the link to activate the alert
driver.find_element(:link_text, 'See an example alert').click

# Store the alert reference in a variable
alert = driver.switch_to.alert

# Store the alert text in a variable
alert_text = alert.text

# Press on OK button
alert.accept
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
//Click the link to activate the alert
await driver.findElement(By.linkText('See an example alert')).click();

// Wait for the alert to be displayed
await driver.wait(until.alertIsPresent());

// Store the alert in a variable
let alert = await driver.switchTo().alert();

//Store the alert text in a variable
let alertText = await alert.getText();

//Press the OK button
await alert.accept();

// Note: To use await, the above code should be inside an async function
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
//Click the link to activate the alert
driver.findElement(By.linkText("See an example alert")).click()

//Wait for the alert to be displayed and store it in a variable
val alert = wait.until(ExpectedConditions.alertIsPresent())

//Store the alert text in a variable
val text = alert.getText()

//Press the OK button
alert.accept()
  {{< /tab >}}
{{< /tabpane >}}

## Confirm 确认框

确认框类似于警告框, 不同之处在于用户还可以选择取消消息. <a onclick="window.confirm('Are you sure?')">查看样例确认框</a>.

此示例还呈现了警告的另一种实现:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
//Click the link to activate the alert
driver.findElement(By.linkText("See a sample confirm")).click();

//Wait for the alert to be displayed
wait.until(ExpectedConditions.alertIsPresent());

//Store the alert in a variable
Alert alert = driver.switchTo().alert();

//Store the alert in a variable for reuse
String text = alert.getText();

//Press the Cancel button
alert.dismiss();
  {{< /tab >}}
  {{< tab header="Python" >}}
# Click the link to activate the alert
driver.find_element(By.LINK_TEXT, "See a sample confirm").click()

# Wait for the alert to be displayed
wait.until(expected_conditions.alert_is_present())

# Store the alert in a variable for reuse
alert = driver.switch_to.alert

# Store the alert text in a variable
text = alert.text

# Press the Cancel button
alert.dismiss()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
//Click the link to activate the alert
driver.FindElement(By.LinkText("See a sample confirm")).Click();

//Wait for the alert to be displayed
wait.Until(ExpectedConditions.AlertIsPresent());

//Store the alert in a variable
IAlert alert = driver.SwitchTo().Alert();

//Store the alert in a variable for reuse
string text = alert.Text;

//Press the Cancel button
alert.Dismiss();
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Click the link to activate the alert
driver.find_element(:link_text, 'See a sample confirm').click

# Store the alert reference in a variable
alert = driver.switch_to.alert

# Store the alert text in a variable
alert_text = alert.text

# Press on Cancel button
alert.dismiss
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
//Click the link to activate the alert
await driver.findElement(By.linkText('See a sample confirm')).click();

// Wait for the alert to be displayed
await driver.wait(until.alertIsPresent());

// Store the alert in a variable
let alert = await driver.switchTo().alert();

//Store the alert text in a variable
let alertText = await alert.getText();

//Press the Cancel button
await alert.dismiss();

// Note: To use await, the above code should be inside an async function
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
//Click the link to activate the alert
driver.findElement(By.linkText("See a sample confirm")).click()

//Wait for the alert to be displayed
wait.until(ExpectedConditions.alertIsPresent())

//Store the alert in a variable
val alert = driver.switchTo().alert()

//Store the alert in a variable for reuse
val text = alert.text

//Press the Cancel button
alert.dismiss()
  {{< /tab >}}
{{< /tabpane >}}

## Prompt 提示框

提示框与确认框相似, 不同之处在于它们还包括文本输入. 与处理表单元素类似, 您可以使用WebDriver的sendKeys来填写响应. 这将完全替换占位符文本. 按下取消按钮将不会提交任何文本.
<a onclick="window.prompt('What is your tool of choice?',navigator.appName)">
查看样例提示框</a>.


{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
//Click the link to activate the alert
driver.findElement(By.linkText("See a sample prompt")).click();

//Wait for the alert to be displayed and store it in a variable
Alert alert = wait.until(ExpectedConditions.alertIsPresent());

//Type your message
alert.sendKeys("Selenium");

//Press the OK button
alert.accept();
  {{< /tab >}}
  {{< tab header="Python" >}}
# Click the link to activate the alert
driver.find_element(By.LINK_TEXT, "See a sample prompt").click()

# Wait for the alert to be displayed
wait.until(expected_conditions.alert_is_present())

# Store the alert in a variable for reuse
alert = Alert(driver)

# Type your message
alert.send_keys("Selenium")

# Press the OK button
alert.accept()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
//Click the link to activate the alert
driver.FindElement(By.LinkText("See a sample prompt")).Click();

//Wait for the alert to be displayed and store it in a variable
IAlert alert = wait.Until(ExpectedConditions.AlertIsPresent());

//Type your message
alert.SendKeys("Selenium");

//Press the OK button
alert.Accept();
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Click the link to activate the alert
driver.find_element(:link_text, 'See a sample prompt').click

# Store the alert reference in a variable
alert = driver.switch_to.alert

# Type a message
alert.send_keys("selenium")

# Press on Ok button
alert.accept
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
//Click the link to activate the alert
await driver.findElement(By.linkText('See a sample prompt')).click();

// Wait for the alert to be displayed
await driver.wait(until.alertIsPresent());

// Store the alert in a variable
let alert = await driver.switchTo().alert();

//Type your message
await alert.sendKeys("Selenium");

//Press the OK button
await alert.accept();

//Note: To use await, the above code should be inside an async function
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
//Click the link to activate the alert
driver.findElement(By.linkText("See a sample prompt")).click()

//Wait for the alert to be displayed and store it in a variable
val alert = wait.until(ExpectedConditions.alertIsPresent())

//Type your message
alert.sendKeys("Selenium")

//Press the OK button
alert.accept()
  {{< /tab >}}
{{< /tabpane >}}
