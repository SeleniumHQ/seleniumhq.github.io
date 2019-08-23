---
title: "Keyboard"
weight: 10
---

{{% notice info %}}
<i class="fas fa-language"></i> Page being translated from 
English to Dutch. Do you speak Dutch? Help us to translate
it by sending us pull requests!
{{% /notice %}}

Represents a KeyBoard event. KeyBoard actions are performed by using low-level
interface which allows us to provide virtualized device input to the web browser.

# sendKeys

The sendKeys types a key sequence in DOM element even if modifier key sequence is encountered.

{{< code-tab >}}
  {{< code-panel language="java" >}}
// We don't have a Java code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
// We don't have a Python code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
// We don't have a Ruby code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder, By, Key} = require('selenium-webdriver');

(async function example() {
    let driver = await new Builder().forBrowser('chrome').build();

    try {
        // Navigate to Url
        await driver.get('https://www.google.com');

        // Enter text "webdriver" and perform keyboard action "Enter"
        await driver.findElement(By.name('q')).sendKeys('webdriver', Key.ENTER);
    }
    finally {
        await driver.quit();
    }
})();
  {{< / code-panel >}}
{{< / code-tab >}}