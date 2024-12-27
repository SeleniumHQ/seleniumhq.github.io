import os
import pytest
import sys
from urllib3.util import Retry, Timeout
from selenium import webdriver
from selenium.webdriver.common.proxy import Proxy
from selenium.webdriver.common.proxy import ProxyType
from selenium.webdriver.remote.client_config import ClientConfig


@pytest.mark.skipif(sys.platform == "win32", reason="Gets stuck on Windows, passes locally")
def test_start_remote_with_client_config(grid_server):
    proxy = Proxy({"proxyType": ProxyType.AUTODETECT})
    retries = Retry(connect=2, read=2, redirect=2)
    timeout = Timeout(connect=300, read=3600)
    client_config = ClientConfig(remote_server_addr=grid_server,
                                 proxy=proxy,
                                 init_args_for_pool_manager={
                                     "init_args_for_pool_manager": {"retries": retries, "timeout": timeout}},
                                 ca_certs=_get_resource_path("tls.crt"),
                                 username="admin", password="myStrongPassword")
    options = webdriver.ChromeOptions()
    driver = webdriver.Remote(command_executor=grid_server, options=options, client_config=client_config)
    driver.get("https://www.selenium.dev")
    driver.quit()


@pytest.mark.skipif(sys.platform == "win32", reason="Gets stuck on Windows, passes locally")
def test_start_remote_ignore_certs(grid_server):
    proxy = Proxy({"proxyType": ProxyType.AUTODETECT})
    client_config = ClientConfig(remote_server_addr=grid_server,
                                 proxy=proxy,
                                 timeout=3600,
                                 ignore_certificates=True,
                                 username="admin", password="myStrongPassword")
    options = webdriver.ChromeOptions()
    driver = webdriver.Remote(command_executor=grid_server, options=options, client_config=client_config)
    driver.get("https://www.selenium.dev")
    driver.quit()


def _get_resource_path(file_name: str):
    if os.path.abspath("").endswith("tests"):
        path = os.path.abspath(f"resources/{file_name}")
    else:
        path = os.path.abspath(f"tests/resources/{file_name}")
    return path
