---
title: "Esperas"
linkTitle: "Esperas"
weight: 6
aliases: ["/documentation/pt-br/webdriver/waits/"]
---

Geralmente, pode-se dizer que o WebDriver tem uma API de blocante.
Porque é uma biblioteca fora de processo que
_instrui_ ao navegador o que fazer,
e porque a plataforma web tem uma natureza intrinsecamente assíncrona,
O WebDriver não rastreia o estado ativo em tempo real do DOM.
Isso traz alguns desafios que discutiremos aqui.

Por experiência,
a maioria dos problemas intermitentes que surgem do uso de Selenium e WebDriver
estão conectados a _condições de corrida_ que ocorrem entre
o navegador e as instruções do usuário.
Um exemplo pode ser que o usuário instrui o navegador a navegar para uma página,
em seguida, obtém um erro **no such element**
ao tentar encontrar um elemento.

Considere o seguinte documento:

```html
<!doctype html>
<meta charset=utf-8>
<title>Race Condition Example</title>

<script>
  var initialised = false;
  window.addEventListener("load", function() {
    var newElement = document.createElement("p");
    newElement.textContent = "Hello from JavaScript!";
    document.body.appendChild(newElement);
    initialised = true;
  });
</script>
```

As instruções do WebDriver podem parecer inocentes:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
driver.get("file:///race_condition.html");
WebElement element = driver.findElement(By.tagName("p"));
assertEquals(element.getText(), "Hello from JavaScript!");
  {{< /tab >}}
  {{< tab header="Python" >}}
driver.navigate("file:///race_condition.html")
el = driver.find_element(By.TAG_NAME, "p")
assert el.text == "Hello from JavaScript!"
  {{< /tab >}}
  {{< tab header="CSharp" >}}
driver.Navigate().GoToUrl("file:///race_condition.html");
IWebElement element = driver.FindElement(By.TagName("p"));
assertEquals(element.Text, "Hello from JavaScript!");
  {{< /tab >}}
  {{< tab header="Ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :firefox
begin
  # Navigate to URL
  driver.get 'file:///race_condition.html'

  # Get and store Paragraph Text
  search_form = driver.find_element(:css,'p').text

  "Hello from JavaScript!".eql? search_form
ensure
  driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
await driver.get('file:///race_condition.html');
const element = driver.findElement(By.css('p'));
assert.strictEqual(await element.getText(), 'Hello from JavaScript!');
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
driver.get("file:///race_condition.html")
val element = driver.findElement(By.tagName("p"))
assert(element.text == "Hello from JavaScript!")
  {{< /tab >}}
{{< /tabpane >}}

O problema aqui é que a
[estratégia de carregamento de página padrão]({{< ref "drivers/options#pageloadstrategy" >}})
usado no WebDriver escuta o `document.readyState`
para mudar para `"complete"` antes de retornar da chamada para _navigate_.
Porque o elemento `p` é
adicionado _após_ o carregamento do documento concluído,
este script WebDriver _pode_ ser intermitente.
"Pode" ser intermitente porque nenhuma garantia pode ser feita
sobre elementos ou eventos que disparam de forma assíncrona
sem esperar explicitamente - ou bloquear - nesses eventos.

Felizmente, o conjunto normal de instruções disponível na interface
[_WebElement _]({{< ref "elements" >}}) - tal
  como _WebElement.click_ e _WebElement.sendKeys_ — são
  garantidamente síncrono,
  em que as chamadas de função não retornarão
  (ou o retorno de chamada não será acionado em linguagens de estilo de
  retorno de chamada) até que o comando seja concluído no navegador.
  As APIs avançadas de interação com o usuário,
  [_Keyboard_]({{< ref "actions_api/keyboard.md" >}})
  e [_Mouse_]({{< ref "actions_api/mouse.md">}}),
  são exceções, pois são explicitamente pretendidas como
  comandos assíncronos “faça o que eu digo”.

Esperar é fazer a execução de tarefa automatizada
esperar passar um certo tempo antes de continuar com a próxima etapa.

Para superar o problema das condições de corrida
entre o navegador e o script WebDriver,
a maioria dos clientes Selenium vem com um pacote _wait_.
Ao empregar uma espera,
você está usando o que é comumente referido
como uma [_espera explícita_](#explicit-wait).


## Espera explícita

_Esperas explícitas_ estão disponíveis para clientes Selenium
para linguagens procedurais imperativas.
Eles permitem que seu código interrompa a execução do programa,
ou congelar o tópico,
até que a _condição_ que você passe resolva.
A condição é chamada com uma certa frequência
até que o tempo limite de espera tenha decorrido.
Isso significa que, enquanto a condição retornar um valor falso,
ele continuará tentando e esperando.

Como as esperas explícitas permitem que você espere até que uma condição ocorra,
eles são adequados para sincronizar o estado entre o navegador e seu DOM,
e seu script WebDriver.

Para remediar o nosso conjunto de instruções com erros de antes,
poderíamos empregar um tempo de espera para que a chamada _findElement_
espere até que o elemento adicionado dinamicamente do script
seja adicionado ao DOM:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
WebDriver driver = new ChromeDriver();
driver.get("https://google.com/ncr");
driver.findElement(By.name("q")).sendKeys("cheese" + Keys.ENTER);
// Initialize and wait till element(link) became clickable - timeout in 10 seconds
WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.elementToBeClickable(By.xpath("//a/h3")));
// Print the first result
System.out.println(firstResult.getText());
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium.webdriver.support.wait import WebDriverWait
def document_initialised(driver):
    return driver.execute_script("return initialised")

driver.navigate("file:///race_condition.html")
WebDriverWait(driver, timeout=10).until(document_initialised)
el = driver.find_element(By.TAG_NAME, "p")
assert el.text == "Hello from JavaScript!"
  {{< /tab >}}
  {{< tab header="CSharp" >}}
driver = new ChromeDriver();
driver.Url = "https://www.google.com/ncr";
driver.FindElement(By.Name("q")).SendKeys("cheese" + Keys.Enter);

WebDriverWait wait = new WebDriverWait(driver, TimeSpan.FromSeconds(10));
IWebElement firstResult = wait.Until(e => e.FindElement(By.XPath("//a/h3")));

Console.WriteLine(firstResult.Text);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :firefox
wait = Selenium::WebDriver::Wait.new(:timeout => 10)

def document_initialised(driver)
  driver.execute_script('return initialised')
end

begin
  driver.get 'file:///race_condition.html'
  wait.until{document_initialised driver}
  search_form = driver.find_element(:css,'p').text
  "Hello from JavaScript!".eql? search_form
ensure
  driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const documentInitialised = () =>
    driver.executeScript('return initialised');

await driver.get('file:///race_condition.html');
await driver.wait(() => documentInitialised(), 10000);
const element = driver.findElement(By.css('p'));
assert.strictEqual(await element.getText(), 'Hello from JavaScript!');
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
driver.get("https://google.com/ncr")
driver.findElement(By.name("q")).sendKeys("cheese" + Keys.ENTER)
// Initialize and wait till element(link) became clickable - timeout in 10 seconds
val firstResult = WebDriverWait(driver, Duration.ofSeconds(10))
      .until(ExpectedConditions.elementToBeClickable(By.xpath("//a/h3")))
// Print the first result
println(firstResult.text)
  {{< /tab >}}
{{< /tabpane >}}

Passamos a _condição_ como uma referência de função
que o _wait_ executará repetidamente até que seu valor de retorno seja verdadeiro.
Um valor de retorno “verdadeiro” é qualquer coisa avaliada como booleana verdadeira
na linguagem em questão, como string, número, booleano,
um objeto (incluindo um _WebElement_),
ou uma sequência ou lista preenchida (não vazia).
Isso significa que uma _lista vazia_ é avaliada como falsa.
Quando a condição é verdadeira e a espera de bloqueio é abortada,
o valor de retorno da condição se torna o valor de retorno da espera.

Com este conhecimento,
e como o utilitário de espera ignora erros _no such element_ por padrão,
podemos refatorar nossas instruções para sermos mais concisos:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
WebElement foo = new WebDriverWait(driver, Duration.ofSeconds(3))
          .until(driver -> driver.findElement(By.name("q")));
assertEquals(foo.getText(), "Hello from JavaScript!");
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium.webdriver.support.wait import WebDriverWait

driver.navigate("file:///race_condition.html")
el = WebDriverWait(driver, timeout=3).until(lambda d: d.find_element(By.TAG_NAME,"p"))
assert el.text == "Hello from JavaScript!"
  {{< /tab >}}
  {{< tab header="CSharp" >}}
   using (var driver = new FirefoxDriver())
    {
        var foo = new WebDriverWait(driver, TimeSpan.FromSeconds(3))
                        .Until(drv => drv.FindElement(By.Name("q")));
        Debug.Assert(foo.Text.Equals("Hello from JavaScript!"));
    }
  {{< /tab >}}
  {{< tab header="Ruby" >}}
  driver.get 'file:///race_condition.html'
  wait = Selenium::WebDriver::Wait.new(:timeout => 10)
  ele = wait.until { driver.find_element(css: 'p')}
  foo = ele.text
  assert_match foo, 'Hello from JavaScript'
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
let ele = await driver.wait(until.elementLocated(By.css('p')),10000);
let foo = await ele.getText();
assert(foo == "Hello from JavaScript");
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
driver.get("file:///race_condition.html")
val ele = WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.presenceOfElementLocated(By.tagName("p")))
assert(ele.text == "Hello from JavaScript!")
  {{< /tab >}}
{{< /tabpane >}}

Nesse exemplo, passamos uma função anônima
(mas também podemos definá-la explicitamente, como fizemos antes,
para que possa ser reutilizado). O primeiro e único argumento que é
passado para nossa condição é sempre uma referência ao nosso objeto
driver, _WebDriver_. Em um ambiente multi-thread, você deve ter cuidado
para operar na referência do driver passada para a condição
em vez da referência ao driver no escopo externo.

Dado que a espera vai engolir erros _no such element_
que são gerados quando o elemento não é encontrado,
a condição tentará novamente até que o elemento seja encontrado.
Em seguida, ele receberá o valor de retorno, um _WebElement_,
e o passará de volta para o nosso script.

Se a condição falhar,
por exemplo um valor de retorno verdadeiro da condição nunca for
alcançado, a espera lançará/gerará um erro/exceção chamado
_timeout error_.


### Opções

A condição de espera pode ser personalizada para atender às suas
necessidades. Às vezes, é desnecessário esperar todo o tempo limite
padrão, já que a penalidade por não atingir uma condição de sucesso pode
ser cara.

A espera permite que você passe um argumento para substituir o tempo
limite:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//a/h3")));
  {{< /tab >}}
  {{< tab header="Python" >}}
WebDriverWait(driver, timeout=3).until(some_condition)
  {{< /tab >}}
  {{< tab header="CSharp" >}}
new WebDriverWait(driver, TimeSpan.FromSeconds(3)).Until(ExpectedConditions.ElementToBeClickable(By.XPath("//a/h3")));
  {{< /tab >}}
  {{< tab header="Ruby" >}}
wait = Selenium::WebDriver::Wait.new(:timeout => 10)

wait.until { driver.find_element(:id, 'message').displayed? }
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
  await driver.wait(until.elementLocated(By.id('foo')), 30000);
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//a/h3")))
  {{< /tab >}}
{{< /tabpane >}}

### Condições esperadas

Já que é uma ocorrência bastante comum
ter que sincronizar o DOM e suas instruções,
a maioria dos clientes também vem com um conjunto de _condições esperadas_ predefinidas.
Como pode ser óbvio pelo nome,
são condições predefinidas para operações de espera frequentes.

As condições disponíveis nas diferentes linguagens variam,
mas esta é uma lista não exaustiva de alguns:

<!-- TODO(ato): Fill in -->
* alert is present
* element exists
* element is visible
* title contains
* title is
* element staleness
* visible text

Você pode consultar a documentação da API para cada biblioteca de cliente
para encontrar uma lista exaustiva das condições esperadas:

* Classe Java [org.openqa.selenium.support.ui.ExpectedConditions](//seleniumhq.github.io/selenium/docs/api/java/org/openqa/selenium/support/ui/ExpectedConditions.html)
* Classe Python [selenium.webdriver.support.expected_conditions](//seleniumhq.github.io/selenium/docs/api/py/webdriver_support/selenium.webdriver.support.expected_conditions.html?highlight=expected)
* Tipo .NET [OpenQA.Selenium.Support.UI.ExpectedConditions](//seleniumhq.github.io/selenium/docs/api/dotnet/html/T_OpenQA_Selenium_Support_UI_ExpectedConditions.htm)
* JavaScript's [selenium-webdriver/lib/until](//seleniumhq.github.io/selenium/docs/api/javascript/module/selenium-webdriver/lib/until.html) module


## Espera implícita

Há um segundo tipo de espera que é diferente de
[espera explícita](#explicit-wait) chamada _espera implícita_.
Esperando implicitamente, o WebDriver pesquisa o DOM
por um certo período ao tentar encontrar _qualquer_ elemento.
Isso pode ser útil quando certos elementos da página da web
não estão disponíveis imediatamente e precisam de algum tempo para
carregar.

A espera implícita pelo aparecimento de elementos está desativada por
padrão e precisará ser habilitada manualmente por sessão.
Misturar [esperas explícitas](#explicit-wait) e esperas implícitas
irá causar consequências não intencionais, ou seja, espera dormir pelo
máximo tempo mesmo se o elemento estiver disponível ou a condição for
verdadeira.

*Atenção:*
Não misture esperas implícitas e explícitas.
Isso pode causar tempos de espera imprevisíveis.
Por exemplo, definir uma espera implícita de 10 segundos
e uma espera explícita de 15 segundos
pode causar um tempo limite após 20 segundos.

Uma espera implícita é dizer ao WebDriver para pesquisar o DOM
por um certo período de tempo ao tentar encontrar um elemento ou
elementos se não estiverem imediatamente disponíveis.
A configuração padrão é 0, o que significa desativado.
Depois de definida, a espera implícita é definida para a duração da
sessão.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
WebDriver driver = new FirefoxDriver();
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
driver.get("http://somedomain/url_that_delays_loading");
WebElement myDynamicElement = driver.findElement(By.id("myDynamicElement"));
  {{< /tab >}}
  {{< tab header="Python" >}}
driver = Firefox()
driver.implicitly_wait(10)
driver.get("http://somedomain/url_that_delays_loading")
my_dynamic_element = driver.find_element(By.ID, "myDynamicElement")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
IWebDriver driver = new ChromeDriver();
driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromSeconds(10);
driver.Url = "http://somedomain/url_that_delays_loading";
IWebElement dynamicElement = driver.FindElement(By.Name("dynamicElement"));
  {{< /tab >}}
  {{< tab header="Ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :firefox
driver.manage.timeouts.implicit_wait = 10

begin
  driver.get 'http://somedomain/url_that_delays_loading'
  search_form = driver.find_element(:id,'dynamic_element')
ensure
  driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
(async function(){

// Apply timeout for 10 seconds
await driver.manage().setTimeouts( { implicit: 10000 } );

// Navigate to url
await driver.get('http://somedomain/url_that_delays_loading');

let webElement = driver.findElement(By.id("myDynamicElement"));

}());
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val driver = FirefoxDriver()
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10))
driver.get("http://somedomain/url_that_delays_loading")
val myDynamicElement = driver.findElement(By.id("myDynamicElement"))
  {{< /tab >}}
{{< /tabpane >}}

## FluentWait

A instância FluentWait define a quantidade máxima de tempo de espera por
uma condição, bem como a frequência com que verificar a condição.

Os usuários podem configurar a espera para ignorar tipos específicos de
exceções enquanto esperam, como `NoSuchElementException` ao pesquisar um
elemento na página.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
// Waiting 30 seconds for an element to be present on the page, checking
// for its presence once every 5 seconds.
Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
  .withTimeout(Duration.ofSeconds(30))
  .pollingEvery(Duration.ofSeconds(5))
  .ignoring(NoSuchElementException.class);

WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
  public WebElement apply(WebDriver driver) {
    return driver.findElement(By.id("foo"));
  }
});
  {{< /tab >}}
  {{< tab header="Python" >}}
driver = Firefox()
driver.get("http://somedomain/url_that_delays_loading")
wait = WebDriverWait(driver, timeout=10, poll_frequency=1, ignored_exceptions=[ElementNotVisibleException, ElementNotSelectableException])
element = wait.until(EC.element_to_be_clickable((By.XPATH, "//div")))
  {{< /tab >}}
  {{< tab header="CSharp" >}}
using (var driver = new FirefoxDriver())
{
  WebDriverWait wait = new WebDriverWait(driver, timeout: TimeSpan.FromSeconds(30))
  {
      PollingInterval = TimeSpan.FromSeconds(5),
  };
  wait.IgnoreExceptionTypes(typeof(NoSuchElementException));

  var foo = wait.Until(drv => drv.FindElement(By.Id("foo")));
}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :firefox
exception = Selenium::WebDriver::Error::NoSuchElementError

begin
  driver.get 'http://somedomain/url_that_delays_loading'
  wait = Selenium::WebDriver::Wait.new(timeout: 30, interval: 5, message: 'Timed out after 30 sec', ignore: exception)
  foo = wait.until { driver.find_element(id: 'foo')}
ensure
  driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const {Builder, until} = require('selenium-webdriver');

(async function example() {
    let driver = await new Builder().forBrowser('firefox').build();
    await driver.get('http://somedomain/url_that_delays_loading');
    // Waiting 30 seconds for an element to be present on the page, checking
    // for its presence once every 5 seconds.
    let foo = await driver.wait(until.elementLocated(By.id('foo')), 30000, 'Timed out after 30 seconds', 5000);
})();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val wait = FluentWait<WebDriver>(driver)
        .withTimeout(Duration.ofSeconds(30))
        .pollingEvery(Duration.ofSeconds(3))
        .ignoring(NoSuchElementException::class.java)

val foo = wait.until {it.findElement(By.id("foo")) }
  {{< /tab >}}
{{< /tabpane >}}

