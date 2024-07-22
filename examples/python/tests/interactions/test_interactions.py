from selenium import webdriver

driver = webdriver.Chrome()

driver.get("https://www.selenium.dev")

title = driver.title
assert title == "Selenium"

url = driver.current_url
assert url == "https://www.selenium.dev/"

driver.quit()
