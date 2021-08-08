---
title: "Codes réponse HTTP"
liniTitle: "Codes réponse HTTP"
weight: 3
aliases: ["/documentation/fr/worst_practices/http_response_codes/"]
---

Pour certaines configurations de navigateur dans Selenium RC,
Selenium a agi comme un proxy entre le navigateur
et le site étant automatisé.
Cela signifiait que tout le trafic du navigateur passait par Selenium
pourrait être capturé ou manipulé.
La méthode `captureNetworkTraffic()`
censé capturer tout le trafic réseau entre le navigateur
et le site étant automatisé,
y compris les codes de réponse HTTP.

Selenium WebDriver est une approche complètement différente
à l'automatisation du navigateur,
préférant agir plus comme un utilisateur
et cela est représenté dans la façon dont 
vous écrivez des tests avec WebDriver.
Dans les tests fonctionnels automatisés,
vérification du code d'état
n'est pas un détail particulièrement important de l'échec d'un test;
les étapes qui l'ont précédé sont plus importantes.

Le navigateur représentera toujours le code d'état HTTP,
imaginez par exemple une page d'erreur 404 ou 500.
Un moyen simple de "échouer rapidement" lorsque 
vous rencontrez l'une de ces pages d'erreur
est de vérifier le titre de la page ou le contenu d'un point fiable
(par exemple, la balise `<h1>`) après chaque chargement de page.
Si vous utilisez le modèle d'objet de page,
vous pouvez inclure cette vérification dans votre constructeur de classe
ou un point similaire où le chargement de la page est prévu.
Parfois, le code HTTP peut même être représenté
dans la page d'erreur du navigateur
et vous pouvez utiliser WebDriver pour lire ceci
et améliorez votre sortie de débogage.

La vérification de la page Web 
elle-même est en ligne
avec la pratique idéale de WebDriver
de représenter et d'affirmer la 
vision de l'utilisateur du site Web.

Si vous insistez, une solution avancée pour 
capturer les codes d'état HTTP
consiste à reproduire le comportement de 
Selenium RC à l'aide d'un proxy.
L'API WebDriver permet de définir un proxy pour le navigateur,
et il existe un certain nombre de procurations qui
vous permet de manipuler par programme
le contenu des demandes envoyées et reçues du serveur Web.
L'utilisation d'un proxy vous permet de 
décider comment vous souhaitez répondre
aux codes de réponse de redirection.
De plus, tous les navigateurs ne sont pas
met les codes de réponse à la disposition de WebDriver,
donc choisir d'utiliser un proxy
vous permet d'avoir une solution qui 
fonctionne pour chaque navigateur.
