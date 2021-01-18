---
title: "Modelos de objetos de página"
weight: 1
---

Page Object (objeto de página) es un patrón de diseño que se ha vuelto popular en 
la automatización de pruebas para mejorar el mantenimiento de 
las pruebas y reducir la duplicación de código. Un objeto de 
página es un clase orientada a objetos que sirve como interfaz 
para una página de tu AUT. Las pruebas luego usan los 
métodos de esta clase de objeto de página cuando lo necesitan 
para interactuar con la interfaz de usuario de esa página. El 
beneficio es que si la interfaz de usuario cambia para la 
página, las pruebas en sí mismas no necesitan cambiar, solo el 
código dentro del objeto de página necesita cambiar. 
Posteriormente, todos los cambios para soportar esa nueva 
interfaz de usuario están ubicados en un solo lugar. 

El patrón de diseño de objetos de página ofrece las siguientes 
ventajas: 

* Existe una separación clara entre el código de prueba y el 
código específico de la página, como localizadores (o su uso si 
está utilizando un mapa de interfaz de usuario) y diseño. 
* Existe un único repositorio para los servicios u operaciones que 
ofrece la página en lugar de tener estos servicios dispersos a 
lo largo de las pruebas. 

En ambos casos, esto permite cualquier modificación requerida 
debido a cambios en la interfaz de usuario puedan hacerse en 
un solo lugar. Puedes encontrar buena información sobre esta 
técnica en numerosos blogs, ya que este "patrón de diseño de 
prueba" se está utilizando ampliamente. Nosotros animamos al 
lector que desea saber más a buscar blogs en Internet al respecto.  
Muchos han escrito sobre este patrón de diseño y 
pueden proporcionar consejos útiles más allá del alcance de esta 
guía del usuario. Para empezar, sin embargo, ilustraremos 
objetos de página con un ejemplo simple. 

Primero, considere un ejemplo, típico de la automatización de 
pruebas, que no utiliza un objeto de página:

```java
/***
 * Prueba de la funcionalidad de inicio de sesión
 */
public class Login {

  public void testLogin() {
    // ingresa los datos de inicio de sesión en la página de inicio de sesión
    driver.findElement(By.name("user_name")).sendKeys("testUser");
    driver.findElement(By.name("password")).sendKeys("my supersecret password");
    driver.findElement(By.name("sign-in")).click();

    // verifica que la etiqueta h1 tiene el valor "Hello userName" después de iniciar sesión
    driver.findElement(By.tagName("h1")).isDisplayed();
    assertThat(driver.findElement(By.tagName("h1")).getText(), is("Hello userName"));
  }
}
```

Hay dos problemas con este enfoque. 

* No hay separación entre el método de prueba y los 
localizadores del AUT (ID en este ejemplo); ambos están 
entrelazados en un solo método. Si la UI del AUT cambia sus 
identificadores, diseño o cómo se ingresa y procesa un inicio de 
sesión, la prueba en sí debe cambiar. 
* Los localizadores de ID 
se distribuirían en múltiples pruebas, en todas las pruebas que 
debían usar esta página de inicio de sesión. 

Aplicando las técnicas de objeto de página, este ejemplo podría 
reescribirse como en el siguiente ejemplo de un objeto de página 
para una página de inicio de sesión.

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * El objeto de página encapsula la página de inicio de sesión.
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
    * Inicia sesión como un usuario válido
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
```

y el objeto de página para una página de inicio podría verse así.

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * El objeto de página encapsula la página de inicio
 */
public class HomePage {
  protected WebDriver driver;

  // <h1>Hola userName</h1>
  private By messageBy = By.tagName("h1");

  public HomePage(WebDriver driver){
    this.driver = driver;
    if (!driver.getTitle().equals("Home Page of logged in user")) {
      throw new IllegalStateException("This is not Home Page of logged in user," +
            " current page is: " + driver.getCurrentUrl());
    }
  }

  /**
    * Get message (h1 tag)
    *
    * @return String message text
    */
  public String getMessageText() {
    return driver.findElement(messageBy).getText();
  }

  public HomePage manageProfile() {
    // Encapsulación de página para administrar la funcionalidad del perfil
    return new HomePage(driver);
  }
  /* Mas métodos que ofrecen los servicios representados por la página de inicio
   del usuario registrado. Estos métodos a su vez podrían devolver más objetos de página,
   por ejemplo hacer clic en el botón Redactar correo podría devolver el objeto de clase ComposeMail */
}
```

Entonces, la prueba de inicio de sesión usaría estos dos objetos de página de la siguiente manera.

```java
/***
 * Prueba de la funcionalidad de inicio de sesión de pruebas
 */
public class TestLogin {

  @Test
  public void testLogin() {
    SignInPage signInPage = new SignInPage(driver);
    HomePage homePage = signInPage.loginValidUser("userName", "password");
    assertThat(homePage.getMessageText(), is("Hello userName"));
  }

}
```

Hay mucha flexibilidad en cómo se pueden diseñar los objetos de 
página, pero hay algunas reglas básicas para obtener la 
mantenibilidad deseada de tu código de prueba.

Los objetos de página en sí mismos nunca deben hacer 
verificaciones o afirmaciones. Esto es parte de tu prueba y 
siempre debe estar dentro del código de la prueba, nunca en una 
página objeto. El objeto de página contendrá la representación 
de la página, y el servicios que proporciona la página a través 
de métodos, pero ningún código relacionado con lo que se está 
probado debe estar dentro del objeto de la página. 

Hay una verificación única que puede y debe estar dentro del
objeto de página y eso es para verificar que la página, y 
posiblemente elementos críticos en la página, se cargaron 
correctamente. Esta verificación debe hacerse mientras 
se instancia el objeto de la página. En los ejemplos anteriores, 
tanto SignInPage como los constructores de HomePage verifican 
que la página esperada esté disponible y lista para las solicitudes 
de la prueba. 

Un objeto de página no necesariamente necesita representar una 
página completa. El patrón de diseño de objetos de pagina podría 
usarse para representar componentes en una página. Si la 
página en el AUT tiene múltiples componentes, puede mejorar la 
mantenibilidad si hay un objeto de página separado para cada 
componente. 

Existen otros patrones de diseño que también pueden usarse en 
las pruebas. Algunos usan un Page Factory para crear instancias 
de sus objetos de página. Discutir todo esto es más allá del 
alcance de esta guía del usuario. Aquí, simplemente queremos 
presentar los conceptos para que el lector tome conciencia de 
algunas de las cosas que se pueden hacer. Como fue mencionado 
anteriormente, muchos han blogueado sobre este tema y alentamos 
lector para buscar blogs sobre estos temas.
