package DemoBlazeMiniProject.DemoBlazeProject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Utils.ScreenshotUtil;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class LogOutTest {
    WebDriver driver;
    LogOutPage logoutPage;

    @BeforeClass
    public void setUp() {
    	WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        logoutPage = new LogOutPage(driver);
    }
    @Test
    public void validateLogoutFlow() throws Exception {
        try {
            // Step 1: Log in
            logoutPage.login("Anish@Fath", "98765"); 
            Thread.sleep(2000);
            ScreenshotUtil.captureScreenshot(driver, "AfterLogin");
            Thread.sleep(2000);

            // Step 2: Validate login success
            Assert.assertTrue(logoutPage.isLoginSuccessful(), "Login failed! Logout button not visible.");
            Thread.sleep(2000);
            // Step 3: Perform logout
            logoutPage.logout();
            Thread.sleep(2000);
            ScreenshotUtil.captureScreenshot(driver, "AfterLogout");

            // Step 4: Validate logout success
            Assert.assertTrue(logoutPage.isLogoutSuccessful(), "Logout failed! Login button not visible.");
        } catch (Exception e) {
            ScreenshotUtil.captureScreenshot(driver, "ErrorOccurred");
            throw e; // Re-throw the exception after capturing a screenshot
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}