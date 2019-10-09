---
title: "Sobre automatización de pruebas"
weight: 2
---


Antes que nada, pregúntese si realmente necesita o no usar un navegador. Las probabilidades son buenas de que, en algún momento, si está trabajando en una aplicación web compleja, necesitará abrir un navegador y probarla.

Sin embargo, las pruebas funcionales de usuario final, como las pruebas de Selenium, son caras de ejecutar. Además, por lo general requieren una infraestructura sustancial para poder funcionar de manera efectiva. Es una buena regla preguntarse siempre si lo que desea probar se puede hacer utilizando enfoques de prueba más livianos, como pruebas unitarias o con un enfoque de nivel inferior.

Una vez que haya tomado la determinación de que está en el negocio de las pruebas con un navegador web, y tenga su entorno Selenium listo para comenzar a escribir las pruebas, generalmente realizará una combinación de tres pasos:

* Configurar los datos
* Realizar un conjunto discreto de acciones
* Evaluar los resultados

Deberá mantener estos pasos lo más cortos posible; una o dos operaciones deberían ser suficientes la mayor parte del tiempo. La automatización del navegador tiene la reputación de ser "inestable", pero en realidad eso se debe a que los usuarios suelen exigir demasiado. En capítulos posteriores, volveremos a las técnicas que puede usar para mitigar aparentes problemas intermitentes en las pruebas, en particular sobre cómo [superar las condiciones de carrera]({{<ref "/webdriver/waits.es.md">}}) entre el navegador y WebDriver.

Al mantener sus pruebas cortas y usar el navegador web solo cuando no tiene absolutamente ninguna alternativa, puede realizar muchas pruebas con un minimo desgaste.

Una ventaja distintiva de las pruebas de Selenium es su capacidad inherente para probar todos los componentes de la aplicación, desde el backend hasta el frontend, desde la perspectiva del usuario. En otras palabras, si bien las pruebas funcionales pueden ser costosas de ejecutar, también abarcan grandes porciones críticas para el negocio al mismo tiempo.


### Requisitos de prueba

Como se mencionó anteriormente, las pruebas de Selenium pueden ser costosas de ejecutar. Incluso depende del navegador con el que esté ejecutando las pruebas, pero históricamente el comportamiento de los navegadores ha variado tanto que a menudo ha sido un objetivo declarado realizar pruebas con múltiples navegadores (_cross browser_).

Selenium le permite ejecutar las mismas instrucciones en múltiples navegadores y en múltiples sistemas operativos, pero la enumeración de todos los navegadores posibles, sus diferentes versiones y los muchos sistemas operativos en los que se ejecutan se le convertirá rápidamente en una tarea compleja.


### Comencemos con un ejemplo

Larry ha escrito un sitio web que permite a los usuarios ordenar sus propios unicornios personalizados.

El flujo de trabajo general (lo que llamaremos el "camino feliz") es algo como esto:

* Crea una cuenta
* Configurar tu unicornio
* Agrégalo al carrito de compras
* Echa un vistazo y paga
* Dar comentarios sobre tu unicornio


Sería tentador escribir un gran script Selenium para realizar todas estas operaciones, muchos lo intentarán.
**¡Resista la tentación!**
Hacerlo dará como resultado una prueba que
a) lleva mucho tiempo,
b) estará sujeto a algunos problemas comunes relacionados con los problemas de tiempo de renderizado de la página, y
c) es tal que si falla, no le dará un método conciso y "fácil de ver" para diagnosticar lo que salió mal.

La estrategia preferida para probar este escenario sería dividirlo en una serie de pruebas rápidas e independientes, cada una de las cuales tiene una "razón" para existir.

Supongamos que quieres probar el segundo paso:
Configurando tu unicornio.
Deberá realizar las siguientes acciones:

* Crea una cuenta
* Configurar un unicornio

Tenga en cuenta que estamos omitiendo el resto de estos pasos, probaremos el resto del flujo de trabajo en otros casos de prueba pequeños y discretos, una vez que hayamos terminado con este.

Para comenzar, debe crear una cuenta. Aquí tienes algunas opciones a resolver:

* ¿Quieres usar una cuenta existente?
* ¿Quieres crear una nueva cuenta?
* ¿Hay alguna propiedad especial de dicho usuario que deba tenerse en cuenta antes de que comience la configuración?

Independientemente de cómo responda estas preguntas, la solución es hacer que forme parte del flujo de "configurar los datos" de la prueba –si Larry ha expuesto una API que le permite a usted (o cualquier persona) crear y actualizar cuentas de usuario, asegúrese de usarla para responder esta situación– si es posible, lo deseable es iniciar el navegador solo después de tener un usuario disponible, cuyas credenciales le permitan iniciar sesión.

Si cada prueba para cada flujo de trabajo comienza con la creación de una cuenta de usuario, se agregarán muchos segundos a la ejecución de cada prueba. Llamar a una API y hablar con una base de datos son operaciones rápidas y sin interfaz gráfica (_headless_) que no requieren el costoso proceso de abrir un navegador, navegar a las páginas correctas, hacer clic y esperar a que se envíen los formularios, etc.

Idealmente, puede abordar esta fase de configuración en una línea de código, que se ejecutará antes de que se inicie cualquier navegador:



{{< code-tab >}}
  {{< code-panel language="java" >}}
// Cree un usuario que tenga permisos de solo lectura: puede configurar un unicornio,
// pero no tienen configurada la información de pago, ni tienen
// privilegios administrativos. En el momento en que se cree el usuario, su correo electrónico
// la dirección y la contraseña se generan aleatoriamente; ni siquiera necesita
// conocerlos.
User user = UserFactory.createCommonUser(); // Este método se define en otra parte.

// Inicie sesión como este usuario.
// Iniciar sesión en este sitio lo lleva a su página personal "Mi cuenta", por lo que
// El método loginAs devuelve el objeto AccountPage, lo que le permite
// realiza acciones desde AccountPage.
AccountPage accountPage = loginAs(user.getEmail(), user.getPassword());
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Cree un usuario que tenga permisos de solo lectura: puede configurar un unicornio,
# pero no tienen configurada la información de pago, ni tienen
# Privilegios administrativos. En el momento en que se crea el usuario, su correo electrónico
# la dirección y la contraseña se generan aleatoriamente; ni siquiera necesita
# conocerlos.
user = user_factory.create_common_user() #Este método se define en otra parte.

# Inicie sesión como este usuario.
# Iniciar sesión en este sitio lo lleva a su página personal "Mi cuenta", por lo que
# El objeto loginAs devuelve el objeto AccountPage, lo que le permite
# realizar acciones desde la página de cuenta.
account_page = login_as(user.get_email(), user.get_password())
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Todavía no tenemos una muestra de código C# - ayúdenos y genere un PR (pull request)
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Todavía no tenemos una muestra de código en Ruby - ayúdenos y genere un PR (pull request)
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Todavía no tenemos una muestra de código JavaScript - ayúdenos y genere un PR (pull request)
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Create a user who has read-only permissions--they can configure a unicorn,
// but they do not have payment information set up, nor do they have
// administrative privileges. At the time the user is created, its email
// address and password are randomly generated--you don't even need to
// know them.
val user = UserFactory.createCommonUser() //This method is defined elsewhere.

// Log in as this user.
// Logging in on this site takes you to your personal "My Account" page, so the
// AccountPage object is returned by the loginAs method, allowing you to then
// perform actions from the AccountPage.
val accountPage = loginAs(user.getEmail(), user.getPassword())
  {{< / code-panel >}}
{{< / code-tab >}}

Como se puede imaginar, la `UserFactory` se puede ampliar para proporcionar métodos como `createAdminUser()` y `createUserWithPayment()`. El punto es que estas dos líneas de código no le distraigan del objetivo final de esta prueba: configurar un unicornio.

Las complejidades del [Modelo de objeto de página (_Page Object Model_)]({{<ref "/guidelines_and_recommendations/page_object_models.es.md">}}) se analizarán en capítulos posteriores, pero presentaremos el concepto aquí:

Sus pruebas deben estar compuestas de acciones, realizadas desde el punto de vista del usuario, dentro del contexto de las páginas del sitio. Estas páginas se almacenan como objetos, que contendrán información específica sobre cómo se compone la página web y cómo se realizan las acciones, muy poco de lo que debería preocuparte como _tester_.

¿Qué tipo de unicornio quieres? Es posible que desee rosa, pero no necesariamente. El morado ha sido muy popular últimamente. ¿El necesita gafas de sol? ¿Tatuajes de estrellas? Estas elecciones, si bien son difíciles, son su principal preocupación como probador: debe asegurarse de que su centro de cumplimiento de pedidos envíe el unicornio correcto a la persona adecuada, y eso comienza con estas elecciones.

Observe que en ninguna parte de ese párrafo hablamos de botones, campos, menús desplegables, botones de opción o formularios web.
**¡Tampoco debería hacerlo sus pruebas!**
Lo deseable es escribir el código como el usuario que intenta resolver su problema. Aquí hay una forma de hacerlo (continuando con el ejemplo anterior):

{{< code-tab >}}
  {{< code-panel language="java" >}}
// El Unicornio es un Objeto de nivel superior: tiene atributos, que se establecen aquí.
// Esto solo almacena los valores; no llena ningún formulario web ni interactúa
// con el navegador de cualquier manera.
Unicorn sparkles = new Unicorn("Sparkles", UnicornColors.PURPLE, UnicornAccessories.SUNGLASSES, UnicornAdornments.STAR_TATTOOS);

// Como ya estamos "en" la página de la cuenta, tenemos que usarla para acceder al
// lugar real donde configuras unicornios. Llamando al método "addUnicorn"
// nos lleva allí.
AddUnicornPage addUnicornPage = accountPage.addUnicorn();

// Ahora que estamos en AddUnicornPage, pasaremos el objeto "sparkles"
// al método createUnicorn(). Este método tomará los atributos de Sparkles,
// llena el formulario y hace clic en el botón enviar.
UnicornConfirmationPage unicornConfirmationPage = addUnicornPage.createUnicorn(sparkles);
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# El Unicornio es un Objeto de nivel superior: tiene atributos, que se establecen aquí.
# Esto solo almacena los valores; no llena ningún formulario web ni interactúa
# con el navegador de cualquier manera.
sparkles = Unicorn("Sparkles", UnicornColors.PURPLE, UnicornAccessories.SUNGLASSES, UnicornAdornments.STAR_TATTOOS)

# Como ya estamos "en" la página de la cuenta, tenemos que usarla para acceder a
# lugar real donde se configuran los unicornios. Llamando al método "addUnicorn"
# nos lleva allí.
add_unicorn_page = account_page.add_unicorn()

# Ahora que estamos en AddUnicornPage, pasaremos el objeto "sparkles" a
# su método createUnicorn(). Este método tomará los atributos de Sparkles,
# completará el formulario y haga clic en enviar.
unicorn_confirmation_page = add_unicorn_page.create_unicorn(sparkles)
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Todavía no tenemos una muestra de código en C# - ayúdanos y genera un PR (pull request)
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Todavía no tenemos una muestra de código en Ruby - ayúdanos y genera un PR (pull request)
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Todavía no tenemos una muestra de código en JavaScript - ayúdanos y genera un PR (pull request)
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// The Unicorn is a top-level Object--it has attributes, which are set here. 
// This only stores the values; it does not fill out any web forms or interact
// with the browser in any way.
val sparkles = Unicorn("Sparkles", UnicornColors.PURPLE, UnicornAccessories.SUNGLASSES, UnicornAdornments.STAR_TATTOOS)

// Since we are already "on" the account page, we have to use it to get to the
// actual place where you configure unicorns. Calling the "Add Unicorn" method
// takes us there.
val addUnicornPage = accountPage.addUnicorn()

// Now that we're on the AddUnicornPage, we will pass the "sparkles" object to
// its createUnicorn() method. This method will take Sparkles' attributes,
// fill out the form, and click submit.
unicornConfirmationPage = addUnicornPage.createUnicorn(sparkles)

  {{< / code-panel >}}
{{< / code-tab >}}

Ahora que ha configurado su unicornio, debe continuar al paso 3: asegurarse de que realmente funcionó.

{{< code-tab >}}
  {{< code-panel language="java" >}}
// El método exist() de UnicornConfirmationPage tomará Sparkles
// objeto: una especificación de los atributos que desea ver y compararlos
// con los campos en la página.
Assert.assertTrue("Deben haberse creado Sparkles, con todos los atributos intactos.", unicornConfirmationPage.exists(sparkles));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# El método exist() de UnicornConfirmationPage tomará Sparkles
# objeto: una especificación de los atributos que desea ver y compararlos
# con los campos en la página.
assert unicorn_confirmation_page.exists(sparkles), "Sparkles should have been created, with all attributes intact"
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Todavía no tenemos una muestra de código en Ruby - ayúdanos y genera un PR (pull request)
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Todavía no tenemos una muestra de código en JavaScript - ayúdanos y genera un PR (pull request)
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// The exists() method from UnicornConfirmationPage will take the Sparkles 
// object--a specification of the attributes you want to see, and compare
// them with the fields on the page.
//CHECK Boris
assertTrue("Sparkles should have been created, with all attributes intact", unicornConfirmationPage.exists(sparkles))
  {{< / code-panel >}}
{{< / code-tab >}}

Tenga en cuenta que el _tester_ aún no ha hecho nada más que hablar de unicornios en este código, sin botones, sin localizadores, sin controles del navegador.
Este método de "modelado" de la aplicación le permite mantener estos comandos de nivel de prueba en su lugar y sin cambios, incluso si Larry decide la próxima semana que ya no le gusta Ruby-on-Rails y decide volver a implementar todo el sitio con las librerías más recientes de Haskell y con un front-end en Fortran.

Los objetos de su página requerirán un pequeño mantenimiento para cumplir con el rediseño del sitio, pero estas pruebas seguirán siendo las mismas. Tomando este diseño básico, querrá continuar con sus flujos de trabajo con la menor cantidad posible de pasos orientados hacia el navegador. Su próximo flujo de trabajo implicará agregar un unicornio al carrito de compras. Probablemente requeriá muchas iteraciones de esta prueba para asegurarse de que el carrito mantenga su estado correctamente:
¿Hay más de un unicornio en el carrito antes de comenzar?
¿Cuántos pueden caber en el carrito de compras?
Si crea más de uno con el mismo nombre o características, ¿se romperá? ¿Conservará solo el existente o agregará otro?

Cada vez que se mueva por el flujo de trabajo, debe intentar evitar tener que crear una cuenta, iniciar sesión como usuario y configurar el unicornio. Idealmente, podrá crear una cuenta y preconfigurar un unicornio a través de la API o la base de datos. Luego, todo lo que tiene que hacer es iniciar sesión como usuario, localizar Sparkles y agregarla al carrito.

