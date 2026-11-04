---
title: "等待策略"
linkTitle: "等待"
weight: 6
---


或许浏览器自动化面临的最常见挑战在于,
确保网络应用程序处于能够按预期执行特定 Selenium 命令的状态. 
这些过程常常陷入一种 _竞态条件_ , 
有时浏览器会先达到正确状态 (一切按预期运行) , 
有时 Selenium 代码会先执行 (一切未按预期运行) . 
这是导致 _不稳定测试_ 的主要原因之一. 


所有导航命令都会等待特定基于 [页面加载策略]({{< ref "drivers/options#pageloadstrategy">}}) 的值 `readyState` 
 (默认等待的值为 `"complete"` ) , 
然后驱动程序才会将控制权交还给代码. 
`readyState`  仅关注 HTML 中定义的资源加载, 
但加载的 JavaScript 资源常常会导致网站发生变化, 
而当代码准备执行下一个 Selenium 命令时, 
需要交互的元素可能尚未出现在页面上. 


同样, 在许多单页应用程序中, 
元素会根据点击操作动态添加到页面上或改变可见性.  
对于 Selenium 能够与之交互, 
该元素必须既存在于页面上又处于[displayed]({{< ref "elements/information/#是否显示">}}) 状态. 


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
隐式等待的值可以通过浏览器选项中的 [timeouts]({{< ref "drivers/options#timeouts">}}) 设置来设定, 
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


{{< tabpane text=true >}}
  {{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/waits/WaitsTest.java#L51" >}}
  {{< /tab >}}
  {{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/waits/test_waits.py#L28" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Waits/WaitsTest.cs#L39" >}}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/waits/waits_spec.rb#L28" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/waits/waits.spec.js#L39" >}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/waits/WaitsTest.kt#L19" >}}
  {{< /tab >}}
{{< /tabpane >}}



## 显式等待

_显式等待_ 是在代码中添加的, 用于轮询应用程序的循环, 
直到特定条件评估为真时, 才退出循环并继续执行代码中的下一个命令. 
如果在指定的超时值之前条件未满足, 
代码将给出超时错误. 
由于应用程序未处于所需状态的方式有很多, 
因此显式等待是为每个需要等待的地方指定确切等待条件的绝佳选择.  
另一个不错的特性是, 默认情况下, 
Selenium 等待类会自动等待指定的元素存在. 



{{< tabpane text=true >}}
  {{% tab header="Java" %}}
This example shows the condition being waited for as a _lambda_. Java also supports
[Expected Conditions]({{< ref "support_features/expected_conditions" >}})
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/waits/WaitsTest.java#L68-L69" >}}
  {{% /tab %}}
  {{% tab header="Python" %}}
This example shows the condition being waited for as a _lambda_. Python also supports
[Expected Conditions]({{< ref "support_features/expected_conditions" >}})
{{< gh-codeblock path="/examples/python/tests/waits/test_waits.py#L42-L43" >}}
  {{% /tab %}}
  {{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Waits/WaitsTest.cs#L56-L57" >}}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/waits/waits_spec.rb#L42-L43" >}}
  {{< /tab >}}
  {{% tab header="JavaScript" %}}
JavaScript also supports [Expected Conditions]({{< ref "support_features/expected_conditions" >}})
{{< gh-codeblock path="/examples/javascript/test/waits/waits.spec.js#L52" >}}
  {{% /tab %}}
  {{< tab header="Kotlin" >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/waits/WaitsTest.kt#L36-L37" >}}
  {{< /tab >}}
{{< /tabpane >}}



### Built-in conditions

Instead of writing a lambda for every wait, most bindings ship a library of ready-made
conditions for the things that are most often waited on.
Conditions that take a locator find the element on every poll,
so they also wait for the element to exist.
Many of them return the element (or elements) once the condition is met,
so the result of the wait can be used directly.

This example locates an element, waits for it to be visible,
and then waits for its text, using only built-in conditions:

{{< tabpane text=true >}}
  {{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/waits/WaitsTest.java#L106-L110" >}}
  {{< /tab >}}
  {{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/waits/test_waits.py#L66-L70" >}}
  {{< /tab >}}
  {{% tab header="CSharp" %}}
.NET does not ship built-in conditions. Pass a lambda to `WebDriverWait.Until()`, as in the
[explicit wait](#explicit-waits) example.
  {{% /tab %}}
  {{% tab header="Ruby" %}}
Ruby does not ship built-in conditions. Pass a block to `Selenium::WebDriver::Wait#until`, as in the
[explicit wait](#explicit-waits) example.
  {{% /tab %}}
  {{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/waits/waits.spec.js#L62-L65" >}}
  {{< /tab >}}
  {{% tab header="Kotlin" %}}
Kotlin uses the Java `ExpectedConditions` class listed in the Java tab below.
  {{% /tab %}}
{{< /tabpane >}}

The complete list of built-in conditions for each binding:

{{< tabpane text=true >}}
{{% tab header="Java" %}}
Class `org.openqa.selenium.support.ui.ExpectedConditions`
([API docs](https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/ui/ExpectedConditions.html)).
Use with `new WebDriverWait(driver, timeout).until(...)`.

| Condition | Waits until |
|-----------|-------------|
| `titleIs(title)` | The page title equals the value |
| `titleContains(title)` | The page title contains the value |
| `urlToBe(url)` | The current URL equals the value |
| `urlContains(fraction)` | The current URL contains the value |
| `urlMatches(regex)` | The current URL matches the regular expression |
| `presenceOfElementLocated(By)` | An element is in the DOM (not necessarily visible); returns it |
| `presenceOfAllElementsLocatedBy(By)` | At least one element is in the DOM; returns them all |
| `presenceOfNestedElementLocatedBy(By or WebElement, By)` | A child element is in the DOM under a parent; returns it |
| `presenceOfNestedElementsLocatedBy(By, By)` | Child elements are in the DOM under a parent; returns them |
| `visibilityOfElementLocated(By)` | An element is in the DOM and displayed; returns it |
| `visibilityOf(WebElement)` | A known element is displayed; returns it |
| `visibilityOfAllElementsLocatedBy(By)` | All matching elements are displayed; returns them |
| `visibilityOfAllElements(WebElement...)` | All given elements are displayed; returns them |
| `visibilityOfNestedElementsLocatedBy(By or WebElement, By)` | Child elements under a parent are displayed; returns them |
| `invisibilityOfElementLocated(By)` | An element is hidden or not in the DOM |
| `invisibilityOf(WebElement)` | A known element is hidden or no longer in the DOM |
| `invisibilityOfAllElements(WebElement...)` | All given elements are hidden |
| `invisibilityOfElementWithText(By, text)` | An element with the given text is hidden or not in the DOM |
| `elementToBeClickable(By or WebElement)` | An element is displayed and enabled; returns it |
| `elementToBeSelected(By or WebElement)` | An element is selected |
| `elementSelectionStateToBe(By or WebElement, selected)` | An element's selected state matches the value |
| `stalenessOf(WebElement)` | An element is no longer attached to the DOM |
| `textToBe(By, text)` | An element's text equals the value |
| `textToBePresentInElement(WebElement, text)` | An element's text contains the value |
| `textToBePresentInElementLocated(By, text)` | A located element's text contains the value |
| `textToBePresentInElementValue(By or WebElement, text)` | An element's `value` contains the value |
| `textMatches(By, Pattern)` | An element's text matches the regular expression |
| `attributeToBe(By or WebElement, attribute, value)` | An element's attribute or CSS property equals the value |
| `attributeContains(By or WebElement, attribute, value)` | An element's attribute or CSS property contains the value |
| `attributeToBeNotEmpty(WebElement, attribute)` | An element's attribute or CSS property is not empty |
| `domAttributeToBe(WebElement, attribute, value)` | An element's DOM attribute equals the value |
| `domPropertyToBe(WebElement, property, value)` | An element's DOM property equals the value |
| `numberOfElementsToBe(By, number)` | The number of matching elements equals the value; returns them |
| `numberOfElementsToBeMoreThan(By, number)` | The number of matching elements is more than the value; returns them |
| `numberOfElementsToBeLessThan(By, number)` | The number of matching elements is less than the value; returns them |
| `numberOfWindowsToBe(number)` | The number of open windows or tabs equals the value |
| `frameToBeAvailableAndSwitchToIt(By, String, int or WebElement)` | A frame is available, then switches to it |
| `alertIsPresent()` | An alert is open; returns it |
| `jsReturnsValue(script)` | The JavaScript returns a value; returns it |
| `javaScriptThrowsNoExceptions(script)` | The JavaScript runs without throwing |
| `and(conditions...)` | All of the conditions are true |
| `or(conditions...)` | Any of the conditions is true |
| `not(condition)` | The condition is false |
| `refreshed(condition)` | The condition is true, retrying it after a stale element error |
{{% /tab %}}
{{% tab header="Python" %}}
Module `selenium.webdriver.support.expected_conditions`
([API docs](https://www.selenium.dev/selenium/docs/api/py/webdriver_support/selenium.webdriver.support.expected_conditions.html)),
usually imported as `EC`.
Use with `WebDriverWait(driver, timeout).until(...)`.
Locators are tuples, such as `(By.ID, "message")`.

| Condition | Waits until |
|-----------|-------------|
| `title_is(title)` | The page title equals the value |
| `title_contains(title)` | The page title contains the value |
| `url_to_be(url)` | The current URL equals the value |
| `url_contains(url)` | The current URL contains the value |
| `url_matches(pattern)` | The current URL matches the regular expression |
| `url_changes(url)` | The current URL is different from the value |
| `presence_of_element_located(locator)` | An element is in the DOM (not necessarily visible); returns it |
| `presence_of_all_elements_located(locator)` | At least one element is in the DOM; returns them all |
| `visibility_of_element_located(locator)` | An element is in the DOM and displayed; returns it |
| `visibility_of(element)` | A known element is displayed; returns it |
| `visibility_of_any_elements_located(locator)` | At least one matching element is displayed; returns the displayed ones |
| `visibility_of_all_elements_located(locator)` | All matching elements are displayed; returns them |
| `invisibility_of_element_located(locator or element)` | An element is hidden or not in the DOM |
| `invisibility_of_element(element or locator)` | An element is hidden or not in the DOM |
| `element_to_be_clickable(locator or element)` | An element is displayed and enabled; returns it |
| `element_to_be_selected(element)` | A known element is selected |
| `element_located_to_be_selected(locator)` | A located element is selected |
| `element_selection_state_to_be(element, is_selected)` | A known element's selected state matches the value |
| `element_located_selection_state_to_be(locator, is_selected)` | A located element's selected state matches the value |
| `staleness_of(element)` | An element is no longer attached to the DOM |
| `text_to_be_present_in_element(locator, text)` | An element's text contains the value |
| `text_to_be_present_in_element_value(locator, text)` | An element's `value` contains the value |
| `text_to_be_present_in_element_attribute(locator, attribute, text)` | An element's attribute contains the value |
| `element_attribute_to_include(locator, attribute)` | An element has the attribute |
| `number_of_windows_to_be(num_windows)` | The number of open windows or tabs equals the value |
| `new_window_is_opened(current_handles)` | A window or tab has opened since the handles were read |
| `frame_to_be_available_and_switch_to_it(locator, name or element)` | A frame is available, then switches to it |
| `alert_is_present()` | An alert is open; returns it |
| `all_of(*conditions)` | All of the conditions are true |
| `any_of(*conditions)` | Any of the conditions is true |
| `none_of(*conditions)` | None of the conditions is true |
{{% /tab %}}
{{% tab header="CSharp" %}}
.NET does not ship built-in conditions.
The `ExpectedConditions` class was removed from the bindings in Selenium 4.
Pass a lambda to `WebDriverWait.Until()` that returns a non-null value or `true`
when the condition is met.
{{% /tab %}}
{{% tab header="Ruby" %}}
Ruby does not ship built-in conditions.
Pass a block to `Selenium::WebDriver::Wait#until` that returns a truthy value
when the condition is met.
{{% /tab %}}
{{% tab header="JavaScript" %}}
Module `until` from `selenium-webdriver`
([API docs](https://www.selenium.dev/selenium/docs/api/javascript/index.html)).
Use with `await driver.wait(until.condition(...), timeout)`.
Conditions that check an element resolve to that element once they are met.

| Condition | Waits until |
|-----------|-------------|
| `titleIs(title)` | The page title equals the value |
| `titleContains(substr)` | The page title contains the value |
| `titleMatches(regex)` | The page title matches the regular expression |
| `urlIs(url)` | The current URL equals the value |
| `urlContains(substrUrl)` | The current URL contains the value |
| `urlMatches(regex)` | The current URL matches the regular expression |
| `elementLocated(locator)` | An element is in the DOM; returns it |
| `elementsLocated(locator)` | At least one element is in the DOM; returns them all |
| `elementIsVisible(element)` | An element is displayed |
| `elementIsNotVisible(element)` | An element is not displayed |
| `elementIsEnabled(element)` | An element is enabled |
| `elementIsDisabled(element)` | An element is disabled |
| `elementIsSelected(element)` | An element is selected |
| `elementIsNotSelected(element)` | An element is not selected |
| `elementTextIs(element, text)` | An element's text equals the value |
| `elementTextContains(element, substr)` | An element's text contains the value |
| `elementTextMatches(element, regex)` | An element's text matches the regular expression |
| `stalenessOf(element)` | An element is no longer attached to the DOM |
| `ableToSwitchToFrame(frame)` | A frame is available, then switches to it |
| `alertIsPresent()` | An alert is open; returns it |
{{% /tab %}}
{{% tab header="Kotlin" %}}
Kotlin uses the Java `ExpectedConditions` class. See the Java tab.
{{% /tab %}}
{{< /tabpane >}}

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



{{< tabpane text=true >}}
  {{% tab header="Java" %}}
The easiest way to customize Waits in Java is to use the `FluentWait` class: 
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/waits/WaitsTest.java#L83-L93" >}}
  {{% /tab %}}
  {{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/waits/test_waits.py#L54-L56" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Waits/WaitsTest.cs#L70-L79" >}}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/waits/waits_spec.rb#L54-L60" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
{{< badge-code >}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/waits/WaitsTest.kt#L51-L60" >}}
  {{< /tab >}}
{{< /tabpane >}}

