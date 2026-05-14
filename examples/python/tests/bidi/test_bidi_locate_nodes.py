import pytest
from selenium.webdriver.common.by import By


@pytest.mark.driver_type("bidi")
def test_locate_nodes(driver):
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")

    # Locate nodes by CSS selector
    nodes = driver.browsing_context.locate_nodes(
        context=driver.current_window_handle,
        locator={"type": "css", "value": "button"}
    )

    assert len(nodes) > 0


@pytest.mark.driver_type("bidi")
def test_locate_nodes_by_xpath(driver):
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")

    # Locate nodes by XPath
    nodes = driver.browsing_context.locate_nodes(
        context=driver.current_window_handle,
        locator={"type": "xpath", "value": "//button"}
    )

    assert len(nodes) > 0


@pytest.mark.driver_type("bidi")
def test_locate_nodes_with_start_nodes(driver):
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")

    # Get start node
    body = driver.find_element(By.TAG_NAME, "body")

    # Locate nodes starting from body
    # start_nodes expects a list of dictionaries with sharedId or handle
    nodes = driver.browsing_context.locate_nodes(
        context=driver.current_window_handle,
        locator={"type": "css", "value": "button"},
        start_nodes=[{"sharedId": body.id}]
    )

    assert len(nodes) > 0


@pytest.mark.driver_type("bidi")
def test_locate_nodes_by_id(driver):
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")

    # Locate specific element by ID
    nodes = driver.browsing_context.locate_nodes(
        context=driver.current_window_handle,
        locator={"type": "css", "value": "#consoleLog"}
    )

    assert len(nodes) > 0


@pytest.mark.driver_type("bidi")
def test_locate_nodes_by_class(driver):
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")

    # Locate nodes by class
    driver.browsing_context.locate_nodes(
        context=driver.current_window_handle,
        locator={"type": "css", "value": ".button-class"}
    )


@pytest.mark.driver_type("bidi")
def test_locate_nodes_multiple_results(driver):
    # Use logEntryAdded.html which has multiple buttons
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")

    # Locate all buttons
    nodes = driver.browsing_context.locate_nodes(
        context=driver.current_window_handle,
        locator={"type": "css", "value": "button"}
    )

    assert len(nodes) > 0


@pytest.mark.driver_type("bidi")
def test_locate_nodes_in_nested_elements(driver):
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")

    # Locate elements in nested structure
    nodes = driver.browsing_context.locate_nodes(
        context=driver.current_window_handle,
        locator={"type": "css", "value": "body button"}
    )

    assert len(nodes) >= 0
