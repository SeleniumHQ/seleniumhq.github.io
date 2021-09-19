---
title: "Page object models translated into Spanish"
linkTitle: "Modelos de Objetos página"
weight: 100
description: >-
 Translation into Spanish of https://www.selenium.dev/es/documentation/guidelines/page_object_models/
---

## Heading

Modelos de objetos página (Page object models POM):
* Hay mucha flexibilidad en cómo se pueden diseñar los objetos página, pero hay algunas
* reglas básicas para conseguir la mantenibilidad deseada de su código de prueba.
* Los objetos página en sí mismos nunca deben hacer verificaciones o aseveraciones. Esto es parte de
* su prueba y siempre debe estar dentro del código de la prueba, nunca en un
* objeto página. El objeto página contendrá la representación de la página, y los
* servicios que la página proporciona a través de métodos, pero ningún código
* relacionado con lo que se está probando debe estar dentro del objeto página.
* Hay una única verificación que puede, y debe, estar dentro del objeto página y es verificar
* que la página, y posiblemente los elementos críticos de la página, se cargaron
* correctamente. Esta verificación debe hacerse al instanciar el objeto página.
* En los ejemplos anteriores, tanto el constructor de SignInPage como el de
* HomePage comprueban que la página esperada está disponible y lista para las
* peticiones de la prueba.
* Un objeto página no tiene que representar necesariamente todas las partes de una página en sí.
* Los mismos principios utilizados para los objetos página pueden utilizarse para
* crear "objetos componentes de página" que representan trozos
* discretos de la página y pueden incluirse en los objetos página. Estos objetos
* componentes pueden proporcionar referencias a los elementos dentro de esos
* trozos discretos, y métodos para aprovechar la funcionalidad proporcionada por
* ellos. Incluso se pueden anidar objetos componentes dentro de otros objetos
* componentes para obtener páginas más complejas. Si una página en el AST tiene
* múltiples componentes, o componentes comunes utilizados en todo el sitio (por
* ejemplo, una barra de navegación), entonces puede mejorar la mantenibilidad y
* reducir la duplicación de código.



 



Hay otros patrones
de diseño que también pueden utilizarse en las pruebas. Algunos utilizan una
fábrica de páginas para instanciar sus objetos página. Discutir todo esto está
más allá del alcance de esta guía de usuario. Aquí, simplemente queremos
introducir los conceptos para que el lector sea consciente de algunas de las
cosas que se pueden hacer. Como se mencionó anteriormente, muchos han escrito un
blog sobre este tema y animamos al lector a buscar blogs sobre estos temas.




/***
 * Tests login feature
 */
public class Login {

  public void testLogin() {
    // fill login data on sign-in page
    driver.findElement(By.name("user_name")).sendKeys("testUser");
    driver.findElement(By.name("password")).sendKeys("my supersecret password");
    driver.findElement(By.name("sign-in")).click();

    // verify h1 tag is "Hello userName" after login
    driver.findElement(By.tagName("h1")).isDisplayed();
    assertThat(driver.findElement(By.tagName("h1")).getText(), is("Hello userName"));
  }
}
/**
*/**
 * Page Object encapsulates the Sign-in page.
 */
public class SignInPage {
  protected WebDriver driver;

  // <input name="user_name" type="text" value="">
  private By usernameBy = By.name("user_name");
  // <input name="password" type="password" value="">
  private By passwordBy = By.name("password");
  // <input name="sign_in" type="submit" value="SignIn">
  private By signinBy = By.name("sign_in");

  public SignInPage(WebDriver driver){
    this.driver = driver;
  }
  /**
    * Login as valid user
    *
    * @param userName
    * @param password
    * @return HomePage object
    */
  public HomePage loginValidUser(String userName, String password) {
    driver.findElement(usernameBy).sendKeys(userName);
    driver.findElement(passwordBy).sendKeys(password);
    driver.findElement(signinBy).click();
    return new HomePage(driver);
  }
}
/***
 * Tests login feature
 */
public class TestLogin {

  @Test
  public void testLogin() {
    SignInPage signInPage = new SignInPage(driver);
    HomePage homePage = signInPage.loginValidUser("userName", "password");
    assertThat(homePage.getMessageText(), is("Hello userName"));
  }

}


* Give it a good name, ending in `.md` - e.g. `getting-started.md`
* Edit the "front matter" section at the top of the page (weight controls how its ordered amongst other pages in the same directory; lowest number first).
* Add a good commit message at the bottom of the page (<80 characters; use the extended description field for more detail).
* Create a new branch so you can preview your new file and request a review via Pull Request.
