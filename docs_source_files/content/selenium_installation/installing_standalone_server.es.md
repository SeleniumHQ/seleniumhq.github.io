---
title: "Instalación del servidor Standalone"
weight: 3
---

Si planeas usar [Grid]({{< ref "/grid/_index.md" >}}) debes descargar el fichero 
[selenium-server-standalone JAR](//www.seleniumhq.org/download/).
El jar _selenium-server-standalone_ nunca se carga, pero todos los componentes están disponibles a través de 
[selenium-server](//repo1.maven.org/maven2/org/seleniumhq/selenium/selenium-server/).
El _standalone_ JAR contiene todo, incluso el servidor remoto de Selenium 
y los enlaces del lado del cliente.
Ésto quiere decir que si usas el selenium-server-standalone jar
en tu proyecto, no tienes que añadir selenium-java
o un jar de navegador específico.

 ```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-server</artifactId>
  <version>3.X</version>
</dependency>
```

