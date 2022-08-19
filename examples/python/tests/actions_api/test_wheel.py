from time import sleep

from selenium.webdriver import ActionChains
from selenium.webdriver.common.by import By
from selenium.webdriver.common.actions.wheel_input import ScrollOrigin


def test_can_scroll_to_element(driver):
    driver.get("https://selenium.dev/selenium/web/scrolling_tests/frame_with_nested_scrolling_frame_out_of_view.html")

    iframe = driver.find_element(By.TAG_NAME, "iframe")
    ActionChains(driver)\
        .scroll_to_element(iframe)\
        .perform()

    assert _in_viewport(driver, iframe)


def test_can_scroll_from_viewport_by_amount(driver):
    driver.get("https://selenium.dev/selenium/web/scrolling_tests/frame_with_nested_scrolling_frame_out_of_view.html")

    footer = driver.find_element(By.TAG_NAME, "footer")
    delta_y = footer.rect['y']
    ActionChains(driver)\
        .scroll_by_amount(0, delta_y)\
        .perform()

    sleep(0.5)
    assert _in_viewport(driver, footer)


def test_can_scroll_from_element_by_amount(driver):
    driver.get("https://selenium.dev/selenium/web/scrolling_tests/frame_with_nested_scrolling_frame_out_of_view.html")

    iframe = driver.find_element(By.TAG_NAME, "iframe")
    scroll_origin = ScrollOrigin.from_element(iframe)
    ActionChains(driver)\
        .scroll_from_origin(scroll_origin, 0, 200)\
        .perform()

    sleep(0.5)
    driver.switch_to.frame(iframe)
    checkbox = driver.find_element(By.NAME, "scroll_checkbox")
    assert _in_viewport(driver, checkbox)


def test_can_scroll_from_element_with_offset_by_amount(driver):
    driver.get("https://selenium.dev/selenium/web/scrolling_tests/frame_with_nested_scrolling_frame_out_of_view.html")

    footer = driver.find_element(By.TAG_NAME, "footer")
    scroll_origin = ScrollOrigin.from_element(footer, 0, -50)
    ActionChains(driver)\
        .scroll_from_origin(scroll_origin, 0, 200)\
        .perform()

    sleep(0.5)
    iframe = driver.find_element(By.TAG_NAME, "iframe")
    driver.switch_to.frame(iframe)
    checkbox = driver.find_element(By.NAME, "scroll_checkbox")
    assert _in_viewport(driver, checkbox)


def test_can_scroll_from_viewport_with_offset_by_amount(driver):
    driver.get("https://selenium.dev/selenium/web/scrolling_tests/frame_with_nested_scrolling_frame.html")

    scroll_origin = ScrollOrigin.from_viewport(10, 10)

    ActionChains(driver)\
        .scroll_from_origin(scroll_origin, 0, 200)\
        .perform()

    sleep(0.5)
    iframe = driver.find_element(By.TAG_NAME, "iframe")
    driver.switch_to.frame(iframe)
    checkbox = driver.find_element(By.NAME, "scroll_checkbox")
    assert _in_viewport(driver, checkbox)


def _in_viewport(driver, element):
    script = (
        "for(var e=arguments[0],f=e.offsetTop,t=e.offsetLeft,o=e.offsetWidth,n=e.offsetHeight;\n"
        "e.offsetParent;)f+=(e=e.offsetParent).offsetTop,t+=e.offsetLeft;\n"
        "return f<window.pageYOffset+window.innerHeight&&t<window.pageXOffset+window.innerWidth&&f+n>\n"
        "window.pageYOffset&&t+o>window.pageXOffset"
    )
    return driver.execute_script(script, element)
