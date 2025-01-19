package DemoBlazeMiniProject.DemoBlazeProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductPage {
    WebDriver driver;

    // Locators
    private By categoryList = By.cssSelector(".list-group-item");
    private By productList = By.className("card-title");
    private By productTitle = By.className("name");
    private By productPrice = By.className("price-container");
    private By addToCartButton = By.linkText("Add to cart"); 
    

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getCategoryList() {
        return driver.findElements(categoryList);
    }

    public void selectCategory(String categoryName) {
        driver.findElement(By.linkText(categoryName)).click();
    }

    public List<WebElement> getProductList() {
        return driver.findElements(productList);
    }

    public void selectProduct(String productName) {
        driver.findElement(By.linkText(productName)).click();
    }

    public String getProductTitle() {
        return driver.findElement(productTitle).getText();
    }

    public String getProductPrice() {
        return driver.findElement(productPrice).getText();
    }

    public void clickAddToCart() {
        driver.findElement(addToCartButton).click();
    }

    public void handleAlert() {
        driver.switchTo().alert().accept(); // Accept the alert after clicking "Add to Cart"
    }

	
}
