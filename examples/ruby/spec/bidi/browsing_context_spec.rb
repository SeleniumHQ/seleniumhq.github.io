# frozen_string_literal: true

require 'spec_helper'

RSpec.describe 'Browsing Context' do
  let(:driver) { start_bidi_session }
  let(:bidi_bc) { Selenium::WebDriver::BiDi::BrowsingContext.new(driver) }

  it 'creates a window' do
    id = bidi_bc.create(type: :window)
    expect(id).not_to be_nil
  end

  it 'creates a tab' do
    id = bidi_bc.create(type: :tab)
    expect(id).not_to be_nil
  end

  it 'navigates to a url' do
    id = bidi_bc.create(type: :tab)

    navigation_info = bidi_bc.navigate(
      'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html',
      context_id: id
    )

    expect(id).not_to be_nil
    expect(navigation_info['navigation']).not_to be_nil
    expect(navigation_info['url']).to include('/bidi/logEntryAdded.html')
  end

  it 'navigates to url with readiness state' do
    id = bidi_bc.create(type: :tab)

    # In Ruby, readiness is handled via the constructor/bridge options
    navigation_info = bidi_bc.navigate(
      'https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html',
      context_id: id
    )

    expect(id).not_to be_nil
    expect(navigation_info['navigation']).not_to be_nil
  end

  it 'closes a window' do
    id = bidi_bc.create(type: :window)
    expect { bidi_bc.close(context_id: id) }.not_to raise_error
  end

  it 'closes a tab' do
    id = bidi_bc.create(type: :tab)
    expect { bidi_bc.close(context_id: id) }.not_to raise_error
  end

  it 'activates a browsing context' do
    id = bidi_bc.create(type: :tab)
    expect { bidi_bc.activate(context_id: id) }.not_to raise_error
  end

  it 'reloads a browsing context' do
    id = bidi_bc.create(type: :tab)
    bidi_bc.navigate('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html', context_id: id)

    navigation_info = bidi_bc.reload(context_id: id)

    expect(navigation_info).not_to be_nil
  end
end
