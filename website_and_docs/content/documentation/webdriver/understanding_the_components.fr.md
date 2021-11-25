---
title: "Comprendre les composants"
linkTitle: "Comprendre les composants"
weight: 1
aliases: ["/documentation/fr/webdriver/understanding_the_components/"]
---

Pour créer une suite de tests à l'aide de WebDriver, vous devrez comprendre et
utiliser efficacement un certain nombre de composants différents.
logiciel, différentes personnes utilisent des termes différents pour la même idée.
une ventilation de la façon dont les termes sont utilisés dans cette description.

### Terminologie

* **API:** Interface de programmation d'application. Il s'agit de l'ensemble des "commandes"
vous utilisez pour manipuler WebDriver.
* **Bibliothèque:** Un module de code qui contient les API et le code nécessaires
Les bibliothèques sont spécifiques à chaque liaison de langue, par exemple .jar
fichiers pour Java, fichiers .dll pour .NET, etc.
* **Pilote:** Responsable du contrôle du navigateur actuel.
sont créés par les fournisseurs de navigateurs eux-mêmes.
modules exécutables qui s'exécutent sur le système avec le navigateur lui-même,
pas sur le système exécutant la suite de tests (bien que ceux-ci puissent être
même système.) _NOTE: Certaines personnes se réfèrent aux pilotes en tant que proxies._
* **Framework:** Une bibliothèque supplémentaire utilisée comme support pour WebDriver
Ces cadres peuvent être des cadres de test tels que JUnit ou NUnit.
Il peut également s'agir de cadres prenant en charge les fonctionnalités du langage naturel telles que
comme Cucumber ou Robotium.Frameworks peut également être écrit et utilisé pour
des choses telles que la manipulation ou la configuration du système sous test, les données
création, test d'oracles, etc.

### Les pièces et pièces

Au minimum, WebDriver communique avec un navigateur via un pilote.
est à double sens: WebDriver passe des commandes au navigateur via le pilote, et
reçoit des informations via le même itinéraire.

{{< figure src="/images/documentation/webdriver/basic_comms.png" class="img-responsive text-center" alt="Communication de base">}}

Le pilote est spécifique au navigateur, tel que ChromeDriver pour Google
Chrome / Chrome, GeckoDriver pour Firefox de Mozilla, etc. Le pilote fonctionne sur
le même système que le navigateur. Il peut s'agir ou non du même système sur lequel
les tests eux-mêmes sont en cours d'exécution.

Cet exemple simple ci-dessus est la communication _directe_.
le navigateur peut également être une communication à _distance_ via Selenium Server ou
RemoteWebDriver. RemoteWebDriver fonctionne sur le même système que le pilote
et le navigateur.

{{< figure src="/images/documentation/webdriver/remote_comms.png" class="img-responsive text-center" alt="Communication à distance">}}

La communication à distance peut également avoir lieu à l'aide de Selenium Server ou Selenium
Grille, qui à son tour parlent au conducteur sur le système hôte

{{< figure src="/images/documentation/webdriver/remote_comms_server.png" class="img-responsive text-center" alt="Communication à distance avec Grid">}}

## Où les cadres s'intègrent

WebDriver a un seul travail et un seul travail: communiquez avec le navigateur via
des méthodes ci-dessus.WebDriver ne sait rien du test: il ne sait pas
savoir comparer les choses, affirmer réussir ou échouer, et il ne sait certainement pas
une chose à propos de la grammaire Reporting ou Given / When / Then.

C'est là que divers frameworks entrent en jeu. Au minimum, vous aurez besoin d'un
cadre de test qui correspond aux liaisons de langage, par exemple NUnit pour .NET, JUnit
pour Java, RSpec pour Ruby, etc.

Le framework de test est responsable de l'exécution et de l'exécution de votre WebDriver
et les étapes connexes dans vos tests. En tant que tel, vous pouvez penser qu'il ressemble à
à l'image suivante.

{{< figure src="/images/documentation/webdriver/test_framework.png" class="img-responsive text-center" alt="Cadre de test">}}

Des cadres / outils en langage naturel tels que le concombre peuvent exister dans le cadre de cette
Test Framework cadre dans la figure ci-dessus, ou ils peuvent envelopper le Test Framework
entièrement dans leur propre mise en œuvre.
