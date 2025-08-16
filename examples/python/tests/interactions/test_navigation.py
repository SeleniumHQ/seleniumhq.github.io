from selenium import webdriver

driver = webdriver.Chrome()

driver.get("https://www.selenium.dev")
driver.get("https://www.selenium.dev/selenium/web/index.html")

title = driver.title
assert title == "Index of Available Pages"

driver.back()
title = driver.title
assert title == "Selenium"

driver.forward()
title = driver.title
assert title == "Index of Available Pages"

driver.refresh()
title = driver.title
assert title == "Index of Available Pages"

driver.quit()
