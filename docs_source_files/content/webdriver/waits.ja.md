---
title: "Waits"
weight: 4
---

{{% notice info %}}
<i class="fas fa-language"></i> ページは英語から日本語へ訳されています。
日本語は話せますか？プルリクエストをして翻訳を手伝ってください!
{{% /notice %}}

WebDriver can generally be said to have a blocking API.
Because it is an out-of-process library that
_instructs_ the browser what to do,
and because the web platform has an intrinsically asynchronous nature,
WebDriver doesn't track the active, real-time state of the DOM.
This comes with some challenges that we will discuss here.

From experience,
most intermittents that arise from use of Selenium and WebDriver
are connected to _race conditions_ that occur between
the browser and the user's instructions.
An example could be that the user instructs the browser to navigate to a page,
then gets a **no such element** error
when trying to find an element.

Consider the following document:

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

The WebDriver instructions might look innocent enough:

{{< code-tab >}}
  {{< code-panel language="java" >}}
driver.get("file:///race_condition.html");
WebElement element = driver.findElement(By.tagName("p"));
assertEquals(element.getText(), "Hello from JavaScript!");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
driver.navigate("file:///race_condition.html")
el = driver.find_element_by_tag_name("p")
assert el.text == "Hello from JavaScript!"
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
driver.Navigate().GoToUrl("file:///race_condition.html");
IWebElement element = driver.FindElement(By.TagName("p"));
assertEquals(element.Text, "Hello from JavaScript!");
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
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
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
await driver.get('file:///race_condition.html');
const element = await driver.findElement(By.css('p'));
assert.strictEqual(await element.getText(), 'Hello from JavaScript!');
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
driver.get("file:///race_condition.html")
val element = driver.findElement(By.tagName("p"))
assert(element.text == "Hello from JavaScript!")
  {{< / code-panel >}}
{{< / code-tab >}}

The issue here is that the default
[page load strategy]({{< ref "/webdriver/page_loading_strategy.ja.md" >}})
used in WebDriver listens for the `document.readyState`
to change to `"complete"` before returning from the call to _navigate_.
Because the `p` element is
added _after_ the document has completed loading,
this WebDriver script _might_ be intermittent.
It “might” be intermittent because no guarantees can be made
about elements or events that trigger asynchronously
without explicitly waiting—or blocking—on those events.

Fortunately, using the normal instruction set available on
the [_WebElement_]({{< ref "/webdriver/web_element.ja.md" >}}) interface—such
 as _WebElement.click_ and _WebElement.sendKeys_—are
 guaranteed to be synchronous,
 in that the function calls won't return
 (or the callback won't trigger in callback-style languages)
 until the command has been completed in the browser.
 The advanced user interaction APIs,
 [_Keyboard_]({{< ref "/webdriver/keyboard.ja.md" >}})
 and [_Mouse_]({{< ref "/webdriver/mouse.ja.md" >}}),
 are exceptions as they are explicitly intended as
 “do what I say” asynchronous commands.

Waiting is having the automated task execution
elapse a certain amount of time before continuing with the next step.

To overcome the problem of race conditions
between the browser and your WebDriver script,
most Selenium clients ship with a _wait_ package.
When employing a wait,
you are using what is commonly referred to
as an [_explicit wait_](#explicit-wait).


## Explicit wait

_Explicit waits_ are available to Selenium clients
for imperative, procedural languages.
They allow your code to halt program execution,
or freeze the thread,
until the _condition_ you pass it resolves.
The condition is called with a certain frequency
until the timeout of the wait is elapsed.
This means that for as long as the condition returns a falsy value,
it will keep trying and waiting.

Since explicit waits allow you to wait for a condition to occur,
they make a good fit for synchronising the state between the browser and its DOM,
and your WebDriver script.

To remedy our buggy instruction set from earlier,
we could employ a wait to have the _findElement_ call
wait until the dynamically added element from the script
has been added to the DOM:

{{< code-tab >}}
  {{< code-panel language="java" >}}
WebDriver driver = new ChromeDriver();
driver.get("https://google.com/ncr");
driver.findElement(By.name("q")).sendKeys("cheese" + Keys.ENTER);
// Initialize and wait till element(link) became clickable - timeout in 10 seconds
WebElement firstResult = new WebDriverWait(driver, 10)
        .until(ExpectedConditions.elementToBeClickable(By.xpath("//a/h3")));
// Print the first result
System.out.println(firstResult.getText());
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium.webdriver.support.ui import WebDriverWait
def document_initialised(driver):
    return driver.execute_script("return initialised")

driver.navigate("file:///race_condition.html")
WebDriverWait(driver).until(document_initialised)
el = driver.find_element_by_tag_name("p")
assert el.text == "Hello from JavaScript!"
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
driver = new ChromeDriver();
driver.Url = "https://www.google.com/ncr";
driver.FindElement(By.Name("q")).SendKeys("cheese" + Keys.Enter);
            
WebDriverWait wait = new WebDriverWait(driver, TimeSpan.FromSeconds(10));
IWebElement firstResult = wait.Until(e => e.FindElement(By.XPath("//a/h3")));

Console.WriteLine(firstResult.Text);
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
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
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const documentInitialised = () =>
    driver.executeScript('return initialised');

await driver.get('file:///race_condition.html');
await driver.wait(() => documentInitialised(), 10000);
const element = await driver.findElement(By.css('p'));
assert.strictEqual(await element.getText(), 'Hello from JavaScript!');
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
driver.get("https://google.com/ncr")
driver.findElement(By.name("q")).sendKeys("cheese" + Keys.ENTER)
// Initialize and wait till element(link) became clickable - timeout in 10 seconds
val firstResult = WebDriverWait(driver, 10)
      .until(ExpectedConditions.elementToBeClickable(By.xpath("//a/h3")))
// Print the first result
println(firstResult.text)
  {{< / code-panel >}}
{{< / code-tab >}}

We pass in the _condition_ as a function reference
that the _wait_ will run repeatedly until its return value is truthy.
A “truthful” return value is anything that evaluates to boolean true
in the language at hand, such as a string, number, a boolean,
an object (including a _WebElement_),
or a populated (non-empty) sequence or list.
That means an _empty list_ evaluates to false.
When the condition is truthful and the blocking wait is aborted,
the return value from the condition becomes the return value of the wait.

With this knowledge,
and because the wait utility ignores _no such element_ errors by default,
we can refactor our instructions to be more concise:

{{< code-tab >}}
  {{< code-panel language="java" >}}
WebElement foo = new WebDriverWait(driver, 3)
            .until(driver -> driver.findElement(By.name("q")));
assertEquals(foo.getText(), "Hello from JavaScript!"); 
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium.webdriver.support.ui import WebDriverWait

driver.navigate("file:///race_condition.html")
el = WebDriverWait(driver).until(lambda d: return d.find_element_by_tag_name("p"))
assert el.text == "Hello from JavaScript!"
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# We don't have a Ruby code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// We don't have a JavaScript code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
driver.get("file:///race_condition.html")
val ele = WebDriverWait(getWebDriver(), 10)
            .until(ExpectedConditions.presenceOfElementLocated(By.tagName("p")))
assert(ele.text == "Hello from JavaScript!")
  {{< / code-panel >}}
{{< / code-tab >}}

In that example, we pass in an anonymous function
(but we could also define it explicitly as we did earlier so it may be reused).
The first and only argument that is passed to our condition
is always a reference to our driver object, _WebDriver_
(called `d` in the example).
In a multi-threaded environment, you should be careful
to operate on the driver reference passed in to the condition
rather than the reference to the driver in the outer scope.

Because the wait will swallow _no such element_ errors
that are raised when the element isn't found,
the condition will retry until the element is found.
Then it will take the return value, a _WebElement_,
and pass it back through to our script.

If the condition fails,
e.g. a truthful return value from the condition is never reached,
the wait will throw/raise an error/exception called a _timeout error_.


### Options

The wait condition can be customised to match your needs.
Sometimes it's unnecessary to wait the full extent of the default timeout,
as the penalty for not hitting a successful condition can be expensive.

The wait lets you pass in an argument to override the timeout:

{{< code-tab >}}
  {{< code-panel language="java" >}}
//new WebDriverWait(driver,3).until(some_condition(WebElement))
new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(By.xpath("//a/h3")));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
WebDriverWait(driver, timeout=3).until(some_condition)
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# We don't have a Ruby code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// We don't have a JavaScript code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(By.xpath("//a/h3")))
  {{< / code-panel >}}
{{< / code-tab >}}

### Expected conditions

Because it's quite a common occurrence
to have to synchronise the DOM and your instructions,
most clients also come with a set of predefined _expected conditions_.
As might be obvious by the name,
they are conditions that are predefined for frequent wait operations.

The conditions available in the different language bindings vary,
but this is a non-exhaustive list of a few:

<!-- TODO(ato): Fill in -->
* alert is present
* element exists
* element is visible
* title contains
* title is
* element staleness
* visible text

You can refer to the API documentation for each client binding
to find an exhaustive list of expected conditions:

* Java's [org.openqa.selenium.support.ui.ExpectedConditions](//seleniumhq.github.io/selenium/docs/api/java/org/openqa/selenium/support/ui/ExpectedConditions.html) class
* Python's [selenium.webdriver.support.expected_conditions](//seleniumhq.github.io/selenium/docs/api/py/webdriver_support/selenium.webdriver.support.expected_conditions.html?highlight=expected) class
* .NET's [OpenQA.Selenium.Support.UI.ExpectedConditions](//seleniumhq.github.io/selenium/docs/api/dotnet/html/T_OpenQA_Selenium_Support_UI_ExpectedConditions.html) type


## Implicit wait

There is a second type of wait that is distinct from
[explicit wait](#explicit-wait) called _implicit wait_.
By implicitly waiting, WebDriver polls the DOM
for a certain duration when trying to find _any_ element.
This can be useful when certain elements on the webpage
are not available immediately and need some time to load.

Implicit waiting for elements to appear is disabled by default
and will need to be manually enabled on a per-session basis.
Mixing [explicit waits](#explicit-wait) and implicit waits
will cause unintended consequences, namely waits sleeping for the maximum
time even if the element is available or condition is true.

*Warning:*
Do not mix implicit and explicit waits.
Doing so can cause unpredictable wait times.
For example, setting an implicit wait of 10 seconds
and an explicit wait of 15 seconds
could cause a timeout to occur after 20 seconds.

An implicit wait is to tell WebDriver to poll the DOM
for a certain amount of time when trying to find an element or elements
if they are not immediately available.
The default setting is 0, meaning disabled.
Once set, the implicit wait is set for the life of the session.

{{< code-tab >}}
  {{< code-panel language="java" >}}
WebDriver driver = new FirefoxDriver();
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
driver.get("http://somedomain/url_that_delays_loading");
WebElement myDynamicElement = driver.findElement(By.id("myDynamicElement"));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
driver = Firefox()
driver.implicitly_wait(10)
driver.get("http://somedomain/url_that_delays_loading")
my_dynamic_element = driver.find_element_by_id("myDynamicElement")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
IWebDriver driver = new ChromeDriver();
driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromSeconds(10);
driver.Url = "http://somedomain/url_that_delays_loading";
IWebElement dynamicElement = driver.FindElement(By.Name("dynamicElement"));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :firefox
driver.manage.timeouts.implicit_wait = 10

begin
  driver.get 'http://somedomain/url_that_delays_loading'
  search_form = driver.find_element(:id,'dynamic_element')
ensure
  driver.quit
end
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
(async function(){

// Apply timeout for 10 seconds
await driver.manage().setTimeouts( { implicit: 10000 } );

// Navigate to url
await driver.get('http://somedomain/url_that_delays_loading');

let webElement = await driver.findElement(By.id("myDynamicElement"));

}());
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val driver = FirefoxDriver()
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
driver.get("http://somedomain/url_that_delays_loading")
val myDynamicElement = driver.findElement(By.id("myDynamicElement"))
  {{< / code-panel >}}
{{< / code-tab >}}

## FluentWait

FluentWait instance defines the maximum amount of time to wait for a condition,
as well as the frequency with which to check the condition.

Users may configure the wait to ignore specific types of exceptions whilst waiting,
such as `NoSuchElementException` when searching for an element on the page.

{{< code-tab >}}
  {{< code-panel language="java" >}}
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
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
driver = Firefox()
driver.get("http://somedomain/url_that_delays_loading")
wait = WebDriverWait(driver, 10, poll_frequency=1, ignored_exceptions=[ElementNotVisibleException, ElementNotSelectableException])
element = wait.until(EC.element_to_be_clickable((By.XPATH, "//div")))
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# We don't have a Ruby code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// We don't have a JavaScript code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val wait = FluentWait<WebDriver>(getWebDriver())
        .withTimeout(Duration.ofSeconds(30))
        .pollingEvery(Duration.ofSeconds(3))
        .ignoring(NoSuchElementException::class.java)

val foo = wait.until {it.findElement(By.id("foo")) }
  {{< / code-panel >}}
{{< / code-tab >}}
