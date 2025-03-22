# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Frames Test' do
  it 'interacts with elements inside iframes' do
    driver = Selenium::WebDriver.for :chrome
    driver.manage.timeouts.implicit_wait = 0.5
    
    # Navigate to URL
    driver.get('https://www.selenium.dev/selenium/web/iframes.html')
    
    # Switch to iframe using WebElement
    iframe = driver.find_element(id: 'iframe1')
    driver.switch_to.frame(iframe)
    expect(driver.page_source.include?('We Leave From Here')).to be true
    
    # Interact with email field
    email_element = driver.find_element(id: 'email')
    email_element.send_keys('admin@selenium.dev')
    email_element.clear
    driver.switch_to.default_content
    
    # Switch to iframe using name
    iframe=driver.find_element(name: 'iframe1-name')
    driver.switch_to.frame(iframe)
    expect(driver.page_source.include?('We Leave From Here')).to be true
    
    email = driver.find_element(id: 'email')
    email.send_keys('admin@selenium.dev')
    email.clear
    driver.switch_to.default_content
    
    # Switch to iframe using index
    driver.switch_to.frame(0)
    expect(driver.page_source.include?('We Leave From Here')).to be true
    
    # Leave frame
    driver.switch_to.default_content
    expect(driver.page_source.include?('This page has iframes')).to be true
    
    # Quit the browser
    driver.quit
  end
end
