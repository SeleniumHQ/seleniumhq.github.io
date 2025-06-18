# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Element Locators', skip: 'These are reference following the documentation example' do
  it 'finds element by class name' do
    driver.find_element(class: 'information')
  end

  it 'finds element by css selector' do
    driver.find_element(css: '#fname')
  end

  it 'finds element by id' do
    driver.find_element(id: 'lname')
  end

  it 'find element by name' do
    driver.find_element(name: 'newsletter')
  end

  it 'finds element by link text' do
    driver.find_element(link_text: 'Selenium Official Page')
  end

  it 'finds element by partial link text' do
    driver.find_element(partial_link_text: 'Official Page')
  end

  it 'finds element by tag name' do
    driver.find_element(tag_name: 'a')
  end

  it 'finds element by xpath' do
    driver.find_element(xpath: "//input[@value='f']")
  end

  context 'with relative locators' do
    it 'finds element above' do
      driver.find_element({relative: {tag_name: 'input', above: {id: 'password'}}})
    end

    it 'finds element below' do
      driver.find_element({relative: {tag_name: 'input', below: {id: 'email'}}})
    end

    it 'finds element to the left' do
      driver.find_element({relative: {tag_name: 'button', left: {id: 'submit'}}})
    end

    it 'finds element to the right' do
      driver.find_element({relative: {tag_name: 'button', right: {id: 'cancel'}}})
    end

    it 'finds near element' do
      driver.find_element({relative: {tag_name: 'input', near: {id: 'lbl-email'}}})
    end

    it 'chains relative locators' do
      driver.find_element({relative: {tag_name: 'button', below: {id: 'email'}, right: {id: 'cancel'}}})
    end
  end
end
