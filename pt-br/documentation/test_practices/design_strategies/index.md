# Design patterns and development strategies

URL: https://www.selenium.dev/pt-br/documentation/test_practices/design_strategies/

---

(previously located: https://github.com/SeleniumHQ/selenium/wiki/Bot-Style-Tests)

## Overview
Over time, projects tend to accumulate large numbers of tests. As the total number of tests increases, 
it becomes harder to make changes to the codebase --- a single "simple" 
change may cause numerous tests to fail, even though the application still 
works properly. Sometimes these problems are unavoidable, but when they do 
occur you want to be up and running again as quickly as possible. The following 
design patterns and strategies have been used before with WebDriver to help make 
tests easier to write and maintain. They may help you too.

[DomainDrivenDesign](https://www.selenium.dev/pt-br/documentation/test_practices/encouraged/domain_specific_language/index.md): Express your tests in the language of the end-user of the app.
[PageObjects](https://www.selenium.dev/pt-br/documentation/test_practices/encouraged/page_object_models/index.md): A simple abstraction of the UI of your web app.
LoadableComponent: Modeling PageObjects as components.
BotStyleTests: Using a command-based approach to automating tests, rather than the object-based approach that PageObjects encourage

## Loadable Component

### What Is It?

The LoadableComponent is a base class that aims to make writing PageObjects 
less painful. It does this by providing a standard way of ensuring that 
pages are loaded and providing hooks to make debugging the failure of a 
page to load easier. You can use it to help reduce the amount of boilerplate 
code in your tests, which in turn make maintaining your tests less tiresome.

There is currently an implementation in Java that ships as part of Selenium 2, 
but the approach used is simple enough to be implemented in any language.

### Simple Usage

As an example of a UI that we'd like to model, take a look at 
the [new issue](https://github.com/SeleniumHQ/selenium/issues/new?assignees=&labels=I-defect%2Cneeds-triaging&projects=&template=bug-report.yml&title=%5B%F0%9F%90%9B+Bug%5D%3A+) page. From 
the point of view of a test author, this offers the service of being able to 
file a new issue. A basic Page Object -- we'll call this
simple version `EditIssueBasic`, before turning it into the `EditIssue` LoadableComponent below -- would look like:

**Python**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

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

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

In order to turn this into a LoadableComponent, all we need to do is to set that as the base type:

**Python**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Java**

```java
class EditIssue extends LoadableComponent<EditIssue> {
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

This signature looks a little unusual, but it all means is that this class 
represents a LoadableComponent that loads the EditIssue page.

By extending this base class, we need to implement two new methods:

**Python**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

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

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

The `load` method is used to navigate to the page, whilst the `isLoaded` method 
is used to determine whether we are on the right page. Although the 
method looks like it should return a boolean, instead it performs a 
series of assertions using JUnit's Assert class. There can be as few 
or as many assertions as you like. By using these assertions it's 
possible to give users of the class clear information that can be 
used to debug tests.

With a little rework, our PageObject looks like:

**Python**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

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

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

That doesn't seem to have bought us much, right? One thing it has done is 
encapsulate the information about how to navigate to the page into the page 
itself, meaning that this information's not scattered through the code base. 
It also means that we can do this in our tests:

```java
EditIssue page = new EditIssue(driver).get();
```

This call will cause the driver to navigate to the page if that's necessary.

### Nested Components

LoadableComponents start to become more useful when they are used in conjunction 
with other LoadableComponents. Using our example, we could view the "edit issue" 
page as a component within a project's website (after all, we access it via a 
tab on that site). You also need to be logged in to file an issue. We could 
model this as a tree of nested components:

```
 + ProjectPage
 +---+ SecuredPage
     +---+ EditIssue
```

What would this look like in code? For a start, each logical component 
would have its own class. The "load" method in each of them would "get" 
the parent. The end result, in addition to the EditIssue class above is:

ProjectPage.java:

**Python**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

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

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

and SecuredPage.java:

**Python**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

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

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

The "load" method in EditIssue now looks like:

**Python**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

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

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

This shows that the components are all "nested" within each other. A call to `get()` in EditIssue will cause all its dependencies to load too. The example usage:

**Python**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

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

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

If you're using a library such as [Guiceberry](https://github.com/zorzella/guiceberry) in your tests, 
the preamble of setting up the PageObjects can be omitted leading to nice, clear, readable tests.

## Bot Pattern

(previously located: https://github.com/SeleniumHQ/selenium/wiki/Bot-Style-Tests)

Although PageObjects are a useful way of reducing duplication in your tests, it's not always a pattern that teams feel comfortable following. An alternative approach is to follow a more "command-like" style of testing.

A "bot" is an action-oriented abstraction over the raw Selenium APIs. This means that if you find that commands aren't doing the Right Thing for your app, it's easy to change them. As an example:

**Python**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

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

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

Once these abstractions have been built and duplication in your tests identified, it's possible to layer PageObjects on top of bots.

## Example

**Python**

An example of `python + pytest + selenium` which implemented "**Action Bot**, **Loadable Component** and **Page Object**".

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

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

"**Action Bot**" implementation.

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

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

"**Loadable Component** definition.

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

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

"**Loadable Component** and **Page Object**" implementation. 

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

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

Test cases implementation.

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

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._
