from selenium import webdriver
import time

options = webdriver.ChromeOptions()
options.enable_bidi = True
driver = webdriver.Chrome(options=options)

def on_req(req):
    print(f"Intercepted: {req.url}")
    try:
        req.continue_request()
        print("Continued")
    except Exception as e:
        print(f"Error continuing: {e}")

try:
    print("Adding request handler...")
    driver.network.add_request_handler('before_request', on_req)
    print("Navigating...")
    driver.get('https://www.google.com')
    print("Page loaded")
finally:
    driver.quit()
