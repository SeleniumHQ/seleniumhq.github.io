import sys

import pytest
from selenium import webdriver


@pytest.mark.skipif(sys.platform != "darwin", reason="requires Mac")
def test_basic_options():
    options = webdriver.safari.options.Options()
    driver = webdriver.Safari(options=options)

    driver.quit()


@pytest.mark.skipif(sys.platform != "darwin", reason="requires Mac")
def test_enable_logging():
    service = webdriver.safari.service.Service(service_args=["--diagnose"])

    driver = webdriver.Safari(service=service)

    driver.quit()

@pytest.mark.skip(reason="Not installed on Mac GitHub Actions Runner Image")
def test_technology_preview():
    options = webdriver.safari.options.Options()
    options.use_technology_preview = True
    service = webdriver.safari.service.Service(
        executable_path='/Applications/Safari Technology Preview.app/Contents/MacOS/safaridriver'
    )
    driver = webdriver.Safari(options=options, service=service)

    driver.quit()

