---
title: "Element 찾기"
weight: 3
---

### 하나의 element 찾기

웹드라이버를 사용할 때 알아야할 가장 기본적인 테크닉 중 하나는 페이지에서 element들을 찾는 것입니다. 웹드라이버는 다양한 종류의 내장된 셀렉터 타입들을 제공해주고, 그 중 하나는 ID 속성을 통해 element들을 찾는 것입니다:

{{< code-tab >}}
  {{< code-panel language="java" >}}
WebElement cheese = driver.findElement(By.id("cheese"));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
driver.find_element(By.ID, "cheese")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
IWebElement element = driver.FindElement(By.Id("cheese"));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
cheese = driver.find_element(id: 'cheese')
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const cheese = driver.findElement(By.id('cheese'));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val cheese: WebElement = driver.findElement(By.id("cheese"))
  {{< / code-panel >}}
{{< / code-tab >}}

예시와 같이, 웹드라이버에서 element들을 찾는 것은 `WebDriver`  인스턴스 객체에서 시행됩니다. `findElement(By)` 메소드는 `WebElement`라는 기초적인 객체를 반환합니다.

* `WebDriver` 는 브라우저를 나타냅니다.
* `WebElement` 는 특정한 DOM 노드를 나타냅니다. (링크나 인풋 필드 등)

발견된 웹 element에 대한 참조가 있다면, 그 참조를 통해 검색 범위를 좁힐 수 있습니다:

{{< code-tab >}}
  {{< code-panel language="java" >}}
WebElement cheese = driver.findElement(By.id("cheese"));
WebElement cheddar = cheese.findElement(By.id("cheddar"));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
cheese = driver.find_element(By.ID, "cheese")
cheddar = cheese.find_elements_by_id("cheddar")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
IWebElement cheese = driver.FindElement(By.Id("cheese"));
IWebElement cheddar = cheese.FindElement(By.Id("cheddar"));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
cheese = driver.find_element(id: 'cheese')
cheddar = cheese.find_element(id: 'cheddar')
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const cheese = driver.findElement(By.id('cheese'));
const cheddar = cheese.findElement(By.id('cheddar'));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val cheese = driver.findElement(By.id("cheese"))
val cheddar = cheese.findElement(By.id("cheddar"))
  {{< / code-panel >}}
{{< / code-tab >}}

이것이 가능한 이유는 *WebDriver* 과 *WebElement* 모두 [_SearchContext_](//seleniumhq.github.io/selenium/docs/api/java/org/openqa/selenium/SearchContext.html)라는 인터페이스를 구현한 것이기 때문입니다.이것은 웹드라이버에서 role-based(역할 기반) 인터페이스라고 알려져 있습니다. Role-based 인터페이스는 특정 드라이버 구현이 주어진 기능을 지원하는지 확인할 수 있게 해줍니다. 이러한 인터페이스는 명확하게 정의되어 있으며, 단 하나의 역할만을 가지고 있는 것을 고수하려고 노력합니다.  웹드라이버의 디자인이나 어떠한 드라이버에서 어떠한 역할을 지원하는지에 대해서는  [여기(아직 미구현)](#)를 참고하십시오.
<!-- TODO: A new section needs to be created for the above.-->

따라서, 위에서 사용한 *By*인터페이스도 다양한 추가 locator 전략을 지원합니다. 중첩된 조회는 브라우저에 별도의 두 개 명령을 실행해야하므로 가장 효과적인 cheese 찾기 전략이 아닐 수 있습니다. 별도의 두 개 명령은 ID "cheese"를 DOM에서 찾는 명령과 그 곳에서 "cheddar"를 그 다음에 찾는 명령을 말합니다.

성능을 향상시키기 위해서는 좀 더 세부적인 locator를 사용해야합니다. 웹드라이버는 CSS locator를 통해 element를 검색하는 기능을 지원하고, 이는 위처럼 두 개의 locator를 사용할 필요가 없게 해줍니다:

{{< code-tab >}}
  {{< code-panel language="java" >}}
driver.findElement(By.cssSelector("#cheese #cheddar"));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
cheddar = driver.find_element_by_css_selector("#cheese #cheddar")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
driver.FindElement(By.CssSelector("#cheese #cheddar"));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
driver.find_element(css: '#cheese #cheddar')
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const cheddar = driver.findElement(By.css('#cheese #cheddar'));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
driver.findElement(By.cssSelector("#cheese #cheddar"))
  {{< / code-panel >}}
{{< / code-tab >}}

### 다수의 element 찾기

우리가 작업하고 있는 문서가 다음과 같이 우리가 제일 좋아하는 치즈들의 정렬된 리스트일 수 있습니다:

```html
<ol id=cheese>
 <li id=cheddar>…
 <li id=brie>…
 <li id=rochefort>…
 <li id=camembert>…
</ol>
```

더 많은 치즈는 의심할 여지 없이 더 좋지만, 각각의 아이템들을 따로 회수하기에는 너무 복잡하고 힘들어질 것입니다. 이를 위한 좀 더 상급 테크닉은 바로 다수 버전의  `findElements(By)`를 사용하는 것입니다. 이 메소드는 web elements들의 컬렉션을 반환합니다. 하나의 element만 발견되어도 여전히 컬렉션을 반환합니다. (이 때의 컬렉션은 하나의 element로 이루어져 있습니다). 만약 아무 element가 발견되지 않는다면, 빈 리스트가 반환됩니다.

{{< code-tab >}}
  {{< code-panel language="java" >}}
List<WebElement> muchoCheese = driver.findElements(By.cssSelector("#cheese li"));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
mucho_cheese = driver.find_elements_by_css_selector("#cheese li")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
IReadOnlyList<IWebElement> muchoCheese = driver.FindElements(By.CssSelector("#cheese li"));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
mucho_cheese = driver.find_elements(css: '#cheese li')
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const muchoCheese = driver.findElements(By.css('#cheese li'));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val muchoCheese: List<WebElement>  = driver.findElements(By.cssSelector("#cheese li"))
  {{< / code-panel >}}
{{< / code-tab >}}

### Element 선택 전략

웹드라이버에는 여덟 가지의 내장된 element location 전략이 있습니다:

| Locator | Description |
| -------- | ---------- |
| class name | class name 중에서 검색한 값이 있는지 찾습니다. |
| css selector | CSS selector 속성 중에서 검색한 값이 있는지 찾습니다. |
| id | ID 속성 중에서 검색한 값이 있는지 찾습니다. |
| name | NAME 속성 중에서 검색한 값이 있는지 찾습니다. |
| link text | 보이는 텍스트 중에서 검색한 값이 있는지 찾습니다. |
| partial link text | 보이는 텍스트 중에서 검색한 값이 있는지 찾습니다. |
| tag name | tag name 중에서 검색한 값이 있는지 찾습니다. |
| xpath | Xpath expression 중에서 검색한 값이 있는지 찾습니다. |

### Selector 사용 팁

일반적으로 HTML ID가 사용가능하고, 고유하며, 예측가능하다면 검색할 때 HTML ID를 통해 하는 것이 좋습니다. HTML ID로 검색하는 것은 매우 빠르며, 복잡하게 DOM을 이용하는 방법들을 속도에서 앞섭니다.

고유한 ID가 없다면, CSS selector이 그 다음으로 선호되는 방법입니다. XPath 또한 CSS selector만큼 잘 작동되지만, 구문이 복잡하며 디버깅을 하기 힘듭니다. 하지만 XPath selector는 매우 유연하기 때문에 일반적으로 브라우저 벤더에 의해 성능 테스트를 받지 않으며 꽤 느린 경향이 있다.

linkText와 partialLinkText를 이용한 전략은 link element에서만 사용이 가능합니다. 또한, 웹드라이버 내부적으로는 XPath selector를 사용하여 작동합니다.

Tag name은 element 검색을 할 때 위험한 방법이 될 수도 있습니다. 한 페이지에는 종종 동일한 tag를 가진 여러 element들이 있습니다. 이 방법은 주로 _findElements(By)_ 메소드를 통해 element의 컬렉션을 반환할 때 유용합니다.

Locator들을 최대한 가독성 있고 간단하게 유지하는 것을 추천드립니다. 웹 드라이버에게 DOM 전체를 검색하라는 것은 성능이 많이 들어가기 때문에, 검색범위를 좁히면 좁힐수록 좋습니다.

## Relative Locators

**Selenium 4**는 Relative Locators, 또는 _Friendly Locators_를 지원합니다. 이 기능은 다른 element 근처에 있는 element를 검색할 때 도움이 됩니다.

사용가능한 Relative Locators들은 다음과 같습니다:

* *above*
* *below*
* *toLeftOf*
* *toRightOf*
* *near*

_findElement_ 메소드는 또다른 메소드 `withTagName()` 를 받습니다. 이 메소드는 Relative Locator를 반환합니다.

### 어떻게 작동하는가

셀레니움은 자바스크립트 함수 [getBoundingClientRect()](https://developer.mozilla.org/en-US/docs/Web/API/Element/getBoundingClientRect) 를 사용해서 releative element를 찾습니다. 이 함수는 right(오른쪽), left(왼쪽), bottom(아래), top(위)와 같은 element의 속성들을 반환합니다.

아래 사진을 예시로 relative locator에 대해 알아봅시다.

![Relative Locators](/images/relative_locators.png?width=400px)

### above()

특정 element 위의 WebElement를 반환합니다.

{{< code-tab >}}
  {{< code-panel language="java" >}}
import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;

WebElement passwordField= driver.findElement(By.id("password"));
WebElement emailAddressField = driver.findElement(withTagName("input")
                                                  .above(passwordField));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium.webdriver.support.relative_locator import with_tag_name

passwordField = driver.find_element(By.ID, "password")
emailAddressField = driver.find_element(with_tag_name("input").above(passwordField))
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using static OpenQA.Selenium.RelativeBy;

IWebElement passwordField = driver.FindElement(By.Id("password"));
IWebElement emailAddressField = driver.FindElement(WithTagName("input")
                                                   .Above(passwordField));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
password_field= driver.find_element(:id, "password")
email_address_field = driver.find_element(relative: {tag_name: 'input', above:password_field})
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
let passwordField = driver.findElement(By.id('password'));
let emailAddressField = await driver.findElement(withTagName('input').above(passwordField));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val passwordField = driver.findElement(By.id("password"))
val emailAddressField = driver.findElement(withTagName("input").above(passwordField))
  {{< / code-panel >}}
{{< / code-tab >}}


### below()

특정 element 아래의 WebElement를 반환합니다.

{{< code-tab >}}
  {{< code-panel language="java" >}}
import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;

WebElement emailAddressField= driver.findElement(By.id("email"));
WebElement passwordField = driver.findElement(withTagName("input")
	                                          .below(emailAddressField));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium.webdriver.support.relative_locator import with_tag_name

emailAddressField = driver.find_element(By.ID, "email")
passwordField = driver.find_element(with_tag_name("input").below(emailAddressField))
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using static OpenQA.Selenium.RelativeBy;  

IWebElement emailAddressField = driver.FindElement(By.Id("email"));
IWebElement passwordField = driver.FindElement(WithTagName("input")
                                               .Below(emailAddressField));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
email_address_field= driver.find_element(:id, "email")
password_field = driver.find_element(relative: {tag_name: 'input', below: email_address_field})
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
let emailAddressField = driver.findElement(By.id('email'));
let passwordField = await driver.findElement(withTagName('input').below(emailAddressField));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val emailAddressField = driver.findElement(By.id("email"))
val passwordField = driver.findElement(withTagName("input").below(emailAddressField))
  {{< / code-panel >}}
{{< / code-tab >}}


### toLeftOf()

특정 element 왼쪽의 WebElement를 반환합니다.

{{< code-tab >}}
  {{< code-panel language="java" >}}
import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;

WebElement submitButton= driver.findElement(By.id("submit"));
WebElement cancelButton= driver.findElement(withTagName("button")
                                            .toLeftOf(submitButton));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium.webdriver.support.relative_locator import with_tag_name

submitButton = driver.find_element(By.ID, "submit")
cancelButton = driver.find_element(with_tag_name("button").
                                   to_left_of(submitButton))
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using static OpenQA.Selenium.RelativeBy;

IWebElement submitButton = driver.FindElement(By.Id("submit"));
IWebElement cancelButton = driver.FindElement(WithTagName("button")
                                              .LeftOf(submitButton));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
submit_button= driver.find_element(:id, "submit")
cancel_button = driver.find_element(relative: {tag_name: 'button', left:submit_button})
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
let submitButton = driver.findElement(By.id("submit"));
let cancelButton = await driver.findElement(withTagName("button").toLeftOf(submitButton));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val submitButton= driver.findElement(By.id("submit"))
val cancelButton= driver.findElement(withTagName("button").toLeftOf(submitButton))
  {{< / code-panel >}}
{{< / code-tab >}}


### toRightOf()

특정 element 오른쪽의 WebElement를 반환합니다.

{{< code-tab >}}
  {{< code-panel language="java" >}}
import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;

WebElement cancelButton= driver.findElement(By.id("cancel"));
WebElement submitButton= driver.findElement(withTagName("button")
                                            .toRightOf(cancelButton));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium.webdriver.support.relative_locator import with_tag_name

cancelButton = driver.find_element(By.ID, "cancel")
submitButton = driver.find_element(with_tag_name("button").
                                   to_right_of(cancelButton))
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using static OpenQA.Selenium.RelativeBy;

IWebElement cancelButton = driver.FindElement(By.Id("cancel"));
IWebElement submitButton = driver.FindElement(WithTagName("button")
                                              .RightOf(cancelButton));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
cancel_button = driver.find_element(:id, "cancel")
submit_button = driver.find_element(relative: {tag_name: 'button', right:cancel_button})
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
let cancelButton = driver.findElement(By.id('cancel'));
let submitButton = await driver.findElement(withTagName('button').toRightOf(cancelButton));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val cancelButton= driver.findElement(By.id("cancel"))
val submitButton= driver.findElement(withTagName("button").toRightOf(cancelButton))
  {{< / code-panel >}}
{{< / code-tab >}}

### near()

특정 element의 최대 50px 근처의 WebElement를 반환합니다.

{{< code-tab >}}
  {{< code-panel language="java" >}}
import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;

WebElement emailAddressLabel= driver.findElement(By.id("lbl-email"));
WebElement emailAddressField = driver.findElement(withTagName("input")
                                                  .near(emailAddressLabel));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium.webdriver.support.relative_locator import with_tag_name

emailAddressLabel = driver.find_element(By.ID, "lbl-email")
emailAddressField = driver.find_element(with_tag_name("input").
                                       near(emailAddressLabel))
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using static OpenQA.Selenium.RelativeBy;

IWebElement emailAddressLabel = driver.FindElement(By.Id("lbl-email"));
IWebElement emailAddressField = driver.FindElement(WithTagName("input")
                                                   .Near(emailAddressLabel));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
email_address_label = driver.find_element(:id, "lbl-email")
email_address_field = driver.find_element(relative: {tag_name: 'input', near: email_address_label})
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
let emailAddressLabel = driver.findElement(By.id("lbl-email"));
let emailAddressField = await driver.findElement(withTagName("input").near(emailAddressLabel));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val emailAddressLabel = driver.findElement(By.id("lbl-email"))
val emailAddressField = driver.findElement(withTagName("input").near(emailAddressLabel))
  {{< / code-panel >}}
{{< / code-tab >}}
