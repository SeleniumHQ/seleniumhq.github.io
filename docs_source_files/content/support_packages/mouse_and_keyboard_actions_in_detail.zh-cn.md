---
title: "Mouse and keyboard actions in detail"
weight: 4
---

{{% notice info %}}
<i class="fas fa-language"></i> 页面需要从英语翻译为简体中文。
您熟悉英语与简体中文吗？帮助我们翻译它，通过 pull requests 给我们！
{{% /notice %}}

Suppose you have an arbitrary web element **e:**

{{< code-tab >}}
  {{< code-panel language="java" >}}
WebElement e = driver.findElement(By.id("testElement"));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
e = driver.find_element_by_id("testElement")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# We don't have a Ruby code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// We don't have a JavaScript code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val element = driver.findElement(By.id("testElement"))
  {{< / code-panel >}}
{{< / code-tab >}}

You can simulate mouse clicking on e if it is visible and has a height and width
that are greater than 0:

{{< code-tab >}}
  {{< code-panel language="java" >}}
e.click();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
e.click()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# We don't have a Ruby code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// We don't have a JavaScript code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
e.click()
  {{< / code-panel >}}
{{< / code-tab >}}

Moreover, it is possible to mimic hovering of the cursor over **e**. In order
to do so, you will need the following import statement:

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.interactions.Actions;
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium.webdriver import ActionChains
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# We don't have a Ruby code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// We don't have a JavaScript code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.interactions.Actions
  {{< / code-panel >}}
{{< / code-tab >}}

With this statement in place, you can now move over the element in question:

{{< code-tab >}}
  {{< code-panel language="java" >}}
Actions actions = new Actions(driver);
actions.moveToElement(e);
actions.perform();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
actions = ActionChains(driver)
actions.move_to_element(e)
actions.perform()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# We don't have a Ruby code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// We don't have a JavaScript code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val actions = Actions(driver)
actions.moveToElement(e);
actions.perform();
  {{< / code-panel >}}
{{< / code-tab >}}

If **e** is an **input** or **textarea** element, the following keyboard 
actions can be carried out:

* Enter a sequence of characters in e:

{{< code-tab >}}
  {{< code-panel language="java" >}}
e.sendKeys("Test");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
e.send_keys("Test")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# We don't have a Ruby code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// We don't have a JavaScript code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
e.sendKeys("Test")
  {{< / code-panel >}}
{{< / code-tab >}}

* Delete the text that is in e (if there is any):

{{< code-tab >}}
  {{< code-panel language="java" >}}
e.clear();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
e.clear()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# We don't have a Ruby code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// We don't have a JavaScript code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
e.clear()
  {{< / code-panel >}}
{{< / code-tab >}}