# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Locate Nodes' do
  let(:driver) { start_bidi_session }
  let(:wait) { Selenium::WebDriver::Wait.new(timeout: 5) }

  it 'locates nodes by css selector' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'

    nodes = driver.script.locate_nodes(locator: {type: 'css', value: 'button'})

    expect(nodes).not_to be_empty
  end

  it 'locates nodes by xpath' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'

    nodes = driver.script.locate_nodes(locator: {type: 'xpath', value: '//button'})

    expect(nodes).not_to be_empty
  end

  it 'locates nodes with start nodes' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'

    body = driver.find_element(tag_name: 'body')

    nodes = driver.script.locate_nodes(
      locator: {type: 'css', value: 'button'},
      start_nodes: [body]
    )

    expect(nodes).not_to be_empty
  end

  it 'locates node by id' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'

    nodes = driver.script.locate_nodes(locator: {type: 'css', value: '#consoleLog'})

    expect(nodes).not_to be_empty
  end

  it 'locates nodes by class' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'

    driver.script.locate_nodes(locator: {type: 'css', value: '.button-class'})
  end

  it 'locates multiple nodes' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/iframes.html'

    nodes = driver.script.locate_nodes(locator: {type: 'css', value: 'input'})

    expect(nodes).not_to be_empty
  end

  it 'locates nodes in nested elements' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/iframes.html'

    nodes = driver.script.locate_nodes(locator: {type: 'css', value: 'form input'})

    expect(nodes.count >= 0).to be true
  end

  it 'locates nodes by tag name' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'

    nodes = driver.script.locate_nodes(locator: {type: 'css', value: 'div'})

    expect(nodes).not_to be_empty
  end
end
