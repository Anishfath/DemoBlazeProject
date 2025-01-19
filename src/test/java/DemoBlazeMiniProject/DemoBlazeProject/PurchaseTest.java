package DemoBlazeMiniProject.DemoBlazeProject;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Utils.ScreenshotUtil;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class PurchaseTest {
    WebDriver driver;
    SoftAssert softAssert;
    PurchasePage purchasePage;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
    	WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        softAssert = new SoftAssert();
        purchasePage = new PurchasePage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void verifyProductCategoriesAndDetails() throws InterruptedException {
        // Verify categories
        softAssert.assertTrue(purchasePage.getCategoryList().size() > 0, "Categories are not displayed!");
        Thread.sleep(2000);
        // Select a category
        purchasePage.selectCategory("Phones");
        softAssert.assertTrue(purchasePage.getProductList().size() > 0, "No products are displayed for Phones!");
        Thread.sleep(2000);
        // Select a product
        purchasePage.selectProduct("Samsung galaxy s6");
        softAssert.assertEquals(purchasePage.getProductTitle(), "Samsung galaxy s6", "Product name does not match!");
        softAssert.assertTrue(purchasePage.getProductPrice().contains("$"), "Product price is not displayed!");
        Thread.sleep(2000);
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = "verifyProductCategoriesAndDetails")
    public void addToCartTest() throws InterruptedException {
        // Add the product to the cart
        purchasePage.clickAddToCart();
        Thread.sleep(2000);
        // Handle the alert
        purchasePage.handleAlert();
        Thread.sleep(2000);
        // Wait for the cart link to be clickable and click it
        WebElement cartLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Cart")));
        cartLink.click();
        Thread.sleep(4000);
        // Verify product is added to the cart
        boolean isProductDisplayed = driver.getPageSource().contains("Samsung galaxy s6");
        softAssert.assertTrue(isProductDisplayed, "Product not added to the cart!");

        softAssert.assertAll();
    }
    @Test(dependsOnMethods = "addToCartTest")
    public void completeOrderTest() throws InterruptedException {
        purchasePage.clickPlaceOrderButton();
        purchasePage.fillUserDetails("Anish", "India", "Delhi");
        Thread.sleep(2000);
        purchasePage.enterPaymentDetails("4111111111111111", "12/25");
        Thread.sleep(2000);
        purchasePage.clickPurchaseButton();
        Thread.sleep(4000);
        ScreenshotUtil.captureScreenshot(driver, "Order Placed");
        softAssert.assertTrue(purchasePage.isOrderCompleted(), "Order was not completed successfully!");

        softAssert.assertAll();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

