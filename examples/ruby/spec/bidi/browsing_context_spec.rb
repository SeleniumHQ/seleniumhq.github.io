# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Browsing Context' do
  let(:driver) { start_bidi_session }
  let(:wait) { Selenium::WebDriver::Wait.new(timeout: 5) }

  it 'creates browsing context for given id' do
    id = driver.window_handle
    browsing_context = Selenium::WebDriver::BiDi::BrowsingContext.new(
      driver, context_id: id
    )
    expect(browsing_context.id).to eq(id)
  end

  it 'creates a window' do
    browsing_context = Selenium::WebDriver::BiDi::BrowsingContext.new(
      driver, type_hint: :window
    )
    expect(browsing_context.id).not_to be_nil
  end

  it 'creates a tab' do
    browsing_context = Selenium::WebDriver::BiDi::BrowsingContext.new(
      driver, type_hint: :tab
    )
    expect(browsing_context.id).not_to be_nil
  end

  it 'navigates to a url' do
    browsing_context = Selenium::WebDriver::BiDi::BrowsingContext.new(
      driver, type_hint: :tab
    )

    navigation_info = browsing_context.navigate(
      'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html'
    )

    expect(browsing_context.id).not_to be_nil
    expect(navigation_info['navigation_id']).not_to be_nil
    expect(navigation_info['url']).to include('/bidi/logEntryAdded.html')
  end

  it 'navigates to url with readiness state' do
    browsing_context = Selenium::WebDriver::BiDi::BrowsingContext.new(
      driver, type_hint: :tab
    )

    navigation_info = browsing_context.navigate(
      'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html',
      wait: :complete
    )

    expect(browsing_context.id).not_to be_nil
    expect(navigation_info['navigation_id']).not_to be_nil
  end

  it 'gets tree with children' do
    reference_context_id = driver.window_handle
    browsing_context = Selenium::WebDriver::BiDi::BrowsingContext.new(
      driver, context_id: reference_context_id
    )

    browsing_context.navigate('https://www.selenium.dev/selenium/web/iframes.html')
    tree = browsing_context.get_tree

    expect(tree).not_to be_empty
    expect(tree.first['context']).to eq(reference_context_id)
    expect(tree.first['children']).not_to be_empty
  end

  it 'gets tree with depth' do
    reference_context_id = driver.window_handle
    browsing_context = Selenium::WebDriver::BiDi::BrowsingContext.new(
      driver, context_id: reference_context_id
    )

    browsing_context.navigate('https://www.selenium.dev/selenium/web/iframes.html')
    tree = browsing_context.get_tree(max_depth: 1)

    expect(tree).not_to be_empty
  end

  it 'gets all top level contexts' do
    contexts = Selenium::WebDriver::BiDi::BrowsingContext.all_top_level(driver)

    expect(contexts).not_to be_empty
    expect(contexts.first.is_a?(Selenium::WebDriver::BiDi::BrowsingContext)).to be true
  end

  it 'closes a window' do
    browsing_context = Selenium::WebDriver::BiDi::BrowsingContext.new(
      driver, type_hint: :window
    )

    expect { browsing_context.close }.not_to raise_error
  end

  it 'closes a tab' do
    browsing_context = Selenium::WebDriver::BiDi::BrowsingContext.new(
      driver, type_hint: :tab
    )

    expect { browsing_context.close }.not_to raise_error
  end

  it 'activates a browsing context' do
    browsing_context = Selenium::WebDriver::BiDi::BrowsingContext.new(
      driver, type_hint: :tab
    )

    expect { browsing_context.activate }.not_to raise_error
  end

  it 'reloads a browsing context' do
    browsing_context = Selenium::WebDriver::BiDi::BrowsingContext.new(
      driver, type_hint: :tab
    )

    browsing_context.navigate('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')

    navigation_info = browsing_context.reload

    expect(navigation_info).not_to be_nil
  end

  it 'prints to pdf' do
    browsing_context = Selenium::WebDriver::BiDi::BrowsingContext.new(
      driver, context_id: driver.window_handle
    )

    browsing_context.navigate('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')

    pdf_data = browsing_context.print

    expect(pdf_data).not_to be_nil
    expect(pdf_data).to be_a(String)
  end
end
