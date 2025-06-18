# Licensed to the Software Freedom Conservancy (SFC) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The SFC licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#   http://www.apache.org/licenses/LICENSE-2.0
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Frames' do
  let(:driver) { start_session }

  it 'performs iframe switching operations' do
    driver.navigate.to 'https://www.selenium.dev/selenium/web/iframes.html'
    # --- Switch to iframe using WebElement ---
    iframe = driver.find_element(:id, 'iframe1')
    driver.switch_to.frame(iframe)
    expect(driver.page_source).to include('We Leave From Here')

    email_element = driver.find_element(:id, 'email')
    email_element.send_keys('admin@selenium.dev')
    email_element.clear
    driver.switch_to.default_content

    # --- Switch to iframe using name or ID ---
    iframe1 = driver.find_element(:name, 'iframe1-name')
    driver.switch_to.frame(iframe1)
    expect(driver.page_source).to include('We Leave From Here')

    email = driver.find_element(:id, 'email')
    email.send_keys('admin@selenium.dev')
    email.clear
    driver.switch_to.default_content

    # --- Switch to iframe using index ---
    driver.switch_to.frame(0)
    expect(driver.page_source).to include('We Leave From Here')
    # --- Final page content check ---
    driver.switch_to.default_content
    expect(driver.page_source).to include('This page has iframes')
  end
end
