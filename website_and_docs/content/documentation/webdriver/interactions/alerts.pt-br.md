---
title: "Alertas, prompts e confirmações JavaScript"
linkTitle: "Alertas, prompts e confirmações JavaScript"
weight: 2
aliases: [
"/documentation/pt-br/webdriver/js_alerts_prompts_and_confirmations/",
"/pt-br/documentation/webdriver/js_alerts_prompts_and_confirmations/",
"/pt-br/documentation/webdriver/browser/alerts/"
]
---

WebDriver fornece uma API para trabalhar com os três tipos nativos de
mensagens pop-up oferecidas pelo JavaScript. Esses pop-ups são estilizados pelo
navegador e oferecem personalização limitada.

## Alertas

O mais simples deles é referido como um alerta, que mostra um
mensagem personalizada e um único botão que dispensa o alerta, rotulado
na maioria dos navegadores como OK. Ele também pode ser dispensado na maioria dos navegadores
pressionando o botão Fechar, mas isso sempre fará a mesma coisa que
o botão OK. <a onclick="window.alert('Sample alert')"> Veja um exemplo de alerta </a>.

O WebDriver pode obter o texto do pop-up e aceitar ou dispensar esses
alertas.

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

{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="examples/ruby/spec/interactions/alerts_spec.rb#L15-L22" >}}
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

## Confirmação

Uma caixa de confirmação é semelhante a um alerta, exceto que o usuário também pode escolher
cancelar a mensagem. <a onclick="window.confirm('Are you sure?')"> Veja
uma amostra de confirmação </a>.

Este exemplo também mostra uma abordagem diferente para armazenar um alerta:

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
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="examples/ruby/spec/interactions/alerts_spec.rb#L28-L35" >}}
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

## Prompt

Os prompts são semelhantes às caixas de confirmação, exceto que também incluem um texto de
entrada. Semelhante a trabalhar com elementos de formulário, você pode
usar o sendKeys do WebDriver para preencher uma resposta. Isso substituirá
completamente o espaço de texto de exemplo. Pressionar o botão Cancelar não enviará nenhum texto.
<a onclick="window.prompt('What is your tool of choice?', navigator.appName)">
Veja um exemplo de prompt </a>.


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
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="examples/ruby/spec/interactions/alerts_spec.rb#L41-L48" >}}
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
