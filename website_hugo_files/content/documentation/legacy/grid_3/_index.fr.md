---
title: "Grid 3"
linkTitle: "Grid 3"
weight: 6
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Most of the documentation found in this section is still in English.
   Please note we are not accepting pull requests to translate this content
   as translating documentation of legacy components does not add value to
   the community nor the project.
</p>
{{% /pageinfo %}}

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
