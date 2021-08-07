---
title: "Installer les librairies Selenium"
linkTitle: "Installer les librairies Selenium"
weight: 1
description: >
  Setting up the Selenium library for your favourite programming language.
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to Spanish. Do you speak Spanish? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

Vous devez d'abord installer les fixations Selenium pour votre projet d'automatisation.
Le processus d'installation des bibliothèques dépend de la langue que vous choisissez d'utiliser.

### Java
L'installation des bibliothèques Selenium pour Java peut être effectuée à l'aide de Maven.
Ajoutez la dépendance _selenium-java_ dans votre projet pom.xml:

```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-java</artifactId>
  <version>3.X</version>
</dependency>
```

La dépendance _selenium-java_ prend en charge l'exécution de votre automatisation
projet avec tous les navigateurs pris en charge par Selenium. Si vous souhaitez exécuter des tests
uniquement dans un navigateur spécifique, vous pouvez ajouter la dépendance pour ce navigateur
dans votre fichier _pom.xml_.
Par exemple, vous devez ajouter la dépendance suivante dans votre _pom.xml_
fichier pour exécuter vos tests uniquement dans Firefox:

```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-firefox-driver</artifactId>
  <version>4.X</version>
</dependency>
```
   
De la même manière, si vous souhaitez exécuter des tests uniquement dans Chrome,
vous devez ajouter la dépendance suivante:

```xml
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-chrome-driver</artifactId>
  <version>4.X</version>
</dependency>
```

### Python
L'installation des bibliothèques Selenium pour Python peut être effectuée à l'aide de pip:

```shell
pip install selenium
```


Vous pouvez également télécharger [l'archive source PyPI](https://pypi.org/project/selenium/#files)
(selenium-x.x.x.tar.gz) et installez-le à l'aide de _setup.py_:

```shell
python setup.py install
```

### C#
L'installation des bibliothèques Selenium pour C # peut être effectuée à l'aide de NuGet:

```shell
# Using package manager
Install-Package Selenium.WebDriver
# or using .Net CLI
dotnet add package Selenium.WebDriver
```

### Ruby
L'installation des bibliothèques Selenium pour Ruby peut être effectuée à l'aide de gem:

```shell
gem install selenium-webdriver
```

### JavaScript
L'installation des bibliothèques Selenium pour JavaScript peut être effectuée à l'aide de npm:

```shell
npm install selenium-webdriver
```

### Kotlin
Due to missing native language bindings for Kotlin, you have to use the 
Java Bindings, e.g. with maven [Java](#java)

