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

A configuração do Selenium é bastante diferente da configuração de outras ferramentas comerciais.
Para usar Selenium em seu projeto de automação, você precisa instalar as
bibliotecas de linguagem para sua linguagem de escolha. Além disso, você precisará dos
binários WebDriver para os navegadores que você deseja automatizar e executar testes.


A instalação do Selenium é dividida nas etapas:

1. [Instalando a biblioteca Selenium]({{< ref "install_library.md" >}}) para sua linguagem de programação escolhida.
2. [Configure o driver para o navegador]({{< ref "install_drivers.md" >}}) para automatizar o navegador (ex. GeckoDriver para Firefox).
3. (Opcional) Escolha e configure [Selenium Grid]({{< ref "/grid" >}}) se você quiser tornar seus testes escaláveis.

Se você deseja iniciar com ferramenta low-code / gravação e reprodução, por favor veja:
[Selenium IDE](https://selenium.dev/selenium-ide)

Depois de completar as etapas de configuração, você pode executar o snippet de codigo em
[starting page](/pt-br/documentation) na documentação. Então siga para seção
[WebDriver]({{< ref "/webdriver.md" >}}) para aprender mais sobre automação de navegadores com Selenium.
