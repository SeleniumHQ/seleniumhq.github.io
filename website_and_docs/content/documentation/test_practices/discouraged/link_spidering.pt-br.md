---
title: "Navegação por links"
linkTitle: "Navegação por links"
weight: 7
aliases: [
"/documentation/pt-br/worst_practices/link_spidering/",
"/pt-br/documentation/worst_practices/link_spidering/"
] 
---

Usar o WebDriver para navegar por links
não é uma prática recomendada. Não porque não pode ser feito,
mas porque WebDriver definitivamente não é a ferramenta ideal para isso.
O WebDriver precisa de tempo para inicializar,
e pode levar vários segundos, até um minuto
dependendo de como seu teste é escrito,
apenas para chegar à página e atravessar o DOM.

Em vez de usar o WebDriver para isso,
você poderia economizar muito tempo
executando um comando [curl](//curl.haxx.se/),
ou usando uma biblioteca como BeautifulSoup
uma vez que esses métodos não dependem
em criar um navegador e navegar para uma página.
Você está economizando muito tempo por não usar o WebDriver para essa tarefa.

