package DemoBlazeMiniProject.DemoBlazeProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage {
    WebDriver driver;

    // Locators for SignUp elements
    private By signUpButton = By.id("signin2");
    private By usernameField = By.id("sign-username");
    private By passwordField = By.id("sign-password");
    private By submitButton = By.xpath("//button[text()='Sign up']");

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    // Click the "Sign Up" button
    public void clickSignUpButton() {
        driver.findElement(signUpButton).click();
    }

    // Enter credentials
    public void enterCredentials(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
    }

    // Submit the signup form
    public void submitSignUp() {
        driver.findElement(submitButton).click();
    }

    // Get the alert message
    public String getAlertMessage() {
        return driver.switchTo().alert().getText();
    }

    // Accept the alert
    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }
}
