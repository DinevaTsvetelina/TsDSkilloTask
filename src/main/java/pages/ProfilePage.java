package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.BasePage;

import java.time.Duration;
import java.util.List;

public class ProfilePage extends BasePage {
    private static final String URL = "http://training.skillo-bg.com/users";

    @FindBy(xpath = "//i[@class='far fa-plus-square fa-3x']")
    private WebElement newPostButton;

    @FindBy(xpath = "//label[@class='btn-public btn btn-primary active']")
    private WebElement publicPostsButton;

    @FindBy(xpath = "//label[@class='btn-private btn btn-primary']")
    private WebElement privatePostsButton;

    @FindBy(xpath = "//label[@class='btn-all btn btn-primary']")
    private WebElement allPostsButton;

    @FindBy(xpath = "//div[@class='post-img']/img")
    private List<WebElement> posts;

    @FindBy(xpath="//app-profile")
    private WebElement profilePage;

    @FindBy(xpath="(//div[@class='post-img'])[1]")
    private WebElement firstPost;

    @FindBy(xpath="//i[@class='fas fa-user-edit ng-star-inserted']")
    private WebElement editProfileButton;

    public ProfilePage(WebDriver driver) {
        super(driver, URL);
    }

    public void clickNewPostThroughProfilePage() {
        this.newPostButton.click();
    }

    public void goToPublicPosts() {
        this.publicPostsButton.click();
    }

    public void goToPrivatePosts() {
        this.privatePostsButton.click();
    }

    public void goToAllPosts() {
        this.allPostsButton.click();
    }

    public int getPostsCount() {
        return this.posts.size();
    }

    public void waitUntilPageLoaded(){
        this.wait.withTimeout(Duration.ofSeconds(10));
        this.wait.pollingEvery(Duration.ofMillis(500));
        this.wait.until(ExpectedConditions.visibilityOf(this.profilePage));
    }

    public void goToFirstPost(){
        this.firstPost.click();
    }

    public void goToModifyProfileDialog() {
        this.editProfileButton.click();
    }
}