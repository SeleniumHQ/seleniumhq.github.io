---
title: "Sessões de Driver"
linkTitle: "Drivers"
weight: 3
---

Iniciar e encerrar uma sessão serve para abrir e fechar um navegador.

## Criando Sessões

Criar uma nova sessão corresponde ao comando W3C para [Nova sessão](https://w3c.github.io/webdriver/#new-session)

A sessão é criada automaticamente ao inicializar um novo objeto da classe Driver. 

Cada linguagem permite que uma sessão seja criada com argumentos de uma dessas classes (ou equivalentes):

* [Options]({{< ref "options.md" >}}) to describe the kind of session you want; default values are used for local, but
  this is required for remote
* Alguma forma de [configuração do cliente HTTP]({{< ref "http_client.md" >}}) (a implementação varia entre as linguagens)
* [Ouvintes]({{< ref "listeners.md" >}})

### Local Driver

O principal argumento exclusivo para iniciar um driver local inclui informações sobre a inicialização do serviço de driver necessário na máquina local.

* Objeto de [Serviço]({{< ref "service.md" >}}) se aplica apenas a drivers locais e fornece informações sobre o driver do navegador.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/drivers/OptionsTest.java#L23" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/drivers/test_options.py#L9" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/BaseTest.cs#L48" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/drivers/options_spec.rb#L14" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/drivers/service.spec.js#L46-L50" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Driver Remoto

O principal argumento exclusivo para iniciar um driver remoto inclui informações sobre onde executar o código. Leia os detalhes na [seção Driver Remoto]({{< ref "remote_webdriver.md" >}})

## Encerrando Sessões

Encerrar uma sessão corresponde ao comando W3C para [Excluir uma Sessão](https://w3c.github.io/webdriver/#delete-session).

Nota importante: o método `quit` é diferente do método `close`, e é recomendável sempre usar `quit` para finalizar a sessão.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/getting_started/FirstScript.java#L29" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/drivers/test_options.py#L11" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/GettingStarted/FirstScript.cs#L28" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/drivers/options_spec.rb#L16" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/getting_started/firstScript.spec.js#L28" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/getting_started/FirstScriptTest.kt#L35" >}}
{{< /tab >}}
{{< /tabpane >}}
