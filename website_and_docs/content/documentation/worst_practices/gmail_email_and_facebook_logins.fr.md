---
title: "Connexion à Gmail, Facebook et les emails"
linkTitle: "Gmail, email and Facebook"
weight: 4
aliases: ["/documentation/fr/worst_practices/gmail_email_and_facebook_logins/"]
---

Pour plusieurs raisons, la connexion à des sites comme Gmail et
Facebook avec l'utilisation de WebDriver n'est pas recommandée.
Outre le fait que cette méthode est contraire aux conditions
d'utilisation de ces sites (et que vous risquez de bloquer
ou fermer votre compte), elle est lente et peu fiable.

La pratique idéale consiste à utiliser les API 
proposées par les fournisseurs de messagerie
ou, dans le cas de Facebook, le service des outils de
développement qui propose une API pour créer des comptes
de test, des amis, etc.
Bien que l'utilisation d'une API puisse sembler un peu plus
difficile, vous serez récompensé en vitesse, fiabilité et
stabilité.
L'API est également peu susceptible de changer
alors que les pages Web et les localisateurs 
HTML changent souvent et vous oblige à mettre 
à jour votre framework de test.

Se connecter à des sites tiers à l'aide de WebDriver
à tout moment de votre test augmente le risque
de l'échec de votre test, car cela le rallonge.
Une règle générale est que les tests sont longs, plus
ils sont plus fragiles et peu fiables.

Les implémentations WebDriver qui sont
[conforment aux W3C](//w3c.github.io/webdriver/webdriver-spec.html)
annotent également l'objet `navigator`
avec une propriété `WebDriver`
afin que les attaques par déni de service 
puissent être atténuées.
