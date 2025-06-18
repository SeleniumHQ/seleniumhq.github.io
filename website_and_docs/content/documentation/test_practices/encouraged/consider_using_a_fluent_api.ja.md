---
title: "Fluent APIの使用を検討する"
linkTitle: "Fluent APIの使用を検討する"
weight: 10
aliases: [
"/documentation/ja/guidelines_and_recommendations/consider_using_a_fluent_api/",
"/ja/documentation/guidelines/consider_using_a_fluent_api/"
]
---


マーチン・ファウラーは["Fluent API"](//www.martinfowler.com/bliki/FluentInterface.html)という用語を作り出しました。
Seleniumは既に、`FluentWait`クラスでこのようなものを実装しています。
これは、標準の<code>Wait</code>クラスの代替としてのものです。
ページオブジェクトでFluent APIデザインパターンを有効にしてから、次のようなコードスニペットを使用してGoogle検索ページを照会できます。

```java
driver.get( "http://www.google.com/webhp?hl=en&amp;tab=ww" );
GoogleSearchPage gsp = new GoogleSearchPage(driver);
gsp.setSearchString().clickSearchButton();
```

この流暢な動作を持つGoogleページオブジェクトクラスは次のようになります。

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
