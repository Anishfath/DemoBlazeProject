package DemoBlazeMiniProject.DemoBlazeProject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    @FindBy(id = "login2")
    private WebElement loginButton;

    @FindBy(id = "loginusername")
    private WebElement usernameField;

    @FindBy(id = "loginpassword")
    private WebElement passwordField;

    @FindBy(xpath = "//button[text()='Log in']")
    private WebElement confirmLoginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getLoginButton() {
        return loginButton;
    }
    public void enterLoginDetails(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        confirmLoginButton.click();
    }

	public void enterUsername(String string) {
		// TODO Auto-generated method stub
		
	}

	public void enterPassword(String string) {
		// TODO Auto-generated method stub
		
	}

	public void clickLogin() {
		// TODO Auto-generated method stub
		
	}
}
