import pytest
from selenium.webdriver.common.by import By
from selenium.webdriver.support.wait import WebDriverWait


@pytest.mark.driver_type("bidi")
def test_add_console_message_handler(driver):
    driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')
    log_entries = []

    driver.script.add_console_message_handler(log_entries.append)

    driver.find_element(By.ID, 'consoleLog').click()
    WebDriverWait(driver, 5).until(lambda _: log_entries)
    assert log_entries[0].text == 'Hello, world!'


@pytest.mark.driver_type("bidi")
def test_remove_console_message_handler(driver):
    driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')
    log_entries = []

    handler_id = driver.script.add_console_message_handler(log_entries.append)
    driver.script.remove_console_message_handler(handler_id)

    driver.find_element(By.ID, 'consoleLog').click()
    assert len(log_entries) == 0


@pytest.mark.driver_type("bidi")
def test_add_javascript_error_handler(driver):
    driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')
    log_entries = []

    driver.script.add_javascript_error_handler(log_entries.append)

    driver.find_element(By.ID, 'jsException').click()
    WebDriverWait(driver, 5).until(lambda _: log_entries)
    assert 'Error: Not working' in log_entries[0].text


@pytest.mark.driver_type("bidi")
def test_remove_javascript_error_handler(driver):
    driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')
    log_entries = []

    handler_id = driver.script.add_javascript_error_handler(log_entries.append)
    driver.script.remove_javascript_error_handler(handler_id)

    driver.find_element(By.ID, 'jsException').click()
    assert len(log_entries) == 0


@pytest.mark.driver_type("bidi")
def test_pin_script(driver):
    driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')

    script_id = driver.script.pin('() => document.title')
    assert script_id is not None

    driver.script.unpin(script_id)
