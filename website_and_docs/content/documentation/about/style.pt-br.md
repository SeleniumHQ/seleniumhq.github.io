---
title: "Style guide for Selenium documentation"
linkTitle: "Style"
needsTranslation: true
description: >-
    Conventions for contributing to the Selenium documenation and code examples
weight: 3
---

## Capitalização de títulos

Deve-se evitar a capitalização do título,
como _Um Título Muito Estiloso_,
e em vez disso, use _Um título muito estiloso_.
Letras maiúsculas gratuitas, ou caixa do título,
muitas vezes mostram um mal-entendido - ou um desprezo por -
convenções ortográficas.
Preferimos o que é conhecido como _sentence case_,
com uma única inicial maiúscula para iniciar cabeçalhos.

## Comprimento da linha

Ao editar o código fonte da documentação,
que é escrito em HTML puro,
limite o comprimento das linhas a cerca de 72 caracteres.

Alguns de nós dão um passo adiante
e usam o que é chamado de
[_linefeeds semânticos_](//rhodesmill.org/brandon/2012/one-sentence-per-line),
que é uma técnica pela qual as linhas de origem HTML,
que não são lidos pelo público,
são divididas em "intervalos naturais" na prosa.
Em outras palavras, as frases são divididas
em quebras naturais entre as orações.
Em vez de se preocupar com as linhas de cada parágrafo
de modo que todos terminem perto da margem direita,
os feeds de linha podem ser adicionados em qualquer lugar
que existe uma ruptura entre as ideias.

Isso pode tornar as diffs muito fáceis de ler
ao colaborar por meio do git,
mas não é algo que obrigamos os colaboradores a usar.

## Traducciones

A documentação é traduzida para vários idiomas e as traduções são baseadas no conteúdo em inglês. Ao alterar um arquivo, **certifique-se** de realizar a
mudanças em todos os outros arquivos traduzidos também. Isso pode ser diferente dependendo
sobre a mudança, por exemplo:
 
* Se você adicionar um exemplo de código ao arquivo `browser_manipulation.en.md`,
também adicione-o a `browser_manipulation.es.md`,` browser_manipulation.ef.md`,
`browser_manipulation.ja.md`, e todos os outros arquivos traduzidos.
* Se você encontrar uma tradução que possa ser melhorada, altere apenas o arquivo traduzido.
* Se você estiver adicionando uma nova tradução de idioma, adicione os novos arquivos com o
sufixo apropriado. Não há necessidade de traduzir tudo para enviar um
PR, pode ser feito iterativamente. Não se esqueça de verificar algumas configurações necessárias de
valores no arquivo `config.toml`.
* Se você fizer alterações de texto na versão em inglês, substitua a mesma seção em
os arquivos traduzidos com sua alteração (sim, em inglês), e adicione o seguinte
observe na parte superior do arquivo.
 

```
{{%/* pageinfo color="warning" */%}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to {LANGUAGE}. Do you speak {LANGUAGE}? Help us to translate
   it by sending us pull requests!
</p>
{{%/* /pageinfo */%}}
```


## Code examples

All references to code should be language independent,
and the code itself should be placed inside code tabs.

### Default Code Tabs

The Docsy code tabs look like this:

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
WebDriver driver = new ChromeDriver();
driver.get("https://selenium.dev");
driver.quit();
{{< /tab >}}
{{< tab header="Python" >}}
driver = webdriver.Chrome()
driver.get("http://selenium.dev")
driver.quit()
{{< /tab >}}
{{< tab header="CSharp" >}}
var driver = new ChromeDriver();
driver.Navigate().GoToUrl("https://selenium.dev");
driver.Quit();
{{< /tab >}}
{{< tab header="Ruby" >}}
driver = Selenium::WebDriver.for :chrome
driver.get 'https://selenium.dev'
driver.quit
{{< /tab >}}
{{< tab header="JavaScript" >}}
let driver = await new Builder().forBrowser('chrome').build();
await driver.get('https://selenium.dev');
await driver.quit();
{{< /tab >}}
{{< tab header="Kotlin" >}}
val driver = ChromeDriver()
driver.get("https://selenium.dev")
driver.quit()
}
{{< /tab >}}
{{< /tabpane >}}

To generate the above tabs, this is what you need to write.
Note that the tabpane includes `langEqualsHeader=true`.
This auto-formats the code in each tab to match the header name.

    {{</* tabpane langEqualsHeader=true */>}}
      {{</* tab header="Java" */>}}
        WebDriver driver = new ChromeDriver();
        driver.get("https://selenium.dev");
        driver.quit();
      {{</* /tab */>}}
      {{</* tab header="Python" */>}}
        driver = webdriver.Chrome()
        driver.get("http://selenium.dev")
        driver.quit()
      {{</* /tab */>}}
      {{</* tab header="CSharp" */>}}
        var driver = new ChromeDriver();
        driver.Navigate().GoToUrl("https://selenium.dev");
        driver.Quit();
      {{</* /tab */>}}
      {{</* tab header="Ruby" */>}}
        driver = Selenium::WebDriver.for :chrome
        driver.get 'https://selenium.dev'
        driver.quit
      {{</* /tab */>}}
      {{</* tab header="JavaScript" */>}}
        let driver = await new Builder().forBrowser('chrome').build();
        await driver.get('https://selenium.dev');
        await driver.quit();
      {{</* /tab */>}}
      {{</* tab header="Kotlin" */>}}
        val driver = ChromeDriver()
        driver.get("https://selenium.dev")
        driver.quit()
      }
      {{</* /tab */>}}
    {{</* /tabpane */>}}

### Disabling Code Block

If you want your example to include both text and code, you
need to disable the default of everything being put in a code block

Maybe you want something like this:

{{< tabpane disableCodeBlock=true >}}
{{< tab header="Java" >}}
1. Start the driver
  ```java
    WebDriver driver = new ChromeDriver();
  ```
2. Navigate to a page
  ```java
  driver.get("https://selenium.dev");
  ```
3. Quit the driver
  ```java
  driver.quit();
  ``` 
{{< /tab >}}
{{< /tabpane >}}

For this you need to use `disableCodeBlock=true` instead of `langEqualsHeader=true`

You need to specify which parts are code and which are not yourself now, like this:

    {{</* tabpane disableCodeBlock=true */>}}
    {{</* tab header="Java" */>}}
    1. Start the driver
      ```java
        WebDriver driver = new ChromeDriver();
      ```
    2. Navigate to a page
      ```java
      driver.get("https://selenium.dev");
      ```
    3. Quit the driver
      ```java
      driver.quit();
      ``` 
    {{</* /tab */>}}
    < ... >
    {{</* /tabpane */>}}

### Code Comments

Minimize code comments because they are difficult to translate.
Further, try to avoid over-explaining each line of code unless it is
directly related to the page you are writing.
