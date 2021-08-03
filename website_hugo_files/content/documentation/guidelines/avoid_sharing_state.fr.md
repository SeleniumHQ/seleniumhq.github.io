---
title: "Eviter de partager l'état"
linkTitle: "Eviter de partager l'état"
weight: 8
---

Bien que mentionné à plusieurs endroits, il convient de le mentionner à nouveau. Assurer
les tests sont isolés les uns des autres.

* Ne partagez pas les données de test. Imaginez plusieurs tests qui interrogent chacun la base de données
pour les commandes valides avant d'en choisir une pour effectuer une action. Devrait deux tests
prenez la même commande que vous risquez d'obtenir un comportement inattendu.

* Nettoyez les données périmées dans l'application qui pourraient être récupérées par un autre
test par ex. enregistrements de commande invalides.

* Créez une nouvelle instance WebDriver par test. Cela permet d'assurer l'isolement des tests
et rend la parallélisation plus simple.
