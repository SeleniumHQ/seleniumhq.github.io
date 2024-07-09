import logging


def test_logging(log_path):
    logger = logging.getLogger('selenium')

    logger.setLevel(logging.DEBUG)

    handler = logging.FileHandler(log_path)
    logger.addHandler(handler)

    logging.getLogger('selenium.webdriver.remote').setLevel(logging.WARN)
    logging.getLogger('selenium.webdriver.common').setLevel(logging.DEBUG)

    logger.info("this is useful information")
    logger.warning("this is a warning")
    logger.debug("this is detailed debug information")

    with open(log_path, 'r') as fp:
        assert len(fp.readlines()) == 3
