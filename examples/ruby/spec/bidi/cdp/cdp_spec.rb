# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Logging' do
  let(:driver) { start_session }

  it 'sets cookie' do
    driver.execute_cdp('Network.setCookie',
                       name: 'cheese',
                       value: 'gouda',
                       domain: 'www.selenium.dev',
                       secure: true)

    driver.get('https://www.selenium.dev')
    cheese = driver.manage.cookie_named('cheese')

    expect(cheese[:value]).to eq 'gouda'
  end
end
