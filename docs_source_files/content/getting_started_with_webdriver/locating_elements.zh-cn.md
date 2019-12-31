---
title: "定位元素"
weight: 3
---

### 定位元素

使用 WebDriver 时要学习的最基本的技术之一是如何查找页面上的元素。
WebDriver 提供了许多内置的选择器类型，其中包括根据 id 属性查找元素:

{{< code-tab >}}
  {{< code-panel language="java" >}}
WebElement cheese = driver.findElement(By.id("cheese"));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
driver.find_element_by_id("cheese")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
IWebElement element = driver.FindElement(By.Id("cheese"));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
cheese = driver.find_element(id: 'cheese')
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const cheese = driver.findElement(By.id('cheese'));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val cheese: WebElement = driver.findElement(By.id("cheese"))
  {{< / code-panel >}}
{{< / code-tab >}}

如示例所示，在 WebDriver 中定位元素是在 `WebDriver` 实例对象上完成的。
 `findElement(By)` 方法返回另一个基本对象类型 `WebElement`。

* `WebDriver` 代表浏览器
* `WebElement` 表示特定的 DOM 节点（控件，例如链接或输入栏等）

一旦你已经找到一个元素的引用，你可以通过对该对象实例使用相同的调用来缩小搜索范围：

{{< code-tab >}}
  {{< code-panel language="java" >}}
WebElement cheese = driver.findElement(By.id("cheese"));
WebElement cheddar = cheese.findElement(By.id("cheddar"));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
cheese = driver.find_element_by_id("cheese")
cheddar = cheese.find_elements_by_id("cheddar")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
IWebElement cheese = driver.FindElement(By.Id("cheese"));
IWebElement cheddar = cheese.FindElement(By.Id("cheddar"));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
cheese = driver.find_element(id: 'cheese')
cheddar = cheese.find_element(id: 'cheddar')
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const cheese = driver.findElement(By.id('cheese'));
const cheddar = cheese.findElement(By.id('cheddar'));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val cheese = driver.findElement(By.id("cheese"))
val cheddar = cheese.findElement(By.id("cheddar"))
  {{< / code-panel >}}
{{< / code-tab >}}

你可以这样做是因为， _WebDriver_ 和 _WebElement_ 类型都实现了 [_搜索上下文_](//seleniumhq.github.io/selenium/docs/api/java/org/openqa/selenium/SearchContext.html) 接口。在 WebDriver 中，这称为 _基于角色的接口_。基于角色的接口允许你确定特定的驱动程序实现是否支持给定的功能。这些接口定义得很清楚，并且尽量只承担单一的功能。你可以阅读更多关于 WebDriver 的设计，以及在 WebDriver 中有哪些角色被支持，在[其他被命名的部分](#)。
<!-- TODO: A new section needs to be created for the above.-->

因此，上面使用的 _By_ 接口也支持许多附加的定位器策略。嵌套查找可能不是最有效的定位 cheese 的策略，因为它需要向浏览器发出两个单独的命令：首先在 DOM 中搜索 id 为“cheese”的元素，然后在较小范围的上下文中搜索“cheddar”。

为了稍微提高性能，我们应该尝试使用一个更具体的定位器：WebDriver 支持通过 CSS 定位器查找元素，我们可以将之前的两个定位器合并到一个搜索里面:

{{< code-tab >}}
  {{< code-panel language="java" >}}
driver.findElement(By.cssSelector("#cheese #cheddar"));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
cheddar = driver.find_element_by_css_selector("#cheese #cheddar")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
driver.FindElement(By.CssSelector("#cheese #cheddar"));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
driver.find_element(css: '#cheese #cheddar')
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const cheddar = driver.findElement(By.css('#cheese #cheddar'));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
driver.findElement(By.cssSelector("#cheese #cheddar"))
  {{< / code-panel >}}
{{< / code-tab >}}

### 定位多个元素

我们正在处理的文本中可能会有一个我们最喜欢的奶酪的订单列表：

```html
<ol id=cheese>
 <li id=cheddar>…
 <li id=brie>…
 <li id=rochefort>…
 <li id=camembert>…
</ul>
```

因为有更多的奶酪无疑是更好的，但是单独检索每一个项目是很麻烦的，检索奶酪的一个更好的方式是使用复数版本 `findElements(By)` 。此方法返回 web 元素的集合。如果只找到一个元素，它仍然返回(一个元素的)集合。如果没有元素被定位器匹配到，它将返回一个空列表。

{{< code-tab >}}
  {{< code-panel language="java" >}}
List<WebElement> muchoCheese = driver.findElements(By.cssSelector("#cheese li"));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
mucho_cheese = driver.find_elements_by_css_selector("#cheese li")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
IReadOnlyList<IWebElement> muchoCheese = driver.FindElements(By.CssSelector(“#cheese li”));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
mucho_cheese = driver.find_elements(css: "#cheese li")
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const muchoCheese = driver.findElements(By.css('#cheese li'));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val muchoCheese: List<WebElement>  = driver.findElements(By.cssSelector("#cheese li"))
  {{< / code-panel >}}
{{< / code-tab >}}

### 元素选择策略

在 WebDriver 中有 8 种不同的内置元素定位策略：

| 定位器 | 描述 |
| -------- | ---------- |
| class 名称 | 定位类的名称中包含搜索值的元素（不允许使用复合类名） |
| css 选择器 | 定位与 CSS 选择器匹配的元素 |
| id | 定位 id 属性与搜索值匹配的元素 |
| name | 定位 name 属性与搜索值匹配的元素 |
| 链接文本| 定位其可视文本与搜索值匹配的锚元素 |
| 部分链接文本 | 定位其可视文本与搜索值匹配的锚元素 |
| 标签名称 | 定位标签名称与搜索值匹配的元素 |
| xpath | 定位与 XPath 表达式匹配的元素 |

### 使用选择器的提示

一般来说，如果 HTML 的 id 是可用的、唯一的且是可预测的，那么它就是在页面上定位元素的首选方法。它们的工作速度非常快，可以避免复杂的 DOM 遍历带来的大量处理。

如果没有唯一的 id，那么最好使用写得好的 CSS 选择器来查找元素。XPath 和 CSS 选择器一样好用，但是它语法很复杂，并且经常很难调试。尽管 XPath 选择器非常灵活，但是他们通常未经过浏览器厂商的性能测试，并且运行速度很慢。

基于链接文本和部分链接文本的选择策略有其缺点，即只能对链接元素起作用。此外，它们在 WebDriver 内部调用 XPath 选择器。

标签名可能是一种危险的定位元素的方法。页面上经常出现同一标签的多个元素。这在调用 _findElements(By)_ 方法返回元素集合的时候非常有用。

建议您尽可能保持定位器的紧凑性和可读性。使用 WebDriver 遍历 DOM 结构是一项性能花销很大的操作，搜索范围越小越好。
