---
title: "Capabilities partagées"
linkTitle: "Capabilities partagées"
weight: 1
---

Afin de créer une nouvelle session par 
Selenium WebDriver, l'extrémité locale doit fournir 
les capacités de base à l'extrémité distante. L'extrémité 
distante utilise le même ensemble de capacités pour
créer une session et décrit les fonctionnalités 
de la session actuelle. 

WebDriver offre des capacités que chaque télécommande
fin soutiendra/devrait soutenir la mise en œuvre.
Voici les fonctionnalités prises en charge par WebDriver:

## browserName:

Cette capacité est utilisée pour définir 
le `browserName` pour une session donnée.
Si le navigateur spécifié n'est pas installé sur le
extrémité distante, la création de la session échouera

## browserVersion: 

Cette capacité est facultative, elle est utilisée pour
définissez la version de navigateur disponible à l'extrémité distante.
Par exemple, si vous demandez Chrome version 75 sur un système qui
n'a que 80 installés, la création de session échouera.

## pageLoadStrategy:

Lors de la navigation vers une nouvelle page via URL, 
par défaut, Selenium attendra jusqu'à ce que la 
page soit entièrement chargée avant de répondre. 
Cela fonctionne bien pour débutants, mais peut entraîner 
de longs temps d'attente sur les pages qui chargent une grande
nombre de ressources tierces. L’utilisation d’une 
stratégie autre que celle par défaut peut
tester l'exécution plus rapidement dans des cas 
comme celui-ci, mais peut également introduire une fragilité
où les éléments de la page changent de position 
lorsque les éléments se chargent et changent Taille.

La stratégie de chargement de page interroge le
[document.readyState](//developer.mozilla.org/fr/docs/Web/API/Document/readyState)
comme décrit dans le tableau ci-dessous:

| Strategy | Ready State | Notes |
| -------- | ----------- | ----- |
| normal | complete | Utilisé par défaut, attend le téléchargement de toutes les ressources |
| eager | interactive | L'accès au DOM est prêt, mais d'autres ressources comme les images peuvent encore se charger |
| none | Any | Ne bloque pas du tout WebDriver |

## platformName

Cela identifie le système d'exploitation à 
l'extrémité distante, la récupération de 
`platformName` renvoie le nom du système d'exploitation.

Dans les fournisseurs basés sur le cloud,
la définition de `platformName` définit 
le système d'exploitation à l'extrémité distante.

## acceptInsecureCerts

Cette capacité vérifie si un (ou) expiré
un `certificat TLS` non valide est 
utilisé lors de la navigation
pendant une session.

Si la capacité est définie sur "false", un
[erreur de certificat non sécurisé](//developer.mozilla.org/fr/docs/Web/WebDriver/Errors/InsecureCertificate)
sera retourné car la navigation rencontre n'importe quel domaine
problèmes de certificat. S'il est défini sur 
`true`, un certificat non valide sera
approuvé par le navigateur.

Tous les certificats auto-signés seront approuvés 
par cette fonctionnalité par défaut.
Une fois définie, la capacité `acceptInsecureCerts` aura un
effet pour toute la session.

## Session timeouts

Une `session` WebDriver est imposée avec un 
certain `délai d'expiration de session`
intervalle, pendant lequel l'utilisateur 
peut contrôler le comportement d'exécuter des 
scripts ou de récupérer des informations à partir du navigateur.

Chaque délai d'expiration de session est configuré avec
combinaison de différents `timeouts` comme décrit ci-dessous:

### Script Timeout:

Spécifie quand interrompre un script en cours 
d'exécution dans un contexte de navigation actuel. 
Délai d'expiration par défaut **30,000**
est imposé lorsqu'une nouvelle session 
est créée par WebDriver.

### Page Load Timeout:

Spécifie l'intervalle de temps dans lequel la page Web
doit être chargé dans un contexte de navigation actuel.
Le délai d'attente par défaut **300,000** est imposé lorsqu'un
une nouvelle session est créée par WebDriver. 
Si les limites de chargement de page un 
laps de temps donné / par défaut, le script sera arrêté par
_TimeoutException_.

### Implicit Wait Timeout:

Cela spécifie le temps d'attente
stratégie de localisation implicite des éléments lorsque
éléments de localisation. Le délai d'expiration par défaut **0**
est imposé lorsqu'une nouvelle session est créée par WebDriver.

## unhandledPromptBehavior

Spécifie l'état du `gestionnaire d'invite utilisateur` 
de la session en cours.
Par défaut, **ignorer et notifier l'état**.

### User Prompt Handler:

Cela définit les mesures à prendre lorsqu'un
rencontres d'invite utilisateur à l'extrémité 
distante. Ceci est défini par
`unhandledPromptBehavior` et a les états suivants:

* dismiss
* accept
* dismiss and notify
* accept and notify
* ignore

## setWindowRect

Cette commande modifie la taille et la 
position du courant fenêtre contextuelle de 
navigation. Cette commande agit en tant que setter
à la commande `getWindowRect` qui accepte **width**, **height**,
**x**, **y** comme arguments _optional_.

Lors de l'automatisation, le contexte de navigation actuel sera associé
avec des états de fenêtre, qui décrivent la visibilité
état de la fenêtre du navigateur. Les états des fenêtres sont

* maximized
* minimized
* normal
* fullscreen

La définition de _Width_ ou _Height_ ne garantit 
pas que le résultat la taille de la fenêtre correspondra 
exactement à celle recherchée. Ceci est dû au fait
certains pilotes peuvent ne pas être en mesure de 
redimensionner par incréments d'un pixel.
Pour cette raison, la récupération de l'état/des 
détails de la fenêtre par `getWindowRect`
peut ne pas correspondre également aux 
valeurs définies pour le navigateur.

## strictFileInteractability

This new capability indicates if strict interactability checks 
should be applied to _input type=file_ elements. As strict interactability 
checks are off by default, there is a change in behaviour 
when using _Element Send Keys_ with hidden file upload controls.