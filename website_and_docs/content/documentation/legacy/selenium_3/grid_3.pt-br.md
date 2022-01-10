---
title: "Grid 3"
linkTitle: "Grid 3"
weight: 2
description: >
    Selenium Grid 3 supported WebDriver without Selenium RC code.
    Grid 3 was completely rewritten for the new Grid 4.
aliases: [
"/documentation/pt-br/grid/grid_3/",
"/pt-br/documentation/legacy/grid_3"
]
---

[Grid 4]({{< ref "/documentation/grid" >}})

_Selenium Grid_ é um servidor proxy inteligente
que permite que os testes Selenium encaminhem comandos para instâncias remotas do navegador da web.
Seu objetivo é fornecer uma maneira fácil de executar testes em paralelo em várias máquinas.

Com Selenium Grid,
um servidor atua como o hub que roteia comandos de teste formatados em JSON
para um ou mais nós registrados.
Os testes entram em contato com o hub para obter acesso a instâncias remotas do navegador.
O hub tem uma lista de servidores registrados aos quais fornece acesso,
e permite o controle dessas instâncias.

Selenium Grid nos permite executar testes em paralelo em várias máquinas,
e gerenciar diferentes versões e configurações do navegador centralmente
(em vez de em cada teste individual).

Selenium Grid não é uma bala de prata.
Ele resolve um subconjunto de problemas comuns de delegação e distribuição,
mas não irá, por exemplo, gerenciar sua infraestrutura,
e pode não atender às suas necessidades específicas.

