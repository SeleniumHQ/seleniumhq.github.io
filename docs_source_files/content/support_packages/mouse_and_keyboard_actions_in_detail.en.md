---
title: "Mouse actions in detail"
weight: 4
---

Mouse Actions In Detail
Mouse represents a mouse event. Mouse actions are performed 
by using low-level interface which allows us to 
provide virtualized device input action to the web browser.

## clickAndHold

It will move to the element and clicks (without releasing) in the middle of the given element.

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class clickAndHold {
  public static void main(String[] args) {
    WebDriver driver = new ChromeDriver();
    try {
      // Navigate to Url
      driver.get("https://google.com");

      // Store 'google search' button web element
      WebElement searchBtn = driver.findElement(By.linkText("Sign in"));
      Actions actionProvider = new Actions(driver);
      // Perform click-and-hold action on the element
      actionProvider.clickAndHold(searchBtn).build().perform();
    } finally {
      driver.quit();
    }
  }
}
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Please raise a PR
  {{< / code-panel >}}
{{< / code-tab >}}

## contextClick
This method firstly performs a mouse-move to the location of the element and performs the context-click (right click) on the given element.

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class contextClick {
  public static void main(String[] args) {
    WebDriver driver = new ChromeDriver();
    try {
      // Navigate to Url
      driver.get("https://google.com");

      // Store 'google search' button web element
      WebElement searchBtn = driver.findElement(By.linkText("Sign in"));
      Actions actionProvider = new Actions(driver);
      // Perform context-click action on the element
      actionProvider.contextClick(searchBtn).build().perform();
    } finally {
      driver.quit();
    }
  }
}
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Please raise a PR
  {{< / code-panel >}}
{{< / code-tab >}}

## doubleClick
It will move to the element and performs a double-click in the middle of the given element.

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class doubleClick {
  public static void main(String[] args) {
    WebDriver driver = new ChromeDriver();
    try {
      // Navigate to Url
      driver.get("https://google.com");

      // Store 'google search' button web element
      WebElement searchBtn = driver.findElement(By.linkText("Sign in"));
      Actions actionProvider = new Actions(driver);
      // Perform double-click action on the element
      actionProvider.doubleClick(searchBtn).build().perform();
    } finally {
      driver.quit();
    }
  }
}
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Please raise a PR
  {{< / code-panel >}}
{{< / code-tab >}}

## moveToElement
This method moves the mouse to the middle of the element. The element is also scrolled into the view on performing this action.

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class moveToElement {
  public static void main(String[] args) {
    WebDriver driver = new ChromeDriver();
    try {
      // Navigate to Url
      driver.get("https://google.com");

      // Store 'google search' button web element
      WebElement searchBtn = driver.findElement(By.linkText("Gmail"));
      Actions actionProvider = new Actions(driver);
      // Performs mouse move action onto the element
      actionProvider.moveToElement(searchBtn).build().perform();
    } finally {
      driver.quit();
    }
  }
}
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Please raise a PR
  {{< / code-panel >}}
{{< / code-tab >}}

## moveByOffset:

This method moves the mouse from its current position (or 0,0) by the given offset. If the coordinates are outside the view window, then the mouse will end up outside the browser window.

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class moveByOffset {
  public static void main(String[] args) {
    WebDriver driver = new ChromeDriver();
    try {
      // Navigate to Url
      driver.get("https://google.com");

      // Store 'google search' button web element
      WebElement searchBtn = driver.findElement(By.linkText("Gmail"));
      // Capture x and y offset positions of element
      int xOffset = searchBtn.getRect().getX();
      int yOffset = searchBtn.getRect().getY();
      Actions actionProvider = new Actions(driver);
      // Performs mouse move action onto the offset position
      actionProvider.moveByOffset(xOffset, yOffset).build().perform();
    } finally {
      driver.quit();
    }
  }
}
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Please raise a PR
  {{< / code-panel >}}
{{< / code-tab >}}

## dragAndDrop

This method firstly performs a click-and-hold on the source element, 
moves to the location of the target element and then releases the mouse.

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class dragAndDrop {
  public static void main(String[] args) {
    WebDriver driver = new ChromeDriver();
    try {
      // Navigate to Url
      driver.get("https://crossbrowsertesting.github.io/drag-and-drop");
      // Store 'box A' as source element
      WebElement sourceEle = driver.findElement(By.id("draggable"));
      // Store 'box B' as source element
      WebElement targetEle = driver.findElement(By.id("droppable"));
      Actions actionProvider = new Actions(driver);
      // Performs drag and drop action of sourceEle onto the targetEle
      actionProvider.dragAndDrop(sourceEle, targetEle).build().perform();
    } finally {
      driver.quit();
    }
  }
}
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Please raise a PR
  {{< / code-panel >}}
{{< / code-tab >}}

## dragAndDropBy

This method firstly performs a click-and-hold on the source element, moves to the given offset and then releases the mouse.

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class dragAndDropBy {
  public static void main(String[] args) {
    WebDriver driver = new ChromeDriver();
    try {
      // Navigate to Url
      driver.get("https://crossbrowsertesting.github.io/drag-and-drop");
      // Store 'box A' as source element
      WebElement sourceEle = driver.findElement(By.id("draggable"));
      // Store 'box B' as source element
      WebElement targetEle = driver.findElement(By.id("droppable"));
      int targetEleXOffset = targetEle.getLocation().getX();
      int targetEleYOffset = targetEle.getLocation().getY();
      Actions actionProvider = new Actions(driver);
      // Performs dragAndDropBy onto the  target element offset position
      actionProvider.dragAndDropBy(sourceEle, targetEleXOffset, targetEleYOffset).build().perform();
    } finally {
      driver.quit();
    }
  }
}
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Please raise a PR
  {{< / code-panel >}}
{{< / code-tab >}}

## release

This action releases the depressed left mouse button. If WebElement is passed, 
it will release depressed left mouse button on the given WebElement

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class release {
  public static void main(String[] args) {
    WebDriver driver = new ChromeDriver();
    try {
      // Navigate to Url
      driver.get("https://crossbrowsertesting.github.io/drag-and-drop");
      // Store 'box A' as source element
      WebElement sourceEle = driver.findElement(By.id("draggable"));
      // Store 'box B' as source element
      WebElement targetEle = driver.findElement(By.id("droppable"));
      Actions actionProvider = new Actions(driver);
      actionProvider.clickAndHold(sourceEle).moveToElement(targetEle).build().perform();
      // Performs release event
      actionProvider.release().build().perform();
    } finally {
      driver.quit();
    }
  }
}
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Please raise a PR
  {{< / code-panel >}}
{{< / code-tab >}}

