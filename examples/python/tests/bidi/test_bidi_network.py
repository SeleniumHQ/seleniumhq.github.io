import pytest


@pytest.mark.driver_type("bidi")
def test_add_auth_handler(driver):
    driver.network.add_auth_handler("user", "pass")
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")


@pytest.mark.driver_type("bidi")
def test_add_then_remove_auth_handler(driver):
    handler_id = driver.network.add_auth_handler("user", "pass")

    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")

    driver.network.remove_auth_handler(handler_id)
