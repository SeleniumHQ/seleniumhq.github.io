import os
import subprocess

from selenium import webdriver
from selenium.webdriver.chrome.service import Service as ChromeService


def test_basic_service():
    service = ChromeService()
    driver = webdriver.Chrome(service=service)

    driver.quit()

def test_driver_location():
    driver_path = os.getenv('CHROMEWEBDRIVER') + 'chromedriver'
    service = ChromeService(executable_path=driver_path)

    driver = webdriver.Chrome(service=service)

    driver.quit()


def test_driver_port():
    service = ChromeService(port=1234)

    driver = webdriver.Chrome(service=service)

    driver.quit()


def test_log_to_file():
    log_path = 'chromedriver.log'
    service = ChromeService(log_path=log_path)

    driver = webdriver.Chrome(service=service)

    with open(log_path, 'r') as fp:
        assert "Starting ChromeDriver" in fp.readline()

    driver.quit()

