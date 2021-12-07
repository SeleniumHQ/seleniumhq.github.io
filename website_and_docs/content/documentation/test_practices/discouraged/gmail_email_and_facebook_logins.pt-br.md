---
title: "Login via Gmail, email e Facebook"
linkTitle: "Login via Gmail, email e Facebook"
weight: 4
aliases: [
"/documentation/pt-br/worst_practices/gmail_email_and_facebook_logins/",
"/pt-br/documentation/worst_practices/gmail_email_and_facebook_logins/"
] 
---

Por vários motivos, fazer login em sites como Gmail e Facebook
usando do WebDriver não é recomendado.
Além de ser contra os termos de uso desses sites
(onde você corre o risco de ter a conta encerrada),
é lento e não confiável.

A prática ideal é usar as APIs que os provedores de e-mail oferecem,
ou no caso do Facebook, o serviço de ferramentas para desenvolvedores
que expõe uma API para criar contas de teste, amigos e assim por diante.
Embora usar uma API possa parecer um pouco trabalhoso,
você será recompensado em velocidade, confiabilidade e estabilidade.
A API também não deve mudar,
enquanto as páginas da web e os localizadores de HTML mudam frequentemente
e exigem que você atualize sua estrutura de teste.

Login em sites de terceiros usando WebDriver
em qualquer ponto do seu teste aumenta o risco
de seu teste falhar porque torna o teste mais longo.
Uma regra geral é que testes mais longos
são mais frágeis e não confiáveis.

Implementações WebDriver que estão
[em conformidade com W3C](//w3c.github.io/webdriver/webdriver-spec.html)
também anotam o objeto `navigator`
com uma propriedade `WebDriver`
para que os ataques de negação de serviço possam ser mitigados.
