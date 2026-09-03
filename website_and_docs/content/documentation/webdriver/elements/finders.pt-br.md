---
title: "Encontrando Elementos Web"
linkTitle: "Finders"
weight: 2
needsTranslation: true
aliases: [
"/documentation/pt-br/webdriver/locating_elements/",
"/pt-br/documentation/webdriver/locating_elements/"
]
description: >
  Localizando elementos com base nos valores providenciados pelo localizador.
---
Um dos aspectos mais fundamentais do uso do Selenium é obter referências de elementos para trabalhar.
O Selenium oferece várias [estratégias de localizador]({{< ref "locators.md" >}}) para identificar exclusivamente um elemento.
Há muitas maneiras de usar os localizadores em cenários complexos. Para os propósitos desta documentação,
use a [página de teste de localizadores do Selenium](https://www.selenium.dev/selenium/web/locators_tests/locators.html).

## Primeiro Elemento correspondente
Muitos localizadores irão corresponder a vários elementos na página.
O método de elemento de localização singular retornará uma referência ao
primeiro elemento encontrado dentro de um determinado contexto.

### Avaliando o DOM inteiro
Quando o metodo find element é chamado na instância do driver, ele
retorna uma referência ao primeiro elemento no DOM que corresponde ao localizador fornecido.
Esse valor pode ser guardado e usado para ações futuras do elemento. Na página de teste de localizadores do Selenium, existem
dois elementos com o nome de classe `information`, então este método retorna o primeiro campo de texto.

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
  {{< tab header="Java" >}}
driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html");
WebElement firstInput = driver.findElement(By.className("information"));
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L7-L8">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/locators_tests/locators.html");
var firstInput = driver.FindElement(By.ClassName("information"));
  {{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L10-L11" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
await driver.get('https://www.selenium.dev/selenium/web/locators_tests/locators.html');
const firstInput = await driver.findElement(By.className('information'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html")
val firstInput: WebElement = driver.findElement(By.className("information"))
  {{< /tab >}}
{{< /tabpane >}}


### Avaliando um subconjunto do DOM
Ao em vez de tentar encontrar um localizador unico no DOM inteiro, normalmente é útil restringir a busca ao escopo de outro elemento
já localizado.

Uma possível solução seria localizar um ancestral do elemento desejado, então invoque o find element nesse objeto:

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
  {{< tab header="Java" >}}
driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html");
WebElement form = driver.findElement(By.tagName("form"));
WebElement input = form.findElement(By.className("information"));
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L14-L16">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/locators_tests/locators.html");
IWebElement form = driver.FindElement(By.TagName("form"));
IWebElement input = form.FindElement(By.ClassName("information"));
  {{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L17-L19" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
await driver.get('https://www.selenium.dev/selenium/web/locators_tests/locators.html');
const form = await driver.findElement(By.tagName('form'));
const input = await form.findElement(By.className('information'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html")
val form = driver.findElement(By.tagName("form"))
val input = form.findElement(By.className("information"))
  {{< /tab >}}
{{< /tabpane >}}

{{% pageinfo color="info" %}}
**Java e C#**<br>
As classes `WebDriver`, `WebElement` e `ShadowRoot` todas implementam o `SearchContext` interface, que é
considerada uma _role-based interface_(interface baseada em função). As interfaces baseadas em função permitem determinar se uma determinada
implementação de driver suporta um recurso específico. Essas interfaces são claramente definidas e tentam
aderir a ter apenas um único papel de responsabilidade.
{{% /pageinfo %}}

### Evaluating the Shadow DOM

The Shadow DOM is an encapsulated DOM tree hidden inside an element. 
With the release of v96 in Chromium Browsers, Selenium can now allow you to access this tree 
with easy-to-use shadow root methods. NOTE: These methods require Selenium 4.0 or greater.

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
{{< tab header="Java" >}}
WebElement shadowHost = driver.findElement(By.cssSelector("#shadow_host"));
SearchContext shadowRoot = shadowHost.getShadowRoot();
WebElement shadowContent = shadowRoot.findElement(By.cssSelector("#shadow_content"));
{{< /tab >}}
{{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L39-L42">}}
{{< /tab >}}
{{< tab header="CSharp" >}}
var shadowHost = _driver.FindElement(By.CssSelector("#shadow_host"));
var shadowRoot = shadowHost.GetShadowRoot();
var shadowContent = shadowRoot.FindElement(By.CssSelector("#shadow_content"));
{{< /tab >}}
{{< tab header="Ruby" >}}
shadow_host = @driver.find_element(css: '#shadow_host')
shadow_root = shadow_host.shadow_root
shadow_content = shadow_root.find_element(css: '#shadow_content')
{{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" text=true >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Localizador otimizado
Uma pesquisa aninhada pode não ser a estratégia de localização mais eficaz, pois requer dois
comandos separados a serem emitidos para o navegador.

Para melhorar um pouco o desempenho, podemos usar CSS ou XPath para encontrar esse elemento com um único comando.
Veja as [sugestões de estratégia do localizador]({{< ref "/documentation/test_practices/encouraged/locators" >}}) na nossa sessão de
[Práticas de teste incentivadas]({{< ref "/documentation/test_practices/encouraged" >}}).

Para esse exemplo, utilizaremos um seletor CSS:

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
  {{< tab header="Java" >}}
driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html");
WebElement input = driver.findElement(By.cssSelector("form .information"));
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L22-L23">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/locators_tests/locators.html");
var input = driver.FindElement(By.CssSelector("form .information"));
  {{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L25-L26" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
await driver.get('https://www.selenium.dev/selenium/web/locators_tests/locators.html');
const input = await driver.findElement(By.css('form .information'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html")
val input = driver.findElement(By.cssSelector("form .information"))
  {{< /tab >}}
{{< /tabpane >}}


## Todos os elementos correspondentes
Existem vários casos de uso para a necessidade de obter referências a todos os elementos que correspondem a um localizador, em vez
do que apenas o primeiro. Os métodos plurais find elements retornam uma coleção de referências de elementos.
Se não houver correspondências, uma lista vazia será retornada. Nesse caso,
referências a todos os elementos input serão devolvidas em uma coleção.

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
  {{< tab header="Java" >}}
driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html");
List<WebElement> inputs = driver.findElements(By.tagName("input"));
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L29-L30">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/locators_tests/locators.html");
IReadOnlyList<IWebElement> inputs = driver.FindElements(By.TagName("input"));
  {{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L32-L33" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
await driver.get('https://www.selenium.dev/selenium/web/locators_tests/locators.html');
const inputs = await driver.findElements(By.tagName('input'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html")
val inputs: List<WebElement> = driver.findElements(By.tagName("input"))
  {{< /tab >}}
{{< /tabpane >}}

### Obter Elemento
Muitas vezes você obterá uma coleção de elementos, mas quer trabalhar apenas com um elemento específico, o que significa que você
precisa iterar sobre a coleção e identificar o que você deseja.


{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
  {{< tab header="Java" >}}
driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html");
List<WebElement> elements = driver.findElements(By.tagName("p"));

for (WebElement element : elements) {
    System.out.println("Paragraph text:" + element.getText());
}
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L51-L53">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Firefox;
using System.Collections.Generic;

namespace FindElementsExample {
 class FindElementsExample {
  public static void Main(string[] args) {
   IWebDriver driver = new FirefoxDriver();
   try {
    // Navegar até a URL
    driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/locators_tests/locators.html");

    // Obtém todos os elementos disponiveis com o nome da tag 'p'
    IList < IWebElement > elements = driver.FindElements(By.TagName("p"));
    foreach(IWebElement e in elements) {
     System.Console.WriteLine(e.Text);
    }

   } finally {
    driver.Quit();
   }
  }
 }
}
  {{< /tab >}}
   {{< tab header="Ruby" text=true >}}
   {{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L40-L42" >}}
   {{< /tab >}}
  {{< tab header="JavaScript" >}}
const {Builder, By} = require('selenium-webdriver');
(async function example() {
    let driver = await new Builder().forBrowser('firefox').build();
    try {
        // Navegar até a URL
        await driver.get('https://www.selenium.dev/selenium/web/locators_tests/locators.html');

        // Obtém todos os elementos disponiveis com o nome da tag 'p'
        let elements = await driver.findElements(By.css('p'));
        for(let e of elements) {
            console.log(await e.getText());
        }
    }
    finally {
        await driver.quit();
    }
})();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
import org.openqa.selenium.By
import org.openqa.selenium.firefox.FirefoxDriver

fun main() {
    val driver = FirefoxDriver()
    try {
        driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html")
        // Obtém todos os elementos disponiveis com o nome da tag 'p'
        val elements = driver.findElements(By.tagName("p"))
        for (element in elements) {
            println("Paragraph text:" + element.text)
        }
    } finally {
        driver.quit()
    }
}
  {{< /tab >}}
{{< /tabpane >}}

## Localizar Elementos em um Elemento

Ele é usado para localizar a lista de WebElements filhos correspondentes dentro do contexto do elemento pai.
Para realizar isso, o WebElement pai é encadeado com o 'findElements' para acessar seus elementos filhos.

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
  {{< tab header="Java" >}}
  import org.openqa.selenium.By;
  import org.openqa.selenium.WebDriver;
  import org.openqa.selenium.WebElement;
  import org.openqa.selenium.chrome.ChromeDriver;
  import java.util.List;

  public class findElementsFromElement {
      public static void main(String[] args) {
          WebDriver driver = new ChromeDriver();
          try {
              driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html");

              // Obtém o elemento form
              WebElement element = driver.findElement(By.tagName("form"));

              // Obtém todos os elementos input dentro do form
              List<WebElement> elements = element.findElements(By.tagName("input"));
              for (WebElement e : elements) {
                  System.out.println(e.getAttribute("value"));
              }
          } finally {
              driver.quit();
          }
      }
  }
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L61-L64">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using System.Collections.Generic;

namespace FindElementsFromElement {
 class FindElementsFromElement {
  public static void Main(string[] args) {
   IWebDriver driver = new ChromeDriver();
   try {
    driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/locators_tests/locators.html");

    // Obtém o elemento form
    IWebElement element = driver.FindElement(By.TagName("form"));

    // Obtém todos os elementos input dentro do form
    IList < IWebElement > elements = element.FindElements(By.TagName("input"));
    foreach(IWebElement e in elements) {
     System.Console.WriteLine(e.GetAttribute("value"));
    }
   } finally {
    driver.Quit();
   }
  }
 }
}
  {{< /tab >}}
   {{< tab header="Ruby" text=true >}}
   {{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L48-L51" >}}
   {{< /tab >}}
  {{< tab header="JavaScript" >}}
  const {Builder, By} = require('selenium-webdriver');

  (async function example() {
      let driver = new Builder()
          .forBrowser('chrome')
          .build();

      await driver.get('https://www.selenium.dev/selenium/web/locators_tests/locators.html');

      //  Obtém o elemento form
      let element = driver.findElement(By.css("form"));

      // Obtém todos os elementos input dentro do form
      let elements = await element.findElements(By.css("input"));
      for(let e of elements) {
          console.log(await e.getAttribute("value"));
      }
  })();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  import org.openqa.selenium.By
  import org.openqa.selenium.chrome.ChromeDriver

  fun main() {
      val driver = ChromeDriver()
      try {
          driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html")

           // Obtém o elemento form
          val element = driver.findElement(By.tagName("form"))

          // Obtém todos os elementos input dentro do form
          val elements = element.findElements(By.tagName("input"))
          for (e in elements) {
              println(e.getAttribute("value"))
          }
      } finally {
          driver.quit()
      }
  }
  {{< /tab >}}
{{< /tabpane >}}

## Obter elemento ativo

Ele é usado para rastrear (ou) encontrar um elemento DOM que tem o foco no contexto de navegação atual.

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
  {{< tab header="Java" >}}
  import org.openqa.selenium.*;
  import org.openqa.selenium.chrome.ChromeDriver;

  public class activeElementTest {
    public static void main(String[] args) {
      WebDriver driver = new ChromeDriver();
      try {
        driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html");
        driver.findElement(By.cssSelector("#fname")).sendKeys("webElement");

        // Obter atributo do elemento atualmente ativo
        String attr = driver.switchTo().activeElement().getAttribute("name");
        System.out.println(attr);
      } finally {
        driver.quit();
      }
    }
  }
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L72-L73">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
    using OpenQA.Selenium;
    using OpenQA.Selenium.Chrome;

    namespace ActiveElement {
     class ActiveElement {
      public static void Main(string[] args) {
       IWebDriver driver = new ChromeDriver();
       try {
        // Navegar até a URL
        driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/locators_tests/locators.html");
        driver.FindElement(By.CssSelector("#fname")).SendKeys("webElement");

        // Obter atributo do elemento atualmente ativo
        string attr = driver.SwitchTo().ActiveElement().GetAttribute("name");
        System.Console.WriteLine(attr);
       } finally {
        driver.Quit();
       }
      }
     }
    }
  {{< /tab >}}
  {{< tab header="Ruby" text=true >}}
  {{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L58-L60" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
  const {Builder, By} = require('selenium-webdriver');

  (async function example() {
      let driver = await new Builder().forBrowser('chrome').build();
      await driver.get('https://www.selenium.dev/selenium/web/locators_tests/locators.html');
      await driver.findElement(By.css('#fname')).sendKeys("webElement");

      // Obter atributo do elemento atualmente ativo
      let attr = await driver.switchTo().activeElement().getAttribute("name");
      console.log(`${attr}`)
  })();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  import org.openqa.selenium.By
  import org.openqa.selenium.chrome.ChromeDriver

  fun main() {
      val driver = ChromeDriver()
      try {
          driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html")
          driver.findElement(By.cssSelector("#fname")).sendKeys("webElement")

          // Obter atributo do elemento atualmente ativo
          val attr = driver.switchTo().activeElement().getAttribute("name")
          print(attr)
      } finally {
          driver.quit()
      }
  }
  {{< /tab >}}
{{< /tabpane >}}


