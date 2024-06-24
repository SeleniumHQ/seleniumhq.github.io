import pytest
from selenium.webdriver.common.by import By
from selenium.webdriver.support.wait import WebDriverWait


@pytest.mark.driver_type("bidi")
def test_add_console_log_handler(driver):
    driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')
    log_entries = []

    driver.script.add_console_message_handler(log_entries.append)

    driver.find_element(By.ID, "consoleLog").click()
    WebDriverWait(driver, 5).until(lambda _: log_entries)
    assert log_entries[0].text == "Hello, world!"


@pytest.mark.driver_type("bidi")
def test_remove_console_log_handler(driver):
    driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')
    log_entries = []

    id = driver.script.add_console_message_handler(log_entries.append)
    driver.script.remove_console_message_handler(id)

    driver.find_element(By.ID, "consoleLog").click()
    assert len(log_entries) == 0


@pytest.mark.driver_type("bidi")
def test_add_js_exception_handler(driver):
    driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')
    log_entries = []

    driver.script.add_javascript_error_handler(log_entries.append)

    driver.find_element(By.ID, "jsException").click()
    WebDriverWait(driver, 5).until(lambda _: log_entries)
    assert log_entries[0].text == "Error: Not working"


@pytest.mark.driver_type("bidi")
def test_remove_js_exception_handler(driver):
    driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')
    log_entries = []

    id = driver.script.add_javascript_error_handler(log_entries.append)
    driver.script.remove_javascript_error_handler(id)

    driver.find_element(By.ID, "consoleLog").click()
    assert len(log_entries) == 0
