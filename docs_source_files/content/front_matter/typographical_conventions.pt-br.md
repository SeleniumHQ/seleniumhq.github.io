---
title: "Convenções tipográficas"
weight: 2
---

## Capitalização de títulos

Deve-se evitar a capitalização do título,
como _Um Título Muito Estiloso_,
e em vez disso, use _Um título muito estiloso_.
Letras maiúsculas gratuitas, ou caixa do título,
muitas vezes mostram um mal-entendido - ou um desprezo por -
convenções ortográficas.
Preferimos o que é conhecido como _sentence case_,
com uma única inicial maiúscula para iniciar cabeçalhos.

## Comprimento da linha

Ao editar o código fonte da documentação,
que é escrito em HTML puro,
limite o comprimento das linhas a cerca de 72 caracteres.

Alguns de nós dão um passo adiante
e usam o que é chamado de
[_linefeeds semânticos_](//rhodesmill.org/brandon/2012/one-sentence-per-line),
que é uma técnica pela qual as linhas de origem HTML,
que não são lidos pelo público,
são divididas em "intervalos naturais" na prosa.
Em outras palavras, as frases são divididas
em quebras naturais entre as orações.
Em vez de se preocupar com as linhas de cada parágrafo
de modo que todos terminem perto da margem direita,
os feeds de linha podem ser adicionados em qualquer lugar
que existe uma ruptura entre as ideias.

Isso pode tornar as diffs muito fáceis de ler
ao colaborar por meio do git,
mas não é algo que obrigamos os colaboradores a usar.
