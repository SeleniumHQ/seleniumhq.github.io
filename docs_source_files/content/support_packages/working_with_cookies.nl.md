---
title: "Working with cookies"
weight: 6
---

{{% notice info %}}
<i class="fas fa-language"></i> Page being translated from 
English to Dutch. Do you speak Dutch? Help us to translate
it by sending us pull requests!
{{% /notice %}}

A cookie is a small piece of data that is sent from a website and stored in your computer. 
Cookies are mostly used to recognise the user and load the stored information. 

WebDriver API provides a way to interact with cookies with built-in methods: 

## Add Cookie
It is used to add a cookie to the current browsing context. 
Add Cookie only accepts a set of defined serializable JSON object. <a href="https://w3c.github.io/webdriver/#cookies"> Here </a>
is the link to the list of accepted JSON key values

First of all, you need to be on the domain that the cookie will be
valid for. If you are trying to preset cookies before
you start interacting with a site and your homepage is large / takes a while to load
an alternative is to find a smaller page on the site (typically the 404 page is small, 
e.g. http://example.com/some404page)

{{< code-tab >}}
  {{< code-panel language="java" >}}
// Please raise a PR
  {{< / code-panel >}}
{{< code-panel language="python" >}}
# Please raise a PR
  {{< / code-panel >}}
{{< code-panel language="csharp" >}}
// Please raise a PR
  {{< / code-panel >}}
{{< code-panel language="ruby" >}}
  {{< / code-panel >}}
{{< code-panel language="javascript" >}}
const {Builder} = require('selenium-webdriver');
(async function example() {
    let driver = new Builder()
        .forBrowser('chrome')
        .build();

    await driver.get('https://www.example.com');

    // set a cookie on the current domain
    await driver.manage().addCookie({name:'key', value: 'value'});
})();
  {{< / code-panel >}}
{{< code-panel language="kotlin" >}}
// We don't have a Kotlin code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
{{< / code-tab >}}

## Get Named Cookie

It returns the serialized cookie data matching with the cookie name among all associated cookies.

{{< code-tab >}}
  {{< code-panel language="java" >}}
  // Please raise a PR
  {{< / code-panel >}}
 {{< code-panel language="python" >}}
# Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
#please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require('selenium-webdriver');
(async function example() {
    let driver = new Builder()
        .forBrowser('chrome')
        .build();

    await driver.get('https://www.example.com');

    // set a cookie on the current domain
    await driver.manage().addCookie({name:'foo', value: 'bar'});

    driver.manage().getCookie('foo').then(function (cookie) {
        console.log('cookie details => ', cookie);
    });
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// We don't have a Kotlin code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
{{< / code-tab >}}

## Get All Cookies

It returns a ‘successful serialized cookie data’ for current browsing context. 
If browser is no longer available it returns error. 

{{< code-tab >}}
  {{< code-panel language="java" >}}
  // Please raise a PR
  {{< / code-panel >}}
 {{< code-panel language="python" >}}
# Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
#please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require('selenium-webdriver');
(async function example() {
    let driver = new Builder()
        .forBrowser('chrome')
        .build();

    await driver.get('https://www.example.com');

    // Add few cookies
    await driver.manage().addCookie({name:'test1', value:'cookie1'});
    await driver.manage().addCookie({name:'test12', value:'cookie2'});

    // Get all Available cookies
    driver.manage().getCookies().then(function (cookies) {
        console.log('cookie details => ', cookies);
    });
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// We don't have a Kotlin code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
{{< / code-tab >}}


## Delete Cookie

It deletes the cookie data matching with the provided cookie name.

{{< code-tab >}}
  {{< code-panel language="java" >}}
  // Please raise a PR
  {{< / code-panel >}}
 {{< code-panel language="python" >}}
# Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
#please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require('selenium-webdriver');
(async function example() {
    let driver = new Builder()
        .forBrowser('chrome')
        .build();

    await driver.get('https://www.example.com');

    // Add few cookies
    await driver.manage().addCookie({name:'test1', value:'cookie1'});
    await driver.manage().addCookie({name:'test12', value:'cookie2'});

    // Delete a cookie bu name 'test1'
    await driver.manage().deleteCookie('test1');
    
    // Get all Available cookies
    driver.manage().getCookies().then(function (cookies) {
        console.log('cookie details => ', cookies);
    });
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// We don't have a Kotlin code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
{{< / code-tab >}}


## Delete All Cookies

It deletes all the cookies of the current browsing context.

{{< code-tab >}}
  {{< code-panel language="java" >}}
  // Please raise a PR
  {{< / code-panel >}}
 {{< code-panel language="python" >}}
# Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
#please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require('selenium-webdriver');
(async function example() {
    let driver = new Builder()
        .forBrowser('chrome')
        .build();

    await driver.get('https://www.example.com');

    // Add few cookies
    await driver.manage().addCookie({name:'test1', value:'cookie1'});
    await driver.manage().addCookie({name:'test12', value:'cookie2'});

    // Delete all cookies
    await driver.manage().deleteAllCookies();

    // Get all Available cookies
    driver.manage().getCookies().then(function (cookies) {
        console.log('cookie details => ', cookies);
    });
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// We don't have a Kotlin code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
{{< / code-tab >}}

