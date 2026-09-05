from selenium.webdriver.common.by import By

LOCATORS_PAGE = "https://www.selenium.dev/selenium/web/locators_tests/locators.html"


def test_first_matching_element(driver):
    driver.get(LOCATORS_PAGE)
    first_input = driver.find_element(By.CLASS_NAME, "information")

    assert first_input.get_attribute("id") == "fname"


def test_subset_of_dom(driver):
    driver.get(LOCATORS_PAGE)
    form = driver.find_element(By.TAG_NAME, "form")
    input_element = form.find_element(By.CLASS_NAME, "information")

    assert input_element.get_attribute("id") == "fname"


def test_optimized_locator(driver):
    driver.get(LOCATORS_PAGE)
    input_element = driver.find_element(By.CSS_SELECTOR, "form .information")

    assert input_element.get_attribute("id") == "fname"


def test_all_matching_elements(driver):
    driver.get(LOCATORS_PAGE)
    inputs = driver.find_elements(By.TAG_NAME, "input")

    assert len(inputs) > 1


def test_evaluating_shadow_dom(driver):
    driver.implicitly_wait(5)
    driver.get('https://www.selenium.dev/selenium/web/shadowRootPage.html')

    shadow_host = driver.find_element(By.TAG_NAME, 'custom-checkbox-element')
    shadow_root = shadow_host.shadow_root
    assert shadow_root
    shadow_content = shadow_root.find_element(By.CSS_SELECTOR, 'input[type=checkbox]')

    assert shadow_host.is_displayed()
    assert shadow_content.is_displayed()


def test_get_element(driver):
    driver.get(LOCATORS_PAGE)

    elements = driver.find_elements(By.TAG_NAME, 'p')
    for element in elements:
        print(f"Paragraph text:{element.text}")

    assert len(elements) > 0


def test_find_elements_from_element(driver):
    driver.get(LOCATORS_PAGE)

    form = driver.find_element(By.TAG_NAME, 'form')
    elements = form.find_elements(By.TAG_NAME, 'input')
    for element in elements:
        print(element.get_attribute('value'))

    assert len(elements) > 0


def test_get_active_element(driver):
    driver.get(LOCATORS_PAGE)

    driver.find_element(By.CSS_SELECTOR, '#fname').send_keys('webElement')
    attr = driver.switch_to.active_element.get_attribute('name')

    assert attr == 'fname'
