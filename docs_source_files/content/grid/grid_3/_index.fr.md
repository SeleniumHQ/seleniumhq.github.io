---
title: "Grid 3"
chapter: true
weight: 3
---

{{% notice info %}}
<i class="fas fa-language"></i> Page being translated from 
English to French. Do you speak French? Help us to translate
it by sending us pull requests!
{{% /notice %}}

# Grid 3

_Selenium Grid_ est un serveur proxy intelligent
qui permet aux tests Selenium d'acheminer des 
commandes vers des instances de navigateur Web distantes.
Son objectif est de fournir un moyen simple 
d'exécuter des tests en parallèle sur plusieurs machines.

Avec Selenium Grid,
un serveur fait office de concentrateur 
qui achemine les commandes de test au format JSON
à un ou plusieurs nœuds de grille enregistrés.
Les tests contactent le concentrateur pour 
obtenir l'accès aux instances de navigateur distantes.
Le concentrateur dispose d'une liste de 
serveurs enregistrés auxquels il donne accès,
et permet le contrôle de ces instances.

Selenium Grid nous permet d'effectuer des 
tests en parallèle sur plusieurs machines,
et pour gérer les différentes versions et 
configurations de navigateur de manière centralisée
(au lieu de dans chaque test individuel).

La grille de sélénium n'est pas une 
solution miracle. Il résout un sous-ensemble 
de problèmes de délégation et de distribution communs,
mais ne gèrera par exemple pas votre infrastructure,
et pourrait ne pas répondre à vos besoins spécifiques.

**Please note Grid 3 is not supported anymore and the Selenium project
recommends to use [Grid 4]({{<ref "/grid/grid_4/_index.fr.md">}})**
