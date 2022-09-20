---
title: "Working with iFrames and frames"
linkTitle: "Frames"
weight: 6
aliases: [
"/documentation/webdriver/browser/frames",
]
---

Frames are a now deprecated means of building a site layout from
multiple documents on the same domain. You are unlikely to work with
them unless you are working with an pre HTML5 webapp.  Iframes allow
the insertion of a document from an entirely different domain, and are
still commonly used.

If you need to work with frames or iframes, WebDriver allows you to
work with them in the same way. Consider a button within an iframe.
If we inspect the element using the browser development tools, we might
see the following:

```html
<div id="modal">
  <iframe id="buttonframe" name="myframe"  src="https://seleniumhq.github.io">
   <button>Click here</button>
 </iframe>
</div>
```

If it was not for the iframe we would expect to click on the button
using something like:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
//This won't work
driver.findElement(By.tagName("button")).click();
  {{< /tab >}}
  {{< tab header="Python" >}}
    # This Wont work
driver.find_element(By.TAG_NAME, 'button').click()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
//This won't work
driver.FindElement(By.TagName("button")).Click();
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    # This won't work
driver.find_element(:tag_name,'button').click
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// This won't work
await driver.findElement(By.css('button')).click();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
//This won't work
driver.findElement(By.tagName("button")).click()
  {{< /tab >}}
{{< /tabpane >}}

However, if there are no buttons outside of the iframe, you might
instead get a _no such element_ error. This happens because Selenium is
only aware of the elements in the top level document. To interact with
the button, we will need to first switch to the frame, in a similar way
to how we switch windows. WebDriver offers three ways of switching to
a frame.

## Using a WebElement

Switching using a WebElement is the most flexible option. You can
find the frame using your preferred selector and switch to it.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
//Store the web element
WebElement iframe = driver.findElement(By.cssSelector("#modal>iframe"));

//Switch to the frame
driver.switchTo().frame(iframe);

//Now we can click the button
driver.findElement(By.tagName("button")).click();
  {{< /tab >}}
  {{< tab header="Python" >}}
    # Store iframe web element
iframe = driver.find_element(By.CSS_SELECTOR, "#modal > iframe")

    # switch to selected iframe
driver.switch_to.frame(iframe)

    # Now click on button
driver.find_element(By.TAG_NAME, 'button').click()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
//Store the web element
IWebElement iframe = driver.FindElement(By.CssSelector("#modal>iframe"));

//Switch to the frame
driver.SwitchTo().Frame(iframe);

//Now we can click the button
driver.FindElement(By.TagName("button")).Click();
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    # Store iframe web element
iframe = driver.find_element(:css,'#modal > iframe')

    # Switch to the frame
driver.switch_to.frame iframe

    # Now, Click on the button
driver.find_element(:tag_name,'button').click
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Store the web element
const iframe = driver.findElement(By.css('#modal > iframe'));

// Switch to the frame
await driver.switchTo().frame(iframe);

// Now we can click the button
await driver.findElement(By.css('button')).click();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
//Store the web element
val iframe = driver.findElement(By.cssSelector("#modal>iframe"))

//Switch to the frame
driver.switchTo().frame(iframe)

//Now we can click the button
driver.findElement(By.tagName("button")).click()
  {{< /tab >}}
{{< /tabpane >}}

## Using a name or ID
If your frame or iframe has an id or name attribute, this can be used
instead.  If the name or ID is not unique on the page, then the first
one found will be switched to.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
//Using the ID
driver.switchTo().frame("buttonframe");

//Or using the name instead
driver.switchTo().frame("myframe");

//Now we can click the button
driver.findElement(By.tagName("button")).click();
  {{< /tab >}}
  {{< tab header="Python" >}}
    # Switch frame by id
driver.switch_to.frame('buttonframe')

    # Now, Click on the button
driver.find_element(By.TAG_NAME, 'button').click()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
//Using the ID
driver.SwitchTo().Frame("buttonframe");

//Or using the name instead
driver.SwitchTo().Frame("myframe");

//Now we can click the button
driver.FindElement(By.TagName("button")).Click();
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    # Switch by ID
driver.switch_to.frame 'buttonframe'

    # Now, Click on the button
driver.find_element(:tag_name,'button').click
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Using the ID
await driver.switchTo().frame('buttonframe');

// Or using the name instead
await driver.switchTo().frame('myframe');

// Now we can click the button
await driver.findElement(By.css('button')).click();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
//Using the ID
driver.switchTo().frame("buttonframe")

//Or using the name instead
driver.switchTo().frame("myframe")

//Now we can click the button
driver.findElement(By.tagName("button")).click()
  {{< /tab >}}
{{< /tabpane >}}

## Using an index

It is also possible to use the index of the frame, such as can be
queried using _window.frames_ in JavaScript.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
// Switches to the second frame
driver.switchTo().frame(1);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    # Switch to the second frame
driver.switch_to.frame(1)
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// Switches to the second frame
driver.SwitchTo().Frame(1);
  {{< /tab >}}
  {{< tab header="Python" >}}
    # switching to second iframe based on index
iframe = driver.find_elements(By.TAG_NAME,'iframe')[1]

    # switch to selected iframe
driver.switch_to.frame(iframe)
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Switches to the second frame
await driver.switchTo().frame(1);
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
// Switches to the second frame
driver.switchTo().frame(1)
  {{< /tab >}}
{{< /tabpane >}}


## Leaving a frame

To leave an iframe or frameset, switch back to the default content
like so:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
// Return to the top level
driver.switchTo().defaultContent();
  {{< /tab >}}
  {{< tab header="Python" >}}
    # switch back to default content
driver.switch_to.default_content()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// Return to the top level
driver.SwitchTo().DefaultContent();
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    # Return to the top level
driver.switch_to.default_content
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Return to the top level
await driver.switchTo().defaultContent();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
// Return to the top level
driver.switchTo().defaultContent()
  {{< /tab >}}
{{< /tabpane >}}
