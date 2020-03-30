---
title: "Gmail, email and logins Facebook"
menuTitle: "Gmail, email and Facebook"
weight: 4
---

Pour plusieurs raisons, vous connecter à des 
sites comme Gmail et Facebook
l'utilisation de WebDriver n'est pas recommandée.
En plus d'être contre les conditions d'utilisation de ces sites
(où vous risquez de fermer le compte),
c'est lent et peu fiable.

La pratique idéale consiste à utiliser les API 
proposées par les fournisseurs de messagerie,
ou dans le cas de Facebook le service des outils de développement
qui expose une API pour créer des comptes de test, des amis, etc.
Bien que l'utilisation d'une API puisse 
sembler un peu plus difficile,
vous serez récompensé en vitesse, fiabilité et stabilité.
L'API est également peu susceptible de changer
alors que les pages Web et les localisateurs 
HTML changent souvent et vous obliger à mettre 
à jour votre framework de test.

Se connecter à des sites tiers à l'aide de WebDriver
à tout moment de votre test augmente le risque
de l'échec de votre test car cela rallonge votre test.
Une règle générale est que des tests plus longs
sont plus fragiles et peu fiables.

Les implémentations WebDriver qui sont
[W3C conformant](//w3c.github.io/webdriver/webdriver-spec.html)
annoter également l'objet `navigator`
avec une propriété `WebDriver`
afin que les attaques par déni de service 
puissent être atténuées.