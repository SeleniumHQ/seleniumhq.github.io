# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Chrome Options' do
  it 'add extensions' do
    options = default_chrome_options

    extension_file_path = File.expand_path('../../../spec_support/extensions/webextensions-selenium-example.crx',
                                           __dir__)
    options.add_extension(extension_file_path)

    @driver = Selenium::WebDriver.for :chrome, options: options
    @driver.get('https://www.selenium.dev/selenium/web/blank.html')
    injected = @driver.find_element(:id, 'webextensions-selenium-example')
    expect(injected.text).to eq 'Content injected by webextensions-selenium-example'
  end
end
