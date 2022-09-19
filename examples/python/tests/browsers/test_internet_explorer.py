import os
import sys

import pytest
from selenium import webdriver
from selenium.webdriver.ie.options import Options as InternetExplorerOptions
from selenium.webdriver.ie.service import Service as InternetExplorerService
from webdriver_manager.microsoft import IEDriverManager


@pytest.mark.skipif(sys.platform != "win32", reason="requires Windows")
def test_basic_options():
    service = InternetExplorerService(executable_path=IEDriverManager().install())
    options = InternetExplorerOptions()
    options.attach_to_edge_chrome = True
    options.edge_executable_path = os.getenv("EDGE_PATH")
    driver = webdriver.Ie(options=options, service=service)

    driver.quit()
