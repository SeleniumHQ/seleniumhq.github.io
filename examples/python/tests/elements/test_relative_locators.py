from selenium.webdriver.common.by import By
from selenium.webdriver.support.relative_locator import locate_with


def test_below(driver):
    driver.get("https://www.selenium.dev/selenium/web/login.html")

    password_locator = locate_with(By.TAG_NAME, "input").below({By.ID: "username-field"})
    password_field = driver.find_element(password_locator)

    assert password_field.get_attribute("id") == "password-field"
