---
title: "Toml配置选项"
linkTitle: "Toml选项"
weight: 3
description: 使用Toml文件的Grid配置示例.
aliases: [
"/zh-cn/documentation/grid/configuring_components/toml_options/"
]
requiresTranslation: true
---


[CLI选项]({{< ref "cli_options.md" >}}) 中
显示的所有选项都可以通过
[TOML](https://github.com/toml-lang/toml) 文件进行配置.
此页面显示不同Grid组件的配置示例.

{{% pageinfo color="primary" %}}
请注意, 如果修改或添加了选项,
但尚未记录, 则此文档可能已过时.
如果您遇到这种情况,
请查看 ["配置帮助"]({{< ref "help.md" >}}) 部分,
并随时向我们发送更新此页面的请求.
{{% /pageinfo %}}


## 概述

Selenium Grid对配置文件使用 [TOML](https://github.com/toml-lang/toml) 格式.
配置文件由多个部分组成,
每个部分都有选项及其各自的值.

有关详细的使用指南,
请参阅[TOML文档](https://toml.io/en/) .
如果出现解析错误,
请使用 [TOML linter](https://www.toml-lint.com/) 验证配置.

一般配置结构具有以下模式:

```toml
[section1]
option1="value"

[section2]
option2=["value1","value2"]
option3=true
```

下面是一些使用Toml文件配置的
Grid组件示例,
该组件可以
从下面的方式开始:

```
java -jar selenium-server-<version>.jar <component> --config /path/to/file/<file-name>.toml
```


### 单机模式

单机服务器,
在端口4449上运行,
新会话请求超时500秒.

```toml
[server]
port = 4449

[sessionqueue]
session-request-timeout = 500
```

### 特定浏览器和最大会话数限制

默认情况下仅启用Firefox
和Chrome的单机服务器或节点.

```toml
[node]
drivers = ["chrome", "firefox"]
max-sessions = 3
```

### 配置和定制驱动程序

具有定制驱动程序的单机或节点服务器,
允许使用Firefox试用或者每日构建的功能,
并且有不同的浏览器版本.

```toml
[node]
detect-drivers = false
[[node.driver-configuration]]
max-sessions = 100
display-name = "Firefox Nightly"
stereotype = "{\"browserName\": \"firefox\", \"browserVersion\": \"93\", \"platformName\": \"MAC\", \"moz:firefoxOptions\": {\"binary\": \"/Applications/Firefox Nightly.app/Contents/MacOS/firefox-bin\"}}"
[[node.driver-configuration]]
display-name = "Chrome Beta"
stereotype = "{\"browserName\": \"chrome\", \"browserVersion\": \"94\", \"platformName\": \"MAC\", \"goog:chromeOptions\": {\"binary\": \"/Applications/Google Chrome Beta.app/Contents/MacOS/Google Chrome Beta\"}}"
[[node.driver-configuration]]
display-name = "Chrome Dev"
stereotype = "{\"browserName\": \"chrome\", \"browserVersion\": \"95\", \"platformName\": \"MAC\", \"goog:chromeOptions\": {\"binary\": \"/Applications/Google Chrome Dev.app/Contents/MacOS/Google Chrome Dev\"}}"
webdriver-executable = '/path/to/chromedriver/95/chromedriver'
```

### 带Docker的单机或节点

单机或节点服务器能够在Docker容器中运行每个新会话.
禁用驱动程序检测,
则最多有2个并发会话.
原型配置需要映射一个Docker映像,
Docker的守护进程需要通过http/tcp公开.
此外，可以通过 `devices` 属性定义在主机上可访问的哪些设备文件将在容器中可用。
有关 docker 设备映射如何工作的更多信息，请参阅 [docker](https://docs.docker.com/engine/reference/commandline/run/#add-host-device-to-container---device) 文档。

```toml
[node]
detect-drivers = false
max-sessions = 2

[docker]
configs = [
    "selenium/standalone-chrome:93.0", "{\"browserName\": \"chrome\", \"browserVersion\": \"91\"}", 
    "selenium/standalone-firefox:92.0", "{\"browserName\": \"firefox\", \"browserVersion\": \"92\"}"
]
#Optionally define all device files that should be mapped to docker containers
#devices = [
#    "/dev/kvm:/dev/kvm"
#]
url = "http://localhost:2375"
video-image = "selenium/video:latest"
```

### 将命令中继到支持WebDriver的服务端点

连接到支持WebDriver外部服务
的Selenium Grid非常有用.
这种服务的一个例子可以是
云提供商或Appium服务器. 这样,
Grid可以实现对本地不存在的平台和版本的更多覆盖.

下面是一个将Appium服务器连接到Grid的示例.

```toml
[node]
detect-drivers = false

[relay]
# Default Appium/Cloud server endpoint
url = "http://localhost:4723/wd/hub"
status-endpoint = "/status"
# Stereotypes supported by the service. The initial number is "max-sessions", and will allocate 
# that many test slots to that particular configuration
configs = [
  "5", "{\"browserName\": \"chrome\", \"platformName\": \"android\", \"appium:platformVersion\": \"11\"}"
]
```

### 启用基本身份验证

通过配置包含用户名和密码的
路由器/集线器/单机的方式,
可以使用这样的基本身份验证保护Grid.
加载Grid UI或者开始一个新的会话时
需要此用户/密码组合.

```toml
[router]
username = "admin"
password = "myStrongPassword"
```

下面是一个Java示例, 演示如何使用配置的用户和密码启动会话.

```java
URL gridUrl = new URL("http://admin:myStrongPassword@localhost:4444");
RemoteWebDriver webDriver = new RemoteWebDriver(gridUrl, new ChromeOptions());
```

### Setting custom capabilities for matching specific Nodes

**Important:** Custom capabilities need to be set in the configuration in all Nodes. They also
need to be included always in every session request.

```toml
[node]
detect-drivers = false

[[node.driver-configuration]]
display-name = "firefox"
stereotype = '{"browserName": "firefox", "platformName": "macOS", "browserVersion":"96", "networkname:applicationName":"node_1", "nodename:applicationName":"app_1" }'
max-sessions = 5
```

Here is a Java example showing how to match that Node

```java
FirefoxOptions options = new FirefoxOptions();
options.setCapability("networkname:applicationName", "node_1");
options.setCapability("nodename:applicationName", "app_1");
options.setBrowserVersion("96");
options.setPlatformName("macOS");
WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
driver.get("https://selenium.dev");
driver.quit();
```
