from selenium import webdriver


driver = webdriver.Chrome()

driver.get("https://d24ujavrqgkaxj.cloudfront.net/bf/index_5.html?id=57113896")

for _ in range(100): # Simulating 100 fake invites 

invite_button = driver.find_element("id" , "invite")

invite_button.click()

driver.quit()
