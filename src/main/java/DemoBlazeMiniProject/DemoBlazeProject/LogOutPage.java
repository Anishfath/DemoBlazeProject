package DemoBlazeMiniProject.DemoBlazeProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogOutPage {
    WebDriver driver;

    // Locators for login elements
    private By usernameField = By.id("loginusername");
    private By passwordField = By.id("loginpassword");
    private By loginButton = By.xpath("//button[text()='Log in']");
    private By loginModalButton = By.id("login2");

    // Locators for logout elements
    private By logoutButton = By.id("logout2");

    public LogOutPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to log in
    public void login(String username, String password) {
        // Open the login modal
        driver.findElement(loginModalButton).click();
        
        // Enter username and password
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        
        // Click the login button
        driver.findElement(loginButton).click();
    }

    // Method to log out
    public void logout() {
        driver.findElement(logoutButton).click();
    }

    // Verify if login was successful (Logout button visible)
    public boolean isLoginSuccessful() {
        return driver.findElement(logoutButton).isDisplayed();
    }

    // Verify if logout was successful (Login button visible)
    public boolean isLogoutSuccessful() {
        return driver.findElement(loginModalButton).isDisplayed();
    }
}
