package userarea.negative;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Header;
import pages.ModifyProfileDialog;
import pages.ProfilePage;
import pages.RegisterPage;
import utils.UserProfileData;
import utils.UserRegistrationData;

public class UpdateProfileTests extends BaseTest {

    @BeforeMethod
    public void prerequisiteSetup() {

        var register = new RegisterPage(this.driver);
        register.navigateTo();
        UserRegistrationData userData = createMockedData();
        register.addUser(userData);
        register.waitUntilToastInvisible();

        var header = new Header(this.driver);
        header.clickProfile();
    }

    @Test
    public void cannotUpdateUsernameWithShorterThanMinimumAllowedSize() {

        var profile = new ProfilePage(this.driver);
        profile.goToModifyProfileDialog();

        var modifyProfileDialog = new ModifyProfileDialog(this.driver);
        var userProfileData = new UserProfileData();
        userProfileData.setUsername("u");
        modifyProfileDialog.editProfileInfo(userProfileData);

        Assert.assertTrue(modifyProfileDialog.isSaveButtonDisabled(), "Save button is enabled.");
    }

    @Test
    public void cannotUpdateUsernameWithLongerThanMaximumAllowedSize() {

        var profile = new ProfilePage(this.driver);
        profile.goToModifyProfileDialog();

        var modifyProfileDialog = new ModifyProfileDialog(this.driver);
        var userProfileData = new UserProfileData();
        userProfileData.setUsername("longerThanMaxUsername");
        modifyProfileDialog.editProfileInfo(userProfileData);

        Assert.assertTrue(modifyProfileDialog.isSaveButtonDisabled(), "Save button is enabled.");
    }

    @Test
    public void cannotUpdateEmailWithLongerThanMaximumAllowedSize() {

        var profile = new ProfilePage(this.driver);
        profile.goToModifyProfileDialog();

        var modifyProfileDialog = new ModifyProfileDialog(this.driver);
        var userProfileData = new UserProfileData();
        userProfileData.setEmail("longerThanMaximumAllowedSizeEmaillongerThanMaximumAllowedSizeEmai@test.com");
        modifyProfileDialog.editProfileInfo(userProfileData);

        Assert.assertTrue(modifyProfileDialog.isSaveButtonDisabled(), "Save button is enabled.");
    }

    @Test
    public void cannotUpdateEmailWithValueNotContainingAtSymbol() {

        var profile = new ProfilePage(this.driver);
        profile.goToModifyProfileDialog();

        var modifyProfileDialog = new ModifyProfileDialog(this.driver);
        var userProfileData = new UserProfileData();
        userProfileData.setEmail("testtest.com");
        modifyProfileDialog.editProfileInfo(userProfileData);

        Assert.assertTrue(modifyProfileDialog.isSaveButtonDisabled(), "Save button is enabled.");
    }

    @Test
    public void cannotUpdatePasswordWithShorterThanMinimumAllowedSize() {

        var profile = new ProfilePage(this.driver);
        profile.goToModifyProfileDialog();

        var modifyProfileDialog = new ModifyProfileDialog(this.driver);
        var userProfileData = new UserProfileData();
        userProfileData.setPassword("12345");
        userProfileData.setConfirmPassword("12345");
        modifyProfileDialog.editProfileInfo(userProfileData);

        Assert.assertTrue(modifyProfileDialog.isSaveButtonDisabled(), "Save button is enabled.");
    }

    @Test
    public void cannotUpdatePasswordWithLongerThanMaximumAllowedSize() {

        var profile = new ProfilePage(this.driver);
        profile.goToModifyProfileDialog();

        var modifyProfileDialog = new ModifyProfileDialog(this.driver);
        var userProfileData = new UserProfileData();
        userProfileData.setPassword("longerThanMaxSizePass");
        userProfileData.setConfirmPassword("longerThanMaxSizePass");
        modifyProfileDialog.editProfileInfo(userProfileData);

        Assert.assertTrue(modifyProfileDialog.isSaveButtonDisabled(), "Save button is enabled.");
    }

    @Test
    public void cannotUpdatePasswordWhenMismatched() {

        var profile = new ProfilePage(this.driver);
        profile.goToModifyProfileDialog();

        var modifyProfileDialog = new ModifyProfileDialog(this.driver);
        var userProfileData = new UserProfileData();
        userProfileData.setPassword("123456");
        userProfileData.setConfirmPassword("123455");
        modifyProfileDialog.editProfileInfo(userProfileData);

        Assert.assertTrue(modifyProfileDialog.isSaveButtonDisabled(), "Save button is enabled.");
    }

    @Test
    public void cannotUpdatePublicInfoWithShorterThanMinimumAllowedSize() {

        var profile = new ProfilePage(this.driver);
        profile.goToModifyProfileDialog();

        var modifyProfileDialog = new ModifyProfileDialog(this.driver);
        var userProfileData = new UserProfileData();
        userProfileData.setPublicInfo("short");
        modifyProfileDialog.editProfileInfo(userProfileData);

        Assert.assertTrue(modifyProfileDialog.isSaveButtonDisabled(), "Save button is enabled.");
    }

    @Test
    public void cannotUpdatePublicInfoWithLongerThanMaximumAllowedSize() {

        var profile = new ProfilePage(this.driver);
        profile.goToModifyProfileDialog();

        var modifyProfileDialog = new ModifyProfileDialog(this.driver);
        var userProfileData = new UserProfileData();
        userProfileData.setPublicInfo("longerThanMaximumAllowedSizePublicInfoLongerThanMaximumAllowedSizePublicInfoLongerThanMaximumAllowedSizePublicInfoLongerThanMaximumAllowedSizePublicInfoLongerThanMaximumAllowedSizePublicInfoLongerThanM");
        modifyProfileDialog.editProfileInfo(userProfileData);

        Assert.assertTrue(modifyProfileDialog.isSaveButtonDisabled(), "Save button is enabled.");
    }
}