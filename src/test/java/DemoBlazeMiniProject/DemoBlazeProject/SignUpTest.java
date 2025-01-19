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
import java.util.UUID;

public class SignUpTest {
    WebDriver driver;
    SignUpPage signUpPage;

    @BeforeClass
    public void setUp() {
    	WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        signUpPage = new SignUpPage(driver);
    }

    @Test
    public void validateSignUp() throws Exception {
        // Generate a unique username using UUID
        String uniqueUsername = "user_" + UUID.randomUUID().toString().substring(0, 8);
        String password = "123456";

        try {
            // Perform signup
            signUpPage.clickSignUpButton();
            signUpPage.enterCredentials(uniqueUsername, password);
            Thread.sleep(2000);
            signUpPage.submitSignUp();
            Thread.sleep(2000);
           
            // Handle alert for successful signup
            String alertMessage = signUpPage.getAlertMessage();
            signUpPage.acceptAlert();
            // Capture a screenshot after signup
            ScreenshotUtil.captureScreenshot(driver, "SignUpSuccess_" + uniqueUsername);
      
            // Validate the alert message
            Assert.assertTrue(alertMessage.contains("Sign up successful"), "Signup failed! Unexpected alert message.");

            

        } catch (Exception e) {
        	// Handle alert if user already exists
            String alertMessage = signUpPage.getAlertMessage();
            signUpPage.acceptAlert();

            // Validate the alert message
            Assert.assertTrue(alertMessage.contains("already exists"), "Unexpected alert message.");
            ScreenshotUtil.captureScreenshot(driver, "SignUpFailure_AlreadyExists");
            
            throw e; 
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
