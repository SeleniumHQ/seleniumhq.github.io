---
title: "Ajuda de configuração"
linkTitle: "Ajuda de configuração"
weight: 1
description: Obtenha ajuda sobre todas as opções disponíveis para configurar a Grid.
aliases: [
"/documentation/pt-br/grid/grid_4/configuring_components/config_help/",
"/pt-br/documentation/grid/configuring_components/config_help/"
]
---

{{% pageinfo color="primary" %}}
Os comandos de ajuda (help) mostram informação baseada na implementação de código em vigor.
Desta forma, irá obter informação potencialmente mais actual do que esta documentação.
Embora possa não ser a mais detalhada, é sempre a forma mais simples de aprender sobre
as configurações da Grid 4 em qualquer nova versão.
{{% /pageinfo %}}

## Commando info

O comando `info` fornece detalhes de documentação sobre os seguintes tópicos:
* Configurar Selenium
* Segurança
* Setup Session Map
* Traceamento

### Ajuda sobre configuração

Ajuda rápida e resumo pode ser obtida com:

```shell
java -jar selenium-server-<version>.jar info config
```

### Segurança

Para obter detalhes em como configurar os servidores Grid para comunicação segura
e para o registo de **Nodes**:

```shell
java -jar selenium-server-<version>.jar info security
```

### Configuração Session Map

Por omissão, a Grid usa um `local session map` para armazenar informação de sessões.
A Grid permite armazenamento opcional em Redis ou bancos de dados JDBC SQL.
Para obter informação de como usar, use o seguinte comando:

```shell
java -jar selenium-server-<version>.jar info sessionmap
```

### Traceamento com OpenTelemetry e Jaeger

Por omissao, traceamento está activo. Para exportar e visualizar através de Jaeger, use o 
comando seguinte para instruções de como o efectuar:

```shell
java -jar selenium-server-<version>.jar info tracing
```

## Lista de comandos Selenium Grid
 
Irá mostrar todos os comandos disponíveis e também a descrição de cada um.

```shell
java -jar selenium-server-<version>.jar --config-help
```

## Comandos de ajuda para componente

Adicione `--help` após o Selenium role para obter informação específica de configuração do componente.

### Standalone 

```shell
java -jar selenium-server-<version>.jar standalone --help
```
### Hub 

```shell
java -jar selenium-server-<version>.jar hub --help
```

### Sessions 

```shell
java -jar selenium-server-<version>.jar sessions --help
```

### New Session Queue

```shell
java -jar selenium-server-<version>.jar sessionqueue --help
```

### Distributor 

```shell
java -jar selenium-server-<version>.jar distributor --help
```

### Router 

```shell
java -jar selenium-server-<version>.jar router --help
```

### Node 

```shell
java -jar selenium-server-<version>.jar node --help
```



