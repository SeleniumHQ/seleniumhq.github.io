---
title: "外部数据存储"
linkTitle: "外部数据存储"
weight: 5
---

## 目录
* [简介](#introduction)
* [配置](#setup)
* [基于数据库的会话映射](#database-backed-session-map)
    * [步骤](#steps)
* [基于 Redis 的会话映射](#redis-backed-session-map)
    * [步骤](#steps-1)

## 简介 {#introduction}

Selenium Grid 支持将正在运行的会话相关信息持久化到外部数据存储中。
外部数据存储可以由你选择的数据库或 Redis 缓存系统提供支持。

## 配置 {#setup}

* [Coursier](https://get-coursier.io/docs/cli-installation) - 作为依赖解析器，
  用于按需下载 Maven 构件并将其添加到类路径中。
* [Docker](https://docs.docker.com/engine/install/) - 用于管理 PostgreSQL 和 Redis 容器。

## 基于数据库的会话映射 {#database-backed-session-map}

本示例使用 PostgreSQL 数据库。

我们将通过 Docker Compose 文件启动一个 PostgreSQL 数据库容器。

### 步骤 {#steps}

如果你已有可用的 PostgreSQL 数据库实例，可以跳过此步骤。

* 创建名为 `init.sql` 的 SQL 文件，内容如下：

```sql
CREATE TABLE IF NOT EXISTS sessions_map(
    session_ids varchar(256),
    session_caps text,
    session_uri varchar(256),
    session_stereotype text,
    session_start varchar(256)
 );
```

* 在 `init.sql` 所在目录中创建名为 `docker-compose.yml` 的文件，内容如下：

```yaml
version: '3.8'
services:
  db:
    image: postgres:9.6-bullseye
    restart: always
    environment:
      - POSTGRES_USER=seluser
      - POSTGRES_PASSWORD=seluser
      - POSTGRES_DB=selenium_sessions
    ports:
      - "5432:5432"
    volumes:
    - ./init.sql:/docker-entrypoint-initdb.d/init.sql
```

现在可以运行以下命令启动数据库容器：

```bash
docker-compose up -d
```

_数据库名称为 `selenium_sessions`，用户名和密码均为 `seluser`。_

如果你使用的是已经运行的 PostgreSQL 数据库实例，只需创建名为 `selenium_sessions` 的数据库，
并使用上面的 SQL 语句创建 `sessions_map` 表。

* 创建名为 `sessions.toml` 的 Selenium Grid 配置文件，内容如下：

```toml
[sessions]
implementation = "org.openqa.selenium.grid.sessionmap.jdbc.JdbcBackedSessionMap"
jdbc-url = "jdbc:postgresql://localhost:5432/selenium_sessions"
jdbc-user = "seluser"
jdbc-password = "seluser"
```

_注意：_如果你使用的是已有的 PostgreSQL 数据库实例，请将 `localhost:5432` 替换为该实例的实际主机名和端口号。

* 下面的简单 shell 脚本（命名为 `distributed.sh`）用于启动分布式 Grid：

```bash
SE_VERSION=<current_selenium_version>
JAR_NAME=selenium-server-${SE_VERSION}.jar
PUBLISH="--publish-events tcp://localhost:4442"
SUBSCRIBE="--subscribe-events tcp://localhost:4443"
SESSIONS="--sessions http://localhost:5556"
SESSIONS_QUEUE="--sessionqueue http://localhost:5559"
echo 'Starting Event Bus'
java -jar $JAR_NAME event-bus $PUBLISH $SUBSCRIBE --port 5557 &
echo 'Starting New Session Queue'
java -jar $JAR_NAME sessionqueue --port 5559 &
echo 'Starting Sessions Map'
java -jar $JAR_NAME \
--ext $(coursier fetch -p org.seleniumhq.selenium:selenium-session-map-jdbc:${SE_VERSION} org.postgresql:postgresql:42.3.1) \
sessions $PUBLISH $SUBSCRIBE --port 5556 --config sessions.toml &
echo 'Starting Distributor'
java -jar $JAR_NAME  distributor $PUBLISH $SUBSCRIBE $SESSIONS $SESSIONS_QUEUE --port 5553 --bind-bus false &
echo 'Starting Router'
java -jar $JAR_NAME router $SESSIONS --distributor http://localhost:5553 $SESSIONS_QUEUE --port 4444 &
echo 'Starting Node'
java -jar $JAR_NAME node $PUBLISH $SUBSCRIBE &
```

* 此时，当前目录应包含以下文件：
    * `docker-compose.yml`
    * `init.sql`
    * `sessions.toml`
    * `distributed.sh`

* 现在可以运行 `distributed.sh` 脚本启动 Grid 并快速运行测试。
  此时，Grid 会将会话信息存储到 PostgreSQL 数据库中。

在某台机器上启动 `SessionMap` 的命令如下：

```bash
export SE_VERSION=<current_selenium_version>
java -jar selenium-server-${SE_VERSION}.jar \
--ext $(coursier fetch -p org.seleniumhq.selenium:selenium-session-map-jdbc:${SE_VERSION} org.postgresql:postgresql:42.3.1) \
sessions --publish-events tcp://localhost:4442 \
--subscribe-events tcp://localhost:4443 \
--port 5556 --config sessions.toml 
```

* 为便于说明，上述脚本中的变量名已替换为实际值。
* 请将 `localhost` 替换为运行事件总线的机器的实际主机名。
* 传递给 `coursier` 的参数是 Maven 坐标，即 GAV（Group、Artifact、Version）：
    * [selenium-session-map-jdbc](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-session-map-jdbc)
      用于将会话信息存储到数据库中。
    * [postgresql](https://mvnrepository.com/artifact/org.postgresql/postgresql)
      用于连接 PostgreSQL 数据库。
* `sessions.toml` 是前面创建的配置文件。

## 基于 Redis 的会话映射 {#redis-backed-session-map}

我们将通过 Docker Compose 文件启动一个 Redis 缓存容器。

### 步骤 {#steps-1}

如果你已有可用的 Redis 缓存实例，可以跳过此步骤。

* 创建名为 `docker-compose.yml` 的文件，内容如下：

```yaml
version: '3.8'
services:
  redis:
    image: redis:bullseye
    restart: always
    ports:
      - "6379:6379"
```

现在可以运行以下命令启动 Redis 容器：

```bash
docker-compose up -d
```

* 创建名为 `sessions.toml` 的 Selenium Grid 配置文件，内容如下：

```toml
[sessions]
scheme = "redis"
implementation = "org.openqa.selenium.grid.sessionmap.redis.RedisBackedSessionMap"
hostname = "localhost"
port = 6379
```

_注意：_如果你使用的是已有的 Redis 缓存实例，请将 `localhost` 和 `6379` 替换为该实例的实际主机名和端口号。

* 下面的简单 shell 脚本（命名为 `distributed.sh`）用于启动分布式 Grid：

```bash
SE_VERSION=<current_selenium_version>
JAR_NAME=selenium-server-${SE_VERSION}.jar
PUBLISH="--publish-events tcp://localhost:4442"
SUBSCRIBE="--subscribe-events tcp://localhost:4443"
SESSIONS="--sessions http://localhost:5556"
SESSIONS_QUEUE="--sessionqueue http://localhost:5559"
echo 'Starting Event Bus'
java -jar $JAR_NAME event-bus $PUBLISH $SUBSCRIBE --port 5557 &
echo 'Starting New Session Queue'
java -jar $JAR_NAME sessionqueue --port 5559 &
echo 'Starting Session Map'
java -jar $JAR_NAME \
--ext $(coursier fetch -p org.seleniumhq.selenium:selenium-session-map-redis:${SE_VERSION}) \
sessions $PUBLISH $SUBSCRIBE --port 5556 --config sessions.toml &
echo 'Starting Distributor'
java -jar $JAR_NAME  distributor $PUBLISH $SUBSCRIBE $SESSIONS $SESSIONS_QUEUE --port 5553 --bind-bus false &
echo 'Starting Router'
java -jar $JAR_NAME router $SESSIONS --distributor http://localhost:5553 $SESSIONS_QUEUE --port 4444 &
echo 'Starting Node'
java -jar $JAR_NAME node $PUBLISH $SUBSCRIBE &
```

* 此时，当前目录应包含以下文件：
    * `docker-compose.yml`
    * `sessions.toml`
    * `distributed.sh`

* 现在可以运行 `distributed.sh` 脚本启动 Grid 并快速运行测试。
  此时，Grid 会将会话信息存储到 Redis 实例中。
  你也可以使用 Redis GUI（例如 [TablePlus](https://tableplus.com/)）查看会话信息。
  请确保在测试中设置断点，因为测试运行结束后，这些值会被删除。

在某台机器上启动 `SessionMap` 的命令如下：

```bash
export SE_VERSION=<current_selenium_version>
java -jar selenium-server-${SE_VERSION}.jar \
--ext $(coursier fetch -p org.seleniumhq.selenium:selenium-session-map-redis:${SE_VERSION}) \
sessions --publish-events tcp://localhost:4442 \
--subscribe-events tcp://localhost:4443 \
--port 5556 --config sessions.toml 
```

* 为便于说明，上述脚本中的变量名已替换为实际值。
* 请将 `localhost` 替换为运行事件总线的机器的实际主机名。
* 传递给 `coursier` 的参数是 Maven 坐标，即 GAV（Group、Artifact、Version）：
    * [selenium-session-map-redis](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-session-map-redis)
      用于将会话信息存储到 Redis 缓存中。
* `sessions.toml` 是前面创建的配置文件。
