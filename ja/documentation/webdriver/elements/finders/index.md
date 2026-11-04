# Web要素の検索

> 提供されたロケーターの値に基づいて要素を検索します。

URL: https://www.selenium.dev/ja/documentation/webdriver/elements/finders/

---

Seleniumを使用する最も基本的な側面の1つは、操作する要素の参照を取得することです。 
Seleniumは、要素を一意に識別するための多数の組み込み[ロケーター戦略](https://www.selenium.dev/ja/documentation/webdriver/elements/locators/index.md)を提供します。 
非常に高度なシナリオでロケーターを使用する方法はたくさんあります。 
このドキュメントの目的のために、[Seleniumロケーターテストページ](https://www.selenium.dev/selenium/web/locators_tests/locators.html)を使用します。

## 最初に一致する要素

多くのロケーターは、ページ上の複数の要素と一致します。 
単数の find elementメソッドは、指定されたコンテキスト内で最初に見つかった要素への参照を返します。

### DOM全体の評価

ドライバーインスタンスで要素の検索メソッドが呼び出されると、提供されたロケーターと一致するDOMの最初の要素への参照が返されます。 
この値は保存して、将来の要素アクションに使用できます。 
Seleniumロケーターテストページには、クラス名が `information` の要素が2つあるため、このメソッドは最初のテキスト入力を返します。

**Java**

```java
driver.get(LOCATORS_PAGE);
WebElement firstInput = driver.findElement(By.className("information"));
```

**Python**

```python
driver.get(LOCATORS_PAGE)
first_input = driver.find_element(By.CLASS_NAME, "information")
```

**CSharp**

```csharp
driver.Url = LocatorsPage;
IWebElement firstInput = driver.FindElement(By.ClassName("information"));
```

**Ruby**

```ruby
driver.navigate.to locators_page
first_input = driver.find_element(class: 'information')
```

**JavaScript**

```javascript
await driver.get(LOCATORS_PAGE);
const firstInput = await driver.findElement(By.className('information'));
```

**Kotlin**

```kotlin
driver.get(locatorsPage)
val firstInput = driver.findElement(By.className("information"))
```

### DOMのサブセットの評価

DOM全体で一意のロケーターを見つけるのではなく、検索を別の検索された要素のスコープに絞り込むと便利なことがよくあります。

1つの解決策は、目的の要素の祖先を見つけて、そのオブジェクトでfind要素を呼び出すことです。

**Java**

```java
driver.get(LOCATORS_PAGE);
WebElement form = driver.findElement(By.tagName("form"));
WebElement input = form.findElement(By.className("information"));
```

**Python**

```python
driver.get(LOCATORS_PAGE)
form = driver.find_element(By.TAG_NAME, "form")
input_element = form.find_element(By.CLASS_NAME, "information")
```

**CSharp**

```csharp
driver.Url = LocatorsPage;
IWebElement form = driver.FindElement(By.TagName("form"));
IWebElement input = form.FindElement(By.ClassName("information"));
```

**Ruby**

```ruby
driver.navigate.to locators_page
form = driver.find_element(tag_name: 'form')
input_element = form.find_element(class: 'information')
```

**JavaScript**

```javascript
await driver.get(LOCATORS_PAGE);
const form = await driver.findElement(By.tagName('form'));
const input = await form.findElement(By.className('information'));
```

**Kotlin**

```kotlin
driver.get(locatorsPage)
val form = driver.findElement(By.tagName("form"))
val input = form.findElement(By.className("information"))
```

> **Java and C#**<br>
> `WebDriver` 、 `WebElement` 、および `ShadowRoot` クラスはすべて、 _ロールベースのインターフェイス_ と見なされる `SearchContext` インターフェイスを実装します。 
> ロールベースのインターフェイスを使用すると、特定のドライバーの実装が特定の機能をサポートしているかどうかを判断できます。
> これらのインターフェースは明確に定義されており、責任の役割を1つだけ持つように努めています。

### Evaluating the Shadow DOM

The Shadow DOM is an encapsulated DOM tree hidden inside an element. 
With the release of v96 in Chromium Browsers, Selenium can now allow you to access this tree 
with easy-to-use shadow root methods. NOTE: These methods require Selenium 4.0 or greater.

**Java**

```java
WebElement shadowHost = driver.findElement(By.cssSelector("custom-checkbox-element"));
SearchContext shadowRoot = shadowHost.getShadowRoot();
WebElement shadowContent = shadowRoot.findElement(By.cssSelector("input[type=checkbox]"));
```

**Python**

```python
shadow_host = driver.find_element(By.TAG_NAME, 'custom-checkbox-element')
shadow_root = shadow_host.shadow_root
assert shadow_root
shadow_content = shadow_root.find_element(By.CSS_SELECTOR, 'input[type=checkbox]')
```

**CSharp**

```csharp
IWebElement shadowHost = driver.FindElement(By.CssSelector("custom-checkbox-element"));
ISearchContext shadowRoot = shadowHost.GetShadowRoot();
IWebElement shadowContent = shadowRoot.FindElement(By.CssSelector("input[type=checkbox]"));
```

**Ruby**

```ruby
shadow_host = driver.find_element(css: 'custom-checkbox-element')
shadow_root = shadow_host.shadow_root
shadow_content = shadow_root.find_element(css: 'input[type=checkbox]')
```

**JavaScript**

```javascript
const shadowHost = await driver.findElement(By.css('custom-checkbox-element'));
const shadowRoot = await shadowHost.getShadowRoot();
const shadowContent = await shadowRoot.findElement(By.css('input[type=checkbox]'));
```

**Kotlin**

```kotlin
val shadowHost = driver.findElement(By.cssSelector("custom-checkbox-element"))
val shadowRoot = shadowHost.shadowRoot
val shadowContent = shadowRoot.findElement(By.cssSelector("input[type=checkbox]"))
```

### 最適化されたロケーター

ネストされたルックアップは、ブラウザに2つの別々のコマンドを発行する必要があるため、最も効果的なロケーション戦略ではない可能性があります。

パフォーマンスをわずかに向上させるために、CSSまたはXPathのいずれかを使用して、単一のコマンドでこの要素を見つけることができます。 
[推奨されるテストプラクティス](https://www.selenium.dev/ja/documentation/test_practices/encouraged/index.md)の章で、[ロケーター戦略](https://www.selenium.dev/ja/documentation/test_practices/encouraged/locators/index.md)の提案を参照してください。

この例では、CSSセレクターを使用します。

**Java**

```java
driver.get(LOCATORS_PAGE);
WebElement input = driver.findElement(By.cssSelector("form .information"));
```

**Python**

```python
driver.get(LOCATORS_PAGE)
input_element = driver.find_element(By.CSS_SELECTOR, "form .information")
```

**CSharp**

```csharp
driver.Url = LocatorsPage;
IWebElement input = driver.FindElement(By.CssSelector("form .information"));
```

**Ruby**

```ruby
driver.navigate.to locators_page
input_element = driver.find_element(css: 'form .information')
```

**JavaScript**

```javascript
await driver.get(LOCATORS_PAGE);
const input = await driver.findElement(By.css('form .information'));
```

**Kotlin**

```kotlin
driver.get(locatorsPage)
val input = driver.findElement(By.cssSelector("form .information"))
```

## 一致するすべての要素

最初の要素だけでなく、ロケーターに一致するすべての要素への参照を取得する必要があるユースケースがいくつかあります。 
複数の要素の検索メソッドは、要素参照のコレクションを返します。 
一致するものがない場合は、空のリストが返されます。 
この場合、すべてのinput要素への参照がコレクションに返されます。

**Java**

```java
driver.get(LOCATORS_PAGE);
List<WebElement> inputs = driver.findElements(By.tagName("input"));
```

**Python**

```python
driver.get(LOCATORS_PAGE)
inputs = driver.find_elements(By.TAG_NAME, "input")
```

**CSharp**

```csharp
driver.Url = LocatorsPage;
var inputs = driver.FindElements(By.TagName("input"));
```

**Ruby**

```ruby
driver.navigate.to locators_page
inputs = driver.find_elements(tag_name: 'input')
```

**JavaScript**

```javascript
await driver.get(LOCATORS_PAGE);
const inputs = await driver.findElements(By.tagName('input'));
```

**Kotlin**

```kotlin
driver.get(locatorsPage)
val inputs = driver.findElements(By.tagName("input"))
```

### 要素の取得
多くの場合、要素のコレクションを取得しますが、特定の要素を操作したいので、コレクションを繰り返し処理して、
必要な要素を特定する必要があります。

**Java**

```java
List<WebElement> elements = driver.findElements(By.tagName("p"));
for (WebElement element : elements) {
  System.out.println("Paragraph text:" + element.getText());
}
```

**Python**

```python
elements = driver.find_elements(By.TAG_NAME, 'p')
for element in elements:
    print(f"Paragraph text:{element.text}")
```

**CSharp**

```csharp
var elements = driver.FindElements(By.TagName("p"));
foreach (var element in elements)
{
    System.Console.WriteLine("Paragraph text:" + element.Text);
}
```

**Ruby**

```ruby
driver.navigate.to locators_page
elements = driver.find_elements(tag_name: 'p')
elements.each { |e| puts "Paragraph text:#{e.text}" }
```

**JavaScript**

```javascript
const elements = await driver.findElements(By.tagName('p'));
for (const element of elements) {
  console.log('Paragraph text:' + await element.getText());
}
```

**Kotlin**

```kotlin
val elements = driver.findElements(By.tagName("p"))
for (element in elements) {
    println("Paragraph text:" + element.text)
}
```

## 要素から要素を検索

これは、親要素のコンテキスト内で一致する子のWebElementのリストを見つけるために利用されます。 
これを実現するために、親WebElementは'findElements'と連鎖して子要素にアクセスします。

**Java**

```java
WebElement form = driver.findElement(By.tagName("form"));
List<WebElement> elements = form.findElements(By.tagName("input"));
for (WebElement e : elements) {
  System.out.println(e.getAttribute("value"));
}
```

**Python**

```python
form = driver.find_element(By.TAG_NAME, 'form')
elements = form.find_elements(By.TAG_NAME, 'input')
for element in elements:
    print(element.get_attribute('value'))
```

**CSharp**

```csharp
IWebElement form = driver.FindElement(By.TagName("form"));
var elements = form.FindElements(By.TagName("input"));
foreach (var e in elements)
{
    System.Console.WriteLine(e.GetAttribute("value"));
}
```

**Ruby**

```ruby
driver.navigate.to locators_page
form = driver.find_element(tag_name: 'form')
elements = form.find_elements(tag_name: 'input')
elements.each { |e| puts e.attribute('value') }
```

**JavaScript**

```javascript
const form = await driver.findElement(By.css('form'));
const elements = await form.findElements(By.css('input'));
for (const e of elements) {
  console.log(await e.getAttribute('value'));
}
```

**Kotlin**

```kotlin
val form = driver.findElement(By.tagName("form"))
val elements = form.findElements(By.tagName("input"))
for (e in elements) {
    println(e.getAttribute("value"))
}
```

## アクティブな要素を取得する

これは、現在のブラウジングコンテキストでフォーカスを持っているDOM要素を追跡（または）検索するために使用されます。

**Java**

```java
driver.findElement(By.cssSelector("#fname")).sendKeys("webElement");
String attr = driver.switchTo().activeElement().getAttribute("name");
```

**Python**

```python
driver.find_element(By.CSS_SELECTOR, '#fname').send_keys('webElement')
attr = driver.switch_to.active_element.get_attribute('name')
```

**CSharp**

```csharp
driver.FindElement(By.CssSelector("#fname")).SendKeys("webElement");
string attr = driver.SwitchTo().ActiveElement().GetAttribute("name");
```

**Ruby**

```ruby
driver.navigate.to locators_page
driver.find_element(css: '#fname').send_keys('webElement')
attr = driver.switch_to.active_element.attribute('name')
```

**JavaScript**

```javascript
await driver.findElement(By.css('#fname')).sendKeys('webElement');
const attr = await driver.switchTo().activeElement().getAttribute('name');
```

**Kotlin**

```kotlin
driver.findElement(By.cssSelector("#fname")).sendKeys("webElement")
val attr = driver.switchTo().activeElement().getAttribute("name")
```
