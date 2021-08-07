---
title: "Langue spécifique au domaine"
linkTitle: "Langue spécifique au domaine"
weight: 4
---

Une langue spécifique au domaine (DSL) est un système qui fournit à l'utilisateur
un moyen expressif de résoudre un problème. Il permet à un utilisateur de
interagir avec le système à leurs conditions - pas seulement parler de programmeur.

Vos utilisateurs, en général, ne se soucient pas de l'apparence de votre site. Ils ne
se soucient de la décoration, des animations ou des graphismes. Ils
voulez utiliser votre système pour pousser leurs nouveaux employés à travers le
processus avec une difficulté minimale; ils veulent réserver un voyage en Alaska;
ils veulent configurer et acheter des licornes à prix réduit. Votre travail en tant que
testeur doit se rapprocher le plus possible de la «capture» de cet état d'esprit.
Dans cet esprit, nous avons entrepris de "modéliser" l'application que vous êtes
travailler, de telle sorte que les scripts de test (la seule version préliminaire de l'utilisateur
proxy) "parler" et représenter l'utilisateur.

Avec Selenium, DSL est généralement représenté par des méthodes, écrites pour
l'API simple et lisible - ils permettent un rapport entre le
les développeurs et les parties prenantes (utilisateurs, propriétaires de produits,
spécialistes du renseignement, etc.).

## Benefits

* **Lisible:** Les parties prenantes commerciales peuvent le comprendre.
* **Inscriptible:** Facile à écrire, évite les doublons inutiles.
* **Extensible:** La fonctionnalité peut (raisonnablement) être ajoutée 
sans rompre les contrats et les fonctionnalités existantes.
* **Maintenable:** En laissant les détails de mise en œuvre hors de test 
cas, vous êtes bien isolé contre les modifications de l'AUT*.

## Java

Voici un exemple d'une méthode DSL raisonnable en Java.
Par souci de concision, il suppose que l'objet `driver` est prédéfini
et disponible pour la méthode.

```java
/**
 * Takes a username and password, fills out the fields, and clicks "login".
 * @return An instance of the AccountPage
 */
public AccountPage loginAsUser(String username, String password) {
  WebElement loginField = driver.findElement(By.id("loginField"));
  loginField.clear();
  loginField.sendKeys(username);

  // Fill out the password field. The locator we're using is "By.id", and we should
  // have it defined elsewhere in the class.
  WebElement passwordField = driver.findElement(By.id("password"));
  passwordField.clear();
  passwordField.sendKeys(password);

  // Click the login button, which happens to have the id "submit".
  driver.findElement(By.id("submit")).click();

  // Create and return a new instance of the AccountPage (via the built-in Selenium
  // PageFactory).
  return PageFactory.newInstance(AccountPage.class);
}
```

Cette méthode résume complètement les concepts de champs de saisie,
des boutons, des clics et même des pages de votre code de test. Utiliser ceci
approche, tout ce qu'un testeur doit faire est d'appeler cette méthode. Cela donne
vous un avantage de maintenance: si les champs de connexion ont changé, vous
n'aurait qu'à changer cette méthode - pas vos tests.

```java
public void loginTest() {
    loginAsUser("cbrown", "cl0wn3");

    // Now that we're logged in, do some other stuff--since we used a DSL to support
    // our testers, it's as easy as choosing from available methods.
    do.something();
    do.somethingElse();
    Assert.assertTrue("Something should have been done!", something.wasDone());

    // Note that we still haven't referred to a button or web control anywhere in this
    // script...
}
```

Il faut le répéter: l'un de vos principaux objectifs devrait être de rédiger un
API qui permet à vos tests de répondre **le problème à portée de main, et NON
le problème de l'interface utilisateur**. L'interface utilisateur est une préoccupation secondaire pour votre
utilisateurs - ils ne se soucient pas de l'interface utilisateur, ils veulent juste obtenir leur emploi
terminé. Vos scripts de test doivent se lire comme une liste de choses à laver
l'utilisateur veut FAIRE et ce qu'il veut SAVOIR. Les tests
ne devrait pas se préoccuper de la façon dont l'interface utilisateur vous oblige à aller
à propos de ça.

***AUT**: Application under test

