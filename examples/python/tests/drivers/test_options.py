from selenium import webdriver
from selenium.webdriver.common.proxy import Proxy
from selenium.webdriver.common.proxy import ProxyType


def test_page_load_strategy_normal():
    options = webdriver.ChromeOptions()
    options.page_load_strategy = 'normal'
    driver = webdriver.Chrome(options=options)
    driver.get("https://www.selenium.dev/")
    driver.quit()


def test_page_load_strategy_eager():
    options = webdriver.ChromeOptions()
    options.page_load_strategy = 'eager'
    driver = webdriver.Chrome(options=options)
    driver.get("https://www.selenium.dev/")
    driver.quit()


def test_page_load_strategy_none():
    options = webdriver.ChromeOptions()
    options.page_load_strategy = 'none'
    driver = webdriver.Chrome(options=options)
    driver.get("https://www.selenium.dev/")
    driver.quit()

def test_timeouts_script():
    options = webdriver.ChromeOptions()
    options.timeouts = { 'script': 5000 }
    driver = webdriver.Chrome(options=options)
    driver.get("https://www.selenium.dev/")
    driver.quit()

def test_timeouts_page_load():
    options = webdriver.ChromeOptions()
    options.timeouts = { 'pageLoad': 5000 }
    driver = webdriver.Chrome(options=options)
    driver.get("https://www.selenium.dev/")
    driver.quit()

def test_timeouts_implicit_wait():
    options = webdriver.ChromeOptions()
    options.timeouts = { 'implicit': 5000 }
    driver = webdriver.Chrome(options=options)
    driver.get("https://www.selenium.dev/")
    driver.quit()

def test_unhandled_prompt():
    options = webdriver.ChromeOptions()
    options.unhandled_prompt_behavior = 'accept'
    driver = webdriver.Chrome(options=options)
    driver.get("https://www.selenium.dev/")
    driver.quit()

def test_set_window_rect():
    options = webdriver.FirefoxOptions()
    options.set_window_rect = True # Full support in Firefox
    driver = webdriver.Firefox(options=options)
    driver.get("https://www.selenium.dev/")
    driver.quit()

def test_strict_file_interactability():
    options = webdriver.ChromeOptions()
    options.strict_file_interactability = True
    driver = webdriver.Chrome(options=options)
    driver.get("https://www.selenium.dev/")
    driver.quit()

def test_proxy():
    options = webdriver.ChromeOptions()
    options.proxy = Proxy({ 'proxyType': ProxyType.MANUAL, 'httpProxy' : 'http.proxy:1234'})
    driver = webdriver.Chrome(options=options)
    driver.get("https://www.selenium.dev/")
    driver.quit()
    
def test_set_browser_name():
    options = webdriver.ChromeOptions()
    assert options.capabilities['browserName'] == 'chrome'
    driver = webdriver.Chrome(options=options)
    driver.get("https://www.selenium.dev/")
    driver.quit()
    
def test_set_browser_version():
    options = webdriver.ChromeOptions()
    options.browser_version = 'stable'
    assert options.capabilities['browserVersion'] == 'stable'
    driver = webdriver.Chrome(options=options)
    driver.get("https://www.selenium.dev/")
    driver.quit()
    
def test_platform_name():
    options = webdriver.ChromeOptions()
    options.platform_name = 'any'
    driver = webdriver.Chrome(options=options)
    driver.get("https://www.selenium.dev/")
    driver.quit()
    
def test_accept_insecure_certs():
    options = webdriver.ChromeOptions()
    options.accept_insecure_certs = True
    driver = webdriver.Chrome(options=options)
    driver.get("https://www.selenium.dev/")
    driver.quit()
