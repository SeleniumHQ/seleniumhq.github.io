---
title: "Começando com o WebDriver"
chapter: true
weight: 4
---

# Começando com o WebDriver

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

Mais detalhes sobre os drivers podem ser encontrados em
[Idiossincrasias do driver]({{<ref "/driver_idiosyncrasies/_index.md">}}).