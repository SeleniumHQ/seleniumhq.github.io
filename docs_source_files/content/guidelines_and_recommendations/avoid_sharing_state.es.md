---
title: "Evitar compartir estados"
weight: 6
---

Aunque se menciona en varios lugares, vale la pena mencionarlo 
nuevamente. Asegurate de que las pruebas estén aisladas unas de otras. 

* No compartas datos de prueba. Imagina varias pruebas en la que cada una
consulta a la base de datos para pedidos válidos antes de elegir 
uno para realizar una acción. Si dos pruebas eligen el mismo 
pedido es probable que obtengas un comportamiento 
inesperado. 

* Limpia los datos obsoletos en la aplicación que podrían ser 
recogidos por otra prueba p. ej. registros de pedidos inválidos. 

* Crear una nueva instancia de WebDriver por prueba. Esto ayuda 
a garantizar el aislamiento de la prueba y simplifica la 
paralelización.
