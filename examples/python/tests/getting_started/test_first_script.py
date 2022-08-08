from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service as ChromeService
from webdriver_manager.chrome import ChromeDriverManager


def test_eight_components():
    driver = webdriver.Chrome(service=ChromeService(executable_path=ChromeDriverManager().install()))

    driver.get("https://duckduckgo.com/")

    title = driver.title
    assert "DuckDuckGo" in title

    driver.implicitly_wait(5)

    search_box = driver.find_element(by=By.NAME, value="q")
    search_button = driver.find_element(by=By.ID, value="search_button_homepage")

    search_box.send_keys("Selenium")
    search_button.click()

    search_box = driver.find_element(by=By.NAME, value="q")
    value = search_box.get_attribute("value")
    assert value == "Selenium"

    driver.quit()
