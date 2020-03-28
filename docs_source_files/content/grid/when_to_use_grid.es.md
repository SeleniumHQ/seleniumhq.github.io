---
title: "Cuando usar el Grid"
weight: 4
---

Generalmente hablando, hay dos razones por las cuales podrías querer usar el Grid.


* Para ejecutar tus tests contra múltiples navegadores, múltiples versiones de
navegadores y múltiples navegadores bajo diferentes sistemas operativos.
* Para reducir el tiempo que tarda en completarse la ejecución de tu suite de test.

El Grid se usa para acelerar la ejecución de los test usando múltiples maquinas
para ejecutarlos en paralelo. Por ejemplo, si tienes una suite con 100 
tests, pero configuras el Grid para soportar cuatro maquinas diferentes (ya sean 
maquinas virtuales o maquinas separadas físicamente) para ejecutar los tests, tu
suite completará la ejecución en aproximadamente una cuarta parte del tiempo que
habría tardado si se hubiesen ejecutado de manera secuencial en una sola maquina.
Para suites de test grandes y suites de larga duración, como aquellas que realizan
gran cantidad de validaciones de datos, puede suponer un gran ahorro de tiempo.
Algunas suites de test pueden llegar tardar horas en ejecutarse. Otra razón puede
ser acortar el tiempo de espera para recibir los resultados de los tests cuando los 
desarrolladores suben el código de su aplicación a los entornos de pruebas . Cada 
vez mas equipos de software practican metodologías de desarrollo software Agile
 en las cuales es necesario tener feedback tan rápido como sea posible en lugar 
de esperar durante toda la noche para que los tests finalicen su ejecución.

El Grid también es usado para soportar múltiples ejecuciones de test contra
múltiples entornos, especialmente, contra diferentes navegadores al mismo tiempo.
Por ejemplo, un Grid de maquinas virtuales puede ser configurado con cada una
soportando un navegador diferente, estos navegadores pueden ser aquellos que
la aplicación deba soportar. Así, la maquina uno tiene Internet Explorer 8, la 
maquina dos Internet Explorer 9, la maquina tres la ultima versión de Chrome
y la maquina cuatro la ultima versión de Firefox. Cuando la suite de test es
ejecutada, el Grid de Selenium recibe cada combinación de test-navegador y 
asigna la ejecución al navegador requerido.

Adicionalmente, uno puede tener en el Grid los mismos navegadores, tipos y 
versiones. Por ejemplo podría tener en el Grid cuatro maquinas cada una ejecutando
tres instancias de Firefox 70, permitiendo así tener una granja de servidores 
(en cierto sentido) de instancias de Firefox. De esta manera cuando se ejecute 
la suite, cada test es pasado al Grid el cual lo asignará a la siguiente instancia
de Firefox disponible. De esta forma se obtendrían los resultados en los cuales 
doce tests se habrán estado ejecutando todos a mismo tiempo en paralelo, 
reduciendo así significativamente el tiempo que se requiere en completar la 
ejecución de la suite.

El Grid es muy flexible. Estos dos ejemplos pueden ser combinados para permitir
múltiples instancias de cada tipo de navegador y versión. Configuraciones de este
tipo permiten proveer una paralelización para completar las ejecuciones mas
rápidamente y soportar múltiples tipos de navegadores y versiones simultáneamente.
