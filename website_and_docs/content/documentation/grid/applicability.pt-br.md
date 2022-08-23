---
title: "Quando usar a Grid"
linkTitle: "Quando usar a Grid"
weight: 4
description: >
  Is Grid right for you?
aliases: [
"/documentation/pt-br/grid/when_to_use_grid/",
"/pt-br/documentation/grid/when_to_use_grid"
]
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to Portuguese. Do you speak Portuguese? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

De modo geral, há dois motivos pelos quais você pode querer usar a Grid.

* Para executar seus testes em vários navegadores, várias versões de navegador,
e navegadores executados em diferentes sistemas operacionais.
* Para reduzir o tempo que leva para o conjunto de testes concluir uma aprovação no teste.

A Grid é usada para acelerar a execução dos testes usando
várias máquinas para executar testes em paralelo. Por exemplo, se você tiver um conjunto de
100 testes, mas você configurou o Grid para suportar 4 máquinas diferentes (VMs ou
máquinas físicas separadas) para executar esses testes, seu conjunto de testes será concluído
em (aproximadamente) um quarto do tempo, do que se você executasse seus testes sequencialmente
em uma única máquina. Para grandes conjuntos de testes e conjuntos de testes de longa duração, como
aqueles que realizam grandes quantidades de validação de dados, isso pode ser um
economizador de tempo significativo. Alguns conjuntos de testes podem levar horas para serem executados.
Outro motivo para impulsionar o tempo gasto na execução do pacote é para encurtar o tempo de resposta para os resultados do teste
após o código de check-in dos desenvolvedores para o AUT. Cada vez mais equipes de software
praticando o desenvolvimento de software Agile desejam testar o feedback tão imediatamente quanto
possível, em oposição a esperar durante a noite para uma aprovação no teste durante a noite.

A Grid também é usada para suportar testes em execução em vários
ambientes de runtime, especificamente, em diferentes navegadores ao mesmo tempo. Por
exemplo, uma ‘Grid’ de máquinas virtuais pode ser configurada com cada uma suportando um
navegador diferente que o aplicativo a ser testado deve suportar. Então, máquina 1
tem Internet Explorer 8, máquina 2, Internet Explorer 9, máquina 3, o mais recente
Chrome e máquina 4 com o Firefox mais recente. Quando o conjunto de testes é executado,
o Selenium Grid recebe cada combinação de navegador de teste e atribui cada teste a um navegador necessário.

Além disso, pode-se ter uma Grid do mesmo navegador, tipo e versão. Por
exemplo, pode-se ter uma Grid de 4 máquinas, cada uma executando 3 instâncias de
Firefox 70, permitindo um ‘server-farm’ (em certo sentido) de Firefox.
Quando o pacote é executado, cada teste é passado para o Grid que
atribui o teste à próxima instância disponível do Firefox. Desta maneira um
obtém aprovação em que, concebivelmente, 12 testes estão sendo executados ao mesmo tempo em
paralelo, reduzindo significativamente o tempo necessário para concluir os testes.

A Grid é muito flexível. Esses dois exemplos podem ser combinados para permitir
várias instâncias de cada tipo e versão de navegador. Uma configuração como
essa forneceria execução paralela para conclusão rápida de testes e
suporte para vários tipos e versões de navegador simultaneamente.
