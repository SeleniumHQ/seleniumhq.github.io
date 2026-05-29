# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Locate Nodes' do
  let(:driver) { start_bidi_session }

  it 'locates nodes by css selector' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'

    nodes = driver.find_elements(css: 'button')

    expect(nodes).not_to be_empty
  end

  it 'locates nodes by xpath' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'

    nodes = driver.find_elements(xpath: '//button')

    expect(nodes).not_to be_empty
  end

  it 'locates node by id' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'

    node = driver.find_element(id: 'consoleLog')

    expect(node).not_to be_nil
  end

  it 'locates nodes by tag name' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'

    nodes = driver.find_elements(tag_name: 'div')

    expect(nodes).not_to be_empty
  end
end
