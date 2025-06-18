import os
import re
import subprocess
import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By

def test_basic_options():
    options = get_default_chrome_options()
    driver = webdriver.Chrome(options=options)

    driver.quit()


def test_args():
    options = get_default_chrome_options()

    options.add_argument("--start-maximized")

    driver = webdriver.Chrome(options=options)
    driver.get('http://selenium.dev')

    driver.quit()


def test_set_browser_location(chrome_bin):
    options = get_default_chrome_options()

    options.binary_location = chrome_bin

    driver = webdriver.Chrome(options=options)

    driver.quit()


def test_add_extension():
    options = get_default_chrome_options()
    extension_file_path = os.path.abspath("tests/extensions/webextensions-selenium-example.crx")

    options.add_extension(extension_file_path)

    driver = webdriver.Chrome(options=options)
    driver.get("https://www.selenium.dev/selenium/web/blank.html")

    driver.quit()


def test_keep_browser_open():
    options = get_default_chrome_options()

    options.add_experimental_option("detach", True)

    driver = webdriver.Chrome(options=options)
    driver.get('http://selenium.dev')

    driver.quit()


def test_exclude_switches():
    options = get_default_chrome_options()

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


def test_set_network_conditions():
    driver = webdriver.Chrome()

    network_conditions = {
        "offline": False,
        "latency": 20,  # 20 ms of latency
        "download_throughput": 2000 * 1024 / 8,  # 2000 kbps
        "upload_throughput": 2000 * 1024 / 8,    # 2000 kbps
    }
    driver.set_network_conditions(**network_conditions)

    driver.get("https://www.selenium.dev")

    # check whether the network conditions are set
    assert driver.get_network_conditions() == network_conditions

    driver.quit()


def test_set_permissions():
    driver = webdriver.Chrome()
    driver.get('https://www.selenium.dev')

    driver.set_permissions('camera', 'denied')

    assert get_permission_state(driver, 'camera') == 'denied'
    driver.quit()


def get_permission_state(driver, name):
    """Helper function to query the permission state."""
    script = """
    const callback = arguments[arguments.length - 1];
    navigator.permissions.query({name: arguments[0]}).then(permissionStatus => {
        callback(permissionStatus.state);
    });
    """
    return driver.execute_async_script(script, name)


def test_cast_features():
    driver = webdriver.Chrome()

    try:
        sinks = driver.get_sinks()
        if sinks:
            sink_name = sinks[0]['name']
            driver.start_tab_mirroring(sink_name)
            driver.stop_casting(sink_name)
        else:
            pytest.skip("No available Cast sinks to test with.")
    finally:
        driver.quit()


def test_get_browser_logs():
    driver = webdriver.Chrome()
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html")
    driver.find_element(By.ID, "consoleError").click()

    logs = driver.get_log("browser")

    # Assert that at least one log contains the expected message
    assert any("I am console error" in log['message'] for log in logs), "No matching log message found."
    driver.quit()

def get_default_chrome_options():
    options = webdriver.ChromeOptions()
    options.add_argument("--no-sandbox")
    return options
