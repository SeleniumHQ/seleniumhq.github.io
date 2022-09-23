# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Chrome' do

  let(:driver) { start_session }

  before do
    driver.get('https://www.selenium.dev/selenium/web/formPage.html')
  end

  it 'select options' do
    select_element = driver.find_element(name: 'selectomatic')
    select = Selenium::WebDriver::Support::Select.new(select_element)

    two_element = driver.find_element(css: 'option[value=two]')
    four_element = driver.find_element(css: 'option[value=four]')
    count_element = driver.find_element(css: "option[value='still learning how to count, apparently']")

    select.select_by(:text,'Four')
    expect(four_element).to be_selected

    select.select_by(:value,'two')
    expect(two_element).to be_selected

    select.select_by(:index,3)
    expect(count_element).to be_selected
  end

  it 'select multiple options' do
    select_element = driver.find_element(name: 'multi')
    select = Selenium::WebDriver::Support::Select.new(select_element)

    ham_element = driver.find_element(css: 'option[value=ham]')
    gravy_element = driver.find_element(css: "option[value='onion gravy']")
    egg_element = driver.find_element(css: 'option[value=eggs]')
    sausage_element = driver.find_element(css: "option[value='sausages']")

    option_elements = select_element.find_elements(tag_name: 'option')
    option_list = select.options
    expect(option_elements).to eq option_list

    selected_option_list = select.selected_options
    expected_selection = [egg_element, sausage_element]
    expect(selected_option_list).to eq expected_selection

    select.select_by(:value, 'ham')
    select.select_by(:value, 'onion gravy')
    expect(ham_element).to be_selected
    expect(gravy_element).to be_selected

    select.deselect_by(:value, 'eggs')
    select.deselect_by(:value, 'sausages')
    expect(egg_element).not_to be_selected
    expect(sausage_element).not_to be_selected
  end

  it 'disabled options' do
    select_element = driver.find_element(name: 'single_disabled')
    select = Selenium::WebDriver::Support::Select.new(select_element)

    expect {
      select.select_by(:value, 'disabled')
    }.to raise_exception(Selenium::WebDriver::Error::UnsupportedOperationError)
  end
end
