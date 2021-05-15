---
title: "'Page object' Modell"
weight: 1
---

{{% notice info %}}
<i class="fas fa-language"></i> Diese Seite wird von Englisch 
auf Deutsch übersetzt. Sprichst Du Deutsch? Hilf uns die Seite 
zu übersetzen indem Du uns einen Pull Reqeust schickst!
 {{% /notice %}}
Page Object is a Design Pattern which has become popular in test automation for
enhancing test maintenance and reducing code duplication. A page object is an
object-oriented class that serves as an interface to a page of your AUT. The
tests then use the methods of this page object class whenever they need to
interact with the UI of that page. The benefit is that if the UI changes for
the page, the tests themselves don’t need to change, only the code within the
page object needs to change. Subsequently all changes to support that new UI
are located in one place.

The Page Object Design Pattern provides the following advantages:

* There is a clean separation between test code and page specific code such as
  locators (or their use if you’re using a UI Map) and layout.
* There is a single repository for the services or operations offered by the page
  rather than having these services scattered throughout the tests.

In both cases this allows any modifications required due to UI changes to all
be made in one place. Useful information on this technique can be found on
numerous blogs as this ‘test design pattern’ is becoming widely used. We
encourage the reader who wishes to know more to search the internet for blogs
on this subject. Many have written on this design pattern and can provide
useful tips beyond the scope of this user guide. To get you started, though,
we’ll illustrate page objects with a simple example.

First, consider an example, typical of test automation, that does not use a
page object:

```java
/***
 * Tests login feature
 */
public class Login {

  public void testLogin() {
    // fill login data on sign-in page
    driver.findElement(By.name("user_name")).sendKeys("testUser");
    driver.findElement(By.name("password")).sendKeys("my supersecret password");
    driver.findElement(By.name("sign-in")).click();

    // verify h1 tag is "Hello userName" after login
    driver.findElement(By.tagName("h1")).isDisplayed();
    assertThat(driver.findElement(By.tagName("h1")).getText(), is("Hello userName"));
  }
}
```

There are two problems with this approach.

* There is no separation between the test method and the AUT’s locators (IDs in 
this example); both are intertwined in a single method. If the AUT’s UI changes 
its identifiers, layout, or how a login is input and processed, the test itself 
must change.
* The ID-locators would be spread in multiple tests, in all tests that had to 
use this login page.

Applying the page object techniques, this example could be rewritten like this
in the following example of a page object for a Sign-in page.

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

and page object for a Home page could look like this.

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

So now, the login test would use these two page objects as follows.

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

There is a lot of flexibility in how the page objects may be designed, but
there are a few basic rules for getting the desired maintainability of your
test code.

Page objects themselves should never make verifications or assertions. This is
part of your test and should always be within the test’s code, never in an page
object. The page object will contain the representation of the page, and the
services the page provides via methods but no code related to what is being
tested should be within the page object.

There is one, single, verification which can, and should, be within the page
object and that is to verify that the page, and possibly critical elements on
the page, were loaded correctly. This verification should be done while
instantiating the page object. In the examples above, both the SignInPage and
HomePage constructors check that the expected page is available and ready for
requests from the test.

A page object does not necessarily need to represent all the parts of a
page itself. The same principles used for page objects can be used to
create "Page _Component_ Objects" that represent discrete chunks of the
page and can be included in page objects. These component objects can
provide references to the elements inside those discrete chunks, and
methods to leverage the functionality provided by them. You can even
nest component objects inside other component objects for more complex
pages. If a page in the AUT has multiple components, or common
components used throughout the site (e.g. a navigation bar), then it
may improve maintainability and reduce code duplication.

There are other design patterns that also may be used in testing. Some use a
Page Factory for instantiating their page objects. Discussing all of these is
beyond the scope of this user guide. Here, we merely want to introduce the
concepts to make the reader aware of some of the things that can be done. As
was mentioned earlier, many have blogged on this topic and we encourage the
reader to search for blogs on these topics.
