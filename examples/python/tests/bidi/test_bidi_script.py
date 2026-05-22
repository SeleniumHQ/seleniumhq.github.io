import pytest
from selenium.webdriver.common.by import By
from selenium.webdriver.support.wait import WebDriverWait


@pytest.mark.driver_type("bidi")
def test_call_function(driver):
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")

    # In newer Selenium versions, these are public
    # Using public names for documentation purposes
    result = driver.script.call_function(
        "function(a, b) { return a + b; }",
        arguments=[{"type": "number", "value": 2}, {"type": "number", "value": 3}],
        await_promise=True,
        target={"context": driver.current_window_handle}
    )

    assert result['result']['type'] == "number"
    assert result['result']['value'] == 5


@pytest.mark.driver_type("bidi")
def test_evaluate_script(driver):
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")

    result = driver.script.evaluate(
        "2 + 2",
        await_promise=True,
        target={"context": driver.current_window_handle}
    )

    assert result['result']['type'] == "number"
    assert result['result']['value'] == 4


@pytest.mark.driver_type("bidi")
def test_disown_value(driver):
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")

    result = driver.script.evaluate(
        "({x: 1})",
        await_promise=True,
        target={"context": driver.current_window_handle},
        result_ownership="root"
    )
    handle = result['result']['handle']

    # Disown the value
    driver.script.disown(
        handles=[handle],
        target={"context": driver.current_window_handle}
    )
    # If no exception is raised, disown was successful


@pytest.mark.driver_type("bidi")
def test_call_function_with_element_args(driver):
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")

    element = driver.find_element(By.ID, "consoleLog")

    result = driver.script.call_function(
        "function(elem) { return elem.tagName; }",
        arguments=[{"type": "node", "sharedId": element.id}],
        await_promise=True,
        target={"context": driver.current_window_handle}
    )

    assert result['result']['value'] == "BUTTON"


@pytest.mark.driver_type("bidi")
def test_evaluate_with_realm(driver):
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")

    # Get realms
    realms_result = driver.script.get_realms()
    realms = realms_result['realms']

    assert len(realms) > 0
    realm_id = realms[0]['realm']

    # Evaluate in specific realm
    result = driver.script.evaluate(
        "1 + 1",
        await_promise=True,
        target={"realm": realm_id}
    )

    assert result['result']['type'] == "number"
    assert result['result']['value'] == 2


@pytest.mark.driver_type("bidi")
def test_add_dom_mutation_handler(driver):
    mutation_events = []

    def on_mutation(event):
        mutation_events.append(event)

    driver.script.add_dom_mutation_handler(on_mutation)
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")

    # Mutate an attribute to trigger the handler
    script = """
    document.getElementById('consoleLog').setAttribute('data-test', 'value');
    """
    driver.execute_script(script)

    wait = WebDriverWait(driver, 5)
    wait.until(lambda _: len(mutation_events) > 0)

    assert len(mutation_events) > 0
