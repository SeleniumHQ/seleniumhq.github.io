---
title: "Le projet Selenium et les outils"
weight: 1
---

### Selenium contrôle les navigateurs Web

_Selenium_ est beaucoup de choses, mais à la base, 
c'est un ensemble d'outils pour l'automatisation du 
navigateur Web qui utilise les meilleures techniques 
disponibles pour contrôler à distance les instances 
du navigateur et émuler l'interaction d'un utilisateur avec le navigateur.

Il permet aux utilisateurs de simuler les activités 
courantes effectuées par les utilisateurs finaux; saisir du 
texte dans les champs, sélectionner des valeurs déroulantes et 
cocher des cases, et cliquer sur les liens dans les documents. 
Il fournit également de nombreux autres contrôles tels que 
le mouvement de la souris, l'exécution arbitraire de JavaScript et bien plus encore.

Bien qu'utilisé principalement pour les tests frontaux des sites Web,
Selenium est à la base une _bibliothèque_ d'agent utilisateur de navigateur.
Les interfaces sont omniprésentes à leur application,
qui encourage la composition avec d'autres bibliothèques en fonction de votre objectif.

### Une interface pour les gouverner tous

Un des principes directeurs du projet
est de prendre en charge une interface commune pour toutes les (principales) technologies de navigateur.
Les navigateurs Web sont des applications incroyablement complexes et hautement conçues,
effectuer leurs opérations de manière complètement différente
mais qui se ressemblent souvent en le faisant.
Même si le texte est rendu dans les mêmes polices,
les images sont affichées au même endroit
et les liens vous mènent à la même destination.
Ce qui se passe en dessous est aussi différent que la nuit et le jour.
Le sélénium «résume» ces différences,
cacher leurs détails et leurs subtilités à la personne qui écrit le code.
Cela vous permet d'écrire plusieurs lignes de code pour effectuer un workflow compliqué,
mais ces mêmes lignes s'exécuteront sur Firefox,
Internet Explorer, Chrome et tous les autres navigateurs pris en charge.

### Outils et support

L'approche de conception minimaliste de Selenium lui confère
polyvalence à inclure en tant que composant dans des applications plus importantes.
L'infrastructure environnante fournie sous l'égide du sélénium
vous donne les outils pour assembler
votre [grille de navigateurs] ({{<ref "/grid/_index.md">}})
afin que les tests puissent être exécutés sur différents navigateurs et plusieurs systèmes d'exploitation
sur une gamme de machines.

Imaginez une banque d'ordinateurs dans votre salle de serveurs ou votre centre de données
allumer tous les navigateurs en même temps
frapper les liens, les formulaires de votre site,
et tables & mdash; tester votre application 24h / 24.
Grâce à l'interface de programmation simple
fourni pour les langues les plus courantes,
ces tests se dérouleront sans relâche en parallèle,
vous faire rapport en cas d'erreur.

C'est un objectif pour aider à en faire une réalité pour vous,
en fournissant aux utilisateurs des outils et de la 
documentation pour non seulement contrôler les navigateurs,
mais pour faciliter la mise à l'échelle et le déploiement de telles grilles.

### Qui utilise le sélénium

Beaucoup des entreprises les plus importantes au monde
ont adopté Selenium pour leurs tests sur navigateur,
remplaçant souvent des efforts de plusieurs années impliquant d'autres outils propriétaires.
À mesure qu'elle gagne en popularité, ses exigences et ses défis se multiplient.

Alors que le Web devient plus compliqué
et de nouvelles technologies sont ajoutées aux sites Web,
c'est la mission de ce projet de les suivre autant que possible.
Être un projet open source,
ce soutien est assuré grâce au généreux don de temps de nombreux bénévoles,
chacun a un "travail de jour".

Une autre mission du projet est d'encourager
plus de volontaires pour participer à cet effort,
et construire une communauté forte
afin que le projet puisse continuer à 
suivre les technologies émergentes
et restent une plate-forme dominante pour 
l'automatisation des tests fonctionnels.


### Histoire

Lorsque Selenium 1 est sorti en 2004,
il n'était pas nécessaire de réduire le temps passé
vérification manuelle d'un comportement cohérent dans le front-end d'une application Web.
Il a utilisé les outils disponibles à l'époque
et s'est fortement appuyé sur l'injection de JavaScript dans la page Web testée
pour émuler l'interaction d'un utilisateur.

Bien que JavaScript soit un bon outil pour vous permettre d'introspecter les propriétés du DOM
et de faire certaines observations côté client que vous ne seriez pas en mesure de faire autrement,
il ne répond pas à la capacité de reproduire naturellement les interactions d'un utilisateur
comme si la souris et le clavier étaient utilisés.

Depuis lors, le sélénium a grandi et mûri beaucoup,
dans un outil largement utilisé par de nombreux & mdash; sinon la plupart des & mdash;
les plus grandes organisations du monde.
Selenium est passé d'une boîte à outils d'automatisation de tests homebrewed développée à Thoughtworks
pour un public niché et un cas d'utilisation spécifique,
à la bibliothèque d'automatisation du navigateur _de facto_ du monde.

Tout comme Selenium RC utilisait les outils du commerce disponibles à l'époque,
[Selenium WebDriver] ({{<ref "/webdriver/_index.md">}}) perpétue cette tradition en prenant
la partie d'interaction du navigateur avec le gazon du vendeur du navigateur
et en leur demandant de prendre la responsabilité des implémentations back-end, orientées navigateur.
Récemment, ce travail est devenu un processus de normalisation du W3C
où l'objectif est de transformer le composant WebDriver dans Selenium
dans la bibliothèque de contrôle à distance _du jeur_ pour les agents utilisateurs.