# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Chrome Options' do
  it 'excludes switches' do
    options = default_chrome_options

    options.exclude_switches << 'disable-popup-blocking'

    @driver = Selenium::WebDriver.for :chrome, options: options
  end
end
