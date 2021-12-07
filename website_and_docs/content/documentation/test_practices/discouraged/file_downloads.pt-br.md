---
title: "Downloads de arquivo"
linkTitle: "Downloads de arquivo"
weight: 2
aliases: [
"/documentation/pt-br/worst_practices/file_downloads/",
"/pt-br/documentation/worst_practices/file_downloads/"
] 
---

Embora seja possível iniciar um download
clicando em um link com um navegador sob o controle do Selenium,
a API não expõe o progresso do download,
tornando-o menos do que ideal para testar arquivos baixados.
Isso ocorre porque o download de arquivos não é considerado um aspecto importante
de emular a interação do usuário com a plataforma da web.
Em vez disso, encontre o link usando Selenium
(e todos os cookies necessários)
e passe este cookie para uma biblioteca de solicitação HTTP como
[libcurl](//curl.haxx.se/libcurl/).

O [driver HtmlUnit](https://github.com/SeleniumHQ/htmlunit-driver) pode baixar 
anexos acessando-os como fluxos de entrada, implementando o 
[AttachmentHandler](https://htmlunit.sourceforge.io/apidocs/com/gargoylesoftware/htmlunit/attachment/AttachmentHandler.html). 
O AttachmentHandler pode ser adicionado ao WebClient [HtmlUnit](https://htmlunit.sourceforge.io/).
