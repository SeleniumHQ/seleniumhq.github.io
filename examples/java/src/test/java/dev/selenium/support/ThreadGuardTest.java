package dev.selenium.support;

import dev.selenium.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;

public class ThreadGuardTest extends BaseTest {

  @Test
  public void protectsDriverFromCrossThreadUse() throws InterruptedException {
    WebDriver protectedDriver = ThreadGuard.protect(startChromeDriver());

    Throwable[] caughtOnOtherThread = new Throwable[1];
    Runnable callFromOtherThread = () -> {
      try {
        protectedDriver.get("https://www.selenium.dev");
      } catch (Throwable t) {
        caughtOnOtherThread[0] = t;
      }
    };
    Thread otherThread = new Thread(callFromOtherThread);
    otherThread.start();
    otherThread.join();

    Assertions.assertNotNull(caughtOnOtherThread[0]);
  }
}
