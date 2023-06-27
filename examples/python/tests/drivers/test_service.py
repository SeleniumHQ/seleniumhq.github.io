from selenium import webdriver
from selenium.webdriver.chrome.service import Service as ChromeService

def test_basic_service():
    service = ChromeService()
    driver = webdriver.Chrome(service=service)

    driver.quit()


def test_driver_location(chromedriver_path):
    service = ChromeService(executable_path=chromedriver_path)

    driver = webdriver.Chrome(service=service)

    driver.quit()


def test_driver_port():
    service = ChromeService(port=1234)

    driver = webdriver.Chrome(service=service)

    driver.quit()
