package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.ComponentBase;

import java.util.List;

public class PostDialog extends ComponentBase {

    @FindBy(xpath = "//app-post-modal//i[@class='like far fa-heart fa-2x']")
    private WebElement postLike;

    @FindBy(xpath = "//app-post-modal//i[@class='like far fa-heart fa-2x liked']")
    private WebElement postAlreadyLiked;

    @FindBy(xpath = "//app-post-modal//i[@class='ml-4 far fa-thumbs-down fa-2x']")
    private WebElement postDislike;

    @FindBy(xpath = "//app-post-modal//i[@class='ml-4 far fa-thumbs-down fa-2x liked']")
    private WebElement postAlreadyDisliked;

    @FindBy(xpath = "//app-post-modal//strong")
    private List<WebElement> postLikeAndDislikeTexts;

    public PostDialog(WebDriver driver) {
        super(driver);
    }

    public void likeLatestPost() {

        this.actions.moveToElement(this.postLike).click().perform();
        this.wait.until(ExpectedConditions.visibilityOf(this.postAlreadyLiked));
    }

    public void dislikeLatestPost() {

        this.actions.moveToElement(this.postDislike).click().perform();
        this.wait.until(ExpectedConditions.visibilityOf(this.postAlreadyDisliked));
    }

    public void revertLatestLikePost() {

        this.actions.moveToElement(this.postAlreadyLiked).click().perform();
        this.wait.until(ExpectedConditions.visibilityOf(this.postLike));
    }

    public void revertLatestDislikePost() {

        this.actions.moveToElement(this.postAlreadyDisliked).click().perform();
        this.wait.until(ExpectedConditions.visibilityOf(this.postDislike));
    }

    public String[] getPostLikesAndDislikes() {

        if (this.postLikeAndDislikeTexts.size() != 2) {
            throw new IllegalArgumentException("Error, post info should have exactly two elements.");
        }
        String[] result = new String[2];
        result[0] = this.postLikeAndDislikeTexts.get(0).getText();
        result[1] = this.postLikeAndDislikeTexts.get(1).getText();
        return result;
    }
}
