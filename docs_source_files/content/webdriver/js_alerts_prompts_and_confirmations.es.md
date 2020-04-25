---
title: "Alertas, avisos y confirmaciones de JavaScript"
weight: 6
---

WebDriver proporciona una API para trabajar con los tres tipos
nativos de mensajes emergentes ofrecidos por JavaScript. 
Estas ventanas emergentes están diseñadas por el
navegador y ofrecen personalización limitada.

## Alertas

El más simple de estos se conoce como alerta, 
que muestra unmensaje personalizado y un solo botón
que descarta la alerta, etiquetado
en la mayoría de los navegadores como OK. 
También se puede descartar en la mayoría de los navegadores
presionando el botón de cerrar, pero esto siempre hará lo mismo que
el presionar botón OK.
<a onclick="window.alert('Alerta de ejemplo')">Esto es una alerta de ejemplo</a>.

WebDriver puede obtener el texto de la ventana emergente
y aceptar o descartar estas alertas.

{{< code-tab >}}
  {{< code-panel language="java" >}}
// Haz clic en el enlace para activar la alerta 
driver.findElement(By.linkText("See an example alert")).click();

// Espera a que se muestre la alerta y almacenala en una variable
Alert alert = wait.until(ExpectedConditions.alertIsPresent());

// Almacena el texto de la alerta en una variable
String text = alert.getText();

// Presiona el botón OK
alert.accept();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Haz clic en el enlace para activar la alerta
driver.find_element(By.LINK_TEXT, "See an example alert").click()

# Espera a que se muestre la alerta y almacenala en una variable
alert = wait.until(expected_conditions.alert_is_present())

# Almacena el texto de la alerta en una variable
text = alert.text

# Presiona el botón OK
alert.accept()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Haz clic en el enlace para activar la alerta
driver.FindElement(By.LinkText("See an example alert")).Click();

// Espera a que se muestre la alerta y almacenala en una variable
IAlert alert = wait.Until(ExpectedConditions.AlertIsPresent());

// Almacena el texto de la alerta en una variable
string text = alert.Text;

// Presiona el botón OK
alert.Accept();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
#  Haz clic en el enlace para activar la aler
driver.find_element(:link_text, 'See an example alert').click

# Almacena la referencia de la alertta en una variable
alert = driver.switch_to.alert

# Almacena el texto de la alerta en una variable
alert_text = alert.text

# Presiona el botón OK
alert.accept
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Haz clic en el enlace para activar la alerta
await driver.findElement(By.linkText('See an example alert')).click();

// Espera a que se muestre la alerta
await driver.wait(until.alertIsPresent());

// Almacena la alerta en una variable
let alert = await driver.switchTo().alert();

// Almacena el texto de la alerta en una variable
let alertText = await alert.getText();

// Presiona el botón OK
await alert.accept();

// Nota: Para usar await, el código mostrado arriba debe estar en una función async
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Haz clic en el enlace para activar la alerta
driver.findElement(By.linkText("See an example alert")).click()

// Espera a que se muestre la alerta y almacenala en una variable
val alert = wait.until(ExpectedConditions.alertIsPresent())

// Almacena el texto de la alerta en una variable
val text = alert.getText()

// Presiona el botón OK
alert.accept()
  {{< / code-panel >}}
{{< / code-tab >}}

## Confirm

Un cuadro de confirmación es similar a una alerta, 
excepto que el usuario también puede elegir
cancelar el mensaje. <a onclick="window.confirm('Estas seguro?')">Esto es un confirm de ejemplo</a>.

Este ejemplo también muestra un enfoque diferente para 
almacenar una alerta:

{{< code-tab >}}
  {{< code-panel language="java" >}}
// Haz clic en el enlace para activar la alerta
driver.findElement(By.linkText("See a sample confirm")).click();

// Espera a que se muestre la alerta
wait.until(ExpectedConditions.alertIsPresent());

// Almacena la alerta en una variable
Alert alert = driver.switchTo().alert();

// Almacena la alerta en una variable para poder reusarla
String text = alert.getText();

// Presiona el botón cancelar
alert.dismiss();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#  Haz clic en el enlace para activar la alerta
driver.find_element(By.LINK_TEXT, "See a sample confirm").click()

# Espera a que se muestre la alerta
wait.until(expected_conditions.alert_is_present())

# Almacena la alerta en una variable para poder reusarla
alert = driver.switch_to.alert

# Almacena el texto de la alerta en una variable
text = alert.text

# Presiona el botón cancelar
alert.dismiss()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Haz clic en el enlace para activar la alerta
driver.FindElement(By.LinkText("See a sample confirm")).Click();

//Espera a que se muestre la alerta
wait.Until(ExpectedConditions.AlertIsPresent());

// Almacena la alerta en una variable
IAlert alert = driver.SwitchTo().Alert();

// Almacena la alerta en una variable para poder reusarla
string text = alert.Text;

// Presiona el botón cancelar
alert.Dismiss();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
#  Haz clic en el enlace para activar la alerta
driver.find_element(:link_text, 'See a sample confirm').click

# Almacena la referencia de la alertta en una variable
alert = driver.switch_to.alert

# Almacena el texto de la alerta en una variable
alert_text = alert.text

# Presiona el botón cancelar
alert.dismiss
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Haz clic en el enlace para activar la alerta
await driver.findElement(By.linkText('See a sample confirm')).click();

// Espera a que se muestre la alerta
await driver.wait(until.alertIsPresent());

// Almacena la alerta en una variable
let alert = await driver.switchTo().alert();

// Almacena el texto de la alerta en una variable
let alertText = await alert.getText();

// Presiona el botón cancelar
await alert.dismiss();

// Nota: Para usar await, el código mostrado arriba debe estar en una función async
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Haz clic en el enlace para activar la alerta
driver.findElement(By.linkText("See a sample confirm")).click()

//Espera a que se muestre la alerta
wait.until(ExpectedConditions.alertIsPresent())

// Almacena la alerta en una variable
val alert = driver.switchTo().alert()

// Almacena la alerta en una variable para poder reusarla
val text = alert.text

//Presiona el botón cancelar
alert.dismiss()
  {{< / code-panel >}}
{{< / code-tab >}}

## Prompt

Los prompts son similares a los cuadros de confirmación, 
excepto que también incluyen una entrada de texto. 
Similar a trabajar con elementos de los formularios, puedes usar
el _sendKeys_ de WebDriver para completar una respuesta. 
Esto reemplazará completamente el texto por defecto. 
Al presionar el botón cancelar
esto hará que no se envie ningún texto.
<a onclick="window.prompt('Cual es tu herramienta preferida?',navigator.appName)">
Esto es un prompt de ejemplo</a>.


{{< code-tab >}}
  {{< code-panel language="java" >}}
// Haz clic en el enlace para activar la alerta
driver.findElement(By.linkText("See a sample prompt")).click();

// Espera a que se muestre la alerta y almacenala en una variable
Alert alert = wait.until(ExpectedConditions.alertIsPresent());

// Inserta tu mensaje
alert.sendKeys("Selenium");

// Presiona el botón OK
alert.accept();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#  Haz clic en el enlace para activar la alerta
driver.find_element(By.LINK_TEXT, "See a sample prompt").click()

# Espera a que se muestre la alerta
wait.until(expected_conditions.alert_is_present())

# Almacena la alerta en una variable para poder reusarla
alert = Alert(driver)

# Inserta tu mensaje
alert.send_keys("Selenium")

# Presiona el botón OK
alert.accept()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Haz clic en el enlace para activar la alerta
driver.FindElement(By.LinkText("See a sample prompt")).Click();

// Espera a que se muestre la alerta y almacenala en una variable
IAlert alert = wait.Until(ExpectedConditions.AlertIsPresent());

// Inserta tu mensaje
alert.SendKeys("Selenium");

// Presiona el botón OK
alert.Accept();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
#  Haz clic en el enlace para activar la alerta
driver.find_element(:link_text, 'See a sample prompt').click

# Almacena la referencia de la alerta en una variable
alert = driver.switch_to.alert

# Inserta tu mensaje
alert.send_keys("selenium")

# Presiona el botón OK
alert.accept
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Haz clic en el enlace para activar la alerta
await driver.findElement(By.linkText('See a sample prompt')).click();

// Espera a que se muestre la alerta
await driver.wait(until.alertIsPresent());

// Almacena la alerta en una variable
let alert = await driver.switchTo().alert();

// Inserta tu mensaje
await alert.sendKeys("Selenium");

// Presiona el botón OK
await alert.accept();

// Nota: Para usar await, el código mostrado arriba debe estar en una función async
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Haz clic en el enlace para activar la alerta
driver.findElement(By.linkText("See a sample prompt")).click()

// Espera a que se muestre la alerta y almacenala en una variable
val alert = wait.until(ExpectedConditions.alertIsPresent())

// Inserta tu mensaje
alert.sendKeys("Selenium")

// Presiona el botón OK
alert.accept()
  {{< / code-panel >}}
{{< / code-tab >}}
