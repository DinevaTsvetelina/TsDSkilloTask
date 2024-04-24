package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ComponentBase;

public class Header extends ComponentBase {
    @FindBy(id = "nav-link-login")
    private WebElement loginLink;

    @FindBy(id = "nav-link-profile")
    private WebElement profileLink;

    @FindBy(xpath = "//i[@class='fas fa-sign-out-alt fa-lg']/parent::a")
    private WebElement logoutButton;

    @FindBy(id = "nav-link-new-post")
    private WebElement newPostLink;

    @FindBy(id = "nav-link-home")
    private WebElement homeLink;

    public Header(WebDriver driver) {
        super(driver);
    }

    public void clickLogin() {
        loginLink.click();
    }

    public boolean verifyProfileMenuElementExists() {
        return this.isElementPresented(this.profileLink);
    }

    public void logoutUser() {
        this.logoutButton.click();
    }

    public void clickNewPost() {
        this.newPostLink.click();
    }

    public void clickProfile() {
        this.profileLink.click();
    }

    public void clickHome() {
        this.homeLink.click();
    }
}
