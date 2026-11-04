# Print Page

URL: https://www.selenium.dev/ja/documentation/webdriver/interactions/print_page/

---

Printing a webpage is a common task, whether for sharing information or maintaining archives. 
Selenium simplifies this process through its PrintOptions, PrintsPage, and browsingContext 
classes, which provide a flexible and intuitive interface for automating the printing of web pages. 
These classes enable you to configure printing preferences, such as page layout, margins, and scaling, 
ensuring that the output meets your specific requirements.

## Configuring

### Orientation
Using the `getOrientation()` and `setOrientation()` methods, you can get/set the page orientation --- either `PORTRAIT` or `LANDSCAPE`.

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

### Range
Using the `getPageRanges()` and `setPageRanges()` methods, you can get/set the range of pages to print --- e.g. "2-4".

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

### Size
Using the `getPageSize()` and `setPageSize()` methods, you can get/set the paper size to print --- e.g. "A0", "A6", "Legal", "Tabloid", etc.

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

### Margins
Using the `getPageMargin()` and `setPageMargin()` methods, you can set the margin sizes of the page you wish to print --- i.e. top, bottom, left, and right margins.

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

### Scale
Using `getScale()` and `setScale()` methods, you can get/set the scale of the page you wish to print --- e.g. 1.0 is 100% or default, 0.25 is 25%, etc.

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

### Background
Using `getBackground()` and `setBackground()` methods, you can get/set whether background colors and images appear --- boolean `true` or `false`.

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

### ShrinkToFit
Using `getShrinkToFit()` and `setShrinkToFit()` methods, you can get/set whether the page will shrink-to-fit content on the page --- boolean `true` or `false`.

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

## Printing

Once you've configured your PrintOptions, you're ready to print the page. To do this, 
you can invoke the print function, which generates a PDF representation of the web page. 
The resulting PDF can be saved to your local storage for further use or distribution. 
Using `PrintsPage()`, the print command will return the PDF data in base64-encoded format, which can be decoded 
and written to a file in your desired location, and using `BrowsingContext()` will return a String. 

There may currently be multiple implementations depending on your language of choice. For example, with Java you
have the ability to print using either `BrowsingContext()` or `PrintsPage()`. Both take `PrintOptions()` objects as a
parameter.

Note: `BrowsingContext()` is part of Selenium's BiDi implementation. To enable BiDi see [Enabling Bidi](https://www.selenium.dev/ja/documentation/webdriver/bidi/index.md) 

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
