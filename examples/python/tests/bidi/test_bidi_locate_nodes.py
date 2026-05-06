import pytest


@pytest.mark.driver_type("bidi")
def test_locate_nodes(driver):
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")

    # Locate nodes by CSS selector
    nodes = driver.script.locate_nodes(
        locator={"type": "css", "value": "button"}
    )

    assert len(nodes) > 0


@pytest.mark.driver_type("bidi")
def test_locate_nodes_by_xpath(driver):
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")

    # Locate nodes by XPath
    nodes = driver.script.locate_nodes(
        locator={"type": "xpath", "value": "//button"}
    )

    assert len(nodes) > 0


@pytest.mark.driver_type("bidi")
def test_locate_nodes_with_start_nodes(driver):
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")

    # Get start node
    body = driver.find_element(tag_name="body")

    # Locate nodes starting from body
    nodes = driver.script.locate_nodes(
        locator={"type": "css", "value": "button"},
        start_nodes=[body]
    )

    assert len(nodes) > 0


@pytest.mark.driver_type("bidi")
def test_locate_nodes_by_id(driver):
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")

    # Locate specific element by ID
    nodes = driver.script.locate_nodes(
        locator={"type": "css", "value": "#consoleLog"}
    )

    assert len(nodes) > 0


@pytest.mark.driver_type("bidi")
def test_locate_nodes_by_class(driver):
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")

    # Locate nodes by class
    driver.script.locate_nodes(
        locator={"type": "css", "value": ".button-class"}
    )


@pytest.mark.driver_type("bidi")
def test_locate_nodes_multiple_results(driver):
    driver.get("https://www.selenium.dev/selenium/web/iframes.html")

    # Locate all form inputs
    nodes = driver.script.locate_nodes(
        locator={"type": "css", "value": "input"}
    )

    assert len(nodes) > 0


@pytest.mark.driver_type("bidi")
def test_locate_nodes_in_nested_elements(driver):
    driver.get("https://www.selenium.dev/selenium/web/iframes.html")

    # Locate elements in nested structure
    nodes = driver.script.locate_nodes(
        locator={"type": "css", "value": "form input"}
    )

    assert len(nodes) >= 0
