---
title: "Alerts, prompts et confirmations Javascript"
weight: 6
---

{{% notice info %}}
<i class="fas fa-language"></i> Page being translated from 
English to French. Do you speak French? Help us to translate
it by sending us pull requests!
{{% /notice %}}

WebDriver provides an API for working with the three types of native
popup message offered by JavaScript. These popups are styled by the
browser and offer limited customisation.

## Alerts

The simplest of these is referred to as an alert, which shows a
custom message, and a single button which dismisses the alert, labelled
in most browsers as OK. It can also be dismissed in most browsers by
pressing the close button, but this will always do the same thing as
the OK button. <a onclick="window.alert('Sample alert')">See an example alert</a>.

WebDriver can get the text from the popup and accept or dismiss these
alerts.

{{< code-tab >}}
  {{< code-panel language="java" >}}
//Click the link to activate the alert
driver.findElement(By.linkText("See an example alert")).click();

//Wait for the alert to be displayed and store it in a variable
Alert alert = wait.until(ExpectedConditions.alertIsPresent());

//Store the alert text in a variable
String text = alert.getText();

//Press the OK button
alert.accept();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Click the link to activate the alert
driver.find_element_by_link_text("See an example alert").click()

# Wait for the alert to be displayed and store it in a variable
alert = wait.until(expected_conditions.alert_is_present())

# Store the alert text in a variable
text = alert.text

# Press the OK button
alert.accept()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
//Click the link to activate the alert
driver.FindElement(By.LinkText("See an example alert")).Click();

//Wait for the alert to be displayed and store it in a variable
IAlert alert = wait.Until(ExpectedConditions.AlertIsPresent());

//Store the alert text in a variable
string text = alert.Text;

//Press the OK button
alert.Accept();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Click the link to activate the alert
driver.find_element(:link_text, 'See an example alert').click

# Store the alert reference in a variable
alert = driver.switch_to.alert

# Store the alert text in a variable
alert_text = alert.text

# Press on OK button
alert.accept
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
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
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
//Click the link to activate the alert
driver.findElement(By.linkText("See an example alert")).click()

//Wait for the alert to be displayed and store it in a variable
val alert = wait.until(ExpectedConditions.alertIsPresent())

//Store the alert text in a variable
val text = alert.getText()

//Press the OK button
alert.accept()
  {{< / code-panel >}}
{{< / code-tab >}}

## Confirm

A confirm box is similar to an alert, except the user can also choose
to cancel the message. <a onclick="window.confirm('Are you sure?')">See
a sample confirm</a>.

This example also shows a different approach to storing an alert:

{{< code-tab >}}
  {{< code-panel language="java" >}}
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
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Click the link to activate the alert
driver.find_element_by_link_text("See a sample confirm").click()

# Wait for the alert to be displayed
wait.until(expected_conditions.alert_is_present())

# Store the alert in a variable for reuse
alert = driver.switch_to.alert

# Store the alert text in a variable
text = alert.text

# Press the Cancel button
alert.dismiss()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
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
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Click the link to activate the alert
driver.find_element(:link_text, 'See a sample confirm').click

# Store the alert reference in a variable
alert = driver.switch_to.alert

# Store the alert text in a variable
alert_text = alert.text

# Press on Cancel button
alert.dismiss
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
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
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
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
  {{< / code-panel >}}
{{< / code-tab >}}

## Prompt

Prompts are similar to confirm boxes, except they also include a text
input. Similar to working with form elements, you can use WebDriver's
send keys to fill in a response. This will completely replace the placeholder
text. Pressing the cancel button will not submit any text.
<a onclick="window.prompt('What is your tool of choice?',navigator.appName)">
See a sample prompt</a>.


{{< code-tab >}}
  {{< code-panel language="java" >}}
//Click the link to activate the alert
driver.findElement(By.linkText("See a sample prompt")).click();

//Wait for the alert to be displayed and store it in a variable
Alert alert = wait.until(ExpectedConditions.alertIsPresent());

//Type your message
alert.sendKeys("Selenium");

//Press the OK button
alert.accept();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Click the link to activate the alert
driver.find_element_by_link_text("See a sample prompt").click()

# Wait for the alert to be displayed
wait.until(expected_conditions.alert_is_present())

# Store the alert in a variable for reuse
alert = Alert(driver)

# Type your message
alert.send_keys("Selenium")

# Press the OK button
alert.accept()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
//Click the link to activate the alert
driver.FindElement(By.LinkText("See a sample prompt")).Click();

//Wait for the alert to be displayed and store it in a variable
IAlert alert = wait.Until(ExpectedConditions.AlertIsPresent());

//Type your message
alert.SendKeys("Selenium");

//Press the OK button
alert.Accept();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Click the link to activate the alert
driver.find_element(:link_text, 'See a sample prompt').click

# Store the alert reference in a variable
alert = driver.switch_to.alert

# Type a message
alert.send_keys("selenium")

# Press on Ok button
alert.accept
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
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
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
//Click the link to activate the alert
driver.findElement(By.linkText("See a sample prompt")).click()

//Wait for the alert to be displayed and store it in a variable
val alert = wait.until(ExpectedConditions.alertIsPresent())

//Type your message
alert.sendKeys("Selenium")

//Press the OK button
alert.accept()
  {{< / code-panel >}}
{{< / code-tab >}}
