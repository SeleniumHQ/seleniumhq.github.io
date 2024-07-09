# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Element Finders' do
  let(:driver) { start_session }

  context 'without executing finders', skip: 'these are just examples, not actual tests' do
    it 'finds the first matching element' do
      driver.find_element(class: 'tomatoes')
    end

    it 'uses a subset of the dom to find an element' do
      fruits = driver.find_element(id: 'fruits')
      fruit = fruits.find_element(class: 'tomatoes')
    end

    it 'uses an optimized locator' do
      fruit = driver.find_element(css: '#fruits .tomatoes')
    end

    it 'finds all matching elements' do
      plants = driver.find_elements(tag_name: 'li')
    end

    it 'gets an element from a collection' do
      elements = driver.find_elements(:tag_name,'p')
      elements.each { |e| puts e.text }
    end

    it 'finds element from element' do
      element = driver.find_element(:tag_name,'div')
      elements = element.find_elements(:tag_name,'p')
      elements.each { |e| puts e.text }
    end

    it 'find active element' do
      driver.find_element(css: '[name="q"]').send_keys('webElement')
      attr = driver.switch_to.active_element.attribute('title')
    end
  end
end
