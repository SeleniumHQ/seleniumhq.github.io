import os
import subprocess
import sys

import pytest
from selenium import webdriver

EDGE_LOCATION = os.getenv("EDGE_BIN")


@pytest.mark.skipif(sys.platform != "win32", reason="requires Windows")
def test_basic_options_win10():
    options = webdriver.IeOptions()
    options.attach_to_edge_chrome = True
    options.edge_executable_path = EDGE_LOCATION
    driver = webdriver.Ie(options=options)

    driver.quit()


@pytest.mark.skipif(sys.platform != "win32", reason="requires Windows")
def test_basic_options_win11():
    options = webdriver.IeOptions()
    driver = webdriver.Ie(options=options)

    driver.quit()


@pytest.mark.skipif(sys.platform != "win32", reason="requires Windows")
def test_log_to_file(log_path):
    service = webdriver.ie.service.Service(log_file=log_path, log_level='INFO')

    driver = webdriver.Ie(service=service)

    with open(log_path, 'r') as fp:
        assert "Starting WebDriver server" in fp.readline()

    driver.quit()


@pytest.mark.skip(reason="this is not supported, yet")
def test_log_to_stdout(capfd):
    service = webdriver.ie.service.Service(log_output=subprocess.STDOUT)

    driver = webdriver.Ie(service=service)

    out, err = capfd.readouterr()
    assert "Started InternetExplorerDriver server" in out

    driver.quit()


@pytest.mark.skipif(sys.platform != "win32", reason="requires Windows")
def test_log_level(log_path):
    service = webdriver.ie.service.Service(log_file=log_path, log_level='WARN')

    driver = webdriver.Ie(service=service)

    with open(log_path, 'r') as fp:
        assert 'Invalid capability setting: timeouts is type null' in fp.readline()

    driver.quit()


@pytest.mark.skip(reason="this is not supported, yet")
def test_supporting_files(temp_dir):
    service = webdriver.ie.service.Service(service_args=["–extract-path="+temp_dir])

    driver = webdriver.Ie(service=service)

    driver.quit()
