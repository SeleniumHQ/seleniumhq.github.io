from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.common.alert import Alert
from selenium.webdriver.support import expected_conditions

driver = webdriver.Chrome()
wait = WebDriverWait(driver, timeout=2)

driver.get("https://www.selenium.dev/selenium/web/alerts.html")

driver.find_element(By.ID, "alert").click()
alert = wait.until(expected_conditions.alert_is_present())
text = alert.text
alert.accept()


driver.find_element(By.LINK_TEXT, "test confirm").click()
wait.until(expected_conditions.alert_is_present())
alert = driver.switch_to.alert
text = alert.text
alert.dismiss()


driver.find_element(By.ID, "prompt").click()
wait.until(expected_conditions.alert_is_present())
alert = Alert(driver)
alert.send_keys("Selenium")
alert.accept()


driver.quit()
