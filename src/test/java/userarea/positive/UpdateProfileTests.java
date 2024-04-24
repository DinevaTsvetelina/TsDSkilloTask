package userarea.positive;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
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
    public void editUsernameSuccessfully() {

        var profile = new ProfilePage(this.driver);
        profile.goToModifyProfileDialog();

        var modifyProfileDialog = new ModifyProfileDialog(this.driver);
        var userProfileData = new UserProfileData();
        userProfileData.setUsername("updatedUsername");
        modifyProfileDialog.editProfileInfo(userProfileData);

        Assert.assertTrue(profile.isToastMessageDisplayed());

        String actualToastMessage = profile.getToast();

        Assert.assertEquals(actualToastMessage, "Profile updated", "Toast message is not as expected.");
    }

    @Test
    public void editEmailSuccessfully() {

        var profile = new ProfilePage(this.driver);
        profile.goToModifyProfileDialog();

        var modifyProfileDialog = new ModifyProfileDialog(this.driver);
        var userProfileData = new UserProfileData();
        userProfileData.setEmail("updatedEmail@test.com");
        modifyProfileDialog.editProfileInfo(userProfileData);

        String actualToastMessage = profile.getToast();

        Assert.assertEquals(actualToastMessage, "Profile updated", "Toast message is not as expected.");
    }

    @Test
    public void editPasswordSuccessfully() {

        var profile = new ProfilePage(this.driver);
        profile.goToModifyProfileDialog();

        var modifyProfileDialog = new ModifyProfileDialog(this.driver);
        var userProfileData = new UserProfileData();
        userProfileData.setPassword("updatedPassword");
        userProfileData.setConfirmPassword("updatedPassword");
        modifyProfileDialog.editProfileInfo(userProfileData);

        String actualToastMessage = profile.getToast();

        Assert.assertEquals(actualToastMessage, "Profile updated", "Toast message is not as expected.");
    }

    @Test
    public void editPublicInfoSuccessfully() {

        var profile = new ProfilePage(this.driver);
        profile.goToModifyProfileDialog();

        var modifyProfileDialog = new ModifyProfileDialog(this.driver);
        var userProfileData = new UserProfileData();
        userProfileData.setPublicInfo("updatedPublicInfo");
        modifyProfileDialog.editProfileInfo(userProfileData);

        String actualToastMessage = profile.getToast();

        Assert.assertEquals(actualToastMessage, "Profile updated", "Toast message is not as expected.");
    }

    @Test
    public void verifyMinimumAllowedSizeUsernameErrorMessageIsDisplayed() {

        var profile = new ProfilePage(this.driver);
        profile.goToModifyProfileDialog();

        var modifyProfileDialog = new ModifyProfileDialog(this.driver);
        var userProfileData = new UserProfileData();
        userProfileData.setUsername("u");
        modifyProfileDialog.editProfileInfo(userProfileData);

        String actualErrorMessage = modifyProfileDialog.getUsernameErrorMessage();

        Assert.assertEquals(actualErrorMessage, "Minimum 2 characters !", "Error message is not as expected.");
    }

    @Test
    public void verifyMaximumAllowedSizeUsernameErrorMessageIsDisplayed() {

        var profile = new ProfilePage(this.driver);
        profile.goToModifyProfileDialog();

        var modifyProfileDialog = new ModifyProfileDialog(this.driver);
        var userProfileData = new UserProfileData();
        userProfileData.setUsername("longerThanMaxUsername");
        modifyProfileDialog.editProfileInfo(userProfileData);

        String actualErrorMessage = modifyProfileDialog.getUsernameErrorMessage();

        Assert.assertEquals(actualErrorMessage, "Maximum 20 characters!", "Error message is not as expected.");
    }

    @Test
    public void verifyInvalidEmailErrorMessageIsDisplayed() {

        var profile = new ProfilePage(this.driver);
        profile.goToModifyProfileDialog();

        var modifyProfileDialog = new ModifyProfileDialog(this.driver);
        var userProfileData = new UserProfileData();
        userProfileData.setEmail("longerThanMaximumAllowedSizeEmaillongerThanMaximumAllowedSizeEmai@test.com");
        modifyProfileDialog.editProfileInfo(userProfileData);

        String actualErrorMessage = modifyProfileDialog.getEmailErrorMessage();

        Assert.assertEquals(actualErrorMessage, "Email invalid!", "Error message is not as expected.");
    }

    @Test
    public void verifyMinimumAllowedSizePasswordErrorMessageIsDisplayed() {

        var profile = new ProfilePage(this.driver);
        profile.goToModifyProfileDialog();

        var modifyProfileDialog = new ModifyProfileDialog(this.driver);
        var userProfileData = new UserProfileData();
        userProfileData.setPassword("123");
        modifyProfileDialog.editProfileInfo(userProfileData);

        String actualErrorMessage = modifyProfileDialog.getMinMaxPasswordErrorMessage();

        Assert.assertEquals(actualErrorMessage, "Minimum 6 characters !", "Error message is not as expected.");
    }

    @Test
    public void verifyMaximumAllowedSizePasswordErrorMessageIsDisplayed() {

        var profile = new ProfilePage(this.driver);
        profile.goToModifyProfileDialog();

        var modifyProfileDialog = new ModifyProfileDialog(this.driver);
        var userProfileData = new UserProfileData();
        userProfileData.setPassword("longerThanMaxSizePass");
        modifyProfileDialog.editProfileInfo(userProfileData);

        String actualErrorMessage = modifyProfileDialog.getMinMaxPasswordErrorMessage();

        Assert.assertEquals(actualErrorMessage, "Maximum 20 characters!", "Error message is not as expected.");
    }

    @Test
    public void verifyDoNotMatchPasswordsErrorMessageIsDisplayed() {

        var profile = new ProfilePage(this.driver);
        profile.goToModifyProfileDialog();

        var modifyProfileDialog = new ModifyProfileDialog(this.driver);
        var userProfileData = new UserProfileData();
        userProfileData.setPassword("123456");
        userProfileData.setConfirmPassword("123455");
        modifyProfileDialog.editProfileInfo(userProfileData);

        String actualErrorMessage = modifyProfileDialog.getDoNotMatchPasswordsErrorMessage();
        Assert.assertEquals(actualErrorMessage, "Passwords do not match!", "Error message is not as expected.");
    }

    @Test
    public void verifySaveButtonBecomesDisabledWhenPublicInfoValueIsGreaterThanMaximumAllowedSize() {

        var profile = new ProfilePage(this.driver);
        profile.goToModifyProfileDialog();

        var modifyProfileDialog = new ModifyProfileDialog(this.driver);
        var userProfileData = new UserProfileData();
        userProfileData.setPublicInfo("longerThanMaximumAllowedSizePublicInfoLongerThanMaximumAllowedSize" +
                "PublicInfoLongerThanMaximumAllowedSizePublicInfoLongerThanMaximumAllowedSizePublicInfoLonger" +
                "ThanMaximumAllowedSizePublicInfoLongerThanM");
        modifyProfileDialog.editProfileInfo(userProfileData);

        Assert.assertTrue(modifyProfileDialog.isSaveButtonDisabled(), "Save button is enabled.");
    }

    @Test
    public void verifySaveButtonIsDisabledWhenNoUpdatesAreMade() {

        var profile = new ProfilePage(this.driver);
        profile.goToModifyProfileDialog();

        var modifyProfileDialog = new ModifyProfileDialog(this.driver);
        modifyProfileDialog.clickSaveButton();

        Assert.assertTrue(modifyProfileDialog.isSaveButtonDisabled(), "Save button is enabled.");
    }
}
