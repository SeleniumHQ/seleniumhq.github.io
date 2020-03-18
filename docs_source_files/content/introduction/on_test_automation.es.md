---
title: "Sobre automatización de pruebas"
weight: 2
---

Primero, pregúntate si realmente necesitas o no usar un navegador. Lo 
más probable es que, en algún momento, si estás trabajando en una 
aplicación web compleja, necesitarás abrir un navegador y realmente 
probarlo. 

Sin embargo, las pruebas funcionales de usuario final, como las 
pruebas de Selenium son caras de ejecutar. Además, normalmente 
requieren que una infraestructura considerable este disponible para 
estas ejecutarse de manera efectiva. Es una buena regla preguntarse siempre 
si lo que se quiere probar se puede hacer usando enfoques de prueba 
más livianos como las pruebas unitarias o con un enfoque de bajo 
nivel. 

Una vez que hayas tomado la determinación de que estás en el negocio 
de hacer pruebas en el navegador, y que tengas tu ambiente de 
Selenium listo para empezar a escribir pruebas, generalmente 
realizaras alguna combinación de estos tres pasos:

* Preparar los datos
* Realizar un conjunto discreto de acciones
* Evaluar los resultados

Querrás mantener estos pasos tan cortos como sea posible; una o dos 
operaciones deberían ser suficientes la mayor parte del tiempo. La 
automatización del navegador tiene la reputación de ser "frágil", 
pero en realidad, esto se debe a que los usuarios suelen exigirle 
demasiado. En capítulos posteriores, volveremos a las técnicas que 
puedes utilizar para mitigar problemas aparentemente intermitentes en 
las pruebas, en particular como
[superar race conditions]({{< ref "/webdriver/waits.es.md" >}}) entre el navegador y WebDriver.

Manteniendo tus pruebas cortas y usando el navegador web solo cuando
no tienes absolutamente ninguna alternativa,
Puedes tener muchas pruebas con fragilidades muy mínimas.

Una clara ventaja de las pruebas de Selenium es su capacidad 
inherente para probar todos los componentes de la aplicación, desde 
el backend hasta el frontend, desde la perspectiva del usuario. En 
otras palabras, aunque las pruebas funcionales pueden ser caras de 
ejecutar, también abarcan a la vez grandes porciones críticas para el 
negocio.


### Requisitos de pruebas

Como se mencionó anteriormente, las pruebas de Selenium pueden ser 
costosas de ejecutar. Hasta qué punto, dependerá del navegador en el 
que estás ejecutando las pruebas, pero históricamente el 
comportamiento de los navegadores ha variado tanto que a menudo ha 
sido un objetivo claro realizar pruebas cruzadas contra múltiples 
navegadores. 

Selenium te permite ejecutar las mismas instrucciones en múltiples 
navegadores en múltiples sistemas operativos, pero la enumeración de 
todos los navegadores posibles, sus diferentes versiones y los muchos 
sistemas operativos en los que se ejecutan se convertirá rápidamente 
en una misión no trivial.


### Iniciemos con un ejemplo

Larry ha escrito un sitio web que le permite a los usuarios ordenar 
unicornios personalizados. 

El flujo de trabajo principal (que llamaremos el "camino feliz") es 
algo como esto: 

* Crea una cuenta
* Configura el unicornio
* Agrégalo al carrito de compras
* Envialo a caja y realiza el pago
* Dar feedback sobre el unicornio

Sería tentador escribir un gran script de Selenium para realizar 
todas estas operaciones, muchos lo intentarán. **¡Resiste la 
tentación!** Hacerlo dará como resultado una prueba que a) toma mucho 
tiempo, b) estará sujeta a algunos problemas comunes relacionados con 
los problemas de tiempo de representación de la página, y c) es tal 
que si falla, no te dará un método conciso y "fácil de ver" para 
diagnosticar lo que salió mal.

La estrategia preferida para probar este escenario sería dividirlo en 
una serie de pruebas rápidas e independientes, cada una de las cuales 
tenga una "razón" para existir. 

Supongamos que deseas probar el segundo paso: Configurando tu 
unicornio. Realizará las siguientes acciones:

* Crea una cuenta
* Configura el unicornio

Ten en cuenta que nos estamos saltando el resto de los pasos, 
probaremos el resto del flujo en otros casos de prueba pequeños y 
discretos, después de que hayamos terminado con este. 

Para comenzar, debes crear una cuenta. Aquí tienes que tomar algunas 
decisiones:

* ¿Quieres usar una cuenta existente?
* ¿Quieres crear una nueva cuenta?
* ¿Hay alguna propiedad especial de dicho usuario que deba ser
  tomado en cuenta antes de que comience la configuración?

Independientemente de cómo respondas esta pregunta, la solución es 
hacer que forme parte del paso de "preparar los datos" de la prueba. Si 
Larry ha expuesto una API que te permite a ti (o cualquier persona) crear 
y actualizar cuentas de usuario, asegúrate de usar eso para responder 
esta pregunta. Si es posible, debes iniciar el navegador solo después 
de tener un usuario "en la mano", cuyas credenciales pueden iniciar 
sesión. 

Si cada prueba para cada flujo de trabajo comienza con la creación de 
una cuenta de usuario, se agregarán muchos segundos a la ejecución de 
cada prueba. Llamar a una API y hablar con una base de datos son 
operaciones "sin cabeza" rápidas, que no requieren el costoso proceso 
de abrir un navegador, navegar a las páginas correctas, hacer clic y 
esperar que se envíen los formularios, etc. 

Idealmente, puedes abordar esta fase de configuración en una sola 
línea de código, que se ejecutará antes de que se inicie cualquier 
navegador: 

{{< code-tab >}}
  {{< code-panel language="java" >}}
// Crea un usuario que tenga permisos de solo lectura--ellos pueden configurar un unicornio,
// pero no tienen configurada la información de pago, ni tienen
// privilegios administrativos. En el momento en que se crea el usuario, su correo electrónico
// la dirección y la contraseña se generan aleatoriamente--ni siquiera necesitas
// conocerlos.
User user = UserFactory.createCommonUser(); //Este método se define en otro lugar.

// Inicia sesión como este usuario.
// Iniciar sesión en este sitio te lleva a tu página personal "Mi cuenta", por lo que
// el método loginAs devuelve el objeto AccountPage, lo que le permite
// realizar acciones de AccountPage.
AccountPage accountPage = loginAs(user.getEmail(), user.getPassword());
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Crea un usuario que tenga permisos de solo lectura--ellos pueden configurar un unicornio,
# pero no tienen configurada la información de pago, ni tienen
# privilegios administrativos. En el momento en que se crea el usuario, su correo electrónico
# la dirección y la contraseña se generan aleatoriamente--ni siquiera necesitas
# conocerlos.
user = user_factory.create_common_user() #Este método se define en otro lugar.

# Inicia sesión como este usuario.
# Iniciar sesión en este sitio te lleva a tu página personal "Mi cuenta", por lo que
# el método loginAs devuelve el objeto AccountPage, lo que le permite
# realizar acciones de AccountPage
account_page = login_as(user.get_email(), user.get_password())
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Crea un usuario que tenga permisos de solo lectura--ellos pueden configurar un unicornio,
// pero no tienen configurada la información de pago, ni tienen
// privilegios administrativos. En el momento en que se crea el usuario, su correo electrónico
// la dirección y la contraseña se generan aleatoriamente--ni siquiera necesitas
// conocerlos.
User user = UserFactory.CreateCommonUser(); //Este método se define en otro lugar.

// Inicia sesión como este usuario.
// Iniciar sesión en este sitio te lleva a tu página personal "Mi cuenta", por lo que
// el método loginAs devuelve el objeto AccountPage, lo que le permite
// realizar acciones de AccountPage.
AccountPage accountPage = LoginAs(user.Email, user.Password);
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Crea un usuario que tenga permisos de solo lectura--ellos pueden configurar un unicornio,
# pero no tienen configurada la información de pago, ni tienen
# privilegios administrativos. En el momento en que se crea el usuario, su correo electrónico
# la dirección y la contraseña se generan aleatoriamente--ni siquiera necesitas
# conocerlos.
user = UserFactory.create_common_user #Este método se define en otro lugar.

# Inicia sesión como este usuario.
# Iniciar sesión en este sitio te lleva a tu página personal "Mi cuenta", por lo que
# el método loginAs devuelve el objeto AccountPage, lo que le permite
# realizar acciones de AccountPage
account_page = login_as(user.email, user.password)
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Crea un usuario que tenga permisos de solo lectura--ellos pueden configurar un unicornio,
// pero no tienen configurada la información de pago, ni tienen
// privilegios administrativos. En el momento en que se crea el usuario, su correo electrónico
// la dirección y la contraseña se generan aleatoriamente--ni siquiera necesitas
// conocerlos.
var user = userFactory.createCommonUser(); //Este método se define en otro lugar.

// Inicia sesión como este usuario.
// Iniciar sesión en este sitio te lleva a tu página personal "Mi cuenta", por lo que
// el método loginAs devuelve el objeto AccountPage, lo que le permite
// realizar acciones de AccountPage.
var accountPage = loginAs(user.email, user.password);
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Crea un usuario que tenga permisos de solo lectura--ellos pueden configurar un unicornio,
// pero no tienen configurada la información de pago, ni tienen
// privilegios administrativos. En el momento en que se crea el usuario, su correo electrónico
// la dirección y la contraseña se generan aleatoriamente--ni siquiera necesitas
// conocerlos.
val user = UserFactory.createCommonUser() //Este método se define en otro lugar.

// Inicia sesión como este usuario.
// Iniciar sesión en este sitio te lleva a tu página personal "Mi cuenta", por lo que
// el método loginAs devuelve el objeto AccountPage, lo que le permite
// realizar acciones de AccountPage.
val accountPage = loginAs(user.getEmail(), user.getPassword())
  {{< / code-panel >}}
{{< / code-tab >}}

Como puedes imaginar, la `UserFactory` puede ampliarse para 
proporcionar métodos como `createAdminUser()` y `createUserWithPayment()`. 
El punto es, que estas dos líneas de código 
no te distraigan del propósito final de esta prueba: configurar un 
unicornio. 

Las complejidades del 
[Modelo Page Object]({{< ref "/guidelines_and_recommendations/page_object_models.es.md" >}})
serán discutidas en capítulos posteriores, pero introduciremos el concepto aquí:

Tus pruebas deben estar compuestas de acciones, realizadas desde el 
punto de vista del usuario, dentro del contexto de las páginas en el 
sitio web. Estas páginas se almacenan como objetos, que contendrán 
información específica sobre cómo está compuesta la página web y cómo 
se realizan las acciones– muy poco de lo que cual debería preocuparte 
como probador. 

¿Qué tipo de unicornio quieres? Es posible que desees rosa, pero no 
necesariamente. El morado es muy popular últimamente. ¿Ella necesita 
gafas de sol? ¿Tatuajes de estrellas? Estas elecciones, aunque 
difíciles, son tu principal preocupación como probador: debes 
asegurarte de que tu centro de distribución de pedidos envíe el 
unicornio correcto a la persona correcta, y eso comienza con estas 
elecciones. 

Observa que en ninguna parte de este párrafo hablamos de botones, 
campos, menús desplegables, botones de opción o formularios web. 
**¡Tampoco deberían tus pruebas!** Debes escribir tu código como el 
usuario que intenta resolver su problema. Aquí hay una forma de 
hacerlo (continuando con el ejemplo anterior): 

{{< code-tab >}}
  {{< code-panel language="java" >}}
// El Unicornio es un Objeto de nivel superior--tiene atributos, que se establecen aquí.
// Esto solo almacena los valores; no llena ningún formulario web ni interactúa
// con el navegador de ninguna manera.
Unicorn sparkles = new Unicorn("Sparkles", UnicornColors.PURPLE, UnicornAccessories.SUNGLASSES, UnicornAdornments.STAR_TATTOOS);

// Dado que ya estamos "en" la página de la cuenta, tenemos que usarla para acceder al
// lugar especifico donde configuras unicornios. Llamar al método "Agregar unicornio"
// nos llevará allí.
AddUnicornPage addUnicornPage = accountPage.addUnicorn();

// Ahora que estamos en la página AddUnicornPage, pasaremos el objeto "sparkles" a
// su método createUnicorn(). Este método tomará los atributos de Sparkles,
// completara el formulario y hará clic en enviar.
UnicornConfirmationPage unicornConfirmationPage = addUnicornPage.createUnicorn(sparkles);
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# El Unicornio es un Objeto de nivel superior--tiene atributos, que se establecen aquí.
# Esto solo almacena los valores; no llena ningún formulario web ni interactúa
# con el navegador de ninguna manera.
sparkles = Unicorn("Sparkles", UnicornColors.PURPLE, UnicornAccessories.SUNGLASSES, UnicornAdornments.STAR_TATTOOS)

# Dado que ya estamos "en" la página de la cuenta, tenemos que usarla para acceder al
# lugar especifico donde configuras unicornios. Llamar al método "Agregar unicornio"
# nos llevará allí.
add_unicorn_page = account_page.add_unicorn()

# Ahora que estamos en la página AddUnicornPage, pasaremos el objeto "sparkles" a
# su método createUnicorn(). Este método tomará los atributos de Sparkles
# completara el formulario y hará clic en enviar.
unicorn_confirmation_page = add_unicorn_page.create_unicorn(sparkles)
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// El Unicornio es un Objeto de nivel superior--tiene atributos, que se establecen aquí.
// Esto solo almacena los valores; no llena ningún formulario web ni interactúa
// con el navegador de ninguna manera.
Unicorn sparkles = new Unicorn("Sparkles", UnicornColors.Purple, UnicornAccessories.Sunglasses, UnicornAdornments.StarTattoos);

// Dado que ya estamos "en" la página de la cuenta, tenemos que usarla para acceder al
// lugar especifico donde configuras unicornios. Llamar al método "Agregar unicornio"
// nos llevará allí.
AddUnicornPage addUnicornPage = accountPage.AddUnicorn();

// Ahora que estamos en la página AddUnicornPage, pasaremos el objeto "sparkles" a
// su método createUnicorn(). Este método tomará los atributos de Sparkles,
// completara el formulario y hará clic en enviar.
UnicornConfirmationPage unicornConfirmationPage = addUnicornPage.CreateUnicorn(sparkles);
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# El Unicornio es un Objeto de nivel superior--tiene atributos, que se establecen aquí.
# Esto solo almacena los valores; no llena ningún formulario web ni interactúa
# con el navegador de ninguna manera.
sparkles = Unicorn("Sparkles", Unicor
sparkles = Unicorn.new('Sparkles', UnicornColors.PURPLE, UnicornAccessories.SUNGLASSES, UnicornAdornments.STAR_TATTOOS)

# Dado que ya estamos "en" la página de la cuenta, tenemos que usarla para acceder al
# lugar especifico donde configuras unicornios. Llamar al método "Agregar unicornio"
# nos llevará allí.
add_unicorn_page = account_page.add_unicorn

# Ahora que estamos en la página AddUnicornPage, pasaremos el objeto "sparkles" a
# su método createUnicorn(). Este método tomará los atributos de Sparkles
# completara el formulario y hará clic en enviar.
unicorn_confirmation_page = add_unicorn_page.create_unicorn(sparkles)
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// El Unicornio es un Objeto de nivel superior--tiene atributos, que se establecen aquí.
// Esto solo almacena los valores; no llena ningún formulario web ni interactúa
// con el navegador de ninguna manera.
var sparkles = new Unicorn("Sparkles", UnicornColors.PURPLE, UnicornAccessories.SUNGLASSES, UnicornAdornments.STAR_TATTOOS);

// Dado que ya estamos "en" la página de la cuenta, tenemos que usarla para acceder al
// lugar especifico donde configuras unicornios. Llamar al método "Agregar unicornio"
// nos llevará allí.
var addUnicornPage = accountPage.addUnicorn();

// Ahora que estamos en la página AddUnicornPage, pasaremos el objeto "sparkles" a
// su método createUnicorn(). Este método tomará los atributos de Sparkles,
// completara el formulario y hará clic en enviar..
var unicornConfirmationPage = addUnicornPage.createUnicorn(sparkles);

  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// El Unicornio es un Objeto de nivel superior--tiene atributos, que se establecen aquí.
// Esto solo almacena los valores; no llena ningún formulario web ni interactúa
// con el navegador de ninguna manera.
val sparkles = Unicorn("Sparkles", UnicornColors.PURPLE, UnicornAccessories.SUNGLASSES, UnicornAdornments.STAR_TATTOOS)

// Dado que ya estamos "en" la página de la cuenta, tenemos que usarla para acceder al
// lugar especifico donde configuras unicornios. Llamar al método "Agregar unicornio"
// nos llevará allí..
val addUnicornPage = accountPage.addUnicorn()

// Ahora que estamos en la página AddUnicornPage, pasaremos el objeto "sparkles" a
// su método createUnicorn(). Este método tomará los atributos de Sparkles,
// completara el formulario y hará clic en enviar.
unicornConfirmationPage = addUnicornPage.createUnicorn(sparkles)

  {{< / code-panel >}}
{{< / code-tab >}}

Ahora que has configurado tu unicornio,
debes avanzar al paso 3: asegurarte de que realmente funcionó.

{{< code-tab >}}
  {{< code-panel language="java" >}}
// El método exist() de UnicornConfirmationPage tomará el objeto
// Sparkles--una especificación de los atributos que deseas ver, y los va a comparar
// con los campos en la página.
Assert.assertTrue("Sparkles debe haberse creado, con todos los atributos intactos", unicornConfirmationPage.exists(sparkles));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# El método exist() de UnicornConfirmationPage tomará el objeto
# Sparkles--una especificación de los atributos que deseas ver, y los va a comparar
# con los campos en la página.
assert unicorn_confirmation_page.exists(sparkles), "Sparkles debe haberse creado, con todos los atributos intactos"
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// El método exist() de UnicornConfirmationPage tomará el objeto
// Sparkles--una especificación de los atributos que deseas ver, y los va a comparar
// con los campos en la página.
Assert.True(unicornConfirmationPage.Exists(sparkles), "Sparkles debe haberse creado, con todos los atributos intactos");
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# El método exist() de UnicornConfirmationPage tomará el objeto
# Sparkles--una especificación de los atributos que deseas ver, y los va a comparar
# con los campos en la página.
expect(unicorn_confirmation_page.exists?(sparkles)).to be, 'Sparkles debe haberse creado, con todos los atributos intactos'
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// El método exist() de UnicornConfirmationPage tomará el objeto
// Sparkles--una especificación de los atributos que deseas ver, y los va a comparar
// con los campos en la página.
assert(unicornConfirmationPage.exists(sparkles), "Sparkles debe haberse creado, con todos los atributos intactos");

  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// El método exist() de UnicornConfirmationPage tomará el objeto
// Sparkles--una especificación de los atributos que deseas ver, y los va a comparar
// con los campos en la página.
assertTrue("Sparkles debe haberse creado, con todos los atributos intactos", unicornConfirmationPage.exists(sparkles))
  {{< / code-panel >}}
{{< / code-tab >}}

Ten en cuenta que el probador aún no ha hecho nada más que hablar de 
unicornios en este código-- nada de botones, nada de localizadores, 
nada de controles del navegador. Este método de _modelar_ la 
aplicación te permite mantener estos comandos de nivel-de-prueba en 
su lugar y sin cambios, incluso si Larry decide la próxima semana que 
ya no le gusta Ruby-on-Rails y decide volver a implementar todo el 
sitio en las últimas ataduras de Haskell con un front-end de Fortran. 

Los objetos de tu página requerirán un pequeño mantenimiento para 
poder cumplir con el rediseño del sitio, pero estas pruebas seguirán 
siendo iguales. Tomando este diseño básico, querrás continuar con tus 
flujos de trabajo con la menor cantidad posible de pasos orientados 
al navegador. Tu próximo flujo de trabajo implicará agregar un 
unicornio al carrito de compras. Probablemente querrás muchas 
iteraciones de esta prueba para asegurarte de que el carrito mantiene 
su estado correctamente: ¿Hay más de un unicornio en el carrito antes 
de comenzar? ¿Cuántos pueden caber en el carrito de compras? Si crea 
más de uno con el mismo nombre y/o características, ¿se romperá? 
¿Conservará solo el existente o agregará otro? 

Cada vez que te mueves por el flujo de trabajo, deseas evitar tener 
que crear una cuenta, iniciar sesión como usuario y configurar el 
unicornio. Idealmente, podrás crear una cuenta y preconfigurar un 
unicornio a través de la API o de la base de datos. Luego, todo lo 
que tienes que hacer es iniciar sesión como usuario, ubicar Sparkles, 
y agrégarla al carrito. 


### ¿Automatizar o no automatizar?

Es la automatización siempre ventajosa? Cuando uno debería decidir 
automatizar casos de prueba? 

No siempre es ventajoso automatizar casos de prueba. Hay veces que 
las pruebas manuales pueden ser mas apropiadas. Por ejemplo, si la 
interface de usuario de la aplicación va a cambiar considerablemente 
en el futuro, entonces no habria que reescribir ninguna 
automatizacion. Tambien, algunas veces simplemente no hay tiempo 
suficiente para construir la automatización de las pruebas. A corto 
plazo, las pruebas manuales pueden ser mas efectivas. Si una 
aplicación tiene una fecha limite muy ajustada, actualmente no hay 
ningún tipo de automatización de pruebas, y es imperativo que las 
pruebas se realicen dentro de la fecha limite, entonces las pruebas es la 
mejor solución.