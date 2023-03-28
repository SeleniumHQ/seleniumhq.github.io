# frozen_string_literal: true

require 'spec_helper'

Selenium::WebDriver.logger.level = :debug

RSpec.describe 'Internet Explorer', exclusive: {platform: :windows} do
  it 'basic options' do
    options = Selenium::WebDriver::Options.ie
    @driver = Selenium::WebDriver.for :ie, options: options
  end
end
