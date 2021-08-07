---
title: "Diretrizes e recomendações"
linkTitle: "Diretrizes"
weight: 7
description: >
  Some guidelines and recommendations on testing from the Selenium project.
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to Portuguese. Do you speak Portuguese? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

Uma nota sobre "Melhores práticas": evitamos intencionalmente a frase "Melhores
Práticas" nesta documentação. Nenhuma abordagem funciona para todas as situações.
Preferimos a ideia de "Diretrizes e Recomendações". Nós encorajamos
que você leia e decida cuidadosamente quais abordagens
funcionarão para você em seu ambiente específico.

O teste funcional é difícil de acertar por muitos motivos.
Como se o estado, a complexidade e as dependências do aplicativo não tornassem o teste suficientemente difícil,
lidar com navegadores (especialmente com incompatibilidades entre navegadores)
torna a escrita de bons testes um desafio.

Selenium fornece ferramentas para facilitar a interação funcional do usuário,
mas não o ajuda a escrever suítes de teste bem arquitetadas.
Neste capítulo, oferecemos conselhos, diretrizes e recomendações
sobre como abordar a automação funcional de páginas da web.

Este capítulo registra os padrões de design de software populares
entre muitos dos usuários do Selenium
que tiveram sucesso ao longo dos anos.
