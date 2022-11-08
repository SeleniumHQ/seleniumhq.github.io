import os

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.firefox.options import Options as FirefoxOptions


def test_basic_options():
    options = FirefoxOptions()
    driver = webdriver.Firefox(options=options)

    driver.quit()

def test_install_addon(firefox_driver):
    driver = firefox_driver

    path = os.path.abspath("tests/extensions/webextensions-selenium-example.xpi")
    driver.install_addon(path)

    driver.get("https://www.selenium.dev/selenium/web/blank.html")
    injected = driver.find_element(By.ID, "webextensions-selenium-example")

    assert injected.text == "Content injected by webextensions-selenium-example"


def test_uninstall_addon(firefox_driver):
    driver = firefox_driver

    path = os.path.abspath("tests/extensions/webextensions-selenium-example.xpi")
    id = driver.install_addon(path)
    driver.uninstall_addon(id)

    driver.get("https://www.selenium.dev/selenium/web/blank.html")
    assert len(driver.find_elements(By.ID, "webextensions-selenium-example")) == 0


def test_install_unsigned_addon_directory(firefox_driver):
    driver = firefox_driver

    path = os.path.abspath("tests/extensions/webextensions-selenium-example/")
    driver.install_addon(path, temporary=True)

    driver.get("https://www.selenium.dev/selenium/web/blank.html")
    injected = driver.find_element(By.ID, "webextensions-selenium-example")

    assert injected.text == "Content injected by webextensions-selenium-example"
