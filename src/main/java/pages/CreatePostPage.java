package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.DropUtils;
import utils.BasePage;

import java.io.File;

public class CreatePostPage extends BasePage {

    private static final String URL = "http://training.skillo-bg.com/posts/create";

    @FindBy(xpath = "//div[@class='uploadfilecontainer']")
    private WebElement dragAndDropArea;

    @FindBy(xpath = "//button[@id='create-post']")
    private WebElement submitPostButton;

    @FindBy(xpath = "//img[@class='image-preview']")
    private WebElement imagePreview;

    @FindBy(xpath = "//label[@for='customSwitch2']")
    private WebElement publicPrivateToggle;

    public CreatePostPage(WebDriver driver) {
        super(driver, URL);
    }

    public void dropFile(File file) {
        this.wait.until(ExpectedConditions.visibilityOf(this.dragAndDropArea));
        DropUtils.dropFile(this.driver, file, this.dragAndDropArea, 1, 1);
    }

    public void submitPost(boolean isPrivate) {
        if (isPrivate) {
            this.publicPrivateToggle.click();
        }
        this.submitPostButton.click();
    }

    public boolean hasAttachedImage() {
        return isElementPresented(this.imagePreview);
    }

    public String getPostToastMessage() {
        return this.toastMessage.getText();
    }
}
