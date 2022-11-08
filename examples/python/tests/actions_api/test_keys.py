import sys

from selenium.webdriver import Keys, ActionChains
from selenium.webdriver.common.by import By


def test_key_down(driver):
    driver.get('https://selenium.dev/selenium/web/single_text_input.html')

    ActionChains(driver)\
        .key_down(Keys.SHIFT)\
        .send_keys("abc")\
        .perform()

    assert driver.find_element(By.ID, "textInput").get_attribute('value') == "ABC"


def test_key_up(driver):
    driver.get('https://selenium.dev/selenium/web/single_text_input.html')

    ActionChains(driver)\
        .key_down(Keys.SHIFT)\
        .send_keys("a")\
        .key_up(Keys.SHIFT)\
        .send_keys("b")\
        .perform()

    assert driver.find_element(By.ID, "textInput").get_attribute('value') == "Ab"


def test_send_keys_to_active_element(driver):
    driver.get('https://selenium.dev/selenium/web/single_text_input.html')

    ActionChains(driver)\
        .send_keys("abc")\
        .perform()

    assert driver.find_element(By.ID, "textInput").get_attribute('value') == "abc"


def test_send_keys_to_designated_element(driver):
    driver.get('https://selenium.dev/selenium/web/single_text_input.html')
    driver.find_element(By.TAG_NAME, "body").click()

    text_input = driver.find_element(By.ID, "textInput")
    ActionChains(driver)\
        .send_keys_to_element(text_input, "abc")\
        .perform()

    assert driver.find_element(By.ID, "textInput").get_attribute('value') == "abc"


def test_copy_and_paste(firefox_driver):
    driver = firefox_driver
    driver.get('https://selenium.dev/selenium/web/single_text_input.html')
    cmd_ctrl = Keys.COMMAND if sys.platform == 'darwin' else Keys.CONTROL

    ActionChains(driver)\
        .send_keys("Selenium!")\
        .send_keys(Keys.ARROW_LEFT)\
        .key_down(Keys.SHIFT)\
        .send_keys(Keys.ARROW_UP)\
        .key_up(Keys.SHIFT)\
        .key_down(cmd_ctrl)\
        .send_keys("xvv")\
        .key_up(cmd_ctrl)\
        .perform()

    assert driver.find_element(By.ID, "textInput").get_attribute('value') == "SeleniumSelenium!"
