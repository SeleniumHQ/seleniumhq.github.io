---
title: "Getting Started"
linkTitle: "Getting Started"
weight: 2
description: >
  If you are new to Selenium, we have a few resources that can help you get up to speed right away.
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to French. Do you speak French? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

La configuration de Selenium est très différente de la 
configuration d'autres outils commerciaux. Pour 
utiliser Selenium dans votre projet d'automatisation, 
vous devez installer les bibliothèques de liaisons 
linguistiques pour la langue de votre choix. De plus, 
vous aurez besoin des fichiers binaires WebDriver pour 
les navigateurs sur lesquels vous souhaitez 
automatiser et exécuter le test.

Installing Selenium can be divided in three steps:

1. [Installing the Selenium library]({{< ref "/installing_selenium_libraries.md" >}}) for your desired programming language
2. [Set up the browser driver]({{< ref "/installing_browser_drivers.md" >}}) to automate your browser (e.g. GeckoDriver for Firefox)
3. (Optional) Set up and configure [Selenium Grid]({{< ref "/grid.md" >}}) if you want to scale up your tests

If you wish to start with a low-code/record and playback tool, please check 
[Selenium IDE](https://selenium.dev/selenium-ide)

After completing the setup, you can run the code snippet shown at the 
[starting page](/fr/documentation) in our docs. Then head to the 
[WebDriver]({{< ref "/webdriver.md" >}}) section to learn more about
browser automation with Selenium.