package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ComponentBase;
import utils.UserProfileData;

public class ModifyProfileDialog extends ComponentBase {

    @FindBy(xpath = "//input[@formcontrolname='username']")
    private WebElement usernameInput;

    @FindBy(xpath = "//input[@formcontrolname='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@formcontrolname='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@formcontrolname='confirmPassword']")
    private WebElement confirmPasswordInput;

    @FindBy(xpath = "//textarea[@formcontrolname='publicInfo']")
    private WebElement publicInfoInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    @FindBy(xpath = "//span[@class='form-text ng-star-inserted']")
    private WebElement usernameErrorMessage;

    @FindBy(xpath = "//span[@class='form-text text-muted ng-star-inserted']")
    private WebElement emailErrorMessage;

    @FindBy(xpath = "//span[@class='form-text text-muted ng-star-inserted']")
    private WebElement minMaxPasswordErrorMessage;

    @FindBy(xpath = "//span[@class='form-text text-muted ng-star-inserted']")
    private WebElement doNotMatchPasswordsErrorMessage;

    public ModifyProfileDialog(WebDriver driver) {
        super(driver);
    }

    public void editProfileInfo(UserProfileData userProfileData) {
        if (userProfileData.getUsername() != null) {
            this.usernameInput.clear();
            this.usernameInput.sendKeys(userProfileData.getUsername());
        }
        if (userProfileData.getEmail() != null) {
            this.emailInput.clear();
            this.emailInput.sendKeys(userProfileData.getEmail());
        }
        if (userProfileData.getPassword() != null) {
            this.passwordInput.clear();
            this.passwordInput.sendKeys(userProfileData.getPassword());
        }
        if (userProfileData.getConfirmPassword() != null) {
            this.confirmPasswordInput.clear();
            this.confirmPasswordInput.sendKeys(userProfileData.getConfirmPassword());
        }
        if (userProfileData.getPublicInfo() != null) {
            this.publicInfoInput.clear();
            this.publicInfoInput.sendKeys(userProfileData.getPublicInfo());
        }

        clickSaveButton();
    }

    public String getUsernameErrorMessage() {
        return this.usernameErrorMessage.getText();
    }

    public String getEmailErrorMessage() {
        return this.emailErrorMessage.getText();
    }

    public String getMinMaxPasswordErrorMessage() {
        return this.minMaxPasswordErrorMessage.getText();
    }

    public String getDoNotMatchPasswordsErrorMessage() {
        return this.doNotMatchPasswordsErrorMessage.getText();
    }

    public boolean isSaveButtonDisabled() {
        return this.saveButton.getAttribute("disabled") != null;
    }

    public void clickSaveButton() {
        this.saveButton.click();
    }
}