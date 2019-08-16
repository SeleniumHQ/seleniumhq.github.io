---
title: Contribuyendo a la documentación de Selenium
disableToc: true
---

Selenium es un gran proyecto de software y la documentación es clave para comprender cómo funcionan las cosas y aprender formas efectivas de explotar su potencial.

Parte de la documentación de Selenium todavía se sirve de nuestro [repositorio ** www.seleniumhq.org ** ](https://github.com/SeleniumHQ/www.seleniumhq.org). Sin embargo, estamos eliminando gradualmente esta documentación que se centra demasiado en Selenium RC y otras piezas anticuadas, a favor de esta reescritura.

La nueva documentación es un proyecto que comenzó a reescribir la documentación de Selenium desde cero. Este es un esfuerzo continuo (no dirigido a ninguna versión específica) para proporcionar un manual actualizado sobre cómo usar Selenium de manera efectiva. Esperamos traer las piezas de la documentación antigua que aún tienen sentido.

Los aportes a los nuevos documentos siguen el proceso descrito en la sección a continuación sobre las contribuciones. Debería pasar un tiempo familiarizándose con la documentación leyendo [más sobre ella]({{<ref "/introduction/about_this_documentation.es.md">}}).

---

El proyecto Selenium agradece las contribuciones de todos. Hay varias formas de colaborar:

## Reportar un problema

Al informar un nuevo problema o al comentar problemas existentes, asegúrese de que las discusiones estén relacionadas con temas técnicos concretos con el software Selenium o su documentación.

Todos los componentes de Selenium cambian bastante rápido en el tiempo, por lo que esto puede hacer que la documentación esté desactualizada. Si considera que este es el caso, como se mencionó, no dude en reportar un problema. También es posible que sepa cómo actualizar la documentación, así que envíenos una solicitud  (_pull request_) con los cambios relacionados.

Si no está seguro de si lo que encontró es un problema o no, consulte primero a la [lista de correo de selenium-users@](https://groups.google.com/forum/#!forum/selenium-users), o únase a nosotros en el canal `#selenium` en [irc.freenode.org](https://webchat.freenode.net/) o [Slack](https://seleniumhq.herokuapp.com/).

## Contribuciones

El proyecto Selenium da la bienvenida a nuevos contribuyentes. Las personas que hacen contribuciones significativas y valiosas a lo largo del tiempo se hacen _Committers_ y se les da acceso para realizar _commit_ al proyecto.

Este documento lo guiará a través del proceso de contribución.

### Step 1: Bifurcar (_Fork_)

Haga _fork_ al proyecto [en Github](https://github.com/seleniumhq/docs)
y verifique su copia localmente.

```shell
% git clone git@github.com:username/docs.git
% cd docs
% git remote add upstream git://github.com/seleniumhq/docs.git
```

#### Dependencias: Hugo

La documentación usa [Hugo](https://gohugo.io/) para construir y renderizar el sitio.
Por favor, verifique todo localmente incluso antes de hacer _commit_ a cualquier cambio
[instale Hugo](https://gohugo.io/getting-started/installing/) y
[corra el servidor local](https://gohugo.io/getting-started/usage/#livereload)
para renderizar el sitio localmente.

### Step 2: Ramificación (_Branch_)

Cree una rama nueva (_feature branch_) y comience a hackear:

```shell
% git checkout -b my-feature-branch
```

Practicamos el desarrollo basado en HEAD (_HEAD-based development_), lo que significa que todos los cambios se aplican directamente sobre la rama maestra (_master_).

### Step 3: Confirmar (_Commit_)

Primero asegúrese de que git sepa su nombre y dirección de correo electrónico:

```shell
% git config --global user.name 'Santa Claus'
% git config --global user.email 'santa@example.com'
```

**Escribir buenos mensajes de confirmación (_commit_) es importante.** Un mensaje de confirmación debe describir qué cambió, por qué y los problemas de referencia corregidos (si los hay). Siga estas pautas al escribir uno:

1. Procure que el texto sea en inglés. Se colocan los ejemplos en Español para aumentar la comprensión.
2. La primera línea debe tener alrededor de 50 caracteres o menos y contener una breve descripción del cambio.
3. Mantenga la segunda línea en blanco.
4. Envuelva todas las demás líneas en 72 columnas.
5. Incluya `Fixes # N`, donde _N_ es el número de problema (_issue_) que corrige la confirmación, si corresponde.

Un buen mensaje de confirmación puede verse así:

```texto
explicar cometer normativamente en una línea

El cuerpo del mensaje de confirmación es unas pocas líneas de texto, que explican las cosas con más detalle, posiblemente brindando algunos antecedentes sobre la resolución del problema, etc.

El cuerpo del mensaje de confirmación puede tener varios párrafos y, por favor, ajuste correctamente las palabras y mantenga las columnas con menos de 72 caracteres aproximadamente. De esa manera, 'git log' mostrará las cosas muy bien incluso cuando está sangrado.

Fixes # 141
```

La primera línea debe ser significativa, ya que es lo que las personas ven cuando ejecutan `git shortlog` o` git log --oneline`.

### Step 4: Rebase

Use `git rebase` (not `git merge`) para sincronizar tu trabajo de vez en cuando.

```shell
% git fetch upstream
% git rebase upstream/master
```

### Paso 5: Prueba

Recuerde siempre [ejecutar el servidor local](https://gohugo.io/getting-started/usage/#livereload), con esto puede estar seguro de que sus cambios no han roto nada.

### Paso 6: Traducciones

Si está actualizando los documentos, agregando nuevos o eliminando los obsoletos, recuerde actualizar las traducciones. Por supuesto, es posible que no hables todos los idiomas traducidos en los documentos. Para eso, cree un hallazgo [issue](https://github.com/SeleniumHQ/docs/issues) donde describa claramente que algo en los documentos ha cambiado y su traducción debe actualizarse. Con eso, alguien que habla ese idioma necesario puede intervenir y ayudarnos a mantenerlo actualizado.

### Paso 7: Firme el CLA

Antes de que podamos aceptar, primero le pedimos a las personas que firmen un
[Acuerdo de licencia de colaborador](https://spreadsheets.google.com/spreadsheet/viewform?hl=en_US&formkey=dFFjXzBzM1VwekFlOWFWMjFFRjJMRFE6MQ#gid=0) (o CLA). Pedimos esto para que sepamos que los contribuyentes tienen el derecho de donar el código.

Cuando abre su solicitud (_pull request_), le pedimos que indique que ha firmado el CLA. Esto reducirá el tiempo que nos lleva integrarlo.

### Paso 8: Empuje (_Push_)

```shell
% git push origin my-feature-branch
```

Vaya a https://github.com/yourusername/docs.git y presione _Pull Request_ y complete el formulario. **Indique que ha firmado el CLA** (consulte el Paso 7).

Las solicitudes generalmente se revisan en unos pocos días. Si hay comentarios que abordar, aplique sus cambios en los nuevos commits (preferiblemente [arreglos o _fixups_](http://git-scm.com/docs/git-commit)) y avance a la misma rama (_branch_).

### Paso 9: Integración

Cuando se completa la revisión del código, un committer tomará su PR (_pull request_) y lo integrará en la rama (_branch_) gh-pages de los documentos. Como nos gusta mantener un historial lineal en la rama maestra, normalmente resumiremos los cambios (_squash and rebase_) del historial de su rama.

## Comunicación

Los contribuyentes de Selenium frecuentan el canal `#selenium` en [`irc.freenode.org`](https://webchat.freenode.net/) o en [Slack](https://seleniumhq.herokuapp.com/). También puede unirse a la [lista de correo `selenium-developers@`](https://groups.google.com/forum/#!forum/selenium-developers).

