---
title: "Tips on working with locators"
linkTitle: "Locators"
weight: 8
needsTranslation: true
description: >
    When to use which locators and how best to manage them in your code.
---

Take a look at examples of the [supported locator strategies]({{< ref "/documentation/webdriver/elements/locators.md" >}}).

No geral, se os IDs de HTML estiverem disponíveis, únicos e consistentemente
previsíveis, eles são o método preferido para localizar um elemento
uma página. Eles tendem a trabalhar muito rapidamente e dispensar muito processamento
que vem com travessias de DOM complicadas.

Se IDs exclusivos não estiverem disponíveis, um seletor CSS bem escrito é o
método preferido de localização de um elemento. XPath funciona bem como CSS
seletores, mas a sintaxe é complicada e frequentemente difícil de
depurar. Embora os seletores XPath sejam muito flexíveis, eles não são tipicamente testados em performance por fornecedores de navegadores e tendem a ser bastante lentos.

As estratégias de seleção baseadas em _linkText_ e _partialLinkText_ têm
desvantagens porque eles só funcionam em elementos de link. Além disso, eles
chamam seletores [querySelectorAll](https://www.w3.org/TR/webdriver/#link-text) internamente no WebDriver.

O nome da tag pode ser uma maneira perigosa de localizar elementos. tem
frequentemente, vários elementos da mesma tag presentes na página.
Isso é útil principalmente ao chamar o método _findElements(By) _ que
retorna uma coleção de elementos.

A recomendação é manter seus localizadores compactos e
legíveis quanto possível. Pedir ao WebDriver para percorrer a estrutura DOM
é uma operação cara, e quanto mais você pode restringir o escopo de
sua pesquisa, melhor.
