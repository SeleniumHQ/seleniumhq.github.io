import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By

# The tests below marked as skipped mirror the HTML snippet shown at the top of the
# "Finding web elements" documentation and are illustrative only, matching how the
# same examples are shown for the other language bindings:
#
# <ol id="vegetables">
#  <li class="potatoes">…
#  <li class="onions">…
#  <li class="tomatoes"><span>Tomato is a Vegetable</span>…
# </ol>
# <ul id="fruits">
#   <li class="bananas">…
#   <li class="apples">…
#   <li class="tomatoes"><span>Tomato is a Fruit</span>…
# </ul>


@pytest.mark.skip(reason="illustrative example, not an executable test")
def test_basic_finders():
    vegetable = driver.find_element(By.CLASS_NAME, 'tomatoes')


@pytest.mark.skip(reason="illustrative example, not an executable test")
def test_subset_of_dom():
    fruits = driver.find_element(By.ID, 'fruits')
    fruit = fruits.find_element(By.CLASS_NAME, 'tomatoes')


@pytest.mark.skip(reason="illustrative example, not an executable test")
def test_optimized_locator():
    fruit = driver.find_element(By.CSS_SELECTOR, '#fruits .tomatoes')


@pytest.mark.skip(reason="illustrative example, not an executable test")
def test_all_matching_elements():
    plants = driver.find_elements(By.TAG_NAME, 'li')


def test_evaluating_shadow_dom():
    driver = webdriver.Chrome()
    driver.implicitly_wait(5)
    driver.get('https://www.selenium.dev/selenium/web/shadowRootPage.html')

    shadow_host = driver.find_element(By.TAG_NAME, 'custom-checkbox-element')
    shadow_root = shadow_host.shadow_root
    shadow_content = shadow_root.find_element(By.CSS_SELECTOR, 'input[type=checkbox]')

    assert shadow_host.is_displayed()
    assert shadow_root
    assert shadow_content.is_displayed()

    driver.quit()


def test_get_element():
    driver = webdriver.Chrome()
    driver.get('https://www.example.com')

    elements = driver.find_elements(By.TAG_NAME, 'p')
    for element in elements:
        print(element.text)

    assert len(elements) > 0

    driver.quit()


def test_find_elements_from_element():
    driver = webdriver.Chrome()
    driver.get('https://www.example.com')

    element = driver.find_element(By.TAG_NAME, 'div')
    elements = element.find_elements(By.TAG_NAME, 'p')
    for e in elements:
        print(e.text)

    assert len(elements) > 0

    driver.quit()


def test_get_active_element():
    driver = webdriver.Chrome()
    driver.get('https://www.selenium.dev/selenium/web/web-form.html')

    driver.find_element(By.CSS_SELECTOR, '[name="my-text"]').send_keys('webElement')
    attr = driver.switch_to.active_element.get_attribute('name')

    assert attr == 'my-text'

    driver.quit()
