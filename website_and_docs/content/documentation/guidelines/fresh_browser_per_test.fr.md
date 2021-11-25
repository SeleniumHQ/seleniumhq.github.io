---
title: "Nouveau navigateur par test"
linkTitle: "Nouveau navigateur par test"
weight: 11
aliases: ["/documentation/fr/guidelines_and_recommendations/fresh_browser_per_test/"]  
---

Commencez chaque test à partir d'un état connu propre.
Idéalement, faites tourner une nouvelle machine virtuelle pour chaque test.
Si la rotation d'une nouvelle machine virtuelle n'est pas pratique,
démarrez au moins un nouveau WebDriver pour chaque test.
Pour Firefox, démarrez un WebDriver avec votre profil connu.

```java
FirefoxProfile profile = new FirefoxProfile(new File("pathToFirefoxProfile"));
WebDriver driver = new FirefoxDriver(profile);
```
