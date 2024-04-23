package userarea.positive;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import utils.UserRegistrationData;

public class CreatePostTests extends BaseTest {

    @BeforeMethod
    public void prerequisiteSetup() {

        var register = new RegisterPage(this.driver);
        register.navigateTo();
        UserRegistrationData userData = createMockedData();
        register.addUser(userData);
        register.waitUntilToastInvisible();
    }

    @Test
    public void createPublicPostSuccessfullyThroughNewPostMenu() {

        var header = new Header(this.driver);
        header.clickNewPost();

        var createPost = new CreatePostPage(this.driver);
        var file = getFileFromResource("publicPost.jpg");
        createPost.dropFile(file);

        Assert.assertTrue(createPost.hasAttachedImage());

        createPost.submitPost(false);

        var profile = new ProfilePage(this.driver);
        profile.waitUntilPageLoaded();
        profile.goToPublicPosts();

        int actualPostsCount = profile.getPostsCount();

        Assert.assertEquals(actualPostsCount, 1, "Posts count is not as expected.");
    }

    @Test
    public void createPrivatePostSuccessfullyThroughNewPostMenu() {

        var header = new Header(this.driver);
        header.clickNewPost();

        var createPost = new CreatePostPage(this.driver);
        var file = getFileFromResource("privatePost.jpg");
        createPost.dropFile(file);

        Assert.assertTrue(createPost.hasAttachedImage());

        createPost.submitPost(true);

        var profile = new ProfilePage(this.driver);
        profile.goToPrivatePosts();

        int actualPostsCount = profile.getPostsCount();

        Assert.assertEquals(actualPostsCount, 1, "Posts count is not as expected.");
    }

    @Test
    public void createPublicPostSuccessfullyThroughProfileMenu() {

        var header = new Header(this.driver);
        header.clickProfile();

        var profile = new ProfilePage(this.driver);
        profile.clickNewPostThroughProfilePage();

        var createPost = new CreatePostPage(this.driver);
        var file = getFileFromResource("publicPost.jpg");
        createPost.dropFile(file);

        Assert.assertTrue(createPost.hasAttachedImage());

        createPost.submitPost(false);

        profile.goToPublicPosts();

        int actualPostsCount = profile.getPostsCount();

        Assert.assertEquals(actualPostsCount, 1, "Posts count is not as expected.");
    }

    @Test
    public void createPrivatePostSuccessfullyThroughProfileMenu() {

        var header = new Header(this.driver);
        header.clickProfile();

        var profile = new ProfilePage(this.driver);
        profile.clickNewPostThroughProfilePage();

        var createPost = new CreatePostPage(this.driver);
        var file = getFileFromResource("privatePost.jpg");
        createPost.dropFile(file);

        Assert.assertTrue(createPost.hasAttachedImage());

        createPost.submitPost(true);

        profile.goToPrivatePosts();

        int actualPostsCount = profile.getPostsCount();

        Assert.assertEquals(actualPostsCount, 1, "Posts count is not as expected.");
    }

    @Test
    public void createMultiplePostsOneAfterAnotherThroughNewPostMenu() {

        var header = new Header(this.driver);
        header.clickNewPost();

        var createPost = new CreatePostPage(this.driver);
        var file = getFileFromResource("publicPost.jpg");
        createPost.dropFile(file);

        Assert.assertTrue(createPost.hasAttachedImage());
        createPost.submitPost(false);

        var profile = new ProfilePage(this.driver);
        profile.waitUntilPageLoaded();

        header.clickNewPost();
        createPost.dropFile(file);
        Assert.assertTrue(createPost.hasAttachedImage());
        createPost.submitPost(false);

        profile.goToPublicPosts();

        int actualPostsCount = profile.getPostsCount();

        Assert.assertEquals(actualPostsCount, 2, "Posts count is not as expected.");
    }

    @Test
    public void createMultiplePostsOneAfterAnotherThroughProfileMenu() {

        var header = new Header(this.driver);
        header.clickProfile();

        var profile = new ProfilePage(this.driver);
        profile.clickNewPostThroughProfilePage();

        var createPost = new CreatePostPage(this.driver);
        var publicFile = getFileFromResource("publicPost.jpg");
        createPost.dropFile(publicFile);
        Assert.assertTrue(createPost.hasAttachedImage());
        createPost.submitPost(false);

        profile.goToPrivatePosts();
        profile.clickNewPostThroughProfilePage();
        var privateFile = getFileFromResource("privatePost.jpg");
        createPost.dropFile(privateFile);
        Assert.assertTrue(createPost.hasAttachedImage());
        createPost.submitPost(false);

        int actualPostsCount = profile.getPostsCount();

        Assert.assertEquals(actualPostsCount, 2, "Posts count is not as expected.");
    }

    @Test
    public void verifyPostCreatedToastMessageIsDisplayedWhenCreatingPublicPost() {

        var header = new Header(this.driver);
        header.clickNewPost();

        var createPost = new CreatePostPage(this.driver);
        var file = getFileFromResource("publicPost.jpg");
        createPost.dropFile(file);

        Assert.assertTrue(createPost.hasAttachedImage());

        createPost.submitPost(false);

        String actualToastMessage = createPost.getToast();

        Assert.assertEquals(actualToastMessage, "Post created!", "Toast message is not as expected.");
    }

    @Test
    public void verifyPostCreatedToastMessageIsDisplayedWhenCreatingPrivatePost() {

        var header = new Header(this.driver);
        header.clickNewPost();

        var createPost = new CreatePostPage(this.driver);
        var file = getFileFromResource("privatePost.jpg");
        createPost.dropFile(file);

        Assert.assertTrue(createPost.hasAttachedImage());

        createPost.submitPost(true);

        String actualToastMessage = createPost.getToast();

        Assert.assertEquals(actualToastMessage, "Post created!", "Toast message is not as expected.");
    }

    @Test
    public void verifyPostCreationErrorMessageIsDisplayedWhenCreatingPublicPost() {

        var header = new Header(this.driver);
        header.clickNewPost();

        var createPost = new CreatePostPage(this.driver);
        createPost.submitPost(false);

        String actualToastMessage = createPost.getErrorToastMessage();

        Assert.assertEquals(actualToastMessage, "Creation of post failed!", "Toast message is not as expected.");
    }

    @Test
    public void verifyPostCreationErrorMessageIsDisplayedWhenCreatingPrivatePost() {

        var header = new Header(this.driver);
        header.clickNewPost();

        var createPost = new CreatePostPage(this.driver);
        createPost.submitPost(true);

        String actualToastMessage = createPost.getErrorToastMessage();

        Assert.assertEquals(actualToastMessage, "Creation of post failed!", "Toast message is not as expected.");
    }
    @Test
    public void verifyAllPostsCountIsCorrect() {

        var header = new Header(this.driver);
        header.clickNewPost();

        var createPost = new CreatePostPage(this.driver);
        var publicFile = getFileFromResource("publicPost.jpg");
        createPost.dropFile(publicFile);
        Assert.assertTrue(createPost.hasAttachedImage());
        createPost.submitPost(false);

        var profile = new ProfilePage(this.driver);
        profile.waitUntilPageLoaded();

        header.clickNewPost();
        createPost.dropFile(publicFile);
        Assert.assertTrue(createPost.hasAttachedImage());
        createPost.submitPost(false);

        profile.waitUntilPageLoaded();

        header.clickNewPost();
        var privateFile = getFileFromResource("privatePost.jpg");
        createPost.dropFile(privateFile);
        Assert.assertTrue(createPost.hasAttachedImage());
        createPost.submitPost(true);

        profile.goToAllPosts();

        int actualPostsCount = profile.getPostsCount();

        Assert.assertEquals(actualPostsCount, 3, "Posts count is not as expected.");
    }
}
