import logging
import os
import socket
import subprocess
import tempfile
import time
from selenium.webdriver.common.utils import free_port
from datetime import datetime
from urllib.request import urlopen
import requests
from requests.auth import HTTPBasicAuth

import pytest
from selenium import webdriver


@pytest.fixture(scope='function')
def driver(request):
    marker = request.node.get_closest_marker("driver_type")
    driver_type = marker.args[0] if marker else None

    if driver_type == "bidi":
        options = webdriver.ChromeOptions()
        options.enable_bidi = True
        driver = webdriver.Chrome(options=options)
    elif driver_type == "firefox":
        driver = webdriver.Firefox()
    else:
        driver = webdriver.Chrome()

    yield driver

    driver.quit()


@pytest.fixture(scope='function')
def chromedriver_bin():
    service = webdriver.ChromeService()
    options = webdriver.ChromeOptions()
    options.browser_version = 'stable'
    yield webdriver.common.driver_finder.DriverFinder(service=service, options=options).get_driver_path()


@pytest.fixture(scope='function')
def chrome_bin():
    service = webdriver.ChromeService()
    options = webdriver.ChromeOptions()
    options.browser_version = 'stable'
    yield webdriver.common.driver_finder.DriverFinder(service=service, options=options).get_browser_path()


@pytest.fixture(scope='function')
def edge_bin():
    service = webdriver.EdgeService()
    options = webdriver.EdgeOptions()
    options.browser_version = 'stable'
    yield webdriver.common.driver_finder.DriverFinder(service=service, options=options).get_browser_path()


@pytest.fixture(scope='function')
def firefox_bin():
    service = webdriver.FirefoxService()
    options = webdriver.FirefoxOptions()
    options.browser_version = 'stable'
    yield webdriver.common.driver_finder.DriverFinder(service=service, options=options).get_browser_path()


@pytest.fixture(scope='function')
def temp_dir():
    with tempfile.TemporaryDirectory() as temp_dir:
        yield temp_dir


@pytest.fixture(scope='function')
def firefox_driver():
    driver = webdriver.Firefox()
    driver.implicitly_wait(1)

    yield driver

    driver.quit()


@pytest.fixture(scope='function')
def log():
    logging.basicConfig(level=logging.WARN)
    logging.getLogger('selenium').setLevel(logging.DEBUG)


@pytest.fixture(scope='function')
def log_path():
    suffix = datetime.now().strftime("%y%m%d_%H%M%S")
    log_path = 'log_file_' + suffix + '.log'

    yield log_path

    logger = logging.getLogger('selenium')
    for handler in logger.handlers:
        logger.removeHandler(handler)
        handler.close()

    os.remove(log_path)


@pytest.fixture(scope='function')
def addon_path_xpi():
    if os.path.abspath("").endswith("tests"):
        path = os.path.abspath("extensions/webextensions-selenium-example.xpi")
    else:
        path = os.path.abspath("tests/extensions/webextensions-selenium-example.xpi")

    yield path


@pytest.fixture(scope='function')
def addon_path_dir():
    if os.path.abspath("").endswith("tests"):
        path = os.path.abspath("extensions/webextensions-selenium-example")
    else:
        path = os.path.abspath("tests/extensions/webextensions-selenium-example")

    yield path


@pytest.fixture(scope='function')
def addon_path_dir_slash():
    if os.path.abspath("").endswith("tests"):
        path = os.path.abspath("extensions/webextensions-selenium-example/")
    else:
        path = os.path.abspath("tests/extensions/webextensions-selenium-example/")

    yield path


@pytest.fixture(scope="function")
def server_old(request):
    _host = "localhost"
    _port = free_port()
    _path = os.path.join(
        os.path.dirname(
            os.path.dirname(
                os.path.abspath(__file__)
            )
        ),
        "selenium-server-4.26.0.jar",
    )

    def wait_for_server(url, timeout):
        start = time.time()
        while time.time() - start < timeout:
            try:
                urlopen(url)
                return 1
            except OSError:
                time.sleep(0.2)
        return 0

    _socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    url = f"http://{_host}:{_port}/status"
    try:
        _socket.connect((_host, _port))
        print(
            "The remote driver server is already running or something else"
            "is using port {}, continuing...".format(_port)
        )
    except Exception:
        print("Starting the Selenium server")
        process = subprocess.Popen(
            [
                "java",
                "-jar",
                _path,
                "standalone",
                "--port",
                _port,
                "--selenium-manager",
                "true",
                "--enable-managed-downloads",
                "true",
            ]
        )
        print(f"Selenium server running as process: {process.pid}")
        assert wait_for_server(url, 10), f"Timed out waiting for Selenium server at {url}"
        print("Selenium server is ready")
        yield process
        process.terminate()
        process.wait()
        print("Selenium server has been terminated")


@pytest.fixture(scope="function")
def server():
    _host = "localhost"
    _port = free_port()
    _path = os.path.join(
        os.path.dirname(
            os.path.dirname(
                os.path.dirname(
                    os.path.abspath(__file__)
                )
            )
        ),
        "selenium-server-4.26.0.jar",
    )

    args = [
        "java",
        "-jar",
        _path,
        "standalone",
        "--port",
        str(_port),
        "--selenium-manager",
        "true",
        "--enable-managed-downloads",
        "true",
    ]

    process = subprocess.Popen(args, stdout=subprocess.PIPE, stderr=subprocess.PIPE)

    def wait_for_server(url, timeout=60):
        start = time.time()
        while time.time() - start < timeout:
            try:
                urlopen(url)
                return True
            except OSError:
                time.sleep(0.2)
        return False

    if not wait_for_server(f"http://{_host}:{_port}/status"):
        raise RuntimeError(f"Selenium server did not start within the allotted time.")

    yield f"http://{_host}:{_port}"

    process.terminate()
    try:
        process.wait(timeout=10)
    except subprocess.TimeoutExpired:
        process.kill()


def _get_resource_path(file_name: str):
    if os.path.abspath("").endswith("tests"):
        path = os.path.abspath(f"resources/{file_name}")
    else:
        path = os.path.join(
                os.path.dirname(
                    os.path.dirname(
                        os.path.abspath(__file__)
                    )
            ),
            f"tests/resources/{file_name}",
        )
    return path


@pytest.fixture(scope="function")
def grid_server():
    _host = "localhost"
    _port = free_port()
    _username = "admin"
    _password = "myStrongPassword"
    _path_cert = _get_resource_path("tls.crt")
    _path_key = _get_resource_path("tls.key")
    _path_jks = _get_resource_path("server.jks")
    _truststore_pass = "seleniumkeystore"
    _path = os.path.join(
        os.path.dirname(
            os.path.dirname(
                os.path.dirname(
                    os.path.abspath(__file__)
                )
            )
        ),
        "selenium-server-4.26.0.jar",
    )

    args = [
        "java",
        f"-Djavax.net.ssl.trustStore={_path_jks}",
        f"-Djavax.net.ssl.trustStorePassword={_truststore_pass}",
        "-Djdk.internal.httpclient.disableHostnameVerification=true",
        "-jar",
        _path,
        "standalone",
        "--port",
        str(_port),
        "--selenium-manager",
        "true",
        "--enable-managed-downloads",
        "true",
        "--username",
        _username,
        "--password",
        _password,
        "--https-certificate",
        _path_cert,
        "--https-private-key",
        _path_key,
    ]

    process = subprocess.Popen(args, stdout=subprocess.PIPE, stderr=subprocess.PIPE)

    def wait_for_server(url, timeout=60):
        start = time.time()
        while time.time() - start < timeout:
            try:
                requests.get(url, verify=_path_cert, auth=HTTPBasicAuth(_username, _password))
                return True
            except OSError as e:
                print(e)
                time.sleep(0.2)
        return False

    if not wait_for_server(f"https://{_host}:{_port}/status"):
        raise RuntimeError(f"Selenium server did not start within the allotted time.")

    yield f"https://{_host}:{_port}"

    process.terminate()
    try:
        process.wait(timeout=10)
    except subprocess.TimeoutExpired:
        process.kill()
