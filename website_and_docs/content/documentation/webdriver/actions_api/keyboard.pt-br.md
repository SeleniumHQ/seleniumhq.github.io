---
title: "Ações de Teclado"
linkTitle: "Teclado"
weight: 2
description: >
  Uma representação de qualquer dispositivo de entrada de teclado para interagir com uma página da web.
aliases: [
"/documentation/pt-br/webdriver/keyboard/",
"/pt-br/documentation/webdriver/keyboard/"
]
---

Existem apenas 2 ações que podem ser realizadas com um teclado: pressionar uma tecla e liberar uma tecla pressionada. Além de suportar caracteres ASCII, cada tecla do teclado possui uma representação que pode ser pressionada ou liberada em sequências designadas.

## Chaves

Além das teclas representadas pelo Unicode regular, valores Unicode foram atribuídos a outras teclas de teclado para uso com o Selenium. Cada linguagem tem sua própria maneira de fazer referência a essas teclas; a lista completa pode ser encontrada
[aqui](https://www.w3.org/TR/webdriver/#keyboard-actions).

{{< tabpane text=true >}}
    {{< tab header="Java" >}}
Use the [Java Keys enum](https://github.com/SeleniumHQ/selenium/blob/selenium-4.2.0/java/src/org/openqa/selenium/Keys.java#L28)
    {{< /tab >}}
    {{< tab header="Python" >}}
Use the [Python Keys class](https://github.com/SeleniumHQ/selenium/blob/selenium-4.2.0/py/selenium/webdriver/common/keys.py#L23)
    {{< /tab >}}
    {{< tab header="CSharp" >}}
Use the [.NET static Keys class](https://github.com/SeleniumHQ/selenium/blob/selenium-4.2.0/dotnet/src/webdriver/Keys.cs#L28)
    {{< /tab >}}
    {{< tab header="Ruby" >}}
Use the [Ruby KEYS constant](https://github.com/SeleniumHQ/selenium/blob/selenium-4.2.0/rb/lib/selenium/webdriver/common/keys.rb#L28)
    {{< /tab >}}
    {{< tab header="JavaScript" >}}
Use the [JavaScript KEYS constant](https://github.com/SeleniumHQ/selenium/blob/selenium-4.2.0/javascript/node/selenium-webdriver/lib/input.js#L44)
   {{< /tab >}}
    {{< tab header="Kotlin" >}}
Use the [Java Keys enum](https://github.com/SeleniumHQ/selenium/blob/selenium-4.2.0/java/src/org/openqa/selenium/Keys.java#L28)
    {{< /tab >}}
{{< /tabpane >}}

## Pressione a tecla

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/actions_api/KeysTest.java#L17-L20" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/actions_api/test_keys.py#L10-L13" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/ActionsAPI/KeysTest.cs#L17-L20" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/actions_api/keys_spec.rb#L13-L16" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/actionsApi/keysTest.spec.js#L19-L22" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/KeysTest.kt#L19-L22" >}}
{{< /tab >}}
{{< /tabpane >}}

## Liberar a tecla

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/actions_api/KeysTest.java#L30-L35" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/actions_api/test_keys.py#L21-L26" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/ActionsAPI/KeysTest.cs#L30-L35" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/actions_api/keys_spec.rb#L25-L30" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/actionsApi/keysTest.spec.js#L34-L39" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/KeysTest.kt#L32-L37" >}}
{{< /tab >}}
{{< /tabpane >}}

## Enviar teclas

This is a convenience method in the Actions API that combines keyDown and keyUp commands in one action.
Executing this command differs slightly from using the element method, but
primarily this gets used when needing to type multiple characters in the middle of other actions.

### Elemento Ativo

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/actions_api/KeysTest.java#L45-L47" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/actions_api/test_keys.py#L34-L36" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/ActionsAPI/KeysTest.cs#L45-L47" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/actions_api/keys_spec.rb#L39-L41" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/actionsApi/keysTest.spec.js#L47-L48" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/KeysTest.kt#L47-L49" >}}
{{< /tab >}}
{{< /tabpane >}}

### Elemento Designado

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/actions_api/KeysTest.java#L59-L62" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/actions_api/test_keys.py#L45-L48" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/ActionsAPI/KeysTest.cs#L58-L61" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/actions_api/keys_spec.rb#L51-L54" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.5.0" >}}
{{< gh-codeblock path="examples/javascript/test/actionsApi/keysTest.spec.js#L61-L65" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/KeysTest.kt#L60-L63" >}}
{{< /tab >}}
{{< /tabpane >}}

## Copiar e Colar

Aqui está um exemplo de uso de todos os métodos acima para realizar uma ação de copiar/colar. Note que a tecla a ser usada para essa operação será diferente, dependendo se for um sistema Mac OS ou não. Este código resultará no texto: `SeleniumSelenium!`

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/actions_api/KeysTest.java#L70-L84" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/actions_api/test_keys.py#L56-L67" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/ActionsAPI/KeysTest.cs#L72-L82" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/actions_api/keys_spec.rb#L64-L74" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/actionsApi/keysTest.spec.js#L75-L87" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/KeysTest.kt#L74-L86" >}}
{{< /tab >}}
{{< /tabpane >}}
