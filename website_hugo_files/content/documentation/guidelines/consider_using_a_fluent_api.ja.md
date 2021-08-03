---
title: "Fluent APIの使用を検討する"
linkTitle: "Fluent APIの使用を検討する"
weight: 10
---


マーチン・ファウラーは["Fluent API"](//www.martinfowler.com/bliki/FluentInterface.html)という用語を作り出しました。
Seleniumは既に、`FluentWait`クラスでこのようなものを実装しています。
これは、標準の<code>Wait</code>クラスの代替としてのものです。
ページオブジェクトでFluent APIデザインパターンを有効にしてから、次のようなコードスニペットを使用してGoogle検索ページを照会できます。

```java
driver.get( "http://www.google.com/webhp?hl=en&amp;tab=ww" );
GoogleSearchPage gsp = new GoogleSearchPage();
gsp.withFluent().setSearchString().clickSearchButton();
```

この流暢な動作を持つGoogleページオブジェクトクラスは次のようになります。

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
