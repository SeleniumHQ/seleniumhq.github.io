---
title: Contributing to the Selenium Site & Documentation
disableToc: true
---

Selenium est un gros projet logiciel, son 
site et sa documentation sont essentiels
comprendre comment les choses fonctionnent 
et apprendre des moyens efficaces d'exploiter
son potentiel.

Ce projet contient à la fois le site et la documentation 
de Selenium. C'est un effort continu 
(qui ne vise aucune version spécifique) pour fournir
des informations mises à jour sur la façon 
d'utiliser efficacement le sélénium,
impliqués et comment contribuer au sélénium.

Les contributions vers le site et les documents 
suivent le processus décrit dans
la section ci-dessous sur les contributions. 
Tu devrais passer du temps
vous familiariser avec la documentation en lisant
[en savoir plus]({{<ref "/introduction/about_this_documentation.fr.md">}}).

Le projet Selenium accueille les contributions 
de tous. Il y a un plusieurs façons d'aider:

## Signaler un problème

Lorsque vous signalez un nouveau problème ou 
commentez un problème existant, veuillez
assurez-vous que les discussions sont liées à 
des problèmes techniques concrets avec le
Logiciel Selenium, son site et / ou sa documentation.

Tous les composants Selenium changent assez rapidement 
au fil du temps, donc cela peut rendre la documentation 
obsolète. Si vous trouvez cela être le cas, comme mentionné, 
ne doutez pas de créer un problème pour cela. Il est 
également possible que vous sachiez comment mettre à jour
documentation, merci de nous envoyer une pull 
request avec les informations changements.

Si vous n'êtes pas sûr que ce que vous avez trouvé soit un problème ou non,
Si vous n'êtes pas sûr que ce que vous avez trouvé soit un problème ou non,
veuillez demander par les canaux de communication décrits à
https://selenium.dev/support.

## Contributions

Le projet Selenium accueille de nouveaux contributeurs. Les individus qui font
des contributions importantes et précieuses au fil du temps sont faites _Committers_
et donné un accès de validation au projet.

Ce guide vous guidera tout au long du processus de contribution.

### Step 1: Fork

Fork le projet [sur Github](https://github.com/seleniumhq/seleniumhq.github.io)
et vérifiez votre copie localement.

```shell
% git clone git@github.com:seleniumhq/seleniumhq.github.io.git
% cd seleniumhq.github.io
```

#### Dépendances: Hugo

Nous utilisons [Hugo](https://gohugo.io/) pour construire et rendre le site et les documents.
Pour tout vérifier localement avant même de commettre des modifications, veuillez
[installez Hugo](https://gohugo.io/getting-started/installing/), familiarisez-vous
avec elle et [exécutez le serveur local](https://gohugo.io/getting-started/usage/#livereload)
pour rendre le site localement (des instructions détaillées peuvent être trouvées dans le
prochaines étapes).

### Step 2: Branch

Créez une branche de fonctionnalité et lancez le piratage:

```shell
% git checkout -b my-feature-branch
```

Nous pratiquons le développement basé sur HEAD, ce qui 
signifie que tous les changements sont appliqués
directement sur le dessus du maître.

### Step 3: Faire des changements

Le référentiel contient le site et les documents, 
qui sont deux Hugo séparés projets. Si vous souhaitez 
apporter des modifications au site, travaillez sur le
répertoire `site_source_files`. Pour voir un aperçu 
en direct de vos modifications,
exécutez `hugo server` sur le répertoire racine du site.

```shell
% cd site_source_files
% hugo server
```

Pour apporter des modifications aux documents, 
basculez vers le répertoire `docs_source_files`.

```shell
% cd docs_source_files
% hugo server
```

Les documents sont traduits en plusieurs langues et 
les traductions sont basées sur
le contenu anglais. Lorsque vous modifiez un fichier, 
**assurez-vous** de changements dans tous les autres 
fichiers traduits également. Cela peut différer selon
sur le changement, par exemple:
 
* Si vous ajoutez un exemple de code au fichier `browser_manipulation.en.md`,
ajoutez-le également à `browser_manipulation.es.md`,` browser_manipulation.ef.md`,
`browser_manipulation.ja.md`, et tous les autres fichiers traduits.
* Si vous trouvez une traduction qui peut être améliorée, ne modifiez que la traduction
fichier.
* Si vous ajoutez une nouvelle traduction linguistique, ajoutez les nouveaux fichiers avec le
suffixe approprié. Il n'est pas nécessaire de tout traduire pour soumettre un
PR, cela peut être fait de manière itérative. N'oubliez pas de 
vérifier certaines configurations nécessaires
dans le fichier `config.toml`.
* Si vous apportez des modifications au texte dans la version 
anglaise, remplacez la même section dans
les fichiers traduits avec votre modification 
(oui, en anglais), et ajoutez ce qui suit
remarquez en haut du fichier.

```
{{%/* notice info */%}}
<i class="fas fa-language"></i> Page being translated from 
English to {LANGUAGE}. Do you speak {LANGUAGE}? Help us to translate
it by sending us pull requests!
{{%/* /notice */%}}
```

### Step 4: Commit

Assurez-vous d'abord que git connaît 
votre nom et votre adresse e-mail:

```shell
% git config --global user.name 'Santa Claus'
% git config --global user.email 'santa@example.com'
```

**Il est important d'écrire de bons messages de validation.** Un message de validation
devrait décrire ce qui a changé, pourquoi et les problèmes de référence résolus (si
tout). Suivez ces directives lorsque vous en rédigez une:

1. La première ligne doit contenir environ 50 caractères ou moins et contenir un
    brève description du changement.
2. Laissez la deuxième ligne vierge.
3. Enveloppez toutes les autres lignes sur 72 colonnes.
4. Incluez `Fixes #N`, où _N_ est le numéro de problème du commit
    corrections, le cas échéant.

Un bon message de validation peut ressembler à ceci:

```text
explain commit normatively in one line

Body of commit message is a few lines of text, explaining things
in more detail, possibly giving some background about the issue
being fixed, etc.

The body of the commit message can be several paragraphs, and
please do proper word-wrap and keep columns shorter than about
72 characters or so. That way `git log` will show things
nicely even when it is indented.

Fixes #141
```

La première ligne doit être significative car c'est 
ce que les gens voient lorsqu'ils
exécutez `git shortlog` ou` git log --oneline`.

### Step 5: Rebase

Utilisez `git rebase` (et non` git merge`) pour 
synchroniser votre travail de temps en temps.

```shell
% git fetch upstream
% git rebase upstream/master
```

### Step 6: Test

N'oubliez pas de [exécuter le serveur local](https://gohugo.io/getting-started/usage/#livereload),
avec cela, vous pouvez être sûr que vos modifications n'ont rien cassé.

### Step 7: Push

```shell
% git push origin my-feature-branch
```

Accédez à _https://github.com/yourusername/seleniumhq.github.io.git_ et
appuyez sur la _Pull Request_ et remplissez le formulaire. **Indiquez s'il vous plait
que vous avez signé le CLA** (voir l'étape 7).

Les demandes d'extraction sont généralement examinées en quelques jours. S'il y a
commentaires à adresser, appliquez vos modifications dans les nouveaux commits (de préférence
[fixups](http://git-scm.com/docs/git-commit)) et pousser à la même chose
branche.

### Step 8: Integration

Lorsque la révision du code est terminée, un committer prendra votre PR et
l'intégrer dans la branche principale du référentiel. Parce que nous aimons garder un
histoire linéaire sur la branche principale, nous allons normalement écraser et rebaser
l'historique de votre succursale.

## Communication

Tous les détails sur la façon de communiquer avec les contributeurs du projet
et la communauté dans son ensemble se trouve sur https://selenium.dev/support
