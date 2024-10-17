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
    driver.get("https://www.selenium.dev/documentation/")

    driver.find_element(By.CSS_SELECTOR, "#td-sidebar-menu")

    driver.quit()

def test_find_by_id():
    driver = webdriver.Chrome()
    driver.get("https://www.selenium.dev/documentation/")

    driver.find_element(By.ID, "td-sidebar-menu")

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

def test_relative_locators_above():
    driver = webdriver.Chrome()
    driver.get("https://www.selenium.dev/selenium/web/inputs.html")

    email_input = driver.find_element(locate_with(By.TAG_NAME, "input").above({ By.NAME: "password_input" }))
    email_input.send_keys("test@test.com")

    driver.quit()

def test_relative_locators_below():
    driver = webdriver.Chrome()
    driver.get("https://www.selenium.dev/selenium/web/inputs.html")

    password_input = driver.find_element(locate_with(By.TAG_NAME, "input").below({ By.NAME: "email_input" }))
    password_input.send_keys("randompassword")

    driver.quit()

def test_relative_locators_to_the_left_of():
    driver = webdriver.Chrome()
    driver.get("https://www.selenium.dev/selenium/web/inputs.html")

    button = driver.find_element(locate_with(By.TAG_NAME, "input").to_left_of({ By.NAME: "submit_input" }))
    button.click()

    driver.quit()

def test_relative_locators_to_the_right_of():
    driver = webdriver.Chrome()
    driver.get("https://www.selenium.dev/selenium/web/inputs.html")

    button = driver.find_element(locate_with(By.TAG_NAME, "input").to_right_of({ By.NAME: "reset_input" }))
    button.click()

    driver.quit()

def test_relative_locators_near():
    driver = webdriver.Chrome()
    driver.get("https://www.selenium.dev/selenium/web/inputs.html")

    button = driver.find_element(locate_with(By.TAG_NAME, "input").near({ By.NAME: "week_input" }))
    button.send_keys('someweek')

    driver.quit()

def test_relative_locators_below_and_right_of():
    driver = webdriver.Chrome()
    driver.get("https://www.selenium.dev/selenium/web/inputs.html")

    button = driver.find_element(locate_with(By.TAG_NAME, "input").below({ By.NAME: "week_input" }).to_right_of({ By.NAME: "button_input" }))
    button.click()
    
    driver.quit()

