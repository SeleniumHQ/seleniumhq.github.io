---
title: "Considere usar uma API fluente"
LinkTitle: "Considere usar uma API fluente"
weight: 10
aliases: [
"/documentation/pt-br/guidelines_and_recommendations/consider_using_a_fluent_api/",
"/pt-br/documentation/guidelines/consider_using_a_fluent_api/"
]
---


Martin Fowler cunhou o termo ["API Fluent"](//www.martinfowler.com/bliki/FluentInterface.html). Selenium já
implementa algo assim em sua classe `FluentWait`, que é
pretende ser uma alternativa à classe padrão <code>Wait</code>.
Você pode habilitar o padrão de design de API fluente em seu objeto de página
e, em seguida, consulte a página de pesquisa do Google com um snippet de código como este:

```java
driver.get( "http://www.google.com/webhp?hl=en&amp;tab=ww" );
GoogleSearchPage gsp = new GoogleSearchPage(driver);
gsp.setSearchString().clickSearchButton();
```

A classe de objeto da página do Google com este comportamento fluente
pode ser assim:

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
