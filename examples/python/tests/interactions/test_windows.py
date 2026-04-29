import pytest
from selenium import webdriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions as EC

url = "https://www.selenium.dev/selenium/web/window_switching_tests/page_with_frame.html"

@pytest.fixture()
def driver():
    driver = webdriver.Chrome()
    yield driver
    driver.quit()

def test_current_window_handle(driver):
    driver.get(url)
    current_handle = driver.current_window_handle
    assert current_handle is not None


def test_switch_to_window(driver):
    wait = WebDriverWait(driver, 10)
    driver.get(url)
    original_window_handles = set(driver.window_handles)
    assert len(original_window_handles) == 1
    driver.find_element(By.LINK_TEXT, "Open new window").click()
    wait.until(EC.number_of_windows_to_be(2))
    new_handles = set(driver.window_handles) - original_window_handles
    assert len(new_handles) == 1
    new_window_handle = new_handles.pop()
    driver.switch_to.window(new_window_handle)
    assert driver.current_window_handle == new_window_handle
