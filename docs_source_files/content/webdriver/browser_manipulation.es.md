---
title: "Manipulación de Navegadores"
weight: 3
---

<!-- #codeExamples -->
<!-- Remember to cover profile and extensions here -->

{{% notice info %}}
<i class="fas fa-language"></i> Page being translated from
English to Spanish. Do you speak Spanish? Help us to translate
it by sending us pull requests!
{{% /notice %}}


## Ruby

Ruby no esta instalado por defecto en Windows. Puedes descargar la ultima
[versión](//rubyinstaller.org/downloads) y ejecutar el instalador. Puedes dejar
todos los parámetros de configuración con los valores por defecto excepto el
parámetro _Add Ruby executables to your PATH_ de la pantalla de
_Installation Destination and Optional Tasks_.
Para manejar cualquier navegador tienes que instalar la gema de Ruby
`selenium-webdriver`. Para instalarla abre una consola de comando y ejecuta el
siguiente comando.


```shell
gem install selenium-webdriver
```

O si usas [Bundler](//bundler.io) añade esta linea al Gemfile de tu aplicación:

```ruby
gem "selenium-webdriver"
```

Y después ejecuta el siguiente comando en el terminal:

```shell
bundle install
```


## Internet Explorer

Internet Explorer viene instalado por defecto en Windows, por lo que no es
necesario realizar ninguna instalación. Para manejar Internet Explorer en Windows
tienes que descargar la ultima versión del
[driver de Internet Explorer](https://selenium.dev/downloads/) y añadirlo a un
directorio que este incluido en el `PATH` del sistema. Para saber que directorios
están incluidos en el `PATH` escribe el comando `echo %PATH%` en el terminal.

```bat
$ echo %PATH%
C:\Ruby200\bin;C:\WINDOWS\system32;C:\WINDOWS;C:\WINDOWS\System32\Wbem
```

Por ejemplo el directorio `C:\Ruby200\bin` parece un buen lugar. Descomprime el
archivo `IEDriverServer` y mueve el ejecutable `IEDriverServer.exe` a esta carpeta.

Las lineas que se muestran a continuación deberían abrir una nueva ventana de
Internet Explorer.

```ruby
require "selenium-webdriver"
driver = Selenium::WebDriver.for :internet_explorer
```

## Manejo de los navegadores web

### Navegar hacia

La primera cosa que querrás hacer después de levantar un navegador es abrir una
pagina web. Esto se puede lograr en una sola linea:

{{< code-tab >}}
  {{< code-panel language="java" >}}
//Forma adecuada
driver.get("https://selenium.dev");

//Forma extensa
driver.navigate().to("https://selenium.dev");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
driver.get("https://selenium.dev")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
driver.Navigate().GoToUrl(@"https://selenium.dev");
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
//Forma adecuada
driver.get 'https://selenium.dev'

//Forma extensa
driver.navigate.to 'https://selenium.dev'
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
await driver.get('https://selenium.dev');
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
//Forma adecuada
driver.get("https://selenium.dev")

//Forma extensa
driver.navigate().to("https://selenium.dev")
  {{< / code-panel >}}
{{< / code-tab >}}

### Obtener la URL actual

Puedes leer la URL actual desde la barra de direcciones del navegador usando:

{{< code-tab >}}
  {{< code-panel language="java" >}}driver.getCurrentUrl();{{< / code-panel >}}
  {{< code-panel language="python" >}}driver.current_url{{< / code-panel >}}
  {{< code-panel language="csharp" >}}driver.Url;{{< / code-panel >}}
  {{< code-panel language="ruby" >}}driver.current_url{{< / code-panel >}}
  {{< code-panel language="javascript" >}}await driver.getCurrentUrl();{{< / code-panel >}}
  {{< code-panel language="kotlin" >}}driver.currentUrl{{< / code-panel >}}
{{< / code-tab >}}

### Retroceder

Presionando el botón de retroceder del navegador:

{{< code-tab >}}
  {{< code-panel language="java" >}}driver.navigate().back();{{< / code-panel >}}
  {{< code-panel language="python" >}}driver.back(){{< / code-panel >}}
  {{< code-panel language="csharp" >}}driver.Navigate().Back();{{< / code-panel >}}
  {{< code-panel language="ruby" >}}driver.navigate.back{{< / code-panel >}}
  {{< code-panel language="javascript" >}}await driver.navigate().back();{{< / code-panel >}}
  {{< code-panel language="kotlin" >}}driver.navigate().back() {{< / code-panel >}}
{{< / code-tab >}}

### Avanzar

Presionando el botón de avanzar del navegador:

{{< code-tab >}}
  {{< code-panel language="java" >}}driver.navigate().forward();{{< / code-panel >}}
  {{< code-panel language="python" >}}driver.forward(){{< / code-panel >}}
  {{< code-panel language="csharp" >}}driver.Navigate().Forward();{{< / code-panel >}}
  {{< code-panel language="ruby" >}}driver.navigate.forward{{< / code-panel >}}
  {{< code-panel language="javascript" >}}await driver.navigate().forward();{{< / code-panel >}}
  {{< code-panel language="kotlin" >}}driver.navigate().forward(){{< / code-panel >}}
{{< / code-tab >}}

### Actualizar

Recargando la pagina actual:

{{< code-tab >}}
  {{< code-panel language="java" >}}driver.navigate().refresh();{{< / code-panel >}}
  {{< code-panel language="python" >}}driver.refresh(){{< / code-panel >}}
  {{< code-panel language="csharp" >}}driver.Navigate().Refresh();{{< / code-panel >}}
  {{< code-panel language="ruby" >}}driver.navigate.refresh{{< / code-panel >}}
  {{< code-panel language="javascript" >}}await driver.navigate().refresh();{{< / code-panel >}}
  {{< code-panel language="kotlin" >}}driver.navigate().refresh(){{< / code-panel >}}
{{< / code-tab >}}

### Obtener el título

Puedes leer el título de la pagina actual desde el navegador:

{{< code-tab >}}
  {{< code-panel language="java" >}}driver.getTitle();{{< / code-panel >}}
  {{< code-panel language="python" >}}driver.title{{< / code-panel >}}
  {{< code-panel language="csharp" >}}driver.Title;{{< / code-panel >}}
  {{< code-panel language="ruby" >}}driver.title{{< / code-panel >}}
  {{< code-panel language="javascript" >}}await driver.getTitle();{{< / code-panel >}}
  {{< code-panel language="kotlin" >}}driver.title{{< / code-panel >}}
{{< / code-tab >}}


## Ventanas y pestañas

### Obtener el controlador de ventanas

El WebDriver no hace distinción entre ventanas y pestañas.  Si tu sitio web abre
una nueva pestaña o ventana, Selenium te permitirá trabajar con ella usando un
controlador de ventanas. Cada ventana tiene un identificador único el cual
persiste en una sola sesión. Puedes obtener el controlador de la ventana de la
ventana actual usando:

{{< code-tab >}}
  {{< code-panel language="java" >}}driver.getWindowHandle();{{< / code-panel >}}
  {{< code-panel language="python" >}}driver.current_window_handle{{< / code-panel >}}
  {{< code-panel language="csharp" >}}driver.CurrentWindowHandle;{{< / code-panel >}}
  {{< code-panel language="ruby" >}}driver.window_handle{{< / code-panel >}}
  {{< code-panel language="javascript" >}}await driver.getWindowHandle();{{< / code-panel >}}
  {{< code-panel language="kotlin" >}}driver.windowHandle{{< / code-panel >}}
{{< / code-tab >}}

### Cambiar entre ventanas o pestañas

Haciendo clic en un enlace el cual abre
<a href="https://seleniumhq.github.io" target="_blank"> una nueva ventana</a>
cambiará el foco a la nueva ventana o pestaña en la pantalla, pero el WebDriver
no sabrá que ventana el sistema operativo considera activa. Para trabajar con la
nueva ventana necesitaras cambiar a ella. Si solo tienes dos ventanas o pestañas
abiertas y sabes en cual empezaste, por proceso de eliminación, puede recorrer
las ventanas o pestañas que ve el WebDriver y cambiar a una de ellas que no sea
la original.

Sin embargo Selenium 4 proporciona una nueva API
<a href="https://selenium.dev/documentation/es/webdriver/browser_manipulation/#create-new-window-or-new-tab-and-switch"> NewWindow </a>
la cual crea una nueva pestaña o ventana y automáticamente cambia a ella.

{{< code-tab >}}
  {{< code-panel language="java" >}}
//Almacena el ID de la ventana original
String originalWindow = driver.getWindowHandle();

//Comprueba que no existen otras ventanas abiertas previamente
assert driver.getWindowHandles().size() == 1;

//Haz clic en el enlace el cual abre una nueva ventana
driver.findElement(By.linkText("new window")).click();

//Espera a la nueva ventana o pestaña
wait.until(numberOfWindowsToBe(2));

//Recorrelas hasta encontrar el controlador de la nueva ventana
for (String windowHandle : driver.getWindowHandles()) {
    if(!originalWindow.contentEquals(windowHandle)) {
        driver.switchTo().window(windowHandle);
        break;
    }
}

//Espera a que la nueva ventana cargue su contenido
wait.until(titleIs("Selenium documentation"));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium import webdriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

# Instancia el driver
with webdriver.Firefox() as driver:
    # Abre la URL
    driver.get("https://seleniumhq.github.io")

    # Configura una espera para despues
    wait = WebDriverWait(driver, 10)

    # Almacena el ID de la ventana original
    original_window = driver.current_window_handle

    # Comprueba que no existen otras ventanas abiertas previamente
    assert len(driver.window_handles) == 1

    # Haz clic en el enlace el cual abre una nueva ventana
    driver.find_element(By.LINK_TEXT, "new window").click()

    # Espera a la nueva ventana o pestaña
    wait.until(EC.number_of_windows_to_be(2))

    # Recorrelas hasta encontrar el controlador de la nueva ventana
    for window_handle in driver.window_handles:
        if window_handle != original_window:
            driver.switch_to.window(window_handle)
            break

    # Espera a que la nueva ventana carge su contenido
    wait.until(EC.title_is("SeleniumHQ Browser Automation"))
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
//Almacena el ID de la ventana original
string originalWindow = driver.CurrentWindowHandle;

//Comprueba que no existen otras ventanas abiertas previamente
Assert.AreEqual(driver.WindowHandles.Count, 1);

//Haz clic en el enlace el cual abre una nueva ventana
driver.FindElement(By.LinkText("new window")).Click();

//Espera a la nueva ventana o pestaña
wait.Until(wd => wd.WindowHandles.Count == 2);

//Recorrelas hasta encontrar el controlador de la nueva ventana
foreach(string window in driver.WindowHandles)
{
    if(originalWindow != window)
    {
        driver.SwitchTo().Window(window);
        break;
    }
}
//Espera a que la nueva ventana cargue su contenido
wait.Until(wd => wd.Title == "Selenium documentation");
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
#Almacena el ID de la ventana original
original_window = driver.window_handle

#Comprueba que no existen otras ventanas abiertas previamente
assert(driver.window_handles.length == 1, 'Expected one window')

#Haz clic en el enlace el cual abre una nueva ventana
driver.find_element(link: 'new window').click

#Espera a la nueva ventana o pestaña
wait.until { driver.window_handles.length == 2 }

#Recorrelas hasta encontrar el controlador de la nueva ventana
driver.window_handles.each do |handle|
    if handle != original_window
        driver.switch_to.window handle
        break
    end
end

#Espera a que la nueva ventana carge su contenido
wait.until { driver.title == 'Selenium documentation'}
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
//Almacena el ID de la ventana original
const originalWindow = await driver.getWindowHandle();

//Comprueba que no existen otras ventanas abiertas previamente
assert((await driver.getAllWindowHandles()).length === 1);

//Haz clic en el enlace el cual abre una nueva ventana
await driver.findElement(By.linkText('new window')).click();

//Espera a la nueva ventana o pestaña
await driver.wait(
    async () => (await driver.getAllWindowHandles()).length === 2,
    10000
  );

//Recorrelas hasta encontrar el controlador de la nueva ventana
const windows = await driver.getAllWindowHandles();
windows.forEach(async handle => {
  if (handle !== originalWindow) {
    await driver.switchTo().window(handle);
  }
});

//Espera a que la nueva ventana cargue su contenido
await driver.wait(until.titleIs('Selenium documentation'), 10000);
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
//Almacena el ID de la ventana original
val originalWindow = driver.getWindowHandle()

//Comprueba que no existen otras ventanas abiertas previamente
assert(driver.getWindowHandles().size() === 1)

//Haz clic en el enlace el cual abre una nueva ventana
driver.findElement(By.linkText("new window")).click()

//Espera a la nueva ventana o pestaña
wait.until(numberOfWindowsToBe(2))

//Recorrelas hasta encontrar el controlador de la nueva ventana
for (windowHandle in driver.getWindowHandles()) {
    if (!originalWindow.contentEquals(windowHandle)) {
        driver.switchTo().window(windowHandle)
        break
    }
}

//Espera a que la nueva ventana cargue su contenido
wait.until(titleIs("Selenium documentation"))

  {{< / code-panel >}}
{{< / code-tab >}}

### Crea una nueva ventana o pestaña y cambia a ella

Crea una nueva ventana o pestaña y cambia el foco de la pantalla a la nueva
ventana o pestaña.
No necesitas cambiar el controlador para poder trabajar sobre la nueva ventana o
pestaña. Si tienes mas de dos ventanas o pestañas abiertas diferentes de la nueva
puedes recorrerlas y cambiar a la que no sea la original.

__Nota: Esta funcionalidad es específica de Selenium 4 y versiones posteriores.__
{{< code-tab >}}
  {{< code-panel language="java" >}}
// Abre una nueva pestaña y cambia el controlador a ella
driver.switchTo().newWindow(WindowType.TAB);

// Abre una nueva ventana y cambia el controlar a ella
driver.switchTo().newWindow(WindowType.WINDOW);
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Abre una nueva pestaña y cambia el controlador a ella
driver.switch_to.new_window('tab')

# Abre una nueva ventana y cambia el controlar a ella
driver.switch_to.new_window('window')
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Abre una nueva pestaña y cambia el controlador a ella
driver.SwitchTo().NewWindow(WindowType.Tab)

// Abre una nueva ventana y cambia el controlar a ella
driver.SwitchTo().NewWindow(WindowType.Window)
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Nota: El API new_window en Ruby solo abre una nueva pestaña o ventana pero no
# cambiará el controlador automáticamente, el usuario tiene forzar el cambio a
# la nueva pestaña o ventana

# Abre una nueva pestaña
driver.manage.new_window(:tab)

# Abre una nueva ventana
driver.manage.new_window(:window)
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Abre una nueva pestaña y cambia el controlador a ella
await driver.switchTo().newWindow('tab');

// Abre una nueva ventana y cambia el controlar a ella
await driver.switchTo().newWindow('window');

  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Abre una nueva pestaña y cambia el controlador a ella
driver.switchTo().newWindow(WindowType.TAB)

// Abre una nueva ventana y cambia el controlar a ella
driver.switchTo().newWindow(WindowType.WINDOW)
  {{< / code-panel >}}
{{< / code-tab >}}

### Cerrando una ventana o pestaña

Cuando hayas acabado con una ventana o una pestaña _y_ no sea la única ventana o
pestaña abierta en el navegador, debes cerrarla y cambiar el controlador de
vuelta a la ventana o pestaña que usabas con anterioridad. Asumiendo que has
seguido los ejemplos de código de la sección anterior dispondrás de una ventana
almacenada en una variable. Si le añades el siguiente código obtendrás un ejemplo:

{{< code-tab >}}
  {{< code-panel language="java" >}}
//Cierra una ventana o pestaña
driver.close();

//Cambia el controlador a la ventana o pestaña original
driver.switchTo().window(originalWindow);
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#Cierra una ventana o pestaña
driver.close()

#Cambia el controlador a la ventana o pestaña original
driver.switch_to.window(original_window)
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
//Cierra una ventana o pestaña
driver.Close();

//Cambia el controlador a la ventana o pestaña original
driver.SwitchTo().Window(originalWindow);
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
#Cierra una ventana o pestaña
driver.close

#Cambia el controlador a la ventana o pestaña original
driver.switch_to.window original_window
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
//Cierra una ventana o pestaña
await driver.close();

//Cambia el controlador a la ventana o pestaña original
await driver.switchTo().window(originalWindow);
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
//Cierra una ventana o pestaña
driver.close()

//Cambia el controlador a la ventana o pestaña original
driver.switchTo().window(originalWindow)

  {{< / code-panel >}}
{{< / code-tab >}}

Si te olvidas de cambiar el controlador de vuelta a otra ventana después de
cerrarla dejará al WebDriver ejecutando una ventana o pestaña cerrada, esto
devolverá la excepción **No Such Window Exception**. Debes de cambiar el
controlador de vuelta a una ventana o pestaña valida para continuar la
ejecución.

### Saliendo del navegador al final de una sesión

Cuando hayas acabado una sesión del navegador debes llamar al método salir en
lugar de cerrar:

{{< code-tab >}}
  {{< code-panel language="java" >}}driver.quit();{{< / code-panel >}}
  {{< code-panel language="python" >}}driver.quit(){{< / code-panel >}}
  {{< code-panel language="csharp" >}}driver.Quit();{{< / code-panel >}}
  {{< code-panel language="ruby" >}}driver.quit{{< / code-panel >}}
  {{< code-panel language="javascript" >}}await driver.quit();{{< / code-panel >}}
  {{< code-panel language="kotlin" >}}driver.quit(){{< / code-panel >}}
{{< / code-tab >}}

* Salir hará:
  * Cerrará todas las ventanas y pestañas asociadas a esa sesión del WebDriver.
  * Cerrará el proceso de navegador.
  * Cerrará el proceso en segundo plano del driver.
  * Notificará al Grid de Selenium que el navegador ya no esta en uso y que
  puede ser usado por otra sesión del Grid de Selenium.

Un fallo en la llamada del método salir dejará procesos corriendo en segundo
plano y puertos abiertos en tu maquina lo que podría llevar a problemas en un
futuro.

Algunos frameworks de pruebas ofrecen métodos y anotaciones a las cuales puedes
llamar para salir de una sesión al finalizar las pruebas.

{{< code-tab >}}
  {{< code-panel language="java" >}}
/**
 * Ejemplo usando JUnit
 * https://junit.org/junit5/docs/current/api/org/junit/jupiter/api/AfterAll.html
 */
@AfterAll
public static void tearDown() {
    driver.quit();
}
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Ejemplo usando unittest
# https://docs.python.org/3/library/unittest.html?highlight=teardown#unittest.TestCase.tearDown
def tearDown(self):
    self.driver.quit()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
/*
    Ejemplo usando UnitTesting de Visual Studio
    https://msdn.microsoft.com/en-us/library/microsoft.visualstudio.testtools.unittesting.aspx
*/
[TestCleanup]
public void TearDown()
{
    driver.Quit();
}
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Ejemplo usando UnitTest
# https://www.rubydoc.info/github/test-unit/test-unit/Test/Unit/TestCase
def teardown
    @driver.quit
end
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
/**
 * Ejemplo usando Mocha
 * https://mochajs.org/#hooks
 */
after('Tear down', async function () {
  await driver.quit();
});
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}

/**
 * Ejemplo usando JUnit
 * https://junit.org/junit5/docs/current/api/org/junit/jupiter/api/AfterAll.html
 */
@AfterAll
fun tearDown() {
    driver.quit()
}
  {{< / code-panel >}}
{{< / code-tab >}}

Si no estas ejecutando el WebDriver en un contexto que no es de tests, puedes
considerar el usar `try  / finally` los cuales son soportadas por la gran
mayoría de lenguajes de programacion de esta manera cuando aparezca una excepción
la sesión del WebDriver saldrá correctamente.

{{< code-tab >}}
  {{< code-panel language="java" >}}
try {
    //Código del WebDriver aquí...
} finally {
    driver.quit();
}
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
try:
    #Código del WebDriver aquí...
finally:
    driver.quit()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
try {
    //Código del WebDriver aquí...
} finally {
    driver.Quit();
}
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
begin
    #Código del WebDriver aquí...
ensure
    driver.quit
end
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
try {
    //Código del WebDriver aquí...
} finally {
    await driver.quit();
}
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
try {
    //Código del WebDriver aquí...
} finally {
    driver.quit()
}
  {{< / code-panel >}}
{{< / code-tab >}}

El WebDriver de Python ahora es soportado como gestor de contexto, lo que permite
ser usado con la palabra clave `with` para que automáticamente se cierre al final
de la ejecución.

```python
with webdriver.Firefox() as driver:
  # Codigo del WebDriver aqui...

# El WebDriver saldrá automaticamente despues de la indentacion

```

## Frames e Iframes
El uso de frames esta desaprobado, estos se basaban en construir sitios desde
múltiples documentos en el mismo dominio. Es poco probable que trabajes con esto
a menos que estés desarrollando una aplicación web previa a la versión de HTML5.
Los iframes te permiten la inserción de un documento desde un dominio diferente
y aun son usados de manera común.

Si necesitas trabajar con iframes o frames, el WebDriver te permite hacerlo de
la misma forma. Considera un iframe que contiene un botón. Si inspeccionamos el
elemento usando las herramientas de desarrollo del navegador podríamos ver lo
siguiente:

```html
<div id="modal">
  <iframe id="buttonframe" name="myframe"  src="https://seleniumhq.github.io">
   <button>Click here</button>
 </iframe>
</div>
```

Si no fuera por el iframe haríamos clic en el botón de una forma como esta:

{{< code-tab >}}
  {{< code-panel language="java" >}}
//Esto no funcionará
driver.findElement(By.tagName("button")).click();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Esto no funcionará
driver.find_element(By.TAG_NAME, 'button').click()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
//Esto no funcionará
driver.FindElement(By.TagName("button")).Click();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Esto no funcionará
driver.find_element(:tag_name,'button').click
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Esto no funcionará
await driver.findElement(By.css('button')).click();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
//Esto no funcionará
driver.findElement(By.tagName("button")).click()
  {{< / code-panel >}}
{{< / code-tab >}}

Sin embargo, como no existirían botones fuera del iframe obtendríamos una
excepción del tipo _no such element_. Esto ocurriría por que Selenium solo esta
al tanto de los elementos en los niveles superiores del documento. Para
interactuar con el botón primero necesitaremos cambiar el foco al iframe, de una
forma similar a lo que ocurría con las ventanas y pestañas. El WebDriver ofrece
tres formas de cambiar el foco a un iframe.

### Usando un WebElement

Cambiar usando un WebElement es la forma mas flexible de todas. Puedes encontrar
el iframe usando tu selector preferido y después cambiar el foco a el.

{{< code-tab >}}
  {{< code-panel language="java" >}}
//Almacena el elemento web
WebElement iframe = driver.findElement(By.cssSelector("#modal>iframe"));

//Cambia el foco al iframe
driver.switchTo().frame(iframe);

//Ahora podemos hacer clic en el botón
driver.findElement(By.tagName("button")).click();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Almacena el elemento web
iframe = driver.find_element(By.CSS_SELECTOR, "#modal > iframe")

# Cambia el foco al iframe
driver.switch_to.frame(iframe)

# Ahora podemos hacer clic en el botón
driver.find_element(By.TAG_NAME, 'button').click()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
//Almacena el elemento web
IWebElement iframe = driver.FindElement(By.CssSelector("#modal>iframe"));

//Cambia el foco al iframe
driver.SwitchTo().Frame(iframe);

//Ahora podemos hacer clic en el botón
driver.FindElement(By.TagName("button")).Click();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Almacena el elemento web
iframe = driver.find_element(:css,'#modal > iframe')

# Cambia el foco al iframe
driver.switch_to.frame iframe

# Ahora podemos hacer clic en el botón
driver.find_element(:tag_name,'button').click
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Almacena el elemento web
const iframe = driver.findElement(By.css('#modal > iframe'));

// Cambia el foco al iframe
await driver.switchTo().frame(iframe);

// Ahora podemos hacer clic en el botón
await driver.findElement(By.css('button')).click();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
//Almacena el elemento web
val iframe = driver.findElement(By.cssSelector("#modal>iframe"))

//Cambia el foco al iframe
driver.switchTo().frame(iframe)

//Ahora podemos hacer clic en el botón
driver.findElement(By.tagName("button")).click()
  {{< / code-panel >}}
{{< / code-tab >}}

### Usando el nombre o el ID
Si tu iframe o frame dispone de un atributo id o un nombre estos pueden ser
usados para ello.
Si los nombres o el id no fuesen únicos en la pagina este método cambiará el foco
al primer iframe o frame que encuentre.

{{< code-tab >}}
  {{< code-panel language="java" >}}
//Usando el ID
driver.switchTo().frame("buttonframe");

//O usando el atributo nombre
driver.switchTo().frame("myframe");

//Ahora podemos hacer clic en el botón
driver.findElement(By.tagName("button")).click();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Usando el ID
driver.switch_to.frame('buttonframe')

# Ahora podemos hacer clic en el botón
driver.find_element(By.TAG_NAME, 'button').click()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
//Usando el ID
driver.SwitchTo().Frame("buttonframe");

//O usando el atributo nombre
driver.SwitchTo().Frame("myframe");

//Ahora podemos hacer clic en el botón
driver.FindElement(By.TagName("button")).Click();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Usando el ID
driver.switch_to.frame 'buttonframe'

# Ahora podemos hacer clic en el botón
driver.find_element(:tag_name,'button').click
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
//Usando el ID
await driver.switchTo().frame('buttonframe');

// O usando el atributo nombre
await driver.switchTo().frame('myframe');

// Ahora podemos hacer clic en el botón
await driver.findElement(By.css('button')).click();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
//Usando el ID
driver.switchTo().frame("buttonframe")

//O usando el atributo nombre
driver.switchTo().frame("myframe")

//Ahora podemos hacer clic en el botón
driver.findElement(By.tagName("button")).click()
  {{< / code-panel >}}
{{< / code-tab >}}

### Por indice

También es posible usar el indice del iframe para cambiar el foco a el, los
indices pueden ser consultados utilizando la query de JavaScript _window.frames_.

{{< code-tab >}}
  {{< code-panel language="java" >}}
// Cambia el foco al segundo frame
driver.switchTo().frame(1);
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Cambia el foco al segundo frame
driver.switch_to.frame(1)
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Cambia el foco al segundo frame
driver.SwitchTo().Frame(1);
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Cambia el foco al segundo frame
iframe = driver.find_elements_by_tag_name('iframe')[1]

# Cambia el foco al iframe seleccionado
driver.switch_to.frame(iframe)
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
//Cambia el foco al segundo frame
await driver.switchTo().frame(1);
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Cambia el foco al segundo frame
driver.switchTo().frame(1)
  {{< / code-panel >}}
{{< / code-tab >}}


### Saliendo de un frame

Para salir de un iframe o un frame y volver el foco al contenido original:

{{< code-tab >}}
  {{< code-panel language="java" >}}
// Vuelve al nivel superior
driver.switchTo().defaultContent();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Vuelve al nivel superior
driver.switch_to.default_content()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Vuelve al nivel superior
driver.SwitchTo().DefaultContent();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Vuelve al nivel superior
driver.switch_to.default_content
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Vuelve al nivel superior
await driver.switchTo().defaultContent();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Vuelve al nivel superior
driver.switchTo().defaultContent()
  {{< / code-panel >}}
{{< / code-tab >}}

## Manejo de las ventanas

La resolución de las pantallas puede impactar en como tu aplicación se renderiza
es por eso que el WebDriver provee de mecanismos para mover y cambiar el tamaño
de la ventana del navegador.

### Obtener el tamaño de la ventana
Obtiene el tamaño de la ventana del navegador en pixeles.

{{< code-tab >}}
  {{< code-panel language="java" >}}
//Accede a cada dimensión individualmente
int width = driver.manage().window().getSize().getWidth();
int height = driver.manage().window().getSize().getHeight();

//O almacénalas para acceder a ellas mas tarde
Dimension size = driver.manage().window().getSize();
int width1 = size.getWidth();
int height1 = size.getHeight();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Accede a cada dimensión individualmente
width = driver.get_window_size().get("width")
height = driver.get_window_size().get("height")

# O almacénalas para acceder a ellas mas tarde
size = driver.get_window_size()
width1 = size.get("width")
height1 = size.get("height")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
//Accede a cada dimensión individualmente
int width = driver.Manage().Window.Size.Width;
int height = driver.Manage().Window.Size.Height;

//O almacénalas para acceder a ellas mas tarde
System.Drawing.Size size = driver.Manage().Window.Size;
int width1 = size.Width;
int height1 = size.Height;
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Accede a cada dimensión individualmente
width = driver.manage.window.size.width
height = driver.manage.window.size.height

# O almacénalas para acceder a ellas mas tarde
size = driver.manage.window.size
width1 = size.width
height1 = size.height
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Accede a cada dimensión individualmente
const { width, height } = await driver.manage().window().getRect();

// O almacénalas para acceder a ellas mas tarde
const rect = await driver.manage().window().getRect();
const width1 = rect.width;
const height1 = rect.height;
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
//Accede a cada dimensión individualmente
val width = driver.manage().window().size.width
val height = driver.manage().window().size.height

//O almacénalas para acceder a ellas mas tarde
val size = driver.manage().window().size
val width1 = size.width
val height1 = size.height
  {{< / code-panel >}}
{{< / code-tab >}}

### Fija el tamaño de la ventana
Recupera la ventana y fija el tamaño de esta.
{{< code-tab >}}
  {{< code-panel language="java" >}}driver.manage().window().setSize(new Dimension(1024, 768));{{< / code-panel >}}
  {{< code-panel language="python" >}}driver.set_window_size(1024, 768){{< / code-panel >}}
  {{< code-panel language="csharp" >}}driver.Manage().Window.Size = new Size(1024, 768);{{< / code-panel >}}
  {{< code-panel language="ruby" >}}driver.manage.window.resize_to(1024,768){{< / code-panel >}}
  {{< code-panel language="javascript" >}}await driver.manage().window().setRect({ width: 1024, height: 768 });{{< / code-panel >}}
  {{< code-panel language="kotlin" >}}driver.manage().window().size = Dimension(1024, 768){{< / code-panel >}}
{{< / code-tab >}}

### Obten la posicion de la ventana

Obtiene la posiciones de las coordernadas en el sistema arriba izquierda de la
ventana del navegador.
{{< code-tab >}}
  {{< code-panel language="java" >}}
// Accede a cada dimensión individualmente
int x = driver.manage().window().getPosition().getX();
int y = driver.manage().window().getPosition().getY();

// O almacénalas para acceder a ellas mas tarde
Point position = driver.manage().window().getPosition();
int x1 = position.getX();
int y1 = position.getY();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Accede a cada dimensión individualmente
x = driver.get_window_position().get('x')
y = driver.get_window_position().get('y')

# O almacénalas para acceder a ellas mas tarde
position = driver.get_window_position()
x1 = position.get('x')
y1 = position.get('y')
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
//Accede a cada dimensión individualmente
int x = driver.Manage().Window.Position.X;
int y = driver.Manage().Window.Position.Y;

//O almacénalas para acceder a ellas mas tarde
Point position = driver.Manage().Window.Position;
int x1 = position.X;
int y1 = position.Y;
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
#Accede a cada dimensión individualmente
x = driver.manage.window.position.x
y = driver.manage.window.position.y

# O almacénalas para acceder a ellas mas tarde
rect  = driver.manage.window.rect
x1 = rect.x
y1 = rect.y
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Accede a cada dimensión individualmente
const { x, y } = await driver.manage().window().getRect();

// O almacénalas para acceder a ellas mas tarde
const rect = await driver.manage().window().getRect();
const x1 = rect.x;
const y1 = rect.y;
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Accede a cada dimensión individualmente
val x = driver.manage().window().position.x
val y = driver.manage().window().position.y

// O almacénalas para acceder a ellas mas tarde
val position = driver.manage().window().position
val x1 = position.x
val y1 = position.y

  {{< / code-panel >}}
{{< / code-tab >}}

## Fija la posición de la ventana

Mueve la ventana a la posición deseada.

{{< code-tab >}}
  {{< code-panel language="java" >}}
// Mueve la ventana arriba izquierda del monitor principal
driver.manage().window().setPosition(new Point(0, 0));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Mueve la ventana arriba izquierda del monitor principal
driver.set_window_position(0, 0)
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Mueve la ventana arriba izquierda del monitor principal
driver.Manage().Window.Position = new Point(0, 0);
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
driver.manage.window.move_to(0,0)
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Mueve la ventana arriba izquierda del monitor principal
await driver.manage().window().setRect({ x: 0, y: 0 });
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Mueve la ventana arriba izquierda del monitor principal
driver.manage().window().position = Point(0,0)
    {{< / code-panel >}}
{{< / code-tab >}}

### Maximizar la ventana
Maximiza la ventana. Para la gran mayoría de sistemas operativos, la ventana
rellenará la pantalla, sin bloquear los menús propios y barras de herramientas
de los sistemas operativos.

{{< code-tab >}}
  {{< code-panel language="java" >}}driver.manage().window().maximize();{{< / code-panel >}}
  {{< code-panel language="python" >}}driver.maximize_window(){{< / code-panel >}}
  {{< code-panel language="csharp" >}}driver.Manage().Window.Maximize();{{< / code-panel >}}
  {{< code-panel language="ruby" >}}driver.manage.window.maximize{{< / code-panel >}}
  {{< code-panel language="javascript" >}}await driver.manage().window().maximize();{{< / code-panel >}}
  {{< code-panel language="kotlin" >}}driver.manage().window().maximize(){{< / code-panel >}}
{{< / code-tab >}}

### Minimizar la ventana
Minimiza la ventana del actual contexto del navegador.
El comportamiento exacto de este comando es especifico individualmente de cada
gestor de ventanas.

Minimizar la ventana típicamente oculta la ventana en la bandeja del sistema.

__Nota: Esta funcionalidad es específica de Selenium 4 y versiones posteriores.__

{{< code-tab >}}
  {{< code-panel language="java" >}}driver.manage().window().minimize();{{< / code-panel >}}
  {{< code-panel language="python" >}}driver.minimize_window(){{< / code-panel >}}
  {{< code-panel language="csharp" >}}driver.Manage().Window.Minimize();{{< / code-panel >}}
  {{< code-panel language="ruby" >}}driver.manage.window.minimize{{< / code-panel >}}
  {{< code-panel language="javascript" >}}await driver.manage().window().minimize();{{< / code-panel >}}
  {{< code-panel language="kotlin" >}}driver.manage().window().minimize(){{< / code-panel >}}
{{< / code-tab >}}

### Modo pantalla completa

Llena la pantalla completamente, similar a presionar la tecla F11 en la gran
mayoria de navegadores.

{{< code-tab >}}
  {{< code-panel language="java" >}}driver.manage().window().fullscreen();{{< / code-panel >}}
  {{< code-panel language="python" >}}driver.fullscreen_window(){{< / code-panel >}}
  {{< code-panel language="csharp" >}}driver.Manage().Window.FullScreen();{{< / code-panel >}}
  {{< code-panel language="ruby" >}}driver.manage.window.full_screen{{< / code-panel >}}
  {{< code-panel language="javascript" >}}await driver.manage().window().fullscreen();{{< / code-panel >}}
  {{< code-panel language="kotlin" >}}driver.manage().window().fullscreen(){{< / code-panel >}}
{{< / code-tab >}}

### TakeScreenshot

Used to capture screenshot for current browsing context.
The WebDriver endpoint [screenshot](https://www.w3.org/TR/webdriver/#dfn-take-screenshot)
returns screenshot which is encoded in Base64 format.

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.*;
import org.openqa.selenium.*;

public class SeleniumTakeScreenshot {
    public static void main(String args[]) throws IOException {
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.example.com");
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("./image.png"));
        driver.quit();
    }
}
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium import webdriver

driver = webdriver.Chrome()

# Navigate to url
driver.get("http://www.example.com")

# Returns and base64 encoded string into image
driver.save_screenshot('./image.png')

driver.quit()

{{< / code-panel >}}
  {{< code-panel language="csharp" >}}
    using OpenQA.Selenium;
    using OpenQA.Selenium.Chrome;
    using OpenQA.Selenium.Support.UI;

    var driver = new ChromeDriver();
    driver.Navigate().GoToUrl("http://www.example.com");
    Screenshot screenshot = (driver as ITakesScreenshot).GetScreenshot();
    screenshot.SaveAsFile("screenshot.png", ScreenshotImageFormat.Png); // Format values are Bmp, Gif, Jpeg, Png, Tiff
   {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :chrome

begin
  driver.get 'https://example.com/'

  # Takes and Stores the screenshot in specified path
  driver.save_screenshot('./image.png')

end
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
let {Builder} = require('selenium-webdriver');
let fs = require('fs');

(async function example() {
    let driver = await new Builder()
      .forBrowser('chrome')
      .build();

    await driver.get('https://www.example.com');
    // Returns base64 encoded string
    let encodedString = await driver.takeScreenshot();
    await fs.writeFileSync('./image.png', encodedString, 'base64');
    await driver.quit();
}())
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import com.oracle.tools.packager.IOUtils.copyFile
import org.openqa.selenium.*
import org.openqa.selenium.chrome.ChromeDriver
import java.io.File

fun main(){
    val driver =  ChromeDriver()
    driver.get("https://www.example.com")
    val scrFile = (driver as TakesScreenshot).getScreenshotAs<File>(OutputType.FILE)
    copyFile(scrFile, File("./image.png"))
    driver.quit()
}
  {{< / code-panel >}}
{{< / code-tab >}}

###  TakeElementScreenshot

Used to capture screenshot of an element for current browsing context.
The WebDriver endpoint [screenshot](https://www.w3.org/TR/webdriver/#take-element-screenshot)
returns screenshot which is encoded in Base64 format.

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.IOException;

public class SeleniumelementTakeScreenshot {
  public static void main(String args[]) throws IOException {
    WebDriver driver = new ChromeDriver();
    driver.get("https://www.example.com");
    WebElement element = driver.findElement(By.cssSelector("h1"));
    File scrFile = element.getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile, new File("./image.png"));
    driver.quit();
  }
}
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium import webdriver
from selenium.webdriver.common.by import By

driver = webdriver.Chrome()

# Navigate to url
driver.get("http://www.example.com")

ele = driver.find_element(By.CSS_SELECTOR, 'h1')

# Returns and base64 encoded string into image
ele.screenshot('./image.png')

driver.quit()
{{< / code-panel >}}
  {{< code-panel language="csharp" >}}
    using OpenQA.Selenium;
    using OpenQA.Selenium.Chrome;
    using OpenQA.Selenium.Support.UI;

    // Webdriver
    var driver = new ChromeDriver();
    driver.Navigate().GoToUrl("http://www.example.com");

    // Fetch element using FindElement
    var webElement = driver.FindElement(By.CssSelector("h1"));

    // Screenshot for the element
    var elementScreenshot = (webElement as ITakesScreenshot).GetScreenshot();
    elementScreenshot.SaveAsFile("screenshot_of_element.png");
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Works with Selenium4-alpha7 Ruby bindings and above
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :chrome

begin
  driver.get 'https://example.com/'
  ele = driver.find_element(:css, 'h1')

  # Takes and Stores the element screenshot in specified path
  ele.save_screenshot('./image.jpg')
end
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder, By} = require('selenium-webdriver');
let fs = require('fs');

(async function example() {
   let driver = await new Builder()
       .forBrowser('chrome')
       .build();

   await driver.get('https://www.example.com');
   let ele = await driver.findElement(By.css("h1"));
   // Captures the element screenshot
   let encodedString = await ele.takeScreenshot(true);
   await fs.writeFileSync('./image.png', encodedString, 'base64');
   await driver.quit();
}())
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.apache.commons.io.FileUtils
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.*
import java.io.File

fun main() {
    val driver = ChromeDriver()
    driver.get("https://www.example.com")
    val element = driver.findElement(By.cssSelector("h1"))
    val scrFile: File = element.getScreenshotAs(OutputType.FILE)
    FileUtils.copyFile(scrFile, File("./image.png"))
    driver.quit()
}
  {{< / code-panel >}}
{{< / code-tab >}}

### Execute Script

Executes JavaScript code snippet in the
current context of a selected frame or window.

{{< code-tab >}}
  {{< code-panel language="java" >}}
    //Creating the JavascriptExecutor interface object by Type casting
      JavascriptExecutor js = (JavascriptExecutor)driver;
    //Button Element
      WebElement button =driver.findElement(By.name("btnLogin"));
    //Executing JavaScript to click on element
      js.executeScript("arguments[0].click();", button);
    //Get return value from script
      String text = (String) js.executeScript("return arguments[0].innerText", button);
    //Executing JavaScript directly
      js.executeScript("console.log('hello world')");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
    # code sample not available please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
    // code sample not available please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
    # code sample not available please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Stores the header element
let header = await driver.findElement(By.css('h1'));

// Executing JavaScript to capture innerText of header element
let text = await driver.executeScript('return arguments[0].innerText', header);
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
    // code sample not available please raise a PR
  {{< / code-panel >}}
{{< / code-tab >}}

### Print Page

Prints the current page within the browser

_Note: This requires Chromium Browsers to be in headless mode_


{{< code-tab >}}
  {{< code-panel language="java" >}}
   import org.openqa.selenium.print.PrintOptions;

    driver.get("https://www.selenium.dev");
    printer = (PrintsPage) driver;

    PrintOptions printOptions = new PrintOptions();
    printOptions.setPageRanges("1-2");

    Pdf pdf = printer.print(printOptions);
    String content = pdf.getContent();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
    from selenium.webdriver.common.print_page_options import PrintOptions

    print_options = PrintOptions()
    print_options.page_ranges = ['1-2']

    driver.get("printPage.html")

    base64code = driver.print_page(print_options)
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
    // code sample not available please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
    driver.navigate_to 'https://www.selenium.dev'

    base64encodedContent = driver.print_page(orientation: 'landscape')
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
  const {Builder} = require('selenium-webdriver');
  const chrome = require('selenium-webdriver/chrome');
  let opts = new chrome.Options();
  let fs = require('fs');
  (async function example() {
    let driver = new Builder()
      .forBrowser('chrome')
      .setChromeOptions(opts.headless())
      .build();
    await driver.get('https://www.selenium.dev');
    try {
      let base64 = await driver.printPage({pageRanges:["1-2"]});
      await fs.writeFileSync('./test.pdf', base64, 'base64');
    } catch (e) {
    console.log(e)
    }
    await driver.quit();
  })();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
    // code sample not available please raise a PR
  {{< / code-panel >}}
{{< / code-tab >}}
