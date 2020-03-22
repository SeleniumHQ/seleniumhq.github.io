---
title: "Clases de apoyo"
weight: 5
---

Las clases de soporte de WebDriver son proporcionadas para 
simplificar el mantenimiento de tu código. Proporcionan una buena 
abstracción para modelar  mas fácilmente elementos HTML como objetos 
de dominio, también proporcionando métodos de ayuda para hacer que el 
uso de tales objetos sea fácil de razonar. Aprenderemos acerca de: 

* Estrategias de localizacion
* Eventos
* LoadableComponent
* ThreadGuard
* etc.

Iniciemos:


## **ThreadGuard**
{{% notice info %}}
Esta clase solo esta disponible en la librería de enlace de Java
{{% /notice %}}
ThreadGuard comprueba que se llama a un controlador solo desde el 
mismo hilo que lo creó. Los problemas de subprocesos, especialmente 
cuando se ejecutan pruebas en paralelo, pueden tener errores misteriosos y 
difícil de diagnosticar. El uso de este contenedor evita esta 
categoría de errores y generará una excepción cuando ocurran.

El siguiente ejemplo simula un choque de hilos:

```java
public class DriverClash {
  //El hilo (thread) main (id 1) creó este controlador
  private WebDriver protectedDriver = ThreadGuard.protect(new ChromeDriver());

  static {
    System.setProperty("webdriver.chrome.driver", "<Set path to your Chromedriver>");
  }

  //Thread-1 (id 24) llama al mismo controlador causando el choque
  Runnable r1 = () -> {protectedDriver.get("https://selenium.dev");};
  Thread thr1 = new Thread(r1);

  void runThreads(){
    thr1.start();
  }

  public static void main(String[] args) {
    new DriverClash().runThreads();
  }
}
```

El resultado se muestra a continuación:
```text
Exception in thread "Thread-1" org.openqa.selenium.WebDriverException:
Thread safety error; this instance of WebDriver was constructed
on thread main (id 1)and is being accessed by thread Thread-1 (id 24)
This is not permitted and *will* cause undefined behaviour

```
Como puede verse en el ejemplo:

 * `protectedDriver` Será creado en el hilo Main.
 * Utilizamos Java `Runnable` para iniciar un nuevo proceso y un nuevo `Thread` para ejecutar el proceso.
 * Ambos `Thread` chocarán porque el Thread principal no tiene `protectedDriver` en su memoria.
 * `ThreadGuard.protect` lanzará una excepción.
 
#### Nota:

Esto no reemplaza la necesidad de usar `ThreadLocal` para administrar los controladores cuando se ejecutan en paralelo.

