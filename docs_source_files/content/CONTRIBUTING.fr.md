---
title: Contribuer à la Documentation Selenium
disableToc: true
---

Selenium est un gros projet logiciel  
et sa documentation est la clé
pour comprendre comment fonctionnent les choses 
et apprendre à exploiter son potentiel efficacement.

C'est un projet démarré dans le but de réécrire la documentation Selenium à partir de zéro. 
C'est un effort continu (sans cible de release spécifique) 
dans le but de fournir un manuel à jour sur l'utilisation effective de Selenium. 
Nous espérons rappatrier les parties de l'ancienne documentation lorsque nécessaire.

Les contributions à cette nouvelle documentation suivent le process décrit
dans la section _Contribution_ ci-dessous. 
Il est recommandé de se familiariser avec la documentation 
en en[lisant un peu plus ici]({{< ref "/introduction/about_this_documentation.fr.md" >}}).

---

Le projet Selenium accueille tout type de contribution. 
Voici les différentes aides que vous pouvez apporter:

## Signaler un problème

Lorsque vous signalez une nouveau issue ou en commentez une existante, 
merci de vous assurer que la discussion se concentre sur des problèmes techniques concrets liés à Selenium et/ou sa documentation.

Tous les composants de Selenium évoluent assez rapidement au cours du temps, 
ce qui peut engendrer une documentation dépassée. 
Si cela se trouve être le cas, 
comme mentionné précédemment n'hésitez pas à créer une issue. 
Il se peut que vous souhaitiez également contribuer à mettre à jour la documentation, 
dans ce cas envoyez nous une pull request avec les modifications associées.

Si vous avez un doute sur la réalité d'un problème que vous rencontrez, 
merci de d'abord poser la question à la [mailing list selenium-users@](https://groups.google.com/forum/#!forum/selenium-users),
ou de nous rejoindre sur le canal `#selenium` sur [irc.freenode.org](https://webchat.freenode.net/) 
ou [Slack](https://seleniumhq.herokuapp.com/).

## Contributions

Le projet Selenium appécie les nouveaux contributeurs. 
Les personnes contribuant de façon significative et répétée dans le temps 
sont promus _Committers_ et ont un commit-access sur le projet.

Ce document va vous guider à travers le process de contribution.

### Etape 1: Fork

Forker le projet [sur Github](https://github.com/seleniumhq/docs)
et faites un check out sur votre copie locale.

```shell
% git clone git@github.com:username/docs.git
% cd docs
% git remote add upstream git://github.com/seleniumhq/docs.git
```

#### Dépendences: Hugo

La documentation utilise [Hugo](https://gohugo.io/) afin de construire et afficher le site.
Afin de tester localement avant de commiter tout changement, 
merci d'[installer Hugo](https://gohugo.io/getting-started/installing/) 
et de [démarrer le serveur local](https://gohugo.io/getting-started/usage/#livereload) pour afficher le site en local.

### Etape 2: Branch

Créez une branche pour votre fonctionnalité 
et commencez à faire vos modifications:

```shell
% git checkout -b my-feature-branch
```

Nous utilisons un développement basé sur le HEAD, ce qui veut dire que tous les changements sont appliqués
directement sur le master.

### Etape 3: Commit

Assurez-vous d'abord que git connaisse votre nom et votre adresse mail:

```shell
% git config --global user.name 'Santa Claus'
% git config --global user.email 'santa@example.com'
```

**Ecrire de bon message de commit est important.** 
Un message de commit devrait décrire le changement,
la raison du changement, et référencer les issues corrigées (si nécessaire). 
Suivez les lignes directrices suivantes:

1. La première ligne devrait faire approximativement 50 caractères ou moins et contenir une description courte du changement
2. La seoncde ligne doit rester vide
3. Retourner à la ligne à la colonne 72 pour chaque ligne du corps du message
4. Inclure `Fixes #N`, lorsque nécessaire. Où _N_ est le numéro de li'ssue que le commit corrige

Un bon message de commit peut ressembler à ceci:
```text
explique le commit en une ligne

Corps du commit de message constitué de quelques lignes, expliquant
les choses de façon plus détaillé, contextualisant si besoin 
le problème corrigé, etc.

Le corps du message peut être constitué de plusieurs paragraphes.
Et encore merci de garder des lignes de moins de 72 caractères.
Grâce à cela, la sortie de `git log` restera propre même avec
indentation.

Fixes #141
```
La première ligne doit être la plus parlante possible, 
c'est ce que les gens verront lors d'un `git shortlog` ou `git log --oneline`.

### Etape 4: Rebase

Faites un `git rebase` (et non pas `git merge`) afin de synchroniser votre travail de temps en temps.

```shell
% git fetch upstream
% git rebase upstream/master
```

### Etape 5: Test

Rappelez-vous bien de toujours [lancer le serveur local](https://gohugo.io/getting-started/usage/#livereload),
pour vous assurez que vos changements non rien cassé.

### Etape 6: Traduction

Si vous mettez à jour des infos, en ajouter de nouvelles, ou supprimer des infos obsolètes, 
merci de garder la traduction à jour également. 
Bien sûr il est possible que vous ne parliez pas toutes les langues disponibles. 
Dans ce cas, créez une nouvelle [issue](https://github.com/SeleniumHQ/docs/issues) 
dans laquelle vous décrivez clairement que telle partie de la documentation a évolué 
et qu'une traduction est nécessaire. 
De cette manière, un contributeur parlant une des langues disponibles pourra intervenir 
et nous aider à garder la traduction à jour.

### Etape 7: Signer le CLA

Avant que nous puissions accepter, 
nous vous demandons de signer un [Contributor License Agreement](https://spreadsheets.google.com/spreadsheet/viewform?hl=en_US&formkey=dFFjXzBzM1VwekFlOWFWMjFFRjJMRFE6MQ#gid=0) (ou CLA). 
Et ce afin de nous assurer que les contributeurs ont le droit de céder leur code.

Lorsque vous ouvrez votre pull request, 
nous vous demandons d'indiquer que vous avez signé le CLA. 
Cela réduira d'autant le temps d'intégration de votre demande.

### Etape 8: Push

```shell
% git push origin my-feature-branch
```

Rendez-vous sur https://github.com/yourusername/site.git 
et cliquez sur le bouton _Pull Request_ 
et remplissez le formulaire. 
**Merci d'indiquer que vous avez signé le CLA** (voir Etape 7).

Les Pull Requests sont généralement traitées en quelques jours.
Si il y a des commentaires à prendre en compte de votre part, 
appliquer vos modifications dans de nouveaux commits (de préférence via [fixups](http://git-scm.com/docs/git-commit)) 
et faites un push sur la même branche.

### Etape 9: Intégration

Lorsque la revue de code est terminée, 
un commiter intégrera votre PR sur la branche gh-pages de la documentation. 
Comme nous aimons garder un historique linéraire de la branche master,
nous ferons normalement un squash et rebase de l'historique de votre branche.

## Communication

Les contributeurs Selenium fréquentent le canal `#selenium` sur
[`irc.freenode.org`](https://webchat.freenode.net/) ou sur [Slack](https://seleniumhq.herokuapp.com/).
vous pouvez également rejoindre la [mailing list `selenium-developers@`](https://groups.google.com/forum/#!forum/selenium-developers).

