package userarea.negative;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CreatePostPage;
import pages.Header;
import pages.ProfilePage;
import pages.RegisterPage;
import utils.UserRegistrationData;

public class CreatePostTests extends BaseTest {

    @BeforeMethod
    public void prerequisiteSetup() {

        var register = new RegisterPage(this.driver);
        register.navigateTo();
        UserRegistrationData userData = createMockedData();
        register.addUser(userData);
        register.waitUntilErrorToastIsInvisible();
    }

    @Test
    public void cannotCreatePublicPostWithoutAttachingPictureThroughNewPostMenu() {

        var header = new Header(this.driver);
        header.clickNewPost();

        var createPost = new CreatePostPage(this.driver);
        createPost.submitPost(false);

        String actualToastMessage = createPost.getPostToastMessage();

        Assert.assertEquals(actualToastMessage, "Creation of post failed!", "Toast message is not as expected.");
    }

    @Test
    public void cannotCreatePrivatePostWithoutAttachingPictureThroughNewPostMenu() {

        var header = new Header(this.driver);
        header.clickNewPost();

        var createPost = new CreatePostPage(this.driver);
        createPost.submitPost(true);

        String actualToastMessage = createPost.getPostToastMessage();

        Assert.assertEquals(actualToastMessage, "Creation of post failed!", "Toast message is not as expected.");
    }

    @Test
    public void cannotCreatePublicPostWithoutAttachingPictureThroughProfileMenu() {

        var header = new Header(this.driver);
        header.clickProfile();

        var profile = new ProfilePage(this.driver);
        profile.clickNewPostThroughProfilePage();

        var createPost = new CreatePostPage(this.driver);
        createPost.submitPost(false);

        String actualToastMessage = createPost.getPostToastMessage();

        Assert.assertEquals(actualToastMessage, "Creation of post failed!", "Toast message is not as expected.");
    }

    @Test
    public void cannotCreatePrivatePostWithoutAttachingPictureThroughProfileMenu() {

        var header = new Header(this.driver);
        header.clickProfile();

        var profile = new ProfilePage(this.driver);
        profile.clickNewPostThroughProfilePage();

        var createPost = new CreatePostPage(this.driver);
        createPost.submitPost(true);

        String actualToastMessage = createPost.getPostToastMessage();

        Assert.assertEquals(actualToastMessage, "Creation of post failed!", "Toast message is not as expected.");
    }

    @Test
    public void cannotCreatePublicPostWithUnsupportedMediaTypePictureThroughNewPostMenu() {

        var header = new Header(this.driver);
        header.clickNewPost();

        var createPost = new CreatePostPage(this.driver);
        var file = getFileFromResource("unsupportedMediaTypePost.webp");
        createPost.dropFile(file);

        Assert.assertTrue(createPost.hasAttachedImage());

        createPost.submitPost(false);

        String actualToastMessage = createPost.getPostToastMessage();

        Assert.assertEquals(actualToastMessage, "Creation of post failed!", "Toast message is not as expected.");
    }

    @Test
    public void cannotCreatePrivatePostWithUnsupportedMediaTypePictureThroughNewPostMenu() {

        var header = new Header(this.driver);
        header.clickNewPost();

        var createPost = new CreatePostPage(this.driver);
        var file = getFileFromResource("unsupportedMediaTypePost.webp");
        createPost.dropFile(file);

        Assert.assertTrue(createPost.hasAttachedImage());

        createPost.submitPost(true);

        String actualToastMessage = createPost.getPostToastMessage();

        Assert.assertEquals(actualToastMessage, "Creation of post failed!", "Toast message is not as expected.");
    }

    @Test
    public void cannotCreatePublicPostWithUnsupportedMediaTypePictureThroughProfileMenu() {

        var header = new Header(this.driver);
        header.clickProfile();

        var profile = new ProfilePage(this.driver);
        profile.clickNewPostThroughProfilePage();

        var createPost = new CreatePostPage(this.driver);
        var file = getFileFromResource("unsupportedMediaTypePost.webp");
        createPost.dropFile(file);

        Assert.assertTrue(createPost.hasAttachedImage());

        createPost.submitPost(false);

        String actualToastMessage = createPost.getPostToastMessage();

        Assert.assertEquals(actualToastMessage, "Creation of post failed!", "Toast message is not as expected.");
    }

    @Test
    public void cannotCreatePrivatePostWithUnsupportedMediaTypePictureThroughProfileMenu() {

        var header = new Header(this.driver);
        header.clickProfile();

        var profile = new ProfilePage(this.driver);
        profile.clickNewPostThroughProfilePage();

        var createPost = new CreatePostPage(this.driver);
        var file = getFileFromResource("unsupportedMediaTypePost.webp");
        createPost.dropFile(file);

        Assert.assertTrue(createPost.hasAttachedImage());

        createPost.submitPost(true);

        String actualToastMessage = createPost.getPostToastMessage();

        Assert.assertEquals(actualToastMessage, "Creation of post failed!", "Toast message is not as expected.");
    }
}
