# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Internet Explorer', exclusive: {platform: :windows} do
  it 'basic options' do
    Selenium::WebDriver.logger.level = :debug
    options = Selenium::WebDriver::Options.ie
    @driver = Selenium::WebDriver.for :ie, options: options
  end
  after(:each) do 
    Selenium::WebDriver.logger.level = :error
  end
end
