---
title: "Manipulación de Navegadores"
linkTitle: "Manipulación de Navegadores"
weight: 2
aliases: ["/documentation/es/webdriver/browser_manipulation/"]
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to Spanish. Do you speak Spanish? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}


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

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
//Forma adecuada
driver.get("https://selenium.dev");

//Forma extensa
driver.navigate().to("https://selenium.dev");
  {{< /tab >}}
  {{< tab header="Python" >}}
driver.get("https://selenium.dev")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
driver.Navigate().GoToUrl(@"https://selenium.dev");
  {{< /tab >}}
  {{< tab header="Ruby" >}}
//Forma adecuada
driver.get 'https://selenium.dev'

//Forma extensa
driver.navigate.to 'https://selenium.dev'
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
await driver.get('https://selenium.dev');
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
//Forma adecuada
driver.get("https://selenium.dev")

//Forma extensa
driver.navigate().to("https://selenium.dev")
  {{< /tab >}}
{{< /tabpane >}}

### Obtener la URL actual

Puedes leer la URL actual desde la barra de direcciones del navegador usando:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.getCurrentUrl();{{< /tab >}}
  {{< tab header="Python" >}}driver.current_url{{< /tab >}}
  {{< tab header="CSharp" >}}driver.Url;{{< /tab >}}
  {{< tab header="Ruby" >}}driver.current_url{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.getCurrentUrl();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.currentUrl{{< /tab >}}
{{< /tabpane >}}

### Retroceder

Presionando el botón de retroceder del navegador:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.navigate().back();{{< /tab >}}
  {{< tab header="Python" >}}driver.back(){{< /tab >}}
  {{< tab header="CSharp" >}}driver.Navigate().Back();{{< /tab >}}
  {{< tab header="Ruby" >}}driver.navigate.back{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.navigate().back();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.navigate().back() {{< /tab >}}
{{< /tabpane >}}

### Avanzar

Presionando el botón de avanzar del navegador:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.navigate().forward();{{< /tab >}}
  {{< tab header="Python" >}}driver.forward(){{< /tab >}}
  {{< tab header="CSharp" >}}driver.Navigate().Forward();{{< /tab >}}
  {{< tab header="Ruby" >}}driver.navigate.forward{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.navigate().forward();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.navigate().forward(){{< /tab >}}
{{< /tabpane >}}

### Actualizar

Recargando la pagina actual:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.navigate().refresh();{{< /tab >}}
  {{< tab header="Python" >}}driver.refresh(){{< /tab >}}
  {{< tab header="CSharp" >}}driver.Navigate().Refresh();{{< /tab >}}
  {{< tab header="Ruby" >}}driver.navigate.refresh{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.navigate().refresh();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.navigate().refresh(){{< /tab >}}
{{< /tabpane >}}

### Obtener el título

Puedes leer el título de la pagina actual desde el navegador:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.getTitle();{{< /tab >}}
  {{< tab header="Python" >}}driver.title{{< /tab >}}
  {{< tab header="CSharp" >}}driver.Title;{{< /tab >}}
  {{< tab header="Ruby" >}}driver.title{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.getTitle();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.title{{< /tab >}}
{{< /tabpane >}}


## Ventanas y pestañas

### Obtener el controlador de ventanas

El WebDriver no hace distinción entre ventanas y pestañas.  Si tu sitio web abre
una nueva pestaña o ventana, Selenium te permitirá trabajar con ella usando un
controlador de ventanas. Cada ventana tiene un identificador único el cual
persiste en una sola sesión. Puedes obtener el controlador de la ventana de la
ventana actual usando:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.getWindowHandle();{{< /tab >}}
  {{< tab header="Python" >}}driver.current_window_handle{{< /tab >}}
  {{< tab header="CSharp" >}}driver.CurrentWindowHandle;{{< /tab >}}
  {{< tab header="Ruby" >}}driver.window_handle{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.getWindowHandle();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.windowHandle{{< /tab >}}
{{< /tabpane >}}

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

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
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
  {{< /tab >}}
  {{< tab header="Python" >}}
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
  {{< /tab >}}
  {{< tab header="CSharp" >}}
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
  {{< /tab >}}
  {{< tab header="Ruby" >}}
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
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
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
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
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

  {{< /tab >}}
{{< /tabpane >}}

### Crea una nueva ventana o pestaña y cambia a ella

Crea una nueva ventana o pestaña y cambia el foco de la pantalla a la nueva
ventana o pestaña.
No necesitas cambiar el controlador para poder trabajar sobre la nueva ventana o
pestaña. Si tienes mas de dos ventanas o pestañas abiertas diferentes de la nueva
puedes recorrerlas y cambiar a la que no sea la original.

__Nota: Esta funcionalidad es específica de Selenium 4 y versiones posteriores.__
{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
// Abre una nueva pestaña y cambia el controlador a ella
driver.switchTo().newWindow(WindowType.TAB);

// Abre una nueva ventana y cambia el controlar a ella
driver.switchTo().newWindow(WindowType.WINDOW);
  {{< /tab >}}
  {{< tab header="Python" >}}
# Abre una nueva pestaña y cambia el controlador a ella
driver.switch_to.new_window('tab')

# Abre una nueva ventana y cambia el controlar a ella
driver.switch_to.new_window('window')
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// Abre una nueva pestaña y cambia el controlador a ella
driver.SwitchTo().NewWindow(WindowType.Tab)

// Abre una nueva ventana y cambia el controlar a ella
driver.SwitchTo().NewWindow(WindowType.Window)
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Nota: El API new_window en Ruby solo abre una nueva pestaña o ventana pero no
# cambiará el controlador automáticamente, el usuario tiene forzar el cambio a
# la nueva pestaña o ventana

# Abre una nueva pestaña
driver.manage.new_window(:tab)

# Abre una nueva ventana
driver.manage.new_window(:window)
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Abre una nueva pestaña y cambia el controlador a ella
await driver.switchTo().newWindow('tab');

// Abre una nueva ventana y cambia el controlar a ella
await driver.switchTo().newWindow('window');

  {{< /tab >}}
  {{< tab header="Kotlin" >}}
// Abre una nueva pestaña y cambia el controlador a ella
driver.switchTo().newWindow(WindowType.TAB)

// Abre una nueva ventana y cambia el controlar a ella
driver.switchTo().newWindow(WindowType.WINDOW)
  {{< /tab >}}
{{< /tabpane >}}

### Cerrando una ventana o pestaña

Cuando hayas acabado con una ventana o una pestaña _y_ no sea la única ventana o
pestaña abierta en el navegador, debes cerrarla y cambiar el controlador de
vuelta a la ventana o pestaña que usabas con anterioridad. Asumiendo que has
seguido los ejemplos de código de la sección anterior dispondrás de una ventana
almacenada en una variable. Si le añades el siguiente código obtendrás un ejemplo:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
//Cierra una ventana o pestaña
driver.close();

//Cambia el controlador a la ventana o pestaña original
driver.switchTo().window(originalWindow);
  {{< /tab >}}
  {{< tab header="Python" >}}
#Cierra una ventana o pestaña
driver.close()

#Cambia el controlador a la ventana o pestaña original
driver.switch_to.window(original_window)
  {{< /tab >}}
  {{< tab header="CSharp" >}}
//Cierra una ventana o pestaña
driver.Close();

//Cambia el controlador a la ventana o pestaña original
driver.SwitchTo().Window(originalWindow);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
#Cierra una ventana o pestaña
driver.close

#Cambia el controlador a la ventana o pestaña original
driver.switch_to.window original_window
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
//Cierra una ventana o pestaña
await driver.close();

//Cambia el controlador a la ventana o pestaña original
await driver.switchTo().window(originalWindow);
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
//Cierra una ventana o pestaña
driver.close()

//Cambia el controlador a la ventana o pestaña original
driver.switchTo().window(originalWindow)

  {{< /tab >}}
{{< /tabpane >}}

Si te olvidas de cambiar el controlador de vuelta a otra ventana después de
cerrarla dejará al WebDriver ejecutando una ventana o pestaña cerrada, esto
devolverá la excepción **No Such Window Exception**. Debes de cambiar el
controlador de vuelta a una ventana o pestaña valida para continuar la
ejecución.

### Saliendo del navegador al final de una sesión

Cuando hayas acabado una sesión del navegador debes llamar al método salir en
lugar de cerrar:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.quit();{{< /tab >}}
  {{< tab header="Python" >}}driver.quit(){{< /tab >}}
  {{< tab header="CSharp" >}}driver.Quit();{{< /tab >}}
  {{< tab header="Ruby" >}}driver.quit{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.quit();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.quit(){{< /tab >}}
{{< /tabpane >}}

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

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
/**
 * Ejemplo usando JUnit
 * https://junit.org/junit5/docs/current/api/org/junit/jupiter/api/AfterAll.html
 */
@AfterAll
public static void tearDown() {
    driver.quit();
}
  {{< /tab >}}
  {{< tab header="Python" >}}
# Ejemplo usando unittest
# https://docs.python.org/3/library/unittest.html?highlight=teardown#unittest.TestCase.tearDown
def tearDown(self):
    self.driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
/*
    Ejemplo usando UnitTesting de Visual Studio
    https://msdn.microsoft.com/en-us/library/microsoft.visualstudio.testtools.unittesting.aspx
*/
[TestCleanup]
public void TearDown()
{
    driver.Quit();
}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Ejemplo usando UnitTest
# https://www.rubydoc.info/github/test-unit/test-unit/Test/Unit/TestCase
def teardown
    @driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
/**
 * Ejemplo usando Mocha
 * https://mochajs.org/#hooks
 */
after('Tear down', async function () {
  await driver.quit();
});
  {{< /tab >}}
  {{< tab header="Kotlin" >}}

/**
 * Ejemplo usando JUnit
 * https://junit.org/junit5/docs/current/api/org/junit/jupiter/api/AfterAll.html
 */
@AfterAll
fun tearDown() {
    driver.quit()
}
  {{< /tab >}}
{{< /tabpane >}}

Si no estas ejecutando el WebDriver en un contexto que no es de tests, puedes
considerar el usar `try  / finally` los cuales son soportadas por la gran
mayoría de lenguajes de programacion de esta manera cuando aparezca una excepción
la sesión del WebDriver saldrá correctamente.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
try {
    //Código del WebDriver aquí...
} finally {
    driver.quit();
}
  {{< /tab >}}
  {{< tab header="Python" >}}
try:
    #Código del WebDriver aquí...
finally:
    driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
try {
    //Código del WebDriver aquí...
} finally {
    driver.Quit();
}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
begin
    #Código del WebDriver aquí...
ensure
    driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
try {
    //Código del WebDriver aquí...
} finally {
    await driver.quit();
}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
try {
    //Código del WebDriver aquí...
} finally {
    driver.quit()
}
  {{< /tab >}}
{{< /tabpane >}}

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

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
//Esto no funcionará
driver.findElement(By.tagName("button")).click();
  {{< /tab >}}
  {{< tab header="Python" >}}
# Esto no funcionará
driver.find_element(By.TAG_NAME, 'button').click()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
//Esto no funcionará
driver.FindElement(By.TagName("button")).Click();
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Esto no funcionará
driver.find_element(:tag_name,'button').click
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Esto no funcionará
await driver.findElement(By.css('button')).click();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
//Esto no funcionará
driver.findElement(By.tagName("button")).click()
  {{< /tab >}}
{{< /tabpane >}}

Sin embargo, como no existirían botones fuera del iframe obtendríamos una
excepción del tipo _no such element_. Esto ocurriría por que Selenium solo esta
al tanto de los elementos en los niveles superiores del documento. Para
interactuar con el botón primero necesitaremos cambiar el foco al iframe, de una
forma similar a lo que ocurría con las ventanas y pestañas. El WebDriver ofrece
tres formas de cambiar el foco a un iframe.

### Usando un WebElement

Cambiar usando un WebElement es la forma mas flexible de todas. Puedes encontrar
el iframe usando tu selector preferido y después cambiar el foco a el.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
//Almacena el elemento web
WebElement iframe = driver.findElement(By.cssSelector("#modal>iframe"));

//Cambia el foco al iframe
driver.switchTo().frame(iframe);

//Ahora podemos hacer clic en el botón
driver.findElement(By.tagName("button")).click();
  {{< /tab >}}
  {{< tab header="Python" >}}
# Almacena el elemento web
iframe = driver.find_element(By.CSS_SELECTOR, "#modal > iframe")

# Cambia el foco al iframe
driver.switch_to.frame(iframe)

# Ahora podemos hacer clic en el botón
driver.find_element(By.TAG_NAME, 'button').click()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
//Almacena el elemento web
IWebElement iframe = driver.FindElement(By.CssSelector("#modal>iframe"));

//Cambia el foco al iframe
driver.SwitchTo().Frame(iframe);

//Ahora podemos hacer clic en el botón
driver.FindElement(By.TagName("button")).Click();
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Almacena el elemento web
iframe = driver.find_element(:css,'#modal > iframe')

# Cambia el foco al iframe
driver.switch_to.frame iframe

# Ahora podemos hacer clic en el botón
driver.find_element(:tag_name,'button').click
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Almacena el elemento web
const iframe = driver.findElement(By.css('#modal > iframe'));

// Cambia el foco al iframe
await driver.switchTo().frame(iframe);

// Ahora podemos hacer clic en el botón
await driver.findElement(By.css('button')).click();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
//Almacena el elemento web
val iframe = driver.findElement(By.cssSelector("#modal>iframe"))

//Cambia el foco al iframe
driver.switchTo().frame(iframe)

//Ahora podemos hacer clic en el botón
driver.findElement(By.tagName("button")).click()
  {{< /tab >}}
{{< /tabpane >}}

### Usando el nombre o el ID
Si tu iframe o frame dispone de un atributo id o un nombre estos pueden ser
usados para ello.
Si los nombres o el id no fuesen únicos en la pagina este método cambiará el foco
al primer iframe o frame que encuentre.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
//Usando el ID
driver.switchTo().frame("buttonframe");

//O usando el atributo nombre
driver.switchTo().frame("myframe");

//Ahora podemos hacer clic en el botón
driver.findElement(By.tagName("button")).click();
  {{< /tab >}}
  {{< tab header="Python" >}}
# Usando el ID
driver.switch_to.frame('buttonframe')

# Ahora podemos hacer clic en el botón
driver.find_element(By.TAG_NAME, 'button').click()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
//Usando el ID
driver.SwitchTo().Frame("buttonframe");

//O usando el atributo nombre
driver.SwitchTo().Frame("myframe");

//Ahora podemos hacer clic en el botón
driver.FindElement(By.TagName("button")).Click();
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Usando el ID
driver.switch_to.frame 'buttonframe'

# Ahora podemos hacer clic en el botón
driver.find_element(:tag_name,'button').click
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
//Usando el ID
await driver.switchTo().frame('buttonframe');

// O usando el atributo nombre
await driver.switchTo().frame('myframe');

// Ahora podemos hacer clic en el botón
await driver.findElement(By.css('button')).click();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
//Usando el ID
driver.switchTo().frame("buttonframe")

//O usando el atributo nombre
driver.switchTo().frame("myframe")

//Ahora podemos hacer clic en el botón
driver.findElement(By.tagName("button")).click()
  {{< /tab >}}
{{< /tabpane >}}

### Por indice

También es posible usar el indice del iframe para cambiar el foco a el, los
indices pueden ser consultados utilizando la query de JavaScript _window.frames_.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
// Cambia el foco al segundo frame
driver.switchTo().frame(1);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Cambia el foco al segundo frame
driver.switch_to.frame(1)
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// Cambia el foco al segundo frame
driver.SwitchTo().Frame(1);
  {{< /tab >}}
  {{< tab header="Python" >}}
# Cambia el foco al segundo frame
iframe = driver.find_elements_by_tag_name('iframe')[1]

# Cambia el foco al iframe seleccionado
driver.switch_to.frame(iframe)
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
//Cambia el foco al segundo frame
await driver.switchTo().frame(1);
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
// Cambia el foco al segundo frame
driver.switchTo().frame(1)
  {{< /tab >}}
{{< /tabpane >}}


### Saliendo de un frame

Para salir de un iframe o un frame y volver el foco al contenido original:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
// Vuelve al nivel superior
driver.switchTo().defaultContent();
  {{< /tab >}}
  {{< tab header="Python" >}}
# Vuelve al nivel superior
driver.switch_to.default_content()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// Vuelve al nivel superior
driver.SwitchTo().DefaultContent();
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Vuelve al nivel superior
driver.switch_to.default_content
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Vuelve al nivel superior
await driver.switchTo().defaultContent();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
// Vuelve al nivel superior
driver.switchTo().defaultContent()
  {{< /tab >}}
{{< /tabpane >}}

## Manejo de las ventanas

La resolución de las pantallas puede impactar en como tu aplicación se renderiza
es por eso que el WebDriver provee de mecanismos para mover y cambiar el tamaño
de la ventana del navegador.

### Obtener el tamaño de la ventana
Obtiene el tamaño de la ventana del navegador en pixeles.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
//Accede a cada dimensión individualmente
int width = driver.manage().window().getSize().getWidth();
int height = driver.manage().window().getSize().getHeight();

//O almacénalas para acceder a ellas mas tarde
Dimension size = driver.manage().window().getSize();
int width1 = size.getWidth();
int height1 = size.getHeight();
  {{< /tab >}}
  {{< tab header="Python" >}}
# Accede a cada dimensión individualmente
width = driver.get_window_size().get("width")
height = driver.get_window_size().get("height")

# O almacénalas para acceder a ellas mas tarde
size = driver.get_window_size()
width1 = size.get("width")
height1 = size.get("height")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
//Accede a cada dimensión individualmente
int width = driver.Manage().Window.Size.Width;
int height = driver.Manage().Window.Size.Height;

//O almacénalas para acceder a ellas mas tarde
System.Drawing.Size size = driver.Manage().Window.Size;
int width1 = size.Width;
int height1 = size.Height;
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Accede a cada dimensión individualmente
width = driver.manage.window.size.width
height = driver.manage.window.size.height

# O almacénalas para acceder a ellas mas tarde
size = driver.manage.window.size
width1 = size.width
height1 = size.height
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Accede a cada dimensión individualmente
const { width, height } = await driver.manage().window().getRect();

// O almacénalas para acceder a ellas mas tarde
const rect = await driver.manage().window().getRect();
const width1 = rect.width;
const height1 = rect.height;
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
//Accede a cada dimensión individualmente
val width = driver.manage().window().size.width
val height = driver.manage().window().size.height

//O almacénalas para acceder a ellas mas tarde
val size = driver.manage().window().size
val width1 = size.width
val height1 = size.height
  {{< /tab >}}
{{< /tabpane >}}

### Fija el tamaño de la ventana
Recupera la ventana y fija el tamaño de esta.
{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.manage().window().setSize(new Dimension(1024, 768));{{< /tab >}}
  {{< tab header="Python" >}}driver.set_window_size(1024, 768){{< /tab >}}
  {{< tab header="CSharp" >}}driver.Manage().Window.Size = new Size(1024, 768);{{< /tab >}}
  {{< tab header="Ruby" >}}driver.manage.window.resize_to(1024,768){{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.manage().window().setRect({ width: 1024, height: 768 });{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.manage().window().size = Dimension(1024, 768){{< /tab >}}
{{< /tabpane >}}

### Obten la posicion de la ventana

Obtiene la posiciones de las coordernadas en el sistema arriba izquierda de la
ventana del navegador.
{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
// Accede a cada dimensión individualmente
int x = driver.manage().window().getPosition().getX();
int y = driver.manage().window().getPosition().getY();

// O almacénalas para acceder a ellas mas tarde
Point position = driver.manage().window().getPosition();
int x1 = position.getX();
int y1 = position.getY();
  {{< /tab >}}
  {{< tab header="Python" >}}
# Accede a cada dimensión individualmente
x = driver.get_window_position().get('x')
y = driver.get_window_position().get('y')

# O almacénalas para acceder a ellas mas tarde
position = driver.get_window_position()
x1 = position.get('x')
y1 = position.get('y')
  {{< /tab >}}
  {{< tab header="CSharp" >}}
//Accede a cada dimensión individualmente
int x = driver.Manage().Window.Position.X;
int y = driver.Manage().Window.Position.Y;

//O almacénalas para acceder a ellas mas tarde
Point position = driver.Manage().Window.Position;
int x1 = position.X;
int y1 = position.Y;
  {{< /tab >}}
  {{< tab header="Ruby" >}}
#Accede a cada dimensión individualmente
x = driver.manage.window.position.x
y = driver.manage.window.position.y

# O almacénalas para acceder a ellas mas tarde
rect  = driver.manage.window.rect
x1 = rect.x
y1 = rect.y
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Accede a cada dimensión individualmente
const { x, y } = await driver.manage().window().getRect();

// O almacénalas para acceder a ellas mas tarde
const rect = await driver.manage().window().getRect();
const x1 = rect.x;
const y1 = rect.y;
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
// Accede a cada dimensión individualmente
val x = driver.manage().window().position.x
val y = driver.manage().window().position.y

// O almacénalas para acceder a ellas mas tarde
val position = driver.manage().window().position
val x1 = position.x
val y1 = position.y

  {{< /tab >}}
{{< /tabpane >}}

## Fija la posición de la ventana

Mueve la ventana a la posición deseada.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
// Mueve la ventana arriba izquierda del monitor principal
driver.manage().window().setPosition(new Point(0, 0));
  {{< /tab >}}
  {{< tab header="Python" >}}
# Mueve la ventana arriba izquierda del monitor principal
driver.set_window_position(0, 0)
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// Mueve la ventana arriba izquierda del monitor principal
driver.Manage().Window.Position = new Point(0, 0);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
driver.manage.window.move_to(0,0)
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Mueve la ventana arriba izquierda del monitor principal
await driver.manage().window().setRect({ x: 0, y: 0 });
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
// Mueve la ventana arriba izquierda del monitor principal
driver.manage().window().position = Point(0,0)
    {{< /tab >}}
{{< /tabpane >}}

### Maximizar la ventana
Maximiza la ventana. Para la gran mayoría de sistemas operativos, la ventana
rellenará la pantalla, sin bloquear los menús propios y barras de herramientas
de los sistemas operativos.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.manage().window().maximize();{{< /tab >}}
  {{< tab header="Python" >}}driver.maximize_window(){{< /tab >}}
  {{< tab header="CSharp" >}}driver.Manage().Window.Maximize();{{< /tab >}}
  {{< tab header="Ruby" >}}driver.manage.window.maximize{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.manage().window().maximize();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.manage().window().maximize(){{< /tab >}}
{{< /tabpane >}}

### Minimizar la ventana
Minimiza la ventana del actual contexto del navegador.
El comportamiento exacto de este comando es especifico individualmente de cada
gestor de ventanas.

Minimizar la ventana típicamente oculta la ventana en la bandeja del sistema.

__Nota: Esta funcionalidad es específica de Selenium 4 y versiones posteriores.__

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.manage().window().minimize();{{< /tab >}}
  {{< tab header="Python" >}}driver.minimize_window(){{< /tab >}}
  {{< tab header="CSharp" >}}driver.Manage().Window.Minimize();{{< /tab >}}
  {{< tab header="Ruby" >}}driver.manage.window.minimize{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.manage().window().minimize();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.manage().window().minimize(){{< /tab >}}
{{< /tabpane >}}

### Modo pantalla completa

Llena la pantalla completamente, similar a presionar la tecla F11 en la gran
mayoria de navegadores.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.manage().window().fullscreen();{{< /tab >}}
  {{< tab header="Python" >}}driver.fullscreen_window(){{< /tab >}}
  {{< tab header="CSharp" >}}driver.Manage().Window.FullScreen();{{< /tab >}}
  {{< tab header="Ruby" >}}driver.manage.window.full_screen{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.manage().window().fullscreen();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.manage().window().fullscreen(){{< /tab >}}
{{< /tabpane >}}

### TakeScreenshot

Used to capture screenshot for current browsing context.
The WebDriver endpoint [screenshot](https://www.w3.org/TR/webdriver/#dfn-take-screenshot)
returns screenshot which is encoded in Base64 format.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
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
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver

driver = webdriver.Chrome()

# Navigate to url
driver.get("http://www.example.com")

# Returns and base64 encoded string into image
driver.save_screenshot('./image.png')

driver.quit()

{{< /tab >}}
  {{< tab header="CSharp" >}}
    using OpenQA.Selenium;
    using OpenQA.Selenium.Chrome;
    using OpenQA.Selenium.Support.UI;

    var driver = new ChromeDriver();
    driver.Navigate().GoToUrl("http://www.example.com");
    Screenshot screenshot = (driver as ITakesScreenshot).GetScreenshot();
    screenshot.SaveAsFile("screenshot.png", ScreenshotImageFormat.Png); // Format values are Bmp, Gif, Jpeg, Png, Tiff
   {{< /tab >}}
  {{< tab header="Ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :chrome

begin
  driver.get 'https://example.com/'

  # Takes and Stores the screenshot in specified path
  driver.save_screenshot('./image.png')

end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
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
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
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
  {{< /tab >}}
{{< /tabpane >}}

###  TakeElementScreenshot

Used to capture screenshot of an element for current browsing context.
The WebDriver endpoint [screenshot](https://www.w3.org/TR/webdriver/#take-element-screenshot)
returns screenshot which is encoded in Base64 format.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
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
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver
from selenium.webdriver.common.by import By

driver = webdriver.Chrome()

# Navigate to url
driver.get("http://www.example.com")

ele = driver.find_element(By.CSS_SELECTOR, 'h1')

# Returns and base64 encoded string into image
ele.screenshot('./image.png')

driver.quit()
{{< /tab >}}
  {{< tab header="CSharp" >}}
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
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Works with Selenium4-alpha7 Ruby bindings and above
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :chrome

begin
  driver.get 'https://example.com/'
  ele = driver.find_element(:css, 'h1')

  # Takes and Stores the element screenshot in specified path
  ele.save_screenshot('./image.jpg')
end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
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
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
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
  {{< /tab >}}
{{< /tabpane >}}

### Execute Script

Executes JavaScript code snippet in the
current context of a selected frame or window.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
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
  {{< /tab >}}
  {{< tab header="Python" >}}
# Stores the header element
header = driver.find_element(By.CSS_SELECTOR, "h1")

# Executing JavaScript to capture innerText of header element
driver.execute_script('return arguments[0].innerText', header)
  {{< /tab >}}
  {{< tab header="CSharp" >}}
    //creating Chromedriver instance
	IWebDriver driver = new ChromeDriver();
	//Creating the JavascriptExecutor interface object by Type casting
	IJavaScriptExecutor js = (IJavaScriptExecutor) driver;
	//Button Element
	IWebElement button = driver.FindElement(By.Name("btnLogin"));
	//Executing JavaScript to click on element
	js.ExecuteScript("arguments[0].click();", button);
	//Get return value from script
	String text = (String)js.ExecuteScript("return arguments[0].innerText", button);
	//Executing JavaScript directly
	js.ExecuteScript("console.log('hello world')");
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Stores the header element
header = driver.find_element(css: 'h1')

# Get return value from script
result = driver.execute_script("return arguments[0].innerText", header)

# Executing JavaScript directly
driver.execute_script("alert('hello world')")
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Stores the header element
let header = await driver.findElement(By.css('h1'));

// Executing JavaScript to capture innerText of header element
let text = await driver.executeScript('return arguments[0].innerText', header);
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
// Stores the header element
val header = driver.findElement(By.cssSelector("h1"))

// Get return value from script
val result = driver.executeScript("return arguments[0].innerText", header)

// Executing JavaScript directly
driver.executeScript("alert('hello world')")
  {{< /tab >}}
{{< /tabpane >}}

### Print Page

Prints the current page within the browser

_Note: This requires Chromium Browsers to be in headless mode_


{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
   import org.openqa.selenium.print.PrintOptions;

    driver.get("https://www.selenium.dev");
    printer = (PrintsPage) driver;

    PrintOptions printOptions = new PrintOptions();
    printOptions.setPageRanges("1-2");

    Pdf pdf = printer.print(printOptions);
    String content = pdf.getContent();
  {{< /tab >}}
  {{< tab header="Python" >}}
    from selenium.webdriver.common.print_page_options import PrintOptions

    print_options = PrintOptions()
    print_options.page_ranges = ['1-2']

    driver.get("printPage.html")

    base64code = driver.print_page(print_options)
  {{< /tab >}}
  {{< tab header="CSharp" >}}
    // code sample not available please raise a PR
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    driver.navigate_to 'https://www.selenium.dev'

    base64encodedContent = driver.print_page(orientation: 'landscape')
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
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
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
    driver.get("https://www.selenium.dev")
    val printer = driver as PrintsPage

    val printOptions = PrintOptions()
    printOptions.setPageRanges("1-2")
    
    val pdf: Pdf = printer.print(printOptions)
    val content = pdf.content
  {{< /tab >}}
{{< /tabpane >}}
