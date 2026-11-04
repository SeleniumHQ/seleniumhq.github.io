# 滚轮操作

> 一种用于与网页交互的滚轮输入设备的示意.

URL: https://www.selenium.dev/zh-cn/documentation/webdriver/actions_api/wheel/

---

_Requires [Selenium v4.2](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.2.0) or later._

_Only works in Chromium ([browser support](https://wpt.fyi/results/webdriver/tests/perform_actions/wheel.py))._

在页面上滚动有 5 种情况.

## 滚动到元素

这是最常见的场景. 
与传统的点击和发送按键的方法不同, 
动作类不会自动将目标元素滚动到视图中, 
因此如果元素不在视口内, 
就需要使用此方法.

此方法仅将一个网页元素作为参数.

无论该元素是在当前视图屏幕的上方还是下方, 
视口都会滚动, 使该元素的底部位于屏幕底部.

**Java**

```java
WebElement iframe = driver.findElement(By.tagName("iframe"));
new Actions(driver)
        .scrollToElement(iframe)
        .perform();
```

**Python**

```python
iframe = driver.find_element(By.TAG_NAME, "iframe")
ActionChains(driver)\
    .scroll_to_element(iframe)\
    .perform()
```

**CSharp**

```csharp
IWebElement iframe = driver.FindElement(By.TagName("iframe"));
new Actions(driver)
    .ScrollToElement(iframe)
    .Perform();
```

**Ruby**

```ruby
iframe = driver.find_element(tag_name: 'iframe')
driver.action
      .scroll_to(iframe)
      .perform
```

**JavaScript**

```javascript
const iframe = await driver.findElement(By.css("iframe"))
await driver.actions()
  .scroll(0, 0, 0, 0, iframe)
  .perform()
```

**Kotlin**

```kotlin
val iframe = driver.findElement(By.tagName("iframe"))
Actions(driver)
        .scrollToElement(iframe)
        .perform()
```

## 按给定的量进行滚动

这是第二种最常见的滚动场景. 
传入一个 x 方向的偏移量和一个 y 方向的偏移量, 
表示向右和向下滚动的距离. 负值分别表示向左和向上滚动.

**Java**

```java
WebElement footer = driver.findElement(By.tagName("footer"));
int deltaY = footer.getRect().y;
new Actions(driver)
        .scrollByAmount(0, deltaY)
        .perform();
```

**Python**

```python
footer = driver.find_element(By.TAG_NAME, "footer")
delta_y = footer.rect['y']
ActionChains(driver)\
    .scroll_by_amount(0, delta_y)\
    .perform()
```

**CSharp**

```csharp
IWebElement footer = driver.FindElement(By.TagName("footer"));
int deltaY = footer.Location.Y;
new Actions(driver)
    .ScrollByAmount(0, deltaY)
    .Perform();
```

**Ruby**

```ruby
footer = driver.find_element(tag_name: 'footer')
delta_y = footer.rect.y
driver.action
      .scroll_by(0, delta_y)
      .perform
```

**JavaScript**

```javascript
const footer = await driver.findElement(By.css("footer"))
const deltaY = (await footer.getRect()).y

await driver.actions()
  .scroll(0, 0, 0, deltaY)
  .perform()
```

**Kotlin**

```kotlin
val footer = driver.findElement(By.tagName("footer"))
val deltaY = footer.getRect().y
Actions(driver)
        .scrollByAmount(0, deltaY)
        .perform()
```

## 将元素按照给定的量进行滚动

这种情形实际上是上述两种方法的结合.

要执行此操作, 请使用“从...滚动”方法, 
该方法需要 3 个参数.  
第一个参数表示起始点, 我们将其指定为元素,  
后两个参数分别是 x 偏移量和 y 偏移量的值.

如果元素不在视口内, 它将滚动到屏幕底部, 
然后页面将根据提供的 x 和 y 偏移量进行滚动.

**Java**

```java
WebElement iframe = driver.findElement(By.tagName("iframe"));
WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromElement(iframe);
new Actions(driver)
        .scrollFromOrigin(scrollOrigin, 0, 200)
        .perform();
```

**Python**

```python
iframe = driver.find_element(By.TAG_NAME, "iframe")
scroll_origin = ScrollOrigin.from_element(iframe)
ActionChains(driver)\
    .scroll_from_origin(scroll_origin, 0, 200)\
    .perform()
```

**CSharp**

```csharp
IWebElement iframe = driver.FindElement(By.TagName("iframe"));
WheelInputDevice.ScrollOrigin scrollOrigin = new WheelInputDevice.ScrollOrigin
{
    Element = iframe
};
new Actions(driver)
    .ScrollFromOrigin(scrollOrigin, 0, 200)
    .Perform();
```

**Ruby**

```ruby
iframe = driver.find_element(tag_name: 'iframe')
scroll_origin = Selenium::WebDriver::WheelActions::ScrollOrigin.element(iframe)
driver.action
      .scroll_from(scroll_origin, 0, 200)
      .perform
```

**JavaScript**

```javascript
const iframe = await driver.findElement(By.css("iframe"))

await driver.actions()
  .scroll(0, 0, 0, 200, iframe)
  .perform()
```

**Kotlin**

```kotlin
val iframe = driver.findElement(By.tagName("iframe"))
val scrollOrigin = WheelInput.ScrollOrigin.fromElement(iframe)
Actions(driver)
        .scrollFromOrigin(scrollOrigin, 0, 200)
        .perform()
```

## 从具有偏移量的元素进行滚动

当您需要滚动屏幕的某一部分, 而该部分位于视口之外, 
或者位于视口之内但必须滚动的屏幕部分与特定元素之间存在已知偏移量时, 
会使用此场景.

这再次使用了“从...滚动”的方法, 并且除了指定元素之外, 
还指定了一个偏移量来表明滚动的起始点. 
该偏移量是从所提供的元素的中心计算得出的.

如果元素不在视口内, 它首先会被滚动到屏幕底部, 
然后滚动的原点将通过将偏移量添加到元素中心的坐标来确定, 
最后页面将根据提供的 x 和 y 偏移量值进行滚动.

请注意, 如果元素中心的偏移量超出视口范围, 
将会导致异常.

**Java**

```java
WebElement footer = driver.findElement(By.tagName("footer"));
WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromElement(footer, 0, -50);
new Actions(driver)
        .scrollFromOrigin(scrollOrigin,0, 200)
        .perform();
```

**Python**

```python
footer = driver.find_element(By.TAG_NAME, "footer")
scroll_origin = ScrollOrigin.from_element(footer, 0, -50)
ActionChains(driver)\
    .scroll_from_origin(scroll_origin, 0, 200)\
    .perform()
```

**CSharp**

```csharp
IWebElement footer = driver.FindElement(By.TagName("footer"));
var scrollOrigin = new WheelInputDevice.ScrollOrigin
{
    Element = footer,
    XOffset = 0,
    YOffset = -50
};
new Actions(driver)
    .ScrollFromOrigin(scrollOrigin, 0, 200)
    .Perform();
```

**Ruby**

```ruby
  expect(in_viewport?(checkbox)).to eq true
end

it 'scrolls by given amount with offset' do
  driver.get('https://selenium.dev/selenium/web/scrolling_tests/frame_with_nested_scrolling_frame.html')
```

**JavaScript**

```javascript

await driver.sleep(500)

await driver.switchTo().frame(iframe)
const checkbox = await driver.findElement(By.name('scroll_checkbox'))
```

**Kotlin**

```kotlin
val footer = driver.findElement(By.tagName("footer"))
val scrollOrigin = WheelInput.ScrollOrigin.fromElement(footer, 0, -50)
Actions(driver)
        .scrollFromOrigin(scrollOrigin,0, 200)
        .perform()
```

## 从给定元素的原点偏移量处滚动指定的距离

最后一种情况适用于您需要滚动屏幕的某一部分, 
而该部分已处于视口内的情况.

这再次使用了“从...滚动”的方法, 但这次指定的是视口而非某个元素. 
从当前视口的左上角开始指定偏移量. 
确定原点后, 页面将根据提供的 x 偏移量和 y 偏移量进行滚动.

请注意, 如果从视口左上角算起的偏移量超出屏幕范围, 
将会导致异常.

**Java**

```java
WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromViewport(10, 10);
new Actions(driver)
        .scrollFromOrigin(scrollOrigin, 0, 200)
        .perform();
```

**Python**

```python
scroll_origin = ScrollOrigin.from_viewport(10, 10)

ActionChains(driver)\
    .scroll_from_origin(scroll_origin, 0, 200)\
    .perform()
```

**CSharp**

```csharp
var scrollOrigin = new WheelInputDevice.ScrollOrigin
{
    Viewport = true,
    XOffset = 10,
    YOffset = 10
};
new Actions(driver)
    .ScrollFromOrigin(scrollOrigin, 0, 200)
    .Perform();
```

**Ruby**

```ruby
scroll_origin = Selenium::WebDriver::WheelActions::ScrollOrigin.viewport(10, 10)
driver.action
      .scroll_from(scroll_origin, 0, 200)
      .perform
```

**JavaScript**

```javascript
await driver.actions()
  .scroll(10, 10, 0, 200)
  .perform()
```

**Kotlin**

```kotlin
val scrollOrigin = WheelInput.ScrollOrigin.fromViewport(10, 10)
Actions(driver)
        .scrollFromOrigin(scrollOrigin, 0, 200)
        .perform()
```
