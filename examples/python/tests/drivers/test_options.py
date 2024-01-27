from selenium import webdriver


def test_page_load_strategy_normal():
    options = webdriver.ChromeOptions()

    options.page_load_strategy = 'normal'
    driver = webdriver.Chrome(options=options)

    driver.get("http://www.google.com")
    driver.quit()


def test_page_load_strategy_eager():
    options = webdriver.ChromeOptions()

    options.page_load_strategy = 'eager'
    driver = webdriver.Chrome(options=options)

    driver.get("http://www.google.com")
    driver.quit()


def test_page_load_strategy_none():
    options = webdriver.ChromeOptions()

    options.page_load_strategy = 'none'
    driver = webdriver.Chrome(options=options)

    driver.get("http://www.google.com")
    driver.quit()
