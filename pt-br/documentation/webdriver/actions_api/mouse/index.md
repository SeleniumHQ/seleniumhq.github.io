# Ações do Mouse

> Uma representação de qualquer dispositivo de ponteiro para interagir com uma página da web.

URL: https://www.selenium.dev/pt-br/documentation/webdriver/actions_api/mouse/

---

Existem apenas 3 ações que podem ser realizadas com um mouse: pressionar um botão, liberar um botão pressionado e mover o mouse. O Selenium fornece métodos de conveniência que combinam essas ações da maneira mais comum.

## Clicar e Manter Pressionado

Este método combina mover o mouse para o centro de um elemento com a pressão do botão esquerdo do mouse. Isso é útil para focar em um elemento específico:

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

## Clicar e Liberar

Este método combina mover o mouse para o centro de um elemento com a pressão e liberação do botão esquerdo do mouse. Isso é conhecido como "clicar":

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

## Clique com o Botão Alternativo

Existem um total de 5 botões definidos para um mouse:
- 0 — Botão Esquerdo (o padrão)
- 1 — Botão do Meio (atualmente não suportado)
- 2 — Botão Direito
- 3 — Botão X1 (Voltar)
- 4 — Botão X2 (Avançar)

###  Context Click

Este método combina mover o mouse para o centro de um elemento com a pressão e liberação do botão direito do mouse (botão 2). Isso é conhecido como "clicar com o botão direito" ou "menu de contexto"

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

### Click botão de voltar do mouse

Este termo pode se referir a um clique com o botão X1 (botão de voltar) do mouse. No entanto, essa terminologia específica pode variar dependendo do contexto.

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

### botão de avançar) do mouse

Este termo se refere a um clique com o botão X2 (botão de avançar) do mouse. Não existe um método de conveniência específico para essa ação, sendo apenas a pressão e liberação do botão do mouse de número 4.

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

## Duplo click

Este método combina mover o mouse para o centro de um elemento com a pressão e liberação do botão esquerdo do mouse duas vezes. Isso é conhecido como "duplo clique".

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

## Mover para o Elemento

Este método move o mouse para o ponto central do elemento que está visível na tela. Isso é conhecido como "hovering" ou "pairar". É importante observar que o elemento deve estar no viewport (área visível na tela) ou então o comando resultará em erro.

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

## Mover por Deslocamento

Esses métodos primeiro movem o mouse para a origem designada e, em seguida, pelo número de pixels especificado no deslocamento fornecido. É importante observar que a posição do mouse deve estar dentro da janela de visualização (viewport) ou, caso contrário, o comando resultará em erro.

### Deslocamento a partir do Elemento

Este método move o mouse para o ponto central do elemento visível na tela e, em seguida, move o mouse pelo deslocamento fornecido.

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

### Deslocamento a partir da Janela de Visualização

Este método move o mouse a partir do canto superior esquerdo da janela de visualização atual pelo deslocamento fornecido.

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

### Deslocamento a partir da Localização Atual do Ponteiro
Este método move o mouse a partir de sua posição atual pelo deslocamento fornecido pelo usuário. Se o mouse não tiver sido movido anteriormente, a posição será no canto superior esquerdo da janela de visualização. É importante notar que a posição do ponteiro não muda quando a página é rolada.

Observe que o primeiro argumento, X, especifica o movimento para a direita quando positivo, enquanto o segundo argumento, Y, especifica o movimento para baixo quando positivo. Portanto, `moveByOffset(30, -10)` move o mouse 30 unidades para a direita e 10 unidades para cima a partir da posição atual do mouse.

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

## Arrastar e Soltar no Elemento

Este método primeiro realiza um clique e mantém pressionado no elemento de origem, move para a localização do elemento de destino e, em seguida, libera o botão do mouse.

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

## Arrastar e Soltar pelo Deslocamento

Este método primeiro realiza um clique e mantém pressionado no elemento de origem, move para o deslocamento fornecido e, em seguida, libera o botão do mouse.

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
