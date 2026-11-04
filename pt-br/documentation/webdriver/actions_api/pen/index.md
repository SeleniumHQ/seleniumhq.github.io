# Ações de Caneta

> Uma representação de uma entrada de ponteiro do tipo caneta stylus para interagir com uma página da web.

URL: https://www.selenium.dev/pt-br/documentation/webdriver/actions_api/pen/

---

_Only works in Chromium ([browser support](https://wpt.fyi/results/webdriver/tests/perform_actions/pointer.py))._

Uma caneta é um tipo de entrada de ponteiro que possui a maior parte do mesmo comportamento que um mouse, mas também pode ter propriedades de evento únicas para uma caneta stylus. Além disso, enquanto um mouse possui 5 botões, uma caneta possui 3 estados equivalentes de botão:

* 0 — Contato por Toque (o padrão; equivalente a um clique com o botão esquerdo)
* 2 — Botão do Barril (equivalente a um clique com o botão direito)
* 5 — Botão de Borracha (atualmente não suportado pelos drivers)

## Usando uma Caneta

**Java**

_Requires [Selenium v4.2](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.2.0) or later._

```java
WebElement pointerArea = driver.findElement(By.id("pointerArea"));
new Actions(driver)
        .setActivePointer(PointerInput.Kind.PEN, "default pen")
        .moveToElement(pointerArea)
        .clickAndHold()
        .moveByOffset(2, 2)
        .release()
        .perform();
```

**Python**

_Requires [Selenium v4.2](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.2.0) or later._

```python
pointer_area = driver.find_element(By.ID, "pointerArea")
pen_input = PointerInput(POINTER_PEN, "default pen")
action = ActionBuilder(driver, mouse=pen_input)
action.pointer_action\
    .move_to(pointer_area)\
    .pointer_down()\
    .move_by(2, 2)\
    .pointer_up()
action.perform()
```

**CSharp**

```csharp
IWebElement pointerArea = driver.FindElement(By.Id("pointerArea"));
ActionBuilder actionBuilder = new ActionBuilder();
PointerInputDevice pen = new PointerInputDevice(PointerKind.Pen, "default pen");

actionBuilder.AddAction(pen.CreatePointerMove(pointerArea, 0, 0, TimeSpan.FromMilliseconds(800)));
actionBuilder.AddAction(pen.CreatePointerDown(MouseButton.Left));
actionBuilder.AddAction(pen.CreatePointerMove(CoordinateOrigin.Pointer,
    2, 2, TimeSpan.Zero));
actionBuilder.AddAction(pen.CreatePointerUp(MouseButton.Left));
((IActionExecutor)driver).PerformActions(actionBuilder.ToActionSequenceList());
```

**Ruby**

_Requires [Selenium v4.2](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.2.0) or later._

```ruby
pointer_area = driver.find_element(id: 'pointerArea')
driver.action(devices: :pen)
      .move_to(pointer_area)
      .pointer_down
      .move_by(2, 2)
      .pointer_up
      .perform
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

```kotlin
Actions(driver)
        .setActivePointer(PointerInput.Kind.PEN, "default pen")
        .moveToElement(pointerArea)
        .clickAndHold()
        .moveByOffset(2, 2)
        .release()
        .perform()
```

## Adicionando Atributos de Evento de Ponteiro

_Requires [Selenium v4.2](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.2.0) or later._

**Java**

```java
WebElement pointerArea = driver.findElement(By.id("pointerArea"));
PointerInput pen = new PointerInput(PointerInput.Kind.PEN, "default pen");
PointerInput.PointerEventProperties eventProperties = PointerInput.eventProperties()
        .setTiltX(-72)
        .setTiltY(9)
        .setTwist(86);
PointerInput.Origin origin = PointerInput.Origin.fromElement(pointerArea);

Sequence actionListPen = new Sequence(pen, 0)
        .addAction(pen.createPointerMove(Duration.ZERO, origin, 0, 0))
        .addAction(pen.createPointerDown(0))
        .addAction(pen.createPointerMove(Duration.ZERO, origin, 2, 2, eventProperties))
        .addAction(pen.createPointerUp(0));

((RemoteWebDriver) driver).perform(Collections.singletonList(actionListPen));
```

**Python**

```python
pointer_area = driver.find_element(By.ID, "pointerArea")
pen_input = PointerInput(POINTER_PEN, "default pen")
action = ActionBuilder(driver, mouse=pen_input)
action.pointer_action\
    .move_to(pointer_area)\
    .pointer_down()\
    .move_by(2, 2, tilt_x=-72, tilt_y=9, twist=86)\
    .pointer_up(0)
action.perform()
```

**CSharp**

```csharp
IWebElement pointerArea = driver.FindElement(By.Id("pointerArea"));
ActionBuilder actionBuilder = new ActionBuilder();
PointerInputDevice pen = new PointerInputDevice(PointerKind.Pen, "default pen");
PointerInputDevice.PointerEventProperties properties = new PointerInputDevice.PointerEventProperties() {
    TiltX = -72,
    TiltY = 9,
    Twist = 86,
};            
actionBuilder.AddAction(pen.CreatePointerMove(pointerArea, 0, 0, TimeSpan.FromMilliseconds(800)));
actionBuilder.AddAction(pen.CreatePointerDown(MouseButton.Left));
actionBuilder.AddAction(pen.CreatePointerMove(CoordinateOrigin.Pointer,
    2, 2, TimeSpan.Zero, properties));
actionBuilder.AddAction(pen.CreatePointerUp(MouseButton.Left));
((IActionExecutor)driver).PerformActions(actionBuilder.ToActionSequenceList());
```

**Ruby**

```ruby
pointer_area = driver.find_element(id: 'pointerArea')
driver.action(devices: :pen)
      .move_to(pointer_area)
      .pointer_down
      .move_by(2, 2, tilt_x: -72, tilt_y: 9, twist: 86)
      .pointer_up
      .perform
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

```kotlin
val pen = PointerInput(PointerInput.Kind.PEN, "default pen")
val eventProperties = PointerInput.eventProperties()
        .setTiltX(-72)
        .setTiltY(9)
        .setTwist(86)
val origin = PointerInput.Origin.fromElement(pointerArea)

val actionListPen = Sequence(pen, 0)
        .addAction(pen.createPointerMove(Duration.ZERO, origin, 0, 0))
        .addAction(pen.createPointerDown(0))
        .addAction(pen.createPointerMove(Duration.ZERO, origin, 2, 2, eventProperties))
        .addAction(pen.createPointerUp(0))

(driver as RemoteWebDriver).perform(listOf(actionListPen))
```
