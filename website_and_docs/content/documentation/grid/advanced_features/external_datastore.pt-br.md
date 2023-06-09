---
title: "External datastore"
linkTitle: "External datastore"
weight: 5
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language d-4"></i>
   Page being translated from
   English to Portuguese. Do you speak Portuguese? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

## Table of Contents
* [Introduction](#introduction)
* [Setup](#setup)
* [Database backed Session Map](#database-backed-session-map) 
    * [Steps](#steps)
* [Redis backed Session Map](#redis-backed-session-map) 
    * [Steps](#steps)

## Introduction

Selenium Grid allows you to persist information related to currently running sessions into an external data store.
The external data store could be backed by your favourite database (or) Redis Cache system.

## Setup

* [Coursier](https://get-coursier.io/docs/cli-installation) - As a dependency resolver, so that we can download maven artifacts on the fly and make them available in our classpath
* [Docker](https://docs.docker.com/engine/install/) - To manage our PostGreSQL/Redis docker containers.

## Database backed Session Map

For the sake of this illustration, we are going to work with PostGreSQL database.

We will spin off a PostGreSQL database as a docker container using a docker compose file.

### Steps

You can skip this step if you already have a PostGreSQL database instance available at your disposal.

* Create a sql file named `init.sql` with the below contents:

```sql
CREATE TABLE IF NOT EXISTS sessions_map(
    session_ids varchar(256),
    session_caps text,
    session_uri varchar(256),
    session_stereotype text,
    session_start varchar(256)
 );
```

* In the same directory as the `init.sql`, create a file named `docker-compose.yml` with its contents as below:

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

We can now start our database container by running:

```bash
docker-compose up -d
```

_Our database name is `selenium_sessions` with its username and password set to `seluser`_

If you are working with an already running PostGreSQL DB instance, then you just need to create a database named `selenium_sessions` and the table `sessions_map` using the above mentioned SQL statement.

* Create a Selenium Grid configuration file named `sessions.toml` with the below contents:

```toml
[sessions]
implementation = "org.openqa.selenium.grid.sessionmap.jdbc.JdbcBackedSessionMap"
jdbc-url = "jdbc:postgresql://localhost:5432/selenium_sessions"
jdbc-user = "seluser"
jdbc-password = "seluser"
```

_Note:_ If you plan to use an existing PostGreSQL DB instance, then replace `localhost:5432` with the actual host and port number of your instance.

* Below is a simple shell script (let's call it `distributed.sh`) that we will use to bring up our distributed Grid.

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

* At this point the current directory should contain the following files:
    * `docker-compose.yml` 
    * `init.sql`
    * `sessions.toml`
    * `distributed.sh`

* You can now spawn the Grid by running `distributed.sh` shell script and quickly run a test. You will notice that the Grid now stores session information into the PostGreSQL database.

In the line which spawns a `SessionMap` on a machine:

```bash
export SE_VERSION=<current_selenium_version>
java -jar selenium-server-${SE_VERSION}.jar \
--ext $(coursier fetch -p org.seleniumhq.selenium:selenium-session-map-jdbc:${SE_VERSION} org.postgresql:postgresql:42.3.1) \
sessions --publish-events tcp://localhost:4442 \
--subscribe-events tcp://localhost:4443 \
--port 5556 --config sessions.toml 
```

* The variable names from the above script have been replaced with their actual values for clarity.
* Remember to substitute `localhost` with the actual hostname of the machine where your `Event-Bus` is running.
* The arguments being passed to `coursier` are basically the GAV (Group Artifact Version) Maven co-ordinates of: 
    * [selenium-session-map-jdbc](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-session-map-jdbc) which is needed to help us store sessions information in database
    * [postgresql](https://mvnrepository.com/artifact/org.postgresql/postgresql) which is needed to help us talk PostGreSQL database.
* `sessions.toml` is the configuration file that we created earlier.


## Redis backed Session Map

We will spin off a Redis Cache docker container using a docker compose file.

### Steps

You can skip this step if you already have a Redis Cache instance available at your disposal.

* Create a file named `docker-compose.yml` with its contents as below:

```yaml
version: '3.8'
services:
  redis:
    image: redis:bullseye
    restart: always
    ports:
      - "6379:6379"
```

We can now start our Redis container by running:

```bash
docker-compose up -d
```

* Create a Selenium Grid configuration file named `sessions.toml` with the below contents:

```toml
[sessions]
scheme = "redis"
implementation = "org.openqa.selenium.grid.sessionmap.redis.RedisBackedSessionMap"
hostname = "localhost"
port = 6379
```

_Note:_ If you plan to use an existing Redis Cache instance, then replace `localhost` and `6379` with the actual host and port number of your instance.

* Below is a simple shell script (let's call it `distributed.sh`) that we will use to bring up our distributed grid.

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

* At this point the current directory should contain the following files:
    * `docker-compose.yml`
    * `sessions.toml`
    * `distributed.sh`

* You can now spawn the Grid by running `distributed.sh` shell script and quickly run a test. You will notice that the Grid now stores session information into the Redis instance. You can perhaps make use of a Redis GUI such as [TablePlus](https://tableplus.com/) to see them (Make sure that you have setup a debug point in your test, because the values will get deleted as soon as the test runs to completion).

In the line which spawns a `SessionMap` on a machine:

```bash
export SE_VERSION=<current_selenium_version>
java -jar selenium-server-${SE_VERSION}.jar \
--ext $(coursier fetch -p org.seleniumhq.selenium:selenium-session-map-redis:${SE_VERSION}) \
sessions --publish-events tcp://localhost:4442 \
--subscribe-events tcp://localhost:4443 \
--port 5556 --config sessions.toml 
```

* The variable names from the above script have been replaced with their actual values for clarity.
* Remember to substitute `localhost` with the actual hostname of the machine where your `Event-Bus` is running.
* The arguments being passed to `coursier` are basically the GAV (Group Artifact Version) Maven co-ordinates of: 
    * [selenium-session-map-redis](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-session-map-redis) which is needed to help us store sessions information in Redis Cache.
* `sessions.toml` is the configuration file that we created earlier.

