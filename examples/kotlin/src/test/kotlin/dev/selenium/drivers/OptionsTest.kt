package dev.selenium.drivers

import dev.selenium.BaseTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.openqa.selenium.Proxy
import org.openqa.selenium.chrome.ChromeOptions

class OptionsTest : BaseTest() {

    @Test
    fun setsProxy() {
        val proxy = Proxy()
        proxy.setHttpProxy("myproxy.com:8080")
        val options = ChromeOptions()
        options.setCapability("proxy", proxy)

        val capabilityObject = options.getCapability("proxy") as Proxy
        Assertions.assertNotNull(capabilityObject)
        Assertions.assertEquals("myproxy.com:8080", capabilityObject.httpProxy)
    }
}
