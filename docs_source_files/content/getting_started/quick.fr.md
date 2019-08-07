---
title: "Tour rapide"
weight: 1
---

Selenium n'est pas juste un outil ou une API
mais est composé de nombreux outils.

## WebDriver

_[WebDriver]({{< ref "/webdriver/_index.md" >}})_ aussi connu sous le nom de Selenium 2.
Si vous débutez dans l'automatisation de test de site web _desktop_ vous allez utiliser les APIs WebDriver.
WebDriver utilise les APIs d'automatisation fournies par les distributeurs de navigateur 
pour les contrôler et exécuter les tests. 
C'est comme si un utilisateur réel utilisait le navigateur. 
Puisque WebDriver n'a pas besoin que ses APIs soient compilées avec le code de l'application testée,
il est non intrusif par nature. Ainsi vous testez exactement l'application qui sera en production.

## Remote Control

[_Remote Control_](https://www.seleniumhq.org/docs/05_selenium_rc.jsp) aussi connu sous le nom de Selenium 1.
Selenium RC était l'outil Selenium central avant l'avènement de Selenium WebDriver.
Selenium RC utilise un serveur proxy et injecte du Javascript dans le navigateur 
afin de pouvoir en prendre le contrôle.
Etant donné la nature intrusive de Selenium RC sur la navigateur, 
vous ne pouvez jamais être sûr de tester la même application 
que celle qui sera déployée en production. 
A noter que les APIs Selenium 2 contiennent les APIs Selenium RC,
mais les APIs Selenium 3, elles, se débarrasseront complètement de Selenium RC.
Si vous utilisez toujours Selenium RC, vous devez 
[_migrer_](https://www.seleniumhq.org/docs/03_webdriver.jsp#migrating-from-selenium-1-0)
vers Selenium WebDriver.


## IDE

_[IDE](https://www.seleniumhq.org/selenium-ide)_ est un plugin Firefox
qui peut être utilisé pour enregistrer les étapes d'un test depuis firefox lui-même.
Selenium IDE peut être utiliser pour générer des tests en mode _quick and dirty_
dans une variété de langage (à savoir C#, Java, Python et Ruby).
Etant donné la maintenabilité du code généré via Selenium IDE,
il n'est pas recommandé de l'utiliser pour autre chose que
de se familiariser avec des locators de web element
ou générer du code temporaire (_throw away code_).
Nous sommes sûr qu'une fois habitué à l'API WebDriver, 
vous n'utiliserez plus jamais Selenium IDE.


## Grid

Peu après le développement de tests basés sur WebDriver,
il se peut que vous rencontriez le besoin d'exécuter ceux-ci
sur des combinaisons différentes de navigateurs / système d'exploitation.
C'est ici que _[Grid]({{< ref "/grid/_index.md" >}})_ intervient pour vous aider.


## HTML Runner

Cet outil permet de permet d'exécuter des Test Suites depuis la ligne de commande.
Les Tests Suites sont des exports HTML depuis Selenium IDE ou autre outil compatible. _[HTML Runner]({{< ref "/getting_started/html-runner.fr.md" >}})_

