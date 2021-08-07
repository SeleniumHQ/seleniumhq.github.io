---
title: "Começando"
linkTitle: "Começando"
weight: 2
description: >
  If you are new to Selenium, we have a few resources that can help you get up to speed right away.
aliases: 
        [
          "/documentation/pt-br/getting_started/", 
          "/documentation/pt-br/getting_started/quick/",
          "/documentation/pt-br/selenium_installation/",
          "/documentation/pt-br/getting_started_with_webdriver/"
        ]
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to Portuguese. Do you speak Portuguese? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

Selenium suporta automação de todos os principais navegadores do mercado
por meio do uso do _WebDriver_.
WebDriver é uma API e protocolo que define uma interface de linguagem neutra
para controlar o comportamento dos navegadores da web.
Cada navegador é apoiado por uma implementação WebDriver específica, chamada de *driver*.
O driver é o componente responsável por delegar ao navegador,
e lida com a comunicação de e para o Selenium e o navegador.

Essa separação é parte de um esforço consciente para que os fornecedores de navegadores
assumam a responsabilidade pela implementação de seus navegadores.
Selenium faz uso desses drivers de terceiros sempre que possível,
mas também fornece seus próprios drivers mantidos pelo projeto
para os casos em que isso não é uma realidade.

A estrutura do Selenium une todas essas peças
por meio de uma interface voltada para o usuário que permite aos diferentes back-ends de navegador
serem usados de forma transparente,
permitindo a automação entre navegadores e plataformas cruzadas.

A configuração do Selenium é bastante diferente da configuração de outras ferramentas comerciais.
Para usar Selenium em seu projeto de automação, você precisa instalar as
bibliotecas de linguagem para sua linguagem de escolha. Além disso, você precisará dos
binários WebDriver para os navegadores que você deseja automatizar e executar testes.

Installing Selenium can be divided in three steps:

1. [Installing the Selenium library]({{< ref "/installing_selenium_libraries.md" >}}) for your desired programming language
2. [Set up the browser driver]({{< ref "/installing_browser_drivers.md" >}}) to automate your browser (e.g. GeckoDriver for Firefox)
3. (Optional) Set up and configure [Selenium Grid]({{< ref "/grid.md" >}}) if you want to scale up your tests

If you wish to start with a low-code/record and playback tool, please check 
[Selenium IDE](https://selenium.dev/selenium-ide)

After completing the setup, you can run the code snippet shown at the 
[starting page](/pt-br/documentation) in our docs. Then head to the 
[WebDriver]({{< ref "/webdriver.md" >}}) section to learn more about
browser automation with Selenium.