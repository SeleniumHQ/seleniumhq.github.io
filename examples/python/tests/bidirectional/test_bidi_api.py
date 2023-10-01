import pytest
from selenium.webdriver.common.bidi.console import Console
from selenium.webdriver.common.log import Log


@pytest.mark.trio
async def test_console_log(driver):
    async with driver.bidi_connection() as session:
        log = Log(driver, session)
        driver.get('https://www.selenium.dev/selenium/web/javascriptPage.html')

        async with log.add_listener(Console.ALL) as messages:
            driver.execute_script("console.log('I love cheese')")

        assert messages["message"] == "I love cheese"
