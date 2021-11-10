---
title: "Browser manipulation"
linkTitle: "브라우저 조작"
weight: 2
aliases: ["/documentation/ko/webdriver/browser_manipulation/"]
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to Korean. Do you speak Korean? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}


## Ruby

Ruby는 기본적으로 Windows에 설치되지 않습니다. [최신 버전](//rubyinstaller.org/downloads)을 다운로드하고 설치 프로그램을 실행합니다. _설치 대상 및 선택 작업 화면 체크_ 에서 _PATH에 Ruby 실행 파일 추가 확인란_ 을 선택해야 하는 경우를 제외하고 모든 설정을 기본값으로 유지할 수 있습니다. 브라우저를 구동하려면 Selenium 웹드라이버 Ruby gem을 설치해야 합니다. 설치하려면 명령 프롬프트를 열고 다음을 입력합니다:

```shell
gem install selenium-webdriver
```

또는 [Bundler](//bundler.io)를 사용하는 경우 다음 줄을 응용 프로그램의 Gemfile에 추가하십시오.
Gemfile:

```ruby
gem "selenium-webdriver"
```
그 후에 명령 프롬프트에서 다음 명령을 실행합니다:

```shell
bundle install
```


## 인터넷 익스플로러

Internet Explorer(인터넷 익스플로러)는 기본적으로 Windows(윈도우)에 설치되어 있으므로 설치할 필요가 없습니다. Windows에서 Internet Explorer를 구동하려면 [최신 Internet Explorer 드라이버](https://selenium.dev/downloads/)를 다운로드하여 파일을 'PATH'에 있는 폴더에 넣어야 합니다. 'PATH'에 있는 디렉터리를 확인하려면 명령 프롬프트에 'echo %PATH%'를 입력하십시오.

```bat
$ echo %PATH%
C:\Ruby200\bin;C:\WINDOWS\system32;C:\WINDOWS;C:\WINDOWS\System32\Wbem
```

`C:\Ruby200\bin` looks like a good place. Unzip `IEDriverServer` file and
move `IEDriverServer.exe` there.

This should open a new Internet Explorer window:

'C: Ruby200\bin'은 좋은 위치처럼 보입니다. 'IEDriverServer' 파일의 압축을 풀고 IEDriver서버.exe를 이동시킵니다.

이렇게 하면 새 Internet Explorer 창이 열립니다:

```ruby
require "selenium-webdriver"
driver = Selenium::WebDriver.for :internet_explorer
```

## 브라우저 탐색

### 원하는 위치로 이동

브라우저를 시작한 후 가장 먼저 하고 싶은 일은 웹 사이트를 여는 것입니다. 
이 작업은 한 줄로 수행할 수 있습니다:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
//Convenient
driver.get("https://selenium.dev");

//Longer way
driver.navigate().to("https://selenium.dev");
  {{< /tab >}}
  {{< tab header="Python" >}}
driver.get("https://selenium.dev")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
driver.Navigate().GoToUrl(@"https://selenium.dev");
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Convenient way
driver.get 'https://selenium.dev'

# Longer Way
driver.navigate.to 'https://selenium.dev'
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
await driver.get('https://selenium.dev');
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
//Convenient
driver.get("https://selenium.dev")

//Longer way
driver.navigate().to("https://selenium.dev")
  {{< /tab >}}
{{< /tabpane >}}

### 현재 URL을 얻기

다음을 사용하여 브라우저의 주소 표시줄에서 현재 URL을 읽을 수 있습니다:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.getCurrentUrl();{{< /tab >}}
  {{< tab header="Python" >}}driver.current_url{{< /tab >}}
  {{< tab header="CSharp" >}}driver.Url;{{< /tab >}}
  {{< tab header="Ruby" >}}driver.current_url{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.getCurrentUrl();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.currentUrl{{< /tab >}}
{{< /tabpane >}}

### 뒤로 가기

브라우저의 '뒤로 가기' 버튼을 누릅니다:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.navigate().back();{{< /tab >}}
  {{< tab header="Python" >}}driver.back(){{< /tab >}}
  {{< tab header="CSharp" >}}driver.Navigate().Back();{{< /tab >}}
  {{< tab header="Ruby" >}}driver.navigate.back{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.navigate().back();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.navigate().back() {{< /tab >}}
{{< /tabpane >}}


### 앞으로 가기
브라우저의 '앞으로 가기' 버튼을 누릅니다:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.navigate().forward();{{< /tab >}}
  {{< tab header="Python" >}}driver.forward(){{< /tab >}}
  {{< tab header="CSharp" >}}driver.Navigate().Forward();{{< /tab >}}
  {{< tab header="Ruby" >}}driver.navigate.forward{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.navigate().forward();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.navigate().forward(){{< /tab >}}
{{< /tabpane >}}

### 새로고침

현재 페이지를 새로고침합니다:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.navigate().refresh();{{< /tab >}}
  {{< tab header="Python" >}}driver.refresh(){{< /tab >}}
  {{< tab header="CSharp" >}}driver.Navigate().Refresh();{{< /tab >}}
  {{< tab header="Ruby" >}}driver.navigate.refresh{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.navigate().refresh();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.navigate().refresh(){{< /tab >}}
{{< /tabpane >}}

### 제목 얻기

브라우저에서 현재 페이지 제목을 읽을 수 있습니다:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.getTitle();{{< /tab >}}
  {{< tab header="Python" >}}driver.title{{< /tab >}}
  {{< tab header="CSharp" >}}driver.Title;{{< /tab >}}
  {{< tab header="Ruby" >}}driver.title{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.getTitle();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.title{{< /tab >}}
{{< /tabpane >}}


## 창과 탭

### 창 handle 얻어오기

웹드라이버는 창과 탭을 구분하지 않습니다. 사이트에서 새 탭이나 창을 열면 Selenium에서 창 핸들을 사용하여 해당 탭이나 창을 사용할 수 있습니다. 각 창에는 단일 세션에서 지속적으로 유지되는 고유 식별자가 있습니다. 다음을 사용하여 현재 창의 창 핸들을 가져올 수 있습니다:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.getWindowHandle();{{< /tab >}}
  {{< tab header="Python" >}}driver.current_window_handle{{< /tab >}}
  {{< tab header="CSharp" >}}driver.CurrentWindowHandle;{{< /tab >}}
  {{< tab header="Ruby" >}}driver.window_handle{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.getWindowHandle();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.windowHandle{{< /tab >}}
{{< /tabpane >}}

### 창 또는 탭 전환

<a href="https://seleniumhq.github.io" target="_blank"> 새 창</a> 에서 열리는 링크를 클릭하면 화면의 새 창이나 탭에 초점이 맞춰지지만, WebDriver는 운영 체제가 활성으로 간주하는 창을 알 수 없습니다. 새 창으로 작업하려면 창으로 전환해야 합니다. 탭이나 창이 두 개만 열려 있고 제거 과정을 통해 어느 창으로 시작하는지 알 수 있는 경우 WebDriver에서 볼 수 있는 창이나 탭을 모두 반복하고 원본이 아닌 창으로 전환할 수 있습니다.

그러나 Selenium 4는 새 탭(또는) 창을 만들고 자동으로 새 창으로 전환하는 새로운 api인 [NewWindow](#create-new-window-or-new-tab-and-switch)를 제공합니다.

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

### 새 창 만들기(또는) 새 탭 만들기 후 전환

새 창(또는) 탭을 만들고 화면의 새 창 또는 탭에 초점을 맞춥니다. 새 창 탭으로 전환할 필요가 없습니다. 새 창 외에 세 개 이상의 창(또는) 탭이 열려 있는 경우 WebDriver에서 볼 수 있는 두 창 또는 탭을 모두 루프하고 원본이 아닌 창으로 전환할 수 있습니다.

__참고: 이 기능은 Selenium 4 이상 버전에서 작동합니다.__

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

### 창 또는 탭 닫기

창이나 탭이 브라우저에 열려 있는 마지막 창이나 탭이 아닌 경우 창을 닫고 이전에 사용하던 창으로 다시 전환해야 합니다. 이전 섹션의 예시 코드을 따랐다고 가정하면 이전의 windows 핸들이 변수에 저장됩니다. 이를 종합하면 다음과 같은 결과를 얻을 수 있습니다:

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

창을 닫은 후 다른 창 핸들로 다시 전환하는 것을 잊어버리면 웹드라이버가 지금 닫힌 페이지에서 실행 상태로 남게 되고 **No Such Window Exception** 을 트리거하게 됩니다. 실행을 계속하려면 올바른 창 핸들로 다시 전환해야 합니다.

### 세션이 끝날 때 브라우저 종료

브라우저 세션을 마쳤으면 닫는 대신 종료로 호출해야 합니다:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.quit();{{< /tab >}}
  {{< tab header="Python" >}}driver.quit(){{< /tab >}}
  {{< tab header="CSharp" >}}driver.Quit();{{< /tab >}}
  {{< tab header="Ruby" >}}driver.quit{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.quit();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.quit(){{< /tab >}}
{{< /tabpane >}}

* 종료 의지:
  * 해당 WebDriver 세션과 연결된 모든 창 및 탭 닫기
  * 브라우저 프로세스 닫기
  * 백그라운드 드라이버 프로세스 닫기
  * 브라우저가 더 이상 사용되지 않으므로 다른 세션에서 사용할 수 있음을 Selenium 그리드에 알림(Selenium 그리드를 사용하는 경우).


종료를 호출하지 못하면 추가 백그라운드 프로세스와 포트가 컴퓨터에서 실행되어 나중에 문제가 발생할 수 있습니다.

일부 테스트 프레임워크는 테스트 종료 시 해체할 수 있는 방법과 주석을 제공합니다.

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

테스트 컨텍스트에서 웹드라이버를 실행하지 않는 경우, 당신은 예외가 여전히 웹드라이버 세션을 정리할 수 있도록 대부분의 언어에 의해 제공되는 ' try/finally' 로 사용을 고려할 수 있습니다.

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

Python's WebDriver now supports the python context manager,
which when using the `with` keyword can automatically quit the driver at
the end of execution.

Python의 WebDriver는 이제 Python 문맥 관리자를 지원하는데, 이것은 'with' 키워드를 사용할 때 실행이 끝날 때 자동으로 드라이버를 종료할 수 있습니다.

```python
with webdriver.Firefox() as driver:
  # WebDriver code here...

# WebDriver will automatically quit after indentation
```

## 프레임 및 Iframes
프레임은 이제 동일한 도메인에 있는 여러 문서에서 사이트 레이아웃을 작성하는 데 사용되지 않는 수단입니다. HTML5 이전 웹앱으로 작업하지 않는 한 이러한 웹앱과 함께 작업할 수 없습니다. iframe은 완전히 다른 도메인에서 문서를 삽입할 수 있도록 허용하며, 여전히 일반적으로 사용됩니다.

프레임이나 iframe으로 작업해야 하는 경우, WebDriver를 통해 동일한 방식으로 작업할 수 있습니다. iframe 내의 버튼을 고려합니다. 브라우저 개발 도구를 사용하여 요소를 검사하면 다음을 확인할 수 있습니다:

```html
<div id="modal">
  <iframe id="buttonframe" name="myframe"  src="https://seleniumhq.github.io">
   <button>Click here</button>
 </iframe>
</div>
```

iframe이 아니었다면 다음과 같은 방법으로 버튼을 클릭할 수 있을 것입니다:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
//This won't work
driver.findElement(By.tagName("button")).click();
  {{< /tab >}}
  {{< tab header="Python" >}}
# This Wont work
driver.find_element(By.TAG_NAME, 'button').click()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
//This won't work
driver.FindElement(By.TagName("button")).Click();
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# This won't work
driver.find_element(:tag_name,'button').click
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// This won't work
await driver.findElement(By.css('button')).click();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
//This won't work
driver.findElement(By.tagName("button")).click()
  {{< /tab >}}
{{< /tabpane >}}

그러나 iframe 외부에 단추가 없는 경우 대신 no-reflement 오류가 발생할 수 있습니다. 이 문제는 셀레늄이 최상위 문서의 요소만 인식하기 때문에 발생합니다. 버튼과 상호 작용하려면 먼저 창 전환 방법과 유사한 방식으로 프레임으로 전환해야 합니다. 웹드라이버는 프레임으로 전환하는 세 가지 방법을 제공합니다.

### WebElement 사용

WebElement를 사용하여 전환하는 것이 가장 유연한 옵션입니다. 원하는 선택기를 사용하여 프레임을 찾고 프레임으로 전환할 수 있습니다.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
//Store the web element
WebElement iframe = driver.findElement(By.cssSelector("#modal>iframe"));

//Switch to the frame
driver.switchTo().frame(iframe);

//Now we can click the button
driver.findElement(By.tagName("button")).click();
  {{< /tab >}}
  {{< tab header="Python" >}}
# Store iframe web element
iframe = driver.find_element(By.CSS_SELECTOR, "#modal > iframe")

# switch to selected iframe
driver.switch_to.frame(iframe)

# Now click on button
driver.find_element(By.TAG_NAME, 'button').click()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
//Store the web element
IWebElement iframe = driver.FindElement(By.CssSelector("#modal>iframe"));

//Switch to the frame
driver.SwitchTo().Frame(iframe);

//Now we can click the button
driver.FindElement(By.TagName("button")).Click();
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Store iframe web element
iframe = driver.find_element(:css,'#modal > iframe')

# Switch to the frame
driver.switch_to.frame iframe

# Now, Click on the button
driver.find_element(:tag_name,'button').click
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Store the web element
const iframe = driver.findElement(By.css('#modal > iframe'));

// Switch to the frame
await driver.switchTo().frame(iframe);

// Now we can click the button
await driver.findElement(By.css('button')).click();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
//Store the web element
val iframe = driver.findElement(By.cssSelector("#modal>iframe"))

//Switch to the frame
driver.switchTo().frame(iframe)

//Now we can click the button
driver.findElement(By.tagName("button")).click()
  {{< /tab >}}
{{< /tabpane >}}

### 이름 또는 ID 사용
프레임 또는 iframe에 ID 또는 이름 속성이 있는 경우 대신 사용할 수 있습니다. 
이름 또는 ID가 페이지에서 고유하지 않은 경우, 처음 발견된 ID가 로 전환됩니다.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
//Using the ID
driver.switchTo().frame("buttonframe");

//Or using the name instead
driver.switchTo().frame("myframe");

//Now we can click the button
driver.findElement(By.tagName("button")).click();
  {{< /tab >}}
  {{< tab header="Python" >}}
# Switch frame by id
driver.switch_to.frame('buttonframe')

# Now, Click on the button
driver.find_element(By.TAG_NAME, 'button').click()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
//Using the ID
driver.SwitchTo().Frame("buttonframe");

//Or using the name instead
driver.SwitchTo().Frame("myframe");

//Now we can click the button
driver.FindElement(By.TagName("button")).Click();
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Switch by ID
driver.switch_to.frame 'buttonframe'

# Now, Click on the button
driver.find_element(:tag_name,'button').click
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Using the ID
await driver.switchTo().frame('buttonframe');

// Or using the name instead
await driver.switchTo().frame('myframe');

// Now we can click the button
await driver.findElement(By.css('button')).click();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
//Using the ID
driver.switchTo().frame("buttonframe")

//Or using the name instead
driver.switchTo().frame("myframe")

//Now we can click the button
driver.findElement(By.tagName("button")).click()
  {{< /tab >}}
{{< /tabpane >}}

### 인덱스 사용하기

프레임의 인덱스를 사용할 수도 있습니다. 
예를 들어, 자바스크립트에서 _window.frames_ 를 사용하여 쿼리할 수 있습니다.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
// Switches to the second frame
driver.switchTo().frame(1);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Switch to the second frame
driver.switch_to.frame(1)
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// Switches to the second frame
driver.SwitchTo().Frame(1);
  {{< /tab >}}
  {{< tab header="Python" >}}
# switching to second iframe based on index
iframe = driver.find_elements_by_tag_name('iframe')[1]

# switch to selected iframe
driver.switch_to.frame(iframe)
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Switches to the second frame
await driver.switchTo().frame(1);
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
// Switches to the second frame
driver.switchTo().frame(1)
  {{< /tab >}}
{{< /tabpane >}}


### 프레임 남기기

iframe 또는 프레임셋을 그대로 두려면 다음과 같은 기본 콘텐츠로 다시 전환합니다:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
// Return to the top level
driver.switchTo().defaultContent();
  {{< /tab >}}
  {{< tab header="Python" >}}
# switch back to default content
driver.switch_to.default_content()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// Return to the top level
driver.SwitchTo().DefaultContent();
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Return to the top level
driver.switch_to.default_content
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Return to the top level
await driver.switchTo().defaultContent();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
// Return to the top level
driver.switchTo().defaultContent()
  {{< /tab >}}
{{< /tabpane >}}

## 창 관리
화면 해상도는 웹 응용 프로그램이 렌더링하는 방법에 영향을 미칠 수 있으므로 웹 드라이버는 브라우저 창을 이동하고 크기를 조정하는 메커니즘을 제공합니다.

### 창 크기 가져오기
브라우저 창의 크기(픽셀)을 가져옵니다.

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

### 창 크기 설정

창을 복원하고 창 크기를 설정합니다.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.manage().window().setSize(new Dimension(1024, 768));{{< /tab >}}
  {{< tab header="Python" >}}driver.set_window_size(1024, 768){{< /tab >}}
  {{< tab header="CSharp" >}}driver.Manage().Window.Size = new Size(1024, 768);{{< /tab >}}
  {{< tab header="Ruby" >}}driver.manage.window.resize_to(1024,768){{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.manage().window().setRect({ width: 1024, height: 768 });{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.manage().window().size = Dimension(1024, 768){{< /tab >}}
{{< /tabpane >}}

### 창 위치 가져오기

브라우저 창의 왼쪽 상단 좌표의 좌표를 가져옵니다.

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

## 창 위치 설정

창을 선택한 위치로 이동시킵니다.

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

### 창 최대화

창을 확대합니다. 대부분의 운영 체제에서 창은 운영 체제의 자체 메뉴와 도구 모음을 차단하지 않고 화면을 채웁니다.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.manage().window().maximize();{{< /tab >}}
  {{< tab header="Python" >}}driver.maximize_window(){{< /tab >}}
  {{< tab header="CSharp" >}}driver.Manage().Window.Maximize();{{< /tab >}}
  {{< tab header="Ruby" >}}driver.manage.window.maximize{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.manage().window().maximize();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.manage().window().maximize(){{< /tab >}}
{{< /tabpane >}}

### 창 최소화
현재 검색 컨텍스트의 창을 최소화합니다. 이 명령의 정확한 동작은 개별 창 관리자에게만 적용됩니다.

최소화 창은 일반적으로 시스템 트레이에서 창을 숨깁니다.

__참고: 이 기능은 Selenium 4 이상 버전에서 작동합니다.__

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.manage().window().minimize();{{< /tab >}}
  {{< tab header="Python" >}}driver.minimize_window(){{< /tab >}}
  {{< tab header="CSharp" >}}driver.Manage().Window.Minimize();{{< /tab >}}
  {{< tab header="Ruby" >}}driver.manage.window.minimize{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.manage().window().minimize();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.manage().window().minimize(){{< /tab >}}
{{< /tabpane >}}

### 전체 화면 창

대부분의 브라우저에서 F11 키를 누르는 것과 유사하게 전체 화면을 채웁니다.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.manage().window().fullscreen();{{< /tab >}}
  {{< tab header="Python" >}}driver.fullscreen_window(){{< /tab >}}
  {{< tab header="CSharp" >}}driver.Manage().Window.FullScreen();{{< /tab >}}
  {{< tab header="Ruby" >}}driver.manage.window.full_screen{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.manage().window().fullscreen();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.manage().window().fullscreen(){{< /tab >}}
{{< /tabpane >}}

### 스크린 샷

현재 검색 컨텍스트의 스크린샷을 캡처하는 데 사용됩니다. WebDriver 엔드포인트 [스크린샷](https://www.w3.org/TR/webdriver/#dfn-take-screenshot)은 Base64 형식으로 인코딩된 스크린샷을 반환합니다.

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

	
###  특정 요소 스크린샷

요소의 스크린샷을 캡처하여 현재 검색 컨텍스트에 사용할 수 있습니다. WebDriver 엔드포인트 [스크린샷](https://www.w3.org/TR/webdriver/#take-element-screenshot)은 Base64 형식으로 인코딩된 스크린샷을 반환합니다.

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

### 스크립트 실행

선택한 프레임 또는 창의 현재 컨텍스트에서 JavaScript 코드 조각을 실행합니다.

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

### Print Page

브라우저 내에서 현재 페이지를 인쇄합니다.

__참고: 크롬 브라우저가 헤드리스 모드여야 합니다.__


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
  })();
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
