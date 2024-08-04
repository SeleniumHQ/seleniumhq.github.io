require 'spec_helper'

RSpec.describe 'Browser' do
  let(:driver) { start_session }

  it 'gets the current title' do
    driver.navigate.to 'https://www.selenium.dev/'
    current_title = driver.title
    expect(current_title).to eq 'Selenium'
  end

  it 'gets the current url' do
    driver.navigate.to 'https://www.selenium.dev/'
    current_url = driver.current_url
    expect(current_url).to eq 'https://www.selenium.dev/'
  end
end
