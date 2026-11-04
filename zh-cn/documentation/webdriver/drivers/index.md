# 驱动会话

URL: https://www.selenium.dev/zh-cn/documentation/webdriver/drivers/

---

启动和停止会话, 用于打开和关闭浏览器.

## 创建会话

创建会话对应于W3C的命令 [新建会话](https://w3c.github.io/webdriver/#new-session)

会话是通过初始化新的驱动类对象自动创建的.

每种语言都允许使用来自这些类 (或等效类) 之一的参数创建会话:

* [选项](https://www.selenium.dev/zh-cn/documentation/webdriver/drivers/options/index.md) 描述您想要的会话类型; 默认值为local，但是对于remote则是必须设置的
* 各种形式的 [HTTP Client configuration](https://www.selenium.dev/zh-cn/documentation/webdriver/drivers/http_client/index.md) (实现因语言而异)
* [监听器](https://www.selenium.dev/zh-cn/documentation/webdriver/support_features/listeners/index.md)

### 本地驱动

启动本地驱动的首要唯一参数
包括在本地计算机上有关启动所需驱动服务的信息.

* [服务](https://www.selenium.dev/zh-cn/documentation/webdriver/drivers/service/index.md) 对象仅适用于本地驱动，并提供有关浏览器驱动的信息

**Java**

```java
WebDriver driver = new ChromeDriver(chromeOptions);
```

**Python**

```python
driver = webdriver.Chrome(options=options)
```

**CSharp**

```csharp
driver = new ChromeDriver(options);
```

**Ruby**

```ruby
driver = Selenium::WebDriver.for :chrome, options: options
```

**JavaScript**

```javascript
driver = new Builder()
  .forBrowser(Browser.CHROME)
  .setChromeOptions(options)
  .setChromeService(service)
  .build();
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

### 远程驱动

用于启动远程驱动的首要唯一参数包括有关在何处执行代码的信息.
请浏览 [远程驱动章节](https://www.selenium.dev/zh-cn/documentation/webdriver/drivers/remote_webdriver/index.md)中的详细信息

## 退出会话

退出会话对应于W3C的命令 [删除会话](https://w3c.github.io/webdriver/#delete-session).

重要提示: `quit` 方法与 `close` 方法不同,
建议始终使用 `quit` 来结束会话

**Java**

```java
driver.quit();
```

**Python**

```python
driver.quit()
```

**CSharp**

```csharp
driver.Quit();
```

**Ruby**

```ruby
driver.quit
```

**JavaScript**

```javascript
await driver.quit();
```

**Kotlin**

```kotlin
driver.quit()
```

---

Section pages:

- [浏览器选项](https://www.selenium.dev/zh-cn/documentation/webdriver/drivers/options/index.md): 这些capabilities用于所有浏览器.
- [HTTP Client Configuration](https://www.selenium.dev/zh-cn/documentation/webdriver/drivers/http_client/index.md)
- [驱动服务类](https://www.selenium.dev/zh-cn/documentation/webdriver/drivers/service/index.md)
- [远程WebDriver](https://www.selenium.dev/zh-cn/documentation/webdriver/drivers/remote_webdriver/index.md)
