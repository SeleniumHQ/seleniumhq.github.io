import os
import re
import subprocess

import pytest
from selenium import webdriver


def test_basic_options():
    options = webdriver.ChromeOptions()
    driver = webdriver.Chrome(options=options)

    driver.quit()


def test_args():
    options = webdriver.ChromeOptions()
    options.add_argument("--start-maximized")

    driver = webdriver.Chrome(options=options)
    driver.get('http://selenium.dev')

    driver.quit()


def test_keep_browser_open():
    options = webdriver.ChromeOptions()
    options.add_experimental_option("detach", True)

    driver = webdriver.Chrome(options=options)
    driver.get('http://selenium.dev')

    driver.quit()


def test_exclude_switches():
    options = webdriver.ChromeOptions()
    options.add_experimental_option('excludeSwitches', ['disable-popup-blocking'])

    driver = webdriver.Chrome(options=options)
    driver.get('http://selenium.dev')

    driver.quit()


def test_log_to_file(log_path):
    service = webdriver.chrome.service.Service(log_path=log_path)

    driver = webdriver.Chrome(service=service)

    with open(log_path, 'r') as fp:
        assert "Starting ChromeDriver" in fp.readline()

    driver.quit()


@pytest.mark.skip(reason="this is not supported, yet")
def test_log_to_stdout(capfd):
    service = webdriver.chrome.service.Service(log_output=subprocess.STDOUT)

    driver = webdriver.Chrome(service=service)

    out, err = capfd.readouterr()
    assert "Starting ChromeDriver" in out

    driver.quit()


def test_log_level(log_path):
    service = webdriver.chrome.service.Service(service_args=['--log-level=DEBUG'], log_path=log_path)

    driver = webdriver.Chrome(service=service)

    with open(log_path, 'r') as f:
        assert '[DEBUG]' in f.read()

    driver.quit()


def test_log_features(log_path):
    service = webdriver.chrome.service.Service(service_args=['--append-log', '--readable-timestamp'], log_path=log_path)

    driver = webdriver.Chrome(service=service)

    with open(log_path, 'r') as f:
        assert re.match("\[\d\d-\d\d-\d\d\d\d", f.read())

    driver.quit()


def test_build_checks(log_path):
    service = webdriver.chrome.service.Service(service_args=['--disable-build-check'], log_path=log_path)

    driver = webdriver.Chrome(service=service)

    expected = "[WARNING]: You are using an unsupported command-line switch: --disable-build-check"
    with open(log_path, 'r') as f:
        assert expected in f.read()

    driver.quit()

    driver.quit()

def test_add_extension():
    chrome_options = webdriver.ChromeOptions()
    path = os.path.abspath("tests/extensions/webextensions-selenium-example.crx")
    chrome_options.add_extension(path)

    driver = webdriver.Chrome(options=chrome_options)
    driver.get("https://www.selenium.dev/selenium/web/blank.html");

    driver.quit()
