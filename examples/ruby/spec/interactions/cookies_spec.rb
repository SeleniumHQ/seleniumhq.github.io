# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Cookies' do
  before(:each) do
    @driver = Selenium::WebDriver.for :chrome
  end

  after(:each) do
    @driver.quit
  end

  it 'adds a cookie' do
    @driver.navigate.to 'https://www.selenium.dev/selenium/web/blank.html'
    @driver.manage.add_cookie(name: 'key', value: 'value')
  end

  it 'gets a named cookie' do
    @driver.navigate.to 'https://www.selenium.dev/selenium/web/blank.html'
    @driver.manage.add_cookie(name: 'foo', value: 'bar')
    cookie = @driver.manage.cookie_named('foo')
    expect(cookie[:value]).to eq('bar')
  end

  it 'gets all cookies' do
    @driver.navigate.to 'https://www.selenium.dev/selenium/web/blank.html'
    @driver.manage.add_cookie(name: 'test1', value: 'cookie1')
    @driver.manage.add_cookie(name: 'test2', value: 'cookie2')

    cookies = @driver.manage.all_cookies
    test1 = cookies.find { |c| c[:name] == 'test1' }
    test2 = cookies.find { |c| c[:name] == 'test2' }

    expect(test1[:value]).to eq('cookie1')
    expect(test2[:value]).to eq('cookie2')
  end

  it 'deletes a cookie by name' do
    @driver.navigate.to 'https://www.selenium.dev/selenium/web/blank.html'
    @driver.manage.add_cookie(name: 'test1', value: 'cookie1')
    @driver.manage.delete_cookie('test1')
    expect(@driver.manage.cookie_named('test1')).to be_nil
  end

  it 'deletes a cookie using cookie object' do
    @driver.navigate.to 'https://www.selenium.dev/selenium/web/blank.html'
    cookie = { name: 'test2', value: 'cookie2' }
    @driver.manage.add_cookie(cookie)
    @driver.manage.delete_cookie('test2')
    expect(@driver.manage.cookie_named('test2')).to be_nil
  end

  it 'deletes all cookies' do
    @driver.navigate.to 'https://www.selenium.dev/selenium/web/blank.html'
    @driver.manage.add_cookie(name: 'test1', value: 'cookie1')
    @driver.manage.add_cookie(name: 'test2', value: 'cookie2')
    @driver.manage.delete_all_cookies
    expect(@driver.manage.all_cookies).to be_empty
  end

  it 'creates SameSite cookies' do
    @driver.navigate.to 'http://www.example.com'

    cookie_strict = {
      name: 'key',
      value: 'value',
      same_site: 'Strict'
    }

    cookie_lax = {
      name: 'key',
      value: 'value',
      same_site: 'Lax'
    }

    @driver.manage.add_cookie(cookie_strict)
    @driver.manage.add_cookie(cookie_lax)

    puts cookie_strict[:same_site]
    puts cookie_lax[:same_site]
  end
end
