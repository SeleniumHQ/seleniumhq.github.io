---
title: "Alertas, prompts e confirmações JavaScript"
linkTitle: "Alertas, prompts e confirmações JavaScript"
weight: 2
aliases: [
"/documentation/webdriver/js_alerts_prompts_and_confirmations/",
"/documentation/webdriver/browser/alerts/"
]
---

WebDriver fornece uma API para trabalhar com os três tipos nativos de
mensagens pop-up oferecidas pelo JavaScript. Esses pop-ups são estilizados pelo
navegador e oferecem personalização limitada.

## Alertas

O mais simples deles é referido como um alerta, que mostra um
mensagem personalizada e um único botão que dispensa o alerta, rotulado
na maioria dos navegadores como OK. Ele também pode ser dispensado na maioria dos navegadores
pressionando o botão Fechar, mas isso sempre fará a mesma coisa que
o botão OK. <a onclick="window.alert('Sample alert')"> Veja um exemplo de alerta </a>.

O WebDriver pode obter o texto do pop-up e aceitar ou dispensar esses
alertas.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/interactions/AlertsTest.java#L36-L41" >}}
{{< /tab >}}
{{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/interactions/test_alerts.py#L13-L19" >}}
{{< /tab >}}

{{< tab header="CSharp" text=true >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Interactions/AlertsTest.cs#L42-L52" >}}
{{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/interactions/alerts_spec.rb#L15-L22" >}}
{{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/interactions/alert.spec.js#L19-L21" >}}
{{< /tab >}}
{{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/interactions/AlertsTest.kt#L25-L35" >}}
{{< /tab >}}
{{< /tabpane >}}

## Confirmação

Uma caixa de confirmação é semelhante a um alerta, exceto que o usuário também pode escolher
cancelar a mensagem. <a onclick="window.confirm('Are you sure?')"> Veja
uma amostra de confirmação </a>.

Este exemplo também mostra uma abordagem diferente para armazenar um alerta:

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/interactions/AlertsTest.java#L131-L138" >}}
{{< /tab >}}

{{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/interactions/test_alerts.py#L27-L33" >}}
{{< /tab >}}

{{< tab header="CSharp" text=true >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Interactions/AlertsTest.cs#L62-L76" >}}
{{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/interactions/alerts_spec.rb#L28-L35" >}}
{{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/interactions/alert.spec.js#L30-L32" >}}
{{< /tab >}}
{{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/interactions/AlertsTest.kt#L44-L58" >}}
{{< /tab >}}
{{< /tabpane >}}

## Prompt

Os prompts são semelhantes às caixas de confirmação, exceto que também incluem um texto de
entrada. Semelhante a trabalhar com elementos de formulário, você pode
usar o sendKeys do WebDriver para preencher uma resposta. Isso substituirá
completamente o espaço de texto de exemplo. Pressionar o botão Cancelar não enviará nenhum texto.
<a onclick="window.prompt('What is your tool of choice?', navigator.appName)">
Veja um exemplo de prompt </a>.


{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/interactions/AlertsTest.java#L79-L84" >}}
{{< /tab >}}

{{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/interactions/test_alerts.py#L41-L48" >}}
{{< /tab >}}

{{< tab header="CSharp" text=true >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Interactions/AlertsTest.cs#L86-L100" >}}
{{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/interactions/alerts_spec.rb#L41-L48" >}}
{{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/interactions/alert.spec.js#L42-L45" >}}
{{< /tab >}}
{{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/interactions/AlertsTest.kt#L67-L81" >}}
{{< /tab >}}
{{< /tabpane >}}

## beforeunload

Drivers recentes descartam automaticamente os prompts de `beforeunload` por padrão.
Para manter o comportamento antigo,
veja [unhandledPromptBehavior]({{< ref "../drivers/options#unhandledpromptbehavior" >}}).
