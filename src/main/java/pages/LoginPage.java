package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.BasePage;

public class LoginPage extends BasePage {
    private static final String URL = "http://training.skillo-bg.com/users/login";

    @FindBy(xpath = "//app-login//form/p[last()]/a")
    private WebElement registerLink;

    @FindBy(id = "defaultLoginFormUsername")
    private WebElement usernameOrEmailInput;

    @FindBy(id = "defaultLoginFormPassword")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@id='sign-in-button']")
    private WebElement signInButton;

    @FindBy(xpath = "//input[@class='remember-me-button ng-untouched ng-pristine ng-valid']")
    private WebElement rememberMeCheckbox;

    public LoginPage(WebDriver driver) {
        super(driver, URL);
    }

    public void clickRegister() {
        this.registerLink.click();
    }

    public void signInUserWithUsernameOrEmail(String usernameOrEmail, String password, boolean isRememberMeEnabled) {
        this.usernameOrEmailInput.sendKeys(usernameOrEmail);
        this.passwordInput.sendKeys(password);
        if (isRememberMeEnabled) {
            this.rememberMeCheckbox.click();
        }
        this.signInButton.click();
    }

    public String getUsernameOrEmailText() {
        return this.usernameOrEmailInput.getAttribute("value");
    }

    public String getPasswordText() {
        return this.passwordInput.getAttribute("value");
    }

    public String getErrorToast() {
        return this.toastMessage.getText();
    }
}
