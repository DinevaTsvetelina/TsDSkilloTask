package utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class ComponentBase {
    protected final WebDriver driver;

    protected final WebDriverWait wait;

    protected final Actions actions;

    protected ComponentBase(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.actions = new Actions(this.driver);
        
        PageFactory.initElements(driver, this);
    }

    protected boolean isElementPresented(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}