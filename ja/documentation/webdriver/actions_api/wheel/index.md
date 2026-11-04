# Scroll wheel actions

> A representation of a scroll wheel input device for interacting with a web page.

URL: https://www.selenium.dev/ja/documentation/webdriver/actions_api/wheel/

---

_Requires [Selenium v4.2](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.2.0) or later._

_Only works in Chromium ([browser support](https://wpt.fyi/results/webdriver/tests/perform_actions/wheel.py))._

There are 5 scenarios for scrolling on a page.

## Scroll to element

This is the most common scenario. Unlike traditional click and send keys methods,
the actions class does not automatically scroll the target element into view,
so this method will need to be used if elements are not already inside the viewport.

This method takes a web element as the sole argument.

Regardless of whether the element is above or below the current viewscreen,
the viewport will be scrolled so the bottom of the element is at the bottom of the screen.

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

## Scroll by given amount

This is the second most common scenario for scrolling. Pass in an delta x and a delta y value for how much to scroll
in the right and down directions. Negative values represent left and up, respectively.

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

## Scroll from an element by a given amount

This scenario is effectively a combination of the above two methods.

To execute this use the "Scroll From" method, which takes 3 arguments.
The first represents the origination point, which we designate as the element,
and the second two are the delta x and delta y values.

If the element is out of the viewport,
it will be scrolled to the bottom of the screen, then the page will be scrolled by the provided
delta x and delta y values.

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

## Scroll from an element with an offset

This scenario is used when you need to scroll only a portion of the screen, and it is outside the viewport.
Or is inside the viewport and the portion of the screen that must be scrolled
is a known offset away from a specific element.

This uses the "Scroll From" method again, and in addition to specifying the element,
an offset is specified to indicate the origin point of the scroll. The offset is
calculated from the center of the provided element.

If the element is out of the viewport,
it first will be scrolled to the bottom of the screen, then the origin of the scroll will be determined
by adding the offset to the coordinates of the center of the element, and finally
the page will be scrolled by the provided delta x and delta y values.

Note that if the offset from the center of the element falls outside of the viewport,
it will result in an exception.

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
footer = driver.find_element(tag_name: 'footer')
scroll_origin = Selenium::WebDriver::WheelActions::ScrollOrigin.element(footer, 0, -50)
driver.action
      .scroll_from(scroll_origin, 0, 200)
      .perform
```

**JavaScript**

```javascript
const footer = await driver.findElement(By.css("footer"))

await driver.actions()
  .scroll(0, -50, 0, 200, footer)
  .perform()
```

**Kotlin**

```kotlin
val footer = driver.findElement(By.tagName("footer"))
val scrollOrigin = WheelInput.ScrollOrigin.fromElement(footer, 0, -50)
Actions(driver)
        .scrollFromOrigin(scrollOrigin,0, 200)
        .perform()
```

## Scroll from a offset of origin (element) by given amount

The final scenario is used when you need to scroll only a portion of the screen,
and it is already inside the viewport.

This uses the "Scroll From" method again, but the viewport is designated instead
of an element. An offset is specified from the upper left corner of the
current viewport. After the origin point is determined,
the page will be scrolled by the provided delta x and delta y values.

Note that if the offset from the upper left corner of the viewport falls outside of the screen,
it will result in an exception.

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
