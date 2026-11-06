# 鼠标操作

> 任何用于与网页进行交互的指针设备的表示形式.

URL: https://www.selenium.dev/zh-cn/documentation/webdriver/actions_api/mouse/

---

一个鼠标仅可以完成3个操作：
按住按钮，松开按钮，还有移动光标。
Selenium组合了常见的操作并提供了方便的方法。

## 按住鼠标左键

这个方法包含2个操作，首先将光标移动到被操作元素的正中心，然后按下鼠标左键不松开。
这对于聚焦一个特殊元素很有用：

**Java**

```java
WebElement clickable = driver.findElement(By.id("clickable"));
new Actions(driver)
        .clickAndHold(clickable)
        .perform();
```

**Python**

```python
clickable = driver.find_element(By.ID, "clickable")
ActionChains(driver) \
    .click_and_hold(clickable) \
    .perform()

WebDriverWait(driver, 10).until(EC.text_to_be_present_in_element((By.ID, "click-status"), "focused"))
```

**CSharp**

```csharp
IWebElement clickable = driver.FindElement(By.Id("clickable"));
new Actions(driver)
    .ClickAndHold(clickable)
    .Perform();
```

**Ruby**

```ruby
clickable = driver.find_element(id: 'clickable')
driver.action
      .click_and_hold(clickable)
      .perform
```

**JavaScript**

```javascript
let clickable = driver.findElement(By.id("clickable"));
const actions = driver.actions({async: true});
await actions.move({origin: clickable}).press().perform();
```

**Kotlin**

```kotlin
        .clickAndHold(clickable)
        .perform()

Assertions.assertEquals("focused", driver.findElement(By.id("click-status")).getText())
```

## 点击鼠标左键

这个方法包含2个操作，首先将光标移动到被操作元素的正中心，然后按下鼠标左键后再松开。
另一种叫法“点击”：

**Java**

```java
WebElement clickable = driver.findElement(By.id("click"));
new Actions(driver)
        .click(clickable)
        .perform();
```

**Python**

```python
clickable = driver.find_element(By.ID, "click")
ActionChains(driver) \
    .click(clickable) \
    .perform()
```

**CSharp**

```csharp
IWebElement clickable = driver.FindElement(By.Id("click"));
new Actions(driver)
    .Click(clickable)
    .Perform();
```

**Ruby**

```ruby
clickable = driver.find_element(id: 'click')
driver.action
      .click(clickable)
      .perform
```

**JavaScript**

```javascript
let click = driver.findElement(By.id("click"));
const actions = driver.actions({async: true});
await actions.move({origin: click}).click().perform();
```

**Kotlin**

```kotlin
        .click(clickable)
        .perform()

Assertions.assertTrue(driver.getCurrentUrl()!!.contains("resultPage.html"))
```

## 点击鼠标备用按钮

鼠标一共有5个定义好的按钮:
* 0 — 左键 (默认值)
* 1 — 中间键 (当前不支持)
* 2 — 右键
* 3 — X1 (返回) 按钮
* 4 — X2 (前进) 按钮

### 点击鼠标右键

这个方法包含2个操作，首先将光标移动到被操作元素的正中心，然后点击鼠标右键。
另一种叫法“点击右键”：

**Java**

```java
WebElement clickable = driver.findElement(By.id("clickable"));
new Actions(driver)
        .contextClick(clickable)
        .perform();
```

**Python**

```python
clickable = driver.find_element(By.ID, "clickable")
ActionChains(driver) \
    .context_click(clickable) \
    .perform()

WebDriverWait(driver, 10).until(EC.text_to_be_present_in_element((By.ID, "click-status"), "context-clicked"))
```

**CSharp**

```csharp
IWebElement clickable = driver.FindElement(By.Id("clickable"));
new Actions(driver)
    .ContextClick(clickable)
    .Perform();
```

**Ruby**

```ruby
clickable = driver.find_element(id: 'clickable')
driver.action
      .context_click(clickable)
      .perform
```

**JavaScript**

```javascript
const clickable = driver.findElement(By.id("clickable"));
const actions = driver.actions({async: true});
await actions.contextClick(clickable).perform();
```

**Kotlin**

```kotlin
        .contextClick(clickable)
        .perform()

Assertions.assertEquals("context-clicked", driver.findElement(By.id("click-status")).getText())
```

### 点击鼠标回退键

除了这个没有更方便的方法，只是点击鼠标回退按钮

**Java**

```java
PointerInput mouse = new PointerInput(PointerInput.Kind.MOUSE, "default mouse");

Sequence actions = new Sequence(mouse, 0)
        .addAction(mouse.createPointerDown(PointerInput.MouseButton.BACK.asArg()))
        .addAction(mouse.createPointerUp(PointerInput.MouseButton.BACK.asArg()));

((RemoteWebDriver) driver).perform(Collections.singletonList(actions));
```

**Python**

_Requires [Selenium v4.2](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.2.0) or later._

```python
action = ActionBuilder(driver)
action.pointer_action.pointer_down(MouseButton.BACK)
action.pointer_action.pointer_up(MouseButton.BACK)
action.perform()
```

**CSharp**

_Requires [Selenium v4.2](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.2.0) or later._

```csharp
ActionBuilder actionBuilder = new ActionBuilder();
PointerInputDevice mouse = new PointerInputDevice(PointerKind.Mouse, "default mouse");
actionBuilder.AddAction(mouse.CreatePointerDown(MouseButton.Back));
actionBuilder.AddAction(mouse.CreatePointerUp(MouseButton.Back));
((IActionExecutor)driver).PerformActions(actionBuilder.ToActionSequenceList());
```

**Ruby**

_Requires [Selenium v4.2](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.2.0) or later._

```ruby
driver.action
      .pointer_down(:back)
      .pointer_up(:back)
      .perform
```

**JavaScript**

_Requires [Selenium v4.5.0](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.5.0.0) or later._

```javascript
const actions = driver.actions({async: true});
await actions.press(Button.BACK).release(Button.BACK).perform()
```

**Kotlin**

```kotlin
val actions = Sequence(mouse, 0)
        .addAction(mouse.createPointerDown(PointerInput.MouseButton.BACK.asArg()))
        .addAction(mouse.createPointerUp(PointerInput.MouseButton.BACK.asArg()))

(driver as RemoteWebDriver).perform(Collections.singletonList(actions))

Assertions.assertEquals("BasicMouseInterfaceTest", driver.getTitle())
```

### 点击鼠标前进键

除了这个没有更方便的方法，只是点击鼠标前进按钮

**Java**

```java
PointerInput mouse = new PointerInput(PointerInput.Kind.MOUSE, "default mouse");

Sequence actions = new Sequence(mouse, 0)
        .addAction(mouse.createPointerDown(PointerInput.MouseButton.FORWARD.asArg()))
        .addAction(mouse.createPointerUp(PointerInput.MouseButton.FORWARD.asArg()));

((RemoteWebDriver) driver).perform(Collections.singletonList(actions));
```

**Python**

_Requires [Selenium v4.2](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.2.0) or later._

```python
action = ActionBuilder(driver)
action.pointer_action.pointer_down(MouseButton.FORWARD)
action.pointer_action.pointer_up(MouseButton.FORWARD)
action.perform()
```

**CSharp**

_Requires [Selenium v4.2](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.2.0) or later._

```csharp
ActionBuilder actionBuilder = new ActionBuilder();
PointerInputDevice mouse = new PointerInputDevice(PointerKind.Mouse, "default mouse");
actionBuilder.AddAction(mouse.CreatePointerDown(MouseButton.Forward));
actionBuilder.AddAction(mouse.CreatePointerUp(MouseButton.Forward));
((IActionExecutor)driver).PerformActions(actionBuilder.ToActionSequenceList());
```

**Ruby**

_Requires [Selenium v4.2](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.2.0) or later._

```ruby
driver.action
      .pointer_down(:forward)
      .pointer_up(:forward)
      .perform
```

**JavaScript**

_Requires [Selenium v4.5.0](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.5.0.0) or later._

```javascript
const actions = driver.actions({async: true});
await actions.press(Button.FORWARD).release(Button.FORWARD).perform()
```

**Kotlin**

```kotlin
val actions = Sequence(mouse, 0)
        .addAction(mouse.createPointerDown(PointerInput.MouseButton.FORWARD.asArg()))
        .addAction(mouse.createPointerUp(PointerInput.MouseButton.FORWARD.asArg()))

(driver as RemoteWebDriver).perform(Collections.singletonList(actions))

Assertions.assertEquals("We Arrive Here", driver.getTitle())
```

## 双击鼠标左键

这个方法包含2个操作，首先将光标移动到被操作元素的正中心，然后双击鼠标左键。

**Java**

```java
WebElement clickable = driver.findElement(By.id("clickable"));
new Actions(driver)
        .doubleClick(clickable)
        .perform();
```

**Python**

```python
clickable = driver.find_element(By.ID, "clickable")
ActionChains(driver) \
    .double_click(clickable) \
    .perform()

WebDriverWait(driver, 10).until(EC.text_to_be_present_in_element((By.ID, "click-status"), "double-clicked"))
```

**CSharp**

```csharp
IWebElement clickable = driver.FindElement(By.Id("clickable"));
new Actions(driver)
    .DoubleClick(clickable)
    .Perform();
```

**Ruby**

```ruby
clickable = driver.find_element(id: 'clickable')
driver.action
      .double_click(clickable)
      .perform
```

**JavaScript**

```javascript
const clickable = driver.findElement(By.id("clickable"));
const actions = driver.actions({async: true});
await actions.doubleClick(clickable).perform();
```

**Kotlin**

```kotlin
        .doubleClick(clickable)
        .perform()

Assertions.assertEquals("double-clicked", driver.findElement(By.id("click-status")).getText())
```

## 移动光标到元素上

这个方法是将光标移动到元素的中心点。
另一种叫法“悬停”。
元素必须在可视窗口范围内否则这条命令将会报错。

**Java**

```java
WebElement hoverable = driver.findElement(By.id("hover"));
new Actions(driver)
        .moveToElement(hoverable)
        .perform();
```

**Python**

```python
hoverable = driver.find_element(By.ID, "hover")
ActionChains(driver) \
    .move_to_element(hoverable) \
    .perform()

WebDriverWait(driver, 10).until(EC.text_to_be_present_in_element((By.ID, "move-status"), "hovered"))
```

**CSharp**

```csharp
IWebElement hoverable = driver.FindElement(By.Id("hover"));
new Actions(driver)
    .MoveToElement(hoverable)
    .Perform();
```

**Ruby**

```ruby
hoverable = driver.find_element(id: 'hover')
driver.action
      .move_to(hoverable)
      .perform
```

**JavaScript**

```javascript
const hoverable = driver.findElement(By.id("hover"));
const actions = driver.actions({async: true});
await actions.move({origin: hoverable}).perform();
```

**Kotlin**

```kotlin
        .moveToElement(hoverable)
        .perform()

Assertions.assertEquals("hovered", driver.findElement(By.id("move-status")).getText())
```

## 通过偏移量移动动光标

这些方法让光标先移动到指定的坐标原点，然后通过单位为px的偏移量进行光标相对原点的偏移移动。
注意光标位置必须在可视窗口区域否则会报错。

### 从元素中心点（原点）偏移

这个方法是指先将光标移动到元素中心点（原点），然后通过偏移量进行光标相对原点的偏移。

**Java**

```java
WebElement tracker = driver.findElement(By.id("mouse-tracker"));
new Actions(driver)
        .moveToElement(tracker, 8, 0)
        .perform();
```

**Python**

```python
mouse_tracker = driver.find_element(By.ID, "mouse-tracker")
ActionChains(driver) \
    .move_to_element_with_offset(mouse_tracker, 8, 0) \
    .perform()

location = driver.find_element(By.ID, "relative-location")
WebDriverWait(driver, 10).until(lambda _: location.text != "")
```

**CSharp**

```csharp
IWebElement tracker = driver.FindElement(By.Id("mouse-tracker"));
new Actions(driver)
    .MoveToElement(tracker, 8, 0)
    .Perform();
```

**Ruby**

```ruby
mouse_tracker = driver.find_element(id: 'mouse-tracker')
driver.action
      .move_to(mouse_tracker, 8, 11)
      .perform
```

**JavaScript**

```javascript
const mouseTracker = driver.findElement(By.id("mouse-tracker"));
const actions = driver.actions({async: true});
await actions.move({x: 8, y: 0, origin: mouseTracker}).perform();
```

**Kotlin**

```kotlin
        .moveToElement(tracker, 8, 0)
        .perform()

val result = driver.findElement(By.id("relative-location")).getText().split(", ")
```

### 从视窗左上角（原点）偏移

这个方法是指先将光标移动到视窗左上角（原点），然后通过偏移量进行光标相对原点的偏移。

**Java**

```java
PointerInput mouse = new PointerInput(PointerInput.Kind.MOUSE, "default mouse");

Sequence actions = new Sequence(mouse, 0)
        .addAction(mouse.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 8, 12));

((RemoteWebDriver) driver).perform(Collections.singletonList(actions));
```

**Python**

```python
action = ActionBuilder(driver)
action.pointer_action.move_to_location(8, 0)
action.perform()

location = driver.find_element(By.ID, "absolute-location")
WebDriverWait(driver, 10).until(lambda _: location.text != "")
```

**CSharp**

```csharp
ActionBuilder actionBuilder = new ActionBuilder();
PointerInputDevice mouse = new PointerInputDevice(PointerKind.Mouse, "default mouse");
actionBuilder.AddAction(mouse.CreatePointerMove(CoordinateOrigin.Viewport,
    8, 0, TimeSpan.Zero));
((IActionExecutor)driver).PerformActions(actionBuilder.ToActionSequenceList());
```

**Ruby**

```ruby
driver.action
      .move_to_location(8, 12)
      .perform
```

**JavaScript**

```javascript
const actions = driver.actions({async: true});
await actions.move({x: 8, y: 0}).perform();
```

**Kotlin**

```kotlin
val actions = Sequence(mouse, 0)
        .addAction(mouse.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 8, 12))

(driver as RemoteWebDriver).perform(Collections.singletonList(actions))

val result = driver.findElement(By.id("absolute-location")).getText().split(", ")
```

### 从当前光标位置（原点）偏移

这个方法是指光标位于当前位置（原点），然后通过偏移量进行光标相对原点的偏移。
如果之前没有移动过光标位置，则这个位置是视窗左上角（原点）。
注意当页面发生滚动后光标位置不会发生变化。

注意第一个参数指定为正数时向右移动，第二个参数指定为正数时向下移动。所以 `moveByOffset(30, -10)` 是指从当前光标位置向右移动30个像素位置和向上移动10个像素位置。

**Java**

```java
new Actions(driver)
        .moveByOffset(13, 15)
        .perform();
```

**Python**

```python
ActionChains(driver) \
    .move_by_offset(13, 15) \
    .perform()

WebDriverWait(driver, 10).until(lambda _: location.text != start)
```

**CSharp**

```csharp
new Actions(driver)
    .MoveByOffset(13, 15)
    .Perform();
```

**Ruby**

```ruby
driver.action
      .move_by(13, 15)
      .perform
```

**JavaScript**

```javascript
await actions.move({x: 13, y: 15, origin: Origin.POINTER}).perform()
```

**Kotlin**

```kotlin
        .perform()

val result = driver.findElement(By.id("absolute-location")).getText().split(", ")
```

## 拖放元素

这个方法首先在原元素上提交执行按下鼠标左键，移动到目标元素位置后是释放鼠标左键。

**Java**

```java
WebElement draggable = driver.findElement(By.id("draggable"));
WebElement droppable = driver.findElement(By.id("droppable"));
new Actions(driver)
        .dragAndDrop(draggable, droppable)
        .perform();
```

**Python**

```python
draggable = driver.find_element(By.ID, "draggable")
droppable = driver.find_element(By.ID, "droppable")
ActionChains(driver) \
    .drag_and_drop(draggable, droppable) \
    .perform()

WebDriverWait(driver, 10).until(EC.text_to_be_present_in_element((By.ID, "drop-status"), "dropped"))
```

**CSharp**

```csharp
IWebElement draggable = driver.FindElement(By.Id("draggable"));
IWebElement droppable = driver.FindElement(By.Id("droppable"));
new Actions(driver)
    .DragAndDrop(draggable, droppable)
    .Perform();
```

**Ruby**

```ruby
draggable = driver.find_element(id: 'draggable')
droppable = driver.find_element(id: 'droppable')
driver.action
      .drag_and_drop(draggable, droppable)
      .perform
```

**JavaScript**

```javascript
const draggable = driver.findElement(By.id("draggable"));
const droppable = await driver.findElement(By.id("droppable"));
const actions = driver.actions({async: true});
await actions.dragAndDrop(draggable, droppable).perform();
```

**Kotlin**

```kotlin
Actions(driver)
        .dragAndDrop(draggable, droppable)
        .perform()

Assertions.assertEquals("dropped", driver.findElement(By.id("drop-status")).getText())
```

## 通过偏移量拖放元素
这个方法首先在原元素上提交执行按下鼠标左键，通过给出的偏移量移动元素后释放鼠标左键。

**Java**

```java
WebElement draggable = driver.findElement(By.id("draggable"));
Rectangle start = draggable.getRect();
Rectangle finish = driver.findElement(By.id("droppable")).getRect();
new Actions(driver)
        .dragAndDropBy(draggable, finish.getX() - start.getX(), finish.getY() - start.getY())
        .perform();
```

**Python**

```python
draggable = driver.find_element(By.ID, "draggable")
start = draggable.location
finish = driver.find_element(By.ID, "droppable").location
ActionChains(driver) \
    .drag_and_drop_by_offset(draggable, finish['x'] - start['x'], finish['y'] - start['y']) \
    .perform()

WebDriverWait(driver, 10).until(EC.text_to_be_present_in_element((By.ID, "drop-status"), "dropped"))
```

**CSharp**

```csharp
IWebElement draggable = driver.FindElement(By.Id("draggable"));
Point start = draggable.Location;
Point finish = driver.FindElement(By.Id("droppable")).Location;
new Actions(driver)
    .DragAndDropToOffset(draggable, finish.X - start.X, finish.Y - start.Y)
    .Perform();
```

**Ruby**

```ruby
draggable = driver.find_element(id: 'draggable')
start = draggable.rect
finish = driver.find_element(id: 'droppable').rect
driver.action
      .drag_and_drop_by(draggable, finish.x - start.x, finish.y - start.y)
      .perform
```

**JavaScript**

```javascript
const draggable = driver.findElement(By.id("draggable"));
let start = await draggable.getRect();
let finish = await driver.findElement(By.id("droppable")).getRect();
const actions = driver.actions({async: true});
await actions.dragAndDrop(draggable, {x: finish.x - start.x, y: finish.y - start.y}).perform();
```

**Kotlin**

```kotlin
val finish = driver.findElement(By.id("droppable")).getRect()
Actions(driver)
        .dragAndDropBy(draggable, finish.getX() - start.getX(), finish.getY() - start.getY())
        .perform()

Assertions.assertEquals("dropped", driver.findElement(By.id("drop-status")).getText())
```
