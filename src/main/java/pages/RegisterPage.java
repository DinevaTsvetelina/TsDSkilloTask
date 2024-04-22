package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.BasePage;
import utils.UserRegistrationData;

public class RegisterPage extends BasePage {
    private static final String URL = "http://training.skillo-bg.com/users/register";

    @FindBy(xpath = "//div/input[contains(@name, 'username')]")
    private WebElement usernameInput;

    @FindBy(xpath = "//div/input[contains(@type, 'email')]")
    private WebElement emailInput;

    @FindBy(xpath = "//div/input[contains(@id, 'defaultRegisterFormPassword')][1]")
    private WebElement passwordInput;

    @FindBy(xpath = "//div/input[contains(@name, 'verify-password')]")
    private WebElement confirmPasswordInput;

    @FindBy(xpath = "//div/button[contains(@id, 'sign-in-button')]")
    private WebElement signInButton;

    @FindBy(xpath = "//div[@class='toast-error ngx-toastr ng-trigger ng-trigger-flyInOut']")
    private WebElement errorToast;

    @FindBy(xpath = "//span[@class='invalid-feedback']")
    private WebElement usernameErrorMessage;

    @FindBy(xpath = "//input[@formcontrolname='email']/following-sibling::span")
    private WebElement emailErrorMessage;

    @FindBy(xpath = "//input[@formcontrolname='password']/following-sibling::span")
    private WebElement minMaxPasswordErrorMessage;

    @FindBy(xpath = "//input[@formcontrolname='confirmPassword']/following-sibling::span")
    private WebElement doNotMatchPasswordsErrorMessage;

    public RegisterPage(WebDriver driver) {
        super(driver, URL);
    }

    public void addUser(UserRegistrationData userData) {
        addUserInformation(userData);
        clickSignIn();
    }

    public void addUserInformation(UserRegistrationData userData) {
        this.usernameInput.sendKeys(userData.getUsername());
        this.emailInput.sendKeys(userData.getEmail());
        this.passwordInput.sendKeys(userData.getPassword());
        this.confirmPasswordInput.sendKeys(userData.getConfirmPassword());
    }

    public String getErrorToastText() {

        return this.errorToast.getText();
    }

    public String getEmailErrorMessage() {
        return this.emailErrorMessage.getText();
    }

    public String getMinMaxPasswordErrorMessage() {
        return this.minMaxPasswordErrorMessage.getText();
    }

    public void clickSignIn() {
        this.signInButton.click();
    }

    public String getDoNotMatchPasswordsErrorMessage() {
        return this.doNotMatchPasswordsErrorMessage.getText();
    }

    public boolean isErrorToastDisplayed() {
        return this.isElementPresented(this.errorToast);
    }

    public void waitUntilErrorToastIsInvisible() {
        wait.until(ExpectedConditions.invisibilityOf(this.errorToast));
    }

    public void waitUntilToastInvisible() {
        this.wait.until(ExpectedConditions.invisibilityOf(this.toastMessage));
    }

    public String getUsernameErrorMessage() {
        return this.usernameErrorMessage.getText();
    }
}
