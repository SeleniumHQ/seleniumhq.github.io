---
title: "Page object models"
weight: 1
---

{{% notice info %}}
<i class="fas fa-language"></i> Page being translated from 
English to French. Do you speak French? Help us to translate
it by sending us pull requests!
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
                selenium.type("inputBox", "testUser");
                selenium.type("password", "my supersecret password");
                selenium.click("sign-in");
                selenium.waitForPageToLoad("PageWaitPeriod");
                Assert.assertTrue(selenium.isElementPresent("compose button"),
                                "Login was unsuccessful");
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
/**
 * Page Object encapsulates the Sign-in page.
 */
public class SignInPage {

        private Selenium selenium;

        public SignInPage(Selenium selenium) {
                this.selenium = selenium;
                if(!selenium.getTitle().equals("Sign in page")) {
                        throw new IllegalStateException("This is not sign in page, current page is: "
                                        +selenium.getLocation());
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
                selenium.type("usernamefield", userName);
                selenium.type("passwordfield", password);
                selenium.click("sign-in");
                selenium.waitForPageToLoad("waitPeriod");

                return new HomePage(selenium);
        }
}
```

and page object for a Home page could look like this.

```java
/**
 * Page Object encapsulates the Home Page
 */
public class HomePage {

        private Selenium selenium;

        public HomePage(Selenium selenium) {
                if (!selenium.getTitle().equals("Home Page of logged in user")) {
                        throw new IllegalStateException("This is not Home Page of logged in user, current page" +
                                        "is: " +selenium.getLocation());
                }
        }

        public HomePage manageProfile() {
                // Page encapsulation to manage profile functionality
                return new HomePage(selenium);
        }

        /*More methods offering the services represented by Home Page
        of Logged User. These methods in turn might return more Page Objects
        for example click on Compose mail button could return ComposeMail class object*/

}
```

So now, the login test would use these two page objects as follows.

```java
/***
 * Tests login feature
 */
public class TestLogin {

        public void testLogin() {
                SignInPage signInPage = new SignInPage(selenium);
                HomePage homePage = signInPage.loginValidUser("userName", "password");
                Assert.assertTrue(selenium.isElementPresent("compose button"),
                                "Login was unsuccessful");
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

A page object does not necessarily need to represent an entire page. The Page
Object design pattern could be used to represent components on a page. If a
page in the AUT has multiple components, it may improve maintainability if
there is a separate page object for each component.

There are other design patterns that also may be used in testing. Some use a
Page Factory for instantiating their page objects. Discussing all of these is
beyond the scope of this user guide. Here, we merely want to introduce the
concepts to make the reader aware of some of the things that can be done. As
was mentioned earlier, many have blogged on this topic and we encourage the
reader to search for blogs on these topics.
