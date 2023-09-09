import time

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


driver = webdriver.Chrome()

driver.get("https://www.selenium.dev/selenium/web/alerts.html")

def accept_alert_with_delay_in_seconds(driver, delay):
    time.sleep(delay)
    alert = driver.switch_to.alert
    alert.accept()


def asert_and_accept_alert_with_delay_in_seconds(driver, delay, assertAlertText=None):
    time.sleep(delay)
    alert = driver.switch_to.alert

    if assertAlertText is not None:
        assert alert.text == assertAlertText

    alert.accept()


driver.find_element(By.ID, "alert").click()
asert_and_accept_alert_with_delay_in_seconds(driver,1,"cheese")

driver.find_element(By.ID, "empty-alert").click()
asert_and_accept_alert_with_delay_in_seconds(driver,1,"")

driver.find_element(By.ID, "prompt").click()
accept_alert_with_delay_in_seconds(driver,1)

driver.find_element(By.ID, "prompt-with-default").click()
accept_alert_with_delay_in_seconds(driver,1)

driver.find_element(By.ID, "double-prompt").click()
accept_alert_with_delay_in_seconds(driver,1)
accept_alert_with_delay_in_seconds(driver,1)

driver.find_element(By.ID, "slow-alert").click()
asert_and_accept_alert_with_delay_in_seconds(driver,1,"Slow")

driver.find_element(By.ID, "confirm").click()
asert_and_accept_alert_with_delay_in_seconds(driver,1,"Are you sure?")
driver.back()

# Does not work because of this bug https://github.com/SeleniumHQ/seleniumhq.github.io/issues/1469
# driver.find_element(By.ID, "dialog").click()

driver.find_element(By.ID, "open-page-with-onunload-alert").click()
driver.back()
time.sleep(0.5)


driver.quit()