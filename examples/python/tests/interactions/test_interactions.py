from selenium import webdriver

driver = webdriver.Chrome()

driver.get("https://www.selenium.dev")

title = driver.title
assert title == "Selenium"

title = driver.current_url
assert title == "https://www.selenium.dev/"

driver.quit()
