import pytest
from selenium import webdriver
from selenium.webdriver.common import PrintOptions, PageMargin

@pytest.fixture()
def driver():
    driver = webdriver.Chrome()
    yield driver
    driver.quit()

def test_orientation(driver):
    driver.get("https://www.selenium.dev/")
    print_options = PrintOptions()
    print_options.set_orientation(PrintOptions.Orientation.LANDSCAPE) ## LANDSCAPE or PORTRAIT
    current_orientation = print_options.get_orientation()
    assert current_orientation == PrintOptions.Orientation.LANDSCAPE

def test_range(driver):
    driver.get("https://www.selenium.dev/")
    print_options = PrintOptions()
    print_options.set_page_ranges("1-3")
    # print_options.set_page_ranges(["1", "2", "3"])
    current_range = print_options.get_page_ranges()
    assert current_range == ["1", "2", "3"]

def test_size(driver):
    driver.get("https://www.selenium.dev/")
    print_options = PrintOptions()
    print_options.set_page_size("A4") ## A4, A3, A2, A1, A0, Letter, Legal, Tabloid
    current_size = print_options.get_size()
    assert current_size == "A4"

def test_margin(driver):
    driver.get("https://www.selenium.dev/")
    margins = PageMargin(10, 10, 10, 10)  # margins are in millimeters (mm)
    print_options = PrintOptions()
    print_options.set_page_margin(margins)
    current_margin = print_options.get_page_margin()
    assert current_margin.get_top() == 10
    assert current_margin.get_bottom() == 10
    assert current_margin.get_left() == 10
    assert current_margin.get_right() == 10

def test_scale(driver):
    driver.get("https://www.selenium.dev/")
    print_options = PrintOptions()
    print_options.set_scale(0.5) ## 0.1 to 2.0
    current_scale = print_options.get_scale()
    assert current_scale == 0.5

def test_background(driver):
    driver.get("https://www.selenium.dev/")
    print_options = PrintOptions()
    print_options.set_background(True) ## True or False
    current_background = print_options.get_background()
    assert current_background is True

def test_shrink_to_fit(driver):
    driver.get("https://www.selenium.dev/")
    print_options = PrintOptions()
    print_options.set_shrink_to_fit(True) ## True or False
    current_shrink_to_fit = print_options.get_shrink_to_fit()
    assert current_shrink_to_fit is True