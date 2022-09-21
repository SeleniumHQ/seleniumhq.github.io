---
title: "ウィンドウとタブの操作"
linkTitle: "ウィンドウ"
weight: 8
aliases: [
"/ja/documentation/webdriver/browser/windows/"
]
---

## ウィンドウとタブ

### ウィンドウハンドルの取得

WebDriverは、ウィンドウとタブを区別しません。
サイトが新しいタブまたはウィンドウを開く場合、Seleniumはウィンドウハンドルを使って連動します。
各ウィンドウには一意の識別子があり、これは単一のセッションで持続します。
次のコードを使用して、現在のウィンドウのウィンドウハンドルを取得できます。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.getWindowHandle();{{< /tab >}}
  {{< tab header="Python" >}}driver.current_window_handle{{< /tab >}}
  {{< tab header="CSharp" >}}driver.CurrentWindowHandle;{{< /tab >}}
  {{< tab header="Ruby" >}}driver.window_handle{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.getWindowHandle();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.windowHandle{{< /tab >}}
{{< /tabpane >}}

### ウィンドウまたはタブの切り替え

<a href="https://seleniumhq.github.io" target="_blank"> 新しいウィンドウ</a>で開くリンクをクリックすると、新しいウィンドウまたはタブが画面にフォーカスされますが、WebDriverはオペレーティングシステムがアクティブと見なすウィンドウを認識しません。
新しいウィンドウで作業するには、それに切り替える必要があります。
開いているタブまたはウィンドウが2つしかなく、どちらのウィンドウから開始するかがわかっている場合、削除のプロセスによって、WebDriverが表示できる両方のウィンドウまたはタブをループし、元のウィンドウまたはタブに切り替えることができます。

ただし、Selenium 4には、新しいタブ（または）新しいウィンドウを作成して自動的に切り替える新しいAPI [NewWindow](#新しいウィンドウまたは新しいタブを作成して切り替える) が用意されています。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
//Store the ID of the original window
String originalWindow = driver.getWindowHandle();

//Check we don't have other windows open already
assert driver.getWindowHandles().size() == 1;

//Click the link which opens in a new window
driver.findElement(By.linkText("new window")).click();

//Wait for the new window or tab
wait.until(numberOfWindowsToBe(2));

//Loop through until we find a new window handle
for (String windowHandle : driver.getWindowHandles()) {
    if(!originalWindow.contentEquals(windowHandle)) {
        driver.switchTo().window(windowHandle);
        break;
    }
}

//Wait for the new tab to finish loading content
wait.until(titleIs("Selenium documentation"));
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

    # Start the driver
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

### 新しいウィンドウ（または）新しいタブを作成して切り替える

新しいウィンドウ（または）タブを作成し、画面上の新しいウィンドウまたはタブにフォーカスします。
新しいウィンドウ（または）タブを使用するように切り替える必要はありません。
新しいウィンドウ以外に3つ以上のウィンドウ（または）タブを開いている場合、WebDriverが表示できる両方のウィンドウまたはタブをループして、元のものではないものに切り替えることができます。

__注意: この機能は、Selenium 4以降のバージョンで機能します。__

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
// Opens a new tab and switches to new tab
driver.switchTo().newWindow(WindowType.TAB);

// Opens a new window and switches to new window
driver.switchTo().newWindow(WindowType.WINDOW);
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
  {{< tab header="Ruby" >}}
    # Note: The new_window in ruby only opens a new tab (or) Window and will not switch automatically
    # The user has to switch to new tab (or) new window

    # Opens a new tab and switches to new tab
driver.manage.new_window(:tab)

    # Opens a new window and switches to new window
driver.manage.new_window(:window)
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Opens a new tab and switches to new tab
await driver.switchTo().newWindow('tab');

// Opens a new window and switches to new window
await driver.switchTo().newWindow('window');

  {{< /tab >}}
  {{< tab header="Kotlin" >}}
// Opens a new tab and switches to new tab
driver.switchTo().newWindow(WindowType.TAB)

// Opens a new window and switches to new window
driver.switchTo().newWindow(WindowType.WINDOW)
  {{< /tab >}}
{{< /tabpane >}}

### ウィンドウまたはタブを閉じる

ウィンドウまたはタブでの作業が終了し、 _かつ_ ブラウザーで最後に開いたウィンドウまたはタブではない場合、それを閉じて、以前使用していたウィンドウに切り替える必要があります。
前のセクションのコードサンプルに従ったと仮定すると、変数に前のウィンドウハンドルが格納されます。
これをまとめると以下のようになります。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
//Close the tab or window
driver.close();

//Switch back to the old tab or window
driver.switchTo().window(originalWindow);
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

ウィンドウを閉じた後に別のウィンドウハンドルに切り替えるのを忘れると、現在閉じられているページでWebDriverが実行されたままになり、 **No Such Window Exception** が発行されます。実行を継続するには、有効なウィンドウハンドルに切り替える必要があります。

### セッションの終了時にブラウザーを終了する

ブラウザーセッションを終了したら、closeではなく、quitを呼び出す必要があります。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.quit();{{< /tab >}}
  {{< tab header="Python" >}}driver.quit(){{< /tab >}}
  {{< tab header="CSharp" >}}driver.Quit();{{< /tab >}}
  {{< tab header="Ruby" >}}driver.quit{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.quit();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.quit(){{< /tab >}}
{{< /tabpane >}}

* Quitは、
  * そのWebDriverセッションに関連付けられているすべてのウィンドウとタブを閉じます
  * ブラウザーのプロセス
  * バックグラウンドのドライバーのプロセス
  * ブラウザーが使用されなくなったことをSelenium Gridに通知して、別のセッションで使用できるようにします（Selenium Gridを使用している場合）

quitの呼び出しに失敗すると、余分なバックグラウンドプロセスとポートがマシン上で実行されたままになり、後で問題が発生する可能性があります。

一部のテストフレームワークでは、テストの終了時にフックできるメソッドとアノテーションを提供しています。

{{< tabpane langEqualsHeader=true >}}
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

テストコンテキストでWebDriverを実行していない場合は、ほとんどの言語で提供されている `try  / finally` の使用を検討して、例外がWebDriverセッションをクリーンアップするようにします。

{{< tabpane langEqualsHeader=true >}}
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

PythonのWebDriverは、pythonコンテキストマネージャーをサポートするようになりました。
withキーワードを使用すると、実行終了時にドライバーを自動的に終了できます。

```python
with webdriver.Firefox() as driver:
  # WebDriver code here...

# WebDriver will automatically quit after indentation
```

## ウィンドウマネジメント

画面解像度はWebアプリケーションのレンダリング方法に影響を与える可能性があるため、WebDriverはブラウザーウィンドウを移動およびサイズ変更するメカニズムを提供します。

### ウィンドウサイズの取得

ブラウザーウィンドウのサイズをピクセル単位で取得します。

{{< tabpane langEqualsHeader=true >}}
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
  {{< tab header="JavaScript" >}}
// Access each dimension individually
const { width, height } = await driver.manage().window().getRect();

// Or store the dimensions and query them later
const rect = await driver.manage().window().getRect();
const width1 = rect.width;
const height1 = rect.height;
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

### ウィンドウサイズの設定

ウィンドウを復元し、ウィンドウサイズを設定します。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.manage().window().setSize(new Dimension(1024, 768));{{< /tab >}}
  {{< tab header="Python" >}}driver.set_window_size(1024, 768){{< /tab >}}
  {{< tab header="CSharp" >}}driver.Manage().Window.Size = new Size(1024, 768);{{< /tab >}}
  {{< tab header="Ruby" >}}driver.manage.window.resize_to(1024,768){{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.manage().window().setRect({ width: 1024, height: 768 });{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.manage().window().size = Dimension(1024, 768){{< /tab >}}
{{< /tabpane >}}

### ウィンドウの位置を取得

ブラウザーウィンドウの左上の座標を取得します。

{{< tabpane langEqualsHeader=true >}}
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
  {{< tab header="JavaScript" >}}
// Access each dimension individually
const { x, y } = await driver.manage().window().getRect();

// Or store the dimensions and query them later
const rect = await driver.manage().window().getRect();
const x1 = rect.x;
const y1 = rect.y;
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

    ## ウィンドウの位置設定

選択した位置にウィンドウを移動します。

{{< tabpane langEqualsHeader=true >}}
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

### ウィンドウの最大化

ウィンドウを拡大します。ほとんどのオペレーティングシステムでは、オペレーティングシステムのメニューとツールバーをブロックすることなく、ウィンドウが画面いっぱいに表示されます。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.manage().window().maximize();{{< /tab >}}
  {{< tab header="Python" >}}driver.maximize_window(){{< /tab >}}
  {{< tab header="CSharp" >}}driver.Manage().Window.Maximize();{{< /tab >}}
  {{< tab header="Ruby" >}}driver.manage.window.maximize{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.manage().window().maximize();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.manage().window().maximize(){{< /tab >}}
{{< /tabpane >}}

### ウィンドウを最小化
現在のブラウジングコンテキストのウィンドウを最小化します。
このコマンドの正確な動作は、個々のウィンドウマネージャーに固有のものです。

ウィンドウを最小化すると、通常、システムトレイのウィンドウが非表示になります。

__注：この機能は、Selenium 4以降のバージョンで機能します。__

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.manage().window().minimize();{{< /tab >}}
  {{< tab header="Python" >}}driver.minimize_window(){{< /tab >}}
  {{< tab header="CSharp" >}}driver.Manage().Window.Minimize();{{< /tab >}}
  {{< tab header="Ruby" >}}driver.manage.window.minimize{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.manage().window().minimize();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.manage().window().minimize(){{< /tab >}}
{{< /tabpane >}}

### 全画面ウィンドウ

ほとんどのブラウザーでF11を押すのと同じように、画面全体に表示されます。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.manage().window().fullscreen();{{< /tab >}}
  {{< tab header="Python" >}}driver.fullscreen_window(){{< /tab >}}
  {{< tab header="CSharp" >}}driver.Manage().Window.FullScreen();{{< /tab >}}
  {{< tab header="Ruby" >}}driver.manage.window.full_screen{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.manage().window().fullscreen();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.manage().window().fullscreen(){{< /tab >}}
{{< /tabpane >}}

### スクリーンショットの取得

現在のブラウジング コンテキストのスクリーンショットをキャプチャするために使います。  
WebDriver エンドポイントの [スクリーンショット](https://www.w3.org/TR/webdriver/#dfn-take-screenshot) は、
Base64 形式でエンコードされたスクリーンショットを返します。

{{< tabpane langEqualsHeader=true >}}
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

    # Navigate to url
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
  {{< tab header="JavaScript" >}}
let {Builder} = require('selenium-webdriver');
let fs = require('fs');

(async function example() {
    let driver = await new Builder()
      .forBrowser('chrome')
      .build();

    await driver.get('https://www.example.com');
    // Returns base64 encoded string
    let encodedString = await driver.takeScreenshot();
    await fs.writeFileSync('./image.png', encodedString, 'base64');
    await driver.quit();
}())
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

###  要素のスクリーンショットの取得

現在のブラウジング コンテキストの要素のスクリーンショットをキャプチャするために使います。 
WebDriver エンドポイントの [スクリーンショット](https://www.w3.org/TR/webdriver/#dfn-take-screenshot) は、
Base64 形式でエンコードされたスクリーンショットを返します。

{{< tabpane langEqualsHeader=true >}}
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

    # Navigate to url
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
  {{< tab header="JavaScript" >}}
const {Builder, By} = require('selenium-webdriver');
let fs = require('fs');

(async function example() {
   let driver = await new Builder()
       .forBrowser('chrome')
       .build();

   await driver.get('https://www.example.com');
   let ele = await driver.findElement(By.css("h1"));
   // Captures the element screenshot
   let encodedString = await ele.takeScreenshot(true);
   await fs.writeFileSync('./image.png', encodedString, 'base64');
   await driver.quit();
}())
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

### スクリプトの実行

選択したフレームまたはウィンドウの現在のコンテキストで、JavaScript コードスニペットを実行します。

{{< tabpane langEqualsHeader=true >}}
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
  {{< tab header="JavaScript" >}}
// Stores the header element
let header = await driver.findElement(By.css('h1'));

// Executing JavaScript to capture innerText of header element
let text = await driver.executeScript('return arguments[0].innerText', header);
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

### ページの印刷

ブラウザ内の現在のページを印刷します。

_Note: Chromium ブラウザがヘッドレスモードである必要があります。_


{{< tabpane langEqualsHeader=true >}}
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
  {{< tab header="JavaScript" >}}
  const {Builder} = require('selenium-webdriver');
  const chrome = require('selenium-webdriver/chrome');
  let opts = new chrome.Options();
  let fs = require('fs');
  (async function example() {
  let driver = new Builder()
  .forBrowser('chrome')
  .setChromeOptions(opts.headless())
  .build();
  await driver.get('https://www.selenium.dev');
  try {
  let base64 = await driver.printPage({pageRanges:["1-2"]});
  await fs.writeFileSync('./test.pdf', base64, 'base64');
  } catch (e) {
  console.log(e)
  }
  await driver.quit();
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

