# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Element Finders' do
  let(:driver) { start_session }
  let(:locators_page) { 'https://www.selenium.dev/selenium/web/locators_tests/locators.html' }

  it 'finds the first matching element' do
    driver.navigate.to locators_page
    first_input = driver.find_element(class: 'information')

    expect(first_input.attribute('id')).to eq('fname')
  end

  it 'uses a subset of the dom to find an element' do
    driver.navigate.to locators_page
    form = driver.find_element(tag_name: 'form')
    input_element = form.find_element(class: 'information')

    expect(input_element.attribute('id')).to eq('fname')
  end

  it 'uses an optimized locator' do
    driver.navigate.to locators_page
    input_element = driver.find_element(css: 'form .information')

    expect(input_element.attribute('id')).to eq('fname')
  end

  it 'finds all matching elements' do
    driver.navigate.to locators_page
    inputs = driver.find_elements(tag_name: 'input')

    expect(inputs.size).to be > 1
  end

  # rubocop:disable RSpec/Output
  it 'gets an element from a collection' do
    driver.navigate.to locators_page
    elements = driver.find_elements(tag_name: 'p')
    elements.each { |e| puts "Paragraph text:#{e.text}" }

    expect(elements).not_to be_empty
  end

  it 'finds element from element' do
    driver.navigate.to locators_page
    form = driver.find_element(tag_name: 'form')
    elements = form.find_elements(tag_name: 'input')
    elements.each { |e| puts e.attribute('value') }

    expect(elements).not_to be_empty
  end
  # rubocop:enable RSpec/Output

  it 'finds the active element' do
    driver.navigate.to locators_page
    driver.find_element(css: '#fname').send_keys('webElement')
    attr = driver.switch_to.active_element.attribute('name')

    expect(attr).to eq('fname')
  end
end
