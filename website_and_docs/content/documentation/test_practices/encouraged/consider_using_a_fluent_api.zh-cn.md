---
title: "考虑使用Fluent API"
linkTitle: "考虑使用Fluent API"
weight: 10
aliases: [
"/documentation/zh-cn/guidelines_and_recommendations/consider_using_a_fluent_api/",
"/zh-cn/documentation/guidelines/consider_using_a_fluent_api/"
]
---

Martin Fowler创造了术语 ["Fluent API"](//www.martinfowler.com/bliki/FluentInterface.html). 
Selenium已经在其 `FluentWait` 类中实现了类似的东西, 这是对标准 <code>Wait</code> 类的替代. 
您可以在页面对象中启用Fluent API设计模式, 然后使用如下代码段查询Google搜索页面:

```java
driver.get( "http://www.google.com/webhp?hl=en&amp;tab=ww" );
GoogleSearchPage gsp = new GoogleSearchPage(driver);
gsp.setSearchString().clickSearchButton();
```

Google页面对象类具有这种流畅行为后可能看起来像这样:

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
