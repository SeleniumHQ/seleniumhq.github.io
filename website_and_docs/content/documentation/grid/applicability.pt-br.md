---
title: "Quando usar a Grid"
linkTitle: "Quando usar a Grid"
weight: 4
description: >
  Será a Grid a melhor escolha para você?
aliases: [
"/documentation/pt-br/grid/when_to_use_grid/",
"/pt-br/documentation/grid/when_to_use_grid"
]
---

Quando deve usar a Selenium Grid?

* Para executar testes em paralelo, com vários tipos e versões de navegador e sistemas operativos
* Para reduzir o tempo necessário a executar uma bateria de testes

A Selenium Grid executa baterias de testes em paralelo contra várias máquinas (chamadas Nodes).
Para testes longos, isto poderá poupar minutos, horas e talvez dias.
Isto encurta o tempo de espera para obter resultados dos testes de cada vez que sobre a aplicação 
em testes é alterada.

A Grid consegue executar testes (em paralelo) contra multiplos e diferentes navegadores, também
é capaz de executar contra várias instâncias do mesmo navegador. Como exemplo, vamos imaginar
uma Grid com seis Nodes. A primeira máquina tem a versão mais recente do Firefox, a segunda tem
a versão "mais recente -1", a terceira tem o Chrome mais recente e as restantes máquinas são Mac
Mini, que permitem três testes em paralelo com a versão mais recente to Safari.

O tempo de execução pode ser expresso como uma fórmula simples:

```Número de testes * Tempo médio de cada teste / Número de Nodes = Tempo total de execução```

       15      *       45s        /        1        =      11m 15s   // Sem Grid
       15      *       45s        /        5        =      2m 15s    // Grid com 5 Nodes
       15      *       45s        /        15       =      45s       // Grid com 15 Nodes
      100      *       120s       /        15       =      13m 20s   // Demoraria mais de 3 horas sem Grid

À medida que a bateria de testes executa, a Grid vai alocando os testes contra estes navegadores
como está definido nos testes.

Uma configuração deste tipo pode acelerar bastante o tempo de execução mesmo no caso de baterias de testes grandes.

A Selenium Grid é uma parte integrante do projecto Selenium e é mantida em paralelo pela mesma equipa de developers
que desenvolvem o resto das funcionalidades base do projecto. Dada a importância da velocidade e desempenho da
execução dos testes, a Grid tem sido considerada desde o início como uma parte crítica e fundamental ao projecto.
