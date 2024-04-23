package pages;

import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.BasePage;

import java.util.List;

public class HomePage extends BasePage {
    private static final String URL = "http://training.skillo-bg.com/";

    @FindBy(xpath = "(//div[@class='post-info'])[1]//i[@class='far fa-heart fa-2x']")
    private WebElement latestPostLike;

    @FindBy(xpath = "(//div[@class='post-info'])[1]//i[@class='far fa-heart fa-2x liked']")
    private WebElement latestPostAlreadyLiked;

    @FindBy(xpath = "(//div[@class='post-info'])[1]//i[@class='ml-4 far fa-thumbs-down fa-2x']")
    private WebElement latestPostDislike;

    @FindBy(xpath = "(//div[@class='post-info'])[1]//i[@class='ml-4 far fa-thumbs-down fa-2x liked']")
    private WebElement latestPostAlreadyDisliked;

    @FindBy(xpath = "(//div[@class='post-info'])[1]//strong")
    private List<WebElement> latestPostLikeAndDislikeTexts;

    @FindBy(xpath = "(//div[@class='post-feed-img'])[1]")
    private WebElement latestPost;

    public HomePage(WebDriver driver) {
        super(driver, URL);
    }

    public void likeLatestPost() {

        this.actions.moveToElement(this.latestPostLike).click().perform();
        this.wait.until(ExpectedConditions.visibilityOf(this.latestPostAlreadyLiked));
    }

    public void dislikeLatestPost() {

        this.actions.moveToElement(this.latestPostDislike).click().perform();
        this.wait.until(ExpectedConditions.visibilityOf(this.latestPostAlreadyDisliked));
    }

    public void revertLatestLikePost() {

        this.actions.moveToElement(this.latestPostAlreadyLiked).click().perform();
        this.wait.until(ExpectedConditions.visibilityOf(this.latestPostLike));
    }

    public void revertLatestDislikePost() {
        
        this.actions.moveToElement(this.latestPostAlreadyDisliked).click().perform();
        this.wait.until(ExpectedConditions.visibilityOf(this.latestPostDislike));
    }

    public String[] getPostLikesAndDislikes() {

        if (this.latestPostLikeAndDislikeTexts.size() != 2) {
            throw new InvalidElementStateException("Error, post info should have exactly two elements.");
        }
        String[] result = new String[2];
        result[0] = this.latestPostLikeAndDislikeTexts.get(0).getText();
        result[1] = this.latestPostLikeAndDislikeTexts.get(1).getText();
        return result;
    }

    public void clickLatestPost() {
        this.latestPost.click();
    }
}
