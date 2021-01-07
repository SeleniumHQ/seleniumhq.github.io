---
title: "WebDriver Remoto"
chapter: true
weight: 6
---

# WebDriver Remoto

Você pode usar o WebDriver remotamente da mesma forma que o usaria
localmente. A principal diferença é que um WebDriver remoto precisa ser
configurado para que possa executar seus testes em uma máquina separada.

Um WebDriver remoto é composto por duas peças: um cliente e um
servidor. O cliente é o seu teste WebDriver e o servidor é simplesmente um
Servlet Java, que pode ser hospedado em qualquer servidor de aplicativo JEE moderno.