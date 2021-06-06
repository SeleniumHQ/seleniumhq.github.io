---
title: "Configurando a sua"
weight: 2
---

## Diferentes modos de configuração de Grid no Selenium 4:
* Standalone
* Hub and Node
* Distributed
* Docker

## Modo Standalone:
O novo Selenium Server Jar contém tudo que você precisa para executar uma Grid. É também o modo mais fácil de ligar uma Selenium Grid. Por padrão, o servidor estará escutando em http://localhost:4444, e essa é a URL para a qual você deve apontar seus testes RemoteWebDriver. O servidor detectará os drivers disponíveis que podem ser usados no PATH do sistema

```shell
java -jar selenium-server-4.0.0-alpha-6.jar standalone
```

## Modo Hub e Nó:

### Inicialize o Hub:
```shell
java -jar selenium-server-4.0.0-alpha-6.jar hub
```

### Registre o Nó:

```shell
java -jar selenium-server-4.0.0-alpha-6.jar node --detect-drivers true
```

### Faça a busca na Selenium Grid:

No Selenium 4, também adicionamos GraphQL, uma nova maneira de consultar os dados necessários facilmente e obter exatamente a mesma coisa.

```shell
curl -X POST -H "Content-Type: application/json" --data '{ "query": "{grid{uri}}" }' -s http://localhost:4444/graphql | jq .
```
<br><br>

## Modo Distribuído:

* Passo 1: Em primeiro lugar, inicie o Event Bus, ele serve como um caminho de comunicação para outros componentes da rede nas etapas subsequentes.

    ```shell
    java -jar selenium-server-4.0.0-alpha-6.jar  event-bus
    ```

* Passo 2: Inicie o mapa da sessão, que é responsável por mapear os IDs da sessão para o nó em que a sessão está sendo executada:

    ```shell
        java -jar selenium-server-4.0.0-alpha-6.jar sessions
    ```

* Passo 3: Inicie o novo enfileirador de sessão, ele adiciona a nova solicitação de sessão a uma fila local. O distribuidor atende o pedido da fila.

    ```shell
        java -jar selenium-server-4.0.0-alpha-6.jar sessionqueuer
    ```

* Passo 4: Inicie o Distribuidor. Todos os Nós são anexados como parte do processo do Distribuidor. É responsável por atribuir um nó, quando um pedido de criação de sessão é invocado.

    ```shell
        java -jar selenium-server-4.0.0-alpha-6.jar distributor --sessions http://localhost:5556 --sessionqueuer http://localhost:5559 --bind-bus false
    ```

* Passo 5: A próxima etapa é iniciar o Roteador, um endereço que você exporia à web

    ```shell
        java -jar selenium-server-4.0.0-alpha-6.jar router --sessions http://localhost:5556 --distributor http://localhost:5553 --sessionqueuer http://localhost:5559
    ```

* Step 6: Finalmente, adicione um Nó

    ```shell
        java -jar selenium-server-4.0.0-alpha-6.jar node --detect-drivers true
    ```

## Inicie a Standalone Grid via imagens Docker

  Você pode simplesmente iniciar um Nó com o seguinte comando:

```shell
    java -jar selenium-server-4.0.0-alpha-1.jar node -D selenium/standalone-firefox:latest '{"browserName": "firefox"}'
```

  Você pode iniciar o servidor Selenium e delegá-lo ao docker para a criação de novas instâncias:

```shell
     java -jar selenium-server-4.0.0-alpha-6.jar standalone -D selenium/standalone-firefox:latest '{"browserName": "firefox"}' --detect-drivers false
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
