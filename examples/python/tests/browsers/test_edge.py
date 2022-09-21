from selenium import webdriver
from selenium.webdriver.edge.options import Options as EdgeOptions
from selenium.webdriver.edge.service import Service as EdgeService
from webdriver_manager.microsoft import EdgeChromiumDriverManager


def test_basic_options():
    service = EdgeService(executable_path=EdgeChromiumDriverManager().install())
    options = EdgeOptions()
    driver = webdriver.Edge(options=options, service=service)

    driver.quit()
