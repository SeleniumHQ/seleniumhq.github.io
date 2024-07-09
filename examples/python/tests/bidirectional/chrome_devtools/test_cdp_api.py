import base64

import pytest
from selenium.webdriver.common.by import By
from selenium.webdriver.common.devtools.v124.network import Headers


@pytest.mark.trio
async def test_set_cookie(driver):
    async with driver.bidi_connection() as connection:
        execution = connection.devtools.network.set_cookie(
            name="cheese",
            value="gouda",
            domain="www.selenium.dev",
            secure=True
        )

        await connection.session.execute(execution)

    driver.get("https://www.selenium.dev")
    cheese = driver.get_cookie("cheese")

    assert cheese["value"] == "gouda"


@pytest.mark.trio
async def test_performance(driver):
    driver.get('https://www.selenium.dev/selenium/web/frameset.html')

    async with driver.bidi_connection() as connection:
        await connection.session.execute(connection.devtools.performance.enable())

        metric_list = await connection.session.execute(connection.devtools.performance.get_metrics())

    metrics = {metric.name: metric.value for metric in metric_list}

    assert metrics["DevToolsCommandDuration"] > 0
    assert metrics["Frames"] == 12


@pytest.mark.trio
async def test_basic_auth(driver):
    async with driver.bidi_connection() as connection:
        await connection.session.execute(connection.devtools.network.enable())

        credentials = base64.b64encode("admin:admin".encode()).decode()
        auth = {'authorization': 'Basic ' + credentials}

        await connection.session.execute(connection.devtools.network.set_extra_http_headers(Headers(auth)))

        driver.get('https://the-internet.herokuapp.com/basic_auth')

    success = driver.find_element(by=By.TAG_NAME, value='p')
    assert success.text == 'Congratulations! You must have the proper credentials.'
