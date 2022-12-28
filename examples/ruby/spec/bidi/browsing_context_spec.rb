# frozen_string_literal: true

require 'selenium-webdriver'

RSpec.describe 'Browsing Context' do
  let(:driver) do
    options = Selenium::WebDriver::Options.firefox
    options.add_option(:web_socket_url, true)
    Selenium::WebDriver.for :firefox, options: options
  end

  after do
    driver.quit
  end

  it 'test create a browsing context for given id' do
    id = driver.window_handle
    browsing_context = Selenium::WebDriver::BiDi::BrowsingContext.new(driver: driver, browsing_context_id: id)
    expect(browsing_context.id).to eq(id)
  end

  it 'test create a window' do
    browsing_context = Selenium::WebDriver::BiDi::BrowsingContext.new(driver: driver, type: :window)
    expect(browsing_context.id).not_to be_nil
  end

  it 'test create a window with a reference context' do
    browsing_context = Selenium::WebDriver::BiDi::BrowsingContext.new(driver: driver, type: :window,
                                                                      reference_context: driver.window_handle)
    expect(browsing_context.id).not_to be_nil
  end

  it 'test create a tab' do
    browsing_context = Selenium::WebDriver::BiDi::BrowsingContext.new(driver: driver, type: :tab)
    expect(browsing_context.id).not_to be_nil
  end

  it 'test create a tab with a reference context' do
    browsing_context = Selenium::WebDriver::BiDi::BrowsingContext.new(driver: driver, type: :tab,
                                                                      reference_context: driver.window_handle)
    expect(browsing_context.id).not_to be_nil
  end

  it 'test navigate to a url' do
    browsing_context = Selenium::WebDriver::BiDi::BrowsingContext.new(driver: driver, type: :tab)

    info = browsing_context.navigate url: 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'

    expect(browsing_context.id).not_to be_nil
    expect(info.navigation_id).to be_nil
    expect(info.url).to include('/bidi/logEntryAdded.html')
  end

  it 'test navigate to a url with readiness state' do
    browsing_context = Selenium::WebDriver::BiDi::BrowsingContext.new(driver: driver, type: :tab)

    info = browsing_context.navigate url: 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html',
                                     readiness_state: :complete

    expect(browsing_context.id).not_to be_nil
    expect(info.navigation_id).to be_nil
    expect(info.url).to include('/bidi/logEntryAdded.html')
  end

  it 'test get tree with a child' do
    browsing_context_id = driver.window_handle
    parent_window = Selenium::WebDriver::BiDi::BrowsingContext.new(driver: driver,
                                                                   browsing_context_id: browsing_context_id)
    parent_window.navigate(url: 'https://www.selenium.dev/selenium/web/iframes.html',
                           readiness_state: :complete)

    context_info = parent_window.get_tree
    expect(context_info.children.size).to eq(1)
    expect(context_info.id).to eq(browsing_context_id)
    expect(context_info.children[0]['url']).to include('formPage.html')
  end

  it 'test get tree with depth' do
    browsing_context_id = driver.window_handle
    parent_window = Selenium::WebDriver::BiDi::BrowsingContext.new(driver: driver,
                                                                   browsing_context_id: browsing_context_id)
    parent_window.navigate(url: 'https://www.selenium.dev/selenium/web/iframes.html',
                           readiness_state: :complete)

    context_info = parent_window.get_tree(max_depth: 0)
    expect(context_info.children).to be_nil
    expect(context_info.id).to eq(browsing_context_id)
  end

  it 'test close a window' do
    window1 = Selenium::WebDriver::BiDi::BrowsingContext.new(driver: driver, type: :window)
    window2 = Selenium::WebDriver::BiDi::BrowsingContext.new(driver: driver, type: :window)

    window2.close

    expect { window1.get_tree }.not_to raise_error
    expect { window2.get_tree }.to raise_error(Selenium::WebDriver::Error::WebDriverError)
  end

  it 'test close a tab' do
    tab1 = Selenium::WebDriver::BiDi::BrowsingContext.new(driver: driver, type: :tab)
    tab2 = Selenium::WebDriver::BiDi::BrowsingContext.new(driver: driver, type: :tab)

    tab2.close

    expect { tab1.get_tree }.not_to raise_error
    expect { tab2.get_tree }.to raise_error(Selenium::WebDriver::Error::WebDriverError)
  end
end
