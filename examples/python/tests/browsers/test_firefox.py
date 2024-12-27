import os
import subprocess
import sys

import pytest
from selenium import webdriver


def test_basic_options():
    options = webdriver.FirefoxOptions()
    driver = webdriver.Firefox(options=options)

    driver.quit()


def test_arguments():
    options = webdriver.FirefoxOptions()

    options.add_argument("-headless")

    driver = webdriver.Firefox(options=options)
    driver.quit()


def test_set_browser_location(firefox_bin):
    options = webdriver.FirefoxOptions()

    options.binary_location = firefox_bin

    driver = webdriver.Firefox(options=options)

    driver.quit()


def test_log_to_file(log_path):
    service = webdriver.FirefoxService(log_output=log_path, service_args=['--log', 'debug'])

    driver = webdriver.Firefox(service=service)
    driver.get("https://www.selenium.dev")

    with open(log_path, 'r') as fp:
        assert "geckodriver	INFO	Listening on" in fp.readline()

    driver.quit()


def test_log_to_stdout(capfd):
    service = webdriver.FirefoxService(log_output=subprocess.STDOUT)

    driver = webdriver.Firefox(service=service)

    out, err = capfd.readouterr()
    assert "geckodriver	INFO	Listening on" in out

    driver.quit()


def test_log_level(log_path):
    service = webdriver.FirefoxService(log_output=log_path, service_args=['--log', 'debug'])

    driver = webdriver.Firefox(service=service)

    with open(log_path, 'r') as f:
        assert '\tDEBUG' in f.read()

    driver.quit()


def test_log_truncation(log_path):
    service = webdriver.FirefoxService(service_args=['--log-no-truncate', '--log', 'debug'], log_output=log_path)

    driver = webdriver.Firefox(service=service)

    with open(log_path, 'r') as f:
        assert ' ... ' not in f.read()

    driver.quit()


def test_profile_location(temp_dir):
    service = webdriver.FirefoxService(service_args=['--profile-root', temp_dir])

    driver = webdriver.Firefox(service=service)
    profile_name = driver.capabilities.get('moz:profile').replace('\\', '/').split('/')[-1]

    assert profile_name in os.listdir(temp_dir)

    driver.quit()


def test_install_addon(firefox_driver, addon_path_xpi):
    driver = firefox_driver

    driver.install_addon(addon_path_xpi)

    driver.get("https://www.selenium.dev/selenium/web/blank.html")
    injected = driver.find_element(webdriver.common.by.By.ID, "webextensions-selenium-example")

    assert injected.text == "Content injected by webextensions-selenium-example"


def test_uninstall_addon(firefox_driver, addon_path_xpi):
    driver = firefox_driver

    id = driver.install_addon(addon_path_xpi)
    driver.uninstall_addon(id)

    driver.get("https://www.selenium.dev/selenium/web/blank.html")
    assert len(driver.find_elements(webdriver.common.by.By.ID, "webextensions-selenium-example")) == 0


def test_install_unsigned_addon_directory(firefox_driver, addon_path_dir):
    driver = firefox_driver

    driver.install_addon(addon_path_dir, temporary=True)

    driver.get("https://www.selenium.dev/selenium/web/blank.html")
    injected = driver.find_element(webdriver.common.by.By.ID, "webextensions-selenium-example")

    assert injected.text == "Content injected by webextensions-selenium-example"


def test_install_unsigned_addon_directory_slash(firefox_driver, addon_path_dir_slash):
    driver = firefox_driver

    driver.install_addon(addon_path_dir_slash, temporary=True)

    driver.get("https://www.selenium.dev/selenium/web/blank.html")
    injected = driver.find_element(webdriver.common.by.By.ID, "webextensions-selenium-example")

    assert injected.text == "Content injected by webextensions-selenium-example"


def test_full_page_screenshot(firefox_driver):
    driver = firefox_driver

    driver.get("https://www.selenium.dev")

    driver.save_full_page_screenshot("full_page_screenshot.png")

    assert os.path.exists("full_page_screenshot.png")

    driver.quit()


def test_set_context(firefox_driver):
    driver = firefox_driver

    with driver.context(driver.CONTEXT_CHROME):
        driver.execute_script("console.log('Inside Chrome context');")

    # Check if the context is back to content
    assert driver.execute("GET_CONTEXT")["value"] == "content"


def test_firefox_profile():
    from selenium.webdriver.firefox.options import Options
    from selenium.webdriver.firefox.firefox_profile import FirefoxProfile

    options = Options()
    firefox_profile = FirefoxProfile()
    firefox_profile.set_preference("javascript.enabled", False)
    options.profile = firefox_profile

    driver = webdriver.Firefox(options=options)

    driver.quit()
