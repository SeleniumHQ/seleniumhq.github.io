---
title: "页面对象模型"
linkTitle: "页面对象模型"
weight: 3
aliases: [
"/documentation/guidelines/page_object_models/"
]
---

注意：本页内容合并自多个来源，包括
[Selenium wiki](https://github.com/SeleniumHQ/selenium/wiki/PageObjects)

## 概述

在 Web 应用的 UI 中，总有一些区域是测试需要与之交互的。
页面对象仅将这些区域建模为测试代码中的对象。
这减少了重复代码的数量，意味着如果 UI 发生变化，
只需在一处进行修复。

页面对象模型是一种设计模式，它在测试自动化领域日益流行，
旨在增强测试的可维护性并减少代码重复。页面对象是一个
面向对象的类，它作为被测应用 (AUT) 某个页面的接口。
测试在需要与该页面的 UI 交互时，会使用此页面对象类的
方法。好处在于，如果该页面的 UI 发生变化，测试本身
不需要更改，只需更改页面对象中的代码即可。随后，
所有支持新 UI 的更改都集中在一个地方。

### 优势

* 测试代码与页面特定代码（如定位器，或使用 UI 映射时的定位器使用方式）和布局之间有清晰的分离。
* 页面所提供的服务或操作有单一的存储库，
  而不是将这些服务分散在各处测试中。

在以上两种情况下，这使得因 UI 变更而需要进行的所有修改
都可以在一处完成。关于此技术的有用信息可以在众多博客中找到，
因为这种"测试设计模式"正被广泛使用。
我们鼓励希望了解更多信息的读者在互联网上搜索关于此主题的博客。
许多人已经就这一设计模式撰写了文章，
并能提供超出本用户指南范围的有用提示。
为了帮助您入门，我们将用一个简单的示例来说明页面对象。

### 示例

首先，考虑一个不使用页面对象的典型测试自动化示例：

```java
/***
 * Tests login feature
 */
public class Login {

  public void testLogin() {
    // fill login data on sign-in page
    driver.findElement(By.name("user_name")).sendKeys("userName");
    driver.findElement(By.name("password")).sendKeys("my supersecret password");
    driver.findElement(By.name("sign-in")).click();

    // verify h1 tag is "Hello userName" after login
    driver.findElement(By.tagName("h1")).isDisplayed();
    assertThat(driver.findElement(By.tagName("h1")).getText(), is("Hello userName"));
  }
}
```

这种方式存在两个问题。

* 测试方法与 AUT 的定位器（在本例中为 ID）之间没有分离；两者
  交织在同一个方法中。如果 AUT 的 UI 更改了其标识符、布局，
  或登录的输入和处理方式，测试本身必须随之更改。
* ID 定位器会分散在多个测试中，散布在所有需要
  使用此登录页面的测试里。

应用页面对象模型后，此示例可以重写为
以下登录页面的页面对象示例。

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object encapsulates the Sign-in page.
 */
public class SignInPage {
  protected WebDriver driver;

  // <input name="user_name" type="text" value="">
  private By usernameBy = By.name("user_name");
  // <input name="password" type="password" value="">
  private By passwordBy = By.name("password");
  // <input name="sign_in" type="submit" value="SignIn">
  private By signinBy = By.name("sign_in");

  public SignInPage(WebDriver driver){
    this.driver = driver;
     if (!driver.getTitle().equals("Sign In Page")) {
      throw new IllegalStateException("This is not Sign In Page," +
            " current page is: " + driver.getCurrentUrl());
    }
  }

  /**
    * Login as valid user
    *
    * @param userName
    * @param password
    * @return HomePage object
    */
  public HomePage loginValidUser(String userName, String password) {
    driver.findElement(usernameBy).sendKeys(userName);
    driver.findElement(passwordBy).sendKeys(password);
    driver.findElement(signinBy).click();
    return new HomePage(driver);
  }
}
```

而首页的页面对象可能如下所示。

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object encapsulates the Home Page
 */
public class HomePage {
  protected WebDriver driver;

  // <h1>Hello userName</h1>
  private By messageBy = By.tagName("h1");

  public HomePage(WebDriver driver){
    this.driver = driver;
    if (!driver.getTitle().equals("Home Page of logged in user")) {
      throw new IllegalStateException("This is not Home Page of logged in user," +
            " current page is: " + driver.getCurrentUrl());
    }
  }

  /**
    * Get message (h1 tag)
    *
    * @return String message text
    */
  public String getMessageText() {
    return driver.findElement(messageBy).getText();
  }

  public HomePage manageProfile() {
    // Page encapsulation to manage profile functionality
    return new HomePage(driver);
  }
  /* More methods offering the services represented by Home Page
  of Logged User. These methods in turn might return more Page Objects
  for example click on Compose mail button could return ComposeMail class object */
}
```

现在，登录测试将按如下方式使用这两个页面对象。

```java
/***
 * Tests login feature
 */
public class TestLogin {

  @Test
  public void testLogin() {
    SignInPage signInPage = new SignInPage(driver);
    HomePage homePage = signInPage.loginValidUser("userName", "password");
    assertThat(homePage.getMessageText(), is("Hello userName"));
  }

}
```

页面对象的设计方式有很大的灵活性，
但要获得所需的测试代码可维护性，有一些基本规则需要遵循。

## 页面对象中的断言

页面对象本身不应进行验证或断言。
这是测试的一部分，应始终在测试代码中，
而非页面对象中。
页面对象将包含页面的表示，
以及页面通过方法提供的服务，
但不应包含与被测内容相关的代码。

有一个唯一的验证可以、也应当在页面对象中进行，
那就是验证页面以及页面上的关键元素是否已正确加载。
此验证应在实例化页面对象时完成。
在上面的示例中，SignInPage 和
HomePage 的构造函数都会检查预期页面是否可用并准备好
接收测试的请求。

## 页面组件对象

页面对象不一定需要自身表示页面的所有部分。
这在早期就已被[Martin Fowler](https://martinfowler.com/bliki/PageObject.html#footnote-panel-object) 指出，
他当时首次提出了"面板对象"这一术语。

用于页面对象的相同原则可以用来
创建"页面 _组件_ 对象"（后来的称呼），它表示页面的离散区块，
并 **可以包含在页面对象中**。
这些组件对象可以提供对这些离散区块内部元素的引用，
以及利用它们所提供的功能或行为的方法。

例如，一个产品页面有多个产品。

```html
<!-- Products Page -->
<div class="header_container">
    <span class="title">Products</span>
</div>

<div class="inventory_list">
    <div class="inventory_item">
    </div>
    <div class="inventory_item">
    </div>
    <div class="inventory_item">
    </div>
    <div class="inventory_item">
    </div>
    <div class="inventory_item">
    </div>
    <div class="inventory_item">
    </div>
</div>
```

每个产品都是产品页面的一个组件。


```html
<!-- Inventory Item -->
<div class="inventory_item">
    <div class="inventory_item_name">Backpack</div>
    <div class="pricebar">
        <div class="inventory_item_price">$29.99</div>
        <button id="add-to-cart-backpack">Add to cart</button>
    </div>
</div>
```

产品页面拥有（HAS-A）一个产品列表。这种对象关系称为组合。
简单来说，某个事物 _由_ 另一个事物 _组成_。

```java
public abstract class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
}

// Page Object
public class ProductsPage extends BasePage {
    public ProductsPage(WebDriver driver) {
        super(driver);
        // No assertions, throws an exception if the element is not loaded
        new WebDriverWait(driver, Duration.ofSeconds(3))
            .until(d -> d.findElement(By.className​("header_container")));
    }

    // Returning a list of products is a service of the page
    public List<Product> getProducts() {
        return driver.findElements(By.className​("inventory_item"))
            .stream()
            .map(e -> new Product(e)) // Map WebElement to a product component
            .toList();
    }

    // Return a specific product using a boolean-valued function (predicate)
    // This is the behavioral Strategy Pattern from GoF
    public Product getProduct(Predicate<Product> condition) {
        return getProducts()
            .stream()
            .filter(condition) // Filter by product name or price
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Product not found")); // Error thrown during actual test run
    }
}
```

Product 组件对象在产品页面对象内部使用。

```java
public abstract class BaseComponent {
    protected WebElement root;

    public BaseComponent(WebElement root) {
        this.root = root;
    }
}

// Page Component Object
public class Product extends BaseComponent {
    // The root element contains the entire component
    public Product(WebElement root) {
        super(root); // inventory_item
    }

    public String getName() {
        // Locating an element begins at the root of the component
        return root.findElement(By.className("inventory_item_name")).getText();
    }

    public BigDecimal getPrice() {
        return new BigDecimal(
                root.findElement(By.className("inventory_item_price"))
                    .getText()
                    .replace("$", "")
            ).setScale(2, RoundingMode.UNNECESSARY); // Sanitation and formatting
    }

    public void addToCart() {
        root.findElement(By.id("add-to-cart-backpack")).click();
    }
}
```

现在，产品测试将按如下方式使用页面对象和页面组件对象。

```java
public class ProductsTest {
    @Test
    public void testProductInventory() {
        var productsPage = new ProductsPage(driver); // page object
        var products = productsPage.getProducts();
        assertEquals(6, products.size()); // expected, actual
    }
    
    @Test
    public void testProductPrices() {
        var productsPage = new ProductsPage(driver);

        // Pass a lambda expression (predicate) to filter the list of products
        // The predicate or "strategy" is the behavior passed as parameter
        var backpack = productsPage.getProduct(p -> p.getName().equals("Backpack")); // page component object
        var bikeLight = productsPage.getProduct(p -> p.getName().equals("Bike Light"));

        assertEquals(new BigDecimal("29.99"), backpack.getPrice());
        assertEquals(new BigDecimal("9.99"), bikeLight.getPrice());
    }
}
```

页面和组件由各自的对象表示。这两个对象只有提供其**服务**的方法，
这与实际应用相匹配，也是面向对象编程的核心原则。
当构建应用程序时，它们并不是由一个庞大的页面实体构成的。
它们是由包含在页面中的组件构建的。页面组件对象实现了相同的方法。

您甚至可以
将组件对象嵌套在其他组件对象中，用于更复杂的
页面。如果 AUT 中的某个页面有多个组件，或
整个站点中使用的公共组件（例如导航栏），那么这
可能会提高可维护性并减少代码重复。

## 测试中使用的其他设计模式

还有其他一些设计模式也可用于测试。讨论所有这些模式
超出了本用户指南的范围。在这里，我们只是想介绍
这些概念，让读者了解可以做的一些事情。正如
前面提到的，许多人已经在这一主题上撰写了博客，我们鼓励
读者搜索关于这些主题的博客。

## 实现说明

页面对象可以被看作同时面向两个方向。面向测试开发者的一面，
它们表示特定页面提供的**服务**。
背对开发者的一面，它们应该是唯一对页面（或页面的一部分）的 HTML 结构有深入了解的东西。
最简单的理解方式是将页面对象上的方法视为提供页面所提供的"服务"，而不是暴露页面的细节和机制。
举例来说，想想任何基于 Web 的电子邮件系统的收件箱。
它提供的服务包括撰写新邮件、选择阅读单封邮件，以及列出收件箱中邮件的主题行。
这些功能如何实现对测试而言不应重要。

因为我们鼓励测试开发者尝试思考他们正在交互的服务而非实现，
所以页面对象应尽量少地暴露底层的 WebDriver 实例。
为此，**页面对象上的方法可以返回另一个页面对象、另一个页面组件对象，
甚至自身**。这意味着我们可以有效地对用户在应用程序中的旅程进行建模。
这也意味着，如果页面之间的关联方式发生变化
（例如，登录页面在用户首次登录某服务时要求其更改密码，而以前并不这样做），
只需更改相应方法的签名就会导致测试无法编译。
换言之，当我们更改页面之间的关系并在页面对象中反映这一变化时，
无需运行测试就能知道哪些测试会失败。

这种方法的一个后果是，可能需要对（例如）成功和失败的登录分别建模；
或者一次点击可能根据应用程序的状态产生不同的结果。
当发生这种情况时，通常在页面对象上有多个方法：

```java
public class LoginPage {
    public HomePage loginAs(String username, String password) {
        // ... clever magic happens here
    }
    
    public LoginPage loginAsExpectingError(String username, String password) {
        //  ... failed login here, maybe because one or both of the username and password are wrong
    }
    
    public String getErrorMessage() {
        // So we can verify that the correct error is shown
    }
}
```

上面的代码展示了一个要点：测试而非页面对象应负责对页面状态进行断言。例如：

```java
public void testMessagesAreReadOrUnread() {
    Inbox inbox = new Inbox(driver);
    inbox.assertMessageWithSubjectIsUnread("I like cheese");
    inbox.assertMessageWithSubjectIsNotUnread("I'm not fond of tofu");
}
```

可以重写为：

```java
public void testMessagesAreReadOrUnread() {
    Inbox inbox = new Inbox(driver);
    assertTrue(inbox.isMessageWithSubjectIsUnread("I like cheese"));
    assertFalse(inbox.isMessageWithSubjectIsUnread("I'm not fond of tofu"));
}
```

当然，与每条指南一样，也有例外情况，
页面对象中常见的一个例外是在实例化页面对象时检查 WebDriver 是否在正确的页面上。
这在下面的示例中有所体现。

最后，页面对象不必表示整个页面，可以由页面对象组件组成。
这些组件可以表示在站点或页面中频繁出现的部分，例如站点导航。
核心原则是，在你的测试套件中只有一个地方了解特定页面（或页面的一部分）的 HTML 结构。

## 总结

* 公共方法表示页面或组件提供的服务
* 尽量不要暴露页面或组件的内部细节
* 通常不要进行断言
* 方法返回其他页面对象、页面组件对象，或可选地返回自身（用于流畅语法）
* 不必始终表示整个页面
* 同一操作的不同结果建模为不同的方法

## 示例

```java
public class LoginPage {
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;

        // Check that we're on the right page.
        if (!"Login".equals(driver.getTitle())) {
            // Alternatively, we could navigate to the login page, perhaps logging out first
            throw new IllegalStateException("This is not the login page");
        }
    }

    // The login page contains several HTML elements that will be represented as WebElements.
    // The locators for these elements should only be defined once.
        By usernameLocator = By.id("username");
        By passwordLocator = By.id("passwd");
        By loginButtonLocator = By.id("login");

    // The login page allows the user to type their username into the username field
    public LoginPage typeUsername(String username) {
        // This is the only place that "knows" how to enter a username
        driver.findElement(usernameLocator).sendKeys(username);

        // Return the current page object as this action doesn't navigate to a page represented by another PageObject
        return this;	
    }

    // The login page allows the user to type their password into the password field
    public LoginPage typePassword(String password) {
        // This is the only place that "knows" how to enter a password
        driver.findElement(passwordLocator).sendKeys(password);

        // Return the current page object as this action doesn't navigate to a page represented by another PageObject
        return this;	
    }

    // The login page allows the user to submit the login form
    public HomePage submitLogin() {
        // This is the only place that submits the login form and expects the destination to be the home page.
        // A separate method should be created for the instance of clicking login whilst expecting a login failure. 
        driver.findElement(loginButtonLocator).submit();

        // Return a new page object representing the destination. Should the login page ever
        // go somewhere else (for example, a legal disclaimer) then changing the method signature
        // for this method will mean that all tests that rely on this behaviour won't compile.
        return new HomePage(driver);	
    }

    // The login page allows the user to submit the login form knowing that an invalid username and / or password were entered
    public LoginPage submitLoginExpectingFailure() {
        // This is the only place that submits the login form and expects the destination to be the login page due to login failure.
        driver.findElement(loginButtonLocator).submit();

        // Return a new page object representing the destination. Should the user ever be navigated to the home page after submitting a login with credentials 
        // expected to fail login, the script will fail when it attempts to instantiate the LoginPage PageObject.
        return new LoginPage(driver);	
    }

    // Conceptually, the login page offers the user the service of being able to "log into"
    // the application using a user name and password. 
    public HomePage loginAs(String username, String password) {
        // The PageObject methods that enter username, password & submit login have already defined and should not be repeated here.
        typeUsername(username);
        typePassword(password);
        return submitLogin();
    }
}
```
