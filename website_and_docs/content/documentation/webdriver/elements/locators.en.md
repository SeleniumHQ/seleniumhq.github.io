---
title: "Locator strategies"
linkTitle: "Locators"
weight: 1
aliases: [
"/documentation/webdriver/relative_locators/"
]
description: >
  Ways to identify one or more specific elements in the DOM.
---

A locator is a way to identify elements on a page. It is the argument passed to the
[Finding element]({{< ref "finders.md" >}}) methods.

Check out our [encouraged test practices]({{< ref "/documentation/test_practices/encouraged" >}}) for tips on
[locators]({{< ref "/documentation/test_practices/encouraged/locators.md" >}}), including which to use when and
why to declare locators separately from the finding methods.

## Traditional Locators

Selenium provides support for these 8 traditional location strategies in WebDriver:

| Locator           | Description |
|-------------------| ---------- |
| class name        | Locates elements whose class name contains the search value (compound class names are not permitted) |
| css selector      | Locates elements matching a CSS selector |
| id                | Locates elements whose ID attribute matches the search value |
| name              | Locates elements whose NAME attribute matches the search value |
| link text         | Locates anchor elements whose visible text matches the search value |
| partial link text | Locates anchor elements whose visible text contains the search value. If multiple elements are matching, only the first one will be selected. |
| tag name          | Locates elements whose tag name matches the search value |
| xpath             | Locates elements matching an XPath expression |

{{< alert-code >}}
of selecting elements using each locator strategy
{{< /alert-code >}}

## Relative Locators

**Selenium 4** introduces Relative Locators (previously
called as _Friendly Locators_). These locators are helpful when it is not easy to construct a locator for
the desired element, but easy to describe spatially where the element is in relation to an element that does have
an easily constructed locator.

### How it works

Selenium uses the JavaScript function
[getBoundingClientRect()](https://developer.mozilla.org/en-US/docs/Web/API/Element/getBoundingClientRect)
to determine the size and position of elements on the page, and can use this information to locate neighboring elements.  
find the relative elements. 

Relative locator methods can take as the argument for the point of origin, either a previously located element reference, 
or another locator. In these examples we'll be using locators only, but you could swap the locator in the final method with
an element object and it will work the same.

Let us consider the below example for understanding the relative locators.

{{< figure src="/images/documentation/webdriver/relative_locators.png" class="img-responsive text-center" alt="Relative Locators">}}

### Available relative locators

#### Above

If the email text field element is not easily identifiable for some reason, but the password text field element is,
we can locate the text field element using the fact that it is an "input" element "above" the password element. 

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
By emailLocator = RelativeLocator.with(By.tagName("input")).above(By.id("password"));
{{< /tab >}}
{{< tab header="Python" >}}
email_locator = locate_with(By.TAG_NAME, "input").above({By.ID: "password"})
{{< /tab >}}
{{< tab header="CSharp" >}}
var emailLocator = RelativeBy.WithLocator(By.TagName("input")).Above(By.Id("password"));
{{< /tab >}}
{{< tab header="Ruby" >}}
email_locator = {relative: {tag_name: 'input', above: {id: 'password'}}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
let emailLocator = locateWith(By.tagName('input')).above(By.id('password'));
{{< /tab >}}
{{< tab header="Kotlin" >}}
val emailLocator = RelativeLocator.with(By.tagName("input")).above(By.id("password"))
{{< /tab >}}
{{< /tabpane >}}

#### Below

If the password text field element is not easily identifiable for some reason, but the email text field element is,
we can locate the text field element using the fact that it is an "input" element "below" the email element.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
By passwordLocator = RelativeLocator.with(By.tagName("input")).below(By.id("email"));
{{< /tab >}}
{{< tab header="Python" >}}
password_locator = locate_with(By.TAG_NAME, "input").below({By.ID: "email"})
{{< /tab >}}
{{< tab header="CSharp" >}}
var passwordLocator = RelativeBy.WithLocator(By.TagName("input")).Below(By.Id("email"));
{{< /tab >}}
{{< tab header="Ruby" >}}
password_locator = {relative: {tag_name: 'input', below: {id: 'email'}}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
let passwordLocator = locateWith(By.tagName('input')).below(By.id('email'));
{{< /tab >}}
{{< tab header="Kotlin" >}}
val passwordLocator = RelativeLocator.with(By.tagName("input")).below(By.id("email"))
{{< /tab >}}
{{< /tabpane >}}

#### Left of

If the cancel button is not easily identifiable for some reason, but the submit button element is,
we can locate the cancel button element using the fact that it is a "button" element to the "left of" the submit element.
    
{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
By cancelLocator = RelativeLocator.with(By.tagName("button")).toLeftOf(By.id("submit"));
{{< /tab >}}
{{< tab header="Python" >}}
cancel_locator = locate_with(By.TAG_NAME, "button").to_left_of({By.ID: "submit"})
{{< /tab >}}
{{< tab header="CSharp" >}}
var cancelLocator = RelativeBy.WithLocator(By.tagName("button")).LeftOf(By.Id("submit"));
{{< /tab >}}
{{< tab header="Ruby" >}}
cancel_locator = {relative: {tag_name: 'button', left: {id: 'submit'}}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
let cancelLocator = locateWith(By.tagName('button')).toLeftOf(By.id('submit'));
{{< /tab >}}
{{< tab header="Kotlin" >}}
val cancelLocator = RelativeLocator.with(By.tagName("button")).toLeftOf(By.id("submit"))
{{< /tab >}}
{{< /tabpane >}}

#### Right of

If the submit button is not easily identifiable for some reason, but the cancel button element is,
we can locate the submit button element using the fact that it is a "button" element "to the right of" the cancel element.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
By submitLocator = RelativeLocator.with(By.tagName("button")).toRightOf(By.id("cancel"));
{{< /tab >}}
{{< tab header="Python" >}}
submit_locator = locate_with(By.TAG_NAME, "button").to_right_of({By.ID: "cancel"})
{{< /tab >}}
{{< tab header="CSharp" >}}
var submitLocator = RelativeBy.WithLocator(By.tagName("button")).RightOf(By.Id("cancel"));
{{< /tab >}}
{{< tab header="Ruby" >}}
submit_locator = {relative: {tag_name: 'button', right: {id: 'cancel'}}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
let submitLocator = locateWith(By.tagName('button')).toRightOf(By.id('cancel'));
{{< /tab >}}
{{< tab header="Kotlin" >}}
val submitLocator = RelativeLocator.with(By.tagName("button")).toRightOf(By.id("cancel"))
{{< /tab >}}
{{< /tabpane >}}

#### Near

If the relative positioning is not obvious, or it varies based on window size, you can use the near method to 
identify an element that is at most `50px` away from the provided locator.
One great use case for this is to work with a form element that doesn't have an easily constructed locator,
but its associated [input label element](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/label) does.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
By emailLocator = RelativeLocator.with(By.tagName("input")).near(By.id("lbl-email"));
{{< /tab >}}
{{< tab header="Python" >}}
email_locator = locate_with(By.TAG_NAME, "input").near({By.ID: "lbl-email"})
{{< /tab >}}
{{< tab header="CSharp" >}}
var emailLocator = RelativeBy.WithLocator(By.tagName("input")).Near(By.Id("lbl-email"));
{{< /tab >}}
{{< tab header="Ruby" >}}
email_locator = {relative: {tag_name: 'input', near: {id: 'lbl-email'}}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
let emailLocator = locateWith(By.tagName('input')).near(By.id('lbl-email'));
{{< /tab >}}
{{< tab header="Kotlin" >}}
val emailLocator = RelativeLocator.with(By.tagName("input")).near(By.id("lbl-email"));
{{< /tab >}}
{{< /tabpane >}}

### Chaining relative locators

You can also chain locators if needed. Sometimes the element is most easily identified as being both above/below one element and right/left of another.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
By submitLocator = RelativeLocator.with(By.tagName("button")).below(By.id("email")).toRightOf(By.id("cancel"));
{{< /tab >}}
{{< tab header="Python" >}}
submit_locator = locate_with(By.TAG_NAME, "button").below({By.ID: "email"}).to_right_of({By.ID: "cancel"})
{{< /tab >}}
{{< tab header="CSharp" >}}
var submitLocator = RelativeBy.WithLocator(By.tagName("button")).Below(By.Id("email")).RightOf(By.Id("cancel"));
{{< /tab >}}
{{< tab header="Ruby" >}}
submit_locator = {relative: {tag_name: 'button', below: {id: 'email'}, right: {id: 'cancel'}}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
let submitLocator = locateWith(By.tagName('button')).below(By.id('email')).toRightOf(By.id('cancel'));
{{< /tab >}}
{{< tab header="Kotlin" >}}
val submitLocator = RelativeLocator.with(By.tagName("button")).below(By.id("email")).toRightOf(By.id("cancel"))
{{< /tab >}}
{{< /tabpane >}}
