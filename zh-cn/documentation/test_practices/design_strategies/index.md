# 设计模式和开发策略

URL: https://www.selenium.dev/zh-cn/documentation/test_practices/design_strategies/

---

（原位置： https://github.com/SeleniumHQ/selenium/wiki/Bot-Style-Tests）

## 概览 

随着时间推移, 项目往往会积累大量测试. 
随着测试总数的增加, 对代码库进行改动会变得更困难 —— 即便只是一次“简单”的改动, 
也可能导致大量测试失败, 而应用本身仍然能正常工作. 
有时这种问题不可避免, 但一旦发生, 你希望能尽快恢复运行. 
下面介绍的一些设计模式与策略在 WebDriver 场景下被证明可以简化测试编写与维护, 
可能也对你有帮助. 

[领域驱动设计](https://www.selenium.dev/zh-cn/documentation/test_practices/encouraged/domain_specific_language/index.md): 以最终用户的语言来表达测试.

[页面对象](https://www.selenium.dev/zh-cn/documentation/test_practices/encouraged/page_object_models/index.md): 对 Web 应用 UI 的一种简单抽象. 

LoadableComponent: 把页面对象建模为可加载的组件. 

BotStyleTests: 使用以命令为导向的自动化测试方式, 而不是页面对象更偏向的对象化方法

## Loadable Component 

### 其是什么?

LoadableComponent 是一个基类, 目标是让编写页面对象更轻松. 
它通过提供一套标准机制来确保页面被正确加载, 并提供在页面加载失败时用于调试的钩子. 
你可以利用它来减少测试中的样板代码, 从而降低维护测试的工作量.

目前在 Selenium 中包含了一个 Java 实现, 
但该方法足够简单, 可在任何语言中实现.

### 简单用法

举个我们想要建模的 UI 的例子, 看看 [new issue](https://github.com/SeleniumHQ/selenium/issues/new?assignees=&labels=I-defect%2Cneeds-triaging&projects=&template=bug-report.yml&title=%5B%F0%9F%90%9B+Bug%5D%3A+) 页面.
对于测试作者而言, 该页面的作用是提供一个提交新 issue 的功能. 
一个基本的页面对象(这个简化版本我们称为 `EditIssueBasic`, 之后会把它改造成 `EditIssue` 这个 LoadableComponent)看起来像:

**Python**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Java**

```java
class EditIssueBasic {

  private final WebDriver driver;

  public EditIssueBasic(WebDriver driver) {
    this.driver = driver;
  }

  public void setTitle(String title) {
    WebElement field = driver.findElement(By.id("issue_title"));
    clearAndType(field, title);
  }

  public void setBody(String body) {
    WebElement field = driver.findElement(By.id("issue_body"));
    clearAndType(field, body);
  }

  public void setHowToReproduce(String howToReproduce) {
    WebElement field = driver.findElement(By.id("issue_form_repro-command"));
    clearAndType(field, howToReproduce);
  }

  public void setLogOutput(String logOutput) {
    WebElement field = driver.findElement(By.id("issue_form_logs"));
    clearAndType(field, logOutput);
  }

  public void setOperatingSystem(String operatingSystem) {
    WebElement field = driver.findElement(By.id("issue_form_operating-system"));
    clearAndType(field, operatingSystem);
  }

  public void setSeleniumVersion(String seleniumVersion) {
    WebElement field = driver.findElement(By.id("issue_form_selenium-version"));
    clearAndType(field, seleniumVersion);
  }

  public void setBrowserVersion(String browserVersion) {
    WebElement field = driver.findElement(By.id("issue_form_browser-versions"));
    clearAndType(field, browserVersion);
  }

  public void setDriverVersion(String driverVersion) {
    WebElement field = driver.findElement(By.id("issue_form_browser-driver-versions"));
    clearAndType(field, driverVersion);
  }

  public void setUsingGrid(String usingGrid) {
    WebElement field = driver.findElement(By.id("issue_form_selenium-grid-version"));
    clearAndType(field, usingGrid);
  }

  public IssueList submit() {
    driver.findElement(By.cssSelector("button[type='submit']")).click();
    return new IssueList(driver);
  }

  private void clearAndType(WebElement field, String text) {
    field.clear();
    field.sendKeys(text);
  }
}
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

为了把它变成一个 LoadableComponent, 
我们所要做的就是将其设为基类:

**Python**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Java**

```java
class EditIssue extends LoadableComponent<EditIssue> {
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

这个签名看起来有些不寻常, 但它的含义很简单:
该类表示一个用于加载 EditIssue 页面 的 LoadableComponent.

通过继承这个基类, 我们需要实现两个新方法:

**Python**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Java**

```java
@Override
protected void load() {
  if (parent != null) {
    parent.get();
  }

  driver.get("https://github.com/SeleniumHQ/selenium/issues/new?assignees=&labels=I-defect%2Cneeds-triaging&projects=&template=bug-report.yml&title=%5B%F0%9F%90%9B+Bug%5D%3A+");
}

@Override
protected void isLoaded() throws Error {
  String url = driver.getCurrentUrl();
  Assertions.assertTrue(url.contains("/issues/new"), "Not on the issue entry page: " + url);
}
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

`load` 方法用于导航到页面, 
而 `isLoaded` 方法用于判断我们是否位于正确的页面. 
虽然该方法看起来像是应该返回 boolean, 
但实际上它通过 JUnit 的 Assert 类执行一系列断言. 
断言可以多也可以少. 通过这些断言, 
可以为类的使用者提供清晰的调试信息, 
用于定位测试失败的原因. 

稍作改造后, 我们的页面对象如下：

**Python**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Java**

```java
class EditIssue extends LoadableComponent<EditIssue> {

  private final WebDriver driver;
  private final LoadableComponent<?> parent;

  // By default the PageFactory will locate elements with the same name or id
  // as the field. Since the issue_title element has an id attribute of "issue_title"
  // we don't need any additional annotations.
  private WebElement issue_title;

  // But we'd prefer a different name in our code than "issue_body", so we use the
  // FindBy annotation to tell the PageFactory how to locate the element.
  @FindBy(id = "issue_body") private WebElement body;

  public EditIssue(WebDriver driver) {
    this(driver, null);
  }

  public EditIssue(WebDriver driver, LoadableComponent<?> parent) {
    this.driver = driver;
    this.parent = parent;

    // This call sets the WebElement fields.
    PageFactory.initElements(driver, this);
  }

  @Override
  protected void load() {
    if (parent != null) {
      parent.get();
    }

    driver.get("https://github.com/SeleniumHQ/selenium/issues/new?assignees=&labels=I-defect%2Cneeds-triaging&projects=&template=bug-report.yml&title=%5B%F0%9F%90%9B+Bug%5D%3A+");
  }

  @Override
  protected void isLoaded() throws Error {
    String url = driver.getCurrentUrl();
    Assertions.assertTrue(url.contains("/issues/new"), "Not on the issue entry page: " + url);
  }

  public void setHowToReproduce(String howToReproduce) {
    WebElement field = driver.findElement(By.id("issue_form_repro-command"));
    clearAndType(field, howToReproduce);
  }

  public void setLogOutput(String logOutput) {
    WebElement field = driver.findElement(By.id("issue_form_logs"));
    clearAndType(field, logOutput);
  }

  public void setOperatingSystem(String operatingSystem) {
    WebElement field = driver.findElement(By.id("issue_form_operating-system"));
    clearAndType(field, operatingSystem);
  }

  public void setSeleniumVersion(String seleniumVersion) {
    WebElement field = driver.findElement(By.id("issue_form_selenium-version"));
    clearAndType(field, seleniumVersion);
  }

  public void setBrowserVersion(String browserVersion) {
    WebElement field = driver.findElement(By.id("issue_form_browser-versions"));
    clearAndType(field, browserVersion);
  }

  public void setDriverVersion(String driverVersion) {
    WebElement field = driver.findElement(By.id("issue_form_browser-driver-versions"));
    clearAndType(field, driverVersion);
  }

  public void setUsingGrid(String usingGrid) {
    WebElement field = driver.findElement(By.id("issue_form_selenium-grid-version"));
    clearAndType(field, usingGrid);
  }

  public IssueList submit() {
    driver.findElement(By.cssSelector("button[type='submit']")).click();
    return new IssueList(driver);
  }

  private void clearAndType(WebElement field, String text) {
    field.clear();
    field.sendKeys(text);
  }
}
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

看起来似乎没带来太多好处, 对吧？
不过它确实把如何导航到该页面的信息封装进了页面本身, 
这意味着这些信息不会散落在代码库各处. 
这也意味着我们可以在测试中这样做：

```java
EditIssue page = new EditIssue(driver).get();
```

上述调用会在必要时促使 driver 导航到该页面. 

### 嵌套组件

当 LoadableComponent 与其他 LoadableComponent 配合使用时, 
会更加有用. 以我们的例子为例, 
我们可以把“编辑 issue”页面视为项目网站中的一个组件（毕竟我们是通过该网站的某个选项卡访问它）. 
并且提交 issue 需要登录. 我们可以把它建模为一棵嵌套组件树：

```
 + ProjectPage
 +---+ SecuredPage
     +---+ EditIssue
```

这在代码中会是什么样子？
首先, 每个逻辑组件都有自己的类. 
每个类的 `load` 方法会调用父组件的 `get` 方法. 
最终效果, 除了上面的 EditIssue 类外, 还包括：

ProjectPage.java:

**Python**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Java**

```java
class ProjectPage extends LoadableComponent<ProjectPage> {

  private final WebDriver driver;
  private final String projectName;

  public ProjectPage(WebDriver driver, String projectName) {
    this.driver = driver;
    this.projectName = projectName;
  }

  @Override
  protected void load() {
    driver.get("http://" + projectName + ".googlecode.com/");
  }

  @Override
  protected void isLoaded() throws Error {
    String url = driver.getCurrentUrl();

    Assertions.assertTrue(url.contains(projectName));
  }
}
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

以及 SecuredPage.java:

**Python**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Java**

```java
class SecuredPage extends LoadableComponent<SecuredPage> {

  private final WebDriver driver;
  private final LoadableComponent<?> parent;
  private final String username;
  private final String password;

  public SecuredPage(WebDriver driver, LoadableComponent<?> parent, String username, String password) {
    this.driver = driver;
    this.parent = parent;
    this.username = username;
    this.password = password;
  }

  @Override
  protected void load() {
    parent.get();

    String originalUrl = driver.getCurrentUrl();

    // Sign in
    driver.get("https://www.google.com/accounts/ServiceLogin?service=code");
    driver.findElement(By.name("Email")).sendKeys(username);
    WebElement passwordField = driver.findElement(By.name("Passwd"));
    passwordField.sendKeys(password);
    passwordField.submit();

    // Now return to the original URL
    driver.get(originalUrl);
  }

  @Override
  protected void isLoaded() throws Error {
    // If you're signed in, you have the option of picking a different login.
    // Let's check for the presence of that.
    if (driver.findElements(By.id("multilogin-dropdown")).isEmpty()) {
      Assertions.fail("Cannot locate user name link");
    }
  }
}
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

EditIssue 中的 `load` 方法现在如下：

**Python**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Java**

```java
@Override
protected void load() {
  if (parent != null) {
    parent.get();
  }

  driver.get("https://github.com/SeleniumHQ/selenium/issues/new?assignees=&labels=I-defect%2Cneeds-triaging&projects=&template=bug-report.yml&title=%5B%F0%9F%90%9B+Bug%5D%3A+");
}
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

这表明这些组件都是相互 "嵌套" 的. 
在 EditIssue 中调用 `get()` 会导致它的所有依赖组件也被加载. 
示例用法：

**Python**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Java**

```java
class NestedComponentsExampleTest {

  @Test
  @Disabled("Illustrative only: exercises live GitHub sign-in and issue-creation pages")
  void demonstrateNestedLoadableComponents() {
    WebDriver driver = new ChromeDriver();
    try {
      ProjectPage project = new ProjectPage(driver, "selenium");
      SecuredPage securedPage = new SecuredPage(driver, project, "example", "not-a-real-password");

      EditIssue editIssue = new EditIssue(driver, securedPage).get();
      editIssue.setHowToReproduce("How to Reproduce");
      editIssue.setLogOutput("Log Output");
      editIssue.setOperatingSystem("Operating System");
      editIssue.setSeleniumVersion("Selenium Version");
      editIssue.setBrowserVersion("Browser Version");
      editIssue.setDriverVersion("Driver Version");
      editIssue.setUsingGrid("I Am Using Grid");
    } finally {
      driver.quit();
    }
  }
}
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

如果在测试中使用像 [Guiceberry](https://github.com/zorzella/guiceberry) 这样的库,
设置页面对象的前置步骤可以省略, 从而使测试更简洁、可读.

## Bot 模式

（原位置： https://github.com/SeleniumHQ/selenium/wiki/Bot-Style-Tests）

尽管页面对象是减少测试重复的一种有效方式, 
但并非所有团队都愿意或适应这种模式. 
另一种可选的方式是采用更加 "命令式" 的测试风格. 

“bot” 是对底层 Selenium API 的一种面向动作的抽象. 
这意味着如果发现某些命令不适合你的应用, 可以轻松修改它们. 例如：

**Python**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Java**

```java
class ActionBot {

  private final WebDriver driver;

  public ActionBot(WebDriver driver) {
    this.driver = driver;
  }

  public void click(By locator) {
    driver.findElement(locator).click();
  }

  public void submit(By locator) {
    driver.findElement(locator).submit();
  }

  /**
   * Type something into an input field. WebDriver doesn't normally clear these
   * before typing, so this method does that first. It also sends a return key
   * to move the focus out of the element.
   */
  public void type(By locator, String text) {
    WebElement element = driver.findElement(locator);
    element.clear();
    element.sendKeys(text + "\n");
  }
}
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

一旦构建了这些抽象并找出测试中的重复, 
就可以在 bot 之上再封装 页面对象. 

## 示例

**Python**

一个用例 使用 `python + pytest + selenium` 实现了设计策略 "**Action Bot**, **Loadable Component** 和 **页面对象**".

A `pytest` fixture `chrome_driver`.

```python
import pytest
from selenium import webdriver
from selenium.common import (
    ElementNotInteractableException,
    NoSuchElementException,
    StaleElementReferenceException,
)
from selenium.webdriver import ActionChains
from selenium.webdriver.common.by import By
from selenium.webdriver.remote.webelement import WebElement
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.ui import WebDriverWait

@pytest.fixture(scope="function")
def chrome_driver():
    with webdriver.Chrome() as driver:
        driver.set_window_size(1024, 768)
        driver.implicitly_wait(0.5)
        yield driver
```

**Java**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

"**Action Bot**" 实现.

**Python**

```python
class ActionBot:
    def __init__(self, driver) -> None:
        self.driver = driver
        self.wait = WebDriverWait(
            driver,
            timeout=10,
            poll_frequency=2,
            ignored_exceptions=[
                NoSuchElementException,
                StaleElementReferenceException,
                ElementNotInteractableException,
            ],
        )

    def element(self, locator: tuple) -> WebElement:
        self.wait.until(lambda driver: driver.find_element(*locator))
        return self.driver.find_element(*locator)

    def elements(self, locator: tuple) -> list[WebElement]:
        return self.driver.find_elements(*locator)

    def hover(self, locator: tuple) -> None:
        element = self.element(locator)
        ActionChains(self.driver).move_to_element(element).perform()

    def click(self, locator: tuple) -> None:
        element = self.element(locator)
        element.click()

    def type(self, locator: tuple, value: str) -> None:
        element = self.element(locator)
        element.clear()
        element.send_keys(value)

    def text(self, locator: tuple) -> str:
        element = self.element(locator)
        return element.text
```

**Java**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

"**Loadable Component** 定义.

**Python**

```python
class LoadableComponent:
    def load(self):
        raise NotImplementedError("Subclasses must implement this method")

    def is_loaded(self):
        raise NotImplementedError("Subclasses must implement this method")

    def get(self):
        if not self.is_loaded():
            self.load()
        if not self.is_loaded():
            raise Exception("Page not loaded properly.")
        return self
```

**Java**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

"**Loadable Component** 以及 **页面对象**" 实现. 

**Python**

```python
class TodoPage(LoadableComponent):
    url = "https://todomvc.com/examples/react/dist/"

    new_todo_by = (By.CSS_SELECTOR, "input.new-todo")
    count_todo_left_by = (By.CSS_SELECTOR, "span.todo-count")
    todo_items_by = (By.CSS_SELECTOR, "ul.todo-list>li")

    view_all_by = (By.LINK_TEXT, "All")
    view_active_by = (By.LINK_TEXT, "Active")
    view_completed_by = (By.LINK_TEXT, "Completed")

    toggle_all_by = (By.CSS_SELECTOR, "input.toggle-all")
    clear_completed_by = (By.CSS_SELECTOR, "button.clear-completed")

    @staticmethod
    def build_todo_by(s: str) -> tuple:
        p = f"//li[.//label[contains(text(), '{s}')]]"
        return By.XPATH, p

    @staticmethod
    def build_todo_item_label_by(s: str) -> tuple:
        p = f"//label[contains(text(), '{s}')]"
        return By.XPATH, p

    @staticmethod
    def build_todo_item_toggle_by(s: str) -> tuple:
        by, using = TodoPage.build_todo_item_label_by(s)
        p = f"{using}/../input[@class='toggle']"
        return by, p

    @staticmethod
    def build_todo_item_delete_by(s: str) -> tuple:
        by, using = TodoPage.build_todo_item_label_by(s)
        p = f"{using}/../button[@class='destroy']"
        return by, p

    def build_count_todo_left(self, count: int) -> str:
        if count == 1:
            return "1 item left!"
        else:
            return f"{count} items left!"

    def __init__(self, driver):
        self.driver = driver
        self.bot = ActionBot(driver)

    def load(self):
        self.driver.get(self.url)

    def is_loaded(self):
        try:
            WebDriverWait(self.driver, 10).until(EC.visibility_of_element_located(self.new_todo_by))
            return True
        except:
            return False

    # business domain below
    def count_todo_items_left(self) -> str:
        return self.bot.text(self.count_todo_left_by)

    def todo_count(self) -> int:
        return len(self.bot.elements(self.todo_items_by))

    def new_todo(self, s: str):
        self.bot.type(self.new_todo_by, s + "\n")

    def toggle_todo(self, s: str):
        self.bot.click(self.build_todo_item_toggle_by(s))

    def hover_todo(self, s: str) -> None:
        self.bot.hover(self.build_todo_by(s))

    def delete_todo(self, s: str):
        self.hover_todo(s)
        self.bot.click(self.build_todo_item_delete_by(s))

    def clear_completed_todo(self):
        self.bot.click(self.clear_completed_by)

    def toggle_all_todo(self):
        self.bot.click(self.toggle_all_by)

    def view_all_todo(self):
        self.bot.click(self.view_all_by)

    def view_active_todo(self):
        self.bot.click(self.view_active_by)

    def view_completed_todo(self):
        self.bot.click(self.view_completed_by)
```

**Java**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

测试用例实现.

**Python**

Test cases implementation with `pytest`.

```python
@pytest.fixture
def page(chrome_driver) -> TodoPage:
    driver = chrome_driver
    return TodoPage(driver).get()

class TestTodoPage:
    def test_new_todo(self, page: TodoPage):
        assert page.todo_count() == 0
        page.new_todo("aaa")
        assert page.count_todo_items_left() == page.build_count_todo_left(1)

    def test_todo_toggle(self, page: TodoPage):
        s = "aaa"
        page.new_todo(s)
        assert page.count_todo_items_left() == page.build_count_todo_left(1)

        page.toggle_todo(s)
        assert page.count_todo_items_left() == page.build_count_todo_left(0)

        page.toggle_todo(s)
        assert page.count_todo_items_left() == page.build_count_todo_left(1)

    def test_todo_delete(self, page: TodoPage):
        s1 = "aaa"
        s2 = "bbb"
        page.new_todo(s1)
        page.new_todo(s2)
        assert page.count_todo_items_left() == page.build_count_todo_left(2)

        page.delete_todo(s1)
        assert page.count_todo_items_left() == page.build_count_todo_left(1)

        page.delete_todo(s2)
        assert page.todo_count() == 0

    def test_new_100_todo(self, page: TodoPage):
        for i in range(100):
            s = f"ToDo{i}"
            page.new_todo(s)
        assert page.count_todo_items_left() == page.build_count_todo_left(100)

    def test_toggle_all_todo(self, page: TodoPage):
        for i in range(10):
            s = f"ToDo{i}"
            page.new_todo(s)
        assert page.count_todo_items_left() == page.build_count_todo_left(10)
        assert page.todo_count() == 10

        page.toggle_all_todo()
        assert page.count_todo_items_left() == page.build_count_todo_left(0)
        assert page.todo_count() == 10

        page.toggle_all_todo()
        assert page.count_todo_items_left() == page.build_count_todo_left(10)
        assert page.todo_count() == 10

    def test_clear_completed_todo(self, page: TodoPage):
        for i in range(10):
            s = f"ToDo{i}"
            page.new_todo(s)
        assert page.count_todo_items_left() == page.build_count_todo_left(10)
        assert page.todo_count() == 10

        for i in range(5):
            s = f"ToDo{i}"
            page.toggle_todo(s)
```

**Java**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._
