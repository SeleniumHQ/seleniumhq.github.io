---
title: "A Deeper Look"
linkTitle: "A Deeper Look"
weight: 2
description: >
  Selenium est un projet englobant un ensemble d'outil et de librairies rendant possible l'automatisation de navigateur web. 
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to French. Do you speak French? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

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

L'approche de conception minimaliste de Selenium lui confère une
polyvalence à inclure en tant que composant dans des applications plus importantes.
L'infrastructure environnante fournie sous l'égide du sélénium
vous donne les outils pour assembler
votre [grille de navigateurs]({{</* ref "/grid/_index.md" */>}})
afin que les tests puissent être exécutés sur différents navigateurs et plusieurs systèmes d'exploitation
sur une gamme de machines.

Imaginez une banque d'ordinateurs dans votre salle de serveurs ou votre centre de données
allumer tous les navigateurs en même temps
frapper les liens, les formulaires de votre site,
et tables&mdash;tester votre application 24h / 24.
Grâce à l'interface de programmation simple
fournie pour les langues les plus courantes,
ces tests se dérouleront sans relâche en parallèle,
vous faire rapport en cas d'erreur.

C'est un objectif pour aider à en faire une réalité pour vous,
en fournissant aux utilisateurs des outils et de la 
documentation pour non seulement contrôler les navigateurs,
mais pour faciliter la mise à l'échelle et le déploiement de telles grilles.

### Qui utilise le selenium

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
et qui puisse rester une plate-forme dominante pour 
l'automatisation des tests fonctionnels.
