# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Logging' do
  describe 'Options' do
    let(:file_name) { File.expand_path('selenium.log') }
    after { FileUtils.rm_f(file_name) }

    it 'logs things' do
      logger = Selenium::WebDriver.logger

      logger.level = :debug

      logger.output = file_name

      logger.ignore(:jwp_caps, :logger_info)
      logger.allow(%i[selenium_manager example_id])

      logger.warn('this is a warning', id: :example_id)
      logger.info('this is useful information', id: :example_id)
      logger.debug('this is detailed debug information', id: :example_id)

      expect(File.readlines(file_name).grep(/\[:example_id\]/).size).to eq 3
    end
  end
end
