---
title: "Installer le serveur Standalone"
weight: 3
---

Si vous prévoyez d'utiliser [Grid] ({{<ref "/grid/_index.md">}}), vous devez télécharger le
[selenium-server-standalone JAR] (https://selenium.dev/downloads/).
Tous les composants sont disponibles via
 [selenium-server](//repo1.maven.org/maven2/org/seleniumhq/selenium/selenium-server/).
Le JAR autonome contient tout, y compris le serveur Selenium distant
et les liaisons côté client.
Cela signifie que si vous utilisez le la jar selenium-server-standalone
dans votre projet, vous n'avez pas besoin d'ajouter de sélénium-java
ou un le jar spécifique au navigateur.

 ```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-server</artifactId>
  <version>3.X</version>
</dependency>
```
