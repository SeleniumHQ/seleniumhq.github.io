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


@pytest.mark.driver_type("bidi")
def test_add_intercept(driver):
    intercept = driver.network._add_intercept()
    assert intercept is not None


@pytest.mark.driver_type("bidi")
def test_remove_intercept(driver):
    intercept = driver.network._add_intercept()
    driver.network._remove_intercept(intercept["intercept"])
    assert driver.network.intercepts == []


@pytest.mark.driver_type("bidi")
def test_fail_request(driver):
    from selenium.webdriver.common.bidi.network import Request

    failed_requests = []

    intercept = driver.network._add_intercept()

    def on_request(request: Request):
        failed_requests.append(request)
        driver.network.fail_request(request.request["requestId"])

    driver.network.add_request_handler("before_request", on_request)

    try:
        driver.get("https://www.selenium.dev/selenium/web/blank.html")
    except Exception:
        pass

    import time
    time.sleep(1)

    assert len(failed_requests) > 0
    driver.network._remove_intercept(intercept["intercept"])
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