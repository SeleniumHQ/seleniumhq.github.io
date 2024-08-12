import os
import re
import subprocess

from selenium import webdriver


def test_basic_options():
    options = webdriver.ChromeOptions()
    driver = webdriver.Chrome(options=options)

    driver.quit()


def test_args():
    options = webdriver.ChromeOptions()

    options.add_argument("--start-maximized")

    driver = webdriver.Chrome(options=options)
    driver.get('http://selenium.dev')

    driver.quit()


def test_set_browser_location(chrome_bin):
    options = webdriver.ChromeOptions()

    options.binary_location = chrome_bin

    driver = webdriver.Chrome(options=options)

    driver.quit()


def test_add_extension():
    options = webdriver.ChromeOptions()
    extension_file_path = os.path.abspath("tests/extensions/webextensions-selenium-example.crx")

    options.add_extension(extension_file_path)

    driver = webdriver.Chrome(options=options)
    driver.get("https://www.selenium.dev/selenium/web/blank.html")

    driver.quit()


def test_keep_browser_open():
    options = webdriver.ChromeOptions()

    options.add_experimental_option("detach", True)

    driver = webdriver.Chrome(options=options)
    driver.get('http://selenium.dev')

    driver.quit()


def test_exclude_switches():
    options = webdriver.ChromeOptions()

    options.add_experimental_option('excludeSwitches', ['disable-popup-blocking'])

    driver = webdriver.Chrome(options=options)
    driver.get('http://selenium.dev')

    driver.quit()


def test_log_to_file(log_path):
    service = webdriver.ChromeService(log_output=log_path)

    driver = webdriver.Chrome(service=service)

    with open(log_path, 'r') as fp:
        assert "Starting ChromeDriver" in fp.readline()

    driver.quit()


def test_log_to_stdout(capfd):
    service = webdriver.ChromeService(log_output=subprocess.STDOUT)

    driver = webdriver.Chrome(service=service)

    out, err = capfd.readouterr()
    assert "Starting ChromeDriver" in out

    driver.quit()


def test_log_level(capfd):
    service = webdriver.ChromeService(service_args=['--log-level=DEBUG'], log_output=subprocess.STDOUT)

    driver = webdriver.Chrome(service=service)

    out, err = capfd.readouterr()
    assert '[DEBUG]' in err

    driver.quit()


def test_log_features(log_path):
    service = webdriver.ChromeService(service_args=['--append-log', '--readable-timestamp'], log_output=log_path)

    driver = webdriver.Chrome(service=service)

    with open(log_path, 'r') as f:
        assert re.match(r"\[\d\d-\d\d-\d\d\d\d", f.read())

    driver.quit()


def test_build_checks(capfd):
    service = webdriver.ChromeService(service_args=['--disable-build-check'], log_output=subprocess.STDOUT)

    driver = webdriver.Chrome(service=service)

    expected = "[WARNING]: You are using an unsupported command-line switch: --disable-build-check"
    out, err = capfd.readouterr()
    assert expected in err

    driver.quit()

def test_network_conditions():
    driver = webdriver.Chrome()

    driver.set_network_conditions(offline=False, latency=250, throughput=500*1024)
    driver.get('http://selenium.dev')

    driver.quit()

def test_logs():
    driver = webdriver.Chrome()

    driver.get('https://www.selenium.dev/webs')
    browser_logs = driver.get_log('browser')

    assert 'Failed to load' in browser_logs[0]['message']

    driver.quit()

def test_permissions():
    driver = webdriver.Chrome()

    driver.get('https://www.selenium.dev')
    
    driver.set_permissions('geolocation', 'denied')

    geolocation_permissions = driver.execute_script('return await navigator.permissions.query({name: \'geolocation\'})')
    assert geolocation_permissions['state'] == 'denied'

    driver.quit()

def test_casting():

    driver = webdriver.Chrome()

    try:
        sinks = driver.get_sinks()
        if len(sinks) > 0:
            device_name = sinks[0]['name']
            driver.start_tab_mirroring(device_name)
            driver.stop_casting(device_name)
    except:
        assert False, 'Exception when starting or stopping casting'

    driver.get('http://selenium.dev')

    driver.quit()
    