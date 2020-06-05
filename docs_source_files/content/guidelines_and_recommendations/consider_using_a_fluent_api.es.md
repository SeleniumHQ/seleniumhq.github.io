---
title: "Considerar el uso de un API fluída"
weight: 8
---

Martin Fowler acuñó el término ["API fluido"](//www.martinfowler.com/bliki/FluentInterface.html). 
Selenium ya implementa algo como esto en su clase `FluentWait`, que
se entiende como una alternativa a la clase estándar <code>Wait</code>.
Puedes habilitar el patrón de diseño Fluent API en tu objeto de página
y luego consulta la página de búsqueda de Google con un fragmento de código como este:

```java
driver.get( "http://www.google.com/webhp?hl=en&amp;tab=ww" );
GoogleSearchPage gsp = new GoogleSearchPage();
gsp.withFluent().setSearchString().clickSearchButton();
```

La clase de objeto de página de Google con este comportamiento fluido
podría verse así:

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
    this.get(); // Si load() falla, se llama a isLoaded() hasta que la página termine de cargarse
    PageFactory.initElements(driver, this); // Inicializa los WebElements en la pagina
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