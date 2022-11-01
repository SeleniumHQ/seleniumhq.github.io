import sys

import pytest
from selenium import webdriver
from selenium.webdriver.safari.options import Options as SafariOptions


@pytest.mark.skipif(sys.platform != "darwin", reason="requires Mac")
def test_basic_options():
    options = SafariOptions()
    driver = webdriver.Safari(options=options)

    driver.quit()
