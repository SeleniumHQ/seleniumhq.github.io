from selenium.webdriver.common.by import By
from selenium.webdriver.support.events import AbstractEventListener, EventFiringWebDriver


class RecordingListener(AbstractEventListener):
    def __init__(self):
        self.events = []

    def after_navigate_to(self, url, driver):
        self.events.append(f"navigated to {url}")

    def before_click(self, element, driver):
        self.events.append(f"clicking {element.get_attribute('id')}")


def test_listener(driver):
    listener = RecordingListener()
    event_driver = EventFiringWebDriver(driver, listener)

    event_driver.get("https://www.selenium.dev/selenium/web/formPage.html")
    event_driver.find_element(By.ID, "checky").click()

    assert listener.events == [
        "navigated to https://www.selenium.dev/selenium/web/formPage.html",
        "clicking checky",
    ]
