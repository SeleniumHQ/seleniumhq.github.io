def test_set_cookie(driver):
    cookie = {'name': 'cheese',
              'value': 'gouda',
              'domain': 'www.selenium.dev',
              'secure': True}

    driver.execute_cdp_cmd('Network.setCookie', cookie)

    driver.get('https://www.selenium.dev')
    cheese = driver.get_cookie(cookie['name'])

    assert cheese['value'] == 'gouda'
