---
title: "Tipos de teste"
linkTitle: "Tipos de teste"
weight: 2
aliases: [
"/documentation/pt-br/introduction/types_of_testing/",
"/pt-br/documentation/guidelines/types_of_testing/"
]
---

### Teste de aceitação
Este tipo de teste é feito para determinar se um recurso ou sistema
atende às expectativas e requisitos do cliente.
Este tipo de teste geralmente envolve
cooperação ou feedback do cliente, sendo uma atividade de validação que
responde a pergunta:
>Estamos construindo o produto **_certo_**?.

Para aplicações web, a automação desse teste pode ser feita
diretamente com o Selenium, simulando o comportamento esperado do usuário.
Esta simulação pode ser feita por gravação / reprodução ou por meio dos
diferentes idiomas suportados, conforme explicado nesta documentação.
Observação: o teste de aceitação é um subtipo de **_teste funcional_**,
ao qual algumas pessoas também podem se referir.
            
### Teste funcional
Este tipo de teste é feito para determinar se um
recurso ou sistema funciona corretamente sem problemas. Verifica
o sistema em diferentes níveis para garantir que todos os cenários
são cobertos e que o sistema faz _o que está_
suposto fazer. É uma atividade de verificação que
responde a pergunta:
>Estamos construindo o produto **_corretamente?_**.
             
Isso geralmente inclui: os testes funcionam sem erros
(404, exceções ...), de forma utilizável (redirecionamentos corretos),
de forma acessível e atendendo às suas especificações
(consulte **_teste de aceitação_** acima).

Para aplicativos da web, a automação desse teste pode ser
feito diretamente com o Selenium, simulando os retornos esperados.
Esta simulação pode ser feita por gravação / reprodução ou por meio de
os diferentes idiomas suportados, conforme explicado nesta documentação.

### Testes de Integração

Os testes de integração verificam as interações entre diferentes componentes ou módulos de um sistema. Vários módulos são testados juntos. O objetivo dos testes de integração é garantir que todos os módulos se integrem e funcionem juntos conforme esperado. Os testes de integração automatizados ajudam a garantir que essas interações funcionem conforme o esperado e que os componentes integrados funcionem corretamente juntos.
>Por exemplo, **_Testando o fluxo de pedido de um item em um site de comércio eletrônico junto com o pagamento._**

### Testes de sistema

O System Testing é um teste de produto completo e totalmente integrado. É um teste ponta a ponta onde o ambiente de teste é semelhante ao ambiente de produção. Aqui, navegamos por todos os recursos do software e testamos se o negócio final/recurso final funciona. Apenas testamos o recurso final e não verificamos o fluxo de dados, nem fazemos testes funcionais e tudo mais.
>Por exemplo, **_Testando o fluxo de ponta a ponta desde o login até a colocação e pedido e verificando novamente o pedido na página Meus Pedidos e logoff de um site de comércio eletrônico._**

### Teste de performance/desempenho
Como o próprio nome indica, testes de desempenho são feitos
para medir o desempenho de um aplicativo.

Existem dois subtipos principais para testes de desempenho:

#### Teste de carga
O teste de carga é feito para verificar o quão bem o
aplicativo funciona sob diferentes cargas definidas
(geralmente um determinado número de usuários conectados ao mesmo tempo).

#### Teste de estresse
O teste de estresse é feito para verificar o quão bem
a aplicação funciona sob estresse (ou acima da carga máxima suportada).

Geralmente, os testes de estresse são feitos executando alguns
testes escritos com Selenium simulando diferentes usuários
utilizando uma função específica no aplicativo da web e
recuperando algumas medições significativas.

Isso geralmente é feito por outras ferramentas que recuperam as métricas.
Uma dessas ferramentas é a **_JMeter_**.

Para um aplicativo da web, os detalhes a serem medidos incluem
taxa de transferência, latência, perda de dados, tempos de carregamento de componentes individuais ...

Nota 1: todos os navegadores têm uma guia de desempenho em seus
seção de ferramentas para desenvolvedores (acessível pressionando F12)

Nota 2: é um subtipo de **_teste não funcional_**
já que isso geralmente é medido por sistema e não por função / recurso.
            
### Teste regressivo
Esse teste geralmente é feito após uma alteração, correção ou adição de recurso.

Para garantir que a mudança não quebrou nenhumas das
funcionalidades, alguns testes já executados são executados novamente.
            
O conjunto de testes re-executados pode ser total ou parcial
e pode incluir vários tipos diferentes, dependendo
da equipe de aplicação e desenvolvimento.
            
### Desenvolvimento orientado a testes (TDD)
Em vez de um tipo de teste _per se_, o TDD é uma metodologia iterativa de desenvolvimento na qual os testes conduzem o design de um recurso.

Cada ciclo começa criando um conjunto de testes de unidade no qual
o recurso deve eventualmente ser aprovado (eles devem falhar na primeira execução).

Depois disso, ocorre o desenvolvimento para fazer os testes passarem.
Os testes são executados novamente, iniciando outro ciclo
e esse processo continua até que todos os testes sejam aprovados.

Visa acelerar o desenvolvimento de um aplicativo
com base no fato de que os defeitos custam menos quanto mais cedo são encontrados.

### Desenvolvimento orientado a comportamento (BDD)
BDD também é uma metodologia de desenvolvimento iterativa
com base no TDD acima, em que o objetivo é envolver
todas as partes no desenvolvimento de um aplicativo.

Cada ciclo começa criando algumas especificações
(que deve falhar). Em seguida, crie a os testes de unidade com falha
(que também devem falhar) e, em seguida, faça o desenvolvimento.

Este ciclo é repetido até que todos os tipos de testes sejam aprovados.

Para fazer isso, uma linguagem de especificação é
usada. Deve ser compreensível por todas as partes e ser
simples, padronizada e explícita.
A maioria das ferramentas usa **_Gherkin_** como esse idioma.

O objetivo é ser capaz de detectar ainda mais erros
do que TDD, visando potenciais erros de aceitação
também e tornar a comunicação entre as partes mais fácil.

Um conjunto de ferramentas está atualmente disponível
para escrever as especificações e combiná-las com funções de código,
como **_Cucumber_** ou **_SpecFlow_**.

Um conjunto de ferramentas é construído em cima do Selenium para tornar este processo
ainda mais rápido, transformando diretamente as especificações BDD em
código executável.
Alguns deles são **_JBehave, Capybara e Robot Framework_**.
            
