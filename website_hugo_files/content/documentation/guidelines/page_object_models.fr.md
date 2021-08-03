---
title: "Page object models"
linkTitle: "Page object models"
weight: 3
---

L'objet de page est un modèle de conception qui est devenu populaire dans l'automatisation des tests pour
améliorer la maintenance des tests et réduire la duplication de code. Un objet page est un
classe orientée objet qui sert d'interface à une page de votre AUT. le
les tests utilisent ensuite les méthodes de cette classe d'objets de page chaque fois qu'ils ont besoin de
interagir avec l'interface utilisateur de cette page. L'avantage est que si l'interface utilisateur change pour
la page, les tests eux-mêmes n'ont pas besoin de changer, seul le code dans le
l'objet de page doit changer. Par la suite, tous les changements pour prendre en charge cette nouvelle interface utilisateur
sont situés en un seul endroit.

Le modèle de conception d'objet de page offre les avantages suivants:

* Il existe une séparation nette entre le code de test et le code spécifique à la page, tel que
  les localisateurs (ou leur utilisation si vous utilisez une carte d'interface utilisateur) et la mise en page.
* Il existe un référentiel unique pour les services ou opérations proposés par la page
  plutôt que d'avoir ces services dispersés tout au long des tests.

Dans les deux cas, cela permet toutes les modifications requises en raison des modifications de l'interface utilisateur à tous
être fait en un seul endroit. Vous trouverez des informations utiles sur cette technique sur
nombreux blogs car ce «modèle de conception de test» est de plus en plus utilisé. nous
encourager le lecteur qui souhaite en savoir plus à rechercher des blogs sur Internet
à propos de ce sujet. Beaucoup ont écrit sur ce modèle de conception et peuvent fournir
des conseils utiles dépassant le cadre de ce guide de l'utilisateur. Pour vous aider à démarrer, cependant,
nous allons illustrer les objets de page avec un exemple simple.

Prenons d'abord un exemple, typique de l'automatisation des tests, qui n'utilise pas de
objet page:

```java
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
```

Il y a deux problèmes avec cette approche.

* Il n'y a pas de séparation entre la méthode de test et les localisateurs de l'AUT (ID dans
cet exemple); les deux sont entrelacés dans une seule méthode. Si l'interface utilisateur de l'AUT change
ses identifiants, sa disposition ou la manière dont une connexion est entrée et traitée, le test lui-même
doit changer.
* Les localisateurs d'ID seraient répartis dans plusieurs tests, dans tous les tests qui devaient
utilisez cette page de connexion.

En appliquant les techniques d'objet de page, cet exemple pourrait être réécrit comme ceci
dans l'exemple suivant d'un objet de page pour une page de connexion.

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
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
```

et l'objet de page pour une page d'accueil pourrait ressembler à ceci.

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object encapsulates the Home Page
 */
public class HomePage {
  protected WebDriver driver;

  // <h1>Hello userName</h1>
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
    // Page encapsulation to manage profile functionality
    return new HomePage(driver);
  }
  /* More methods offering the services represented by Home Page
  of Logged User. These methods in turn might return more Page Objects
  for example click on Compose mail button could return ComposeMail class object */
}
```

Alors maintenant, le test de connexion utiliserait ces deux objets de page comme suit.

```java
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
```

Il y a beaucoup de flexibilité dans la façon dont les objets de page peuvent être conçus, mais
il existe quelques règles de base pour obtenir la maintenabilité souhaitée de votre
code de test.

Les objets de page eux-mêmes ne doivent jamais faire de vérifications ou d'assertions. C'est
partie de votre test et doit toujours être dans le code du test, jamais dans une page
objet. L'objet page contiendra la représentation de la page et le
services fournis par la page via des méthodes mais aucun code lié à ce qui est
testé doit se trouver dans l'objet page.

Il y a une seule vérification qui peut et doit être dans la page
objet et qui est de vérifier que la page, et éventuellement les éléments critiques sur
la page, ont été chargés correctement. Cette vérification doit être effectuée pendant
instancier l'objet page. Dans les exemples ci-dessus, SignInPage et
Les constructeurs de HomePage vérifient que la page attendue est disponible et prête pour
demandes du test.

Un objet de page n'a pas nécessairement besoin de représenter une page entière. La page
Le modèle de conception d'objet peut être utilisé pour représenter des composants sur une page. Si un
La page de l'AUT comporte plusieurs composants, elle peut améliorer la maintenabilité si
il existe un objet page distinct pour chaque composant.

Il existe d'autres modèles de conception qui peuvent également être utilisés dans les tests. Certains utilisent un
Page Factory pour instancier leurs objets de page. Discuter de tout cela est
au-delà de la portée de ce guide de l'utilisateur. Ici, nous voulons simplement introduire le
concepts pour sensibiliser le lecteur à certaines des choses qui peuvent être faites. Comme
a été mentionné plus tôt, beaucoup ont blogué sur ce sujet et nous encourageons
lecteur pour rechercher des blogs sur ces sujets.
