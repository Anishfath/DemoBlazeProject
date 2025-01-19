package DemoBlazeMiniProject.DemoBlazeProject;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import Utils.DataProviderUtil;
import Utils.ScreenshotUtil;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;
    SoftAssert softAssert;

    @BeforeClass
    public void setUp() {
        // Initialize ChromeDriver
    	WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
        loginPage = new LoginPage(driver);
        softAssert = new SoftAssert();
    }

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviderUtil.class)
    public void testLogin(String username, String password) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click "Login" button with JavaScript fallback to avoid interception
        try {
            wait.until(ExpectedConditions.elementToBeClickable(loginPage.getLoginButton())).click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", loginPage.getLoginButton());
        }
        Thread.sleep(4000);
        // Enter username directly via its WebElement
        driver.findElement(By.id("loginusername")).sendKeys(username);

        // Enter password directly via its WebElement
        driver.findElement(By.id("loginpassword")).sendKeys(password);

        // Click on the "Log in" button
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
        Thread.sleep(2000);
        ScreenshotUtil.captureScreenshot(driver, "AfterLogin");


        // Wait for success verification (e.g., user profile loaded, logout button visible)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
        String welcomeText = driver.findElement(By.id("nameofuser")).getText();

        // Assert login success
        softAssert.assertTrue(welcomeText.contains(username), "Login failed!");
        softAssert.assertAll();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
