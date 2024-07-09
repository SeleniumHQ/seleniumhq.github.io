---
title: "JavaScript アラート、プロンプトおよび確認"
linkTitle: "JavaScript アラート、プロンプトおよび確認"
weight: 2
aliases: [
"/documentation/ja/webdriver/js_alerts_prompts_and_confirmations/",
"/ja/documentation/webdriver/js_alerts_prompts_and_confirmations/",
"/ja/documentation/webdriver/browser/alerts",
]
---

WebDriverは、JavaScriptが提供する3種類のネイティブポップアップメッセージを操作するためのAPIを提供します。
これらのポップアップはブラウザーによってスタイルが設定され、カスタマイズが制限されています。

## アラート

これらの最も単純なものはアラートと呼ばれ、カスタムメッセージと、ほとんどのブラウザーでOKのラベルが付いたアラートを非表示にする単一のボタンを表示します。
ほとんどのブラウザーでは閉じるボタンを押すことで閉じることもできますが、これは常にOKボタンと同じことを行います。
<a onclick="window.alert('Sample alert')">アラートの例を参照してください</a>。

WebDriverはポップアップからテキストを取得し、これらのアラートを受け入れるか、または閉じることができます。

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

{{< tab header="Python" text=true >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_alerts.py#L12-L18" >}}
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
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="examples/javascript/test/interactions/alert.spec.js#L19-L21" >}}
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

## 確認

確認ダイアログボックスはアラートに似ていますが、ユーザーがメッセージをキャンセルすることも選択できる点が異なります。
<a onclick="window.confirm('Are you sure?')">サンプルを確認してください</a>。

この例は、アラートを保存する別の方法も示しています。

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

{{< tab header="Python" text=true >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_alerts.py#L26-L32" >}}
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
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="examples/javascript/test/interactions/alert.spec.js#L30-L32" >}}
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

## プロンプト

プロンプトは確認ボックスに似ていますが、テキスト入力も含まれている点が異なります。
フォーム要素の操作と同様に、WebDriverの送信キーを使用して応答を入力できます。
これにより、プレースホルダーテキストが完全に置き換えられます。
キャンセルボタンを押してもテキストは送信されません。
<a onclick="window.prompt('What is your tool of choice?',navigator.appName)">サンプルプロンプトを参照してください</a>。

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

{{< tab header="Python" text=true >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_alerts.py#L40-L47" >}}
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
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="examples/javascript/test/interactions/alert.spec.js#L42-L45" >}}
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
