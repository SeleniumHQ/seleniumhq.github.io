---
title: "JavaScript의 alert, prompt, confirm 대화 상자"  
weight: 6
---

WebDriver에는 JavaScript의 세 가지 대화 상자를 제어하는 API가 있습니다. 
이들 대화 상자의 스타일은 브라우저에 의해 결정되며 제한된 영역만 변경할 수 
있습니다.

## Alert

경고를 위해 사용되는 가장 단순한 형태의 대화 상자입니다. 대화 상자에는 
사용자에게 보여줄 메시지와 버튼 한 개가 있으며, 버튼을 누르면 대화 상자가 
닫힙니다. 이 버튼에는 대개 ‘확인’이라는 텍스트가 적혀 있습니다. 다수 
브라우저에서는 대화 상자의 닫기 버튼을 눌러 대화 상자를 닫을 수도 있습니다. 
이는 확인 버튼을 누르는 것과 같은 동작을 수행합니다. 
<a onclick="window.alert('alert 예시')">alert의 예시 보기</a>.

WebDriver는 대화 상자에 표시된 텍스트를 가져오거나 대화 상자를 닫을 수 
있습니다.

{{< code-tab >}}
  {{< code-panel language="java" >}}
//alert 대화 상자를 띄우기 위해 클릭
driver.findElement(By.linkText("alert의 예시 보기")).click();

//대화 상자가 화면에 나타나길 기다린 뒤 변수에 저장
Alert alert = wait.until(ExpectedConditions.alertIsPresent());

//대화 상자의 텍스트를 변수에 저장
String text = alert.getText();

//확인 버튼 클릭
alert.accept();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# alert 대화 상자를 띄우기 위해 클릭
driver.find_element(By.LINK_TEXT, "alert의 예시 보기").click()

# 대화 상자가 화면에 나타나길 기다린 뒤 변수에 저장
alert = wait.until(expected_conditions.alert_is_present())

# 대화 상자의 텍스트를 변수에 저장
text = alert.text

# 확인 버튼 클릭
alert.accept()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
//alert 대화 상자를 띄우기 위해 클릭
driver.FindElement(By.LinkText("alert의 예시 보기")).Click();

//대화 상자가 화면에 나타나길 기다린 뒤 변수에 저장
IAlert alert = wait.Until(ExpectedConditions.AlertIsPresent());

//대화 상자의 텍스트를 변수에 저장
string text = alert.Text;

//확인 버튼 클릭
alert.Accept();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# alert 대화 상자를 띄우기 위해 클릭
driver.find_element(:link_text, 'alert의 예시 보기').click

# 대화 상자를 변수에 저장
alert = driver.switch_to.alert

# 대화 상자의 텍스트를 변수에 저장
alert_text = alert.text

# 확인 버튼 클릭
alert.accept
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
//alert 대화 상자를 띄우기 위해 클릭
await driver.findElement(By.linkText('alert의 예시 보기')).click();

//대화 상자가 화면에 나타나길 기다림
await driver.wait(until.alertIsPresent());

//대화 상자를 변수에 저장
let alert = await driver.switchTo().alert();

//대화 상자의 텍스트를 변수에 저장
let alertText = await alert.getText();

//확인 버튼 클릭
await alert.accept();

//참고: await문을 사용하기 위해 위의 코드는 async 함수 안에 위치해야 합니다.
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
//alert 대화 상자를 띄우기 위해 클릭
driver.findElement(By.linkText("alert의 예시 보기")).click()

//대화 상자가 화면에 나타나길 기다린 뒤 변수에 저장
val alert = wait.until(ExpectedConditions.alertIsPresent())

//대화 상자의 텍스트를 변수에 저장
val text = alert.getText()

//확인 버튼 클릭
alert.accept()
  {{< / code-panel >}}
{{< / code-tab >}}

## Confirm

confirm 대화 상자는 alert와 비슷합니다. 그러나 confirm 대화 상자에는 취소 
버튼이 있어서 사용자는 대화 상자에 나타난 메시지를 보고 취소를 선택할 수 
있습니다. <a onclick="window.confirm('계속하시겠습니까?')">confirm의 예시 
보기</a>.

다음의 confirm 대화 상자 예시에서는 alert를 저장하는 다른 방식도 함께 
확인할 수 있습니다.

{{< code-tab >}}
  {{< code-panel language="java" >}}
//alert 대화 상자를 띄우기 위해 클릭
driver.findElement(By.linkText("confirm의 예시 보기")).click();

//대화 상자가 화면에 나타나길 기다림
wait.until(ExpectedConditions.alertIsPresent());

//대화 상자를 변수에 저장
Alert alert = driver.switchTo().alert();

//대화 상자의 텍스트를 변수에 저장
String text = alert.getText();

//취소 버튼 클릭
alert.dismiss();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# alert 대화 상자를 띄우기 위해 클릭
driver.find_element(By.LINK_TEXT, "confirm의 예시 보기").click()

# 대화 상자가 화면에 나타나길 기다림
wait.until(expected_conditions.alert_is_present())

# 대화 상자를 변수에 저장
alert = driver.switch_to.alert

# 대화 상자의 텍스트를 변수에 저장
text = alert.text

# 취소 버튼 클릭
alert.dismiss()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
//alert 대화 상자를 띄우기 위해 클릭
driver.FindElement(By.LinkText("confirm의 예시 보기")).Click();

//대화 상자가 화면에 나타나길 기다림
wait.Until(ExpectedConditions.AlertIsPresent());

//대화 상자를 변수에 저장
IAlert alert = driver.SwitchTo().Alert();

//대화 상자의 텍스트를 변수에 저장
string text = alert.Text;

//취소 버튼 클릭
alert.Dismiss();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# alert 대화 상자를 띄우기 위해 클릭
driver.find_element(:link_text, 'confirm의 예시 보기').click

# 대화 상자를 변수에 저장
alert = driver.switch_to.alert

# 대화 상자의 텍스트를 변수에 저장
alert_text = alert.text

# 취소 버튼 클릭
alert.dismiss
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
//alert 대화 상자를 띄우기 위해 클릭
await driver.findElement(By.linkText('confirm의 예시 보기')).click();

//대화 상자가 화면에 나타나길 기다림
await driver.wait(until.alertIsPresent());

//대화 상자를 변수에 저장
let alert = await driver.switchTo().alert();

//대화 상자의 텍스트를 변수에 저장
let alertText = await alert.getText();

//취소 버튼 클릭
await alert.dismiss();

//참고: await문을 사용하기 위해 위의 코드는 async 함수 안에 위치해야 합니다.
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
//alert 대화 상자를 띄우기 위해 클릭
driver.findElement(By.linkText("confirm의 예시 보기")).click()

//대화 상자가 화면에 나타나길 기다림
wait.until(ExpectedConditions.alertIsPresent())

//대화 상자를 변수에 저장
val alert = driver.switchTo().alert()

//대화 상자의 텍스트를 변수에 저장
val text = alert.text

//취소 버튼 클릭
alert.dismiss()
  {{< / code-panel >}}
{{< / code-tab >}}

## Prompt

prompt 대화 상자는 confirm과 비슷하지만, 추가로 텍스트 입력 필드를 가지고 
있습니다. prompt는 폼 요소(form element)를 다루듯, WebDriver로 대화 상자에 
키보드 입력 키(key)를 보낼 수 있습니다. 이 동작은 prompt 대화 상자의 텍스트 
입력 필드 내용을 변경합니다. 대화 상자의 취소 버튼을 누르면 입력 필드의 
텍스트가 전송되지 않습니다. <a onclick="window.prompt(
'어떤 도구를 선택하시겠습니까?',navigator.appName)">prompt의 예시 보기</a>.


{{< code-tab >}}
  {{< code-panel language="java" >}}
//alert 대화 상자를 띄우기 위해 클릭
driver.findElement(By.linkText("prompt의 예시 보기")).click();

//대화 상자가 화면에 나타나길 기다린 뒤 변수에 저장
Alert alert = wait.until(ExpectedConditions.alertIsPresent());

//메시지 입력
alert.sendKeys("Selenium");

//확인 버튼 클릭
alert.accept();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# alert 대화 상자를 띄우기 위해 클릭
driver.find_element(By.LINK_TEXT, "prompt의 예시 보기").click()

# 대화 상자가 화면에 나타나길 기다림
wait.until(expected_conditions.alert_is_present())

# 대화 상자를 변수에 저장
alert = Alert(driver)

# 메시지 입력
alert.send_keys("Selenium")

# 확인 버튼 클릭
alert.accept()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
//alert 대화 상자를 띄우기 위해 클릭
driver.FindElement(By.LinkText("prompt의 예시 보기")).Click();

//대화 상자가 화면에 나타나길 기다린 뒤 변수에 저장
IAlert alert = wait.Until(ExpectedConditions.AlertIsPresent());

//메시지 입력
alert.SendKeys("Selenium");

//확인 버튼 클릭
alert.Accept();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# alert 대화 상자를 띄우기 위해 클릭
driver.find_element(:link_text, 'prompt의 예시 보기').click

# 대화 상자를 변수에 저장
alert = driver.switch_to.alert

# 메시지 입력
alert.send_keys("selenium")

# 확인 버튼 클릭
alert.accept
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
//alert 대화 상자를 띄우기 위해 클릭
await driver.findElement(By.linkText('prompt의 예시 보기')).click();

//대화 상자가 화면에 나타나길 기다림
await driver.wait(until.alertIsPresent());

//대화 상자를 변수에 저장
let alert = await driver.switchTo().alert();

//메시지 입력
await alert.sendKeys("Selenium");

//확인 버튼 클릭
await alert.accept();

//참고: await문을 사용하기 위해 위의 코드는 async 함수 안에 위치해야 합니다.
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
//alert 대화 상자를 띄우기 위해 클릭
driver.findElement(By.linkText("prompt의 예시 보기")).click()

//대화 상자가 화면에 나타나길 기다린 뒤 변수에 저장
val alert = wait.until(ExpectedConditions.alertIsPresent())

//메시지 입력
alert.sendKeys("Selenium")

//확인 버튼 클릭
alert.accept()
  {{< / code-panel >}}
{{< / code-tab >}}
