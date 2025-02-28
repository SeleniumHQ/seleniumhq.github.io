from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.events import EventFiringWebDriver, AbstractEventListener


class MyListener(AbstractEventListener):
    def __init__(self):
        self.events = []

    def before_navigate_to(self, url: str, driver) -> None:
        print(f"before_navigate_to: {url}")
        self.events.append(f"before_navigate_to")

    def after_navigate_to(self, url: str, driver) -> None:
        print(f"after_navigate_to: {url}")
        self.events.append(f"after_navigate_to")

    def before_navigate_back(self, driver) -> None:
        print("before_navigate_back")
        self.events.append("before_navigate_back")

    def after_navigate_back(self, driver) -> None:
        print("after_navigate_back")
        self.events.append("after_navigate_back")

    def before_navigate_forward(self, driver) -> None:
        print("before_navigate_forward")
        self.events.append("before_navigate_forward")

    def after_navigate_forward(self, driver) -> None:
        print("after_navigate_forward")
        self.events.append("after_navigate_forward")

    def before_close(self, driver) -> None:
        print("before_close")
        self.events.append("before_close")

    def after_close(self, driver) -> None:
        print("after_close")
        self.events.append("after_close")

    def before_quit(self, driver) -> None:
        print("before_quit")
        self.events.append("before_quit")

    def after_quit(self, driver) -> None:
        print("after_quit")
        self.events.append("after_quit")

    def before_find(self, by, value, driver) -> None:
        print("before_find")
        self.events.append("before_find")

    def after_find(self, by, value, driver) -> None:
        print("after_find")
        self.events.append("after_find")

    def before_click(self, element, driver) -> None:
        print("before_click")
        self.events.append("before_click")

    def after_click(self, element, driver) -> None:
        print("after_click")
        self.events.append("after_click")

    def before_change_value_of(self, element, driver) -> None:
        print("before_change_value")
        self.events.append("before_change_value")

    def after_change_value_of(self, element, driver) -> None:
        print("after_change_value")
        self.events.append("after_change_value")

    def before_execute_script(self, script, driver) -> None:
        print("before_execute_script")
        self.events.append("before_execute_script")

    def after_execute_script(self, script, driver) -> None:
        print("after_execute_script")
        self.events.append("after_execute_script")

    def on_exception(self, exception, driver) -> None:
        print("on_exception")
        self.events.append("on_exception")

def test_navigate_and_quit():
    driver = webdriver.Chrome()
    listener = MyListener()
    event_driver = EventFiringWebDriver(driver, listener)
    event_driver.get("https://www.selenium.dev/selenium/web/")
    event_driver.get("https://www.selenium.dev/selenium/web/clicks.html")
    event_driver.back()
    event_driver.forward()
    event_driver.quit()
    print(listener.events)

    assert "before_navigate_to" in listener.events
    assert "after_navigate_to" in listener.events
    assert "before_navigate_back" in listener.events
    assert "after_navigate_back" in listener.events
    assert "before_navigate_forward" in listener.events
    assert "after_navigate_forward" in listener.events
    assert "before_quit" in listener.events
    assert "after_quit" in listener.events

def test_find_and_click_and_close():
    driver = webdriver.Chrome()
    listener = MyListener()
    event_driver = EventFiringWebDriver(driver, listener)
    event_driver.get("https://www.selenium.dev/selenium/web/clicks.html")
    element = event_driver.find_element(By.ID, "new-window")

    assert 'before_find' in listener.events
    assert 'after_find' in listener.events

    element.click()

    assert 'before_click' in listener.events
    assert 'after_click' in listener.events

    event_driver.switch_to.window(event_driver.window_handles[-1])
    event_driver.close()

    assert 'before_close' in listener.events

    event_driver.switch_to.window(event_driver.window_handles[0])
    print(listener.events)

def test_change_value():
    driver = webdriver.Chrome()
    listener = MyListener()
    event_driver = EventFiringWebDriver(driver, listener)
    event_driver.get("https://www.selenium.dev/selenium/web/inputs.html")
    element = event_driver.find_element(By.NAME, "no_type")
    print(listener.events)
    element.clear()

    assert 'before_change_value' in listener.events
    assert 'after_change_value' in listener.events

def test_execute_script_and_exception():
    driver = webdriver.Chrome()
    listener = MyListener()
    event_driver = EventFiringWebDriver(driver, listener)
    urls = ["https://www.selenium.dev/selenium/web/",
            "https://www.selenium.dev/selenium/web/inputs.html"]
    for url in urls:
        event_driver.get(url)
        try:
            element = event_driver.find_element(By.NAME, "no_type")
            print(listener.events)
            event_driver.execute_script("arguments[0].value = 'test'", element)
        except Exception as e:
            print(f"Exception: {e}")
            continue

    assert 'before_execute_script' in listener.events
    assert 'after_execute_script' in listener.events
