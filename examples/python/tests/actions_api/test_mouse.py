from time import sleep

from selenium.webdriver import ActionChains
from selenium.webdriver.common.actions.action_builder import ActionBuilder
from selenium.webdriver.common.actions.mouse_button import MouseButton
from selenium.webdriver.common.by import By


def test_click_and_hold(driver):
    driver.get('https://selenium.dev/selenium/web/mouse_interaction.html')

    clickable = driver.find_element(By.ID, "clickable")
    ActionChains(driver)\
        .click_and_hold(clickable)\
        .perform()

    sleep(0.5)
    assert driver.find_element(By.ID, "click-status").text == "focused"


def test_click_and_release(driver):
    driver.get('https://selenium.dev/selenium/web/mouse_interaction.html')

    clickable = driver.find_element(By.ID, "click")
    ActionChains(driver)\
        .click(clickable)\
        .perform()

    assert "resultPage.html" in driver.current_url


def test_right_click(driver):
    driver.get('https://selenium.dev/selenium/web/mouse_interaction.html')

    clickable = driver.find_element(By.ID, "clickable")
    ActionChains(driver)\
        .context_click(clickable)\
        .perform()

    sleep(0.5)
    assert driver.find_element(By.ID, "click-status").text == "context-clicked"


def test_back_click_ab(driver):
    driver.get('https://selenium.dev/selenium/web/mouse_interaction.html')
    driver.find_element(By.ID, "click").click()
    assert driver.title == "We Arrive Here"

    action = ActionBuilder(driver)
    action.pointer_action.pointer_down(MouseButton.BACK)
    action.pointer_action.pointer_up(MouseButton.BACK)
    action.perform()

    assert driver.title == "BasicMouseInterfaceTest"


def test_forward_click_ab(driver):
    driver.get('https://selenium.dev/selenium/web/mouse_interaction.html')
    driver.find_element(By.ID, "click").click()
    driver.back()
    assert driver.title == "BasicMouseInterfaceTest"

    action = ActionBuilder(driver)
    action.pointer_action.pointer_down(MouseButton.FORWARD)
    action.pointer_action.pointer_up(MouseButton.FORWARD)
    action.perform()

    assert driver.title == "We Arrive Here"


def test_double_click(driver):
    driver.get('https://selenium.dev/selenium/web/mouse_interaction.html')

    clickable = driver.find_element(By.ID, "clickable")
    ActionChains(driver)\
        .double_click(clickable)\
        .perform()

    assert driver.find_element(By.ID, "click-status").text == "double-clicked"


def test_hover(driver):
    driver.get('https://selenium.dev/selenium/web/mouse_interaction.html')

    hoverable = driver.find_element(By.ID, "hover")
    ActionChains(driver)\
        .move_to_element(hoverable)\
        .perform()

    assert driver.find_element(By.ID, "move-status").text == "hovered"


def test_move_by_offset_from_element(driver):
    driver.get('https://selenium.dev/selenium/web/mouse_interaction.html')

    mouse_tracker = driver.find_element(By.ID, "mouse-tracker")
    ActionChains(driver)\
        .move_to_element_with_offset(mouse_tracker, 8, 0)\
        .perform()

    coordinates = driver.find_element(By.ID, "relative-location").text.split(", ")
    assert abs(int(coordinates[0]) - 100 - 8) < 2


def test_move_by_offset_from_viewport_origin_ab(driver):
    driver.get('https://selenium.dev/selenium/web/mouse_interaction.html')

    action = ActionBuilder(driver)
    action.pointer_action.move_to_location(8, 0)
    action.perform()

    coordinates = driver.find_element(By.ID, "absolute-location").text.split(", ")

    assert abs(int(coordinates[0]) - 8) < 2


def test_move_by_offset_from_current_pointer_ab(driver):
    driver.get('https://selenium.dev/selenium/web/mouse_interaction.html')

    action = ActionBuilder(driver)
    action.pointer_action.move_to_location(6, 3)
    action.perform()

    ActionChains(driver)\
        .move_by_offset( 13, 15)\
        .perform()

    coordinates = driver.find_element(By.ID, "absolute-location").text.split(", ")

    assert abs(int(coordinates[0]) - 6 - 13) < 2
    assert abs(int(coordinates[1]) - 3 - 15) < 2


def test_drag_and_drop_onto_element(driver):
    driver.get('https://selenium.dev/selenium/web/mouse_interaction.html')

    draggable = driver.find_element(By.ID, "draggable")
    droppable = driver.find_element(By.ID, "droppable")
    ActionChains(driver)\
        .drag_and_drop(draggable, droppable)\
        .perform()

    assert driver.find_element(By.ID, "drop-status").text == "dropped"


def test_drag_and_drop_by_offset(driver):
    driver.get('https://selenium.dev/selenium/web/mouse_interaction.html')

    draggable = driver.find_element(By.ID, "draggable")
    start = draggable.location
    finish = driver.find_element(By.ID, "droppable").location
    ActionChains(driver)\
        .drag_and_drop_by_offset(draggable, finish['x'] - start['x'], finish['y'] - start['y'])\
        .perform()

    assert driver.find_element(By.ID, "drop-status").text == "dropped"







