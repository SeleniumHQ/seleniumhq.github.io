---
title: "Domain specific language"
weight: 2
---

A domain specific language (DSL) is a system which provides the user with
an expressive means of solving a problem. It allows a user to
interact with the system on their terms – not just programmer-speak.

Your users, in general, do not care how your site looks. They do not
care about the decoration, animations, or graphics. They
want to use your system to push their new employees through the
process with minimal difficulty; they want to book travel to Alaska; 
they want to configure and buy unicorns at a discount. Your job as
tester is to come as close as you can to “capturing” this mind-set.
With that in mind, we set about “modeling” the application you are
working on, such that the test scripts (the user's only pre-release
proxy) “speak” for, and represent the user.

With Selenium, DSL is usually represented by methods, written to make
the API simple and readable – they enable a report between the
developers and the stakeholders (users, product owners, business
intelligence specialists, etc.).

## Benefits

* **Readable:** Business stakeholders can understand it.
* **Writable:** Easy to write, avoids unnecessary duplication.
* **Extensible:** Functionality can (reasonably) be added
  without breaking contracts and existing functionality.
* **Maintainable:** By leaving the implementation details out of test
  cases, you are well-insulated against changes to the AUT*.


## Java

Here is an example of a reasonable DSL method in Java.
For brevity's sake, it assumes the `driver` object is pre-defined
and available to the method.

```java
/**
 * Takes a username and password, fills out the fields, and clicks "login".
 * @return An instance of the AccountPage
 */
public AccountPage loginAsUser(String username, String password) {
  WebElement loginField = driver.findElement(By.id("loginField"));
  loginField.clear();
  loginField.sendKeys(username);

  // Fill out the password field. The locator we're using is "By.id", and we should
  // have it defined elsewhere in the class.
  WebElement passwordField = driver.findElement(By.id("password"));
  passwordField.clear();
  passwordField.sendKeys(password);

  // Click the login button, which happens to have the id "submit".
  driver.findElement(By.id("submit")).click();

  // Create and return a new instance of the AccountPage (via the built-in Selenium
  // PageFactory).
  return PageFactory.newInstance(AccountPage.class);
}
```

This method completely abstracts the concepts of input fields,
buttons, clicking, and even pages from your test code. Using this
approach, all a tester has to do is call this method. This gives
you a maintenance advantage: if the login fields ever changed, you
would only ever have to change this method - not your tests.

```java
public void loginTest() {
    loginAsUser("cbrown", "cl0wn3");

    // Now that we're logged in, do some other stuff--since we used a DSL to support
    // our testers, it's as easy as choosing from available methods.
    do.something();
    do.somethingElse();
    Assert.assertTrue("Something should have been done!", something.wasDone());

    // Note that we still haven't referred to a button or web control anywhere in this
    // script...
}
```

It bears repeating: one of your primary goals should be writing an
API that allows your tests to address **the problem at hand, and NOT
the problem of the UI**. The UI is a secondary concern for your
users – they do not care about the UI, they just want to get their job
done. Your test scripts should read like a laundry list of things
the user wants to DO, and the things they want to KNOW. The tests
should not concern themselves with HOW the UI requires you to go
about it.

***AUT**: Application under test

