---
title: "Observabilidade"
linkTitle: "Observabilidade"
weight: 1
aliases: ["/documentation/pt-br/grid/grid_4/advanced_features/observability/"]
---


## Índice
 - [Selenium Grid](#selenium-grid)
 - [Observabilidade](#observabilidade)
	 - [Rastreamento distribuído](#rastreamento-distribuído) 	
	 -  [Registro de eventos](#Registro-de-eventos)
  - [Observabilidade da Grade](#observabilidade-da-grade)
	  - [Visualizando Traços](#visualizando-traços)
	  - [Aproveitando logs de eventos](#Aproveitando-logs-de-eventos)
  - [Referências](#referências)

## Selenium Grid

O Grid auxilia na escalabilidade e distribuição de testes, executando testes em várias combinações de navegadores e sistemas operacionais.

## Observabilidade

A observabilidade tem três pilares: rastreamentos, métricas e registros. Como o Selenium Grid 4 foi projetado para ser totalmente distribuído, a observabilidade tornará mais fácil entender e depurar os detalhes internos.

## Rastreamento Distribuído
Uma única solicitação ou transação abrange vários serviços e componentes. O rastreamento acompanha o ciclo de vida da solicitação à medida que cada serviço a executa. Isso é útil para depurar cenários de erro.
Alguns termos-chave usados no contexto de rastreamento são:


**Rastreamento**
O rastreamento permite rastrear uma solicitação por meio de vários serviços, desde sua origem até seu destino final. A jornada dessa solicitação ajuda na depuração, no monitoramento do fluxo de ponta a ponta e na identificação de falhas. Um rastreamento representa o fluxo da solicitação de ponta a ponta. Cada rastreamento possui um identificador único.


**Segmento**
Cada rastreamento é composto por operações cronometradas chamadas segmentos. Um segmento possui um horário de início e término e representa operações realizadas por um serviço. A granularidade do segmento depende de como ele é instrumentado. Cada segmento possui um identificador único. Todos os segmentos dentro de um rastreamento têm o mesmo ID de rastreamento.


**Atributos de Segmento**
Atributos de segmento são pares de chave e valor que fornecem informações adicionais sobre cada segmento.

**Eventos**
Eventos são registros com carimbo de data/hora dentro de um segmento. Eles fornecem contexto adicional para os segmentos existentes. Os eventos também contêm pares de chave e valor como atributos de evento.

## Registro de Eventos

O registro é essencial para depurar um aplicativo. O registro é frequentemente feito em um formato legível por humanos. Mas, para que as máquinas possam pesquisar e analisar os registros, é necessário ter um formato bem definido. O registro estruturado é uma prática comum de registrar logs de forma consistente em um formato fixo. Ele normalmente contém campos como:

 * Carimbo de data/horas
 * Nível de registro
 * Classe de registro
 * Mensagem de registro (isso é detalhado em campos relevantes à operação em que o registro foi feito)

Registros e eventos estão intimamente relacionados. Os eventos encapsulam todas as informações possíveis para realizar uma única unidade de trabalho. Os registros são essencialmente subconjuntos de um evento. No cerne, ambos auxiliam na depuração.

Consulte os recursos a seguir para entender em detalhes:

 1. [https://www.honeycomb.io/blog/how-are-structured-logs-different-from-events/](https://www.honeycomb.io/blog/how-are-structured-logs-different-from-events/)
 2. [https://charity.wtf/2019/02/05/logs-vs-structured-events/](https://charity.wtf/2019/02/05/logs-vs-structured-events/)

## Observabilidade do Grid


O servidor Selenium é instrumentado com rastreamento usando o OpenTelemetry. Cada solicitação ao servidor é rastreada do início ao fim. Cada rastreamento consiste em uma série de segmentos à medida que uma solicitação é executada no servidor. A maioria dos segmentos no servidor Selenium consiste em dois eventos:

1. Evento normal - registra todas as informações sobre uma unidade de trabalho e marca a conclusão bem-sucedida do trabalho.
2. Evento de erro - registra todas as informações até que ocorra o erro e, em seguida, registra as informações do erro. Marca um evento de exceção.

Executando servidor Selenium

 1. [Standalone](https://github.com/SeleniumHQ/selenium/wiki/Selenium-Grid-4#standalone-mode)
 2. [Hub and Node](https://github.com/SeleniumHQ/selenium/wiki/Selenium-Grid-4#hub-and-node)
 3. [Fully Distributed](https://github.com/SeleniumHQ/selenium/wiki/Selenium-Grid-4#fully-distributed)
 4. [Docker](https://github.com/SeleniumHQ/selenium/wiki/Selenium-Grid-4#using-docker)

##  Visualizando Rastreamentos
Todos os segmentos, eventos e seus respectivos atributos fazem parte de um rastreamento. O rastreamento funciona enquanto o servidor é executado em todos os modos mencionados acima.

Por padrão, o rastreamento está habilitado no servidor Selenium. O servidor Selenium exporta os rastreamentos por meio de dois exportadores:

1. Console - Registra todos os rastreamentos e os segmentos incluídos com nível FINE. Por padrão, o servidor Selenium imprime registros no nível INFO e acima.

A opção **log-level** pode ser usada para definir um nível de registro de sua escolha ao executar o arquivo JAR do Selenium Grid jar/s.

```shell
java -jar selenium-server-4.0.0-<selenium-version>.jar standalone --log-level FINE
```
2. Jaeger UI - OpenTelemetry fornece as APIs e SDKs para instrumentar rastreamentos no código. Enquanto o Jaeger é um sistema de rastreamento de backend que auxilia na coleta de dados de telemetria de rastreamento e oferece recursos de consulta, filtragem e visualização dos dados.

Instruções detalhadas sobre como visualizar rastreamentos usando a interface do Jaeger podem ser obtidas executando o seguinte comando:

```shell
java -jar selenium-server-4.0.0-<selenium-version>.jar info tracing
```

[Um exemplo muito bom e scripts para executar o servidor e enviar rastreamentos para o Jaeger](https://github.com/manoj9788/tracing-selenium-grid)

## Explorando logs de eventos
O rastreamento deve estar habilitado para o registro de eventos, mesmo que alguém não deseje exportar rastreamentos para visualizá-los.

**Por padrão, o rastreamento está habilitado. Não é necessário passar parâmetros adicionais para ver os logs no console.**

Todos os eventos dentro de um segmento são registrados no nível FINE. Eventos de erro são registrados no nível WARN..

| Campo | Valor do Campo | Descrição |
|------|------|------|
| Hora do Evento | eventId | Carimbo de data/hora do registro do evento em nanossegundos desde a época. |
| ID de Rastreamento | tracedId | Cada rastreamento é identificado exclusivamente por um ID de rastreamento. |
| ID de Segmento | spanId | Cada segmento dentro de um rastreamento é identificado exclusivamente por um ID de segmento. |
| Tipo de Segmento | spanKind | O tipo de segmento é uma propriedade do segmento que indica o tipo de segmento. Isso ajuda a entender a natureza da unidade de trabalho realizada pelo segmento. |
| Nome do Evento | eventName | Isso mapeia para a mensagem de registro. |
| Atributos do Evento | eventAttributes | Isso forma a essência dos registros de eventos, com base na operação executada, ele contém pares chave-valor formatados em JSON. Isso também inclui um atributo de classe do manipulador, para mostrar a classe do registro. |

 Simples log  

 

    FINE [LoggingOptions$1.lambda$export$1] - {
      "traceId": "fc8aef1d44b3cc8bc09eb8e581c4a8eb",
      "spanId": "b7d3b9865d3ddd45",
      "spanKind": "INTERNAL",
      "eventTime": 1597819675128886121,
      "eventName": "Session request execution complete",
      "attributes": {
        "http.status_code": 200,
        "http.handler_class": "org.openqa.selenium.grid.router.HandleSession",
        "http.url": "\u002fsession\u002fdd35257f104bb43fdfb06242953f4c85",
        "http.method": "DELETE",
        "session.id": "dd35257f104bb43fdfb06242953f4c85"
      }
    }
    
Além dos campos mencionados anteriormente, com base na [especificação do OpenTelemetry](https://github.com/open-telemetry/opentelemetry-specification/blob/master/specification/trace/semantic_conventions/exceptions.md) os registros de erro consistem nos seguintes campos: :

| Campo | Valor do Campo | Descrição |
|------|------|------|
| Tipo de Exceção | exception.type | O nome da classe da exceção. |
| Mensagem da Exceção | exception.message | Motivo da exceção. |
| Rastreamento de Exceção | exception.stacktrace | Imprime a pilha de chamadas no momento em que a exceção foi lançada. Ajuda a entender a origem da exceção. |

 

Simples error log 
  

    WARN [LoggingOptions$1.lambda$export$1] - {
      "traceId": "7efa5ea57e02f89cdf8de586fe09f564",
      "spanId": "914df6bc9a1f6e2b",
      "spanKind": "INTERNAL",
      "eventTime": 1597820253450580272,
      "eventName": "exception",
      "attributes": {
        "exception.type": "org.openqa.selenium.ScriptTimeoutException",
        "exception.message": "Unable to execute request: java.sql.SQLSyntaxErrorException: Table 'mysql.sessions_mappa' doesn't exist ..." (full message will be printed),
        "exception.stacktrace": "org.openqa.selenium.ScriptTimeoutException: java.sql.SQLSyntaxErrorException: Table 'mysql.sessions_mappa' doesn't exist\nBuild info: version: '4.0.0-alpha-7', revision: 'Unknown'\nSystem info: host: 'XYZ-MacBook-Pro.local', ip: 'fe80:0:0:0:10d5:b63a:bdc6:1aff%en0', os.name: 'Mac OS X', os.arch: 'x86_64', os.version: '10.13.6', java.version: '11.0.7'\nDriver info: driver.version: unknown ...." (full stack will be printed),
        "http.handler_class": "org.openqa.selenium.grid.distributor.remote.RemoteDistributor",
        "http.url": "\u002fsession",
        "http.method": "POST"
      }
    }


**Observação:** Os logs são formatados acima para facilitar a leitura. A formatação de logs está desativada no servidor Selenium.

Os passos acima devem configurá-lo para visualizar rastreamentos e logs.

## Referências
1. [Compreendendo o Rastreamento](https://lightstep.com/blog/opentelemetry-101-what-is-tracing/)
2. [Especificação da API de Rastreamento do OpenTelemetry](https://github.com/open-telemetry/opentelemetry-specification/blob/master/specification/trace/api.md#status)
3. [Selenium Wiki](https://github.com/SeleniumHQ/selenium/wiki)
4. [Logs Estruturados vs. Eventos](https://www.honeycomb.io/blog/how-are-structured-logs-different-from-events/)
5. [Framework Jaeger](https://github.com/jaegertracing/jaeger)
