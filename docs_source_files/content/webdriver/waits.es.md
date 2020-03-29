---
title: "Esperas"
weight: 4
---

Generalmente se puede decir que WebDriver posee una API de bloqueo. 
Porque es una biblioteca fuera-de-proceso que _instruye_ al navegador 
qué hacer, y debido a que la plataforma web tiene una naturaleza 
intrínsecamente asíncrona, WebDriver no rastrea el estado activo y en 
tiempo real del DOM. Esto viene con algunos desafíos que discutiremos 
aquí. 

Por experiencia la mayoría de las intermitencias que surgen del uso de 
Selenium y WebDriver están conectadas a condiciones de carrera que 
ocurren entre el navegador y las instrucciones del usuario. Un 
ejemplo podría ser que el usuario indique al navegador que navegue a 
una página, luego cuando intentas encontrar un elemento 
obtienes el error **no existe tal elemento**. 

Considera el siguiente documento:

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

Las instrucciones de WebDriver pueden parecer lo 
suficientemente inocentes:

{{< code-tab >}}
  {{< code-panel language="java" >}}
driver.get("file:///race_condition.html");
WebElement element = driver.findElement(By.tagName("p"));
assertEquals(element.getText(), "Hello from JavaScript!");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
driver.navigate("file:///race_condition.html")
el = driver.find_element_by_tag_name("p")
assert el.text == "Hello from JavaScript!"
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
driver.Navigate().GoToUrl("file:///race_condition.html");
IWebElement element = driver.FindElement(By.TagName("p"));
assertEquals(element.Text, "Hello from JavaScript!");
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :firefox
begin
  # Navega a la URL
  driver.get 'file:///race_condition.html'

  # Obtén y almacena el texto del párrafo
  search_form = driver.find_element(:css,'p').text

  "Hello from JavaScript!".eql? search_form
ensure
  driver.quit
end
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
await driver.get('file:///race_condition.html');
const element = driver.findElement(By.css('p'));
assert.strictEqual(await element.getText(), 'Hello from JavaScript!');
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
driver.get("file:///race_condition.html")
val element = driver.findElement(By.tagName("p"))
assert(element.text == "Hello from JavaScript!")
  {{< / code-panel >}}
{{< / code-tab >}}

El problema aquí es que la 
[estrategia de carga de página]({{<ref "/webdriver/page_loading_strategy.es.md">}})
predeterminada utilizada en WebDriver escucha a que el `document.readyState`
cambie a `"complete"` antes de retornar de la llamada a _navigate_.
Debido a que el elemento `p` se agregó _después_ de que el 
documento haya cargado por completo, este script de WebDriver
 _podría_ ser intermitente.
"Podría" ser intermitente porque no se pueden hacer garantías
sobre elementos o eventos que se disparan de forma asíncrona
sin esperar —o bloquear— explícitamente esos eventos.

Afortunadamente, utilizando el conjunto normal de instrucciones
disponibles en la interfaz 
[_WebElement_]({{<ref "/webdriver/web_element.es.md">}}) 
tales como —_WebElement.click_ y _WebElement.sendKeys_— 
que son garantizados para ser síncronos, esto es que las llamadas 
a funciones no retornaran (o el _callback_ no se activará en los
lenguajes de estilo _callback_) hasta que el comando se haya completado 
en el navegador. Las API avanzadas de interacción del usuario,
[_Keyboard_]({{<ref "/webdriver/keyboard.es.md">}})
y [_Mouse_]({{<ref "/support_packages/mouse_and_keyboard_actions_in_detail.es.md">}}),
son excepciones ya que están explícitamente pensadas como
comandos asíncronos "Haz lo que digo".

Esperar es hacer que la ejecución automatizada de la tarea 
transcurra una cierta cantidad de tiempo antes de continuar 
con el siguiente paso.

Para superar el problema de las condiciones de carrera.
entre el navegador y tu script de WebDriver, 
la mayoría de los clientes de Selenium se entregan con un paquete _wait_.
Al emplear una espera, está utilizando lo que comúnmente se conoce
como una [_explicit wait_](#explicit-wait).


## Explicit wait

Las _esperas explícitas_ están disponibles para los clientes de
lenguajes imperativos y procedimentales de Selenium.
Permiten que tu código detenga la ejecución del programa, 
o congelar el hilo, hasta que la _condición_ que le pases se resuelva.
La condición se llama con cierta frecuencia, hasta que transcurra 
el tiempo de espera. 
Esto significa que mientras la condición retorne un 
valor falso, seguirá intentando y esperando. 

Dado que las esperas explícitas te permiten esperar a que ocurra una 
condición, hacen una buena combinación para sincronizar el estado 
entre el navegador y su DOM, y tu script de WebDriver. 

Para remediar nuestro conjunto anterior de instrucciones con errores, 
podríamos esperar a que la llamada _findElement_ espere hasta que 
el elemento agregado dinámicamente desde el script se haya agregado
al DOM:

{{< code-tab >}}
  {{< code-panel language="java" >}}
WebDriver driver = new ChromeDriver();
driver.get("https://google.com/ncr");
driver.findElement(By.name("q")).sendKeys("cheese" + Keys.ENTER);
// Inicializa y espera hasta que se haga clic en el element(link): tiempo de espera en 10 segundos
WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.elementToBeClickable(By.xpath("//a/h3")));
// Imprime en pantalla el primer resultado
System.out.println(firstResult.getText());
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium.webdriver.support.ui import WebDriverWait
def document_initialised(driver):
    return driver.execute_script("return initialised")

driver.navigate("file:///race_condition.html")
WebDriverWait(driver).until(document_initialised)
el = driver.find_element_by_tag_name("p")
assert el.text == "Hello from JavaScript!"
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
driver = new ChromeDriver();
driver.Url = "https://www.google.com/ncr";
driver.FindElement(By.Name("q")).SendKeys("cheese" + Keys.Enter);
            
WebDriverWait wait = new WebDriverWait(driver, TimeSpan.FromSeconds(10));
IWebElement firstResult = wait.Until(e => e.FindElement(By.XPath("//a/h3")));

Console.WriteLine(firstResult.Text);
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
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
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const documentInitialised = () =>
    driver.executeScript('return initialised');

await driver.get('file:///race_condition.html');
await driver.wait(() => documentInitialised(), 10000);
const element = driver.findElement(By.css('p'));
assert.strictEqual(await element.getText(), 'Hello from JavaScript!');
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
driver.get("https://google.com/ncr")
driver.findElement(By.name("q")).sendKeys("cheese" + Keys.ENTER)
// Inicializa y espera hasta que se haga clic en el element(link): tiempo de espera en 10 segundos
val firstResult = WebDriverWait(driver, Duration.ofSeconds(10))
      .until(ExpectedConditions.elementToBeClickable(By.xpath("//a/h3")))
// Imprime en pantalla el primer resultado
println(firstResult.text)
  {{< / code-panel >}}
{{< / code-tab >}}

Pasamos la _condición_ como referencia de la función
que _wait_ ejecutará repetidamente hasta que su valor
de retorno sea verdadero. Un valor de retorno "verdadero" 
es todo lo que se evalúa como booleano verdadero 
en el lenguaje en cuestión, como una cadena de caracteres, 
un número, un booleano, un objeto (incluido un _WebElement_),
o una secuencia o lista poblada (no vacía).
Eso significa que una _lista vacía_ se evalúa como falsa.
Cuando la condición es verdadera y se cancela
el bloqueo de espera, el valor de retorno de la condición
se convierte en el valor de retorno de la espera.

Con este conocimiento, y debido a que la utilidad de espera
ignora por defecto los errores _no such element_, 
podemos refactorizar nuestras instrucciones para hacerlas
mas concisas:

{{< code-tab >}}
    {{< code-panel language="java" >}}
WebElement foo = new WebDriverWait(driver, Duration.ofSeconds(3))
            .until(driver -> driver.findElement(By.name("q")));
assertEquals(foo.getText(), "Hello from JavaScript!"); 
    {{< / code-panel >}} 
{{< code-panel language="python" >}}
from selenium.webdriver.support.ui import WebDriverWait

driver.navigate("file:///race_condition.html")
el = WebDriverWait(driver).until(lambda d: d.find_element_by_tag_name("p"))
assert el.text == "Hello from JavaScript!"
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
 using (var driver = new FirefoxDriver())
 {
      var foo = new WebDriverWait(driver, TimeSpan.FromSeconds(3))
                      .Until(drv => drv.FindElement(By.Name("q")));
      Debug.Assert(foo.Text.Equals("Hello from JavaScript!"));
 }
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
  driver.get 'file:///race_condition.html'
  wait = Selenium::WebDriver::Wait.new(:timeout => 10)
  ele = wait.until { driver.find_element(css: 'p')}
  foo = ele.text
  assert_match foo, 'Hello from JavaScript'
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
let ele = await driver.wait(until.elementLocated(By.css('p')),10000);
let foo = await ele.getText();
assert(foo == "Hello from JavaScript");
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
driver.get("file:///race_condition.html")
val ele = WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.presenceOfElementLocated(By.tagName("p")))
assert(ele.text == "Hello from JavaScript!")
  {{< / code-panel >}}
{{< / code-tab >}}

En este ejemplo, pasamos una función anónima
(pero también podríamos definirlo explícitamente como lo hicimos
anteriormente para que pueda reutilizarse).
El primer y único argumento que se pasa a nuestra condición
siempre es una referencia a nuestro objeto controlador, _WebDriver_
(llamado `d` en el ejemplo).
En un entorno multiproceso, debes tener cuidado
operando en la referencia del controlador pasada a la condición
en lugar de la referencia al controlador en el ámbito externo.

Debido a que la espera se tragará los errores _no such element_
que se generan cuando no se encuentra el elemento,
la condición volverá a intentar hasta que se encuentre el elemento.
Luego tomará el valor de retorno, un _WebElement_,
y lo pasara nuevamente a nuestro script.

Si la condición falla,
p.ej. nunca se alcanza un valor de retorno verdadero para la condición,
la espera arrojará/generará un error/excepción llamado _timeout error_.


### Options

La condición de espera se puede personalizar 
para satisfacer tus necesidades.
A veces es innecesario esperar completamente el tiempo
de espera predeterminado,
ya que la penalización por no alcanzar una condición exitosa
puede ser costosa.

La espera te permite pasar un argumento para anular
el tiempo de espera:

{{< code-tab >}}
  {{< code-panel language="java" >}}
new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//a/h3")));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
WebDriverWait(driver, timeout=3).until(some_condition)
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
new WebDriverWait(driver, TimeSpan.FromSeconds(3)).Until(ExpectedConditions.ElementToBeClickable(By.XPath("//a/h3")));  
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
wait = Selenium::WebDriver::Wait.new(:timeout => 10)

wait.until { driver.find_element(:id, 'message').displayed? }
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
  await driver.wait(until.elementLocated(By.id('foo')), 30000);
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//a/h3")))
  {{< / code-panel >}}
{{< / code-tab >}}

### Expected conditions

Porque es una ocurrencia bastante común
tener que sincronizar el DOM y tus instrucciones,
La mayoría de los clientes también vienen con un conjunto de
_condiciones esperadas_ predefinidas.
Como podría ser obvio por el nombre,
son condiciones predefinidas para operaciones frecuentes de espera.

Las condiciones disponibles varían en las diferentes
librerias enlace de los lenguajes,
pero esta es una lista no exhaustiva de algunos:

<!-- TODO(ato): Fill in -->
* alert is present (_la alerta esta presente_)
* element exists (_el elemento existe_)
* element is visible (_el elemento es visible_)
* title contains (_el titulo contiene_)
* title is (_el titulo es_)
* element staleness (_estancamiento del elemento_)
* visible text (_texto visible_)

Puedes consultar la documentación de las API para las librerias 
de enlace de cada cliente
para encontrar una lista exhaustiva de las _expected conditions_:

* La clase de Java [org.openqa.selenium.support.ui.ExpectedConditions](//seleniumhq.github.io/selenium/docs/api/java/org/openqa/selenium/support/ui/ExpectedConditions.html)
* La clase dePython [selenium.webdriver.support.expected_conditions](//seleniumhq.github.io/selenium/docs/api/py/webdriver_support/selenium.webdriver.support.expected_conditions.html?highlight=expected)
* En .NET el tipo [OpenQA.Selenium.Support.UI.ExpectedConditions](//seleniumhq.github.io/selenium/docs/api/dotnet/html/T_OpenQA_Selenium_Support_UI_ExpectedConditions.htm)


## Implicit wait

Hay un segundo tipo de espera que es distinto de
[esperas explícitas](#explicit-wait) llamada _implicit wait_.
Al esperar implícitamente, WebDriver sondea el DOM
por una cierta duración al intentar encontrar cualquier elemento.
Esto puede ser útil cuando ciertos elementos en la página web
no están disponibles de inmediato y necesitan algo de tiempo
para cargarse.

Esperar implicitamente que aparezcan elementos está deshabilitado
de forma predeterminada y deberá habilitarse manualmente por sesión.
Mezclar [esperas explícitas](#explicit-wait) y esperas implícitas
causará consecuencias no deseadas, es decir, esperara el máximo de
tiempo incluso si el elemento está disponible o la condición
es verdadera.

*Advertencia:*
No mezcles esperas implícitas y explícitas.
Hacerlo puede causar tiempos de espera impredecibles.
Por ejemplo, establecer una espera implícita de 10 segundos
y una espera explícita de 15 segundos
podría provocar que ocurra un timeout después de 20 segundos.

Una espera implícita es decirle a WebDriver que sondee el DOM
durante un cierto período de tiempo al intentar encontrar un elemento
o elementos
si no están disponibles de inmediato.
La configuración predeterminada es 0, lo que significa deshabilitado.
Una vez establecido, la espera implícita se establece por el tiempo
de vida de la sesión.

{{< code-tab >}}
  {{< code-panel language="java" >}}
WebDriver driver = new FirefoxDriver();
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
driver.get("http://somedomain/url_that_delays_loading");
WebElement myDynamicElement = driver.findElement(By.id("myDynamicElement"));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
driver = Firefox()
driver.implicitly_wait(10)
driver.get("http://somedomain/url_that_delays_loading")
my_dynamic_element = driver.find_element_by_id("myDynamicElement")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
IWebDriver driver = new ChromeDriver();
driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromSeconds(10);
driver.Url = "http://somedomain/url_that_delays_loading";
IWebElement dynamicElement = driver.FindElement(By.Name("dynamicElement"));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :firefox
driver.manage.timeouts.implicit_wait = 10

begin
  driver.get 'http://somedomain/url_that_delays_loading'
  search_form = driver.find_element(:id,'dynamic_element')
ensure
  driver.quit
end
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
(async function(){

// Aplica un tiempo de espera timeout por 10 segundos
await driver.manage().setTimeouts( { implicit: 10000 } );

// Navega a la URL
await driver.get('http://somedomain/url_that_delays_loading');

let webElement = driver.findElement(By.id("myDynamicElement"));

}()); 
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val driver = FirefoxDriver()
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
driver.get("http://somedomain/url_that_delays_loading")
val myDynamicElement = driver.findElement(By.id("myDynamicElement"))
  {{< / code-panel >}}
{{< / code-tab >}}

## FluentWait

La instancia de FluentWait define la cantidad máxima de tiempo
para esperar por una condición,
así como la frecuencia con la que verificar dicha condición.

Los usuarios pueden configurar la espera para ignorar tipos específicos
de excepciones mientras esperan,
como `NoSuchElementException` cuando buscan un elemento en la página.

{{< code-tab >}}
  {{< code-panel language="java" >}}
// Esperando 30 segundos a que un elemento este presente en la página, verificando
// si está presente una vez cada 5 segundos

Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
  .withTimeout(Duration.ofSeconds(30))
  .pollingEvery(Duration.ofSeconds(5))
  .ignoring(NoSuchElementException.class);

WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
  public WebElement apply(WebDriver driver) {
    return driver.findElement(By.id("foo"));
  }
});
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
driver = Firefox()
driver.get("http://somedomain/url_that_delays_loading")
wait = WebDriverWait(driver, 10, poll_frequency=1, ignored_exceptions=[ElementNotVisibleException, ElementNotSelectableException])
element = wait.until(EC.element_to_be_clickable((By.XPATH, "//div")))
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using (var driver = new FirefoxDriver())
{
  WebDriverWait wait = new WebDriverWait(driver, timeout: TimeSpan.FromSeconds(30))
  {
      PollingInterval = TimeSpan.FromSeconds(5),
  };
  wait.IgnoreExceptionTypes(typeof(NoSuchElementException));

  var foo = wait.Until(drv => drv.FindElement(By.Id("foo")));
}  
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
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
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder, until} = require('selenium-webdriver');

(async function example() {
    let driver = await new Builder().forBrowser('firefox').build();
    await driver.get('http://somedomain/url_that_delays_loading');
    // Esperando 30 segundos a que un elemento este presente en la página, chequeando
    // si está presente una vez cada 5 segundos
    let foo = await driver.wait(until.elementLocated(By.id('foo')), 30000, 'Timed out after 30 seconds', 5000);
})(); 
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val wait = FluentWait<WebDriver>(driver)
        .withTimeout(Duration.ofSeconds(30))
        .pollingEvery(Duration.ofSeconds(3))
        .ignoring(NoSuchElementException::class.java)

val foo = wait.until {it.findElement(By.id("foo")) }
  {{< / code-panel >}}
{{< / code-tab >}}

