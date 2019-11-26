---
title: Contribuyendo al sitio y documentación de Selenium
disableToc: true
---

{{% notice info %}}
<i class="fas fa-language"></i> Page being translated from 
English to Spanish. Do you speak Spanish? Help us to translate
it by sending us pull requests!
{{% /notice %}}

Selenium es un proyecto grande de software, su documentación y sitio son
claves para comprender cómo funcionan las cosas y aprender formas efectivas
de explotar su potencial.


Este proyecto continene el sitio y la documentación de Selenium. Éste es un
esfuerzo continuo (no dirigido a ninguna versión específica) para proporcionar
información actualizada sobre cómo usar Selenium de efectivamente, cómo
involucrarse y cómo contribuír a Selenium.

Las contribuciones al sitio o la documentación siguen el proceso descrito en
la sección a continuación. Debería pasar un tiempo familiarizándose con la
documentación leyendo [más sobre ella]({{<ref "/introduction/about_this_documentation.es.md">}}).

---

El proyecto Selenium agradece las contribuciones de todos. 
Hay varias formas de colaborar:

## Reportar un problema

Al informar un nuevo problema o al comentar problemas existentes, asegúrese
de que las discusiones estén relacionadas con temas técnicos concretos con
el software Selenium o su documentación.

Todos los componentes de Selenium cambian bastante rápido en el tiempo, por
lo que esto puede hacer que la documentación esté desactualizada. Si considera
que este es el caso, como se mencionó, no dude en reportar un problema.
También es posible que sepa cómo actualizar la documentación, así que envíenos
un _pull request_ con los cambios relacionados.

Si no está seguro de si lo que encontró es un problema o no, por favor pregunte
a través de los canales de comunicación descritos en https://selenium.dev/support. 

## Contribuciones

El proyecto Selenium da la bienvenida a nuevos contribuyentes. Las personas que
hacen contribuciones significativas y valiosas a lo largo del tiempo se hacen 
_Committers_ y se les da acceso para realizar _commit_ al proyecto.

Este documento lo guiará a través del proceso de contribución.

### Paso 1: Bifurcar (_Fork_)

Haga _fork_ al proyecto [en Github](https://github.com/seleniumhq/seleniumhq.github.io)
y verifique su copia localmente.

```shell
% git clone git@github.com:seleniumhq/seleniumhq.github.io.git
% cd seleniumhq.github.io
```

#### Dependencias: Hugo

La documentación usa [Hugo](https://gohugo.io/) para construir el sitio y la
documentación. Verifique todo localmente incluso antes de hacer _commit_ a cualquier 
cambio, [instale Hugo](https://gohugo.io/getting-started/installing/) y
[corra el servidor local](https://gohugo.io/getting-started/usage/#livereload)
para renderizar el sitio localmente.
s
### Paso 2: Ramificación (_Branch_)

Cree una rama nueva (_feature branch_) y comience a hackear:

```shell
% git checkout -b my-feature-branch
```

Practicamos el desarrollo basado en HEAD (_HEAD-based development_), lo que
significa que todos los cambios se aplican directamente sobre la rama maestra (_master_).

### Paso 3: Haga cambios

The repository contains the site and docs, which are two separate Hugo 
projects. If you want to make changes to the site, work on the
`site_source_files` directory. To see a live preview of your changes,
run `hugo server` on the site's root directory.

```shell
% cd site_source_files
% hugo server
```

To make changes to the docs, switch to the `docs_source_files` directory. 

```shell
% cd docs_source_files
% hugo server
```

The docs are translated into several languages, and translations are based on
the English content. When you are changing a file, **be sure** to make your
changes in all the other translated files as well. This might differ depending
on the change, for example:
 
* If you add a code example to the `browser_manipulation.en.md` file,
also add it to `browser_manipulation.es.md`, `browser_manipulation.ef.md`, 
`browser_manipulation.ja.md`, and all other translated files.
* If you find a translation that can be improved, only change the translated
file.
* If you are adding a new language translation, add the new files with the
appropriate suffix. There is no need to have everything translated to submit a
PR, it can be done iteratively. Don't forget to check some needed configuration
values in the `config.toml` file.
* If you make text changes in the English version, replace the same section in
the translated files with your change (yes, in English), and add the following
notice at the top of the file. 

```
{{%/* notice info */%}}
<i class="fas fa-language"></i> Page being translated from 
English to {LANGUAGE}. Do you speak {LANGUAGE}? Help us to translate
it by sending us pull requests!
{{%/* /notice */%}}
```


### Paso 4: Confirmar (_Commit_)

Primero asegúrese de que git sepa su nombre y dirección de correo electrónico:

```shell
% git config --global user.name 'Santa Claus'
% git config --global user.email 'santa@example.com'
```

**Escribir buenos mensajes de confirmación (_commit_) es importante.** Un 
mensaje de confirmación debe describir qué cambió, por qué y los problemas
de referencia corregidos (si los hay). Siga estas pautas al escribir uno:

1. Procure que el texto sea en inglés. 
Se colocan los ejemplos en Español para aumentar la comprensión.
2. La primera línea debe tener alrededor de 50 caracteres o menos y 
contener una breve descripción del cambio.
3. Mantenga la segunda línea en blanco.
4. Envuelva todas las demás líneas en 72 columnas.
5. Incluya `Fixes # N`, donde _N_ es el número de problema (_issue_)
que corrige la confirmación, si corresponde.

Un buen mensaje de confirmación puede verse así:

```texto
explicar cometer normativamente en una línea

El cuerpo del mensaje de confirmación es unas pocas líneas de texto,
que explican las cosas con más detalle, posiblemente brindando algunos
antecedentes sobre la resolución del problema, etc.

El cuerpo del mensaje de confirmación puede tener varios párrafos y, 
por favor, ajuste correctamente las palabras y mantenga las columnas
con menos de 72 caracteres aproximadamente. De esa manera, 'git log'
mostrará las cosas muy bien incluso cuando está sangrado.

Fixes # 141
```

La primera línea debe ser significativa, ya que es lo que las personas
ven cuando ejecutan `git shortlog` o` git log --oneline`.

### Step 5: Rebase

Use `git rebase` (not `git merge`) para sincronizar tu trabajo de vez en cuando.

```shell
% git fetch upstream
% git rebase upstream/master
```

### Paso 6: Prueba

Recuerde siempre [ejecutar el servidor local](https://gohugo.io/getting-started/usage/#livereload), 
con esto puede estar seguro de que sus cambios no han roto nada.

### Paso 7: Suba sus cambios (_Push_)

```shell
% git push origin my-feature-branch
```

Vaya a https://github.com/yourusername/site.git y presione 
_Pull Request_ y complete el formulario. 
**Indique que ha firmado el CLA** (consulte el Paso 7).

Las solicitudes generalmente se revisan en unos pocos días. 
Si hay comentarios que abordar, aplique sus cambios en los nuevos 
commits (preferiblemente [arreglos o _fixups_](http://git-scm.com/docs/git-commit)) 
y avance a la misma rama (_branch_).

### Paso 8: Integración

Cuando se completa la revisión del código, un committer tomará su 
PR (_pull request_) y lo integrará en la rama (_branch_) gh-pages de los
documentos. Como nos gusta mantener un historial lineal en la rama maestra,
normalmente resumiremos los cambios (_squash and rebase_) del 
historial de su rama.

## Comunicación

Todos los detalles sobre cómo comunicarse con los miembros del proyecto y la
comunidad pueden encontrarse en https://selenium.dev/support.

