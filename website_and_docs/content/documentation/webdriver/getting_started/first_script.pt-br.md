---
title: "Programe o seu primeiro script Selenium"
linkTitle: "Primeiro Script"
weight: 8
needsTranslation: false
description: >
    Instruções passo a passo para programar um script Selenium
---

Assim que você tiver o [Selenium instalado]({{< ref "install_library.md" >}}), 
você estará pronto para programar códigos Selenium.

## Oito Componentes Básicos
Tudo que o Selenium faz é enviar comandos ao navegador de internet para fazer algo ou solicitar informações dele.
A maior parte do que você irá fazer com o Selenium é uma combinação desses comandos básicos.

Click on the link to "View full example on GitHub" to see the code in context.

### 1. Iniciando uma sessão
Para ter mais detalhes sobre como iniciar uma sessão, leia nossa documentação em [driver sessions]({{< ref "../drivers/" >}})

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/FirstScript.java#L12" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/getting_started/first_script.py#L4" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/GettingStarted/FirstScript.cs#L11" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/getting_started/first_script.rb#L3" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/getting_started/firstScript.spec.js#L8" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/getting_started/FirstScriptTest.kt#L16" >}}
{{< /tab >}}
{{< /tabpane >}}

### 2. Agindo no navegador de internet
Nesse exemplo estamos [navegando]({{< ref "/documentation/webdriver/interactions/navigation.md" >}}) para uma página web. 

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/FirstScript.java#L14" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/getting_started/first_script.py#L6" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/GettingStarted/FirstScript.cs#L13" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/getting_started/first_script.rb#L5" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/getting_started/firstScript.spec.js#L9" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/getting_started/FirstScriptTest.kt#L18" >}}
{{< /tab >}}
{{< /tabpane >}}

### 3. Solicitando informação do navegador de internet
Existem diversos tipos de [informação sobre o navegador de internet]({{< ref "/documentation/webdriver/interactions" >}}) que você
pode solicitar, incluindo window handles, tamanho / posição do navegador, cookies, alertas e etc.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/FirstScript.java#16" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/getting_started/first_script.py#L8" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/GettingStarted/FirstScript.cs#L15" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/getting_started/first_script.rb#L7" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/getting_started/firstScript.spec.js#L11" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/getting_started/FirstScriptTest.kt#L20" >}}
{{< /tab >}}
{{< /tabpane >}}

### 4. Estabelecendo uma Estratégia de Espera

Sincronizar o código ao estado atual do navegador é um dos maiores 
desafios 
quando se trabalha com o Selenium, fazer isso de maneira bem feita é um tópico avançado.

Essencialmente, você quer ter certeza absoluta de que o elemento está na página antes de tentar localizá-lo
e o elemento está em um estado interativo antes de você tentar interagir com ele.

Uma espera implícita raramente é a melhor solução, mas é a mais fácil de demonstrar aqui, então
vamos usá-la como um substituto. 

Leia mais sobre [Estratégias de espera]({{< ref "/documentation/webdriver/waits.md" >}}).

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/FirstScript.java#L18" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/getting_started/first_script.py#L10" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/GettingStarted/FirstScript.cs#L17" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/getting_started/first_script.rb#L9" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/getting_started/firstScript.spec.js#L14" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/getting_started/FirstScriptTest.kt#L23" >}}
{{< /tab >}}
{{< /tabpane >}}

### 5. Encontrando um elemento
A maioria dos comandos na maior parte das sessões do Selenium são relacionados a elementos e você não pode 
interagir
com um sem o primeiro [encontrando um elemento]({{< ref "/documentation/webdriver/elements" >}})

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/FirstScript.java#L20-L21" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/getting_started/first_script.py#L12-L13" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/GettingStarted/FirstScript.cs#L19-L20" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/getting_started/first_script.rb#L11-L12" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/getting_started/firstScript.spec.js#L16-L17" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/getting_started/FirstScriptTest.kt#L25-L26" >}}
{{< /tab >}}
{{< /tabpane >}}

### 6. Agindo no elemento
Há apenas um punhado de [ações a serem executadas em um elemento]({{< ref "/documentation/webdriver/elements/interactions.md" >}}),
mas você irá usá-las com frequência.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/FirstScript.java#L23-L24" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/getting_started/first_script.py#L15-L16" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/GettingStarted/FirstScript.cs#L22-L23" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/getting_started/first_script.rb#L14-L15" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/getting_started/firstScript.spec.js#L19-L20" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/getting_started/FirstScriptTest.kt#L28-L29" >}}
{{< /tab >}}
{{< /tabpane >}}

### 7. Solicitando informações do elemento
Elementos podem guardar muitas [informações que podem ser solicitadas]({{< ref "/documentation/webdriver/elements/information" >}}).

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/FirstScript.java#L27" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/getting_started/first_script.py#L19" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/GettingStarted/FirstScript.cs#L26" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/getting_started/first_script.rb#L18" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/getting_started/firstScript.spec.js#L23" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/getting_started/FirstScriptTest.kt#L32" >}}
{{< /tab >}}
{{< /tabpane >}}

### 8. Encerrando a sessão

Isso encerra o processo do driver, que por padrão também fecha o navegador.
Nenhum outro comando pode ser enviado para esta instância do driver.

See [Quitting Sessions]({{< ref "../drivers/#quitting-sessions" >}}).

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/FirstScript.java#L29" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/getting_started/first_script.py#L21" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/GettingStarted/FirstScript.cs#L28" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/getting_started/first_script.rb#L20" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/getting_started/firstScript.spec.js#L28" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/getting_started/FirstScriptTest.kt#L35" >}}
{{< /tab >}}
{{< /tabpane >}}


## Running Selenium File

{{< alert-code >}}
Add example for how to execute each of the above pages via command line
{{< /alert-code >}}


## Próximos Passos

Most Selenium users execute many sessions and need to organize them to minimize duplication and keep the code
more maintainable. Read on to learn about how to put this code into context for your use case with
[Using Selenium]({{< ref "using_selenium.md" >}}).
