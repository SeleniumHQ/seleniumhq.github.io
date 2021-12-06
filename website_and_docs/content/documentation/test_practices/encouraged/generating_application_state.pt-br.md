---
title: "Gerando estado da aplicação"
linkTitle: "Gerando estado da aplicação"
weight: 5
aliases: [
"/documentation/pt-br/guidelines_and_recommendations/generating_application_state/",
"/pt-br/documentation/guidelines/generating_application_state/"
]
---

Selenium não deve ser usado para preparar um caso de teste. Tudo as
ações repetitivas e preparações para um caso de teste devem ser feitas por meio de outros
métodos. Por exemplo, a maioria das IUs da web tem autenticação (por exemplo, um formulário de login). Eliminar o login via navegador da web antes de cada teste irá
melhorar a velocidade e estabilidade do teste. Um método deve ser
criado para obter acesso à AUT* (por exemplo, usando uma API para fazer login e definir um
cookie). Além disso, a criação de métodos para pré-carregar dados para
o teste não deve ser feito usando Selenium. Como dito anteriormente,
APIs existentes devem ser aproveitadas para criar dados para a AUT *.

***AUT**: Application under test
