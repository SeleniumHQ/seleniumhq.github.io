import pytest
from selenium.webdriver.common.by import By
from selenium.webdriver.common.log import Log


@pytest.mark.trio
async def test_mutation(driver):
    async with driver.bidi_connection() as session:
        async with Log(driver, session).mutation_events() as event:
            driver.get('https://www.selenium.dev/selenium/web/dynamic.html')
            driver.find_element(By.ID, "reveal").click()

    assert event["element"] == driver.find_element(By.ID, "revealed")
