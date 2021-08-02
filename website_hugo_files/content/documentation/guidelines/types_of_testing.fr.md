---
title: "Types de test"
linkTitle: "Types de test"
weight: 2
---

### Test d'acceptation

Ce type de test est effectué pour déterminer si 
une fonctionnalité ou un système
répond aux attentes et exigences du client.
Ce type de test implique généralement le client
la coopération ou la rétroaction, étant une activité de validation qui
répond à la question:
> Construisons-nous le produit **_non?_**.

Pour les applications web, l'automatisation de ces tests peut se faire
directement avec Selenium en simulant le comportement attendu de l'utilisateur.
Cette simulation peut être effectuée par enregistrement / lecture ou par
différentes langues prises en charge comme expliqué dans cette documentation.
Remarque: Les tests d'acceptation sont un sous-type de **_tests fonctionnels_**,
auquel certaines personnes pourraient également se référer.
            
### Test fonctionel

Ce type de test est effectué pour déterminer si un
fonction ou le système fonctionne correctement sans problèmes. Il vérifie
le système à différents niveaux pour garantir que tous les scénarios
sont couverts et que le système fait
censé faire. C'est une activité de vérification qui
répond à la question:
> Construisons-nous le produit **_non?_**.
             
Cela comprend généralement: les tests fonctionnent sans erreur
(404, exceptions ...), de manière utilisable (redirections correctes),
de manière accessible et correspondant à ses spécifications
(voir **_test d'acceptation_** ci-dessus).

Pour les applications Web, l'automatisation de ces tests peut être
fait directement avec Selenium en simulant les rendements attendus.
Cette simulation peut être effectuée par enregistrement / lecture ou par
les différentes langues prises en charge comme expliqué dans cette documentation.

### Test de performance

Comme son nom l'indique, des tests de performances sont effectués
pour mesurer la performance d'une application.

Il existe deux sous-types principaux pour les tests de performances:

#### Load testing
Des tests de charge sont effectués pour vérifier la
l'application fonctionne sous différentes charges définies
(généralement un nombre particulier d'utilisateurs connectés en même temps)

#### Tests de résistance
Des tests de résistance sont effectués pour vérifier que
l'application fonctionne sous contrainte (ou au-dessus de la charge maximale supportée).

En règle générale, les tests de performances sont effectués en exécutant certains
Test écrit de sélénium simulant différents utilisateurs
frapper une fonction particulière sur l'application Web et
récupérer des mesures significatives. 

Cela se fait généralement par d'autres outils qui récupèrent les métriques.
Un tel outil est **_JMeter_**.

Pour une application Web, les détails à mesurer incluent
débit, latence, perte de données, temps de chargement des composants individuels ...

Remarque 1: tous les navigateurs ont un onglet de performances dans leur
section des outils des développeurs (accessible en appuyant sur F12)

Remarque 2: est un sous-type de **_tests non fonctionnels_**
car cela est généralement mesuré par système et non par fonction / caractéristique.
            
### Les tests de régression
Ce test est généralement effectué après un changement, un correctif ou un ajout de fonctionnalité. 

Pour s'assurer que le changement n'a rompu aucun des
fonctionnalité, certains tests déjà exécutés sont exécutés à nouveau. 
            
L'ensemble des tests réexécutés peut être complet ou partiel
et peut inclure plusieurs types différents, selon
sur l'équipe d'application et de développement.
            
### Développement piloté par les tests (TDD)
Plutôt qu'un type de test en soi, le TDD est un itératif
méthodologie de développement dans laquelle les tests pilotent la conception d'une fonctionnalité.

Chaque cycle commence par la création d'un ensemble de tests unitaires qui
la fonctionnalité doit réussir (ce qui devrait échouer lors de sa première exécution).

Après cela, le développement a lieu pour faire passer les tests.
Les tests sont exécutés à nouveau en commençant un autre cycle
et ce processus se poursuit jusqu'à ce que tous les tests soient réussis.

Cela vise à accélérer le développement d'une application
basé sur le fait que les défauts sont moins coûteux plus tôt ils sont trouvés.

### Développement axé sur le comportement (BDD)

BDD est également une méthodologie de développement itérative
basé sur ci-dessus (TDD) dans lequel le but est d'impliquer
toutes les parties dans le développement d'une application.

Chaque cycle commence par la création de quelques spécifications
(qui devrait échouer). Créez ensuite l'unité défaillante
tests (qui devraient également échouer), puis créer le développement. 

Ce cycle est répété jusqu'à ce que tous les types de tests réussissent.

Pour ce faire, un langage de spécification est
utilisé. Il doit être compréhensible par toutes les parties et
simple, standard et explicite.
La plupart des outils utilisent **_Gherkin_** comme langue.

L'objectif est de pouvoir détecter encore plus d'erreurs
que TDD en ciblant les erreurs d'acceptation potentielles
aussi et rendre la communication entre les parties plus fluide.

Un ensemble d'outils est actuellement disponible
écrire les spécifications et les associer aux fonctions de code,
tels que **_Concombre_** ou **_SpecFlow._**

Un ensemble d'outils est construit sur Selenium pour rendre ce processus
encore plus rapide en transformant directement les spécifications BDD en
code exécutable.
Certains d'entre eux sont **_JBehave, Capybara et Robot Framework_**.
            
