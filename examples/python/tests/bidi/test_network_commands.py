# Licensed to the Software Freedom Conservancy (SFC) under one
# or more contributor license agreements. See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership. The SFC licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License. You may obtain a copy of the License at
#
#   http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied. See the License for the
# specific language governing permissions and limitations
# under the License.

import pytest
from selenium.common.exceptions import WebDriverException
from selenium.webdriver.common.by import By


@pytest.mark.driver_type("bidi")
def test_add_intercept(driver):
    # _add_intercept is currently the available Python API for BiDi network intercepts.
    # This will be updated when a public API is stabilized.
    intercept = driver.network._add_intercept()
    assert intercept is not None
    driver.network._remove_intercept(intercept["intercept"])


@pytest.mark.driver_type("bidi")
def test_remove_intercept(driver):
    # _add_intercept/_remove_intercept are currently the available Python APIs.
    # These will be updated when a public API is stabilized.
    intercept = driver.network._add_intercept()
    driver.network._remove_intercept(intercept["intercept"])
    assert driver.network.intercepts == []


@pytest.mark.driver_type("bidi")
def test_fail_request(driver):
    from selenium.webdriver.common.bidi.browsing_context import ReadinessState
    from selenium.webdriver.common.bidi.network import Request

    def block_request(request: Request):
        request.fail()

    driver.network.add_request_handler(["**/blank.html"], block_request)

    try:
        with pytest.raises(WebDriverException):
            driver.browsing_context.navigate(
                context=driver.current_window_handle,
                url="https://www.selenium.dev/selenium/web/blank.html",
                wait=ReadinessState.COMPLETE,
            )
    finally:
        driver.network.clear_request_handlers()


@pytest.mark.driver_type("bidi")
def test_add_and_remove_request_handler(driver):
    from selenium.webdriver.common.bidi.network import Request

    requests = []

    def callback(request: Request):
        requests.append(request)

    callback_id = driver.network.add_request_handler("before_request", callback)
    assert callback_id is not None

    driver.network.remove_request_handler("before_request", callback_id)

    driver.get("https://www.selenium.dev/selenium/web/blank.html")
    assert not requests


# The following auth-challenge tests use Firefox because Chrome does not
# expose its native basic-auth dialog as a WebDriver Alert, so the fallback
# and cancellation paths would hang waiting on a dialog Selenium can't see
# or dismiss. This mirrors the Java and JavaScript BiDi examples.


@pytest.mark.driver_type("firefox_bidi")
def test_continue_with_auth_credentials(driver):
    callback_id = driver.network.add_auth_handler("admin", "admin")

    try:
        driver.get("https://the-internet.herokuapp.com/basic_auth")
        success_message = "Congratulations! You must have the proper credentials."
        assert driver.find_element(By.TAG_NAME, "p").text == success_message
    finally:
        driver.network.remove_auth_handler(callback_id)


@pytest.mark.driver_type("firefox_bidi")
def test_continue_without_auth_credentials(driver):
    from selenium.webdriver.support import expected_conditions as EC
    from selenium.webdriver.support.wait import WebDriverWait

    def callback(request):
        pass  # Neither provide_credentials() nor cancel(): falls back to the browser's default handling.

    handler_id = driver.network.add_authentication_handler(callback)

    try:
        driver.get("https://the-internet.herokuapp.com/basic_auth")
        alert = WebDriverWait(driver, 5).until(EC.alert_is_present())
        alert.dismiss()
        WebDriverWait(driver, 5).until(lambda d: "Not authorized" in d.page_source)
    finally:
        driver.network.remove_authentication_handler(handler_id)


@pytest.mark.driver_type("firefox_bidi")
def test_cancel_auth(driver):
    def callback(request):
        request.cancel()

    handler_id = driver.network.add_authentication_handler(callback)

    try:
        driver.get("https://the-internet.herokuapp.com/basic_auth")
        assert "Not authorized" in driver.page_source
    finally:
        driver.network.remove_authentication_handler(handler_id)


@pytest.mark.driver_type("firefox_bidi")
def test_auth_required_event(driver):
    from selenium.webdriver.common.bidi.network import AuthenticationRequest

    challenges = []

    def callback(request: AuthenticationRequest):
        challenges.append(request)
        request.provide_credentials("admin", "admin")

    handler_id = driver.network.add_authentication_handler(callback)

    try:
        driver.get("https://the-internet.herokuapp.com/basic_auth")
        assert len(challenges) == 1
        assert challenges[0].scheme == "Basic"
        assert challenges[0].realm == "Restricted Area"
    finally:
        driver.network.remove_authentication_handler(handler_id)
