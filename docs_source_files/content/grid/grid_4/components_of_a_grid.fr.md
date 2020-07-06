---
title: "Composant"
weight: 1
---

![Grid](/images/grid4.png)

## Hub
* Intermédiaire et gestionnaire
* Accepte les demandes d'exécution de tests
* Prend les instructions du client et les exécute à distance sur les nœuds
* Gère les threads

Un _Hub_ est un point central où tous 
vos tests sont envoyés.
Chaque grille de sélénium se compose exactement 
d'un concentrateur. Le hub doit être accessible
des clients respectifs (c.-à-d. serveur CI, machine développeur, etc.)
Le concentrateur connectera un ou plusieurs nœuds
auquel les tests seront délégués.

## Nodes

* Où vivent les navigateurs
* S'enregistre auprès du hub et communique ses capacités
* Reçoit les demandes du hub et les exécute

_Nodes_ sont différentes instances de Selenium
qui exécutera des tests sur des 
systèmes informatiques individuels.
Il peut y avoir plusieurs nœuds dans une grille.
Les machines qui sont des nœuds n'ont 
pas besoin d'être la même plate-forme
ou avoir la même sélection de navigateur 
que celle du concentrateur ou des autres nœuds.
Un nœud sous Windows peut avoir la capacité de
offrant Internet Explorer comme option de navigateur,
alors que cela ne serait pas possible sur Linux ou Mac.

