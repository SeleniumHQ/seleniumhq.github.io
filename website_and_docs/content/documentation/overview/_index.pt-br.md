---
title: "Resumo"
linkTitle: "Resumo"
weight: 1
description: >
  Será Selenium a ferramenta para você? Veja um resumo dos componentes do projecto.
aliases: ["/documentation/pt-br/introduction/"]
---

Selenium não é só uma ferramenta ou API,
mas sim uma composição de várias ferramentas.

## WebDriver

Se você está começando com automação de testes de um site de desktop ou site para celular, então
vai usar as APIs WebDriver. O [WebDriver]({{< ref "/webdriver.md" >}})
usa APIs de automação de navegador disponibilizadas por fornecedores de navegador para o controlar e
executar testes. É como se um usuário real o estivesse operando. Como o
WebDriver não exige que sua API seja compilada com o código do aplicativo,
não é intrusivo. Portanto, você está testando o
mesmo aplicativo que você envia aos ambientes de produção.


## IDE

[IDE](//selenium.dev/selenium-ide) (Ambiente de Desenvolvimento Integrado, em português)
é a ferramenta que você usa para desenvolver seus casos de teste Selenium. É uma extensão para Chrome
e Firefox fácil de usar e geralmente é a maneira mais eficiente de desenvolver
casos de teste. Ela registra as ações dos usuários no navegador para você, usando
comandos Selenium existentes, com parâmetros definidos pelo contexto daquele
elemento. Isso não é apenas uma economia de tempo, mas também uma maneira excelente
de aprender a sintaxe de script do Selenium.

## Grid

Selenium Grid permite que você execute casos de teste em diferentes
máquinas em diferentes plataformas. O controle para
acionar os casos de teste está na extremidade local, e
quando os casos de teste são acionados, eles são automaticamente
executados pela extremidade remota.

Após o desenvolvimento dos testes WebDriver, você pode enfrentar
a necessidade de executar seus testes em vários navegadores e
combinações de sistemas operacionais.
É aqui que o [Grid]({{< ref "/grid.md" >}}) entra em cena.
