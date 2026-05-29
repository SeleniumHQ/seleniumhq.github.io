import pytest
from selenium.webdriver.common.by import By
from selenium.webdriver.common.action_chains import ActionChains


@pytest.mark.driver_type("bidi")
def test_input_keyboard_actions(driver):
    driver.get("https://www.selenium.dev/selenium/web/single_text_input.html")

    input_field = driver.find_element(By.ID, "textInput")
    input_field.send_keys("Hello World")

    assert input_field.get_attribute("value") == "Hello World"


@pytest.mark.driver_type("bidi")
def test_input_mouse_click(driver):
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")

    button = driver.find_element(By.ID, "consoleLog")
    button.click()

    assert button is not None


@pytest.mark.driver_type("bidi")
def test_dispatch_keyboard_events(driver):
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")

    driver.execute_script("""
        document.addEventListener('keydown', function(e) {
            console.log('Key pressed: ' + e.key);
        });
    """)

    body = driver.find_element(By.TAG_NAME, "body")
    body.send_keys("a")


@pytest.mark.driver_type("bidi")
def test_dispatch_mouse_events(driver):
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")

    button = driver.find_element(By.ID, "consoleLog")

    driver.execute_script("""
        arguments[0].addEventListener('mouseover', function(e) {
            console.log('Mouse over');
        });
    """, button)

    actions = ActionChains(driver)
    actions.move_to_element(button).perform()


@pytest.mark.driver_type("bidi")
def test_double_click(driver):
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")

    element = driver.find_element(By.TAG_NAME, "body")

    actions = ActionChains(driver)
    actions.double_click(element).perform()


@pytest.mark.driver_type("bidi")
def test_right_click(driver):
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")

    element = driver.find_element(By.TAG_NAME, "body")

    actions = ActionChains(driver)
    actions.context_click(element).perform()
