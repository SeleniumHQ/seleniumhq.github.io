from selenium import webdriver
from selenium.webdriver.firefox.options import Options as FirefoxOptions
from selenium.webdriver.firefox.service import Service as FirefoxService
from webdriver_manager.firefox import GeckoDriverManager


def test_basic_options():
    service = FirefoxService(executable_path=GeckoDriverManager().install())
    options = FirefoxOptions()
    driver = webdriver.Firefox(options=options, service=service)

    driver.quit()
