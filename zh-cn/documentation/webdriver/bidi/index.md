# WebDriver BiDi (双向功能)

> WebDriver BiDi 是由 Selenium 项目与各浏览器厂商共同制定的 W3C 标准双向协议, 用于浏览器自动化. 它在 WebDriver 之上增加了 WebSocket 连接, 使脚本能够实时接收并响应浏览器事件, 包括网络请求, 控制台消息和 JavaScript 错误. 它是取代 Chrome DevTools Protocol 的跨浏览器标准.

URL: https://www.selenium.dev/zh-cn/documentation/webdriver/bidi/

---

双向是指通信同时在两个方向上进行. 
传统的 WebDriver 模型涉及严格的请求/响应命令, 
在任何时候都只允许单向通信. 
在大多数情况下, 这正是您想要的；它能确保浏览器以正确的顺序执行预期的操作, 
但异步交互也有许多有趣的地方. 

目前, [Chrome DevTools Protocol] (CDP) 可以有限地提供这种功能, 
但为了解决它的一些缺点, 
Selenium 团队与主要浏览器供应商一起创建了新的 [WebDriver BiDi 协议](https://w3c.github.io/webdriver-bidi/).
该规范旨在创建一个稳定的跨浏览器 API, 
利用双向通信增强浏览器自动化和测试功能、
包括通过 WebSockets 从用户代理到控制软件的流式事件. 
用户将能在 Selenium 会话过程中监听、记录或操作事件. 

### 在Selenium中启用 BiDi

为了使用 WebDriver BiDi, 
在浏览器选项中设置该功能将启用所需的功能:

**Java**

```java
options.setCapability("webSocketUrl", true);
```

**Python**

```python
options.enable_bidi = True
```

**CSharp**

```csharp
UseWebSocketUrl = true,
```

**Ruby**

```ruby
options.web_socket_url = true
```

**JavaScript**

```javascript
Options().enableBidi();
```

**Kotlin**

```kotlin
options.setCapability("webSocketUrl", true);
```

这将启用用于双向通信的 WebSocket 连接、
释放 WebDriver BiDi 协议的全部潜能. 

请注意, Selenium 正在将其整个实现从 WebDriver Classic 
升级到 WebDriver BiDi (同时尽可能保持向后兼容性) , 
但本部分文档的重点是双向通信所允许的新功能. 

终端用户可以在代码中访问低级 BiDi 域, 
但我们的目标是提供高级应用程序接口, 
这些应用程序接口是真实世界用例的直接方法. 
因此, 我们将不对底层组件进行记录, 本节将只关注我们鼓励使用者利用的用户友好功能. 

如果您希望看到其他功能, 请提出
[功能请求](https://github.com/SeleniumHQ/selenium/issues/new?assignees=&labels=&template=feature.md).

---

Section pages:

- [WebDriver BiDi Network Features](https://www.selenium.dev/zh-cn/documentation/webdriver/bidi/network/index.md): These features are related to networking, and are made available via a "network" namespace.
- [WebDriver BiDi Script Features](https://www.selenium.dev/zh-cn/documentation/webdriver/bidi/script/index.md): These features are related to scripts, and are made available via a "script" namespace.
- [WebDriver BiDi 日志功能](https://www.selenium.dev/zh-cn/documentation/webdriver/bidi/logging/index.md): 这些功能与日志记录有关。 由于"logging"可以指代许多不同的事物, 因此这些方法通过"script"命名空间提供.
- [Chrome DevTools 协议](https://www.selenium.dev/zh-cn/documentation/webdriver/bidi/cdp/index.md): 使用 Selenium 操作 Chrome DevTools 协议的示例。 CDP 的支持是临时的，直到 WebDriver BiDi 实现为止。
- [BiDirectional API (W3C compliant)](https://www.selenium.dev/zh-cn/documentation/webdriver/bidi/w3c/index.md)
