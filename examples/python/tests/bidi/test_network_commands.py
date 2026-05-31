import pytest
from selenium.common.exceptions import TimeoutException
from selenium.webdriver.support.wait import WebDriverWait


@pytest.mark.driver_type("bidi")
def test_add_intercept(driver):
    requests = []

    def capture_request(request):
        requests.append(request.request_id)
        request.continue_request()

    callback_id = driver.network.add_request_handler("before_request", capture_request)
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")

    WebDriverWait(driver, 5).until(lambda _: requests)
    assert callback_id is not None


@pytest.mark.driver_type("bidi")
def test_remove_intercept(driver):
    requests = []

    def capture_request(request):
        requests.append(request.request_id)
        request.continue_request()

    callback_id = driver.network.add_request_handler("before_request", capture_request)
    driver.network.remove_request_handler("before_request", callback_id)

    driver.get("https://www.selenium.dev/selenium/web/blank.html")
    assert not requests


@pytest.mark.driver_type("bidi")
def test_fail_request(driver):
    blocked_requests = []

    def fail_request(request):
        blocked_requests.append(request.request_id)
        request.fail_request()

    driver.network.add_request_handler("before_request", fail_request)
    driver.set_page_load_timeout(5)
    with pytest.raises(TimeoutException):
        driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")
    WebDriverWait(driver, 5).until(lambda _: blocked_requests)


@pytest.mark.driver_type("bidi")
def test_add_and_remove_request_handler(driver):
    requests = []

    def capture_request(request):
        requests.append(request.request_id)
        request.continue_request()

    callback_id = driver.network.add_request_handler("before_request", capture_request)
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")
    WebDriverWait(driver, 5).until(lambda _: requests)

    requests.clear()
    driver.network.remove_request_handler("before_request", callback_id)

    driver.get("https://www.selenium.dev/selenium/web/blank.html")
    assert not requests
