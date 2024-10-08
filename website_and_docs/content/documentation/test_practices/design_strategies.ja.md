---
title: "デザインパターンと開発戦略"
linkTitle: "デザイン戦略"
weight: 1
---
(以前の場所: https://github.com/SeleniumHQ/selenium/wiki/Bot-Style-Tests)

## 概要
時間の経過とともに、プロジェクトは多数のテストが積み上がる傾向があります。 
テストの総数が増えると、コードベースに変更を加えることが難しくなります。
アプリケーションが正常に機能していても、1回の"単純な"変更で多数のテストが失敗する可能性があります。 
これらの問題が避けられない場合もありますが、問題が発生した場合は、できるだけ早く稼働を再開する必要があります。 
次のデザインパターンと戦略は、テストの作成と保守を容易にするためにWebDriverで以前に使用されています。 
それらもあなたにとって役に立つかもしれません。

[DomainDrivenDesign]({{< ref "encouraged/domain_specific_language.md" >}})：アプリのエンドユーザーの言語でテストを表現します。   
[PageObjects]({{< ref "encouraged/page_object_models.md" >}})：WebアプリのUIの単純な抽象化  
LoadableComponent：PageObjectsをコンポーネントとしてモデリングします。   
BotStyleTests：PageObjectsが推奨するオブジェクトベースのアプローチではなく、コマンドベースのアプローチを使用してテストを自動化します。  

## ロード可能なコンポーネント

### それは何ですか？

LoadableComponentは、PageObjectsの作成の負担を軽減することを目的としたベースクラスです。 
これは、ページがロードされることを保証する標準的な方法を提供し、ページのロードの失敗のデバッグを容易にするフックを提供することによってこれを行います。 
これを使用して、テストの定型コードの量を減らすことができます。これにより、テストの保守が面倒になります。

現在、Selenium 2の一部として出荷されるJavaの実装がありますが、使用されるアプローチは、どの言語でも実装できるほど単純です。

### 簡単な使用方法

モデル化するUIの例として、[新しいissue](https://github.com/SeleniumHQ/selenium/issues/new)のページをご覧ください。 
テスト作成者の観点から、これは新しい問題を提出できるサービスを提供します。 
基本的なページオブジェクトは次のようになります。

```java
package com.example.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditIssue {

  private final WebDriver driver;

  public EditIssue(WebDriver driver) {
    this.driver = driver;
  }

  public void setSummary(String summary) {
    WebElement field = driver.findElement(By.name("summary"));
    clearAndType(field, summary);
  }

  public void enterDescription(String description) {
    WebElement field = driver.findElement(By.name("comment"));
    clearAndType(field, description);
  }

  public IssueList submit() {
    driver.findElement(By.id("submit")).click();
    return new IssueList(driver);
  }

  private void clearAndType(WebElement field, String text) {
    field.clear();
    field.sendKeys(text);
  }
}
```

これをLoadableComponentに変換するには、これを基本型として設定するだけです。

```java
public class EditIssue extends LoadableComponent<EditIssue> {
  // rest of class ignored for now
}
```

この署名は少し変わっているように見えますが、それは、このクラスがEditIssueページをロードするLoadableComponentを表すことを意味します。

このベースクラスを拡張することにより、2つの新しいメソッドを実装する必要があります。

```java
  @Override
  protected void load() {
    driver.get("https://github.com/SeleniumHQ/selenium/issues/new");
  }

  @Override
  protected void isLoaded() throws Error {
    String url = driver.getCurrentUrl();
    assertTrue("Not on the issue entry page: " + url, url.endsWith("/new"));
  }
```

`load` メソッドはページに移動するために使用され、　`isLoaded` メソッドは正しいページにいるかどうかを判断するために使用されます。 
このメソッドはブール値を返す必要があるように見えますが、代わりにJUnitのAssertクラスを使用して一連のアサーションを実行します。 
アサーションは好きなだけ少なくても多くてもかまいません。 
これらのアサーションを使用することで、クラスのユーザーにテストのデバッグに使用できる明確な情報を提供することができます。

少し手直しすると、PageObjectは次のようになります。

```java
package com.example.webdriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static junit.framework.Assert.assertTrue;

public class EditIssue extends LoadableComponent<EditIssue> {

  private final WebDriver driver;
  
  // By default the PageFactory will locate elements with the same name or id
  // as the field. Since the summary element has a name attribute of "summary"
  // we don't need any additional annotations.
  private WebElement summary;
  
  // Same with the submit element, which has the ID "submit"
  private WebElement submit;
  
  // But we'd prefer a different name in our code than "comment", so we use the
  // FindBy annotation to tell the PageFactory how to locate the element.
  @FindBy(name = "comment") private WebElement description;
  
  public EditIssue(WebDriver driver) {
    this.driver = driver;
    
    // This call sets the WebElement fields.
    PageFactory.initElements(driver, this);
  }

  @Override
  protected void load() {
    driver.get("https://github.com/SeleniumHQ/selenium/issues/new");
  }

  @Override
  protected void isLoaded() throws Error {
    String url = driver.getCurrentUrl();
    assertTrue("Not on the issue entry page: " + url, url.endsWith("/new"));
  }
  
  public void setSummary(String issueSummary) {
    clearAndType(summary, issueSummary);
  }

  public void enterDescription(String issueDescription) {
    clearAndType(description, issueDescription);
  }

  public IssueList submit() {
    submit.click();
    return new IssueList(driver);
  }

  private void clearAndType(WebElement field, String text) {
    field.clear();
    field.sendKeys(text);
  }
}

```

それは私たちをあまり信じられなかったようですよね？ 
これまでに行ったことの1つは、ページに移動する方法に関する情報をページ自体にカプセル化することです。
つまり、この情報はコードベース全体に散らばっていません。 
これは、テストで下記を実行できることも意味します。

```java
EditIssue page = new EditIssue(driver).get();
```

この呼び出しにより、ドライバーは必要に応じてページに移動します。

### ネストされたコンポーネント

LoadableComponentsは、他のLoadableComponentsと組み合わせて使用すると、より便利になります。 
この例を使用すると、 "edit issue" ページをプロジェクトのWebサイト内のコンポーネントとして表示できます（結局のところ、そのサイトのタブからアクセスします）。 
また、issue を報告するにはログインする必要があります。 
これをネストされたコンポーネントのツリーとしてモデル化できます。

```
 + ProjectPage
 +---+ SecuredPage
     +---+ EditIssue
```

これはコードではどのように見えますか？ 
まず、各論理コンポーネントには独自のクラスがあります。 
それぞれの "load" メソッドは、親クラスを "get" します。 
上記のEditIssueクラスに加えて、最終結果は次のようになります。

ProjectPage.java:

```java
package com.example.webdriver;

import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class ProjectPage extends LoadableComponent<ProjectPage> {

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

    assertTrue(url.contains(projectName));
  }
}
```

and SecuredPage.java:

```java
package com.example.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.fail;

public class SecuredPage extends LoadableComponent<SecuredPage> {

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

    try {
      WebElement div = driver.findElement(By.id("multilogin-dropdown"));
    } catch (NoSuchElementException e) {
      fail("Cannot locate user name link");
    }
  }
}
```

EditIssueの "load" メソッドは次のようになります。

```java
  @Override
  protected void load() {
    securedPage.get();

    driver.get("https://github.com/SeleniumHQ/selenium/issues/new");
  }
```

これは、コンポーネントがすべて相互に "ネストされている" ことを示しています。 
EditIssueで `get()` を呼び出すと、そのすべての依存関係も読み込まれます。 
使用例：

```java
public class FooTest {
  private EditIssue editIssue;

  @Before
  public void prepareComponents() {
    WebDriver driver = new FirefoxDriver();

    ProjectPage project = new ProjectPage(driver, "selenium");
    SecuredPage securedPage = new SecuredPage(driver, project, "example", "top secret");
    editIssue = new EditIssue(driver, securedPage);
  }

  @Test
  public void demonstrateNestedLoadableComponents() {
    editIssue.get();

    editIssue.setSummary("Summary");
    editIssue.enterDescription("This is an example");
  }
}
```

テストで [Guiceberry](https://github.com/zorzella/guiceberry) などのライブラリを使用している場合は、PageObjectsの設定の前文を省略して、わかりやすく読みやすいテストを作成できます。

## ボットパターン

(以前の場所: https://github.com/SeleniumHQ/selenium/wiki/Bot-Style-Tests)

PageObjectsは、テストでの重複を減らすための便利な方法ですが、チームが快適にフォローできるパターンであるとは限りません。 
別のアプローチは、より "コマンドのような" スタイルのテストに従うことです。

"ボット" は、生のSeleniumAPIに対するアクション指向の抽象化です。 
つまり、コマンドがアプリに対して正しいことをしていないことがわかった場合、コマンドを簡単に変更できます。 
例として：

```java
public class ActionBot {
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

これらの抽象化が構築され、テストでの重複が特定されると、ボットの上にPageObjectsを階層化することができます。

## Example

**Action Bot**、**Loadable Component**、および **Page Object** を実装した `python + pytest + selenium` の例です。

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

"**Action Bot**" implementation.

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

"**Loadable Component** definition.

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

"**Loadable Component** and **Page Object**" implementation. 

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
            WebDriverWait(self.driver, 10).until(
                EC.visibility_of_element_located(self.new_todo_by)
            )
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
        assert page.count_todo_items_left() == page.build_count_todo_left(5)
        assert page.todo_count() == 10

        page.clear_completed_todo()
        assert page.count_todo_items_left() == page.build_count_todo_left(5)
        assert page.todo_count() == 5

    def test_view_todo(self, page: TodoPage):
        for i in range(10):
            s = f"ToDo{i}"
            page.new_todo(s)
        for i in range(4):
            s = f"ToDo{i}"
            page.toggle_todo(s)

        page.view_all_todo()
        assert page.count_todo_items_left() == page.build_count_todo_left(6)
        assert page.todo_count() == 10

        page.view_active_todo()
        assert page.count_todo_items_left() == page.build_count_todo_left(6)
        assert page.todo_count() == 6

        page.view_completed_todo()
        assert page.count_todo_items_left() == page.build_count_todo_left(6)
        assert page.todo_count() == 4
```