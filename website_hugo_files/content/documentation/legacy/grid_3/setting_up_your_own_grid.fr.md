---
title: "Mettre en place votre propre Grid"
linkTitle: "Mettre en place votre propre Grid"
weight: 2
---

Pour utiliser la grille de Selenium,
vous devez maintenir votre propre 
infrastructure pour les nœuds.
Comme cela peut être un effort lourd et 
intense en temps, de nombreuses organisations 
utilisent des fournisseurs IaaS
comme Amazon EC2 et Google Compute
pour fournir cette infrastructure.

D'autres options incluent l'utilisation de 
fournisseurs tels que Sauce Labs ou Testing Bot
qui fournissent un Selenium Grid en 
tant que service dans le cloud.
Il est certainement possible d'exécuter 
également des nœuds sur votre propre matériel.
Ce chapitre abordera en détail l'option de 
faire fonctionner votre propre grille,
complet avec sa propre infrastructure de nœuds.

## Quick start

Cet exemple vous montrera comment démarrer 
le Selenium 2 Grid Hub, et enregistrer à la 
fois un nœud WebDriver et un nœud hérité Selenium 1 RC.
Nous vous montrerons également comment appeler la grille depuis Java.
Le hub et les nœuds sont montrés ici fonctionnant sur la même machine,
mais bien sûr, vous pouvez copier le serveur 
autonome de Selenium sur plusieurs machines.

Le package `selenium-server-standalone` comprend le concentrateur,
WebDriver et l'héritage RC nécessaires pour exécuter la grille,
_ant_ n'est plus requis.
Vous pouvez télécharger le `selenium-server-standalone.jar` depuis
[https://selenium.dev/downloads/](https://selenium.dev/downloads/).


### Étape 1: démarrez le hub

Le Hub est le point central qui 
recevra les demandes de test
et les distribuer aux bons nœuds.
La distribution se fait sur la base 
des capacités, ce qui signifie un test 
nécessitant un ensemble de capacités ne sera 
distribué qu'aux nœuds offrant cet ensemble 
ou sous-ensemble de capacités.

Parce que les capacités souhaitées d'un test 
sont exactement ce que son nom l'indique, _desired_,
le concentrateur ne peut pas garantir qu'il localisera un nœud
correspondant parfaitement à l'ensemble de capacités souhaité.

Ouvrez une invite de commande
et accédez au répertoire dans lequel vous avez copié
le fichier `selenium-server-standalone.jar`.
Vous démarrez le hub en passant le drapeau `-role hub`
sur le serveur autonome:

```shell
java -jar selenium-server-standalone.jar -role hub
```

The Hub will listen to port 4444 by default.
You can view the status of the hub by opening a browser window and navigating to
[http://localhost:4444/grid/console](http://localhost:4444/grid/console).

Le concentrateur écoutera le port 4444 par défaut.
Vous pouvez afficher l'état du concentrateur en 
ouvrant une fenêtre de navigateur et en accédant à
[http://localhost:4444/grid/console](http://localhost:4444/grid/console).

Pour modifier le port par défaut,
vous pouvez ajouter le drapeau facultatif `-port`
avec un entier représentant le port à 
écouter lorsque vous exécutez la commande.
En outre, toutes les autres options que 
vous voyez dans le fichier de configuration JSON (voir ci-dessous)
sont des drapeaux de ligne de commande possibles.

Vous pouvez certainement vous en tirer 
avec la simple commande ci-dessus,
mais si vous avez besoin d'une configuration plus avancée,
vous pouvez également spécifier un 
fichier de configuration au format JSON, 
pour plus de commodité, pour configurer 
le concentrateur au démarrage.
Vous pouvez le faire comme ceci:

```shell
java -jar selenium-server-standalone.jar -role hub -hubConfig hubConfig.json -debug
```

Ci-dessous, vous verrez un exemple de 
fichier `hubConfig.json`. Nous verrons plus en 
détail comment fournir les fichiers de 
configuration de noeud à l'étape 2.

```json
{
  "_comment" : "Configuration for Hub - hubConfig.json",
  "host": ip,
  "maxSession": 5,
  "port": 4444,
  "cleanupCycle": 5000,
  "timeout": 300000,
  "newSessionWaitTimeout": -1,
  "servlets": [],
  "prioritizer": null,
  "capabilityMatcher": "org.openqa.grid.internal.utils.DefaultCapabilityMatcher",
  "throwOnCapabilityNotPresent": true,
  "nodePolling": 180000,
  "platform": "WINDOWS"}
```


### Étape 2: démarrez les nœuds

Que vous souhaitiez exécuter une grille avec 
de nouvelles fonctionnalités WebDriver,
ou une grille avec la fonctionnalité Selenium 1 RC,
Ou les deux à la fois,
vous utilisez le même fichier 
`selenium-server-standalone.jar` pour démarrer les nœuds:

```shell
java -jar selenium-server-standalone.jar -role node -hub http://localhost:4444
```

Si un port n'est pas spécifié via l'indicateur `-port`,
un port libre sera choisi. Vous pouvez 
exécuter plusieurs nœuds sur une seule machine
mais si vous le faites, vous devez être 
conscient des ressources mémoire de votre système
et des problèmes avec les captures 
d'écran si vos tests les prennent.

#### Configuration du nœud avec options

Comme mentionné, pour une compatibilité ascendante
Les rôles "wd" et "rc" sont toujours un 
sous-ensemble valide du rôle "node".
Mais ces rôles limitent les types de 
connexions distantes à leur API correspondante,
tandis que "node" permet à la fois 
les connexions à distance RC et WebDriver.

Passer les propriétés JVM (en utilisant l'indicateur `-D`
_avant l'argument -jar_)
sur la ligne de commande,
et ceux-ci seront ramassés et propagés aux nœuds:

`-Dwebdriver.chrome.driver=chromedriver.exe`

#### Configuration de Node avec JSON

Vous pouvez également démarrer des 
nœuds de grille configurés
avec un fichier de configuration JSON

```shell
java -Dwebdriver.chrome.driver=chromedriver.exe -jar selenium-server-standalone.jar -role node -nodeConfig node1Config.json
```

Et voici un exemple de fichier `nodeConfig.json`:

```json
{
  "capabilities": [
    {
      "browserName": "firefox",
      "acceptSslCerts": true,
      "javascriptEnabled": true,
      "takesScreenshot": false,
      "firefox_profile": "",
      "browser-version": "27",
      "platform": "WINDOWS",
      "maxInstances": 5,
      "firefox_binary": "",
      "cleanSession": true
    },
    {
      "browserName": "chrome",
      "maxInstances": 5,
      "platform": "WINDOWS",
      "webdriver.chrome.driver": "C:/Program Files (x86)/Google/Chrome/Application/chrome.exe"
    },
    {
      "browserName": "internet explorer",
      "maxInstances": 1,
      "platform": "WINDOWS",
      "webdriver.ie.driver": "C:/Program Files (x86)/Internet Explorer/iexplore.exe"
    }
  ],
  "configuration": {
    "_comment" : "Configuration for Node",
    "cleanUpCycle": 2000,
    "timeout": 30000,
    "proxy": "org.openqa.grid.selenium.proxy.WebDriverRemoteProxy",
    "port": 5555,
    "host": ip,
    "register": true,
    "hubPort": 4444,
    "maxSession": 5
  }
}
```

Une note sur le drapeau `-host`

Pour le concentrateur et le nœud, si 
l'indicateur `-host` n'est pas spécifié,
`0.0.0.0` sera utilisé par défaut. Cela se 
liera à tous les interfaces IPv4 publiques 
(sans boucle) de la machine. Si vous avez un spécial
configuration réseau ou tout composant 
créant des interfaces réseau supplémentaires,
il est conseillé de définir le drapeau 
`-host` avec une valeur qui
concentrateur/node accessible 
depuis une autre machine.

#### Spécification du port

Le port TCP/IP par défaut utilisé par le 
concentrateur est 4444. Si vous devez changer le port
veuillez utiliser les configurations mentionnées ci-dessus.

## Dépannage

### Utilisation du fichier journal

Pour un dépannage avancé, vous pouvez spécifier un 
fichier journal pour enregistrer les messages système.
Démarrez le concentrateur ou le nœud Selenium 
GRID avec l'argument -log. Veuillez voir l'exemple ci-dessous:

```shell
java -jar selenium-server-standalone.jar -role hub -log log.txt
```

Utilisez votre éditeur de texte préféré pour 
ouvrir le fichier journal (log.txt dans l'exemple ci-dessus) pour trouver
Journaux "ERREUR" si vous rencontrez des problèmes.

### Utilisation de l'argument `-debug`

Vous pouvez également utiliser l'argument 
`-debug` pour imprimer les journaux de débogage sur la console.
Démarrez Selenium Grid Hub ou Node avec 
l'argument `-debug`. S'il te plait regarde
l'exemple ci-dessous:

```shell
java -jar selenium-server-standalone.jar -role hub -debug
```

## Attention

La grille de Selenium doit être protégée des accès externes à l'aide de
autorisations de pare-feu.

Le fait de ne pas protéger votre grille peut 
entraîner un ou plusieurs des événements suivants:

* Vous fournissez un accès ouvert à votre infrastructure Grid
* Vous autorisez des tiers à accéder aux applications et fichiers Web internes
* Vous autorisez des tiers à exécuter des binaires personnalisés

Voir cet article de blog sur [Detectify](//labs.detectify.com), qui donne une bonne
aperçu de l'utilisation abusive d'une grille exposée publiquement:
[Ne laissez pas votre grille grande ouverte](//labs.detectify.com/2017/10/06/guest-blog-dont-leave-your-grid-wide-open/).

## Docker Selenium

[Docker](//www.docker.com/) fournit un moyen pratique de
fournir et dimensionner l'infrastructure Selenium Grid 
dans une unité appelée conteneur.
Les conteneurs sont des unités logicielles standardisées 
qui contiennent tout le nécessaire
pour exécuter l'application souhaitée, y compris toutes 
les dépendances, dans un environnement fiable et reproductible
sur différentes machines.

Le projet Selenium gère un ensemble d'images 
Docker que vous pouvez télécharger
et exécutez pour obtenir une grille de travail 
rapidement opérationnelle. Les nœuds sont disponibles pour
Firefox et Chrome. Vous trouverez tous 
les détails sur la configuration d'un réseau
dans le [Docker Selenium](//github.com/SeleniumHQ/docker-selenium)
dépôt.

### Prérequis

La seule exigence pour exécuter une grille 
est d'avoir Docker installé et fonctionnel.
[Installer Docker](//www.docker.com/products/docker-desktop).
