# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Chrome Options' do
  it 'keeps browser open' do
    options = default_chrome_options

    options.detach = true

    @driver = Selenium::WebDriver.for :chrome, options: options
  end
end
