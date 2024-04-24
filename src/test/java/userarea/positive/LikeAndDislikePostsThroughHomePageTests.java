package userarea.positive;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import utils.UserRegistrationData;

public class LikeAndDislikePostsThroughHomePageTests extends BaseTest {
    @BeforeMethod
    public void prerequisiteSetup() {

        var register = new RegisterPage(this.driver);
        register.navigateTo();
        UserRegistrationData userData = createMockedData();
        register.addUser(userData);
        register.waitUntilToastInvisible();

        var header = new Header(this.driver);
        header.clickNewPost();

        var createPost = new CreatePostPage(this.driver);
        var file = getFileFromResource("anotherPost.jpg");
        createPost.dropFile(file);

        Assert.assertTrue(createPost.hasAttachedImage());

        createPost.submitPost(false);
        var profile = new ProfilePage(this.driver);
        profile.waitUntilPageLoaded();
        header.clickHome();
    }

    @Test
    public void likePostSuccessfullyInHomePage() {

        var home = new HomePage(this.driver);
        home.likeLatestPost();

        String[] actualPostLikesDislikes = home.getPostLikesAndDislikes();

        Assert.assertEquals(actualPostLikesDislikes[0], "1 likes", "The post does not have expected number of likes.");
        Assert.assertEquals(actualPostLikesDislikes[1], "0 dislikes", "The post does not have expected number of dislikes.");
    }

    @Test
    public void revertLikePostSuccessfullyInHomePage() {

        var home = new HomePage(this.driver);
        home.likeLatestPost();

        home.revertLatestLikePost();

        String[] actualPostLikesDislikes = home.getPostLikesAndDislikes();

        Assert.assertEquals(actualPostLikesDislikes[0], "0 likes", "The post does not have expected number of likes.");
        Assert.assertEquals(actualPostLikesDislikes[1], "0 dislikes", "The post does not have expected number of dislikes.");
    }

    @Test
    public void dislikePostSuccessfullyInHomePage() {

        var home = new HomePage(this.driver);
        home.dislikeLatestPost();

        String[] actualPostLikesDislikes = home.getPostLikesAndDislikes();

        Assert.assertEquals(actualPostLikesDislikes[0], "0 likes", "The post does not have expected number of likes.");
        Assert.assertEquals(actualPostLikesDislikes[1], "1 dislikes", "The post does not have expected number of dislikes.");
    }

    @Test
    public void revertDislikePostSuccessfullyInHomePage() {

        var home = new HomePage(this.driver);
        home.dislikeLatestPost();

        home.revertLatestDislikePost();

        String[] actualPostLikesDislikes = home.getPostLikesAndDislikes();

        Assert.assertEquals(actualPostLikesDislikes[0], "0 likes", "The post does not have expected number of likes.");
        Assert.assertEquals(actualPostLikesDislikes[1], "0 dislikes", "The post does not have expected number of dislikes.");
    }

    @Test
    public void likePostSuccessfullyInPostDialog() {

        var home = new HomePage(this.driver);
        home.clickLatestPost();

        var postDialog = new PostDialog(this.driver);
        postDialog.likeLatestPost();

        String[] actualPostLikesDislikes = postDialog.getPostLikesAndDislikes();

        Assert.assertEquals(actualPostLikesDislikes[0], "1 likes", "The post does not have expected number of likes.");
        Assert.assertEquals(actualPostLikesDislikes[1], "0 dislikes", "The post does not have expected number of dislikes.");
    }

    @Test
    public void revertLikePostSuccessfullyInPostDialog() {

        var home = new HomePage(this.driver);
        home.clickLatestPost();

        var postDialog = new PostDialog(this.driver);
        postDialog.likeLatestPost();

        postDialog.revertLatestLikePost();

        String[] actualPostLikesDislikes = postDialog.getPostLikesAndDislikes();

        Assert.assertEquals(actualPostLikesDislikes[0], "0 likes", "The post does not have expected number of likes.");
        Assert.assertEquals(actualPostLikesDislikes[1], "0 dislikes", "The post does not have expected number of dislikes.");
    }

    @Test
    public void dislikePostSuccessfullyInPostDialog() {

        var home = new HomePage(this.driver);
        home.clickLatestPost();

        var postDialog = new PostDialog(this.driver);
        postDialog.dislikeLatestPost();

        String[] actualPostLikesDislikes = postDialog.getPostLikesAndDislikes();

        Assert.assertEquals(actualPostLikesDislikes[0], "0 likes", "The post does not have expected number of likes.");
        Assert.assertEquals(actualPostLikesDislikes[1], "1 dislikes", "The post does not have expected number of dislikes.");
    }

    @Test
    public void revertDislikePostSuccessfullyInPostDialog() {

        var home = new HomePage(this.driver);
        home.clickLatestPost();

        var postDialog = new PostDialog(this.driver);
        postDialog.dislikeLatestPost();

        postDialog.revertLatestDislikePost();

        String[] actualPostLikesDislikes = postDialog.getPostLikesAndDislikes();

        Assert.assertEquals(actualPostLikesDislikes[0], "0 likes", "The post does not have expected number of likes.");
        Assert.assertEquals(actualPostLikesDislikes[1], "0 dislikes", "The post does not have expected number of dislikes.");
    }
}