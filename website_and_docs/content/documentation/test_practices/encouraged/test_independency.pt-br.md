---
title: "Independência de Testes"
linkTitle: "Independência de Testes"
weight: 9
aliases: [
"/documentation/pt-br/guidelines_and_recommendations/test_independency/",
"/pt-br/documentation/guidelines/test_independency/"
]
---


Escreva cada teste como sua própria unidade. Escreva os testes de uma forma que não seja
dependente de outros testes para concluir:

Digamos que existe um sistema de gerenciamento de conteúdo com o qual você pode criar
algum conteúdo personalizado que então aparece em seu site como um módulo após
publicação, e pode levar algum tempo para sincronizar entre o CMS e a aplicação.

Uma maneira errada de testar seu módulo é que o conteúdo seja criado e
publicado em um teste e, em seguida, verificar o módulo em outro teste. Este teste
não é viável, pois o conteúdo pode não estar disponível imediatamente para o
outro teste após a publicação.

Em vez disso, você pode criar um conteúdo stub que pode ser ligado e desligado
dentro do teste e use-o para validar o módulo. Contudo,
para a criação de conteúdo, você ainda pode ter um teste separado.
