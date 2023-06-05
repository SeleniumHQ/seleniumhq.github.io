---
title: "Começando"
linkTitle: "Começando"
weight: 2
needsTranslation: true
description: >
  Se você é novo no Selenium, nós temos alguns recursos que podem te ajudar a se atualizar imediatamente.
aliases: [
"/documentation/pt-br/getting_started/", 
"/documentation/pt-br/getting_started/quick/",
"/documentation/pt-br/selenium_installation/",
"/documentation/pt-br/getting_started_with_webdriver/",
"/pt-br/documentation/getting_started/"
]
---

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

Selenium setup is quite different from the setup of other commercial tools.
Before you can start writing Selenium code, you have to 
install the language bindings libraries for your language of choice, the browser you
want to use, and the driver for that browser.

***Follow the links below to get up and going with Selenium WebDriver.***

If you wish to start with a low-code/record and playback tool, please check
[Selenium IDE](https://selenium.dev/selenium-ide)

Once you get things working, if you want to scale up your tests, check out the 
[Selenium Grid]({{< ref "/documentation/grid" >}}).
