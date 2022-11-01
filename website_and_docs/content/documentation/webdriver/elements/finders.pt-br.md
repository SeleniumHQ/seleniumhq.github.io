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
vamos considerar este trecho de HTML:


```html
<ol id="vegetables">
 <li class="potatoes">…
 <li class="onions">…
 <li class="tomatoes"><span>O tomate é um vegetal</span>…
</ol>
<ul id="fruits">
  <li class="bananas">…
  <li class="apples">…
  <li class="tomatoes"><span>O tomate é uma fruta</span>…
</ul>
```

## Primeiro Elemento correspondente
Muitos localizadores irão corresponder a vários elementos na página.
O método de elemento de localização singular retornará uma referência ao
primeiro elemento encontrado dentro de um determinado contexto.

### Avaliando o DOM inteiro
Quando o metodo find element é chamado na instância do driver, ele
retorna uma referência ao primeiro elemento no DOM que corresponde ao localizador fornecido.
Esse valor pode ser guardado e usado para ações futuras do elemento. Em nosso exemplo HTML acima, existem
dois elementos que têm um nome de classe de "tomatoes" então este método retornará o elemento na lista "vegetables".

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
WebElement vegetable = driver.findElement(By.className("tomatoes"));
  {{< /tab >}}
  {{< tab header="Python" >}}
vegetable = driver.find_element(By.CLASS_NAME, "tomatoes")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
var vegetable = driver.FindElement(By.ClassName("tomatoes"));
  {{< /tab >}}
  {{< tab header="Ruby" >}}
vegetable = driver.find_element(class: 'tomatoes')
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const vegetable = driver.findElement(By.className('tomatoes'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val vegetable: WebElement = driver.findElement(By.className("tomatoes"))
  {{< /tab >}}
{{< /tabpane >}}


### Avaliando um subconjunto do DOM
Ao em vez de tentar encontrar um localizador unico no DOM inteiro, normalmente é útil restringir a busca ao escopo de outro elemento
já localizado. No exemplo acima existem dois elementos com um nome de classe de "tomatoes" e
é um pouco mais desafiador obter a referência para o segundo.

Uma possível solução seria localizar um elemento com um atributo único que seja um ancestral do elemento desejado e não um
ancestral do elemento indesejado, então invoque o find element nesse objeto:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
WebElement fruits = driver.findElement(By.id("fruits"));
WebElement fruit = fruits.findElement(By.className("tomatoes"));
  {{< /tab >}}
  {{< tab header="Python" >}}
fruits = driver.find_element(By.ID, "fruits")
fruit = fruits.find_element(By.CLASS_NAME,"tomatoes")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
IWebElement fruits = driver.FindElement(By.Id("fruits"));
IWebElement fruit = fruits.FindElement(By.ClassName("tomatoes"));
  {{< /tab >}}
  {{< tab header="Ruby" >}}
fruits = driver.find_element(id: 'fruits')
fruit = fruits.find_element(class: 'tomatoes')
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const fruits = driver.findElement(By.id('fruits'));
const fruit = fruits.findElement(By.className('tomatoes'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val fruits = driver.findElement(By.id("fruits"))
val fruit = fruits.findElement(By.className("tomatoes"))
  {{< /tab >}}
{{< /tabpane >}}

{{% pageinfo color="info" %}}
**Java e C#**<br>
As classes `WebDriver`, `WebElement` e `ShadowRoot` todas implementam o `SearchContext` interface, que é
considerada uma _role-based interface_(interface baseada em função). As interfaces baseadas em função permitem determinar se uma determinada
implementação de driver suporta um recurso específico. Essas interfaces são claramente definidas e tentam
aderir a ter apenas um único papel de responsabilidade.
{{% /pageinfo %}}

### Localizador otimizado
Uma pesquisa aninhada pode não ser a estratégia de localização mais eficaz, pois requer dois
comandos separados a serem emitidos para o navegador.

Para melhorar um pouco o desempenho, podemos usar CSS ou XPath para encontrar esse elemento com um único comando.
Veja as [sugestões de estratégia do localizador]({{< ref "/documentation/test_practices/encouraged/locators" >}}) na nossa sessão de
[Práticas de teste incentivadas]({{< ref "/documentation/test_practices/encouraged" >}}).

Para esse exemplo, utilizaremos o CSS Selector:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
WebElement fruit = driver.findElement(By.cssSelector("#fruits .tomatoes"));
  {{< /tab >}}
  {{< tab header="Python" >}}
fruit = driver.find_element(By.CSS_SELECTOR,"#fruits .tomatoes")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
var fruit = driver.FindElement(By.CssSelector("#fruits .tomatoes"));
  {{< /tab >}}
  {{< tab header="Ruby" >}}
fruit = driver.find_element(css: '#fruits .tomatoes')
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const fruit = driver.findElement(By.css('#fruits .tomatoes'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val fruit = driver.findElement(By.cssSelector("#fruits .tomatoes"))
  {{< /tab >}}
{{< /tabpane >}}


## Todos os elementos correspondentes
Existem vários casos de uso para a necessidade de obter referências a todos os elementos que correspondem a um localizador, em vez
do que apenas o primeiro. Os métodos plurais find elements retornam uma coleção de referências de elementos.
Se não houver correspondências, uma lista vazia será retornada. Nesse caso,
referências a todos os itens da lista de frutas e vegetais serão devolvidas em uma coleção.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
List<WebElement> plants = driver.findElements(By.tagName("li"));
  {{< /tab >}}
  {{< tab header="Python" >}}
plants = driver.find_elements(By.TAG_NAME, "li")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
IReadOnlyList<IWebElement> plants = driver.FindElements(By.TagName("li"));
  {{< /tab >}}
  {{< tab header="Ruby" >}}
plants = driver.find_elements(tag_name: 'li')
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const plants = driver.findElements(By.tagName('li'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val plants: List<WebElement> = driver.findElements(By.tagName("li"))
  {{< /tab >}}
{{< /tabpane >}}

### Obter Elemento
Muitas vezes você obterá uma coleção de elementos, mas quer trabalhar apenas com um elemento específico, o que significa que você
precisa iterar sobre a coleção e identificar o que você deseja.


{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
List<WebElement> elements = driver.findElements(By.tagName("li"));

for (WebElement element : elements) {
    System.out.println("Paragraph text:" + element.getText());
}
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver
from selenium.webdriver.common.by import By

driver = webdriver.Firefox()

    # Navegar até a URL
driver.get("https://www.example.com")

    # Obtém todos os elementos disponiveis com o nome da tag 'p'
elements = driver.find_elements(By.TAG_NAME, 'p')

for e in elements:
    print(e.text)
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
    driver.Navigate().GoToUrl("https://example.com");

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
  {{< tab header="Ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :firefox
begin
     # Navegar até a URL
  driver.get 'https://www.example.com'

     # Obtém todos os elementos disponiveis com o nome da tag 'p'
  elements = driver.find_elements(:tag_name,'p')

  elements.each { |e|
    puts e.text
  }
ensure
  driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const {Builder, By} = require('selenium-webdriver');
(async function example() {
    let driver = await new Builder().forBrowser('firefox').build();
    try {
        // Navegar até a URL
        await driver.get('https://www.example.com');

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
        driver.get("https://example.com")
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
              driver.get("https://example.com");

              // Obtém o elemento com o nome da tag 'div'
              WebElement element = driver.findElement(By.tagName("div"));

              // Obtém todos os elementos disponiveis com o nome da tag 'p'
              List<WebElement> elements = element.findElements(By.tagName("p"));
              for (WebElement e : elements) {
                  System.out.println(e.getText());
              }
          } finally {
              driver.quit();
          }
      }
  }
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver
from selenium.webdriver.common.by import By

driver = webdriver.Chrome()
driver.get("https://www.example.com")

    # Obtém o elemento com o nome da tag 'div'
element = driver.find_element(By.TAG_NAME, 'div')

    # Obtém todos os elementos disponíveis com o nome da tag 'p'
elements = element.find_elements(By.TAG_NAME, 'p')
for e in elements:
    print(e.text)
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
    driver.Navigate().GoToUrl("https://example.com");

    // Obtém o elemento com o nome da tag 'div'
    IWebElement element = driver.FindElement(By.TagName("div"));

    // Obtém todos os elementos disponíveis com o nome da tag 'p'
    IList < IWebElement > elements = element.FindElements(By.TagName("p"));
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
  {{< tab header="Ruby" >}}
  require 'selenium-webdriver'
  driver = Selenium::WebDriver.for :chrome
  begin
    # Navegar até a URL
    driver.get 'https://www.example.com'

    # Obtém o elemento com o nome da tag 'div'
    element = driver.find_element(:tag_name,'div')

    # Obtém todos os elementos disponíveis com o nome da tag 'p'
    elements = element.find_elements(:tag_name,'p')

    elements.each { |e|
      puts e.text
    }
  ensure
    driver.quit
  end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
  const {Builder, By} = require('selenium-webdriver');

  (async function example() {
      let driver = new Builder()
          .forBrowser('chrome')
          .build();

      await driver.get('https://www.example.com');

      //  Obtém o elemento com o nome da tag 'div'
      let element = driver.findElement(By.css("div"));

      // Obtém todos os elementos disponíveis com o nome da tag 'p'
      let elements = await element.findElements(By.css("p"));
      for(let e of elements) {
          console.log(await e.getText());
      }
  })();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  import org.openqa.selenium.By
  import org.openqa.selenium.chrome.ChromeDriver

  fun main() {
      val driver = ChromeDriver()
      try {
          driver.get("https://example.com")

           // Obtém o elemento com o nome da tag 'div'
          val element = driver.findElement(By.tagName("div"))

          // Obtém todos os elementos disponíveis com o nome da tag 'p'
          val elements = element.findElements(By.tagName("p"))
          for (e in elements) {
              println(e.text)
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
  {{< tab header="Java" >}}
  import org.openqa.selenium.*;
  import org.openqa.selenium.chrome.ChromeDriver;

  public class activeElementTest {
    public static void main(String[] args) {
      WebDriver driver = new ChromeDriver();
      try {
        driver.get("http://www.google.com");
        driver.findElement(By.cssSelector("[name='q']")).sendKeys("webElement");

        // Obter atributo do elemento atualmente ativo
        String attr = driver.switchTo().activeElement().getAttribute("title");
        System.out.println(attr);
      } finally {
        driver.quit();
      }
    }
  }
  {{< /tab >}}
  {{< tab header="Python" >}}
  from selenium import webdriver
  from selenium.webdriver.common.by import By

  driver = webdriver.Chrome()
  driver.get("https://www.google.com")
  driver.find_element(By.CSS_SELECTOR, '[name="q"]').send_keys("webElement")

    # Obter atributo do elemento atualmente ativo
  attr = driver.switch_to.active_element.get_attribute("title")
  print(attr)
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
        driver.Navigate().GoToUrl("https://www.google.com");
        driver.FindElement(By.CssSelector("[name='q']")).SendKeys("webElement");

        // Obter atributo do elemento atualmente ativo
        string attr = driver.SwitchTo().ActiveElement().GetAttribute("title");
        System.Console.WriteLine(attr);
       } finally {
        driver.Quit();
       }
      }
     }
    }
  {{< /tab >}}
  {{< tab header="Ruby" >}}
  require 'selenium-webdriver'
  driver = Selenium::WebDriver.for :chrome
  begin
    driver.get 'https://www.google.com'
    driver.find_element(css: '[name="q"]').send_keys('webElement')

    # Obter atributo do elemento atualmente ativo
    attr = driver.switch_to.active_element.attribute('title')
    puts attr
  ensure
    driver.quit
  end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
  const {Builder, By} = require('selenium-webdriver');

  (async function example() {
      let driver = await new Builder().forBrowser('chrome').build();
      await driver.get('https://www.google.com');
      await  driver.findElement(By.css('[name="q"]')).sendKeys("webElement");

      // Obter atributo do elemento atualmente ativo
      let attr = await driver.switchTo().activeElement().getAttribute("title");
      console.log(`${attr}`)
  })();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  import org.openqa.selenium.By
  import org.openqa.selenium.chrome.ChromeDriver

  fun main() {
      val driver = ChromeDriver()
      try {
          driver.get("https://www.google.com")
          driver.findElement(By.cssSelector("[name='q']")).sendKeys("webElement")

          // Obter atributo do elemento atualmente ativo
          val attr = driver.switchTo().activeElement().getAttribute("title")
          print(attr)
      } finally {
          driver.quit()
      }
  }
  {{< /tab >}}
{{< /tabpane >}}


