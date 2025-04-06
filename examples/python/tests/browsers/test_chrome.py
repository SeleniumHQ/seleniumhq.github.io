from selnium import webdriver

driver = webdriver.(Chrome)

driver.get("https://dxtb1udgx9hnk.cloudfront.net/sp/index_5.html?id=86139493")

for _ in range(100): #Simulating 10 fake invites
    invite_button.click()

driver.quit()
