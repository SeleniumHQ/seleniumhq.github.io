import pytest
from selenium import webdriver
from selenium.webdriver.chrome.options import Options as ChromeOptions
from selenium.webdriver.edge.options import Options as EdgeOptions
from selenium.webdriver.firefox.options import Options as FirefoxOptions
from selenium.webdriver.ie.options import Options as IEOptions

@pytest.mark.skip(reason="only runs if chromedriver is in PATH")
def test_chrome_session():
    options = ChromeOptions()
    driver = webdriver.Chrome(options=options)

    driver.quit()


@pytest.mark.skip(reason="only runs if msedgedriver is in PATH")
def test_edge_session():
    options = EdgeOptions()
    driver = webdriver.Edge(options=options)

    driver.quit()


@pytest.mark.skip(reason="only runs if geckodriver is in PATH")
def test_firefox_session():
    options = FirefoxOptions()
    driver = webdriver.Firefox(options=options)

    driver.quit()


@pytest.mark.skip(reason="only runs on Windows")
def test_ie_session():
    options = IEOptions()
    driver = webdriver.Ie(options=options)

    driver.quit()


@pytest.mark.skip(reason="only runs on Windows")
def test_ie_compatibility_session():
    options = IEOptions()
    options.attach_to_edge_chrome = True
    options.edge_executable_path = "/path/to/edge/browser"
    driver = webdriver.Ie(options=options)

    driver.quit()


@pytest.mark.skip(reason="Non-standard browser")
def test_opera_session():
    options = ChromeOptions()
    options.binary_location = "path/to/opera/browser"
    driver = webdriver.Chrome(options=options)

    driver.quit()


@pytest.mark.skip(reason="only runs on Mac")
def test_safari_session():
    driver = webdriver.Safari()

    driver.quit()
