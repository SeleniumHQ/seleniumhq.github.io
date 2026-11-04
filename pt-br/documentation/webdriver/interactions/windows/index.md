# Working with windows and tabs

URL: https://www.selenium.dev/pt-br/documentation/webdriver/interactions/windows/

---

## Janelas e guias

### Pegue o idenficador da janela

O WebDriver não faz distinção entre janelas e guias. E se
seu site abre uma nova guia ou janela, o Selenium permitirá que você trabalhe
usando um identificador. Cada janela tem um identificador único que permanece
persistente em uma única sessão. Você pode pegar o identificador atual usando:

**Java**

```java
// Navigate to Url
driver.get("https://www.selenium.dev/selenium/web/window_switching_tests/page_with_frame.html");
//fetch handle of this
String currHandle=driver.getWindowHandle();
assertNotNull(currHandle);
```

**Python**

```python
driver.get(url)
current_handle = driver.current_window_handle
assert current_handle is not None
```

**CSharp**

```csharp
  // Navigate to Url
  driver.Url="https://www.selenium.dev/selenium/web/window_switching_tests/page_with_frame.html";
  //fetch handle of this
  String currHandle = driver.CurrentWindowHandle;
  Assert.IsNotNull(currHandle);
  ```

**Ruby**

```ruby
driver.window_handle
```

**JavaScript**

```javascript
await driver.getWindowHandle();
```

**Kotlin**

```kotlin
driver.windowHandle
```

### Alternando janelas ou guias

Clicar em um link que abre em uma
<a href="https://seleniumhq.github.io" target="_blank"> nova janela </a>
focará a nova janela ou guia na tela, mas o WebDriver não saberá qual
janela que o sistema operacional considera ativa. Para trabalhar com a nova janela
você precisará mudar para ela. Se você tiver apenas duas guias ou janelas abertas,
e você sabe com qual janela você iniciou, pelo processo de eliminação
você pode percorrer as janelas ou guias que o WebDriver pode ver e alternar
para aquela que não é o original.

No entanto, o Selenium 4 fornece uma nova API [NewWindow](#criar-nova-janela-ou-nova-guia-e-alternar) 
que cria uma nova guia (ou) nova janela e muda automaticamente para ela.

**Java**

```java
//click on link to open a new window
driver.findElement(By.linkText("Open new window")).click();
//fetch handles of all windows, there will be two, [0]- default, [1] - new window
Object[] windowHandles=driver.getWindowHandles().toArray();
driver.switchTo().window((String) windowHandles[1]);
//assert on title of new window
String title=driver.getTitle();
assertEquals("Simple Page",title);
```

**Python**

```python
driver.get(url)
wait = WebDriverWait(driver, 10)

original_window_handle = driver.current_window_handle

driver.find_element(By.LINK_TEXT, "Open new window").click()
wait.until(EC.number_of_windows_to_be(2))

new_window_handle = (set(driver.window_handles) - {original_window_handle}).pop()
driver.switch_to.window(new_window_handle)
assert driver.current_window_handle == new_window_handle
```

**CSharp**

```csharp
  //click on link to open a new window
  driver.FindElement(By.LinkText("Open new window")).Click();
  //fetch handles of all windows, there will be two, [0]- default, [1] - new window
  IList<string> windowHandles = new List<string>(driver.WindowHandles);
  driver.SwitchTo().Window(windowHandles[1]);
  //assert on title of new window
  String title = driver.Title;
  Assert.AreEqual("Simple Page", title);
  ```

**Ruby**

```ruby
    #Store the ID of the original window
original_window = driver.window_handle

    #Check we don't have other windows open already
assert(driver.window_handles.length == 1, 'Expected one window')

    #Click the link which opens in a new window
driver.find_element(link: 'new window').click

    #Wait for the new window or tab
wait.until { driver.window_handles.length == 2 }

    #Loop through until we find a new window handle
driver.window_handles.each do |handle|
    if handle != original_window
        driver.switch_to.window handle
        break
    end
end

    #Wait for the new tab to finish loading content
wait.until { driver.title == 'Selenium documentation'}

```

**JavaScript**

```javascript
//Store the ID of the original window
const originalWindow = await driver.getWindowHandle();

//Check we don't have other windows open already
assert((await driver.getAllWindowHandles()).length === 1);

//Click the link which opens in a new window
await driver.findElement(By.linkText('new window')).click();

//Wait for the new window or tab
await driver.wait(
    async () => (await driver.getAllWindowHandles()).length === 2,
    10000
  );

//Loop through until we find a new window handle
const windows = await driver.getAllWindowHandles();
windows.forEach(async handle => {
  if (handle !== originalWindow) {
    await driver.switchTo().window(handle);
  }
});

//Wait for the new tab to finish loading content
await driver.wait(until.titleIs('Selenium documentation'), 10000);

```

**Kotlin**

```kotlin
//Store the ID of the original window
val originalWindow = driver.getWindowHandle()

//Check we don't have other windows open already
assert(driver.getWindowHandles().size() === 1)

//Click the link which opens in a new window
driver.findElement(By.linkText("new window")).click()

//Wait for the new window or tab
wait.until(numberOfWindowsToBe(2))

//Loop through until we find a new window handle
for (windowHandle in driver.getWindowHandles()) {
    if (!originalWindow.contentEquals(windowHandle)) {
        driver.switchTo().window(windowHandle)
        break
    }
}

//Wait for the new tab to finish loading content
wait.until(titleIs("Selenium documentation"))

```

### Fechando uma janela ou guia

Quando você fechar uma janela ou guia _e_ que não é a
última janela ou guia aberta em seu navegador, você deve fechá-la e alternar
de volta para a janela que você estava usando anteriormente. Supondo que você seguiu a
amostra de código na seção anterior, você terá o identificador da janela
anterior armazenado em uma variável. Junte isso e você obterá:

**Java**

```java
//closing current window
driver.close();
//Switch back to the old tab or window
driver.switchTo().window((String) windowHandles[0]);
```

**Python**

```python
driver.close()
```

**CSharp**

```csharp
  //closing current window
  driver.Close();
  //Switch back to the old tab or window
  driver.SwitchTo().Window(windowHandles[0]);
  ```

**Ruby**

```ruby
    #Close the tab or window
driver.close

    #Switch back to the old tab or window
driver.switch_to.window original_window

```

**JavaScript**

```javascript
//Close the tab or window
await driver.close();

//Switch back to the old tab or window
await driver.switchTo().window(originalWindow);

```

**Kotlin**

```kotlin
//Close the tab or window
driver.close()

//Switch back to the old tab or window
driver.switchTo().window(originalWindow)

```

Esquecer de voltar para outro gerenciador de janela após fechar uma
janela deixará o WebDriver em execução na página agora fechada e
acionara uma **No Such Window Exception**. Você deve trocar
de volta para um identificador de janela válido para continuar a execução.
### Criar nova janela (ou) nova guia e alternar
Cria uma nova janela (ou) guia e focará a nova janela ou guia na tela.
Você não precisa mudar para trabalhar com a nova janela (ou) guia. Se você tiver mais de duas janelas
(ou) guias abertas diferentes da nova janela, você pode percorrer as janelas ou guias que o WebDriver pode ver
e mudar para aquela que não é a original.

__Nota: este recurso funciona com Selenium 4 e versões posteriores.__

**Java**

```java
//Opens a new tab and switches to new tab
driver.switchTo().newWindow(WindowType.TAB);
assertEquals("",driver.getTitle());

//Opens a new window and switches to new window
driver.switchTo().newWindow(WindowType.WINDOW);
assertEquals("",driver.getTitle());
```

**Python**

```python
# Opens a new tab and switches to new tab
driver.switch_to.new_window('tab')
assert driver.title == ""
# Opens a new window and switches to new window
driver.switch_to.new_window('window')
assert driver.title == ""
```

**CSharp**

```csharp
  //Opens a new tab and switches to new tab
  driver.SwitchTo().NewWindow(WindowType.Tab);
  Assert.AreEqual("", driver.Title);

  //Opens a new window and switches to new window
  driver.SwitchTo().NewWindow(WindowType.Window);
  Assert.AreEqual("", driver.Title);
  ```

**Ruby**

Opens a new tab and switches to new tab

```ruby
driver.switch_to.new_window(:tab)
```

Opens a new window and switches to new window

```ruby
driver.switch_to.new_window(:window)
```

**JavaScript**

Opens a new tab and switches to new tab

```javascript
await driver.switchTo().newWindow('tab');
```

Opens a new window and switches to new window:

```javascript
await driver.switchTo().newWindow('window');
```

**Kotlin**

```kotlin
// Opens a new tab and switches to new tab
driver.switchTo().newWindow(WindowType.TAB)

// Opens a new window and switches to new window
driver.switchTo().newWindow(WindowType.WINDOW)

```

### Sair do navegador no final de uma sessão

Quando você terminar a sessão do navegador, você deve chamar a função _quit()_,
em vez de fechar:

**Java**

```java
//quitting driver
driver.quit(); //close all windows
```

**Python**

```python
driver.quit()
```

**CSharp**

```csharp
  //quitting driver
  driver.Quit(); //close all windows
  ```

**Ruby**

```ruby
driver.quit
```

**JavaScript**

```javascript
await driver.quit();
```

**Kotlin**

```kotlin
driver.quit()
```

* _quit()_ irá:
   * Fechar todas as janelas e guias associadas a essa sessão do WebDriver
   * Fechar o processo do navegador
   * Fechar o processo do driver em segundo plano
   * Notificar o Selenium Grid de que o navegador não está mais em uso para que possa
    ser usado por outra sessão (se você estiver usando Selenium Grid)

A falha em encerrar deixará processos e portas extras em segundo plano
rodando em sua máquina, o que pode causar problemas mais tarde.

Algumas estruturas de teste oferecem métodos e anotações em que você pode ligar para derrubar no final de um teste.

**Java**

```java
/**
 * Example using JUnit
 * https://junit.org/junit5/docs/current/api/org/junit/jupiter/api/AfterAll.html
 */
@AfterAll
public static void tearDown() {
    driver.quit();
}

```

**Python**

```python
    # unittest teardown
    # https://docs.python.org/3/library/unittest.html?highlight=teardown#unittest.TestCase.tearDown
def tearDown(self):
    self.driver.quit()

```

**CSharp**

```csharp
/*
    Example using Visual Studio's UnitTesting
    https://msdn.microsoft.com/en-us/library/microsoft.visualstudio.testtools.unittesting.aspx
*/
[TestCleanup]
public void TearDown()
{
    driver.Quit();
}

```

**Ruby**

```ruby
    # UnitTest Teardown
    # https://www.rubydoc.info/github/test-unit/test-unit/Test/Unit/TestCase
def teardown
    @driver.quit
end

```

**JavaScript**

```javascript
/**
 * Example using Mocha
 * https://mochajs.org/#hooks
 */
after('Tear down', async function () {
  await driver.quit();
});

```

**Kotlin**

```kotlin
/**
 * Example using JUnit
 * https://junit.org/junit5/docs/current/api/org/junit/jupiter/api/AfterAll.html
 */
@AfterAll
fun tearDown() {
    driver.quit()
}

```

Se não estiver executando o WebDriver em um contexto de teste, você pode considerar o uso do
`try/finally` que é oferecido pela maioria das linguagens para que uma exceção
ainda limpe a sessão do WebDriver.

**Java**

```java
try {
    //WebDriver code here...
} finally {
    driver.quit();
}

```

**Python**

```python
try:
    #WebDriver code here...
finally:
    driver.quit()

```

**CSharp**

```csharp
try {
    //WebDriver code here...
} finally {
    driver.Quit();
}

```

**Ruby**

```ruby
begin
    #WebDriver code here...
ensure
    driver.quit
end

```

**JavaScript**

```javascript
try {
    //WebDriver code here...
} finally {
    await driver.quit();
}

```

**Kotlin**

```kotlin
try {
    //WebDriver code here...
} finally {
    driver.quit()
}

```

O WebDriver do Python agora suporta o gerenciador de contexto python,
que ao usar a palavra-chave `with` pode encerrar automaticamente o
driver no fim da execução.

```python
with webdriver.Firefox() as driver:
  # WebDriver code here...

# WebDriver will automatically quit after indentation
```

## Gerenciamento de janelas
A resolução da tela pode impactar como seu aplicativo da web é renderizado, então
WebDriver fornece mecanismos para mover e redimensionar a janela do navegador.

### Coletar o tamanho da janela
Obtém o tamanho da janela do navegador em pixels.

**Java**

```java
//Access each dimension individually
int width = driver.manage().window().getSize().getWidth();
int height = driver.manage().window().getSize().getHeight();

//Or store the dimensions and query them later
Dimension size = driver.manage().window().getSize();
int width1 = size.getWidth();
int height1 = size.getHeight();

```

**Python**

```python
    # Access each dimension individually
width = driver.get_window_size().get("width")
height = driver.get_window_size().get("height")

    # Or store the dimensions and query them later
size = driver.get_window_size()
width1 = size.get("width")
height1 = size.get("height")

```

**CSharp**

```csharp
//Access each dimension individually
int width = driver.Manage().Window.Size.Width;
int height = driver.Manage().Window.Size.Height;

//Or store the dimensions and query them later
System.Drawing.Size size = driver.Manage().Window.Size;
int width1 = size.Width;
int height1 = size.Height;

```

**Ruby**

```ruby
    # Access each dimension individually
width = driver.manage.window.size.width
height = driver.manage.window.size.height

    # Or store the dimensions and query them later
size = driver.manage.window.size
width1 = size.width
height1 = size.height

```

**JavaScript**

Access each dimension individually

```javascript
const { width, height } = await driver.manage().window().getRect();
```

(or) store the dimensions and query them later

```javascript
const rect = await driver.manage().window().getRect();
const windowWidth = rect.width;
const windowHeight = rect.height;
```

**Kotlin**

```kotlin
//Access each dimension individually
val width = driver.manage().window().size.width
val height = driver.manage().window().size.height

//Or store the dimensions and query them later
val size = driver.manage().window().size
val width1 = size.width
val height1 = size.height

```

### Definir o tamanho da janela

Restaura a janela e define o tamanho da janela.

**Java**

```java
driver.manage().window().setSize(new Dimension(1024, 768));
```

**Python**

```python
driver.set_window_size(1024, 768)
```

**CSharp**

```csharp
driver.Manage().Window.Size = new Size(1024, 768);
```

**Ruby**

```ruby
driver.manage.window.resize_to(1024,768)
```

**JavaScript**

```javascript
await driver.manage().window().setRect({ width: 1024, height: 768 });
```

**Kotlin**

```kotlin
driver.manage().window().size = Dimension(1024, 768)
```

### Coletar posição da janela

Busca as coordenadas da coordenada superior esquerda da janela do navegador.

**Java**

```java
// Access each dimension individually
int x = driver.manage().window().getPosition().getX();
int y = driver.manage().window().getPosition().getY();

// Or store the dimensions and query them later
Point position = driver.manage().window().getPosition();
int x1 = position.getX();
int y1 = position.getY();

```

**Python**

```python
    # Access each dimension individually
x = driver.get_window_position().get('x')
y = driver.get_window_position().get('y')

    # Or store the dimensions and query them later
position = driver.get_window_position()
x1 = position.get('x')
y1 = position.get('y')

```

**CSharp**

```csharp
//Access each dimension individually
int x = driver.Manage().Window.Position.X;
int y = driver.Manage().Window.Position.Y;

//Or store the dimensions and query them later
Point position = driver.Manage().Window.Position;
int x1 = position.X;
int y1 = position.Y;

```

**Ruby**

```ruby
    #Access each dimension individually
x = driver.manage.window.position.x
y = driver.manage.window.position.y

    # Or store the dimensions and query them later
rect  = driver.manage.window.rect
x1 = rect.x
y1 = rect.y

```

**JavaScript**

Access each dimension individually

```javascript
const { x, y } = await driver.manage().window().getRect();
```

(or) store the dimensions and query them later

```javascript
const rect = await driver.manage().window().getRect();
const x1 = rect.x;
const y1 = rect.y;
```

**Kotlin**

```kotlin
// Access each dimension individually
val x = driver.manage().window().position.x
val y = driver.manage().window().position.y

// Or store the dimensions and query them later
val position = driver.manage().window().position
val x1 = position.x
val y1 = position.y

```

## Definir posição da janela

Move a janela para a posição escolhida.

**Java**

```java
// Move the window to the top left of the primary monitor
driver.manage().window().setPosition(new Point(0, 0));

```

**Python**

```python
    # Move the window to the top left of the primary monitor
driver.set_window_position(0, 0)

```

**CSharp**

```csharp
// Move the window to the top left of the primary monitor
driver.Manage().Window.Position = new Point(0, 0);

```

**Ruby**

```ruby
driver.manage.window.move_to(0,0)

```

**JavaScript**

```javascript
// Move the window to the top left of the primary monitor
await driver.manage().window().setRect({ x: 0, y: 0 });

```

**Kotlin**

```kotlin
// Move the window to the top left of the primary monitor
driver.manage().window().position = Point(0,0)

```

### Maximizar janela
Aumenta a janela. Para a maioria dos sistemas operacionais, a janela irá preencher
a tela, sem bloquear os próprios menus do sistema operacional e
barras de ferramentas.

**Java**

```java
driver.manage().window().maximize();
```

**Python**

```python
driver.maximize_window()
```

**CSharp**

```csharp
driver.Manage().Window.Maximize();
```

**Ruby**

```ruby
driver.manage.window.maximize
```

**JavaScript**

```javascript
await driver.manage().window().maximize();
```

**Kotlin**

```kotlin
driver.manage().window().maximize()
```

### Minimizar janela
Minimiza a janela do contexto de navegação atual.
O comportamento exato deste comando é específico para
gerenciadores de janela individuais.

Minimizar Janela normalmente oculta a janela na bandeja do sistema.

__Nota: este recurso funciona com Selenium 4 e versões posteriores.__

**Java**

```java
driver.manage().window().minimize();
```

**Python**

```python
driver.minimize_window()
```

**CSharp**

```csharp
driver.Manage().Window.Minimize();
```

**Ruby**

```ruby
driver.manage.window.minimize
```

**JavaScript**

```javascript
await driver.manage().window().minimize();
```

**Kotlin**

```kotlin
driver.manage().window().minimize()
```

### Janela em tamanho cheio

Preenche a tela inteira, semelhante a pressionar F11 na maioria dos navegadores.

**Java**

```java
driver.manage().window().fullscreen();
```

**Python**

```python
driver.fullscreen_window()
```

**CSharp**

```csharp
driver.Manage().Window.FullScreen();
```

**Ruby**

```ruby
driver.manage.window.full_screen
```

**JavaScript**

```javascript
await driver.manage().window().fullscreen();
```

**Kotlin**

```kotlin
driver.manage().window().fullscreen()
```

### TakeScreenshot

Usado para capturar a tela do contexto de navegação atual.
O endpoint WebDriver [screenshot](https://www.w3.org/TR/webdriver/#dfn-take-screenshot)
retorna a captura de tela codificada no formato Base64.

**Java**

```java
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.*;
import org.openqa.selenium.*;

public class SeleniumTakeScreenshot {
    public static void main(String args[]) throws IOException {
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.example.com");
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("./image.png"));
        driver.quit();
    }
}

```

**Python**

```python
from selenium import webdriver

driver = webdriver.Chrome()

    # Navigate to url
driver.get("http://www.example.com")

    # Returns and base64 encoded string into image
driver.save_screenshot('./image.png')

driver.quit()
```

**CSharp**

```csharp
  using OpenQA.Selenium;
  using OpenQA.Selenium.Chrome;
  using OpenQA.Selenium.Support.UI;

  var driver = new ChromeDriver();
  driver.Navigate().GoToUrl("http://www.example.com");
  Screenshot screenshot = (driver as ITakesScreenshot).GetScreenshot();
  screenshot.SaveAsFile("screenshot.png", ScreenshotImageFormat.Png); // Format values are Bmp, Gif, Jpeg, Png, Tiff

```

**Ruby**

```ruby
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :chrome

begin
  driver.get 'https://example.com/'

      # Takes and Stores the screenshot in specified path
  driver.save_screenshot('./image.png')

end

```

**JavaScript**

```javascript
// Captures the screenshot
let encodedString = await driver.takeScreenshot();
// save screenshot as below
// await fs.writeFileSync('./image.png', encodedString, 'base64');
```

**Kotlin**

```kotlin
import com.oracle.tools.packager.IOUtils.copyFile
import org.openqa.selenium.*
import org.openqa.selenium.chrome.ChromeDriver
import java.io.File

fun main(){
    val driver =  ChromeDriver()
    driver.get("https://www.example.com")
    val scrFile = (driver as TakesScreenshot).getScreenshotAs<File>(OutputType.FILE)
    copyFile(scrFile, File("./image.png"))
    driver.quit()
}

```

###  TakeElementScreenshot

Usado para capturar a imagem de um elemento para o contexto de navegação atual.
O endpoint WebDriver [screenshot](https://www.w3.org/TR/webdriver/#take-element-screenshot)
retorna a captura de tela codificada no formato Base64.

**Java**

```java
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.IOException;

public class SeleniumelementTakeScreenshot {
  public static void main(String args[]) throws IOException {
    WebDriver driver = new ChromeDriver();
    driver.get("https://www.example.com");
    WebElement element = driver.findElement(By.cssSelector("h1"));
    File scrFile = element.getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile, new File("./image.png"));
    driver.quit();
  }
}

```

**Python**

```python
from selenium import webdriver
from selenium.webdriver.common.by import By

driver = webdriver.Chrome()

    # Navigate to url
driver.get("http://www.example.com")

ele = driver.find_element(By.CSS_SELECTOR, 'h1')

    # Returns and base64 encoded string into image
ele.screenshot('./image.png')

driver.quit()

```

**CSharp**

```csharp
    using OpenQA.Selenium;
    using OpenQA.Selenium.Chrome;
    using OpenQA.Selenium.Support.UI;

    // Webdriver
    var driver = new ChromeDriver();
    driver.Navigate().GoToUrl("http://www.example.com");

    // Fetch element using FindElement
    var webElement = driver.FindElement(By.CssSelector("h1"));

    // Screenshot for the element
    var elementScreenshot = (webElement as ITakesScreenshot).GetScreenshot();
    elementScreenshot.SaveAsFile("screenshot_of_element.png");

```

**Ruby**

```ruby
    # Works with Selenium4-alpha7 Ruby bindings and above
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :chrome

begin
  driver.get 'https://example.com/'
  ele = driver.find_element(:css, 'h1')

      # Takes and Stores the element screenshot in specified path
  ele.save_screenshot('./image.jpg')
end

```

**JavaScript**

```javascript
  let header = await driver.findElement(By.css('h1'));
  // Captures the element screenshot
  let encodedString = await header.takeScreenshot(true);
  // save screenshot as below
  // await fs.writeFileSync('./image.png', encodedString, 'base64');
  ```

**Kotlin**

```kotlin
import org.apache.commons.io.FileUtils
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.*
import java.io.File

fun main() {
    val driver = ChromeDriver()
    driver.get("https://www.example.com")
    val element = driver.findElement(By.cssSelector("h1"))
    val scrFile: File = element.getScreenshotAs(OutputType.FILE)
    FileUtils.copyFile(scrFile, File("./image.png"))
    driver.quit()
}

```

### Executar Script

Executa o snippet de código JavaScript no
contexto atual de um frame ou janela selecionada.

**Java**

```java
    //Creating the JavascriptExecutor interface object by Type casting
      JavascriptExecutor js = (JavascriptExecutor)driver;
    //Button Element
      WebElement button =driver.findElement(By.name("btnLogin"));
    //Executing JavaScript to click on element
      js.executeScript("arguments[0].click();", button);
    //Get return value from script
      String text = (String) js.executeScript("return arguments[0].innerText", button);
    //Executing JavaScript directly
      js.executeScript("console.log('hello world')");

```

**Python**

```python
    # Stores the header element
header = driver.find_element(By.CSS_SELECTOR, "h1")

    # Executing JavaScript to capture innerText of header element
driver.execute_script('return arguments[0].innerText', header)

```

**CSharp**

```csharp
   //creating Chromedriver instance
	IWebDriver driver = new ChromeDriver();
	//Creating the JavascriptExecutor interface object by Type casting
	IJavaScriptExecutor js = (IJavaScriptExecutor) driver;
	//Button Element
	IWebElement button = driver.FindElement(By.Name("btnLogin"));
	//Executing JavaScript to click on element
	js.ExecuteScript("arguments[0].click();", button);
	//Get return value from script
	String text = (String)js.ExecuteScript("return arguments[0].innerText", button);
	//Executing JavaScript directly
	js.ExecuteScript("console.log('hello world')");

```

**Ruby**

```ruby
    # Stores the header element
header = driver.find_element(css: 'h1')

    # Get return value from script
result = driver.execute_script("return arguments[0].innerText", header)

    # Executing JavaScript directly
driver.execute_script("alert('hello world')")

```

**JavaScript**

```javascript
// Stores the header element
let header = await driver.findElement(By.css('h1'));

// Executing JavaScript to capture innerText of header element
let text = await driver.executeScript('return arguments[0].innerText', header);
```

**Kotlin**

```kotlin
// Stores the header element
val header = driver.findElement(By.cssSelector("h1"))

// Get return value from script
val result = driver.executeScript("return arguments[0].innerText", header)

// Executing JavaScript directly
driver.executeScript("alert('hello world')")

```

### Imprimir Página

Imprime a página atual dentro do navegador

_Nota: isto requer que navegadores Chromium estejam no modo sem cabeçalho_

**Java**

```java
    import org.openqa.selenium.print.PrintOptions;

    driver.get("https://www.selenium.dev");
    printer = (PrintsPage) driver;

    PrintOptions printOptions = new PrintOptions();
    printOptions.setPageRanges("1-2");

    Pdf pdf = printer.print(printOptions);
    String content = pdf.getContent();

```

**Python**

```python
    from selenium.webdriver.common.print_page_options import PrintOptions

    print_options = PrintOptions()
    print_options.page_ranges = ['1-2']

    driver.get("printPage.html")

    base64code = driver.print_page(print_options)

```

**CSharp**

```csharp
    // code sample not available please raise a PR

```

**Ruby**

```ruby
    driver.navigate_to 'https://www.selenium.dev'

    base64encodedContent = driver.print_page(orientation: 'landscape')

```

**JavaScript**

```javascript
  await driver.get('https://www.selenium.dev/selenium/web/alerts.html');
  let base64 = await driver.printPage({pageRanges: ["1-2"]});
  // page can be saved as a PDF as below
  // await fs.writeFileSync('./test.pdf', base64, 'base64');
  ```

**Kotlin**

```kotlin
    driver.get("https://www.selenium.dev")
    val printer = driver as PrintsPage

    val printOptions = PrintOptions()
    printOptions.setPageRanges("1-2")

    val pdf: Pdf = printer.print(printOptions)
    val content = pdf.content

```
