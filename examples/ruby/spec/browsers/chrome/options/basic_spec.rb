# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Chrome Options' do
  it 'start session with basic options' do
    options = Selenium::WebDriver::Chrome::Options.new
    @driver = Selenium::WebDriver.for :chrome, options: options
  end
end
