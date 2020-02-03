---
title: "Details van muisactie"
weight: 4
---

{{% notice info %}}
<i class="fas fa-language"></i> Page being translated from 
English to Dutch. Do you speak Dutch? Help us to translate
it by sending us pull requests!
{{% /notice %}}

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
const {Builder, By} = require('selenium-webdriver');

(async function clickAndHold() {
  let driver = await new Builder().forBrowser('chrome').build();
  try {
    // Navigate to Url
    await driver.get('https://www.google.com');
    // Store 'google search' button web element
    let searchBtn = driver.findElement(By.linkText("Sign in"));
    const actions = driver.actions({async: true});
    // Perform mouseMove to element and mouseDown (press) action on the element
    await actions.move({origin:searchBtn}).press().perform();
  }
  finally {
    await driver.quit();
  }
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions

fun main() {
    val driver =  ChromeDriver()
    try {
        // Navigate to Url
        driver.get("https://google.com")
        // Store 'google search' button web element
        val searchBtn = driver.findElement(By.linkText("Sign in"))
        val actionProvider = Actions(driver)
        // Perform click-and-hold action on the element
        actionProvider.clickAndHold(searchBtn).build().perform()
    } finally {
        driver.quit()
    }
}
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
const {Builder, By} = require('selenium-webdriver');

(async function contextClick() {
  let driver = await new Builder().forBrowser('chrome').build();
  try {
    // Navigate to Url
    await driver.get('https://www.google.com');
    // Store 'google search' button web element
    let searchBtn = driver.findElement(By.linkText("Sign in"));
    const actions = driver.actions({async: true});
    // Perform context-click action on the element
    await actions.contextClick(searchBtn).perform();
  }
  finally {
    await driver.quit();
  }
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions

fun main() {
    val driver =  ChromeDriver()
    try {
        // Navigate to Url
        driver.get("https://google.com")
        // Store 'google search' button web element
        val searchBtn = driver.findElement(By.linkText("Sign in"))
        val actionProvider = Actions(driver)
        // Perform context-click action on the element
        actionProvider.contextClick(searchBtn).build().perform()
    } finally {
        driver.quit()
    }
}
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
const {Builder, By} = require('selenium-webdriver');

(async function doubleClick() {
  let driver = await new Builder().forBrowser('chrome').build();
  try {
    // Navigate to Url
    await driver.get('https://www.google.com');
    // Store 'google search' button web element
    let searchBtn = driver.findElement(By.linkText("Sign in"));
    const actions = driver.actions({async: true});
    // Perform double-click action on the element
    await actions.doubleClick(searchBtn).perform();
  }
  finally {
    await driver.quit();
  }
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions

fun main() {
    val driver =  ChromeDriver()
    try {
        // Navigate to Url
        driver.get("https://google.com")
        // Store 'google search' button web element
        val searchBtn = driver.findElement(By.linkText("Sign in"))
        val actionProvider = Actions(driver)
        // Perform double-click action on the element
        actionProvider.doubleClick(searchBtn).build().perform()
    } finally {
        driver.quit()
    }
}
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

      // Store 'Gmail' anchor web element
      WebElement gmailLink = driver.findElement(By.linkText("Gmail"));
      Actions actionProvider = new Actions(driver);
      // Performs mouse move action onto the element
      actionProvider.moveToElement(gmailLink).build().perform();
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
const {Builder, By} = require('selenium-webdriver');

(async function moveToElement() {
  let driver = await new Builder().forBrowser('chrome').build();
  try {
    // Navigate to Url
    await driver.get('https://www.google.com');
    // Store 'Gmail' anchor web element
    let gmailLink = driver.findElement(By.linkText("Gmail"));
    const actions = driver.actions({async: true});
    // Performs mouse move action onto the element
    await actions.move({origin:gmailLink}).perform();
  }
  finally {
    await driver.quit();
  }
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions

fun main() {
    val driver =  ChromeDriver()
    try {
        // Navigate to Url
        driver.get("https://google.com")
        // Store 'Gmail' anchor web element
        val gmailLink = driver.findElement(By.linkText("Gmail"))
        val actionProvider = Actions(driver)
        // Performs mouse move action onto the element
        actionProvider.moveToElement(gmailLink).build().perform()
    } finally {
        driver.quit()
    }
}
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

      // Store 'Gmail' anchor web elementt
      WebElement gmailLink = driver.findElement(By.linkText("Gmail"));
      // Capture x and y offset positions of element
      int xOffset = gmailLink.getRect().getX();
      int yOffset = gmailLink.getRect().getY();
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
const {Builder, By} = require('selenium-webdriver');

(async function moveByOffset() {
  let driver = await new Builder().forBrowser('chrome').build();
  try {
    // Navigate to Url
    await driver.get('https://www.google.com');
    // Store 'Gmail' anchor web element
    let gmailLink = driver.findElement(By.linkText("Gmail"));
    // Capture offset positions of element
    let offset = await gmailLink.getRect();
    let x = await offset.x;
    let y = await offset.y;
    const actions = driver.actions({async: true});
    // Performs mouse move action onto the element
    await actions.move({x:parseInt(x),y:parseInt(y)}).pause(3000).perform();
  }
  finally {
    await driver.quit();
  }
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions

fun main() {
    val driver =  ChromeDriver()
    try {
        // Navigate to Url
        driver.get("https://google.com")
        // Store 'Gmail' anchor web element
        val gmailLink = driver.findElement(By.linkText("Gmail"))
        // Capture x and y offset positions of element
        val xOffset = gmailLink.rect.getX()
        val yOffset = gmailLink.rect.getY()
        val actionProvider = Actions(driver)
        // Performs mouse move action onto the element
        actionProvider.moveByOffset(xOffset, yOffset).build().perform()
    } finally {
        driver.quit()
    }
}
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
const {Builder, By} = require('selenium-webdriver');

(async function dragAndDrop() {
  let driver = await new Builder().forBrowser('chrome').build();
  try {
    // Navigate to Url
    await driver.get('https://crossbrowsertesting.github.io/drag-and-drop');
    // Store 'box A' as source element
    let sourceEle = driver.findElement(By.id("draggable"));
    // Store 'box B' as source element
    let targetEle = driver.findElement(By.id("droppable"));
    const actions = driver.actions({async: true});
    // Performs drag and drop action of sourceEle onto the targetEle
    await actions.dragAndDrop(sourceEle, targetEle).perform();
  }
  finally {
    await driver.quit();
  }
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions

fun main() {
    val driver =  ChromeDriver()
    try {
        // Navigate to Url
        driver.get("https://crossbrowsertesting.github.io/drag-and-drop")
        // Store 'box A' as source element
        val sourceEle = driver.findElement(By.id("draggable"))
        // Store 'box B' as source element
        val targetEle = driver.findElement(By.id("droppable"))
        val actionProvider = Actions(driver)
        // Performs drag and drop action of sourceEle onto the targetEle
        actionProvider.dragAndDrop(sourceEle, targetEle).build().perform()
    } finally {
        driver.quit()
    }
}
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
const {Builder, By} = require('selenium-webdriver');

(async function dragAndDropBy() {
  let driver = await new Builder().forBrowser('chrome').build();
  try {
    // Navigate to Url
    await driver.get('https://crossbrowsertesting.github.io/drag-and-drop');
    // Store 'box A' as source element
    let sourceEle = driver.findElement(By.id("draggable"));
    // Store 'box B' as source element
    let targetEle = driver.findElement(By.id("droppable"));
    let offset = await targetEle.getRect();
    let x = await offset.x;
    let y = await offset.y;
    const actions = driver.actions({async: true});
    // Performs dragAndDropBy onto the  target element offset position
    await actions.dragAndDrop(sourceEle, {x:parseInt(x), y:parseInt(y)}).perform();
  }
  finally {
    await driver.quit();
  }
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions

fun main() {
    val driver =  ChromeDriver()
    try {
        // Navigate to Url
        driver.get("https://crossbrowsertesting.github.io/drag-and-drop")
        // Store 'box A' as source element
        val sourceEle = driver.findElement(By.id("draggable"))
        // Store 'box B' as source element
        val targetEle = driver.findElement(By.id("droppable"))
        val targetEleXOffset = targetEle.location.getX()
        val targetEleYOffset = targetEle.location.getY()
        val actionProvider = Actions(driver)
        // Performs dragAndDropBy onto the  target element offset position
        actionProvider.dragAndDropBy(sourceEle, targetEleXOffset, targetEleYOffset).build().perform()
    } finally {
        driver.quit()
    }
}
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
const {Builder, By} = require('selenium-webdriver');

(async function release() {
  let driver = await new Builder().forBrowser('chrome').build();
  try {
    // Navigate to Url
    await driver.get('https://crossbrowsertesting.github.io/drag-and-drop');
    // Store 'box A' as source element
    let sourceEle = driver.findElement(By.id("draggable"));
    // Store 'box B' as source element
    let targetEle = driver.findElement(By.id("droppable"));
    const actions = driver.actions({async: true});
    await actions.move({origin:sourceEle}).press().perform();
    // Performs release event on target element
    await actions.move({origin:targetEle}).release().perform();
  }
  finally {
    await driver.quit();
  }
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions

fun main() {
    val driver =  ChromeDriver()
    try {
        // Navigate to Url
        driver.get("https://crossbrowsertesting.github.io/drag-and-drop")
        // Store 'box A' as source element
        val sourceEle = driver.findElement(By.id("draggable"))
        // Store 'box B' as source element
        val targetEle = driver.findElement(By.id("droppable"))
        val actionProvider = Actions(driver)
        actionProvider.clickAndHold(sourceEle).moveToElement(targetEle).build().perform()
        // Performs release event
        actionProvider.release().build().perform()
    } finally {
        driver.quit()
    }
}
  {{< / code-panel >}}
{{< / code-tab >}}
