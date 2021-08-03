---
title: "Rastreo de enlaces"
linkTitle: "Rastreo de enlaces"
weight: 7
---

Usando WebDriver para arañar una web a través de enlaces
no es una práctica recomendada, no porque no se pueda hacer,
pero porque definitivamente no es la herramienta más ideal.
WebDriver necesita tiempo para iniciarse,
y puede tomar varios de segundos hasta un minuto
dependiendo de cómo se escriba tu prueba,
solo para llegar a la página y atravesar el DOM.

En lugar de usar WebDriver para esto,
podrías ahorrar un montón de tiempo
ejecutando un comando [curl](//curl.haxx.se/),
o usando una librería como BeautifulSoup
ya que estos métodos no se basan
en crear un navegador y navegar a una página.
Estás ahorrando toneladas de tiempo al no utilizar 
WebDriver para esta tarea.

