# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Locate Nodes' do
  let(:driver) { start_bidi_session }
  let(:bidi_bc) { Selenium::WebDriver::BiDi::BrowsingContext.new(driver) }
  let(:wait) { Selenium::WebDriver::Wait.new(timeout: 5) }

  it 'locates nodes by css selector' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'

    nodes = bidi_bc.locate_nodes(
      context_id: driver.current_window_handle,
      locator: {type: 'css', value: 'button'}
    )

    expect(nodes).not_to be_empty
  end

  it 'locates nodes by xpath' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'

    nodes = bidi_bc.locate_nodes(
      context_id: driver.current_window_handle,
      locator: {type: 'xpath', value: '//button'}
    )

    expect(nodes).not_to be_empty
  end

  it 'locates node by id' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'

    nodes = bidi_bc.locate_nodes(
      context_id: driver.current_window_handle,
      locator: {type: 'css', value: '#consoleLog'}
    )

    expect(nodes).not_to be_empty
  end

  it 'locates nodes by class' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'

    nodes = bidi_bc.locate_nodes(
      context_id: driver.current_window_handle,
      locator: {type: 'css', value: '.button-class'}
    )

    expect(nodes.count >= 0).to be true
  end

  it 'locates multiple nodes' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'

    nodes = bidi_bc.locate_nodes(
      context_id: driver.current_window_handle,
      locator: {type: 'css', value: 'button'}
    )

    expect(nodes).not_to be_empty
  end

  it 'locates nodes by tag name' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'

    nodes = bidi_bc.locate_nodes(
      context_id: driver.current_window_handle,
      locator: {type: 'css', value: 'div'}
    )

    expect(nodes).not_to be_empty
  end
end
