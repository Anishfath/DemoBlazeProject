package DemoBlazeMiniProject.DemoBlazeProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Utils.ScreenshotUtil;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class ProductTest {
    WebDriver driver;
    SoftAssert softAssert;
    ProductPage productPage;

    @BeforeClass
    public void setUp() {
    	WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        softAssert = new SoftAssert();
        productPage = new ProductPage(driver);
    }

    @Test
    public void verifyProductCategoriesAndDetails() throws InterruptedException {
        // Verify categories
        softAssert.assertTrue(productPage.getCategoryList().size() > 0, "Categories are not displayed!");
        Thread.sleep(2000);
        // Select a category
        productPage.selectCategory("Phones");
        softAssert.assertTrue(productPage.getProductList().size() > 0, "No products are displayed for Phones!");
         Thread.sleep(2000);
        // Select a product
        productPage.selectProduct("Samsung galaxy s6");
        softAssert.assertEquals(productPage.getProductTitle(), "Samsung galaxy s6", "Product name does not match!");
        Thread.sleep(2000);
        softAssert.assertTrue(productPage.getProductPrice().contains("$"), "Product price is not displayed!");

        softAssert.assertAll();
    }

    @Test(dependsOnMethods = "verifyProductCategoriesAndDetails")
    public void addToCartTest() throws InterruptedException {
        // Add the product to the cart
        productPage.clickAddToCart();
        Thread.sleep(4000);
        // Handle the alert
        productPage.handleAlert();

        // Validate that the product is added to the cart by going to the Cart page
       driver.findElement(By.linkText("Cart")).click();
       Thread.sleep(4000);
       boolean isProductDisplayed = driver.getPageSource().contains("Samsung galaxy s6");
        softAssert.assertTrue(isProductDisplayed, "Product not added to the cart!");
        Thread.sleep(4000);
        ScreenshotUtil.captureScreenshot(driver, "Product added to Cart");
        driver.findElement(By.xpath("//a[normalize-space()='Delete']")).click();
        Thread.sleep(2000);
        ScreenshotUtil.captureScreenshot(driver, "Product deleted from Cart");
       

        softAssert.assertAll();
    }

    

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}


