from selenium import webdriver


def add_cookie():
    driver = webdriver.Chrome()
    driver.get("http://www.example.com")

    # Adds the cookie into current browser context
    driver.add_cookie({"name": "key", "value": "value"})


def get_named_cookie():
    driver = webdriver.Chrome()
    driver.get("http://www.example.com")

    # Adds the cookie into current browser context
    driver.add_cookie({"name": "foo", "value": "bar"})

    # Get cookie details with named cookie 'foo'
    print(driver.get_cookie("foo"))


def get_all_cookies():
    driver = webdriver.Chrome()

    driver.get("http://www.example.com")

    driver.add_cookie({"name": "test1", "value": "cookie1"})
    driver.add_cookie({"name": "test2", "value": "cookie2"})

    # Get all available cookies
    print(driver.get_cookies())

def delete_cookie():
    driver = webdriver.Chrome()

    driver.get("http://www.example.com")

    driver.add_cookie({"name": "test1", "value": "cookie1"})
    driver.add_cookie({"name": "test2", "value": "cookie2"})

    # Delete cookie with name 'test1'
    driver.delete_cookie("test1")


def delete_all_cookies():
    driver = webdriver.Chrome()

    driver.get("http://www.example.com")

    driver.add_cookie({"name": "test1", "value": "cookie1"})
    driver.add_cookie({"name": "test2", "value": "cookie2"})

    # Delete all cookies
    driver.delete_all_cookies()


def same_side_cookie_attr():
    driver = webdriver.Chrome()

    driver.get("http://www.example.com")

    # Adds the cookie into current browser context with sameSite 'Strict' (or) 'Lax'
    driver.add_cookie({"name": "foo", "value": "value", "sameSite": "Strict"})
    driver.add_cookie({"name": "foo1", "value": "value", "sameSite": "Lax"})

    cookie1 = driver.get_cookie("foo")
    cookie2 = driver.get_cookie("foo1")

    print(cookie1)
    print(cookie2)
