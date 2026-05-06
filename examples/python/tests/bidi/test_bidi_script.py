import pytest
from selenium.webdriver.common.by import By
from selenium.webdriver.support.wait import WebDriverWait


@pytest.mark.driver_type("bidi")
def test_call_function(driver):
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")
    
    result = driver.script.call_function(
        "function(a, b) { return a + b; }",
        args=[{"type": "number", "value": 2}, {"type": "number", "value": 3}]
    )
    
    assert result.get("type") == "number"
    assert result.get("value") == 5


@pytest.mark.driver_type("bidi")
def test_evaluate_script(driver):
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")
    
    result = driver.script.evaluate("2 + 2")
    
    assert result.get("type") == "number"
    assert result.get("value") == 4


@pytest.mark.driver_type("bidi")
def test_disown_value(driver):
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")
    
    result = driver.script.evaluate("({x: 1})")
    handle = result.get("handle")
    
    # Disown the value
    driver.script.disown(handles=[handle])
    # If no exception is raised, disown was successful


@pytest.mark.driver_type("bidi")
def test_call_function_with_element_args(driver):
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")
    
    element = driver.find_element(By.ID, "consoleLog")
    
    result = driver.script.call_function(
        "function(elem) { return elem.tagName; }",
        args=[{"type": "HTMLElement", "handle": element}]
    )
    
    assert result.get("value") == "BUTTON"


@pytest.mark.driver_type("bidi")
def test_evaluate_with_realm(driver):
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")
    
    # Get realms
    realms = driver.script.get_realms()
    
    assert len(realms) > 0
    realm_id = realms[0].get("realm")
    
    # Evaluate in specific realm
    result = driver.script.evaluate("1 + 1", realm=realm_id)
    
    assert result.get("type") == "number"
    assert result.get("value") == 2


@pytest.mark.driver_type("bidi")
def test_add_dom_mutation_handler(driver):
    mutation_events = []
    
    def on_mutation(event):
        mutation_events.append(event)
    
    driver.script.add_dom_mutation_handler(on_mutation)
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")
    
    script = """
    const div = document.createElement('div');
    div.textContent = 'Hello';
    document.body.appendChild(div);
    """
    driver.execute_script(script)
    
    wait = WebDriverWait(driver, 5)
    wait.until(lambda _: len(mutation_events) > 0)
    
    assert len(mutation_events) > 0
