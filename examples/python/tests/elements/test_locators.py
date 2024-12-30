import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By


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
