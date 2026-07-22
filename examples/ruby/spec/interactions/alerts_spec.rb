# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Alerts' do
  let(:driver) { start_session }

  before do
    driver.navigate.to 'https://selenium.dev'
  end

  it 'interacts with an alert' do
    driver.execute_script 'alert("Hello, World!")'

    # Store the alert reference in a variable
    alert = driver.switch_to.alert

    # Get the text of the alert
    alert.text

    # Press on Cancel button
    alert.dismiss
  end

  it 'interacts with a confirm' do
    driver.execute_script 'confirm("Are you sure?")'

    # Store the alert reference in a variable
    alert = driver.switch_to.alert

    # Get the text of the alert
    alert.text

    # Press on Cancel button
    alert.dismiss
  end

  it 'interacts with a prompt' do
    driver.execute_script 'prompt("What is your name?")'

    # Store the alert reference in a variable
    alert = driver.switch_to.alert

    # Type a message
    alert.send_keys('selenium')

    # Press on Ok button
    alert.accept
  end
end
