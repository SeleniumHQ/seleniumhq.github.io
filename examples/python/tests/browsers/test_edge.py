from selenium import webdriver
from selenium.webdriver.edge.options import Options as EdgeOptions


def test_basic_options():
    options = EdgeOptions()
    driver = webdriver.Edge(options=options)

    driver.quit()

def test_headless():
    options = EdgeOptions()
    options.add_argument("--headless=new")

    driver = webdriver.Edge(options=options)
    driver.get('http://selenium.dev')

    driver.quit()