from selenium import webdriver
from selenium.webdriver.chrome.options import Options as ChromeOptions


def test_basic_options():
    options = ChromeOptions()
    driver = webdriver.Chrome(options=options)

    driver.quit()
