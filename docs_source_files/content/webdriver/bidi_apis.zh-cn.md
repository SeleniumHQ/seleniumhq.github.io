---
title: "WebDriver Bidi APIs"
weight: 3
---


在Selenium 4中, 引入了新的事件API,
使用户能够在事件发生时从浏览器捕获事件, 
并非WebDriver用于其他API的传统请求/响应方法.

WebDriver将在内部创建针对浏览器的WebSocket连接,
用于传输事件和命令.

随着Selenium项目更多地用于实际案例, 相应的API列表将不断扩充.
如果缺少API, 请提出[功能请求](https://github.com/SeleniumHQ/selenium/issues/new?assignees=&labels=&template=feature.md). 

## 变化监测

变化监测是一种能力,
用于当特定元素的DOM发生变化时,
得以通过WebDriver BiDi捕获事件.

{{< code-tab >}}
  {{< code-panel language="java" >}}
WebDriver driver = new FirefoxDriver();


HasLogEvents logger = (HasLogEvents) driver;

AtomicReference<DomMutationEvent> seen = new AtomicReference<>();
CountDownLatch latch = new CountDownLatch(1);
logger.onLogEvent(domMutation(mutation -> {
    seen.set(mutation);
    latch.countDown();
}));

driver.get("http://www.google.com");
WebElement span = driver.findElement(By.cssSelector("span"));

((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('cheese', 'gouda');", span);

assertThat(latch.await(10, SECONDS)).isTrue();
assertThat(seen.get().getAttributeName()).isEqualTo("cheese");
assertThat(seen.get().getCurrentValue()).isEqualTo("gouda");


  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.wait import WebDriverWait

driver = webdriver.Chrome()
async with driver.log.mutation_events() as event:
    pages.load("dynamic.html")
    driver.find_element(By.ID, "reveal").click()
    WebDriverWait(driver, 5)\
        .until(EC.visibility_of(driver.find_element(By.ID, "revealed")))

assert event["attribute_name"] == "style"
assert event["current_value"] == ""
assert event["old_value"] == "display:none;"

  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
IWebDriver driver = new FirefoxDriver();
driver.Url = "http://www.google.com";
// Please help with a .NET example
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :firefox
begin
  driver.on_log_event(:mutation) { |mutation| mutations.push(mutation) }
  driver.navigate.to url_for('dynamic.html')
  driver.find_element(id: 'reveal').click
  wait.until { mutations.any? }
  mutation = mutations.first
  expect(mutation.element).to eq(driver.find_element(id: 'revealed'))
  expect(mutation.attribute_name).to eq('style')
  expect(mutation.current_value).to eq('')
  expect(mutation.old_value).to eq('display:none;')
ensure
  driver.quit
end
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
let {Builder, By} = require('selenium-webdriver');
driver = new Builder().forBrowser('firefox').build();

(async function test(){

    const cdpConnection = await driver.createCDPConnection('page')
    await driver.logMutationEvents(cdpConnection, function (event) {
        assert.strictEqual(event['attribute_name'], 'style')
        assert.strictEqual(event['current_value'], '')
        assert.strictEqual(event['old_value'], 'display:none;')
    })

    await driver.get("https://www.google.com")

    let element = driver.findElement({ id: 'reveal' })
    await element.click()
    let revealed = driver.findElement({ id: 'revealed' })
    await driver.wait(until.elementIsVisible(revealed), 5000)

})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val driver = FirefoxDriver()
driver.get("http://www.google.com")
// Please help us create an example for Kotlin
  {{< / code-panel >}}
{{< / code-tab >}}
