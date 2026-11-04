# 同窗口和标签一起工作

URL: https://www.selenium.dev/zh-cn/documentation/webdriver/interactions/windows/

---

## 窗口和标签页

WebDriver 没有区分窗口和标签页。如果你的站点打开了一个新标签页或窗口，Selenium 将允许您使用窗口句柄来处理它。
每个窗口都有一个唯一的标识符，该标识符在单个会话中保持持久性。你可以使用以下方法获得当前窗口的窗口句柄:

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

### 切换窗口或标签页

单击在 <a href="https://seleniumhq.github.io"target="_blank">新窗口</a> 中打开链接，
则屏幕会聚焦在新窗口或新标签页上，但 WebDriver 不知道操作系统认为哪个窗口是活动的。
要使用新窗口，您需要切换到它。 如果只有两个选项卡或窗口被打开，并且你知道从哪个窗口开始，
则你可以遍历 WebDriver， 通过排除法可以看到两个窗口或选项卡，然后切换到你需要的窗口或选项卡。

不过，Selenium 4 提供了一个新的 api [NewWindow](#创建新窗口或新标签页并且切换)
它创建一个新选项卡 (或) 新窗口并自动切换到它。

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
    # 存储原始窗口的 ID
original_window = driver.window_handle

    #检查一下，我们还没有打开其他的窗口
assert(driver.window_handles.length == 1,'Expected one window')

    #点击在新窗口中打开的链接
driver.find_element(link:'new window').click

    #等待新窗口或标签页
wait.until {driver.window_handles.length == 2}

    #循环执行，直到找到一个新的窗口句柄
driver.window_handles.each do |handle|
if handle != original_window
driver.switch_to.window handle
break
end
end

    #等待新标签页完成加载内容
wait.until {driver.title =='Selenium documentation'}
```

**JavaScript**

```javascript
// 存储原始窗口的 ID
const originalWindow = await driver.getWindowHandle();

// 检查一下，我们还没有打开其他的窗口
assert((await driver.getAllWindowHandles()).length === 1);

// 点击在新窗口中打开的链接
await driver.findElement(By.linkText('new window')).click();

// 等待新窗口或标签页
await driver.wait(async () => (await driver.getAllWindowHandles()).length === 2,
10000
);

// 循环执行，直到找到一个新的窗口句柄
const windows = await driver.getAllWindowHandles();
windows.forEach(async handle => {if (handle !== originalWindow) {await driver.switchTo().window(handle);
}
});

// 等待新标签页完成加载内容
await driver.wait(until.titleIs('Selenium documentation'), 10000);
```

**Kotlin**

```kotlin
// 存储原始窗口的 ID
val originalWindow = driver.getWindowHandle()

// 检查一下，我们还没有打开其他的窗口
assert(driver.getWindowHandles().size() === 1)

// 点击在新窗口中打开的链接
driver.findElement(By.linkText("new window")).click()

// 等待新窗口或标签页
wait.until(numberOfWindowsToBe(2))

// 循环执行，直到找到一个新的窗口句柄
for (windowHandle in driver.getWindowHandles()) {
if (!originalWindow.contentEquals(windowHandle)) {
driver.switchTo().window(windowHandle)
break
}
}

// 等待新标签页完成加载内容
wait.until(titleIs("Selenium documentation"))
```

### 关闭窗口或标签页

当你完成了一个窗口或标签页的工作时，_并且_它不是浏览器中最后一个打开的窗口或标签页时，你应该关闭它并切换回你之前使用的窗口。
假设您遵循了前一节中的代码示例，您将把前一个窗口句柄存储在一个变量中。把这些放在一起，你会得到:

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
    #关闭标签页或窗口
driver.close

    #切回到之前的标签页或窗口
driver.switch_to.window original_window
```

**JavaScript**

```javascript
//关闭标签页或窗口
await driver.close();

//切回到之前的标签页或窗口
await driver.switchTo().window(originalWindow);
```

**Kotlin**

```kotlin
//关闭标签页或窗口
driver.close()

//切回到之前的标签页或窗口
driver.switchTo().window(originalWindow)
```

如果在关闭一个窗口后忘记切换回另一个窗口句柄，WebDriver 将在当前关闭的页面上执行，并触发一个
**No Such Window Exception 无此窗口异常**。必须切换回有效的窗口句柄才能继续执行。

### 创建新窗口(或)新标签页并且切换

创建一个新窗口 (或) 标签页，屏幕焦点将聚焦在新窗口或标签在上。您不需要切换到新窗口 (或) 标签页。如果除了新窗口之外，
您打开了两个以上的窗口 (或) 标签页，您可以通过遍历 WebDriver 看到两个窗口或选项卡，并切换到非原始窗口。

_注意: 该特性适用于 Selenium 4 及其后续版本。_

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

打开新标签页并切换到新标签页

```ruby
driver.switch_to.new_window(:tab)
```

打开一个新窗口并切换到新窗口

```ruby
driver.switch_to.new_window(:window)
```

**JavaScript**

// 打开新标签页并切换到新标签页

```javascript
await driver.switchTo().newWindow('tab');
```

// 打开一个新窗口并切换到新窗口

```javascript
await driver.switchTo().newWindow('window');
```

**Kotlin**

```kotlin
// 打开新标签页并切换到新标签页
driver.switchTo().newWindow(WindowType.TAB)

// 打开一个新窗口并切换到新窗口
driver.switchTo().newWindow(WindowType.WINDOW)
```

### 在会话结束时退出浏览器

当你完成了浏览器会话，你应该调用 quit 退出，而不是 close 关闭:

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

* 退出将会
  * 关闭所有与 WebDriver 会话相关的窗口和选项卡
  * 结束浏览器进程
  * 结束后台驱动进程
  * 通知 Selenium Grid 浏览器不再使用，以便可以由另一个会话使用它(如果您正在使用 Selenium Grid)

调用 quit() 失败将留下额外的后台进程和端口运行在机器上，这可能在以后导致一些问题。

有的测试框架提供了一些方法和注释，您可以在测试结束时放入 teardown() 方法中。

**Java**

```java
/**
* 使用 JUnit 的例子
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
使用 Visual Studio 的 UnitTesting 的例子
https://msdn.microsoft.com/en-us/library/microsoft.visualstudio.testtools.unittesting.aspx
*/
[TestCleanup]
public void TearDown()
{driver.Quit();
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
* 使用 Mocha 的例子
* https://mochajs.org/#hooks
  */
  after('Tear down', async function () {await driver.quit();
  });

```

**Kotlin**

```kotlin
/**
* 使用 JUnit 的例子
* https://junit.org/junit5/docs/current/api/org/junit/jupiter/api/AfterAll.html
*/
@AfterAll
fun tearDown() {
	driver.quit()
}
```

如果不在测试上下文中运行 WebDriver，您可以考虑使用 `try / finally`，这是大多数语言都提供的，
这样一个异常处理仍然可以清理 WebDriver 会话。

**Java**

```java
try {
    //WebDriver 代码…
} finally {
    driver.quit();
}
```

**Python**

```python
try:
    #WebDriver 代码…
finally:
driver.quit()
```

**CSharp**

```csharp
try {//WebDriver 代码…} finally {driver.Quit();
}
```

**Ruby**

```ruby
begin
    #WebDriver 代码…
ensure
driver.quit
end
```

**JavaScript**

```javascript
try {//WebDriver 代码…} finally {await driver.quit();
}
```

**Kotlin**

```kotlin
try {//WebDriver 代码…} finally {driver.quit()
}
```

Python 的 WebDriver 现在支持 Python 上下文管理器，当使用 with 关键字时，可以在执行结束时自动退出驱动程序。

```python
with webdriver.Firefox() as driver:
  # WebDriver 代码…

# 在此缩进位置后 WebDriver 会自动退出
```

## 窗口管理

屏幕分辨率会影响 web 应用程序的呈现方式，因此 WebDriver 提供了移动和调整浏览器窗口大小的机制。

### 获取窗口大小
获取浏览器窗口的大小(以像素为单位)。

**Java**

```java
// 分别获取每个尺寸
int width = driver.manage().window().getSize().getWidth();
int height = driver.manage().window().getSize().getHeight();

// 或者存储尺寸并在以后查询它们
Dimension size = driver.manage().window().getSize();
int width1 = size.getWidth();
int height1 = size.getHeight();
```

**Python**

```python
    # 分别获取每个尺寸
width = driver.get_window_size().get("width")
height = driver.get_window_size().get("height")

    # 或者存储尺寸并在以后查询它们
size = driver.get_window_size()
width1 = size.get("width")
height1 = size.get("height")
```

**CSharp**

```csharp
// 分别获取每个尺寸
int width = driver.Manage().Window.Size.Width;
int height = driver.Manage().Window.Size.Height;

// 或者存储尺寸并在以后查询它们
System.Drawing.Size size = driver.Manage().Window.Size;
int width1 = size.Width;
int height1 = size.Height;
```

**Ruby**

```ruby
    # 分别获取每个尺寸
width = driver.manage.window.size.width
height = driver.manage.window.size.height

    # 或者存储尺寸并在以后查询它们
size = driver.manage.window.size
width1 = size.width
height1 = size.height
```

**JavaScript**

分别获取每个尺寸

```javascript
const { width, height } = await driver.manage().window().getRect();
```

或者存储尺寸并在以后查询它们

```javascript
const rect = await driver.manage().window().getRect();
const windowWidth = rect.width;
const windowHeight = rect.height;
```

**Kotlin**

```kotlin
// 分别获取每个尺寸
val width = driver.manage().window().size.width
val height = driver.manage().window().size.height

// 或者存储尺寸并在以后查询它们
val size = driver.manage().window().size
val width1 = size.width
val height1 = size.height
```

### 设置窗口大小

恢复窗口并设置窗口大小。

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
await driver.manage().window().setRect({width: 1024, height: 768});
```

**Kotlin**

```kotlin
driver.manage().window().size = Dimension(1024, 768)
```

### 得到窗口的位置

获取浏览器窗口左上角的坐标。

**Java**

```java
// 分别获取每个尺寸
int x = driver.manage().window().getPosition().getX();
int y = driver.manage().window().getPosition().getY();

// 或者存储尺寸并在以后查询它们
Point position = driver.manage().window().getPosition();
int x1 = position.getX();
int y1 = position.getY();
```

**Python**

```python
    # 分别获取每个尺寸
x = driver.get_window_position().get('x')
y = driver.get_window_position().get('y')

    # 或者存储尺寸并在以后查询它们
position = driver.get_window_position()
x1 = position.get('x')
y1 = position.get('y')
```

**CSharp**

```csharp
// 分别获取每个尺寸
int x = driver.Manage().Window.Position.X;
int y = driver.Manage().Window.Position.Y;

// 或者存储尺寸并在以后查询它们
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

分别获取每个尺寸

```javascript
const { x, y } = await driver.manage().window().getRect();
```

或者存储尺寸并在以后查询它们

```javascript
const rect = await driver.manage().window().getRect();
const x1 = rect.x;
const y1 = rect.y;
```

**Kotlin**

```kotlin
// 分别获取每个尺寸
val x = driver.manage().window().position.x
val y = driver.manage().window().position.y

// 或者存储尺寸并在以后查询它们
val position = driver.manage().window().position
val x1 = position.x
val y1 = position.y
```

## 设置窗口位置

将窗口移动到设定的位置。

**Java**

```java
// 将窗口移动到主显示器的左上角
driver.manage().window().setPosition(new Point(0, 0));
```

**Python**

```python
    # 将窗口移动到主显示器的左上角
driver.set_window_position(0, 0)
```

**CSharp**

```csharp
// 将窗口移动到主显示器的左上角
driver.Manage().Window.Position = new Point(0, 0);
```

**Ruby**

```ruby
driver.manage.window.move_to(0,0)
```

**JavaScript**

```javascript
// 将窗口移动到主显示器的左上角
await driver.manage().window().setRect({x: 0, y: 0});
```

**Kotlin**

```kotlin
// 将窗口移动到主显示器的左上角
driver.manage().window().position = Point(0,0)
```

### 最大化窗口

扩大窗口。对于大多数操作系统，窗口将填满屏幕，而不会阻挡操作系统自己的菜单和工具栏。

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

### 最小化窗口
最小化当前浏览上下文的窗口.
这种命令的精准行为将作用于各个特定的窗口管理器.

最小化窗口通常将窗口隐藏在系统托盘中.

__注意: 此功能适用于Selenium 4以及更高版本.__

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

### 全屏窗口

填充整个屏幕，类似于在大多数浏览器中按下 F11。

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

### 屏幕截图

用于捕获当前浏览上下文的屏幕截图.
WebDriver端点
[屏幕截图](https://www.w3.org/TR/webdriver/#dfn-take-screenshot)
返回以Base64格式编码的屏幕截图.

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

###  元素屏幕截图

用于捕获当前浏览上下文的元素的屏幕截图.
WebDriver端点
[屏幕截图](https://www.w3.org/TR/webdriver/#take-element-screenshot)
返回以Base64格式编码的屏幕截图.

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

### 执行脚本

在当前frame或者窗口的上下文中，执行JavaScript代码片段.

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

### 打印页面

打印当前浏览器内的页面

_注意: 此功能需要无头模式下的Chromium浏览器_

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
