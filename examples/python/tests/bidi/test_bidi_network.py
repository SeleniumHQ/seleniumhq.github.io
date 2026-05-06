import pytest
from selenium.webdriver.common.by import By
from selenium.webdriver.support.wait import WebDriverWait


@pytest.mark.driver_type("bidi")
def test_intercept_network_requests(driver):
    request_events = []
    
    def on_request(event):
        request_events.append(event)
    
    driver.bidi_connection.add_network_request_listener(on_request)
    
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")
    
    wait = WebDriverWait(driver, 5)
    wait.until(lambda _: len(request_events) > 0)
    
    assert len(request_events) > 0


@pytest.mark.driver_type("bidi")
def test_intercept_network_responses(driver):
    response_events = []
    
    def on_response(event):
        response_events.append(event)
    
    driver.bidi_connection.add_network_response_listener(on_response)
    
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")
    
    wait = WebDriverWait(driver, 5)
    wait.until(lambda _: len(response_events) > 0)
    
    assert len(response_events) > 0


@pytest.mark.driver_type("bidi")
def test_intercept_network_auth_required(driver):
    auth_events = []
    
    def on_auth_required(event):
        auth_events.append(event)
    
    driver.bidi_connection.add_auth_required_listener(on_auth_required)
    
    # Navigate to a URL that requires authentication
    # This is a placeholder - actual auth required event would need a protected resource
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")


@pytest.mark.driver_type("bidi")
def test_continue_response(driver):
    # This test demonstrates intercepting and continuing responses
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")
    
    def on_response(event):
        request_id = event.get("request", {}).get("request")
        # Continue the response
        driver.bidi_connection.bidi_session.network.continue_response(request=request_id)
    
    driver.bidi_connection.add_network_response_listener(on_response)
    
    driver.get("https://www.selenium.dev/selenium/web/iframes.html")


@pytest.mark.driver_type("bidi")
def test_continue_with_auth(driver):
    def on_auth_required(event):
        # Provide credentials
        driver.bidi_connection.bidi_session.network.provide_response_body(
            request=event.get("request", {}).get("request"),
            username="user",
            password="pass"
        )
    
    driver.bidi_connection.add_auth_required_listener(on_auth_required)
