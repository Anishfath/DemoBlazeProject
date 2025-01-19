package DemoBlazeMiniProject.DemoBlazeProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class PurchasePage {
    private WebDriver driver;

    // Constructor
    public PurchasePage(WebDriver driver) {
        this.driver = driver;
    }

    // Get the list of product categories
    public List<WebElement> getCategoryList() {
        return driver.findElements(By.cssSelector(".list-group>a"));
    }

    // Select a category
    public void selectCategory(String category) {
        driver.findElement(By.linkText(category)).click();
    }

    // Get the list of products for the selected category
    public List<WebElement> getProductList() {
        return driver.findElements(By.cssSelector(".card-title>a"));
    }

    // Select a product
    public void selectProduct(String productName) {
        driver.findElement(By.linkText(productName)).click();
    }

    // Get the product title
    public String getProductTitle() {
        return driver.findElement(By.cssSelector("h2")).getText();
    }

    // Get the product price
    public String getProductPrice() {
        return driver.findElement(By.cssSelector(".price-container")).getText();
    }

    // Add the selected product to the cart
    public void clickAddToCart() {
        driver.findElement(By.linkText("Add to cart")).click();
    }

    // Handle the alert (click OK)
    public void handleAlert() {
        driver.switchTo().alert().accept();
    }

    // Navigate to the cart page
    public void navigateToCart() {
        driver.findElement(By.linkText("Cart")).click();
    }

    // Get the total price of the cart
    public double getTotalPrice() {
        String totalPriceText = driver.findElement(By.cssSelector(".total-price")).getText().replace("$", "").trim();
        return Double.parseDouble(totalPriceText);
    }

    // Clear the cart
    public void clearCart() {
        driver.findElement(By.xpath("//button[contains(text(), 'Clear cart')]")).click();
    }

    // Click the place order button
    public void clickPlaceOrderButton() {
        driver.findElement(By.xpath("//button[contains(text(), 'Place Order')]")).click();
    }

    // Fill in the user details
    public void fillUserDetails(String name, String country, String city) {
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("country")).sendKeys(country);
        driver.findElement(By.id("city")).sendKeys(city);
    }

    // Enter payment details
    public void enterPaymentDetails(String cardNumber, String expiryDate) {
        driver.findElement(By.id("card")).sendKeys(cardNumber);
        driver.findElement(By.id("month")).sendKeys(expiryDate.split("/")[0]);
        driver.findElement(By.id("year")).sendKeys(expiryDate.split("/")[1]);
       
    }

    // Click the purchase button
    public void clickPurchaseButton() {
        driver.findElement(By.xpath("//button[contains(text(), 'Purchase')]")).click();
    }
    
    // Check if the order is completed
    public boolean isOrderCompleted() {
        try {
        	return driver.findElement(By.xpath("//h2[contains(text(), 'Thank you for your purchase!')]")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Check if the payment is successful
    public boolean isPaymentSuccessful() {
        try {
            return driver.findElement(By.xpath("//h2[contains(text(), 'Payment successful')]")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}

