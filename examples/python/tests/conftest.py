import pytest
from selenium import webdriver


@pytest.fixture(scope='function')
def driver():
    driver = webdriver.Chrome()

    yield driver

    driver.quit()


@pytest.fixture(scope='function')
def firefox_driver():
    driver = webdriver.Firefox()
    driver.implicitly_wait(1)

    yield driver

    driver.quit()
