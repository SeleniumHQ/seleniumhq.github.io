# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Element Locators' do

  let(:driver) { start_session }
  let(:url) { 'https://www.selenium.dev/selenium/web/locators_tests/locators.html' }

  before { driver.get(url) }

  it 'finds element by class name' do
    ele1 = driver.find_element(class: 'information')
    expect(ele1).not_to be_nil
    expect(ele1.tag_name).to eq('input')
  end

  it 'finds element by css selector' do
    ele = driver.find_element(css: '#fname')
    expect(ele).not_to be_nil
    expect(ele.attribute('value')).to eq('Jane')
  end

  it 'finds element by id' do
    ele2 = driver.find_element(id: 'lname')
    expect(ele2).not_to be_nil
    expect(ele2.attribute('value')).to eq('Doe')
  end

  it 'finds element by name' do
    ele3 = driver.find_element(name: 'newsletter')
    expect(ele3).not_to be_nil
    expect(ele3.tag_name).to eq('input')
  end

  it 'finds element by link text' do
    ele = driver.find_element(link_text: 'Selenium Official Page')
    expect(ele).not_to be_nil
    expect(ele.attribute('href')).to eq('https://www.selenium.dev/')
  end

  it 'finds element by partial link text' do
    ele = driver.find_element(partial_link_text: 'Official Page')
    expect(ele).not_to be_nil
    expect(ele.attribute('href')).to eq('https://www.selenium.dev/')
  end

  it 'finds element by tag name' do
    ele = driver.find_element(tag_name: 'a')
    expect(ele).not_to be_nil
    expect(ele.attribute('href')).to eq('https://www.selenium.dev/')
  end

  it 'finds element by xpath' do
    ele = driver.find_element(xpath: "//input[@value='f']")
    expect(ele).not_to be_nil
    expect(ele.attribute('type')).to eq('radio')
  end

  context 'with relative locators' do
    it 'finds element above' do
      ele = driver.find_element(relative: { tag_name: 'input', above: { id: 'password' } })
      expect(ele).not_to be_nil
    end

    it 'finds element below' do
      ele = driver.find_element(relative: { tag_name: 'input', below: { id: 'email' } })
      expect(ele).not_to be_nil
    end

    it 'finds element to the left' do
      ele = driver.find_element(relative: { tag_name: 'button', left: { id: 'submit' } })
      expect(ele).not_to be_nil
    end

    it 'finds element to the right' do
      ele = driver.find_element(relative: { tag_name: 'button', right: { id: 'cancel' } })
      expect(ele).not_to be_nil
    end

    it 'finds near element' do
      ele = driver.find_element(relative: { tag_name: 'input', near: { id: 'lbl-email' } })
      expect(ele).not_to be_nil
    end

    it 'chains relative locators' do
      ele = driver.find_element(relative: { tag_name: 'button', below: { id: 'email' }, right: { id: 'cancel' } })
      expect(ele).not_to be_nil
    end
  end
end