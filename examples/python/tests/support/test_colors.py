from selenium.webdriver.common.by import By
from selenium.webdriver.support.color import Color


def test_color_formats():
    HEX_COLOUR = Color.from_string('#2F7ED8')
    RGB_COLOUR = Color.from_string('rgb(255, 255, 255)')
    RGB_COLOUR_PERCENT = Color.from_string('rgb(40%, 20%, 40%)')
    RGBA_COLOUR = Color.from_string('rgba(255, 255, 255, 0.5)')
    RGBA_COLOUR_PERCENT = Color.from_string('rgba(40%, 20%, 40%, 0.5)')
    HSL_COLOUR = Color.from_string('hsl(100, 0%, 50%)')
    HSLA_COLOUR = Color.from_string('hsla(100, 0%, 50%, 0.5)')

    assert HEX_COLOUR.hex == '#2f7ed8'
    assert RGB_COLOUR.rgb == 'rgb(255, 255, 255)'
    assert RGB_COLOUR_PERCENT.rgb == 'rgb(102, 51, 102)'
    assert RGBA_COLOUR.rgba == 'rgba(255, 255, 255, 0.5)'
    assert RGBA_COLOUR_PERCENT.rgba == 'rgba(102, 51, 102, 0.5)'
    assert HSL_COLOUR.rgb == 'rgb(128, 128, 128)'
    assert HSLA_COLOUR.rgba == 'rgba(128, 128, 128, 0.5)'


def test_named_colors():
    BLACK = Color.from_string('black')
    CHOCOLATE = Color.from_string('chocolate')
    HOTPINK = Color.from_string('hotpink')

    assert BLACK.hex == '#000000'
    assert CHOCOLATE.hex == '#d2691e'
    assert HOTPINK.hex == '#ff69b4'


def test_transparent_color():
    TRANSPARENT = Color.from_string('transparent')

    assert TRANSPARENT.rgba == 'rgba(0, 0, 0, 0)'


def test_element_colors(driver):
    driver.get('https://www.selenium.dev/selenium/web/colorPage.html')
    GREEN = Color.from_string('green')

    element_colour = Color.from_string(driver.find_element(By.ID, 'namedColor').value_of_css_property('color'))
    element_background_colour = Color.from_string(driver.find_element(By.ID, 'namedColor').value_of_css_property('background-color'))

    assert element_colour == Color.from_string('black')

    assert element_background_colour == GREEN

    assert element_background_colour.hex == '#008000'
    assert element_background_colour.rgba == 'rgba(0, 128, 0, 1)'
    assert element_background_colour.rgb == 'rgb(0, 128, 0)'
