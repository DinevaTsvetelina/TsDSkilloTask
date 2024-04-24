package userarea.positive;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Header;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;
import utils.UserRegistrationData;

public class RegisterUserTests extends BaseTest {
    @Test
    public void createSuccessfullyNewUser() {

        var home = new HomePage(this.driver);
        home.navigateTo();

        var header = new Header(this.driver);
        header.clickLogin();

        var login = new LoginPage(this.driver);
        login.clickRegister();

        var register = new RegisterPage(this.driver);
        UserRegistrationData userData = createMockedData();
        register.addUser(userData);

        Assert.assertTrue(header.verifyProfileMenuElementExists(), "User is not signed in as expected.");
    }

    @Test
    public void createSuccessfullyNewUserUsingDirectLink() {

        var register = new RegisterPage(this.driver);
        register.navigateTo();
        UserRegistrationData userData = createMockedData();
        register.addUser(userData);

        var header = new Header(this.driver);

        Assert.assertTrue(header.verifyProfileMenuElementExists(), "User is not signed in as expected.");
    }

    @Test
    public void verifyMinimumAllowedSizeUsernameErrorMessageIsDisplayed() {

        var home = new HomePage(this.driver);
        home.navigateTo();

        var header = new Header(this.driver);
        header.clickLogin();

        var login = new LoginPage(this.driver);
        login.clickRegister();

        var register = new RegisterPage(this.driver);
        UserRegistrationData userData = createMockedData();
        userData.setUsername("u");
        register.addUserInformation(userData);

        String actualErrorMessage = register.getUsernameErrorMessage();

        Assert.assertEquals(actualErrorMessage, "Minimum 2 characters !", "Error message is not as expected.");
    }

    @Test
    public void verifyMaximumAllowedSizeUsernameErrorMessageIsDisplayed() {

        var home = new HomePage(this.driver);
        home.navigateTo();

        var header = new Header(this.driver);
        header.clickLogin();

        var login = new LoginPage(this.driver);
        login.clickRegister();

        var register = new RegisterPage(this.driver);
        UserRegistrationData userData = createMockedData();
        userData.setUsername("longerThanMaxUsername");
        register.addUserInformation(userData);

        String actualErrorMessage = register.getUsernameErrorMessage();

        Assert.assertEquals(actualErrorMessage, "Maximum 20 characters!", "Error message is not as expected.");
    }

    @Test
    public void verifyInvalidEmailErrorMessageIsDisplayed() {

        var home = new HomePage(this.driver);
        home.navigateTo();

        var header = new Header(this.driver);
        header.clickLogin();

        var login = new LoginPage(this.driver);
        login.clickRegister();

        var register = new RegisterPage(this.driver);
        UserRegistrationData userData = createMockedData();
        userData.setEmail("inv");
        register.addUserInformation(userData);

        String actualErrorMessage = register.getEmailErrorMessage();

        Assert.assertEquals(actualErrorMessage, "Email invalid!", "Error message is not as expected.");
    }

    @Test
    public void verifyMinimumAllowedSizePasswordErrorMessageIsDisplayed() {

        var home = new HomePage(this.driver);
        home.navigateTo();

        var header = new Header(this.driver);
        header.clickLogin();

        var login = new LoginPage(this.driver);
        login.clickRegister();

        var register = new RegisterPage(this.driver);
        var userData = this.createMockedData();
        userData.setPassword("1");
        register.addUserInformation(userData);

        String actualErrorMessage = register.getMinMaxPasswordErrorMessage();

        Assert.assertEquals(actualErrorMessage, "Minimum 6 characters !", "Error message is not as expected.");
    }

    @Test
    public void verifyMaximumAllowedSizePasswordErrorMessageIsDisplayed() {

        var home = new HomePage(this.driver);
        home.navigateTo();

        var header = new Header(this.driver);
        header.clickLogin();

        var login = new LoginPage(this.driver);
        login.clickRegister();

        var register = new RegisterPage(this.driver);
        var userData = this.createMockedData();
        userData.setPassword("longerThanMaxSizePass");
        register.addUserInformation(userData);

        String actualErrorMessage = register.getMinMaxPasswordErrorMessage();

        Assert.assertEquals(actualErrorMessage, "Maximum 20 characters!", "Error message is not as expected.");
    }

    @Test
    public void verifyDoNotMatchPasswordsErrorMessageIsDisplayed() {

        var home = new HomePage(this.driver);
        home.navigateTo();

        var header = new Header(this.driver);
        header.clickLogin();

        var login = new LoginPage(this.driver);
        login.clickRegister();

        var register = new RegisterPage(this.driver);
        var userData = this.createMockedData();
        userData.setConfirmPassword("something_else");
        register.addUserInformation(userData);

        String actualDoNotMatchPasswordsErrorMessage = register.getDoNotMatchPasswordsErrorMessage();

        Assert.assertEquals(actualDoNotMatchPasswordsErrorMessage, "Passwords do not match!", "Error message is not as expected.");
    }
}