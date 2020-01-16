---
title: "AUT* を操作する"
weight: 4
---


次のようにsendKeysを使うことで、要素にテキストをセットすることができます。

{{< code-tab >}}
  {{< code-panel language="java" >}}
String name = "Charles";
driver.findElement(By.name("name")).sendKeys(name);
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
name = "Charles"
driver.find_element_by_name("name").send_keys(name)
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

JavaScriptライブラリを使ってドラックアンドドロップ機能を追加したWebアプリケーションがあります。
次の例はある要素を別の要素へドラッグする例です。

{{< code-tab >}}
  {{< code-panel language="java" >}}
WebElement source = driver.findElement(By.id("source"));
WebElement target = driver.findElement(By.id("target"));
new Actions(driver).dragAndDrop(source, target).build().perform();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
source = driver.find_element_by_id("source")
target = driver.find_element_by_id("target")
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

### 要素をクリックする

clickメソッドを使うと要素のクリックができます。

{{< code-tab >}}
  {{< code-panel language="java" >}}
driver.findElement(By.cssSelector("input[type='submit']")).click();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
driver.find_element_by_css_selector("input[type='submit']").click()
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

***AUT**: Application under test（テスト対象アプリケーション）
