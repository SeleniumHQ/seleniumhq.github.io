# O Projeto Selenium de Automação de Navegadores

URL: https://www.selenium.dev/pt-br/documentation/

---

Selenium é um projeto que abrange uma variedade de ferramentas e bibliotecas
que permitem e suportam a automação de navegadores da web.

Ele fornece extensões para emular a interação do usuário com os navegadores,
um servidor de distribuição para escalonar a alocação do navegador,
e a infraestrutura para implementações da [Especificação W3C WebDriver](//www.w3.org/TR/webdriver/)
que permite escrever código intercambiável para todos os principais navegadores da web.

Este projeto é possível graças a colaboradores voluntários
que dedicam milhares de horas de seu próprio tempo,
e disponibilizaram o código-fonte [disponível gratuitamente](https://www.selenium.dev/pt-br/documentation/about/copyright/index.md#license)
para qualquer um usar, aproveitar e melhorar.

Selenium reúne criadores de navegadores, engenheiros e entusiastas
para promover uma discussão aberta sobre a automação da plataforma da web.
O projeto organiza [uma conferência anual](//seleniumconf.com/)
para ensinar e nutrir a comunidade.

No núcleo do Selenium está [WebDriver](https://www.selenium.dev/pt-br/documentation/webdriver/index.md),
uma interface para escrever conjuntos de instruções que podem ser executados alternadamente em muitos
navegadores. Aqui está uma das instruções mais simples que você pode fazer:

**Java**

```java
package dev.selenium.hello;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloSelenium {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.get("https://selenium.dev");

        driver.quit();
    }
}
```

**Python**

```python
from selenium import webdriver

driver = webdriver.Chrome()

driver.get("http://selenium.dev")

driver.quit()
```

**CSharp**

```csharp
using OpenQA.Selenium.Chrome;

namespace SeleniumDocs.Hello;

public static class HelloSelenium
{
    public static void Main()
    {
        var driver = new ChromeDriver();

        driver.Navigate().GoToUrl("https://selenium.dev");

        driver.Quit();
    }
}
```

**Ruby**

```ruby
# frozen_string_literal: true

require 'selenium-webdriver'

driver = Selenium::WebDriver.for :chrome

driver.get 'https://selenium.dev'

driver.quit
```

**JavaScript**

```javascript
const {Builder, Browser} = require('selenium-webdriver');

(async function helloSelenium() {
  let driver = await new Builder().forBrowser(Browser.CHROME).build();

  await driver.get('https://selenium.dev');

  await driver.quit();
})();
```

**Kotlin**

```kotlin
package dev.selenium.hello

import org.openqa.selenium.chrome.ChromeDriver

fun main() {
    val driver = ChromeDriver()

    driver.get("https://selenium.dev")

    driver.quit()
}
```

Consulte a [Visão Geral](https://www.selenium.dev/pt-br/documentation/overview/index.md) para verificar os diferentes componentes do projeto
e decidir se o Selenium é a ferramenta certa para você.

Você deve continuar no Guia de [Introdução](https://www.selenium.dev/pt-br/documentation/webdriver/getting_started/index.md)
para entender como instalar o Selenium e usá-lo com sucesso como uma 
ferramenta de automação de teste e dimensionar testes simples como esse para serem executados em ambientes grandes 
e distribuídos em vários navegadores e em vários sistemas operacionais diferentes.

---

Section pages:

- [Resumo](https://www.selenium.dev/pt-br/documentation/overview/index.md): Será Selenium a ferramenta para você? Veja um resumo dos componentes do projecto.
- [WebDriver](https://www.selenium.dev/pt-br/documentation/webdriver/index.md): WebDriver manipula um navegador nativamente; aprenda mais sobre isso.
- [Gerenciador do Selenium](https://www.selenium.dev/pt-br/documentation/selenium_manager/index.md): O Selenium Manager é uma ferramenta de linha de comando implementada em Rust que fornece gerenciamento automatizado de drivers e navegadores para o Selenium. As bibliotecas do Selenium usam essa ferramenta por padrão, portanto, você não precisa baixá-la, adicionar nada ao seu código ou realizar qualquer outra ação para utilizá-la.
- [Grid](https://www.selenium.dev/pt-br/documentation/grid/index.md): Pretende executar testes em paralelo em várias máquinas? Então a Grid é para si.
- [Selenium IDE](https://www.selenium.dev/pt-br/documentation/ide/index.md): Selenium IDE é uma aplicação autónoma que grava e reproduz uma acção do utilizador.
- [Diretrizes e recomendações](https://www.selenium.dev/pt-br/documentation/test_practices/index.md): Guias e recomendações ao preparar soluções de testes com o projecto Selenium.
- [Legado](https://www.selenium.dev/pt-br/documentation/legacy/index.md): Nesta seção você pode encontrar toda a documentação relacionada aos componentes legados do Selenium. Isso deve ser mantido puramente por razões históricas e não como um incentivo para o uso obsoleto componentes.
- [Sobre esta documentação](https://www.selenium.dev/pt-br/documentation/about/index.md)
