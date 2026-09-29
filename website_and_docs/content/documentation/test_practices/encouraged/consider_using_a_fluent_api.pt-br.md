---
title: "Considere usar uma API fluente"
LinkTitle: "Considere usar uma API fluente"
weight: 10
aliases: [
"/documentation/guidelines/consider_using_a_fluent_api/"
]
---


Martin Fowler cunhou o termo ["API Fluent"](//www.martinfowler.com/bliki/FluentInterface.html). Selenium já
implementa algo assim em sua classe `FluentWait`, que é
pretende ser uma alternativa à classe padrão <code>Wait</code>.
Você pode habilitar o padrão de design de API fluente em seu objeto de página
e, em seguida, consulte a página de pesquisa do Google com um snippet de código como este:

{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/test_practices/FluentApiExample.java#L44-L46" >}}

A classe de objeto da página do Google com este comportamento fluente
pode ser assim:

{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/test_practices/FluentApiExample.java#L11-L35" >}}
