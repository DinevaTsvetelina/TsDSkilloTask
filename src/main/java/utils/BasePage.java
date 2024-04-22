package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class BasePage extends ComponentBase{
    protected final String url;

    @FindBy(xpath ="//div[@class='toast-message ng-star-inserted']")
    protected WebElement toastMessage;

    protected BasePage(WebDriver driver, String url) {
        super(driver);
        this.url = url;
    }

    public void navigateTo(){
        this.driver.get(this.url);
    }
}
