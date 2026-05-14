import pytest
from selenium.webdriver.common.window import WindowTypes


@pytest.mark.driver_type("bidi")
def test_create_window(driver):
    id = driver.browsing_context.create(type="window")
    assert id is not None


@pytest.mark.driver_type("bidi")
def test_create_tab(driver):
    id = driver.browsing_context.create(type="tab")
    assert id is not None


@pytest.mark.driver_type("bidi")
def test_navigate_to_url(driver):
    id = driver.browsing_context.create(type="tab")

    navigation_info = driver.browsing_context.navigate(
        context=id,
        url="https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html"
    )

    assert id is not None
    # navigation_id might be named 'navigation' in some versions
    assert navigation_info.get('navigation') is not None or navigation_info.get('navigation_id') is not None
    assert "/bidi/logEntryAdded.html" in navigation_info.get('url', '')


@pytest.mark.driver_type("bidi")
def test_get_tree(driver):
    reference_context_id = driver.current_window_handle

    driver.get("https://www.selenium.dev/selenium/web/iframes.html")
    tree = driver.browsing_context.get_tree(root=reference_context_id)

    assert tree is not None
    assert len(tree) > 0
    assert tree[0].context == reference_context_id


@pytest.mark.driver_type("bidi")
def test_close_window(driver):
    id = driver.browsing_context.create(type="window")
    driver.browsing_context.close(context=id)


@pytest.mark.driver_type("bidi")
def test_activate_browsing_context(driver):
    id = driver.browsing_context.create(type="tab")
    driver.browsing_context.activate(context=id)


@pytest.mark.driver_type("bidi")
def test_reload_browsing_context(driver):
    id = driver.browsing_context.create(type="tab")
    driver.browsing_context.navigate(
        context=id,
        url="https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html"
    )

    navigation_info = driver.browsing_context.reload(context=id)

    assert navigation_info is not None
