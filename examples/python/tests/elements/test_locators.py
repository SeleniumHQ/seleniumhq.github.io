import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By

@pytest.fixture
def driver(html_server):
    """
    Initialize the WebDriver and navigate to the elements/locators.html page.
    """
    driver = webdriver.Chrome()
    driver.implicitly_wait(0.5)
    driver.get(f"{html_server}/elements/locators.html")
    yield driver
    driver.quit()

def test_class_name(driver):
    element = driver.find_element(By.CLASS_NAME, "information")
    assert element is not None
    assert element.tag_name == "input"

def test_css_selector(driver):
    element = driver.find_element(By.CSS_SELECTOR, "#fname")
    assert element is not None
    assert element.get_attribute("value") == "Jane"

def test_id(driver):
    element = driver.find_element(By.ID, "lname")
    assert element is not None
    assert element.get_attribute("value") == "Doe"

def test_name(driver):
    element = driver.find_element(By.NAME, "newsletter")
    assert element is not None
    assert element.tag_name == "input"

def test_link_text(driver):
    element = driver.find_element(By.LINK_TEXT, "Selenium Official Page")
    assert element is not None
    assert element.get_attribute("href") == "https://www.selenium.dev/"

def test_partial_link_text(driver):
    element = driver.find_element(By.PARTIAL_LINK_TEXT, "Official Page")
    assert element is not None
    assert element.get_attribute("href") == "https://www.selenium.dev/"

def test_tag_name(driver):
    element = driver.find_element(By.TAG_NAME, "a")
    assert element is not None
    assert element.get_attribute("href") == "https://www.selenium.dev/"

def test_xpath(driver):
    element = driver.find_element(By.XPATH, "//input[@value='f']")
    assert element is not None
    assert element.get_attribute("type") == "radio"
