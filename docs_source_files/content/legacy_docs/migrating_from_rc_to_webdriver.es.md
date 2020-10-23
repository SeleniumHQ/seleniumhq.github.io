---
title: "Migrando de RC a WebDriver"
weight: 2
---

## Cómo migrar a Selenium WebDriver

Una pregunta común al adoptar Selenium 2 es qué seria
lo correcto al agregar nuevas pruebas a un conjunto existente
de pruebas?. Los usuarios que son nuevos al framework pueden
comenzar utilizando las nuevas API de WebDriver para
escribir sus pruebas. ¿Pero qué pasa con los usuarios que
ya tienen suites de pruebas existentes? Esta guía es
diseñada para demostrar cómo migrar tus pruebas existentes
a las nuevas APIs, permitiendo que todas las pruebas nuevas
se escriban usando las nuevas características ofrecidas por
WebDriver.

El método presentado aquí describe una migración gradual al
WebDriver API sin necesidad de volver a trabajar todo en un
solo esfuerzo masivo. Esto significa que te puedes permitir más
tiempo para migrar tus pruebas existentes, lo que puede
facilitarte decidir dónde dirigir tu esfuerzo.

Esta guía está escrita usando Java, porque tiene el mejor
soporte para hacer la migración. A medida que
proporcionemos mejores herramientas para otros lenguajes,
esta guía se ampliará para incluir esos lenguajes.

## Por qué migrar a WebDriver

Mover un conjunto de pruebas de una API a otra requiere una
enorme cantidad de esfuerzo. ¿Por qué tu y tu equipo
considerarían hacer este movimiento? Estas son algunas
razones por las que deberían considerar migrar sus pruebas
de Selenium para usar WebDriver.

* API más pequeña y compacta. La API de WebDriver está más
orientada a objetos que la API original de Selenium RC.
Esto puede hacer que sea más fácil trabajar con él.
* Mejor emulación de las interacciones del usuario. Donde sea
posible, WebDriver hace uso de eventos nativos para
interactuar con una página web. Esto imita más de cerca la
forma en que tus usuarios trabajan con tu sitio y tus
aplicaciones. Adicionalmente, WebDriver ofrece las API
avanzadas de interacciones de usuario que te permiten modelar
interacciones complejas con tu sitio.
* Soporte por proveedores de navegador. Opera, Mozilla y Google son
todos participantes activos en el desarrollo de WebDriver,
y cada uno tiene ingenieros trabajando para mejorar el
framework. A menudo, esto significa que el soporte para
WebDriver está integrado en el navegador: tus pruebas se
ejecutan tan rápido y tan estables como sea posible.

## Antes de empezar

Para que el proceso de migración sea lo menos doloroso
posible, asegúrate de que todas tus pruebas se
ejecuten correctamente con la última versión de Selenium.
Esto puede sonar obvio, ¡pero es mejor decirlo!

## Empezando

El primer paso al comenzar la migración es cambiar la forma
de obtener tu instancia de Selenium. Cuando se usa Selenium
RC, esto se hace así:

```java
Selenium selenium = new DefaultSelenium("localhost", 4444, "*firefox", "http://www.yoursite.com");
selenium.start();
```

Esto debería ser reemplazado de la siguiente manera:

```java
WebDriver driver = new FirefoxDriver();
Selenium selenium = new WebDriverBackedSelenium(driver, "http://www.yoursite.com");
```

## Próximos pasos

Una vez que tus pruebas se ejecutan sin errores, la
siguiente etapa es migrar el código de prueba real para
usar las API de WebDriver. Dependiendo de qué tan bien
abstraído este tu código, esto podría ser un proceso corto o
largo. En cualquier caso, el enfoque es el mismo y se puede
resumir simplemente: modifique el código para usar la nueva
API cuando vayas a editarlo.

Si necesitas extraer la implementación subyacente de
WebDriver de en la instancia de Selenium, simplemente
puedes hacer un casteo a WrapsDriver:

```java
WebDriver driver = ((WrapsDriver) selenium).getWrappedDriver();
```

Esto te permite continuar pasando la instancia de Selenium
normalmente, pero desenvuelve la instancia de
WebDriver según sea necesario.

En algún momento, tu código base utilizará
principalmente las API más nuevas. En este punto, puedes
voltear la relación, utilizando WebDriver para instanciar una
instancia de Selenium según sea necesario:

```java
Selenium selenium = new WebDriverBackedSelenium(driver, baseUrl);
```

## Problemas Comunes

Afortunadamente, no eres la primera persona en pasar por esta migración,
Aquí hay algunos problemas comunes que otros han encontrado y cómo resolverlos.

### Hacer clic y escribir es más completo

Un patrón común en una prueba de Selenium RC es ver algo como:

```java
selenium.type("name", "exciting tex");
selenium.keyDown("name", "t");
selenium.keyPress("name", "t");
selenium.keyUp("name", "t");
```

Esto se basa en el hecho de que "type" simplemente
reemplaza el contenido del elemento identificado sin
disparar todos los eventos que normalmente serian
disparados si un usuario interactúa con la página. Las
invocaciones directas finales de "key*" hace que los
manejadores JS se activen como se esperaba.

Cuando se usa WebDriverBackedSelenium, el resultado de
completar el formulario en el campo sería "exciting texttt":
¡no es lo que esperarías! La razón de esto es
que WebDriver emula con mayor precisión el comportamiento
del usuario y, por lo tanto, estaría disparando
eventos todo el tiempo.

Este mismo hecho a veces puede provocar que la carga de una
página se dispare antes de lo que lo haría en una
prueba de Selenium 1. Se puede decir que esto ha sucedido si
WebDriver lanza un "StaleElementException".

### WaitForPageToLoad retorna demasiado pronto

Descubrir cuándo se completa la carga de una página es un
negocio complicado. Acaso nos referimos a "cuando se dispara el
evento de carga", "cuando todas las solicitudes de AJAX
están completas", "cuando no hay tráfico de red","cuando
document.readyState ha cambiado" o algo completamente diferente?

WebDriver intenta simular el comportamiento original de
Selenium, pero esto no siempre funciona perfectamente por
varias razones. La razón más común es que es difícil
saber la diferencia entre una carga de página que aún no ha
comenzado, y un carga de página completada entre llamadas
de métodos. Esto a veces significa que el control se ha retornado
a tu prueba antes de que la página finalice (¡o incluso
comience!) su carga.

La solución a esto es esperar algo específico. Comúnmente,
esto podría ser el elemento con el que deseas interactuar,
o que alguna variable de Javascript  se establezca en un
valor específico. Un ejemplo sería:

```java
Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(30));
WebElement element= wait.until(visibilityOfElementLocated(By.id("some_id")));
```

Donde "visibilityOfElementLocated" se implementa como:

```java
public ExpectedCondition<WebElement> visibilityOfElementLocated(final By locator) {
  return new ExpectedCondition<WebElement>() {
    public WebElement apply(WebDriver driver) {
      WebElement toReturn = driver.findElement(locator);
      if (toReturn.isDisplayed()) {
        return toReturn;
      }
      return null;
    }
  };
}
```

Esto puede parecer complejo, pero es casi todo el codigo boiler-plate.
Lo único interesante es que la "ExpectedCondition"
se evaluará repetidamente hasta que el método "apply" retorne algo
que no sea "nulo" ni Boolean.FALSE.

Por supuesto, agregar todas estas llamadas de "espera"
puede saturar tu código. Si ese es el caso, y tus
necesidades son simples, considera usar las esperas
implícitas:

```java
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
```

Al hacer esto, cada vez que se localiza un elemento,
si el elemento no está presente, la localización se vuelve
a intentar hasta que esté presente o hasta que hayan
transcurrido 30 segundos.

### Encontrar Por Xpath O CSS Selectores No Siempre Funciona, Pero Lo Hace en Selenium 1

En Selenium 1, era común que xpath usara una librería
incluida en lugar de utilizar las capacidades del navegador en sí.
WebDriver siempre usará los métodos nativos del navegador a
menos que no haya alternativa. Eso significa que expresiones
xpath complejas pueden romperse en algunos navegadores.

Los selectores CSS en Selenium 1 se implementaron
utilizando la librería Sizzle. Esta implementa un
superconjunto de la especificación CSS Selector, y no
siempre está claro dónde has cruzado la línea. Si estás
utilizando WebDriverBackedSelenium y utilizas un localizador
Sizzle en lugar de un selector CSS para encontrar
elementos, se registrara una advertencia en la consola.
Vale la pena tomarse el tiempo para encontrar estas ocurrencias,
particularmente si las pruebas fallan por no poder
encontrar elementos.

### No hay Browserbot

Selenium RC se basó en Selenium Core, por lo tanto,
cuando ejecutó Javascript, pudo acceder a partes de Selenium
Core para facilitar las cosas. Como WebDriver no se basa en
Selenium Core, esto ya no es posible. ¿Cómo puedes saber si
está usando Selenium Core? ¡Sencillo! Solo mira si
tus llamadas "getEval" o similares están usando "selenium"
o "browserbot" en el Javascript evaluado.

Es posible que esté utilizando el browserbot para
obtener un identificador de la ventana actual o el documento
de la prueba. Afortunadamente, WebDriver siempre evalúa JS
en el contexto de la ventana actual, por lo que puede usar
"window" o "document" directamente.

Alternativamente, puedes estar usando el browserbot
para localizar elementos. En WebDriver, la forma para
hacer esto es localizar primero el elemento, y luego
pasarlo como argumento al Javascript. Así:

```java
String name = selenium.getEval(
    "selenium.browserbot.findElement('id=foo', browserbot.getCurrentWindow()).tagName");
```

resulta en:

```java
WebElement element = driver.findElement(By.id("foo"));
String name = (String) ((JavascriptExecutor) driver).executeScript(
    "return arguments[0].tagName", element);
```

Observa cómo la variable "element" pasada aparece como el primer elemento
en la matriz de "arguments" estándar de JS.

### Ejecutar Javascript no retorna nada

JavascriptExecutor de WebDriver envolverá todo JS y lo evaluará como una expresión anónima. Esto significa que debes usar la tecla "return"

```java
String title = selenium.getEval("browserbot.getCurrentWindow().document.title");
```

resulta en:

```java
((JavascriptExecutor) driver).executeScript("return document.title;");
```
