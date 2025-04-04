---
title: "Selenium Manager (测试版)"
linkTitle: "Selenium Manager"
weight: 3
description: >
  Selenium Manager 是一个用 Rust 语言实现的命令行工具, 为 Selenium 提供了自动化的驱动程序和浏览器管理功能. Selenium 默认绑定使用此工具, 因此您无需下载它, 也不需要在代码中添加任何内容或执行其他操作即可使用它. 
---

## 动机
***简而言之:*** 
*Selenium Manager 是 Selenium 项目的官方驱动程序管理器, 并且在每次 Selenium 发布时都会随附提供.*

Selenium 利用每个浏览器实现的原生支持来执行自动化流程. 
因此, Selenium 用户需要在使用 Selenium API 的脚本和浏览器之间放置一个名为 _驱动程序_(如 chromedriver、geckodriver、msedgedriver 等)的组件. 
多年来, 管理这些驱动程序, 对Selenium 用户来说, 一直是个繁琐手动过程. 
他们必须下载浏览器所需的驱动程序(如 Chrome 的 chromedriver、Firefox 的 geckodriver 等), 
并将其放置在  `PATH`  中或以系统属性的形式导出驱动程序路径(如 Java、JavaScript 等). 
但这种过程很麻烦, 导致了可维护性问题.

让我们来看一个例子. 
假设您手动下载了用于通过 Selenium 驱动 Chrome 的所需 chromedriver. 
在执行此操作时, Chrome 的稳定版本是 113, 
所以您下载了 chromedriver 113 并将其放在您的  `PATH` 中. 
此时, 您的 Selenium 脚本执行正确. 但 *问题* 在于 Chrome 是 *保持更新* 的. 
这指的是 Chrome 能够在有新版本可用时自动且静默地升级到下一个稳定版本. 
此功能对终端用户来说很棒, 但对浏览器自动化来说可能很危险. 
让我们回到这个例子来明确这一点. 
当您本地的 Chrome 最终更新到了 115 版本. 
此时, 由于手动下载的驱动程序(113 版)与 Chrome 版本(115 版)不兼容, 
您的 Selenium 脚本出错了. 
因此, 您的 Selenium 脚本会因以下错误消息而失败: 
*“会话无法创建: 此版本的 ChromeDriver 仅支持 Chrome 版本 113”*. 


这个问题是所谓的驱动管理器(例如 Java 的 [WebDriverManager](https://bonigarcia.dev/webdrivermanager/) 、
Python 的 [webdriver-manager](https://pypi.org/project/webdriver-manager/) 、
JavaScript 的 [webdriver-manager](https://www.npmjs.com/package/webdriver-manager) 、
C# 的 [WebDriverManager.Net](https://github.com/rosolko/WebDriverManager.Net) 以及 Ruby 的 [webdrivers](https://github.com/titusfortner/webdrivers) 
存在的主要原因. 
所有这些项目都是一种启示, 
也清楚地表明社区需要将此功能内置到 Selenium 中. 
因此, Selenium 项目创建了 *Selenium Manager*, 
这是 Selenium 的官方驱动管理器, 从 4.6 版本开始, 它随每个 Selenium 发行版一起提供. 


## 用法
***简而言之:*** 
*当驱动程序(如 ChromeDriver、GeckoDriver 等)不可用时, Selenium 绑定会使用 Selenium Manager.*

通过 Selenium Manager 进行驱动程序管理是 Selenium 绑定的 *可选功能* . 
因此, 用户可以继续手动管理其驱动程序(将驱动程序放在  `PATH`   中或使用系统属性), 
也可以依靠第三方 *驱动程序管理器* 自动完成. 
Selenium Manager 仅作为备用方案: 
如果未提供驱动程序, Selenium Manager 将会介入. 

Selenium Manager 是一个用 Rust 语言实现的命令行界面(CLI)工具, 
可于 Windows、Linux 和 macOS 等多种操作系统上跨平台运行. 
Selenium Manager 的二进制文件随每个 Selenium 版本一起发布. 
这样, 每个 Selenium 绑定语言都会调用 Selenium Manager 来执行以下各节中所述的自动化驱动程序和浏览器管理. 

## Automated driver management 自动驱动管理
***简而言之:*** 
*当所需驱动程序不可用时, Selenium Manager 会自动寻找、下载并缓存 Selenium 所需的驱动程序.*

Selenium Manager 的主要特性被称为 *自动驱动管理* . 
让我们通过一个例子来理解它. 假设我们想用 Selenium 驱动 Chrome(请参阅关于如何[使用Selenium启动会话](https://www.selenium.dev/documentation/webdriver/getting_started/first_script/#1-start-the-session)的文档). 
在会话开始之前, 如果驱动程序不可用, Selenium Manager 会为我们管理 chromedriver. 
我们用 *管理* 这个词来描述此功能(而不仅仅是 *下载* ), 因为这个过程更广泛, 包含不同的步骤: 

1. 探索浏览器版本. Selenium Manager 会发现执行 Selenium 的机器上安装的浏览器版本(例如, Chrome、Firefox、Edge). 此步骤使用 shell 命令(例如,  `google-chrome --version` ). 
2. 寻找驱动程序版本. 通过探索过的浏览器版本, 确定合适的驱动程序版本. 在此步骤中, 使用由浏览器供应商维护的在线元数据/端点(例如, [chromedriver](https://chromedriver.chromium.org/downloads), [geckodriver](https://github.com/mozilla/geckodriver/releases), 或 [msedgedriver](https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/)). 
3. 驱动下载. 通过解析出的驱动程序版本获取驱动程序的 URL；利用该 URL 下载驱动程序文件, 解压后将其存储在本地. 
4. 驱动程序缓存. 未压缩的驱动程序二进制文件存储在本地缓存文件夹( `~/.cache/selenium` )中. 下次需要相同的驱动程序时, 如果该驱动程序已在缓存中, 则将从那里使用. 


## 自动化浏览器管理
***简而言之:*** 
*当本地系统未安装 Selenium 驱动的浏览器(Chrome、Firefox 和 Edge)时, Selenium Manager 会自动发现、下载并缓存这些浏览器.*

从 Selenium 4.11.0 版本开始, Selenium Manager 还实现了 *自动浏览器管理*. 
借助此功能, Selenium Manager 能够发现、下载并缓存不同浏览器的版本, 
使其能够无缝地供 Selenium 使用. 
在内部, Selenium Manager 使用了与前一节所述类似的管理流程, 但这次是针对浏览器版本的. 

Selenium Manager 自动管理的浏览器有: 

- Chrome浏览器, 基于 [Chrome for Testing (CfT)](https://googlechromelabs.github.io/chrome-for-testing/), 自 Selenium 4.11.0 版本起. 
- 火狐浏览器. 基于[public Firefox releases](https://ftp.mozilla.org/pub/firefox/releases/), 自 Selenium 4.12.0 版本. 
- Edge浏览器, 基于 [Edge downloads](https://www.microsoft.com/en-us/edge/download), 自 Selenium 4.14.0 版本. 

让我们再次考虑用 Selenium 驱动 Chrome 的典型示例. 
这一次, 假设在[启动新会话](https://www.selenium.dev/documentation/webdriver/getting_started/first_script/#1-start-the-session)时本地机器上未安装 Chrome. 
在这种情况下, Selenium Manager会发现、下载并缓存当前的稳定版 Chrome 浏览器(在 `~/.cache/selenium/chrome` 中). 

但不仅如此. 除了稳定的浏览器版本, 
Selenium Manager 还允许下载旧版浏览器(对于 Chrome for Testing 而言, 
从 113 版本开始, 这是作为 Chrome for Testing 发布的第一个版本). 
要使用 Selenium 设置浏览器版本, 我们使用一个名为 [browserVersion](https://www.selenium.dev/documentation/webdriver/drivers/options/#browserversion) 的浏览器选项. 

让我们考虑另一个简单的例子. 
假设我们使用 [Chrome options](https://www.selenium.dev/documentation/webdriver/browsers/chrome/) 将  `browserVersion`  设置为  `114` . 
在这种情况下, Selenium Manager会检查是否已安装 Chrome 114 版本. 
如果已安装, 就会使用它. 如果没有安装, Selenium Manager会进行管理(即发现、下载并缓存)Chrome 114 版本. 
无论哪种情况, chromedriver 也会被管理. 最后, Selenium 会像往常一样启动 Chrome 以实现程序化驱动. 

但还有更多. 除了固定的浏览器版本(例如,  `113`, `114`, `115`  等), 
我们还可以为  `browserVersion`  使用以下标签: 

- `stable`: 当前 CfT 版本.
- `beta`: 下一个稳定版.
- `dev`: 当前正在开发的版本.
- `canary`: 面向开发者的夜间构建版本.
- `esr`: 扩展支持版本(仅适用于 Firefox 浏览器).

当指定了这些标签时, Selenium Manager首先会检查给定的浏览器是否已安装( `beta`, `dev`  等), 
如果未检测到, 则会自动管理该浏览器. 

### Windows中的Edge

在 Windows 系统中, Selenium Manager 对 Edge 的自动化边缘管理与其他浏览器有所不同. 
对于 Chrome 和 Firefox(以及 macOS 和 Linux 系统中的 Edge), 
Selenium Manager 会自动将其下载到本地缓存( `~/.cache/selenium` ). 
然而, 在 Windows 系统中, Edge 却无法实现同样的操作. 
原因在于 Windows 版本的 Edge 安装程序是以微软安装程序(MSI)文件的形式分发的, 
需要以管理员权限执行. 
因此, 当在 Windows 系统中使用非管理员权限会话通过 Selenium Manager 安装 Edge 时, 
Selenium Manager 会显示如下警告信息: 

```
edge can only be installed in Windows with administrator permissions
```

因此, 通过 Selenium Manager 在 Windows 系统中自动安装 Edge 浏览器需要管理员权限, 
并且 Edge 最终会被安装在通常的程序文件夹中(例如 `C:\Program Files (x86)\Microsoft\Edge` ). 

## 数据收集
Selenium Manager会向 [Plausible](https://plausible.io/manager.selenium.dev) 报送匿名使用[statistics](https://plausible.io/privacy-focused-web-analytics) 数据. 
这能让 Selenium 团队更深入地了解 Selenium 的使用情况, 
从而更好地集中我们的开发精力. 所收集的数据包括: 

| Data                          | Purpose |
|-------------------------------|---------|
| Selenium 版本                   | 这使得 Selenium 开发人员能够安全地弃用和移除功能, 并确定哪些新功能可能对您可用.  |
| 语言绑定                          | 用于执行 Selenium 脚本的编程语言(Java、JavaScript、Python、.Net、Ruby)|
| Selenium Manager 正在运行的操作系统和架构 | Selenium 开发人员可以利用这些信息来帮助确定错误报告的优先级, 并识别是否存在系统性的与操作系统相关的故障.  |
| 浏览器及浏览器版本                     | 协助确定错误报告的优先级 |
| 大致地理位置                        | 根据您连接的 IP 地址得出. 这有助于我们确定需要在哪些地区集中文档编写工作.  |

Selenium Manager 每天会将这些数据发送给 Plausible 一次. 
此周期基于 TTL 值(请参阅[configuration](https://www.selenium.dev/documentation/selenium_manager/#configuration)). 

### 选择退出数据收集
**默认情况下会收集数据.** 若要禁用数据收集, 请将 `SE_AVOID_STATS` 环境变量设置 `true`.
您也可以在配置文件中(见下文)通过设置  `avoid-stats = true` 来禁用数据收集. 

## 配置
***简而言之:*** 
*对于大多数用户而言, Selenium Manager 应该能静默且透明地运行. 不过, 在某些场景下(例如指定自定义缓存路径或全局设置代理), 可能需要自定义配置.*

Selenium Manager 是一个命令行界面(CLI)工具. 
因此, 在底层, Selenium 绑定通过调用 shell 命令来调用 Selenium Manager. 
和任何其他 CLI 工具一样, 可以使用参数来指定 Selenium Manager 中的特定功能. 
要查看 Selenium Manager 支持的不同参数, 可以运行以下命令: 

```
$ ./selenium-manager --help
```

除了命令行参数之外, Selenium Manager 还支持两种额外的配置机制: 

- 配置文件. Selenium Manager使用位于 Selenium 缓存中的一个名为  `se-config.toml`   的文件(默认情况下位于  `~/.cache/selenium` )来存储自定义配置值. 此 TOML 文件包含用于自定义配置的键值集合. 
- 环境变量. 每个配置键在环境变量中都有对应的等效项, 方法是将每个键名转换为大写, 将破折号(`-`)替换为下划线(`_`), 并在前面加上前缀`SE_`. 

当存在配置文件且未指定相应的命令行参数时, Selenium Manager 会遵循该配置文件. 
此外, 如果既未指定命令行参数也未提供配置文件, 则会使用环境变量. 换句话说, Selenium Manager 自定义配置的优先级顺序如下: 

1. CLI参数. 
2. 配置文件. 
3. 环境变量. 

请注意, Selenium 绑定使用命令行参数来指定配置值, 
而这些配置值又在每个绑定中通过[browser options](https://www.selenium.dev/documentation/webdriver/drivers/options/)来定义. 

下表总结了 Selenium Manager 支持的所有参数及其在配置文件和环境变量中的对应键. 


| CLI argument| Configuration file | Env variable | Description |
|-------------|--------------------|--------------|-------------|
|`--browser BROWSER`|`browser = "BROWSER"`|`SE_BROWSER=BROWSER`|Browser name: `chrome`, `firefox`, `edge`, `iexplorer`, `safari`, `safaritp`, or `webview2`|
|`--driver <DRIVER>`|`driver = "DRIVER"`|`SE_DRIVER=DRIVER`|Driver name: `chromedriver`, `geckodriver`, `msedgedriver`, `IEDriverServer`, or `safaridriver`|
|`--browser-version <BROWSER_VERSION>`|`browser-version = "BROWSER_VERSION"`|`SE_BROWSER_VERSION=BROWSER_VERSION`|Major browser version (e.g., `105`, `106`, etc. Also: `beta`, `dev`, `canary` -or `nightly`-, and `esr` -in Firefox- are accepted)|
|`--driver-version <DRIVER_VERSION>`|`driver-version = "DRIVER_VERSION"`|`SE_DRIVER_VERSION=DRIVER_VERSION`|Driver version (e.g., `106.0.5249.61, 0.31.0`, etc.)|
|`--browser-path <BROWSER_PATH>`|`browser-path = "BROWSER_PATH"`|`SE_BROWSER_PATH=BROWSER_PATH`|Browser path (absolute) for browser version detection (e.g., `/usr/bin/google-chrome`, `/Applications/Google Chrome.app/Contents/MacOS/Google Chrome`, `C:\Program Files\Google\Chrome\Application\chrome.exe`)|
|`--driver-mirror-url <DRIVER_MIRROR_URL>`|`driver-mirror-url = "DRIVER_MIRROR_URL"`|`SE_DRIVER_MIRROR_URL=DRIVER_MIRROR_URL`|Mirror URL for driver repositories|
|`--browser-mirror-url <BROWSER_MIRROR_URL>`|`browser-mirror-url = "BROWSER_MIRROR_URL"`|`SE_BROWSER_MIRROR_URL=BROWSER_MIRROR_URL`|Mirror URL for browser repositories|
|`--output <OUTPUT>`|`output = "OUTPUT"`|`SE_OUTPUT=OUTPUT`|Output type: `LOGGER` (using `INFO`, `WARN`, etc.), `JSON` (custom JSON notation), `SHELL` (Unix-like), or `MIXED` (`INFO`, `WARN`, `DEBUG`, etc. to stderr and minimal `JSON` to stdout). Default: `LOGGER`|
|`--os <OS>`|`os = "OS"`|`SE_OS=OS`|Operating system for drivers and browsers (i.e., `windows`, `linux`, or `macos`)|
|`--arch <ARCH>`|`arch = "ARCH"`|`SE_ARCH=ARCH`|System architecture for drivers and browsers (i.e., `x32`, `x64`, or `arm64`)|
|`--proxy <PROXY>`|`proxy = "PROXY"`|`SE_PROXY=PROXY`|HTTP proxy for network connection (e.g., `myproxy:port`, `myuser:mypass@myproxy:port`)|
|`--timeout <TIMEOUT>`|`timeout = TIMEOUT`|`SE_TIMEOUT=TIMEOUT`|Timeout for network requests (in seconds). Default: `300`|
|`--offline`|`offline = true`|`SE_OFFLINE=true`|Offline mode (i.e., disabling network requests and downloads)|
|`--force-browser-download`|`force-browser-download = true`|`SE_FORCE_BROWSER_DOWNLOAD=true`|Force to download browser, e.g., when a browser is already installed in the system, but you want Selenium Manager to download and use it|
|`--avoid-browser-download`|`avoid-browser-download = true`|`SE_AVOID_BROWSER_DOWNLOAD=true`|Avoid to download browser, e.g., when a browser is supposed to be downloaded by Selenium Manager, but you prefer to avoid it|
|`--debug`|`debug = true`|`SE_DEBUG=true`|Display `DEBUG` messages|
|`--trace`|`trace = true`|`SE_TRACE=true`|Display `TRACE` messages|
|`--cache-path <CACHE_PATH>`|`cache-path="CACHE_PATH"`|`SE_CACHE_PATH=CACHE_PATH`|Local folder used to store downloaded assets (drivers and browsers), local metadata, and configuration file. See next section for details. Default: `~/.cache/selenium`. For Windows paths in the TOML configuration file, double backslashes are required (e.g., `C:\\custom\\cache`).|
|`--ttl <TTL>`|`ttl = TTL`|`SE_TTL=TTL`|Time-to-live in seconds. See next section for details. Default: `3600` (1 hour)|
|`--language-binding <LANGUAGE>`|`language-binding = "LANGUAGE"`|`SE_LANGUAGE_BINDING=LANGUAGE`|Language that invokes Selenium Manager (e.g., Java, JavaScript, Python, DotNet, Ruby)|
|`--avoid-stats`|`avoid-stats = true`|`SE_AVOID_STATS=true`|Avoid sends usage statistics to plausible.io. Default: `false`|

除了前面表格中指定的配置键之外, 还有一些特殊情况, 即: 

- 浏览器版本. 除了 `browser-version` 之外, 我们还可以使用特定的配置键为每个受支持的浏览器指定自定义版本. 这样, 键 `chrome-version`, `firefox-version`, `edge-version` 等就得到了支持. 环境变量(即 `SE_CHROME_VERSION`, `SE_FIREFOX_VERSION`, `SE_EDGE_VERSION`  等)也是如此. 
- 驱动程序版本. 遵循相同的模式, 我们可以在配置文件中使用 `chromedriver-version`, `geckodriver-version`,  `msedgedriver-version`等, 在环境变量中使用 `SE_CHROMEDRIVER_VERSION`, `SE_GECKODRIVER_VERSION`, `SE_MSEDGEDRIVER_VERSION` 等. 
- 浏览器路径. 遵循相同的模式, 我们可以在配置文件中使用  `chrome-path`, `firefox-path`,  `edge-path`  等, 在环境变量中使用 `SE_CHROME_PATH`, `SE_FIREFOX_PATH`, `SE_EDGE_PATH`等. Selenium 绑定还允许使用选项指定浏览器路径的自定义位置, 例如: Chrome、Edge 或 Firefox. 
- 驱动镜像. 遵循同样的模式, 我们可以在配置文件中使用  `chromedriver-mirror-url`, `geckodriver-mirror-url`,  `msedgedriver-mirror-url` 等, 在环境变量中使用 `SE_CHROMEDRIVER_MIRROR_URL`, `SE_GECKODRIVER_MIRROR_URL`, `SE_MSEDGEDRIVER_MIRROR_URL` 等. 
- 浏览器镜像. 遵循同样的模式, 我们可以在配置文件中使用 `chrome-mirror-url`, `firefox-mirror-url`,  `edge-mirror-url` 等, 在环境变量中使用 `SE_CHROME_MIRROR_URL`, `SE_FIREFOX_MIRROR_URL`, `SE_EDGE_MIRROR_URL` 等. 

### se-config.toml 示例
{{< tabpane text=true >}}
{{< tab header="se-config.toml" >}}
{{< gh-codeblock path="examples/python/tests/selenium_manager/example_se-config.toml#L1-L21" >}}
{{< /tab >}}
{{< /tabpane >}}

## 缓存
***简而言之:*** 
*由 Selenium Manager 管理的驱动程序和浏览器会存储在本地文件夹(`~/.cache/selenium` )中.*

Selenium Manager中的缓存是一个本地文件夹(默认为 `~/.cache/selenium` ), 用于存储下载的资源(驱动程序和浏览器). 
为了提高性能, 当驱动程序或浏览器已存在于缓存中(即存在 *缓存* 提示)时, Selenium Manager会从那里使用它们. 

除了已下载的驱动程序和浏览器之外, 缓存根目录中还有两个额外的文件: 

- 配置文件( `se-config.toml` ). 此文件是可选的, 正如前一节所述, 它允许为 Selenium Manager 存储自定义配置值. 此文件由最终用户维护, 并由 Selenium Manager 读取. 
- 元数据文件( `se-metadata.json` ). 此文件包含由 Selenium Manager通过网络请求(例如, 使用 [CfT JSON endpoints](https://github.com/GoogleChromeLabs/chrome-for-testing#json-api-endpoints))发现的版本以及它们有效的生存时间(TTL). Selenium Manager会自动维护此文件. 

Selenium Manager 中的生存时间(TTL)借鉴了 DNS 的 TTL 机制, 这是一种广为人知的机制, 
指的是某些值在被自动刷新之前被缓存的时长. 
对于 Selenium Manager 而言, 这些值是通过网络请求获取的驱动程序和浏览器版本发现的结果. 
默认情况下, TTL 为  3600  秒(即 1 小时), 并且可以通过配置值进行调整, 或者将其设置为  0  来禁用此功能. 

TTL 机制是一种提升 Selenium 总体性能的方法. 
它基于这样一个事实: 在短期内, 发现的驱动程序和浏览器版本(例如, 适用于 Chrome 115 的正确 chromedriver 版本是 115.0.5790.170)很可能保持不变. 
因此, 发现的版本会被写入元数据文件, 并从那里读取, 而不是连续进行相同的网络请求. 
这样, 在驱动程序版本发现过程中(之前介绍的自动化驱动程序管理流程的第 2 步), 
Selenium Manager 首先读取元数据文件. 如果找到新的解决方案(即在 TTL 期间有效的驱动程序/浏览器版本), 
则使用该版本(节省了进行新网络请求的时间). 
如果未找到或 TTL 已过期, 则会进行网络请求, 并将结果存储在元数据文件中. 

我们来看一个例子. 
一个 Selenium 绑定请求 Selenium Manager解析 chromedriver. 
Selenium Manager检测到已安装 Chrome 115 版本, 
于是向 CfT 端点发出网络请求以确定合适的 chromedriver 版本(当时为 115.0.5790.170). 
此版本会被存储在元数据文件中, 并在接下来的一小时内(TTL)被视为有效. 
如果在此期间(在执行测试套件时很可能发生)再次请求 Selenium Manager解析 chromedriver, 
那么将通过读取元数据文件来获取 chromedriver 版本, 而无需向 CfT 端点发出新的请求. 一小时后, 
缓存中存储的 chromedriver 版本将被视为过期, Selenium Manager会通过向相应端点发出新的网络请求来刷新它. 

Selenium Manager includes two additional arguments two handle the cache, namely:
Selenium Manager包含两个用于处理缓存的附加参数, 分别是: 

- `--clear-cache`:  要删除缓存文件夹(相当于环境变量 `SE_CLEAR_CACHE=true` ). 
- `--clear-metadata` : 删除元数据文件(相当于环境变量  `SE_CLEAR_METADATA=true` ). 

## 版本控制
Selenium Manager 采用与 Selenium 相同的版本命名规则. 
不过, 由于 Selenium Manager 仍处于测试阶段, 因此其主版本号为 0. 
例如, 与 Selenium 4.12.0 一同发布的 Selenium Manager 二进制文件对应的是 0.4.12 版本. 

## 获取 Selenium Manager
对于大多数用户而言, 由于 Selenium 绑定会在内部使用 Selenium Manager, 
所以无需直接与之交互. 
不过, 如果您想试用 Selenium Manager 或将其用于涉及驱动程序或浏览器管理的用例, 
可以通过多种方式获取 Selenium Manager 的二进制文件

- Selenium Manager的源代码存储在 Selenium 主仓库的 [rust](https://github.com/SeleniumHQ/selenium/tree/trunk/rust) 文件夹中. 此外, 您可以在 [Selenium Manager Artifacts](https://github.com/SeleniumHQ/selenium_manager_artifacts) 仓库中找到适用于 Windows、Linux 和 macOS 的编译版本. 此文件中链接了稳定版的 Selenium Manager二进制[file](https://github.com/SeleniumHQ/selenium/blob/trunk/common/selenium_manager.bzl)(即在最新稳定版 Selenium 中分发的那些). 
- 在构建工作流中, Selenium Manager 是通过 [GitHub Actions workflow](https://github.com/SeleniumHQ/selenium/actions/workflows/ci-rust.yml)进行编译的. 此工作流会为 Windows、Linux 和 macOS 创建二进制文件. 您可以从这些工作流执行中下载这些二进制文件. 
- 在缓存中. 自 Selenium Java 绑定的 4.15.0 版本起, Selenium Manager 二进制文件会被提取并复制到缓存文件夹中. 例如, 与 Selenium 4.15.0 一同提供的 Selenium Manager 二进制文件会存储在文件夹  `~/.cache/selenium/manager/0.4.15`  中. 

## 例子
让我们来看一个典型的例子: 我们想要自动管理 chromedriver. 
为此, 我们像下面这样调用 Selenium Manager
(请注意, 标志 `--debug` 是可选的, 但它有助于我们理解 Selenium Manager 正在做什么): 

```
$ ./selenium-manager --browser chrome --debug
DEBUG   chromedriver not found in PATH
DEBUG   chrome detected at C:\Program Files\Google\Chrome\Application\chrome.exe
DEBUG   Running command: wmic datafile where name='C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe' get Version /value
DEBUG   Output: "\r\r\n\r\r\nVersion=116.0.5845.111\r\r\n\r\r\n\r\r\n\r"
DEBUG   Detected browser: chrome 116.0.5845.111
DEBUG   Discovering versions from https://googlechromelabs.github.io/chrome-for-testing/known-good-versions-with-downloads.json
DEBUG   Required driver: chromedriver 116.0.5845.96
DEBUG   Downloading chromedriver 116.0.5845.96 from https://edgedl.me.gvt1.com/edgedl/chrome/chrome-for-testing/116.0.5845.96/win64/chromedriver-win64.zip
INFO    Driver path: C:\Users\boni\.cache\selenium\chromedriver\win64\116.0.5845.96\chromedriver.exe
INFO    Browser path: C:\Program Files\Google\Chrome\Application\chrome.exe
```

在这种情况下, Selenium Manager会检测到本地的 Chrome(在 Windows 系统中). 
然后, 根据其版本和 CfT 端点, 会将合适的 chromedriver 版本(在此示例中为 115 版)下载到本地缓存. 
最后, Selenium Manager提供两个结果: 
i)驱动程序路径(已下载)和 ii)浏览器路径(本地). 

让我们再来看一个例子. 现在我们想要使用 Chrome 测试版. 
因此, 我们调用 Selenium Manager并指定该版本标签, 
如下所示(请注意, CfT 测试版会被发现、下载并存储在本地缓存中): 

```
$ ./selenium-manager --browser chrome --browser-version beta --debug
DEBUG   chromedriver not found in PATH
DEBUG   chrome not found in PATH
DEBUG   chrome beta not found in the system
DEBUG   Discovering versions from https://googlechromelabs.github.io/chrome-for-testing/last-known-good-versions-with-downloads.json
DEBUG   Required browser: chrome 117.0.5938.22
DEBUG   Downloading chrome 117.0.5938.22 from https://edgedl.me.gvt1.com/edgedl/chrome/chrome-for-testing/117.0.5938.22/win64/chrome-win64.zip
DEBUG   chrome 117.0.5938.22 has been downloaded at C:\Users\boni\.cache\selenium\chrome\win64\117.0.5938.22\chrome.exe
DEBUG   Discovering versions from https://googlechromelabs.github.io/chrome-for-testing/known-good-versions-with-downloads.json
DEBUG   Required driver: chromedriver 117.0.5938.22
DEBUG   Downloading chromedriver 117.0.5938.22 from https://edgedl.me.gvt1.com/edgedl/chrome/chrome-for-testing/117.0.5938.22/win64/chromedriver-win64.zip
INFO    Driver path: C:\Users\boni\.cache\selenium\chromedriver\win64\117.0.5938.22\chromedriver.exe
INFO    Browser path: C:\Users\boni\.cache\selenium\chrome\win64\117.0.5938.22\chrome.exe
```

### 在您的脚本中实现 Selenium Manager

{{< tabpane text=true >}}
{{% tab header="Java" %}}
**Previously**
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/selenium_manager/SeleniumManagerUsageDemo.java#L10-L15" >}}
**Selenium Manager**
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/selenium_manager/SeleniumManagerUsageDemo.java#L18-L22" >}}
{{< /tab >}}
{{% tab header="Python" %}}
**Previously**
{{< gh-codeblock path="examples/python/tests/selenium_manager/usage.py#L5-L8" >}}
**Selenium Manager**
{{< gh-codeblock path="examples/python/tests/selenium_manager/usage.py#L10-L12" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## Selenium Grid
Selenium Manager 可让您在设置 Selenium Grid 时自动配置驱动程序. 
为此, 您需要在启动 Selenium Grid 的命令中包含 `--selenium-manager true` 参数. 
更多详情, 请访问 [Selenium Grid starting page](https://www.selenium.dev/documentation/grid/getting_started/). 

此外, Selenium Manager 还允许自动管理 Selenium Grid 的版本. 为此, 使用如下参数  `--grid`  : 

```
$ ./selenium-manager --grid
```

执行此命令后, Selenium Manager会发现 Selenium Grid 的最新版本, 
并将 `selenium-server.jar` 存储在本地缓存中. 

可选地, 参数  `--grid`  允许指定 Selenium Grid 版本( `--grid <GRID_VERSION>` ). 

## 已知的限制

###  连接问题
Selenium Manager会请求远程端点(例如 Chrome 测试版(CfT)等)
从在线存储库中发现并下载驱动程序和浏览器. 
当在具有代理或防火墙的企业环境中执行此操作时, 可能会导致以下连接问题: 

```
error sending request for url (https://googlechromelabs.github.io/chrome-for-testing/known-good-versions-with-downloads.json)
```

```
error trying to connect: dns error: failed to lookup address information
```

```
error trying to connect: An existing connection was forcibly closed by the remote host. (os error 10054)
```

当这种情况发生时, 请考虑以下解决方案: 

- 使用 Selenium 的代理功能(请参阅[documentation](https://www.selenium.dev/documentation/webdriver/drivers/options/#proxy)). 或者, 使用环境变量   `SE_PROXY` 来设置代理 URL, 或者使用配置文件(请参阅 [configuration](https://www.selenium.dev/documentation/selenium_manager/#configuration) ). 
- 检查您的网络设置, 以启用 Selenium Manager所需的远程请求和下载. 

### 自定义包管理器
如果您正在使用需要特定驱动程序的 Linux 软件包管理器(如 Anaconda、snap 等)来运行您的浏览器, 
您可能需要指定[driver location](https://www.selenium.dev/documentation/webdriver/drivers/service/#driver-location)、[browser location](https://www.selenium.dev/documentation/webdriver/browsers/chrome/#start-browser-in-a-specified-location), 
或者两者都需要, 具体取决于管理器的要求. 



### 备选架构
Selenium 支持由谷歌 Chrome for Testing 管理的所有五种架构, 
以及为微软 Edge 提供的所有六种驱动程序. 

Selenium 绑定的每次发布都包含三个独立的 Selenium Manager 二进制文件, 
分别适用于 Linux、Windows 和 Mac 系统. 

* Mac 版本支持 x64 和 aarch64(英特尔和苹果)架构. 
* Windows 版本应适用于 x86 和 x64(32 位和 64 位操作系统). 
* Linux 版本仅经过验证可在 x64 系统上运行

不支持更多架构的原因: 

1. 无论是 Chrome for Testing 还是 Microsoft Edge 都不支持其他架构, 因此 Selenium Manager 需要管理一些非官方的东西才能使其正常工作. 
2. 我们目前从现有的 GitHub 操作运行器构建二进制文件, 这些运行器不支持这些架构. 
3. 任何额外的架构都会随所有 Selenium 版本一起分发, 从而增加总的构建大小. 

如果您在 arm64/aarch64、32 位架构或树莓派上运行 Linux, Selenium Manager 将无法为您服务.  
对于用户来说, 最大的问题在于他们过去常常获取自定义构建的驱动程序并将其放在 PATH 上, 然后就能正常工作.  
现在由于 Selenium Manager 负责在 PATH 上查找驱动程序, 
这种方法不再奏效, 用户需要使用 `Service` 类并[直接设置位置](https://www.selenium.dev/documentation/webdriver/drivers/service/#driver-location) .
让 Selenium Manager 在 PATH 上查找驱动程序而不是在每个绑定中管理该逻辑, 有诸多优势, 所以目前这是我们愿意接受的权衡. 

然而, 从 Selenium 4.13.0 版本开始, 
Selenium 绑定允许通过一个名为  `SE_MANAGER_PATH`  的环境变量来定位 Selenium Manager 二进制文件. 
如果设置了此变量, 绑定将使用其值作为本地文件系统中的 Selenium Manager 路径. 
此功能将允许用户提供自定义编译的 Selenium Manager, 
例如, 如果默认的二进制文件(针对 Windows、Linux 和 macOS 编译)与给定系统(例如 Linux 中的 ARM64)不兼容. 

### 浏览器依赖
在 Linux 系统中自动管理浏览器时, Selenium Manager 依赖于浏览器供应商(例如 Chrome、Firefox 和 Edge)发布的版本. 
这些版本在大多数情况下都是可移植的. 
然而, 在某些情况下可能需要现有的库. 
在 Linux 中, 尝试运行 Firefox 时可能会遇到此问题, 例如: 

```
libdbus-glib-1.so.2: cannot open shared object file: No such file or directory
Couldn't load XPCOM.
```

如果出现这种情况, 解决办法是安装相应的库, 例如, 可以按如下方式操作: 

```
sudo apt-get install libdbus-glib-1-2
```

在 Linux 系统中尝试执行 Chrome for Testing 时可能会出现类似的问题: 

```
error while loading shared libraries: libatk-1.0.so.0: cannot open shared object file: No such file or directory
```

在这种情况下, 要安装的库是以下这个: 

```
sudo apt-get install libatk-bridge2.0-0
```

### 使用环境变量来指定驱动程序路径
可以使用环境变量来指定驱动程序路径, 而无需使用 Selenium Manager.  
支持以下环境变量: 

* `SE_CHROMEDRIVER`
* `SE_EDGEDRIVER`
* `SE_GECKODRIVER`
* `SE_IEDRIVER`
* `SE_SAFARIDRIVER`

例如, 要指定 chromedriver 的路径, 
您可以将  `SE_CHROMEDRIVER`  环境变量设置为 chromedriver 可执行文件的路径. 
以下绑定允许您使用环境变量指定驱动程序路径: 

* Ruby
* Java
* Python

此功能从 Selenium Ruby 绑定的 4.25.0 版本以及 Python 绑定的 4.26.0 版本开始可用. 

## 构建自定义 Selenium Manager
若要构建适用于我们当前不支持的架构的自定义 Selenium Manager, 
您可以按照以下步骤操作: 

2. 安装 Rust 开发环境
3. 将 Selenium 克隆到您的本地机器上  `git clone https://github.com/SeleniumHQ/selenium.git --depth 1`
4. 进入您的下载目录  `cd selenium/rust`
5. 构建 Selenium `cargo build --release`
6. 设置以下环境变量以指定驱动程序路径 `SE_MANAGER_PATH=~/selenium/rust/target/release/selenium-manager`
7. 将您想要的驱动程序放在系统路径中的某个位置. 
8. Selenium 现在将使用内置的 Selenium Manager在 PATH 中定位手动下载的驱动程序. 

## 路线图
您可以在 [Selenium Manager project dashboard](https://github.com/orgs/SeleniumHQ/projects/5) 中追踪正在进行的工作. 
此外, 您还可以在每个 Selenium Manager 版本的[changelog file](https://github.com/SeleniumHQ/selenium/blob/trunk/rust/CHANGELOG.md) 中查看随版本发布的新增功能. 
