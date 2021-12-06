---
title: "Códigos de respostas HTTP"
linkTitle: "Códigos de respostas HTTP"
weight: 3
aliases: [
"/documentation/pt-br/worst_practices/http_response_codes/",
"/pt-br/documentation/worst_practices/http_response_codes/"
] 
---

Para algumas configurações de navegador no Selenium RC,
Selenium atuou como um proxy entre o navegador
e o site sendo automatizado.
Isso significa que todo o tráfego do navegador que passou pelo Selenium
poderia ser capturado ou manipulado.
O método `captureNetworkTraffic()`
pretendia capturar todo o tráfego de rede entre o navegador
e o site sendo automatizado,
incluindo códigos de resposta HTTP.

Selenium WebDriver é uma abordagem completamente diferente
para a automação do navegador,
preferindo agir mais como um usuário.
Isso é representado na maneira como você escreve testes com o WebDriver.
Em testes funcionais automatizados,
verificar o código de status
não é um detalhe particularmente importante da falha de um teste;
as etapas que o precederam são mais importantes.

O navegador sempre representará o código de status HTTP,
imagine, por exemplo, uma página de erro 404 ou 500.
Uma maneira simples de “falhar rapidamente” quando você encontrar uma dessas páginas de erro
é verificar o título da página ou o conteúdo de um ponto confiável
(por exemplo, a tag `<h1>`) após cada carregamento de página.
Se você estiver usando o modelo de objeto de página,
você pode incluir esta verificação em seu construtor de classe
ou ponto semelhante onde o carregamento da página é esperado.
Ocasionalmente, o código HTTP pode até ser representado
na página de erro do navegador
e você pode usar o WebDriver para ler isso
e melhorar sua saída de depuração.

Verificar se a própria página da web está alinhada
com a prática ideal do WebDriver
de representar a visão do usuário do site.

Se você insiste, uma solução avançada para capturar códigos de status HTTP
é replicar o comportamento do Selenium RC usando um proxy.
A API WebDriver fornece a capacidade de definir um proxy para o navegador,
e há uma série de proxies que irão
permitir que você manipule de forma programática
o conteúdo das solicitações enviadas e recebidas do servidor da web.
Usar um proxy permite que você decida como deseja responder
para códigos de resposta de redirecionamento.
Além disso, nem todo navegador
torna os códigos de resposta disponíveis para WebDriver,
então optar por usar um proxy
permite que você tenha uma solução que funciona para todos os navegadores.
