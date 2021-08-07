---
title: "Indépendence des tests"
linkTitle: "Indépendence des tests"
weight: 9
---

Écrivez chaque test comme sa propre unité. Écrivez les tests d'une manière qui ne sera pas
dépendant d'autres tests pour effectuer:

Disons qu'il existe un système de gestion de contenu avec lequel vous pouvez créer
du contenu personnalisé qui apparaît ensuite sur votre site Web sous forme de module après
la publication et la synchronisation entre le CMS et le serveur peut prendre un certain temps.
application.

Une mauvaise façon de tester votre module est que le contenu est créé et
publié dans un test, puis vérifier le module dans un autre test. Cette
n'est pas réalisable car le contenu peut ne pas être disponible immédiatement pour le
autre test après publication.

Au lieu de cela, vous pouvez créer un contenu de stub qui peut être activé et désactivé
dans le test affecté, et utilisez-le pour valider le module. cependant,
pour la création de contenu, vous pouvez toujours avoir un test séparé.
