import os
import re
import subprocess

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


def test_set_browser_location(chrome_bin):
    options = webdriver.ChromeOptions()

    options.binary_location = chrome_bin

    driver = webdriver.Chrome(options=options)

    driver.quit()


def test_add_extension():
    options = webdriver.ChromeOptions()
    extension_file_path = os.path.abspath("tests/extensions/webextensions-selenium-example.crx")

    options.add_extension(extension_file_path)

    driver = webdriver.Chrome(options=options)
    driver.get("https://www.selenium.dev/selenium/web/blank.html")

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
    service = webdriver.ChromeService(log_output=log_path)

    driver = webdriver.Chrome(service=service)

    with open(log_path, 'r') as fp:
        assert "Starting ChromeDriver" in fp.readline()

    driver.quit()


def test_log_to_stdout(capfd):
    service = webdriver.ChromeService(log_output=subprocess.STDOUT)

    driver = webdriver.Chrome(service=service)

    out, err = capfd.readouterr()
    assert "Starting ChromeDriver" in out

    driver.quit()


def test_log_level(capfd):
    service = webdriver.ChromeService(service_args=['--log-level=DEBUG'], log_output=subprocess.STDOUT)

    driver = webdriver.Chrome(service=service)

    out, err = capfd.readouterr()
    assert '[DEBUG]' in err

    driver.quit()


def test_log_features(log_path):
    service = webdriver.ChromeService(service_args=['--append-log', '--readable-timestamp'], log_output=log_path)

    driver = webdriver.Chrome(service=service)

    with open(log_path, 'r') as f:
        assert re.match(r"\[\d\d-\d\d-\d\d\d\d", f.read())

    driver.quit()


def test_build_checks(capfd):
    service = webdriver.ChromeService(service_args=['--disable-build-check'], log_output=subprocess.STDOUT)

    driver = webdriver.Chrome(service=service)

    expected = "[WARNING]: You are using an unsupported command-line switch: --disable-build-check"
    out, err = capfd.readouterr()
    assert expected in err

    driver.quit()
