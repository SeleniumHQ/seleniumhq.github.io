---
title: "Lenguaje de dominio específico"
weight: 2
---

Un lenguaje de dominio específico (DSL) es un sistema que 
proporciona al usuario un medio expresivo para resolver un 
problema. Permite a un usuario interactuar con el sistema en sus 
términos, no solo en jerga del programador. 

A tus usuarios, en general, no les importa cómo se ve su sitio. 
Ellos no se preocupan por la decoración, animaciones o gráficos. 
Ellos desean utilizar tu sistema para impulsar a sus nuevos 
empleados a través del proceso con mínima dificultad; quieren 
reservar un viaje a Alaska; quieren configurar y comprar 
unicornios con descuento. Tu trabajo como el probador debe 
acercarse lo más que pueda a "capturar" esta mentalidad. Con eso 
en mente, nos propusimos "modelar" la aplicación con que 
estas trabajando, de modo que los scripts de prueba (el único 
proxy pre-lanzamiento del usuario ) "hablen"  por, y representen al 
usuario. 

Con Selenium, el DSL generalmente se representa por métodos, 
escritos para hacer la API simple y legible: permiten un informe 
entre desarrolladores y partes interesadas (usuarios, dueños
de producto, especialistas en inteligencia de negocios, etc).

## Beneficios

* **Legible:** Las partes interesadas del negocio pueden entenderlo.
* **Escribible:** Fácil de escribir, evita duplicaciones innecesarias.
* **Extensible:** Se puede agregar funcionalidad (razonablemente) 
sin romper los contratos y la funcionalidad existente.
* **Mantenible:** Al dejar los detalles de implementación fuera de 
casos de prueba, está bien aislado contra cambios en el AUT*.


## Java

Aquí hay un ejemplo de un método DSL razonable en Java.
En aras de la brevedad, se supone que el objeto `driver` está predefinido
y disponible para el método.

```java
/**
 * Toma un nombre de usuario y una contraseña, completa los campos y hace clic en "iniciar sesión".
 * @return Una instancia de AccountPage
 */
public AccountPage loginAsUser(String username, String password) {
  WebElement loginField = driver.findElement(By.id("loginField"));
  loginField.clear();
  loginField.sendKeys(username);
  
  // Completa el campo de contraseña. El localizador que estamos usando es "By.id", y deberíamos
  // tenerlo definido en otra parte de la clase.
  WebElement passwordField = driver.findElement(By.id("password"));
  passwordField.clear();
  passwordField.sendKeys(password);

  // Haz clic en el botón de inicio de sesión, que tiene el id "enviar".
  driver.findElement(By.id("submit")).click();

  // Crea y devuelve una nueva instancia de AccountPage (a través del PageFactory
  // incorporado a Selenium).
  return PageFactory.newInstance(AccountPage.class);
}
```

Este método abstrae completamente los conceptos de campos de entrada,
botones, clics e incluso páginas de tu código de prueba. Usando este
enfoque, todo lo que un probador tiene que hacer es llamar a este método. 
Esto da una ventaja de mantenimiento: si los campos de inicio de sesión 
alguna vez cambian, solo tendrías que cambiar este método, no tus pruebas.

```java
public void loginTest() {
    loginAsUser("cbrown", "cl0wn3");

   // Ahora que hemos iniciado sesión, haz otras cosas, ya que utilizamos un DSL para admitir
   // nuestros probadore, es tan fácil como elegir entre los métodos disponibles.
    do.something();
    do.somethingElse();
    Assert.assertTrue("Something should have been done!", something.wasDone());

    // Ten en cuenta que todavía no nos hemos referido a un botón o control web en ninguna parte de este
    // script...
}
```

Vale la pena repetirlo: uno de tus objetivos principales debe ser escribir un
API que permite que tus pruebas aborden **el problema en cuestión, y NO
el problema de la interfaz de usuario**. La interfaz de usuario es una 
preocupación secundaria para tus usuarios: no les importa la interfaz de usuario, 
solo quieren obtener su trabajo hecho. 
Tus scripts de prueba deben leerse como una lista de cosas
el usuario quiere HACER, y las cosas que quiere SABER. Las pruebas
no deben preocuparse por CÓMO la IU requiere que vayas
al respecto.

***AUT**: Application under test

