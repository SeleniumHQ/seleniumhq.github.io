const { By } = require('selenium-webdriver')
const { suite } = require('selenium-webdriver/testing')
const assert = require('assert/strict')
const { Select } = require('selenium-webdriver')

suite(function (env) {
    describe('Select Tests', async function () {
        let driver

        before(async function () {
            driver = await env.builder().build()
            await driver.get('https://www.selenium.dev/selenium/web/formPage.html')
        })

        after(async () => await driver.quit())

        it('Select an option', async function () {
            const selectElement = await driver.findElement(By.name('selectomatic'))
            const select = new Select(selectElement)

            const twoElement = await driver.findElement(By.css('option[value=two]'))
            const fourElement = await driver.findElement(By.css('option[value=four]'))
            const countElement = await driver.findElement(By.css("option[value='still learning how to count, apparently']"))

            await select.selectByVisibleText('Four')
            assert.equal(true, await fourElement.isSelected())

            await select.selectByValue('two')
            assert.equal(true, await twoElement.isSelected())

            await select.selectByIndex(3)
            assert.equal(true, await countElement.isSelected())
        })

        it('Select by multiple options', async function () {
            const selectElement = await driver.findElement(By.name('multi'))
            const select = await new Select(selectElement)

            const hamElement = await driver.findElement(By.css('option[value=ham]'))
            const gravyElement = await driver.findElement(By.css("option[value='onion gravy']"))
            const eggElement = await driver.findElement(By.css('option[value=eggs]'))
            const sausageElement = await driver.findElement(By.css("option[value='sausages']"))

            const optionElements = await selectElement.findElements(By.css('option'))
            const optionList = await select.getOptions()
            assert.equal(optionList.length, optionElements.length)
            for (var index = 0; index < optionList.length; index++) {
                assert.equal(await optionList[index].getText(), await optionElements[index].getText())
            }

            const selectedOptionList = await select.getAllSelectedOptions()
            const expectedSelection = [eggElement, sausageElement]
            assert.equal(expectedSelection.length, selectedOptionList.length)
            for (var index = 0; index < selectedOptionList.length; index++) {
                assert.equal(await selectedOptionList[index].getText(), await expectedSelection[index].getText())
            }

            await select.selectByValue('ham')
            await select.selectByValue('onion gravy')
            assert.equal(true, await hamElement.isSelected())
            assert.equal(true, await gravyElement.isSelected())

            await select.deselectByValue('eggs')
            await select.deselectByValue('sausages')
            assert.equal(false, await eggElement.isSelected())
            assert.equal(false, await sausageElement.isSelected())
        })

        it('Try selecting disabled option', async function () {
            const selectElement = await driver.findElement(By.name('single_disabled'))
            const select = await new Select(selectElement)

            await assert.rejects(async () => { await select.selectByValue("disabled") }, {
                name: 'UnsupportedOperationError',
                message: 'You may not select a disabled option'
            })
        })
    })
})