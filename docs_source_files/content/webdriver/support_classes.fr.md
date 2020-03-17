---
title: "Classes de support"
weight: 5
---

Les classes de support WebDriver sont fournies pour simplifier 
la maintenance de votre code.Elles fournissent une belle 
abstraction pour faciliter la modélisation des éléments HTML en 
tant qu'objets de domaine, ainsi que des méthodes d'aide pour rendre 
l'utilisation de ces objets facile à raisonner.

* Stratégies de localisation
* Événements
* LoadableComponent
* ThreadGuard
* etc.


Commençons:


## **ThreadGuard**
{{% notice info %}}
Cette classe est uniquement disponible dans la liaison Java
{{% /notice %}}
ThreadGuard vérifie qu'un pilote est appelé uniquement à partir du même thread qui l'a créé.
Les problèmes de thread, en particulier lors de l'exécution de tests en parallèle, peuvent être mystérieux
et difficile à diagnostiquer les erreurs. L'utilisation de ce wrapper empêche cette catégorie d'erreurs
et lèvera une exception lorsque cela se produira.

L'exemple suivant simule un choc de threads:

```java
public class DriverClash {
  //thread main (id 1) created this driver
  private WebDriver protectedDriver = ThreadGuard.protect(new ChromeDriver());

  static {
    System.setProperty("webdriver.chrome.driver", "<Set path to your Chromedriver>");
  }

  //Thread-1 (id 24) is calling the same driver causing the clash to happen
  Runnable r1 = () -> {protectedDriver.get("https://selenium.dev");};
  Thread thr1 = new Thread(r1);

  void runThreads(){
    thr1.start();
  }

  public static void main(String[] args) {
    new DriverClash().runThreads();
  }
}
```

Le résultat ci-dessous:
```text
Exception in thread "Thread-1" org.openqa.selenium.WebDriverException:
Thread safety error; this instance of WebDriver was constructed
on thread main (id 1)and is being accessed by thread Thread-1 (id 24)
This is not permitted and *will* cause undefined behaviour

```
Comme le montre l'exemple:

 * `protectedDriver` sera créé dans le thread principal
 *  Nous utilisons Java `Runnable` pour lancer un nouveau processus et un nouveau `Thread` pour exécuter le processus
 *  Les deux `Thread` s'affrontent car le Thread principal n'a pas` protectedDriver` dans sa mémoire.
 * `ThreadGuard.protect` lèvera une exception.
 
#### Remarque:

Cela ne remplace pas la nécessité d'utiliser `ThreadLocal` pour gérer les pilotes lors de l'exécution en parallèle.

