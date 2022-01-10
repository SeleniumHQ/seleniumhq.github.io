---
title: "Configurando a sua"
linkTitle: "Configurando a sua"
weight: 4
description: >
    Quick start guide for setting up Grid 3.
aliases: [
"/documentation/pt-br/grid/grid_3/setting_up_your_own_grid/",
"/pt-br/documentation/legacy/grid_3/setting_up_your_own_grid/"
]
---

Para usar Selenium Grid,
você precisa manter sua própria infraestrutura para os nós.
Como isso pode ser um esforço pesado e intenso,
muitas organizações usam provedores IaaS
como Amazon EC2 e Google Compute
para fornecer essa infraestrutura.

Outras opções incluem o uso de provedores como Sauce Labs ou Testing Bot
que fornecem uma Selenium Grid como um serviço na nuvem.
Certamente também é possível executar nós em seu próprio hardware.
Este capítulo entrará em detalhes sobre a opção de executar sua própria Grid,
completo com sua própria infraestrutura de nós.


## Início

Este exemplo mostrará como iniciar o Selenium 2 Grid Hub,
e registrar um nó WebDriver e um nó legado Selenium 1 RC.
Também mostraremos como chamar a Grid a partir do Java.
O hub e os nós são mostrados aqui em execução na mesma máquina,
mas é claro que você pode copiar o selenium-server-standalone para várias máquinas.

O pacote `selenium-server-standalone` inclui o hub,
WebDriver e RC legado necessários para executar o Grid,
_ant_ não é mais necessário.
Você pode baixar o `selenium-server-standalone.jar` de
[https://selenium.dev/downloads/](https://selenium.dev/downloads/).


### Passo 1: Inicialize o Hub

O Hub é o ponto central que receberá solicitações de teste
e os distribuirá para os nós certos.
A distribuição é feita com base em recursos,
significando que um teste que requer um conjunto de recursos
só será distribuído para nós que oferecem esse conjunto ou subconjunto de recursos.

Porque os recursos desejados de um teste são apenas o que o nome indica, _desired_,
o hub não pode garantir que localizará um nó
corresponder totalmente ao conjunto de recursos desejados solicitados.

Abra um prompt de comando
e navegue até o diretório onde você copiou
o arquivo `selenium-server-standalone.jar`.
Você inicia o hub passando a sinalização `-role hub`
para o servidor autônomo:

```shell
java -jar selenium-server-standalone.jar -role hub
```

The Hub will listen to port 4444 by default.
You can view the status of the hub by opening a browser window and navigating to
[http://localhost:4444/grid/console](http://localhost:4444/grid/console).

Para alterar a porta padrão,
você pode adicionar a flag opcional `-port`
com um número inteiro representando a porta a ser ouvida quando você executa o comando.
Além disso, todas as outras opções que você vê no arquivo de configuração JSON (veja abaixo)
são possíveis flags de linha de comando.

Você certamente pode sobreviver apenas com o comando simples mostrado acima,
mas se você precisar de uma configuração mais avançada,
você também pode especificar um arquivo de configuração de formato JSON, por conveniência,
para configurar o hub ao iniciá-lo.
Você pode fazer assim:

```shell
java -jar selenium-server-standalone.jar -role hub -hubConfig hubConfig.json -debug
```

Abaixo você verá um exemplo de um arquivo `hubConfig.json`.
Entraremos em mais detalhes sobre como fornecer arquivos de configuração de nó no Passo 2.

```json
{
  "_comment" : "Configuration for Hub - hubConfig.json",
  "host": ip,
  "maxSession": 5,
  "port": 4444,
  "cleanupCycle": 5000,
  "timeout": 300000,
  "newSessionWaitTimeout": -1,
  "servlets": [],
  "prioritizer": null,
  "capabilityMatcher": "org.openqa.grid.internal.utils.DefaultCapabilityMatcher",
  "throwOnCapabilityNotPresent": true,
  "nodePolling": 180000,
  "platform": "WINDOWS"}
```


### Pasos 2: Inicialize os Nós

Independentemente de você querer executar uma Grid com a nova funcionalidade WebDriver,
ou uma Grid com funcionalidade Selenium 1 RC,
ou os dois ao mesmo tempo,
você usa o mesmo arquivo `selenium-server-standalone.jar` para iniciar os nós:

```shell
java -jar selenium-server-standalone.jar -role node -hub http://localhost:4444
```

Se uma porta não for especificada por meio do sinalizador `-port`,
uma porta livre será escolhida. Você pode executar vários nós em uma máquina
mas se você fizer isso, você precisa estar ciente dos recursos de memória de seus sistemas
e problemas com capturas de tela se seus testes as fizerem.


#### Configuração de um nó com opções

Como mencionado, para compatibilidade com versões anteriores
as funções “wd” e “rc” ainda são um subconjunto válido da função “node”.
Mas essas funções limitam os tipos de conexões remotas para sua API correspondente,
enquanto “node” permite conexões remotas RC e WebDriver.

Ao passar propriedades JVM (usando o sinalizador `-D`
_antes do argumento -jar_)
na linha de comando também,
estas serão coletadas e propagadas para os nós:

`-Dwebdriver.chrome.driver=chromedriver.exe`


#### Configuração de um nó com JSON

Você também pode iniciar nós da Grid que estão configurados
com um arquivo de configuração JSON

```shell
java -Dwebdriver.chrome.driver=chromedriver.exe -jar selenium-server-standalone.jar -role node -nodeConfig node1Config.json
```

E aqui está um exemplo do arquivo `nodeConfig.json`:

```json
{
  "capabilities": [
    {
      "browserName": "firefox",
      "acceptSslCerts": true,
      "javascriptEnabled": true,
      "takesScreenshot": false,
      "firefox_profile": "",
      "browser-version": "27",
      "platform": "WINDOWS",
      "maxInstances": 5,
      "firefox_binary": "",
      "cleanSession": true
    },
    {
      "browserName": "chrome",
      "maxInstances": 5,
      "platform": "WINDOWS",
      "webdriver.chrome.driver": "C:/Program Files (x86)/Google/Chrome/Application/chrome.exe"
    },
    {
      "browserName": "internet explorer",
      "maxInstances": 1,
      "platform": "WINDOWS",
      "webdriver.ie.driver": "C:/Program Files (x86)/Internet Explorer/iexplore.exe"
    }
  ],
  "configuration": {
    "_comment" : "Configuration for Node",
    "cleanUpCycle": 2000,
    "timeout": 30000,
    "proxy": "org.openqa.grid.selenium.proxy.WebDriverRemoteProxy",
    "port": 5555,
    "host": ip,
    "register": true,
    "hubPort": 4444,
    "maxSession": 5
  }
}
```

Uma observação sobre a flag `-host`

Para hub e nó, se a flag `-host` não for especificada,
`0.0.0.0` será usado por padrão. Isso se ligará a todos as
interfaces IPv4 públicas (sem loopback) da máquina.
Se você tem uma configuração especial de rede ou qualquer
componente que crie interfaces de rede extras,
é aconselhável definir a flag `-host` com um valor que permite o
hub / nó acessível a partir de uma máquina diferente.

#### Especificando a porta

A porta TCP / IP padrão usada pelo hub é 4444. Se você precisar alterar a porta
use as configurações mencionadas acima.

## Solução de problemas

### Usando um arquivo de log
Para solução de problemas avançada, você pode especificar um arquivo de log para registrar mensagens do sistema.
Inicie o hub ou nó Selenium Grid com o argumento -log. Por favor, veja o exemplo abaixo:

```shell
java -jar selenium-server-standalone.jar -role hub -log log.txt
```

Use o seu editor de texto favorito para abrir o arquivo de log (log.txt no exemplo acima) para encontrar
registros de "ERROR" se você tiver problemas.

### Usando o argumento `-debug`

Você também pode usar o argumento `-debug` para imprimir logs de depuração no console.
Inicie o Selenium Grid Hub ou Node com o argumento `-debug`. Por favor, veja
o exemplo abaixo:

```shell
java -jar selenium-server-standalone.jar -role hub -debug
```

## Aviso

A Selenium Grid deve ser protegida do acesso externo usando
permissões de firewall.

A falha em proteger sua rede pode resultar em um ou mais dos seguintes eventos:

* Você fornece acesso aberto à sua infraestrutura de rede
* Você permite que terceiros acessem aplicativos e arquivos internos da web
* Você permite que terceiros executem binários personalizados

Veja esta postagem do blog em [Detectify](//labs.detectify.com), que dá uma boa
visão geral de como uma rede exposta publicamente pode ser mal utilizada:
[Não deixe sua grade totalmente aberta](//labs.detectify.com/2017/10/06/guest-blog-dont-leave-your-grid-wide-open/).


## Docker Selenium
[Docker](//www.docker.com/) fornece uma maneira conveniente de
provisionar e escalar a infraestrutura da Selenium Grid em uma unidade conhecida como contêiner.
Os contêineres são unidades padronizadas de software que contêm tudo o que é necessário
para executar o aplicativo desejado, incluindo todas as dependências, de forma confiável e repetível em máquinas diferentes.

O projeto Selenium mantém um conjunto de imagens Docker que você pode baixar
e executar para colocar uma Grid em funcionamento rapidamente. Os nós estão disponíveis para
Firefox e Chrome. Detalhes completos de como provisionar uma grade podem ser encontrados
no repositório [Docker Selenium](//github.com/SeleniumHQ/docker-selenium).

### Pré-requisitos
O único requisito para executar um Grid é ter o Docker instalado e funcionando.
[Instale o Docker] (// www.docker.com/products/docker-desktop).
