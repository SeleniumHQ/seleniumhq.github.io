import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.relative_locator import locate_with

def test_find_by_classname():
    driver = webdriver.Chrome()
    driver.get("https://www.selenium.dev/")

    driver.find_element(By.CLASS_NAME, "td-home")

    driver.quit()

def test_find_by_css_selector():
    driver = webdriver.Chrome()
    driver.get("https://www.selenium.dev/")

    driver.find_element(By.CSS_SELECTOR, "#announcement-banner")

    driver.quit()

def test_find_by_id():
    driver = webdriver.Chrome()
    driver.get("https://www.selenium.dev/")

    driver.find_element(By.ID, "announcement-banner")

    driver.quit()

def test_find_by_name():
    driver = webdriver.Chrome()
    driver.get("https://www.selenium.dev/selenium/web/formPage.html")

    driver.find_element(By.NAME, "image")

    driver.quit()

def test_find_by_link_text():
    driver = webdriver.Chrome()
    driver.get("https://www.selenium.dev/")

    driver.find_element(By.LINK_TEXT, "Documentation")

    driver.quit()

def test_find_by_partial_link_text():
    driver = webdriver.Chrome()
    driver.get("https://www.selenium.dev/documentation/")

    driver.find_element(By.PARTIAL_LINK_TEXT, "Selenium script")

    driver.quit()

def test_find_by_tag_name():
    driver = webdriver.Chrome()
    driver.get("https://www.selenium.dev/")

    driver.find_element(By.TAG_NAME, "nav")

    driver.quit()

def test_find_by_xpath():
    driver = webdriver.Chrome()
    driver.get("https://www.selenium.dev/")

    driver.find_element(By.XPATH, "//a[@class=\"navbar-brand\"]")

    driver.quit()

pytest.mark.skip(reason='the examples are tied to an image with an example, on the site')
def find_by_relative_locators():
    driver = webdriver.Chrome()
    driver.get("https://www.selenium.dev/")

    email_locator = locate_with(By.TAG_NAME, "input").above({By.ID: "password"})

    password_locator = locate_with(By.TAG_NAME, "input").below({By.ID: "email"})

    cancel_locator = locate_with(By.TAG_NAME, "button").to_left_of({By.ID: "submit"})

    submit_locator = locate_with(By.TAG_NAME, "button").to_right_of({By.ID: "cancel"})

    email_locator = locate_with(By.TAG_NAME, "input").near({By.ID: "lbl-email"})

    submit_locator = locate_with(By.TAG_NAME, "button").below({By.ID: "email"}).to_right_of({By.ID: "cancel"})


# def test_find_by_relative_locators():
#     driver = webdriver.Chrome()
#     driver.get("https://www.selenium.dev/selenium/web/formPage.html")

#     locate_with(By.TAG_NAME, "input").above({ By.ID: "checkedchecky" })

#     locate_with(By.TAG_NAME, "input").below({ By.ID: "checkedchecky" })

#     locate_with(By.TAG_NAME, "select").to_left_of({ By.ID: "multi" })

#     locate_with(By.TAG_NAME, "select").to_right_of({ By.NAME: "no-select" })

#     locate_with(By.TAG_NAME, "p").near({By.ID: "lone_disabled_selected_radio"})

#     locate_with(By.TAG_NAME, "select").to_right_of({ By.NAME: "no-select" }).below({ By.TAG_NAME: "form" })

#     driver.quit()

