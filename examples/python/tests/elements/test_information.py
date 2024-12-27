from selenium import webdriver
from selenium.webdriver.common.by import By

import pytest


def test_informarion():
    # Initialize WebDriver
    driver = webdriver.Chrome()
    driver.implicitly_wait(0.5)

    driver.get("https://www.selenium.dev/selenium/web/inputs.html")

    # isDisplayed
    is_email_visible = driver.find_element(By.NAME, "email_input").is_displayed()
    assert is_email_visible == True

    # isEnabled
    is_enabled_button = driver.find_element(By.NAME, "button_input").is_enabled()
    assert is_enabled_button == True

    # isSelected
    is_selected_check = driver.find_element(By.NAME, "checkbox_input").is_selected()
    assert is_selected_check == True

    # TagName
    tag_name_inp = driver.find_element(By.NAME, "email_input").tag_name
    assert tag_name_inp == "input"

    # GetRect
    rect = driver.find_element(By.NAME, "range_input").rect
    assert rect["x"] == 10

    # CSS Value
    css_value = driver.find_element(By.NAME, "color_input").value_of_css_property(
        "font-size"
    )
    assert css_value == "13.3333px"

    # GetText
    text = driver.find_element(By.TAG_NAME, "h1").text
    assert text == "Testing Inputs"

    # FetchAttributes
    email_txt = driver.find_element(By.NAME, "email_input")
    value_info = email_txt.get_attribute("value")
    assert value_info == "admin@localhost"
