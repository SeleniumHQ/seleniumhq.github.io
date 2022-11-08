---
title: "PO设计模式"
linkTitle: "PO设计模式"
weight: 3
needsTranslation: true
aliases: [
"/documentation/zh-cn/guidelines_and_recommendations/page_object_models/",
"/zh-cn/documentation/guidelines/page_object_models/"
]
---


PO（page object）设计模式是在自动化中已经流行起来的一种易于维护和减少代码的设计模式. 在自动化测试中, PO对象作为一个与页面交互的接口. 
测试中需要与页面的UI进行交互时, 便调用PO的方法. 这样做的好处是, 如果页面的UI发生了更改,那么测试用例本身不需要更改, 只需更改PO中的代码即可. 

PO设计模式具有以下优点: 

* 测试代码与页面的定位代码（如定位器或者其他的映射）相分离. 
* 该页面提供的方法或元素在一个独立的类中, 而不是将这些方法或元素分散在整个测试中. 

这允许在一个地方修改由于UI变化所带来的所有修改. 随着这种"测试设计模式"的广泛使用, 可以在众多博客中找到有关此技术的有用信息. 
我们鼓励希望了解更多信息的读者在互联网上搜索有关此主题的博客. 许多人已经写过这种设计模式, 并且可以提供超出本用户指南范围的有用提示. 
不过, 为了让您入门, 我们将通过一个简单的示例来说明页面对象. 

首先, 思考一个不使用PO模式的自动化测试的典型案例: 

```java
/***
 * Tests login feature
 */
public class Login {

  public void testLogin() {
    // 在登录页面上填写登录数据
    driver.findElement(By.name("user_name")).sendKeys("userName");
    driver.findElement(By.name("password")).sendKeys("my supersecret password");
    driver.findElement(By.name("sign-in")).click();

    // 登录后验证h1标签是否为Hello userName
    driver.findElement(By.tagName("h1")).isDisplayed();
    assertThat(driver.findElement(By.tagName("h1")).getText(), is("Hello userName"));
  }
}
```

这种方法有两个问题. 

* 测试方法与定位器 (在此实例中为By.name)耦合过于严重. 如果测试的用户界面更改了其定位器或登录名的输入和处理方式, 则测试本身必须进行更改. 
* 在对登录页面的所有测试中, 同一个定位器会散布在其中. 

可以在以下登录页面的示例中应用PO设计模式重写此示例. 

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
    if (!driver.getTitle().equals("Home Page of logged in user")) {
      throw new IllegalStateException("This is not Home Page of logged in user," +
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

Home page的PO如下所示. 

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
  /* 提供登录用户主页所代表的服务的更多方法. 这些方法可能会返回更多页面对象. 
  例如, 单击"撰写邮件"按钮可以返回ComposeMail类对象 */
}
```

那么, 接下来的登录测试用例将使用这两个页面对象. 

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

PO的设计方式具有很大的灵活性, 但是有一些基本规则可以使测试代码具有理想的可维护性. 

PO本身绝不应进行判断或断言. 判断和断言是测试的一部分, 应始终在测试的代码内, 而不是在PO中. 
PO用来包含页面的表示形式, 以及页面通过方法提供的服务, 但是与PO无关的测试代码不应包含在其中. 

实例化PO时, 应进行一次验证, 即验证页面以及页面上可能的关键元素是否已正确加载. 
在上面的示例中, SignInPage和HomePage的构造函数均检查预期的页面是否可用并准备接受测试请求. 

PO不一定需要代表整个页面.  PO设计模式可用于表示页面上的组件. 
如果自动化测试中的页面包含多个组件, 则每个组件都有单独的页面对象, 则可以提高可维护性. 

还有其他设计模式也可以在测试中使用. 一些使用页面工厂实例化其页面对象. 讨论所有这些都不在本用户指南的范围之内. 
在这里, 我们只想介绍一些概念, 以使读者了解可以完成的一些事情. 
如前所述, 许多人都在此主题上写博客, 我们鼓励读者搜索有关这些主题的博客. 

