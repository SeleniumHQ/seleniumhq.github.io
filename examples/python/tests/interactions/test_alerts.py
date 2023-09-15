import time

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


driver = webdriver.Chrome()

driver.get("https://www.selenium.dev/selenium/web/alerts.html")

# Click the link to activate the alert
driver.find_element(By.ID, "alert").click()

# Store the alert in a variable
alert = driver.switch_to.alert

# Store the alert text in a variable
text = alert.text

#assert alert text
assert alert.text == "cheese"

# Press the OK button
alert.accept()



# Click the link to activate the empty-alert
driver.find_element(By.ID, "empty-alert").click()

# Store the alert in a variable
alert = driver.switch_to.alert

# Store the alert text in a variable
text = alert.text

#assert alert text
assert alert.text == ""

# Press the OK button
alert.accept()



# Click the link to activate the prompt
driver.find_element(By.ID, "prompt").click()

# Store the alert in a variable
alert = driver.switch_to.alert

# Press the Cancel button
alert.dismiss()



driver.quit()
