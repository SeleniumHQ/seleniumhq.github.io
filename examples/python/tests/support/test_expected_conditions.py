from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.wait import WebDriverWait

# Expected Conditions API Documentation:
# https://www.selenium.dev/selenium/docs/api/py/webdriver_support/selenium.webdriver.support.expected_conditions.html


def test_expected_condition(driver):
    driver.get("https://www.selenium.dev/selenium/web/dynamic.html")
    revealed = driver.find_element(By.ID, "revealed")
    driver.find_element(By.ID, "reveal").click()

    wait = WebDriverWait(driver, timeout=2)
    wait.until(EC.visibility_of_element_located((By.ID, "revealed")))

    revealed.send_keys("Displayed")
    assert revealed.get_property("value") == "Displayed"
