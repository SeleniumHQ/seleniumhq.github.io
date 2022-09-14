---
title: "待機"
linkTitle: "待機"
weight: 6
aliases: ["/documentation/ja/webdriver/waits/"]
---

WebDriverは一般にブロッキングAPIを持っていると言えます。
ブラウザーに処理を _指示する_ Out-of-Processライブラリであり、Webプラットフォームは本質的に非同期の性質を持っているため、WebDriverはDOMのアクティブでリアルタイムな状態を追跡しません。
このことは、ここで説明するいくつかの課題が出てきます。

経験から、SeleniumとWebDriverの使用から生じる断続的なもののほとんどは、ブラウザーとユーザーの指示の間で発生する _競合状態_ に関連しています。
たとえば、ユーザーがブラウザーにページに移動するように指示し、要素を見つけようとすると、**no such element** エラーが表示される場合があります。

次のドキュメントを考えてみましょう。

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

WebDriverの指示は十分問題なく見えるかもしれません。

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

ここでは、WebDriverで使用されるデフォルトの [ページロード戦略]({{< ref "drivers/options#pageloadstrategy" >}}) が`document.readyState`をリッスンして、ナビゲーションの呼び出しから戻る前に`"complete"`に変更することが問題です。ドキュメントの読み込みが完了した後に`p`要素が追加されるため、このWebDriverスクリプトは断続的になる _可能性があります。_ これらのイベントを明示的に待機（またはブロック）せずに非同期でトリガーする要素またはイベントについては保証できないため、断続的である可能性があります。

幸いなことに、 _WebElement.click_ や _WebElement.sendKeys_ などのWebElementインターフェイスで使用可能な通常の命令セットを使用すると、コマンドの呼び出しがブラウザーで完了するまで関数呼び出しが返されない（または、コールバックはコールバックスタイルの言語ではトリガーされない）ため、同期が保証されます。高度なユーザーインタラクションAPIである[_キーボード_]({{< ref "actions_api/keyboard.md" >}})と[_マウス_]({{< ref "actions_api/mouse.md" >}})は、 "言うことをする" 非同期コマンドとして明示的に意図されているため、例外です。

待機とは、自動化されたタスクの実行を一定時間経過させてから次のステップに進むことです。

ブラウザーとWebDriverスクリプト間の競合状態の問題を克服するために、ほとんどのSeleniumクライアントには待機パッケージが付属しています。待機を使用する場合、一般に[_明示的な待機_](#明示的な待機)と呼ばれるものを使用しています。

## 明示的な待機

Seleniumクライアントは、命令型の手続き型言語の _明示的な待機_  を利用できます。
これにより、 _条件_ が解決するまで、コードでプログラムの実行を停止したり、スレッドをフリーズしたりできます。
条件は、明示的な待機のタイムアウトが経過するまで特定の頻度で呼び出されます。
つまり、条件がfalseの値を返す限り、試行、待機し続けます。

明示的な待機により条件が発生するのを待機できるため、ブラウザーとそのDOM、およびWebDriverスクリプトの間で状態を同期するのに適しています。

以前のバグのある命令セットを修正するには、スクリプトから動的に追加された要素がDOMに追加されるまで、 _findElement_ 呼び出しを待機させるために待機を採用できます。

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

戻り値がtrueになるまで _待機_ が繰り返し実行される関数リファレンスとして _条件_ を渡します。
"真の"戻り値とは、文字列、数値、ブール値、オブジェクト（ _WebElement_ を含む）、または入力された（空でない）シーケンスまたはリストなど、手元の言語でブール値trueと評価されるものです。
つまり、 _空のリスト_ はfalseと評価されます。
条件がtrueで、ブロッキング待機が中止されると、条件からの戻り値が待機の戻り値になります。

このナレッジと、ウェイトユーティリティはデフォルトで _no such element_ エラーを無視するため、より簡潔になるように命令をリファクタリングできます。

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

この例では、匿名関数を渡します（ただし、以前に行ったように明示的に定義して再利用できるようにすることもできます）。
条件に渡される最初で唯一の引数は、常にドライバーオブジェクト　_WebDriver_　への参照です。
マルチスレッド環境では、外部スコープ内のドライバーへのリファレンスではなく、条件に渡されたドライバーのリファレンスを操作するように注意する必要があります。

待機は、要素が見つからないときに発生する _no such element_ エラーを飲み込むため、要素が見つかるまで条件は再試行されます。
次に、戻り値である _WebElement_ を取得して、スクリプトに渡します。

条件が失敗した場合、例えば条件からの真の戻り値に到達しない場合、待機は _timeout error_ と呼ばれるエラー/例外をスロー/発生させます。

### オプション

待機条件は、ニーズに合わせてカスタマイズできます。
成功した条件にヒットしないことに対するペナルティは高額になる可能性があるため、デフォルトのタイムアウトの全範囲を待つ必要がない場合があります。

WebDriverWaitに引数を渡してタイムアウトをオーバーライドできます。

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

### 期待される条件

DOMと指示を同期しなければならないことは非常に一般的であるため、ほとんどのクライアントには事前に定義された一連の _期待される条件_もあります。
名前から明らかなように、これらは頻繁な待機操作に対して事前定義されている条件です。

異なる言語バインディングで利用可能な条件は異なりますが、これは少数の抜粋したリストです。

<!-- TODO(ato): Fill in -->
* alert is present
* element exists
* element is visible
* title contains
* title is
* element staleness
* visible text

各クライアントバインディングのAPIドキュメントを参照して、予想される条件の完全なリストを見つけることができます。

* Java's [org.openqa.selenium.support.ui.ExpectedConditions](//seleniumhq.github.io/selenium/docs/api/java/org/openqa/selenium/support/ui/ExpectedConditions.html) class
* Python's [selenium.webdriver.support.expected_conditions](//seleniumhq.github.io/selenium/docs/api/py/webdriver_support/selenium.webdriver.support.expected_conditions.html?highlight=expected) class
* .NET's [OpenQA.Selenium.Support.UI.ExpectedConditions](//seleniumhq.github.io/selenium/docs/api/dotnet/html/T_OpenQA_Selenium_Support_UI_ExpectedConditions.htm) type
* JavaScript's [selenium-webdriver/lib/until](//seleniumhq.github.io/selenium/docs/api/javascript/module/selenium-webdriver/lib/until.html) module


## 暗黙的な待機

_暗黙的な待機_ と呼ばれる[明示的な待機](#明示的な待機)とは異なる2番目の種類の待機があります。
暗黙的に待機することにより、WebDriverは _何か_ 要素を見つけようとするときに特定の期間DOMをポーリングします。
これは、Webページ上の特定の要素がすぐに利用できず、ロードに時間がかかる場合に役立ちます。

要素の表示を暗黙的に待機することはデフォルトで無効になっており、セッションごとに手動で有効にする必要があります。
[明示的な待機](#明示的な待機)と暗黙的な待機を混在させると、意図しない結果、すなわち、要素が利用可能または条件が真であっても、最大時間スリープする待機が発生します。

*警告 :*
暗黙的な待機と明示的な待機を混在させないでください。
これを行うと、予測できない待機時間が発生する可能性があります。
たとえば、10秒の暗黙的な待機と15秒の明示的な待機を設定すると、20秒後にタイムアウトが発生する可能性があります。

暗黙的な待機は、1つまたは複数の要素がすぐに利用できない場合にそれらを見つけようとするときにWebDriverにDOMを一定時間ポーリングするように指示することです。
デフォルト設定は0で、無効を意味します。
設定すると、セッションの存続期間中、暗黙的な待機が設定されます。

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

## FluentWait

FluentWaitインスタンスは、条件を待機する最大時間を定義します。
状態を確認する頻度も同様です。

ユーザーは、ページ上の要素を検索するときの`NoSuchElementException`など、待機中に特定の種類の例外を無視するように待機を構成できます。

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
