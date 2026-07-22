import pytest
from selenium import webdriver
from selenium.webdriver.common.print_page_options import PrintOptions

pytest.fixture()
def driver():
    driver = webdriver.Chrome()
    yield driver
    driver.quit()

def test_prints_page(driver):
    driver.get("https://www.selenium.dev/")
    print_options = PrintOptions()
    pdf = driver.print_page(print_options)
    assert len(pdf) > 0