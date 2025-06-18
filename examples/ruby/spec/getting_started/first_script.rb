require 'selenium-webdriver'

driver = Selenium::WebDriver.for :chrome

driver.get('https://www.selenium.dev/selenium/web/web-form.html')

driver.title

driver.manage.timeouts.implicit_wait = 500

text_box = driver.find_element(name: 'my-text')
submit_button = driver.find_element(tag_name: 'button')

text_box.send_keys('Selenium')
submit_button.click

message = driver.find_element(id: 'message')
message.text

driver.quit
