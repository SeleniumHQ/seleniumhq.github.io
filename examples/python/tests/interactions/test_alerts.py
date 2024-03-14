from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.wait import WebDriverWait


driver = webdriver.Chrome()
driver.get('https://www.selenium.dev/zh-cn/documentation/webdriver/interactions/alerts/')
print(driver.page_source)

alert_dialog = driver.find_element(by=By.LINK_TEXT,value=""See an example alert"")
alert_dialog.click()
# Wait for the alert to be displayed and store it in a variable
alert = WebDriverWait(driver,10).until(EC.alert_is_present())

print(alert.text)
# Press the OK button
alert.accept()

driver.quit()
