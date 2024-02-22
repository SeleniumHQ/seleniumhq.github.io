package dev.selenium.bidirectional.webdriver_bidi;

import dev.selenium.BaseTest;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.Network;
import org.openqa.selenium.bidi.network.AddInterceptParameters;
import org.openqa.selenium.bidi.network.InterceptPhase;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class NetworkCommandsTest extends BaseTest {

    @BeforeEach
    public void setup() {
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("webSocketUrl", true);
        driver = new FirefoxDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    @Disabled
    void canAddIntercept() {
        try (Network network = new Network(driver)) {
            String intercept =
                    network.addIntercept(new AddInterceptParameters(InterceptPhase.BEFORE_REQUEST_SENT));
            Assertions.assertNotNull(intercept);
        }
    }

    @Test
    @Disabled
    void canRemoveIntercept() {
        try (Network network = new Network(driver)) {
            String intercept =
                    network.addIntercept(new AddInterceptParameters(InterceptPhase.BEFORE_REQUEST_SENT));
            Assertions.assertNotNull(intercept);
            network.removeIntercept(intercept);
        }
    }

    @Test
    @Disabled
    void canContinueWithAuthCredentials() {
        try (Network network = new Network(driver)) {
            network.addIntercept(new AddInterceptParameters(InterceptPhase.AUTH_REQUIRED));
            network.onAuthRequired(
                    responseDetails ->
                            network.continueWithAuth(
                                    responseDetails.getRequest().getRequestId(),
                                    new UsernameAndPassword("admin", "admin")));
            driver.get("https://the-internet.herokuapp.com/basic_auth");
            String successMessage = "Congratulations! You must have the proper credentials.";
            WebElement elementMessage = driver.findElement(By.tagName("p"));
            Assertions.assertEquals(successMessage, elementMessage.getText());
        }
    }

    @Test
    @Disabled
    void canContinueWithoutAuthCredentials() {
        try (Network network = new Network(driver)) {
            network.addIntercept(new AddInterceptParameters(InterceptPhase.AUTH_REQUIRED));
            network.onAuthRequired(
                    responseDetails ->
                            // Does not handle the alert
                            network.continueWithAuthNoCredentials(responseDetails.getRequest().getRequestId()));
            driver.get("https://the-internet.herokuapp.com/basic_auth");
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.dismiss();
            Assertions.assertTrue(driver.getPageSource().contains("Not authorized"));
        }
    }

    @Test
    @Disabled
    void canCancelAuth() {
        try (Network network = new Network(driver)) {
            network.addIntercept(new AddInterceptParameters(InterceptPhase.AUTH_REQUIRED));
            network.onAuthRequired(
                    responseDetails ->
                            // Does not handle the alert
                            network.cancelAuth(responseDetails.getRequest().getRequestId()));
            driver.get("https://the-internet.herokuapp.com/basic_auth");
            Assertions.assertTrue(driver.getPageSource().contains("Not authorized"));
        }
    }

    @Test
    @Disabled
    void canFailRequest() {
        try (Network network = new Network(driver)) {
            network.addIntercept(new AddInterceptParameters(InterceptPhase.BEFORE_REQUEST_SENT));
            network.onBeforeRequestSent(
                    responseDetails -> network.failRequest(responseDetails.getRequest().getRequestId()));
            driver.manage().timeouts().pageLoadTimeout(Duration.of(5, ChronoUnit.SECONDS));
            Assertions.assertThrows(TimeoutException.class, () -> driver.get("https://the-internet.herokuapp.com/basic_auth"));
            }
    }
}
