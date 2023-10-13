import pytest
from selenium.webdriver.common.bidi.console import Console
from selenium.webdriver.common.by import By
from selenium.webdriver.common.log import Log


@pytest.mark.trio
async def test_mutation(driver):
    async with driver.bidi_connection() as session:
        log = Log(driver, session)

        async with log.mutation_events() as event:
            driver.get('https://www.selenium.dev/selenium/web/dynamic.html')
            driver.find_element(By.ID, "reveal").click()

    assert event["element"] == driver.find_element(By.ID, "revealed")


@pytest.mark.trio
async def test_console_log(driver):
    driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')

    async with driver.bidi_connection() as session:
        log = Log(driver, session)

        async with log.add_listener(Console.ALL) as messages:
            driver.find_element(by=By.ID, value='consoleLog').click()

        assert messages["message"] == "Hello, world!"


@pytest.mark.trio
async def test_js_error(driver):
    driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')

    async with driver.bidi_connection() as session:
        log = Log(driver, session)

        async with log.add_js_error_listener() as messages:
            driver.find_element(by=By.ID, value='jsException').click()

        assert "Error: Not working" in messages.exception_details.exception.description
