import org.junit.jupiter.api.Test;

public class FooTest {
  private EditIssue editIssue;

  // Removed @Before
  public void prepareComponents() {
    WebDriver driver = new FirefoxDriver();

    ProjectPage project = new ProjectPage(driver, "selenium");
    SecuredPage securedPage = new SecuredPage(driver, project, "example", "top secret");
    editIssue = new EditIssue(driver, securedPage);
  }

  // Removed @Test
  public void demonstrateNestedLoadableComponents() {
    editIssue.get();

    editIssue.title.sendKeys('Title');
    editIssue.body.sendKeys('What Happened');
    editIssue.setHowToReproduce('How to Reproduce');
    editIssue.setLogOutput('Log Output');
    editIssue.setOperatingSystem('Operating System');
    editIssue.setSeleniumVersion('Selenium Version');
    editIssue.setBrowserVersion('Browser Version');
    editIssue.setDriverVersion('Driver Version');
    editIssue.setUsingGrid('I Am Using Grid');
  }

}