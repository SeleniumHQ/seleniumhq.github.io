---
title: "Working with windows and tabs"
linkTitle: "Windows"
weight: 8
aliases: [
"/documentation/webdriver/browser/windows",
]
---

## Windows and tabs

### Get window handle

WebDriver does not make the distinction between windows and tabs. If
your site opens a new tab or window, Selenium will let you work with it
using a window handle.  Each window has a unique identifier which remains
persistent in a single session. You can get the window handle of the
current window by using:

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/WindowsTest.java#L16-L20" >}}
{{< /tab >}}
  {{< tab header="Python" >}}driver.current_window_handle{{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/WindowsTest.cs#L14-L18" >}}
   {{< /tab >}}
  {{< tab header="Ruby" >}}driver.window_handle{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.getWindowHandle();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.windowHandle{{< /tab >}}
{{< /tabpane >}}

### Switching windows or tabs

Clicking a link which opens in a
<a href="https://seleniumhq.github.io" target="_blank"> new window</a>
will focus the new window or tab on screen, but WebDriver will not know which
window the Operating System considers active.  To work with the new window
you will need to switch to it. For this, we fetch all window handles,
and store them in an array. The array position fills in the order the
window is launched. So first position will be default browser, and so on.

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
  {{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/WindowsTest.java#L22-L29" >}}
{{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

with webdriver.Firefox() as driver:
    # Open URL
    driver.get("https://seleniumhq.github.io")

    # Setup wait for later
    wait = WebDriverWait(driver, 10)

    # Store the ID of the original window
    original_window = driver.current_window_handle

    # Check we don't have other windows open already
    assert len(driver.window_handles) == 1

    # Click the link which opens in a new window
    driver.find_element(By.LINK_TEXT, "new window").click()

    # Wait for the new window or tab
    wait.until(EC.number_of_windows_to_be(2))

    # Loop through until we find a new window handle
    for window_handle in driver.window_handles:
        if window_handle != original_window:
            driver.switch_to.window(window_handle)
            break

    # Wait for the new tab to finish loading content
    wait.until(EC.title_is("SeleniumHQ Browser Automation"))
  {{< /tab >}}
  {{< tab header="CSharp" >}}
//Store the ID of the original window
string originalWindow = driver.CurrentWindowHandle;

//Check we don't have other windows open already
Assert.AreEqual(driver.WindowHandles.Count, 1);

//Click the link which opens in a new window
driver.FindElement(By.LinkText("new window")).Click();

//Wait for the new window or tab
wait.Until(wd => wd.WindowHandles.Count == 2);

//Loop through until we find a new window handle
foreach(string window in driver.WindowHandles)
{
    if(originalWindow != window)
    {
        driver.SwitchTo().Window(window);
        break;
    }
}
//Wait for the new tab to finish loading content
wait.Until(wd => wd.Title == "Selenium documentation");
  {{< /tab >}}
  {{< tab header="Ruby" >}}

    # Store the ID of the original window
original_window = driver.window_handle

    # Check we don't have other windows open already
assert(driver.window_handles.length == 1, 'Expected one window')

    # Click the link which opens in a new window
driver.find_element(link: 'new window').click

    # Wait for the new window or tab
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
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
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
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
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

  {{< /tab >}}
{{< /tabpane >}}

### Closing a window or tab

When you are finished with a window or tab _and_ it is not the
last window or tab open in your browser, you should close it and switch
back to the window you were using previously.  Assuming you followed the
code sample in the previous section you will have the previous window
handle stored in a variable. Put this together and you will get:

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
 {{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/WindowsTest.java#L31-L34" >}}
{{< /tab >}}
  {{< tab header="Python" >}}
    #Close the tab or window
driver.close()

    #Switch back to the old tab or window
driver.switch_to.window(original_window)
  {{< /tab >}}
  {{< tab header="CSharp" >}}
//Close the tab or window
driver.Close();

//Switch back to the old tab or window
driver.SwitchTo().Window(originalWindow);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    #Close the tab or window
driver.close

    #Switch back to the old tab or window
driver.switch_to.window original_window
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
//Close the tab or window
await driver.close();

//Switch back to the old tab or window
await driver.switchTo().window(originalWindow);
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
//Close the tab or window
driver.close()

//Switch back to the old tab or window
driver.switchTo().window(originalWindow)

  {{< /tab >}}
{{< /tabpane >}}

Forgetting to switch back to another window handle after closing a
window will leave WebDriver executing on the now closed page, and will
trigger a **No Such Window Exception**. You must switch
back to a valid window handle in order to continue execution.

### Create new window (or) new tab and switch
Creates a new window (or) tab and will focus the new window or tab on screen.
You don't need to switch to work with the new window (or) tab. If you have more than two windows
(or) tabs opened other than the new window, you can loop over both windows or tabs that WebDriver can see,
and switch to the one which is not the original.

__Note: This feature works with Selenium 4 and later versions.__

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
   {{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/WindowsTest.java#L36-L42" >}}
{{< /tab >}}
  {{< tab header="Python" >}}
    # Opens a new tab and switches to new tab
driver.switch_to.new_window('tab')

    # Opens a new window and switches to new window
driver.switch_to.new_window('window')
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// Opens a new tab and switches to new tab
driver.SwitchTo().NewWindow(WindowType.Tab)

// Opens a new window and switches to new window
driver.SwitchTo().NewWindow(WindowType.Window)
  {{< /tab >}}
  {{% tab header="Ruby" text=true %}}
Opens a new tab and switches to new tab:
{{< gh-codeblock path="/examples/ruby/spec/interactions/windows_spec.rb#L9" >}}

Opens a new window and switches to new window:
{{< gh-codeblock path="/examples/ruby/spec/interactions/windows_spec.rb#L15" >}}
  {{% /tab %}}
{{< tab header="JavaScript" text=true >}}
Opens a new tab and switches to new tab
{{< gh-codeblock path="examples/javascript/test/interactions/windows.spec.js#L70" >}}

Opens a new window and switches to new window:
{{< gh-codeblock path="examples/javascript/test/interactions/windows.spec.js#L75" >}}
{{< /tab >}}
  {{< tab header="Kotlin" >}}
// Opens a new tab and switches to new tab
driver.switchTo().newWindow(WindowType.TAB)

// Opens a new window and switches to new window
driver.switchTo().newWindow(WindowType.WINDOW)
  {{< /tab >}}
{{< /tabpane >}}



### Quitting the browser at the end of a session

When you are finished with the browser session you should call quit,
instead of close:

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
     {{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/WindowsTest.java#L44-L45" >}}
{{< /tab >}}
  {{< tab header="Python" >}}driver.quit(){{< /tab >}}
  {{< tab header="CSharp" >}}driver.Quit();{{< /tab >}}
  {{< tab header="Ruby" >}}driver.quit{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.quit();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.quit(){{< /tab >}}
{{< /tabpane >}}

* Quit will:
  * Close all the windows and tabs associated with that WebDriver
  session
  * Close the browser process
  * Close the background driver process
  * Notify Selenium Grid that the browser is no longer in use so it can
   be used by another session (if you are using Selenium Grid)

Failure to call quit will leave extra background processes and ports
running on your machine which could cause you problems later.

Some test frameworks offer methods and annotations which you can hook
into to tear down at the end of a test.

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
  {{< tab header="Java" >}}
/**
 * Example using JUnit
 * https://junit.org/junit5/docs/current/api/org/junit/jupiter/api/AfterAll.html
 */
@AfterAll
public static void tearDown() {
    driver.quit();
}
  {{< /tab >}}
  {{< tab header="Python" >}}
    # unittest teardown
    # https://docs.python.org/3/library/unittest.html?highlight=teardown#unittest.TestCase.tearDown
def tearDown(self):
    self.driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
/*
    Example using Visual Studio's UnitTesting
    https://msdn.microsoft.com/en-us/library/microsoft.visualstudio.testtools.unittesting.aspx
*/
[TestCleanup]
public void TearDown()
{
    driver.Quit();
}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    # UnitTest Teardown
    # https://www.rubydoc.info/github/test-unit/test-unit/Test/Unit/TestCase
def teardown
    @driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
/**
 * Example using Mocha
 * https://mochajs.org/#hooks
 */
after('Tear down', async function () {
  await driver.quit();
});
  {{< /tab >}}
  {{< tab header="Kotlin" >}}

/**
 * Example using JUnit
 * https://junit.org/junit5/docs/current/api/org/junit/jupiter/api/AfterAll.html
 */
@AfterAll
fun tearDown() {
    driver.quit()
}
  {{< /tab >}}
{{< /tabpane >}}

If not running WebDriver in a test context, you may consider using
`try  / finally` which is offered by most languages so that an exception
will still clean up the WebDriver session.

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
  {{< tab header="Java" >}}
try {
    //WebDriver code here...
} finally {
    driver.quit();
}
  {{< /tab >}}
  {{< tab header="Python" >}}
try:
    #WebDriver code here...
finally:
    driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
try {
    //WebDriver code here...
} finally {
    driver.Quit();
}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
begin
    #WebDriver code here...
ensure
    driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
try {
    //WebDriver code here...
} finally {
    await driver.quit();
}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
try {
    //WebDriver code here...
} finally {
    driver.quit()
}
  {{< /tab >}}
{{< /tabpane >}}

Python's WebDriver now supports the python context manager,
which when using the `with` keyword can automatically quit the driver at
the end of execution.

```python
with webdriver.Firefox() as driver:
  # WebDriver code here...

# WebDriver will automatically quit after indentation
```

## Window management
Screen resolution can impact how your web application renders, so
WebDriver provides mechanisms for moving and resizing the browser
window.

### Get window size
Fetches the size of the browser window in pixels.

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
  {{< tab header="Java" >}}
//Access each dimension individually
int width = driver.manage().window().getSize().getWidth();
int height = driver.manage().window().getSize().getHeight();

//Or store the dimensions and query them later
Dimension size = driver.manage().window().getSize();
int width1 = size.getWidth();
int height1 = size.getHeight();
  {{< /tab >}}
  {{< tab header="Python" >}}
    # Access each dimension individually
width = driver.get_window_size().get("width")
height = driver.get_window_size().get("height")

    # Or store the dimensions and query them later
size = driver.get_window_size()
width1 = size.get("width")
height1 = size.get("height")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
//Access each dimension individually
int width = driver.Manage().Window.Size.Width;
int height = driver.Manage().Window.Size.Height;

//Or store the dimensions and query them later
System.Drawing.Size size = driver.Manage().Window.Size;
int width1 = size.Width;
int height1 = size.Height;
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    # Access each dimension individually
width = driver.manage.window.size.width
height = driver.manage.window.size.height

    # Or store the dimensions and query them later
size = driver.manage.window.size
width1 = size.width
height1 = size.height
  {{< /tab >}}
{{< tab header="JavaScript" text=true >}}
Access each dimension individually
{{< gh-codeblock path="examples/javascript/test/interactions/windows.spec.js#L93" >}}

(or) store the dimensions and query them later
{{< gh-codeblock path="examples/javascript/test/interactions/windows.spec.js#L96-L98" >}}
{{< /tab >}}
  {{< tab header="Kotlin" >}}
//Access each dimension individually
val width = driver.manage().window().size.width
val height = driver.manage().window().size.height

//Or store the dimensions and query them later
val size = driver.manage().window().size
val width1 = size.width
val height1 = size.height
  {{< /tab >}}
{{< /tabpane >}}

### Set window size

Restores the window and sets the window size.
{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
  {{< tab header="Java" >}}driver.manage().window().setSize(new Dimension(1024, 768));{{< /tab >}}
  {{< tab header="Python" >}}driver.set_window_size(1024, 768){{< /tab >}}
  {{< tab header="CSharp" >}}driver.Manage().Window.Size = new Size(1024, 768);{{< /tab >}}
  {{< tab header="Ruby" >}}driver.manage.window.resize_to(1024,768){{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.manage().window().setRect({ width: 1024, height: 768 });{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.manage().window().size = Dimension(1024, 768){{< /tab >}}
{{< /tabpane >}}

### Get window position

Fetches the coordinates of the top left coordinate of the browser window.

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
  {{< tab header="Java" >}}
// Access each dimension individually
int x = driver.manage().window().getPosition().getX();
int y = driver.manage().window().getPosition().getY();

// Or store the dimensions and query them later
Point position = driver.manage().window().getPosition();
int x1 = position.getX();
int y1 = position.getY();
  {{< /tab >}}
  {{< tab header="Python" >}}
    # Access each dimension individually
x = driver.get_window_position().get('x')
y = driver.get_window_position().get('y')

    # Or store the dimensions and query them later
position = driver.get_window_position()
x1 = position.get('x')
y1 = position.get('y')
  {{< /tab >}}
  {{< tab header="CSharp" >}}
//Access each dimension individually
int x = driver.Manage().Window.Position.X;
int y = driver.Manage().Window.Position.Y;

//Or store the dimensions and query them later
Point position = driver.Manage().Window.Position;
int x1 = position.X;
int y1 = position.Y;
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    #Access each dimension individually
x = driver.manage.window.position.x
y = driver.manage.window.position.y

    # Or store the dimensions and query them later
rect  = driver.manage.window.rect
x1 = rect.x
y1 = rect.y
  {{< /tab >}}
{{< tab header="JavaScript" text=true >}}
Access each dimension individually
{{< gh-codeblock path="examples/javascript/test/interactions/windows.spec.js#L108" >}}

(or) store the dimensions and query them later
{{< gh-codeblock path="examples/javascript/test/interactions/windows.spec.js#L111-L113" >}}
{{< /tab >}}
  {{< tab header="Kotlin" >}}
// Access each dimension individually
val x = driver.manage().window().position.x
val y = driver.manage().window().position.y

// Or store the dimensions and query them later
val position = driver.manage().window().position
val x1 = position.x
val y1 = position.y

  {{< /tab >}}
{{< /tabpane >}}

## Set window position

Moves the window to the chosen position.

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
  {{< tab header="Java" >}}
// Move the window to the top left of the primary monitor
driver.manage().window().setPosition(new Point(0, 0));
  {{< /tab >}}
  {{< tab header="Python" >}}
    # Move the window to the top left of the primary monitor
driver.set_window_position(0, 0)
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// Move the window to the top left of the primary monitor
driver.Manage().Window.Position = new Point(0, 0);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
driver.manage.window.move_to(0,0)
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Move the window to the top left of the primary monitor
await driver.manage().window().setRect({ x: 0, y: 0 });
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
// Move the window to the top left of the primary monitor
driver.manage().window().position = Point(0,0)
    {{< /tab >}}
{{< /tabpane >}}

### Maximize window
Enlarges the window. For most operating systems, the window will fill
the screen, without blocking the operating system's own menus and
toolbars.

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
  {{< tab header="Java" >}}driver.manage().window().maximize();{{< /tab >}}
  {{< tab header="Python" >}}driver.maximize_window(){{< /tab >}}
  {{< tab header="CSharp" >}}driver.Manage().Window.Maximize();{{< /tab >}}
  {{< tab header="Ruby" >}}driver.manage.window.maximize{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.manage().window().maximize();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.manage().window().maximize(){{< /tab >}}
{{< /tabpane >}}

### Minimize window
Minimizes the window of current browsing context.
The exact behavior of this command is specific to
individual window managers.

Minimize Window typically hides the window in the system tray.

__Note: This feature works with Selenium 4 and later versions.__

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
  {{< tab header="Java" >}}driver.manage().window().minimize();{{< /tab >}}
  {{< tab header="Python" >}}driver.minimize_window(){{< /tab >}}
  {{< tab header="CSharp" >}}driver.Manage().Window.Minimize();{{< /tab >}}
  {{< tab header="Ruby" >}}driver.manage.window.minimize{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.manage().window().minimize();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.manage().window().minimize(){{< /tab >}}
{{< /tabpane >}}

### Fullscreen window

Fills the entire screen, similar to pressing F11 in most browsers.

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
  {{< tab header="Java" >}}driver.manage().window().fullscreen();{{< /tab >}}
  {{< tab header="Python" >}}driver.fullscreen_window(){{< /tab >}}
  {{< tab header="CSharp" >}}driver.Manage().Window.FullScreen();{{< /tab >}}
  {{< tab header="Ruby" >}}driver.manage.window.full_screen{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.manage().window().fullscreen();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.manage().window().fullscreen(){{< /tab >}}
{{< /tabpane >}}

### TakeScreenshot

Used to capture screenshot for current browsing context.
The WebDriver endpoint [screenshot](https://www.w3.org/TR/webdriver/#dfn-take-screenshot)
returns screenshot which is encoded in Base64 format.

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
  {{< tab header="Java" >}}
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
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver

driver = webdriver.Chrome()

driver.get("http://www.example.com")

    # Returns and base64 encoded string into image
driver.save_screenshot('./image.png')

driver.quit()

{{< /tab >}}
  {{< tab header="CSharp" >}}
  using OpenQA.Selenium;
  using OpenQA.Selenium.Chrome;
  using OpenQA.Selenium.Support.UI;

  var driver = new ChromeDriver();
  driver.Navigate().GoToUrl("http://www.example.com");
  Screenshot screenshot = (driver as ITakesScreenshot).GetScreenshot();
  screenshot.SaveAsFile("screenshot.png", ScreenshotImageFormat.Png); // Format values are Bmp, Gif, Jpeg, Png, Tiff
  {{< /tab >}}
  {{< tab header="Ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :chrome

begin
  driver.get 'https://example.com/'

    # Takes and Stores the screenshot in specified path
  driver.save_screenshot('./image.png')

end
  {{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="examples/javascript/test/interactions/windows.spec.js#L56-L59" >}}
{{< /tab >}}
  {{< tab header="Kotlin" >}}
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
   {{< /tab >}}
{{< /tabpane >}}

###  TakeElementScreenshot

Used to capture screenshot of an element for current browsing context.
The WebDriver endpoint [screenshot](https://www.w3.org/TR/webdriver/#take-element-screenshot)
returns screenshot which is encoded in Base64 format.

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
  {{< tab header="Java" >}}
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
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver
from selenium.webdriver.common.by import By

driver = webdriver.Chrome()

driver.get("http://www.example.com")

ele = driver.find_element(By.CSS_SELECTOR, 'h1')

    # Returns and base64 encoded string into image
ele.screenshot('./image.png')

driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
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
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    # Works with Selenium4-alpha7 Ruby bindings and above
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :chrome

begin
  driver.get 'https://example.com/'
  ele = driver.find_element(:css, 'h1')

    # Takes and Stores the element screenshot in specified path
  ele.save_screenshot('./image.jpg')
end
  {{< /tab >}}
  {{< tab header="JavaScript" text=true >}}
  {{< gh-codeblock path="examples/javascript/test/interactions/windows.spec.js#L44-L48" >}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
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
  {{< /tab >}}
{{< /tabpane >}}


### Execute Script

Executes JavaScript code snippet in the
current context of a selected frame or window.

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
  {{< tab header="Java" >}}
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
  {{< /tab >}}
  {{< tab header="Python" >}}
    # Stores the header element
header = driver.find_element(By.CSS_SELECTOR, "h1")

    # Executing JavaScript to capture innerText of header element
driver.execute_script('return arguments[0].innerText', header)
  {{< /tab >}}
  {{< tab header="CSharp" >}}
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
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    # Stores the header element
header = driver.find_element(css: 'h1')

    # Get return value from script
result = driver.execute_script("return arguments[0].innerText", header)

    # Executing JavaScript directly
driver.execute_script("alert('hello world')")
  {{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="examples/javascript/test/interactions/windows.spec.js#L33-L37" >}}
{{< /tab >}}
  {{< tab header="Kotlin" >}}
// Stores the header element
val header = driver.findElement(By.cssSelector("h1"))

// Get return value from script
val result = driver.executeScript("return arguments[0].innerText", header)

// Executing JavaScript directly
driver.executeScript("alert('hello world')")
  {{< /tab >}}
{{< /tabpane >}}

### Print Page

Prints the current page within the browser.

_Note: This requires Chromium Browsers to be in headless mode_


{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
  {{< tab header="Java" >}}
    import org.openqa.selenium.print.PrintOptions;

    driver.get("https://www.selenium.dev");
    printer = (PrintsPage) driver;

    PrintOptions printOptions = new PrintOptions();
    printOptions.setPageRanges("1-2");

    Pdf pdf = printer.print(printOptions);
    String content = pdf.getContent();
  {{< /tab >}}
  {{< tab header="Python" >}}
    from selenium.webdriver.common.print_page_options import PrintOptions

    print_options = PrintOptions()
    print_options.page_ranges = ['1-2']

    driver.get("printPage.html")

    base64code = driver.print_page(print_options)
  {{< /tab >}}
  {{< tab header="CSharp" >}}
    // code sample not available please raise a PR
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    driver.navigate_to 'https://www.selenium.dev'

    base64encodedContent = driver.print_page(orientation: 'landscape')
  {{< /tab >}}
  {{< tab header="JavaScript" text=true >}}
  {{< gh-codeblock path="examples/javascript/test/interactions/windows.spec.js#L22-L25" >}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
    driver.get("https://www.selenium.dev")
    val printer = driver as PrintsPage

    val printOptions = PrintOptions()
    printOptions.setPageRanges("1-2")
    
    val pdf: Pdf = printer.print(printOptions)
    val content = pdf.content
  {{< /tab >}}
{{< /tabpane >}}

