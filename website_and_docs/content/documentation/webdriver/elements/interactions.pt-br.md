---
title: "Interacting with web elements"
linkTitle: "Interactions"
weight: 2
description: >
   A high-level instruction set for manipulating form controls.
needsTranslation: true
---

There are only 5 basic commands that can be executed on an element:
* [click](https://w3c.github.io/webdriver/#element-click) (applies to any element)
* [send keys](https://w3c.github.io/webdriver/#element-send-keys) (only applies to text fields and content editable elements)
* [clear](https://w3c.github.io/webdriver/#element-send-keys) (only applies to text fields and content editable elements)
* submit (only applies to form elements)
* select (see [Select List Elements]({{< ref "select_lists.md" >}}))

## Additional validations

These methods are designed to closely emulate a user's experience, so,
unlike the [Actions API]({{< ref "/documentation/webdriver/actions_api/" >}}), it attempts to perform two things
before attempting the specified action.
1. If it determines the element is outside the viewport, it
   [scrolls the element into view](https://w3c.github.io/webdriver/#dfn-scrolls-into-view), specifically
   it will align the bottom of the element with the bottom of the viewport.
2. It ensures the element is [interactable](https://w3c.github.io/webdriver/#interactability)
   before taking the action. This could mean that the scrolling was unsuccessful, or that the
   element is not otherwise displayed.  Determining if an element is displayed on a page was too difficult to
   [define directly in the webdriver specification](https://w3c.github.io/webdriver/#element-displayedness),
   so Selenium sends an execute command with a JavaScript atom that checks for things that would keep
   the element from being displayed. If it determines an element is not in the viewport, not displayed, not
   [keyboard-interactable](https://w3c.github.io/webdriver/#dfn-keyboard-interactable), or not
   [pointer-interactable](https://w3c.github.io/webdriver/#dfn-pointer-interactable),
   it returns an [element not interactable](https://w3c.github.io/webdriver/#dfn-element-not-interactable) error.

## Click

The [element click command](https://w3c.github.io/webdriver/#dfn-element-click) is executed on
the [center of the element](https://w3c.github.io/webdriver/#dfn-center-point).
If the center of the element is [obscured](https://w3c.github.io/webdriver/#dfn-obscuring) for some reason,
Selenium will return an [element click intercepted](https://w3c.github.io/webdriver/#dfn-element-click-intercepted) error.



{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java"  text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/elements/InteractionTest.java#L18-L22" >}}
{{< /tab >}}
  {{< tab header="Python" >}}

    # Navigate to url
	driver.get("https://www.selenium.dev/selenium/web/inputs.html")

    # Click on the element 
	driver.find_element(By.NAME, "color_input").click()
  {{< /tab >}}
  {{< tab header="CSharp" >}}

  // Navigate to Url
  driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/inputs.html");

  // Click the element
  driver.FindElement(By.Name("color_input")).Click();
  
  {{< /tab >}}
  {{< tab header="Ruby" >}}

    # Navigate to URL
  driver.get 'https://www.selenium.dev/selenium/web/inputs.html'

    # Click the element
  driver.find_element(name: 'color_input').click

  {{< /tab >}}
  {{< tab header="JavaScript" >}}

    // Navigate to Url
    await driver.get('https://www.selenium.dev/selenium/web/inputs.html');

    // Click the element
    await driver.findElement(By.name('color_input')).click();
  
  {{< /tab >}}
  {{< tab header="Kotlin" >}}

    // Navigate to Url
    driver.get("https://www.selenium.dev/selenium/web/inputs.html")

    // Click the element
    driver.findElement(By.name("color_input")).click();
  
  {{< /tab >}}
{{< /tabpane >}}


## Send keys

The [element send keys command](https://w3c.github.io/webdriver/#dfn-element-send-keys)
types the provided keys into an [editable](https://w3c.github.io/webdriver/#dfn-editable) element.
Typically, this means an element is an input element of a form with a `text` type or an element
with a `content-editable` attribute. If it is not editable,
[an invalid element state](https://w3c.github.io/webdriver/#dfn-invalid-element-state) error is returned.

[Here](https://www.w3.org/TR/webdriver/#keyboard-actions) is the list of
possible keystrokes that WebDriver Supports.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java"  text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/elements/InteractionTest.java#L27-L32" >}}
{{< /tab >}}

  {{< tab header="Python" >}}


    # Navigate to url
	driver.get("https://www.selenium.dev/selenium/web/inputs.html")

    # Clear field to empty it from any previous data
	driver.find_element(By.NAME, "email_input").clear()

	# Enter Text
	driver.find_element(By.NAME, "email_input").send_keys("admin@localhost.dev" )

  {{< /tab >}}
  {{< tab header="CSharp" >}}

  // Navigate to Url
  driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/inputs.html");

  // Clear field to empty it from any previous data
  driver.FindElement(By.Name("email_input")).Clear();
  
  //Enter Text
  driver.FindElement(By.Name("email_input")).SendKeys("admin@localhost.dev");
  
  
}
  {{< /tab >}}
  {{< tab header="Ruby" >}}

    # Navigate to URL
	driver.get 'https://www.selenium.dev/selenium/web/inputs.html'

    # Clear field to empty it from any previous data
	driver.find_element(name: 'email_input').clear
	
	# Enter Text
	driver.find_element(name: 'email_input').send_keys 'admin@localhost.dev'

  {{< /tab >}}
  {{< tab header="JavaScript" >}}

    // Navigate to Url
    await driver.get('https://www.selenium.dev/selenium/web/inputs.html');

	//Clear field to empty it from any previous data
	await driver.findElement(By.name('email_input')).clear();

    // Enter text 
    await driver.findElement(By.name('email_input')).sendKeys('admin@localhost.dev');
  
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  
    // Navigate to Url
    driver.get("https://www.selenium.dev/selenium/web/inputs.html")

	//Clear field to empty it from any previous data
	driver.findElement(By.name("email_input")).clear()
	
    // Enter text 
    driver.findElement(By.name("email_input")).sendKeys("admin@localhost.dev")
  
  {{< /tab >}}
{{< /tabpane >}}

## Clear

The [element clear command](https://w3c.github.io/webdriver/#dfn-element-clear) resets the content of an element.
This requires an element to be [editable](https://w3c.github.io/webdriver/#dfn-editable),
and [resettable](https://w3c.github.io/webdriver/#dfn-resettable-elements). Typically,
this means an element is an input element of a form with a `text` type or an element
with a`content-editable` attribute. If these conditions are not met,
[an invalid element state](https://w3c.github.io/webdriver/#dfn-invalid-element-state) error is returned.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/elements/InteractionTest.java#L38-L40" >}}
{{< /tab >}}
  {{< tab header="Python" >}}


    # Navigate to url
	driver.get("https://www.selenium.dev/selenium/web/inputs.html")

    # Clear field to empty it from any previous data
	driver.find_element(By.NAME, "email_input").clear()

	
  {{< /tab >}}
  {{< tab header="CSharp" >}}

  // Navigate to Url
  driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/inputs.html");

  // Clear field to empty it from any previous data
  driver.FindElement(By.Name("email_input")).Clear();
  
 
  
}
  {{< /tab >}}
  {{< tab header="Ruby" >}}

    # Navigate to URL
	driver.get 'https://www.selenium.dev/selenium/web/inputs.html'

    # Clear field to empty it from any previous data
	driver.find_element(name: 'email_input').clear

  {{< /tab >}}
  {{< tab header="JavaScript" >}}

    // Navigate to Url
    await driver.get('https://www.selenium.dev/selenium/web/inputs.html');

	//Clear field to empty it from any previous data
	await driver.findElement(By.name('email_input')).clear();

   
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  
    // Navigate to Url
    driver.get("https://www.selenium.dev/selenium/web/inputs.html")

	//Clear field to empty it from any previous data
	driver.findElement(By.name("email_input")).clear()
	
  
  {{< /tab >}}
{{< /tabpane >}}

## Submit

In Selenium 4 this is no longer implemented with a separate endpoint and functions by executing a script. As
such, it is recommended not to use this method and to click the applicable form submission button instead.

