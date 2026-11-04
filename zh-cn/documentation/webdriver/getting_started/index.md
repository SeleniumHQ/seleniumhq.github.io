# 入门指南

> 如果你是Selenium的新手, 我们有一些资源帮助你快速入门.

URL: https://www.selenium.dev/zh-cn/documentation/webdriver/getting_started/

---

Selenium 通过使用 _WebDriver_ 支持市场上所有主流浏览器的自动化。
WebDriver 是一个 API 和协议，它定义了一个语言中立的接口，用于控制 web 浏览器的行为。
每个浏览器都有一个特定的 WebDriver 实现，称为驱动程序。
驱动程序是负责委派给浏览器的组件，并处理与 Selenium 和浏览器之间的通信。

这种分离是有意识地努力让浏览器供应商为其浏览器的实现负责的一部分。
Selenium 在可能的情况下使用这些第三方驱动程序，
但是在这些驱动程序不存在的情况下，它也提供了由项目自己维护的驱动程序。

Selenium 框架通过一个面向用户的界面将所有这些部分连接在一起，
该界面允许透明地使用不同的浏览器后端，
从而实现跨浏览器和跨平台自动化。

Selenium的设置与其他商业工具有很大不同.
在开始编写 Selenium 代码之前, 
您必须安装所选语言的相关类库,
目标浏览器的驱动程序.

***请点击以下链接，开始使用 Selenium WebDriver.***

如果您希望从低代码/录制和播放工具开始，请查看
[Selenium IDE](https://selenium.dev/selenium-ide)

开始工作后，如果想扩展您的测试，请查看 
[Selenium Grid](https://www.selenium.dev/zh-cn/documentation/grid/index.md).

---

Section pages:

- [安装Selenium类库](https://www.selenium.dev/zh-cn/documentation/webdriver/getting_started/install_library/index.md): 配置自动化的浏览器.
- [编写第一个Selenium脚本](https://www.selenium.dev/zh-cn/documentation/webdriver/getting_started/first_script/index.md): 逐步构建一个Selenium脚本的说明
- [组织和执行Selenium代码](https://www.selenium.dev/zh-cn/documentation/webdriver/getting_started/using_selenium/index.md): 使用IDE和Test Runner库组织Selenium的执行
