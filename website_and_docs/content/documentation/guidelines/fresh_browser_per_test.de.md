---
title: "Neuer Browser pro Test"
linkTitle: "Neuer Browser pro Test"
weight: 11
---

Starte jeden Test mit einem definierten und "sauberen" Status.
Idealerweise wird für jeden Test eine neue virtuelle Maschine gestartet.
Sollte das starten einer virtuellen Maschine nicht praktikabel sein,
sollte zumindest ein neuer WebDriver für jeden Test instanziert werden.
Firefox sollte den WebDriver mit einem definierten Profil gestartet werden.

```java
FirefoxProfile profile = new FirefoxProfile(new File("pathToFirefoxProfile"));
WebDriver driver = new FirefoxDriver(profile);
```
