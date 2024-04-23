package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class BasePage extends ComponentBase{
    protected final String url;

    @FindBy(xpath ="//div[@class='toast-message ng-star-inserted']")
    private WebElement toast;

    @FindBy(xpath = "//div[@class='toast-error ngx-toastr ng-trigger ng-trigger-flyInOut']")
    private WebElement errorToast;

    protected BasePage(WebDriver driver, String url) {
        super(driver);
        this.url = url;
    }

    public void navigateTo(){
        this.driver.get(this.url);
    }

    public void waitUntilToastInvisible() {
        this.wait.until(ExpectedConditions.invisibilityOf(this.toast));
    }

    public boolean isToastMessageDisplayed() {
        return this.isElementPresented(this.toast);
    }

    public boolean isErrorToastDisplayed() {
        return this.isElementPresented(this.errorToast);
    }

    public String getToast() {
        return this.toast.getText();
    }

    public String getErrorToastMessage() {
        return this.errorToast.getText();
    }
}