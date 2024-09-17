---
title: "Evite compartilhamento de estado"
linkTitle: "Evite compartilhamento de estado"
weight: 8
aliases: [
"/documentation/pt-br/guidelines_and_recommendations/avoid_sharing_state/",
"/pt-br/documentation/guidelines/avoid_sharing_state/"
]
---


Embora mencionado em vários lugares, vale a pena mencionar novamente. Garanta que
os testes são isolados uns dos outros.

* Não compartilhe dados de teste. Imagine vários testes em que cada um consulta o banco de dados
para pedidos válidos antes de escolher um para executar uma ação. Caso dois testes
peguem a mesma ordem, provavelmente você obterá um comportamento inesperado.

* Limpe dados desatualizados no aplicativo que podem ser obtidos por outro
teste, por exemplo registros de pedidos inválidos.

* Crie uma nova instância do WebDriver por teste. Isso ajuda a garantir o isolamento do teste
e torna a paralelização mais simples.

    * Se você escolher o [pytest](https://pytest.org/) como seu executor de testes, isso pode ser facilmente feito retornando seu driver em um fixture global. Dessa forma, cada teste terá sua própria instância de driver, e você pode garantir que os drivers sempre sejam encerrados após a conclusão de um teste (seja ele aprovado ou reprovado)
