from selenium import webdriver
from selenium.webdriver.chrome.options import Options as ChromeOptions


def test_basic_options():
    options = ChromeOptions()
    driver = webdriver.Chrome(options=options)

    driver.quit()

def test_keep_browser_open():
    chrome_options = Options()
    chrome_options.add_experimental_option("detach", True)
    
    driver = webdriver.Chrome(options=chrome_options)
