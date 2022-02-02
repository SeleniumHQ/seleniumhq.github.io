---
title: "Locate your locators - Python bindings changes upcoming"
linkTitle: "Locate Your Locators"
date: 2022-02-01
tags: ["webdriver", "python"]
categories: ["general"]
author: Josh Grant ([@joshin4colours](https://twitter.com/joshin4colours))
description: >
  Updating your locators for changes in the Python 
  bindings in Selenium 4
---

In real estate, the mantra for finding a new house or office space is
"location, location, location!". 
It could be said that when working with Selenium, a critical aspect of
writing tests is "locators, locators, locators!".
Having a robust locator strategy - in your app under test and in
your test framework - is highly important for effective testing.

If you are a Pythonista like myself and using Selenium for your test automation,
then there are some important changes coming to how locators are defined and used.

Sometime after Selenium 4.2, the Python Selenium bindings will remove
locator-specific methods for finding elements. This means that the methods

```python
driver.find_element_by_id("some_id")
driver.find_element_by_name("some_name")
driver.find_element_by_tag_name("some_tag")
driver.find_element_by_css_selector("some_selector")
driver.find_element_by_class_name("some_class")
driver.find_element_by_link_text("some_text")
driver.find_element_by_partial_link_text("some_other_text")
driver.find_element_by_xpath("some_xpath")
```
will be removed. All of these methods are in fact special cases of

```python
driver.find_element(By_object, "some_locator")
```

so this approach is now preferred (required, even) with the Python bindings.

Note that it's good practice to use the
[By object](https://www.selenium.dev/selenium/docs/api/py/webdriver/selenium.webdriver.common.by.html#module-selenium.webdriver.common.by)
which has specific values for using particular locator strategies. For example, this line

```python
driver.find_element_by_id("submit_button").click()
driver.find_element_by_css_selectors('.myelement child').text
```

becomes

```python
driver.find_element(By.ID, "submit_button").click()
driver.find_element(By.CSS_SELECTOR, '.myelement child').text
```

If you're really desperate however you can use strings instead of the By object:

```python
driver.find_element('id', "submit_button").click()
driver.find_element('css selector', '.myelement child').text
```

If you have any plans to upgrade your Selenium client for your Python tests
to recent versions of Selenium 4, definitely keep these changes in mind.
It's a good time to update your locator strategy and structure.

(This article was originally posted
[here](https://simplythetest.tumblr.com/post/674917293614039040/locate-your-locators-python-bindings-changes).
Thanks to the Selenium core contributors for adding this here!)
