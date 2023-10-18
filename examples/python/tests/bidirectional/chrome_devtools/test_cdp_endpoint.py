import base64

from selenium.webdriver.common.by import By


def test_set_cookie(driver):
    cookie = {'name': 'cheese',
              'value': 'gouda',
              'domain': 'www.selenium.dev',
              'secure': True}

    driver.execute_cdp_cmd('Network.setCookie', cookie)

    driver.get('https://www.selenium.dev')
    cheese = driver.get_cookie(cookie['name'])

    assert cheese['value'] == 'gouda'


def test_performance(driver):
    driver.get('https://www.selenium.dev/selenium/web/frameset.html')

    driver.execute_cdp_cmd('Performance.enable', {})

    metric_list = driver.execute_cdp_cmd('Performance.getMetrics', {})["metrics"]

    metrics = {metric["name"]: metric["value"] for metric in metric_list}

    assert metrics["DevToolsCommandDuration"] > 0
    assert metrics["Frames"] == 12


def test_basic_auth(driver):
    driver.execute_cdp_cmd("Network.enable", {})

    credentials = base64.b64encode("admin:admin".encode()).decode()
    headers = {'headers': {'authorization': 'Basic ' + credentials}}

    driver.execute_cdp_cmd('Network.setExtraHTTPHeaders', headers)

    driver.get('https://the-internet.herokuapp.com/basic_auth')

    success = driver.find_element(by=By.TAG_NAME, value='p')
    assert success.text == 'Congratulations! You must have the proper credentials.'
