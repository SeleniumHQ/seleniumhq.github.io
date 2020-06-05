---
title: "Test de performance"
weight: 6
---

Test de performances avec Selenium et WebDriver
n'est généralement pas conseillé.
Pas parce qu'il est incapable
mais parce qu'il n'est pas optimisé pour le travail
et il est peu probable que vous obteniez de bons résultats.

Cela peut sembler idéal pour tester les performances
dans le contexte de l'utilisateur mais une suite 
de tests WebDriver sont soumis à de nombreux 
points de fragilité externe et interne
qui échappent à votre contrôle;
par exemple la vitesse de démarrage du navigateur,
vitesse des serveurs HTTP,
réponse des serveurs tiers qui hébergent JavaScript ou CSS,
et la pénalité d'instrumentation
de l'implémentation WebDriver elle-même.
Une variation à ces points entraînera une 
variation de vos résultats.
Il est difficile de séparer la différence
entre les performances de votre site web
et la performance des ressources externes,
et il est également difficile de dire quelle 
est la pénalité de performance
pour utiliser WebDriver dans le navigateur,
surtout si vous injectez des scripts.

L'autre attraction potentielle est "gagner du temps" -
effectuer des tests fonctionnels et de performance en 
même temps. Cependant, les tests fonctionnels et 
de performance ont des objectifs opposés.
Pour tester la fonctionnalité, un testeur 
peut devoir être patient et attendre le chargement,
mais cela obscurcira les résultats des 
tests de performances et vice versa.

Pour améliorer les performances de votre site Web,
vous devrez être en mesure d'analyser les performances globales
indépendamment des différences d'environnement,
identifier les mauvaises pratiques de code,
ventilation des performances des ressources individuelles
(i.e. CSS ou JavaScript)
afin de savoir quoi améliorer.
Il existe des outils de test de performance disponibles
qui peut déjà faire ce travail,
et qui fournissent des rapports et des analyses
qui peut même faire des suggestions d'amélioration.

Exemples de packages (open source) à utiliser: [JMeter](//jmeter.apache.org/)
