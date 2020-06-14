---
title: "AUT*에 대한 작업 수행"
weight: 4
---

sendKeys 메소드를 이용하여 element의 텍스트를 설정할 수 있습니다:

{{< code-tab >}}
  {{< code-panel language="java" >}}
String name = "Charles";
driver.findElement(By.name("name")).sendKeys(name);
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
name = "Charles"
driver.find_element(By.NAME, "name").send_keys(name)
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
string name = "Charles";
driver.FindElement(By.Name("name")).SendKeys(name);
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
name = "Charles"
driver.find_element(name: "name").send_keys(name)
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const name = "Charles";
await driver.findElement(By.name('name')).sendKeys(name);
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val name = "Charles"
driver.findElement(By.name("name")).sendKeys(name)
  {{< / code-panel >}}
{{< / code-tab >}}

몇몇 웹 어플리케이션들은 자바스크립트 라이브러리를 이용하여 드래그-앤-드롭 기능을 구현합니다. 아래는 하나의 element를 또다른 element로 드래그하는 가장 기본적인 예시입니다:

{{< code-tab >}}
  {{< code-panel language="java" >}}
WebElement source = driver.findElement(By.id("source"));
WebElement target = driver.findElement(By.id("target"));
new Actions(driver).dragAndDrop(source, target).build().perform();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
source = driver.find_element(By.ID, "source")
target = driver.find_element(By.ID, "target")
ActionChains(driver).drag_and_drop(source, target).perform()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
IWebElement source = driver.FindElement(By.Id("source"));
IWebElement target = driver.FindElement(By.Id("target"));
new Actions(driver).DragAndDrop(source, target).Build().Perform();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
source = driver.find_element(id: "source")
target = driver.find_element(id: "target")
driver.action.drag_and_drop(source, target).perform
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const actions = driver.actions({bridge: true});
const source = driver.findElement(By.id('source'));
const target = driver.findElement(By.id('target'));
await actions.dragAndDrop(source, target).perform();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val source = driver.findElement(By.id("source"))
val target = driver.findElement(By.id("target"))
Actions(driver).dragAndDrop(source, target).build().perform()
  {{< / code-panel >}}
{{< / code-tab >}}

### Element 클릭하기

click 메소드를 이용하여 element를 클릭할 수 있습니다:

{{< code-tab >}}
  {{< code-panel language="java" >}}
driver.findElement(By.cssSelector("input[type='submit']")).click();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
driver.find_element(By.CSS_SELECTOR, "input[type='submit']").click()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
driver.FindElement(By.CssSelector("input[type='submit']")).Click();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
driver.find_element(css: "input[type='submit']").click
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
await driver.findElement(By.css("input[type='submit']")).click();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
driver.findElement(By.cssSelector("input[type='submit']")).click()
  {{< / code-panel >}}
{{< / code-tab >}}

***AUT**: Application under test