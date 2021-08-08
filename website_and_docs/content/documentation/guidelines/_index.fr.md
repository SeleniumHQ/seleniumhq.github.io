---
title: "Lignes directrices et recommendations"
linkTitle: "Lignes directrices"
weight: 7
description: >
  Some guidelines and recommendations on testing from the Selenium project.
aliases: ["/documentation/fr/guidelines_and_recommendations/"]  
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to French. Do you speak French? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

Une note sur les "meilleures pratiques": nous avons intentionnellement évité l'expression "meilleures
Pratiques "dans cette documentation. Aucune approche ne fonctionne pour toutes les situations.
Nous préférons l'idée de "Lignes directrices et recommandations". Nous encourageons
vous devez les lire et décider de manière réfléchie quelles approches
travaillera pour vous dans votre environnement particulier.

Les tests fonctionnels sont difficiles à obtenir correctement pour de nombreuses raisons.
Comme si l'état, la complexité et les dépendances des applications ne rendent pas les tests assez difficiles,
gérer les navigateurs (en particulier les incompatibilités entre navigateurs)
fait de la rédaction de bons tests un défi.

Selenium fournit des outils pour faciliter l'interaction fonctionnelle des utilisateurs,
mais ne vous aide pas à écrire des suites de tests bien conçues.
Dans ce chapitre, nous proposons des conseils, des directives et des recommandations
sur la façon d'aborder l'automatisation fonctionnelle des pages Web.

Ce chapitre enregistre les modèles de conception de logiciels populaires
parmi de nombreux utilisateurs de sélénium
qui ont fait leurs preuves au fil des ans.
