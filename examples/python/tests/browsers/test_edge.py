from selenium import webdriver
from selenium.webdriver.edge.options import Options as EdgeOptions


def test_basic_options():
    options = EdgeOptions()
    driver = webdriver.Edge(options=options)

    driver.quit()
