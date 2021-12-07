---
title: "Teste de performance/desempenho"
linkTitle: "Teste de performance/desempenho"
weight: 6
aliases: [
"/documentation/pt-br/worst_practices/performance_testing/",
"/pt-br/documentation/worst_practices/performance_testing/"
] 
---

Teste de desempenho usando Selenium e WebDriver
geralmente não é recomendado.
Não porque é incapaz,
mas porque não é otimizado para o trabalho
e é improvável que você obtenha bons resultados.

Pode parecer ideal para teste de desempenho
no contexto do usuário, mas um conjunto de testes WebDriver
estão sujeitos a muitos pontos de fragilidade externa e interna
que estão além do seu controle;
por exemplo, velocidade de inicialização do navegador,
velocidade dos servidores HTTP,
resposta de servidores de terceiros que hospedam JavaScript ou CSS,
e a penalidade de instrumentação
da própria implementação do WebDriver.
A variação nesses pontos causará variação em seus resultados.
É difícil separar a diferença
entre o desempenho do seu site
e o desempenho de recursos externos,
e também é difícil dizer qual é a penalidade de desempenho
para usar WebDriver no navegador,
especialmente se você estiver injetando scripts.

A outra atração potencial é "economizar tempo" -
execução de testes funcionais e de desempenho ao mesmo tempo.
No entanto, os testes funcionais e de desempenho têm objetivos opostos.
Para testar a funcionalidade, um testador pode precisar ser paciente
e aguarde o carregamento,
mas isso irá turvar os resultados do teste de desempenho e vice-versa.

Para melhorar o desempenho do seu site,
você precisará ser capaz de analisar o desempenho geral
independente das diferenças de ambiente,
identificar práticas de código ruins,
repartição do desempenho de recursos individuais
(ou seja, CSS ou JavaScript),
para saber o que melhorar.
Existem ferramentas de teste de desempenho disponíveis
que podem fazer este trabalho,
que fornecem relatórios e análises,
e podem até fazer sugestões de melhorias.

Pacotes de exemplo (código aberto) a serem usados ​​são: [JMeter](//jmeter.apache.org/)
