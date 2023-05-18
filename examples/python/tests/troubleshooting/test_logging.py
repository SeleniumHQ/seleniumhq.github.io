import atexit
import logging
import os

import pytest


def test_logging():
    logger = logging.getLogger('selenium')

    logger.setLevel(logging.DEBUG)

    log_path = "selenium.log"
    handler = logging.FileHandler(log_path)
    logger.addHandler(handler)

    logging.getLogger('selenium.webdriver.remote').setLevel(logging.WARN)
    logging.getLogger('selenium.webdriver.common').setLevel(logging.DEBUG)

    logger.info("this is useful information")
    logger.warning("this is a warning")
    logger.debug("this is detailed debug information")

    try:
        with open(log_path, 'r') as fp:
            assert len(fp.readlines()) == 3
    finally:
        atexit.register(delete_path, log_path)
def delete_path(path):
    os.remove(path)
