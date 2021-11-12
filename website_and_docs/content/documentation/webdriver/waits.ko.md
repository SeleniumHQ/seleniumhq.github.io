---
title: "대기"
linkTitle: "Waits"
weight: 4
aliases: ["/documentation/ko/webdriver/waits/"]
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to Korean. Do you speak Korean? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

WebDriver can generally be said to have a blocking API.
Because it is an out-of-process library that
_instructs_ the browser what to do,
and because the web platform has an intrinsically asynchronous nature,
WebDriver does not track the active, real-time state of the DOM.
This comes with some challenges that we will discuss here.

From experience,
most intermittent issues that arise from use of Selenium and WebDriver
are connected to _race conditions_ that occur between
the browser and the user's instructions.
An example could be that the user instructs the browser to navigate to a page,
then gets a **no such element** error
when trying to find an element.

Consider the following document:

웹드라이버는 일반적으로 차단 API를 가지고 있다고 말할 수 있습니다.
브라우저가 수행할 작업을 _지시_ 하는 프로세스 중 라이브러리이기 때문에,
그리고 웹 플랫폼은 본질적으로 비동기적인 특성을 가지고 있기 때문에, 웹드라이버는 DOM의 활성과 실시간 상태를 추적하지 않습니다.
이에 대해 여기서 논의할 몇 가지 과제가 있습니다.

경험에 비추어 볼 때, 셀레늄과 웹드라이버의 사용으로 인해 발생하는 가장 간헐적인 문제
브라우저와 사용자의 지시사항 사이에 발생하는 _종족 조건_ 에 연결됩니다. 예를 들어 
사용자가 브라우저에 페이지로 이동하도록 지시한 다음 요소를 찾으려고 할 때 **no this element** 오류가
나타날 수 있습니다.

다음 문서를 고려하십시오:

```html
<!doctype html>
<meta charset=utf-8>
<title>Race Condition Example</title>

<script>
  var initialised = false;
  window.addEventListener("load", function() {
    var newElement = document.createElement("p");
    newElement.textContent = "Hello from JavaScript!";
    document.body.appendChild(newElement);
    initialised = true;
  });
</script>
```

WebDriver 지침은 잘못이 없어 보일 수 있습니다:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
driver.get("file:///race_condition.html");
WebElement element = driver.findElement(By.tagName("p"));
assertEquals(element.getText(), "Hello from JavaScript!");
  {{< /tab >}}
  {{< tab header="Python" >}}
driver.navigate("file:///race_condition.html")
el = driver.find_element(By.TAG_NAME, "p")
assert el.text == "Hello from JavaScript!"
  {{< /tab >}}
  {{< tab header="CSharp" >}}
driver.Navigate().GoToUrl("file:///race_condition.html");
IWebElement element = driver.FindElement(By.TagName("p"));
assertEquals(element.Text, "Hello from JavaScript!");
  {{< /tab >}}
  {{< tab header="Ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :firefox
begin
  # Navigate to URL
  driver.get 'file:///race_condition.html'

  # Get and store Paragraph Text
  search_form = driver.find_element(:css,'p').text

  "Hello from JavaScript!".eql? search_form
ensure
  driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
await driver.get('file:///race_condition.html');
const element = driver.findElement(By.css('p'));
assert.strictEqual(await element.getText(), 'Hello from JavaScript!');
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
driver.get("file:///race_condition.html")
val element = driver.findElement(By.tagName("p"))
assert(element.text == "Hello from JavaScript!")
  {{< /tab >}}
{{< /tabpane >}}

The issue here is that the default
[page load strategy]
used in WebDriver listens for the `document.readyState`
to change to `"complete"` before returning from the call to _navigate_.
Because the `p` element is
added _after_ the document has completed loading,
this WebDriver script _might_ be intermittent.
It “might” be intermittent because no guarantees can be made
about elements or events that trigger asynchronously
without explicitly waiting—or blocking—on those events.

여기서 문제는 WebDriver에 사용되는 기본 [페이지 로드 전략]({{< ref "/page_loading_strategy.md" >}}) 이 탐색을 
위한 호출에서 돌아오기 전에 `document.readyState`를 `"complete"`로 변경한다는 것입니다. 문서 로드가 완료된 후 
p 요소가 추가되기 때문에 이 WebDriver 스크립트는 간헐적일 수 있습니다. 해당 이벤트에 대해 명시적으로 대기하거나
차단하지 않고 비동기적으로 트리거하는 요소나 이벤트에 대해서는 보장이 불가능하기 때문에 "조금" 간헐적으로 발생합니다.

 
 다행히  [_WebElement_]({{< ref "/web_element.md" >}}) 인터페이스(예: WebElement.click 및 WebElement)에서 
 사용할 수 있는 일반적인 명령 집합입니다.sendKeys—함수 호출이 브라우저에서 명령이 완료될 때까지 반환되지 않거나
 콜백 스타일의 언어로 콜백이 트리거되지 않는다는 점에서 동기화가 보장됩니다. 고급 사용자 상호 작용 API인
 [_키보드_]({{< ref "/keyboard.md" >}})와 [_마우스_]({{< ref "/mouse_and_keyboard_actions_in_detail.md" >}})는 
 명시적으로 "내가 말한 대로 하라" 비동기 명령으로 의도되었기 때문에 예외입니다.

대기 중이란 다음 단계를 계속하기 전에 자동화된 작업 실행 시간이 일정 시간 경과하는 것입니다.

브라우저와 웹드라이버 스크립트 사이의 경쟁 조건 문제를 극복하기 위해 대부분의 셀레늄 클라이언트는 대기 
패키지와 함께 배송됩니다. 대기 기능을 사용할 때 일반적으로 [_명시적 대기_](#explicit-wait)라고 하는 것을 사용하는 것입니다.


## 명시적 대기

Selenium 클라이언트는 명령형 절차 언어를 사용할 수 있습니다. 전달된 _조건_ 이 해결될 때까지 코드 
실행을 중지하거나 스레드를 고정할 수 있습니다. 이 조건은 대기 시간이 경과될 때까지 특정 빈도로 호출됩니다.
이는 조건이 거짓 값을 반환하는 한 계속 시도하고 기다릴 것임을 의미합니다.

명시적 대기는 당신이 어떤 조건이 발생할 때까지 기다릴 수 있게 해주기 때문에, 그것들은 브라우저와 그것의 DOM, 
그리고 당신의 WebDriver 스크립트 사이의 상태를 동기화하는데 적합합니다.

이전 버전의 버그 명령 집합을 수정하기 위해 스크립트에서 동적으로 추가된 요소가 DOM에 추가될 때까지
_findElement_ 호출 대기 시간을 사용할 수 있습니다:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
WebDriver driver = new ChromeDriver();
driver.get("https://google.com/ncr");
driver.findElement(By.name("q")).sendKeys("cheese" + Keys.ENTER);
// Initialize and wait till element(link) became clickable - timeout in 10 seconds
WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.elementToBeClickable(By.xpath("//a/h3")));
// Print the first result
System.out.println(firstResult.getText());
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium.webdriver.support.ui import WebDriverWait
def document_initialised(driver):
    return driver.execute_script("return initialised")

driver.navigate("file:///race_condition.html")
WebDriverWait(driver).until(document_initialised)
el = driver.find_element(By.TAG_NAME, "p")
assert el.text == "Hello from JavaScript!"
  {{< /tab >}}
  {{< tab header="CSharp" >}}
driver = new ChromeDriver();
driver.Url = "https://www.google.com/ncr";
driver.FindElement(By.Name("q")).SendKeys("cheese" + Keys.Enter);
            
WebDriverWait wait = new WebDriverWait(driver, TimeSpan.FromSeconds(10));
IWebElement firstResult = wait.Until(e => e.FindElement(By.XPath("//a/h3")));

Console.WriteLine(firstResult.Text);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :firefox
wait = Selenium::WebDriver::Wait.new(:timeout => 10)

def document_initialised(driver)
  driver.execute_script('return initialised')
end

begin
  driver.get 'file:///race_condition.html'
  wait.until{document_initialised driver}
  search_form = driver.find_element(:css,'p').text
  "Hello from JavaScript!".eql? search_form
ensure
  driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const documentInitialised = () =>
    driver.executeScript('return initialised');

await driver.get('file:///race_condition.html');
await driver.wait(() => documentInitialised(), 10000);
const element = driver.findElement(By.css('p'));
assert.strictEqual(await element.getText(), 'Hello from JavaScript!');
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
driver.get("https://google.com/ncr")
driver.findElement(By.name("q")).sendKeys("cheese" + Keys.ENTER)
// Initialize and wait till element(link) became clickable - timeout in 10 seconds
val firstResult = WebDriverWait(driver, Duration.ofSeconds(10))
      .until(ExpectedConditions.elementToBeClickable(By.xpath("//a/h3")))
// Print the first result
println(firstResult.text)
  {{< /tab >}}
{{< /tabpane >}}

우리는 그 반환값이 truthy가 될 때까지 반복적으로 대기 상태가 실행되는 함수 참조로서 조건을 통과시킵니다. 
진실 반환 값은 문자열, 숫자, 부울, 개체(_WebElement_ 포함) 또는 채워진 시퀀스 또는 목록과 같이 
현재 언어에서 부울 true로 평가하는 모든 값입니다. 그것은 빈 리스트가 거짓으로 평가된다는 것을 의미합니다. 
조건이 진실이고 차단 대기가 중단되면 조건으로부터의 반환 값이 대기 값의 반환 값이 됩니다.

이러한 지식으로, 그리고 대기 유틸리티는 기본적으로 그러한 요소 오류를 무시하지 않기 때문에, 우리는 보다 
간결하게 하기 위해 우리의 지시를 리팩터링할 수 있습니다.

With this knowledge,
and because the wait utility ignores _no such element_ errors by default,
we can refactor our instructions to be more concise:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
WebElement foo = new WebDriverWait(driver, Duration.ofSeconds(3))
          .until(driver -> driver.findElement(By.name("q")));
assertEquals(foo.getText(), "Hello from JavaScript!"); 
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium.webdriver.support.ui import WebDriverWait

driver.navigate("file:///race_condition.html")
el = WebDriverWait(driver).until(lambda d: d.find_element_by_tag_name("p"))
assert el.text == "Hello from JavaScript!"
  {{< /tab >}}
  {{< tab header="CSharp" >}}
   using (var driver = new FirefoxDriver())
    {
        var foo = new WebDriverWait(driver, TimeSpan.FromSeconds(3))
                        .Until(drv => drv.FindElement(By.Name("q")));
        Debug.Assert(foo.Text.Equals("Hello from JavaScript!"));
    }
  {{< /tab >}}
  {{< tab header="Ruby" >}}
  driver.get 'file:///race_condition.html'
  wait = Selenium::WebDriver::Wait.new(:timeout => 10)
  ele = wait.until { driver.find_element(css: 'p')}
  foo = ele.text
  assert_match foo, 'Hello from JavaScript'
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
let ele = await driver.wait(until.elementLocated(By.css('p')),10000);
let foo = await ele.getText();
assert(foo == "Hello from JavaScript");
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
driver.get("file:///race_condition.html")
val ele = WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.presenceOfElementLocated(By.tagName("p")))
assert(ele.text == "Hello from JavaScript!")
  {{< /tab >}}
{{< /tabpane >}}

그 예에서, 우리는 익명 함수를 전달한다(그러나 우리는 또한 그것을 재사용될 수 있도록 하기 위해 우리가 전에 
했던 것처럼 명시적으로 정의할 수도 있다). 우리의 조건에 전달되는 첫 번째이자 유일한 인수는 항상 우리의 
드라이버 객체인 _WebDriver_ 에 대한 참조이다. 다중 스레드 환경에서는 외부 범위의 드라이버에 대한 참조가 
아니라 조건에 전달된 드라이버 참조에 대해 작동해야 합니다.

요소를 찾을 수 없을 때 발생하는 요소 오류는 대기 중에 발생하지 않으므로 요소를 찾을 때까지 조건이 다시 
시도합니다. 그런 다음 반환 값인 _WebElement_ 를 가져와서 스크립트로 다시 전달합니다.

조건이 실패하는 경우(예: 조건으로부터의 진실된 반환 값에 도달하지 못하는 경우, 대기 시간은 _시간 초과 오류_ 
라고 하는 오류/예외를 던지거나 올립니다.


### 옵션

대기 조건을 필요에 맞게 사용자 정의할 수 있습니다. 경우에 따라 기본 시간 초과의 전체 범위를 기다릴 필요가 없습니다. 
성공적인 조건에 도달하지 못할 경우 패널티가 비쌀 수 있습니다.

대기 중 인수를 전달하여 시간 초과를 재정의할 수 있습니다.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//a/h3")));
  {{< /tab >}}
  {{< tab header="Python" >}}
WebDriverWait(driver, timeout=3).until(some_condition)
  {{< /tab >}}
  {{< tab header="CSharp" >}}
new WebDriverWait(driver, TimeSpan.FromSeconds(3)).Until(ExpectedConditions.ElementToBeClickable(By.XPath("//a/h3")));
  {{< /tab >}}
  {{< tab header="Ruby" >}}
wait = Selenium::WebDriver::Wait.new(:timeout => 10)

wait.until { driver.find_element(:id, 'message').displayed? }
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
  await driver.wait(until.elementLocated(By.id('foo')), 30000);
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//a/h3")))
  {{< /tab >}}
{{< /tabpane >}}

### 예상 조건

DOM과 지시사항을 동기화해야 하는 경우가 매우 흔하기 때문에 대부분의 클라이언트에는 미리 정의된 예상 조건 집합도 
함께 제공됩니다. 이름에서 알 수 있듯이, 빈번한 대기 작업을 위해 미리 정의된 조건입니다.

다른 언어 바인딩에서 사용할 수 있는 조건은 다양하지만, 다음은 몇 가지에 대한 전체적이지 않은 목록입니다.

<!-- TODO(ato): Fill in -->
* 현재임을 인식
* 존재하는 요소
* 보이는 요소
* 제목 포함
* 제목은 ~
* 넉넉한 element
* 보이는 텍스트

각 클라이언트 바인딩에 대한 API 설명서를 참조하여 예상 조건의 전체 목록을 찾을 수 있습니다:

* Java's [org.openqa.selenium.support.ui.ExpectedConditions](//seleniumhq.github.io/selenium/docs/api/java/org/openqa/selenium/support/ui/ExpectedConditions.html) class
* Python's [selenium.webdriver.support.expected_conditions](//seleniumhq.github.io/selenium/docs/api/py/webdriver_support/selenium.webdriver.support.expected_conditions.html?highlight=expected) class
* .NET's [OpenQA.Selenium.Support.UI.ExpectedConditions](//seleniumhq.github.io/selenium/docs/api/dotnet/html/T_OpenQA_Selenium_Support_UI_ExpectedConditions.htm) type
* JavaScript's [selenium-webdriver/lib/until](//seleniumhq.github.io/selenium/docs/api/javascript/module/selenium-webdriver/lib/until.html) module


## 암묵적 대기


_암묵적 대기_ 라고 하는 [명시적 대기](#explicit-wait)와는 구별되는 두 번째 대기 유형이 있습니다. 
암시적으로 기다림으로써, WebDriver는 요소를 찾으려고 할 때 일정 기간 동안 DOM을 폴링합니다. 이 기능은
웹 페이지의 특정 요소를 즉시 사용할 수 없고 로드하는 데 시간이 필요한 경우에 유용할 수 있습니다.

*Warning:*
암시적 대기와 명시적 대기 시간을 함께 사용하지 마십시오. 이렇게 하면 대기 시간을 예측할 수 없습니다. 
예를 들어 암묵적 대기 시간을 10초로 설정하고 명시적 대기 시간을 15초로 설정하면 20초 후에 시간 초과가 발생할 수 있습니다.

암묵적 대기란 웹드라이버가 요소나 요소를 즉시 사용할 수 없는 경우 요소를 찾으려고 할 때 일정 시간 동안 
DOM을 폴링하도록 지시하는 것입니다. 기본 설정은 0이며, 비활성화됨을 의미합니다. 일단 설정하면 세션 수명 동안 암시적 대기가 설정됩니다.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
WebDriver driver = new FirefoxDriver();
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
driver.get("http://somedomain/url_that_delays_loading");
WebElement myDynamicElement = driver.findElement(By.id("myDynamicElement"));
  {{< /tab >}}
  {{< tab header="Python" >}}
driver = Firefox()
driver.implicitly_wait(10)
driver.get("http://somedomain/url_that_delays_loading")
my_dynamic_element = driver.find_element(By.ID, "myDynamicElement")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
IWebDriver driver = new ChromeDriver();
driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromSeconds(10);
driver.Url = "http://somedomain/url_that_delays_loading";
IWebElement dynamicElement = driver.FindElement(By.Name("dynamicElement"));
  {{< /tab >}}
  {{< tab header="Ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :firefox
driver.manage.timeouts.implicit_wait = 10

begin
  driver.get 'http://somedomain/url_that_delays_loading'
  search_form = driver.find_element(:id,'dynamic_element')
ensure
  driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
(async function(){

// Apply timeout for 10 seconds
await driver.manage().setTimeouts( { implicit: 10000 } );

// Navigate to url
await driver.get('http://somedomain/url_that_delays_loading');

let webElement = driver.findElement(By.id("myDynamicElement"));

}());
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val driver = FirefoxDriver()
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
driver.get("http://somedomain/url_that_delays_loading")
val myDynamicElement = driver.findElement(By.id("myDynamicElement"))
  {{< /tab >}}
{{< /tabpane >}}

## FluentWait

FluentWait 인스턴스는 조건을 확인하는 빈도뿐만 아니라 조건을 대기하는 최대 시간을 정의합니다.

사용자는 기다리는 동안 `NoSchElement` 와 같은 특정 유형의 예외를 무시하도록 대기 시간을 구성할 수
있습니다.페이지에서 요소를 검색할 때 예외입니다.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
// Waiting 30 seconds for an element to be present on the page, checking
// for its presence once every 5 seconds.
Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
  .withTimeout(Duration.ofSeconds(30))
  .pollingEvery(Duration.ofSeconds(5))
  .ignoring(NoSuchElementException.class);

WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
  public WebElement apply(WebDriver driver) {
    return driver.findElement(By.id("foo"));
  }
});
  {{< /tab >}}
  {{< tab header="Python" >}}
driver = Firefox()
driver.get("http://somedomain/url_that_delays_loading")
wait = WebDriverWait(driver, 10, poll_frequency=1, ignored_exceptions=[ElementNotVisibleException, ElementNotSelectableException])
element = wait.until(EC.element_to_be_clickable((By.XPATH, "//div")))
  {{< /tab >}}
  {{< tab header="CSharp" >}}
using (var driver = new FirefoxDriver())
{
  WebDriverWait wait = new WebDriverWait(driver, timeout: TimeSpan.FromSeconds(30))
  {
      PollingInterval = TimeSpan.FromSeconds(5),
  };
  wait.IgnoreExceptionTypes(typeof(NoSuchElementException));

  var foo = wait.Until(drv => drv.FindElement(By.Id("foo")));
}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :firefox
exception = Selenium::WebDriver::Error::NoSuchElementError

begin
  driver.get 'http://somedomain/url_that_delays_loading'
  wait = Selenium::WebDriver::Wait.new(timeout: 30, interval: 5, message: 'Timed out after 30 sec', ignore: exception)
  foo = wait.until { driver.find_element(id: 'foo')}
ensure
  driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const {Builder, until} = require('selenium-webdriver');

(async function example() {
    let driver = await new Builder().forBrowser('firefox').build();
    await driver.get('http://somedomain/url_that_delays_loading');
    // Waiting 30 seconds for an element to be present on the page, checking
    // for its presence once every 5 seconds.
    let foo = await driver.wait(until.elementLocated(By.id('foo')), 30000, 'Timed out after 30 seconds', 5000);
})();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val wait = FluentWait<WebDriver>(driver)
        .withTimeout(Duration.ofSeconds(30))
        .pollingEvery(Duration.ofSeconds(3))
        .ignoring(NoSuchElementException::class.java)

val foo = wait.until {it.findElement(By.id("foo")) }
  {{< /tab >}}
{{< /tabpane >}}

