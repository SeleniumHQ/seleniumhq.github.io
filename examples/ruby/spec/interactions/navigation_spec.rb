require 'spec_helper'

RSpec.describe 'Browser' do
  let(:driver) { start_session }

  it 'navigates to a page' do
    driver.navigate.to 'https://www.selenium.dev/'
    driver.get 'https://www.selenium.dev/'
    expect(driver.current_url).to eq 'https://www.selenium.dev/'
  end

  it 'navigates back' do
    driver.navigate.to 'https://www.selenium.dev/'
    driver.navigate.to 'https://www.selenium.dev/selenium/web/inputs.html'
    driver.navigate.back
    expect(driver.current_url).to eq 'https://www.selenium.dev/'
  end

  it 'navigates forward' do
    driver.navigate.to 'https://www.selenium.dev/'
    driver.navigate.to 'https://www.selenium.dev/selenium/web/inputs.html'
    driver.navigate.back
    driver.navigate.forward
    expect(driver.current_url).to eq 'https://www.selenium.dev/selenium/web/inputs.html'
  end

  it 'refreshes the page' do
    driver.navigate.to 'https://www.selenium.dev/'
    driver.navigate.refresh
    expect(driver.current_url).to eq 'https://www.selenium.dev/'
  end
end
