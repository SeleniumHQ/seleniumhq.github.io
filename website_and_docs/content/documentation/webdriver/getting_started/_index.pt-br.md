---
title: "Começando"
linkTitle: "Começando"
weight: 2
needsTranslation: true
description: >
  If you are new to Selenium, we have a few resources that can help you get up to speed right away.
aliases: [
"/documentation/pt-br/getting_started/", 
"/documentation/pt-br/getting_started/quick/",
"/documentation/pt-br/selenium_installation/",
"/documentation/pt-br/getting_started_with_webdriver/",
"/pt-br/documentation/getting_started/"
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

A instalação do Selenium pode ser dividida em 3 passos:

1. [Intalando a ferramenta Selenium]({{< ref "install_library.md" >}}) Para s linguagem de programação desejada;
2. [Configurando o driver do browser]({{< ref "install_drivers.md" >}}) Para automatizar seu browser (ex: GeckoDriver para Firefox);
3. (Optional) Configurando o [Selenium Grid]({{< ref "/grid" >}}) Caso queira aumentar a escala de seus testes.

Caso queira começar com a versão low-code/record e playback tool, por favor cheque: 
[Selenium IDE](https://selenium.dev/selenium-ide)

Depois que completar a instalação, você pode rodar o código mostrado 
[starting page](/pt-br/documentation) em nossa documentação. E então ir até a seção 
[WebDriver]({{< ref "/webdriver.md" >}}) para saber mais sobre automação de browser com Selenium.
