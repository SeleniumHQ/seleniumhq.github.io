---
title: "Tour rapide"
weight: 1
---

Selenium n'est pas juste un outil ou une API
mais est composé de nombreux outils.

## WebDriver

Si vous débutez dans l'automatisation de test de site web _desktop_ 
vous allez utiliser les APIs WebDriver. _[WebDriver]({{< ref "/webdriver/_index.md" >}})_ utilise 
les APIs d'automatisation fournies par les distributeurs de navigateur 
pour les contrôler et exécuter les tests. 
C'est comme si un utilisateur réel utilisait le navigateur. 
Puisque WebDriver n'a pas besoin que ses APIs soient compilées avec le code de l'application testée,
il est non intrusif par nature. Ainsi vous testez exactement l'application qui sera en production.

## IDE

_[IDE](https://selenium.dev/selenium-ide)_ (Integrated Development Environment) 
est l'outil que vous utilisez pour développer vos cas de test Selenium. Il s'agit d'un Chrome facile à utiliser
et l'extension Firefox et est généralement le moyen le plus efficace de développer
cas de test. Il enregistre pour vous les actions des utilisateurs dans le navigateur, en utilisant
commandes Selenium existantes, avec des paramètres définis par le contexte de
cet élément. Ce n'est pas seulement un gain de temps, mais aussi un excellent moyen
d'apprentissage de la syntaxe du script Selenium.


## Grid

Selenium Grid vous permet de lancer des tests dans différentes 
machines et depuis différentes plateformes. Le lancement des tests
se fait en local, et une fois les tests lancés, ils sont automatiquement
exécutés sur le serveur distant.

Après le développement des tests WebDriver, vous pouvez être confronté
à la nécessité de lancer vos tests sur toutes combinaisons de navigateurs et systèmes d'exploitation.
C'est là que _[Grid]({{< ref "/grid/_index.md" >}})_ entre en scène.
