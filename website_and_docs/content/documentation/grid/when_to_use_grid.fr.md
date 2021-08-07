---
title: "Quand utiliser Grid"
linkTitle: "Quand utiliser Grid"
weight: 2
description: >
  Is Grid the right tool for you?
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to French. Do you speak French? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

De manière générale, il existe deux raisons pour 
lesquelles vous souhaiterez peut-être utiliser Grid.

* Pour exécuter vos tests sur plusieurs navigateurs, plusieurs versions de navigateur,
et les navigateurs fonctionnant sur différents systèmes d'exploitation.
* Pour réduire le temps nécessaire à la suite de tests pour terminer une passe de test.

La grille est utilisée pour accélérer l'exécution d'un test 
réussi en utilisant plusieurs machines pour exécuter des 
tests en parallèle. Par exemple, si vous avez une suite de
100 tests, mais vous avez configuré Grid pour prendre en 
charge 4 machines différentes (VM ou machines physiques distinctes) 
pour exécuter ces tests, votre suite de tests se terminera en (environ) 
un quart du temps comme si vous exécutiez vos tests de manière séquentielle 
sur une seule machine. Pour les grandes suites de tests 
et les suites de tests de longue durée telles que ceux qui 
effectuent de grandes quantités de validation des données, cela 
peut être un gagne-temps. Certaines suites de tests peuvent prendre 
des heures à s'exécuter. Une autre raison de booster la
le temps passé à exécuter la suite est de raccourcir le délai 
d'exécution des résultats des tests après le code d'enregistrement 
des développeurs pour l'AUT. De plus en plus d'équipes logicielles
pratiquant le développement logiciel Agile veulent des 
commentaires sur les tests aussi possible par opposition 
à attendre la nuit pour un passage de test de nuit.

La grille est également utilisée pour prendre en charge 
l'exécution de tests sur plusieurs environnements d'exécution
environnements, en particulier, contre différents 
navigateurs en même temps. Pour Par exemple, une "grille" de 
machines virtuelles peut être configurée, chacune prenant en charge un
navigateur différent que l'application à tester doit prendre en charge. Donc, la machine 1
a Internet Explorer 8, machine 2, Internet Explorer 9, machine 3 la dernière
Chrome et la machine 4 la dernière version de Firefox. 
Lorsque la suite de tests est exécutée, Selenium-Grid reçoit 
chaque combinaison de navigateur de test et attribue chaque test à
exécuter sur son navigateur requis.

De plus, on peut avoir une grille de tous les mêmes 
navigateur, type et version. Pour par exemple, on 
pourrait avoir une grille de 4 machines exécutant chacune 3 
instances de Firefox 70, permettant une "batterie de serveurs" 
(dans un sens) de Firefox disponible instances. 
Lorsque la suite s'exécute, chaque test est passé à Grid qui attribue 
le test à la prochaine instance de Firefox disponible. 
De cette manière, on obtient la réussite du test où, 
en théorie, 12 tests sont tous en cours d'exécution en 
même temps dans parallèle, ce qui réduit 
considérablement le temps nécessaire pour réussir un test.

La grille est très flexible. Ces deux exemples peuvent 
être combinés pour permettre plusieurs instances de chaque 
type et version de navigateur. Une configuration telle que cela 
fournirait à la fois une exécution parallèle pour un achèvement 
rapide des tests et prise en charge simultanée de 
plusieurs types et versions de navigateur.
