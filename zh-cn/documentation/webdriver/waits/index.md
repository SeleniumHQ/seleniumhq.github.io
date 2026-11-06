# 等待策略

URL: https://www.selenium.dev/zh-cn/documentation/webdriver/waits/

---

或许浏览器自动化面临的最常见挑战在于,
确保网络应用程序处于能够按预期执行特定 Selenium 命令的状态. 
这些过程常常陷入一种 _竞态条件_ , 
有时浏览器会先达到正确状态 (一切按预期运行) , 
有时 Selenium 代码会先执行 (一切未按预期运行) . 
这是导致 _不稳定测试_ 的主要原因之一. 

所有导航命令都会等待特定基于 [页面加载策略](https://www.selenium.dev/zh-cn/documentation/webdriver/drivers/options/index.md#pageloadstrategy) 的值 `readyState` 
 (默认等待的值为 `"complete"` ) , 
然后驱动程序才会将控制权交还给代码. 
`readyState`  仅关注 HTML 中定义的资源加载, 
但加载的 JavaScript 资源常常会导致网站发生变化, 
而当代码准备执行下一个 Selenium 命令时, 
需要交互的元素可能尚未出现在页面上. 

同样, 在许多单页应用程序中, 
元素会根据点击操作动态添加到页面上或改变可见性.  
对于 Selenium 能够与之交互, 
该元素必须既存在于页面上又处于[displayed](https://www.selenium.dev/zh-cn/documentation/webdriver/elements/information/index.md#是否显示) 状态. 

以这个页面为例: https://www.selenium.dev/selenium/web/dynamic.html 
当点击 "Add a box!" 按钮时, 
会创建一个原本不存在的 "div" 元素.  
当点击 "Reveal a new input" 按钮时, 
一个隐藏的文本字段元素会被显示出来.  
在这两种情况下, 过渡都需要几秒钟.  
如果 Selenium 代码要点击其中一个按钮并与生成的元素进行交互,  
它会在该元素准备好之前就执行操作, 从而导致失败. 

许多人首先想到的解决办法是在代码中添加一个睡眠语句, 
让代码暂停执行一段设定的时间. 
由于代码无法确切知道需要等待多久, 
如果设置的睡眠时间不够长, 
这种方法可能会失败. 
相反, 如果睡眠时间设置得过高, 并且在每个需要的地方都添加睡眠语句, 
那么会话的持续时间可能会变得难以接受. 

Selenium 提供了更好的两种不同的同步机制, 

## 隐式等待
Selenium 内置了一种自动等待元素出现的方式, 称为 _隐式等待_ .  
隐式等待的值可以通过浏览器选项中的 [timeouts](https://www.selenium.dev/zh-cn/documentation/webdriver/drivers/options/index.md#timeouts) 设置来设定, 
也可以通过驱动程序的方法来设定 (如下所示) . 

这是一个全局设置, 适用于整个会话期间的每个元素定位调用. 
默认值为 `0` , 
这意味着如果未找到元素, 
将立即返回错误. 
如果设置了隐式等待, 
驱动程序将在返回错误之前等待所提供的时长. 
请注意, 一旦定位到元素, 
驱动程序将返回元素引用, 
代码将继续执行, 
因此较大的隐式等待值不一定增加会话的持续时间. 

*警告:*
请勿混合使用隐式等待和显式等待.  
这样做可能会导致等待时间不可预测. 
例如, 设置 10 秒的隐式等待和 15 秒的显式等待,  
可能会导致在 20 秒后发生超时. 

使用隐式等待解决我们的示例代码如下: 

**Java**

```java
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
```

**Python**

```python
driver.implicitly_wait(2)
```

**CSharp**

```csharp
driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromSeconds(2);
```

**Ruby**

```ruby
driver.manage.timeouts.implicit_wait = 2
```

**JavaScript**

```javascript
await driver.manage().setTimeouts({ implicit: 2000 });
```

**Kotlin**

```kotlin
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2))
```

## 显式等待

_显式等待_ 是在代码中添加的, 用于轮询应用程序的循环, 
直到特定条件评估为真时, 才退出循环并继续执行代码中的下一个命令. 
如果在指定的超时值之前条件未满足, 
代码将给出超时错误. 
由于应用程序未处于所需状态的方式有很多, 
因此显式等待是为每个需要等待的地方指定确切等待条件的绝佳选择.  
另一个不错的特性是, 默认情况下, 
Selenium 等待类会自动等待指定的元素存在. 

**Java**

This example shows the condition being waited for as a _lambda_. Java also supports
[Expected Conditions](https://www.selenium.dev/zh-cn/documentation/webdriver/support_features/expected_conditions/index.md)

```java
Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
wait.until(d -> revealed.isDisplayed());
```

**Python**

This example shows the condition being waited for as a _lambda_. Python also supports
[Expected Conditions](https://www.selenium.dev/zh-cn/documentation/webdriver/support_features/expected_conditions/index.md)

```python
wait = WebDriverWait(driver, timeout=2)
wait.until(lambda _ : revealed.is_displayed())
```

**CSharp**

```csharp
WebDriverWait wait = new WebDriverWait(driver, TimeSpan.FromSeconds(2));
wait.Until(d => revealed.Displayed);
```

**Ruby**

```ruby
wait = Selenium::WebDriver::Wait.new
wait.until { revealed.displayed? }
```

**JavaScript**

JavaScript also supports [Expected Conditions](https://www.selenium.dev/zh-cn/documentation/webdriver/support_features/expected_conditions/index.md)

```javascript
await driver.wait(until.elementIsVisible(revealed), 2000);
```

**Kotlin**

```kotlin
val wait =  WebDriverWait(driver, Duration.ofSeconds(2))
wait.until { revealed.isDisplayed }
```

### Built-in conditions

Most bindings include a set of ready-made conditions
for the situations that come up most often in waits.
You don't have to write a custom lambda for each one.
Conditions that take a locator search for the element on every poll,
so they also wait for it to appear.
Many of them hand back the element (or elements) once the condition is met,
letting you use the result of the wait directly.

This example locates an element, waits for it to be visible,
and then waits for its text, using only built-in conditions:

**Java**

```java
Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
wait.until(ExpectedConditions.titleIs("Web form - target page"));
WebElement message = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("message")));
wait.until(ExpectedConditions.visibilityOf(message));
wait.until(ExpectedConditions.textToBePresentInElement(message, "Received!"));
```

**Python**

```python
wait = WebDriverWait(driver, timeout=2)
wait.until(EC.title_is("Web form - target page"))
message = wait.until(EC.presence_of_element_located((By.ID, "message")))
wait.until(EC.visibility_of(message))
wait.until(EC.text_to_be_present_in_element((By.ID, "message"), "Received!"))
```

**CSharp**

.NET does not ship built-in conditions. Pass a lambda to `WebDriverWait.Until()`, as in the
[explicit wait](/zh-cn/documentation/webdriver/waits/index.md#%e6%98%be%e5%bc%8f%e7%ad%89%e5%be%85) example.

**Ruby**

Ruby does not ship built-in conditions. Pass a block to `Selenium::WebDriver::Wait#until`, as in the
[explicit wait](/zh-cn/documentation/webdriver/waits/index.md#%e6%98%be%e5%bc%8f%e7%ad%89%e5%be%85) example.

**JavaScript**

```javascript
await driver.wait(until.titleIs("Web form - target page"), 2000);
let message = await driver.wait(until.elementLocated(By.id("message")), 2000);
await driver.wait(until.elementIsVisible(message), 2000);
await driver.wait(until.elementTextIs(message, "Received!"), 2000);
```

**Kotlin**

Kotlin uses the Java `ExpectedConditions` class listed in the Java tab below.

The complete list of built-in conditions for each binding:

**Java**

Class `org.openqa.selenium.support.ui.ExpectedConditions`
([API docs](https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/ui/ExpectedConditions.html)).
Use with `new WebDriverWait(driver, timeout).until(...)`.

<table>
	<thead>
			<tr>
					<th>Condition</th>
					<th>Waits until</th>
			</tr>
	</thead>
	<tbody>
			<tr>
					<td>`titleIs(title)`</td>
					<td>The page title equals the value</td>
			</tr>
			<tr>
					<td>`titleContains(title)`</td>
					<td>The page title contains the value</td>
			</tr>
			<tr>
					<td>`urlToBe(url)`</td>
					<td>The current URL equals the value</td>
			</tr>
			<tr>
					<td>`urlContains(fraction)`</td>
					<td>The current URL contains the value</td>
			</tr>
			<tr>
					<td>`urlMatches(regex)`</td>
					<td>The current URL matches the regular expression</td>
			</tr>
			<tr>
					<td>`presenceOfElementLocated(By)`</td>
					<td>An element is in the DOM (not necessarily visible); returns it</td>
			</tr>
			<tr>
					<td>`presenceOfAllElementsLocatedBy(By)`</td>
					<td>At least one element is in the DOM; returns them all</td>
			</tr>
			<tr>
					<td>`presenceOfNestedElementLocatedBy(By or WebElement, By)`</td>
					<td>A child element is in the DOM under a parent; returns it</td>
			</tr>
			<tr>
					<td>`presenceOfNestedElementsLocatedBy(By, By)`</td>
					<td>Child elements are in the DOM under a parent; returns them</td>
			</tr>
			<tr>
					<td>`visibilityOfElementLocated(By)`</td>
					<td>An element is in the DOM and displayed; returns it</td>
			</tr>
			<tr>
					<td>`visibilityOf(WebElement)`</td>
					<td>A known element is displayed; returns it</td>
			</tr>
			<tr>
					<td>`visibilityOfAllElementsLocatedBy(By)`</td>
					<td>All matching elements are displayed; returns them</td>
			</tr>
			<tr>
					<td>`visibilityOfAllElements(WebElement...)`</td>
					<td>All given elements are displayed; returns them</td>
			</tr>
			<tr>
					<td>`visibilityOfNestedElementsLocatedBy(By or WebElement, By)`</td>
					<td>Child elements under a parent are displayed; returns them</td>
			</tr>
			<tr>
					<td>`invisibilityOfElementLocated(By)`</td>
					<td>An element is hidden or not in the DOM</td>
			</tr>
			<tr>
					<td>`invisibilityOf(WebElement)`</td>
					<td>A known element is hidden or no longer in the DOM</td>
			</tr>
			<tr>
					<td>`invisibilityOfAllElements(WebElement...)`</td>
					<td>All given elements are hidden</td>
			</tr>
			<tr>
					<td>`invisibilityOfElementWithText(By, text)`</td>
					<td>No displayed element matching the locator has the given text</td>
			</tr>
			<tr>
					<td>`elementToBeClickable(By or WebElement)`</td>
					<td>An element is displayed and enabled; returns it</td>
			</tr>
			<tr>
					<td>`elementToBeSelected(By or WebElement)`</td>
					<td>An element is selected</td>
			</tr>
			<tr>
					<td>`elementSelectionStateToBe(By or WebElement, selected)`</td>
					<td>An element’s selected state matches the value</td>
			</tr>
			<tr>
					<td>`stalenessOf(WebElement)`</td>
					<td>An element is no longer attached to the DOM</td>
			</tr>
			<tr>
					<td>`textToBe(By, text)`</td>
					<td>An element’s text equals the value</td>
			</tr>
			<tr>
					<td>`textToBePresentInElement(WebElement, text)`</td>
					<td>An element’s text contains the value</td>
			</tr>
			<tr>
					<td>`textToBePresentInElementLocated(By, text)`</td>
					<td>A located element’s text contains the value</td>
			</tr>
			<tr>
					<td>`textToBePresentInElementValue(By or WebElement, text)`</td>
					<td>An element’s `value` contains the value</td>
			</tr>
			<tr>
					<td>`textMatches(By, Pattern)`</td>
					<td>An element’s text matches the regular expression</td>
			</tr>
			<tr>
					<td>`attributeToBe(By or WebElement, attribute, value)`</td>
					<td>An element’s attribute or CSS property equals the value</td>
			</tr>
			<tr>
					<td>`attributeContains(By or WebElement, attribute, value)`</td>
					<td>An element’s attribute or CSS property contains the value</td>
			</tr>
			<tr>
					<td>`attributeToBeNotEmpty(WebElement, attribute)`</td>
					<td>An element’s attribute or CSS property is not empty</td>
			</tr>
			<tr>
					<td>`domAttributeToBe(WebElement, attribute, value)`</td>
					<td>An element’s DOM attribute equals the value</td>
			</tr>
			<tr>
					<td>`domPropertyToBe(WebElement, property, value)`</td>
					<td>An element’s DOM property equals the value</td>
			</tr>
			<tr>
					<td>`numberOfElementsToBe(By, number)`</td>
					<td>The number of matching elements equals the value; returns them</td>
			</tr>
			<tr>
					<td>`numberOfElementsToBeMoreThan(By, number)`</td>
					<td>The number of matching elements is more than the value; returns them</td>
			</tr>
			<tr>
					<td>`numberOfElementsToBeLessThan(By, number)`</td>
					<td>The number of matching elements is less than the value; returns them</td>
			</tr>
			<tr>
					<td>`numberOfWindowsToBe(number)`</td>
					<td>The number of open windows or tabs equals the value</td>
			</tr>
			<tr>
					<td>`frameToBeAvailableAndSwitchToIt(frame)`</td>
					<td>A frame, given as a `By`, name or ID, index, or `WebElement`, is available; then switches to it</td>
			</tr>
			<tr>
					<td>`alertIsPresent()`</td>
					<td>An alert is open; returns it</td>
			</tr>
			<tr>
					<td>`jsReturnsValue(script)`</td>
					<td>The JavaScript returns a value; returns it</td>
			</tr>
			<tr>
					<td>`javaScriptThrowsNoExceptions(script)`</td>
					<td>The JavaScript runs without throwing</td>
			</tr>
			<tr>
					<td>`and(conditions...)`</td>
					<td>All of the conditions are true</td>
			</tr>
			<tr>
					<td>`or(conditions...)`</td>
					<td>Any of the conditions is true</td>
			</tr>
			<tr>
					<td>`not(condition)`</td>
					<td>The condition is false</td>
			</tr>
			<tr>
					<td>`refreshed(condition)`</td>
					<td>The condition is true, retrying it after a stale element error</td>
			</tr>
	</tbody>
</table>

**Python**

Module `selenium.webdriver.support.expected_conditions`
([API docs](https://www.selenium.dev/selenium/docs/api/py/webdriver_support/selenium.webdriver.support.expected_conditions.html)),
usually imported as `EC`.
Use with `WebDriverWait(driver, timeout).until(...)`.
Locators are tuples, such as `(By.ID, "message")`.

<table>
	<thead>
			<tr>
					<th>Condition</th>
					<th>Waits until</th>
			</tr>
	</thead>
	<tbody>
			<tr>
					<td>`title_is(title)`</td>
					<td>The page title equals the value</td>
			</tr>
			<tr>
					<td>`title_contains(title)`</td>
					<td>The page title contains the value</td>
			</tr>
			<tr>
					<td>`url_to_be(url)`</td>
					<td>The current URL equals the value</td>
			</tr>
			<tr>
					<td>`url_contains(url)`</td>
					<td>The current URL contains the value</td>
			</tr>
			<tr>
					<td>`url_matches(pattern)`</td>
					<td>The current URL matches the regular expression</td>
			</tr>
			<tr>
					<td>`url_changes(url)`</td>
					<td>The current URL is different from the value</td>
			</tr>
			<tr>
					<td>`presence_of_element_located(locator)`</td>
					<td>An element is in the DOM (not necessarily visible); returns it</td>
			</tr>
			<tr>
					<td>`presence_of_all_elements_located(locator)`</td>
					<td>At least one element is in the DOM; returns them all</td>
			</tr>
			<tr>
					<td>`visibility_of_element_located(locator)`</td>
					<td>An element is in the DOM and displayed; returns it</td>
			</tr>
			<tr>
					<td>`visibility_of(element)`</td>
					<td>A known element is displayed; returns it</td>
			</tr>
			<tr>
					<td>`visibility_of_any_elements_located(locator)`</td>
					<td>At least one matching element is displayed; returns the displayed ones</td>
			</tr>
			<tr>
					<td>`visibility_of_all_elements_located(locator)`</td>
					<td>All matching elements are displayed; returns them</td>
			</tr>
			<tr>
					<td>`invisibility_of_element_located(locator or element)`</td>
					<td>An element is hidden or not in the DOM</td>
			</tr>
			<tr>
					<td>`invisibility_of_element(element or locator)`</td>
					<td>An element is hidden or not in the DOM</td>
			</tr>
			<tr>
					<td>`element_to_be_clickable(locator or element)`</td>
					<td>An element is displayed and enabled; returns it</td>
			</tr>
			<tr>
					<td>`element_to_be_selected(element)`</td>
					<td>A known element is selected</td>
			</tr>
			<tr>
					<td>`element_located_to_be_selected(locator)`</td>
					<td>A located element is selected</td>
			</tr>
			<tr>
					<td>`element_selection_state_to_be(element, is_selected)`</td>
					<td>A known element’s selected state matches the value</td>
			</tr>
			<tr>
					<td>`element_located_selection_state_to_be(locator, is_selected)`</td>
					<td>A located element’s selected state matches the value</td>
			</tr>
			<tr>
					<td>`staleness_of(element)`</td>
					<td>An element is no longer attached to the DOM</td>
			</tr>
			<tr>
					<td>`text_to_be_present_in_element(locator, text)`</td>
					<td>An element’s text contains the value</td>
			</tr>
			<tr>
					<td>`text_to_be_present_in_element_value(locator, text)`</td>
					<td>An element’s `value` contains the value</td>
			</tr>
			<tr>
					<td>`text_to_be_present_in_element_attribute(locator, attribute, text)`</td>
					<td>An element’s attribute contains the value</td>
			</tr>
			<tr>
					<td>`element_attribute_to_include(locator, attribute)`</td>
					<td>An element has the attribute</td>
			</tr>
			<tr>
					<td>`number_of_windows_to_be(num_windows)`</td>
					<td>The number of open windows or tabs equals the value</td>
			</tr>
			<tr>
					<td>`new_window_is_opened(current_handles)`</td>
					<td>A window or tab has opened since the handles were read</td>
			</tr>
			<tr>
					<td>`frame_to_be_available_and_switch_to_it(frame)`</td>
					<td>A frame, given as a locator, name or ID, or element, is available; then switches to it</td>
			</tr>
			<tr>
					<td>`alert_is_present()`</td>
					<td>An alert is open; returns it</td>
			</tr>
			<tr>
					<td>`all_of(*conditions)`</td>
					<td>All of the conditions are true</td>
			</tr>
			<tr>
					<td>`any_of(*conditions)`</td>
					<td>Any of the conditions is true</td>
			</tr>
			<tr>
					<td>`none_of(*conditions)`</td>
					<td>None of the conditions is true</td>
			</tr>
	</tbody>
</table>

**CSharp**

.NET does not ship built-in conditions.
The `ExpectedConditions` class was removed from the bindings in Selenium 4.
Pass a lambda to `WebDriverWait.Until()` that returns a non-null value or `true`
when the condition is met.

**Ruby**

Ruby does not ship built-in conditions.
Pass a block to `Selenium::WebDriver::Wait#until` that returns a truthy value
when the condition is met.

**JavaScript**

Module `until` from `selenium-webdriver`
([API docs](https://www.selenium.dev/selenium/docs/api/javascript/index.html)).
Use with `await driver.wait(until.condition(...), timeout)`.
Conditions that check an element resolve to that element once they are met.

<table>
	<thead>
			<tr>
					<th>Condition</th>
					<th>Waits until</th>
			</tr>
	</thead>
	<tbody>
			<tr>
					<td>`titleIs(title)`</td>
					<td>The page title equals the value</td>
			</tr>
			<tr>
					<td>`titleContains(substr)`</td>
					<td>The page title contains the value</td>
			</tr>
			<tr>
					<td>`titleMatches(regex)`</td>
					<td>The page title matches the regular expression</td>
			</tr>
			<tr>
					<td>`urlIs(url)`</td>
					<td>The current URL equals the value</td>
			</tr>
			<tr>
					<td>`urlContains(substrUrl)`</td>
					<td>The current URL contains the value</td>
			</tr>
			<tr>
					<td>`urlMatches(regex)`</td>
					<td>The current URL matches the regular expression</td>
			</tr>
			<tr>
					<td>`elementLocated(locator)`</td>
					<td>An element is in the DOM; returns it</td>
			</tr>
			<tr>
					<td>`elementsLocated(locator)`</td>
					<td>At least one element is in the DOM; returns them all</td>
			</tr>
			<tr>
					<td>`elementIsVisible(element)`</td>
					<td>An element is displayed</td>
			</tr>
			<tr>
					<td>`elementIsNotVisible(element)`</td>
					<td>An element is not displayed</td>
			</tr>
			<tr>
					<td>`elementIsEnabled(element)`</td>
					<td>An element is enabled</td>
			</tr>
			<tr>
					<td>`elementIsDisabled(element)`</td>
					<td>An element is disabled</td>
			</tr>
			<tr>
					<td>`elementIsSelected(element)`</td>
					<td>An element is selected</td>
			</tr>
			<tr>
					<td>`elementIsNotSelected(element)`</td>
					<td>An element is not selected</td>
			</tr>
			<tr>
					<td>`elementTextIs(element, text)`</td>
					<td>An element’s text equals the value</td>
			</tr>
			<tr>
					<td>`elementTextContains(element, substr)`</td>
					<td>An element’s text contains the value</td>
			</tr>
			<tr>
					<td>`elementTextMatches(element, regex)`</td>
					<td>An element’s text matches the regular expression</td>
			</tr>
			<tr>
					<td>`stalenessOf(element)`</td>
					<td>An element is no longer attached to the DOM</td>
			</tr>
			<tr>
					<td>`ableToSwitchToFrame(frame)`</td>
					<td>A frame is available, then switches to it</td>
			</tr>
			<tr>
					<td>`alertIsPresent()`</td>
					<td>An alert is open; returns it</td>
			</tr>
	</tbody>
</table>

**Kotlin**

Kotlin uses the Java `ExpectedConditions` class. See the Java tab.

### 定制

Wait 类可以通过各种参数进行实例化, 
这些参数会改变条件的评估方式. 

这可以包括：
* 更改代码的评估频率 (轮询间隔) 
* 指定哪些异常应自动处理
* 更改总超时时长
* 自定义超时消息

例如, 如果默认情况下对 _元素不可交互_ 错误进行重试, 
那么我们可以在执行中的代码里的某个方法内添加一个操作
 (我们只需要确保代码在成功时返回  `true`  即可)：

**Java**

The easiest way to customize Waits in Java is to use the `FluentWait` class:

```java
Wait<WebDriver> wait =
    new FluentWait<>(driver)
        .withTimeout(Duration.ofSeconds(2))
        .pollingEvery(Duration.ofMillis(300))
        .ignoring(ElementNotInteractableException.class);

wait.until(
    d -> {
      revealed.sendKeys("Displayed");
      return true;
    });
```

**Python**

```python
errors = [NoSuchElementException, ElementNotInteractableException]
wait = WebDriverWait(driver, timeout=2, poll_frequency=.2, ignored_exceptions=errors)
wait.until(lambda _ : revealed.send_keys("Displayed") or True)
```

**CSharp**

```csharp
WebDriverWait wait = new WebDriverWait(driver, TimeSpan.FromSeconds(2))
{
    PollingInterval = TimeSpan.FromMilliseconds(300),
};
wait.IgnoreExceptionTypes(typeof(ElementNotInteractableException));

wait.Until(d => {
    revealed.SendKeys("Displayed");
    return true;
});
```

**Ruby**

```ruby
errors = [Selenium::WebDriver::Error::NoSuchElementError,
          Selenium::WebDriver::Error::ElementNotInteractableError]
wait = Selenium::WebDriver::Wait.new(timeout: 2,
                                     interval: 0.3,
                                     ignore: errors)

wait.until { revealed.send_keys('Displayed') || true }
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

```kotlin
val wait: Wait<WebDriver> =
    FluentWait(driver)
        .withTimeout(Duration.ofSeconds(2))
        .pollingEvery(Duration.ofMillis(300))
        .ignoring(ElementNotInteractableException::class.java)

wait.until {
    revealed.sendKeys("Displayed")
    true
}
```
