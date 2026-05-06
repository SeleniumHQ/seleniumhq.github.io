import pytest
from selenium.webdriver.common.window import WindowTypes


@pytest.mark.driver_type("bidi")
def test_create_browsing_context_for_given_id(driver):
    id = driver.current_window_handle
    browsing_context = (
        driver.bidi_connection.bidi_session.browsing_context.create(
            context_id=id
        )
    )
    assert browsing_context == id


@pytest.mark.driver_type("bidi")
def test_create_window(driver):
    browsing_context = (
        driver.bidi_connection.bidi_session.browsing_context.create(
            type_hint=WindowTypes.WINDOW
        )
    )
    assert browsing_context is not None


@pytest.mark.driver_type("bidi")
def test_create_tab(driver):
    browsing_context = (
        driver.bidi_connection.bidi_session.browsing_context.create(
            type_hint=WindowTypes.TAB
        )
    )
    assert browsing_context is not None


@pytest.mark.driver_type("bidi")
def test_navigate_to_url(driver):
    browsing_context = (
        driver.bidi_connection.bidi_session.browsing_context.create(
            type_hint=WindowTypes.TAB
        )
    )

    navigation_info = (
        driver.bidi_connection.bidi_session.browsing_context.navigate(
            context=browsing_context,
            url="https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html"
        )
    )

    assert browsing_context is not None
    assert navigation_info.get('navigation_id') is not None
    assert "/bidi/logEntryAdded.html" in navigation_info.get('url', '')


@pytest.mark.driver_type("bidi")
def test_get_tree(driver):
    reference_context_id = driver.current_window_handle

    driver.get("https://www.selenium.dev/selenium/web/iframes.html")
    tree = (
        driver.bidi_connection.bidi_session.browsing_context.get_tree(
            root=reference_context_id
        )
    )

    assert tree is not None
    assert len(tree) > 0
    assert tree[0].get('context') == reference_context_id


@pytest.mark.driver_type("bidi")
def test_close_window(driver):
    browsing_context = (
        driver.bidi_connection.bidi_session.browsing_context.create(
            type_hint=WindowTypes.WINDOW
        )
    )

    driver.bidi_connection.bidi_session.browsing_context.close(
        context=browsing_context
    )
    # If no exception is raised, the close was successful


@pytest.mark.driver_type("bidi")
def test_activate_browsing_context(driver):
    browsing_context = (
        driver.bidi_connection.bidi_session.browsing_context.create(
            type_hint=WindowTypes.TAB
        )
    )

    driver.bidi_connection.bidi_session.browsing_context.activate(
        context=browsing_context
    )
    # If no exception is raised, the activate was successful


@pytest.mark.driver_type("bidi")
def test_reload_browsing_context(driver):
    browsing_context = (
        driver.bidi_connection.bidi_session.browsing_context.create(
            type_hint=WindowTypes.TAB
        )
    )
    driver.bidi_connection.bidi_session.browsing_context.navigate(
        context=browsing_context,
        url="https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html"
    )

    navigation_info = (
        driver.bidi_connection.bidi_session.browsing_context.reload(
            context=browsing_context
        )
    )

    assert navigation_info is not None
