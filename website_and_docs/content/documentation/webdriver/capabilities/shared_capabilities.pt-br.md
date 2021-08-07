---
title: "Recursos compartilhados"
linkTitle: "Recursos compartilhados"
weight: 1
---

Para criar uma nova sessão por Selenium WebDriver,
a extremidade local deve fornecer os recursos básicos para a extremidade remota.
A extremidade remota usa o mesmo conjunto de recursos para
cria uma sessão e descreve os recursos da sessão atual.

WebDriver fornece recursos que cada controle extremidade
remota irá/deverá apoiar a implementação.
A seguir estão os recursos que o WebDriver suporta:

## browserName:

Este recurso é usado para definir o `browserName` para uma determinada sessão.
Se o navegador especificado não estiver instalado na
extremidade remota, a criação da sessão irá falhar

## browserVersion: 

Este recurso é opcional, usado para
definir a versão do navegador disponível na extremidade remota.
Por exemplo, se pedir o Chrome versão 75 em um sistema que
tem apenas a versão 80 instalada, a criação da sessão irá falhar

## pageLoadStrategy:

Ao navegar para uma nova página via URL, por padrão o Selenium irá esperar
até que a página esteja totalmente carregada antes de responder. Isso funciona bem para
iniciantes, mas pode causar longos tempos de espera em páginas que carregam um grande
número de recursos de terceiros. Usar uma estratégia não padrão pode tornar a
execução de testes mais rápida em casos como este, mas também pode apresentar instabilidade
onde os elementos na página mudam de posição conforme os elementos são carregados e mudam
de tamanho.

A estratégia de carregamento da página consulta o
[document.readyState](//developer.mozilla.org/en-US/docs/Web/API/Document/readyState)
conforme descrito na tabela abaixo:

| Estrtégia | Ready State | Notas |
| -------- | ----------- | ----- |
| normal | complete | Usado por padrão, aguarda o download de todos os recursos |
| eager | interactive | O acesso ao DOM está pronto, mas outros recursos, como imagens, ainda podem estar carregando |
| none | Any | Não bloqueia o WebDriver de nenhuma forma |

## platformName

Isso identifica o sistema operacional na extremidade remota e 
buscar o `platformName` retorna o nome do sistema operacional.

Em provedores baseados em nuvem,
definir `platformName` define o sistema operacional na extremidade remota.

## acceptInsecureCerts

Este recurso verifica se um `Certificado TLS`
expirado (ou) inválido é usado durante a navegação
durante uma sessão.

Se o recurso for definido como `false`, um
[erro de certificado inseguro](//developer.mozilla.org/en-US/docs/Web/WebDriver/Errors/InsecureCertificate)
será retornado quando a navegação encontrar qualquer
problema de certificado de domínio. Se definido como `verdadeiro`, o certificado inválido será
confiável para o navegador.

Todos os certificados autoassinados serão considerados confiáveis por esse recurso por padrão.
Uma vez definido, o recurso `acceptInsecureCerts` terá um
efeito para toda a sessão.

## Timeout de Sessão

Uma `session` do WebDriver é imposta com um certo intervalo `session timeout`
durante o qual o usuário pode controlar o comportamento
de executar scripts ou recuperar informações do navegador.

Cada tempo limite de sessão é configurado com
combinação de diferentes `timeouts`, conforme descrito abaixo:

### Timeout de Script:
Especifica quando interromper um script em execução em
um contexto de navegação atual. O tempo limite padrão **30.000**
é imposto quando uma nova sessão é criada pelo WebDriver.

### Timeout de Carregamento de Página:
Especifica o intervalo de tempo em que a página da web
precisa ser carregado em um contexto de navegação atual.
O tempo limite padrão **300.000** é imposto quando uma
nova sessão é criada pelo WebDriver. Se os carregamento da página delimitar
um determinado período de tempo, o script será interrompido por
_TimeoutException_.

### Timeout de Espera Implícita:
Isso especifica o tempo de espera pela
estratégia de implicit element location quando
localizando de elementos. O tempo limite padrão **0**
é imposto quando uma nova sessão é criada pelo WebDriver.

## unhandledPromptBehavior

Especifica o estado do `user prompt handler` da sessão atual.
O padrão é **dismiss and notify state** (**dispensar e notificar estado**, em português)

### User Prompt Handler

Isso define qual ação deve ser tomada quando um
o prompt do usuário se encontra na extremidade remota. Isso é definido pelo
recurso `unhandledPromptBehavior` e tem os seguintes estados:

* dismiss
* accept
* dismiss and notify
* accept and notify
* ignore

## setWindowRect

Este comando altera o tamanho e a posição da atual
janela de contexto de navegação. Este comando atua como setter
para o comando `getWindowRect` que aceita **largura**, **altura**,
**x**, **y** como argumentos _opcionais_.

Durante a automação, o contexto de navegação atual será associado
com estados de janela, que descrevem a visibilidade
da janela do navegador. Os estados da janela são

* maximized
* minimized
* normal
* fullscreen

Definir _Width_ ou _Height_ não garante que o resultado
o tamanho da janela corresponderá exatamente ao que foi inserido. Isto é porque
alguns drivers podem não ser redimensionados em incrementos de pixel único.
Devido a isso, buscar o estado / detalhes da janela por `getWindowRect`
pode não corresponder igualmente aos valores definidos para o navegador.

## strictFileInteractability

O novo recurso indica se as verificações estritas de interatividade
devem ser aplicadas aos elementos _input type = file_. Como as verificações de
interatividade estrita estão desativadas por padrão, há uma mudança no comportamento
ao usar _Element Send Keys_ com controles de upload de arquivos ocultos.