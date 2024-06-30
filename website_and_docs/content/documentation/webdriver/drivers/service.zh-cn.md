---
title: "驱动服务类"
linkTitle: "服务"
weight: 3
---

服务类用于管理驱动程序的启动和停止.
它们不能与远程 WebDriver 会话一起使用.

服务类允许您指定有关驱动程序的信息,
诸如位置和要使用的端口.
它们还允许您指定传递哪些参数到命令行.
大多数有用的参数都与日志记录有关.

## 默认服务实例

使用默认服务实例启动驱动程序:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/drivers/ServiceTest.java#L15-L16" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.11" >}}
{{< gh-codeblock path="examples/python/tests/drivers/test_service.py#L5-L6" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Drivers/ServiceTest.cs#L14-L15" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/drivers/service_spec.rb#L14-L15" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## 驱动程序位置

**注意:** 如果您使用的是 Selenium 4.6 或更高版本, 
则无需设置驱动程序位置.
如果您无法更新 Selenium 或有高阶用法需求, 
以下是指定驱动程序位置的方法:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/drivers/ServiceTest.java#L25-L26" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.11" >}}
{{< gh-codeblock path="examples/python/tests/drivers/test_service.py#L15" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-version version="4.9" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Drivers/ServiceTest.cs#L23" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="examples/ruby/spec/drivers/service_spec.rb#L22" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## 驱动程序端口

如果希望驱动程序在特定端口上运行, 
可以按如下方式指定:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/drivers/ServiceTest.java#L33" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.11" >}}
{{< gh-codeblock path="examples/python/tests/drivers/test_service.py#L23" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Drivers/ServiceTest.cs#L32" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="examples/ruby/spec/drivers/service_spec.rb#L29" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

<span id="setting-log-output"></span>
## 日志

日志记录功能因浏览器而异.
大多数浏览器都允许您指定日志的位置和级别.
请查看相应的浏览器页面:
* [Chrome]({{< ref "../browsers/chrome#service" >}})
* [Edge]({{< ref "../browsers/edge#service" >}})
* [Firefox]({{< ref "../browsers/firefox#service" >}})
* [Internet Explorer]({{< ref "../browsers/internet_explorer#service" >}})
* [Safari]({{< ref "../browsers/safari#service" >}})
