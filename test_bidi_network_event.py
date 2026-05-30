from selenium import webdriver
import time

options = webdriver.ChromeOptions()
options.enable_bidi = True
driver = webdriver.Chrome(options=options)

def cb(e):
    print(f"Event: {e}")

try:
    print("Adding event handler...")
    handler_id = driver.network.add_event_handler('network.responseStarted', cb)
    print(f"Added handler with ID: {handler_id}")
    
    print("Navigating...")
    driver.get('https://www.selenium.dev')
    print("Navigation complete")
    time.sleep(2)
finally:
    driver.quit()
