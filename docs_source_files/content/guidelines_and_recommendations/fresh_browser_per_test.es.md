---
title: "Nueva instancia del navegador por prueba"
weight: 9
---

Comienza cada prueba desde un estado limpio conocido.
Idealmente, ejecuta una nueva máquina virtual para cada prueba.
Si ejecutar una nueva máquina virtual no es práctico,
al menos inicia un nuevo WebDriver para cada prueba.
Para Firefox, inicia un WebDriver con su perfil conocido.

```java
FirefoxProfile profile = new FirefoxProfile(new File("pathToFirefoxProfile"));
WebDriver driver = new FirefoxDriver(profile);
```
