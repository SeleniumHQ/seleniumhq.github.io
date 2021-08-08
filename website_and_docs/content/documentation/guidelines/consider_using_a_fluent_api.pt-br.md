---
title: "Considere usar uma API fluente"
LinkTitle: "Considere usar uma API fluente"
weight: 10
aliases: ["/documentation/pt-br/guidelines_and_recommendations/consider_using_a_fluent_api/"]  
---


Martin Fowler cunhou o termo ["API Fluent"](//www.martinfowler.com/bliki/FluentInterface.html). Selenium já
implementa algo assim em sua classe `FluentWait`, que é
pretende ser uma alternativa à classe padrão <code>Wait</code>.
Você pode habilitar o padrão de design de API fluente em seu objeto de página
e, em seguida, consulte a página de pesquisa do Google com um snippet de código como este:

```java
driver.get( "http://www.google.com/webhp?hl=en&amp;tab=ww" );
GoogleSearchPage gsp = new GoogleSearchPage();
gsp.withFluent().setSearchString().clickSearchButton();
```

A classe de objeto da página do Google com este comportamento fluente
pode ser assim:

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
    this.get(); // Se load() falhar, chama isLoaded() até que a página termine de carregar
    PageFactory.initElements(driver, this); // Inicializa WebElements na página
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
