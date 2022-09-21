---
title: "等待"
linkTitle: "等待"
weight: 6
aliases: ["/documentation/zh-cn/webdriver/waits/"]
---

WebDriver通常可以说有一个阻塞API。因为它是一个指示浏览器做什么的进程外库，而且web平台本质上是异步的，所以WebDriver不跟踪DOM的实时活动状态。这伴随着一些我们将在这里讨论的挑战。

根据经验，大多数由于使用Selenium和WebDriver而产生的间歇性问题都与浏览器和用户指令之间的 _竞争条件_ 有关。例如，用户指示浏览器导航到一个页面，然后在试图查找元素时得到一个 **no such element** 的错误。

考虑下面的文档：

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

这个  WebDriver的说明可能看起来很简单:

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

这里的问题是WebDriver中使用的默认页面加载策略[页面加载策略]({{< ref "drivers/options#pageloadstrategy" >}})听从`document.readyState`在返回调用 _navigate_ 之前将状态改为`"complete"` 。因为`p`元素是在文档完成加载之后添加的，所以这个WebDriver脚本可能是间歇性的。它“可能”间歇性是因为无法做出保证说异步触发这些元素或事件不需要显式等待或阻塞这些事件。

幸运的是，[_WebElement_]({{< ref "elements" >}})接口上可用的正常指令集——例如 _WebElement.click_ 和 _WebElement.sendKeys_—是保证同步的，因为直到命令在浏览器中被完成之前函数调用是不会返回的(或者回调是不会在回调形式的语言中触发的)。高级用户交互APIs,[_键盘_]({{< ref "actions_api/keyboard.md" >}})和[_鼠标_]({{< ref "actions_api/mouse.md" >}})是例外的，因为它们被明确地设计为“按我说的做”的异步命令。

等待是在继续下一步之前会执行一个自动化任务来消耗一定的时间。

为了克服浏览器和WebDriver脚本之间的竞争问题，大多数Selenium客户都附带了一个 _wait_ 包。在使用等待时，您使用的是通常所说的[_显式等待_](#explicit-wait)。

## 显式等待

_显示等待_ 是Selenium客户可以使用的命令式过程语言。它们允许您的代码暂停程序执行，或冻结线程，直到满足通过的 _条件_ 。这个条件会以一定的频率一直被调用，直到等待超时。这意味着只要条件返回一个假值，它就会一直尝试和等待

由于显式等待允许您等待条件的发生，所以它们非常适合在浏览器及其DOM和WebDriver脚本之间同步状态。

为了弥补我们之前的错误指令集，我们可以使用等待来让 _findElement_ 调用等待直到脚本中动态添加的元素被添加到DOM中:

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
from selenium.webdriver.support.wait import WebDriverWait
def document_initialised(driver):
    return driver.execute_script("return initialised")

driver.navigate("file:///race_condition.html")
WebDriverWait(driver, timeout=10).until(document_initialised)
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

我们将 _条件_ 作为函数引用传递， _等待_ 将会重复运行直到其返回值为true。“truthful”返回值是在当前语言中计算为boolean true的任何值，例如字符串、数字、boolean、对象(包括 _WebElement_ )或填充(非空)的序列或列表。这意味着 _空列表_ 的计算结果为false。当条件为true且阻塞等待终止时，条件的返回值将成为等待的返回值。

有了这些知识，并且因为等待实用程序默认情况下会忽略 _no such element_ 的错误，所以我们可以重构我们的指令使其更简洁:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
WebElement foo = new WebDriverWait(driver, Duration.ofSeconds(3))
          .until(driver -> driver.findElement(By.name("q")));
assertEquals(foo.getText(), "Hello from JavaScript!");         
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium.webdriver.support.wait import WebDriverWait

driver.navigate("file:///race_condition.html")
el = WebDriverWait(driver, timeout=3).until(lambda d: d.find_element(By.TAG_NAME,"p"))
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

在这个示例中，我们传递了一个匿名函数(但是我们也可以像前面那样显式地定义它，以便重用它)。传递给我们条件的第一个，也是唯一的一个参数始终是对驱动程序对象 _WebDriver_ 的引用。在多线程环境中，您应该小心操作传入条件的驱动程序引用，而不是外部范围中对驱动程序的引用。

因为等待将会吞没在没有找到元素时引发的 _no such element_ 的错误，这个条件会一直重试直到找到元素为止。然后它将获取一个 _WebElement_ 的返回值，并将其传递回我们的脚本。

如果条件失败，例如从未得到条件为真实的返回值，等待将会抛出/引发一个叫 _timeout error_ 的错误/异常。

### 选项

等待条件可以根据您的需要进行定制。有时候是没有必要等待缺省超时的全部范围，因为没有达到成功条件的代价可能很高。

等待允许你传入一个参数来覆盖超时:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//a/h3")));
  {{< /tab >}}
  {{< tab header="Python" >}}
WebDriverWait(driver, timeout=3).until(some_condition)
  {{< /tab >}}
  {{< tab header="CSharp" >}}
 new WebDriverWait(driver, TimeSpan.FromSeconds(3)).Until(ExpectedConditions.ElementToBeClickable(By.XPath("//a/h3")));  {{< /tab >}}
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

### 预期的条件

由于必须同步DOM和指令是相当常见的情况，所以大多数客户端还附带一组预定义的 _预期条件_ 。顾名思义，它们是为频繁等待操作预定义的条件。

不同的语言绑定提供的条件各不相同，但这只是其中一些:

<!-- TODO(ato): Fill in -->
* alert is present
* element exists
* element is visible
* title contains
* title is
* element staleness
* visible text

您可以参考每个客户端绑定的API文档，以找到期望条件的详尽列表:

* Java's [org.openqa.selenium.support.ui.ExpectedConditions](//seleniumhq.github.io/selenium/docs/api/java/org/openqa/selenium/support/ui/ExpectedConditions.html) class
* Python's [selenium.webdriver.support.expected_conditions](//seleniumhq.github.io/selenium/docs/api/py/webdriver_support/selenium.webdriver.support.expected_conditions.html?highlight=expected) class
* .NET's [OpenQA.Selenium.Support.UI.ExpectedConditions](//seleniumhq.github.io/selenium/docs/api/dotnet/html/T_OpenQA_Selenium_Support_UI_ExpectedConditions.htm) type
* JavaScript's [selenium-webdriver/lib/until](//seleniumhq.github.io/selenium/docs/api/javascript/module/selenium-webdriver/lib/until.html) module


## 隐式等待

还有第二种区别于[显示等待](#explicit-wait) 类型的 _隐式等待_ 。通过隐式等待，WebDriver在试图查找_任何_元素时在一定时间内轮询DOM。当网页上的某些元素不是立即可用并且需要一些时间来加载时是很有用的。

默认情况下隐式等待元素出现是禁用的，它需要在单个会话的基础上手动启用。将[显式等待](#explicit-wait)和隐式等待混合在一起会导致意想不到的结果，就是说即使元素可用或条件为真也要等待睡眠的最长时间。

*警告:*
不要混合使用隐式和显式等待。这样做会导致不可预测的等待时间。例如，将隐式等待设置为10秒，将显式等待设置为15秒，可能会导致在20秒后发生超时。

隐式等待是告诉WebDriver如果在查找一个或多个不是立即可用的元素时轮询DOM一段时间。默认设置为0，表示禁用。一旦设置好，隐式等待就被设置为会话的生命周期。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
WebDriver driver = new FirefoxDriver();
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10))
driver.get("http://somedomain/url_that_delays_loading")
val myDynamicElement = driver.findElement(By.id("myDynamicElement"))
  {{< /tab >}}
{{< /tabpane >}}

## 流畅等待

流畅等待实例定义了等待条件的最大时间量，以及检查条件的频率。

用户可以配置等待来忽略等待时出现的特定类型的异常，例如在页面上搜索元素时出现的`NoSuchElementException`。

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
wait = WebDriverWait(driver, timeout=10, poll_frequency=1, ignored_exceptions=[ElementNotVisibleException, ElementNotSelectableException])
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
}  {{< /tab >}}
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

