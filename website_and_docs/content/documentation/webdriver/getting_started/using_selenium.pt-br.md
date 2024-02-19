---
title: "Organizando e executando o código Selenium"
linkTitle: "Utilizando o Selenium"
weight: 10
description: >
    Escalonamento da execução do Selenium com um IDE e uma biblioteca do Test Runner
---

Se quiser executar mais do que um punhado de scripts pontuais, precisa de 
ser capaz de organizar e trabalhar com seu código. Esta página deve dar a você
ideias de como fazer coisas produtivas com seu código Selenium.

## Usos comuns

A maioria das pessoas usa o Selenium para executar testes automatizados para aplicações web, 
mas o Selenium suporta qualquer caso de uso de automação de navegador.

### Tarefas Repetitivas

Talvez seja necessário fazer login em um site e baixar algo ou enviar um formulário.
Você pode criar um script Selenium para ser executado com um serviço em horários pré-definidos.

### Web Scrapping

Está a tentar recolher dados de um site que não tem uma API? O Selenium
permitirá que você faça isso, mas certifique-se de estar familiarizado com os termos de serviço do site
termos de serviço do site, pois alguns sites não permitem isso e outros até bloqueiam o Selenium.

### Testes

Executar o Selenium para testes requer fazer asserções sobre as ações tomadas pelo Selenium.
Então uma boa biblioteca de asserções é necessária. Características adicionais para prover estrutura para testes
requerem o uso de [Test Runner] (#test-runners).


## IDEs

Independentemente de como você usa o código do Selenium, 
não será muito eficaz escrevendo ou executando-o sem um bom
ambiente de desenvolvimento integrado. Aqui estão algumas opções comuns...

- [Eclipse](https://www.eclipse.org/)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/)
- [PyCharm](https://www.jetbrains.com/pycharm/)
- [RubyMine](https://www.jetbrains.com/ruby/)
- [Rider](https://www.jetbrains.com/rider/)
- [WebStorm](https://www.jetbrains.com/webstorm/)
- [VS Code](https://code.visualstudio.com/)

## Executador de teste

Mesmo que não esteja a usar o Selenium para testes, se tiver casos de uso avançado, pode fazer
sentido usar um executor de testes para organizar melhor seu código. Ser capaz de usar hooks antes/depois 
e executar coisas em grupos ou em paralelo pode ser muito útil.

### Escolhendo
Há muitos executores de teste diferentes disponíveis.

Todos os exemplos de código nesta documentação podem ser encontrados em (ou estão sendo movidos para) nossos diretórios
que usam test runners e são executados a cada lançamento para garantir que todo o código esteja correto e atualizado.
Aqui está uma lista de executores de teste com links. O primeiro item é o que é usado por este repositório e o que
que será usado para todos os exemplos nesta página.

{{< tabpane text=true >}}
{{% tab header="Java" %}}
- [JUnit](https://junit.org/junit5/) - Uma estrutura de teste amplamente utilizada para testes Selenium baseados em Java.
- [TestNG](https://testng.org/) - Oferece recursos extras, como execução de testes paralelos e testes parametrizados.
{{% /tab %}}

{{% tab header="Python" %}}
- [pytest](https://pytest.org/) -Uma escolha preferida por muitos, graças à sua simplicidade e aos seus poderosos plugins.
- [unittest](https://docs.python.org/3/library/unittest.html) - A estrutura de testes da biblioteca padrão do Python.
{{% /tab %}}

{{% tab header="CSharp" %}}
- [NUnit](https://nunit.org/) - Um popular framework de teste unitário para .NET.
- [MS Test](https://docs.microsoft.com/en-us/visualstudio/test/getting-started-with-unit-testing?view=vs-2019) - O Framework de testes unitários da Microsoft.
{{% /tab %}}

{{% tab header="Ruby" %}}
- [RSpec](https://rspec.info/) - A biblioteca de testes mais utilizada para executar testes Selenium em Ruby.
- [Minitest](https://github.com/seattlerb/minitest) - Um framework de testes leve que vem com a biblioteca padrão do Ruby.
{{% /tab %}}

{{% tab header="JavaScript" %}}
- [Jest](https://jestjs.io/) - Principalmente conhecido como um framework de teste para React, também pode ser utilizado para testes Selenium.
- [Mocha](https://mochajs.org/) - A biblioteca JS mais comum para executar testes Selenium.
{{% /tab %}}

{{% tab header="Kotlin" %}}

{{% /tab %}}
{{< /tabpane >}}


### Instalando

Isto é muito semelhante ao que foi requerido em [Install a Selenium Library]({{< ref "install_library.md" >}}).
Este código está apenas a mostrar exemplos do que está a ser usado no nosso projeto de Exemplos de Documentação.

{{< tabpane text=true >}}
{{% tab header="Java" %}}

**Maven**

**Gradle**

{{% /tab %}}
{{% tab header="Python" %}}

Para usá-lo em um projeto, adicione-o ao arquivo `requirements.txt`:

{{% /tab %}}
{{% tab header="CSharp" %}}
in the project's `csproj` especifique a dependência como `PackageReference` em `ItemGroup`:

{{% /tab %}}
{{% tab header="Ruby" %}}

Add to project's gemfile

{{% /tab %}}
{{% tab header="JavaScript" %}}
In your project's `package.json`, adicionar requisito às `dependências`:

{{% /tab %}}
{{< tab header="Kotlin" >}}
{{< /tab >}}
{{< /tabpane >}}

### Afirmar

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< badge-code >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Configuarar e Desconfigurar

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< badge-code >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Executando

{{< tabpane text=true >}}
{{% tab header="Java" %}}
### Maven

```shell
mvn clean test
```

### Gradle

```shell
gradle clean test
```

{{% /tab %}}
{{% tab header="Python" %}}
{{< badge-code >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{% tab header="JavaScript" %}}
```shell
mocha runningTests.spec.js
```
{{% /tab %}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Examplos

In [First script]({{< ref "first_script.md" >}}), we saw each of the components of a Selenium script.
Here's an example of that code using a test runner:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/UsingSeleniumTest.java" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/getting_started/using_selenium_tests.py" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/GettingStarted/UsingSeleniumTest.cs" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/getting_started/using_selenium_spec.rb" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/getting_started/runningTests.spec.js" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## Proximos passos

Pegue no que aprendeu e desenvolva o seu código Selenium!

À medida que encontrar mais funcionalidades de que precisa, leia o resto da nossa
[documentação do WebDriver]({{< ref "/documentation/webdriver/" >}}).
