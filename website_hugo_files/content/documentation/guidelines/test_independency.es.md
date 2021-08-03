---
title: "Independencia en las pruebas"
linkTitle: "Independencia en las pruebas"
weight: 9
---

Escribe cada prueba como su propia unidad. Escribe las pruebas 
de una manera que no dependan de otras pruebas para 
completarse: 

Digamos que hay un sistema de gestión de contenido con el que 
puedes crear algún contenido personalizado que luego después 
publicar aparece en tu sitio web como un módulo, y puede llevar 
algún tiempo sincronizar entre el CMS y y la aplicación. 

Una forma incorrecta de probar tu módulo es que el contenido 
está creado y publicado en una prueba y luego verificando el 
módulo en otra prueba. Esta no es factible ya que el contenido 
puede no estar disponible de inmediato para otra prueba después 
de la publicación. 

En cambio, puedes crear un contenido auxiliar que se puede 
activar y desactivar dentro de la prueba afectada y úsarla para 
validar el módulo. Sin embargo, para la creación de contenido, 
aún puedes tener una prueba por separado.
