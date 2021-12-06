---
title: "Começando"
linkTitle: "Começando"
weight: 2
description: >
  Se você é novato no Selenium, temos alguns recursos que lhe irão ajudar
  a ficar pronto para começar.
aliases: 
        [
          "/documentation/pt-br/getting_started/", 
          "/documentation/pt-br/getting_started/quick/",
          "/documentation/pt-br/selenium_installation/",
          "/documentation/pt-br/getting_started_with_webdriver/"
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

A instalação do Selenium pode ser dividida em três passos:

1. [Instale a biblioteca Selenium]({{< ref "/installing_selenium_libraries.md" >}}) para a sua 
linguagem de programação favorita 
2. [Prepare o driver do navegador]({{< ref "/installing_browser_drivers.md" >}}) para automatizar 
o seu navegador (exemplo. GeckoDriver para Firefox)
3. (Opcional) Prepare e configure [Selenium Grid]({{< ref "/grid.md" >}}) se pretende escalar os 
seus testes

Se pretende começar com uma ferramenta "low code" de gravação/reprodução, por favor veja
[Selenium IDE](https://selenium.dev/selenium-ide)

Após terminar o setup pode experimentar o exemplo de código presente na nossa  
[página inicial](/pt-br/documentation). Depois visite a secção 
[WebDriver]({{< ref "/webdriver.md" >}}) para aprender mais sobre automação de navegadores com Selenium.
