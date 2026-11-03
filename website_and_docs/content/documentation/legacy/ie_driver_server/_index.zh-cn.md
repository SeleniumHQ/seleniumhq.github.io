---
title: "IE驱动服务器"
linkTitle: "IE驱动服务器"
weight: 8
description: >
    Internet Explorer驱动是一种实现WebDriver规范的单机服务器.
---
This documentation previously located [on the wiki](https://github.com/SeleniumHQ/selenium/wiki/InternetExplorerDriver-Internals)

`InternetExplorerDriver` 是一个独立的服务器, 
它实现WebDriver的wire协议. 
此驱动程序已在Windows 10的IE 11上进行了测试. 
它可能适用于旧版本的IE和Windows, 
但未受支持. 

驱动程序支持运行32位和64位版本的浏览器. 
启动浏览器的"位数", 
取决于启动的IEDriverServer.exe的版本. 
若是32位的`IEDriverServer.exe`已启动, 
将启动32位版本的IE. 
同样的, 如果启动的64位版本的IEDriverServer.exe, 
将启动64位版本的IE.

## 安装

尽管需要一些配置, 
但是在使用`InternetExplorerDriver`之前, 
您并不需要运行安装程序. 
独立服务器可执行文件必须从[下载](https://www.selenium.dev/downloads/)
页面下载并放置在您的[PATH](http://en.wikipedia.org/wiki/PATH_(variable)) 中.

## Pros

* 在真实浏览器中运行并支持JavaScript

## Cons

* 显然InternetExplorerDriver只能在Windows上运行!
* 相当慢 (尽管仍然很快 :) 

## 命令行开关

作为一个独立的可执行文件, 
IE驱动程序的行为可以通过各种命令行参数进行修改. 
要设置这些命令行参数的值, 
应参考所使用语言绑定的文档. 
下表描述了支持的命令行开关. 
支持所有 -`<开关>`, --`<开关>`
和 /`<开关>` .

| 开关                    | 释义                                                                                                                                             |
|:----------------------|:-----------------------------------------------------------------------------------------------------------------------------------------------|
| --port=`<端口号>`        | 指定IE驱动程序的HTTP服务器将在其上侦听来自语言绑定的命令的端口. 默认值为5555.                                                                                                    |
| --host=`<主机IP地址>`     | 指定主机适配器的IP地址, IE驱动程序的HTTP服务器将在其上侦听来自语言绑定的命令. 默认值为127.0.0.1.                                                                                       |
| --log-level=`<日志级别>`  | 指定日志消息的输出级别. 有效值包括 FATAL, ERROR, WARN, INFO, DEBUG, 以及 TRACE. 默认为FATAL.                                                                         |
| --log-file=`<日志文件>`   | 指定日志文件的完整路径和文件名. 默认为标准输出.                                                                      |
| --extract-path=`<路径>` | 指定用于提取服务器使用的支持文件的目录的完整路径. 如果未指定, 则默认为临时目录.  |
| --silent              | 在服务器启动时抑制诊断输出.                                                                                       |

## 重要的系统属性

以下系统属性用于 `InternetExplorerDriver`
(在Java代码中使用 `System.getProperty()` 
读取并使用`System.setProperty()` 进行设置, 或者
在命令行标识 "`-DpropertyName=value`") :


| **属性**                            | **含义**                                                                                                                                         |
|:----------------------------------|:-----------------------------------------------------------------------------------------------------------------------------------------------|
| `webdriver.ie.driver`             | IE驱动程序二进制文件的位置.                                                                                                          |
| `webdriver.ie.driver.host`        | 指定IE驱动程序将在其上侦听的主机适配器的IP地址.                                                               |
| `webdriver.ie.driver.loglevel`    | 指定日志消息的输出级别. 有效值包括 FATAL, ERROR, WARN, INFO, DEBUG, 以及 TRACE. 默认为FATAL.      |
| `webdriver.ie.driver.logfile`     | 指定日志文件的完整路径和文件名.                                                                                         |
| `webdriver.ie.driver.silent`      | 在IE驱动程序启动时抑制诊断输出.                                                                                     |
| `webdriver.ie.driver.extractpath` | 指定用于提取服务器使用的支持文件的目录的完整路径. 如果未指定, 则默认为临时目录.   |

## 必要的配置

* `IEDriverServer`可执行文件必须[已下载](https://www.selenium.dev/downloads/) 并放置在您的 [PATH](http://en.wikipedia.org/wiki/PATH_(variable)) 中. 
* 在Windows Vista, Windows 7或Windows 10上的IE 7或更高版本上, 必须将每个区域的保护模式设置设置为相同的值. 该值可以打开或关闭, 只要每个区域的值相同. 要设置受保护模式设置, 请从“工具”菜单中选择“Internet选项…”, 然后单击“安全”选项卡. 对于每个区域, 标签底部将有一个复选框, 标记为“启用保护模式”. 
* 此外, IE 10及更高版本必须禁用“增强保护模式”. 此选项位于“Internet选项”对话框的“高级”选项卡中. 
* 浏览器缩放级别必须设置为100%, 以便将本机鼠标事件设置为正确的坐标. 
* 对于Windows 10, 您还需要在显示设置中将“更改文本、应用程序和其他项目的大小”设置为100%. 
* _仅_ 对于IE 11, 您需要在目标计算机上设置一个注册表项, 以便驱动程序能够保持与它创建的Internet Explorer实例的连接. 对于32位Windows安装, 您必须在注册表编辑器中检查的项是`HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Internet Explorer\Main\FeatureControl\FEATURE_BFCACHE`. 对于64位Windows安装, 键为 `HKEY_LOCAL_MACHINE\SOFTWARE\Wow6432Node\Microsoft\Internet Explorer\Main\FeatureControl\FEATURE_BFCACHE`. 请注意,  `FEATURE_BFCACHE` 子项可能存在, 也可能不存在, 如果不存在, 则应创建该子项. **要点:** 在此键内, 创建一个名为`iexplore.exe`值为0的DWORD.

## 原生事件以及Internet Explorer

由于 `InternetExplorerDriver` 仅适用于Windows, 
因此它尝试使用所谓的“原生”或操作系统级事件
在浏览器中执行鼠标和键盘操作. 
针对同样的操作, 
与使用JavaScript模拟相比有明显的差异. 
使用原生事件的优点是它不依赖于JavaScript沙箱, 
并且可以确保在浏览器中正确传播JavaScript事件. 
但是, 当IE浏览器窗口失焦, 
以及当尝试将鼠标悬停在元素上时, 
当前存在一些鼠标事件问题.

### 浏览器焦点

难点在于, 如果窗口没有焦点, 
IE本身似乎没有完全尊重我们在IE浏览器窗口
(`WM\_MOUSEDOWN` 和 `WM\_MOUSEUP`) 上发送的Windows消息. 
具体来说, 被单击的元素将在其周围接收一个焦点窗口, 
但该元素不会处理该单击. 
可以说, 我们根本不应该发送信息；
相反, 我们应该使用 `SendInput()` API, 
但该API明确要求窗口具有焦点. 
WebDriver项目有两个相互冲突的目标.

首先, 我们争取尽可能地模仿用户. 
这意味着使用原生事件, 
而不是使用JavaScript模拟事件.

其次, 我们不希望浏览器窗口的焦点被自动化. 
这意味着仅将浏览器窗口强制到前台是次优的. .

另一个考虑因素是
多个IE实例在多个WebDriver实例下运行的可能性, 
这意味着任何此类“将窗口置于前台”解决方案
都必须包装某种同步结构 (互斥体?)到
IE驱动程序的C++代码中. 
即使如此, 若用户将另一个窗口带到前台的操作, 
介于驱动程序将IE带到前台和执行原生事件之间, 
则此代码仍将受制于竞争条件.

围绕驱动程序的需求, 
以及如何对这两个相互冲突的目标进行优先排序的讨论正在进行中. 
当前流行的观点是前者优先于后者, 
并在使用IE驱动程序时, 
将您的机器标记为无法执行其他任务. 
然而, 这一决定远未最终确定, 
实现这一决定的代码可能相当复杂.

### 悬停在元素上

当您尝试将鼠标悬停在元素上, 
并且物理鼠标光标位于IE浏览器窗口的边界内时, 
鼠标悬停将不起作用. 
更具体地说, 悬停将在几分之一秒内工作, 
然后元素将恢复到其以前的状态. 
出现这种情况的普遍理论是, 
IE在其事件循环期间正在进行某种类型的命中测试, 
这导致它在物理光标位于窗口边界内时响应物理鼠标位置. 
WebDriver开发团队仍未找到解决IE这种行为的方法.

### 点击元素`选项`或提交表单以及`弹窗`

IE驱动程序在两个地方不使用原生事件与元素交互. 
这是在 `<select>` 元素中单击 `<option>` 元素. 
在正常情况下, IE驱动程序根据元素的位置和大小计算单击位置, 
通常由JavaScript  `getBoundingClientRect()` 方法返回. 
但是, 对于 `<option>` 元素, 
`getBoundingClientRect()` 返回一个位置为零、大小为零的矩形. 
IE驱动程序通过使用 `click()` 来处理这一场景, 
它本质上设置了元素 `.selected` 的属性, 
并在JavaScript中模拟 `onChange` 事件. 
但是, 这意味着如果 `<select>` 元素的 `onChange` 事件
包含调用 `alert()`, `confirm()` 或 `prompt()`的JavaScript代码, 
则调用WebElement的 `click()` 方法将挂起, 
直到手动取消模式对话框. 
对于此行为, 仅使用WebDriver代码没有已知的解决方法. 

类似地, 在某些情况下, 
通过WebElement的`submit()`方法提交HTML表单
可能会产生相同的效果. 
如果驱动程序调用表单上的JavaScript`submit()`函数, 
并且有一个onSubmit事件处理程序调用
JavaScript `alert()`, `confirm()` 或 `prompt()`函数, 
则可能发生这种情况. 

该限制被记录为3508号问题 (谷歌代码).

## `InternetExplorerDriver`多实例

随着 `IEDriverServer.exe`的创建, 
应该可以同时创建和使用`InternetExplorerDriver`的多个实例. 
但是, 此功能基本上未经测试, 
并且可能存在cookie、窗口焦点等问题. 
如果尝试使用IE驱动程序的多个实例, 
并遇到这些问题, 
请考虑使用`RemoteWebDriver` 和虚拟机.

对于InternetExplorer的多个实例之间
共享的Cookie (和其他会话项) 问题, 
有两种解决方案.

首先是在私有模式下启动InternetExplorer. 
在此之后, InternetExplorer将使用干净的会话数据启动, 
并且在退出时不会保存更改的会话数据. 
为此, 您需要向驱动程序传递2个特定功能：
具有 `true` 值的 `ie.forceCreateProcessApi` 和具有 `-private` 值的 `ie.browserCommandLineSwitches` . 
请注意, 它仅适用于InternetExplorer 8及更高版本, 
Windows注册表`HKLM_CURRENT_USER\\Software\\Microsoft\\Internet Explorer\\Main` 
路径应包含值为 `0` 的`TabProcGrowth` 键.

第二个是在InternetExplorer启动期间清理会话. 
为此, 您需要将具有 `true` 值的
特定 `ie.ensureCleanSession` 功能传递给驱动程序. 
这将清除InternetExplorer所有运行实例的缓存, 
包括手动启动的实例.

## 远程远行 `IEDriverServer.exe` 

由 `IEDriverServer.exe` 启动的HTTP服务器, 
将访问控制列表设置为仅接受来自本地计算机的连接, 
并禁止来自远程计算机的传入连接. 
目前, 如果不修改`IEDriverServer.exe`的源代码, 
这一点是无法更改的. 
要在远程计算机上运行Internet Explorer驱动程序, 
请将Java独立远程服务器
与语言绑定的等效`RemoteWebDriver`结合使用.

## 按照Windows服务运行 `IEDriverServer.exe` 

明确不支持尝试使用IEDriverServer.exe
作为Windows服务应用程序的一部分. 
服务进程及其衍生的流程的需求
与在常规用户上下文中执行的流程有很大不同. 
`IEDriverServer.exe` 在该环境中未经测试, 
并且包括明确地禁止在服务进程中使用的Windows API调用. 
虽然可以让IE驱动程序在服务进程下运行, 
但在该环境中遇到问题的用户需要寻求自己的解决方案.
