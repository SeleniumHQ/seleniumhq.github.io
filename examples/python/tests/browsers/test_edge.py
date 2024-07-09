import os
import re
import subprocess

from selenium import webdriver


def test_basic_options():
    options = webdriver.EdgeOptions()
    driver = webdriver.Edge(options=options)

    driver.quit()


def test_args():
    options = webdriver.EdgeOptions()

    options.add_argument("--start-maximized")

    driver = webdriver.Edge(options=options)
    driver.get('http://selenium.dev')

    driver.quit()


def test_set_browser_location(edge_bin):
    options = webdriver.EdgeOptions()

    options.binary_location = edge_bin

    driver = webdriver.Edge(options=options)

    driver.quit()


def test_add_extension():
    options = webdriver.EdgeOptions()
    extension_file_path = os.path.abspath("tests/extensions/webextensions-selenium-example.crx")

    options.add_extension(extension_file_path)

    driver = webdriver.Edge(options=options)
    driver.get("https://www.selenium.dev/selenium/web/blank.html")

    driver.quit()


def test_keep_browser_open():
    options = webdriver.EdgeOptions()

    options.add_experimental_option("detach", True)

    driver = webdriver.Edge(options=options)
    driver.get('http://selenium.dev')

    driver.quit()


def test_exclude_switches():
    options = webdriver.EdgeOptions()

    options.add_experimental_option('excludeSwitches', ['disable-popup-blocking'])

    driver = webdriver.Edge(options=options)
    driver.get('http://selenium.dev')

    driver.quit()


def test_log_to_file(log_path):
    service = webdriver.EdgeService(log_output=log_path)

    driver = webdriver.Edge(service=service)

    with open(log_path, 'r') as fp:
        assert "Starting Microsoft Edge WebDriver" in fp.readline()

    driver.quit()


def test_log_to_stdout(capfd):
    service = webdriver.EdgeService(log_output=subprocess.STDOUT)

    driver = webdriver.Edge(service=service)

    out, err = capfd.readouterr()
    assert "Starting Microsoft Edge WebDriver" in out

    driver.quit()


def test_log_level(log_path):
    service = webdriver.EdgeService(service_args=['--log-level=DEBUG'], log_output=log_path)

    driver = webdriver.Edge(service=service)

    with open(log_path, 'r') as f:
        assert '[DEBUG]' in f.read()

    driver.quit()


def test_log_features(log_path):
    service = webdriver.EdgeService(service_args=['--append-log', '--readable-timestamp'], log_output=log_path)

    driver = webdriver.Edge(service=service)

    with open(log_path, 'r') as f:
        assert re.match(r"\[\d\d-\d\d-\d\d\d\d", f.read())

    driver.quit()


def test_build_checks(log_path):
    service = webdriver.EdgeService(service_args=['--disable-build-check'], log_output=log_path)

    driver = webdriver.Edge(service=service)

    expected = "[WARNING]: You are using an unsupported command-line switch: --disable-build-check"
    with open(log_path, 'r') as f:
        assert expected in f.read()

    driver.quit()

