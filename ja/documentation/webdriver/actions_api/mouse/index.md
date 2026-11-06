# Mouse actions

> A representation of any pointer device for interacting with a web page.

URL: https://www.selenium.dev/ja/documentation/webdriver/actions_api/mouse/

---

There are only 3 actions that can be accomplished with a mouse:
pressing down on a button, releasing a pressed button, and moving the mouse.
Selenium provides convenience methods that combine these actions in the most common ways.

Some pages, like the test page used in these examples, update their content from the mouse
events after the action has been performed. When your code checks a result like this, wait for
it explicitly instead of reading it right away or adding a fixed sleep.
See [Waiting strategies](https://www.selenium.dev/ja/documentation/webdriver/waits/index.md).

## Click and hold

This method combines moving the mouse to the center of an element with pressing the left mouse button.
This is useful for focusing a specific element:

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

## Click and release

This method combines moving to the center of an element with pressing and releasing the left mouse button.
This is otherwise known as "clicking":

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

## Alternate Button Clicks

There are a total of 5 defined buttons for a Mouse:
* 0 — Left Button (the default)
* 1 — Middle Button (currently unsupported)
* 2 — Right Button
* 3 — X1 (Back) Button
* 4 — X2 (Forward) Button

### Context Click

This method combines moving to the center of an element with pressing and releasing the right mouse button (button 2).
This is otherwise known as "right-clicking":

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

### Back Click

There is no convenience method for this, it is just pressing and releasing mouse button 3

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

### Forward Click

There is no convenience method for this, it is just pressing and releasing mouse button 4

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

## Double click

This method combines moving to the center of an element with pressing and releasing the left mouse button twice.

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

## Move to element

This method moves the mouse to the in-view center point of the element. 
This is otherwise known as "hovering."
Note that the element must be in the viewport or else the command will error.

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

## Move by offset

These methods first move the mouse to the designated origin and then
by the number of pixels in the provided offset.
Note that the position of the mouse must be in the viewport or else the command will error.

### Offset from Element

This method moves the mouse to the in-view center point of the element,
then moves by the provided offset.

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

### Offset from Viewport

This method moves the mouse from the upper left corner of the current viewport by the provided
offset.

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

### Offset from Current Pointer Location

This method moves the mouse from its current position by the offset provided by the user.
If the mouse has not previously been moved, the position will be in the upper left
corner of the viewport.
Note that the pointer position does not change when the page is scrolled.

Note that the first argument X specifies to move right when positive, while the second argument
Y specifies to move down when positive. So `moveByOffset(30, -10)` moves right 30 and up 10 from
the current mouse position.

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

## Drag and Drop on Element

This method firstly performs a click-and-hold on the source element, 
moves to the location of the target element and then releases the mouse.

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

## Drag and Drop by Offset

This method firstly performs a click-and-hold on the source element, moves to the given offset and then releases the mouse.

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
