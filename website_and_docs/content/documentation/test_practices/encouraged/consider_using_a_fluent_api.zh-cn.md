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
GoogleSearchPage gsp = new GoogleSearchPage();
gsp.withFluent().setSearchString().clickSearchButton();
```

Google页面对象类具有这种流畅行为后可能看起来像这样:

```java
public class GoogleSearchPage extends LoadableComponent<GoogleSearchPage> {
  private final WebDriver driver;
  private GSPFluentInterface gspfi;

  public class GSPFluentInterface {
    private GoogleSearchPage gsp;

    public GSPFluentInterface(GoogleSearchPage googleSearchPage) {
        gsp = googleSearchPage;
    }

    public GSPFluentInterface clickSearchButton() {
        gsp.searchButton.click();
        return this;
    }

    public GSPFluentInterface setSearchString( String sstr ) {
        clearAndType( gsp.searchField, sstr );
        return this;
    }
  }

  @FindBy(id = "gbqfq") private WebElement searchField;
  @FindBy(id = "gbqfb") private WebElement searchButton;
  public GoogleSearchPage(WebDriver driver) {
    gspfi = new GSPFluentInterface( this );
    this.get(); // If load() fails, calls isLoaded() until page is finished loading
    PageFactory.initElements(driver, this); // Initialize WebElements on page
  }

  public GSPFluentInterface withFluent() {
    return gspfi;
  }

  public void clickSearchButton() {
    searchButton.click();
  }

  public void setSearchString( String sstr ) {
    clearAndType( searchField, sstr );
  }

  @Override
  protected void isLoaded() throws Error {
    Assert.assertTrue("Google search page is not yet loaded.", isSearchFieldVisible() );
  }

  @Override
  protected void load() {
    if ( isSFieldPresent ) {
      Wait<WebDriver> wait = new WebDriverWait( driver, Duration.ofSeconds(3) );
      wait.until( visibilityOfElementLocated( By.id("gbqfq") ) ).click();
    }
  }
}
```
