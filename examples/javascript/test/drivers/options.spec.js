const chrome = require('selenium-webdriver/chrome')
const proxy = require('selenium-webdriver/proxy')
const assert = require('node:assert')

describe('Options Test', function () {
  it('Sets proxy', function () {
    const options = new chrome.Options()
    options.setProxy(proxy.manual({ http: 'myproxy.com:8080' }))

    const capability = options.getProxy()
    assert.notStrictEqual(capability, undefined)
    assert.strictEqual(capability.httpProxy, 'myproxy.com:8080')
  })
})
