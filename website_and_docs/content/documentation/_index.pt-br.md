---
title: "O Projeto Selenium de Automação de Navegadores"
linkTitle: "Documentação"
cascade:
- type: docs
aliases: ["/documentation/pt-br/"]
---

Selenium é um projeto que abrange uma variedade de ferramentas e bibliotecas
que permitem e suportam a automação de navegadores da web.

Ele fornece extensões para emular a interação do usuário com os navegadores,
um servidor de distribuição para escalonar a alocação do navegador,
e a infraestrutura para implementações da [Especificação W3C WebDriver](//www.w3.org/TR/webdriver/)
que permite escrever código intercambiável para todos os principais navegadores da web.

Este projeto é possível graças a colaboradores voluntários
que dedicam milhares de horas de seu próprio tempo,
e disponibilizaram o código-fonte [disponível gratuitamente]({{< ref "copyright.md#license" >}})
para qualquer um usar, aproveitar e melhorar.

Selenium reúne criadores de navegadores, engenheiros e entusiastas
para promover uma discussão aberta sobre a automação da plataforma da web.
O projeto organiza [uma conferência anual](//seleniumconf.com/)
para ensinar e nutrir a comunidade.

No núcleo do Selenium está [WebDriver]({{< ref "/webdriver.md" >}}),
uma interface para escrever conjuntos de instruções que podem ser executados alternadamente em muitos
navegadores. Aqui está uma das instruções mais simples que você pode fazer:


{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/hello/HelloSelenium.java" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/hello/hello_selenium.py" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Hello/HelloSelenium.cs" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/hello/hello_selenium_spec.rb" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/hello/helloSelenium.js" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/hello/HelloSelenium.kt" >}}
{{< /tab >}}
{{< /tabpane >}}


Consulte a [Visão Geral]({{< ref "overview" >}}) para verificar os diferentes componentes do projeto
e decidir se o Selenium é a ferramenta certa para você.

Você deve continuar no Guia de [Introdução]({{< ref "webdriver/getting_started" >}})
para entender como instalar o Selenium e usá-lo com sucesso como uma 
ferramenta de automação de teste e dimensionar testes simples como esse para serem executados em ambientes grandes 
e distribuídos em vários navegadores e em vários sistemas operacionais diferentes.
