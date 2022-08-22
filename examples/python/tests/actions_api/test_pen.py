import math

from selenium.webdriver.common.actions.action_builder import ActionBuilder
from selenium.webdriver.common.actions.interaction import POINTER_PEN
from selenium.webdriver.common.actions.pointer_input import PointerInput
from selenium.webdriver.common.by import By


def test_use_pen(driver):
    driver.get('https://www.selenium.dev/selenium/web/pointerActionsPage.html')

    pointer_area = driver.find_element(By.ID, "pointerArea")
    pen_input = PointerInput(POINTER_PEN, "default pen")
    action = ActionBuilder(driver, mouse=pen_input)
    action.pointer_action\
        .move_to(pointer_area)\
        .pointer_down()\
        .move_by(2, 2)\
        .pointer_up()
    action.perform()

    moves = driver.find_elements(By.CLASS_NAME, "pointermove")
    move_to = properties(moves[0])
    down = properties(driver.find_element(By.CLASS_NAME, "pointerdown"))
    move_by = properties(moves[1])
    up = properties(driver.find_element(By.CLASS_NAME, "pointerup"))

    rect = pointer_area.rect
    center_x = rect["x"] + rect["width"] / 2
    center_y = rect["y"] + rect["height"] / 2

    assert move_to["button"] == "-1"
    assert move_to["pointerType"] == "pen"
    assert move_to["pageX"] == str(math.floor(center_x))
    assert move_to["pageY"] == str(math.floor(center_y))
    assert down["button"] == "0"
    assert down["pointerType"] == "pen"
    assert down["pageX"] == str(math.floor(center_x))
    assert down["pageY"] == str(math.floor(center_y))
    assert move_by["button"] == "-1"
    assert move_by["pointerType"] == "pen"
    assert move_by["pageX"] == str(math.floor(center_x + 2))
    assert move_by["pageY"] == str(math.floor(center_y + 2))
    assert up["button"] == "0"
    assert up["pointerType"] == "pen"
    assert up["pageX"] == str(math.floor(center_x + 2))
    assert up["pageY"] == str(math.floor(center_y + 2))


def test_set_pointer_event_properties(driver):
    driver.get('https://www.selenium.dev/selenium/web/pointerActionsPage.html')

    pointer_area = driver.find_element(By.ID, "pointerArea")
    pen_input = PointerInput(POINTER_PEN, "default pen")
    action = ActionBuilder(driver, mouse=pen_input)
    action.pointer_action\
        .move_to(pointer_area)\
        .pointer_down()\
        .move_by(2, 2, tilt_x=-72, tilt_y=9, twist=86)\
        .pointer_up(0)
    action.perform()

    moves = driver.find_elements(By.CLASS_NAME, "pointermove")
    move_to = properties(moves[0])
    down = properties(driver.find_element(By.CLASS_NAME, "pointerdown"))
    move_by = properties(moves[1])
    up = properties(driver.find_element(By.CLASS_NAME, "pointerup"))

    rect = pointer_area.rect
    center_x = rect["x"] + rect["width"] / 2
    center_y = rect["y"] + rect["height"] / 2

    assert move_to["button"] == "-1"
    assert move_to["pointerType"] == "pen"
    assert move_to["pageX"] == str(math.floor(center_x))
    assert move_to["pageY"] == str(math.floor(center_y))
    assert down["button"] == "0"
    assert down["pointerType"] == "pen"
    assert down["pageX"] == str(math.floor(center_x))
    assert down["pageY"] == str(math.floor(center_y))
    assert move_by["button"] == "-1"
    assert move_by["pointerType"] == "pen"
    assert move_by["pageX"] == str(math.floor(center_x + 2))
    assert move_by["pageY"] == str(math.floor(center_y + 2))
    assert move_by["tiltX"] == "-72"
    assert move_by["tiltY"] == "9"
    assert move_by["twist"] == "86"
    assert up["button"] == "0"
    assert up["pointerType"] == "pen"
    assert up["pageX"] == str(math.floor(center_x + 2))
    assert up["pageY"] == str(math.floor(center_y + 2))


def properties(element):
    kv = element.text.split(' ', 1)[1].split(', ')
    return {x[0]:x[1] for x in list(map(lambda item: item.split(': '), kv))}


