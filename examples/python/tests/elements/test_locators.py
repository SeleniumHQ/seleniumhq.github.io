from pathlib import Path

import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.relative_locator import locate_with


def test_class_name():
    driver = webdriver.Chrome()
    driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html")
    element = driver.find_element(By.CLASS_NAME, "information")

    assert element is not None
    assert element.tag_name == "input"

    driver.quit()

def test_css_selector(driver):
    driver = webdriver.Chrome()
    driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html")
    element = driver.find_element(By.CSS_SELECTOR, "#fname")

    assert element is not None
    assert element.get_attribute("value") == "Jane"

    driver.quit()

def test_id(driver):
    driver = webdriver.Chrome()
    driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html")
    element = driver.find_element(By.ID, "lname")

    assert element is not None
    assert element.get_attribute("value") == "Doe"

    driver.quit()

def test_name(driver):
    driver = webdriver.Chrome()
    driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html")
    element = driver.find_element(By.NAME, "newsletter")

    assert element is not None
    assert element.tag_name == "input"

    driver.quit()

def test_link_text(driver):
    driver = webdriver.Chrome()
    driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html")
    element = driver.find_element(By.LINK_TEXT, "Selenium Official Page")

    assert element is not None
    assert element.get_attribute("href") == "https://www.selenium.dev/"

    driver.quit()

def test_partial_link_text(driver):
    driver = webdriver.Chrome()
    driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html")
    element = driver.find_element(By.PARTIAL_LINK_TEXT, "Official Page")

    assert element is not None
    assert element.get_attribute("href") == "https://www.selenium.dev/"

    driver.quit()

def test_tag_name(driver):
    driver = webdriver.Chrome()
    driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html")
    element = driver.find_element(By.TAG_NAME, "a")

    assert element is not None
    assert element.get_attribute("href") == "https://www.selenium.dev/"

    driver.quit()

def test_xpath(driver):
    driver = webdriver.Chrome()
    driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html")
    element = driver.find_element(By.XPATH, "//input[@value='f']")

    assert element is not None
    assert element.get_attribute("type") == "radio"

    driver.quit()


RELATIVE_LOCATORS_PAGE = (Path(__file__).parent.parent / "resources" / "relative_locators.html").as_uri()


def test_relative_above(driver):
    driver.get(RELATIVE_LOCATORS_PAGE)
    email_locator = locate_with(By.TAG_NAME, "input").above({By.ID: "password"})
    email = driver.find_element(email_locator)

    assert email.get_attribute("id") == "email"


def test_relative_below(driver):
    driver.get(RELATIVE_LOCATORS_PAGE)
    password_locator = locate_with(By.TAG_NAME, "input").below({By.ID: "email"})
    password = driver.find_element(password_locator)

    assert password.get_attribute("id") == "password"


def test_relative_to_left_of(driver):
    driver.get(RELATIVE_LOCATORS_PAGE)
    cancel_locator = locate_with(By.TAG_NAME, "button").to_left_of({By.ID: "submit"})
    cancel = driver.find_element(cancel_locator)

    assert cancel.get_attribute("id") == "cancel"


def test_relative_to_right_of(driver):
    driver.get(RELATIVE_LOCATORS_PAGE)
    submit_locator = locate_with(By.TAG_NAME, "button").to_right_of({By.ID: "cancel"})
    submit = driver.find_element(submit_locator)

    assert submit.get_attribute("id") == "submit"


def test_relative_near(driver):
    driver.get(RELATIVE_LOCATORS_PAGE)
    email_locator = locate_with(By.TAG_NAME, "input").near({By.ID: "lbl-email"})
    email = driver.find_element(email_locator)

    assert email.get_attribute("id") == "email"


def test_relative_chaining(driver):
    driver.get(RELATIVE_LOCATORS_PAGE)
    submit_locator = locate_with(By.TAG_NAME, "button").below({By.ID: "email"}).to_right_of({By.ID: "cancel"})
    submit = driver.find_element(submit_locator)

    assert submit.get_attribute("id") == "submit"
