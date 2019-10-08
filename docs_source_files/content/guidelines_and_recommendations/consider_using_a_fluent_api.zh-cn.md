---
title: "Consider using a fluent API"
weight: 8
---

{{% notice info %}}
<i class="fas fa-language"></i> 页面需要从英语翻译为简体中文。
您熟悉英语与简体中文吗？帮助我们翻译它，通过 pull requests 给我们！
{{% /notice %}}

Martin Fowler coined the term ["Fluent API"](//www.martinfowler.com/bliki/FluentInterface.html). 
Selenium already implements something like this in their `FluentWait` class which is
meant as an alternative to the standard <code>Wait</code> class.
You could enable the Fluent API design pattern in your page object
and then query the Google search page with a code snippet like this one:

```java
driver.get( "http://www.google.com/webhp?hl=en&amp;tab=ww" );
GoogleSearchPage gsp = new GoogleSearchPage();
gsp.withFluent().setSearchString().clickSearchButton();
```

The Google page object class with this fluent behavior
might look like this:

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
      Wait<WebDriver> wait = new WebDriverWait( driver, 3 );
      wait.until( visibilityOfElementLocated( By.id("gbqfq") ) ).click();
    }
  }
}
```