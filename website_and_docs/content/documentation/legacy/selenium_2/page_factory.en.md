---
title: "The PageFactory"
linkTitle: "PageFactory"
weight: 9
description: >
    Java support class for initializing WebElement fields in Page Objects.
---

This documentation previously located [on the wiki](https://github.com/SeleniumHQ/selenium/wiki/PageFactory) \
For current guidance, read about [Page Object Models]({{< ref "/documentation/test_practices/encouraged/page_object_models" >}}).

In order to support the PageObject pattern, WebDriver's support library contains a factory class.

## A Simple Example

In order to use the PageFactory, first declare some fields on a PageObject that are 
`WebElement` or `List<WebElement>`, for example:

```java
package org.openqa.selenium.example;

import org.openqa.selenium.WebElement;

public class GoogleSearchPage {
    // Here's the element
    private WebElement q;

    public void searchFor(String text) {
        // And here we use it. Note that it looks like we've
        // not properly instantiated it yet....
        q.sendKeys(text);
        q.submit();
    }
}
```

In order for this code to work and not throw a NullPointerException because the "q" field 
isn't instantiated, we need to initialise the PageObject:

```java
package org.openqa.selenium.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.PageFactory;

public class UsingGoogleSearchPage {
    public static void main(String[] args) {
        // Create a new instance of a driver
        WebDriver driver = new HtmlUnitDriver();

        // Navigate to the right place
        driver.get("http://www.google.com/");

        // Create a new instance of the search page class
        // and initialise any WebElement fields in it.
        GoogleSearchPage page = PageFactory.initElements(driver, GoogleSearchPage.class);

        // And now do the search.
        page.searchFor("Cheese");
    }
}
```

### Explanation

The PageFactory relies on using sensible defaults: the name of the field in the Java class 
is assumed to be the "id" or "name" of the element on the HTML page. That is, in the 
example above, the line:

```java
    q.sendKeys(text);
```

is equivalent to:

```java
    driver.findElement(By.id("q")).sendKeys(text);
```

The driver instance that's used is the one that's passed to the PageFactory's initElements method.

In the example given, we rely on the PageFactory to instantiate the instance of the 
PageObject. It does this by first looking for a constructor that takes "WebDriver" as its 
sole argument (`public SomePage(WebDriver driver) {`). If this is not present, then the 
default constructor is called. Sometimes, however, the PageObject depends on more than just 
an instance of the WebDriver interface. Should this be the case, it is possible to get the 
PageFactory to initialise the elements of an already constructed object:

```java
ComplexPageObject page = new ComplexPageObject("expected title", driver);

// Note, we still need to pass in an instance of driver for the 
// initialised elements to use
PageFactory.initElements(driver, page);
```

## Making the Example Work Using Annotations

When we run the example, the PageFactory will search for an element on the page that 
matches the field name of the `WebElement` in the class. It does this by first looking for 
an element with a matching ID attribute. If this fails, the PageFactory falls back to 
searching for an element by the value of its "name" attribute.

Although the code works, someone who's not familiar with the source of the Google home page 
may not know that the name of the field is "q". Fortunately, we can pick a meaningful name 
and change the strategy used to look the element up using an annotation:

```java
package org.openqa.selenium.example;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.WebElement;

public class GoogleSearchPage {
    // The element is now looked up using the name attribute
    @FindBy(how = How.NAME, using = "q")
    private WebElement searchBox;

    public void searchFor(String text) {
        // We continue using the element just as before
        searchBox.sendKeys(text);
        searchBox.submit();
    }
}
```

One wrinkle that remains is that every time we call a method on the WebElement, the driver 
will go and find it on the current page again. In an AJAX-heavy application this is what 
you would like to happen, but in the case of the Google search page we know that the 
element is always going to be there and won't change. We also know that we won't be 
navigating away from the page and returning (which would mean that a different element 
with the same name would be present). It would be handy if we could "cache" the element 
once we'd looked it up:

```java
package org.openqa.selenium.example;

import org.openqa.selenium.By;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.WebElement;

public class GoogleSearchPage {
    // The element is now looked up using the name attribute,
    // and we never look it up once it has been used the first time
    @FindBy(how = How.NAME, using = "q")
    @CacheLookup
    private WebElement searchBox;

    public void searchFor(String text) {
        // We continue using the element just as before
        searchBox.sendKeys(text);
        searchBox.submit();
    }
}
```

### Reducing Verbosity

The example above is still a little verbose. A slightly cleaner way of annotating the field would be:

```java
public class GoogleSearchPage {
  @FindBy(name = "q")
  private WebElement searchBox;

  // The rest of the class is unchanged.
}
```

## Notes

* If you use the PageFactory, you can assume that the fields are initialised. If you don't 
  use the PageFactory, then NullPointerExceptions will be thrown if you make the assumption 
  that the fields are already initialised.
* `List<WebElement>` fields are decorated if and only if they have `@FindBy` or `@FindBys` 
  annotation. Default search strategy "by id or name" that works for WebElement fields is 
  hardly suitable for lists because it is rare to have several elements with the same id 
  or name on a page.
* WebElements are evaluated lazily. That is, if you never use a WebElement field in a 
  PageObject, there will never be a call to "findElement" for it.
* The functionality works using dynamic proxies. This means that you shouldn't expect a 
  WebElement to be a particular subclass, even if you know the type of the driver. For 
  example, if you are using the HtmlUnitDriver, you shouldn't expect the WebElement field 
  to be initialised with an instance of HtmlUnitWebElement.
