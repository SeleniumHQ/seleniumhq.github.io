---
title: "Information about web elements"
linkTitle: "Information"
weight: 4
needsTranslation: true
description: >
  What you can learn about an element.
---

There are a number of details you can query about a specific element.

## Is Displayed

This method is used to check if the connected Element is
displayed on a webpage. Returns a `Boolean` value,
True if the connected element is displayed in the current
browsing context else returns false.

This functionality is [mentioned in](https://w3c.github.io/webdriver/#element-displayedness), but not defined by
the w3c specification due to the
[impossibility of covering all potential conditions](https://www.youtube.com/watch?v=LAD_XPGP_kk).
As such, Selenium cannot expect drivers to implement
this functionality directly, and now relies on
executing a large JavaScript function directly.
This function makes many approximations about an element's
nature and relationship in the tree to return a value.


{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/elements/InformationTest.java#L20-L25" >}}
{{< /tab >}}
{{< tab header="Python" >}}
# Navigate to the url
driver.get("https://www.selenium.dev/selenium/web/inputs.html")

# Get boolean value for is element display
is_email_visible = driver.find_element(By.NAME, "email_input").is_displayed()
{{< /tab >}}
{{< tab header="CSharp" >}}
//Navigate to the url
driver.Url = "https://www.selenium.dev/selenium/web/inputs.html";

//Get boolean value for is element display
Boolean is_email_visible = driver.FindElement(By.Name("email_input")).Displayed;
{{< /tab >}}
{{< tab header="Ruby" >}}
# Navigate to the url
driver.get("https://www.selenium.dev/selenium/web/inputs.html");

#fetch display status
val = driver.find_element(name: 'email_input').displayed?
{{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/elements/information.spec.js#L16-L17">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
//navigates to url
 driver.get("https://www.selenium.dev/selenium/web/inputs.html")

 //returns true if element is displayed else returns false
 val flag = driver.findElement(By.name("email_input")).isDisplayed()
{{< /tab >}}
{{< /tabpane >}}

## Is Enabled

This method is used to check if the connected Element
is enabled or disabled on a webpage.
Returns a boolean value, **True** if the connected element is
**enabled** in the current browsing context else returns **false**.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/elements/InformationTest.java#L27-L30" >}}
{{< /tab >}}
  {{< tab header="Python" >}}
    # Navigate to url
driver.get("https://www.selenium.dev/selenium/web/inputs.html")

    # Returns true if element is enabled else returns false
value = driver.find_element(By.NAME, 'button_input').is_enabled()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// Navigate to Url
driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/inputs.html");

// Store the WebElement
IWebElement element = driver.FindElement(By.Name("button_input"));

// Prints true if element is enabled else returns false
System.Console.WriteLine(element.Enabled);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    # Navigate to url
driver.get 'https://www.selenium.dev/selenium/web/inputs.html'

    # Returns true if element is enabled else returns false
ele = driver.find_element(name: 'button_input').enabled?
  {{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/elements/information.spec.js#L23-L24">}}
{{< /tab >}}
  {{< tab header="Kotlin" >}}
 //navigates to url
 driver.get("https://www.selenium.dev/selenium/web/inputs.html")

 //returns true if element is enabled else returns false
 val attr = driver.findElement(By.name("button_input")).isEnabled()
  {{< /tab >}}
{{< /tabpane >}}


## Elemento está selecionado

Este método determina se o elemento referenciado
é _Selected_ ou não. Este método é amplamente utilizado em
caixas de seleção, botões de opção, elementos de entrada e elementos de
opção.

Retorna um valor booleano, **true** se o elemento referenciado for
**selected** no contexto de navegação atual, caso contrário, retorna
**false**.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/elements/InformationTest.java#L32-L35" >}}
{{< /tab >}}
  {{< tab header="Python" >}}
    # Navigate to url
driver.get("https://www.selenium.dev/selenium/web/inputs.html")

    # Returns true if element is checked else returns false
value = driver.find_element(By.NAME, "checkbox_input").is_selected()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// Navigate to Url
driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/inputs.html");

// Returns true if element ins checked else returns false
bool value = driver.FindElement(By.Name("checkbox_input")).Selected;
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    # Navigate to url
driver.get 'https://www.selenium.dev/selenium/web/inputs.html'

    # Returns true if element is checked else returns false
ele = driver.find_element(name: "checkbox_input").selected?
  {{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/elements/information.spec.js#L30-L31">}}
{{< /tab >}}
  {{< tab header="Kotlin" >}}
 //navigates to url
 driver.get("https://www.selenium.dev/selenium/web/inputs.html")

 //returns true if element is checked else returns false
 val attr =  driver.findElement(By.name("checkbox_input")).isSelected()
  {{< /tab >}}
{{< /tabpane >}}

## Coletar TagName do elemento

É usado para buscar o [TagName](https://www.w3.org/TR/webdriver/#dfn-get-element-tag-name)
do elemento referenciado que tem o foco no contexto de navegação atual.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/elements/InformationTest.java#L37-L40" >}}
{{< /tab >}}
  {{< tab header="Python" >}}
    # Navigate to url
driver.get("https://www.selenium.dev/selenium/web/inputs.html")

    # Returns TagName of the element
attr = driver.find_element(By.NAME, "email_input").tag_name
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// Navigate to Url
driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/inputs.html");

// Returns TagName of the element
string attr = driver.FindElement(By.Name("email_input")).TagName;
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    # Navigate to url
driver.get 'https://www.selenium.dev/selenium/web/inputs.html'

    # Returns TagName of the element
attr = driver.find_element(name: "email_input").tag_name
  {{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/elements/information.spec.js#L37-L38">}}
{{< /tab >}}
  {{< tab header="Kotlin" >}}
 //navigates to url
 driver.get("https://www.selenium.dev/selenium/web/inputs.html")

 //returns TagName of the element
 val attr =  driver.findElement(By.name("email_input")).getTagName()
  {{< /tab >}}
{{< /tabpane >}}

## Coletar retângulo do elemento

É usado para buscar as dimensões e coordenadas
do elemento referenciado.

O corpo de dados buscado contém os seguintes detalhes:
* Posição do eixo X a partir do canto superior esquerdo do elemento
* posição do eixo y a partir do canto superior esquerdo do elemento
* Altura do elemento
* Largura do elemento


{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/elements/InformationTest.java#L42-L46" >}}
{{< /tab >}}
  {{< tab header="Python" >}}
    # Navigate to url
driver.get("https://www.selenium.dev/selenium/web/inputs.html")

    # Returns height, width, x and y coordinates referenced element
res = driver.find_element(By.NAME, "range_input").rect
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// Navigate to Url
driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/inputs.html");

var res = driver.FindElement(By.Name("range_input"));
// Return x and y coordinates referenced element
System.Console.WriteLine(res.Location);
// Returns height, width
System.Console.WriteLine(res.Size);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    # Navigate to url
driver.get 'https://www.selenium.dev/selenium/web/inputs.html'

    # Returns height, width, x and y coordinates referenced element
res = driver.find_element(name: "range_input").rect
  {{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/elements/information.spec.js#L45">}}
{{< /tab >}}
  {{< tab header="Kotlin" >}}
// Navigate to url
driver.get("https://www.selenium.dev/selenium/web/inputs.html")

// Returns height, width, x and y coordinates referenced element
val res = driver.findElement(By.name("range_input")).rect

// Rectangle class provides getX,getY, getWidth, getHeight methods
println(res.getX())
  {{< /tab >}}
{{< /tabpane >}}

## Coletar valor CSS do elemento

Recupera o valor da propriedade de estilo computado especificada
de um elemento no contexto de navegação atual.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/elements/InformationTest.java#L49-L51" >}}
{{< /tab >}}
  {{< tab header="Python" >}}

    # Navigate to Url
driver.get('https://www.selenium.dev/selenium/web/colorPage.html')

    # Retrieves the computed style property 'color' of linktext
cssValue = driver.find_element(By.ID, "namedColor").value_of_css_property('background-color')

  {{< /tab >}}
  {{< tab header="CSharp" >}}

// Navigate to Url
driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/colorPage.html");

// Retrieves the computed style property 'color' of linktext
String cssValue = driver.FindElement(By.Id("namedColor")).GetCssValue("background-color");

  {{< /tab >}}
  {{< tab header="Ruby" >}}

    # Navigate to Url
driver.get 'https://www.selenium.dev/selenium/web/colorPage.html'

    # Retrieves the computed style property 'color' of linktext
cssValue = driver.find_element(:id, 'namedColor').css_value('background-color')

  {{< /tab >}}
  {{< tab header="JavaScript" text=true >}}
  {{< gh-codeblock path="/examples/javascript/test/elements/information.spec.js#L76-L78">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}

// Navigate to Url
driver.get("https://www.selenium.dev/selenium/web/colorPage.html")

// Retrieves the computed style property 'color' of linktext
val cssValue = driver.findElement(By.id("namedColor")).getCssValue("background-color")

  {{< /tab >}}
{{< /tabpane >}}


## Coletar texto do elemento

Recupera o texto renderizado do elemento especificado.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/elements/InformationTest.java#L54-L57" >}}
{{< /tab >}}
  {{< tab header="Python" >}}
    # Navigate to url
driver.get("https://www.selenium.dev/selenium/web/linked_image.html")

    # Retrieves the text of the element
text = driver.find_element(By.ID, "justanotherlink").text
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// Navigate to url
driver.Url="https://www.selenium.dev/selenium/web/linked_image.html";

// Retrieves the text of the element
String text = driver.FindElement(By.Id("justanotherlink")).Text;
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    # Navigate to url
driver.get 'https://www.selenium.dev/selenium/web/linked_image.html'

    # Retrieves the text of the element
text = driver.find_element(:id, 'justanotherlink').text
  {{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/elements/information.spec.js#L84-L86">}}
{{< /tab >}}
  {{< tab header="Kotlin" >}}
// Navigate to URL
driver.get("https://www.selenium.dev/selenium/web/linked_image.html")

// retrieves the text of the element
val text = driver.findElement(By.id("justanotherlink")).getText()
  {{< /tab >}}
{{< /tabpane >}}

## Fetching Attributes or Properties

Fetches the run time value associated with a 
DOM attribute. It returns the data associated 
with the DOM attribute or property of the element. 

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/elements/InformationTest.java#L60-L65" >}}
{{< /tab >}}
  {{< tab header="Python" >}}
# Navigate to the url
driver.get("https://www.selenium.dev/selenium/web/inputs.html")

# Identify the email text box
email_txt = driver.find_element(By.NAME, "email_input")

# Fetch the value property associated with the textbox
value_info = email_txt.get_attribute("value")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
 //Navigate to the url
driver.Url="https://www.selenium.dev/selenium/web/inputs.html";

//identify the email text box
IWebElement emailTxt = driver.FindElement(By.Name(("email_input")));

//fetch the value property associated with the textbox
String valueInfo = eleSelLink.GetAttribute("value");
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Navigate to the url
driver.get("https://www.selenium.dev/selenium/web/inputs.html");

#identify the email text box
email_element=driver.find_element(name: 'email_input')

#fetch the value property associated with the textbox
emailVal = email_element.attribute("value");
  {{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/elements/information.spec.js#L55-L59">}}
{{< /tab >}}
  {{< tab header="Kotlin" >}}
// Navigate to URL
driver.get("https://www.selenium.dev/selenium/web/inputs.html")

//fetch the value property associated with the textbox
val attr = driver.findElement(By.name("email_input")).getAttribute("value")
  {{< /tab >}}
{{< /tabpane >}}
