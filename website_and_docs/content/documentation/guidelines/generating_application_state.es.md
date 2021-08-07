---
title: "Generar el estado de la aplicación"
linkTitle: "Generar el estado de la aplicación"
weight: 5
---

Selenium no debe usarse para preparar un caso de prueba. Todas 
las acciones repetitivas y los preparativos para un caso de 
prueba deben realizarse a través de otros métodos. Por ejemplo, 
la mayoría de las IU web tienen autenticación (por ejemplo, un 
formulario de inicio de sesión). Eliminar el inicio de sesión a través 
del navegador web antes de cada prueba mejora tanto la 
velocidad como la estabilidad de la prueba. Un método debe ser 
creado para obtener acceso al AUT* (por ejemplo, usando una API 
para iniciar sesión y establecer un cookie). Además, crear 
métodos para precargar datos para las pruebas no deben 
realizarse con Selenium. Como se mencionó previamente, las API 
existentes se deben aprovechar para crear datos para el AUT*.

***AUT**: Application Under Test (aplicación bajo prueba)
