# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Chrome Options' do
  it 'add arguments' do
    options = default_chrome_options

    options.args << '--start-maximized'

    @driver = Selenium::WebDriver.for :chrome, options: options
  end
end
