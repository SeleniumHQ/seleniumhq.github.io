import pytest
from selenium.webdriver.support.wait import WebDriverWait


@pytest.mark.driver_type("bidi")
def test_intercept_network_requests(driver):
    request_events = []

    def on_request(request):
        request_events.append(request)
        request.continue_request()

    driver.network.add_request_handler('before_request', on_request)

    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")

    wait = WebDriverWait(driver, 5)
    wait.until(lambda _: len(request_events) > 0)

    assert len(request_events) > 0


@pytest.mark.driver_type("bidi")
def test_intercept_network_responses(driver):
    response_events = []

    def on_response(request):
        response_events.append(request)
        request.continue_request()

    driver.network.add_request_handler('response_started', on_response)

    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")

    wait = WebDriverWait(driver, 5)
    wait.until(lambda _: len(response_events) > 0)

    assert len(response_events) > 0


@pytest.mark.driver_type("bidi")
def test_intercept_network_auth_required(driver):
    # This high-level API automatically handles auth
    driver.network.add_auth_handler("user", "pass")

    # Navigate to a URL that requires authentication
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")


@pytest.mark.driver_type("bidi")
def test_continue_response(driver):
    # This test demonstrates intercepting and continuing responses
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")

    def on_response(request):
        # High level API handles continuation via continue_request
        request.continue_request()

    driver.network.add_request_handler('response_started', on_response)

    driver.get("https://www.selenium.dev/selenium/web/iframes.html")


@pytest.mark.driver_type("bidi")
def test_continue_with_auth(driver):
    # High-level API version of adding auth handler
    driver.network.add_auth_handler("user", "pass")
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")
