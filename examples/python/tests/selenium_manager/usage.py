from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service

def setup_without_selenium_manager():
    chrome_service = Service(executable_path='path/to/chrome.exe')
    driver = webdriver.Chrome(chrome_service)
    return driver
    
def setup_with_selenium_manager():
    driver = webdriver.Chrome()
    return driver