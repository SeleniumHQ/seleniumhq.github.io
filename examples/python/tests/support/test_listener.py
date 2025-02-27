import time
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.events import EventFiringWebDriver, AbstractEventListener

TEST_URL = "https://www.selenium.dev/selenium/web/clicks.html"

class MyListener(AbstractEventListener):
    def before_click(self, element, driver):
        print(f"Before click: {element.text}")
        assert element.text == "I'm a normal link"

    def after_navigate_to(self, url, driver):
        print(f"After navigating to: {url}")
        assert url == TEST_URL


def test_listener():
    driver = webdriver.Chrome()
    event_driver = EventFiringWebDriver(driver, MyListener())
    event_driver.get(TEST_URL)
    time.sleep(2)
    element = event_driver.find_element(By.ID, "normal")
    element.click()
    event_driver.quit()
