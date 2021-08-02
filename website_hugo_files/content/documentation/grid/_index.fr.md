---
title: "Grid"
linkTitle: "Grid"
weight: 9
description: >
  Want to run tests in parallel across multiple machines? Then, Grid is for you.
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to French. Do you speak French? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

Selenium Grid permet l'exécution de scripts WebDriver sur 
des machines distantes (ou réel) en acheminant les commandes 
envoyées par le client vers des instances de navigateur distantes.
Il vise à fournir un moyen simple d'exécuter des tests 
en parallèle sur plusieurs machines.

Selenium Grid nous permet d'exécuter des tests en parallèle 
sur plusieurs machines, et de gérer les différentes versions 
et configurations de navigateurs de manière centralisée
(au lieu de dans chaque test individuel).

Selenium Grid n'est pas une solution miracle.
Il résout un sous-ensemble de problèmes courants 
de délégation et de distribution,
mais ne gérera par exemple pas votre infrastructure,
et peut ne pas répondre à vos besoins spécifiques.

## Objectifs et fonctionnalitées

* Point d'entrée central pour tous les tests
* Gestion et contrôle des nœuds / environnement où s'exécutent les navigateurs
* Mise à l'échelle
* Exécution de tests en parallèle
* Test multiplateforme
* L'équilibrage de charge