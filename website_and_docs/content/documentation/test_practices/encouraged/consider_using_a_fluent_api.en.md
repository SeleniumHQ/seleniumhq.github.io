---
title: "Consider using a fluent API"
linkTitle: "Consider using a fluent API"
weight: 10
aliases: [
"/documentation/en/guidelines_and_recommendations/consider_using_a_fluent_api/",
"/documentation/guidelines/consider_using_a_fluent_api/"
]
---


Martin Fowler coined the term ["Fluent API"](//www.martinfowler.com/bliki/FluentInterface.html). Selenium already
implements something like this in their `FluentWait` class, which is
meant as an alternative to the standard <code>Wait</code> class. 
You could enable the Fluent API design pattern in your page object 
and then query the Google search page with a code snippet like this one:

```java
driver.get( "http://www.google.com/webhp?hl=en&amp;tab=ww" );
GoogleSearchPage gsp = new GoogleSearchPage(driver);
gsp.setSearchString().clickSearchButton();
```

The Google page object class with this fluent behavior
might look like this:

```java
public abstract class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
}

public class GoogleSearchPage extends BasePage {
    public GoogleSearchPage(WebDriver driver) {
        super(driver);
        // Generally do not assert within pages or components.
        // Effectively throws an exception if the lambda condition is not met.
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(d -> d.findElement(By.id("logo")));
    }

    public GoogleSearchPage setSearchString(String sstr) {
        driver.findElement(By.id("gbqfq")).sendKeys(sstr);
        return this;
    }

    public void clickSearchButton() {
        driver.findElement(By.id("gbqfb")).click();
    }
}
```
