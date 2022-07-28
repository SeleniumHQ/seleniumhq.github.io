---
title: "Navegador novo por teste"
linkTitle: "Navegador novo por teste"
weight: 11
aliases: [
"/documentation/pt-br/guidelines_and_recommendations/fresh_browser_per_test/",
"/pt-br/documentation/guidelines/fresh_browser_per_test/"
]
---

Comece cada teste a partir de um estado limpo conhecido.
Idealmente, ligue uma nova máquina virtual para cada teste.
Se ligar uma nova máquina virtual não for prático,
pelo menos inicie um novo WebDriver para cada teste.
Most browser drivers like GeckoDriver and ChromeDriver will start with a clean
known state with a new user profile, by default.
```java
WebDriver driver = new FirefoxDriver();
```
