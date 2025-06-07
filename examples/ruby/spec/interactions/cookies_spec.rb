

# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Cookies' do
  let(:driver) { start_session }

  it 'adds a cookie' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/blank.html'
    # Add cookie into current browser context
    driver.manage.add_cookie(name: 'key', value: 'value')
    # Verify cookie was added
    expect(driver.manage.cookie_named('key')[:value]).to eq('value')
  end

  it 'gets a named cookie' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/blank.html'
    # Add cookie into current browser context
    driver.manage.add_cookie(name: 'foo', value: 'bar')
    # Get cookie details with named cookie 'foo'
    cookie = driver.manage.cookie_named('foo')
    expect(cookie[:value]).to eq('bar')
  end

  it 'gets all cookies' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/blank.html'
    # Add cookies into current browser context
    driver.manage.add_cookie(name: 'test1', value: 'cookie1')
    driver.manage.add_cookie(name: 'test2', value: 'cookie2')
    # Get cookies
    cookies = driver.manage.all_cookies
    # Verify both cookies exist with correct values
    test1_cookie = cookies.find { |c| c[:name] == 'test1' }
    test2_cookie = cookies.find { |c| c[:name] == 'test2' }
    expect(test1_cookie[:value]).to eq('cookie1')
    expect(test2_cookie[:value]).to eq('cookie2')
  end

  it 'deletes a cookie by name' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/blank.html'
    driver.manage.add_cookie(name: 'test1', value: 'cookie1')
    # Delete cookie named
    driver.manage.delete_cookie('test1')
    # Verify cookie is deleted
    expect { driver.manage.cookie_named('test1') }.to raise_error(Selenium::WebDriver::Error::NoSuchCookieError)
  end

  it 'deletes all cookies' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/blank.html'
    # Add cookies into current browser context
    driver.manage.add_cookie(name: 'test1', value: 'cookie1')
    driver.manage.add_cookie(name: 'test2', value: 'cookie2')
    # Delete All cookies
    driver.manage.delete_all_cookies
    # Verify all cookies are deleted
    expect(driver.manage.all_cookies.size).to eq(0)
  end
end
