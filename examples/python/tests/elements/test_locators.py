from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.relative_locator import locate_with


def testAbove():
    driver = webdriver.Chrome()
    driver.get("https://www.selenium.dev/selenium/web/inputs.html")

    password_input = driver.find_element(By.NAME, "password-input")
    email = locate_with(By.TAG_NAME, "input").above(password_input)
    email.send_keys("test@test.com")
    
def testBelow():
    driver = webdriver.Chrome()
    driver.get("https://www.selenium.dev/selenium/web/inputs.html")

    password_input = driver.find_element(By.NAME, "password_input")
    search = locate_with(By.TAG_NAME, "input").below(password_input)
    search.send_keys("XXXXXXXXXXXXX")
    
def testNear():
    driver = webdriver.Chrome()
    driver.get("https://www.selenium.dev/selenium/web/inputs.html")

    week_input = driver.find_element(By.NAME, "week_input")
    button = locate_with(By.TAG_NAME, "input").near(week_input)
    button.click()
    
def testToTheLeftOf():
    driver = webdriver.Chrome()
    driver.get("https://www.selenium.dev/selenium/web/inputs.html")

    submit_input = driver.find_element(By.NAME, "submit_input")
    reset = locate_with(By.TAG_NAME, "input").to_the_left_of(submit_input)
    reset.click()
    
def testToTheRightOf():
    driver = webdriver.Chrome()
    driver.get("https://www.selenium.dev/selenium/web/inputs.html")

    reset_input = driver.find_element(By.NAME, "reset_input")
    submit = locate_with(By.TAG_NAME, "input").to_the_right_of(reset_input)
    submit.click()
    
def testAboveAndBelow():
    driver = webdriver.Chrome()
    driver.get("https://www.selenium.dev/selenium/web/inputs.html")

    password_input = driver.find_element(By.NAME, "password_input")
    number_input = driver.find_element(By.NAME, "number_input")
    email = locate_with(By.TAG_NAME, "input").above(password_input).below(number_input)
    email.send_keys("test@test.com")

