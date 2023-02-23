import sys

import pytest
from selenium import webdriver
from selenium.webdriver.ie.options import Options as InternetExplorerOptions


@pytest.mark.skipif(sys.platform != "win32", reason="requires Windows")
def test_basic_options():
    options = InternetExplorerOptions()
    driver = webdriver.Ie(options=options)

    driver.quit()
