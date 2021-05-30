---
title: "WebDriver Bidi APIs"
weight: 3
---


Selenium 4では、新しいEvented APIが導入され、WebDriverが他のAPIに
使用していた従来のリクエスト/レスポンスのアプローチを使用するのではなく、
発生時にブラウザーからイベントをキャプチャできるようになりました。

内部的にWebDriverは、送信されるイベントとコマンドのためにブラウザーへのWebSocket接続を作成します。

次のAPIのリストは、Seleniumプロジェクトが現実世界のユースケースをサポートを通じて機能するにつれて、増えていきます。
不足している API がある場合は、 [機能リクエスト](https://github.com/SeleniumHQ/selenium/issues/new?assignees=&labels=&template=feature.md) を提出してください。

## Mutation Observation

Mutation Observation は、DOM 内の特定の要素に DOM の変更がある場合に、
WebDriver BiDi を介してイベントをキャプチャする機能です。

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
