---
title: "Virtual Authenticator"
linkTitle: "Virtual Authenticator"
weight: 16
description: >
    Uma representação do modelo Web Authenticator.
aliases: [
"/pt-br/documentation/webdriver/virtual_authenticator/"
]
---

Aplicações web podem habilitar um mecanismo de autenticação baseado em chaves públicas conhecido como Web Authentication para autenticar usuários sem usar uma senha. 
[Web Authentication](https://www.w3.org/TR/webauthn-2/) define APIs que permitem ao usuário criar uma credencial e registra-la com um autenticador. 
Um autenticador pode ser um dispositivo ou um software que guarde as chaves públicas do usuário e as acesse caso seja pedido. 

Como o nome sugere, Virtual Authenticator emula esses autenticadores para testes.

## Virtual Authenticator Options

Um Autenticador Virtual tem uma [série de propriedades](https://www.w3.org/TR/webauthn-2/#sctn-automation-virtual-authenticators).
Essas propriedades são mapeadas como VirtualAuthenticatorOptions nos bindings do Selenium.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/VirtualAuthenticatorTest.java#L55-L61" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/VirtualAuthenticatorTest.cs#L48-55" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_virtual_authenticator.py#L40-L46" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/virtual_authenticator/virtualAuthenticatorOptions.spec.js#L11-L17" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}{{< /tabpane >}}


## Add Virtual Authenticator

Cria um novo autenticador virtual com as propriedades fornecidas.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/VirtualAuthenticatorTest.java#L68-L73" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/VirtualAuthenticatorTest.cs#L63-71" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_virtual_authenticator.py#L53-L58" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/virtual_authenticator/virtualAuthenticator.spec.js#L51-L55" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}{{< /tabpane >}}

## Remove Virtual Authenticator

Remove o autenticador virtual adicionado anteriormente.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/VirtualAuthenticatorTest.java#L86" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/VirtualAuthenticatorTest.cs#80-86" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_virtual_authenticator.py#L68-L74" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/virtual_authenticator/virtualAuthenticator.spec.js#L62-L63" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}{{< /tabpane >}}

## Create Resident Credential

Cria uma resident (stateful) credential com os requeridos [parâmetros](https://w3c.github.io/webauthn/#sctn-automation-add-credential). 

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/VirtualAuthenticatorTest.java#L100-L103" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/VirtualAuthenticatorTest.cs#103-107" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_virtual_authenticator.py#L80-L97" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/virtual_authenticator/virtualAuthenticator.spec.js#L80-L94" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}{{< /tabpane >}}

## Create Non-Resident Credential 

Cria uma resident (stateless) credential com os requeridos [parâmetros](https://w3c.github.io/webauthn/#sctn-automation-add-credential). 

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/VirtualAuthenticatorTest.java#L143-L145" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/VirtualAuthenticatorTest.cs#L145-148" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}{{< /tabpane >}}

## Add Credential

Registra a credencial com o autenticador. 

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/VirtualAuthenticatorTest.java#L137-L146" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/VirtualAuthenticatorTest.cs#L139-150" >}}   
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}{{< /tabpane >}}

## Get Credential

Retorna a lista de credenciais que o autenticador possui.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/VirtualAuthenticatorTest.java#L157-L171" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/VirtualAuthenticatorTest.cs#L162-178" >}}  
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}{{< /tabpane >}}


## Remove Credential

Remove a credencial do autenticador baseado na id da credencial passado.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/VirtualAuthenticatorTest.java#L181-L190" >}}
{{< /tab >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/VirtualAuthenticatorTest.cs#L189-198" >}} 
{{< tab header="CSharp" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}{{< /tabpane >}}


## Remove All Credentials

Remove todas as credenciais do autenticador.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/VirtualAuthenticatorTest.java#L196-L205" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/VirtualAuthenticatorTest.cs#L207-216" >}} 
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}{{< /tabpane >}}

## Set User Verified

Diz se o autenticador simulará sucesso ou falha na verificação de usuário.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/VirtualAuthenticatorTest.java#L211-L212" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/VirtualAuthenticatorTest.cs#L224-225" >}} 
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}{{< /tabpane >}}