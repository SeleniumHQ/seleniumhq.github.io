import pytest
from selenium.webdriver.common.by import By
from selenium.webdriver.common.log import Log
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


@pytest.mark.trio
async def test_mutation(driver):
    async with driver.bidi_connection() as session:
        async with Log(driver, session).mutation_events() as event:
            driver.get('https://www.selenium.dev/selenium/web/dynamic.html')
            WebDriverWait(driver, 10).until(EC.element_to_be_clickable((By.ID, "reveal")))
            driver.find_element(By.ID, "reveal").click()

    assert event["element"] == driver.find_element(By.ID, "revealed")
