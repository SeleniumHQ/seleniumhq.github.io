---
title: "Design patterns and development strategies"
linkTitle: "Design Strategies"
weight: 1
needsTranslation: true
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

[DomainDrivenDesign]({{< ref "encouraged/domain_specific_language.md" >}}): Express your tests in the language of the end-user of the app.
[PageObjects]({{< ref "encouraged/page_object_models.md" >}}): A simple abstraction of the UI of your web app.
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
the [new issue](https://github.com/SeleniumHQ/selenium/issues/new) page. From 
the point of view of a test author, this offers the service of being able to 
file a new issue. A basic Page Object would look like:

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

In order to turn this into a LoadableComponent, all we need to do is to set that as the base type:

```java
public class EditIssue extends LoadableComponent<EditIssue> {
  // rest of class ignored for now
}
```

This signature looks a little unusual, but it all means is that this class 
represents a LoadableComponent that loads the EditIssue page.

By extending this base class, we need to implement two new methods:

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

The `load` method is used to navigate to the page, whilst the `isLoaded` method 
is used to determine whether we are on the right page. Although the 
method looks like it should return a boolean, instead it performs a 
series of assertions using JUnit's Assert class. There can be as few 
or as many assertions as you like. By using these assertions it's 
possible to give users of the class clear information that can be 
used to debug tests.

With a little rework, our PageObject looks like:

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

The "load" method in EditIssue now looks like:

```java
  @Override
  protected void load() {
    securedPage.get();

    driver.get("https://github.com/SeleniumHQ/selenium/issues/new");
  }
```

This shows that the components are all "nested" within each other. A call to `get()` in EditIssue will cause all its dependencies to load too. The example usage:

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

If you're using a library such as [Guiceberry](https://github.com/zorzella/guiceberry) in your tests, 
the preamble of setting up the PageObjects can be omitted leading to nice, clear, readable tests.


## Bot Pattern

(previously located: https://github.com/SeleniumHQ/selenium/wiki/Bot-Style-Tests)

Although PageObjects are a useful way of reducing duplication in your tests, it's not always a pattern that teams feel comfortable following. An alternative approach is to follow a more "command-like" style of testing.

A "bot" is an action-oriented abstraction over the raw Selenium APIs. This means that if you find that commands aren't doing the Right Thing for your app, it's easy to change them. As an example:

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

Once these abstractions have been built and duplication in your tests identified, it's possible to layer PageObjects on top of bots.
