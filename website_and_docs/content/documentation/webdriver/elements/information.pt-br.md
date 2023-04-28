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
{{< tab header="Java" >}}
// Navigate to the url
driver.get("https://www.selenium.dev/selenium/web/inputs.html");

// Get boolean value for is element display
boolean isEmailVisible = driver.findElement(By.name("email_input")).isDisplayed();
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
{{< tab header="JavaScript" >}}
// Navigate to url
await driver.get("https://www.selenium.dev/selenium/web/inputs.html");

// Resolves Promise and returns boolean value
let result =  await driver.findElement(By.name("email_input")).isDisplayed();
{{< /tab >}}
{{< tab header="Kotlin" >}}
//navigates to url
 driver.get("https://www.selenium.dev/selenium/web/inputs.html")

 //returns true if element is displayed else returns false
 val flag = driver.findElement(By.name("email_input")).isDisplayed()
{{< /tab >}}
{{< /tabpane >}}

{{< alert-code >}}
for element displayedness
{{< /alert-code >}}


## Elemento está selecionado

Este método determina se o elemento referenciado
é _Selected_ ou não. Este método é amplamente utilizado em
caixas de seleção, botões de opção, elementos de entrada e elementos de
opção.

Retorna um valor booleano, **true** se o elemento referenciado for
**selected** no contexto de navegação atual, caso contrário, retorna
**false**.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
//navigates to url
driver.get("https://the-internet.herokuapp.com/checkboxes");

//returns true if element is checked else returns false
boolean value = driver.findElement(By.cssSelector("input[type='checkbox']:first-of-type")).isSelected();
{{< /tab >}}
{{< tab header="Python" >}}
# Navigate to url
driver.get("https://the-internet.herokuapp.com/checkboxes")

# Returns true if element is checked else returns false
value = driver.find_element(By.CSS_SELECTOR, "input[type='checkbox']:first-of-type").is_selected()
{{< /tab >}}
{{< tab header="CSharp" >}}
// Navigate to Url
driver.Navigate().GoToUrl("https://the-internet.herokuapp.com/checkboxes");

// Returns true if element ins checked else returns false
bool value = driver.FindElement(By.CssSelector("input[type='checkbox']:last-of-type")).Selected;
{{< /tab >}}
{{< tab header="Ruby" >}}
# Navigate to url
driver.get 'https://the-internet.herokuapp.com/checkboxes'

# Returns true if element is checked else returns false
ele = driver.find_element(css: "input[type='checkbox']:last-of-type").selected?
{{< /tab >}}
{{< tab header="JavaScript" >}}
// Navigate to url
await driver.get('https://the-internet.herokuapp.com/checkboxes');

// Returns true if element ins checked else returns false
let res = await driver.findElement(By.css("input[type='checkbox']:last-of-type")).isSelected();
{{< /tab >}}
{{< tab header="Kotlin" >}}
//navigates to url
driver.get("https://the-internet.herokuapp.com/checkboxes")

//returns true if element is checked else returns false
val attr =  driver.findElement(By.cssSelector("input[type='checkbox']:first-of-type")).isSelected()
{{< /tab >}}
{{< /tabpane >}}

## Coletar TagName do elemento

É usado para buscar o [TagName](https://www.w3.org/TR/webdriver/#dfn-get-element-tag-name)
do elemento referenciado que tem o foco no contexto de navegação atual.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
//navigates to url
driver.get("https://www.example.com");

//returns TagName of the element
String value = driver.findElement(By.cssSelector("h1")).getTagName();
{{< /tab >}}
{{< tab header="Python" >}}
# Navigate to url
driver.get("https://www.example.com")

# Returns TagName of the element
attr = driver.find_element(By.CSS_SELECTOR, "h1").tag_name
{{< /tab >}}
{{< tab header="CSharp" >}}
// Navigate to Url
driver.Navigate().GoToUrl("https://www.example.com");

// Returns TagName of the element
string attr = driver.FindElement(By.CssSelector("h1")).TagName;
{{< /tab >}}
{{< tab header="Ruby" >}}
# Navigate to url
driver.get 'https://www.example.com'

# Returns TagName of the element
attr = driver.find_element(css: "h1").tag_name
{{< /tab >}}
{{< tab header="JavaScript" >}}
// Navigate to URL
await driver.get('https://www.example.com');

// Returns TagName of the element
let value = await driver.findElement(By.css('h1')).getTagName();
{{< /tab >}}
{{< tab header="Kotlin" >}}
//navigates to url
driver.get("https://www.example.com")

//returns TagName of the element
val attr =  driver.findElement(By.cssSelector("h1")).getTagName()
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
{{< tab header="Java" >}}
// Navigate to url
driver.get("https://www.example.com");

// Returns height, width, x and y coordinates referenced element
Rectangle res =  driver.findElement(By.cssSelector("h1")).getRect();

// Rectangle class provides getX,getY, getWidth, getHeight methods
System.out.println(res.getX());
{{< /tab >}}
{{< tab header="Python" >}}
# Navigate to url
driver.get("https://www.example.com")

# Returns height, width, x and y coordinates referenced element
res = driver.find_element(By.CSS_SELECTOR, "h1").rect
{{< /tab >}}
{{< tab header="CSharp" >}}
// Navigate to Url
driver.Navigate().GoToUrl("https://example.com");

var res = driver.FindElement(By.CssSelector("h1"));
// Return x and y coordinates referenced element
System.Console.WriteLine(res.Location);
// Returns height, width
System.Console.WriteLine(res.Size);
{{< /tab >}}
{{< tab header="Ruby" >}}
# Navigate to url
driver.get 'https://www.example.com'

# Returns height, width, x and y coordinates referenced element
res = driver.find_element(css: "h1").rect
{{< /tab >}}
{{< tab header="JavaScript" >}}
// Navigate to url
await driver.get('https://www.example.com');

// Returns height, width, x and y coordinates referenced element
let element =  await driver.findElement(By.css("h1")).getRect();
{{< /tab >}}
{{< tab header="Kotlin" >}}
// Navigate to url
driver.get("https://www.example.com")

// Returns height, width, x and y coordinates referenced element
val res = driver.findElement(By.cssSelector("h1")).rect

// Rectangle class provides getX,getY, getWidth, getHeight methods
println(res.getX())
{{< /tab >}}
{{< /tabpane >}}

## Coletar valor CSS do elemento

Recupera o valor da propriedade de estilo computado especificada
de um elemento no contexto de navegação atual.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}

// Navigate to Url
driver.get("https://www.example.com");

// Retrieves the computed style property 'color' of linktext
String cssValue = driver.findElement(By.linkText("More information...")).getCssValue("color");

{{< /tab >}}
{{< tab header="Python" >}}

# Navigate to Url
driver.get('https://www.example.com')

# Retrieves the computed style property 'color' of linktext
cssValue = driver.find_element(By.LINK_TEXT, "More information...").value_of_css_property('color')

{{< /tab >}}
{{< tab header="CSharp" >}}

// Navigate to Url
driver.Navigate().GoToUrl("https://www.example.com");

// Retrieves the computed style property 'color' of linktext
String cssValue = driver.FindElement(By.LinkText("More information...")).GetCssValue("color");

{{< /tab >}}
{{< tab header="Ruby" >}}

# Navigate to Url
driver.get 'https://www.example.com'

# Retrieves the computed style property 'color' of linktext
cssValue = driver.find_element(:link_text, 'More information...').css_value('color')

{{< /tab >}}
{{< tab header="JavaScript" >}}

// Navigate to Url
await driver.get('https://www.example.com');

// Retrieves the computed style property 'color' of linktext
let cssValue = await driver.findElement(By.linkText("More information...")).getCssValue('color');

    {{< /tab >}}
{{< tab header="Kotlin" >}}

// Navigate to Url
driver.get("https://www.example.com")

// Retrieves the computed style property 'color' of linktext
val cssValue = driver.findElement(By.linkText("More information...")).getCssValue("color")

{{< /tab >}}
{{< /tabpane >}}

## Coletar texto do elemento

Recupera o texto renderizado do elemento especificado.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
// Navigate to url
driver.get("https://example.com");

// Retrieves the text of the element
String text = driver.findElement(By.cssSelector("h1")).getText();
{{< /tab >}}
{{< tab header="Python" >}}
# Navigate to url
driver.get("https://www.example.com")

# Retrieves the text of the element
text = driver.find_element(By.CSS_SELECTOR, "h1").text
{{< /tab >}}
{{< tab header="CSharp" >}}
// Navigate to url
driver.Url="https://example.com";

// Retrieves the text of the element
String text = driver.FindElement(By.CssSelector("h1")).Text;
{{< /tab >}}
{{< tab header="Ruby" >}}
# Navigate to url
driver.get 'https://www.example.com'

# Retrieves the text of the element
text = driver.find_element(:css, 'h1').text
{{< /tab >}}
{{< tab header="JavaScript" >}}
// Navigate to URL
await driver.get('http://www.example.com');

// retrieves the text of the element
let text = await driver.findElement(By.css('h1')).getText();
{{< /tab >}}
{{< tab header="Kotlin" >}}
// Navigate to URL
driver.get("https://www.example.com")

// retrieves the text of the element
val text = driver.findElement(By.cssSelector("h1")).getText()
{{< /tab >}}
{{< /tabpane >}}

## Fetching Attributes or Properties

Fetches the run time value associated with a 
DOM attribute. It returns the data associated 
with the DOM attribute or property of the element. 

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
//Navigate to the url
driver.get("https://www.selenium.dev/selenium/web/inputs.html");

//identify the email text box
WebElement emailTxt = driver.findElement(By.name(("email_input")));

//fetch the value property associated with the textbox
String valueInfo = eleSelLink.getAttribute("value");
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
    {{< tab header="JavaScript" >}}
// Navigate to the Url
await driver.get("https://www.selenium.dev/selenium/web/inputs.html");

// identify the email text box
const emailElement = await driver.findElements(By.xpath('//input[@name="email_input"]'));

//fetch the attribute "name" associated with the textbox
const nameAttribute = await emailElement.getAttribute("name");
    {{< /tab >}}
  {{< tab header="Kotlin" >}}
// Navigate to URL
driver.get("https://www.selenium.dev/selenium/web/inputs.html")

//fetch the value property associated with the textbox
val attr = driver.findElement(By.name("email_input")).getAttribute("value")
  {{< /tab >}}
{{< /tabpane >}}
