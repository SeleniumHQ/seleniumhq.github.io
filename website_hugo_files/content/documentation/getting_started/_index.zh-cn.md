---
title: "入门指南"
linkTitle: "入门指南"
weight: 2
description: >
  If you are new to Selenium, we have a few resources that can help you get up to speed right away.
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to Chinese. Do you speak Chinese? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

Selenium 设置与其他商业工具的设置完全不同。
要在自动化项目中使用 Selenium，您需要为您选择的语言安装语言绑定库。
此外，对于要自动运行并运行测试的浏览器，您将需要 WebDriver 二进制文件。


Installing Selenium can be divided in three steps:

1. [Installing the Selenium library]({{< ref "/installing_selenium_libraries.md" >}}) for your desired programming language
2. [Set up the browser driver]({{< ref "/installing_browser_drivers.md" >}}) to automate your browser (e.g. GeckoDriver for Firefox)
3. (Optional) Set up and configure [Selenium Grid]({{< ref "/grid.md" >}}) if you want to scale up your tests

If you wish to start with a low-code/record and playback tool, please check 
[Selenium IDE](https://selenium.dev/selenium-ide)

After completing the setup, you can run the code snippet shown at the 
[starting page](/zh-cn/documentation) in our docs. Then head to the 
[WebDriver]({{< ref "/webdriver.md" >}}) section to learn more about
browser automation with Selenium.