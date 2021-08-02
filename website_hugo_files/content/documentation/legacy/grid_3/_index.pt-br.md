---
title: "Grid 3"
linkTitle: "Grid 3"
weight: 6
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Most of the documentation found in this section is still in English.
   Please note we are not accepting pull requests to translate this content
   as translating documentation of legacy components does not add value to
   the community nor the project.
</p>
{{% /pageinfo %}}

# Grid 3

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

