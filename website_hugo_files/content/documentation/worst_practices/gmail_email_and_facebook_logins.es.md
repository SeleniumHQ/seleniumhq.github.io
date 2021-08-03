---
title: "Autenticarse con Gmail, email y Facebook"
linkTitle: "Gmail, email y Facebook"
weight: 4
---

Por múltiples razones, autenticarse en sitios como Gmail y Facebook usando el 
WebDriver no esta recomendado.
Aparte de estar en contra de los términos y condiciones de estos sitios (te 
expones a que te cierren la cuenta), es un proceso lento y poco fiable.

La practica ideal respecto a estos los sitios de emails es usar las APIs que 
ofrecen, en el caso de Facebook usar las herramientas para desarrolladores las 
cuales exponen un API para crear cuentas de prueba, amigos, etc.
A pesar de que usar un API puede parecer como un esfuerzo extra, lo recuperarás 
en velocidad, fiabilidad y estabilidad.
Ademas el API tiene muy pocas probabilidades de cambiar mientras que las paginas 
web y los elementos HTML cambian frecuentemente y requieren actualizar tu 
_framework_ de pruebas constantemente.

Autenticarse en sitios de terceros usando el WebDriver en cualquier punto de tus
tests incrementa el riesgo de que tus pruebas fallen debido a que aumenta la 
duración de estas.
Por regla general cuanto mas largos sean los tests mas frágiles y poco fiables 
son.

Las implementaciones del WebDriver que son 
[conformes al W3C](//w3c.github.io/webdriver/webdriver-spec.html)
también marcan el objecto `navigator` con la propiedad `WebDriver` para que los
ataques de denegación de servicio (_DoS_) puedan ser mitigados.