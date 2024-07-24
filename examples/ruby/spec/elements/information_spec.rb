# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Element Information' do
  let(:driver) { start_session }
  let(:url) { 'https://www.selenium.dev/selenium/web/inputs.html' }

  before { driver.get(url) }

  it 'checks if an element is displayed' do
    displayed_value = driver.find_element(name: 'email_input').displayed?
    expect(displayed_value).to be_truthy
  end

  it 'checks if an element is enabled' do
    enabled_value = driver.find_element(name: 'email_input').enabled?
    expect(enabled_value).to be_truthy
  end

  it 'checks if an element is selected' do
    selected_value = driver.find_element(name: 'email_input').selected?
    expect(selected_value).to be_falsey
  end

  it 'gets the tag name of an element' do
    tag_name = driver.find_element(name: 'email_input').tag_name
    expect(tag_name).to eq('input')
  end

  it 'gets the size and position of an element' do
    size = driver.find_element(name: 'email_input').size
    expect(size.width).to eq(147)
    expect(size.height).to eq(22)
  end

  it 'gets the css value of an element' do
    css_value = driver.find_element(name: 'email_input').css_value('background-color')
    expect(css_value).to eq('rgba(255, 255, 255, 1)')
  end

  it 'gets the text of an element' do
    text = driver.find_element(xpath: '//h1').text
    expect(text).to eq('Testing Inputs')
  end

  it 'gets the attribute value of an element' do
    attribute_value = driver.find_element(name: 'number_input').attribute('value')
    expect(attribute_value).to eq '42'
  end
end
