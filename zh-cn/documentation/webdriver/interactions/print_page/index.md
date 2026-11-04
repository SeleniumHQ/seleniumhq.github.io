# 打印页面

URL: https://www.selenium.dev/zh-cn/documentation/webdriver/interactions/print_page/

---

无论是共享信息还是维护档案，打印网页都是一项常见任务。
Selenium 通过其 PrintOptions、PrintsPage 和 browsingContext 类简化了这一过程，这些类为网页自动打印提供了灵活直观的接口。
这些类使得用户可以配置打印首选项，如页面布局、页边距和缩放比例，以确保输出满足特定要求。

## 配置

### 方向
通过 `getOrientation()` 和 `setOrientation()` 方法，可以获取/设置页面方向（`PORTRAIT` 或 `LANDSCAPE`）。

**Java**

```java
driver.get("https://www.selenium.dev/");
PrintOptions printOptions = new PrintOptions();
printOptions.setOrientation(PrintOptions.Orientation.LANDSCAPE);
PrintOptions.Orientation current_orientation = printOptions.getOrientation();
```

**CSharp**

```csharp
public void TestOrientation()
{
    IWebDriver driver = new ChromeDriver();
    driver.Navigate().GoToUrl("https://selenium.dev");
    PrintOptions printOptions  = new PrintOptions();
    printOptions.Orientation = PrintOrientation.Landscape;
    PrintOrientation currentOrientation = printOptions.Orientation;
    driver.Quit();
```

**Ruby**

_Not available in this language._

**Python**

```python
driver.get("https://www.selenium.dev/")
print_options = PrintOptions()
print_options.orientation = "landscape" ## landscape or portrait
```

**JavaScript**

_Not available in this language._

**Kotlin**

_Not available in this language._

### 范围
通过 `getPageRanges()` 和 `setPageRanges()` 方法，可以获取设置要打印页面的范围（如 "2-4"）。

**Java**

```java
driver.get("https://www.selenium.dev/");
PrintOptions printOptions = new PrintOptions();
printOptions.setPageRanges("1-2");
String[] current_range = printOptions.getPageRanges();
```

**CSharp**

```csharp
[TestMethod]
public void TestRange()
{
    IWebDriver driver = new ChromeDriver();
    driver.Navigate().GoToUrl("https://selenium.dev");
    PrintOptions printOptions  = new PrintOptions();
    printOptions.AddPageRangeToPrint("1-3"); // add range of pages
    printOptions.AddPageToPrint(5); // add individual page
```

**Ruby**

_Not available in this language._

**Python**

```python
driver.get("https://www.selenium.dev/")
print_options = PrintOptions()
print_options.page_ranges = ["1, 2, 3"] ## ["1", "2", "3"] or ["1-3"]
```

**JavaScript**

_Not available in this language._

**Kotlin**

_Not available in this language._

### 尺寸
通过 `getPageSize()` 和 `setPageSize()` 方法，可以获取/设置要打印页面的纸张尺寸（如"A0"、"A6"、"Legal"、"Tabloid" 等）。

**Java**

```java
driver.get("https://www.selenium.dev/");
PrintOptions printOptions = new PrintOptions();
printOptions.setPageSize(PageSize.ISO_A4);
double currentHeight = printOptions.getPageSize().getHeight(); // use getWidth() to retrieve width
```

**CSharp**

```csharp

[TestMethod]
public void TestSize()
{
    IWebDriver driver = new ChromeDriver();
    driver.Navigate().GoToUrl("https://www.selenium.dev/");
    PrintOptions printOptions = new PrintOptions();
```

**Ruby**

_Not available in this language._

**Python**

```python
driver.get("https://www.selenium.dev/")
print_options = PrintOptions()
print_options.page_height = 27.94  # Use page_width to assign width
```

**JavaScript**

_Not available in this language._

**Kotlin**

_Not available in this language._

### 边距
通过 `getPageMargin()` 和 `setPageMargin()` 方法，可以获取/设置要打印页面的边距大小（也就是上、下、左右边距）。

**Java**

```java
driver.get("https://www.selenium.dev/");
PrintOptions printOptions = new PrintOptions();
PageMargin margins = new PageMargin(1.0,1.0,1.0,1.0);
printOptions.setPageMargin(margins);
double topMargin = margins.getTop();
double bottomMargin = margins.getBottom();
double leftMargin = margins.getLeft();
double rightMargin = margins.getRight();
```

**CSharp**

```csharp
    driver.Quit();
}

[TestMethod]
public void TestMargins()
{
    IWebDriver driver = new ChromeDriver();
```

**Ruby**

_Not available in this language._

**Python**

```python
driver.get("https://www.selenium.dev/")
print_options = PrintOptions()
print_options.margin_top = 10
print_options.margin_bottom = 10
print_options.margin_left = 10
print_options.margin_right = 10
```

**JavaScript**

_Not available in this language._

**Kotlin**

_Not available in this language._

### 缩放
通过 `getScale()` 和 `setScale()` 方法，可以获取/设置要打印页面的缩放尺寸（如 1.0 为 100% 或默认缩放，0.25 为 25% 等）。

**Java**

```java
{
    driver.get("https://www.selenium.dev/");
    PrintOptions printOptions = new PrintOptions();
    printOptions.setScale(.50);
    double current_scale = printOptions.getScale();
```

**CSharp**

```csharp
    driver.Quit();
}

[TestMethod]
public void TestScale()
{
    IWebDriver driver = new ChromeDriver();
```

**Ruby**

_Not available in this language._

**Python**

```python
driver.get("https://www.selenium.dev/")
print_options = PrintOptions()
print_options.scale = 0.5 ## 0.1 to 2.0
current_scale = print_options.scale
```

**JavaScript**

_Not available in this language._

**Kotlin**

_Not available in this language._

### 背景
通过 `getBackground()` 和 `setBackground()` 方法，可以获取/设置背景色和图片出现，其为布尔值 `true` 或 `false`。

**Java**

```java
driver.get("https://www.selenium.dev/");
PrintOptions printOptions = new PrintOptions();
printOptions.setBackground(true);
boolean current_background = printOptions.getBackground();
```

**CSharp**

```csharp
}

[TestMethod]
public void TestBackgrounds()
{
    IWebDriver driver = new ChromeDriver();
    driver.Navigate().GoToUrl("https://www.selenium.dev/");
    PrintOptions printOptions = new PrintOptions();
```

**Ruby**

_Not available in this language._

**Python**

```python
driver.get("https://www.selenium.dev/")
print_options = PrintOptions()
print_options.background = True ## True or False
```

**JavaScript**

_Not available in this language._

**Kotlin**

_Not available in this language._

### 缩放至合适大小
通过 `getShrinkToFit()` 和 `setShrinkToFit()` 方法，可以获取/设置页面是否会根据页面内容缩小，其为布尔值 `true` 或 `false`。

**Java**

```java
driver.get("https://www.selenium.dev/");
PrintOptions printOptions = new PrintOptions();
printOptions.setShrinkToFit(true);
boolean current_shrink_to_fit = printOptions.getShrinkToFit();
```

**CSharp**

```csharp
    printOptions.ScaleFactor = 0.5;
    double currentScale = printOptions.ScaleFactor;
    driver.Quit();
}

[TestMethod]
public void TestShrinkToFit()
{
```

**Ruby**

_Not available in this language._

**Python**

```python
driver.get("https://www.selenium.dev/")
print_options = PrintOptions()
print_options.shrink_to_fit = True ## True or False
```

**JavaScript**

_Not available in this language._

**Kotlin**

_Not available in this language._

## 打印

配置好打印选项后，就可以打印页面了。为此，您可以调用打印功能，生成网页的 PDF 表示形式。
生成的 PDF 文件可以保存到本地存储器中，以便进一步使用或分发。
使用 `PrintsPage()` 时，打印命令将以 base64 编码格式返回 PDF
数据，该格式可以解码并写入所需位置的文件，而使用 `BrowsingContext()` 时将返回字符串。

目前可能有多种实现方式，这取决于您所选择的语言。例如，Java 可以使用 `BrowsingContext()`
或 `PrintsPage()` 进行打印。两者都将 `PrintOptions()` 对象作为一个参数。

注意：`BrowsingContext()` 是 Selenium BiDi 实现的一部分。为启用 BiDi，请参见[启用 Bidi](https://www.selenium.dev/zh-cn/documentation/webdriver/bidi/index.md) 

**Java**

**PrintsPage()**

```java
    PrintsPage printer = (PrintsPage) driver;
    PrintOptions printOptions = new PrintOptions();
    Pdf printedPage = printer.print(printOptions);
    Assertions.assertNotNull(printedPage);
}
```

**BrowsingContext()**

```java
    driver.get("https://www.selenium.dev/selenium/web/formPage.html");
    PrintOptions printOptions = new PrintOptions();
    String printPage = browsingContext.print(printOptions);
    Assertions.assertTrue(printPage.length() > 0);
}
```

**CSharp**

```csharp
    PrintOptions printOptions = new PrintOptions();
    printOptions.ShrinkToFit = true;
    bool currentShrinkToFit = printOptions.ShrinkToFit;
    driver.Quit();
}

[TestMethod]
public void PrintWithPrintsPageTest()
```

**Ruby**

_Not available in this language._

**Python**

**print_page()**

```python
driver.get("https://www.selenium.dev/")
print_options = PrintOptions()
pdf = driver.print_page(print_options)
```

**JavaScript**

_Not available in this language._

**Kotlin**

_Not available in this language._
